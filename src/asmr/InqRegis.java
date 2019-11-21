package asmr;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class InqRegis extends JPanel {
	
	private static String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private static String user = "asmr";
	private static String password = "asmr";
	
	private static Connection con = null;
	private static PreparedStatement pstmt = null;
	private static ResultSet rs = null;
	private static ResultSetMetaData rsmd = null;
	
	InqRegisButtonListener inqRegisButtonListener;
	
	private JLabel vInq, vTit, vCont;
	
	private static JTextField xTit;
	
	private static JTextArea xCont;
	
	private JButton regis, cancel;
	
	GridBagLayout gridbaglayout;
	GridBagConstraints gridbagconstraints;
	
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	private Color black = new Color(0,0,0);
	
	public InqRegis() {
		
		inqRegisButtonListener = new InqRegisButtonListener();
		
		gridbaglayout = new GridBagLayout();
		gridbagconstraints = new GridBagConstraints();
		
		vInq = new JLabel("Q&A");
		vInq.setFont(new Font("나눔고딕", Font.BOLD, 24));
		
		vTit = new JLabel("제목");
		vTit.setFont(new Font("나눔고딕", Font.PLAIN, 16));
		xTit = new JTextField(20);
		
		vCont = new JLabel("내용");
		vCont.setFont(new Font("나눔고딕", Font.PLAIN, 16));
		xCont = new JTextArea(5,50);
		
		regis = new JButton("등록");
		regis.setFont(new Font("나눔고딕", Font.BOLD, 16));
		regis.setBackground(blue);
		regis.setForeground(white);
		regis.addActionListener(inqRegisButtonListener);
		
		cancel = new JButton("취소");
		cancel.setFont(new Font("나눔고딕", Font.BOLD, 16));
		cancel.setBackground(white);
		cancel.setForeground(black);
		cancel.addActionListener(inqRegisButtonListener);
		
		InqRegisView();
		
	}
	
	private void InqRegisView() {
		setBackground(MainFrame.bgc);
		
//		setTitle("문의등록");	
		
		gridbagconstraints.anchor = GridBagConstraints.WEST;
		gridbagconstraints.ipadx = 7;
		
		gridbagconstraints.weightx=1.0;
		gridbagconstraints.weighty=1.0;
		
		setLayout(gridbaglayout);

		gridbagAdd(vInq, 0, 0, 1, 1);
		
		gridbagAdd(vTit, 0, 1, 1, 1);
		gridbagAdd(xTit, 2, 1, 2, 1);
		
		gridbagAdd(vCont, 0, 2, 1, 1);
		gridbagAdd(xCont, 2, 2, 5, 1);
		
		gridbagAdd(regis, 4, 3, 1, 1);
		gridbagAdd(cancel, 5, 3, 1, 1);
		
		
		gridbagconstraints.anchor = GridBagConstraints.CENTER;

//		pack();
//		setResizable(false);
//		setVisible(true);
		
		
	}
	
	private void gridbagAdd(Component c, int x, int y, int w, int h) {			
		
		gridbagconstraints.gridx = x;		
		gridbagconstraints.gridy = y; 		
	      //가장 왼쪽 위 gridx, gridy값은 0 			
				
		gridbagconstraints.gridwidth  = w;	//넓이	
		gridbagconstraints.gridheight = h;	//높이	
	     			
	      			
	    gridbaglayout.setConstraints(c, gridbagconstraints); //컴포넌트를 컴포넌트 위치+크기 정보에 따라 GridBagLayout에 배치			
				
	   add(c);			
				
	   }
	
    class InqRegisButtonListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource().equals(regis)) {	
				PostInq();	
				MainFrame.qnaBoardCase();
			}
			else if(e.getSource().equals(cancel)) {
				MainFrame.qnaBoardCase();
			}
		}
		
	}
    
    static void PostInq() {
    	connection();
    	
		String postTit = xTit.getText();
		String postCont = xCont.getText();
		String cust_no = CustData.getSCustNo(Login.custID);
		
    	
		try {
			StringBuffer query1 = new StringBuffer("INSERT INTO POST(POST_NO, POST_TIT, WRT_DTTM, POST_CONT, POST_TP, INQ_POST_NO, INQ_WRT_PRSN_NO, ANS_WRT_PRSN_NO, NOTI_WRT_PRSN_NO) ");
			query1.append("VALUES( ");
			query1.append("POST_SEQ.nextval, ");
			query1.append("'"+postTit+"', ");
			query1.append("sysdate, ");
			query1.append("'"+postCont+"', ");
			query1.append("'q', ");
			query1.append("null, ");
			query1.append("'"+cust_no+"', ");
			query1.append("null, ");
			query1.append("null) ");
			
			pstmt = con.prepareStatement(query1.toString());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				con.commit();
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		disconnection();
		
    	
    	
    }
    
    static void PostAns(String pno) {
    	connection();
    	
		String postTit = xTit.getText();
		String postCont = xCont.getText();
		String emp_no = EmpData.getSEMPNo(Login.empID);
		
    	
		try {
			StringBuffer query1 = new StringBuffer("INSERT INTO POST(POST_NO, POST_TIT, WRT_DTTM, POST_CONT, POST_TP, INQ_POST_NO, INQ_WRT_PRSN_NO, ANS_WRT_PRSN_NO, NOTI_WRT_PRSN_NO) ");
			query1.append("VALUES( ");
			query1.append("POST_SEQ.nextval, ");
			query1.append("'"+postTit+"', ");
			query1.append("sysdate, ");
			query1.append("'"+postCont+"', ");
			query1.append("'a', ");
			query1.append("'"+pno+"', ");
			query1.append("null, ");
			query1.append("'"+emp_no+"', ");
			query1.append("null) ");
			
			pstmt = con.prepareStatement(query1.toString());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				con.commit();
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		disconnection();
		
    	
    	
    }
    
    // 데이터베이스 연결

    public static void connection() {

             try {

                      Class.forName("oracle.jdbc.driver.OracleDriver");

                      con = DriverManager.getConnection(url,user,password);


             } catch (ClassNotFoundException e) {
            	 e.printStackTrace();
             } catch (SQLException e) {
            	 e.printStackTrace();
             }

    }

    // 데이터베이스 연결 해제
    public static void disconnection() {

        try {

                 if(pstmt != null) pstmt.close();

                 if(rs != null) rs.close();

                 if(con != null) con.close();

        } catch (SQLException e) {
        	e.printStackTrace();
        }

    }
		
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new InqRegis();

	}

}
