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
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
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

public class ProtAniRegist extends JFrame{
	private JLabel vProtAniRegist, vAbanAniType, vRscuNo, vParAbanAniName, vAbanAniName, vAge, vAniType, vKind, vSex, vNeutWhet, vColor, vAniSize, vFeature, vPicture, vCage;
	private JTextField xRscuNo, xParAbanAniName, xAbanAniName, xAge, xKind, xColor;
	private JComboBox<String> cbAbanAniType, cbAniType, cbSex, cbNeutWhet, cbAniSize, cbCage;
	private JTextArea xFeature;
	private JButton searchRscu, searchPar, pictureManage, register, cancel;
	
	private JScrollPane featureScroll;
	
	private ArrayList<String> picPaths;
	private String parAbanNo = null;
	private boolean isPicture = false;
	
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "asmr";
	private String password = "asmr";
	
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private ResultSetMetaData rsmd = null;
	
	private final String[] abanAniTypeDiv = {"구조","탄생"};
	private final String[] aniTypeDiv = {"개","고양이","기타"};
	private final String[] sexDiv = {"수컷","암컷","미상"};
	private final String[] neutWhetDiv = {"Y","N"};
	private final String[] aniSizeDiv = {"대","중","소"};
	private final String[] cageDiv = {"케이지1(대)","케이지4(중)","케이지9(소)"};
	
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	
	ProtAniRegistItemListener protAniRegistItemListener;
	ProtAniTypeItemListener protAniTypeItemListener;
	ProtAniRegistButtonListener protAniRegistButtonListener;
	
	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;
	
	public ProtAniRegist() {
		
		gridBagLayout = new GridBagLayout();		
		gridBagConstraints = new GridBagConstraints();
		
		protAniRegistItemListener = new ProtAniRegistItemListener();
		protAniRegistButtonListener = new ProtAniRegistButtonListener();
		protAniTypeItemListener = new ProtAniTypeItemListener();
		
//		vProtAniRegist = new JLabel("보호동물등록");
//		vProtAniRegist.setFont(new Font("나눔고딕", Font.BOLD, 20));
		
		
		vAbanAniType = new JLabel("유기동물구분");
		cbAbanAniType = new JComboBox<String>(abanAniTypeDiv);
		cbAbanAniType.addItemListener(protAniRegistItemListener);
		
		vRscuNo = new JLabel("구조번호");
		xRscuNo = new JTextField(10);
		xRscuNo.setEditable(false);
		searchRscu = new JButton("검색");
		searchRscu.setBackground(blue);
		searchRscu.setForeground(white);
		searchRscu.addActionListener(protAniRegistButtonListener);
		
		vParAbanAniName = new JLabel("어미유기동물명");
		xParAbanAniName = new JTextField(10);
		xParAbanAniName.setEditable(false);
		searchPar = new JButton("검색");
		searchPar.setBackground(blue);
		searchPar.setForeground(white);
		searchPar.addActionListener(protAniRegistButtonListener);
		
		vAbanAniName = new JLabel("유기동물명");
		xAbanAniName = new JTextField(10);
		
		vAge = new JLabel("나이(개월)");
		xAge = new JTextField(10);
		
		vAniType = new JLabel("동물종류");
		cbAniType = new JComboBox<String>(aniTypeDiv);
		cbAniType.addItemListener(protAniTypeItemListener);
		
		vKind = new JLabel("품종");
		xKind = new JTextField(10);
		
		vSex = new JLabel("성별");
		cbSex = new JComboBox<String>(sexDiv);
		
		vNeutWhet = new JLabel("중성화여부");
		cbNeutWhet = new JComboBox<String>(neutWhetDiv);
		
		vColor = new JLabel("색상");
		xColor = new JTextField(10);
		
		vAniSize = new JLabel("동물크기");
		cbAniSize = new JComboBox<String>(aniSizeDiv);
		
		vFeature = new JLabel("특징");
		xFeature = new JTextArea();
		featureScroll = new JScrollPane(xFeature);
		featureScroll.setPreferredSize(new Dimension(300,100));
		featureScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		vPicture = new JLabel("사진");
		pictureManage = new JButton("사진관리");
		pictureManage.setBackground(blue);
		pictureManage.setForeground(white);
		pictureManage.addActionListener(protAniRegistButtonListener);
		
		vCage = new JLabel("케이지");
		cbCage = new JComboBox<String>();
		
		register = new JButton("등록");
		register.setBackground(blue);
		register.setForeground(white);
		register.addActionListener(protAniRegistButtonListener);
		
		cancel = new JButton("취소");
		cancel.addActionListener(protAniRegistButtonListener);
		
		JComponent[] vComps = {vAbanAniType, vRscuNo, vParAbanAniName, vAbanAniName, vAge, vAniType, vKind, vSex, vNeutWhet, vColor, vAniSize, vFeature, vPicture, vCage};
		ChangeFont(vComps, new Font("나눔고딕", Font.PLAIN, 16));
		
		JComponent[] bComps = {searchRscu, searchPar, pictureManage, register, cancel};
		ChangeFont(bComps, new Font("나눔고딕", Font.BOLD, 12));
		
		activateRscu();
		
		ProtAniRegistView();
	}
	
	private void ProtAniRegistView() {
		setLayout(gridBagLayout);
		setTitle("보호동물등록");
		
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.ipadx = 7;
		
		gridBagConstraints.weightx=1.0;
		gridBagConstraints.weighty=1.0;
	
//		gridbagAdd(vProtAniRegist, 0, 0, 1, 1);
		
		gridbagAdd(vAbanAniType, 0, 1, 1, 1);
		gridbagAdd(cbAbanAniType, 1, 1, 1, 1);
		
		gridbagAdd(vRscuNo, 0, 2, 1, 1);
		gridbagAdd(xRscuNo, 1, 2, 1, 1);
		gridbagAdd(searchRscu, 2, 2, 1, 1);
		
		gridbagAdd(vParAbanAniName, 3, 2, 1, 1);
		gridbagAdd(xParAbanAniName, 4, 2, 1, 1);
		gridbagAdd(searchPar, 5, 2, 1, 1);
		
		gridbagAdd(vAbanAniName, 0, 3, 1, 1);
		gridbagAdd(xAbanAniName, 1, 3, 1, 1);
		
		gridbagAdd(vAge, 2, 3, 1, 1);
		gridbagAdd(xAge, 3, 3, 1, 1);
		
		gridbagAdd(vAniType, 0, 4, 1, 1);
		gridbagAdd(cbAniType, 1, 4, 1, 1);
		
		gridbagAdd(vKind, 2, 4, 1, 1);
		gridbagAdd(xKind, 3, 4, 1, 1);
		
		gridbagAdd(vSex, 0, 5, 1, 1);
		gridbagAdd(cbSex, 1, 5, 1, 1);
		
		gridbagAdd(vNeutWhet, 2, 5, 1, 1);
		gridbagAdd(cbNeutWhet, 3, 5, 1, 1);
		
		gridbagAdd(vColor, 0, 6, 1, 1);
		gridbagAdd(xColor, 1, 6, 1, 1);
		
		gridbagAdd(vAniSize, 2, 6, 1, 1);
		gridbagAdd(cbAniSize, 3, 6, 1, 1);
		
		gridbagAdd(vFeature, 0, 7, 1, 1);
		gridbagAdd(featureScroll, 1, 7, 3, 1);
		
		gridbagAdd(vPicture, 0, 8, 1, 1);
		gridbagAdd(pictureManage, 1, 8, 1, 1);
		
		gridbagAdd(vCage, 0, 9, 1, 1);
		gridbagAdd(cbCage, 1, 9, 1, 1);
	
		Component[] cops = {register, cancel};
		CombinePanel buttonPanel = new CombinePanel(cops, true);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 225, 0, 0));
		gridbagAdd(buttonPanel, 0, 10, 6, 1);
		
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
	
	//두개의 컴포넌트를 하나의 패널로 묶는 JPanel
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
	
	class ProtAniRegistItemListener implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			String target = (String)e.getItem();
			if(target=="구조") {
				activateRscu();
			}
			else if(target=="탄생") {
				activateBirth();
			}
		}
		
	}
	
	class ProtAniTypeItemListener implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			String target = (String)e.getItem();
			if(target=="고양이") {
				activateCat();
			}
			else {
				deactivateCat();
			}
		}
		
	}
	
	private void activateCat() {
		cbAniSize.setSelectedItem("소");
		cbAniSize.setEnabled(false);
	}
	
	private void deactivateCat() {
		cbAniSize.setSelectedItem("대");
		cbAniSize.setEnabled(true);
	}
	
	private void activateRscu() {
		xRscuNo.setEnabled(true);
		searchRscu.setEnabled(true);
		
		xParAbanAniName.setEnabled(false);
		searchPar.setEnabled(false);
	}
	
	private void activateBirth() {
		xParAbanAniName.setEnabled(true);
		searchPar.setEnabled(true);
		
		xRscuNo.setEnabled(false);
		searchRscu.setEnabled(false);
		
	}
	
    private void ChangeFont(JComponent[] comps, Font font) {
    	for(JComponent comp: comps) {
    		comp.setFont(font);
    	}
    }
	
    class ProtAniRegistButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource().equals(searchRscu)) {
				RscuSearch rscuSearch = new RscuSearch(xRscuNo);
				rscuSearch.addWindowListener(new WindowAdapter() {

					@Override
					public void windowClosed(WindowEvent e) {
						// TODO Auto-generated method stub
						super.windowClosed(e);
						String rscuNo = xRscuNo.getText();
						GetAvalCages(rscuNo);
					}
					
				});
			}
			else if(e.getSource().equals(searchPar)) {
				ProtAnmlSearchPopup protAnmlSearchPopup = new ProtAnmlSearchPopup(xParAbanAniName);
				protAnmlSearchPopup.addWindowListener(new WindowAdapter() {

					@Override
					public void windowClosed(WindowEvent e) {
						// TODO Auto-generated method stub
						super.windowClosed(e);
						parAbanNo = protAnmlSearchPopup.getProtNo();
						GetAvalCages2(parAbanNo);
					}
				
				
				});
			}
			else if(e.getSource().equals(pictureManage)) {
				try {
					NewPictureManage newPictureManage = new NewPictureManage(null);
					newPictureManage.addWindowListener(new WindowAdapter() {

						@Override
						public void windowClosed(WindowEvent e) {
							// TODO Auto-generated method stub
							super.windowClosed(e);
							picPaths = newPictureManage.getPaths();
						}
						
					});
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if(e.getSource().equals(register)) {
				int result = JOptionPane.showConfirmDialog(null, "보호동물을 등록하시겠습니까?","확인",JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.YES_OPTION) { 
					JOptionPane.showMessageDialog(null, "등록되었습니다.","등록 확인",JOptionPane.PLAIN_MESSAGE);
					RegistProtAni();
					dispose();
				}
			}
			else if(e.getSource().equals(cancel)) {
				dispose();
			}
		}
    	
    }
    
    private void RegistProtAni() {
    	
    	String abanAniType= (String)cbAbanAniType.getSelectedItem();
    	String engAbanAniType = null;
    	
    	String rscuNo = xRscuNo.getText();
    	String parAbanNo = this.parAbanNo;
    	String abanName = xAbanAniName.getText();
    	String age = xAge.getText();
    	
    	String aniKind = (String)cbAniType.getSelectedItem();
    	String engAniKind = null;
    	
    	String kind = xKind.getText();
    	
    	String sex = (String)cbSex.getSelectedItem();
    	String engSex = null;
    	
    	String neutWhet = (String)cbNeutWhet.getSelectedItem();
    	String engNeutWhet = null;
    	
    	String color = xColor.getText();
    	
    	String aniSize = (String)cbAniSize.getSelectedItem();
    	String engAniSize = null;
    
    	String feature = xFeature.getText();
    	
    	//보호등록시 사용
		String cage = (String) cbCage.getSelectedItem();
		String ornu = cage.replaceAll("[^0-9]", "");
		
    	
    	switch(abanAniType) {
    	case "구조":
    		engAbanAniType = "r";
    		break;
    	case "탄생":
    		engAbanAniType = "b";
    		break;
    	}
    	
    	switch(aniKind) {
    	case "개":
    		engAniKind = "d";
    		break;
    	case "고양이":
    		engAniKind = "c";
    		break;
    	case "기타":
    		engAniKind = "e";
    		break;
    	}
    	
    	switch(sex) {
    	case "수컷":
    		engSex = "m";
    		break;
    	case "암컷":
    		engSex = "f";
    		break;
    	case "미상":
    		engSex = "u";
    		break;
    	}
    	
    	switch(aniSize) {
    	case "대":
    		engAniSize="b";
    		break;
    	case "중":
    		engAniSize="m";
    		break;
    	case "소":
    		engAniSize="s";
    		break;
    	}
    	
    	connection();
    	
    	StringBuffer query1 = new StringBuffer();
	    
		if(abanAniType=="구조") {
	    	query1.append("INSERT INTO ABAN(ABAN_NO,ABAN_TP,ABAN_NAME,AGE,KIND,SEX,COLOR,NEUT_WHET,REGIS_DATE,ANML_SIZE,FEAT,RSCU_NO) ");
    		query1.append("SELECT ");
    		query1.append("	CASE WHEN SUBSTR(ABAN_NO,1,8) = to_char(TRUNC(SYSDATE),'yyyymmdd') ");
    		query1.append("	THEN to_char(TRUNC(SYSDATE),'yyyymmdd') || CASE WHEN SUBSTR(ABAN_NO,10,1) = '9' THEN to_char(SUBSTR(ABAN_NO,9,1)+1) ELSE SUBSTR(ABAN_NO,9,1) END || CASE WHEN SUBSTR(ABAN_NO,10,1)='9' THEN '0' ELSE to_char(SUBSTR(ABAN_NO,10,1)+1) END ");
    		query1.append("	ELSE to_char(TRUNC(SYSDATE),'yyyymmdd') || '01' END ABAN_NO, ");
    		query1.append(" '"+engAbanAniType+"' ABAN_TP, ");
    		query1.append(" '"+abanName+"' ABAN_NAME, ");
    		query1.append(" '"+age+"' AGE, ");
    		query1.append(" '"+engAniKind+"' ANML_KINDS, ");
    		query1.append(" '"+kind+"' KIND, ");
    		query1.append(" '"+engSex+"' SEX, ");
    		query1.append(" '"+color+"' COLOR, ");
    		query1.append(" '"+neutWhet+"' NEUT_WHET, ");
    		query1.append(" TRUNC(SYSDATE) REGIS_DATE, ");
    		query1.append(" '"+engAniSize+"' ANML_SIZE, ");
    		query1.append(" '"+feature+"' FEAT, ");
    		query1.append(" '"+rscuNo+"' RSCU_NO ");
    		query1.append("FROM( ");
    		query1.append("	SELECT NVL(ABAN_NO,0) ABAN_NO ");
    		query1.append("	FROM(SELECT /*+INDEX_DESC(ABAN ABAN_PK) */ MAX(ABAN_NO) ABAN_NO ");
    		query1.append("		 FROM ABAN) ");
    		query1.append(") ");
		}	
    	//동물구분이 탄생일때 쿼리
    	else if(abanAniType=="탄생") {
    		query1.append("INSERT INTO ABAN(ABAN_NO,ABAN_TP,ABAN_NAME,AGE,ANML_KINDS,KIND,SEX,COLOR,NEUT_WHET,REGIS_DATE,ANML_SIZE,FEAT,MOM_ABAN_NO) ");
    		query1.append("SELECT ");
    		query1.append("	CASE WHEN SUBSTR(ABAN_NO,1,8) = to_char(TRUNC(SYSDATE),'yyyymmdd') ");
    		query1.append("	THEN to_char(TRUNC(SYSDATE),'yyyymmdd') || CASE WHEN SUBSTR(ABAN_NO,10,1) = '9' THEN to_char(SUBSTR(ABAN_NO,9,1)+1) ELSE SUBSTR(ABAN_NO,9,1) END || CASE WHEN SUBSTR(ABAN_NO,10,1)='9' THEN '0' ELSE to_char(SUBSTR(ABAN_NO,10,1)+1) END ");
    		query1.append("	ELSE to_char(TRUNC(SYSDATE),'yyyymmdd') || '01' END ABAN_NO, ");
    		query1.append(" '"+engAbanAniType+"' ABAN_TP, ");
    		query1.append(" '"+abanName+"' ABAN_NAME, ");
    		query1.append(" '"+age+"' AGE, ");
    		query1.append(" '"+engAniKind+"' ANML_KINDS, ");
    		query1.append(" '"+kind+"' KIND, ");
    		query1.append(" '"+engSex+"' SEX, ");
    		query1.append(" '"+color+"' COLOR, ");
    		query1.append(" '"+neutWhet+"' NEUT_WHET, ");
    		query1.append(" TRUNC(SYSDATE) REGIS_DATE, ");
    		query1.append(" '"+engAniSize+"' ANML_SIZE, ");
    		query1.append(" '"+feature+"' FEAT, ");
    		query1.append(" '"+parAbanNo+"' MOM_ABAN_NO ");
    		query1.append("FROM( ");
    		query1.append("	SELECT NVL(ABAN_NO,0) ABAN_NO ");
    		query1.append("	FROM(SELECT /*+INDEX_DESC(ABAN ABAN_PK) */ MAX(ABAN_NO) ABAN_NO ");
    		query1.append("		 FROM ABAN) ");
    		query1.append(") ");
    	}
		
		StringBuffer query2 = new StringBuffer();    
			
		try {
			query2.append("INSERT INTO ABAN_PIC ");
	    	query2.append("SELECT ");
	    	query2.append("	(SELECT NVL(ABAN_NO,0) ABAN_NO ");
	    	query2.append("	FROM(SELECT /*+INDEX_DESC(ABAN ABAN_PK) */ MAX(ABAN_NO) ABAN_NO ");
	    	query2.append("		 FROM ABAN)) ABAN_NO, ");
	    	query2.append("	ROWNUM, t.* ");
	    	query2.append("FROM( ");
	    	
	    	int numLoop = 0;
	    	for(String path : picPaths) {
	    		query2.append("SELECT '"+path+"' FROM DUAL ");
	    		if(numLoop == picPaths.size()-1) 
	    			break;
	    		query2.append("UNION ALL ");
	    		numLoop+=1;
	    	}
	    	query2.append(") t ");
	    	
	    	isPicture = true;
	    	
		}catch(NullPointerException e) {
			isPicture = false;
		}
		
		StringBuffer query3 = new StringBuffer("INSERT INTO PROT(PROT_NO,PROT_START_DATE,ABAN_NO,CNTR_NO,CAGE_ORNU,CAMA_EMP_NO) ");
		query3.append("SELECT ");
		query3.append("	CASE WHEN SUBSTR(PROT_NO,1,8) = to_char(TRUNC(SYSDATE),'yyyymmdd') ");
		query3.append(" THEN to_char(TRUNC(SYSDATE),'yyyymmdd') || CASE WHEN SUBSTR(PROT_NO,10,1) = '9' THEN to_char(SUBSTR(PROT_NO,9,1)+1) ELSE SUBSTR(PROT_NO,9,1) END || CASE WHEN SUBSTR(PROT_NO,10,1)='9' THEN '0' ELSE to_char(SUBSTR(PROT_NO,10,1)+1) END ");
		query3.append(" ELSE to_char(TRUNC(SYSDATE),'yyyymmdd') || '01' END PROT_NO, ");
		query3.append("	TRUNC(SYSDATE) PROT_START_DATE, ");
		query3.append("	(SELECT NVL(ABAN_NO,0) ABAN_NO FROM (SELECT /*+INDEX_DESC(ABAN ABAN_PK) */ MAX(ABAN_NO) ABAN_NO FROM ABAN)) ABAN_NO, ");
		query3.append("	(SELECT CNTR_NO ");
		query3.append("	FROM(SELECT * FROM ASSG ");
		query3.append("		WHERE ASSG_RES='a') a INNER JOIN (SELECT RSCU_NO ");
		query3.append("		FROM RSCU ");
		query3.append("	WHERE RSCU_NO='1') r ");
		query3.append("	ON a.ASSG_NO = r.RSCU_NO) CNTR_NO, ");
		query3.append("	'"+ornu+"' CAGE_ORNU, ");
		query3.append("	(SELECT /*+INDEX_ASC(EMP_WORK_HIST EMP_WORK_HIST_PK) */ EMP_NO FROM EMP_WORK_HIST ");
		query3.append("	WHERE CNTR_NO = (SELECT CNTR_NO ");
		query3.append("	FROM(SELECT * FROM ASSG ");
		query3.append("		WHERE ASSG_RES='a') a INNER JOIN (SELECT RSCU_NO ");
		query3.append("		FROM RSCU ");
		query3.append("		WHERE RSCU_NO='1') r ");
		query3.append("		ON a.ASSG_NO = r.RSCU_NO) ");
		query3.append("	AND BIZ_FILD='p' ");
		query3.append("	AND ROWNUM=1) CAMA_EMP_NO ");
		query3.append("FROM(SELECT NVL(PROT_NO,0) PROT_NO ");
		query3.append("	FROM(SELECT /*+INDEX_DESC(PROT PROT_PK) */ MAX(PROT_NO) PROT_NO ");
		query3.append("		FROM PROT) ");
		query3.append(") ");
			
		try {
			pstmt = con.prepareStatement(query1.toString());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				con.commit();
			}
			if(isPicture) {
				pstmt = con.prepareStatement(query2.toString());
				rs = pstmt.executeQuery();
				if(rs.next()) {
					con.commit();
				}
			}
			pstmt = con.prepareStatement(query3.toString());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				con.commit();
			}
			
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    
    	disconnection();
    }
    
    private void GetAvalCages2(String parAbanNo) {
    	
    	cbCage.removeAllItems();
    	
    	StringBuffer query = new StringBuffer("SELECT '케이지'||CAGE_ORNU||'('||CASE CAGE_SIZE WHEN 'b' THEN '대' WHEN 'm' THEN '중' WHEN 's' THEN '소' END||')' CAGES ");
    	query.append("FROM( ");
    	query.append("	SELECT c.CAGE_ORNU,c.CAGE_SIZE ");
    	query.append("	FROM CAGE c INNER  JOIN (SELECT DISTINCT(CNTR_NO) CNTR_NO FROM PROT WHERE ABAN_NO='2019102701') p ");
    	query.append("	ON c.CNTR_NO = p.CNTR_NO) t ");
    	query.append("	WHERE t.CAGE_ORNU NOT IN( ");
    	query.append("		SELECT c.CAGE_ORNU ");
    	query.append("		FROM CAGE c INNER JOIN (SELECT * FROM PROT WHERE CNTR_NO = (SELECT DISTINCT(CNTR_NO) CNTR_NO FROM PROT WHERE ABAN_NO='2019102701') AND PROT_END_DATE=to_date('9999-12-31','YYYY-MM-DD')) p ");
    	query.append("		ON c.CNTR_NO=p.CNTR_NO AND c.CAGE_ORNU=p.CAGE_ORNU ");
    	query.append(") ORDER BY 1 ");
    	
    	connection();
    	
    	try {
    		pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				cbCage.addItem(rs.getString("CAGES"));
			}
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	
    	disconnection();
    }
    
    private void GetAvalCages(String rscuNo) {
    	
    	cbCage.removeAllItems();
    	
    	StringBuffer query= new StringBuffer("SELECT '케이지'||c.CAGE_ORNU||'('|| ");
		query.append("	CASE c.CAGE_SIZE WHEN 's' THEN '소' WHEN 'm' THEN '중' WHEN 'b' THEN '대' END ");
		query.append("	||')' CAGES ");
		query.append("FROM(SELECT t1.CNTR_NO,c.CAGE_ORNU ");
		query.append("	FROM( ");
		query.append("		SELECT a.CNTR_NO ");
		query.append("		FROM (SELECT * FROM RSCU ");
		query.append("			  WHERE RSCU_NO = '"+rscuNo+"') rs INNER JOIN (SELECT * FROM ASSG ");
		query.append("			  WHERE ASSG_RES = 'a') a ");
		query.append("			  ON rs.RSCU_NO = a.ASSG_NO) t1 LEFT OUTER JOIN CAGE c ");
		query.append("			  	ON t1.CNTR_NO = c.CNTR_NO ");
		query.append("	MINUS ");
		query.append("		SELECT p.CNTR_NO,p.CAGE_ORNU ");
		query.append("		FROM(SELECT a.CNTR_NO ");
		query.append("			FROM (SELECT * FROM RSCU ");
		query.append("				WHERE RSCU_NO = '"+rscuNo+"') rs INNER JOIN (SELECT * FROM ASSG ");
		query.append("					WHERE ASSG_RES = 'a') a ");
		query.append("					ON rs.RSCU_NO = a.ASSG_NO) t1 INNER JOIN (SELECT * FROM PROT WHERE PROT_END_DATE = to_date('9999-12-31','YYYY-MM-DD')) p ");
		query.append("						ON t1.CNTR_NO = p.CNTR_NO ");
		query.append(") t2 INNER JOIN CAGE c ");
		query.append("	ON t2.CNTR_NO = c.CNTR_NO AND t2.CAGE_ORNU = c.CAGE_ORNU ");
		query.append("ORDER BY 1 ");
	
    	connection();
    		
    	try {
			
			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				cbCage.addItem(rs.getString("CAGES"));
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
		new ProtAniRegist();
	}
}
