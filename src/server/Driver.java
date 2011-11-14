package server;

public class Driver {

	/*
	 * Start the ORB Server	
	 * ~$ orbd -ORBInitialPort 1050 -ORBInitialHost localhost&
	 */
	
	public static void main (String[] args) {
		BoxOfficeServer mtl = new BoxOfficeServer("MTL", "127.0.0.1", 44948);
		BoxOfficeServer tor = new BoxOfficeServer("TOR", "127.0.0.1", 44949);
		BoxOfficeServer ott = new BoxOfficeServer("OTT", "127.0.0.1", 44950);
		
		mtl.start();
		tor.start();
		ott.start();
	}
	
}
