package photoalbum;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Set;

import photoalbum.entities.Category;
import photoalbum.entities.Photo;
import photoalbum.entities.User;
import photoalbum.entities.interfaces.ICategoryContainer;
import photoalbum.filesystem.FileSystemManager;
import photoalbum.hibernate.HibernateConnection;
import photoalbum.hibernate.HibernateConnectionManager;
import photoalbum.logging.Logger;

public class PhotoAlbumManipulator {
	
	public static final String LOG_FILENAME = "PhotoAlbumManipulator.log";
	
	private static Logger logger = null;
	
	private static Logger getLogger() {
		if (logger == null) {
			logger = Logger.getLogger(LOG_FILENAME);
		}
		return logger;
	}
	
	public static boolean adminAccessGranted(String password) {
		return HibernateConnectionManager.adminAccessGranted(password);
	}
	
	private HibernateConnection hbConnection = null;

	private HibernateConnection getHbConnection() {
		if (hbConnection == null) {
			hbConnection = HibernateConnectionManager.openConnection();
		}
		return hbConnection;
	}
	
	public boolean accessGranted(String userName, String password) {
		
		User user = getHbConnection().getUserByUserName(userName);
		if (user != null && user.getPassword().equals(password)) {
			return true;
		}
		getHbConnection().close();

		return false;
	}
	
	public User[] getAllUsers() {
		List<User> usersList = getHbConnection().getAllUsers();
		User[] users = new User[usersList.size()];
		usersList.toArray(users);
		
		return users;
	}
	
	public User getUserById(int userId) {
		return getHbConnection().getUserById(userId);
	}
	
	public User getUserByUsername(String username) {
		return getHbConnection().getUserByUserName(username);
	}
	
	public void addUser(String username, String password, String firstName, String lastName) throws CreateUserException {
		User user = new User(username, password, firstName, lastName);
		addUser(user);
	}
	
	public void addUser(User user) throws CreateUserException {
		if (getHbConnection().getUserByUserName(user.getUsername()) == null) {
			getHbConnection().beginTransaction();
			try {
				getHbConnection().save(user);
				FileSystemManager.addUser(user);				
				getHbConnection().commit();
			} catch (Throwable e) {
				getHbConnection().rollback();
				getLogger().log(e);
				throw new CreateUserException("Cannot create user [" + user.getUsername() + "].", e);
			}
		} else {
			throw new CreateUserException("User [" + user.getUsername() + "] already exists.");
		}
	}
	
	public Category addCategory(ICategoryContainer parent, String catName) throws CreateCategoryException {
		if (parent == null) {
			return null;
		}
		
		ICategoryContainer parentInDB = null;
		if (parent instanceof User) {
			int userId = ((User) parent).getUserId();
			parentInDB = getHbConnection().getUserById(userId);
		} else if (parent instanceof Category) {
			int categoryId = ((Category) parent).getCategoryId();
			parentInDB = getHbConnection().getCategoryById(categoryId);
		}
		if (parentInDB != null) {
			parent = parentInDB;
		}

		String path = FileSystemManager.getPathForChild(parent, catName);
		Category category = getCategoryByPath(path);
		if (category == null) {
			category = new Category(catName, path, parent);
			getHbConnection().beginTransaction();
			try {
				getHbConnection().save(category);
				FileSystemManager.addCategory(category);
				parent.add(category);
				getHbConnection().commit();
			} catch (Throwable e) {
				getHbConnection().rollback();
				getLogger().log(e);
				throw new CreateCategoryException("Cannot create category [" + category.getPath() + "].", e);
			}
		}
		
		return category;
	}
	
	public Category getCategoryById(int categoryId) {
		return getHbConnection().getCategoryById(categoryId);
	}
	
	public Category getCategoryByPath(String path) {
		return getHbConnection().getCategoryByPath(path);
	}
	
	public Photo getPhotoById(int photoId) {
		return getHbConnection().getPhotoById(photoId);
	}
	
	public Photo getPhotoByPath(String path) {
		return getHbConnection().getPhotoByPath(path);
	}
	
	public Photo addPhoto(Category category, File imageFile) throws CreatePhotoException {
		if (category == null || imageFile == null) {
			return null;
		}
		int categoryId = ((Category) category).getCategoryId();
		Category categoryInDB = getHbConnection().getCategoryById(categoryId);
		if (categoryInDB != null) {
			category = categoryInDB;
		}
		
		String phName = imageFile.getName();
		String path = FileSystemManager.getPathForChild(category, phName);
		Photo photo = getPhotoByPath(path);
		if (photo == null) {
			photo = new Photo(phName, path, category);
			getHbConnection().beginTransaction();
			try {
				InputStream iStream = new FileInputStream(imageFile);
				getHbConnection().save(photo);
				FileSystemManager.addPhoto(photo, iStream);
				category.add(photo);
				getHbConnection().commit();
			} catch (Throwable e) {
				getHbConnection().rollback();
				getLogger().log(e);
				throw new CreatePhotoException("Cannot create photo [" + photo.getPath() + "].", e);
			}
		}
		
		return photo;
	}
	
	public Photo addPhoto(Category category, String phName, byte[] image) throws CreatePhotoException {
		if (category == null) {
			return null;
		}
		
		int categoryId = ((Category) category).getCategoryId();
		Category categoryInDB = getHbConnection().getCategoryById(categoryId);
		if (categoryInDB != null) {
			category = categoryInDB;
		}

		String path = FileSystemManager.getPathForChild(category, phName);
		Photo photo = getPhotoByPath(path);
		if (photo == null) {
			photo = new Photo(phName, path, category);
			getHbConnection().beginTransaction();
			try {
				getHbConnection().save(photo);
				FileSystemManager.addPhoto(photo, image);
				category.add(photo);
				getHbConnection().commit();
			} catch (Throwable e) {
				getHbConnection().rollback();
				getLogger().log(e);
				throw new CreatePhotoException("Cannot create photo [" + photo.getPath() + "].", e);
			}
		}
		
		return photo;
	}
	
	public void updateUser(User user) {
		int userId = user.getUserId();
		
		User userInDB = getUserById(userId);
		
		if (userInDB != null) {
			userInDB.setUsername(user.getUsername());
			userInDB.setFirstName(user.getFirstName());
			userInDB.setLastName(user.getLastName());
			userInDB.setPassword(user.getPassword());
			
			getHbConnection().beginTransaction();
			try {
				getHbConnection().update(userInDB);
				getHbConnection().commit();
			} catch (Throwable e) {
				getHbConnection().rollback();
				getLogger().log(e);
			}
		}
	}
	
	public void delete(Object obj) {
		if (obj instanceof User) {
			User user = (User) obj;
			deleteUser(user);
		} else if (obj instanceof Category) {
			Category category = (Category) obj;
			deleteCategory(category);
		} else if (obj instanceof Photo) {
			Photo photo = (Photo) obj;
			deletePhoto(photo);
		}
	}
	
	public void deleteUser(User user) {
		if (user == null) {
			return;
		}
		User userInDB = getHbConnection().getUserById(user.getUserId());		
		deleteObject(userInDB);
	}
	
	public void deleteCategory(Category category) {
		if (category == null) {
			return;
		}
		Category categoryInDB = getHbConnection().getCategoryById(category.getCategoryId());
		categoryInDB.getParent().remove(categoryInDB);
		deleteObject(categoryInDB);
	}
	
	public void deletePhoto(Photo photo) {
		if (photo == null) {
			return;
		}
		Photo photoInDB = getHbConnection().getPhotoById(photo.getPhotoId());
		photoInDB.getCategory().remove(photoInDB);
		deleteObject(photoInDB);
	}
	
	private void deleteObject(Object obj) {
		getHbConnection().beginTransaction();
		try {
			getHbConnection().delete(obj);
			FileSystemManager.delete(obj);
			getHbConnection().commit();
		} catch (Throwable e) {
			getHbConnection().rollback();
			getLogger().log(e);
		}
	}
	
	public void refresh(Object obj) {
		getHbConnection().refresh(obj);
	}
	
	public void refreshAll(Object[] objects) {
		for (Object obj : objects) {
			refresh(obj);
		}
		User[] users = getAllUsers();
		for (User user : users) {
			refresh(user);
		}
	}
	
	public String getAbsolutePath(Object obj) {
		String absolutePath = FileSystemManager.getAbsolutePath(obj);
		return absolutePath;
	}
	
	public Category renameCategory(Category category, String catName) {
		if (category == null) {
			return null;
		}
		Category categoryInDB = getHbConnection().getCategoryById(category.getCategoryId());
		if (categoryInDB != null) {
			updateCategoryName(categoryInDB, catName);
		}
		
		return categoryInDB;
	}
	
	private void updateCategoryName(Category category, String catName) {
		if (category == null) {
			return;
		}
		
		String path = category.getPath();
		int lastSlashIndex = path.lastIndexOf(FileSystemManager.SEPARATOR);
		if (lastSlashIndex != -1) {
			path = path.substring(0, lastSlashIndex + 1) + catName;
		}
		FileSystemManager.renameFile(category.getPath(), path);
		category.setCatName(catName);
		category.setPath(path);
		
		Set<Category> children = category.getCategories();
		for (Category child : children) {
			updateParentPath(child, path);
		}
		Set<Photo> photos = category.getPhotos();
		for (Photo photo : photos) {
			updateParentPath(photo, path);
		}
		updateInDB(category);
	}
	
	public Photo renamePhoto(Photo photo, String phName) {
		if (photo == null) {
			return null;
		}
		
		Photo photoInDB = getHbConnection().getPhotoById(photo.getPhotoId());
		if (photoInDB != null) {
			updatePhotoName(photoInDB, phName);
		}
		
		return photoInDB;
	}
	
	private void updatePhotoName(Photo photo, String phName) {
		if (photo == null) {
			return;
		}
		
		String path = photo.getPath();
		int lastSlashIndex = path.lastIndexOf(FileSystemManager.SEPARATOR);
		if (lastSlashIndex != -1) {
			path = path.substring(0, lastSlashIndex + 1) + phName;
		}
		FileSystemManager.renameFile(photo.getPath(), path);
		photo.setPhName(phName);
		photo.setPath(path);
		updateInDB(photo);
	}
	
	private void updateParentPath(Object obj, String parentPath) {
		if (obj == null || parentPath == null) {
			return;
		}
		
		if (obj instanceof Category) {
			String path = parentPath + FileSystemManager.SEPARATOR + ((Category) obj).getCatName();
//			FileSystemManager.renameFile( ((Category) obj).getPath(), path);
			((Category) obj).setPath(path);
		} else if (obj instanceof Photo) {
			String path = parentPath + FileSystemManager.SEPARATOR + ((Photo) obj).getPhName();
//			FileSystemManager.renameFile( ((Photo) obj).getPath(), path);
			((Photo) obj).setPath(path);
		}
	}
	
	private void updateInDB(Object obj) {
		getHbConnection().beginTransaction();
		try {
			getHbConnection().update(obj);
			getHbConnection().commit();
		} catch (Throwable e) {
			getHbConnection().rollback();
			getLogger().log(e);
		}
	}

}
