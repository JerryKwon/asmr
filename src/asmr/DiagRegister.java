package asmr;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import com.toedter.calendar.JDateChooser;


public class DiagRegister extends JFrame{
	private JLabel vDiagRegister, vDiagDate, vDiagType, vIndiResult, vIndiVtrnName, vOudiResult, vHospName, vDisease, vInfecWhet, vCureType, vHsptzDate, vDschDate,vDeathType, vDeathReason, vDiagContent; 
	private JTextField xDiagDate, xIndiVtrnName, xHospName, xDisease, xInfecWhet, xCureType, xHsptzDate, xDschDate, xDeathType, xDeathReason;
	private JComboBox<String> cbDiagType, cbIndiResult, cbOudiResult, cbInfectWhet, cbCureType, cbDeathType;
	private JButton imageButton1, imageButton2, search, register, cancel;
	private JTextArea xDiagContent;
	private BufferedImage buttonIcon;
	private JDateChooser diagDateChooser, hsptzDateChooser, dschDateChooser;
	
	private String protNo;
	private String vtrnDate = null;
	
	private JScrollPane diagContentScroll;
	
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "asmr";
	private String password = "asmr";
	
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private ResultSetMetaData rsmd = null;
	
	private String[] diagTypeDiv = {"내진","외진"};
	private String[] indiResultDiv = {"좋음","보통","나쁨"};
	private String[] oudiResultDiv = {"치료","사망"};
	private String[] infectWhetDiv = {"Y","N"};
	private String[] cureTypeDiv = {"통원","입원"};
	private String[] deathTypeDiv = {"자연사","안락사"};
	
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	
	DiagRegisterButtonListener diagRegisterButtonListener;
	DiagTypeItemListener diagTypeItemListener;
	OudiResultItemListener oudiResultItemListener;
	CureTypeItemListener cureTypeItemListener;
	
	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;
	
	public DiagRegister(String protNo) throws IOException {
		gridBagLayout = new GridBagLayout();		
		gridBagConstraints = new GridBagConstraints();
	
		diagRegisterButtonListener = new DiagRegisterButtonListener();
		diagTypeItemListener = new DiagTypeItemListener();
		oudiResultItemListener = new OudiResultItemListener();
		cureTypeItemListener = new CureTypeItemListener();
		
		this.protNo = protNo;
		
//		vDiagRegister = new JLabel("진료등록");
		
		vDiagDate = new JLabel("진료일자");
		xDiagDate = new JTextField(10);
		
		LocalDate now = LocalDate.now();
		Date date = Date.valueOf(now);
		diagDateChooser = new JDateChooser(date, "yyyy-MM-dd");
		JTextField textField= (JTextField) diagDateChooser.getDateEditor().getUiComponent();
		textField.setEditable(false);
		
		vDiagType = new JLabel("진료구분");
		cbDiagType = new JComboBox<String>(diagTypeDiv);
		cbDiagType.addItemListener(diagTypeItemListener);
		
		vIndiResult = new JLabel("내진결과");
		cbIndiResult = new JComboBox<String>(indiResultDiv);
		
		vIndiVtrnName = new JLabel("내진수의사명");
		xIndiVtrnName =  new JTextField(10);
		xIndiVtrnName.setEditable(false);
		search = new JButton("검색");
		search.setBackground(blue);
		search.setForeground(white);
		search.addActionListener(diagRegisterButtonListener);
		
		
		vOudiResult = new JLabel("외진결과");
		cbOudiResult = new JComboBox<String>(oudiResultDiv);
		cbOudiResult.addItemListener(oudiResultItemListener);
		
		vHospName = new JLabel("병원명");
		xHospName = new JTextField(10);
		
		vDisease = new JLabel("병명");
		xDisease = new JTextField(10);
		
		vInfecWhet = new JLabel("전염여부");
		cbInfectWhet = new JComboBox<String>(infectWhetDiv);
		
		vCureType = new JLabel("치료구분");
		cbCureType = new JComboBox<String>(cureTypeDiv);
		cbCureType.addItemListener(cureTypeItemListener);
		
		vHsptzDate = new JLabel("입원일자");
		hsptzDateChooser = new JDateChooser(date,"yyyy-MM-dd");
				
		vDschDate = new JLabel("퇴원일자");
		dschDateChooser = new JDateChooser(null,"yyyy-MM-dd");

		vDeathType = new JLabel("사망구분");
		cbDeathType = new JComboBox<String>(deathTypeDiv);
		
		vDeathReason = new JLabel("사망사유");
		xDeathReason = new JTextField(10);
		
		vDiagContent = new JLabel("진료내용");
		xDiagContent = new JTextArea();
		xDiagContent.setLineWrap(true);
		diagContentScroll = new JScrollPane(xDiagContent);
		diagContentScroll.setPreferredSize(new Dimension(400,150));
		diagContentScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		register = new JButton("등록");
		register.setBackground(blue);
		register.setForeground(white);
		register.addActionListener(diagRegisterButtonListener);
		
		cancel = new JButton("취소");
		cancel.addActionListener(diagRegisterButtonListener);
		
		JComponent[] vComps = {vDiagDate, vDiagType, vIndiResult, vIndiVtrnName, vOudiResult, vHospName, vDisease, vInfecWhet, vCureType, vHsptzDate, vDschDate,vDeathType, vDeathReason, vDiagContent};
		ChangeFont(vComps, new Font("나눔고딕", Font.PLAIN, 16));
		
		JComponent[] bComps = {search, register, cancel};
		ChangeFont(bComps, new Font("나눔고딕", Font.BOLD, 12));
		activateIndi();
		
		DiagRegisterView();
	}
	
	private void DiagRegisterView() {
		setLayout(gridBagLayout);
		setTitle("진료등록");
		
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.ipadx = 7;
		
		gridBagConstraints.weightx=1.0;
		gridBagConstraints.weighty=1.0;
		
//		gridbagAdd(vDiagRegister, 0, 0, 1, 1);
		
		gridbagAdd(vDiagDate, 0, 1, 1, 1);
		gridbagAdd(diagDateChooser, 1, 1, 1, 1);
		
		gridbagAdd(vDiagType, 2, 1, 1, 1);
		gridbagAdd(cbDiagType, 3, 1, 1, 1);
		
		gridbagAdd(vIndiResult, 0, 2, 1, 1);
		gridbagAdd(cbIndiResult, 1, 2, 1, 1);
		
		gridbagAdd(vIndiVtrnName, 2, 2, 1, 1);
		gridbagAdd(xIndiVtrnName, 3, 2, 1, 1);
		gridbagAdd(search, 4, 2, 1, 1);
		
		gridbagAdd(vOudiResult, 0, 3, 1, 1);
		gridbagAdd(cbOudiResult, 1, 3, 1, 1);
		
		gridbagAdd(vHospName, 2, 3, 1, 1);
		gridbagAdd(xHospName, 3, 3, 1, 1);
		
		gridbagAdd(vDisease, 0, 4, 1, 1);
		gridbagAdd(xDisease, 1, 4, 1, 1);
		
		gridbagAdd(vInfecWhet, 2, 4, 1, 1);
		gridbagAdd(cbInfectWhet, 3, 4, 1, 1);
		
		gridbagAdd(vCureType, 0, 5, 1, 1);
		gridbagAdd(cbCureType, 1, 5, 1, 1);
		
		gridbagAdd(vHsptzDate, 2, 5, 1, 1);
		gridbagAdd(hsptzDateChooser, 3, 5, 1, 1);
//		gridbagAdd(xHsptzDate, 3, 5, 1, 1);
		
		gridbagAdd(vDschDate, 2, 6, 1, 1);
		gridbagAdd(dschDateChooser, 3, 6, 1, 1);
//		Component[] cops2 = {xDschDate, imageButton2};
//		CombinePanel dschDatePanel = new CombinePanel(cops2, false);
//		gridbagAdd(dschDatePanel, 3, 6, 1, 1);
		
		gridbagAdd(vDeathType, 0, 7, 1, 1);
		gridbagAdd(cbDeathType, 1, 7, 1, 1);
		
		gridbagAdd(vDeathReason, 2, 7, 1, 1);
		gridbagAdd(xDeathReason, 3, 7, 1, 1);
		
		gridbagAdd(vDiagContent, 0, 8, 1, 1);
		gridbagAdd(diagContentScroll, 1, 8, 4, 1);
		
		Component[] cops3 = {register, cancel};
		CombinePanel buttonPanel = new CombinePanel(cops3,true);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(10,175,0,0));
		gridbagAdd(buttonPanel, 0, 9, 4, 1);
		
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
		public CombinePanel(Component[] cops, boolean isBorder) {
			//Margin이 필요하지 않을 때
			if(!isBorder) {
				setLayout(new FlowLayout(FlowLayout.LEFT,0,0));	
			}
			else {
				setLayout(new FlowLayout(FlowLayout.LEFT,15,0));	
			}
			for (Component c: cops) {
				add(c);
			}
		}
	}
	
	class DiagRegisterButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource().equals(search)) {
				VtrnSearch vtrnSearch = new VtrnSearch(xIndiVtrnName);
				vtrnSearch.addWindowListener(new WindowAdapter() {

					@Override
					public void windowClosed(WindowEvent e) {
						// TODO Auto-generated method stub
						super.windowClosed(e);
						vtrnDate = vtrnSearch.getVtrnBdate();
					}
					
				});
			}
			if(e.getSource().equals(register)) {
				int result = JOptionPane.showConfirmDialog(null, "진료를 등록하시겠습니까?", "진료등록", JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.YES_OPTION) {
					SelectCase();
					dispose();
				}
			}
			if(e.getSource().equals(cancel)) {
				dispose();
			}
		}
		
	}
	
	private void SelectCase() {
		String diagType = (String)cbDiagType.getSelectedItem();
		
		if(diagType == "내진") {
			String engDiagType = "i";
			RegistDiag1(engDiagType);
		}
		else {
			//외진
			String engDiagType = "o";
			String oudiRes = (String) cbOudiResult.getSelectedItem();
			
			if(oudiRes == "치료") {
				String engOudiRes = "c";
				
				String cureType = (String) cbCureType.getSelectedItem();
				
				if(cureType == "통원") {
					String engCureType = "v";
					RegistDiag2(engDiagType,engOudiRes,engCureType);
				}else {
					//입원
					String engCureType = "a";
					RegistDiag3(engDiagType,engOudiRes,engCureType);
				}
			}else {
				//사망
				String engOudiRes = "d";
				
				String deathType = (String) cbDeathType.getSelectedItem();
				String engDeathType = null;
				switch(deathType) {
				case "자연사":
					engDeathType="n";
					break;
				case "안락사":
					engDeathType="e";
					break;
				}
				//사망, 
				RegistDiag4(engDiagType,engOudiRes,engDeathType);
				//해당 동물의 보호를 종료시키는 함수
				FinishProt();
 			}
		}
	}
	
	//내진등록
	private void RegistDiag1(String diagType) {
		connection();
		
		String diagDate = ((JTextField)diagDateChooser.getDateEditor().getUiComponent()).getText();
		String vtrnName = xIndiVtrnName.getText();
		String diagContent = xDiagContent.getText();
		
		StringBuffer query = new StringBuffer("INSERT INTO DIAG(PROT_NO,DIAG_ORNU,DIAG_DATE,DIAG_TP,DIAG_CONT,VTRN_NO) ");
		query.append("SELECT '"+protNo+"' PROT_NO, ");
		query.append("	(SELECT NVL(DIAG_ORNU,0)+1 ");
		query.append("	FROM( ");
		query.append("		SELECT /*+INDEX_DESC(DIAG DIAG_PK)*/ MAX(DIAG_ORNU) DIAG_ORNU FROM DIAG ");
		query.append("	WHERE PROT_NO='"+protNo+"')) DIAG_ORNU, ");
		query.append("	to_date('"+diagDate+"','YYYY-MM-DD') DIAG_DATE, ");
		query.append("	'"+diagType+"' DIAG_TP, ");
		query.append("	'"+diagContent+"' DIAG_CONT, ");
		query.append("	(SELECT EMP_NO FROM EMP WHERE EMP_NAME='"+vtrnName+"' AND BRTH_YEAR_MNTH_DAY=to_date('"+vtrnDate+"','YYYY-MM-DD')) VTRN_NO ");
		query.append("FROM DUAL ");
		
		try {
			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				con.commit();
		}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		disconnection();
	};
	

	//외진-치료-통원
	private void RegistDiag2(String diagType,String oudiRes,String cureType) {
		
		String diagDate = ((JTextField)diagDateChooser.getDateEditor().getUiComponent()).getText();
		String diagContent = xDiagContent.getText();
		String hospName = xHospName.getText();
		String dise = xDisease.getText();
		String infectWhet = (String)cbInfectWhet.getSelectedItem();
		
		StringBuffer query = new StringBuffer("INSERT INTO DIAG(PROT_NO,DIAG_ORNU,DIAG_DATE,DIAG_TP,DIAG_CONT,HOSP_NAME,OUDI_RES,DISE,INFEC_WHET,CURE_TP) ");
		query.append("SELECT '"+protNo+"' PROT_NO, ");
		query.append("	(SELECT NVL(DIAG_ORNU,0)+1 ");
		query.append("	FROM( ");
		query.append("		SELECT /*+INDEX_DESC(DIAG DIAG_PK)*/ MAX(DIAG_ORNU) DIAG_ORNU FROM DIAG ");
		query.append("	WHERE PROT_NO='"+protNo+"')) DIAG_ORNU, ");
		query.append("	to_date('"+diagDate+"','YYYY-MM-DD') DIAG_DATE, ");
		query.append("	'"+diagType+"' DIAG_TP, ");
		query.append("	'"+diagContent+"' DIAG_CONT, ");
		query.append("	'"+hospName+"' HOSP_NAME, ");
		query.append("	'"+oudiRes+"' OUDI_RES, ");
		query.append("	'"+dise+"' DISE, ");
		query.append("	'"+infectWhet+"' INFEC_WHET, ");
		query.append("	'"+cureType+"' CURE_TP ");
		query.append("FROM DUAL ");
	
		connection();
		
		try {
			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				con.commit();
		}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		disconnection();
		
	};
	

	//외진-치료-입원
	private void RegistDiag3(String diagType,String oudiRes,String cureType) {
		
		String diagDate = ((JTextField)diagDateChooser.getDateEditor().getUiComponent()).getText();
		String diagContent = xDiagContent.getText();
		String hospName = xHospName.getText();
		String dise = xDisease.getText();
		String infectWhet = (String)cbInfectWhet.getSelectedItem();
		String hsptzDate= ((JTextField)hsptzDateChooser.getDateEditor().getUiComponent()).getText();
		String dschDate= ((JTextField)dschDateChooser.getDateEditor().getUiComponent()).getText();
		
		StringBuffer query = new StringBuffer("INSERT INTO DIAG(PROT_NO,DIAG_ORNU,DIAG_DATE,DIAG_TP,DIAG_CONT,HOSP_NAME,OUDI_RES,DISE,INFEC_WHET,CURE_TP,HSPTZ_DATE,DSCH_DATE) ");
		query.append("SELECT '"+protNo+"' PROT_NO, ");
		query.append("	(SELECT NVL(DIAG_ORNU,0)+1 ");
		query.append("	FROM( ");
		query.append("		SELECT /*+INDEX_DESC(DIAG DIAG_PK)*/ MAX(DIAG_ORNU) DIAG_ORNU FROM DIAG ");
		query.append("	WHERE PROT_NO='"+protNo+"')) DIAG_ORNU, ");
		query.append("	to_date('"+diagDate+"','YYYY-MM-DD') DIAG_DATE, ");
		query.append("	'"+diagType+"' DIAG_TP, ");
		query.append("	'"+diagContent+"' DIAG_CONT, ");
		query.append("	'"+hospName+"' HOSP_NAME, ");
		query.append("	'"+oudiRes+"' OUDI_RES, ");
		query.append("	'"+dise+"' DISE, ");
		query.append("	'"+infectWhet+"' INFEC_WHET, ");
		query.append("	'"+cureType+"' CURE_TP, ");
		query.append("	'"+hsptzDate+"' HSPTZ_DATE, ");
		query.append("	'"+dschDate+"' DSCH_DATE ");
		query.append("FROM DUAL ");
	
		connection();
		
		try {
			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				con.commit();
		}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		disconnection();
		
	};
	

	//외진(사망)등록
	private void RegistDiag4(String diagType,String oudiRes,String deathType) {
		
		String diagDate = ((JTextField)diagDateChooser.getDateEditor().getUiComponent()).getText();
		String diagContent = xDiagContent.getText();
		String hospName = xHospName.getText();
		String reas = xDeathReason.getText();
		
		StringBuffer query = new StringBuffer("INSERT INTO DIAG(PROT_NO,DIAG_ORNU,DIAG_DATE,DIAG_TP,DIAG_CONT,HOSP_NAME,OUDI_RES,REAS,DTH_TP) ");
		query.append("SELECT '"+protNo+"' PROT_NO, ");
		query.append("	(SELECT NVL(DIAG_ORNU,0)+1 ");
		query.append("	FROM( ");
		query.append("		SELECT /*+INDEX_DESC(DIAG DIAG_PK)*/ MAX(DIAG_ORNU) DIAG_ORNU FROM DIAG ");
		query.append("	WHERE PROT_NO='"+protNo+"')) DIAG_ORNU, ");
		query.append("	to_date('"+diagDate+"','YYYY-MM-DD') DIAG_DATE, ");
		query.append("	'"+diagType+"' DIAG_TP, ");
		query.append("	'"+diagContent+"' DIAG_CONT, ");
		query.append("	'"+hospName+"' HOSP_NAME, ");
		query.append("	'"+oudiRes+"' OUDI_RES, ");
		query.append("	'"+reas+"' REAS, ");
		query.append("	'"+deathType+"' DTH_TP ");
		query.append("FROM DUAL ");
		
		connection();
		
		try {
			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				con.commit();
		}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		disconnection();
	
		
	};
	
	//보호를 종료하는 함수
	private void FinishProt() {
		
	}
	
	class DiagTypeItemListener implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			String target = (String)e.getItem();
			if(target=="내진") {
				activateIndi();
			}
			else if(target=="외진") {
				activateOudi();
				cbOudiResult.setSelectedItem("치료");
				cbCureType.setSelectedItem("통원");
			}
		}
		
	}

	class OudiResultItemListener implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			String target = (String)e.getItem();
			if(target=="치료") {
				activateCure();
			}
			else if(target=="사망") {
				activateDeath();
				cbCureType.setSelectedItem("통원");
			}
		}
		
	}
	
	class CureTypeItemListener implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			String target = (String)e.getItem();
			if(target=="통원") {
				activateHsptz();
			}
			else if(target=="입원") {
				activateDsch();
			}
		}
		
	}
 
	private void activateIndi() {
		cbIndiResult.setEnabled(true);
		search.setEnabled(true);
		
		cbOudiResult.setEnabled(false);
		xHospName.setEnabled(false);
		xDisease.setEnabled(false);
		cbInfectWhet.setEnabled(false);
		cbCureType.setEnabled(false);
//		xHsptzDate.setEnabled(false);
//		xDschDate.setEnabled(false);
//		imageButton2.setEnabled(false);
		hsptzDateChooser.setEnabled(false);
		dschDateChooser.setEnabled(false);
		cbDeathType.setEnabled(false);
		xDeathReason.setEnabled(false);
	}
	
	private void activateOudi() {
		cbIndiResult.setEnabled(false);
		search.setEnabled(false);
		
		cbOudiResult.setEnabled(true);
		xHospName.setEnabled(true);
		xDisease.setEnabled(true);
		cbInfectWhet.setEnabled(true);
		cbCureType.setEnabled(true);
//		xHsptzDate.setEnabled(true);
//		xDschDate.setEnabled(false);
//		imageButton2.setEnabled(false);
		hsptzDateChooser.setEnabled(false);
		dschDateChooser.setEnabled(false);
		cbDeathType.setEnabled(false);
		xDeathReason.setEnabled(false);
	}
	
	private void activateCure() {
//		cbOudiResult.setEnabled(true);
		xHospName.setEnabled(true);
		xDisease.setEnabled(true);
		cbInfectWhet.setEnabled(true);
		cbCureType.setEnabled(true);
//		xHsptzDate.setEnabled(true);
//		xDschDate.setEnabled(false);
//		imageButton2.setEnabled(false);
		hsptzDateChooser.setEnabled(true);
		dschDateChooser.setEnabled(false);
		
		cbDeathType.setEnabled(false);
		xDeathReason.setEnabled(false);
	}
	
	private void activateDeath() {
//		cbOudiResult.setEnabled(true);
		xHospName.setEnabled(false);
		xDisease.setEnabled(false);
		cbInfectWhet.setEnabled(false);
		cbCureType.setEnabled(false);
//		xHsptzDate.setEnabled(false);
//		xDschDate.setEnabled(false);
//		imageButton2.setEnabled(false);
		hsptzDateChooser.setEnabled(false);
		dschDateChooser.setEnabled(false);
		
		cbDeathType.setEnabled(true);
		xDeathReason.setEnabled(true);	
	}
	
	
	private void activateHsptz() {
//		xHsptzDate.setEnabled(true);
		hsptzDateChooser.setEnabled(false);
		
//		xDschDate.setEnabled(false);
//		imageButton2.setEnabled(false);
		dschDateChooser.setEnabled(false);
	}
	
	private void activateDsch() {
//		xHsptzDate.setEnabled(true);
		hsptzDateChooser.setEnabled(true);
		
//		xDschDate.setEnabled(true);
//		imageButton2.setEnabled(true);
		dschDateChooser.setEnabled(true);
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
	
	
   private void ChangeFont(JComponent[] comps, Font font) {
    	for(JComponent comp: comps) {
    		comp.setFont(font);
    	}
    }
		
	public static void main(String[] args) throws IOException {
		new DiagRegister(null);
	}
}
