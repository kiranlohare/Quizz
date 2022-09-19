package com.miniproject.quizz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Project2{
	public void signIn() {
	
	 
    try{
  	  @SuppressWarnings("resource")
	Scanner scanner = new Scanner(System.in);
  	    int a = scanner.nextInt();
  	  String query = "select * from students where class=?";
  	  Class.forName("com.mysql.cj.jdbc.Driver"); 
  	  Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz", "root", "suhas@3259");
        PreparedStatement stmt = con.prepareStatement(query);      		      
      stmt.setInt(1, a);
       ResultSet rs = stmt.executeQuery();
       while(rs.next()){
          System.out.println("UID: " + rs.getInt(1));
          System.out.println("Name: " + rs.getString(2));
          System.out.println("email: " + rs.getInt(3));
   ;
          }

       
  
       
       rs.close();
    } catch (Exception e) {
       e.printStackTrace();
    } 
 }}





