package com.miniproject.quizz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SignIn 
{
	public static final String QUERY1="SELECT UID,PASSWORD FROM USERS WHERE EMAIL=?";
	
	Connection con=null;
	BuildConnection build=null;
	Users user=null;
	int count=0;
	public void signIn()
	{
		build=new BuildConnection();
		user=new Users();
		con=build.getConnectionDb();
		int flag=0;
		try(PreparedStatement ps=con.prepareStatement(QUERY1);
				Scanner sc=new Scanner(System.in);)
		{
			//connection check
			if(con!=null)
			{
				//scanner check
				if(sc!=null)
				{
					//prepared statement check
					if(ps!=null)
					{
						//infinite loop to take multiple inputs
						while(true)
						{
							//input 
							System.out.println("Enter your email : ");
							user.setUsername(sc.next());
							if(count<2)
							{
								if (!user.getUsername().contains("@") || !user.getUsername().endsWith(".com")) 
								{
									count++;
									System.out.println("Re-correct please !");
								}
								else
								{
									ps.setString(1,user.getUsername());
									ResultSet rs=ps.executeQuery();
									if(rs!=null)
									{
										int local=1;
										while(rs.next())
										{
											flag=1;
											while(true)
											{
												System.out.println("Enter your password :");
												user.setPassword(sc.next());
												if(local<3)
												{
													if(user.getPassword().equals(rs.getString(2)))
													{
														System.out.println("Logged In successfully...Enjoy the game...!");
														System.out.println("Your User ID is: "+rs.getInt(1));
														System.out.println("Shall we start the game  :  Y?N");
														String str=sc.next();
														char ch=str.toLowerCase().charAt(0);
														if(ch=='y')
														{
															Questions q=new Questions(rs.getInt(1));
															q.getQuestions();
														}
														
													}
													else
													{
														local++;
														System.out.println("Password is incorrect try again");
													}
												}
												else
												{
													System.out.println("Oops! You have excceded maximum attempt!!\n Try after some time");
													break;
												}
											}
										}
										if(flag==0)
										{
											try
											{
											System.out.println("Email is not registered with us! Would you like to register?");
											System.out.println("1. Yes! Register.   2.No");
											int choice=sc.nextInt();
											if(choice==1)
											{
												SignUp up=new SignUp();
												up.signUp();
											}
											if(choice==2)
											{
												System.exit(0);
											}
											}
											catch (InputMismatchException e) 
											{
												System.out.println("Enter in digits");
											}
										}
									}
									break;
								}
							}
							else 
							{
								break;
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
	}
}
