package ak_ss.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class StreamManager {
	
	private Socket sock;
	private BufferedReader in;
	private PrintWriter out;
	private boolean isOpen;
	
	public StreamManager(Socket socket){
		isOpen = true;
		sock = socket;
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean write(String text){
//		System.out.println("[DEBUG][StreamManager] write()");
		if(out == null) return false;

//		System.out.println("[DEBUG][StreamManager] before out.println");
		out.println(text);
		out.flush();
//		System.out.println("[DEBUG][StreamManager] after out.println");
		
		return true;
	}
	
	public String read(){
//		System.out.println("[DEBUG][StreamManager] read()");
		if(in == null) return null;
		try {
//			System.out.println("[DEBUG][StreamManager] before in.readLine");
			String msg = in.readLine();
//			System.out.println("[DEBUG][StreamManager] after in.readLine");
			return msg;
		} catch (IOException e) {
			isOpen = false;
			return null;
		}
	}
	
	public Socket getSocket(){
		return sock;
	}

	@Override
	public String toString() {
		return "StreamManager[sock=" + sock + ",in=" + in + ",out=" + out + ", isOpen=" + isOpen + "]";
	}
	
	public boolean isOpen(){
		return isOpen;
	}
}
