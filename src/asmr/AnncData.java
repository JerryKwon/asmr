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

public class AnncData {
	public static Connection conn = ConnDB.getConnection();
	static String query;
	static PreparedStatement pstm = null;
	static ResultSet rs = null;
	
	public static List<Map<String, Serializable>> AnncList = new ArrayList<Map<String, Serializable>>();
	public static List<Map<String, Serializable>> abanList = new ArrayList<Map<String, Serializable>>();
	public static Map<String, Serializable> Annc;
	public static Map<String, Serializable> abanNo;
 	
	static Map<String, Serializable> getAnnc(String abanNo){
		query = "SELECT  a.regis_date, a.anml_kinds, a.kind, a.sex, rp.dscv_loc, feat "
				+ "FROM ABAN A, PROT P, RSCU R, RPRT RP "
				+ "WHERE A.ABAN_NO = P.ABAN_NO "
				+ "AND R.RSCU_NO = A.RSCU_NO "
				+ "AND rp.rprt_no = r.rscu_no "
				+ "AND a.aban_no = '"+abanNo+"'";
				
		try{
			pstm = conn.prepareStatement(query, rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_READ_ONLY);
			rs = pstm.executeQuery();
			
			while(rs.next()){
				Annc = new HashMap<String, Serializable>();
				Annc.put("�������", rs.getString(1));
				Annc.put("��������", rs.getString(2));
				Annc.put("ǰ��", rs.getString(3));
				Annc.put("����", rs.getString(4));
				Annc.put("�߰����", rs.getString(5));
				Annc.put("Ư¡", rs.getString(6));
		}
			}
		catch(SQLException e){
			System.out.println("SELECT�� ���� �߻�");
			e.printStackTrace();
		}
		return Annc;
		
	}
	static List<Map<String, Serializable>> getAbanNoList() {
		query = "SELECT ABAN_NO "
				+ "FROM PROT ";
		
		try{
			pstm = conn.prepareStatement(query, rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_READ_ONLY);
			rs = pstm.executeQuery();
			
			while(rs.next()){
				abanNo = new HashMap<String, Serializable>();
				abanNo.put("��ȣ", rs.getString("ABAN_NO"));
				System.out.println(abanNo);
				abanList.add(abanNo);
				
		}
			}
		catch(SQLException e){
			System.out.println("SELECT�� ���� �߻�");
			e.printStackTrace();
		}
		return abanList;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getAbanNoList());
	}

}
