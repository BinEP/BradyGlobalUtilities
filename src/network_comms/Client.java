package network_comms;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import game_actions.Control;
import listener_control.SceneManager;

public class Client {

	private InetAddress address;
	public static int port = 4445;
	private Control game;

	public static void main(String[] args) throws IOException {
		
		Client c = new Client(null, "127.0.0.1");
		c.testConnection();
		
		DatagramSocket socket = new DatagramSocket();

		byte[] buf = new byte[1024];
		InetAddress address = InetAddress.getByName("127.0.0.1");
		DatagramPacket packet = new DatagramPacket(buf, buf.length, address, Server.port);
		socket.send(packet);

		packet = new DatagramPacket(buf, buf.length);
		socket.receive(packet);
		String received = new String(packet.getData(), 0, packet.getLength());
		System.out.println("Quote of the Moment: " + received);
		
		
		socket.close();
	}

	public Client(Control game, String host) {
		this.game = game;
		if (host.equals("localhost")) host = "127.0.0.1";
		try {
			address = InetAddress.getByName(host);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	public void testConnection() {
		try {
			DatagramSocket socket = new DatagramSocket();
			socket.connect(address, Server.port);
			System.out.println("Connection Successful");
			socket.close();
		} catch (SocketException e) {
			System.out.println("Trouble connecting to the Server");
		}
	}

	public void sendMessage(String property, Object data) {

		try {
			DatagramSocket socket = new DatagramSocket();
			Data d = new Data(property, data);
			byte[] buf = getBytes(d);
			DatagramPacket packet = new DatagramPacket(buf, buf.length, address, Server.port);

			socket.send(packet);
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static Data getData(byte[] data) throws IOException {

		ByteArrayInputStream in = new ByteArrayInputStream(data);
		ObjectInputStream is = new ObjectInputStream(in);
		
		try {
			if (is.readObject() instanceof String) {
				SceneManager.setScene("Start");
				return null;
			}
			return (Data) is.readObject();
		} catch (ClassNotFoundException e) {
			System.out.println("Could not cast to Data");
			return null;
		}

	}
	
	private static byte[] getBytes(Data d) throws IOException {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ObjectOutputStream os = new ObjectOutputStream(outputStream);
		os.writeObject(d);
		return outputStream.toByteArray();
	}

	public class ClientThread extends Thread {

		public boolean running = false;
		private DatagramSocket socket;

		public ClientThread() throws IOException {
			this("Client");
		}

		public ClientThread(String name) throws IOException {
			super(name);
			socket = new DatagramSocket(4445);
			running = true;
		}

		public void run() {

			while (running) {

				byte[] buf = new byte[1024];
				DatagramPacket packet = new DatagramPacket(buf, buf.length);
				try {
					socket.receive(packet);
					Data data = getData(packet.getData());
					if (data != null)
					game.gotMessage(data.property, data.data);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			socket.close();
		}
	}

	private static class Data implements Serializable {

		private static final long serialVersionUID = 1L;

		public Data(String prop, Object d) {
			property = prop;
			data = d;
		}

		public String property;
		public Object data;
	}
}
