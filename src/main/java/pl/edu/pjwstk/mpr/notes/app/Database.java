package pl.edu.pjwstk.mpr.notes.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	private String db_host, db_name, db_user, db_pass;
	protected Connection c = null;
	
	public Database(String db_host, String db_name, String db_user, String db_pass){
		this.db_host = db_host;
		this.db_user = db_user;
		this.db_name = db_name;
		this.db_pass = db_pass;
	}
	
	public void connect(){
	
    	try { 
    		Class.forName("org.gjt.mm.mysql.Driver"); 
    		String jdbc_url = "jdbc:mysql://" + db_host + "/" + db_name; 
    		c = DriverManager.getConnection(jdbc_url, db_user, db_pass); 
    	} catch (ClassNotFoundException e) { 
    		System.out.println("Could not find the database driver"); 
    	} catch (SQLException e) { 
    		System.out.println("Could not connect to the database"); 
    	} 
    	
	}

	public Connection getConnection() {
		return c;
	}
}
