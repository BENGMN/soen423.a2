package domain;

public class ServerDetail {

	private String ip;
	private int port;
	
	
	public ServerDetail(String ip, int port) {
		this.setIp(ip);
		this.setPort(port);
	}


	public String getIp() {
		return ip;
	}


	public void setIp(String ip) {
		this.ip = ip;
	}


	public int getPort() {
		return port;
	}


	public void setPort(int port) {
		this.port = port;
	}
	
	
}
