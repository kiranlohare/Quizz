package com.miniproject.quizz;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Result {
	public static final String QUERY = "SELECT * FROM SCORECARD";

	void diplayAllResult() {
		BuildConnection b = new BuildConnection();
		Connection con = b.getConnectionDb();
		try (PreparedStatement ps = con.prepareStatement(QUERY);) {
			if (con != null) {
				if (ps != null) {
					ResultSet rs = ps.executeQuery();
					if (rs != null) {
						while (rs.next()) {
							System.out.println("UID :" + rs.getInt(1));
							System.out.println("Name :" + rs.getString(2));
							System.out.println("score :" + rs.getString(3));
							System.out.println("oldScore :" + rs.getString(4));

						}
					}

				}
			}
		
				
			}

		
		catch (SQLException e) 
		{
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		Result r=new Result();
		r.diplayAllResult();
	}

}
