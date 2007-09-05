package photoalbum.filesystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import photoalbum.PhotoAlbumManager;
import photoalbum.entities.Category;
import photoalbum.entities.Photo;
import photoalbum.entities.User;
import photoalbum.logging.Logger;

public class FileSystemManager {
	
	public static final String ROOT_DIR = PhotoAlbumManager.ROOT_DIR;
	
	public static final int BUFF_SIZE = 1 * 1024 * 1024;
	
	public static final String SEPARATOR = "/";
	
	public static boolean deleteFile(String fileName) {
		return deleteFile(new File(fileName));
	}
	
	public static boolean deleteFile(File file) {
		File[] children = file.listFiles();
		if (children != null) {
			for (File child : children) {
				deleteFile(child);
			}
		}
		return file.delete();
	}
	
	public static boolean deleteObject(Object obj) {
		if (obj instanceof User) {
			return deleteUser((User) obj);
		} else if (obj instanceof Category) {
			return deleteCategory((Category) obj);
		} else if (obj instanceof Photo) {
			return deletePhoto((Photo) obj);
		}
		return false;
	}
	
	public static void addUser(User user) {
		addUser(user.getUsername());
	}
	
	public static void addUser(String username) {
		File userDirectory = new File(ROOT_DIR + SEPARATOR + username);
		if (!userDirectory.exists()) {
			userDirectory.mkdirs();
		}
	}
	
	public static boolean deleteUser(User user) {
		return deleteUser(user.getUsername());
	}
	
	public static boolean deleteUser(String username) {
		File userDirectory = new File(ROOT_DIR + SEPARATOR + username);
		return deleteFile(userDirectory);
	}
	
	public static void addCategory(Category category) {
		addCategory(category.getPath());
	}
	
	public static void addCategory(String path) {
		File categoryDirectory = new File(path);
		if (!categoryDirectory.exists()) {
			categoryDirectory.mkdirs();
		}
	}
	
	public static boolean deleteCategory(Category category) {
		return deleteCategory(category.getPath());
	}
	
	public static boolean deleteCategory(String path) {
		File file = new File(path);
		return deleteFile(file);
	}
	
	public static void addPhoto(Photo photo, InputStream iStream) throws FileSystemException {
		addPhoto(photo.getPath(), iStream);
	}
	
	public static void addPhoto(String path, InputStream iStream) throws FileSystemException {
		if (iStream == null) {
			return;
		}
		
		File image = new File(path);
		if (!image.getParentFile().exists()) {
			image.getParentFile().mkdirs();
		}
		OutputStream oStream = null;		
		
		try {
			oStream = new FileOutputStream(image);
			
			byte[] buffer = new byte[BUFF_SIZE];
			int bytesRead = iStream.read(buffer);
			
			while (bytesRead != -1) {
				oStream.write(buffer, 0, bytesRead);
				bytesRead = iStream.read(buffer);
			}
		} catch (FileNotFoundException e) {
			Logger.getDefaultInstance().log(e);
			throw new FileSystemException("Cannot create file [" + image.getName() + "].", e);
		} catch (IOException e) {
			Logger.getDefaultInstance().log(e);
			throw new FileSystemException("Cannot create file [" + image.getName() + "].", e);
		} finally {
			if (oStream != null) {
				try {
					oStream.close();
				} catch (IOException e) {
					Logger.getDefaultInstance().log(e);
				}
			}
		}
	}
	
	public static boolean deletePhoto(Photo photo) {
		return deletePhoto(photo.getPath());
	}
	
	public static boolean deletePhoto(String path) {
		File file = new File(path);
		return deleteFile(file);
	}
	
	public static String getFileExtension(File file) {
		String fileExtension = "";
		String fileName = file.getName();
		int index = fileName.lastIndexOf(".");
		if (index != -1 && index != fileName.length() - 1) {
			fileExtension = file.getName().substring(index + 1);
		}		
		return fileExtension;
	}

}
