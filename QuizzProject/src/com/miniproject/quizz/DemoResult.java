package com.miniproject.quizz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DemoResult {
	
		public static final String QUERY = "SELECT *  FROM SCORECARD  WHERE UID = ? ";
		//method to display result using UserId
		void diplayResult() {
			BuildConnection b = new BuildConnection();
			Connection con = b.getConnectionDb();
			//try to handle known exception
			try (PreparedStatement ps = con.prepareStatement(QUERY);) {
				
				//taking userid from user
				Scanner sc =new Scanner(System.in);
				System.out.println("\t\t\t====== Enter User Id =====");
				System.out.print("\t\t\t\t");
				String uid = sc.next();
				
				if (con != null) {
					if (ps != null) 
					{
						ps.setString(1,uid);
						ResultSet rs = ps.executeQuery();
						if (rs != null) {
							while (rs.next()) {
								System.out.println("\t\t\t===== YOUR DETAILS =====");
								System.out.println("\t\t\tUserID          :  " + rs.getInt(1));
								System.out.println("\t\t\tName            :  " +rs.getString(2));
								System.out.println("\t\t\tOld Score       :  " + rs.getString(4));
								System.out.println("\t\t\tLatest Score    :  " + rs.getString(3));
								System.out.println("\t\t\t===== KEEP PLAYING =====");
								
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

		public static void main(String[] args) {
			DemoResult r=new DemoResult();
			r.diplayResult();
		}

	}

	
	
	

	
