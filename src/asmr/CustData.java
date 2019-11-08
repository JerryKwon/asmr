package asmr;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CustData {
	public static Connection conn = ConnDB.getConnection();
	static String query;
	static PreparedStatement pstm = null;
	static ResultSet rs = null;
	
	public static Map<String, Serializable> custdata = new HashMap<String, Serializable>();
	
	
	
	public static void initCustData(String custName, String address, String phone, String id, String pwd){
		custdata.put("이름", custName);
		custdata.put("주소", address);
		custdata.put("전화번호", phone);
		custdata.put("아이디", id);
		custdata.put("비밀번호", pwd);		
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
