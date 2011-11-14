package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import server.BoxOfficeImpl;

public class UDPServer implements Runnable {

	private String host = null;
	private int port = 0;
	private boolean stayAlive = true;
	private BoxOfficeImpl boxOffice = null;
	
	public UDPServer(String host, int port, BoxOfficeImpl boxOffice) {
		this.host = host;
		this.port = port;
		this.boxOffice = boxOffice;
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
			unmarshalled_data.execute(this.boxOffice);
			
			// send the response out
			byte[] data_out = MarshallService.marshall(unmarshalled_data);
			DatagramPacket out = new DatagramPacket(data_out, data_out.length, ip, port);
			server.send(out);
		}
	}

	@Override
	public void run() {
		try {
			start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
