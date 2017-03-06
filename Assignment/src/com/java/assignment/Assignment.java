package com.java.assignment;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
public class Assignment {

	// Define all the private variable
	static Scanner s = new Scanner(System.in);
	static Connection con = null;
	static ArrayList<String> al1 = new ArrayList<String>();
	static ArrayList<String> al2 = new ArrayList<String>();
public static void main(String args[])
{
	Connection current_con  = getconnect();
	System.out.println("Do you want to Register or Login");
	System.out.println("Press 1 for Registration");
	System.out.println("Press 2 for Login");
	int x=s.nextInt();
	if(x==1)
	{
		registrationValid( current_con);
	}
	else if(x==2)
	
		loginValid();
	else
	System.out.println("Thank you for visiting");
}
	// Creating the connection with DataBase and storing the value in ArrayList
	public static Connection getconnect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/student","root","root");
			Statement stmt1 = con.createStatement();
			ResultSet rs = stmt1.executeQuery("Select * from userdata");
			while (rs.next()) {
				al1.add(rs.getString("Name"));
				al2.add(rs.getString("passwd"));
			}
			return con;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return con;

	}

	// Validating the existing user
	public static void loginValid() {
		System.out.println("Enter username and password");
		int count=0;
		String vname=s.next();
		String vpass=s.next();
		for (int i = 0; i < al1.size(); i++) {
			if (al1.get(i).equalsIgnoreCase(vname) && al2.get(i).equals(vpass))
			{
				System.out.println("Valid User");
				count++;
				break;
			}}
		if (count==0)
			System.out.println("Invalid User");
		}
		

	// Validating the New User Data.If Correct then Store them in DataBase.
	public static void registrationValid(Connection con) {
		System.out.println("Enter username and password for registration");
		String name = s.next();
		String password = s.next();
		
		int count = 0;
		if (count == 0) {
			try {
				//System.out.println(name + password);
				PreparedStatement pstmt = con.prepareStatement("insert into UserData values(?,?)");
				pstmt.setString(1, name);
				pstmt.setString(2, password);
				pstmt.executeUpdate();
				System.out.println("Registration Successful");
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
				
	
			}
}
	}}
