package asmr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InqAnsData {
	
	public static Connection conn = ConnDB.getConnection();
	static String query;
	static PreparedStatement pstm = null;
	static ResultSet rs = null;
	
    static String getPostNo(String a, String b) {
    	String pno = "";
    	query = "select post_no from post where post_tit='"+a+"' "
    			+"and wrt_dttm = to_date('"+b+"','YYYY-MM-DD hh24:mi:ss') ";
    	try{
			pstm = conn.prepareStatement(query, rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_READ_ONLY);
			rs = pstm.executeQuery();
			
			while(rs.next()){
				pno = rs.getString(1);
			}
		}catch(SQLException e){
			System.out.println("sql 오류");
			e.printStackTrace();
		}
		return pno;
    	
    }
    
    static String getPostTp(String no) {
    	String tp = "";
    	query = "select post_tp from post where post_no = '"+no+"' ";
    	try{
			pstm = conn.prepareStatement(query, rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_READ_ONLY);
			rs = pstm.executeQuery();
			
			while(rs.next()){
				tp = rs.getString(1);
			}
		}catch(SQLException e){
			System.out.println("sql 오류");
			e.printStackTrace();
		}
		return tp;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		System.out.println(getPostNo("나의소원", ""));

	}

}
