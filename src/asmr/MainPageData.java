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
	public static Map<String, Serializable> notiData;
	public static List<Map<String, Serializable>> notiListData = new ArrayList<Map<String, Serializable>>();
	
	static List<Map<String, Serializable>> getNotiList(int rownum){
		query = "SELECT post_no, post_tit, emp_name, wrt_dttm "
				+ "FROM post join emp on noti_wrt_prsn_no = emp_no "
				+ "WHERE post_tp = 'n' "
				+ "AND rownum <= 5 "
				+ "ORDER BY 4";
		
		notiListData.clear();
		try{
			pstm = conn.prepareStatement(query, rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_READ_ONLY);
			rs = pstm.executeQuery();
			
			while(rs.next()){
				notiListDataSet = new HashMap<String, Serializable>();
				
				String[] dttm = rs.getString("wrt_dttm").split(":");
				String noSec = dttm[0]+":"+dttm[1];
				
				notiListDataSet.put("번호", rs.getString(1));
				notiListDataSet.put("제목", rs.getString(2));
				notiListDataSet.put("작성자", rs.getString(3));
				notiListDataSet.put("작성일시", noSec);
				
				notiListData.add(notiListDataSet);
			}
		}catch(SQLException e){
			System.out.println("SELECT문 예외 발생");
			e.printStackTrace();
		}
		return notiListData;	
	}
	static Map<String, Serializable> getNotiData(String postNo){
		query = "SELECT POST_TIT, POST_CONT, EMP_NAME, WRT_DTTM "
				+ "FROM POST JOIN EMP ON POST.NOTI_WRT_PRSN_NO = EMP.EMP_NO "
				+ "WHERE POST_NO='"+postNo+"'";
		try{
			pstm = conn.prepareStatement(query, rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_READ_ONLY);
			rs = pstm.executeQuery();
			
			while(rs.next()){
				
				notiData = new HashMap<String, Serializable>();
				String[] dttm = rs.getString("wrt_dttm").split(":");
				String noSec = dttm[0]+":"+dttm[1];
				
				notiData.put("제목", rs.getString(1));
				notiData.put("내용", rs.getString(2));
				notiData.put("작성자", rs.getString(3));
				notiData.put("작성일시", noSec);
			}
		}catch(SQLException e){
			System.out.println("SELECT문 예외 발생");
			e.printStackTrace();
		}
		
		return notiData;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getNotiData("83"));
	}

}
