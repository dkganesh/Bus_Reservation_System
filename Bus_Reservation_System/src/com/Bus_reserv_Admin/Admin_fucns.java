package com.Bus_reserv_Admin;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import com.Bus_reserv_DB.Bus_DB;
import com.Bus_reserv_DB.Passenger_DB;

public class Admin_fucns {

	Passenger_DB pdb;
	Bus_DB bdb;

	public void addPassenger(String uname, String mail, String phone, String pass)
			throws ClassNotFoundException, SQLException {
		pdb = new Passenger_DB();
		pdb.set_data_to_passlist(uname, mail, phone, pass);
	}

	public void addBus(int bno, int bcap, boolean ac, String source, String dest)
			throws ClassNotFoundException, SQLException {
		bdb = new Bus_DB();
		bdb.Set_data_to_buslist(bno, bcap, ac, source, dest);
	}

	public void getPassenger() throws ClassNotFoundException, SQLException {
		pdb = new Passenger_DB();
		List<List<String>> lists = pdb.get_passlist_details();
		for (List<String> list : lists) {
			String temp[] = list.toArray(new String[0]);
			System.out.println(Arrays.toString(temp));
		}
	}

	public void getBus() throws ClassNotFoundException, SQLException {
		bdb = new Bus_DB();
		List<List<String>> lists = bdb.get_buslist_details();
		for (List<String> list : lists) {
			String temp[] = list.toArray(new String[0]);
			System.out.println(Arrays.toString(temp));
		}
	}
}
