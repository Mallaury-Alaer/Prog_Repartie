package tp2;

import java.io.*;
import java.net.Socket;

public class ClientEcho {
	public static void main(String[] args) throws Exception {
		String hostname = "localhost";

		Socket theSocket = new Socket(hostname, 7);
		BufferedReader networkIn = new BufferedReader(new InputStreamReader(theSocket.getInputStream()));
		BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(theSocket.getOutputStream());
		System.out.println("Connected to echo server");

		while (true) {
			String theLine = userIn.readLine();
			if (theLine.equals(".") || theLine.toLowerCase().contains("fin"))
				break;
			out.println(theLine);
			out.flush();
			System.out.println(networkIn.readLine());
		}
		networkIn.close();
		out.close();
	}
}
