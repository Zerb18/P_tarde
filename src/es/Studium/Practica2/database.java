package es.Studium.Practica2;

import java.sql.*;

public class database {
	Connection con;
	PreparedStatement pst;
	ResultSet rs;

	database() {
		try {

		
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/tiendecita?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "1234");
			pst = con.prepareStatement("select * from usuarios where usuario=? and contraseña=?");

		} catch (Exception e) {
			System.out.println(e);
		}
	}


	public Boolean checkLogin(String uname, String pwd) {
		try {

			pst.setString(1, uname); 
			pst.setString(2, pwd); 
			
			rs = pst.executeQuery();
			if (rs.next()) {
			
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			
			System.out.println("error while validating" + e);
			return false;
		}
	}
}