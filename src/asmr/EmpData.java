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
	
	
	
	
	static String getEmpName(int empNo){
		query = "SELECT emp_name FROM emp WHERE emp_no="+empNo;
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
	static String getEmpPwd(int empNo){
		query = "SELECT pwd FROM emp WHERE emp_no="+empNo;
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getEmpPwd(0));
	}

}
