package com.miniproject.quizz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
/**
 * @author Suhas Patil
 * @since 1.0
 */
public class SignUp //
{
	//pre-compiled SQL Query
	//SELECT count(*) FROM quizz.users where email='suhas@gmail.com';
	public static final String QUERY="INSERT INTO USERS (NAME,EMAIL,PASSWORD,SECURITY) VALUES(?,?,?,?)";
	public static final String QUERY1="SELECT COUNT(*) FROM USERS WHERE EMAIL=?";
	
	//instance variables
	private int isExecuted;
	Connection con=null;
	BuildConnection b=null;
	
	public int signUp() 
	{
		String valid=null;
		//establish connect with database
		Users users=new Users();
		b=new BuildConnection();
		con=b.getConnectionDb();
		//try with resource
		try(PreparedStatement ps1=con.prepareStatement(QUERY);)
		{
			@SuppressWarnings("resource")
			Scanner sc= new Scanner(System.in);
			//user inputs to store register
			if(sc!=null)
			{
				valid="^[A-Za-z]+$";
				while(true)
				{
					System.out.println("Enter your name : ");
					String name=sc.nextLine();
					if(name.matches(valid))
					{
						users.setName(name);
						break;
					}
					else
					{
						System.out.println("please correct name");
					}
				}
				
				while(true)
				{
					System.out.println("Enter your email : ");
					String temp=sc.next(); 
					valid="^[a-zA-Z0-9_.-]+@[a-zA-Z.]+.[a-zA-Z]+$";
					if(temp.matches(valid))
					{
						users.setUsername(temp);
						break;
					}
					else
					{
						System.out.println("Recorrect email address");
					}
				}
				PreparedStatement ps2=con.prepareStatement(QUERY1);
				ps2.setString(1, users.getUsername());
				ResultSet rs=ps2.executeQuery();
				rs.next();
				int records=rs.getInt(1);
				if(records>0)
				{
					System.out.println("user is present with the user id");
				}
				else
				{
					while(true)
					{
						System.out.println("Enter your password : ");
						users.setPassword(sc.next());
						if(users.getPassword()!=null)
						{	
							break;
						}
					}
					
					while(true)
					{
						System.out.println("Enter security question : ");
						System.out.println("What is your pet name?");
						users.setSecuritycheck(sc.next());
						if(users.getSecuritycheck()!=null)
						{	
							break;
						}
					}
					
					if(con!=null)
					{
						if(ps1!=null)
						{
							ps1.setString(1, users.getName());
							ps1.setString(2, users.getUsername());
							ps1.setString(3, users.getPassword());
							ps1.setString(4, users.getSecuritycheck());
							isExecuted=ps1.executeUpdate();
							if(isExecuted==1)
							{
								PreparedStatement ps3=con.prepareStatement("select uid from users where email=?");
								ps3.setString(1, users.getUsername());
								ResultSet rs1= ps3.executeQuery();
								rs1.next();
								return rs1.getInt(1);
							}
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
		return 0;
	}
}