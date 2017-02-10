package tp2;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class ServiceDaytime {
	int port;
	ServerSocket sSocket;
	Socket socket;
	
	public ServiceDaytime(int port){
		this.port = port;
		try {
			sSocket = new ServerSocket(port);
			while(true){
				socket = sSocket.accept();
				PrintWriter sortie = new PrintWriter(socket.getOutputStream(), true);
				sortie.println(new Date());
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main (String[] args){
		new ServiceDaytime(9876);
	}
	
}
