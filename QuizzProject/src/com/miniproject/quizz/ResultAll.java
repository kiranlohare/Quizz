package com.miniproject.quizz;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultAll {
	
	public static final String QUERY = "SELECT * FROM SCORE";
   
	//method to display result
	void diplayAllResult() {
		
		BuildConnection b = new BuildConnection();
		Connection con = b.getConnectionDb();
		
		//try to handle known exception
		try (PreparedStatement ps = con.prepareStatement(QUERY);) {
			if (con != null) {
				if (ps != null) {
					ResultSet rs = ps.executeQuery();
					if (rs != null) {
						while (rs.next()) {
							System.out.println("\t\t\t===== YOUR DETAILS =====");
							System.out.println("\t\t\tUserID          :  " + rs.getInt(1));
							System.out.println("\t\t\tOld Score       :  " + rs.getString(3));
							System.out.println("\t\t\tLatest Score    :  " + rs.getString(2));
							System.out.println("\t\t\t===== KEEP PLAYING =====");
							System.out.println("                              ");

						}
					}
				}
			}
		
		//to handle SQL exception known exception	
		}catch (SQLException e) 
		{
			e.printStackTrace();
		}
		//to handle Unknown exception
		catch (Exception e) {
			e.printStackTrace();
		}

	}
}
