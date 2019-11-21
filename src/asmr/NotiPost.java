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
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class NotiPost extends JPanel {
	private boolean isClicked = false;
	
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "asmr";
	private String password = "asmr";
	
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private ResultSetMetaData rsmd = null;
	
	private JLabel vNoti, vWrt, vWrtDttm, vTit, vCont;
	
	protected static JTextField xWrt;

	protected static JTextField xWrtDttm;

	protected static JTextField xTit;
	
	protected static JTextArea xCont;
	
	private JButton update, getBack;
	
	GridBagLayout gridbaglayout;
	GridBagConstraints gridbagconstraints;
	
	NotiPostButtonListener notiPostButtonListener;
	
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	private Color black = new Color(0,0,0);
	
	public NotiPost() {
		
		notiPostButtonListener = new NotiPostButtonListener();
		
		gridbaglayout = new GridBagLayout();
		gridbagconstraints = new GridBagConstraints();
		
		vNoti = new JLabel("��������");
		vNoti.setFont(new Font("�������", Font.BOLD, 24));
		
		vWrt = new JLabel("�ۼ���");
		vWrt.setFont(new Font("�������", Font.PLAIN, 16));
		xWrt = new JTextField(20);
		xWrt.setEditable(false);
		
		vWrtDttm = new JLabel("�ۼ��Ͻ�");
		vWrtDttm.setFont(new Font("�������", Font.PLAIN, 16));
		xWrtDttm = new JTextField(20);
		xWrtDttm.setEditable(false);
		
		vTit = new JLabel("����");
		vTit.setFont(new Font("�������", Font.PLAIN, 16));
		xTit = new JTextField(20);
		xTit.setEditable(false);
		
		vCont = new JLabel("����");
		vCont.setFont(new Font("�������", Font.PLAIN, 16));
		xCont = new JTextArea(5,50);
		xCont.setEditable(false);
		
		
		update = new JButton("����");
		update.setFont(new Font("�������", Font.BOLD, 16));
		update.setBackground(blue);
		update.setForeground(white);
		update.addActionListener(notiPostButtonListener);
		
		getBack = new JButton("�������");
		getBack.setFont(new Font("�������", Font.BOLD, 16));
		getBack.setBackground(white);
		getBack.setForeground(black);
		getBack.addActionListener(notiPostButtonListener);
		
		
		JComponent[] vComps = {vWrt, vWrtDttm, vTit, vCont};
		ChangeFont(vComps, new Font("�������", Font.PLAIN, 16));
		
		NotiPostView();
		
	}
	
	private void NotiPostView() {
		
		setBackground(MainFrame.bgc);
		
//		setTitle("�Ű����_�Ϲݼ���");	
		
		gridbagconstraints.anchor = GridBagConstraints.WEST;
		gridbagconstraints.ipadx = 7;
		
		gridbagconstraints.weightx=1.0;
		gridbagconstraints.weighty=1.0;
		
		setLayout(gridbaglayout);

		gridbagAdd(vNoti, 0, 0, 1, 1);
		
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
	      //���� ���� �� gridx, gridy���� 0 			
				
		gridbagconstraints.gridwidth  = w;	//����	
		gridbagconstraints.gridheight = h;	//����	
	     			
	      			
	    gridbaglayout.setConstraints(c, gridbagconstraints); //������Ʈ�� ������Ʈ ��ġ+ũ�� ������ ���� GridBagLayout�� ��ġ			
				
	   add(c);			
				
	   }
	
	private void ChangeFont(JComponent[] comps, Font font) {
    	for(JComponent comp: comps) {
    		comp.setFont(font);
    	}
    }
	
    class NotiPostButtonListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource().equals(update)) {
				if(!isClicked) {
					isClicked = true;
					
					xTit.setEditable(true);
					xTit.requestFocus();
					xCont.setEditable(true);
					
					update.setText("����");
					
					
//					String prePostTit = xTit.getText();
//					String prePostCont = xCont.getText();
					
//					JComponent[] changeStatusComps = {xTit,xCont};
//					for(JComponent cop: changeStatusComps) {
//						cop.setEnabled(true);
//					}
					
					
				}
				else {
					isClicked = false;
					int result = JOptionPane.showConfirmDialog(null, "���������� �����Ͻðڽ��ϱ�?", "������������", JOptionPane.YES_NO_OPTION);
					if(result == JOptionPane.OK_OPTION) {
//						xTit.setEditable(true);
//						xTit.requestFocus();
//						xCont.setEditable(true);
						
						String newPostTit = xTit.getText();
						String newPostCont = xCont.getText();
						
						
						xTit.setEditable(false);
						xCont.setEditable(false);
						update.setText("����");
						
						
						
						UpdateNotiPost(newPostTit,newPostCont);
						
					}
				}
				
				
					
			}
			else if(e.getSource().equals(getBack)) {
				MainFrame.notiBoardCase();
			}
		}
		
	}
    
    
    private void UpdateNotiPost(String newPostTit, String newPostCont) {
//    		
    		StringBuffer query1 = new StringBuffer("UPDATE POST SET POST_TIT=?, POST_CONT=? WHERE POST_NO=? ");
    		
    		connection();
    		
    		try {
    			pstmt = con.prepareStatement(query1.toString());
    			pstmt.setString(1, newPostTit);
    			pstmt.setString(2, newPostCont);
    			pstmt.setString(3, NotiBoard.pno);
    			rs = pstmt.executeQuery();
    			
    			if(rs.next()) {
    				con.commit();
    			}
    		}catch(SQLException e) {	
    			e.printStackTrace();
    		}
    		
    		disconnection();
    	}
    // �����ͺ��̽� ����

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

    // �����ͺ��̽� ���� ����
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
		
		new NotiPost();

	}

}
