package com.model;

public class Cancellation {
	
	private String conformationId;
	private String flightId;
	private int rewards;
	private String origin;
	private String destination;
	
	
	public String getConformationId() {
		return conformationId;
	}
	public void setConformationId(String conformationId) {
		this.conformationId = conformationId;
	}
	public String getFlightId() {
		return flightId;
	}
	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}
	public int getRewards() {
		return rewards;
	}
	public void setRewards(int rewards) {
		this.rewards = rewards;
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
	

}
