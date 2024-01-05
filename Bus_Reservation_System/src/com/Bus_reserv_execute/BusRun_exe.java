package com.Bus_reserv_execute;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.Bus_reserv_Admin.Admin_fucns;
import com.Bus_reserv_dk.Booking;

public class BusRun_exe {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		System.out.println("Welcome to XYZ Bus services");
		System.out.println("Enter Choice\n1.Normal User\n2.Admin Login\n");
		Scanner in = new Scanner(System.in);
		int acc_choice = in.nextInt();
		switch (acc_choice) {
		case 1: {
			Booking book = new Booking();
			book.run();
			break;
		}
		case 2: {
			if (isAdmin()) {

				Admin_fucns adfs = new Admin_fucns();
				int admin_choice;

				do {
					System.out.println("Welcome to Admin Login...");
					System.out.println("Enter choice for Operation");
					System.out.println("1.Add Passenger");
					System.out.println("2.Add Bus");
					System.out.println("3.Get Passenger");
					System.out.println("4.Get Bus");
					System.out.println("5.Exit");
					admin_choice = in.nextInt();
					switch (admin_choice) {
					case 1: {
						String uname;
						String mail;
						String pass;
						String phone;
						System.out.println("Enter details \n");
						System.out.println("User Name : \t");
						uname = in.next();
						System.out.println("Mail : \t");
						mail = in.next();
						System.out.println("Phone : \t");
						phone = in.next();
						System.out.println("Password : \t");
						pass = in.next();

						adfs.addPassenger(uname, mail, phone, pass);
						break;
					}
					case 2: {
						int bno, bcap;
						boolean ac;
						String source, dest;
						System.out.println("Enter details \n");
						System.out.println("Bus Number : \t");
						bno = in.nextInt();
						System.out.println("Bus Capacity : \t");
						bcap = in.nextInt();
						System.out.println("AC Bus? : \t");
						ac = in.nextBoolean();
						System.out.println("Source Place : \t");
						source = in.next();
						System.out.println("Destination Place : \t");
						dest = in.next();

						adfs.addBus(bno, bcap, ac, source, dest);
						break;
					}
					case 3: {
						adfs.getPassenger();
						break;
					}
					case 4: {
						adfs.getBus();
						break;
					}
					case 5: {
						System.out.println("Closed Admin Login...");
						return;
					}
					default:
						throw new IllegalArgumentException("Unexpected value: " + admin_choice);
					}
				} while (true);

			} else {
				System.out.println("Sorry you have entered wrong password !!!");
			}

		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + acc_choice);
		}

	}

	private static boolean isAdmin() throws ClassNotFoundException, SQLException {

		Scanner in = new Scanner(System.in);
		System.out.println("Enter the ID or mail : ");
		String uname = in.next();
		System.out.println("Enter the Password : ");
		String pass = in.next();

		String url = "jdbc:mysql://localhost:3306/buses";
		String query = "select pass from passlist where mail=\"dk\"";

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, "root", "6257");
		PreparedStatement stat = con.prepareStatement(query);
		ResultSet rs = stat.executeQuery();

		if (rs.next()) {
			if (rs.getString(1).equals(pass))
				return true;
		}
		return false;

	}

}
