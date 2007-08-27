package common;

import java.text.SimpleDateFormat;

public class Common {
	
	public static final String NEW_LINE = System.getProperty("line.separator");
	
	public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
	
	public static final String LOGS_DIRECTORY = "./logs";
	
	public static final String DEFAULT_LOG_FILENAME = "default.log";
	
//	public static final String QUERIES_LOG = "queries.log";
	
	public static final SimpleDateFormat defaultDateTimeFormat = new SimpleDateFormat(Common.DEFAULT_DATE_TIME_FORMAT);
	
	public static final SimpleDateFormat defaultDateFormat = new SimpleDateFormat(Common.DEFAULT_DATE_FORMAT);
	
}
