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
	
	public static void setEmpData(String empNo){
		query = "select emp_name, cntr_no, emp_tp, biz_fild, pwd, sex, brth_year_mnth_day, addr, tel_no "
				+ "from emp e, emp_work_hist h where e.emp_no = h.emp_no "
				+ "and h.work_end_date = to_date('9999-12-31', 'yyyy-mm-dd') "
				+ "and e.emp_no = '"+empNo+"'";
		String empName, empTp, bizFild, pwd, sex, birthDay, addr, telNo;
		empName = empTp = bizFild = pwd = sex = birthDay = addr = telNo = "";
		try{
			pstm = conn.prepareStatement(query, rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_READ_ONLY);
			rs = pstm.executeQuery();
			
			while(rs.next()){
				empName = rs.getString(1);
				//cntrNo = rs.getString(2);
				empTp = rs.getString(3);
				bizFild = rs.getString(4);
				pwd = rs.getString(5);
				sex = rs.getString(6);
				String birthdt[] = rs.getString(7).split(" ");
				birthDay = birthdt[0];
				addr = rs.getString(8);
				telNo = rs.getString(9);
			}
		}catch(SQLException e){
			System.out.println("SELECT문 예외 발생");
			e.printStackTrace();
		}
		String korEmpTp = "";
		switch(empTp){
		case "f":
			korEmpTp = "정규직";
			break;
		case "c":
			korEmpTp = "계약직";
			break;
		}
		String korBizFild = "";
		switch(bizFild){
		case "c":
			korBizFild = "센터장";
			break;
		case "m":
			korBizFild = "관리직원";
			break;
		case "d":
			korBizFild = "수의사";
			break;
		case "o":
			korBizFild = "사무직종사자";
			break;
		case "p":
			korBizFild = "보호관리직원";
			break;
		case "r":
			korBizFild = "유기동물구조원";
			break;
		}
		String korSex = "";
		switch(sex){
		case "m":
			korSex = "남";
			break;
		case "f":
			korSex = "여";
			break;
		}
		
		empdata.put("직원명", empName);
		empdata.put("소속센터", getEmpCntr(empNo));
		empdata.put("직원구분", korEmpTp);
		empdata.put("업무분야", korBizFild);
		empdata.put("비밀번호", pwd);
		empdata.put("성별", korSex);
		empdata.put("생년월일", birthDay);
		empdata.put("주소", addr);
		empdata.put("전화번호", telNo);
	}
	
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
		String empPwd = null;
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
	static String getEmpCntrTp(){
		query = "SELECT CNTR_TP FROM EMP_WORK_HIST H, CNTR C WHERE WORK_END_DATE = TO_DATE('9999-12-31', 'YYYY-MM-DD')"
				+ "AND H.CNTR_NO = C.CNTR_NO AND EMP_NO = '"+Login.empID+"'";
		String CntrTp = "";
		try{
			pstm = conn.prepareStatement(query, rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_READ_ONLY);
			rs = pstm.executeQuery();
			
			while(rs.next()){
				CntrTp = rs.getString(1);
			}
		}catch(SQLException e){
			System.out.println("SELECT문 예외 발생");
			e.printStackTrace();
		}
		return CntrTp;
	}
	static void updateEmp(String newAddr, String newTelNo){
		query = "UPDATE EMP SET ADDR = '"+newAddr+"', TEL_NO = '"+newTelNo+"' "
				+"WHERE EMP_NO = '"+Login.getEmpNo()+"'";
		try{
			pstm = conn.prepareStatement(query, rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_READ_ONLY);
			rs = pstm.executeQuery();
		}catch(SQLException e){
			System.out.println("SELECT문 예외 발생");
			e.printStackTrace();
		}
	}
	static void updateEmpPass(String newPass){
		query = "UPDATE EMP SET PWD = '"+newPass+"' "
				+"WHERE EMP_NO = '"+Login.getEmpNo()+"'";
		try{
			pstm = conn.prepareStatement(query, rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_READ_ONLY);
			rs = pstm.executeQuery();
		}catch(SQLException e){
			System.out.println("SELECT문 예외 발생");
			e.printStackTrace();
		}
	}
	
	static String getSEMPNo(String empID){
		String empNo = "";
		query = "SELECT EMP_NO FROM EMP WHERE EMP_NO = '"+empID+"'";
		try{
			pstm = conn.prepareStatement(query, rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_READ_ONLY);
			rs = pstm.executeQuery();
			
			while(rs.next()){
				empNo = rs.getString(1);
			}
		}catch(SQLException e){
			System.out.println("sql 오류");
			e.printStackTrace();
		}
		return empNo;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		setEmpData("0000");
	}

}
