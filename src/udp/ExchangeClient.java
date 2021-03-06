package udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ExchangeClient {
 private int port = 1010;
 private static String HOST_IP = "lcoalhost";
 private IExchange data;
 
 public ExchangeClient(int port) {
	 this.port = port;
 }
 
 public IExchange getData() {
	 return this.data;
 }
 
 public void sendData(IExchange exchangeObj) {
	 flush();
	 start(exchangeObj);
 }
 
 private void flush() {
	 this.data = null;
 }
 
 private void start(IExchange data) {
	 try {
		 // get the IP of the server
		 InetAddress ip = InetAddress.getByName(HOST_IP);
		 
		 // get some data containers ready
		 byte[] dataOut = MarshallService.marshall(data);
		 byte[] dataIn = new byte[dataOut.length]; // message received is same size
		 
		 // create a socket and a packet to transmit
		 DatagramSocket client = new DatagramSocket();
		 DatagramPacket data_out = new DatagramPacket(dataOut, dataOut.length, ip, port);
		 
		 // transmit the data
		 client.send(data_out);
		 
		 // receive a response
		 DatagramPacket data_in = new DatagramPacket(dataIn, dataIn.length);
		 client.receive(data_in);
		 
		 // unmarshall the data
		 this.data = MarshallService.unmarshall(data_in.getData());
		 client.close(); 
	 }
	 catch(Exception e) {
		 e.printStackTrace();
	 }
 }
}
