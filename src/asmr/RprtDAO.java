package asmr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class RprtDAO {
	
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@192.168.0.3:1521:ORCL";
	private static final String USER = "da"; //DB ID
	private static final String PASS = "da"; //DB �н�����
	Member_List mList;

	public RprtDAO() {
	
}
	
	public RprtDAO(Member_List mList){
	    this.mList = mList;
	    System.out.println("DAO=>"+mList);
	}
	
    /**DB���� �޼ҵ�*/
    public Connection getConn(){
        Connection con = null;
       
        try {
            Class.forName(DRIVER); //1. ����̹� �ε�
            con = DriverManager.getConnection(URL,USER,PASS); //2. ����̹� ����
           
        } catch (Exception e) {
            e.printStackTrace();
        }
       
        return con;
    }
    
    /**�ѻ���� ȸ�� ������ ��� �޼ҵ�*/
    public RprtDTO getRprtDTO(String id){
       
        RprtDTO dto = new RprtDTO();
       
        Connection con = null;       //����
        PreparedStatement ps = null; //���
        ResultSet rs = null;         //���
       
        try {
           
            con = getConn();
            String sql = "select * from rprt where id=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, id);
           
            rs = ps.executeQuery();
           
            if(rs.next()){
                dto.setRprtPrsnName(rs.getInt(columnIndex));
                dto.setTelNo(rs.getString("Pwd"));
                dto.setRprtTp(rs.getString("Name"));
                dto.setAnmlKinds(rs.getString("tel"));
                dto.setAnmlSize(rs.getString("addr"));
                dto.setExpln(rs.getString("birth"));
                dto.setDscvDttm(rs.getString("job"));
                dto.setDscvLoc(rs.getString("gender"));
                dto.setPic(rs.getString("email"));               
            }
        } catch (Exception e) {
            e.printStackTrace();
        }      
       
        return dto;    
    }
   
    /**�������Ʈ ���*/
    public Vector<String> getMemberList(){
       
        Vector<String> data = new Vector<String>();  //Jtable�� ���� ���� �ִ� ��� 1. 2�����迭   2. Vector �� vector�߰�
       
           
        Connection con = null;       //����
        PreparedStatement ps = null; //���
        ResultSet rs = null;         //���
       
        try{
           
            con = getConn();
            String sql = "select * from rprt order by name asc";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
           
            while(rs.next()){
            	String rprtDttm = rs.getString(arg0)
                String id = rs.getString("");
                String pwd = rs.getString("pwd");
                String name = rs.getString("name");
                String tel = rs.getString("tel");
                String addr = rs.getString("addr");
                String birth = rs.getString("birth");
                String gender = rs.getString("gender");
                String job = rs.getString("job");
                String email = rs.getString("email");
                String intro = rs.getString("intro");
               
                Vector row = new Vector();
                row.add(id);
                row.add(pwd);
                row.add(name);
                row.add(tel);
                row.add(addr);
                row.add(birth);
                row.add(job);
                row.add(gender);
                row.add(email);
                row.add(intro);
               
                data.add(row);             
            }//while
        }catch(Exception e){
            e.printStackTrace();
        }
        return data;
    }//getMemberList()
	
	
	
	
	}
