package com.miniproject.quizz;

public class Users 
{
	//class with users 
	private int uid;
	private String name;
	private String username;
	private String password;
	private String securitycheck;
	
	//getter setters
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
	public String getSecuritycheck() {
		return securitycheck;
	}
	public void setSecuritycheck(String securitycheck) 
	{
		this.securitycheck = securitycheck;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
}
