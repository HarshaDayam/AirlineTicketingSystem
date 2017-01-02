package com.model;




public class Airport {

	private String airportName;
	private String airportCode;
	private String city;
	private String country;
	private String state;
	private String zip;
	

	

	public String getAirportName() {
		return airportName;
	}

	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}

	public String getAirportCode() {
		return airportCode;
	}

	
	
	public void setAirportCode(String airportCode) {
		this.airportCode = airportCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	@Override
	public String toString() {
		return "airport[airportname =" + airportName + "airportcode =" + airportCode + "country =" + country + "state ="
				+ state + "zip =" + zip + "]";
	}
}
