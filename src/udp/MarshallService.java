package udp;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class MarshallService implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Take a data object and convert it to a byte array
	 * for sending over the udp service
	 * @param data
	 * @return data passed in a marshalled format
	 * @throws IOException
	 */
	public static final byte[] marshall(IExchange data) throws IOException {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ObjectOutput out = new ObjectOutputStream(os);
		out.writeObject(data);
		out.close();
		return os.toByteArray();
	}
	
	/**
	 * Do essentially the opposite of the above method
	 * @param data
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static final IExchange unmarshall(byte[] data) throws IOException, ClassNotFoundException {
		ByteArrayInputStream bis = new ByteArrayInputStream(data);
		ObjectInputStream is = new ObjectInputStream(bis);
		IExchange unmarshalled_data = (IExchange)is.readObject();
		is.close();
		return unmarshalled_data;
		
	}
}
