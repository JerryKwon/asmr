package asmr;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class InqPost extends JFrame {
	
	InqPostButtonListener inqPostButtonListener;
	
	private JLabel vInq, vWrt, vWrtDttm, vTit, vCont;
	
	private JTextField xWrt, xWrtDttm, xTit;
	
	private JTextArea xCont;
	
	private JButton update, ans, getBack;
	
	GridBagLayout gridbaglayout;
	GridBagConstraints gridbagconstraints;
	
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	private Color black = new Color(0,0,0);
	private Color red = new Color(217,0,27);
	
	private InqPost() {
		
		inqPostButtonListener = new InqPostButtonListener();
		
		gridbaglayout = new GridBagLayout();
		gridbagconstraints = new GridBagConstraints();
		
		vInq = new JLabel("��������");
		vInq.setFont(new Font("�������", Font.BOLD, 24));
		
		vWrt = new JLabel("�ۼ���");
		vWrt.setFont(new Font("�������", Font.PLAIN, 16));
		xWrt = new JTextField(20);
		xWrt.setEditable(false);
		
		vWrtDttm = new JLabel("�ۼ��Ͻ�");
		vWrtDttm.setFont(new Font("�������", Font.PLAIN, 16));
		xWrtDttm = new JTextField(20);
		xWrtDttm.setEditable(false);
		
		vTit = new JLabel("����");
		vTit.setFont(new Font("�������", Font.PLAIN, 16));
		xTit = new JTextField(20);
		xTit.setEditable(false);
		
		vCont = new JLabel("����");
		vCont.setFont(new Font("�������", Font.PLAIN, 16));
		xCont = new JTextArea(5,50);
		xCont.setEditable(false);
		
		
		update = new JButton("����");
		update.setFont(new Font("�������", Font.BOLD, 16));
		update.setBackground(blue);
		update.setForeground(white);
		update.addActionListener(inqPostButtonListener);
		
		ans = new JButton("�亯");
		ans.setFont(new Font("�������", Font.BOLD, 16));
		ans.setBackground(red);
		ans.setForeground(white);
		ans.addActionListener(inqPostButtonListener);
		
		
		getBack = new JButton("�������");
		getBack.setFont(new Font("�������", Font.BOLD, 16));
		getBack.setBackground(white);
		getBack.setForeground(black);
		getBack.addActionListener(inqPostButtonListener);
		
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
	
    class InqPostButtonListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource().equals(update)) {	
				
			}
			else if(e.getSource().equals(getBack)) {
				
			}
			else if(e.getSource().equals(ans)) {
				
			}
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new InqPost();
	}

}
