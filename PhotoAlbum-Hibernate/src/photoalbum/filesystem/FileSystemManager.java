package photoalbum.filesystem;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;

import photoalbum.PhotoAlbumManipulator;
import photoalbum.entities.Category;
import photoalbum.entities.Photo;
import photoalbum.entities.User;
import photoalbum.logging.Logger;

public abstract class FileSystemManager {
	
	public static final String ROOT_DIR = PhotoAlbumManipulator.ROOT_DIR;
	
	public static final int BUFF_SIZE = 1 * 1024 * 1024;
	
	public static final String SEPARATOR = "/";
	
	public static String getPathForChild(Object parent, String childName) {
		String path = null;
		
		if (parent instanceof User) {
			path = ROOT_DIR + SEPARATOR + ((User) parent).getUsername() + SEPARATOR + childName;
		} else if (parent instanceof Category) {
			path = ((Category) parent).getPath() + SEPARATOR + childName;
		}

		return path;
	}
	
	public static boolean isValidImage(File image) {
		return 	image.isFile() && (
				image.getName().endsWith(".jpg") ||
				image.getName().endsWith(".gif") ||
				image.getName().endsWith(".png")
				);
	}
	
	public static byte[] getImage(File imageFile) {
		byte[] result = null;
		
		if (isValidImage(imageFile)) {
			FileInputStream fiStream = null;
			BufferedInputStream buffStream = null;
			ByteArrayOutputStream byteArray = null;
			try {				
				fiStream = new FileInputStream(imageFile);
				buffStream = new BufferedInputStream(fiStream);
				
				byteArray = new ByteArrayOutputStream();
				
				byte[] buffer = new byte[BUFF_SIZE];
				int bytesRead = -1;
				
				do {
					bytesRead = buffStream.read(buffer);
					byteArray.write(buffer, 0, bytesRead);
				} while (bytesRead != -1);
				
				result = byteArray.toByteArray();
				
			} catch (FileNotFoundException e) {
				Logger.getDefaultInstance().log(e);
			} catch (IOException e) {
				Logger.getDefaultInstance().log(e);
			} finally {
				try {
					if (byteArray != null) {
						byteArray.close();
					}
					if (buffStream != null) {
						buffStream.close();
					}
					if (fiStream != null) {
						fiStream.close();
					}
				} catch (IOException e) {
					Logger.getDefaultInstance().log(e);
				}
			}
		}
		
		return result;
	}
	
	public static boolean renameFile(String fileName, String newFileName) {
		File file = new File(fileName);
		File newFile = new File(newFileName);
		
		boolean result = renameFile(file, newFile);
		return result;
	}
	
	public static boolean renameFile(File file, File newFile) {
		boolean result = file.renameTo(newFile);
		return result;
	}
	
	public static boolean deleteFile(String fileName) {
		boolean result = deleteFile(new File(fileName));
		return result;
	}
	
	public static boolean deleteFile(File file) {
		File[] children = file.listFiles();
		if (children != null) {
			for (File child : children) {
				deleteFile(child);
			}
		}
		
		boolean result = file.delete();
		return result;
	}
	
	public static boolean addUser(User user) throws FileSystemException {
		if (user == null) {
			return false;
		}
		
		boolean result = true;
		
		File userDirectory = new File(ROOT_DIR + SEPARATOR + user.getUsername());
		if (!userDirectory.exists()) {
			result = result && userDirectory.mkdirs();
		}
		Set<Category> categories = user.getCategories();
		for (Category category : categories) {
			result = result && addCategory(category);
		}
		
		return result;
	}
	
	public static boolean addCategory(Category category) throws FileSystemException {
		if (category == null) {
			return false;
		}
		
		boolean result = true;
		
		
		File categoryDirectory = new File(category.getPath());
		if (!categoryDirectory.exists()) {
			result = result && categoryDirectory.mkdirs();
		}
		Set<Category> children = category.getCategories();
		for (Category child : children) {
			result = result && addCategory(child);
		}
		
		return result;
	}
	
	public static boolean addPhoto(Photo photo, byte[] image) throws FileSystemException {
		if (photo == null) {
			return false;
		}
		
		ByteArrayInputStream byteArray = new ByteArrayInputStream(image);
		return addPhoto(photo, byteArray);
	}
	
	public static boolean addPhoto(Photo photo, InputStream iStream) throws FileSystemException {
		if (photo == null || iStream == null) {
			return false;
		}
		
		File imageFile = new File(photo.getPath());
		File parent = imageFile.getParentFile();
		if (parent != null && !parent.exists()) {
			parent.mkdirs();
		}
		
		OutputStream oStream = null;		
		try {
			oStream = new FileOutputStream(imageFile);
			
			byte[] buffer = new byte[BUFF_SIZE];
			int bytesRead = iStream.read(buffer);
			
			while (bytesRead != -1) {
				oStream.write(buffer, 0, bytesRead);
				bytesRead = iStream.read(buffer);
			}
		} catch (FileNotFoundException e) {
			Logger.getDefaultInstance().log(e);
			throw new FileSystemException("Cannot create file [" + imageFile.getAbsolutePath() + "].", e);
		} catch (IOException e) {
			Logger.getDefaultInstance().log(e);
			throw new FileSystemException("Cannot create/write file [" + imageFile.getAbsolutePath() + "].", e);
		} finally {
			if (oStream != null) {
				try {
					oStream.close();
				} catch (IOException e) {
					Logger.getDefaultInstance().log(e);
					throw new FileSystemException("Cannot close the output stream for file [" + imageFile.getAbsolutePath() + "].", e);
				}
			}
		}
		
		return true;
	}
	
	public static boolean delete(Object obj) {
		if (obj == null) {
			return false;
		}
		
		boolean result = false;
		if (obj instanceof User) {
			result = deleteUser((User) obj);
		} else if (obj instanceof Category) {
			result = deleteCategory((Category) obj);
		} else if (obj instanceof Photo) {
			result = deletePhoto((Photo) obj);
		}
		return result;
	}
	
	public static boolean deleteUser(User user) {
		if (user == null) {
			return false;
		}
		
		boolean result = deleteUser(user.getUsername());
		return result;
	}
	
	public static boolean deleteUser(String username) {
		File userDirectory = new File(ROOT_DIR + SEPARATOR + username);
		return deleteFile(userDirectory);
	}
	
	public static boolean deleteCategory(Category category) {
		File file = new File(category.getPath());
		return deleteFile(file);
	}
	
	public static boolean deletePhoto(Photo photo) {
		File file = new File(photo.getPath());
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
