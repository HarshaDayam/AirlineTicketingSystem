package Repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.model.Login;

public class LoginDao {

	public Login verifyUser(Login login) {

		Connection connection = MySQLConnection.getConnection();

		String sql = "SELECT name, customerid, username, password from Customers where username=? and password=?";
		PreparedStatement pstmt = null;
		
		try {
							
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1, login.getUsername());
				pstmt.setString(2, login.getPassword());
					
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()){
					Login user = new Login();
					user.setUserId(rs.getString("customerid"));
					user.setUsername(rs.getString("username"));
					user.setName(rs.getString("name"));
					return user;
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
