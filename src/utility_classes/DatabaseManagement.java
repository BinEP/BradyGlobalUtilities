package utility_classes;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseManagement {

	private Connection database;
	private Statement newTableCommand;
	private Statement insertDataCommand;
	private Statement selectData;
	private Statement sortData;
	private ResultSet resultData;
	private String tableName = "SCORES";
	private static int uniqueID = 1;
	
	public DatabaseManagement() {
		connect();
	}

	public void connect() {
		try {
			if (database == null || database.isClosed()) {
			connectCommand();
			newTable("SCORES");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	private void connectCommand() throws ClassNotFoundException,
			SQLException {

		Class.forName("org.sqlite.JDBC");
		database = DriverManager.getConnection("jdbc:sqlite:" + Windows.getResourceFolder() + "/scores.db");
		database.setAutoCommit(false);
		System.out.println("Opened database successfully");
	}

	public void newTable(String tableName) {
		try {
			newTableCommand(tableName);
		} catch (SQLException e) {
			System.out.println("Table created already");
		}
	}

	private void newTableCommand(String tableName) throws SQLException {

		connect();
		String sql = "SELECT * FROM sqlite_master WHERE type='table' AND name='SCORES';";
		
		this.tableName = tableName;
		newTableCommand = database.createStatement();

		sql = "CREATE TABLE " + tableName + " "
				+ "(ID INT PRIMARY KEY     NOT NULL,"
				+ " NAME           TEXT    NOT NULL, "
				+ " SCORE            INT     NOT NULL)";
		newTableCommand.executeUpdate(sql);
		newTableCommand.close();
		closeConnections();
	}

	public void insertInfo(String name, int score) {
		try {
			insertInfoCommand(name, score);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void insertInfoCommand(String name, int score)
			throws SQLException {

		uniqueID = selectData().size() + 1;
		connect();
		
		insertDataCommand = database.createStatement();
		String sql = "INSERT INTO " + tableName + " (ID,NAME,SCORE) "
				+ "VALUES (" + uniqueID + ", '" + name + "', " + score + ");";
		uniqueID++;
		insertDataCommand.executeUpdate(sql);
		
		insertDataCommand.close();
		database.commit();
		closeConnections();
		System.out.println("Records created successfully");
	}

	public ArrayList<String[]> selectData() {
		try {
			return selectDataCommand();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private ArrayList<String[]> selectDataCommand() throws SQLException {

		connect();
		ArrayList<String[]> results = new ArrayList<String[]>();
		selectData = database.createStatement();
//		resultData = selectData
//				.executeQuery("SELECT * FROM " + tableName + ";");
		resultData = selectData.executeQuery("SELECT * FROM " + tableName + " ORDER BY SCORE DESC, NAME ASC");
//		sortData();
		while (resultData.next()) {
			String name = resultData.getString("name");
			int score = resultData.getInt("score");
			
			results.add(new String[]{"" + score, name});
		}
		
		resultData.close();
		selectData.close();
		closeConnections();
		return results;
	}
	
//	public void sortData() {
//		try {
//			sortDataCommand();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	private void sortDataCommand() throws SQLException {
//		
//		connect();
//		sortData = database.createStatement();
//		resultData = sortData.executeQuery("SELECT * FROM " + tableName + " ORDER BY SCORE, NAME ASC");
//		
//		sortData.close();
//		database.commit();
//		closeConnections();
//	}
	
	public void closeConnections() {}
}
