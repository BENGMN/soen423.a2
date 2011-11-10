package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {

	private String host = null;
	private int port = 0;
	private boolean stayAlive = true;
	
	public UDPServer(String host, int port) {
		this.host = host;
		this.port = port;
	}
	
	public void stop() {
		this.stayAlive = false;
	}
	
	public void start() throws IOException, ClassNotFoundException {
		DatagramSocket server = new DatagramSocket(port);
		byte[] data = new byte[2048];
		
		while(stayAlive) {
			
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
}
