package tp2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SrvBeg {
	int  port;
	ServerSocket sSocket;
	Socket socket;
	String phrase;
	
	public SrvBeg(int port){
		this.port = port;
		
		try {
			sSocket = new ServerSocket(port);
			
			while(true){
				socket = sSocket.accept();
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter sortie = new PrintWriter(socket.getOutputStream(), true);
				phrase = in.readLine();
				sortie.println(begaiement(phrase));
				System.out.println("********** DEBUT **********");
				System.out.println("Infos client : " + socket.getInetAddress()+"/"+port);
				System.out.println("Nombre de begaiements: " + getNbBeg(phrase));
				System.out.println("Phrase a transformer: " +getPhrase(phrase));
				System.out.println("**********  FIN  **********");
				socket.close();
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String begaiement(String phrase){
		String[] mots = phrase.split(" ");
		int nb = -1;
		String rep = "";
		if(phrase.substring(0,1).matches("[0-9]")){
			 nb = Integer.parseInt(phrase.substring(0,1));
			 rep += "0";
		}else{
			return "1Erreur nombre \n";
		}
		for(int i = 0; i<mots.length; i++){
			for(int j = 0; j<nb; j++){
				if(!mots[i].contains("\n"))
					if(mots[i].contains(":"))
						rep += mots[i].substring(2) + " ";
					else
						rep += mots[i] + " ";
				else
					rep += " " + mots[i].substring(0, mots[i].lastIndexOf("\n"));
			}
		}
		rep += "\n";
		return rep;
	}
	
	public int getNbBeg(String phrase){
		if(phrase.substring(0, 1).matches("[0-9]"))
			return Integer.parseInt(phrase.substring(0,1));
		else
			return 0;
	}
	
	public String getPhrase(String phrase){
		if(getNbBeg(phrase) != 0)
			return phrase.substring(2);
		else
			return "Phrase incorrect";
	}
	
	public static void main(String[] args){
		new SrvBeg(9876);
	}
}
