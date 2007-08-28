package logging;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Hashtable;

import common.Common;

public class Logger {
	
	public static final String LOGS_DIRECTORY = Common.LOGS_DIRECTORY;
	
	public static final String DEFAULT_LOG_FILENAME = Common.DEFAULT_LOG_FILENAME;
	
	private static Logger defaultInstance = null;
	
	private static Hashtable<String, Logger> loggers = null;
	
	private String logFileName = Logger.LOGS_DIRECTORY + "/" + Logger.DEFAULT_LOG_FILENAME;
	
	private PrintWriter logWriter = null;
	
	private Logger() {
	}
	
	private Logger(String logFileName) {
		this.setLogFileName(logFileName);
	}

	private static Hashtable<String, Logger> getLoggers() {
		if (loggers == null) {
			loggers = new Hashtable<String, Logger>();
		}
		return loggers;
	}
	
	public static Logger getDefaultInstance() {
		if (Logger.defaultInstance == null) {
			Logger.defaultInstance = new Logger();
			Logger.getLoggers().put(Logger.defaultInstance.getLogFileName(), Logger.defaultInstance);
		}
		return Logger.defaultInstance;
	}
	
	public static Logger getLogger(String logFileName) {
		Logger result = null;
		if (logFileName != null) {
			result = Logger.getLoggers().get(logFileName);
			if (result == null) {
				result = new Logger(logFileName);
				Logger.getLoggers().put(result.getLogFileName(), result);
			}
		}
		return result;
	}
	
	public String getLogFileName() {
		return this.logFileName;
	}
	
	private void setLogFileName(String logFileName) {
		if (logFileName != null) {
			this.logFileName = Logger.LOGS_DIRECTORY + "/" + logFileName;
		} else {
			throw new IllegalArgumentException("[null] is not a valid argument for [void setLogFileName(String)].");
		}
	}
	
	private synchronized PrintWriter getLogWriter() {
		if (this.logWriter == null) {
			try {
				FileWriter fWriter = new FileWriter(this.getLogFileName(), true);
				this.logWriter = new PrintWriter(fWriter);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return this.logWriter;
	}
	
	private synchronized void setLogWriter(PrintWriter logWriter) {
		if (logWriter != null) {
			this.logWriter = logWriter;
		} else {
			throw new IllegalArgumentException("[null] is not a valid argument for [void setLogWriter(PrintWriter)].");
		}
	}
	
	public synchronized void log(String msg) {
		Date currentTime = new Date();
		String currentTimeString = Common.defaultDateTimeFormat.format(currentTime);
		this.getLogWriter().println("*************************************************************************");
		this.getLogWriter().println("[" + currentTimeString + "]");
		this.getLogWriter().println(msg);
		this.getLogWriter().close();
		this.setLogWriter(null);
	}
	
	public synchronized void log(Throwable exc) {
		this.log(exc, "");
	}
	
	private synchronized void log(Throwable exc, String indent) {
		Date currentTime = new Date();
		String currentTimeString = Common.defaultDateTimeFormat.format(currentTime);
		this.getLogWriter().println("*************************************************************************");
		this.getLogWriter().println(indent + "[" + currentTimeString + "] ERROR");
		this.getLogWriter().println(indent + exc.getMessage());
		StackTraceElement[] stackTrace = exc.getStackTrace();
		for (StackTraceElement stackTraceElement : stackTrace) {
			this.getLogWriter().println(indent + stackTraceElement.toString());
		}
		Throwable cause = exc.getCause();
		if (cause != null) {
			this.getLogWriter().println(indent + "Caused by:");
			this.log(cause, indent + "\t");
		}
		this.getLogWriter().close();
		this.setLogWriter(null);
	}
	
}
