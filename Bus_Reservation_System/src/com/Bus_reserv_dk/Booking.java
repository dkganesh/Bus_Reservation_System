package com.Bus_reserv_dk;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.Bus_reserv_DB.Booking_DB;

public class Booking extends Booking_DB {
	Bus bus = new Bus();
	Passenger passenger = new Passenger();
	Booking_DB bdb = new Booking_DB();
	private int bno;
	private String source, dest;
	private java.util.Date date;
	SimpleDateFormat dformat;
	Scanner in = new Scanner(System.in);

	public void run() throws ClassNotFoundException, SQLException {
		try {
			getDetails();
			showDetails();
			checkAvailability();

		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	public void getDetails() throws ParseException {
		System.out.println("Enter Source place\t");
		source = in.next().toUpperCase();
		System.out.println("Enter Destination Place\t");
		dest = in.next().toUpperCase();
		System.out.println("Enter Date of Journey in format yyyy-MM-dd");
		String dinput = in.next();
		dformat = new SimpleDateFormat("yyyy-MM-dd");
		date = dformat.parse(dinput);

	}

	public void showDetails() {
		List<List<String>> blists = null;

		try {
			blists = bus.get_buslist_details_normal();
		} catch (ClassNotFoundException e) {
			System.out.println("SQL Driver error");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("SQL DB error");
			e.printStackTrace();
		}

		for (List<String> blist : blists) {
			String temp[] = blist.toArray(new String[0]);
			System.out.println(Arrays.toString(temp));
		}

	}

	public void checkAvailability() throws ClassNotFoundException, SQLException {
		System.out.println("Enter Bus No. to book the Bus...");
		bno = in.nextInt();
		if (check_DB_for_busno(bno)) {
			pre_booking();
		} else {
			System.out.println("Enter a Valid Bus no. !!!");
			checkAvailability();
		}
	}

	private boolean check_DB_for_busno(int n) throws ClassNotFoundException, SQLException {
		return bus.check_bus_no(n);
	}

	private void pre_booking() throws ClassNotFoundException, SQLException {
		System.out.println(
				"Available seats in Selected Bus : " + (bus.get_bus_cap_Bus(bno) - Bus_cap(dformat.format(date), bno)));
		System.out.println("Enter 1 for Existing User\nEnter 2 for Register\n");
		int choice = in.nextInt();
		if (choice == 1) {
			System.out.println("Enter UserName or Mail");
			String uname = in.next();
			System.out.println("Enter Password");
			String pass = in.next();
			if (passenger.p_check_DB_p(uname, pass)) {
				booking_DB_Setdata(uname, bno, dformat.format(date), source, dest);
				System.out.println("Booking Added : " + addto_bookinglist());
			} else {
				System.out.println("Wrong credentials");
				pre_booking();
			}
		} else if (choice == 2) {

			System.out.println("Passenger Registration ");
			passenger.create_user_account();
			pre_booking();

		} else {
			System.out.println("Enter correct Choice no.");
			pre_booking();
		}
	}

	private int Bus_cap(String date, int busno) throws ClassNotFoundException, SQLException {

		return get_Bus_Capacity_bookingDB(date, busno);

	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
		in.close();
	}
}
