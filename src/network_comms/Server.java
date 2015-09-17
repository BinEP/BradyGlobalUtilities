package network_comms;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class Server {

	public static int port = 4446;
	public static int numOfPlayers;

	// public Server() throws IOException {
	// numOfPlayers = numPlayers;
	// new ServerThread().start();
	// }

	public Server(int numPlayers) throws IOException {
		numOfPlayers = numPlayers;
		new ServerThread().start();
	}

	public static void main(String[] args) throws IOException {
		new ServerThread().start();
	}

	public static class ServerThread extends Thread {

		public List<String> hosts = new ArrayList<String>();
		public boolean running = false;
		private DatagramSocket socket;

		public ServerThread() throws IOException {
			this("Server");
		}

		public ServerThread(String name) throws IOException {
			super(name);
			socket = new DatagramSocket(port);
			running = true;

		}

		public void run() {

			while (running) {

				byte[] buf = new byte[1024];
				DatagramPacket packet = new DatagramPacket(buf, buf.length);
				System.out.println("Ready");

				try {
					socket.receive(packet);

					InetAddress address = packet.getAddress();
					String host = address.getHostAddress();
					if (!hosts.contains(host)) {
						if (hosts.size() < numOfPlayers) {
							hosts.add(host);
							if (hosts.size() == numOfPlayers) {
								String message = "Done";
								buf = message.getBytes();
								sendToAll(message.getBytes());
							}
						} else {
							buf = new String("Too many players").getBytes();
							packet = new DatagramPacket(buf, buf.length, InetAddress.getByName(host), Client.port);
							socket.send(packet);
						}

					}

					buf = packet.getData();

					if (hosts.size() == numOfPlayers) {
						sendToAll(buf);
					}

				} catch (IOException e) {
					e.printStackTrace();
				}

			}

			socket.close();
		}

		private void sendToAll(byte[] buf) throws IOException {
			for (String h : hosts) {
				DatagramPacket packet = new DatagramPacket(buf, buf.length, InetAddress.getByName(h), Client.port);
				socket.send(packet);
			}
		}
	}
}
