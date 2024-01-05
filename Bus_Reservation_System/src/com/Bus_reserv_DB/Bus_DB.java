package com.Bus_reserv_DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Bus_DB {
	private String busNo;
	private String busCap;
	private String ac;

	public void Set_data_to_buslist(int bno, int bcap, boolean ac, String source, String dest)
			throws SQLException, ClassNotFoundException {
		busNo = Integer.toString(bno);
		busCap = Integer.toString(bcap);
		this.ac = Integer.toString((ac) ? 1 : 0);

		/* database connection */

		String url = "jdbc:mysql://localhost:3306/buses";
		String query = "insert into buslist values(?,?,?,?,?)";

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, "root", "6257");
		PreparedStatement stat = con.prepareStatement(query);
		stat.setString(1, busNo);
		stat.setString(2, busCap);
		stat.setString(3, this.ac);
		stat.setString(4, source);
		stat.setString(5, dest);
		stat.executeUpdate();
	}

	public List<List<String>> get_buslist_details() throws SQLException, ClassNotFoundException {
		String url = "jdbc:mysql://localhost:3306/buses";
		String query = "select * from buslist";

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, "root", "6257");
		PreparedStatement stat = con.prepareStatement(query);
		ResultSet rs = stat.executeQuery();
		List<List<String>> ans = new ArrayList<>();
		while (rs.next()) {
			List<String> temp = new ArrayList<String>();
			temp.add("Bus No      : " + rs.getString(1));
			temp.add("Bus Capcity : " + rs.getString(2));
			temp.add("Ac / Non-Ac : " + rs.getString(3));
			temp.add("Source      : " + rs.getString(4));
			temp.add("Destination : " + rs.getString(5));

			ans.add(temp);
		}
		return ans;
	}

	public List<List<String>> get_buslist_details_normal() throws SQLException, ClassNotFoundException {
		String url = "jdbc:mysql://localhost:3306/buses";
		String query = "select * from buslist";

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, "root", "6257");
		PreparedStatement stat = con.prepareStatement(query);
		ResultSet rs = stat.executeQuery();
		List<List<String>> ans = new ArrayList<>();
		while (rs.next()) {
			List<String> temp = new ArrayList<String>();
			temp.add("Bus No      : " + rs.getString(1));
			temp.add("Ac / Non-Ac : " + rs.getString(3));
			temp.add("Source      : " + rs.getString(4));
			temp.add("Destination : " + rs.getString(5));

			ans.add(temp);
		}
		return ans;
	}

	public boolean check_bus_no(int n) throws ClassNotFoundException, SQLException {
		String url = "jdbc:mysql://localhost:3306/buses";
		String query = "select * from buslist where busno=" + Integer.toString(n);

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, "root", "6257");
		PreparedStatement stat = con.prepareStatement(query);
		ResultSet rs = stat.executeQuery();
		return rs.next();
	}

	public int get_bus_cap_Bus(int n) throws SQLException, ClassNotFoundException {
		String url = "jdbc:mysql://localhost:3306/buses";
		String query = "select buscap from buslist where busno=" + Integer.toString(n);
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, "root", "6257");
		PreparedStatement stat = con.prepareStatement(query);
		ResultSet rs = stat.executeQuery();
		rs.next();
		return Integer.valueOf(rs.getString(1));

	}
}
