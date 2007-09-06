package photoalbum.util;

import java.io.File;
import java.text.SimpleDateFormat;

public class Utilities {
	
	public static final String NEW_LINE = System.getProperty("line.separator");
	
	public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
	
	public static final String LOGS_DIRECTORY = "./logs";
	
	public static final String DEFAULT_LOG_FILENAME = "default.log";
	
	public static final SimpleDateFormat defaultDateTimeFormat = new SimpleDateFormat(DEFAULT_DATE_TIME_FORMAT);
	
	public static final SimpleDateFormat defaultDateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
	
	public static boolean parentDirExists(String fileName) {
		File file = new File(fileName);
		return parentDirExists(file);
	}
	
	public static boolean parentDirExists(File file) {
		if (file.getParentFile() != null && file.getParentFile().exists()) {
			return true;
		}
		return false;
	}
	
	public static boolean createParentDirs(String fileName) {
		File file = new File(fileName);
		return createParentDirs(file);
	}
	
	public static boolean createParentDirs(File file) {
		return file.getParentFile().mkdirs();
	}

}
