package utility_classes;

import java.io.IOException;
import java.net.Socket;

public class CheckPort {
	
	public static boolean checkPort(String host, int port) {
		try {
			
			@SuppressWarnings({ "unused", "resource" })
			Socket s = new Socket(host, port);
			System.out.println("Open");
			return true;
		} catch (IOException e) {
			System.out.println("Closed");
			return false;
		}
	}
}
