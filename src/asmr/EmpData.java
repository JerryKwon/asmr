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

public class EmpData {
	public static Connection conn = ConnDB.getConnection();
	static String query;
	static PreparedStatement pstm = null;
	static ResultSet rs = null;
	
	public static Map<String, Serializable > empdata = new HashMap<String, Serializable>();
	public static Map<String, Serializable > empdataSet;
	
	public static List<Map<String, Serializable>> empListData = new ArrayList<Map<String, Serializable>>();
	
	
	static String getEmpName(String empNo){
		query = "SELECT emp_name FROM emp WHERE emp_no='"+empNo+"'";
		String empName = "";
		try{
			pstm = conn.prepareStatement(query, rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_READ_ONLY);
			rs = pstm.executeQuery();
			
			while(rs.next()){
				empName = rs.getString(1);
			}
		}catch(SQLException e){
			System.out.println("SELECT문 예외 발생");
			e.printStackTrace();
		}
		return empName;
	}
	static String getEmpPwd(String empNo){
		query = "SELECT pwd FROM emp WHERE emp_no='"+empNo+"'";
		String empPwd = "";
		try{
			pstm = conn.prepareStatement(query, rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_READ_ONLY);
			rs = pstm.executeQuery();
			
			while(rs.next()){
				empPwd = rs.getString(1);
			}
		}catch(SQLException e){
			System.out.println("SELECT문 예외 발생");
			e.printStackTrace();
		}
		return empPwd;
	}
	static String getEmpCntr(String empNo){
		query = "SELECT cntr_name FROM emp_work_hist e, cntr c WHERE e.cntr_no = c.cntr_no and e.emp_no ='"+empNo+"'"
				+"AND e.WORK_END_DATE = TO_DATE('9999-12-31', 'YYYY-MM-DD')";
		String empCntr = "";
		try{
			pstm = conn.prepareStatement(query, rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_READ_ONLY);
			rs = pstm.executeQuery();
			
			while(rs.next()){
				empCntr = rs.getString(1);
			}
		}catch(SQLException e){
			System.out.println("SELECT문 예외 발생");
			e.printStackTrace();
		}
		return empCntr;
		
	}
	static String getCntrNo(String empNo){
		query = "SELECT CNTR_NO FROM EMP_WORK_HIST WHERE WORK_END_DATE = TO_DATE('9999-12-31', 'YYYY-MM-DD')"
				+ "AND EMP_NO = '"+empNo+"'";
		String CntrNo = "";
		try{
			pstm = conn.prepareStatement(query, rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_READ_ONLY);
			rs = pstm.executeQuery();
			
			while(rs.next()){
				CntrNo = rs.getString(1);
			}
		}catch(SQLException e){
			System.out.println("SELECT문 예외 발생");
			e.printStackTrace();
		}
		return CntrNo;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getCntrNo("0000"));
	}

}
