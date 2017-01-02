package Repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.model.Passenger;

public class SeatsDAO {

	public void selectSeats(int confirmationId,int flightId, List<Passenger> passengers) {

		Connection connection = MySQLConnection.getConnection();

		String sql = "update Seats set seatnum = ? where seatid= ?";
		PreparedStatement pstmt = null;
		
		try {
			connection.setAutoCommit(false);
			List<Integer> seatIds = getSeatIds(confirmationId, flightId);
			int j =0;
			pstmt = connection.prepareStatement(sql);
			for (int sid : seatIds) {	
				
				pstmt.setInt(1, passengers.get(j).getSeat());
				pstmt.setInt(2, sid);
				j++;			
				pstmt.executeUpdate();
			}
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
		public List<Integer> getSeatIds(int confirmationId, int flightId){
			Connection connection = MySQLConnection.getConnection();

			String sql = "Select seatid from seats where confirmationid = ? and flightid = ?";
			PreparedStatement pstmt = null;
			List<Integer> seatIds = new ArrayList<Integer>();
			ResultSet rs;
			try{
				pstmt = connection.prepareStatement(sql);
				pstmt.setInt(1, confirmationId);
				pstmt.setInt(2, flightId);
				rs = pstmt.executeQuery();
				while(rs.next()){
					seatIds.add(rs.getInt(1));
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return seatIds;
		}
	
	
		public ArrayList<Integer> getSelectedSeats(int confirmationId, int flightId){
			Connection connection = MySQLConnection.getConnection();

			String sql ="select s.seatnum from sys.seats s where s.flightId = ?";
			ArrayList <Integer> seats = new ArrayList<Integer>();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			
			try{
				pstmt = connection.prepareStatement(sql);
				pstmt.setInt(1, flightId);
				rs = pstmt.executeQuery();
				while(rs.next()){
					seats.add(rs.getInt("seatnum"));
				}
						
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return seats;
			
		}	

}
