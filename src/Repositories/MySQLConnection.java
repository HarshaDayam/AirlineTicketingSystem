package Repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {

	public static Connection getConnection() {
		Connection con = null;
		
		try {
			String url = "jdbc:mysql://localhost/sys";
       	 Class.forName ("com.mysql.jdbc.Driver");        	               
       	 con = (Connection) DriverManager.getConnection (url, "root", "root" );
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} catch (Exception e) {

			System.err.println(e.getMessage());
		}
		return con;
	}
}


