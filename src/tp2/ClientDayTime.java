package tp2;

import java.net.*;
import java.io.*;

public class ClientDayTime {
	InetAddress server;
	int port = 13;
	
	public ClientDayTime(InetAddress server){
		this.server = server;
	}
	
	public String getTime(){
		if(server == null )
			return "You failed";
		String rep = "";
		
		try {
			Socket sock = new Socket(server, port);
			InputStream input = sock.getInputStream();
			InputStreamReader reader = new InputStreamReader(input);
			BufferedReader buf = new BufferedReader(reader);
			StringBuffer sb = new StringBuffer();
		     String theLine;
		      while ((theLine = buf.readLine()) != null) {
		        sb.append(theLine + "\r\n");
		      }
		      rep += sb.toString();
		      
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rep;
	}
	
	public static void main(String[] args){
		try {
			ClientDayTime client  = new ClientDayTime(InetAddress.getByName("127.0.0.1"));
			System.out.println(client.getTime());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
