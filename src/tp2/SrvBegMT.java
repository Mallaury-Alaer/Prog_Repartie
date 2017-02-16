package tp2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SrvBegMT {
	public static final int PORT_SERVICE = 9876;
	private ServerSocket s_Srv ;

	SrvBegMT() throws IOException {
		s_Srv = new ServerSocket ( PORT_SERVICE ) ;
	}

	// Réception clients et transfert vers un thread dédié
	private void attenteClient ( ) throws IOException {
		Socket s_Clt ;
		while ( true ) {
			s_Clt = s_Srv.accept() ;
			new ReponseTruc(s_Clt).start() ;
		}
	}

	// Test d’usage de la classe . . . et rie n d ’ a u t r e
	public static void main ( String [ ] args ) throws IOException {
		SrvBegMT srvTruc = new SrvBegMT( ) ;
		srvTruc.attenteClient( ) ;
	}

	class ReponseTruc extends Thread {

		private Socket s_Client ;
		private BufferedReader in;
		private PrintWriter out;

		ReponseTruc ( Socket sClient ) throws IOException {
			this.s_Client = sClient ;
			in = new BufferedReader(new InputStreamReader(sClient.getInputStream()));
			out = new PrintWriter(sClient.getOutputStream());

		}

		void dialogue( ) throws IOException{
			String phrase = in.readLine();
			out.print(begaiement(phrase));
			out.flush();
			in.close();
			out.close();
		}
		
		public String begaiement(String phrase){
			String[] mots = new String[1];
			if(phrase.contains(" "))
				mots = phrase.split(" ");
			else
				mots[0] = phrase;
			int nb = -1;
			String rep = "";
			if(phrase.substring(0,1).matches("[0-9]")){
				 nb = Integer.parseInt(phrase.substring(0,phrase.lastIndexOf(':')));
				 rep += "0";
			}else{
				return "1:Erreur nombre\n";
			}
			for(int i = 0; i<mots.length; i++){
				for(int j = 0; j<nb; j++){
					if(!mots[i].contains("\n"))
						if(mots[i].contains(":"))
								rep += mots[i].substring(mots[i].lastIndexOf(':')+1);
						
						else
							rep += mots[i] + " ";
						
					else
						if(mots[i].contains(":"))
							rep += mots[i].substring(mots[i].lastIndexOf(':')+1, mots[i].lastIndexOf("\n"))+" ";
						else
							rep += mots[i].substring(0, mots[i].lastIndexOf('\n')) + " ";
				}
			}
			rep += "\n";
			return rep;
		}

		public void run ( ) {
			try {
				dialogue( ) ;
				s_Client.close ( ) ;
			} catch ( IOException e ) {
				e.printStackTrace();
			}
		}
	}
}

