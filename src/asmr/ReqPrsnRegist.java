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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.management.Query;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ReqPrsnRegist extends JFrame {
	private JLabel vReqPrsnType, vCustSearch, vNonCustRegist, vName, vAddress, vPhoneNum;
	private JTextField xCustSearch, xName, xAddress, xPhoneNum;
	private JButton custSearch, addressSearch, returning, cancel;
	private JRadioButton cust,nonCust;
	private JComboBox<String> cbCustSearch;
	
	private static String hint = "- 를 포함한 13자리 숫자";
	
	private String abanNo;
	private boolean isCust = true;
	
	private JTable eCustList;
	private JScrollPane custListScroll;
	
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "asmr";
	private String password = "asmr";
	
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private ResultSetMetaData rsmd = null;
	
	private final String[] col1 = {"이름","아이디","주소"};
	
	private DefaultTableModel model1 = new DefaultTableModel(col1,0);
	
	private final String[] custSearchDiv = {"이름","아이디"};
	
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	private Color red = new Color(217,0,27);
	
	CustItemListener custItemListener;
	NonCustItemListener nonCustItemListener;
	ReqPrsnRegistButtonListener reqPrsnRegistButtonListener;
	ReqPrsnRegistMouseListener reqRegistMouseListener;
	
	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;
	
	public ReqPrsnRegist(String abanNo) {
		
		gridBagLayout = new GridBagLayout();		
		gridBagConstraints = new GridBagConstraints();
		
		reqRegistMouseListener = new ReqPrsnRegistMouseListener();
		reqPrsnRegistButtonListener = new ReqPrsnRegistButtonListener();
		custItemListener = new CustItemListener();
		nonCustItemListener = new NonCustItemListener();
		
		this.abanNo = abanNo;
		
		vReqPrsnType = new JLabel("인수자구분");
		
		cust = new JRadioButton("회원");
		cust.addItemListener(custItemListener);
		cust.setBackground(MainFrame.bgc);
		nonCust = new JRadioButton("비회원");
		nonCust.setBackground(MainFrame.bgc);
		nonCust.addItemListener(nonCustItemListener);
		
		vCustSearch = new JLabel("회원검색");
		vCustSearch.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		
		cbCustSearch = new JComboBox<String>(custSearchDiv);
		xCustSearch = new JTextField(10);
		custSearch = new JButton("검색");
		custSearch.setBackground(blue);
		custSearch.setForeground(white);
		custSearch.addActionListener(reqPrsnRegistButtonListener);
		
//		eCustList = new JTable(model1);
		eCustList = new JTable(model1) {
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
		eCustList.addMouseListener(reqRegistMouseListener);
		custListScroll = new JScrollPane(eCustList);
		custListScroll.setPreferredSize(new Dimension(350,100));
		eCustList.getColumnModel().getColumn(0).setPreferredWidth(75);
		eCustList.getColumnModel().getColumn(1).setPreferredWidth(100);
		eCustList.getColumnModel().getColumn(2).setPreferredWidth(175);
		
		
		vNonCustRegist = new JLabel("비회원등록");
		vNonCustRegist.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		
		vName = new JLabel("성명");
		xName = new JTextField(10);
		
		vAddress = new JLabel("주소");
		xAddress = new JTextField(15);
		xAddress.setEditable(false);
		addressSearch = new JButton("검색");
		addressSearch.setBackground(blue);
		addressSearch.setForeground(white);
		addressSearch.addActionListener(reqPrsnRegistButtonListener);
		
		vPhoneNum = new JLabel("전화번호");
		xPhoneNum = new JTextField(10);
		xPhoneNum.setText(hint);
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
		
		returning = new JButton("반환");
		returning.setBackground(red);
		returning.setForeground(white);
		returning.addActionListener(reqPrsnRegistButtonListener);
		
		cancel = new JButton("취소");
		cancel.addActionListener(reqPrsnRegistButtonListener);
		
		JComponent[] vComps1= {vReqPrsnType, vCustSearch, vNonCustRegist};
		ChangeFont(vComps1, new Font("나눔고딕", Font.BOLD, 16));
		
		JComponent[] vComps2 = {vName, vAddress, vPhoneNum,cust,nonCust};
		ChangeFont(vComps2, new Font("나눔고딕", Font.PLAIN, 16));
		
		JComponent[] bComps = {custSearch, addressSearch, returning, cancel};
		ChangeFont(bComps, new Font("나눔고딕", Font.BOLD, 12));
		
		//기본적으로 회원 활성화
		activateCust();
		GetCustList();
		
		ReqPrsnRegistView();
		
	}
	
	private void ReqPrsnRegistView() {
		setLayout(gridBagLayout);
		setTitle("인수자등록");
		this.getContentPane().setBackground(MainFrame.bgc);
		
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.ipadx = 7;
		
		gridBagConstraints.weightx=1.0;
		gridBagConstraints.weighty=1.0;
		
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		
		gridbagAdd(vReqPrsnType, 0, 0, 1, 1);
		
		gridbagAdd(cust, 0, 1, 1, 1);
		gridbagAdd(nonCust, 1, 1, 1, 1);
		
		gridbagAdd(vCustSearch, 0, 2, 1, 1);
		
		gridbagAdd(cbCustSearch, 0, 3, 1, 1);
		gridbagAdd(xCustSearch, 1, 3, 1, 1);
		gridbagAdd(custSearch, 2, 3, 1, 1);
		
		gridbagAdd(custListScroll, 0, 4, 4, 1);
		
		gridbagAdd(vNonCustRegist, 0, 5, 1, 1);
		
		gridbagAdd(vName, 0, 6, 1, 1);
		gridbagAdd(xName, 1, 6, 1, 1);
		
		gridbagAdd(vAddress, 0, 7, 1, 1);
		gridbagAdd(xAddress, 1, 7, 1, 1);
		gridbagAdd(addressSearch, 2, 7, 1, 1);
		
		gridbagAdd(vPhoneNum, 0, 8, 1, 1);
		gridbagAdd(xPhoneNum, 1, 8, 1, 1);
		
		Component[] cops = {returning, cancel};
		CombinePanel buttonPanel = new CombinePanel(cops, true);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 100, 0, 0));
		buttonPanel.setBackground(MainFrame.bgc);
		gridbagAdd(buttonPanel, 0, 9, 4, 1);
		
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
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
	
	class CustItemListener implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			if(e.getStateChange()==ItemEvent.SELECTED) {
				activateCust();
			}
//			else if(e.getStateChange()==ItemEvent.DESELECTED) {
//				
//			}
		}
		
	}
	
	class NonCustItemListener implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			if(e.getStateChange()==ItemEvent.SELECTED) {
				activateNonCust();
			}
//			else if(e.getStateChange()==ItemEvent.DESELECTED) {
//				
//			}
		}
		
	}
	
	private void activateCust() {
		isCust = true;
		
		nonCust.setSelected(false);
		cust.setSelected(true);
		
		xName.setEnabled(false);
		addressSearch.setEnabled(false);
		xPhoneNum.setEnabled(false);
		
		
		xCustSearch.setEnabled(true);
		custSearch.setEnabled(true);
		cbCustSearch.setEnabled(true);
		eCustList.setEnabled(true);
	}
	
	private void activateNonCust() {
		isCust= false;
		
		cust.setSelected(false);
		nonCust.setSelected(true);
		
		xName.setEnabled(true);
		addressSearch.setEnabled(true);
		xPhoneNum.setEnabled(true);
		
		xCustSearch.setEnabled(false);
		custSearch.setEnabled(false);
		cbCustSearch.setEnabled(false);
		eCustList.setEnabled(false);
		eCustList.getSelectionModel().clearSelection();
		
	}
	
	class ReqPrsnRegistButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource().equals(custSearch)) {
				if(xCustSearch.getText().trim().isEmpty()) {
					model1.setRowCount(0);
					GetCustList();
				}
				else {
					String text = xCustSearch.getText();
					
					String custType = (String)cbCustSearch.getSelectedItem();
					switch(custType) {
					case "이름":
						SearchCust(text, true);
						break;
					case "아이디":
						SearchCust(text, false);
						break;
					}
				}
			}
			else if(e.getSource().equals(addressSearch)) {
				new NewAddressSearch(xAddress);
			}
			else if(e.getSource().equals(returning)) {
				
				int result = JOptionPane.showConfirmDialog(null, "해당 동물을 반환처리 하시겠습니까?", "반환처리", JOptionPane.YES_NO_OPTION);
				
				if(result == JOptionPane.OK_OPTION) {
					
					if(isCust) {
						if(eCustList.getSelectedRow()==-1) {
							JOptionPane.showMessageDialog(null, "[회원] 목록에서 인수자를 선택하세요.", "메시지", JOptionPane.ERROR_MESSAGE);
						}
						else {
							ReturnProtAni();
							dispose();
						}
					}
					else {
						String name = xName.getText().trim();
						String address = xAddress.getText().trim();
						String phoneNum = xPhoneNum.getText().trim();
						
						if(name.isEmpty()||address.isEmpty()||phoneNum.isEmpty()) {
							JOptionPane.showMessageDialog(null, "[비회원] 인수자 기본정보를 입력하게요.", "메시지", JOptionPane.ERROR_MESSAGE);
						}
						else {
							if(NumberFormatCheck(phoneNum)) {
								ReturnProtAni();
								dispose();
							}
							else {
								JOptionPane.showMessageDialog(null, "전화번호 포맷을 확인하세요.(구분자 \"-\" 포함 숫자 13자리)", "메시지", JOptionPane.ERROR_MESSAGE);
							}
						}
					}
					
				}
			
			}
			else if(e.getSource().equals(cancel)) {
				dispose();
			}
		}
		
	}
	
	private boolean NumberFormatCheck(String phoneNum) {
		
		if(phoneNum.length()>13)
			return false;
		else {
			String repStr = phoneNum.replaceAll("[0-9]", "");
			String[] splitStr = repStr.split("");
//			System.out.println(splitStr.toString());
//			System.out.println(splitStr.length);
			if(splitStr.length==2)
				return true;
			else return false;
		}
	}
	
	class ReqPrsnRegistMouseListener extends MouseAdapter{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			super.mouseClicked(e);
			if(e.getButton()==1) {
				
			}
		}
		
	}
	
	private void ReturnProtAni() {
		
		//일단 회원이던 비회원이던 현재 보호중인 동물의 보호종료일자를 오늘날자로 변경해야함. 하지만 반대로하는 것이 좋을 것 같음.
		//반대라는 말은 반환에 먼저 등록하고 그 후에 날짜를 업데이트하자는 뜻
		boolean isCust = cust.isSelected();
		
		String name = null;
		String id = null;
		String addr = null;
		String phoneNum = null;
		
		if(isCust) {
			
			int clickedRow = eCustList.getSelectedRow();
			name = (String)eCustList.getValueAt(clickedRow, 0);
			id = (String)eCustList.getValueAt(clickedRow, 1);
			String abanNo = this.abanNo;
			
			//반환등록
			StringBuffer query1 = new StringBuffer("INSERT INTO RET(RET_NO,RET_DATE,PROT_NO,CUST_NO) ");
			query1.append("SELECT ");
			query1.append("	CASE WHEN SUBSTR(RET_NO,1,8) = to_char(TRUNC(SYSDATE),'yyyymmdd') ");
			query1.append("	THEN to_char(TRUNC(SYSDATE),'yyyymmdd') || CASE WHEN SUBSTR(RET_NO,10,1) = '9' THEN to_char(SUBSTR(RET_NO,9,1)+1) ELSE SUBSTR(RET_NO,9,1) END || CASE WHEN SUBSTR(RET_NO,10,1)='9' THEN '0' ELSE to_char(SUBSTR(RET_NO,10,1)+1) END");
			query1.append("	ELSE to_char(TRUNC(SYSDATE),'yyyymmdd') || '01' END RET_NO,");
			query1.append("	TRUNC(SYSDATE) RET_DATE, ");
			query1.append("	(SELECT PROT_NO FROM PROT WHERE ABAN_NO='"+abanNo+"' AND PROT_END_DATE=to_date('9999-12-31','YYYY-MM-DD')) PROT_NO, ");
			query1.append("	(SELECT CUST_NO FROM CUST WHERE CUST_NAME='"+name+"' AND ID='"+id+"') CUST_NO ");
			query1.append("FROM( ");
			query1.append("	SELECT NVL(RET_NO,0) RET_NO ");
			query1.append("	FROM( ");
			query1.append("		SELECT MAX(RET_NO) RET_NO FROM RET) ");
			query1.append(") ");
			
			//보호종료일자변경
//			System.out.println(abanNo);
			StringBuffer query2 = new StringBuffer("UPDATE PROT ");
			query2.append("SET PROT_END_DATE = trunc(sysdate) ");
			query2.append("WHERE PROT_NO=(SELECT PROT_NO FROM PROT WHERE ABAN_NO='"+abanNo+"' AND PROT_END_DATE=to_date('9999-12-31','YYYY-MM-DD')) ");
			
			connection();
			
			try {
				pstmt = con.prepareStatement(query1.toString());
				rs = pstmt.executeQuery();
				if(rs.next()) {
					con.commit();
				}

				pstmt = con.prepareStatement(query2.toString());
				System.out.println(pstmt.executeUpdate());
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
			disconnection();
		}
		else {
			name = xName.getText();
			addr = xAddress.getText();
			phoneNum = xPhoneNum.getText();
			String abanNo = this.abanNo;
			
			//신규 비회원 등록
			StringBuffer query1 = new StringBuffer("INSERT INTO CUST(CUST_NO,CUST_NAME,ADDR,TEL_NO,CUST_TP) ");
			query1.append("SELECT ");
			query1.append("	(SELECT NVL(CUST_NO,0)+1 CUST_NO ");
			query1.append("	FROM(SELECT MAX(cust_no) cust_no FROM CUST)) CUST_NO, ");
			query1.append("	'"+name+"' CUST_NAME, ");
			query1.append("	'"+addr+"' ADDR, ");
			query1.append("	'"+phoneNum+"' TEL_NO, ");
			query1.append("	'n' CUST_TP ");
			query1.append("FROM DUAL ");
			
			//반환등록
			StringBuffer query2 = new StringBuffer("INSERT INTO RET(RET_NO,RET_DATE,PROT_NO,CUST_NO) ");
			query2.append("SELECT ");
			query2.append("	CASE WHEN SUBSTR(RET_NO,1,8) = to_char(TRUNC(SYSDATE),'yyyymmdd') ");
			query2.append("	THEN to_char(TRUNC(SYSDATE),'yyyymmdd') || CASE WHEN SUBSTR(RET_NO,10,1) = '9' THEN to_char(SUBSTR(RET_NO,9,1)+1) ELSE SUBSTR(RET_NO,9,1) END || CASE WHEN SUBSTR(RET_NO,10,1)='9' THEN '0' ELSE to_char(SUBSTR(RET_NO,10,1)+1) END");
			query2.append("	ELSE to_char(TRUNC(SYSDATE),'yyyymmdd') || '01' END RET_NO,");
			query2.append("	TRUNC(SYSDATE) RET_DATE, ");
			query2.append("	(SELECT PROT_NO FROM PROT WHERE ABAN_NO='"+abanNo+"' AND PROT_END_DATE=to_date('9999-12-31','YYYY-MM-DD' )) PROT_NO, ");
			query2.append("	(SELECT CUST_NO FROM CUST WHERE CUST_NAME='"+name+"' AND ADDR='"+addr+"' AND TEL_NO='"+phoneNum+"' ) CUST_NO ");
			query2.append("FROM( ");
			query2.append("	SELECT NVL(RET_NO,0) RET_NO ");
			query2.append("	FROM( ");
			query2.append("		SELECT MAX(RET_NO) RET_NO FROM RET) ");
			query2.append(") ");
			
			//보호종료일자변경
			StringBuffer query3 = new StringBuffer("UPDATE PROT ");
			query3.append("SET PROT_END_DATE = trunc(sysdate) ");
			query3.append("WHERE PROT_NO=(SELECT PROT_NO FROM PROT WHERE ABAN_NO='"+abanNo+"' AND PROT_END_DATE=to_date('9999-12-31','YYYY-MM-DD')) ");
			
			
			connection();
			
			try {
				pstmt = con.prepareStatement(query1.toString());
				rs = pstmt.executeQuery();
				if(rs.next()) {
					con.commit();
				}
		
				pstmt = con.prepareStatement(query2.toString());
				rs = pstmt.executeQuery();
				if(rs.next()) {
					con.commit();
				}
		
				pstmt = con.prepareStatement(query3.toString());
				System.out.println(pstmt.executeUpdate());
		
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
			disconnection();
		
		}
	}
	
	private void GetCustList() {
		
		connection();
		
		StringBuffer query = new StringBuffer("SELECT /*+INDEX_ASC(CUST CUST_PK) */ CUST_NO,CUST_NAME,ID,ADDR FROM CUST WHERE CUST_TP='m' ");
		
		try {
			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				model1.addRow(new Object[] {rs.getString("CUST_NAME"),rs.getString("ID"),rs.getString("ADDR")});
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		disconnection();
	}
	
	private void SearchCust(String text, boolean isName) {
		model1.setRowCount(0);
		
		connection();
		
		StringBuffer query = new StringBuffer();
		
		if(isName) {
			query.append("SELECT /*+INDEX_ASC(CUST CUST_PK) */ CUST_NO,CUST_NAME,ID,ADDR FROM CUST ");
			query.append("WHERE CUST_NAME LIKE '"+text+"%' ");
		
		}
		else {
			query.append("SELECT /*+INDEX_ASC(CUST CUST_PK) */ CUST_NO,CUST_NAME,ID,ADDR FROM CUST ");
			query.append("WHERE ID LIKE '"+text+"%' ");
			
		}
		
		try {
			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
		
		while(rs.next()) {
			model1.addRow(new Object[] {rs.getString("CUST_NAME"),rs.getString("ID"),rs.getString("ADDR")});
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
		new ReqPrsnRegist(null);
	}
}
