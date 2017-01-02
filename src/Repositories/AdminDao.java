package Repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.model.Airport;
import com.model.Flight;
import com.mysql.jdbc.Statement;

public class AdminDao {

	public int addFlight(Flight flight){
		
		Connection connection = MySQLConnection.getConnection();
		
		String sql = "INSERT INTO flight(number,arrivaltime,origin,"+
					 "destination,departTime,distance,economy_seats,firstclass_seats,"+
					"baseprice,airlines,duration, deptdate)"
				+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			
			pstmt.setString(1, flight.getFlightNumber());
			pstmt.setString(2, flight.getArrivalTime());
			pstmt.setString(3, flight.getOrigin());
			pstmt.setString(4, flight.getDestination());
			pstmt.setString(5, flight.getDepartureTime());
			pstmt.setDouble(6, flight.getDistance());
			pstmt.setInt(7, flight.getEconomySeats());
			pstmt.setInt(8, flight.getFirstClassSeats());
			pstmt.setDouble(9, flight.getBasePrice());
			pstmt.setString(10, flight.getAirlines());
			pstmt.setString(11, flight.getDuration());
			pstmt.setDate(12,flight.getDeptDate());
			int affectedRows = pstmt.executeUpdate();
			
			 if (affectedRows == 0) {
		            throw new SQLException("Failed to insert flight details, no rows affected.");
		        }
		        
		        ResultSet keys = pstmt.getGeneratedKeys();
		        
		        if(keys.next()){
		        	flight.setFlightId(keys.getInt(1));
		        }
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return flight.getFlightId();
	}
	
	public String addAirport(Airport airport){
		
		Connection connection = MySQLConnection.getConnection();
		
		String sql = "INSERT INTO airport(code,name,city,country,state,zip) VALUES(?,?,?,?,?,?)";
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = connection.prepareStatement(sql);

			
			pstmt.setString(1, airport.getAirportCode());
			pstmt.setString(2, airport.getAirportName());
			pstmt.setString(3, airport.getCity());
			pstmt.setString(4, airport.getCountry());
			pstmt.setString(5, airport.getState());
			pstmt.setString(6,airport.getZip());
			
			int affectedRows = pstmt.executeUpdate();
			
			 if (affectedRows == 0) {
		            throw new SQLException("Airport Code already exists");
		        }
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "Airport details successfully inserted";
	}
	
}
