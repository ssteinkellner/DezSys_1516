package ak_ss.client;

public class Model {
	public static final int CONNECT = 0, SEND = 1;
	public static final String CMD_CONNECT = "connect", CMD_SEND = "send";
	private int state, port;
	private String host, lastMsg;
	
	public Model() {
		this("localhost",1204);
	}
	
	public Model(String host, int port){
		this(CONNECT, host, port, "Bereit zum Verbindungsaufbau");
	}
	
	public Model(int state, String host, int port, String lastMsg) {
		this.port = port;
		this.state = state;
		this.host = host;
		this.lastMsg = lastMsg;
	}


	public int getState() {
		return state;
	}
	
	public void setState(int state) {
		this.state = state;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getLastMsg() {
		return lastMsg;
	}
	
	public void setLastMsg(String lastMsg) {
		this.lastMsg = lastMsg;
	}
}
