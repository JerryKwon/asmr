package asmr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnDB {
	
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	
	private static final String USER = "asmr";
	private static final String PASSWORD = "asmr";
	private static Connection conn = null;
	static PreparedStatement pstm = null;
	static ResultSet rs = null;
	
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			//System.out.println("¼º°ø");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	public static Connection getConnection(){
		return conn;
	}
	public static void disConnection(){
		try {
            if(pstm != null) pstm.close();

            if(rs != null) rs.close();

            if(conn != null) conn.close();
   } catch (SQLException e) {
   	e.printStackTrace();
   }
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
