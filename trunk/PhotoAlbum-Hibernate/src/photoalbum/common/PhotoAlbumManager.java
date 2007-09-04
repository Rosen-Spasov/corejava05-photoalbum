package photoalbum.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
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

public class PhotoAlbumManager {
	
	public static enum DialogResult {CONNECT, CREATE, OK, CANCEL, YES, NO}
	
	public static enum DBProvider {ORACLE, MICROSOFT};
	
	public static final String NEW_LINE = System.getProperty("line.separator");
	
	public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
	
	public static final String LOGS_DIRECTORY = "./logs";
	
	public static final String DEFAULT_LOG_FILENAME = "default.log";
	
	public static final String ROOT_DIR = "../PhotoAlbum";
	
	public static final SimpleDateFormat defaultDateTimeFormat = new SimpleDateFormat(PhotoAlbumManager.DEFAULT_DATE_TIME_FORMAT);
	
	public static final SimpleDateFormat defaultDateFormat = new SimpleDateFormat(PhotoAlbumManager.DEFAULT_DATE_FORMAT);
	
	public static boolean parentDirExists(String fileName) {
		File file = new File(fileName);
		return parentDirExists(file);
	}
	
	public static boolean parentDirExists(File file) {
		return file.getParentFile().exists();
	}
	
	public static boolean createParentDirs(String fileName) {
		File file = new File(fileName);
		return createParentDirs(file);
	}
	
	public static boolean createParentDirs(File file) {
		return file.getParentFile().mkdirs();
	}
	
	private static HibernateConnection hbConnection = null;
	
	private static HibernateConnection getHbConnection() {
		if (hbConnection == null || hbConnection.isReleased()) {
			hbConnection = HibernateConnectionManager.openConnection();
		}
		return hbConnection;
	}
	
	public static User[] getAllUsers() {
		List<User> usersList = getHbConnection().getAllUsers();
		User[] users = new User[usersList.size()];
		usersList.toArray(users);
		
		return users;
	}
	
	public static User addUser(String username, String password, String firstName, String lastName) throws CreateUserException {
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
	
	public static void addFileStructure(Object parentObject, File[] selectedFiles) {
		for (File selectedFile : selectedFiles) {
			if (selectedFile.isDirectory()) {
				addCategory(parentObject, selectedFile);
			} else if (isValidFile(selectedFile) && parentObject instanceof Category) {
				addPhoto((Category) parentObject, selectedFile);
			}
		}
		getHbConnection().update(parentObject);
	}
	
	private static Category addCategory(Object parentObject, File category) {
		if (parentObject instanceof ICategoryContainer) {
			return addCategory((ICategoryContainer) parentObject, category);
		}
		return null;
	}
	
	private static Category addCategory(ICategoryContainer parentObject, File directory) {		
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
			} else if (isValidFile(child)) {
				addPhoto(category, child);
			}
		}
		return category;
	}
	
	private static Photo addPhoto(Category parent, File imageFile) {
		
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
	
	public static void editUser(User user, String username) {		
		getHbConnection().update(user);
	}
	
	public static void deleteObject(Object obj) throws Throwable {
		try {
			FileSystemManager.deleteObject(obj);
			getHbConnection().delete(obj);
		} catch (Throwable e) {
			Logger.getDefaultInstance().log(e);
			throw e;
		}
	}
	
	public static boolean isValidFile(File file) {
		return 	file.isFile() && (
				file.getName().endsWith(".jpg") ||
				file.getName().endsWith(".gif") ||
				file.getName().endsWith(".png")
				);
	}
	
}
