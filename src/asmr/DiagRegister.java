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
	
	private String[] diagTypeDiv = {"����","����"};
	private String[] indiResultDiv = {"����","����","����"};
	private String[] oudiResultDiv = {"ġ��","���"};
	private String[] infectWhetDiv = {"Y","N"};
	private String[] cureTypeDiv = {"���","�Կ�"};
	private String[] deathTypeDiv = {"�ڿ���","�ȶ���"};
	
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
		
//		vDiagRegister = new JLabel("������");
		
		vDiagDate = new JLabel("��������");
		xDiagDate = new JTextField(10);
		
		LocalDate now = LocalDate.now();
		Date date = Date.valueOf(now);
		diagDateChooser = new JDateChooser(date, "yyyy-MM-dd");
		JTextField textField= (JTextField) diagDateChooser.getDateEditor().getUiComponent();
		textField.setEditable(false);
		
		vDiagType = new JLabel("���ᱸ��");
		cbDiagType = new JComboBox<String>(diagTypeDiv);
		cbDiagType.addItemListener(diagTypeItemListener);
		
		vIndiResult = new JLabel("�������");
		cbIndiResult = new JComboBox<String>(indiResultDiv);
		
		vIndiVtrnName = new JLabel("�������ǻ��");
		xIndiVtrnName =  new JTextField(10);
		xIndiVtrnName.setEditable(false);
		search = new JButton("�˻�");
		search.setBackground(blue);
		search.setForeground(white);
		search.addActionListener(diagRegisterButtonListener);
		
		
		vOudiResult = new JLabel("�������");
		cbOudiResult = new JComboBox<String>(oudiResultDiv);
		cbOudiResult.addItemListener(oudiResultItemListener);
		
		vHospName = new JLabel("������");
		xHospName = new JTextField(10);
		
		vDisease = new JLabel("����");
		xDisease = new JTextField(10);
		
		vInfecWhet = new JLabel("��������");
		cbInfectWhet = new JComboBox<String>(infectWhetDiv);
		
		vCureType = new JLabel("ġ�ᱸ��");
		cbCureType = new JComboBox<String>(cureTypeDiv);
		cbCureType.addItemListener(cureTypeItemListener);
		
		vHsptzDate = new JLabel("�Կ�����");
		hsptzDateChooser = new JDateChooser(date,"yyyy-MM-dd");
				
		vDschDate = new JLabel("�������");
		dschDateChooser = new JDateChooser(null,"yyyy-MM-dd");

		vDeathType = new JLabel("�������");
		cbDeathType = new JComboBox<String>(deathTypeDiv);
		
		vDeathReason = new JLabel("�������");
		xDeathReason = new JTextField(10);
		
		vDiagContent = new JLabel("���᳻��");
		xDiagContent = new JTextArea();
		xDiagContent.setLineWrap(true);
		diagContentScroll = new JScrollPane(xDiagContent);
		diagContentScroll.setPreferredSize(new Dimension(400,150));
		diagContentScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		register = new JButton("���");
		register.setBackground(blue);
		register.setForeground(white);
		register.addActionListener(diagRegisterButtonListener);
		
		cancel = new JButton("���");
		cancel.addActionListener(diagRegisterButtonListener);
		
		JComponent[] vComps = {vDiagDate, vDiagType, vIndiResult, vIndiVtrnName, vOudiResult, vHospName, vDisease, vInfecWhet, vCureType, vHsptzDate, vDschDate,vDeathType, vDeathReason, vDiagContent};
		ChangeFont(vComps, new Font("�������", Font.PLAIN, 16));
		
		JComponent[] bComps = {search, register, cancel};
		ChangeFont(bComps, new Font("�������", Font.BOLD, 12));
		activateIndi();
		
		DiagRegisterView();
	}
	
	private void DiagRegisterView() {
		setLayout(gridBagLayout);
		setTitle("������");
		
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
		//������Ʈ 1, ������Ʈ 2, �г� ������ ��,�� margin ������ ���ֱ� ���� Flag
		public CombinePanel(Component[] cops, boolean isBorder) {
			//Margin�� �ʿ����� ���� ��
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
				int result = JOptionPane.showConfirmDialog(null, "���Ḧ ����Ͻðڽ��ϱ�?", "������", JOptionPane.YES_NO_OPTION);
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
		
		if(diagType == "����") {
			String engDiagType = "i";
			RegistDiag1(engDiagType);
		}
		else {
			//����
			String engDiagType = "o";
			String oudiRes = (String) cbOudiResult.getSelectedItem();
			
			if(oudiRes == "ġ��") {
				String engOudiRes = "c";
				
				String cureType = (String) cbCureType.getSelectedItem();
				
				if(cureType == "���") {
					String engCureType = "v";
					RegistDiag2(engDiagType,engOudiRes,engCureType);
				}else {
					//�Կ�
					String engCureType = "a";
					RegistDiag3(engDiagType,engOudiRes,engCureType);
				}
			}else {
				//���
				String engOudiRes = "d";
				
				String deathType = (String) cbDeathType.getSelectedItem();
				String engDeathType = null;
				switch(deathType) {
				case "�ڿ���":
					engDeathType="n";
					break;
				case "�ȶ���":
					engDeathType="e";
					break;
				}
				//���, 
				RegistDiag4(engDiagType,engOudiRes,engDeathType);
				//�ش� ������ ��ȣ�� �����Ű�� �Լ�
				FinishProt();
 			}
		}
	}
	
	//�������
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
	

	//����-ġ��-���
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
	

	//����-ġ��-�Կ�
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
	

	//����(���)���
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
	
	//��ȣ�� �����ϴ� �Լ�
	private void FinishProt() {
		
	}
	
	class DiagTypeItemListener implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			String target = (String)e.getItem();
			if(target=="����") {
				activateIndi();
			}
			else if(target=="����") {
				activateOudi();
				cbOudiResult.setSelectedItem("ġ��");
				cbCureType.setSelectedItem("���");
			}
		}
		
	}

	class OudiResultItemListener implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			String target = (String)e.getItem();
			if(target=="ġ��") {
				activateCure();
			}
			else if(target=="���") {
				activateDeath();
				cbCureType.setSelectedItem("���");
			}
		}
		
	}
	
	class CureTypeItemListener implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			String target = (String)e.getItem();
			if(target=="���") {
				activateHsptz();
			}
			else if(target=="�Կ�") {
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
	
	
   private void ChangeFont(JComponent[] comps, Font font) {
    	for(JComponent comp: comps) {
    		comp.setFont(font);
    	}
    }
		
	public static void main(String[] args) throws IOException {
		new DiagRegister(null);
	}
}
