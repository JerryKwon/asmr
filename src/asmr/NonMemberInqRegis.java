package src.asmr;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class NonMemberInqRegis extends JFrame {
	private JLabel vInq, vTit, vCont, vName, vTelNo;
	
	private JTextField xTit, xName, xTelNo;
	
	private JTextArea xCont;
	
	private JButton regis, cancel;
	
	GridBagLayout gridbaglayout;
	GridBagConstraints gridbagconstraints;
	
	private NonMemberInqRegis() {
		
		gridbaglayout = new GridBagLayout();
		gridbagconstraints = new GridBagConstraints();
		
		vInq = new JLabel("���ǵ��");
		
		vTit = new JLabel("����");
		xTit = new JTextField(20);
		
		vName = new JLabel("����");
		xName = new JTextField(20);
		
		vTelNo = new JLabel("��ȭ��ȣ");
		xTelNo = new JTextField(20);
		
		vCont = new JLabel("����");
		xCont = new JTextArea(5,50);
		
		regis = new JButton("���");
		cancel = new JButton("���");
		
		NonMemberInqView();
		
	}
	
	private void NonMemberInqView() {
		
		setTitle("��ȸ��_���ǵ��");	
		
		gridbagconstraints.anchor = GridBagConstraints.WEST;
		gridbagconstraints.ipadx = 7;
		
		gridbagconstraints.weightx=1.0;
		gridbagconstraints.weighty=1.0;
		
		setLayout(gridbaglayout);

		gridbagAdd(vInq, 0, 0, 1, 1);
		
		gridbagAdd(vTit, 0, 1, 1, 1);
		gridbagAdd(xTit, 2, 1, 2, 1);
		
		gridbagAdd(vName, 0, 2, 1, 1);
		gridbagAdd(xName, 2, 2, 5, 1);
		
		gridbagAdd(vTelNo, 0, 3, 1, 1);
		gridbagAdd(xTelNo, 2, 3, 1, 1);
		
		gridbagAdd(vCont, 0, 4, 1, 1);
		gridbagAdd(xCont, 2, 4, 5, 1);
		
		gridbagAdd(regis, 4, 5, 1, 1);
		gridbagAdd(cancel, 5, 5, 1, 1);
		
		
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
		new NonMemberInqRegis();

	}

}
