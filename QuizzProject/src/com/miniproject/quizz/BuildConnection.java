package com.miniproject.quizz;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class BuildConnection 
{
	//global object initialization
	Properties p=null;
	Connection con=null;
	
	//method to build connection
	public Connection getConnectionDb()
	{
		//try with resource to handle known exception
		try(FileInputStream stream=new FileInputStream("D:/Quizz/QuizzProject/bin/com/miniproject/quizz/db.properties");)
		{
			
			Properties p=new Properties();
			p.load(stream);
			con=DriverManager.getConnection(p.getProperty("url"), p.getProperty("username"), p.getProperty("password"));

		} 
		//to handle IO exception known exception
		catch (IOException e) 
		{
			e.printStackTrace();
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
		return con;
	}
}
