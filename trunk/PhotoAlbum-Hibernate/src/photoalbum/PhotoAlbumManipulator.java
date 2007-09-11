package photoalbum;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import photoalbum.entities.Category;
import photoalbum.entities.Photo;
import photoalbum.entities.User;
import photoalbum.entities.interfaces.ICategoryContainer;
import photoalbum.filesystem.FileSystemManager;
import photoalbum.hibernate.HibernateConnection;
import photoalbum.hibernate.HibernateConnectionManager;
import photoalbum.logging.Logger;

public class PhotoAlbumManipulator {
	
	public static final String ROOT_DIR = "../PhotoAlbum";
	
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
				FileSystemManager.addUser(user);
				getHbConnection().save(user);
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

		String path = FileSystemManager.getPathForChild(parent, catName);
		Category category = getCategoryByPath(path);
		if (category == null) {
			category = new Category(catName, path, parent);
			getHbConnection().beginTransaction();
			try {
				FileSystemManager.addCategory(category);
				getHbConnection().save(category);
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
	
	public Photo getPhotoByPath(String path) {
		return getHbConnection().getPhotoByPath(path);
	}
	
	public Photo addPhoto(Category category, File imageFile) throws CreatePhotoException {
		if (category == null || imageFile == null) {
			return null;
		}
		
		String phName = imageFile.getName();
		String path = FileSystemManager.getPathForChild(category, phName);
		Photo photo = getPhotoByPath(path);
		if (photo == null) {
			photo = new Photo(phName, path, category);
			getHbConnection().beginTransaction();
			try {
				InputStream iStream = new FileInputStream(imageFile);
				FileSystemManager.addPhoto(photo, iStream);
				getHbConnection().save(photo);
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

		String path = FileSystemManager.getPathForChild(category, phName);
		Photo photo = getPhotoByPath(path);
		if (photo == null) {
			photo = new Photo(phName, path, category);
			getHbConnection().beginTransaction();
			try {
				FileSystemManager.addPhoto(photo, image);
				getHbConnection().save(photo);
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
		User userInDB = getHbConnection().getUserById(user.getUserId());		
		deleteObject(userInDB);
	}
	
	public void deleteCategory(Category category) {
		Category categoryInDB = getHbConnection().getCategoryById(category.getCategoryId());
		categoryInDB.getParent().remove(categoryInDB);
		deleteObject(categoryInDB);
	}
	
	public void deletePhoto(Photo photo) {
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

}
