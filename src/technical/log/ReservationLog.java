package technical.log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class ReservationLog {

	private static final String LOG_DIR = "/home/ben/workspace/soen423.a1/src/server/sales";
	
	public ReservationLog(String show_id) throws IOException {
		super();
		File f = new File(LOG_DIR, show_id);
		if (!f.exists()) {
			f.createNewFile();
		}
	}
	

	public static void insert(String show_id, int customer_id, int qty) throws IOException {
		File f = new File(LOG_DIR, show_id);
		FileWriter out = new FileWriter(f, true);
		out.write(customer_id+","+qty+"\n");
		out.close();
	}
	

	public static void destroy(String show_id, int customer_id) throws IOException {
		File temp = new File(LOG_DIR,"destroy_tmp");
		File f = new File(LOG_DIR, show_id);
		
		BufferedReader in = new BufferedReader(new FileReader(f));
		FileWriter out = new FileWriter(temp);
		
		String line;
		while ((line = in.readLine()) != null){
			String[] s = line.split(",");
			if(Integer.parseInt(s[0]) != customer_id) {
				out.write(s[0]+","+s[1]+"\n");
			}
		}
		in.close();
		out.close();
		f.delete();
		temp.renameTo(f);		
	}


	public static String[] read(String show_id, int customer_id) throws IOException {
		File f = new File(LOG_DIR, show_id);
		BufferedReader in = new BufferedReader(new FileReader(f));
		String line;
		while ((line = in.readLine()) != null) {
			String[] s = line.split(",");
			if(Integer.parseInt(s[0]) == customer_id) {
				return s;
			}
		}
		return null;
	}
	
	public static HashMap<Integer, Integer> readAll(String show_id) throws NumberFormatException, IOException {
		HashMap<Integer, Integer> reservations = new HashMap<Integer, Integer>();
		File f = new File(LOG_DIR, show_id);
		BufferedReader in = new BufferedReader(new FileReader(f));
		String line;
		while ((line = in.readLine()) != null) {
			String[] s = line.split(",");
			reservations.put(Integer.parseInt(s[0]), Integer.parseInt(s[1]));
		}
		return reservations;
	}


	
	public static void update(String show_id, int customer_id, int qty) throws IOException {
		File temp = new File(LOG_DIR,"update_tmp");
		File f = new File(LOG_DIR, show_id);
		
		BufferedReader in = new BufferedReader(new FileReader(f));
		FileWriter out = new FileWriter(temp);
		
		String line;
		while ((line = in.readLine()) != null){
			String[] s = line.split(",");
			if(Integer.parseInt(s[0]) == customer_id) {
				out.write(customer_id+","+qty+"\n");
			}
			else {
				out.write(s[0]+","+s[1]+"\n");
			}
		}
		in.close();
		out.close();
		f.delete();
		temp.renameTo(f);
	}

}
