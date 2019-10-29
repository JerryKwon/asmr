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
		
		vInq = new JLabel("°øÁö»çÇ×");
		vInq.setFont(new Font("³ª´®°íµñ", Font.BOLD, 24));
		
		vWrt = new JLabel("ÀÛ¼ºÀÚ");
		vWrt.setFont(new Font("³ª´®°íµñ", Font.PLAIN, 16));
		xWrt = new JTextField(20);
		xWrt.setEditable(false);
		
		vWrtDttm = new JLabel("ÀÛ¼ºÀÏ½Ã");
		vWrtDttm.setFont(new Font("³ª´®°íµñ", Font.PLAIN, 16));
		xWrtDttm = new JTextField(20);
		xWrtDttm.setEditable(false);
		
		vTit = new JLabel("Á¦¸ñ");
		vTit.setFont(new Font("³ª´®°íµñ", Font.PLAIN, 16));
		xTit = new JTextField(20);
		xTit.setEditable(false);
		
		vCont = new JLabel("³»¿ë");
		vCont.setFont(new Font("³ª´®°íµñ", Font.PLAIN, 16));
		xCont = new JTextArea(5,50);
		xCont.setEditable(false);
		
		
		update = new JButton("¼öÁ¤");
		update.setFont(new Font("³ª´®°íµñ", Font.BOLD, 16));
		update.setBackground(blue);
		update.setForeground(white);
		update.addActionListener(inqPostButtonListener);
		
		ans = new JButton("´äº¯");
		ans.setFont(new Font("³ª´®°íµñ", Font.BOLD, 16));
		ans.setBackground(red);
		ans.setForeground(white);
		ans.addActionListener(inqPostButtonListener);
		
		
		getBack = new JButton("¸ñ·ÏÀ¸·Î");
		getBack.setFont(new Font("³ª´®°íµñ", Font.BOLD, 16));
		getBack.setBackground(white);
		getBack.setForeground(black);
		getBack.addActionListener(inqPostButtonListener);
		
		InqPostView();
		
	}
	
	private void InqPostView() {
		
		setTitle("¹®ÀÇ»çÇ×°Ô½Ã±Û");	
		
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
	      //°¡Àå ¿ÞÂÊ À§ gridx, gridy°ªÀº 0 			
				
		gridbagconstraints.gridwidth  = w;	//³ÐÀÌ	
		gridbagconstraints.gridheight = h;	//³ôÀÌ	
	     			
	      			
	    gridbaglayout.setConstraints(c, gridbagconstraints); //ÄÄÆ÷³ÍÆ®¸¦ ÄÄÆ÷³ÍÆ® À§Ä¡+Å©±â Á¤º¸¿¡ µû¶ó GridBagLayout¿¡ ¹èÄ¡			
				
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
