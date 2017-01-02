package Repositories;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.model.Flight;

public class FlightDAO {

	Connection connection;

	public List<Flight> getFlightDetails(String origin, String dest, String deptDate) {

		connection = MySQLConnection.getConnection();
	
		String sql = "SELECT * FROM flight Where Origin = ? and destination = ? and deptdate = ?";

		ResultSet rs = null;
		PreparedStatement pstmt = null;
		List<Flight> flights = new ArrayList<Flight>();
		try {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			java.sql.Date d = new java.sql.Date(format.parse(deptDate).getTime());
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, origin);
			pstmt.setString(2, dest);
			pstmt.setDate(3, (java.sql.Date) d);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				Flight flight = new Flight();
				flight.setFlightId(rs.getInt("flightId"));
				flight.setFlightNumber(rs.getString("number"));
				flight.setArrivalTime(rs.getString("arrivaltime"));
				flight.setOrigin(rs.getString("origin"));
				flight.setDestination(rs.getString("destination"));
				flight.setEconomySeats(rs.getInt("economy_seats"));
				flight.setDistance(rs.getInt("distance"));
				flight.setFirstClassSeats(rs.getInt("firstclass_seats"));
				flight.setDuration(rs.getString("duration"));
				flight.setAirlines(rs.getString("Airlines"));
				flight.setDepartureTime(rs.getString("departTime"));
				flight.setDeptDate(rs.getDate("deptdate"));
				flight.setBasePrice(rs.getDouble("baseprice"));

				flights.add(flight);
			}
			rs.close();
			pstmt.close();
			connection.close();

		} catch (java.lang.Exception ex) {
			ex.printStackTrace();
		}
		return flights;

	}
	
	public Flight getFlightData(int flightId) {

		connection = MySQLConnection.getConnection();
	
		String sql = "SELECT * FROM flight Where flightid = ?";

		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Flight flight = new Flight();
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, flightId);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				flight.setFlightId(rs.getInt("flightId"));
				flight.setFlightNumber(rs.getString("number"));
				flight.setArrivalTime(rs.getString("arrivaltime"));
				flight.setOrigin(rs.getString("origin"));
				flight.setDestination(rs.getString("destination"));
				flight.setEconomySeats(rs.getInt("economy_seats"));
				flight.setDistance(rs.getInt("distance"));
				flight.setFirstClassSeats(rs.getInt("firstclass_seats"));
				flight.setDuration(rs.getString("duration"));
				flight.setAirlines(rs.getString("Airlines"));
				flight.setDepartureTime(rs.getString("departTime"));
				flight.setDeptDate(rs.getDate("deptdate"));
				flight.setBasePrice(rs.getDouble("baseprice"));
			}
			
		} catch (java.lang.Exception ex) {
			ex.printStackTrace();
		}finally{
			try {
				rs.close();
				pstmt.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		return flight;
	}
	
	public int decrementAvailableSeats(int flightId, String ticketClass, int numberOfPassengers){
		
		connection = MySQLConnection.getConnection();
		
		String sql = null;
		int result = 0;
		
		if("FirstClass".equalsIgnoreCase(ticketClass)){
			sql = "update FLIGHT set firstclass_seats = firstclass_seats - " + numberOfPassengers + " where flightid = ?";
		}else{
			sql = "update FLIGHT set economy_seats = economy_seats - " + numberOfPassengers + " where flightid = ?";
		}

		PreparedStatement pstmt = null;	
		try {	
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, flightId);
			result = pstmt.executeUpdate();
		}catch (java.lang.Exception ex) {
			ex.printStackTrace();
		}finally{
			try {
				pstmt.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
		
		return result;
	}

}
