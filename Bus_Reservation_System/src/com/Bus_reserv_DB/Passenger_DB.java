package com.Bus_reserv_DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Passenger_DB {
	private String uname;
	private String mail;
	private String phone;
	private String pass;

	public void set_data_to_passlist(String uname, String mail, String phone, String pass)
			throws ClassNotFoundException, SQLException {
		this.uname = uname.toUpperCase();
		this.mail = mail;
		this.phone = phone;
		this.pass = pass;

		/* database connection */

		String url = "jdbc:mysql://localhost:3306/buses";
		String query = "insert into passlist values(?,?,?,?)";

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, "root", "6257");
		PreparedStatement stat = con.prepareStatement(query);
		stat.setString(1, this.mail);
		stat.setString(2, this.uname);
		stat.setString(3, this.phone);
		stat.setString(4, this.pass);
		stat.executeUpdate();
	}

	public List<List<String>> get_passlist_details() throws ClassNotFoundException, SQLException {
		String url = "jdbc:mysql://localhost:3306/buses";
		String query = "select * from passlist";

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, "root", "6257");
		PreparedStatement stat = con.prepareStatement(query);
		ResultSet rs = stat.executeQuery();
		List<List<String>> fin = new ArrayList<>();
		while (rs.next()) {
			List<String> ans = new ArrayList<>();
			ans.add(rs.getString(1));
			ans.add(rs.getString(2));
			ans.add(rs.getString(3));
			ans.add(rs.getString(4));
			fin.add(ans);
		}
		return fin;
	}

	public void create_user_account() throws ClassNotFoundException, SQLException {

		String uname, mail, phone, pass;
		Scanner in = new Scanner(System.in);
		System.out.println("Enter User name : ");
		uname = in.next().toUpperCase();
		System.out.println("Enter User mail : ");
		mail = in.next();
		System.out.println("Enter User Phone: ");
		phone = in.next();
		System.out.println("Enter Password  : ");
		pass = in.next();

		set_data_to_passlist(uname, mail, phone, pass);
		System.out.println("User Account Added Sucessfully...");
	}

}
