package photoalbum.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import photoalbum.CreateUserException;
import photoalbum.entities.Category;
import photoalbum.entities.User;

public class NetworkConnection {
	
	public static final String PROPERTY_CMD = "cmd";
	
	public static final String CMD_GET_ALL_USERS = "getAllUsers";
	
	public static final String CMD_ADD_USER = "addUser";
	
	public static final String CMD_ADD_CATEGORY = "addCategory";
	
	public static final String CMD_EDIT_USER = "editUser";
	
	public static final String CMD_DELETE_OBJECT = "deleteObject";
	
	public static final String CMD_ADMIN_ACCESS_GRANTED = "adminAccessGranted";
	
	public static final String PROTOCOL = "http://";
	
	public static final String ROOT_PATH = "/PhotoAlbum-Web/connection";
	
	private String host = null;
	
	private String port = null;
	
	private String connectionString = null;
	
	private URL url = null;
	
	private HttpURLConnection httpUrlConnection = null;
	
	ObjectInputStream objectInputStream = null;
	
	ObjectOutputStream objectOutputStream = null;
	
	public NetworkConnection(String host, String port) throws MalformedURLException {
		this.host = host;
		this.port = port;
	}
	
	private String getConnectionString() {
		if (this.connectionString == null) {
			this.connectionString = PROTOCOL + this.host + ":" + this.port + ROOT_PATH;
		}
		return this.connectionString;
	}
	
	private URL getUrl() throws MalformedURLException {
		if (this.url == null) {
			this.url = new URL(this.getConnectionString());
		}
		return this.url;
	}
	
	private HttpURLConnection getHttpUrlConnection() throws IOException {
		if (this.httpUrlConnection == null) {
			this.httpUrlConnection = (HttpURLConnection) getUrl().openConnection();
			this.httpUrlConnection.setRequestMethod("POST");
			this.httpUrlConnection.setDoOutput(true);
			this.httpUrlConnection.setDoInput(true);
		}
		return this.httpUrlConnection;
	}

	private ObjectInputStream getObjectInputStream() throws IOException {
		if (this.objectInputStream == null) {
			this.objectInputStream = new ObjectInputStream(getHttpUrlConnection().getInputStream());
		}
		return this.objectInputStream;
	}

	private ObjectOutputStream getObjectOutputStream() throws IOException {
		if (this.objectOutputStream == null) {
			this.objectOutputStream = new ObjectOutputStream(getHttpUrlConnection().getOutputStream());
		}
		return this.objectOutputStream;
	}
	
	private void writeObject(Object obj) throws IOException {
		getObjectOutputStream().writeObject(obj);
		getObjectOutputStream().flush();
	}
	
	private Object readObject() throws IOException, ClassNotFoundException {
		return getObjectInputStream().readObject();
	}
	
	public boolean adminAccessGranted(String password) throws IOException, ClassNotFoundException {
		String cmd = CMD_ADMIN_ACCESS_GRANTED;
		Object[] outputData = new Object[] { cmd, password };
		writeObject(outputData);
		Object inputData = readObject();
		Boolean result = false;
		if (inputData instanceof Boolean) {
			result = (Boolean) inputData;
		}
		return result;
	}

	public User[] getAllUsers() throws IOException, ClassNotFoundException {
		User[] users = (User[]) readObject();
		return users;
	}
	
	public void addUser(String username, String password, String firstName, String lastName) throws CreateUserException {
		User user = new User(username, password, firstName, lastName);
		addUser(user);
	}
	
	public void addUser(User user) throws CreateUserException {
		try {
			writeObject(user);
		} catch (IOException e) {
			throw new CreateUserException("Cannot create user [" + user.getUsername() + "].");
		}
	}
	
	public void addCategory(Category category) throws IOException {
		writeObject(category);
	}
	
	public void editUser(User user) throws IOException {
		writeObject(user);
	}
	
	public void deleteObject(Object obj) throws IOException {
		writeObject(obj);
	}
	
	public void close() throws IOException {
		getObjectInputStream().close();
		getObjectOutputStream().close();
	}

}