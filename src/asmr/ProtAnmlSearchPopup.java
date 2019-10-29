package asmr;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ProtAnmlSearchPopup extends JFrame {
	
	ProtAnmlSearchPopupButtonListener protAnmlSearchPopupButtonListener;
	
	private JLabel vProtAnmlSearch, vAbanName;
	
	private JTextField xAbanName;
	
	private JButton confirm, cancel, search;
	
	private JTable eProtAnmlList;
	
	private JScrollPane scrollPane1;
	
	GridBagLayout gridbagLayout;
	GridBagConstraints gridbagConstraints;
	
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	private Color black = new Color(0,0,0);

	
	private final String[] col1 = {"유기동물명","동물종류","품종","특징","케이지"};
	private DefaultTableModel model1 = new DefaultTableModel(col1,0);
	
	public ProtAnmlSearchPopup() {
		
		protAnmlSearchPopupButtonListener = new ProtAnmlSearchPopupButtonListener();
		// TODO Auto-generated constructor stub
		gridbagLayout = new GridBagLayout();
		gridbagConstraints = new GridBagConstraints();
		
		eProtAnmlList = new JTable(model1){
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
		scrollPane1 = new JScrollPane(eProtAnmlList);
		scrollPane1.setPreferredSize(new Dimension(500,100));
		
		vProtAnmlSearch = new JLabel("보호동물검색");
		vProtAnmlSearch.setFont(new Font("나눔고딕", Font.BOLD, 24));
		
		vAbanName = new JLabel("유기동물명");
		xAbanName = new JTextField(20);
		vAbanName.setFont(new Font("나눔고딕", Font.PLAIN, 16));
		
		
		search = new JButton("검색");
		search.setBackground(blue);
		search.setForeground(white);
		search.addActionListener(protAnmlSearchPopupButtonListener);
		search.setFont(new Font("나눔고딕", Font.BOLD, 16));
		
		confirm = new JButton("확인");
		confirm.setBackground(blue);
		confirm.setForeground(white);
		confirm.addActionListener(protAnmlSearchPopupButtonListener);
		confirm.setFont(new Font("나눔고딕", Font.BOLD, 16));
		
		cancel = new JButton("취소");
		cancel.setBackground(white);
		cancel.setForeground(black);
		cancel.addActionListener(protAnmlSearchPopupButtonListener);
		cancel.setFont(new Font("나눔고딕", Font.BOLD, 16));
		

		ProtAnmlSearchPopupView();
	}
	
	private void ProtAnmlSearchPopupView() {
		
		setTitle("보호동물검색");
		
		gridbagConstraints.anchor = GridBagConstraints.WEST;
		gridbagConstraints.ipadx = 7;

		gridbagConstraints.weightx=1.0;
		gridbagConstraints.weighty=1.0;

		setLayout(gridbagLayout);
		
		gridbagAdd(vProtAnmlSearch, 0, 0, 1, 1);
		
		gridbagAdd(vAbanName, 0, 1, 1, 1);
		gridbagAdd(xAbanName, 2, 1, 2, 1);
		gridbagAdd(search, 5, 1, 1, 1);
		
		gridbagAdd(scrollPane1, 0, 2, 6, 1);
		
		gridbagAdd(confirm, 2, 3, 1, 1);
		gridbagAdd(cancel, 3, 3, 1, 1);
		
		gridbagConstraints.anchor = GridBagConstraints.CENTER;

		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
	}
	
	private void gridbagAdd(Component c, int x, int y, int w, int h) {			
		
		gridbagConstraints.gridx = x;		
		gridbagConstraints.gridy = y; 		
	      //가장 왼쪽 위 gridx, gridy값은 0 			
				
		gridbagConstraints.gridwidth  = w;	//넓이	
		gridbagConstraints.gridheight = h;	//높이	
	     			
	      			
	    gridbagLayout.setConstraints(c, gridbagConstraints); //컴포넌트를 컴포넌트 위치+크기 정보에 따라 GridBagLayout에 배치			
				
	   add(c);			
				
	   }
	
	class ProtAnmlSearchPopupButtonListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource().equals(search)) {	
				
			}
			else if (e.getSource().equals(confirm)) {
				
			}
			else if(e.getSource().equals(cancel)) {
				dispose();
			}
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new ProtAnmlSearchPopup();

	}

}
