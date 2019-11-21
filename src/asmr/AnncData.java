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
	public static List<Map<String, Serializable>> picPathSet = new ArrayList<Map<String, Serializable>>();
	public static Map<String, Serializable> Annc;
	public static Map<String, Serializable> AnncDtl;
	public static Map<String, Serializable> abanNo;
	public static Map<String, Serializable> picPath;
 	
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
				+ "LEFT JOIN RSCU R ON R.RSCU_NO = A.RSCU_NO "
				+ "LEFT JOIN ASSG A2 ON R.RSCU_NO=A2.ASSG_NO "
				+ "LEFT JOIN RPRT RP ON RP.RPRT_NO=A2.RPRT_NO "
				+ "LEFT JOIN ABAN_PIC AP ON A.ABAN_NO=AP.ABAN_NO AND AP.anml_pic_ornu = 1";
		
		try{
			pstm = conn.prepareStatement(query, rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_READ_ONLY);
			rs = pstm.executeQuery();
			
			while(rs.next()){
				String korAnmlKind = "";
				switch(rs.getString(2)){
				case("d"):
					korAnmlKind = "개";
					break;
				case("c"):
					korAnmlKind = "고양이";
					break;
				case("e"):
					korAnmlKind = "기타";	
					break;
				}
				
				String korSex = "";
				switch(rs.getString(4)){
				case("m"):
					korSex = "수컷";
					break;
				case("f"):
					korSex = "암컷";
					break;
				case("e"):
					korSex = "미상";
					break;
				
				}
				
				Annc = new HashMap<String, Serializable>();
				Annc.put("등록일자", rs.getString(1));
				Annc.put("동물종류", korAnmlKind);
				Annc.put("품종", rs.getString(3));
				Annc.put("성별", korSex);
				Annc.put("발견장소", rs.getString(5));
				Annc.put("특징", rs.getString(6));
				Annc.put("경로", rs.getString(7));
		}
			}
		catch(SQLException e){
			System.out.println("SELECT문 예외 발생");
			e.printStackTrace();
		}
		return Annc;
		
	}
	static List<Map<String, Serializable>> getAbanNoList() {
		abanList.clear();
		query = "SELECT ABAN_NO "
				+ "FROM PROT "
				+ "WHERE PROT_END_DATE = TO_DATE('9999-12-31', 'YYYY-MM-DD') "
				+ "ORDER BY 1 ";
		
		try{
			pstm = conn.prepareStatement(query, rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_READ_ONLY);
			rs = pstm.executeQuery();
			
			while(rs.next()){
				abanNo = new HashMap<String, Serializable>();
				abanNo.put("번호", rs.getString("ABAN_NO"));
				abanList.add(abanNo);
				
		}
			}
		catch(SQLException e){
			System.out.println("SELECT문 예외 발생");
			e.printStackTrace();
		}
		return abanList;
	}
	static Map<String, Serializable> getAnncDetail(String abanNo){
		query = "SELECT CNTR_NAME, ABAN_NAME, ANML_KINDS, KIND, SEX, AGE, COLOR, NEUT_WHET, "
				+ "ANML_SIZE, RSCU_DTTM, RSCU_LOC, FEAT "
				+ "FROM ABAN AB INNER JOIN PROT P ON AB.ABAN_NO = P.ABAN_NO "
				+ "INNER JOIN CNTR C ON P.CNTR_NO = C.CNTR_NO "
				+ "LEFT JOIN RSCU R ON AB.RSCU_NO = R.RSCU_NO "
				+ "WHERE P.PROT_END_DATE = TO_DATE('9999-12-31', 'YYYY-MM-DD') "
				+ "AND P.ABAN_NO = '"+abanNo+"'";
		
		try{
			pstm = conn.prepareStatement(query, rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_READ_ONLY);
			rs = pstm.executeQuery();
			
			while(rs.next()){
				
				
				AnncDtl = new HashMap<String, Serializable>();
				AnncDtl.put("센터명", rs.getString(1));
				AnncDtl.put("동물명", rs.getString(2));
				String korAnmlKind = "";
				switch(rs.getString(3)){
				case("d"):
					korAnmlKind = "개";
					break;
				case("c"):
					korAnmlKind = "고양이";
					break;
				case("e"):
					korAnmlKind = "기타";	
					break;
				}
				AnncDtl.put("동물종류", korAnmlKind);
				AnncDtl.put("품종", rs.getString(4));
				String korSex = "";
				switch(rs.getString(5)){
				case("m"):
					korSex = "수컷";
					break;
				case("f"):
					korSex = "암컷";
					break;
				}
				AnncDtl.put("성별", korSex);
				AnncDtl.put("나이", rs.getString(6));
				AnncDtl.put("색상", rs.getString(7));
				AnncDtl.put("중성화여부", rs.getString(8));
				String korAnmlSize = "";
				switch(rs.getString(9)){
				case("b"):
					korAnmlSize = "대형";
					break;
				case("m"):
					korAnmlSize = "중형";
					break;
				case("s"):
					korAnmlSize = "소형";	
					break;
				}
				AnncDtl.put("동물크기", korAnmlSize);
				AnncDtl.put("구조일시", rs.getString(10));
				AnncDtl.put("구조장소", rs.getString(11));
				AnncDtl.put("특징", rs.getString(12));
		}
			}
		catch(SQLException e){
			System.out.println("SELECT문 예외 발생");
			System.out.println(query);
			e.printStackTrace();
		}
		return AnncDtl;
	}
	static List<Map<String, Serializable>> getAbanPicPath(String abanNo){
		picPathSet.clear();
		query = "SELECT PATH "
				+ "FROM ABAN_PIC A INNER JOIN PROT P ON A.ABAN_NO = P.ABAN_NO "
				+ "WHERE P.PROT_END_DATE = TO_DATE('9999-12-31', 'YYYY-MM-DD') "
				+ "AND A.ABAN_NO = '"+abanNo+"'";
		try{
			pstm = conn.prepareStatement(query, rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_READ_ONLY);
			rs = pstm.executeQuery();
			
			while(rs.next()){
				picPath = new HashMap<String, Serializable>();
				picPath.put("경로", rs.getString("PATH"));
				picPathSet.add(picPath);
				}
			}
		catch(SQLException e){
			System.out.println("SELECT문 예외 발생");
			System.out.println(query);
			e.printStackTrace();
		}
		return picPathSet;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getAnnc("2019112202"));
	}

}
