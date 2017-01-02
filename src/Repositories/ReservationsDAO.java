package Repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.model.Flight;
import com.model.Passenger;
import com.model.ReservationDetails;
import com.utils.Action;
import com.utils.EmailUtil;
import com.viewmodels.ReservationCustomerFlight;

public class ReservationsDAO {

	public int makeReservation(ReservationDetails reserve) {
		Connection connection = MySQLConnection.getConnection();
		
		FlightDAO flightDao = new FlightDAO();
		
		Flight flight1 = flightDao.getFlightData(reserve.getFlightId());
		Flight returnFlight = null;
		
		if(reserve.getReturnFlightId() >0){
			returnFlight = flightDao.getFlightData(reserve.getReturnFlightId());
		}

		String reservationSql = "Insert into reservation (flightid,returnflightid,origin,destination,rewards,class,passengers,customerid,totalprice, reservationdate)"
				+ "values(?,?,?,?,?,?,?,?,?,?) ";

		String decrSeatsSql = null;
		if ("FirstClass".equalsIgnoreCase(reserve.getTicketClass())) {
			decrSeatsSql = "update FLIGHT set firstclass_seats = firstclass_seats - "
					+ reserve.getPassengers() + " where flightid = ?";
		} else {
			decrSeatsSql = "update FLIGHT set economy_seats = economy_seats - "
					+ reserve.getPassengers() + " where flightid = ?";
		}
		
		if(reserve.getReturnFlightId() >0){	
			decrSeatsSql +=" or flightid = ?";	
		}
		
		String insertPassengersSql = "INSERT INTO Seats (confirmationid,name,flightid)" + "VALUES(?,?,?)";

		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		
		try {
			connection.setAutoCommit(false);
			
			int customerId = reserve.getCustomerId();

			if (reserve.getCustomerId() == 0) {
				customerId = insertCustomerDetails(reserve);
			}

			reserve.setCustomerId(customerId);
			
            //insert into reservation table
			pstmt = connection.prepareStatement(reservationSql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, reserve.getFlightId());
			pstmt.setInt(2, reserve.getReturnFlightId());
			pstmt.setString(3, reserve.getOrigin());
			pstmt.setString(4, reserve.getDestination());
			pstmt.setDouble(5, reserve.getRewards());
			pstmt.setString(6, reserve.getTicketClass());
			pstmt.setInt(7, reserve.getPassengers());
			pstmt.setInt(8, customerId);
			pstmt.setDouble(9, reserve.getTotalPrice());
			//LocalDateTime now = LocalDateTime.now();
			pstmt.setDate(10, new java.sql.Date(new java.util.Date().getTime()));

			

			int affectedRows = pstmt.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException(
						"Creating user failed, no rows affected.");
			}

			ResultSet keys = pstmt.getGeneratedKeys();

			if (keys.next()) {
				reserve.setConformationId(keys.getInt(1));
			}
			
			
			// decrement seats
						pstmt2 = connection.prepareStatement(decrSeatsSql);
						pstmt2.setInt(1, reserve.getFlightId());
						
						if(reserve.getReturnFlightId() >0){	
							pstmt2.setInt(2, reserve.getReturnFlightId());
						}
						
						pstmt2.executeUpdate();
			
			//Insert passenger names into seats table
			pstmt3 = connection.prepareStatement(insertPassengersSql);
			for (Passenger passenger : reserve.getPassengerNames()) {	
				
				pstmt3.setInt(1, reserve.getConformationId());
				pstmt3.setString(2, passenger.getName());
				pstmt3.setInt(3, reserve.getFlightId());	
				pstmt3.executeUpdate();
			}
			
			if(reserve.getReturnFlightId() >0){	
				
				pstmt4 = connection.prepareStatement(insertPassengersSql);
				for (Passenger passenger : reserve.getPassengerNames()) {			
					pstmt4.setInt(1, reserve.getConformationId());
					pstmt4.setString(2, passenger.getName());
					pstmt4.setInt(3, reserve.getReturnFlightId());	
					pstmt4.executeUpdate();
				}
			}
			
			EmailUtil.sendEmail(reserve, Action.RESERVATION, flight1, returnFlight);
			
			connection.commit();

		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (pstmt2 != null)
					pstmt2.close();
				if (pstmt3 != null)
					pstmt3.close();
				if (connection != null)
					connection.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return reserve.getConformationId();
	}

	public int insertCustomerDetails(ReservationDetails reserve) {

		Connection connection = MySQLConnection.getConnection();
		String sql = "insert into customers (city,country,zip,phone,email,name,streetaddress,ccnumber,expyear,cvv) values(?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = null;
		try {

			pstmt = connection.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, reserve.getCity());
			pstmt.setString(2, reserve.getCountry());
			pstmt.setString(3, reserve.getZip());
			pstmt.setString(4, reserve.getPhonenumber());
			pstmt.setString(5, reserve.getEmail());
			pstmt.setString(6, reserve.getCustomerName());
			pstmt.setString(7, reserve.getStreetAddress());
			pstmt.setString(8, reserve.getCcNumber());
			pstmt.setString(9, reserve.getExpYear());
			pstmt.setInt(10, reserve.getCvv());

			int affectedRows = pstmt.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException(
						"Creating user failed, no rows affected.");
			}

			ResultSet keys = pstmt.getGeneratedKeys();

			if (keys.next()) {
				reserve.setCustomerId(keys.getInt(1));
			} else {
				throw new SQLException("Creating user failed, no ID obtained.");
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			try {
				if (pstmt != null)
					pstmt.close();
				if (connection != null)
					connection.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return reserve.getCustomerId();
	}

	public ReservationCustomerFlight getReservationDetails(int confirmId, String name) {

		Connection connection = MySQLConnection.getConnection();
		String sql = "SELECT r.cancel, r.class, r.reservationDate, r.canceldate, r.flightid, r.returnflightid, rf.deptdate as returndeptdate, rf.departtime as returndeparttime,rf.arrivaltime as returnarrivaltime, r.confirmationid,r.passengers,r.meal,c.email,f.destination,f.origin,f.duration,"
				+ "f.deptdate, f.departtime,f.arrivaltime,f.distance,c.name From reservation r inner join flight f "
				+ "on f.flightid = r.flightid inner join customers c "
				+ " on r.customerid = c.customerid and r.confirmationid = ? left join flight rf on rf.flightid = r.returnflightid ";
		if (name != null) {
			sql += " where c.name = ?";
		}

		ResultSet rs = null;
		PreparedStatement pstmt = null;
		ReservationCustomerFlight reservation = null;

		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, confirmId);
			if (name != null) {
				pstmt.setString(2, name);
			}
			rs = pstmt.executeQuery();
			List<Passenger> passengersList = new ArrayList<Passenger>();
			List<Passenger> returnPassengersList = new ArrayList<Passenger>();
			while (rs.next()) {
				reservation = new ReservationCustomerFlight();
				reservation.setCustomerName(rs.getString("name"));
				reservation.setConformationId(rs.getInt("confirmationid"));
				reservation.setDepartureTime(rs.getString("departtime"));
				reservation.setArrivalTime(rs.getString("arrivaltime"));
				reservation.setDestination(rs.getString("destination"));
				reservation.setOrigin(rs.getString("origin"));
				reservation.setDistance(rs.getDouble("distance"));
				reservation.setDuration(rs.getString("duration"));
				reservation.setMealPlan(rs.getString("meal"));
				reservation.setPassengers(rs.getInt("passengers"));
				reservation.setEmail(rs.getString("email"));
				reservation.setFlightId(rs.getInt("flightid"));
				reservation.setCancelled(rs.getBoolean("cancel"));
				reservation.setCancelledDate(rs.getDate("cancelDate"));
				reservation.setDepartureDate(rs.getDate("deptdate"));
				reservation.setReturnflightId(rs.getInt("returnflightid"));
				reservation.setReturnArrivalTime(rs.getString("returnarrivaltime"));
				reservation.setReturnDepartureDate(rs.getDate("returndeptdate"));
				reservation.setReturnDepartureTime(rs.getString("returndeparttime"));
				reservation.setReservationDate(rs.getDate("reservationDate"));
				reservation.setTicketClass(rs.getString("class"));

				passengersList = getSelectedSeats(confirmId,
						reservation.getFlightId());
				
				returnPassengersList = getSelectedSeats(confirmId,
						reservation.getReturnflightId());
				
				if (!passengersList.isEmpty() && passengersList.get(0).getSeat() != 0) {
					reservation.setSeatSelected(true);
					reservation.setPassenger(passengersList);
				}
				
				if (returnPassengersList != null && !returnPassengersList.isEmpty() && returnPassengersList.get(0).getSeat() != 0) {
					reservation.setReturnSeatSelected(true);
					reservation.setReturnPassenger(returnPassengersList);	
				}
			}

		} catch (java.lang.Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return reservation;
	}

	public List<Passenger> getSelectedSeats(int confirmationId, int flightId) {

		Connection connection = MySQLConnection.getConnection();
		String sql = " select * from seats where  flightid=? and confirmationid=?";
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		List<Passenger> passengers = new ArrayList<Passenger>();
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, flightId);
			pstmt.setInt(2, confirmationId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Passenger p = new Passenger();
				p.setName(rs.getString("name"));
				p.setSeat(rs.getInt("seatnum"));
				passengers.add(p);

			}
		} catch (java.lang.Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return passengers;
	}

	public void cancelReservation(int confirmationId) {

		Connection connection = MySQLConnection.getConnection();
		String sql = " Update reservation set cancel=1,canceldate = ? where confirmationid = ?";
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		java.sql.Date sqlDate = new java.sql.Date(
				new java.util.Date().getTime());
		
		ReservationCustomerFlight reservation = getReservationDetails(confirmationId, null);
		String incrSeatsSql = "";
		if ("FirstClass".equalsIgnoreCase(reservation.getTicketClass())) {
			incrSeatsSql = "update FLIGHT set firstclass_seats = firstclass_seats + "
					+ reservation.getPassengers() + " where flightid = ?";
		} else {
			incrSeatsSql = "update FLIGHT set economy_seats = economy_seats + "
					+ reservation.getPassengers() + " where flightid = ?";
		}
		
		if(reservation.getReturnFlightId() >0){	
			incrSeatsSql +=" or flightid = ?";	
		}
		
		String deletePassengersSql = "DELETE FROM Seats where confirmationid = ?";
		
		try {
			connection.setAutoCommit(false);
			//set cancel and cancel date
			pstmt = connection.prepareStatement(sql);
			pstmt.setDate(1, sqlDate);
			pstmt.setInt(2, confirmationId);
			pstmt.executeUpdate();
			
			//increment seats in fight
			pstmt2 = connection.prepareStatement(incrSeatsSql);
			pstmt2.setInt(1, reservation.getFlightId());
			
			if(reservation.getReturnFlightId() >0){	
				pstmt2.setInt(2, reservation.getReturnFlightId());
			}
			pstmt2.executeUpdate();
			
			//remove passenger information from seats
			pstmt3 = connection.prepareStatement(deletePassengersSql);
			pstmt3.setInt(1, confirmationId);
			pstmt3.executeUpdate();
			
			ReservationDetails details = new ReservationDetails();
			details.setConformationId(reservation.getConformationId());
			details.setEmail(reservation.getEmail());	
			connection.commit();
			EmailUtil.sendEmail(details, Action.CANCELLATION, null, null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<ReservationCustomerFlight> getMyReservations(int customerId) {

		Connection connection = MySQLConnection.getConnection();
		String sql = "SELECT r.cancel, r.class, r.reservationdate, r.canceldate, rf.deptdate as returndeptdate, rf.departtime as returndeparttime,rf.arrivaltime as returnarrivaltime, r.confirmationid,r.passengers,r.meal,c.email,f.destination,f.origin,f.duration,"
				+ "f.deptdate, f.departtime,f.arrivaltime,f.distance,c.name From reservation r inner join flight f "
				+ "on f.flightid = r.flightid inner join customers c "
				+ " on r.customerid = c.customerid and r.customerId = ? left join flight rf on rf.flightid = r.returnflightid";

		ResultSet rs = null;
		PreparedStatement pstmt = null;
		List<ReservationCustomerFlight> myReservations = new ArrayList<ReservationCustomerFlight>();

		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, customerId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ReservationCustomerFlight reservation = new ReservationCustomerFlight();
				reservation.setCancelled(rs.getBoolean("cancel"));
				reservation.setCancelledDate(rs.getDate("cancelDate"));
				reservation.setReturnArrivalTime(rs.getString("returnarrivaltime"));
				reservation.setReturnDepartureDate(rs.getDate("returndeptdate"));
				reservation.setReturnDepartureTime(rs.getString("returndeparttime"));
				reservation.setConformationId(rs.getInt("confirmationid"));
				reservation.setCustomerName(rs.getString("name"));	
				reservation.setDepartureTime(rs.getString("departtime"));
				reservation.setArrivalTime(rs.getString("arrivaltime"));
				reservation.setDestination(rs.getString("destination"));
				reservation.setOrigin(rs.getString("origin"));
				reservation.setDistance(rs.getDouble("distance"));
				reservation.setDuration(rs.getString("duration"));
				reservation.setMealPlan(rs.getString("meal"));
				reservation.setPassengers(rs.getInt("passengers"));
				reservation.setEmail(rs.getString("email"));
				reservation.setDepartureDate(rs.getDate("deptdate"));
				reservation.setReservationDate(rs.getDate("reservationDate"));
				reservation.setTicketClass(rs.getString("class"));	

				myReservations.add(reservation);
			}

		} catch (java.lang.Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return myReservations;
	}

}
