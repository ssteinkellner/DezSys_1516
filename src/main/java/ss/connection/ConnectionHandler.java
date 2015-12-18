package ss.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;


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
	
	public static final String MYSQL = "mysql", POSTGRES = "postgresql";
	private static HashMap<String, String> drivers;
	private String dbtype = MYSQL;
	
	private ConnectionHandler(){
		drivers = new HashMap<String, String>();
		drivers.put(MYSQL, "com.mysql.jdbc.Driver");
		drivers.put(POSTGRES, "org.postgresql.Driver");
	}
	
	public ConnectionHandler(UserCache userCache){
		this();
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
	
	public void setDatabaseType(String type){
		if(drivers.containsKey(type)) dbtype = type;
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
			Class.forName(drivers.get(dbtype)).newInstance();
			System.out.println("Driver " + drivers.get(dbtype) + " successfully loaded.");
		} catch(Exception ex) {
			System.err.println("Can't find Database driver class! " + ex.getMessage());
			System.exit(1);
		}

		try {
			connection = (Connection) DriverManager.getConnection("jdbc:" + dbtype + "://" + host + "/" + database, usercache.getUser(), usercache.getPassword());
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
