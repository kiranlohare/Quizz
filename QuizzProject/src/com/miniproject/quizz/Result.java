package com.miniproject.quizz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Result 
{
	public static final String QUERY = "SELECT *  FROM SCORE  WHERE UID = ? ";
	//method to display result using UserId
	void diplayResult(int id) 
	{
		BuildConnection b = new BuildConnection();
		Connection con = b.getConnectionDb();
		//try to handle known exception
		try (PreparedStatement ps = con.prepareStatement(QUERY);) 
		{
			if (con != null) 
			{
				if (ps != null) 
				{
					ps.setInt(1,id);
					ResultSet rs = ps.executeQuery();
					if (rs != null) 
					{
						if (rs.next()) 
						{
							System.out.println("\t\t\t\t===== YOUR DETAILS =====");
							System.out.println("\t\t\t\tUserID            :  " + rs.getInt(1));
							System.out.println("\t\t\t\tOld Score         :  " + rs.getString(3));
							System.out.println("\t\t\t\tLatest Score      :  " + rs.getString(2));
							System.out.println("\t\t\t\t===== KEEP PLAYING =====");
						}
						else
						{
							System.out.println("User ID Not found! You have not taken test Yet!!!");
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
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
