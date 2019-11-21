package asmr;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

public class CenterList extends JPanel{
	
	private JButton centerRegist, cageRegist, searchManager, modify, cancel;
	private JLabel vCenterList,vCageList,vCenterInfo,vCenterNum,vEstDate,vCenterName,vPhoneNum,vArea,vOperTime,vOperTimeDash,vCenterManager,vCageNum,vCageBig,vCageMid,vCageSmall,vCageBigCount,vCageMidCount,vCageSmallCount;
	private JTextField xCenterNum,xEstDate,xCenterName,xPhoneNum,xArea,xCenterManager,xCageBig,xCageMid,xCageSmall;
	private JComboBox<String> cbOperTimeOpen,cbOperTimeClose;
	
	private static String hint = "- �� ������ 13�ڸ� ����";
	
	private boolean isClicked = false;
//	private String cntrManagerBdate = null;
	private String prevCntrName = null;
	private String prevPhoneNum = null;
	private String prevArea = null;
	private String prevTimeOpen = null;
	private String prevTimeClose = null;

//	private String newCntrManagerNo = null;
//	private String prevCntrManagerNo = null;
	
	private ArrayList<String> cntrNos;
	private String selectedCntrNo;
	private String selectedCntrType;
	
	private String userCntrNo;
	private String userEmpNo;
	private String userCntrType;
	private String userWorkType;
	
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "asmr";
	private String password = "asmr";
	
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private ResultSetMetaData rsmd = null;
	
	//���̺�� ��ũ�� �г�
	private JTable eCenterList, eCageList;
	private JScrollPane scrollpane1, scrollpane2;
	
	//�ش� Ŭ������ ���� ���̾ƿ��� GridBag 
	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;
	
	//���̺��� �÷� �迭
	private final String[] col1 = {"���͸�","�ּ�","��ð�"};
	private final String[] col2 = {"������ ��ȣ","ũ��"};
	
	//���̺� �÷� ���� - ���͸��
	private DefaultTableModel model1 = new DefaultTableModel(col1,0);
	//���̺� �÷� ���� - ������
	private DefaultTableModel model2 = new DefaultTableModel(col2,0);
	
	// private final String[] phoneNumStart = {"010","02","031","032","033","041","042","043","044","051","052","053","054","055","061","062","063","064"};
	private final String[] operTimeOpen = {"08:00","08:30","09:00","09:30","10:00","10:30","11:00"};
	private final String[] operTimeClose = {"16:00","16:30","17:00","17:30","18:00","18:30","19:00"};
	
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	
//  �����ʵ�
	CenterListButtonListener centerListButtonListener;
	CenterListMouseListener centerListMouseListener;

	
	//CenterList ������
	public CenterList(String cntrNo, String empNo){
		gridBagLayout = new GridBagLayout();
		gridBagConstraints = new GridBagConstraints();
		
		centerListButtonListener = new CenterListButtonListener();
		centerListMouseListener = new CenterListMouseListener();

		cntrNos = new ArrayList<String>();
		
		userCntrNo = cntrNo;
		userEmpNo = empNo;
		
		userCntrType = GetCntrType(userCntrNo);
		userWorkType = GetWorkType(userCntrNo,userEmpNo); 
		
		//���͸��, ���������, �������� �ؽ�Ʈ
		vCenterList = new JLabel("���͸��");
		vCenterList.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
//		eCenterList = new JTable(model1);
		eCenterList = new JTable(model1) {
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
		
		eCenterList.getTableHeader().setReorderingAllowed(false);
		eCenterList.getTableHeader().setResizingAllowed(false);
		eCenterList.addMouseListener(centerListMouseListener);
		scrollpane1 = new JScrollPane(eCenterList);
		scrollpane1.setPreferredSize(new Dimension(600,200));
		eCenterList.getColumnModel().getColumn(0).setPreferredWidth(150);
		eCenterList.getColumnModel().getColumn(1).setPreferredWidth(350);
		eCenterList.getColumnModel().getColumn(2).setPreferredWidth(100);
		
		//���������
		vCageList = new JLabel("���������");
		vCageList.setBorder(BorderFactory.createEmptyBorder(0, 20, 10, 0));
//		eCageList = new JTable(model2);
		eCageList = new JTable(model2) {
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
		eCageList.getTableHeader().setReorderingAllowed(false);
		eCageList.getTableHeader().setResizingAllowed(false);
		scrollpane2 = new JScrollPane(eCageList);
		scrollpane2.setPreferredSize(new Dimension(300,200));
		
		vCenterInfo = new JLabel("��������");
		vCenterInfo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		
		//�������� �Ʒ� �Է�â JLabel�� JTextField
		vCenterNum = new JLabel("���͹�ȣ");
		xCenterNum = new JTextField(10);
		xCenterNum.setEditable(false);
		
		//��¥ ���� 2001.01.20[10����]
		vEstDate = new JLabel("��������");
		xEstDate = new JTextField(12);
		xEstDate.setEditable(false);
		
		//���͸�
		vCenterName = new JLabel("���͸�");
		xCenterName = new JTextField(10);
		xCenterName.setEnabled(false);
		
		//��ȭ��ȣ �ϳ��� ���ڿ��� ����
		vPhoneNum = new JLabel("��ȭ��ȣ");
		xPhoneNum = new JTextField(12);
		xPhoneNum.setEnabled(false);
		xPhoneNum.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if(xPhoneNum.getText().trim().length()==0) {
					xPhoneNum.setText(hint);
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				if(xPhoneNum.getText().equals(hint))
					xPhoneNum.setText("");
			}
		});
		

		//����
		vArea = new JLabel("����(m��)");
		xArea = new JTextField(10);
		xArea.setEnabled(false);
		
		//��ð�
		vOperTime = new JLabel("��ð�");
		vOperTimeDash = new JLabel("~");
		cbOperTimeOpen = new JComboBox<String>(operTimeOpen);
		cbOperTimeOpen.setEnabled(false);
		cbOperTimeClose = new JComboBox<String>(operTimeClose);
		cbOperTimeClose.setEnabled(false);
		
		//������ �̸�(������ - VARCHAR(20)) - �ѱ� 1���ڴ� 3byte 6.667����
		vCenterManager = new JLabel("������");
		xCenterManager = new JTextField(10);
		xCenterManager.setEditable(false);
		
		//�� �������� ��
		vCageNum = new JLabel("�� ������ ��");
		vCageNum.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		//������
		vCageBig = new JLabel("����");
		xCageBig = new JTextField(2);
		xCageBig.setEditable(false);
		
		vCageMid = new JLabel("����");
		xCageMid = new JTextField(2);
		xCageMid.setEditable(false);
		
		vCageSmall = new JLabel("����");
		xCageSmall = new JTextField(2);
		xCageSmall.setEditable(false);
		
		vCageBigCount = new JLabel("��");
		vCageMidCount = new JLabel("��");
		vCageSmallCount = new JLabel("��");
		
		//��ư
		centerRegist = new JButton("���");
		centerRegist.setBackground(blue);
		centerRegist.setForeground(white);
		centerRegist.addActionListener(centerListButtonListener);
		
		cageRegist = new JButton("���");
		cageRegist.setBackground(blue);
		cageRegist.setForeground(white);
		cageRegist.addActionListener(centerListButtonListener);
		
//		searchManager = new JButton("�˻�");
//		searchManager.setBackground(blue);
//		searchManager.setForeground(white);
//		searchManager.setEnabled(false);
//		searchManager.addActionListener(centerListButtonListener);
		
		modify = new JButton("����");
		modify.setBackground(blue);
		modify.setForeground(white);
		modify.addActionListener(centerListButtonListener);
		
		cancel = new JButton("���");
		cancel.addActionListener(centerListButtonListener);
		
		JComponent[] vTitleComps = {vCenterList,vCageList};
		ChangeFont(vTitleComps, new Font("�������", Font.BOLD, 24));
		
		vCenterInfo.setFont(new Font("�������", Font.BOLD, 20));
		
		JComponent[] vContextComps	= {vCenterNum,vEstDate,vCenterName,vPhoneNum,vArea,vOperTime,vOperTimeDash,vCenterManager,vCageNum,vCageBig,vCageMid,vCageSmall,vCageBigCount,vCageMidCount,vCageSmallCount};
		ChangeFont(vContextComps, new Font("�������", Font.PLAIN, 16));
		
		JComponent[] bComps = {centerRegist, cageRegist, modify, cancel};
		ChangeFont(bComps, new Font("�������", Font.BOLD, 16));
		
		CenterListView();
		
		GetCenterList();
	}

	//Component ��ġ
	private void CenterListView() {
		setLayout(gridBagLayout);
		setBackground(MainFrame.bgc);
		
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.ipadx = 7;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		
		gridBagConstraints.weightx=1.0;
		gridBagConstraints.weighty=1.0;
		
		//���͸�ϰ� ��Ϲ�ư
		gridbagAdd(vCenterList, 0, 0, 1, 1);
		gridbagAdd(centerRegist, 4, 0, 1, 1);
		
		//�������� ��Ϲ�ư
		gridbagAdd(vCageList, 5, 0, 1, 1);
		JPanel plainPanel2 = new JPanel();
		plainPanel2.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
		plainPanel2.setBackground(MainFrame.bgc);
		plainPanel2.add(cageRegist);
		plainPanel2.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 0));
		gridbagAdd(plainPanel2, 9, 0, 1, 1);
		
		//���͸�����̺�, ������������̺�
		gridbagAdd(scrollpane1, 0, 1, 5, 5);
		JPanel plainPanel = new JPanel();
		plainPanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
		plainPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		plainPanel.setBackground(MainFrame.bgc);
		plainPanel.add(scrollpane2);
		gridbagAdd(plainPanel, 5, 1, 5, 5);
		
		//��������
		gridbagAdd(vCenterInfo, 0, 6, 1, 1);
		
		//���͹�ȣ
		gridbagAdd(vCenterNum, 0, 7, 1, 1);
		gridbagAdd(xCenterNum, 1, 7, 1, 1);
	
		//��������
		gridbagAdd(vEstDate, 2, 7, 1, 1);
		gridbagAdd(xEstDate, 3, 7, 1, 1);
		
		//���͸�
		gridbagAdd(vCenterName,0, 8,1,1);
		gridbagAdd(xCenterName, 1, 8, 1, 1);
		
		//��ȭ��ȣ
		gridbagAdd(vPhoneNum, 2, 8, 1, 1);
		gridbagAdd(xPhoneNum, 3, 8, 1, 1);
		
		//����
		gridbagAdd(vArea, 0, 9, 1, 1);
		gridbagAdd(xArea, 1, 9, 1, 1);
		
		//��ð�
		Component[] cops = {cbOperTimeOpen, vOperTimeDash,cbOperTimeClose};
		CombinePanel operTimePanel = new CombinePanel(cops,0,0);
		operTimePanel.setBackground(MainFrame.bgc);
		
		gridbagAdd(vOperTime, 2, 9, 1, 1);
		gridbagAdd(operTimePanel, 3, 9, 1, 1);
		
//		gridbagAdd(cbOperTimeOpen, 12, 9, 1, 1);
//		gridbagAdd(vOperTimeDash, 13, 9, 1, 1);
//		gridbagAdd(cbOperTimeClose, 14, 9, 1, 1);
		
		//������
		gridbagAdd(vCenterManager, 0, 10, 1, 1);
//		JComponent[] centerManagerComps = {xCenterManager};
//		CombinePanel centerManagerPanel = new CombinePanel(centerManagerComps, 0, 0);
		gridbagAdd(xCenterManager, 1, 10, 1, 1);
		
//		gridbagAdd(xCenterManager, 1, 10, 1, 1);
//		gridbagAdd(searchManager, 2, 10, 1, 1);
		
		//�� ������ ��
		gridbagAdd(vCageNum, 0, 11, 1, 1);
		
		//���º� ������ ������ ���� Bottom Panel �� ��ġ
		BottomPanel bottomPanel = new BottomPanel();
		gridbagAdd(bottomPanel, 0, 12, 2, 1);
		
		//�����ư ��ġ
		JComponent[] bComps = {modify,cancel};
		CombinePanel buttonPanel = new CombinePanel(bComps, 10, 0);
		buttonPanel.setBackground(MainFrame.bgc);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(10,325,0,0));
		gridbagAdd(buttonPanel, 0, 13, 21, 1);
		
	}
	
	
	private void gridbagAdd(Component c, int x, int y, int w , int h) {
		
		gridBagConstraints.gridx = x;
		gridBagConstraints.gridy = y;
		gridBagConstraints.gridwidth = w;
		gridBagConstraints.gridheight = h;
		
		gridBagLayout.setConstraints(c, gridBagConstraints);
		
		add(c);
	}
	
	// ���� ��� ��������
	private void GetCenterList() {
		cntrNos.clear();
		model1.setRowCount(0);
		
		connection();
		
		try {
			StringBuffer query= new StringBuffer("SELECT c.CNTR_NO ,c.CNTR_NAME name, c.ADDR addr, SUBSTR(c.OPEN_TIME,1,2)||':'||SUBSTR(c.OPEN_TIME,3,2)||'-'||SUBSTR(c.CLSE_TIME,1,2)||':'||SUBSTR(c.CLSE_TIME,3,2) opr_time ");
			query.append("FROM CNTR c ");
			query.append("ORDER BY 1 ");
			
			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {		
				
				cntrNos.add(rs.getString("CNTR_NO"));
				
				model1.addRow(new Object[] {rs.getString("name"),rs.getString("addr"),rs.getString("opr_time")});
			}
		
		}catch(Exception e1) {
			e1.printStackTrace();
		}
		
		disconnection();
	}
	
	private void GetCenter() {
		int clickedRow = eCenterList.getSelectedRow();
		selectedCntrNo = cntrNos.get(clickedRow);
		
//		String cntrName = (String)eCenterList.getValueAt(clickedRow, 0);
		
		GetCenter1(selectedCntrNo);
		GetCenter2(selectedCntrNo);
	}
	
	private void GetCenter1(String cntrNo) {
		connection();
		
		try {
			StringBuffer query= new StringBuffer("SELECT c.CNTR_NO, c.CNTR_NAME, c.ADDR, c.TEL_NO, c.AREA ,SUBSTR(OPEN_TIME,1,2)||':'||SUBSTR(OPEN_TIME,3,2) OPEN_TIME, SUBSTR(CLSE_TIME,1,2)||':'||SUBSTR(CLSE_TIME,3,2) CLSE_TIME, ESTB_DATE,CNTR_TP,LISTAGG(e.EMP_NAME,',') WITHIN GROUP (ORDER BY wh.EMP_NO) EMP_NAME ");
			query.append("FROM (");
			query.append("		SELECT * ");
			query.append("		FROM CNTR");
			query.append("		WHERE CNTR_NO='"+cntrNo+"') c LEFT OUTER JOIN EMP_WORK_HIST wh ");
			query.append("			ON c.CNTR_NO = wh.CNTR_NO ");
			query.append("			AND wh.WORK_END_DATE = to_date('9999-12-31','YYYY-MM-DD') ");
			query.append("			AND wh.BIZ_FILD = 'c' LEFT OUTER JOIN EMP e ON wh.EMP_NO = e.EMP_NO ");
			query.append("GROUP BY c.CNTR_NO, c.CNTR_NAME, c.ADDR, c.TEL_NO, c.AREA ,SUBSTR(OPEN_TIME,1,2)||':'||SUBSTR(OPEN_TIME,3,2), SUBSTR(CLSE_TIME,1,2)||':'||SUBSTR(CLSE_TIME,3,2), ESTB_DATE,CNTR_TP ");
			
			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {

//				prevCntrManagerNo = rs.getString("EMP_NO");
				
				String[] estbDate = rs.getString("ESTB_DATE").split(" ");
				
				xCenterNum.setText(rs.getString("CNTR_NO"));
				xCenterName.setText(rs.getString("CNTR_NAME"));
				xArea.setText(rs.getString("AREA"));
				xCenterManager.setText(rs.getString("EMP_NAME"));
				xEstDate.setText(estbDate[0]);
				xPhoneNum.setText(rs.getString("TEL_NO"));
				cbOperTimeOpen.setSelectedItem(rs.getString("OPEN_TIME"));
				cbOperTimeClose.setSelectedItem(rs.getString("CLSE_TIME"));
			}
				
		}catch(Exception e2) {
			e2.printStackTrace();
		}
		
		disconnection();
	}
	
	private void GetCenter2(String cntrNo) {
		connection();
		
		try {
			StringBuffer query= new StringBuffer("SELECT ct.CAGE_TYPE, NVL(c.CNT,0) CNT ");
			query.append("FROM (");
			query.append("	SELECT 'b' CAGE_TYPE FROM DUAL ");
			query.append("	UNION ALL ");
			query.append("	SELECT 'm' FROM DUAL ");
			query.append("	UNION ALL ");
			query.append("	SELECT 's' FROM DUAL ");
			query.append(") ct LEFT OUTER JOIN (SELECT t.CAGE_SIZE, COUNT(*) CNT ");
			query.append("				FROM (SELECT c1.*,c2.CAGE_ORNU,c2.CAGE_SIZE ");
			query.append("					FROM ( ");
			query.append("							SELECT CNTR_NO ");
			query.append("							FROM CNTR ");
			query.append("							WHERE CNTR_NO='"+cntrNo+"') c1 LEFT OUTER JOIN CAGE c2 ");
			query.append("																ON c1.CNTR_NO=c2.CNTR_NO ) t ");
			query.append("				GROUP BY t.CAGE_SIZE) c ");
			query.append("		ON ct.CAGE_TYPE=c.CAGE_SIZE ");
			
			
			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				switch(rs.getString("CAGE_TYPE")) {
					case "b":
						xCageBig.setText(rs.getString("CNT"));
						break;
					case "m":
						xCageMid.setText(rs.getString("CNT"));
						break;
					case "s":
						xCageSmall.setText(rs.getString("CNT"));
						break;
				}
			}
			
		}catch(Exception e3) {
			e3.printStackTrace();
		}
		
		disconnection();
	}
	
	private void GetCageList() {
		model2.setRowCount(0);
		
		int clickedRow = eCenterList.getSelectedRow();
		String cntrName= (String)eCenterList.getValueAt(clickedRow, 0);
		
		connection();
		
		try {
			StringBuffer query = new StringBuffer("SELECT c1.CNTR_NO,c2.CAGE_ORNU,c2.CAGE_SIZE ");
			query.append("FROM ( ");
			query.append("	SELECT * FROM CNTR ");
			query.append("	WHERE CNTR_NAME='"+cntrName+"') c1 INNER JOIN CAGE c2 ");
			query.append("										ON c1.CNTR_NO = c2.CNTR_NO ");
			
			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String korCageSize = null;
				
				switch(rs.getString("CAGE_SIZE")) {
				case "b":
					korCageSize = "����";
					break;
				case "m":
					korCageSize = "����";
					break;
				case "s":
					korCageSize = "����";
					break;
				
				}
				model2.addRow(new Object[] {rs.getString("CAGE_ORNU"),korCageSize});
			}
			
		}catch(Exception e4) {
			e4.printStackTrace();
		}
		
		disconnection();
	}
	
	private void UpdateCenter(String newCntrName,String newPhoneNum,String newArea,String newTimeOpen,String newTimeClose) {
		
		StringBuffer query1 = new StringBuffer("UPDATE CNTR SET CNTR_NAME=?, TEL_NO=?, \"AREA\"=?, OPEN_TIME=?, CLSE_TIME=? WHERE CNTR_NO=? ");
		
		connection();
		
		try {
			pstmt = con.prepareStatement(query1.toString());
			pstmt.setString(1, newCntrName);
			pstmt.setString(2, newPhoneNum);
			pstmt.setString(3, newArea);
			pstmt.setString(4, newTimeOpen);
			pstmt.setString(5, newTimeClose);
			pstmt.setString(6, selectedCntrNo);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				con.commit();
			}
		}catch(SQLException e) {	
			e.printStackTrace();
		}
		
		disconnection();
	}
	
	private String GetCntrType(String cntrNo) {
		String result = null;
		
		String query = "select cntr_tp from cntr where cntr_no='"+cntrNo+"' ";
		
		connection();
		
		try {
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				result = rs.getString("CNTR_TP");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		disconnection();
		
		return result;
	}
	
	private String GetWorkType(String cntrNo, String empNo) {
		String result = null;
		
		StringBuffer query = new StringBuffer("SELECT /*+INDEX_DESC(EMP_WORK_HIST EMP_WORK_HIST_PK)*/ BIZ_FILD ");
		query.append("FROM emp_work_hist ");
		query.append("WHERE emp_no='"+empNo+"' AND cntr_no='"+cntrNo+"' AND work_end_date=to_date('9999-12-31','YYYY-MM-DD') AND ROWNUM =1 ");
		
		connection();
		
		try {
			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				result = rs.getString("BIZ_FILD");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		disconnection();
		
		return result;
	}
	
//	private void UpdateWorkHist(String newCntrManagerNo,boolean isPrevEmpty) {
//		
//		StringBuffer query1 = new StringBuffer();
//		StringBuffer query2 = new StringBuffer();
//		
//		//������� ������, ������ �ش缾���� prev �Ŵ����� ���ó�¥�� END_DATE�ϰ� ���� ���ο� �Ŵ����� ���ó�¥�� START_DATE�Է��Ѵ�.
//		if(!isPrevEmpty) {
//			query1.append("UPDATE EMP_WORK_HIST ");
//			query1.append("SET WORK_END_DATE = trunc(sysdate) ");
//			query1.append("WHERE CNTR_NO='"+cntrNo+"' AND EMP_NO='"+prevCntrManagerNo+"' AND WORK_END_DATE = to_date('9999-12-31','YYYY-MM-DD') ");
//		}
//		//��������� ���Ӱ� ã�� ������ WORK_HIST�� ���ο� ROW �߰�
//		
//		query2.append("INSERT INTO EMP_WORK_HIST(EMP_NO,WORK_START_DATE,CNTR_NO,EMP_TP,BIZ_FILD) ");
//		query2.append("SELECT EMP_NO, trunc(sysdate) WORK_START_DATE, '"+cntrNo+"' CNTR_NO, EMP_TP, BIZ_FILD  FROM EMP_WORK_HIST ");
//		query2.append("WHERE EMP_NO='"+newCntrManagerNo+"' AND WORK_END_DATE = to_date('9999-12-31','YYYY-MM-DD') ");
//	
//		connection();
//		
//		try {
//			if(!isPrevEmpty) {
//				pstmt = con.prepareStatement(query1.toString());
//				rs = pstmt.executeQuery();
//				if(rs.next()) {
//					con.commit();
//				}
//				pstmt = con.prepareStatement(query2.toString());
//				rs = pstmt.executeQuery();
//				if(rs.next()) {
//					con.commit();
//				}
//			}
//			else {
//				pstmt = con.prepareStatement(query2.toString());
//				rs = pstmt.executeQuery();
//				if(rs.next()) {
//					con.commit();
//				}
//			}
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}
//		disconnection();
//	}
	
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

	
	//������ ���º� ������ ������ ���� Bottom Panel ����
	class BottomPanel extends JPanel{
		public BottomPanel() {
			setLayout(new FlowLayout(FlowLayout.LEFT));
			setBackground(MainFrame.bgc);
			
			add(vCageBig);
			add(xCageBig);
			add(vCageBigCount);
			add(vCageMid);
			add(xCageMid);
			add(vCageMidCount);
			add(vCageSmall);
			add(xCageSmall);
			add(vCageSmallCount);
			
		}
	}

	class CombinePanel extends JPanel {
		//������Ʈ 1, ������Ʈ 2, �г� ������ ��,�� margin ������ ���ֱ� ���� Flag
		public CombinePanel(Component[] cops, int borderWidth, int borderHeight) {
			//Margin�� �ʿ����� ���� ��
			
			setLayout(new FlowLayout(FlowLayout.LEFT,borderWidth,borderHeight));
			
			for (Component c: cops) {
				add(c);
			}
		}
	}
	
	class CenterListButtonListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			// TODO Auto-generated method stub
			if(e.getSource().equals(centerRegist)) {
				if(userCntrType.equals("h")) {
					try {
						NewCenterRegistration newCenterRegistration= new NewCenterRegistration();
						newCenterRegistration.addWindowListener(new WindowAdapter() {
	
							@Override
							public void windowClosed(WindowEvent e) {
								// TODO Auto-generated method stub
								super.windowClosed(e);
								GetCenterList();
							}
						
						
						});
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "���μ��������� ���͸� ����� �� �ֽ��ϴ�.", "�ȳ�", JOptionPane.WARNING_MESSAGE);
				}
			}
			
			if(e.getSource().equals(cageRegist)) {
				if(eCenterList.getSelectedRow()!=-1) {
					if(selectedCntrType.equals("h")) {
						JOptionPane.showMessageDialog(null, "���μ��Ϳ��� �������� ����� �� �����ϴ�.", "�ȳ�", JOptionPane.WARNING_MESSAGE);
					}
					else {
						//���μ��������̳� �Ϲݼ����� ������ & �繫�� �������� ��쿡�� ������ ��ϰ���
						if(userCntrType.equals("h")||(userCntrType.equals("n")&&(userWorkType.equals("c")||userWorkType.equals("o")))) {
							int clickedRow = eCenterList.getSelectedRow();
							String cntrName = (String)eCenterList.getValueAt(clickedRow, 0);
							String cntrNo = cntrNos.get(clickedRow);
							NewCageRegister newCageRegister = new NewCageRegister(cntrName,cntrNo);
							newCageRegister.addWindowListener(new WindowAdapter() {
			
								@Override
								public void windowClosed(WindowEvent e) {
									// TODO Auto-generated method stub
									super.windowClosed(e);
									GetCageList();
									GetCenter2(cntrNo);
								}
								
							});
						}
						else {
							JOptionPane.showMessageDialog(null, "��������ϱ����� �����ϴ�.", "�ȳ�", JOptionPane.WARNING_MESSAGE);	
						}
					}
				}
			}
//			if(e.getSource().equals(searchManager)) {
//				CenterManagerSearch centerManagerSearch = new CenterManagerSearch(xCenterManager);
//				centerManagerSearch.addWindowListener(new WindowAdapter() {
//
//					@Override
//					public void windowClosed(WindowEvent e) {
//						// TODO Auto-generated method stub
//						super.windowClosed(e);
//						newCntrManagerNo = centerManagerSearch.getCntrManagerNo();
//					}
//					
//				});
//				
//			}
			if(e.getSource().equals(modify)) {
				
				if(eCenterList.getSelectedRow()!=-1) {
					
					if(userCntrType.equals("h")||(userCntrType.equals("n")&&(userWorkType.equals("c")||userWorkType.equals("o")))) {
					
						if(!isClicked) {
							isClicked = true;
							
							modify.setText("Ȯ��");
							
							prevCntrName = xCenterName.getText();
							prevPhoneNum = xPhoneNum.getText();
							prevArea = xArea.getText();
							prevTimeOpen = String.join("",((String) cbOperTimeOpen.getSelectedItem()).split(":"));
							prevTimeClose = String.join("",((String) cbOperTimeClose.getSelectedItem()).split(":"));
							
							
							JComponent[] changeStatusComps = {xCenterName,xPhoneNum,xArea,cbOperTimeOpen,cbOperTimeClose};
							for(JComponent cop: changeStatusComps) {
								cop.setEnabled(true);
							}
						}
						else {
							isClicked = false;
							
							int result = JOptionPane.showConfirmDialog(null, "���������� �����Ͻðڽ��ϱ�?", "������������", JOptionPane.YES_NO_OPTION);
							if(result == JOptionPane.OK_OPTION) {
								String newCntrName = xCenterName.getText();
								String newPhoneNum = xPhoneNum.getText();
								String newArea = xArea.getText();
								String newTimeOpen = String.join("",((String) cbOperTimeOpen.getSelectedItem()).split(":"));
								String newTimeClose = String.join("",((String) cbOperTimeClose.getSelectedItem()).split(":"));
										
	//							if(prevCntrManagerNo == null) {
	//								prevCntrManagerNo="X";
	//							}
	//							
	//							if(newCntrManagerNo == null)
	//								newCntrManagerNo = prevCntrManagerNo;
								
								if(prevCntrName.equals(newCntrName)&&newPhoneNum.equals(prevPhoneNum)&&prevArea.equals(newArea)&&prevTimeOpen.equals(newTimeOpen)&&prevTimeClose.equals(prevTimeClose)) {
									JOptionPane.showMessageDialog(null, "����������� �����ϴ�.", "�˸�", JOptionPane.WARNING_MESSAGE);
								}
								else {
									
									if(NumberFormatCheck(newPhoneNum)) {
									
									UpdateCenter(newCntrName,newPhoneNum,newArea,newTimeOpen,newTimeClose);
									
	//								if(!prevCntrManagerNo.equals(newCntrManagerNo)) {
	//									if(prevCntrManagerNo.equals("X"))
	//										UpdateWorkHist(newCntrManagerNo, true);
	//									else
	//										UpdateWorkHist(newCntrManagerNo, false);
	//								}
									
									GetCenter();
									GetCenterList();
									model2.setNumRows(0);
									}
									else {
										JOptionPane.showMessageDialog(null, "��ȭ��ȣ ������ Ȯ���ϼ���.(������ \"-\" ���� ���� 13�ڸ�)", "�޽���", JOptionPane.ERROR_MESSAGE);
									}
								}
							}
							modify.setText("����");
							JComponent[] changeStatusComps = {xCenterName,xPhoneNum,xArea,cbOperTimeOpen,cbOperTimeClose};
							for(JComponent cop: changeStatusComps) {
								cop.setEnabled(false);
							}
							ClearAll();
							
						}
					}
					//������ ���� ���
					else {
						JOptionPane.showMessageDialog(null, "������������ ������ �����ϴ�.", "�ȳ�", JOptionPane.WARNING_MESSAGE);
					}
				}
			}

			if(e.getSource().equals(cancel)) {
				ClearAll();
			}
			
		}
		
	}
	
	private boolean NumberFormatCheck(String phoneNum) {
		
		if(phoneNum.length()>13)
			return false;
		else {
			String repStr = phoneNum.replaceAll("[0-9]", "");
			String[] splitStr = repStr.split("");
			System.out.println(splitStr.toString());
			System.out.println(splitStr.length);
			if(splitStr.length==2)
				return true;
			else return false;
		}
	}
	
	private void ClearAll() {
		
		isClicked = false;
		
		eCenterList.getSelectionModel().clearSelection();
		model2.setRowCount(0);
		
		modify.setText("����");
		JComponent[] changeStatusComps = {xCenterName,xPhoneNum,xArea,cbOperTimeOpen,cbOperTimeClose};
		for(JComponent cop: changeStatusComps) {
			cop.setEnabled(false);
		}
		
		JTextComponent[] jComps = {xCenterNum,xEstDate,xCenterName,xPhoneNum,xArea,xCenterManager,xCageBig,xCageMid,xCageSmall};
		
		cbOperTimeOpen.setSelectedItem("08:00");
		cbOperTimeClose.setSelectedItem("16:00");
		
		for(JTextComponent jComp: jComps) {
			jComp.setText("");
		}
	}
	
//  ���͸�����̺� Ŭ���� �߻��ϴ� ������
	class CenterListMouseListener extends MouseAdapter{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			super.mouseClicked(e);
			
//			https://blaseed.tistory.com/18			
			//1:��Ŭ��, 3:��Ŭ��
			if(e.getButton() == 1) {
				GetCageList();
				GetCenter();
				selectedCntrType = GetCntrType(selectedCntrNo);
			}
		}
	}

	
    private void ChangeFont(JComponent[] comps, Font font) {
    	for(JComponent comp: comps) {
    		comp.setFont(font);
    	}
    }
    
	
	public static void main(String[] args) {
		new CenterList(null,null);
	}
}


