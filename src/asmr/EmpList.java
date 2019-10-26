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
import java.io.IOException;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class EmpList extends JPanel {
	private JLabel vEmpList,vEmpNameSearch,vEmpInfo,vEmpNo,vBelongCenter,vEmpType,vWorkType,vEmpName,vBirthDate,vPhoneNum;
	private JTextField xEmpNameSearch,xEmpNo,xBelongCenter,xEmpName,xBirthDate,xPhoneNum;
	private JButton empSearch,centerSearch, modify, cancel, resign, register;
	private JComboBox<String> cbSearchType,cbEmptype,cbWorkType;
	
	private JTable eEmpList;
	private JScrollPane scrollpane;
	
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "asmr";
	private String password = "asmr";
	
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private ResultSetMetaData rsmd = null;
	
	private final String[] searchTypeDiv = {"이름","소속"};
	private final String[] empTypeDiv = {"정규직","계약직"};
	private final String[] workTypeDiv = {"센터장","관리직원","수의사","보호관리직원","사무직종사자","유기동물구조원"};
	
	private final String[] col1 = {"직원번호","이름","소속"};
	
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
		
		vEmpList = new JLabel("직원목록");
		vEmpList.setFont(new Font("나눔고딕", Font.BOLD, 24));
		vEmpList.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
		
		cbSearchType = new JComboBox<String>(searchTypeDiv);
		xEmpNameSearch = new JTextField(10);
		empSearch = new JButton("검색");
		empSearch.setBackground(blue);
		empSearch.setForeground(white);
		empSearch.addActionListener(empListButtonListener);
		
		register = new JButton("등록");
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
		
		vEmpInfo = new JLabel("직원정보");
		vEmpInfo.setFont(new Font("나눔고딕", Font.BOLD, 20));
		vEmpInfo.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		
		vEmpNo = new JLabel("직원번호");
		xEmpNo = new JTextField(10);
		xEmpNo.setEnabled(false);
		
		vBelongCenter = new JLabel("소속");
		xBelongCenter = new JTextField(10);
		xBelongCenter.setEnabled(false);
		centerSearch = new JButton("검색");
		centerSearch.setBackground(blue);
		centerSearch.setForeground(white);
		centerSearch.setEnabled(false);
		centerSearch.addActionListener(empListButtonListener);
		
		vEmpType = new JLabel("직원구분");
		cbEmptype = new JComboBox<String>(empTypeDiv);
		cbEmptype.setEnabled(false);
		
		vWorkType = new JLabel("업무분야");
		cbWorkType = new JComboBox<String>(workTypeDiv);
		cbWorkType.setEnabled(false);
		
		vEmpName = new JLabel("이름");
		xEmpName = new JTextField(10);
		xEmpName.setEnabled(false);
		
		vBirthDate = new JLabel("생년월일");
		xBirthDate = new JTextField(10);
		xBirthDate.setEnabled(false);
		
		vPhoneNum = new JLabel("전화번호");
		xPhoneNum = new JTextField(10);
		xPhoneNum.setEnabled(false);
		
		modify = new JButton("수정");
		modify.setBackground(blue);
		modify.setForeground(white);
		modify.addActionListener(empListButtonListener);
		
		cancel = new JButton("취소");
		cancel.addActionListener(empListButtonListener);
		
		resign = new JButton("퇴사");
		resign.setBackground(red);
		resign.setForeground(white);
		resign.addActionListener(empListButtonListener); 
		
		JComponent[] vComps = {vEmpNo,vBelongCenter,vEmpType,vWorkType,vEmpName,vBirthDate,vPhoneNum};
		ChangeFont(vComps, new Font("나눔고딕", Font.PLAIN, 16));
		
		JComponent[] bComps = {empSearch,centerSearch, modify, cancel, resign, register};
		ChangeFont(bComps, new Font("나눔고딕", Font.BOLD, 16));
		
		EmpListView();
		
		GetEmpList();
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
				String searchType = (String)cbSearchType.getSelectedItem();
				String typedName = xEmpNameSearch.getText();
				switch(searchType) {
				case "이름":
					SearchEmp(typedName,true);
					break;
				case "소속":
					SearchEmp(typedName,false);
					break;
				}
			}
			else if(e.getSource().equals(register)) {
				try {
					new EmpRegister();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if(e.getSource().equals(centerSearch)) {
				new CenterSearch(xBelongCenter);
			}
			else if(e.getSource().equals(modify)) {
				modify.setText("확인");
				JComponent[] changeStatusComps = {cbEmptype,cbWorkType,centerSearch};
				for(JComponent cop: changeStatusComps) {
					cop.setEnabled(true);
				}
			}
			else if(e.getSource().equals(cancel)) {
				modify.setText("수정");
				JComponent[] changeStatusComps = {cbEmptype,cbWorkType,centerSearch};
				for(JComponent cop: changeStatusComps) {
					cop.setEnabled(false);
				}
			}
			else if(e.getSource().equals(resign)) {
				
			}
		}
		
	}
	
//  직원 목록테이블 클릭시 발생하는 리스너
	class EmpListMouseListener extends MouseAdapter{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			super.mouseClicked(e);
			
//			https://blaseed.tistory.com/18			
			//1:좌클릭, 3:우클릭
			if(e.getButton() == 1) {
				int clickedRow = eEmpList.getSelectedRow();
				String empNo = (String)eEmpList.getValueAt(clickedRow, 0);
				String cntrName = (String)eEmpList.getValueAt(clickedRow, 2);
				GetEmp(empNo, cntrName);
			}
		}
	}
	
	//두개의 컴포넌트를 하나의 패널로 묶는 JPanel
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
	
	// 직원 목록 가져오기
	private void GetEmpList() {
		model1.setRowCount(0);
		
		connection();
		
		try {
			StringBuffer query= new StringBuffer("SELECT e.EMP_NO emp_no, wh.WORK_START_DATE, e.EMP_NAME emp_name, c.CNTR_NAME cntr_name ");
			query.append("FROM ( SELECT EMP_NO, EMP_NAME FROM EMP) e INNER JOIN( ");
			query.append("	SELECT  EMP_NO, WORK_START_DATE, CNTR_NO ");
			query.append("	FROM EMP_WORK_HIST ");
			query.append("	WHERE WORK_END_DATE=to_date('9999-12-31','YYYY-MM-DD')) wh ");
			query.append("	ON e.EMP_NO = wh.EMP_NO INNER JOIN( ");
			query.append("		SELECT CNTR_NO, CNTR_NAME FROM CNTR) c ");
			query.append("		ON wh.CNTR_NO = c.CNTR_NO ");
			query.append("ORDER BY 1,2 ASC ");
			
			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {		
				model1.addRow(new Object[] {rs.getString("emp_no"),rs.getString("emp_name"),rs.getString("cntr_name")});
			}
		
		}catch(Exception e1) {
			e1.printStackTrace();
		}
		
		disconnection();
	}
	
	private void GetEmp(String empNo, String cntrName) {
		connection();
		
		try {
			StringBuffer query= new StringBuffer("SELECT e.EMP_NO , c.CNTR_NAME, wh.EMP_TP, wh.BIZ_FILD, e.EMP_NAME, e.BRTH_YEAR_MNTH_DAY BDATE, e.TEL_NO ");
			query.append("FROM ( ");
			query.append("	SELECT EMP_NO, EMP_NAME, BRTH_YEAR_MNTH_DAY, TEL_NO FROM EMP ");
			query.append("	WHERE EMP_NO='"+empNo+"') e LEFT OUTER JOIN ( ");
			query.append("		SELECT EMP_NO, WORK_END_DATE, CNTR_NO, EMP_TP, BIZ_FILD FROM EMP_WORK_HIST) wh ");
			query.append("		ON e.EMP_NO = wh.EMP_NO ");
			query.append("		AND wh.WORK_END_DATE=to_date('9999-12-31','YYYY-MM-DD') INNER JOIN ( ");
			query.append("			SELECT * FROM CNTR WHERE CNTR_NAME='"+cntrName+"') c ");
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
					korEmpType = "정규직";
					break;
				case "c":
					korEmpType = "계약직";
					break;
				}
				
				switch(bizFild) {
				case "c":
					korBizFild = "센터장";
					break;
				case "m":
					korBizFild = "관리직원";
					break;
				case "d":
					korBizFild = "수의사";
					break;
				case "o":
					korBizFild = "사무직종사자";
					break;
				case "r":
					korBizFild = "유기동물구조원";
					break;
				case "p":
					korBizFild = "보호관리직원";
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
	
	public static void main(String[] args) {
		new EmpList();
	}
	
}
