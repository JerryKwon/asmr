package asmr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnDB {
	
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	
	private static final String USER = "asmr";
	private static final String PASSWORD = "asmr";
	private static Connection conn = null;
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("¼º°ø");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	public static Connection getConnection(){
		return conn;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
