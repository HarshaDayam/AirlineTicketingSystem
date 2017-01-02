package com.model;



public class FlightSearch {

	private String origin;
	private String dest;
 	private String deptDate;
 	private int passengers;
 	private boolean oneway;
 	private String ticketClass;
 	
 		
	public String getTicketClass() {
		return ticketClass;
	}
	public void setTicketClass(String ticketClass) {
		this.ticketClass = ticketClass;
	}
	public boolean isOneway() {
		return oneway;
	}
	public void setOneway(boolean oneway) {
		this.oneway = oneway;
	}
	public int getPassengers() {
		return passengers;
	}
	public void setPassengers(int passengers) {
		this.passengers = passengers;
	}
	public String getDeptDate() {
		return deptDate;
	}
	public void setDeptDate(String deptDate) {
		this.deptDate = deptDate;
	}
	
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getDest() {
		return dest;
	}
	public void setDest(String dest) {
		this.dest = dest;
	}
}
