package com.miniproject.quizz;

import java.sql.Connection;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SignUp extends AbstractQuizz
{
	Connection con=null;
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
	public Connection getCon() 
	{
		return con;
	}
	public void setCon(Connection con) 
	{
		this.con = con;
	}
	
	public void SignUp() 
	{
		try(Scanner sc= new Scanner(System.in);)
		{
			if(sc!=null)
			{
				while(true)
				{
					System.out.println("Enter your name : ");
					sc.nextLine();
					name=sc.nextLine();
					if(name!=null)
					{	
						break;
					}
				}
				
				while(true)
				{
					System.out.println("Enter your name : ");
					username=sc.next();
					if(username!=null)
					{	
						break;
					}
				}
				
				while(true)
				{
					System.out.println("Enter your name : ");
					password=sc.next();
					if(password!=null)
					{	
						break;
					}
				}
			}
		}
		catch (InputMismatchException e) 
		{
			e.printStackTrace();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	public static void main(String[] args) 
	{
		
	}	
}
