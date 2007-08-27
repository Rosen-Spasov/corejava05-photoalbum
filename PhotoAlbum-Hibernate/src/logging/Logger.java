package logging;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import common.Common;

public class Logger {
	
	public static final String LOGS_DIRECTORY = Common.LOGS_DIRECTORY;
	
	public static final String DEFAULT_LOG_FILENAME = Common.DEFAULT_LOG_FILENAME;
	
	private static Logger defaultInstance = null;
	
	private String logFileName = Logger.LOGS_DIRECTORY + "/" + Logger.DEFAULT_LOG_FILENAME;
	
	private PrintWriter logWriter = null;
	
	public static Logger getDefaultInstance() {
		if (Logger.defaultInstance == null) {
			Logger.defaultInstance = new Logger();
		}
		return Logger.defaultInstance;
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
	
	private PrintWriter getLogWriter() {
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
	
	private void setLogWriter(PrintWriter logWriter) {
		if (logWriter != null) {
			this.logWriter = logWriter;
		} else {
			throw new IllegalArgumentException("[null] is not a valid argument for [void setLogWriter(PrintWriter)].");
		}
	}
	
	private Logger() {
	}
	
	public Logger(String logFileName) {
		this.setLogFileName(logFileName);
	}
	
	public void log(String msg) {
		Date currentTime = new Date();
		String currentTimeString = Common.defaultDateTimeFormat.format(currentTime);
		this.getLogWriter().println("*************************************************************************");
		this.getLogWriter().println("[" + currentTimeString + "]");
		this.getLogWriter().println(msg);
		this.getLogWriter().close();
		this.setLogWriter(null);
	}
	
	public void log(Throwable exc) {
		this.log(exc, "");
	}
	
	private void log(Throwable exc, String indent) {
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
