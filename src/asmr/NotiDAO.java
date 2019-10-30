package asmr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class NotiDAO {
	
	  private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	  private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	  private static final String USER = "da";
	  private static final String PASS = "da";
	  
	  public Connection getConn() {
		    Connection con = null;
		    try {
		      Class.forName(DRIVER);
		      con = DriverManager.getConnection(URL, USER, PASS);
		    } catch (Exception e) {
		      e.printStackTrace();
		    } // try - catch
		    return con;
		  } // getConn : 연결 메소드 작성

	  public Vector<String> getScore() {
		    Vector<String> data = new Vector<String>();
		    Connection con = null;
		    Statement stmt = null;
		    ResultSet rs = null;
		    try {
		      con = getConn();
		      String sql = "select * from post order by post_tit asc";
		      stmt = con.createStatement();
		      rs = stmt.executeQuery(sql);
		 
		      while (rs.next()) {
		        String name = rs.getString("post_no");
		        String kor = rs.getString("post_tit");
		        String eng = rs.getString("wrt_dttm");
		        String mat = rs.getString("noti_wrt_prsn_no");
		        Vector<String> row = new Vector<String>();
		        row.add(name);
		        row.add(kor);
		        row.add(eng);
		        row.add(mat);

		        data.addAll(row);
		      } // while
		    } catch (Exception e) {
		      e.printStackTrace();
		    } finally {
		      if (rs != null) { try { rs.close(); } catch (SQLException e2) { e2.printStackTrace(); } // rs try - catch
		      } // rs if
		      if (stmt != null) { try { stmt.close(); } catch (SQLException e2) { e2.printStackTrace(); } // ps try - catch
		      } // stmt if
		      if (con != null) { try { con.close(); } catch (SQLException e2) { e2.printStackTrace(); } // con try - catch
		      } // con if
		    } // try - catch - finally
		    return data;
		  } // getScore : 리스트를 가져오는 메서드, 조회해온 데이터를  Vector형 data를 반환한다.


		
//	  
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}

}
