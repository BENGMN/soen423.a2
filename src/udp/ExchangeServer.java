package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ExchangeServer {

	private int port = 1010;
	private boolean keepGoing = true;
	
	public ExchangeServer(int port) {
		this.port = port;
	}
	
	public void stop() {
		this.keepGoing = false;
	}
	
	public void start() throws IOException, ClassNotFoundException {
		DatagramSocket server = new DatagramSocket(port);
		byte[] data = new byte[2048];
		
		while(keepGoing) {
			
			// create a datagram packet to store the incoming data
			DatagramPacket in = new DatagramPacket(data, data.length);
			server.receive(in);
			
			// get the information we need to reply from the incoming data
			InetAddress ip = in.getAddress();
			port = in.getPort();
			
			// unmarshall the incoming data and execute the method
			IExchange unmarshalled_data = MarshallService.unmarshall(in.getData());
			unmarshalled_data.execute();
			
			// send the response out
			byte[] data_out = MarshallService.marshall(unmarshalled_data);
			DatagramPacket out = new DatagramPacket(data_out, data_out.length, ip, port);
			server.send(out);
		}
	}
	
	public static void main(String[] args) {
		try {
			ExchangeServer server = new ExchangeServer(1010);
			server.start();
			System.out.println("Exchange Server online...");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
