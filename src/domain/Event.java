package domain;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import technical.log.ReservationLog;

public class Event {
	
	private String title = null;
	private String id    = null;
	private volatile int availability = 0;
	private Map<Integer, Integer> reservations = new HashMap<Integer, Integer>(); // customer_id, quantity
	
	private static Pattern EVENT_ID_PATTERN = java.util.regex.Pattern.compile("^[A-Z]{3}\\d{3}$");
	private static Pattern CUSTOMER_ID_PATTERN = java.util.regex.Pattern.compile("^\\d{6}$");
	
	public Event(String id, String title, int capacity) throws Exception {
		if (!EVENT_ID_PATTERN.matcher(id).matches()) { throw new Exception("Event ID invalid. Please use the form ABC123."); }
		if (title.isEmpty()) { throw new Exception("Every event must have a title"); }
		if (capacity < 1) { throw new Exception("Capacity must be greater than 1"); }
		
		this.title = title;
		this.id = id;
		this.availability = capacity;
	}
	
	public synchronized void reserve(int customer_id, int qty) throws Exception {
		if (qty > 0 && qty <= this.availability	&& verifyCustomer(customer_id)) {
			if (reservations.containsKey(customer_id)) {
				ReservationLog.update(this.id, customer_id, qty);
			}
			else {
				ReservationLog.insert(this.id, customer_id, qty);
			}
			reservations.put(customer_id, qty);
			this.availability -= qty;
			notifyAll();
		}
		else {
			throw new Exception("Insufficient number of tickets for the request or incorrect customer ID");
		}
	}
	
	public synchronized void cancelReservation(int customer_id, int qty) throws Exception {
		if (reservations.containsKey(customer_id)) {
			int reserved_qty = reservations.get(customer_id);
			if (qty > reserved_qty) {
				throw new Exception("Attempting to cancel more tickets than purchased");
			}
			else {
				int balance = qty - reserved_qty; 
				if (balance > 0) {
					reservations.put(customer_id, balance);
					ReservationLog.update(this.id, customer_id, balance);
				}
				else {
					reservations.remove(customer_id);
					ReservationLog.destroy(this.id, customer_id);
				}
				this.availability += qty;
			}
		}
		notifyAll();
	}
	
	public int getReservation(int customer_id) {
		return reservations.get(customer_id);
	}
	
	public int availability() {
		return this.availability;
	}

	public String getTitle() {
		return this.title;
	}

	public String getEventID() {
		return this.id;
	}
	
	private static boolean verifyCustomer(int customer_id) {
		if (CUSTOMER_ID_PATTERN.matcher(Integer.toString(customer_id)).matches()) {
			return true;
		}
		else {
			return false;
		}
	}
}
