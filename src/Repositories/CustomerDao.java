package Repositories;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.model.Customer;


	public class CustomerDao {
				
			Connection connection;
			
			public Customer getCustomerDetails(int customerId){
				
				connection = MySQLConnection.getConnection();
				String sql = "SELECT * FROM customers where customerid =" + customerId;
				ResultSet rs = null;
				Statement stmt = null;			
				Customer customer = new Customer();
	         try {
	        	stmt = connection.createStatement();
	 			rs = stmt.executeQuery(sql);
	 			while (rs.next()) {
	 				customer.setCustomerName(rs.getString("name"));
	 				customer.setCustomerId(rs.getInt("customerid"));
	 				customer.setUsername(rs.getString("username"));
	 				customer.setPassword(rs.getString("password"));
	 				customer.setStreetAddress(rs.getString("streetAddress"));
	 				customer.setPhonenumber(rs.getString("phone"));
	 				customer.setEmail(rs.getString("email"));
	 				customer.setCountry(rs.getString("country"));
	 				customer.setCity(rs.getString("city"));
	 				customer.setZip(rs.getString("zip"));
	 			}
	 			rs.close();
	 			stmt.close();
	 			connection.close();
	        	            	  
	         }
	         catch (java.lang.Exception ex) {
	                 ex.printStackTrace ();
	         }
	         return customer;
	         
		}
	}




