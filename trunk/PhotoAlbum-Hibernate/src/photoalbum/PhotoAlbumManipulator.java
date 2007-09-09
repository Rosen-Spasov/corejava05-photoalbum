package photoalbum;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import photoalbum.entities.Category;
import photoalbum.entities.Photo;
import photoalbum.entities.User;
import photoalbum.entities.interfaces.ICategoryContainer;
import photoalbum.filesystem.FileSystemException;
import photoalbum.filesystem.FileSystemManager;
import photoalbum.hibernate.HibernateConnection;
import photoalbum.hibernate.HibernateConnectionManager;
import photoalbum.logging.Logger;

public class PhotoAlbumManipulator {
	
	public static final String ROOT_DIR = "../PhotoAlbum";
	
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
			try {
				FileSystemManager.addUser(user.getUsername());
				getHbConnection().save(user);
			} catch (Throwable e) {				
				Logger.getDefaultInstance().log(e);
				throw new CreateUserException("Cannot create user [" + user.getUsername() + "].", e);
			}
		} else {
			throw new CreateUserException("User [" + user.getUsername() + "] already exists.");
		}
	}
	
	public void addFileStructure(Object parentObject, File[] selectedFiles) {
		for (File selectedFile : selectedFiles) {
			if (selectedFile.isDirectory()) {
				addCategory(parentObject, selectedFile);
			} else if (isValidImage(selectedFile) && parentObject instanceof Category) {
				addPhoto((Category) parentObject, selectedFile);
			}
		}
		getHbConnection().update(parentObject);
	}
	
	private Category addCategory(Object parentObject, File category) {
		if (parentObject instanceof ICategoryContainer) {
			return addCategory((ICategoryContainer) parentObject, category);
		}
		return null;
	}
	
	private Category addCategory(ICategoryContainer parentObject, File directory) {		
		boolean parentIsUser = true;
		
		String catName = directory.getName();
		String path = "";
		if (parentObject instanceof User) {
			path = FileSystemManager.ROOT_DIR + FileSystemManager.SEPARATOR + ((User) parentObject).getUsername() + "/" + catName;
		} else if (parentObject instanceof Category) {
			path = ((Category) parentObject).getPath() + "/" + catName;
			parentIsUser = false;
		}
		
		Category category = getHbConnection().getCategoryByPath(path);
		if (category == null) {
			category = new Category();
			category.setCatName(catName);
			category.setPath(path);
			if (parentIsUser) {
				category.setUser((User) parentObject);
			}
			FileSystemManager.addCategory(path);
			parentObject.getCategories().add(category);
		}
		File[] children = directory.listFiles();
		for (File child : children) {
			if (child.isDirectory()) {
				addCategory(category, child);
			} else if (isValidImage(child)) {
				addPhoto(category, child);
			}
		}
		return category;
	}
	
	public Category getCategoryById(int categoryId) {
		return getHbConnection().getCategoryById(categoryId);
	}
	
	private Photo addPhoto(Category parent, File imageFile) {
		
		String phName = imageFile.getName();
		String path = parent.getPath() + "/" + phName;
		Photo photo = getHbConnection().getPhotoByPath(path);
		if (photo == null) {
			photo = new Photo();
			photo.setPhName(phName);
			photo.setPath(path);
			photo.setCategory(parent);
			try {
				InputStream iStream = new FileInputStream(imageFile);
				FileSystemManager.addPhoto(path, iStream);
				parent.getPhotos().add(photo);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (FileSystemException e) {
				e.printStackTrace();
			}
		}
		
		return photo;
	}
	
	public void editUser(User user) {
		int userId = user.getUserId();
		editUser(userId);
	}
	
	public void editUser(int userId) {
		User user = getUserById(userId);
		
		getHbConnection().beginTransaction();
		try {
			getHbConnection().update(user);
			getHbConnection().commit();
		} catch (Throwable e) {
			getHbConnection().rollback();
			Logger.getDefaultInstance().log(e);
		}
	}
	
	public void delete(Object obj) {
		if (obj instanceof User) {
			User user = (User) obj;
			deleteUser(user.getUserId());
		} else if (obj instanceof Category) {
			Category category = (Category) obj;
			deleteCategory(category.getCategoryId());
		} else if (obj instanceof Photo) {
			Photo photo = (Photo) obj;
			deletePhoto(photo.getPhotoId());
		}
	}
	
	public void deleteUser(int userId) {
		User user = getHbConnection().getUserById(userId);
		deleteObject(user);
	}
	
	public void deleteCategory(int categoryId) {
		Category category = getHbConnection().getCategoryById(categoryId);
		deleteObject(category);
	}
	
	public void deletePhoto(int photoId) {
		Photo photo = getHbConnection().getPhotoById(photoId);
		deleteObject(photo);
	}
	
	private void deleteObject(Object obj) {
		getHbConnection().beginTransaction();
		try {
			getHbConnection().delete(obj);
			FileSystemManager.delete(obj);
			getHbConnection().commit();
		} catch (Throwable e) {
			getHbConnection().rollback();
			Logger.getDefaultInstance().log(e);
		}
	}
	
	public boolean isValidImage(File image) {
		return 	image.isFile() && (
				image.getName().endsWith(".jpg") ||
				image.getName().endsWith(".gif") ||
				image.getName().endsWith(".png")
				);
	}

}
