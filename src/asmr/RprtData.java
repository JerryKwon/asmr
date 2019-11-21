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
   
   public static Map<String, Serializable > assgrprtdata = new HashMap<String, Serializable>();
   public static Map<String, Serializable > rprtdata = new HashMap<String, Serializable>();
   public static Map<String, Serializable > cagedata = new HashMap<String, Serializable>();
   public static Map<String, Serializable > cntrdata = new HashMap<String, Serializable>();
   public static Map<String, Serializable > cntrnodata = new HashMap<String, Serializable>();
   public static Map<String, Serializable > assgnodata = new HashMap<String, Serializable>();
   public static Map<String, Serializable > rprtnodata = new HashMap<String, Serializable>();
   public static Map<String, Serializable > apprassgdata = new HashMap<String, Serializable>();
   
   public static Map<String, Serializable > rprtdataSet;
   public static Map<String, Serializable > assgrprtdataSet;
   public static Map<String, Serializable > cagedataSet;
   public static Map<String, Serializable > cntrdataSet;
   public static Map<String, Serializable > cntrnodataSet;
   public static Map<String, Serializable > assgnodataSet;
   public static Map<String, Serializable > rprtnodataSet;
   public static Map<String, Serializable > apprassgdataSet;
   
   public static List<Map<String, Serializable>> assgRprtListData = new ArrayList<Map<String, Serializable>>();
   public static List<Map<String, Serializable>> rprtListData = new ArrayList<Map<String, Serializable>>();
   public static List<Map<String, Serializable>> cageListData = new ArrayList<Map<String, Serializable>>();
   public static List<Map<String, Serializable>> cntrListData = new ArrayList<Map<String, Serializable>>();
   public static List<Map<String, Serializable>> cntrNoListData = new ArrayList<Map<String, Serializable>>();
   public static List<Map<String, Serializable>> assgNoListData = new ArrayList<Map<String, Serializable>>();
   public static List<Map<String, Serializable>> rprtNoListData = new ArrayList<Map<String, Serializable>>();
   public static List<Map<String, Serializable>> apprAssgListData = new ArrayList<Map<String, Serializable>>();
   
   private static ArrayList<String> cntrNm;
   
   // 배정전 신고 정보 가져오기.
   static List<Map<String, Serializable>> getRprtList(){
//      RprtAssignment.rprtNos.clear();
   
      StringBuffer query = new StringBuffer("SELECT rprt_dttm, anml_kinds, anml_size, expln, R.rprt_no ");
      query.append("FROM rprt R ");
      query.append("LEFT OUTER JOIN ASSG A ");
      query.append("ON R.RPRT_NO = A.rprt_no ");
      query.append("WHERE ASSG_NO IS NULL ");
//      query.append("AND ASSG_RES != 'a' "); 배정 결과가 승인이 아닌경우..
      
      rprtListData.clear();
      
      try{
         pstm = conn.prepareStatement(query.toString(), rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_READ_ONLY);
         rs = pstm.executeQuery();
         
         while(rs.next()){
                  rprtdataSet = new HashMap<String, Serializable>();
  
                  rprtdataSet.put("신고일시", rs.getString(1));
                  rprtdataSet.put("동물종류", rs.getString(2));
                  rprtdataSet.put("동물크기", rs.getString(3)); 
                  rprtdataSet.put("설명", rs.getString(4));
//                  rprtdataSet.put("배정센터명",retrieve());
                  
//                  RprtAssignment.rprtNos.add(rs.getString("RPRT_NO"));
               
                  rprtListData.add(rprtdataSet);
         }
      }catch(SQLException e){
         System.out.println("SELECT문 예외 발생");
         e.printStackTrace();
      }
      return rprtListData;
   }
   
   // 로그인한 직원의 센터에 배정된 신고 가져오기.
   static List<Map<String, Serializable>> getAssgRprtList(){
//      RprtAssignment.rprtNos.clear();
      
      String cntr_no = EmpData.getCntrNo(Login.getEmpNo());
   
      StringBuffer query = new StringBuffer("SELECT rprt_dttm, anml_kinds, anml_size, expln, R.rprt_no, ASSG_NO ");
      query.append("FROM rprt R ");
      query.append("LEFT OUTER JOIN ASSG A ");
      query.append("ON R.RPRT_NO = A.rprt_no ");
      query.append("WHERE ASSG_NO IS NOT NULL ");
      query.append("AND CNTR_NO = '"+cntr_no+"' ");
      query.append("AND ASSG_RES IS NULL ");
      
      
      
      assgRprtListData.clear();
      
      try{
         pstm = conn.prepareStatement(query.toString(), rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_READ_ONLY);
         rs = pstm.executeQuery();
         
         while(rs.next()){
                  assgrprtdataSet = new HashMap<String, Serializable>();
  
                  assgrprtdataSet.put("신고일시", rs.getString(1));
                  assgrprtdataSet.put("동물종류", rs.getString(2));
                  assgrprtdataSet.put("동물크기", rs.getString(3)); 
                  assgrprtdataSet.put("설명", rs.getString(4));
                  assgrprtdataSet.put("배정번호",rs.getString(6));
                  
//                  RprtAssignment.rprtNos.add(rs.getString("RPRT_NO"));
               
                  assgRprtListData.add(assgrprtdataSet);
         }
      }catch(SQLException e){
         System.out.println("SELECT문 예외 발생");
         e.printStackTrace();
      }
      return assgRprtListData;
   }
   
   // 로그인한 직원의 센터에 배정승인된 신고 가져오기.
      static List<Map<String, Serializable>> getApprAssgList(){
//         RprtAssignment.rprtNos.clear();
         
         String cntr_no = EmpData.getCntrNo(Login.getEmpNo());
      
         StringBuffer query = new StringBuffer("SELECT assg_dttm, anml_kinds, anml_size, expln, R.rprt_no, ASSG_NO ");
         query.append("FROM rprt R ");
         query.append("LEFT OUTER JOIN ASSG A ");
         query.append("ON R.RPRT_NO = A.rprt_no ");
         query.append("WHERE ASSG_NO IS NOT NULL ");
         query.append("AND CNTR_NO = '"+cntr_no+"' ");
         query.append("AND ASSG_RES = 'a' ");
         
         
         
         apprAssgListData.clear();
         
         try{
            pstm = conn.prepareStatement(query.toString(), rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_READ_ONLY);
            rs = pstm.executeQuery();
            
            while(rs.next()){
                     apprassgdataSet = new HashMap<String, Serializable>();
     
                     apprassgdataSet.put("신고승인일시", rs.getString(1));
                     apprassgdataSet.put("동물종류", rs.getString(2));
                     apprassgdataSet.put("동물크기", rs.getString(3)); 
                     apprassgdataSet.put("설명", rs.getString(4));
                     apprassgdataSet.put("배정번호",rs.getString(6));
                     
//                     RprtAssignment.rprtNos.add(rs.getString("RPRT_NO"));
                  
                     apprAssgListData.add(apprassgdataSet);
            }
         }catch(SQLException e){
            System.out.println("SELECT문 예외 발생");
            e.printStackTrace();
         }
         return apprAssgListData;
      }
   
   static String getCntrNoList(String nm){
      
//      cntrNm = new ArrayList<String>();
      String cntrNo = "";
      
      
      StringBuffer query = new StringBuffer("SELECT CNTR_NO FROM cntr ");
      query.append("WHERE CNTR_NAME= '"+nm+"' ");;
      
      cntrNoListData.clear();
      
      try{
         pstm = conn.prepareStatement(query.toString(), rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_READ_ONLY);
         rs = pstm.executeQuery();
         
         while(rs.next()){
//                  cntrdataSet = new HashMap<String, Serializable>();
                  
                  cntrNo = rs.getString(1);

               
//                  cntrNoListData.add(cntrdataSet);
                  
//                  cntrNo.add(rs.getString(1));
         }
      }catch(SQLException e){
         System.out.println("SELECT문 예외 발생");
         e.printStackTrace();
      }
      return cntrNo;
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
   
   static List<Map<String, Serializable>> getCageList(){
//      RprtAssignment.rprtNos.clear();
      
      StringBuffer query = new StringBuffer("select * ");
      query.append("from (select cntr_name as nm, addr, decode(cage_size, 'b', '여유케이지(대)', 'm', '여유케이지(중)', 's', '여유케이지(소)') as sz ");
      query.append("         from cntr ");
      query.append("         join cage ");
      query.append("         on cntr.cntr_no = cage.cntr_no) ");
      query.append("pivot( ");
      query.append("   count(sz) ");
      query.append("   for sz in ('여유케이지(대)', '여유케이지(중)', '여유케이지(소)') ");
      query.append(") ");

      
      cageListData.clear();
      
      try{
         pstm = conn.prepareStatement(query.toString(), rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_READ_ONLY);
         rs = pstm.executeQuery();
         
         while(rs.next()){
                  cagedataSet = new HashMap<String, Serializable>();
  
                  cagedataSet.put("센터명", rs.getString(1));
                  cagedataSet.put("주소", rs.getString(2));
                  cagedataSet.put("여유케이지(대)", rs.getString(3)); 
                  cagedataSet.put("여유케이지(중)", rs.getString(4));
                  cagedataSet.put("여유케이지(소)",rs.getString(5));
                  
//                  RprtAssignment.rprtNos.add(rs.getString("RPRT_NO"));
               
                  cageListData.add(cagedataSet);
         }
      }catch(SQLException e){
         System.out.println("SELECT문 예외 발생");
         e.printStackTrace();
      }
      return cageListData;
   }
   
static String getRprtNoList(String dttm){
      
//      cntrNm = new ArrayList<String>();
      String rprtNo = "";
      
      
      StringBuffer query = new StringBuffer("SELECT RPRT_NO FROM RPRT ");
      query.append("WHERE RPRT_DTTM= to_date('"+dttm+"','YYYY-MM-DD hh24:mi:ss') ");;
      
      cntrNoListData.clear();
      
      try{
         pstm = conn.prepareStatement(query.toString(), rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_READ_ONLY);
         rs = pstm.executeQuery();
         
         while(rs.next()){
//                  cntrdataSet = new HashMap<String, Serializable>();
                  
                  rprtNo = rs.getString(1);

               
//                  cntrNoListData.add(cntrdataSet);
                  
//                  cntrNo.add(rs.getString(1));
         }
      }catch(SQLException e){
         System.out.println("SELECT문 예외 발생");
         e.printStackTrace();
      }
      return rprtNo;
   }

static String getAssgNoList(String dttm){
   
//   cntrNm = new ArrayList<String>();
   String assgNo = "";
   
   
   StringBuffer query = new StringBuffer("SELECT ASSG_NO FROM ASSG ");
   query.append("WHERE ASSG_DTTM= to_date('"+dttm+"','YYYY-MM-DD hh24:mi:ss') ");;
   
//   assgNoListData.clear();
   
   try{
      pstm = conn.prepareStatement(query.toString(), rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_READ_ONLY);
      rs = pstm.executeQuery();
      
      while(rs.next()){
//               cntrdataSet = new HashMap<String, Serializable>();
               
               assgNo = rs.getString(1);

            
//               cntrNoListData.add(cntrdataSet);
               
//               cntrNo.add(rs.getString(1));
      }
   }catch(SQLException e){
      System.out.println("SELECT문 예외 발생");
      e.printStackTrace();
   }
   return assgNo;
}

   public static void main(String[] args) {
      // TODO Auto-generated method stub

   
   }


}