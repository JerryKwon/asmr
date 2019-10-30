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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class NotiBoard extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	NotiData notiData;
	NotiDAO notiDAO;
	
	Vector<String> data, col;
	
	public static Connection conn = DBConn.getConnection();
	static String quary;
	static PreparedStatement pstm = null;
	static ResultSet rs = null;

	
	NotiBoardButtonListener notiBoardButtonListener;
	// 페이징 미구현, 페이징 번호 없음!
	private JLabel vNoti;
	
	private JTextField xSearch;
	
	private JComboBox<String> cbSearch;
	
	private JTable eNoticeList;
	
	private JScrollPane scrollPane;
	
	private String[] searchDiv = {"제목", "작성자"};
	
	private JButton regis0, search;
	
	//private final String[] col = {"번호","제목","작성자","작성일시"};
		
	//private DefaultTableModel model = new DefaultTableModel(col,0);
	//private DefaultTableModel model = new DefaultTableModel(notiDAO.getScore(), col);
	
	GridBagLayout gridbaglayout;
	GridBagConstraints gridbagconstraints;
	
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	

	
	
	public NotiBoard() {
		
		col = new Vector<String>();
		col.add("번호");
		col.add("제목");
		col.add("작성자");
		col.add("작성일시");
		
		notiDAO = new NotiDAO();
		
		notiBoardButtonListener = new NotiBoardButtonListener();
		
		gridbaglayout = new GridBagLayout();
		gridbagconstraints = new GridBagConstraints();
		
		vNoti = new JLabel("공지사항");
		vNoti.setFont(new Font("나눔고딕", Font.BOLD, 24));
		DefaultTableModel model = new DefaultTableModel(notiDAO.getScore(), col);
		eNoticeList = new JTable(model){
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
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
		
		
		NotiBoardView();
	
	}
	
	private void NotiBoardView() {
		
		//setTitle("공지사항");	
		
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

		//pack();
		//setResizable(false);
		//setVisible(true);
		
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
	
	public void jTableRefresh() {
	    // 테이블 수정 못하게 DefaultTableModel 사용
	    DefaultTableModel model = new DefaultTableModel(notiDAO.getScore(), col) {
	      /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

		public boolean isCellEditable(int row, int column) {
	        return false;
	      }
	    };
	    eNoticeList.setModel(model);
	    jTableSet();
	  } // jTableRefresh : 테이블 내용을 갱신하는 메서드
	
	public void jTableSet() {
	    // 이동과 길이조절 여러개 선택 되는 것을 방지한다
		eNoticeList.getTableHeader().setReorderingAllowed(false);
		eNoticeList.getTableHeader().setResizingAllowed(false);
		eNoticeList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
	     
	    // 컬럼 정렬에 필요한 메서드
	    DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
	    celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
	    DefaultTableCellRenderer celAlignRight = new DefaultTableCellRenderer();
	    celAlignRight.setHorizontalAlignment(JLabel.RIGHT);
	    DefaultTableCellRenderer celAlignLeft = new DefaultTableCellRenderer();
	    celAlignLeft.setHorizontalAlignment(JLabel.LEFT);
	     
	    // 컬럼별 사이즈 조절 & 정렬
	    eNoticeList.getColumnModel().getColumn(0).setPreferredWidth(10);
	    eNoticeList.getColumnModel().getColumn(0).setCellRenderer(celAlignCenter);
	    eNoticeList.getColumnModel().getColumn(1).setPreferredWidth(10);
	    eNoticeList.getColumnModel().getColumn(1).setCellRenderer(celAlignCenter);
	    eNoticeList.getColumnModel().getColumn(2).setPreferredWidth(10);
	    eNoticeList.getColumnModel().getColumn(2).setCellRenderer(celAlignCenter);
	    eNoticeList.getColumnModel().getColumn(3).setPreferredWidth(10);
	    eNoticeList.getColumnModel().getColumn(3).setCellRenderer(celAlignCenter);
	  } // jTableRefresh : 테이블 옵션 설정하는 메서드


	
    class NotiBoardButtonListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource().equals(regis0)) {
				MainFrame.notiCase();
			}
			else if(e.getSource().equals(search)) {
				
			}
		}

		private Container getContentPane() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new NotiBoard();
	}

	
}
