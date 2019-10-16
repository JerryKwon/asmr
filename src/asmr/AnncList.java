package src.asmr;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

// ������
public class AnncList extends JFrame{
	
	private JLabel vAnncList;
	
	private JButton regis;
	
	GridBagLayout gridbagLayout;
	GridBagConstraints gridbagConstraints;
	
	public AnncList() {
		
		gridbagLayout = new GridBagLayout();
		gridbagConstraints = new GridBagConstraints();
		
		vAnncList = new JLabel("������");
		
		regis = new JButton("���");
		
		AnncListView();
		

		
	}
	
	
	private void AnncListView() {
		
		
		gridbagConstraints.anchor = GridBagConstraints.WEST;
		gridbagConstraints.ipadx = 7;

		gridbagConstraints.weightx=1.0;
		gridbagConstraints.weighty=1.0;

		setLayout(gridbagLayout);
		
		gridbagAdd(vAnncList, 0, 0, 1, 1);
		
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
		
		new AnncList();

	}

}
