package common;


/**
* common/_BoxOfficeStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from /home/ben/workspace/soen423.a2/src/idl/boxoffice.idl
* Monday, October 31, 2011 11:37:29 PM EDT
*/

public class _BoxOfficeStub extends org.omg.CORBA.portable.ObjectImpl implements common.BoxOffice
{

  public void reserve (int customer_id, String show_id, int quantity) throws common.BoxOfficePackage.invalid_customer, common.BoxOfficePackage.invalid_event
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("reserve", true);
                $out.write_long (customer_id);
                $out.write_string (show_id);
                $out.write_long (quantity);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:common/BoxOffice/invalid_customer:1.0"))
                    throw common.BoxOfficePackage.invalid_customerHelper.read ($in);
                else if (_id.equals ("IDL:common/BoxOffice/invalid_event:1.0"))
                    throw common.BoxOfficePackage.invalid_eventHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                reserve (customer_id, show_id, quantity        );
            } finally {
                _releaseReply ($in);
            }
  } // reserve

  public void cancel (int customer_id, String show_id, int qty) throws common.BoxOfficePackage.invalid_customer, common.BoxOfficePackage.invalid_event
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("cancel", true);
                $out.write_long (customer_id);
                $out.write_string (show_id);
                $out.write_long (qty);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:common/BoxOffice/invalid_customer:1.0"))
                    throw common.BoxOfficePackage.invalid_customerHelper.read ($in);
                else if (_id.equals ("IDL:common/BoxOffice/invalid_event:1.0"))
                    throw common.BoxOfficePackage.invalid_eventHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                cancel (customer_id, show_id, qty        );
            } finally {
                _releaseReply ($in);
            }
  } // cancel

  public int show (String show_id) throws common.BoxOfficePackage.invalid_event
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("show", true);
                $out.write_string (show_id);
                $in = _invoke ($out);
                int $result = $in.read_long ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:common/BoxOffice/invalid_event:1.0"))
                    throw common.BoxOfficePackage.invalid_eventHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return show (show_id        );
            } finally {
                _releaseReply ($in);
            }
  } // show

  public String[] allEvents ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("allEvents", true);
                $in = _invoke ($out);
                String $result[] = common.BoxOfficePackage.eventsHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return allEvents (        );
            } finally {
                _releaseReply ($in);
            }
  } // allEvents

  public void createEvent (String event_id, String title, int capacity) throws common.BoxOfficePackage.invalid_event
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("createEvent", true);
                $out.write_string (event_id);
                $out.write_string (title);
                $out.write_long (capacity);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:common/BoxOffice/invalid_event:1.0"))
                    throw common.BoxOfficePackage.invalid_eventHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                createEvent (event_id, title, capacity        );
            } finally {
                _releaseReply ($in);
            }
  } // createEvent

  public void exchange (int customer_id, String reserved_event_id, int reserved_tickets, String desired_event_id, int desired_tickets) throws common.BoxOfficePackage.invalid_customer, common.BoxOfficePackage.invalid_event
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("exchange", true);
                $out.write_long (customer_id);
                $out.write_string (reserved_event_id);
                $out.write_long (reserved_tickets);
                $out.write_string (desired_event_id);
                $out.write_long (desired_tickets);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:common/BoxOffice/invalid_customer:1.0"))
                    throw common.BoxOfficePackage.invalid_customerHelper.read ($in);
                else if (_id.equals ("IDL:common/BoxOffice/invalid_event:1.0"))
                    throw common.BoxOfficePackage.invalid_eventHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                exchange (customer_id, reserved_event_id, reserved_tickets, desired_event_id, desired_tickets        );
            } finally {
                _releaseReply ($in);
            }
  } // exchange

  public boolean canExchange (int customer_id, String show_id, int no_tickets) throws common.BoxOfficePackage.invalid_customer, common.BoxOfficePackage.invalid_event
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("canExchange", true);
                $out.write_long (customer_id);
                $out.write_string (show_id);
                $out.write_long (no_tickets);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:common/BoxOffice/invalid_customer:1.0"))
                    throw common.BoxOfficePackage.invalid_customerHelper.read ($in);
                else if (_id.equals ("IDL:common/BoxOffice/invalid_event:1.0"))
                    throw common.BoxOfficePackage.invalid_eventHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return canExchange (customer_id, show_id, no_tickets        );
            } finally {
                _releaseReply ($in);
            }
  } // canExchange

  public void shutdown ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("shutdown", true);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                shutdown (        );
            } finally {
                _releaseReply ($in);
            }
  } // shutdown

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:common/BoxOffice:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.Object obj = org.omg.CORBA.ORB.init (args, props).string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     String str = org.omg.CORBA.ORB.init (args, props).object_to_string (this);
     s.writeUTF (str);
  }
} // class _BoxOfficeStub
