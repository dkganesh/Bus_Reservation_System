package com.Bus_reserv_DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Booking_DB {

	private String passid, date, busno, source, dest, pname;
	private int ticcount;
	private String url = "jdbc:mysql://localhost:3306/buses";
	Scanner in = new Scanner(System.in);

	protected void booking_DB_Setdata(String passid, int busno, String date, String source, String dest) {

		this.passid = passid;
		this.busno = Integer.toString(busno);
		this.date = date;
		this.source = source;
		this.dest = dest;
		System.out.println("Enter Passenger Name : ");
		pname = in.next().toUpperCase();
		System.out.println("Enter No of Tickets  : ");
		ticcount = in.nextInt();
	}

	protected boolean addto_bookinglist() throws ClassNotFoundException, SQLException {

		if (seat_available()) {
			String query = "insert into bookinglist values(?,?,?,?,?,?,?)";

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "root", "6257");
			PreparedStatement stat = con.prepareStatement(query);
			stat.setString(1, pname);
			stat.setString(2, source);
			stat.setString(3, dest);
			stat.setString(4, passid);
			stat.setString(5, busno);
			stat.setString(6, date);
			stat.setString(7, Integer.toString(ticcount));
			int x = stat.executeUpdate();
			return (x > 0) ? true : false;
		} else {
			System.out.println("Enter Ticket count for max. available seats in the bus.");
			return false;
		}
	}

	private boolean seat_available() throws ClassNotFoundException, SQLException {
		String query = "select buscap from buslist where busno=" + busno;

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, "root", "6257");
		PreparedStatement stat = con.prepareStatement(query);
		ResultSet rs = stat.executeQuery();

		rs.next();

		return Integer.valueOf(rs.getString(1)) > (get_Bus_Capacity_bookingDB(date, Integer.valueOf(busno)) + ticcount);

	}

//	private boolean update_fill() throws ClassNotFoundException, SQLException {
//		String query1 = "select fill from buslist where busno=" + busno;
//
//		Class.forName("com.mysql.cj.jdbc.Driver");
//		Connection con = DriverManager.getConnection(url, "root", "6257");
//		PreparedStatement stat1 = con.prepareStatement(query1);
//		ResultSet rs = stat1.executeQuery();
//		rs.next();
//		int fill = Integer.valueOf(rs.getString(1));
//		fill += ticcount;
//		String query2 = "update buslist set fill=" + Integer.toString(fill) + " where busno=" + busno;
//		PreparedStatement stat2 = con.prepareStatement(query2);
//		int x = stat2.executeUpdate();
//		return (x > 0) ? true : false;
//	}

	protected int get_Bus_Capacity_bookingDB(String date, int bno) throws ClassNotFoundException, SQLException {
		String query = "select sum(tickets) from bookinglist where bdate=\"" + date + "\" and busno="
				+ Integer.toString(bno);

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, "root", "6257");
		PreparedStatement stat = con.prepareStatement(query);
		ResultSet rs = stat.executeQuery();
		rs.next();

		return (rs.getString(1) == null) ? 0 : Integer.valueOf(rs.getString(1));

	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
		in.close();
	}
}
