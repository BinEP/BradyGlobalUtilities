package utilityClasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseManagement {

	Connection database;
	Statement newTableCommand;
	Statement insertDataCommand;
	Statement selectData;
	Statement sortData;
	ResultSet resultData;
	String[] fields;
	String tableName;

	public DatabaseManagement() {
		// TODO Auto-generated constructor stub
		database = connect();
		newTable("SCORES");

	}

	public Connection connect() {

		try {

			return connectCommand();

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	private Connection connectCommand() throws ClassNotFoundException,
			SQLException {

		Connection c = null;
		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:InfoFiles/scores.db");
		c.setAutoCommit(false);
		// System.err.println(e.getClass().getName() + ": " + e.getMessage());
		// System.exit(0);

		System.out.println("Opened database successfully");
		return c;

	}

	public void newTable(String tableName) {

		try {
			newTableCommand(tableName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void newTableCommand(String tableName) throws SQLException {

		this.tableName = tableName;
		newTableCommand = database.createStatement();

		String sql = "CREATE TABLE " + tableName + " "
				+ "(ID INT PRIMARY KEY     NOT NULL,"
				+ " NAME           TEXT    NOT NULL, "
				+ " SCORE            INT     NOT NULL)";
		newTableCommand.executeUpdate(sql);
		newTableCommand.close();
		database.close();

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

		insertDataCommand = database.createStatement();
		String sql = "INSERT INTO " + tableName + " (ID,NAME,SCORE) "
				+ "VALUES (1, " + name + ", " + score + ");";
		insertDataCommand.executeUpdate(sql);
		insertDataCommand.close();
		database.commit();
		database.close();
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

		ArrayList<String[]> results = new ArrayList<String[]>();
		selectData = database.createStatement();
		resultData = selectData
				.executeQuery("SELECT * FROM " + tableName + ";");

		while (resultData.next()) {
			int id = resultData.getInt("id");
			String name = resultData.getString("name");
			int score = resultData.getInt("score");
			System.out.println("ID = " + id);
			System.out.println("NAME = " + name);
			System.out.println("SCORE = " + score);
			
			results.add(new String[]{"" + score, name});
			
			System.out.println();
		}
		resultData.close();
		selectData.close();
		database.close();
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
		
		sortData = database.createStatement();
		sortData.executeQuery("SELECT * FROM " + tableName + " ORDER BY SCORE, NAME ASC");
		
		sortData.close();
		database.commit();
		database.close();
	}

	public static void main(String[] args) {
		new DatabaseManagement();
	}
}
