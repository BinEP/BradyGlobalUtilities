package utilityClasses;

import java.sql.*;
import java.util.ArrayList;
import org.sqlite.JDBC;

public class DatabaseManagement {

	private Connection database;
	private Statement newTableCommand;
	private Statement insertDataCommand;
	private Statement selectData;
	private Statement sortData;
	private ResultSet resultData;
	private String[] fields;
	private String tableName = "SCORES";
	private static int uniqueID = 1;
	

	public DatabaseManagement() {
		// TODO Auto-generated constructor stub
		connect();
//		newTable("SCORES");
		

	}

	public void connect() {

		try {
			if (database == null || database.isClosed()) {
			connectCommand();
			newTable("SCORES");
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void connectCommand() throws ClassNotFoundException,
			SQLException {

		Class.forName("org.sqlite.JDBC");
		database = DriverManager.getConnection("jdbc:sqlite:InfoFiles/scores.db");
		database.setAutoCommit(false);
		// System.err.println(e.getClass().getName() + ": " + e.getMessage());
		// System.exit(0);

		System.out.println("Opened database successfully");

	}

	public void newTable(String tableName) {

		try {
			newTableCommand(tableName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Table created already");
//			e.printStackTrace();
		}

	}

	private void newTableCommand(String tableName) throws SQLException {

		connect();
		Statement checkTable = database.createStatement();
		String sql = "SELECT * FROM sqlite_master WHERE type='table' AND name='SCORES';";
		ResultSet r = checkTable.executeQuery(sql);
//		System.out.println(r);
		
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
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	private ArrayList<String[]> selectDataCommand() throws SQLException {

		connect();
		ArrayList<String[]> results = new ArrayList<String[]>();
		selectData = database.createStatement();
		resultData = selectData
				.executeQuery("SELECT * FROM " + tableName + ";");

		while (resultData.next()) {
			int id = resultData.getInt("id");
			String name = resultData.getString("name");
			int score = resultData.getInt("score");
//			System.out.println("ID = " + id);
//			System.out.println("NAME = " + name);
//			System.out.println("SCORE = " + score);
			
			results.add(new String[]{"" + score, name});
			
//			System.out.println();
		}
		
		resultData.close();
		selectData.close();
		closeConnections();
		return results;
	}
	
	public void sortData() {
		try {
			sortDataCommand();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void sortDataCommand() throws SQLException {
		
		connect();
		sortData = database.createStatement();
		sortData.executeQuery("SELECT * FROM " + tableName + " ORDER BY SCORE, NAME ASC");
		
		sortData.close();
		database.commit();
		closeConnections();
		
	}
	
	public void closeConnections() {
		
//		try {
//			
//			database.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}
	
	

	public static void main(String[] args) {
		DatabaseManagement d = new DatabaseManagement();
		d.newTable("SCORES");
		System.out.println(d.selectData());
		d.insertInfo("Brady", 6);
		System.out.println(d.selectData());

	}
}
