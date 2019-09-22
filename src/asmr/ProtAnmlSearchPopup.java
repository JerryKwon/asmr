package asmr;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ProtAnmlSearchPopup extends JFrame {
	
	private JLabel vProtAnmlSearch, vAbanName;
	
	private JTextField xAbanName;
	
	private JButton confirm, cancel, search;
	
	private JTable eProtAnmlList;
	
	private JScrollPane scrollPane1;
	
	GridBagLayout gridbagLayout;
	GridBagConstraints gridbagConstraints;
	
	private final String[] col1 = {"유기동물명","동물종류","품종","특징","케이지"};
	private DefaultTableModel model1 = new DefaultTableModel(col1,0);
	
	public ProtAnmlSearchPopup() {
		// TODO Auto-generated constructor stub
		gridbagLayout = new GridBagLayout();
		gridbagConstraints = new GridBagConstraints();
		
		eProtAnmlList = new JTable(model1);
		scrollPane1 = new JScrollPane(eProtAnmlList);
		scrollPane1.setPreferredSize(new Dimension(500,100));
		
		vProtAnmlSearch = new JLabel("보호동물검색");
		
		vAbanName = new JLabel("유기동물명");
		xAbanName = new JTextField(20);
		search = new JButton("검색");
		
		confirm = new JButton("확인");
		cancel = new JButton("취소");
		
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new ProtAnmlSearchPopup();

	}

}
