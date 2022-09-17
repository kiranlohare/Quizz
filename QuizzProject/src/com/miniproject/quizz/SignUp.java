package com.miniproject.quizz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SignUp //
{
	public static final String QUERY="INSERT INTO USERS (FULLNAME,USERNAME,PASSWORD) VALUES(?,?,?)";
	int isExecuted;
	BuildConnection build=null;
	private String name;
	private String username;
	private String password;
	
	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public String getUsername() 
	{
		return username;
	}
	public void setUsername(String username) 
	{
		this.username = username;
	}
	public String getPassword() 
	{
		return password;
	}
	public void setPassword(String password) 
	{
		this.password = password;
	}
	public boolean signUp() 
	{
		BuildConnection b=new BuildConnection();
		Connection con=b.getConnectionDb();
		try(Scanner sc= new Scanner(System.in);
			PreparedStatement ps=con.prepareStatement(QUERY);)
		{
			if(sc!=null)
			{
				while(true)
				{
					System.out.println("Enter your name : ");
					name=sc.next();
					if(name==null)
					{	
						System.out.println("please re-enter your name: ");
					}
					if(name!=null)
					{	
						break;
					}
				}
				
				while(true)
				{
					System.out.println("Enter your username : ");
					username=sc.next();
					if(username!=null)
					{	
						break;
					}
				}
				
				while(true)
				{
					System.out.println("Enter your password : ");
					password=sc.next();
					if(password!=null)
					{	
						break;
					}
				}
				if(con!=null)
				{
					if(ps!=null)
					{
						ps.setString(1, name);
						ps.setString(2, username);
						ps.setString(3, password);
						isExecuted=ps.executeUpdate();
						if(isExecuted==1)
						{
							System.out.println("Inserted successfully...!");
						}
					}
				}
			}
		}
		catch (InputMismatchException e) 
		{
			e.printStackTrace();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		
		return false;
	}
	public static void main(String[] args) throws SQLException 
	{
		SignUp sp=new SignUp();
		sp.signUp();
	}
}
