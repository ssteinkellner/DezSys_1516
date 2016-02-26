package ak_ss.client;

public class Model {
	public static final int CONNECT = 0, SEND = 1;
	private int state;
	private String host, lastMsg;
	
	public Model() {
		this(CONNECT,"localhost","Bereit zum Verbindungsaufbau");
	}
	
	public Model(int state, String host, String lastMsg) {
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
	
	public String getLastMsg() {
		return lastMsg;
	}
	
	public void setLastMsg(String lastMsg) {
		this.lastMsg = lastMsg;
	}

}
