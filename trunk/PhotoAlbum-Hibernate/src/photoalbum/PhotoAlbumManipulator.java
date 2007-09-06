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
	
	public User getUserByUsername(String username) {
		return getHbConnection().getUserByUserName(username);
	}
	
	public User addUser(String username, String password, String firstName, String lastName) throws CreateUserException {
		User user = getHbConnection().getUserByUserName(username);
		if (user == null) {
			user = new User(username, password, firstName, lastName);
			try {
				FileSystemManager.addUser(username);
				getHbConnection().save(user);
			} catch (Throwable e) {				
				Logger.getDefaultInstance().log(e);
				throw new CreateUserException("Cannot create user [" + username + "].", e);
			}
		} else {
			throw new CreateUserException("User [" + username + "] already exists.");			
		}
		return user;
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
	
	public void editUser(User user, String username) {		
		getHbConnection().update(user);
	}
	
	public void deleteObject(Object obj) throws Throwable {
		try {
			FileSystemManager.deleteObject(obj);
			getHbConnection().delete(obj);
		} catch (Throwable e) {
			Logger.getDefaultInstance().log(e);
			throw e;
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
