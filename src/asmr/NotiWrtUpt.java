// ��ư ��ġ �̻���
package src.asmr;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class NotiWrtUpt extends JFrame {
	
	private JLabel vNoti, vTit, vCont;
	
	private JTextField xTit;
	
	private JTextArea xCont;
	
	private JButton save, cancel;
	
	GridBagLayout gridbaglayout;
	GridBagConstraints gridbagconstraints;
	
	private NotiWrtUpt() {
		
		gridbaglayout = new GridBagLayout();
		gridbagconstraints = new GridBagConstraints();
		
		vNoti = new JLabel("��������");
		
		vTit = new JLabel("����");
		xTit = new JTextField(20);
		
		vCont = new JLabel("����");
		xCont = new JTextArea(5,50);
		
		save = new JButton("����");
		cancel = new JButton("���");
		
		NotiWrtUptView();
		
	}
	
	private void NotiWrtUptView() {
		
		setTitle("�Ű����_�Ϲݼ���");	
		
		gridbagconstraints.anchor = GridBagConstraints.WEST;
		gridbagconstraints.ipadx = 7;
		
		gridbagconstraints.weightx=1.0;
		gridbagconstraints.weighty=1.0;
		
		setLayout(gridbaglayout);

		gridbagAdd(vNoti, 0, 0, 1, 1);
		
		gridbagAdd(vTit, 0, 1, 1, 1);
		gridbagAdd(xTit, 2, 1, 2, 1);
		
		gridbagAdd(vCont, 0, 2, 1, 1);
		gridbagAdd(xCont, 2, 2, 5, 1);
		
		gridbagAdd(save, 4, 3, 1, 1);
		gridbagAdd(cancel, 5, 3, 1, 1);
		
		
		gridbagconstraints.anchor = GridBagConstraints.CENTER;

		pack();
		setResizable(false);
		setVisible(true);
		
		
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
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new NotiWrtUpt();
	}

}
