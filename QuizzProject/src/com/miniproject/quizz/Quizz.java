package com.miniproject.quizz;

public interface Quizz 
{
	public boolean signUp();
	
	
	public void sigIn();
	
	
	public void getQuestions();
	
	public boolean submitResult();
	
	
	public void displayResult();
	
	
	public void displayAllResults();
	
	
	default int validateInt(String a,int start, int end) {
		int b = 0;
		try 
		{
			b = Integer.parseInt(a);
			if (b < start || b > end)
				return -1;
		} catch (Exception e) {
			return -1;
		}
		return b;
	}
}
