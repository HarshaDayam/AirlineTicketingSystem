package Repositories;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.model.Airport;

public class AirportDao {
	Connection connection;

	public List<Airport> getAirportDetails() {

		connection = MySQLConnection.getConnection();
		String sql = "SELECT * FROM Airport";
		ResultSet rs = null;
		Statement stmt = null;
		List<Airport> Airports = new ArrayList<Airport>();
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Airport airport = new Airport();
				airport.setAirportName(rs.getString("name"));
				airport.setAirportCode(rs.getString("code"));
				airport.setCountry(rs.getString("country"));
				airport.setCity(rs.getString("city"));
				airport.setState(rs.getString("state"));
				airport.setZip(rs.getString("zip"));
				Airports.add(airport);

			}
			rs.close();
			stmt.close();
			connection.close();

		} catch (java.lang.Exception ex) {
			ex.printStackTrace();
		}
		return Airports;

	}
}
