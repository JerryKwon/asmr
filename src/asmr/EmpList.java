package asmr;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

public class EmpList extends JPanel {
	private JLabel vEmpList,vEmpNameSearch,vEmpInfo,vEmpNo,vBelongCenter,vEmpType,vWorkType,vEmpName,vBirthDate,vPhoneNum;
	private JTextField xEmpNameSearch,xEmpNo,xBelongCenter,xEmpName,xBirthDate,xPhoneNum;
	private JButton empSearch,centerSearch, modify, cancel, resign, register;
	private JComboBox<String> cbSearchType,cbEmptype,cbWorkType;
	
	private JTable eEmpList;
	private JScrollPane scrollpane;
	
	private ArrayList<String> empNos;
	private ArrayList<String> workStartDates;
	private ArrayList<String> cntrNos;
	private String empNo = null;
	
	
	private boolean isClicked = false;
	
	private String prevCntrNo = null; 
	private String prevEmpType = null; 
	private String prevWorkType = null;
	
	CenterSearch centerSearchClass;
	
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "asmr";
	private String password = "asmr";
	
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private ResultSetMetaData rsmd = null;
	
	private final String[] searchTypeDiv = {"�̸�","�Ҽ�"};
	private final String[] empTypeDiv = {"������","�����"};
//	private final String[] workTypeDiv = {"������","��������","���ǻ�","��ȣ��������","�繫��������","���⵿��������"};
	
	private final String[] col1 = {"������ȣ","�̸�","�Ҽ�"};
	
	private DefaultTableModel model1 = new DefaultTableModel(col1,0);
	
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	private Color red = new Color(217,0,27);
	
	EmpListButtonListener empListButtonListener;
	EmpListMouseListener empListMouseListener;
	
	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;
	
	
	public EmpList() {
		
		gridBagLayout = new GridBagLayout();		
		gridBagConstraints = new GridBagConstraints();

		empListButtonListener = new EmpListButtonListener();
		empListMouseListener = new EmpListMouseListener();
		
		empNos = new ArrayList<String>();
		workStartDates = new ArrayList<String>();
		cntrNos = new ArrayList<String>();
		
		vEmpList = new JLabel("�������");
		vEmpList.setFont(new Font("�������", Font.BOLD, 24));
		vEmpList.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
		
		cbSearchType = new JComboBox<String>(searchTypeDiv);
		xEmpNameSearch = new JTextField(10);
		empSearch = new JButton("�˻�");
		empSearch.setBackground(blue);
		empSearch.setForeground(white);
		empSearch.addActionListener(empListButtonListener);
		
		register = new JButton("���");
		register.setBackground(blue);
		register.setForeground(white);
		register.addActionListener(empListButtonListener);
		
//		eEmpList = new JTable(model1);
		eEmpList = new JTable(model1) {
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
	    eEmpList.addMouseListener(empListMouseListener);
		scrollpane = new JScrollPane(eEmpList);
		scrollpane.setPreferredSize(new Dimension(600,200));
		
		vEmpInfo = new JLabel("��������");
		vEmpInfo.setFont(new Font("�������", Font.BOLD, 20));
		vEmpInfo.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		
		vEmpNo = new JLabel("������ȣ");
		xEmpNo = new JTextField(10);
		xEmpNo.setEnabled(false);
		
		vBelongCenter = new JLabel("�Ҽ�");
		xBelongCenter = new JTextField(10);
		xBelongCenter.setEnabled(false);
		centerSearch = new JButton("�˻�");
		centerSearch.setBackground(blue);
		centerSearch.setForeground(white);
		centerSearch.setEnabled(false);
		centerSearch.addActionListener(empListButtonListener);
		
		vEmpType = new JLabel("��������");
		cbEmptype = new JComboBox<String>(empTypeDiv);
		cbEmptype.setEnabled(false);
		
		vWorkType = new JLabel("�����о�");
		cbWorkType = new JComboBox<String>();
		cbWorkType.setEnabled(false);
		
		vEmpName = new JLabel("�̸�");
		xEmpName = new JTextField(10);
		xEmpName.setEnabled(false);
		
		vBirthDate = new JLabel("�������");
		xBirthDate = new JTextField(10);
		xBirthDate.setEnabled(false);
		
		vPhoneNum = new JLabel("��ȭ��ȣ");
		xPhoneNum = new JTextField(10);
		xPhoneNum.setEnabled(false);
		
		modify = new JButton("����");
		modify.setBackground(blue);
		modify.setForeground(white);
		modify.addActionListener(empListButtonListener);
		
		cancel = new JButton("���");
		cancel.addActionListener(empListButtonListener);
		
		resign = new JButton("���");
		resign.setBackground(red);
		resign.setForeground(white);
		resign.addActionListener(empListButtonListener); 
		
		JComponent[] vComps = {vEmpNo,vBelongCenter,vEmpType,vWorkType,vEmpName,vBirthDate,vPhoneNum};
		ChangeFont(vComps, new Font("�������", Font.PLAIN, 16));
		
		JComponent[] bComps = {empSearch,centerSearch, modify, cancel, resign, register};
		ChangeFont(bComps, new Font("�������", Font.BOLD, 16));
		
		GetEmpList();
		
		EmpListView();
	}
	
	private void EmpListView() {

		setLayout(gridBagLayout);
		
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.ipadx = 7;
		
		gridBagConstraints.weightx=1.0;
		gridBagConstraints.weighty=1.0;

		gridbagAdd(vEmpList, 0, 0, 1, 1);
		
//		cbSearchType.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
//		gridbagAdd(cbSearchType, 0, 1, 1, 1);
		
		Component[] cpts = {cbSearchType,xEmpNameSearch,empSearch};
		CombinePanel cp1 = new CombinePanel(cpts,5,0);
		cp1.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		gridbagAdd(cp1, 0, 1, 2, 1);
//		gridbagAdd(xEmpNameSearch, 1, 1, 1, 1);
//		gridbagAdd(empSearch, 2, 1, 1, 1);
		
		gridbagAdd(register, 5, 1, 1, 1);
		
		JPanel plainPanel = new JPanel();
		plainPanel.add(scrollpane);
		plainPanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 0));
		gridbagAdd(plainPanel, 0, 2, 6, 1);
		
		gridbagAdd(vEmpInfo, 0, 3, 1, 1);
		
		gridbagAdd(vEmpNo, 0, 4, 1, 1);
		gridbagAdd(xEmpNo, 1, 4, 1, 1);
		
		gridbagAdd(vBelongCenter, 2, 4, 1, 1);
		
//		Component[] cops1 = {xBelongCenter,centerSearch};
//		
//		CombinePanel combinePanel1 = new CombinePanel(cops1, false) ;
//		gridbagAdd(combinePanel1, 3, 4, 1, 1);

		gridbagAdd(xBelongCenter, 3, 4, 1, 1);
		gridbagAdd(centerSearch, 4, 4, 1, 1);
		
		gridbagAdd(vEmpType, 0, 5, 1, 1);
		gridbagAdd(cbEmptype, 1, 5, 1, 1);

		gridbagAdd(vWorkType, 2, 5, 1, 1);
		gridbagAdd(cbWorkType, 3, 5, 1, 1);
		
//		CombinePanel combinePanel2 =new CombinePanel(cops2, true);
//		gridbagAdd(combinePanel2, 2, 5, 3, 1);
//		
		gridbagAdd(vEmpName, 0, 6, 1, 1);
		gridbagAdd(xEmpName, 1, 6, 1, 1);
		
		gridbagAdd(vBirthDate, 0, 7, 1, 1);
		gridbagAdd(xBirthDate, 1, 7, 1, 1);
		
		gridbagAdd(vPhoneNum, 2, 7, 1, 1);
		gridbagAdd(xPhoneNum, 3, 7, 1, 1);
		
		Component[] buttons = {modify,cancel,resign};
		CombinePanel combineButtonPanel = new CombinePanel(buttons, 15,0);
		combineButtonPanel.setBorder(BorderFactory.createEmptyBorder(10,175,0,0));
		gridbagAdd(combineButtonPanel, 0, 8, 5, 1);
		
	}
	
	private void gridbagAdd(Component c, int x, int y, int w , int h) {
		
		gridBagConstraints.gridx = x;
		gridBagConstraints.gridy = y;
		gridBagConstraints.gridwidth = w;
		gridBagConstraints.gridheight = h;
		
		gridBagLayout.setConstraints(c, gridBagConstraints);
		
		add(c);
	}
	
	class EmpListButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource().equals(empSearch)) {
				if(xEmpNameSearch.getText().trim().isEmpty()) {
					GetEmpList();
				}
				else {
					String searchType = (String)cbSearchType.getSelectedItem();
					String typedName = xEmpNameSearch.getText();
					switch(searchType) {
					case "�̸�":
						SearchEmp(typedName,true);
						break;
					case "�Ҽ�":
						SearchEmp(typedName,false);
						break;
					}
				}
			}
			else if(e.getSource().equals(register)) {
				try {
					EmpRegister empRegister = new EmpRegister();
					empRegister.addWindowListener(new WindowAdapter() {

						@Override
						public void windowClosed(WindowEvent e) {
							// TODO Auto-generated method stub
							super.windowClosed(e);
							GetEmpList();
						}
						
					});
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if(e.getSource().equals(centerSearch)) {
				centerSearchClass = new CenterSearch(xBelongCenter);
			}
			else if(e.getSource().equals(modify)) {
				
				String newCntrNo = null;
				String newEmpType = null;
				String newWorkType = null;
				
				if(eEmpList.getSelectedRow()!=-1) {
					
					if(!isClicked) {
					
						isClicked = true;
						
						int clickedRow = eEmpList.getSelectedRow();
						
						prevCntrNo = cntrNos.get(clickedRow);
						prevEmpType = (String)cbEmptype.getSelectedItem();
						prevWorkType = (String)cbWorkType.getSelectedItem();
						
						modify.setText("Ȯ��");
						JComponent[] changeStatusComps = {cbEmptype,cbWorkType,centerSearch};
						for(JComponent cop: changeStatusComps) {
							cop.setEnabled(true);
						}
					}
					else {
						isClicked = false;
						
						int result = JOptionPane.showConfirmDialog(null, "���������� �����Ͻðڽ��ϱ�?", "������������", JOptionPane.YES_NO_OPTION);
						if(result == JOptionPane.OK_OPTION) {
							try {
								newCntrNo = centerSearchClass.getCntrNo();
							}catch(NullPointerException e1) {
								newCntrNo=prevCntrNo;
							}
							newEmpType = (String)cbEmptype.getSelectedItem();
							newWorkType = (String)cbWorkType.getSelectedItem();
						}
						
						if(prevCntrNo.equals(newCntrNo)&&prevEmpType.equals(newEmpType)&&prevWorkType.equals(newWorkType)) {
							JOptionPane.showMessageDialog(null, "����������� �����ϴ�.", "�˸�", JOptionPane.WARNING_MESSAGE);
							clearAll();
						}
						else {
							UpdateEmp(empNo,newCntrNo,newEmpType,newWorkType);
							
							GetEmpList();
							clearAll();
						}
					}
				}
			}
			else if(e.getSource().equals(cancel)) {
				clearAll();
			}
			else if(e.getSource().equals(resign)) {
				
				int clickedRow = eEmpList.getSelectedRow();
				if(clickedRow != -1) {
					String empNo = empNos.get(clickedRow);
					String workStartDate = workStartDates.get(clickedRow).split(" ")[0];
					int result = JOptionPane.showConfirmDialog(null, "�ش� ������ ���ó���Ͻðڽ��ϱ�?", "��� ó��", JOptionPane.YES_NO_OPTION);
					if(result == JOptionPane.OK_OPTION) {
						ResignEmp(empNo,workStartDate);
						GetEmpList();
					}
				}
			}
		}
		
	}
	
	private void clearAll() {
		
		modify.setText("����");
		isClicked=false;
		
		JComponent[] changeStatusComps = {cbEmptype,cbWorkType,centerSearch};
		for(JComponent cop: changeStatusComps) {
			cop.setEnabled(false);
		}
		
		JTextComponent[] jcomps = {xEmpNo,xBelongCenter,xEmpName,xBirthDate,xPhoneNum};
		for(JTextComponent jcomp:jcomps) {
			jcomp.setText("");
		}
		cbEmptype.setSelectedItem("������");
		cbWorkType.setSelectedItem("������");
		eEmpList.getSelectionModel().clearSelection();

	}
	
	
	private void UpdateEmp(String empNo, String newCntrNo, String newEmpType,String newWorkType) {
		String engEmpType = null;
		String engWorkType = null;
		
		switch(newEmpType) {
		case "������":
			engEmpType = "f";
			break;
		case "�����":
			engEmpType = "c";
			break;
		}
		switch(newWorkType) {
		case "������":
			engWorkType = "c";
			break;
		case "��������":
			engWorkType = "m";
			break;
		case "���ǻ�":
			engWorkType = "d";
			break;
		case "��ȣ��������":
			engWorkType = "p";
			break;
		case "�繫��������":
			engWorkType = "o";
			break;
		case "���⵿��������":
			engWorkType = "r";
			break;
		}
	
		//������ ���� ��å�� Update�Ͽ� �����ϴ� ����
		StringBuffer query1 = new StringBuffer("UPDATE EMP_WORK_HIST SET WORK_END_DATE=trunc(sysdate) WHERE EMP_NO=? AND WORK_END_DATE=to_date('9999-12-31','YYYY-MM-DD')");
		
		//������ ���ο� ��å�� Insert�ϴ� ����
		StringBuffer query2 = new StringBuffer("INSERT INTO EMP_WORK_HIST(EMP_NO,WORK_START_DATE,CNTR_NO,EMP_TP,BIZ_FILD) ");
		query2.append("SELECT '"+empNo+"' EMP_NO, trunc(SYSDATE) WORK_START_DATE, '"+newCntrNo+"' CNTR_NO, '"+engEmpType+"' EMP_TP, '"+engWorkType+"' BIZ_FILD FROM DUAL");
		
		connection();
		
		try {
			pstmt = con.prepareStatement(query1.toString());
			pstmt.setString(1, empNo);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				con.commit();
			}
			
			pstmt = con.prepareStatement(query2.toString());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				con.commit();
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		disconnection();
	
	}
	
//  ���� ������̺� Ŭ���� �߻��ϴ� ������
	class EmpListMouseListener extends MouseAdapter{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			super.mouseClicked(e);
			
//			https://blaseed.tistory.com/18			
			//1:��Ŭ��, 3:��Ŭ��
			if(e.getButton() == 1) {
				int clickedRow = eEmpList.getSelectedRow();
				empNo = (String)eEmpList.getValueAt(clickedRow, 0);
//				String cntrName = (String)eEmpList.getValueAt(clickedRow, 2);
				String cntrNo = cntrNos.get(clickedRow);
				GetEmp(empNo, cntrNo);
			}
		}
	}
	
	//�ΰ��� ������Ʈ�� �ϳ��� �гη� ���� JPanel
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
	
	//���� ���ó��
	private void ResignEmp(String empNo,String workStartDate) {
		
		StringBuffer query =new StringBuffer("UPDATE EMP_WORK_HIST ");
		query.append("SET WORK_END_DATE=trunc(sysdate) ");
		query.append("WHERE emp_no='"+empNo+"' AND work_start_date=to_date('"+workStartDate+"','YYYY-MM-DD') ");
		
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
	}
	
	// ���� ��� ��������
	private void GetEmpList() {
		empNos.clear();
		workStartDates.clear();
		cntrNos.clear();
		model1.setRowCount(0);
		
		connection();
		
		try {
			StringBuffer query= new StringBuffer("SELECT c.CNTR_NO, e.EMP_NO emp_no, wh.WORK_START_DATE, e.EMP_NAME emp_name, c.CNTR_NAME cntr_name ");
			query.append("FROM ( SELECT EMP_NO, EMP_NAME FROM EMP) e INNER JOIN( ");
			query.append("	SELECT  EMP_NO, WORK_START_DATE, CNTR_NO ");
			query.append("	FROM EMP_WORK_HIST ");
			query.append("	WHERE WORK_END_DATE=to_date('9999-12-31','YYYY-MM-DD')) wh ");
			query.append("	ON e.EMP_NO = wh.EMP_NO INNER JOIN( ");
			query.append("		SELECT CNTR_NO, CNTR_NAME FROM CNTR) c ");
			query.append("		ON wh.CNTR_NO = c.CNTR_NO ");
			query.append("ORDER BY 1,2,3 ASC ");
			
			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {		
				empNos.add(rs.getString("emp_no"));
				workStartDates.add(rs.getString("work_start_date"));
				cntrNos.add(rs.getString("cntr_no"));
				
				model1.addRow(new Object[] {rs.getString("emp_no"),rs.getString("emp_name"),rs.getString("cntr_name")});
			}
		
		}catch(Exception e1) {
			e1.printStackTrace();
		}
		
		disconnection();
	}
	
	private void GetEmp(String empNo, String cntrNo) {
		
		cbWorkType.removeAllItems();
		
		String cntrType = GetCntrType(cntrNo);
		
		switch(cntrType) {
		case"h":
			for(String type:new String[] {"������","��������"}) {
				cbWorkType.addItem(type);
			} 
			break;
		case"n":
			for(String type:new String[] {"������","�繫��������","���ǻ�","��ȣ��������","���⵿��������"}) {
				cbWorkType.addItem(type);
			} 
			break;
		}
		
		connection();
		
		try {
			StringBuffer query= new StringBuffer("SELECT e.EMP_NO , c.CNTR_NAME, wh.EMP_TP, wh.BIZ_FILD, e.EMP_NAME, e.BRTH_YEAR_MNTH_DAY BDATE, e.TEL_NO ");
			query.append("FROM ( ");
			query.append("	SELECT EMP_NO, EMP_NAME, BRTH_YEAR_MNTH_DAY, TEL_NO FROM EMP ");
			query.append("	WHERE EMP_NO='"+empNo+"') e LEFT OUTER JOIN ( ");
			query.append("		SELECT EMP_NO, WORK_END_DATE, CNTR_NO, EMP_TP, BIZ_FILD FROM EMP_WORK_HIST) wh ");
			query.append("		ON e.EMP_NO = wh.EMP_NO ");
			query.append("		AND wh.WORK_END_DATE=to_date('9999-12-31','YYYY-MM-DD') INNER JOIN ( ");
			query.append("			SELECT * FROM CNTR WHERE CNTR_NO='"+cntrNo+"') c ");
			query.append("			ON wh.CNTR_NO = c.CNTR_NO ");
				
			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();

			
			while(rs.next()) {

				String empType = rs.getString("EMP_TP");
				String korEmpType = null;
				
				String bizFild = rs.getString("BIZ_FILD");
				String korBizFild = null;
				
				switch(empType) {
				case "f":
					korEmpType = "������";
					break;
				case "c":
					korEmpType = "�����";
					break;
				}
				
				switch(bizFild) {
				case "c":
					korBizFild = "������";
					break;
				case "m":
					korBizFild = "��������";
					break;
				case "d":
					korBizFild = "���ǻ�";
					break;
				case "o":
					korBizFild = "�繫��������";
					break;
				case "r":
					korBizFild = "���⵿��������";
					break;
				case "p":
					korBizFild = "��ȣ��������";
					break;
				}
				
				xEmpNo.setText(rs.getString("EMP_NO"));
				xBelongCenter.setText(rs.getString("CNTR_NAME"));
				cbEmptype.setSelectedItem(korEmpType);
				cbWorkType.setSelectedItem(korBizFild);
				xEmpName.setText(rs.getString("EMP_NAME"));
				xBirthDate.setText(rs.getString("BDATE").split(" ")[0]);
				xPhoneNum.setText(rs.getString("TEL_NO"));
				
			}
				
		}catch(Exception e2) {
			e2.printStackTrace();
		}
		
		disconnection();
	}
	
	private String GetCntrType(String cntrNo) {
		String cntrType = null;
		
		StringBuffer query = new StringBuffer("SELECT CNTR_TP FROM CNTR WHERE CNTR_NO='"+cntrNo+"' ");
		
		connection();
		
		try {
			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				cntrType = rs.getString("CNTR_TP");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		disconnection();
		
		return cntrType;
	}
	
	private void SearchEmp(String name, boolean isEmp) {
		model1.setRowCount(0);
		
		connection();
		
		StringBuffer query = new StringBuffer();
		
		if(isEmp) {
			query.append("SELECT e.EMP_NO, wh.WORK_START_DATE, e.EMP_NAME, c.CNTR_NAME ");
			query.append("FROM ( ");
			query.append("	SELECT EMP_NO, EMP_NAME  ");
			query.append("	FROM EMP ");
			query.append("	WHERE EMP_NAME='"+name+"') e INNER JOIN( ");
			query.append("		SELECT  EMP_NO, WORK_START_DATE, CNTR_NO ");
			query.append("		FROM EMP_WORK_HIST ");
			query.append("		WHERE WORK_END_DATE=to_date('9999-12-31','YYYY-MM-DD')) wh ");
			query.append("		ON e.EMP_NO = wh.EMP_NO INNER JOIN( ");
			query.append("			SELECT CNTR_NO, CNTR_NAME FROM CNTR) c ");
			query.append("			ON wh.CNTR_NO = c.CNTR_NO ");
			query.append("ORDER BY 1,2 ASC ");
			
		}
		else {
			query.append("SELECT e.EMP_NO, wh.WORK_START_DATE, e.EMP_NAME, c.CNTR_NAME ");
			query.append("FROM ( ");
			query.append("	SELECT EMP_NO, EMP_NAME  ");
			query.append("	FROM EMP) e INNER JOIN( ");
			query.append("		SELECT  EMP_NO, WORK_START_DATE, CNTR_NO ");
			query.append("		FROM EMP_WORK_HIST ");
			query.append("		WHERE WORK_END_DATE=to_date('9999-12-31','YYYY-MM-DD')) wh ");
			query.append("		ON e.EMP_NO = wh.EMP_NO INNER JOIN( ");
			query.append("			SELECT CNTR_NO, CNTR_NAME ");
			query.append("			FROM CNTR ");
			query.append("			WHERE CNTR_NAME='"+name+"') c ");
			query.append("			ON wh.CNTR_NO = c.CNTR_NO ");
			query.append("ORDER BY 1,2 ASC ");
			
		}
		
		try {
		pstmt = con.prepareStatement(query.toString());
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			model1.addRow(new Object[] {rs.getString("emp_no"),rs.getString("emp_name"),rs.getString("cntr_name")});
		}
		}catch(Exception e) {
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
		
		
		
	private void ChangeFont(JComponent[] comps, Font font) {
    	for(JComponent comp: comps) {
    		comp.setFont(font);
    	}
    }
	
	public static void main(String[] args) {
		new EmpList();
	}
	
}
