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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class InqPost extends JPanel {
	
//	static String ino = InqAnsBoard.pno;
	
	private boolean isClicked = false;
	
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "asmr";
	private String password = "asmr";
	
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private ResultSetMetaData rsmd = null;
	
	InqPostButtonListener inqPostButtonListener;
	
	private JLabel vInq, vWrt, vWrtDttm, vTit, vCont;
	
	static JTextField xWrt, xWrtDttm;

	static JTextField xTit;
	
	static JTextArea xCont;
	
	private JButton update, ans, getBack;
	
	GridBagLayout gridbaglayout;
	GridBagConstraints gridbagconstraints;
	
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	private Color black = new Color(0,0,0);
	private Color red = new Color(217,0,27);
	
	public InqPost() {
		
		inqPostButtonListener = new InqPostButtonListener();
		
		gridbaglayout = new GridBagLayout();
		gridbagconstraints = new GridBagConstraints();
		
		vInq = new JLabel("문의답변게시물");
		vInq.setFont(new Font("나눔고딕", Font.BOLD, 24));
		
		vWrt = new JLabel("작성자");
		vWrt.setFont(new Font("나눔고딕", Font.PLAIN, 16));
		xWrt = new JTextField(20);
		xWrt.setEditable(false);
		
		vWrtDttm = new JLabel("작성일시");
		vWrtDttm.setFont(new Font("나눔고딕", Font.PLAIN, 16));
		xWrtDttm = new JTextField(20);
		xWrtDttm.setEditable(false);
		
		vTit = new JLabel("제목");
		vTit.setFont(new Font("나눔고딕", Font.PLAIN, 16));
		xTit = new JTextField(20);
		xTit.setEditable(false);
		
		vCont = new JLabel("내용");
		vCont.setFont(new Font("나눔고딕", Font.PLAIN, 16));
		xCont = new JTextArea(5,50);
		xCont.setEditable(false);
		
		
		update = new JButton("수정");
		update.setFont(new Font("나눔고딕", Font.BOLD, 16));
		update.setBackground(blue);
		update.setForeground(white);
		update.addActionListener(inqPostButtonListener);
		
		ans = new JButton("답변");
		ans.setFont(new Font("나눔고딕", Font.BOLD, 16));
		ans.setBackground(red);
		ans.setForeground(white);
		ans.addActionListener(inqPostButtonListener);
		
		
		getBack = new JButton("목록으로");
		getBack.setFont(new Font("나눔고딕", Font.BOLD, 16));
		getBack.setBackground(white);
		getBack.setForeground(black);
		getBack.addActionListener(inqPostButtonListener);
		
		InqPostView();
		
	}
	
	private void InqPostView() {
		setBackground(MainFrame.bgc);
		
		gridbagconstraints.anchor = GridBagConstraints.WEST;
		gridbagconstraints.ipadx = 7;
		
		gridbagconstraints.weightx=1.0;
		gridbagconstraints.weighty=1.0;
		
		setLayout(gridbaglayout);

		gridbagAdd(vInq, 0, 0, 1, 1);
		
		gridbagAdd(vWrt, 0, 1, 1, 1);
		gridbagAdd(xWrt, 2, 1, 1, 1);
		gridbagAdd(vWrtDttm, 3, 1, 1, 1);
		gridbagAdd(xWrtDttm, 4, 1, 2, 1);
		
		gridbagAdd(vTit, 0, 2, 1, 1);
		gridbagAdd(xTit, 2, 2, 1, 1);
		
		gridbagAdd(vCont, 0, 3, 1, 1);
		gridbagAdd(xCont, 2, 3, 6, 2);
		
		gridbagAdd(update, 4, 7, 1, 1);
		gridbagAdd(ans, 5, 7, 1, 1);
		gridbagAdd(getBack, 6, 7, 1, 1);
		
		gridbagconstraints.anchor = GridBagConstraints.CENTER;

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
	
    class InqPostButtonListener implements ActionListener{
    	
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource().equals(update)) {
				if(!isClicked) {
					isClicked = true;
					
					xTit.setEditable(true);
					xTit.requestFocus();
					xCont.setEditable(true);
					
					update.setText("저장");
					
					
//					String prePostTit = xTit.getText();
//					String prePostCont = xCont.getText();
					
//					JComponent[] changeStatusComps = {xTit,xCont};
//					for(JComponent cop: changeStatusComps) {
//						cop.setEnabled(true);
//					}
					
					
				}
				else {
					isClicked = false;
					int result = JOptionPane.showConfirmDialog(null, "해당 게시글을 수정하시겠습니까?", "게시글정보수정", JOptionPane.YES_NO_OPTION);
					if(result == JOptionPane.OK_OPTION) {
//						xTit.setEditable(true);
//						xTit.requestFocus();
//						xCont.setEditable(true);
						
						String newPostTit = xTit.getText();
						String newPostCont = xCont.getText();
						
						
						xTit.setEditable(false);
						xCont.setEditable(false);
						update.setText("수정");
						
						
						
						UpdateInqPost(newPostTit,newPostCont);
						
					}
				}
				
			}
			else if(e.getSource().equals(getBack)) {
				MainFrame.qnaBoardCase();
				
			}
			else if(e.getSource().equals(ans)) {
//				String pno = InqAnsBoard.pno;
//				
////				InqRegis.PostInq();
				
				MainFrame.ansCase();
//				PostAns(ino);
				
//				
				
				
		}
		
	}
		
    }
    
     void PostAns(String pno) {
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
     
     
     
     
     
     
     
    
    
    private void UpdateInqPost(String newPostTit, String newPostCont) {
//		
		StringBuffer query1 = new StringBuffer("UPDATE POST SET POST_TIT=?, POST_CONT=? WHERE POST_NO=? ");
		
		connection();
		
		try {
			pstmt = con.prepareStatement(query1.toString());
			pstmt.setString(1, newPostTit);
			pstmt.setString(2, newPostCont);
			pstmt.setString(3, InqAnsBoard.pno);
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

    public void connection() {

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
    public void disconnection() {

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
		new InqPost();
	}

}
