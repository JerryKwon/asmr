package asmr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBConn {
  private static final String URL="jdbc:oracle:thin:@localhost:1521:XE";   // �����ϴ� DB�� �ּ�����
  private static final String USER="da";   //�����ϴ� DB�� ��������
  private static final String PASSWORD = "da"; // �����ϴ� DB������ �н���������
  private static Connection conn = null;   //jdbc�� ���� �ڹٿ� DB�� �������ִ� Ŭ���� ��ü
  static {
     // jdbc����̹��� ���Ͽ� �����ϰ����ϴ� DB�� �ּ�, ����, ��й�ȣ ������ �Ѱ��־� DB�� ����
     try {
         Class.forName("oracle.jdbc.driver.OracleDriver");
         conn = DriverManager.getConnection(URL, USER, PASSWORD);
         System.out.println("������");
     }catch(ClassNotFoundException e){
         e.printStackTrace();
     }catch(SQLException e) {
         e.printStackTrace();
     }
  }
  public static Connection getConnection(){
     return conn;
     }
  public static void main(String[] args) {
//      String URL="jdbc:oracle:thin:@DESKTOP-EKJ9J3T:1521:XE";   // �����ϴ� DB�� �ּ�����
//      String USER="scott";   //�����ϴ� DB�� ��������
//      String PASSWORD = "TIGER"; // �����ϴ� DB������ �н���������
//      Connection conn = null;
//
//      try {
//         Class.forName("oracle.jdbc.driver.OracleDriver");
//         System.out.println("����");
//         conn = DriverManager.getConnection(URL, USER, PASSWORD);
//         System.out.println("����");
//         conn.close();
//      }catch(ClassNotFoundException e){
//          e.printStackTrace();
//      }catch(SQLException e) {
//          e.printStackTrace();
//      }
//
  }
}