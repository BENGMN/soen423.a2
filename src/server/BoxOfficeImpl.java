package server;

import java.util.HashMap;
import java.util.Map;

import org.omg.CORBA.ORB;

import udp.ReservationClient;
import udp.IExchange;
import udp.UDPServer;

import common.BoxOfficePOA;
import common.BoxOfficePackage.invalid_customer;
import common.BoxOfficePackage.invalid_event;

import domain.Event;
import domain.Reservation;
import domain.ServerDetail;

public class BoxOfficeImpl extends BoxOfficePOA {

	// Data members needed to implement the BoxOffice interface
	private ORB orb = null;
	private UDPServer udpServer = null;
	private String UDPHost = null;
	private int UDPPort = 0;
	private String city = null;
	private volatile Map<String, Event>  available_shows = new HashMap<String, Event>();  // <show_id, Event>
	private Map<String, ServerDetail> box_off_repo = new HashMap<String, ServerDetail>(); //<MTL, {host, port}>
	
	
	public BoxOfficeImpl(ORB orb, String city, String UDPHost, int UDPPort) throws Exception {
		this.orb = orb;
		this.city = city.toUpperCase();
		this.UDPHost = UDPHost;
		this.UDPPort = UDPPort;
		this.udpServer = new UDPServer(UDPHost, UDPPort, this); // pass a reference of self to UDPServer for exchanges
		initialize();
	}
	
	private void initialize() throws Exception {
		
		// start up the UDP Server in a new thread
		Thread thread = new Thread(this.udpServer);
		thread.start();
		
		// Add the handles to the other box offices into the repository
		this.box_off_repo.put("MTL", new ServerDetail("127.0.0.1", 44948));
		this.box_off_repo.put("OTT", new ServerDetail("127.0.0.1", 44949));
		this.box_off_repo.put("TOR", new ServerDetail("127.0.0.1", 44950));
		
		// Add some test data
		for(int i = 100; i < 104; i++) {
			String show_id    = this.city+i;
			String show_title = "Show"+i;
			createEvent(show_id, show_title, 100);
		}	
	}
	
	public void setORB(ORB orb_val) {
		this.orb = orb_val;
	}
	
	public void shutdown() {
		this.orb.shutdown(false);
		this.udpServer.stop();
	}
	
	@Override
	public void reserve(int customer_id, String show_id, int quantity) throws invalid_customer, invalid_event {
		if(available_shows.containsKey(show_id)) {
			try {
				available_shows.get(show_id).reserve(customer_id, quantity);
				System.out.println(customer_id+" reserved "+quantity+" ticket(s) for "+show_id);
				System.out.println("Number of available tickets left: "+show(show_id));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void cancel(int customer_id, String show_id, int qty) throws invalid_customer, invalid_event {
		if(available_shows.containsKey(show_id)) {
			try {
				available_shows.get(show_id).cancelReservation(customer_id, qty);
				System.out.println(customer_id+" cancelled "+qty+" ticket(s) for "+show_id);
				System.out.println("Number of available tickets left: "+show(show_id));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public int show(String show_id) throws invalid_event {
		if(available_shows.containsKey(show_id)) {
			return available_shows.get(show_id).availability();
		}
		return 0;
		
	}

	@Override
	public void createEvent(String event_id, String title, int capacity) throws invalid_event {
		if (available_shows.containsKey(event_id)) { throw new invalid_event ("Duplicate event"); }
		try {
			Event e = new Event(event_id, title, capacity);
			available_shows.put(event_id, e);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public String[] allEvents() {
		String[] all = new String[available_shows.size()];
		int i = 0;
		for (Map.Entry<String, Event> event : available_shows.entrySet()) {
			all[i] = event.getKey();
			i++;
		}
		return all;
	}

	@Override
	public void exchange(int customer_id, String reserved_event_id, int reserved_tickets, String desired_event_id, int desired_tickets) throws invalid_customer, invalid_event {
			// find out which box-office we need to contact
			String boToContact = desired_event_id.substring(0,3);
			ServerDetail remoteUDP = box_off_repo.get(boToContact);
			
			boolean valid_exchange = false;
			
			// make sure that the client has reserved the tickets at this box office first
			if (desired_tickets <= this.available_shows.get(reserved_event_id).getReservation(customer_id)) {
				valid_exchange = true;
			}
			
			if(valid_exchange) {
			
				// Create a UDP client to contact the server
				ReservationClient client = new ReservationClient(remoteUDP.getIp(), remoteUDP.getPort());
				
				// Create a new exchange object to pass to the server
				IExchange exchange_request = new Reservation(customer_id, desired_event_id, desired_tickets);
				
				// Send the data to the server
				client.sendData(exchange_request);
				
				// Get the response from the server
				exchange_request = client.getData();
				
				// Apply business logic
				if(((Reservation) exchange_request).isConfirmed()) {
	
					cancel(customer_id, reserved_event_id, reserved_tickets); // if the exchange succeeded, cancel
					
					String debug = String.format("Exchange completed\nCustomer\t%s\tCancelled %s tickets\nReserved %s tickets",customer_id,reserved_tickets,desired_tickets);
					System.out.println(debug);
				}
			}
			else {
				String debug = String.format("Exchange failed\nCustomer\t%s\t did not cancel %s tickets\nand did not reserve %s tickets",customer_id,reserved_tickets,desired_tickets);
				System.out.println(debug);
			}
	}

	@Override
	public String getUDPHost() {
		return this.UDPHost;
	}
	
	@Override
	public int getUDPPort() {
		return this.UDPPort;
	}
}
