package ak_ss.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class StreamManager {
	
	private Socket sock;
	private BufferedReader  in;
	private PrintWriter  out;
	
	public StreamManager(Socket socket){
		sock = socket;
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter (socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean write(String text){
		if(out == null) return false;

		out.println(text);
		
		return true;
	}
	
	public String read(){
		if(in == null) return null;
		
		try {
			return in.readLine();
		} catch (IOException e) {
			return null;
		}
	}
	
	public Socket getSocket(){
		return sock;
	}

	@Override
	public String toString() {
		return "StreamManager[sock=" + sock + ",in=" + in + ",out=" + out + "]";
	}
}
