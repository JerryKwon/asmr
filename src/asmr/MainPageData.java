package asmr;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainPageData {
	public static Connection conn = ConnDB.getConnection();
	static String query;
	static PreparedStatement pstm = null;
	static ResultSet rs = null;
	
	public static Map<String, Serializable> notiListDataSet;
	public static List<Map<String, Serializable>> notiListData = new ArrayList<Map<String, Serializable>>();
	
	static List<Map<String, Serializable>> getNotiList(int rownum){
		query = "SELECT post_tit, emp_name, wrt_dttm "
				+ "FROM post join emp on noti_wrt_prsn_no = emp_no "
				+ "WHERE post_tp = 'n' "
				+ "AND rownum <= 5 "
				+ "ORDER BY 3";
		
		notiListData.clear();
		try{
			pstm = conn.prepareStatement(query, rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_READ_ONLY);
			rs = pstm.executeQuery();
			
			while(rs.next()){
				notiListDataSet = new HashMap<String, Serializable>();
				
				String[] dttm = rs.getString("wrt_dttm").split(":");
				String noSec = dttm[0]+":"+dttm[1];
				
				notiListDataSet.put("제목", rs.getString(1));
//				notiListDataSet.put("작성자", rs.getString(2));
				notiListDataSet.put("작성일시", noSec);
				
				notiListData.add(notiListDataSet);
			}
		}catch(SQLException e){
			System.out.println("SELECT문 예외 발생");
			e.printStackTrace();
		}
		return notiListData;	
	}	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getNotiList(5));;
	}

}
