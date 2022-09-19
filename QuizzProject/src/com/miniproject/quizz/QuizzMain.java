package com.miniproject.quizz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class QuizzMain extends AbstractQuizz {
	int id;
	String num;

	public void checkUser() throws IOException {
		int choice = 0;
		InputStreamReader rd = new InputStreamReader(System.in);
		BufferedReader sc = new BufferedReader(rd);
		
			while(true)
			{
				System.out.print("================================= ENTER YOUR CHOICE =========================================");
				System.out.println("");
				System.out.println("           1.SignIn                  2.SignUp                3.Forgot Password");
				System.out.print("=============================================================================================\n\t\t\t\t\t");
				num=sc.readLine();
				choice=validateInt(num, 1, 3);		//suhas@gmail.com //suhas@123
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
				if(choice==3)
				{
					ForgotPassword fp = new ForgotPassword();
					fp.resetPassword();
					if(id!=0)
					{
						break;
					}
				}
			}
			while(true)
			{
				System.out.print("\n================================= ENTER YOUR CHOICE =========================================\n");
				System.out.print("         1.Question                     2.Result                            3.ResultAll");
				System.out.print("\n=============================================================================================\n\t\t\t\t\t");
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
				@SuppressWarnings("resource")
				Scanner sc1 =new Scanner(System.in);
				String c = sc1.next().toUpperCase();
				char c1=validateChar(c);
				if(c1=='N')
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