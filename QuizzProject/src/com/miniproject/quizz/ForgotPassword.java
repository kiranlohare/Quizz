package com.miniproject.quizz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ForgotPassword extends Users
{
	//update users set password = 'kiran@12' where email='klohare7@gmail.com';
	//select security from users where email='surajkuradecode@gmail.com';
	public static final String QUERY1="SELECT SECURITY FROM USERS WHERE EMAIL=?";
	public static final String QUERY2="UPDATE USERS SET PASSWORD=? WHERE EMAIL=?";
	Connection con=null;
	BuildConnection build=null;
	Users user=null;
	
	public void resetPassword()
	{
		user=new Users();
		build=new BuildConnection();
		con=build.getConnectionDb();
		int count=1;
		int flag=0;
		
		String security=null;
		//try with resource to create connection
		try(PreparedStatement ps1=con.prepareStatement(QUERY1);
			PreparedStatement ps2=con.prepareStatement(QUERY2);)
		{
			@SuppressWarnings("resource")
			Scanner sc=new Scanner(System.in);
			//connection check
			if(con!=null)
			{
				//scanner check
				if(sc!=null)
				{
					//prepared statement check
					if(ps1!=null)
					{
						//infinite loop to take multiple inputs
						while(true)
						{
							System.out.print("Enter your email    : ");
							String temp=sc.next();
							String valid="^[a-zA-Z0-9_.-]+@[a-zA-Z.]+.[a-zA-Z]+$";
						
							if(temp.matches(valid))
							{
								user.setUsername(temp);
								ps1.setString(1,user.getUsername());
								ResultSet rs=ps1.executeQuery();				//email id verification while
								if(rs!=null)
								{
									while(rs.next())
									{
										security=rs.getString(1);
										flag=1;
									}
								}
								break;
							}
							else
							{
								System.out.println("Please re-correct email id");
							}
							
						}
						count=1;
						
						if(flag==1)
						{
							while(true)
							{
								System.out.print("What is you pet name ? : ");
								String petName=sc.next();
								if(count<=3)
								{
									if (petName.contains("null")) 
									{
										count++;
										System.out.println("Re-correct pet name please !");
									}
									else
									{
										if(petName.equals(security))
										{
											while(true)
											{
												System.out.print("Enter new password : ");
												String newPassword=sc.next();
												System.out.print("Confirm new password : ");
												String confirmPassword=sc.next();
												if(newPassword.equals(confirmPassword))				//password update while
												{
													ps2.setString(1, confirmPassword);
													ps2.setString(2, user.getUsername());
													ps2.executeUpdate();
													System.out.println("Password updates successfully");
													break;
													
												}
												else
												{
													System.out.println("new password and confirm password does not match");
												}
											}
											break;
										}
										{
											count++;
											System.out.println("Re-correct pet name please !");
										}
									}
								}
								else 
								{
									break;
								}
							}
						}
						else
						{
							System.out.println("No records found with the given email id");
						}
					}
				}
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
}
