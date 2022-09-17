package com.miniproject.quizz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SignUp //
{
	//pre-compiled SQL Query
	public static final String QUERY="INSERT INTO USERS (NAME,EMAIL,PASSWORD) VALUES(?,?,?)";
	
	//instance variables
	private int isExecuted;
	private boolean isCheck;
	Connection con=null;
	BuildConnection b=null;
	
	public boolean signUp() 
	{
		//establish connect with database
		Users users=new Users();
		b=new BuildConnection();
		con=b.getConnectionDb();
		//try with resource
		try(Scanner sc= new Scanner(System.in);
			PreparedStatement ps=con.prepareStatement(QUERY);)
		{
			//user inputs to store register
			if(sc!=null)
			{
				while(true)
				{
					System.out.println("Enter your name : ");
					users.setName(sc.next());
					if(users.getName()!=null && !users.getName().equals("null"))
					{	
						break;
					}
				}
				
				while(true)
				{
					System.out.println("Enter your email : ");
					users.setUsername(sc.next()); 
					if (!users.getUsername().contains("@") || !users.getUsername().endsWith(".com")) 
					{
						System.out.println("Re-correct please !");
					}
					else 
					{
						break;
					}
				}
				
				while(true)
				{
					System.out.println("Enter your password : ");
					users.setPassword(sc.next());
					if(users.getPassword()!=null)
					{	
						break;
					}
				}
				if(con!=null)
				{
					if(ps!=null)
					{
						ps.setString(1, users.getName());
						ps.setString(2, users.getUsername());
						ps.setString(3, users.getPassword());
						isExecuted=ps.executeUpdate();
						if(isExecuted==1)
						{
							isCheck=true;
						}
					}
				}
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return isCheck;
	}
}
