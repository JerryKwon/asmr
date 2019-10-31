// ��ư ��ġ �̻���
package asmr;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class NotiWrtUpt extends JPanel {
	
	NotiWrtUptButtonListener notiWrtUptButtonListener;
	
	private JLabel vNoti, vTit, vCont;
	
	private JTextField xTit;
	
	private JTextArea xCont;
	
	private JButton save, cancel;
	
	GridBagLayout gridbaglayout;
	GridBagConstraints gridbagconstraints;
	
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	private Color black = new Color(0,0,0);
	
	public NotiWrtUpt() {
		
		notiWrtUptButtonListener = new NotiWrtUptButtonListener();
		
		gridbaglayout = new GridBagLayout();
		gridbagconstraints = new GridBagConstraints();
		
		vNoti = new JLabel("��������");
		vNoti.setFont(new Font("�������", Font.BOLD, 24));
		
		vTit = new JLabel("����");
		xTit = new JTextField(20);
		xTit.setFont(new Font("�������", Font.PLAIN, 16));
		
		vCont = new JLabel("����");
		xCont = new JTextArea(5,50);
		xCont.setFont(new Font("�������", Font.PLAIN, 16));
		
		save = new JButton("����");
		save.setFont(new Font("�������", Font.BOLD, 16));
		save.setBackground(blue);
		save.setForeground(white);
		save.addActionListener(notiWrtUptButtonListener);
		
		cancel = new JButton("���");
		cancel.setFont(new Font("�������", Font.BOLD, 16));
		cancel.setBackground(white);
		cancel.setForeground(black);
		cancel.addActionListener(notiWrtUptButtonListener);
		
		NotiWrtUptView();
		
	}
	
	private void NotiWrtUptView() {
		
//		setTitle("�Ű����_�Ϲݼ���");	
		
		gridbagconstraints.anchor = GridBagConstraints.WEST;
		gridbagconstraints.ipadx = 7;
		
		gridbagconstraints.weightx=1.0;
		gridbagconstraints.weighty=1.0;
		
		setLayout(gridbaglayout);

		gridbagAdd(vNoti, 0, 0, 1, 1);
		
		gridbagAdd(vTit, 0, 1, 1, 1);
		gridbagAdd(xTit, 2, 1, 3, 1);
		
		gridbagAdd(vCont, 0, 2, 1, 1);
		gridbagAdd(xCont, 2, 2, 7, 1);
		
		gridbagAdd(save, 5, 3, 1, 1);
		gridbagAdd(cancel, 6, 3, 1, 1);
		
		
		gridbagconstraints.anchor = GridBagConstraints.CENTER;

//		pack();
//		setResizable(false);
//		setVisible(true);
		
		
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
	
	
    class NotiWrtUptButtonListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource().equals(save)) {	
				
			}
			else if(e.getSource().equals(cancel)) {
				MainFrame.notiBoardCase();
			}
		}
		
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new NotiWrtUpt();
	}

}
