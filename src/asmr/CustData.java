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
		custdata.put("�̸�", custName);
		custdata.put("�ּ�", address);
		custdata.put("��ȭ��ȣ", phone);
		custdata.put("���̵�", id);
		custdata.put("��й�ȣ", pwd);		
	}
	
	static void createIsUserCust(){
		char custType = 'm';
		query = "INSERT INTO CUST VALUES ((SELECT NVL(MAX(CUST_NO)+1, 0) FROM CUST), "
				+"'"+custdata.get("�̸�")+"', '"+custdata.get("�ּ�")+"', '"+custdata.get("��ȭ��ȣ")+"',"
				+"'"+custType+"', '"+custdata.get("���̵�")+"', '"+custdata.get("��й�ȣ")+"')";
		try{
			pstm = conn.prepareStatement(query);
			pstm.executeQuery();
		}catch(SQLException e){
			System.out.println("sql����");
			e.printStackTrace();
		}
		ConnDB.disConnection();
	}
	static void createIsNotUserCust(){
		char custType = 'n';
		query = "INSERT INTO CUST VALUES ((SELECT NVL(MAX(CUST_NO)+1, 0) FROM CUST), "
				+"'"+custdata.get("�̸�")+"', '"+custdata.get("�ּ�")+"', '"+custdata.get("��ȭ��ȣ")+"',"
				+"'"+custType+"')";
		try{
			pstm = conn.prepareStatement(query);
			pstm.executeQuery();
		}catch(SQLException e){
			System.out.println("sql����");
			e.printStackTrace();
		}
		ConnDB.disConnection();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
