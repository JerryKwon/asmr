package asmr;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class InqAnsBoard extends JPanel {
	
	InqAnsBoardButtonListener inqAnsBoardButtonListener;
	
	// 페이징 미구현, 페이징 번호 없음!
	private JLabel vInqAns;
	
	private JTextField xSearch;
	
	private JComboBox<String> cbSearch;
	
	private JTable eInqAnsList;
	
	private JScrollPane scrollPane;
	
	private String[] searchDiv = {"제목", "작성자"};
	
	private JButton regis, search;
	
	private final String[] col = {"번호","제목","작성자","작성일시"};
	
	private DefaultTableModel model = new DefaultTableModel(col,0);
	
	GridBagLayout gridbaglayout;
	GridBagConstraints gridbagconstraints;
	
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	
	public InqAnsBoard() {
		
		inqAnsBoardButtonListener = new InqAnsBoardButtonListener();
		
		gridbaglayout = new GridBagLayout();
		gridbagconstraints = new GridBagConstraints();
		
		vInqAns = new JLabel("문의답변게시판");
		vInqAns.setFont(new Font("나눔고딕", Font.BOLD, 24));
		eInqAnsList = new JTable(model){
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
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
		
		InqAnsBoardView();
	
	}

	private void InqAnsBoardView() {
		
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
	
    class InqAnsBoardButtonListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource().equals(regis)) {	
				MainFrame.qnaCase();
				
			}
			else if(e.getSource().equals(search)) {
				
			}
		}
		
	}
	
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//new InqAnsBoard();

	}

}
