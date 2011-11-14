package server;

import java.util.Properties;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;

import common.BoxOffice;
import common.BoxOfficeHelper;

public class BoxOfficeServer {

	String location = null;
	String host = null;
	int port = 0;
	
	public BoxOfficeServer(String location, String host, int port) {
		this.location = location;
		this.host = host;
		this.port = port;
	}
		
	
	public void start() {
		try {
			String[] args = new String[1];
			Properties props = new Properties();
		    props.put("org.omg.CORBA.ORBInitialPort", "1050");
		    props.put("org.omg.CORBA.ORBInitialHost", "localhost");
			// create and initialize the ORB
			ORB orb = ORB.init(args, props);
			
			// get the reference to rootpoa & activate POAManager
			POA rootpoa = (POA)orb.resolve_initial_references("RootPOA");
			rootpoa.the_POAManager().activate();
			
			// create a servant
			BoxOfficeImpl boxOffImpl = new BoxOfficeImpl(orb, location, host, port);
			
			// get object reference from the servant
			org.omg.CORBA.Object ref = rootpoa.servant_to_reference(boxOffImpl);
			
			// cast the reference to a CORBA reference
			BoxOffice href = BoxOfficeHelper.narrow(ref);
			
			// get the root naming context
			// NameService invokes the transient name services
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			
			// use the NamingContextExt which is part of the
			// Interoperable Naming Service (INS) spec.
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
			
			// bind the Object Reference in Naming
			NameComponent path[] = ncRef.to_name(location);
			ncRef.rebind(path, href);
			
			System.out.println(location+" BoxOfficeServer ready...");
			System.out.println(location+" UDP Server ready...");
			
			// wait for invocations from clients
			orb.run();
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
