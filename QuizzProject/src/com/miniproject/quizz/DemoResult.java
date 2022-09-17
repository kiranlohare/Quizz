package com.miniproject.quizz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DemoResult {
	
		public static final String QUERY = "SELECT *  FROM SCORECARD  WHERE UID = ? ";

		void diplayResult() {
			BuildConnection b = new BuildConnection();
			Connection con = b.getConnectionDb();
			try (PreparedStatement ps = con.prepareStatement(QUERY);) {
				
				
				Scanner sc =new Scanner(System.in);
				System.out.println("Enter User Id");
				String uid = sc.next();
				
				if (con != null) {
					if (ps != null) 
					{
						ps.setString(1,uid);
						ResultSet rs = ps.executeQuery();
						if (rs != null) {
							while (rs.next()) {
								System.out.println("UID :" + rs.getInt(1));
								System.out.println("name:" +rs.getString(2));
								System.out.println("newscore :" + rs.getString(3));
								System.out.println("oldscore :" + rs.getString(4));
								

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
			DemoResult r=new DemoResult();
			r.diplayResult();
		}

	}

	
	
	

	
