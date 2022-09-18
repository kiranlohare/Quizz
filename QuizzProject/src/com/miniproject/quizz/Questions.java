package com.miniproject.quizz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Questions extends AbstractQuizz {
	private BuildConnection bcon = null;
	private Connection con = null;
	ResultSet rs = null;
	int uid;

	/**
	 * @param uid Enter user ID
	 */
	public Questions(int uid) {
		this.uid = uid;
	}

	@Override
	public void getQuestions() {
		bcon = new BuildConnection();
		con = bcon.getConnectionDb();
		Scanner sc = new Scanner(System.in);
		int randomnumber = 0;
		int count = 0;
		int selection = 0;
		int oldscore = 0;
		int newscore = 0;
		LinkedList<Integer> RandomNumbersLinkedList = new LinkedList<Integer>();
		LinkedHashMap<Integer, Boolean> Result = new LinkedHashMap<Integer, Boolean>();

		try {
			PreparedStatement getQuestionsFromDB = con.prepareStatement(
					"SELECT qno, question, choice1, choice2, choice3, choice4, description, answer FROM questions where qno = ?");
			while (RandomNumbersLinkedList.size() < 10) {
				randomnumber = generateRandomNumber(1, 10, RandomNumbersLinkedList);
				RandomNumbersLinkedList.add(randomnumber);
				getQuestionsFromDB.setInt(1, randomnumber);
				rs = getQuestionsFromDB.executeQuery();
				while (rs.next()) {
					System.out.println();
					System.out.print("Q" + ++count + ". ");
					System.out.println(rs.getString(2));
					System.out.print(" Description- ");
					System.out.println(rs.getString(7));
					System.out.print(" Option-1. ");
					System.out.println(rs.getString(3) + " ");
					System.out.print(" Option-2. ");
					System.out.println(rs.getString(4) + " ");
					System.out.print(" Option-3. ");
					System.out.println(rs.getString(5) + " ");
					System.out.print(" Option-4. ");
					System.out.println(rs.getString(6) + " ");
					System.out.print(" Select your correct answer>> ");
					while ((selection = validation(sc.next())) == -1) {
						System.out.println(" Make sure you have entered valid option..!");
						System.out.print(" Select your correct answer>> ");
					}
					if (selection == rs.getInt(8)) {
						Result.put(rs.getInt(1), true);
					} else {
						Result.put(rs.getInt(1), false);
					}
				}
			}

			PreparedStatement getOldScorePS = con.prepareStatement("SELECT newscore FROM score where uid = ?");
			getOldScorePS.setInt(1, uid);
			ResultSet getOldScoreRS = getOldScorePS.executeQuery();
			newscore = (int) Result.keySet().stream().map(x -> (Result.get(x))).filter(x -> (x == true)).count();
			if (getOldScoreRS.next()) { // Check if user is new
				oldscore = getOldScoreRS.getInt(1); // get old value
				PreparedStatement setOldUserScorePS = con
						.prepareStatement("update score set newscore = ?, oldscore = ? where uid = ?");
				setOldUserScorePS.setInt(1, newscore);
				setOldUserScorePS.setInt(2, oldscore);
				setOldUserScorePS.setInt(3, uid);
				setOldUserScorePS.execute();
			} else {
				PreparedStatement setNewUserScorePS = con.prepareStatement("insert into score values(?,?,?)");
				setNewUserScorePS.setInt(1, uid);
				setNewUserScorePS.setInt(2, newscore);
				setNewUserScorePS.setInt(3, 0);
				setNewUserScorePS.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println();
		System.out.println("Your Grade-> " + getGrade(newscore));
		System.out.println();
		Result.keySet().stream().forEach(x -> {
			System.out.print("Q." + Result + " ");
			String str = (Result.get(x)) ? "Right" : "Wrong";
			System.out.println(str);
		});
	}

	/**
	 * Generates the random number whose value will be >= start && <=end and
	 * 
	 * @param start         Lower limit of random number
	 * @param end           Upper limit of random number
	 * @param donotrepeatLS Same int value from LinkedList will be not returned
	 */
	private int generateRandomNumber(int start, int end, LinkedList<Integer> donotrepeatLS) {
		Random random = new Random();
		boolean addedInSet = false;
		int randomnumber = 0;
		HashSet<Integer> generatedQno = new HashSet<Integer>();
		generatedQno.addAll(donotrepeatLS);
		while (!addedInSet) {
			randomnumber = start + random.nextInt(end);
			addedInSet = generatedQno.add(randomnumber);
		}
		return randomnumber;
	}

	/**
	 * Validate the given string contains only number or not
	 * 
	 * @param a String to be validated
	 * @return int returns converted value if string contains number (>=1 && <=4)
	 * 
	 */
	private int validation(String a) {
		int b = 0;
		try {
			b = Integer.valueOf(a);
			if (b < 1 || b > 4)
				return -1;
		} catch (Exception e) {
			return -1;
		}
		return b;
	}

	/**
	 * Converts the marks to Grade
	 * 
	 * @param marks 
	 * @return String Grades will be returned depending on given marks  
	 */
	private String getGrade(int marks) {
		if (marks >= 8) {
			return "Grade A";
		} else if (marks >= 6) {
			return "Grade B";
		} else if (marks == 5) {
			return "Grade C";
		} else {
			return "Grade D";
		}
	}
}