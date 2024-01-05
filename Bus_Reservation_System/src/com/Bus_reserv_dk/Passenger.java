package com.Bus_reserv_dk;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Bus_reserv_DB.Passenger_DB;

public class Passenger extends Passenger_DB {
	public Passenger() {
	}

	public boolean p_check_DB_p(String mail, String pass) throws ClassNotFoundException, SQLException {
		String url = "jdbc:mysql://localhost:3306/buses";
		String query = "select * from passlist where mail=\"" + mail + "\"" + "and pass=\"" + pass + "\"";

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, "root", "6257");
		PreparedStatement stat = con.prepareStatement(query);
		ResultSet rs = stat.executeQuery();

		return rs.next();

	}

}
