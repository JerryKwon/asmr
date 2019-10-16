package asmr;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class InqPost extends JFrame {
	
	private JLabel vInq, vWrt, vWrtDttm, vTit, vCont;
	
	private JTextField xWrt, xWrtDttm, xTit;
	
	private JTextArea xCont;
	
	private JButton update, ans, getBack;
	
	GridBagLayout gridbaglayout;
	GridBagConstraints gridbagconstraints;
	
	private InqPost() {
		
		gridbaglayout = new GridBagLayout();
		gridbagconstraints = new GridBagConstraints();
		
		vInq = new JLabel("��������");
		
		vWrt = new JLabel("�ۼ���");
		xWrt = new JTextField(20);
		
		vWrtDttm = new JLabel("�ۼ��Ͻ�");
		xWrtDttm = new JTextField(20);
		
		vTit = new JLabel("����");
		xTit = new JTextField(20);
		
		vCont = new JLabel("����");
		xCont = new JTextArea(5,50);
		
		
		update = new JButton("����");
		ans = new JButton("�亯");
		getBack = new JButton("�������");
		
		InqPostView();
		
	}
	
	private void InqPostView() {
		
		setTitle("���ǻ��װԽñ�");	
		
		gridbagconstraints.anchor = GridBagConstraints.WEST;
		gridbagconstraints.ipadx = 7;
		
		gridbagconstraints.weightx=1.0;
		gridbagconstraints.weighty=1.0;
		
		setLayout(gridbaglayout);

		gridbagAdd(vInq, 0, 0, 1, 1);
		
		gridbagAdd(vWrt, 0, 1, 1, 1);
		gridbagAdd(xWrt, 2, 1, 1, 1);
		gridbagAdd(vWrtDttm, 3, 1, 1, 1);
		gridbagAdd(xWrtDttm, 4, 1, 2, 1);
		
		gridbagAdd(vTit, 0, 2, 1, 1);
		gridbagAdd(xTit, 2, 2, 1, 1);
		
		gridbagAdd(vCont, 0, 3, 1, 1);
		gridbagAdd(xCont, 2, 3, 6, 2);
		
		gridbagAdd(update, 4, 7, 1, 1);
		gridbagAdd(ans, 5, 7, 1, 1);
		gridbagAdd(getBack, 6, 7, 1, 1);
		
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
		new InqPost();
	}

}
