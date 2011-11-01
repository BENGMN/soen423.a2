package common;


/**
* common/BoxOfficeOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from /home/ben/workspace/soen423.a2/src/idl/boxoffice.idl
* Monday, October 31, 2011 11:37:29 PM EDT
*/

public interface BoxOfficeOperations 
{
  void reserve (int customer_id, String show_id, int quantity) throws common.BoxOfficePackage.invalid_customer, common.BoxOfficePackage.invalid_event;
  void cancel (int customer_id, String show_id, int qty) throws common.BoxOfficePackage.invalid_customer, common.BoxOfficePackage.invalid_event;
  int show (String show_id) throws common.BoxOfficePackage.invalid_event;
  String[] allEvents ();
  void createEvent (String event_id, String title, int capacity) throws common.BoxOfficePackage.invalid_event;
  void exchange (int customer_id, String reserved_event_id, int reserved_tickets, String desired_event_id, int desired_tickets) throws common.BoxOfficePackage.invalid_customer, common.BoxOfficePackage.invalid_event;
  boolean canExchange (int customer_id, String show_id, int no_tickets) throws common.BoxOfficePackage.invalid_customer, common.BoxOfficePackage.invalid_event;
  void shutdown ();
} // interface BoxOfficeOperations
