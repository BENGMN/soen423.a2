package technical.log;

import java.io.IOException;

public class LogTest {

	public static void main (String[] args) throws IOException {
		ReservationLog.insert("MTL101",123456, 10);
		ReservationLog.update("MTL101",123456, 5);
		ReservationLog.insert("MTL101",123457, 10);
		ReservationLog.update("MTL101",123457, 4);
		ReservationLog.destroy("MTL101", 123456);
		
	}
}
