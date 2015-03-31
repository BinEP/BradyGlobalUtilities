package utility_classes;

import gameActions.Control;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ScoreInfo {
	
	private static DatabaseManagement database = new DatabaseManagement();
	
	public static void setScores(int score, String person) {
		database.insertInfo(person, score);
		database.sortData();
	}

	public static ArrayList<String[]> getScores() {
		return database.selectData();
	}

	public static ArrayList<String[]> scoreOrder(ArrayList<String> scores,
			ArrayList<String> people) {

		ArrayList<String[]> results = new ArrayList<String[]>();
		for (int i = 0; i < people.size(); i++) {

			String[] sp = { scores.get(i), people.get(i) };
			results.add(sp);
		}

		Collections.sort(results, new Comparator<String[]>() {
			@Override
			public int compare(String[] person1, String[] person2) {
				return person1[1].compareTo(person2[1]);
			}
		});
		Collections.sort(results, new Comparator<String[]>() {
			@Override
			public int compare(String[] score1, String[] score2) {
				return Integer.parseInt(score2[0]) - Integer.parseInt(score1[0]);
			}
		});

		return results;
	}

	public static void drawScores(Graphics2D g) {

		ArrayList<String[]> results = database.selectData();
		
		g.setFont(new Font("Joystix", Font.BOLD, 17));
		int i = 0;
		int yStart = 40;
		int xStart = 30;
		int lineH = 50;
		int l = 0;
		int lMax = (Control.HEIGHT - yStart) / 50;
		int r = 1;
		g.setColor(Color.WHITE);
		for (String[] c : results) {

			if (l > lMax) {
				i++;
				l = 0;
			}
			int x = (340 * i) + xStart;
			String dots = "";
			int m = String.valueOf(r).length();
			for (int n = 0; n < 11 - c[1].length() - m + 1; n++) {
				dots = dots.concat(".");
			}
			
			dots = dots.concat(".");
			g.drawString(r + ". " + c[1] + dots + c[0], x, (yStart - 2)
					+ (l * lineH));

			l++;
			r++;
		}
	}

	public static void enterName(Graphics2D g, int score, String pName) {

		int wSW = Windows.getWidth();
		g.setFont(new Font("Joystix", Font.BOLD, 40));
		CenteredText.draw("Enter", 100, g);
		CenteredText.draw("Your Name", 170, g);

		int barWidth = 35;
		int barSpace = 10 + barWidth;
		int tw = 450;
		int startText = (wSW - tw) / 2;
		g.setFont(new Font("Joystix", Font.BOLD, 20));
		for (int i = 0; i < 10; i++) {
			if (pName.length() > i) {
				CenteredText.draw(Character.toString(pName.charAt(i)), new Rectangle((barSpace * i) + startText, 440, barWidth, 8), g);
			}
			g.fillRect((barSpace * i) + startText, 442, barWidth, 8);
		}
	}
}
