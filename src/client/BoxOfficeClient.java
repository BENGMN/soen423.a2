package client;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import common.BoxOffice;
import common.BoxOfficeHelper;

public class BoxOfficeClient {

	static BoxOffice boxOffImpl;
	
	public static void main (String[] args) {
		try {
			
			// create an ORB object, call the naming service, find objects of interest
			ORB orb = ORB.init(args, null);
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
			boxOffImpl = BoxOfficeHelper.narrow(ncRef.resolve_str("MTL"));
			
			// now we can call methods on the boxOffice using the boxOffImpl
			boxOffImpl.reserve(10, "MTL101", 2);
			
			// shutdown nicely
			boxOffImpl.shutdown();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
