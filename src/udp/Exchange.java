package udp;

import java.util.Properties;

import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;


import common.BoxOffice;
import common.BoxOfficeHelper;

public class Exchange implements IExchange {
	
	private static final long serialVersionUID = 1L;
	private boolean exhange_ok = false;
	private int customer_id, no_tickets;
	private String show_id;
	
	public Exchange(int customer_id, String show_id, int no_tickets) {
		this.customer_id = customer_id;
		this.show_id = show_id;
		this.no_tickets = no_tickets;
	}
	
	public boolean canExchange() {
		return exhange_ok;
	}

	@Override
	public void execute() {
		String box_office_id = show_id.substring(0,3);
		
		Properties system_properties = System.getProperties();
		system_properties.setProperty("org.omg.CORBA.ORBClass", "com.sun.corba.se.internal.POA.POAORB");
		system_properties.setProperty("org.omg.CORBA.ORBSingletonClass", "com.sun.corba.se.internal.corba.ORBSingleton");
		org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(new String[1], system_properties);
		org.omg.CORBA.Object nsObj;
		
		
		try {
			nsObj = orb.resolve_initial_references("NameService");
			NamingContextExt nc = NamingContextExtHelper.narrow(nsObj);
			org.omg.CORBA.Object obj = nc.resolve_str(box_office_id);
			BoxOffice boxOff = BoxOfficeHelper.narrow(obj);
			boxOff.canExchange(customer_id, box_office_id, no_tickets);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	

}
