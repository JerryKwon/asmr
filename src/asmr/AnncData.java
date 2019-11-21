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
//		query = "SELECT  a.regis_date, a.anml_kinds, a.kind, a.sex, rp.dscv_loc, feat, ap.path "
//				+ "FROM ABAN A, PROT P, RSCU R, RPRT RP, ABAN_PIC AP "
//				+ "WHERE A.ABAN_NO = P.ABAN_NO "
//				+ "AND R.RSCU_NO = A.RSCU_NO "
//				+ "AND rp.rprt_no = r.rscu_no "
//				+ "AND ap.anml_pic_ornu = 1"
//				+ "AND ap.aban_no = '"+abanNo+"'";

		query = "SELECT  a.regis_date, a.anml_kinds, a.kind, a.sex, rp.dscv_loc, feat, ap.path "
				+ "FROM ABAN A INNER JOIN PROT P ON A.ABAN_NO = P.ABAN_NO AND A.ABAN_NO='"+abanNo+"'  "
				+ "INNER JOIN RSCU R ON R.RSCU_NO = A.RSCU_NO "
				+ "INNER JOIN ASSG A2 ON R.RSCU_NO=A2.ASSG_NO "
				+ "INNER JOIN RPRT RP ON RP.RPRT_NO=A2.RPRT_NO "
				+ "LEFT OUTER JOIN ABAN_PIC AP ON A.ABAN_NO=AP.ABAN_NO AND AP.anml_pic_ornu = 1";
		
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
				Annc.put("���", rs.getString(7));
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
		System.out.println(getAnnc("2019112201"));
	}

}
