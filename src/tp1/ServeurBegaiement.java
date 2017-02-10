package tp1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServeurBegaiement {
	private DatagramSocket dgSocket;

	ServeurBegaiement(int pSrv) throws IOException {
		dgSocket = new DatagramSocket(pSrv);
	}

	void go() throws IOException {
		DatagramPacket dgPacket = new DatagramPacket(new byte[100], 100);
		String phrase;

		while (true) {
			String envoi="0";
			dgSocket.receive(dgPacket);
			System.out.println("Datagram received from " + dgPacket.getSocketAddress());
			
			byte[] rec = dgPacket.getData();
			phrase = new String(rec);
			String[] mots = phrase.split(" ");
			if(phrase.substring(0,1).matches("[0-9]")){
				mots[0] = mots[0].substring(1);
				for(int j = 0; j<mots.length; j++){
					for(int i= 0; i<Integer.parseInt(phrase.substring(0,1)); i++){
						if(!mots[j].contains("\n"))
							envoi+= mots[j] + " ";
						else
							envoi+= " " + mots[j].substring(0, mots[j].lastIndexOf("\n"));
					}
				}
			}else{
				envoi = "1Erreur : multiplicateur manquant ";
			}
			
			envoi+="\n";
			dgPacket.setSocketAddress(dgPacket.getSocketAddress());
			byte[] buf = envoi.getBytes();
			dgPacket.setData(buf, 0, buf.length);


			dgSocket.send(dgPacket);
		}
	}

	public static void main(String[] args) throws IOException {
		final int DEFAULT_PORT = 9876;
		new ServeurBegaiement( args.length == 0 ? DEFAULT_PORT : Integer.parseInt(args[0]) ).go();
	}

}
