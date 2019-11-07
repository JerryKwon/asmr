package asmr;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
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
import javax.swing.table.DefaultTableModel;

public class NotiBoard extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private String user = "asmr";
	private String password = "asmr";
	
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private ResultSetMetaData rsmd = null;

	
	NotiBoardButtonListener notiBoardButtonListener;
	NotiBoardMouseListener notiBoardMouseListener;
	
	// ����¡ �̱���, ����¡ ��ȣ ����!
	private JLabel vNoti;
	
	private JTextField xSearch;
	
	private JComboBox<String> cbSearch;
	
	private JTable eNoticeList;
	
	private JScrollPane scrollPane;
	
	private String[] searchDiv = {"����", "�ۼ���"};
	
	private JButton regis0, search;
	
	private final String[] col = {"��ȣ","����","�ۼ���","�ۼ��Ͻ�"};
		
	private DefaultTableModel model = new DefaultTableModel(col,0);
	
	GridBagLayout gridbaglayout;
	GridBagConstraints gridbagconstraints;
	
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
		
	
	public NotiBoard() {

		notiBoardButtonListener = new NotiBoardButtonListener();
		notiBoardMouseListener = new NotiBoardMouseListener();
		
		gridbaglayout = new GridBagLayout();
		gridbagconstraints = new GridBagConstraints();
		
		vNoti = new JLabel("��������");
		vNoti.setFont(new Font("�������", Font.BOLD, 24));
		model = new DefaultTableModel(col, 0);
		eNoticeList = new JTable(model){
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
	    eNoticeList.addMouseListener(notiBoardMouseListener);
		scrollPane = new JScrollPane(eNoticeList);
		scrollPane.setPreferredSize(new Dimension(700,300));
		
		regis0 = new JButton("���");
		regis0.setFont(new Font("�������", Font.BOLD, 16));
		regis0.setBackground(blue);
		regis0.setForeground(white);
		regis0.addActionListener(notiBoardButtonListener);
		
		cbSearch = new JComboBox<String>(searchDiv);
		xSearch = new JTextField(20);
		search = new JButton("�˻�");
		search.setFont(new Font("�������", Font.BOLD, 16));
		search.setBackground(blue);
		search.setForeground(white);
		search.addActionListener(notiBoardButtonListener);
		
		GetNotiPostList();
		
		NotiBoardView();
	
	}
	
	private void NotiBoardView() {

		
		gridbagconstraints.anchor = GridBagConstraints.WEST;
		gridbagconstraints.ipadx = 7;
		
		gridbagconstraints.weightx=1.0;
		gridbagconstraints.weighty=1.0;
		
		setLayout(gridbaglayout);

		gridbagAdd(vNoti, 0, 0, 1, 1);
		
		gridbagAdd(regis0, 5, 1, 1, 1);
		
		gridbagAdd(scrollPane, 0, 2, 6, 1);
		
		gridbagAdd(cbSearch, 1, 3, 1, 1);
		gridbagAdd(xSearch, 2, 3, 1, 1);
		gridbagAdd(search, 3, 3, 1, 1);
		
		gridbagconstraints.anchor = GridBagConstraints.CENTER;

	}
	
	private void gridbagAdd(Component c, int x, int y, int w, int h) {			
		
		gridbagconstraints.gridx = x;		
		gridbagconstraints.gridy = y; 		
	      //���� ���� �� gridx, gridy���� 0 			
				
		gridbagconstraints.gridwidth  = w;	//����	
		gridbagconstraints.gridheight = h;	//����	
	     			
	      			
	    gridbaglayout.setConstraints(c, gridbagconstraints); //������Ʈ�� ������Ʈ ��ġ+ũ�� ������ ���� GridBagLayout�� ��ġ			
				
	   add(c);			
				
	   }
	
//	public void jTableRefresh() {
//	    // ���̺� ���� ���ϰ� DefaultTableModel ���
//	    DefaultTableModel model = new DefaultTableModel(notiDAO.getScore(), col) {
//	      /**
//			 * 
//			 */
//			private static final long serialVersionUID = 1L;
//
//		public boolean isCellEditable(int row, int column) {
//	        return false;
//	      }
//	    };
//	    eNoticeList.setModel(model);
//	    jTableSet();
//	  } // jTableRefresh : ���̺� ������ �����ϴ� �޼���
	
	
//  ���� ������̺� Ŭ���� �߻��ϴ� ������
	class NotiBoardMouseListener extends MouseAdapter{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			super.mouseClicked(e);
			
//			https://blaseed.tistory.com/18			
			//1:��Ŭ��, 3:��Ŭ��
			if(e.getButton() == 1) {
				int clickedRow = eNoticeList.getSelectedRow();
				String postNo = (String)eNoticeList.getValueAt(clickedRow, 0);
				GetPost(postNo);
			}
		}
	}
	
	private void GetPost(String postNo) {
	
		MainFrame.notiPostCase();
		connection();
		
		try {
			StringBuffer query= new StringBuffer("SELECT POST_TIT, POST_CONT, EMP_NAME, WRT_DTTM ");
			query.append("FROM POST ");
			query.append("JOIN EMP ");
			query.append("ON POST.NOTI_WRT_PRSN_NO = EMP.EMP_NO ");
			query.append("WHERE POST_NO='"+postNo+"' ");

				
			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();

			
			while(rs.next()) {
								
				NotiPost.xTit.setText(rs.getString("POST_TIT"));
				NotiPost.xCont.setText(rs.getString("POST_CONT"));
				NotiPost.xWrt.setText(rs.getString("EMP_NAME"));
				NotiPost.xWrtDttm.setText(rs.getString("WRT_DTTM"));
				
			}
				
		}catch(Exception e2) {
			e2.printStackTrace();
		}
		
		disconnection();
	}
	
	
	private void GetNotiPostList() {
		model.setRowCount(0);
		
		connection();
			
		try {
			StringBuffer query= new StringBuffer("select p.post_no, p.post_tit, e.emp_name, p.wrt_dttm ");
			query.append("from post p ");
			query.append("join emp e ");
			query.append("on p.noti_wrt_prsn_no = e.emp_no ");
			query.append("order by 1 desc ");
			
			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {		
				
//				cntrNos.add(rs.getString("CNTR_NO"));
				
				model.addRow(new Object[] {rs.getString("post_no"),rs.getString("post_tit"),rs.getString("emp_name"),rs.getString("wrt_dttm")});
			}
		
		}catch(Exception e1) {
			e1.printStackTrace();
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

	
	
//	public void jTableSet() {
//	    // �̵��� �������� ������ ���� �Ǵ� ���� �����Ѵ�
//		eNoticeList.getTableHeader().setReorderingAllowed(false);
//		eNoticeList.getTableHeader().setResizingAllowed(false);
//		eNoticeList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
//	     
//	    // �÷� ���Ŀ� �ʿ��� �޼���
//	    DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
//	    celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
//	    DefaultTableCellRenderer celAlignRight = new DefaultTableCellRenderer();
//	    celAlignRight.setHorizontalAlignment(JLabel.RIGHT);
//	    DefaultTableCellRenderer celAlignLeft = new DefaultTableCellRenderer();
//	    celAlignLeft.setHorizontalAlignment(JLabel.LEFT);
//	     
//	    // �÷��� ������ ���� & ����
//	    eNoticeList.getColumnModel().getColumn(0).setPreferredWidth(10);
//	    eNoticeList.getColumnModel().getColumn(0).setCellRenderer(celAlignCenter);
//	    eNoticeList.getColumnModel().getColumn(1).setPreferredWidth(10);
//	    eNoticeList.getColumnModel().getColumn(1).setCellRenderer(celAlignCenter);
//	    eNoticeList.getColumnModel().getColumn(2).setPreferredWidth(10);
//	    eNoticeList.getColumnModel().getColumn(2).setCellRenderer(celAlignCenter);
//	    eNoticeList.getColumnModel().getColumn(3).setPreferredWidth(10);
//	    eNoticeList.getColumnModel().getColumn(3).setCellRenderer(celAlignCenter);
//	  } // jTableRefresh : ���̺� �ɼ� �����ϴ� �޼���
//

	
    class NotiBoardButtonListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource().equals(regis0)) {
				MainFrame.notiCase();
			}
			else if(e.getSource().equals(search)) {
				String searchType = (String)cbSearch.getSelectedItem();
				String typedName = xSearch.getText();
				switch(searchType) {
				case "����":
					SearchEmp(typedName,true);
					break;
				case "�ۼ���":
					SearchEmp(typedName,false);
					break;
				}
			}
		}
		
		private void SearchEmp(String name, boolean isEmp) {
			model.setRowCount(0);
			
			connection();
			
			StringBuffer query = new StringBuffer();
			
			if(!isEmp) {
				query.append("select post_no, post_tit, emp_name, wrt_dttm ");
				query.append("from post ");
				query.append("join emp  ");
				query.append("on post.noti_wrt_prsn_no = emp.emp_no ");
				query.append("where emp_name = '"+name+"' ");
				query.append("order by 1 desc ");			
			}
			else {
				query.append("select post_no, post_tit, emp_name, wrt_dttm ");
				query.append("from post ");
				query.append("join emp ");
				query.append("on post.noti_wrt_prsn_no = emp.emp_no ");
				query.append("where post_tit like '%"+name+"%' ");
				query.append("order by 1 desc ");				
			}
			
			try {
			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				model.addRow(new Object[] {rs.getString("post_no"),rs.getString("post_tit"),rs.getString("emp_name"),rs.getString("wrt_dttm")});
			}
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			disconnection();
		}
		
		

		private Container getContentPane() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		new NotiBoard();
	}

	
}
