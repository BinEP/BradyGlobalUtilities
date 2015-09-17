package game_actions;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import listener_control.SceneManager;
import network_comms.Client;
import network_comms.Server;
import utility_classes.ScanNetwork;
import utility_classes.Windows;

/**
 * Runs the game. Makes a new JFrame and adds the UserGame to the frame
 */
public class Runner extends JFrame {

	private static final long serialVersionUID = 7320121689295681465L;

	public static void tryFullScreen(Window window) {

		if (isMacOSX()) {
			enableFullScreenMode(window);
			enterFullScreen(window);
		}
	}

	public static void enableFullScreenMode(Window window) {
		try {
			Class<?> FSUClass = Class.forName("com.apple.eawt.FullScreenUtilities");
			Class<?> params[] = new Class[] { Window.class, Boolean.TYPE };
			Method allowFullScreen = FSUClass.getMethod("setWindowCanFullScreen", params);

			allowFullScreen.invoke(FSUClass, window, true);
		} catch (Throwable t) {
			System.err.println("Full screen mode is not supported");
			t.printStackTrace();
		}
	}

	public static void enterFullScreen(Window window) {

		try {
			Class<?> appClass = Class.forName("com.apple.eawt.Application");
			Class<?> params[] = new Class[] {};

			Method getApplication = appClass.getMethod("getApplication", params);
			Object application = getApplication.invoke(appClass);

			Method toggleFullScreen = application.getClass().getMethod("requestToggleFullScreen", Window.class);

			toggleFullScreen.invoke(application, window);
		} catch (Exception e) {
			System.out.println("An exception occurred while trying to toggle full screen mode");
			e.printStackTrace();
		}
	}

	private static boolean isMacOSX() {
		return System.getProperty("os.name").indexOf("Mac OS X") >= 0;
	}

	public Runner() {
		enableFullScreenMode(this);
		setVisible(true);
	}

	public Runner(Control game) {
		super(game.getGameName() + "!");
		System.out.println("Runner Called");

		boolean fullscreen = game.fullscreen;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		setResizable(Windows.isRESIZEABLE());
		setAlwaysOnTop(Windows.isALWAYS_ON_TOP());

		add(game, BorderLayout.CENTER);
		setSize(Windows.getWidth(), Windows.getREAL_HEIGHT());

		enableFullScreenMode(this);
		if (fullscreen)
			enterFullScreen(this);

		setVisible(true);
		System.out.println("Setup Called");
		game.setup();
	}

	/**
	 * A main class for the network TicTacToe game. Main routine shows a dialog
	 * where the user can choose to be a server or a client. If the user chooses
	 * to be a server, then a TicTacToeHub is created to manage the game; the
	 * game will not start until a second player has connected as a client. To
	 * act as a client, the user must know the host name or IP address of the
	 * computer and the port number where the server is waiting for a
	 * connection. When run as a client, this program does not create a hub;
	 * rather, it connects to the hub that was created by the server. In either
	 * case, a TicTacToeWindow is created where the game will be played.
	 */

	private static final int DEFAULT_PORT = 45017;
	private final static int CHAT_PORT = 37829;

	
	public static void main(String[] args) {
		networkGame(null);
	}
	
	public static void networkGame(Control game) {

		SceneManager.setScene("Netwait");
		System.out.println("Net starting");
		// First, construct a panel that will be placed into a JOptionPane
		// confirm dialog.

		JLabel message = new JLabel("Welcome to Networked Go Fish!", JLabel.CENTER);
		message.setFont(new Font("Serif", Font.BOLD, 16));
		// final JTextField numberOfPlayers = new JTextField("2", 2);
		final JTextField numOfPlayersInput = new JTextField("" + DEFAULT_PORT, 5);
		// final JTextField hostInput = new JTextField("localhost", 30);
		final JComboBox hostInput = new JComboBox();
		// final String[] choices = {"A", "B"};
		ScanNetwork scanning = new ScanNetwork();
		final String[] choices = scanning.checkHostsAtPort(DEFAULT_PORT);

		hostInput.setModel(new DefaultComboBoxModel(choices));
		hostInput.setSelectedIndex(0);

		final JTextField connectPortInput = new JTextField("" + DEFAULT_PORT, 5);

		final JRadioButton selectServerMode = new JRadioButton("Start a new game");
		final JRadioButton selectClientMode = new JRadioButton("Connect to existing game");

		ButtonGroup group = new ButtonGroup();
		group.add(selectServerMode);
		group.add(selectClientMode);
		ActionListener radioListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == selectServerMode) {

					// Host settings
					numOfPlayersInput.setEnabled(true);
					// numberOfPlayers.setEnabled(true);
					numOfPlayersInput.setEditable(true);

					// Client settings
					hostInput.setEnabled(false);
					connectPortInput.setEnabled(false);
					hostInput.setEditable(false);
					connectPortInput.setEditable(false);
				} else {

					// Host settings
					numOfPlayersInput.setEnabled(false);
					// numberOfPlayers.setEnabled(false);
					numOfPlayersInput.setEditable(false);

					// Client settings
					hostInput.setEnabled(true);
					connectPortInput.setEnabled(true);
					hostInput.setEditable(true);
					connectPortInput.setEditable(true);
				}
			}
		};
		selectServerMode.addActionListener(radioListener);
		selectClientMode.addActionListener(radioListener);

		selectServerMode.setSelected(true);

		// Client Settings disabled
		hostInput.setEnabled(false);
		connectPortInput.setEnabled(false);
		hostInput.setEditable(false);
		connectPortInput.setEditable(false);

		JPanel inputPanel = new JPanel();

		inputPanel.setLayout(new GridLayout(0, 1, 5, 5));
		inputPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 2),
				BorderFactory.createEmptyBorder(6, 6, 6, 6)));

		inputPanel.add(message);

		inputPanel.add(selectServerMode);

		// inputPanel.add(createRow("Players: ", numberOfPlayers));
		inputPanel.add(createRow("Number of Players: ", numOfPlayersInput));

		inputPanel.add(selectClientMode);

		inputPanel.add(createRow("Computer: ", hostInput));
		inputPanel.add(createRow("Port Number: ", connectPortInput));

		// Show the dialog, get the user's response and -- if the user doesn't
		// cancel -- start a game. If the user chooses to run as the server
		// then a TicTacToeGameHub (server) is created and after that a
		// TicTacToeWindow
		// is created that connects to the server running on localhost, which
		// was
		// just created. In that case, the game will wait for a second
		// connection.
		// If the user chooses to connect to an existing server, then only
		// a TicTacToeWindow is created, that will connect to the specified
		// host where the server is running.

		while (true) { // Repeats until a game is started or the user cancels.

			int action = JOptionPane.showConfirmDialog(null, inputPanel, "Network Go Fish",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

			if (action != JOptionPane.OK_OPTION)
				return;

			if (selectServerMode.isSelected()) {
				int playerNum;
				try {
					playerNum = Integer.parseInt(numOfPlayersInput.getText().trim());
					if (playerNum <= 0)
						throw new Exception();
				} catch (Exception e) {
					message.setText("Illegal port number!");
					numOfPlayersInput.selectAll();
					numOfPlayersInput.requestFocus();
					continue;
				}
//				Hub hub;
//				Hub chatHub;
				try {
//					hub = new GoFishGameHub(port);
//					chatHub = new Hub(CHAT_PORT);
					System.out.println("Server Start");
					new Server(playerNum);

				} catch (Exception e) {
					message.setText("Error: Can't listen on port " + playerNum);
					numOfPlayersInput.selectAll();
					numOfPlayersInput.requestFocus();
					continue;
				}
//				try {
//					ChatRoomWindow.newChat("localhost");
//					new GoFishWindow("localhost", port);
				System.out.println("Client made and runner being called");
					game.client = new Client(game, "localhost");
					new Runner(game);
//				} catch (IOException e) {
//					message.setText("Could not connect to server on localhost!!");
////					hub.shutDownHub();
//					continue;
//				}
				break;
			} else {
				String host;
				int port;
				try {
					host = choices[hostInput.getSelectedIndex()];
				} catch (Exception e) {

					host = (String) hostInput.getEditor().getItem();

				}
				// host = hostInput.getText();
				if (host.length() == 0) {
					message.setText("You must enter a computer name!");
					hostInput.requestFocus();
					continue;
				}
				try {
					port = Integer.parseInt(connectPortInput.getText().trim());
					if (port <= 0)
						throw new Exception();
				} catch (Exception e) {
					message.setText("Illegal port number!");
					connectPortInput.selectAll();
					connectPortInput.requestFocus();
					continue;
				}
//				try {
//					ChatRoomWindow.newChat(host);
//					new GoFishWindow(host, port);
				System.out.println("No server, just client");
					new Runner(game);
//				} catch (IOException e) {
//					message.setText("Could not connect to specified host and port.");
//					// hostInput.selectAll();
//
//					hostInput.requestFocus();
//					continue;
//				}
				break;
			}
		}

	}

	public static JPanel createRow(String labelText, JComponent field) {
		JPanel row = new JPanel();
		row = new JPanel();
		row.setLayout(new FlowLayout(FlowLayout.LEFT));
		row.add(Box.createHorizontalStrut(40));
		row.add(new JLabel(labelText));
		row.add(field);
		return row;

	}
}
