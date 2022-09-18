package com.miniproject.quizz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Result {
	
		public static final String QUERY = "SELECT *  FROM SCORE  WHERE UID = ? ";
		//method to display result using UserId
		void diplayResult(int id) {
			BuildConnection b = new BuildConnection();
			Connection con = b.getConnectionDb();
			//try to handle known exception
			try (PreparedStatement ps = con.prepareStatement(QUERY);) {
				
				//taking userid from user
//				@SuppressWarnings("resource")
//				Scanner sc =new Scanner(System.in);
//				System.out.println("\t\t\t====== Enter User Id =====");
//				System.out.print("\t\t\t\t");
//				String uid = sc.next();
				
				if (con != null) {
					if (ps != null) 
					{
						ps.setInt(1,id);
						ResultSet rs = ps.executeQuery();
						if (rs != null) 
						{
							if (rs.next()) 
							{
								System.out.println("\t\t\t===== YOUR DETAILS =====");
								System.out.println("\t\t\tUserID          :  " + rs.getInt(1));
								System.out.println("\t\t\tOld Score       :  " + rs.getString(3));
								System.out.println("\t\t\tLatest Score    :  " + rs.getString(2));
								System.out.println("\t\t\t===== KEEP PLAYING =====");
								
							}
							else
							{
								System.out.println("User ID Not found!");
							}
						}
				    }
				}
			}

			//to handle SQL exception known exception
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			//to handle Unknown exception
			catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
