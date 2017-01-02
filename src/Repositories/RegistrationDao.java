package Repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.model.Registration;


public class RegistrationDao {

	public int makeRegistration(Registration reg) {
		
		Connection connection = MySQLConnection.getConnection();
		
		String sql = "INSERT INTO Customers ( username , password ,name,email,phone, streetaddress,city, country, zip)"
				+ "VALUES(?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = null;
		int rowsAffected = 0;
		try {
			pstmt = connection.prepareStatement(sql);

			
			pstmt.setString(1, reg.getUsername());
			pstmt.setString(2, reg.getPassword());
			pstmt.setString(3, reg.getName());
			pstmt.setString(4, reg.getEmail());
			pstmt.setString(5, reg.getPhone());
			pstmt.setString(6, reg.getAddress());
			pstmt.setString(7, reg.getCity());
			pstmt.setString(8, reg.getCountry());
			pstmt.setString(9, reg.getZip());

			rowsAffected = pstmt.executeUpdate();
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return rowsAffected;
}
}
