package tp1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClientBegaiement {
	private DatagramSocket dgSocket;

	ClientBegaiement() throws IOException {
		dgSocket = new DatagramSocket();
	}

	void go(String host, String port) throws IOException {
		DatagramPacket dgPacket = new DatagramPacket(new byte[50], 0, InetAddress.getByName(host), Integer.parseInt(port));
		String str;

		while (true) {
			dgSocket.send(dgPacket);
			System.out.println("Datagram received from " + dgPacket.getSocketAddress());

			dgPacket.setSocketAddress(dgPacket.getSocketAddress());
			str = "Test \n";
			byte[] buf = str.getBytes();
			dgPacket.setData(buf, 0, buf.length);

			dgSocket.receive(dgPacket);
		}
	}

	public static void main(String[] args) throws IOException {
		//final int DEFAULT_PORT = 9876;
		new ClientBegaiement().go(args[0], args[1]);
	}
}
