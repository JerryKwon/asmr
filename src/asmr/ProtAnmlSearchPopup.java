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
	
	private final String[] col1 = {"���⵿����","��������","ǰ��","Ư¡","������"};
	private DefaultTableModel model1 = new DefaultTableModel(col1,0);
	
	public ProtAnmlSearchPopup() {
		// TODO Auto-generated constructor stub
		gridbagLayout = new GridBagLayout();
		gridbagConstraints = new GridBagConstraints();
		
		eProtAnmlList = new JTable(model1);
		scrollPane1 = new JScrollPane(eProtAnmlList);
		scrollPane1.setPreferredSize(new Dimension(500,100));
		
		vProtAnmlSearch = new JLabel("��ȣ�����˻�");
		
		vAbanName = new JLabel("���⵿����");
		xAbanName = new JTextField(20);
		search = new JButton("�˻�");
		
		confirm = new JButton("Ȯ��");
		cancel = new JButton("���");
		
		ProtAnmlSearchPopupView();
	}
	
	private void ProtAnmlSearchPopupView() {
		
		setTitle("��ȣ�����˻�");
		
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
	      //���� ���� �� gridx, gridy���� 0 			
				
		gridbagConstraints.gridwidth  = w;	//����	
		gridbagConstraints.gridheight = h;	//����	
	     			
	      			
	    gridbagLayout.setConstraints(c, gridbagConstraints); //������Ʈ�� ������Ʈ ��ġ+ũ�� ������ ���� GridBagLayout�� ��ġ			
				
	   add(c);			
				
	   }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new ProtAnmlSearchPopup();

	}

}
