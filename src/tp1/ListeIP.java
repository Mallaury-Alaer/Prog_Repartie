package tp1;

import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class ListeIP {
	public ListeIP(){}
	
	public static void main (String args[]) throws SocketException{
		Enumeration<NetworkInterface> list = NetworkInterface.getNetworkInterfaces();
		
		List<List<InterfaceAddress>> listA = new ArrayList<>();
		 //Question 2
		/*
		while(list.hasMoreElements()){
			listA.add(list.nextElement().getInterfaceAddresses());
		}
		for(List<InterfaceAddress> l : listA){
			for(InterfaceAddress addr : l){
				System.out.println(addr);
			}
		}
		*/
		//Question 3
		
		List<Integer> mtu = new ArrayList<>();
		while(list.hasMoreElements()){
			mtu.add(list.nextElement().getMTU());
		}
		 for(int i : mtu){
			 System.out.println(i);
		 }
	}
}
