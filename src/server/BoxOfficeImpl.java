package server;

import java.util.HashMap;
import java.util.Map;

import org.omg.CORBA.ORB;

import udp.Exchange;
import udp.ExchangeClient;
import udp.IExchange;

import common.BoxOfficePOA;
import common.BoxOfficePackage.invalid_customer;
import common.BoxOfficePackage.invalid_event;

import domain.Event;

public class BoxOfficeImpl extends BoxOfficePOA {

	// Data members needed to implement the BoxOffice interface
	private ORB orb = null;
	private String UDPHost = null;
	private int UDPPort = 0;
	private String city = null;
	private volatile Map<String, Event>  available_shows = new HashMap<String, Event>();  // <show_id, Event>
	
	
	public BoxOfficeImpl(ORB orb, String city, String UDPHost, int UDPPort) throws Exception {
		this.orb = orb;
		this.city = city;
		this.UDPHost = UDPHost;
		this.UDPPort = UDPPort;
		initialize();
	}
	
	private void initialize() throws Exception {
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
		try {
			ExchangeClient client = new ExchangeClient(1010);
			IExchange exchange_request = new Exchange(customer_id, desired_event_id, desired_tickets);
			client.sendData(exchange_request);
			exchange_request = client.getData();
			
			if(((Exchange) exchange_request).canExchange()) {
				cancel(customer_id, reserved_event_id, reserved_tickets);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
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
