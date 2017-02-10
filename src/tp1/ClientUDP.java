package tp1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClientUDP {
	private DatagramSocket dgSocket;

	ClientUDP() throws IOException {
		dgSocket = new DatagramSocket();
	}

	void go(String host, String port) throws IOException {
		DatagramPacket dgPacket = new DatagramPacket(new byte[0], 0, InetAddress.getByName(host), Integer.parseInt(port));
		String str;

		while (true) {
			dgSocket.send(dgPacket);
			System.out.println("Datagram received from " + dgPacket.getSocketAddress());

			dgPacket.setSocketAddress(dgPacket.getSocketAddress());
			str = new java.util.Date().toString() + "\n";
			byte[] bufDate = str.getBytes();
			dgPacket.setData(bufDate, 0, bufDate.length);

			dgSocket.receive(dgPacket);
		}
	}

	public static void main(String[] args) throws IOException {
		//final int DEFAULT_PORT = 9876;
		new ClientUDP().go(args[0], args[1]);
	}
}

