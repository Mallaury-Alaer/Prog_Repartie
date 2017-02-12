package tp2;

import java.io.BufferedReader;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientBeg {
	Socket socket;
	public ClientBeg(String host, int port) throws InterruptedException{
		try {
			boolean cont = true;
			
			while(cont){
				socket = new Socket(InetAddress.getByName(host), port);
				InputStream in = socket.getInputStream();
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader inServ = new BufferedReader(new InputStreamReader(in));
				BufferedReader inUser = new BufferedReader(new InputStreamReader(System.in));
				
				String phrase = "";
				System.out.println("Phrase = " + phrase);
				int beg = 0;
				System.out.println("Entrez une phrase : ");
				phrase += inUser.readLine();
				if(phrase.equalsIgnoreCase("fin") || phrase.equals(".")){
					System.out.println("Ok bye.");
					Thread.sleep(1000);
					cont = false;
				}else{
					System.out.println("Niveau de begaiement : ");
					beg = Integer.parseInt(inUser.readLine());
					
				}
				out.println(beg+":"+phrase);
				System.out.println(inServ.readLine());
			}
			socket.close();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws InterruptedException{
		new ClientBeg("127.0.0.1", 9876);
	}
}
