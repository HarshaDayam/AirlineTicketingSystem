package com.viewmodels;

import java.util.Date;
import java.util.List;

import com.model.Passenger;

public class ReservationCustomerFlight {
	private int conformationId;
	private int flightId;
	private double rewards;	
	private String promoCode;
	private String customerName;
	private String streetAddress;
	private String country;
	private String city;
	private String zip;	
	private String origin;
	private String destination;
	private String ticketClass;
	private String duration;
	private double distance;
	private String departureTime;
	private String seat;
	private String arrivalTime;
	private String mealPlan;
	private String email;
	private String phonenumber;
	private int returnFlightId;
	private int passengers;
	private boolean seatSelected = false;
	private boolean cancelled = false;
	private Date cancelledDate;
	private Date departureDate;
	private String returnDepartureTime;
	private String returnArrivalTime;
	private Date returnDepartureDate;
	private List<Passenger> passenger;
	private List<Passenger> returnPassenger;
	private Date reservationDate;
	private boolean returnSeatSelected = false;
	
	
	
	
	public boolean isReturnSeatSelected() {
		return returnSeatSelected;
	}
	public void setReturnSeatSelected(boolean returnSeatSelected) {
		this.returnSeatSelected = returnSeatSelected;
	}
	public Date getReservationDate() {
		return reservationDate;
	}
	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}
	public Date getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}
	public boolean isCancelled() {
		return cancelled;
	}
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}
	public Date getCancelledDate() {
		return cancelledDate;
	}
	public void setCancelledDate(Date cancelledDate) {
		this.cancelledDate = cancelledDate;
	}
	public boolean isSeatSelected() {
		return seatSelected;
	}
	public void setSeatSelected(boolean seatSelected) {
		this.seatSelected = seatSelected;
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
	
	public String getMealPlan() {
		return mealPlan;
	}
	public void setMealPlan(String mealPlan) {
		this.mealPlan = mealPlan;
	}
	public String getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	
	public String getSeat() {
		return seat;
	}
	public void setSeat(String seat) {
		this.seat = seat;
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
	public String getPromoCode() {
		return promoCode;
	}
	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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
	public String getTicketClass() {
		return ticketClass;
	}
	public void setTicketClass(String ticketClass) {
		this.ticketClass = ticketClass;
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
	
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public String getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}
	public int getReturnflightId() {
		return returnFlightId;
	}
	public void setReturnflightId(int returnflightId) {
		this.returnFlightId = returnflightId;
	}
	public List<Passenger> getPassenger() {
		return passenger;
	}
	public void setPassenger(List<Passenger> passenger) {
		this.passenger = passenger;
	}
	public String getReturnDepartureTime() {
		return returnDepartureTime;
	}
	public void setReturnDepartureTime(String returnDepartureTime) {
		this.returnDepartureTime = returnDepartureTime;
	}
	public String getReturnArrivalTime() {
		return returnArrivalTime;
	}
	public void setReturnArrivalTime(String returnArrivalTime) {
		this.returnArrivalTime = returnArrivalTime;
	}
	public Date getReturnDepartureDate() {
		return returnDepartureDate;
	}
	public void setReturnDepartureDate(Date returnDepartureDate) {
		this.returnDepartureDate = returnDepartureDate;
	}
	public List<Passenger> getReturnPassenger() {
		return returnPassenger;
	}
	public void setReturnPassenger(List<Passenger> returnPassenger) {
		this.returnPassenger = returnPassenger;
	}
}
