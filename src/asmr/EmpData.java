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
			System.out.println("SELECT�� ���� �߻�");
			e.printStackTrace();
		}
		String korEmpTp = "";
		switch(empTp){
		case "f":
			korEmpTp = "������";
			break;
		case "c":
			korEmpTp = "�����";
			break;
		}
		String korBizFild = "";
		switch(bizFild){
		case "c":
			korBizFild = "������";
			break;
		case "m":
			korBizFild = "��������";
			break;
		case "d":
			korBizFild = "���ǻ�";
			break;
		case "o":
			korBizFild = "�繫��������";
			break;
		case "p":
			korBizFild = "��ȣ��������";
			break;
		case "r":
			korBizFild = "���⵿��������";
			break;
		}
		String korSex = "";
		switch(sex){
		case "m":
			korSex = "��";
			break;
		case "f":
			korSex = "��";
			break;
		}
		
		empdata.put("������", empName);
		empdata.put("�ҼӼ���", getEmpCntr(empNo));
		empdata.put("��������", korEmpTp);
		empdata.put("�����о�", korBizFild);
		empdata.put("��й�ȣ", pwd);
		empdata.put("����", korSex);
		empdata.put("�������", birthDay);
		empdata.put("�ּ�", addr);
		empdata.put("��ȭ��ȣ", telNo);
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
			System.out.println("SELECT�� ���� �߻�");
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
			System.out.println("SELECT�� ���� �߻�");
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
			System.out.println("SELECT�� ���� �߻�");
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
			System.out.println("SELECT�� ���� �߻�");
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
			System.out.println("SELECT�� ���� �߻�");
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
			System.out.println("SELECT�� ���� �߻�");
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
			System.out.println("SELECT�� ���� �߻�");
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
			System.out.println("sql ����");
			e.printStackTrace();
		}
		return empNo;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		setEmpData("0000");
	}

}
