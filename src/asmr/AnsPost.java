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

public class AnsPost extends JPanel {
	
	private boolean isClicked = false;
	
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "asmr";
	private String password = "asmr";
	
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private ResultSetMetaData rsmd = null;
	
	AnsPostButtonListener ansPostButtonListener;
	
	private JLabel vAns, vWrt, vWrtDttm, vTit, vCont;
	
	static JTextField xWrt;

	static JTextField xWrtDttm;

	static JTextField xTit;
	
	static JTextArea xCont;
	
	private JButton update, getBack;
	
	GridBagLayout gridbaglayout;
	GridBagConstraints gridbagconstraints;
	
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	private Color black = new Color(0,0,0);
	
	AnsPost() {
		
		ansPostButtonListener = new AnsPostButtonListener();
		
		gridbaglayout = new GridBagLayout();
		gridbagconstraints = new GridBagConstraints();
		
		vAns = new JLabel("답변");
		vAns.setFont(new Font("나눔고딕", Font.BOLD, 24));
		
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
		update.addActionListener(ansPostButtonListener);
		
		getBack = new JButton("목록으로");
		getBack.setFont(new Font("나눔고딕", Font.BOLD, 16));
		getBack.setBackground(white);
		getBack.setForeground(black);
		getBack.addActionListener(ansPostButtonListener);
		
		AnsPostView();
		
	}
	
	private void AnsPostView() {
//		setBackground(MainFrame.bgc);
//		setTitle("답변게시글");	
		
		setBackground(MainFrame.bgc);
		
		gridbagconstraints.anchor = GridBagConstraints.WEST;
		gridbagconstraints.ipadx = 7;
		
		gridbagconstraints.weightx=1.0;
		gridbagconstraints.weighty=1.0;
		
		setLayout(gridbaglayout);

		gridbagAdd(vAns, 0, 0, 1, 1);
		
		gridbagAdd(vWrt, 0, 1, 1, 1);
		gridbagAdd(xWrt, 2, 1, 1, 1);
		gridbagAdd(vWrtDttm, 3, 1, 1, 1);
		gridbagAdd(xWrtDttm, 4, 1, 2, 1);
		
		gridbagAdd(vTit, 0, 2, 1, 1);
		gridbagAdd(xTit, 2, 2, 1, 1);
		
		gridbagAdd(vCont, 0, 3, 1, 1);
		gridbagAdd(xCont, 2, 3, 6, 2);
		
		gridbagAdd(update, 4, 7, 1, 1);
		gridbagAdd(getBack, 5, 7, 1, 1);
		
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
	
	class AnsPostButtonListener implements ActionListener{
		
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
						
						
						
						UpdateAnsPost(newPostTit,newPostCont);
						
					}
				}
				
			}
			else if(e.getSource().equals(getBack)) {
				MainFrame.qnaBoardCase();
				
			}
		}
		
	}
	
    private void UpdateAnsPost(String newPostTit, String newPostCont) {
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
		new AnsPost();

	}

}
