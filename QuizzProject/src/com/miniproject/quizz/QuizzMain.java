package com.miniproject.quizz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class QuizzMain extends AbstractQuizz {
	int id;
	String num;

	public void checkUser() throws IOException {
		int choice = 0;
		InputStreamReader rd = new InputStreamReader(System.in);
		BufferedReader sc = new BufferedReader(rd);
		
			while(true)
			{
				System.out.println("Enter Your choice");
				System.out.println("1.SignIn                                                        2.SignUp");
				num=sc.readLine();
				choice=validateInt(num, 1, 2);		//suhas@gmail.com //suhas@123
				if(choice==1)
				{
					SignIn in = new SignIn();
					id = in.signIn();
					if(id!=0)
					{
						break;
					}
				}
				if(choice==2)
				{
					SignUp up=new SignUp();
					id=up.signUp();
					if(id!=0)
					{
						break;
					}
				}
			}
			while(true)
			{
				System.out.println("Enter your choice");
				System.out.println("1.Question                      2.Result                           3.ResultAll");
				num = sc.readLine();
				choice = validateInt(num, 1, 3);
				if (choice == 1) 
				{
					System.out.println();
		
					Questions q = new Questions(id);
					q.getQuestions();
				}
				if (choice == 2) {
					Result result = new Result();
					result.diplayResult(id);
				}
				if (choice == 3) {
					ResultAll result = new ResultAll();
					result.diplayAllResult();
				}
				System.out.println("Would you like to continue Y:N?");
				String c = sc.readLine().toUpperCase();
				if(c.charAt(0)=='N')
				{
					break;
				}
			}
	}

	public static void main(String[] args) {
		QuizzMain main = new QuizzMain();
		try {
			main.checkUser();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}