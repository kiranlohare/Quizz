package com.miniproject.quizz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ForgotPassword extends Users
{
	//select security from users where email='surajkuradecode@gmail.com';
	public static final String QUERY1="SELECT SECURITY FROM USERS WHERE EMAIL=?";
	public static final String QUERY2="SELECT SECURITY FROM USERS WHERE EMAIL=?";
	Connection con=null;
	BuildConnection build=null;
	Users user=null;
	
	public void resetPassword()
	{
		user=new Users();
		build=new BuildConnection();
		con=build.getConnectionDb();
		int count=0;
		//try with resource to create connection
		try(PreparedStatement ps1=con.prepareStatement(QUERY1);
			PreparedStatement ps2=con.prepareStatement(QUERY2);
			Scanner sc=new Scanner(System.in);)
		{
			if(sc!=null)
			{
				if(ps1!=null)
				{
					ps1.setString(1, "klohare7@gmail.com");
					ResultSet rs=ps1.executeQuery(QUERY1);
					while(rs.next())
					{
						System.out.println(rs.getString(1));
					}
				}
//				while(true)
//				{
//					System.out.println("Enter your email : ");
//					String username=sc.next();
//					if(count<=3)
//					{
//						if (!username.contains("@") || !username.endsWith(".com")) 
//						{
//							count++;
//							System.out.println("Re-correct please !");
//						}
//						else
//						{
//							ps1.setString(1,username);
//							System.out.println(ps1);
//							try(ResultSet rs=ps1.executeQuery(QUERY1);)
//							{
//								String security=rs.getString(1);
//								System.out.println(security);
//							}
//						}
//					}
//					else 
//					{
//						break;
//					}
//				}
			}	
		}
		//Known type mismatch exception
		catch (InputMismatchException e) 
		{
			e.printStackTrace();
			System.out.println("please enter numberic value : ");
		}
		//known Sql exception
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		//unknown exception
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		ForgotPassword fp=new ForgotPassword();
		fp.resetPassword();

	}

}
