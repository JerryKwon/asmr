package asmr;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
	
	static String pno = null;
	
	private static String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private static String user = "asmr";
	private static String password = "asmr";
	
	private static Connection con = null;
	private static PreparedStatement pstmt = null;
	private static ResultSet rs = null;
	private static ResultSetMetaData rsmd = null;
	
	NotiBoardButtonListener notiBoardButtonListener;
	NotiBoardMouseListener notiBoardMouseListener;
	
	// 페이징 미구현, 페이징 번호 없음!
	private JLabel vNoti;
	
	private JTextField xSearch;
	
	private JComboBox<String> cbSearch;
	
	private JTable eNoticeList;
	
	private JScrollPane scrollPane;
	
	private String[] searchDiv = {"제목", "작성자"};
	
	private JButton regis0, search, pre, next;
	
	private final String[] col = {"번호","제목","작성자","작성일시"};
		
	private DefaultTableModel model = new DefaultTableModel(col,0);
	
	GridBagLayout gridbaglayout;
	GridBagConstraints gridbagconstraints;
	
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	
	private int nowPage; //현재 페이지 번호
	private int nowPanel; //현재 패널 번호
	private int postPerPage = 2; //페이지 당 포스트 수
	private int pagePerPanel = 3; //패널 당 페이지 수
	private int panelNum; //총 패널 수
	private int pageNum; //총 페이지 수
	private JButton[] bPage;
	private JPanel[] pPage;	
		
	
	public NotiBoard() {

		notiBoardButtonListener = new NotiBoardButtonListener();
		notiBoardMouseListener = new NotiBoardMouseListener();
		
		gridbaglayout = new GridBagLayout();
		gridbagconstraints = new GridBagConstraints();
		
		vNoti = new JLabel("공지사항");
		vNoti.setFont(new Font("나눔고딕", Font.BOLD, 24));
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
		
		regis0 = new JButton("등록");
		regis0.setFont(new Font("나눔고딕", Font.BOLD, 16));
		regis0.setBackground(blue);
		regis0.setForeground(white);
		regis0.addActionListener(notiBoardButtonListener);
		
		cbSearch = new JComboBox<String>(searchDiv);
		xSearch = new JTextField(20);
		search = new JButton("검색");
		search.setFont(new Font("나눔고딕", Font.BOLD, 16));
		search.setBackground(blue);
		search.setForeground(white);
		search.addActionListener(notiBoardButtonListener);
		
		pre = new JButton("<");
	    pre.setContentAreaFilled(false);
	    pre.setBorderPainted(false);
	    pre.addMouseListener(notiBoardMouseListener);
	    
	    next = new JButton(">");
	    next.setContentAreaFilled(false);
	    next.setBorderPainted(false);
	    next.addMouseListener(notiBoardMouseListener);


		
		GetNotiPostList();
		createPanel();
		NotiBoardView();
	
	}
	
	private void createPanel() {
	      if(model.getRowCount()!=0 && (model.getRowCount()%postPerPage)==0) { //페이지 수 구하기
	         pageNum = model.getRowCount()/postPerPage;
	      }
	      else {
	         pageNum = model.getRowCount()/postPerPage+1;
	      }
	      
	      bPage = new JButton[pageNum];//페이지수만큼의 원소를 지닌 버튼배열 선언

	      
	      if((pageNum%pagePerPanel)==0) {  //패널 수 구하기
	         panelNum = pageNum/pagePerPanel;
	      }
	      else {
	         panelNum = pageNum/pagePerPanel+1;
	      }
	      
//	      System.out.println(panelNum);
	      pPage = new JPanel[panelNum]; //구한 panelNum만큼의 원소를 지닌 패널배열 선언(버튼을 올려놓을 곳)
	      
	      int indexButton=0;
	      
	      for(int i=0;i<panelNum;i++) {
	         pPage[i] = new JPanel(new FlowLayout(FlowLayout.LEFT)); //패널생성
	         for(int j=0;j<pagePerPanel;j++) {
	            if(indexButton >= pageNum) {
	               break;
	            }
	            bPage[indexButton] = new JButton(""+(indexButton+1)); //버튼생성
	            bPage[indexButton].setContentAreaFilled(false);
	            bPage[indexButton].setBorderPainted(false);
	            bPage[indexButton].addMouseListener(notiBoardMouseListener);
	            
	            pPage[i].add(bPage[indexButton]); //패널위에 버튼올리기
	            indexButton++;
	         }   
	      }
	      nowPage = 0; //현재페이지와 패널 초기화
	      nowPanel = 0;
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
		
		gridbagconstraints.anchor = GridBagConstraints.EAST;
	    gridbagAdd(pre,0,10,1,1);
	    gridbagconstraints.anchor = GridBagConstraints.CENTER;
	    gridbagAdd(pPage[0],1,10,1,1);
	    gridbagconstraints.anchor = GridBagConstraints.WEST;
	    gridbagAdd(next,2,10,1,1);

		
		gridbagconstraints.anchor = GridBagConstraints.CENTER;
		
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
	
//	public void jTableRefresh() {
//	    // 테이블 수정 못하게 DefaultTableModel 사용
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
//	  } // jTableRefresh : 테이블 내용을 갱신하는 메서드
	
	
//	void getData() {
//        for(int i = nowPage*postPerPage; i < nowPage*postPerPage + postPerPage ; i++) {
//           if(i>model.getRowCount()-1) {
//              break;
//           }

	

	class NotiBoardMouseListener extends MouseAdapter{
		
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			super.mouseClicked(e);
			
			for(int i=0; i<pageNum;i++) {
		         if(e.getSource() == bPage[i]) {
		            nowPage = i;
		            model.setRowCount(0);
//		            getData();
		         }
		      }

			
//			https://blaseed.tistory.com/18			
			//1:좌클릭, 3:우클릭
			if(e.getButton() == 1) {
				int clickedRow = eNoticeList.getSelectedRow();
				String postNo = (String)eNoticeList.getValueAt(clickedRow, 0);
				pno = postNo;
				GetPost(postNo);
			}
			else if(e.getSource()==pre) {
		        if(nowPanel > 0) {
		           remove(pPage[nowPanel]);
		           nowPanel--;
		           System.out.println("nowPanel = "+nowPanel);
		           gridbagAdd(pPage[nowPanel],1,10,1,1);
		             revalidate();
//		             if(mainPage == null) {
//		              mainMPage.repaint();
//		             }
//		             else {
//		              mainPage.repaint();
//		             }
		         

		       	}
	      }
		    else if(e.getSource() ==next) {
		        if(nowPanel < panelNum-1) {
		           remove(pPage[nowPanel]);
		           nowPanel++;   
		           gridbagAdd(pPage[nowPanel],1,10,1,1);
	            revalidate();
//	              if(mainPage == null) {
//		               mainMPage.repaint();
//	            }
//	            else {
//	               mainPage.repaint();
//	            }
		        }
		    }
		       
	}
}
	
	
	static private void GetPost(String postNo) {
			
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
			query.append("where p.post_tp = 'n' ");
			query.append("order by 4 desc ");
			
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

	
	
//	public void jTableSet() {
//	    // 이동과 길이조절 여러개 선택 되는 것을 방지한다
//		eNoticeList.getTableHeader().setReorderingAllowed(false);
//		eNoticeList.getTableHeader().setResizingAllowed(false);
//		eNoticeList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
//	     
//	    // 컬럼 정렬에 필요한 메서드
//	    DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
//	    celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
//	    DefaultTableCellRenderer celAlignRight = new DefaultTableCellRenderer();
//	    celAlignRight.setHorizontalAlignment(JLabel.RIGHT);
//	    DefaultTableCellRenderer celAlignLeft = new DefaultTableCellRenderer();
//	    celAlignLeft.setHorizontalAlignment(JLabel.LEFT);
//	     
//	    // 컬럼별 사이즈 조절 & 정렬
//	    eNoticeList.getColumnModel().getColumn(0).setPreferredWidth(10);
//	    eNoticeList.getColumnModel().getColumn(0).setCellRenderer(celAlignCenter);
//	    eNoticeList.getColumnModel().getColumn(1).setPreferredWidth(10);
//	    eNoticeList.getColumnModel().getColumn(1).setCellRenderer(celAlignCenter);
//	    eNoticeList.getColumnModel().getColumn(2).setPreferredWidth(10);
//	    eNoticeList.getColumnModel().getColumn(2).setCellRenderer(celAlignCenter);
//	    eNoticeList.getColumnModel().getColumn(3).setPreferredWidth(10);
//	    eNoticeList.getColumnModel().getColumn(3).setCellRenderer(celAlignCenter);
//	  } // jTableRefresh : 테이블 옵션 설정하는 메서드
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
				case "제목":
					SearchEmp(typedName,true);
					break;
				case "작성자":
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
