package com.model;

import java.util.Date;
import java.util.List;

public class ReservationDetails {

	private int conformationId;
	private int flightId;
	private double rewards;	
	private int customerId;
	private String streetAddress;
	private String country;
	private String city;
	private String zip;	
	private String origin;
	private String destination;
	private String ticketClass;
	private String email;
	private String phonenumber;
	private int returnFlightId;
	private int passengers;
	private String customerName;
	private double totalPrice;
	private Date departureDate;
	private double basePrice;
	private boolean oneway;
	private double distance;
	private String ccNumber;
	private String expYear;
	private int cvv;
	private List<Passenger> passengerNames;
	
	
	public List<Passenger> getPassengerNames() {
		return passengerNames;
	}
	public void setPassengerNames(List<Passenger> passengerNames) {
		this.passengerNames = passengerNames;
	}
	public String getCcNumber() {
		return ccNumber;
	}
	public void setCcNumber(String ccNumber) {
		this.ccNumber = ccNumber;
	}
	public String getExpYear() {
		return expYear;
	}
	public void setExpYear(String expyear) {
		this.expYear = expyear;
	}
	public int getCvv() {
		return cvv;
	}
	public void setCvv(int cvv) {
		this.cvv = cvv;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public double getBasePrice() {
		return basePrice;
	}
	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}
	public boolean isOneway() {
		return oneway;
	}
	public void setOneway(boolean oneway) {
		this.oneway = oneway;
	}
	public Date getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public int getConformationId() {
		return conformationId;
	}
	public void setConformationId(int conformationId) {
		this.conformationId = conformationId;
	}
	public int getFlightId() {
		return flightId;
	}
	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}
	public double getRewards() {
		return rewards;
	}
	public void setRewards(double rewards) {
		this.rewards = rewards;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getTicketClass() {
		return ticketClass;
	}
	public void setTicketClass(String ticketClass) {
		this.ticketClass = ticketClass;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public int getReturnFlightId() {
		return returnFlightId;
	}
	public void setReturnFlightId(int returnFlightId) {
		this.returnFlightId = returnFlightId;
	}
	public int getPassengers() {
		return passengers;
	}
	public void setPassengers(int passengers) {
		this.passengers = passengers;
	}
	
}
