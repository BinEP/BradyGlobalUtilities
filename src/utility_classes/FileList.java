package utility_classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class FileList {

	public static String checkFileName(String fileName) {

		if (fileName.indexOf('.') == -1)
			fileName = fileName.concat(".txt");

		return fileName;
	}

	public static ArrayList<String> getFileList(String filePath) {

		filePath = checkFileName(filePath);
		ArrayList<String> wordList = new ArrayList<String>();
		Scanner input;
		try {
			input = new Scanner(new File(filePath));
			while (input.hasNext()) {
				wordList.add(input.next());
			}
			input.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return wordList;
	}

	public static void addToFile(String filePath, String newLine) {

		PrintWriter fileWriter;
		filePath = checkFileName(filePath);

		try {
			fileWriter = new PrintWriter(new FileWriter(filePath, true));
			fileWriter.println(newLine);

			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void addToFile(String filePath, ArrayList<String> newLine) {

		PrintWriter fileWriter;
		filePath = checkFileName(filePath);

		try {
			fileWriter = new PrintWriter(new FileWriter(filePath, true));

			for (String line : newLine) {
				fileWriter.println(line);
			}

			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void insertLine(String filePath, String newLine, int index) {

		ArrayList<String> wordList = getFileList(filePath);
		wordList.add(index, newLine);
		overwriteFile(filePath, wordList);
	}

	public static void insertLines(String filePath, ArrayList<String> newLines,
			int index) {

		ArrayList<String> wordList = getFileList(filePath);
		wordList.addAll(index, newLines);
		overwriteFile(filePath, wordList);
	}

	public static void overwriteFile(String filePath, String newLine) {

		PrintWriter fileWriter;
		filePath = checkFileName(filePath);

		try {
			fileWriter = new PrintWriter(new FileWriter(filePath));
			fileWriter.println(newLine);

			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void overwriteFile(String filePath, ArrayList<String> newLine) {

		PrintWriter fileWriter;
		filePath = checkFileName(filePath);

		try {
			fileWriter = new PrintWriter(new FileWriter(filePath));

			for (String line : newLine) {
				fileWriter.println(line);
			}

			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String[] getFileArray(String filePath) {

		ArrayList<String> wordList = getFileList(filePath);
		String[] list = new String[wordList.size()];
		wordList.toArray(list);

		return list;
	}

	public static void verifyFile(String gameName, String folderPath) {

		File gameScores = new File(folderPath + gameName.concat("Scores.txt"));
		File gamePeople = new File(folderPath + gameName.concat("People.txt"));

		try {
			if (!gameScores.exists()) {
				gameScores.getParentFile().mkdirs();
				gameScores.createNewFile();
			}
			if (!gamePeople.exists()) {
				gamePeople.getParentFile().mkdirs();
				gamePeople.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
