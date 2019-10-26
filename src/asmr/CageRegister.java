package asmr;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CageRegister extends JFrame {
	private JLabel vCageRegister,vCenterName,vCageSize;
	private JTextField xCenterName;
	private JComboBox<String> cbCageSize;
	private JButton register,cancel;
	
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
	
	public CageRegister(String cntrName) {
		gridBagLayout = new GridBagLayout();
		gridBagConstraints = new GridBagConstraints();
		
		cageRegisterButtonListener = new CageRegisterButtonListener();
		
//		vCageRegister = new JLabel("케이지등록");
		
		vCenterName = new JLabel("센터명");
		xCenterName = new JTextField(10);
		xCenterName.setEnabled(false);
		xCenterName.setText(cntrName);
		
		vCageSize = new JLabel("케이지크기");
		cbCageSize = new JComboBox<String>(cageSizeDiv);
		
		register = new JButton("등록");
		register.setBackground(blue);
		register.setForeground(white);
		register.addActionListener(cageRegisterButtonListener);
		cancel = new JButton("취소");
		cancel.addActionListener(cageRegisterButtonListener);
		
		JComponent[] vComps = {vCenterName,vCageSize};
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
		
		gridbagAdd(xCenterName, 1, 1, 2, 1);
		
		vCageSize.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
		gridbagAdd(vCageSize, 0, 2, 1, 1);
		
		gridbagAdd(cbCageSize, 1, 2, 2, 1);
		
		JComponent[] buttons = {register,cancel};
		ChangeFont(buttons, new Font("나눔고딕", Font.BOLD, 12));
		CombinePanel buttonPanel = new CombinePanel(buttons, 10,0);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(0,30,0,0));
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
		connection();
		
		String korCageSize = (String) cbCageSize.getSelectedItem();
		String engCageSize = null;
		
		switch(korCageSize) {
		case "대":
			engCageSize = "b";
			break;
		case "중":
			engCageSize = "m";
			break;
		case "소":	
			engCageSize = "s";
			break;
		}
		
		try {
			StringBuffer query = new StringBuffer("INSERT INTO CAGE ");
			query.append("SELECT CNTR_NO,MAX(CAGE_ORNU)+1 CAGE_ORNU, '"+engCageSize+"' ");
			query.append("FROM( ");
			query.append("	SELECT c1.CNTR_NO,NVL(c2.CAGE_ORNU,0) CAGE_ORNU ");
			query.append("	FROM( ");
			query.append("		SELECT * FROM CNTR ");
			query.append("		WHERE CNTR_NAME='서울서초보호센터') c1 LEFT OUTER JOIN CAGE c2 ");
			query.append("											ON c1.CNTR_NO = c2.CNTR_NO ");
			query.append(") ");
			query.append("GROUP BY CNTR_NO ");
			
			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				con.commit();
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		disconnection();
	}
	
	class CageRegisterButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource().equals(register)) {
				int result = JOptionPane.showConfirmDialog(null, "신규 케이지를 등록하시겠습니까?", "케이지 등록 확인", JOptionPane.YES_NO_OPTION);
				switch(result) {
				case JOptionPane.YES_OPTION:
					RegistCage();
					dispose();
				}
			}
			else if (e.getSource().equals(cancel)) {
				dispose();
			}
		}
		
	}
	
    private void ChangeFont(JComponent[] comps, Font font) {
    	for(JComponent comp: comps) {
    		comp.setFont(font);
    	}
    }
	
//	리스너 작업으로 인하여 main 메서드 삭제
	public static void main(String[] args) {
		new CageRegister("Test");
	}
}
