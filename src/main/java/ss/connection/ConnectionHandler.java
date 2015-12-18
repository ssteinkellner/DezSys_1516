package ss.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * eine klasse, die fuer das auf/abbauen von verbindungen zustaendig ist.
 * es gibt maximal eine connection.
 * @author SSteinkellner
 * @version 2014.12.30
 */
public class ConnectionHandler {
	private Connection connection;
	private UserCache usercache;
	private String host, database;
	
	public ConnectionHandler(UserCache userCache){
		usercache = userCache;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getHost() {
		return host;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getDatabase() {
		return database;
	}
	
	public Connection getConnection() {
		if(connection==null){
			connStart();
		}
		
		return connection;
	}
	

	/**
	 * loads the database driver and connects to the database. closes the programm, if there is an error
	 */
	private void connStart() {

		try {
			Class.forName("org.postgresql.Driver").newInstance();
		} catch(Exception ex) {
			System.err.println("Can't find Database driver class!");
			System.exit(1);
		}

		try {
			connection = (Connection) DriverManager.getConnection("jdbc:postgresql://" + host + "/" + database, usercache.getUser(), usercache.getPassword());
			System.out.println("Successfully connected to " + database + " on " + host);
		} catch(SQLException ex) {
			System.err.println("DB connection error! " + ex.getMessage());
		}
	}
	
	public void close(){
		if(connection==null){
			try {
				connection.close();
			} catch (SQLException e) {
				System.err.println("Couldn't close Connection!");
			}
		}
	}
	
	public String toString(){
		String text = "Host=" + host + ", Database=" + database + ", ";
		if(usercache == null){
			text += "usercache=null";
		}else{
			text += "Username=" + usercache.getUser();
		}
		return "ConnectionHandler[" + text + "]";
	}
}
