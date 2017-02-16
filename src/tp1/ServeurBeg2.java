package tp1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServeurBeg2 {
	private DatagramSocket dgSocket;

	ServeurBeg2(int pSrv) throws IOException {
		dgSocket = new DatagramSocket(pSrv);
	}

	void go() throws IOException {
		DatagramPacket dgPacket;

		while (true) {
			dgPacket = new DatagramPacket(new byte[100], 100);
			dgSocket.receive(dgPacket);
			System.out.println("Datagram received from " + dgPacket.getSocketAddress());
			
			byte[] rec = dgPacket.getData();
			String phrase = new String(rec);
			String envoi = begaiement(phrase);
			dgPacket.setSocketAddress(dgPacket.getSocketAddress());
			byte[] buf = envoi.getBytes();
			dgPacket.setData(buf, 0, buf.length);


			dgSocket.send(dgPacket);
		}
	}
	
	String begaiement(String toBeg){
		String[] mots = new String[1];
		if(toBeg.contains(" "))
			mots = toBeg.split(" ");
		else
			mots[0] = toBeg;
		int nb = -1;
		String rep = "";
		if(toBeg.substring(0,1).matches("[0-9]")){
			 nb = Integer.parseInt(toBeg.substring(0,toBeg.lastIndexOf(':')));
			 rep += "0";
		}else{
			return "1Erreur : multiplicateur manquant\n";
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

	public static void main(String[] args) throws IOException {
		final int DEFAULT_PORT = 9876;
		new ServeurBeg2( args.length == 0 ? DEFAULT_PORT : Integer.parseInt(args[0]) ).go();
	}

}
