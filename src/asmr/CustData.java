package asmr;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

public class CustData {
	public static Connection conn = ConnDB.getConnection();
	static String query;
	static PreparedStatement pstm = null;
	static ResultSet rs = null;
	
	public static Map<String, Serializable> custdata = new HashMap<String, Serializable>();
	public static Map<String, Serializable> custMydata = new HashMap<String, Serializable>();	
	
	public static void initCustData(String custName, String address, String phone, String id, String pwd){
		custdata.put("이름", custName);
		custdata.put("주소", address);
		custdata.put("전화번호", phone);
		custdata.put("아이디", id);
		custdata.put("비밀번호", pwd);		
	}
	public static void setCustData(){
		query = "SELECT CUST_NAME, ADDR, TEL_NO, ID, PWD FROM CUST";
		String custName, addr, telNo, id, pwd;
		custName = addr = telNo = id = pwd = "";
		try{
			pstm = conn.prepareStatement(query, rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_READ_ONLY);
			rs = pstm.executeQuery();
			
			while(rs.next()){
				custName = rs.getString(1);
				addr = rs.getString(2);
				telNo = rs.getString(3);
				id = rs.getString(4);
				pwd = rs.getString(5);
				
			}
		}catch(SQLException e){
			System.out.println("SELECT문 예외 발생");
			e.printStackTrace();
		}
		custMydata.put("아이디", id);
		custMydata.put("이름", custName);
		custMydata.put("비밀번호", pwd);
		custMydata.put("주소", addr);
		custMydata.put("전화번호", telNo);		
	}
	static void createIsUserCust(){
		char custType = 'm';
		query = "INSERT INTO CUST VALUES ((SELECT NVL(MAX(CUST_NO)+1, 0) FROM CUST), "
				+"'"+custdata.get("이름")+"', '"+custdata.get("주소")+"', '"+custdata.get("전화번호")+"',"
				+"'"+custType+"', '"+custdata.get("아이디")+"', '"+custdata.get("비밀번호")+"')";
		try{
			pstm = conn.prepareStatement(query);
			pstm.executeQuery();
		}catch(SQLException e){
			System.out.println("sql에러");
			e.printStackTrace();
		}
		ConnDB.disConnection();
	}
	static void createIsNotUserCust(){
		char custType = 'n';
		query = "INSERT INTO CUST VALUES ((SELECT NVL(MAX(CUST_NO)+1, 0) FROM CUST), "
				+"'"+custdata.get("이름")+"', '"+custdata.get("주소")+"', '"+custdata.get("전화번호")+"',"
				+"'"+custType+"')";
		try{
			pstm = conn.prepareStatement(query);
			pstm.executeQuery();
		}catch(SQLException e){
			System.out.println("sql에러");
			e.printStackTrace();
		}
		ConnDB.disConnection();
	}
	static int getCustNo(String custID){
		int custNo = 0;
		query = "SELECT CUST_NO FROM CUST WHERE ID = '"+custID+"'";
		try{
			pstm = conn.prepareStatement(query, rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_READ_ONLY);
			rs = pstm.executeQuery();
			
			while(rs.next()){
				custNo = rs.getInt(1);
			}
		}catch(SQLException e){
			System.out.println("sql 오류");
			e.printStackTrace();
		}
		return custNo;
	}
	static String getCustPwd(int custNo){
		String custPwd = "";
		query = "SELECT PWD FROM CUST WHERE CUST_NO = "+custNo;
		try{
			pstm = conn.prepareStatement(query, rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_READ_ONLY);
			rs = pstm.executeQuery();
			
			while(rs.next()){
				custPwd = rs.getString(1);
			}
		}catch(SQLException e){
			System.out.println("SELECT문 예외 발생");
			e.printStackTrace();
		}
		return custPwd;
	}
	static String getCustName(int custNo){
		String custName = "";
		query = "SELECT CUST_NAME FROM CUST WHERE CUST_NO = "+custNo;
		try{
			pstm = conn.prepareStatement(query, rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_READ_ONLY);
			rs = pstm.executeQuery();
			
			while(rs.next()){
				custName = rs.getString(1);
			}
		}catch(SQLException e){
			System.out.println("SELECT문 예외 발생");
			e.printStackTrace();
		}
		return custName;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
