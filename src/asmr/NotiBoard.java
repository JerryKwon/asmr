package asmr;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class NotiBoard extends JFrame {
	// 페이징 미구현, 페이징 번호 없음!
	private JLabel vNoti;
	
	private JTextField xSearch;
	
	private JComboBox<String> cbSearch;
	
	private JTable eNoticeList;
	
	private JScrollPane scrollPane;
	
	private String[] searchDiv = {"제목", "작성자"};
	
	private JButton regis, search;
	
	private final String[] col = {"번호","제목","작성자","작성일시"};
	
	private DefaultTableModel model = new DefaultTableModel(col,0);
	
	GridBagLayout gridbaglayout;
	GridBagConstraints gridbagconstraints;
	
	public NotiBoard() {
		
		gridbaglayout = new GridBagLayout();
		gridbagconstraints = new GridBagConstraints();
		
		vNoti = new JLabel("공지사항");
		eNoticeList = new JTable(model);
		scrollPane = new JScrollPane(eNoticeList);
		scrollPane.setPreferredSize(new Dimension(800,100));
		
		regis = new JButton("등록");
		
		cbSearch = new JComboBox<String>(searchDiv);
		xSearch = new JTextField(20);
		search = new JButton("검색");
		
		NotiBoardView();
	
	}
	
	private void NotiBoardView() {
		
		setTitle("공지사항");	
		
		gridbagconstraints.anchor = GridBagConstraints.WEST;
		gridbagconstraints.ipadx = 7;
		
		gridbagconstraints.weightx=1.0;
		gridbagconstraints.weighty=1.0;
		
		setLayout(gridbaglayout);

		gridbagAdd(vNoti, 0, 0, 1, 1);
		
		gridbagAdd(regis, 5, 1, 1, 1);
		
		gridbagAdd(scrollPane, 0, 2, 6, 1);
		
		gridbagAdd(cbSearch, 1, 3, 1, 1);
		gridbagAdd(xSearch, 2, 3, 1, 1);
		gridbagAdd(search, 3, 3, 1, 1);
		
		gridbagconstraints.anchor = GridBagConstraints.CENTER;

		pack();
		setResizable(false);
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new NotiBoard();
	}

}
