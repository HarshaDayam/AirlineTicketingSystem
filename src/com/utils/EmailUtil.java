package com.utils;

import java.text.DateFormat;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.model.Flight;
import com.model.ReservationDetails;

public class EmailUtil {
	public static void sendEmail(ReservationDetails reservation, Action action, Flight oneWay, Flight returnFlight) {
		
		if(reservation.getEmail() == null || reservation.getEmail().equalsIgnoreCase(""))
			return;

		String subject = "";
		String body = "";
		if(action == Action.RESERVATION){
			subject = "Flight Reservation Confirmation";
			body = "<h3>Reservation Confirmation details</h3>";
		}else if(action == Action.CANCELLATION){
			subject = "Flight Reservation Cancellation";
			body = "<h3>Reservation Cancellation details</h3>";
		}

		// Get the session object	
        Properties props = getGmailProperties();
		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication("southeastairlines1111@gmail.com", "harsha111");
					}
				});

		// compose message
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress("southeastairlines1111@gmail.com"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(reservation.getEmail()));
			message.setSubject(subject);	
			
			DateFormat df = DateFormat.getDateInstance(DateFormat.DATE_FIELD);
			
			
			if(action == Action.RESERVATION){
			
				body += "<table><tr><td><b>Confirmation Id</b></td><td>" + reservation.getConformationId() +"</td></tr>" +
				             "<tr><td><b>Ticket Class</b></td><td>" + reservation.getTicketClass() + "</td></tr>" +
				             "<tr><td><b>Total Price</b></td><td>" + reservation.getTotalPrice() + "</td></tr>" + 
				             "<tr><td><b>Passengers</b></td><td>" + reservation.getPassengers()+ "</td></tr></table>";
				body += "<h4>Flight Details</h4>";
				body += "<table><tr><td><b>From</b></td><td>" + reservation.getOrigin() + "</td></tr>" +
			             "<tr><td><b>To</b></td><td>" + reservation.getDestination() + "</td></tr>" +
			             "<tr><td><b>Departure Date</b></td><td>" + df.format(oneWay.getDeptDate()) + "</td></tr>" +
			             "<tr><td><b>Departure Time</b></td><td>" + oneWay.getDepartureTime() + "</td></tr>" +
			             "<tr><td><b>Arrival Time</b></td><td>" + oneWay.getArrivalTime() + "</td></tr>";
				
				if(returnFlight != null){
					body += "<h4>Return Flight Details</h4>";
					body += "<table><tr><td><b>From</b></td><td>" + reservation.getDestination() + "</td></tr>" +
				             "<tr><td><b>To</b></td><td>" + reservation.getOrigin() + "</td></tr>" +
				             "<tr><td><b>Departure Date</b></td><td>" + df.format(returnFlight.getDeptDate()) + "</td></tr>" +
				             "<tr><td><b>Departure Time</b></td><td>" + returnFlight.getDepartureTime() + "</td></tr>" +
				             "<tr><td><b>Arrival Time</b></td><td>" + returnFlight.getArrivalTime() + "</td></tr>";
				}
			}else if(action == Action.CANCELLATION){
				body += "<h4>Cancellation Details</h4>";
				body += "<p>Your reservation for confirmation number " + reservation.getConformationId() + " is cancelled";
				body += "<p>Your money will be refunded within 3-5 business days</p>";
			}
								
			message.setContent(body, "text/html");;

			// send message
			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}
	
	private static Properties getGmailProperties(){
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		
		return props;
	}
}