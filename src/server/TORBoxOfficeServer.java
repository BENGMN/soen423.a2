package server;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;

import common.BoxOffice;
import common.BoxOfficeHelper;

public class TORBoxOfficeServer {

	public static void main (String[] args) {
		
		try {

			String location = "TOR";
			String host = "localhost";
			int port = 42225;
			
			// create and initialize the ORB
			ORB orb = ORB.init(args, null);
			
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
			
			// wait for invocations from clients
			orb.run();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
