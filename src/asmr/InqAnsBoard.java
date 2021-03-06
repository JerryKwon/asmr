package asmr;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class InqAnsBoard extends JPanel {
	
	/**
	 * 
	 */
	static String pno = null;
	private static final long serialVersionUID = 1L;

	static String ptit = null;
	static String pdttm = null;
	
	private static String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private static String user = "asmr";
	private static String password = "asmr";
	
	private static Connection con = null;
	private static PreparedStatement pstmt = null;
	private static ResultSet rs = null;
	private static ResultSetMetaData rsmd = null;
	
	InqAnsBoardButtonListener inqAnsBoardButtonListener;
	InqAnsBoardMouseListener inqAnsBoardMouseListener;
	
	// 페이징 미구현, 페이징 번호 없음!
	private JLabel vInqAns;
	
	private JTextField xSearch;
	
	private JComboBox<String> cbSearch;
	
	private JTable eInqAnsList;
	
	private JScrollPane scrollPane;
	
	private String[] searchDiv = {"제목", "작성자"};
	
	private JButton regis, search;
	
	private final String[] col = {"제목","작성자","작성일시"};
	
	private DefaultTableModel model = new DefaultTableModel(col,0);
	
	GridBagLayout gridbaglayout;
	GridBagConstraints gridbagconstraints;
	
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	
	public InqAnsBoard() {
		
		inqAnsBoardButtonListener = new InqAnsBoardButtonListener();
		inqAnsBoardMouseListener = new InqAnsBoardMouseListener();
		
		gridbaglayout = new GridBagLayout();
		gridbagconstraints = new GridBagConstraints();
		
		vInqAns = new JLabel("문의답변게시판");
		vInqAns.setFont(new Font("나눔고딕", Font.BOLD, 24));
		model = new DefaultTableModel(col, 0);
		eInqAnsList = new JTable(model){
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
	    eInqAnsList.getTableHeader().setReorderingAllowed(false);
	    eInqAnsList.getTableHeader().setResizingAllowed(false);
	    
	    DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
	    dtcr.setHorizontalAlignment(SwingConstants.CENTER);
	    eInqAnsList.addMouseListener(inqAnsBoardMouseListener);
	    eInqAnsList.getColumnModel().getColumn(0).setPreferredWidth(400);
	    eInqAnsList.getColumnModel().getColumn(0).setCellRenderer(dtcr);
	    eInqAnsList.getColumnModel().getColumn(1).setCellRenderer(dtcr);
	    eInqAnsList.getColumnModel().getColumn(2).setCellRenderer(dtcr);
		scrollPane = new JScrollPane(eInqAnsList);
		scrollPane.setPreferredSize(new Dimension(700,300));
		
		regis = new JButton("등록");
		regis.setFont(new Font("나눔고딕", Font.BOLD, 16));
		regis.setBackground(blue);
		regis.setForeground(white);
		regis.addActionListener(inqAnsBoardButtonListener);
		
		cbSearch = new JComboBox<String>(searchDiv);
		xSearch = new JTextField(20);
		search = new JButton("검색");
		search.setFont(new Font("나눔고딕", Font.BOLD, 16));
		search.setBackground(blue);
		search.setForeground(white);
		search.addActionListener(inqAnsBoardButtonListener);
		
		GetInqAnsPostList();
		InqAnsBoardView();
	
	}

	private void InqAnsBoardView() {
		setBackground(MainFrame.bgc);
		
		//setTitle("문의/답변");	
		
		gridbagconstraints.anchor = GridBagConstraints.WEST;
		gridbagconstraints.ipadx = 7;
		
		gridbagconstraints.weightx=1.0;
		gridbagconstraints.weighty=1.0;
		
		setLayout(gridbaglayout);

		gridbagAdd(vInqAns, 0, 0, 1, 1);
		
		gridbagAdd(regis, 5, 1, 1, 1);
		
		gridbagAdd(scrollPane, 0, 2, 6, 1);
		
		gridbagAdd(cbSearch, 1, 3, 1, 1);
		gridbagAdd(xSearch, 2, 3, 1, 1);
		gridbagAdd(search, 3, 3, 1, 1);
		
		gridbagconstraints.anchor = GridBagConstraints.CENTER;

		//pack();
		//setResizable(false);
		setVisible(true);
		
	}
	
	private void gridbagAdd(Component c, int x, int y, int w, int h) {			
		
		gridbagconstraints.gridx = x;		
		gridbagconstraints.gridy = y; 		
	      //가장 왼쪽 위 gridx, gridy값은 0 			
				
		gridbagconstraints.gridwidth  = w;	//넓이	
		gridbagconstraints.gridheight = h;	//높이	
	     			
	      			
	    gridbaglayout.setConstraints(c, gridbagconstraints); //컴포넌트를 컴포넌트 위치+크기 정보에 따라 GridBagLayout에 배치			
				
	   add(c);			
				
	   }
	
    class InqAnsBoardButtonListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource().equals(regis)) {

				MainFrame.InqCase();
				
			}
			else if(e.getSource().equals(search)) {
				String searchType = (String)cbSearch.getSelectedItem();
				String typedName = xSearch.getText();
				switch(searchType) {
				case "제목":
					SearchEmp(typedName,true);
					break;
				case "작성자":
					SearchEmp(typedName,false);
					break;
				
			}
		}
		
	}
    }
    
    
    class InqAnsBoardMouseListener extends MouseAdapter{
		
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			super.mouseClicked(e);
			if(e.getButton() == 1) {	
				int clickedRow = eInqAnsList.getSelectedRow();
				String postTit = (String)eInqAnsList.getValueAt(clickedRow, 0);
				String postDttm = (String)eInqAnsList.getValueAt(clickedRow, 2);
				ptit = postTit;
				pdttm = postDttm;
//				GetPost(postTit, postDttm);	
				
				pno = InqAnsData.getPostNo(ptit, pdttm);
				System.out.println(pno);
				String postTp = InqAnsData.getPostTp(pno);
				System.out.println(postTp);
				if(postTp.equals("q")) {
					GetPost(postTit, postDttm);	
					
				}else {
					GetAnsPost(postTit, postDttm);
				}
				
			}
		}	
	}
    

    
    private void GetPost(String postTit, String postDttm) {
		
		MainFrame.InqPostCase();

		connection();
		
		StringBuffer query= new StringBuffer("SELECT POST_TIT, POST_CONT, CUST_NAME, WRT_DTTM, POST_NO ");
		query.append("FROM POST, CUST ");
//		query.append("WHERE POST.INQ_WRT_PRSN_NO = CUST.CUST_NO ");
		query.append("WHERE POST_TIT='"+postTit+"' ");
		query.append("AND WRT_DTTM=to_date('"+postDttm+"','YYYY-MM-DD hh24:mi:ss') ");
		query.append("UNION ");
		query.append("SELECT POST_TIT, POST_CONT, EMP_NAME , WRT_DTTM, POST_NO ");
		query.append("FROM POST, EMP ");
//		query.append("WHERE post.ans_wrt_prsn_no = emp.emp_no ");
		query.append("WHERE POST_TIT='"+postTit+"' ");
		query.append("AND WRT_DTTM=to_date('"+postDttm+"','YYYY-MM-DD hh24:mi:ss') ");

		
		
		try {

			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();

			
			while(rs.next()) {
				
								
				InqPost.xTit.setText(rs.getString(1));
				InqPost.xCont.setText(rs.getString(2));
				InqPost.xWrt.setText(rs.getString(3));
				InqPost.xWrtDttm.setText(rs.getString(4));
				pno=rs.getString(5);
				
				
				
			}
				
		}catch(Exception e2) {

			
			e2.printStackTrace();
		}
		
		disconnection();
	}
    
private void GetAnsPost(String postTit, String postDttm) {
		
		MainFrame.AnsPostCase();

		connection();
		
		StringBuffer query= new StringBuffer("SELECT POST_TIT, POST_CONT, CUST_NAME, WRT_DTTM, POST_NO ");
		query.append("FROM POST, CUST ");
//		query.append("WHERE POST.INQ_WRT_PRSN_NO = CUST.CUST_NO ");
		query.append("WHERE POST_TIT='"+postTit+"' ");
		query.append("AND WRT_DTTM=to_date('"+postDttm+"','YYYY-MM-DD hh24:mi:ss') ");
		query.append("UNION ");
		query.append("SELECT POST_TIT, POST_CONT, EMP_NAME , WRT_DTTM, POST_NO ");
		query.append("FROM POST, EMP ");
//		query.append("WHERE post.ans_wrt_prsn_no = emp.emp_no ");
		query.append("WHERE POST_TIT='"+postTit+"' ");
		query.append("AND WRT_DTTM=to_date('"+postDttm+"','YYYY-MM-DD hh24:mi:ss') ");

		
		
		try {

			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();

			
			while(rs.next()) {
				
								
				AnsPost.xTit.setText(rs.getString(1));
				AnsPost.xCont.setText(rs.getString(2));
				AnsPost.xWrt.setText(rs.getString(3));
				AnsPost.xWrtDttm.setText(rs.getString(4));
				pno=rs.getString(5);
				
				
				
				
			}
				
		}catch(Exception e2) {

			
			e2.printStackTrace();
		}
		
		disconnection();
	}
    
    
    private void GetInqAnsPostList() {
		model.setRowCount(0);
		
		connection();
			
		try {
			StringBuffer query= new StringBuffer("SELECT POST_TIT, CUST_NAME , WRT_DTTM ");
			query.append("FROM POST, CUST ");
			query.append("WHERE POST.INQ_WRT_PRSN_NO = CUST.CUST_NO ");
			query.append("UNION ");
			query.append("SELECT POST_TIT,  EMP_NAME AS CUST_NAME, WRT_DTTM ");
			query.append("FROM POST, EMP ");
			query.append("WHERE post.ans_wrt_prsn_no = emp.emp_no ");
//			query.append("where post.post_tp != 'n' ");
			query.append("order by 3 desc ");
//			System.out.println(query);
			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {		
				
//				cntrNos.add(rs.getString("CNTR_NO"));
				String[] dttm = rs.getString("wrt_dttm").split(":");
				String noSec = dttm[0]+":"+dttm[1];
				
				model.addRow(new Object[] {rs.getString("post_tit"),rs.getString("CUST_NAME"),rs.getString("WRT_DTTM")});
			
			}
		
		}catch(Exception e1) {
			e1.printStackTrace();
		}
		
		disconnection();
	}
	
	
    // 데이터베이스 연결

    public static void connection() {

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
    public static void disconnection() {

        try {

                 if(pstmt != null) pstmt.close();

                 if(rs != null) rs.close();

                 if(con != null) con.close();

        } catch (SQLException e) {
        	e.printStackTrace();
        }

    }
    
    private void SearchEmp(String name, boolean isEmp) {
		model.setRowCount(0);
		
		connection();
		
		StringBuffer query = new StringBuffer();
		
		if(!isEmp) {
			query.append("select post_no, post_tit, cust_name, wrt_dttm ");
			query.append("from post ");
			query.append("join cust  ");
			query.append("on post.inq_wrt_prsn_no = cust.cust_no ");
			query.append("where cust_name = '"+name+"' ");
			query.append("union ");
			query.append("select post_no, post_tit, emp_name, wrt_dttm ");
			query.append("from post ");
			query.append("join emp  ");
			query.append("on post.ans_wrt_prsn_no = emp.emp_no ");
			query.append("where emp_name = '"+name+"' ");
			query.append("order by 1 desc ");			
		}
		else {
			query.append("select post_no, post_tit, cust_name, wrt_dttm ");
			query.append("from post ");
			query.append("join cust ");
			query.append("on post.inq_wrt_prsn_no = cust.cust_no ");
			query.append("where post_tit like '%"+name+"%' ");
			query.append("union ");
			query.append("select post_no, post_tit, emp_name, wrt_dttm ");
			query.append("from post ");
			query.append("join emp  ");
			query.append("on post.ans_wrt_prsn_no = emp.emp_no ");
			query.append("where post_tit like '%"+name+"%' ");
			query.append("order by 1 desc ");				
		}
		
		try {
		pstmt = con.prepareStatement(query.toString());
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			model.addRow(new Object[] {rs.getString("post_tit"),rs.getString("cust_name"),rs.getString("wrt_dttm")});
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		disconnection();
	}
    
    
	
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//new InqAnsBoard();

	}

}
