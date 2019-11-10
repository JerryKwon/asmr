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


// 신고목록에 들어갈 데이터
public class RprtData {
	public static Connection conn = ConnDB.getConnection();
	static String query;
	static PreparedStatement pstm = null;
	static ResultSet rs = null;
	
	public static Map<String, Serializable > rprtdata = new HashMap<String, Serializable>();
	public static Map<String, Serializable > cntrdata = new HashMap<String, Serializable>();
	
	public static Map<String, Serializable > rprtdataSet;
	public static Map<String, Serializable > cntrdataSet;
	
	public static List<Map<String, Serializable>> rprtListData = new ArrayList<Map<String, Serializable>>();
	public static List<Map<String, Serializable>> cntrListData = new ArrayList<Map<String, Serializable>>();
	
	private static ArrayList<String> cntrNm;
	
	static List<Map<String, Serializable>> getRprtList(){
//		RprtAssignment.rprtNos.clear();
		query = "SELECT rprt_no, rprt_dttm, anml_kinds, anml_size, expln FROM rprt";
		
		rprtListData.clear();
		
		try{
			pstm = conn.prepareStatement(query, rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_READ_ONLY);
			rs = pstm.executeQuery();
			
			while(rs.next()){
	               rprtdataSet = new HashMap<String, Serializable>();
  
	               rprtdataSet.put("신고일시", rs.getString(1));
	               rprtdataSet.put("동물종류", rs.getString(2));
	               rprtdataSet.put("동물크기", rs.getString(3)); 
	               rprtdataSet.put("설명", rs.getString(4));
//	               rprtdataSet.put("배정센터명",retrieve());
	               
//	               RprtAssignment.rprtNos.add(rs.getString("RPRT_NO"));
	            
	               rprtListData.add(rprtdataSet);
			}
		}catch(SQLException e){
			System.out.println("SELECT문 예외 발생");
			e.printStackTrace();
		}
		return rprtListData;
	}
	
	static ArrayList<String> getCntrList(){
		
		cntrNm = new ArrayList<String>();
		
		
		query = "SELECT DISTINCT(cntr_name) FROM cntr";
		
		cntrListData.clear();
		
		try{
			pstm = conn.prepareStatement(query, rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_READ_ONLY);
			rs = pstm.executeQuery();
			
			while(rs.next()){
	               cntrdataSet = new HashMap<String, Serializable>();
	               
	               cntrdataSet.put("센터이름", rs.getString(1));

	            
	               cntrListData.add(cntrdataSet);
	               
	               cntrNm.add(rs.getString(1));
			}
		}catch(SQLException e){
			System.out.println("SELECT문 예외 발생");
			e.printStackTrace();
		}
		return cntrNm;
	}
	

    //RETRIEVE DATA
//    static public DefaultComboBoxModel<String> retrieve()
//    {
//        DefaultComboBoxModel<String> dm=new DefaultComboBoxModel<>();
//
//        //SQL
//        query="SELECT cntr_name FROM cntr";
//
//        try
//        {
//        	pstm = conn.prepareStatement(query, rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_READ_ONLY);
//			rs = pstm.executeQuery();
//
//
//            //LOOP THRU GETTING ALL VALUES
//            while(rs.next())
//            {
//                //GET VALUES
//                String name=rs.getString(1);
//
//                dm.addElement(name);
//            }
//
//            return dm;
//
//        }catch (SQLException ex) {
//            ex.printStackTrace();
//             return null;
//        }
//
//    }
//	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getCntrList().get(0));
//		System.out.println(retrieve());
	
	}


}