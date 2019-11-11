package asmr;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NewCageRegister extends JFrame{
	private JLabel vCageRegister,vCenterName,vCageSize,vCageBig,vCageMid,vCageSmall,vCageBigCount,vCageMidCount,vCageSmallCount;
	private JTextField xCenterName,xCageBig,xCageMid,xCageSmall;
	private JComboBox<String> cbCageSize;
	private JButton register,cancel;
	
	private String cntrNo;
	
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "asmr";
	private String password = "asmr";
	
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private ResultSetMetaData rsmd = null;
	
	private final String[] cageSizeDiv = {"대","중","소"};
	
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	
	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;
	
	CageRegisterButtonListener cageRegisterButtonListener;
	CageNumFocusListener cageNumFocusListener;
	
	public NewCageRegister(String cntrName,String cntrNo) {
		gridBagLayout = new GridBagLayout();
		gridBagConstraints = new GridBagConstraints();
		
		cageRegisterButtonListener = new CageRegisterButtonListener();
		cageNumFocusListener = new CageNumFocusListener();
		
		this.cntrNo = cntrNo;
		
//		vCageRegister = new JLabel("케이지등록");
		
		vCenterName = new JLabel("센터명");
		xCenterName = new JTextField(10);
		xCenterName.setEditable(false);
		xCenterName.setText(cntrName);
		
		vCageSize = new JLabel("케이지크기");
		cbCageSize = new JComboBox<String>(cageSizeDiv);
		
		//대형
		vCageBig = new JLabel("대형");
		xCageBig = new JTextField(2);
		xCageBig.setText("0");
		xCageBig.addFocusListener(cageNumFocusListener);
		vCageBigCount = new JLabel("개");
		
		//중형
		vCageMid = new JLabel("중형");
		xCageMid = new JTextField(2);
		xCageMid.setText("0");
		xCageMid.addFocusListener(cageNumFocusListener);
		vCageMidCount = new JLabel("개");
		
		//소형
		vCageSmall = new JLabel("소형");
		xCageSmall = new JTextField(2);
		xCageSmall.setText("0");
		xCageSmall.addFocusListener(cageNumFocusListener);
		vCageSmallCount = new JLabel("개");
		
		register = new JButton("등록");
		register.setBackground(blue);
		register.setForeground(white);
		register.addActionListener(cageRegisterButtonListener);
		cancel = new JButton("취소");
		cancel.addActionListener(cageRegisterButtonListener);
		
		JComponent[] vComps = {vCenterName,vCageSize,vCageBig,vCageMid,vCageSmall,vCageBigCount,vCageMidCount,vCageSmallCount};
		ChangeFont(vComps, new Font("나눔고딕", Font.PLAIN, 16));
		
		CageRegisterView();
	}

	private void CageRegisterView() {
		setLayout(gridBagLayout);
		setTitle("케이지등록");

		
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.ipadx = 7;
		
		gridBagConstraints.weightx=1.0;
		gridBagConstraints.weighty=1.0;
		
//		gridbagAdd(vCageRegister, 0, 0, 1, 1);
		
		vCenterName.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
		gridbagAdd(vCenterName, 0, 1, 1, 1);
		
		gridbagAdd(xCenterName, 1, 1, 3, 1);
		
//		vCageBig.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
		
		Component[] copsBig = {vCageBig,xCageBig,vCageBigCount};
		
		CombinePanel cageBigPanel = new CombinePanel(copsBig, 0, 10);
		gridbagAdd(cageBigPanel, 0, 2, 1, 1);
		
		Component[] copsMid = {vCageMid,xCageMid,vCageMidCount};
		
		CombinePanel cageMidPanel = new CombinePanel(copsMid, 0, 10);
		gridbagAdd(cageMidPanel, 1, 2, 1, 1);
		
		Component[] copsSmall = {vCageSmall,xCageSmall,vCageSmallCount};
		
		CombinePanel cageSmallPanel = new CombinePanel(copsSmall, 0, 10);
		gridbagAdd(cageSmallPanel, 2, 2, 1, 1);
		
		
//		gridbagAdd(vCageBig, 0, 2, 1, 1);
//		gridbagAdd(xCageBig, 1, 2, 1, 1);
//		gridbagAdd(vCageBigCount, 2, 2, 1, 1);
		
//		gridbagAdd(cbCageSize, 1, 2, 2, 1);
		
		JComponent[] buttons = {register,cancel};
		ChangeFont(buttons, new Font("나눔고딕", Font.BOLD, 12));
		CombinePanel buttonPanel = new CombinePanel(buttons, 10,0);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(0,45,0,0));
		gridbagAdd(buttonPanel, 0, 6, 6, 1);
		
		
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
	}
	
	private void gridbagAdd(Component c, int x, int y, int w , int h) {
		
		gridBagConstraints.gridx = x;
		gridBagConstraints.gridy = y;
		gridBagConstraints.gridwidth = w;
		gridBagConstraints.gridheight = h;
		
		gridBagLayout.setConstraints(c, gridBagConstraints);
		
		add(c);
	}
	
	
	class CombinePanel extends JPanel {
		//컴포넌트 1, 컴포넌트 2, 패널 구성시 좌,우 margin 공간을 없애기 위한 Flag
		public CombinePanel(Component[] cops, int borderWidth, int borderHeight) {
			//Margin이 필요하지 않을 때
			
			setLayout(new FlowLayout(FlowLayout.LEFT,borderWidth,borderHeight));
			
			for (Component c: cops) {
				add(c);
			}
		}
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

	
	private void RegistCage() {
		
		
		// 신규케이지 등록 관련
		int bigCageNum = Integer.parseInt(xCageBig.getText());
		int midCageNum = Integer.parseInt(xCageMid.getText());
		int smallCageNum = Integer.parseInt(xCageSmall.getText());
		
		connection();
		
		try {
			String query3 = null;
			
			if(bigCageNum >= 1 || midCageNum >= 1 || smallCageNum >= 1) {
				query3 = RegistCage(bigCageNum,midCageNum,smallCageNum);
			
				System.out.println(query3);
				
				
				pstmt = con.prepareStatement(query3);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					con.commit();
				}	
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		disconnection();
	}
	
private String RegistCage(int bigCageNum, int midCageNum, int smallCageNum) {
		
		bigCageNum+=1;
		midCageNum+=1;
		smallCageNum+=1;
		
		StringBuffer query3 = new StringBuffer();
		
		if (bigCageNum != 1 && midCageNum != 1 && smallCageNum != 1) {
				
			query3.append("INSERT INTO CAGE ");
			query3.append("SELECT '"+cntrNo+"' CNTR_NO, ");
			query3.append("			ROWNUM+(SELECT NVL(MAX(CAGE_ORNU),0) FROM CAGE WHERE CNTR_NO='"+cntrNo+"') CAGE_ORNU, ");
			query3.append("		  	a.CAGE_SIZE ");
			query3.append("FROM( ");
			query3.append("SELECT LEVEL,'b' CAGE_SIZE FROM DUAL CONNECT BY LEVEL<'"+bigCageNum+"' ");
			query3.append("UNION ALL ");
			query3.append("SELECT LEVEL,'m' CAGE_SIZE FROM DUAL CONNECT BY LEVEL<'"+midCageNum+"' ");
			query3.append("UNION ALL ");
			query3.append("SELECT LEVEL,'s' CAGE_SIZE FROM DUAL CONNECT BY LEVEL<'"+smallCageNum+"') a ");


		}
		else if(bigCageNum==1&&midCageNum>2&&smallCageNum>2) {
			query3.append("INSERT INTO CAGE ");
			query3.append("SELECT '"+cntrNo+"' CNTR_NO, ");
			query3.append("			ROWNUM+(SELECT NVL(MAX(CAGE_ORNU),0) FROM CAGE WHERE CNTR_NO='"+cntrNo+"') CAGE_ORNU, ");
			query3.append("		  	a.CAGE_SIZE ");
			query3.append("FROM( ");
//			query3.append("SELECT LEVEL,'b' CAGE_SIZE FROM DUAL CONNECT BY LEVEL<'"+bigCageNum+"' ");
//			query3.append("UNION ALL ");
			query3.append("SELECT LEVEL,'m' CAGE_SIZE FROM DUAL CONNECT BY LEVEL<'"+midCageNum+"' ");
			query3.append("UNION ALL ");
			query3.append("SELECT LEVEL,'s' CAGE_SIZE FROM DUAL CONNECT BY LEVEL<'"+smallCageNum+"') a ");
		}
		
		else if(midCageNum==1&&bigCageNum>2&&smallCageNum>2) {
			query3.append("INSERT INTO CAGE ");
			query3.append("SELECT '"+cntrNo+"' CNTR_NO, ");
			query3.append("			ROWNUM+(SELECT NVL(MAX(CAGE_ORNU),0) FROM CAGE WHERE CNTR_NO='"+cntrNo+"') CAGE_ORNU, ");
			query3.append("		  	a.CAGE_SIZE ");
			query3.append("FROM( ");
			query3.append("SELECT LEVEL,'b' CAGE_SIZE FROM DUAL CONNECT BY LEVEL<'"+bigCageNum+"' ");
			query3.append("UNION ALL ");
//			query3.append("SELECT LEVEL,'m' CAGE_SIZE FROM DUAL CONNECT BY LEVEL<'"+midCageNum+"' ");
//			query3.append("UNION ALL ");
			query3.append("SELECT LEVEL,'s' CAGE_SIZE FROM DUAL CONNECT BY LEVEL<'"+smallCageNum+"') a ");
		}
		
		else if(smallCageNum==1&&bigCageNum>2&&midCageNum>2) {
			query3.append("INSERT INTO CAGE ");
			query3.append("SELECT '"+cntrNo+"' CNTR_NO, ");
			query3.append("			ROWNUM+(SELECT NVL(MAX(CAGE_ORNU),0) FROM CAGE WHERE CNTR_NO='"+cntrNo+"') CAGE_ORNU, ");
			query3.append("		  	a.CAGE_SIZE ");
			query3.append("FROM( ");
			query3.append("SELECT LEVEL,'b' CAGE_SIZE FROM DUAL CONNECT BY LEVEL<'"+bigCageNum+"' ");
			query3.append("UNION ALL ");
			query3.append("SELECT LEVEL,'m' CAGE_SIZE FROM DUAL CONNECT BY LEVEL<'"+midCageNum+"') a ");
//			query3.append("UNION ALL ");
//			query3.append("SELECT LEVEL,'s' CAGE_SIZE FROM DUAL CONNECT BY LEVEL<'"+smallCageNum+"') a ");
		}
		
		else if(bigCageNum==1 && midCageNum==1 && smallCageNum>2) {
			query3.append("INSERT INTO CAGE ");
			query3.append("SELECT '"+cntrNo+"' CNTR_NO, ");
			query3.append("			ROWNUM+(SELECT NVL(MAX(CAGE_ORNU),0) FROM CAGE WHERE CNTR_NO='"+cntrNo+"') CAGE_ORNU, ");
			query3.append("		  	a.CAGE_SIZE ");
			query3.append("FROM( ");
//			query3.append("SELECT LEVEL,'b' CAGE_SIZE FROM DUAL CONNECT BY LEVEL<'"+bigCageNum+"' ");
//			query3.append("UNION ALL ");
//			query3.append("SELECT LEVEL,'m' CAGE_SIZE FROM DUAL CONNECT BY LEVEL<'"+midCageNum+"' ");
//			query3.append("UNION ALL ");
			query3.append("SELECT LEVEL,'s' CAGE_SIZE FROM DUAL CONNECT BY LEVEL<'"+smallCageNum+"') a ");
		}
		
		else if(bigCageNum==1 && smallCageNum==1 && midCageNum>2) {
			query3.append("INSERT INTO CAGE ");
			query3.append("SELECT '"+cntrNo+"' CNTR_NO, ");
			query3.append("			ROWNUM+(SELECT NVL(MAX(CAGE_ORNU),0) FROM CAGE WHERE CNTR_NO='"+cntrNo+"') CAGE_ORNU, ");
			query3.append("		  	a.CAGE_SIZE ");
			query3.append("FROM( ");
//			query3.append("SELECT LEVEL,'b' CAGE_SIZE FROM DUAL CONNECT BY LEVEL<'"+bigCageNum+"' ");
//			query3.append("UNION ALL ");
			query3.append("SELECT LEVEL,'m' CAGE_SIZE FROM DUAL CONNECT BY LEVEL<'"+midCageNum+"') a ");
//			query3.append("UNION ALL ");
//			query3.append("SELECT LEVEL,'s' CAGE_SIZE FROM DUAL CONNECT BY LEVEL<'"+smallCageNum+"') a ");
		}
		
		else if(midCageNum==1 && smallCageNum==1 && bigCageNum>2) {
			query3.append("INSERT INTO CAGE ");
			query3.append("SELECT '"+cntrNo+"' CNTR_NO, ");
			query3.append("			ROWNUM+(SELECT NVL(MAX(CAGE_ORNU),0) FROM CAGE WHERE CNTR_NO='"+cntrNo+"') CAGE_ORNU, ");
			query3.append("		  	a.CAGE_SIZE ");
			query3.append("FROM( ");
			query3.append("SELECT LEVEL,'b' CAGE_SIZE FROM DUAL CONNECT BY LEVEL<'"+bigCageNum+"') a ");
//			query3.append("UNION ALL ");
//			query3.append("SELECT LEVEL,'m' CAGE_SIZE FROM DUAL CONNECT BY LEVEL<'"+midCageNum+"' ");
//			query3.append("UNION ALL ");
//			query3.append("SELECT LEVEL,'s' CAGE_SIZE FROM DUAL CONNECT BY LEVEL<'"+smallCageNum+"') a ");

		}
		
		String result = query3.toString();
		return result;
	}
	
	class CageRegisterButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource().equals(register)) {
				String bigNum = xCageBig.getText().trim();
				String midNum = xCageMid.getText().trim();
				String smallNum = xCageSmall.getText().trim();
				
				int result = JOptionPane.showConfirmDialog(null, "신규 케이지를 등록하시겠습니까?", "케이지 등록 확인", JOptionPane.YES_NO_OPTION);
				switch(result) {
				case JOptionPane.YES_OPTION:
					if(bigNum.isEmpty()||midNum.isEmpty()||smallNum.isEmpty()) {
						JOptionPane.showMessageDialog(null, "케이지의 수량을 입력하세요", "메시지", JOptionPane.ERROR_MESSAGE);
					}
						RegistCage();
						dispose();
				}
			}
			else if (e.getSource().equals(cancel)) {
				dispose();
			}
		}
		
	}
	
	class CageNumFocusListener implements FocusListener{

		@Override
		public void focusGained(FocusEvent e) {
			// TODO Auto-generated method stub
			JTextField target = (JTextField)e.getSource();
			if(target.getText().equals("0"))
				target.setText("");
		}

		@Override
		public void focusLost(FocusEvent e) {
			// TODO Auto-generated method stub
			JTextField target = (JTextField)e.getSource();
			if(target.getText().isEmpty())
				target.setText("0");
		}
		
	}
	
    private void ChangeFont(JComponent[] comps, Font font) {
    	for(JComponent comp: comps) {
    		comp.setFont(font);
    	}
    }
    public static void main(String[] args) {
		new NewCageRegister(null,null);
	}
}
