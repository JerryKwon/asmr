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

public class InqRegis extends JPanel {
	
	InqRegisButtonListener inqRegisButtonListener;
	
	private JLabel vInq, vTit, vCont;
	
	private JTextField xTit;
	
	private JTextArea xCont;
	
	private JButton regis, cancel;
	
	GridBagLayout gridbaglayout;
	GridBagConstraints gridbagconstraints;
	
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	private Color black = new Color(0,0,0);
	
	public InqRegis() {
		
		inqRegisButtonListener = new InqRegisButtonListener();
		
		gridbaglayout = new GridBagLayout();
		gridbagconstraints = new GridBagConstraints();
		
		vInq = new JLabel("¹®ÀÇµî·Ï");
		vInq.setFont(new Font("³ª´®°íµñ", Font.BOLD, 24));
		
		vTit = new JLabel("Á¦¸ñ");
		vTit.setFont(new Font("³ª´®°íµñ", Font.PLAIN, 16));
		xTit = new JTextField(20);
		
		vCont = new JLabel("³»¿ë");
		vCont.setFont(new Font("³ª´®°íµñ", Font.PLAIN, 16));
		xCont = new JTextArea(5,50);
		
		regis = new JButton("µî·Ï");
		regis.setFont(new Font("³ª´®°íµñ", Font.BOLD, 16));
		regis.setBackground(blue);
		regis.setForeground(white);
		regis.addActionListener(inqRegisButtonListener);
		
		cancel = new JButton("Ãë¼Ò");
		cancel.setFont(new Font("³ª´®°íµñ", Font.BOLD, 16));
		cancel.setBackground(white);
		cancel.setForeground(black);
		cancel.addActionListener(inqRegisButtonListener);
		
		InqRegisView();
		
	}
	
	private void InqRegisView() {
		
//		setTitle("¹®ÀÇµî·Ï");	
		
		gridbagconstraints.anchor = GridBagConstraints.WEST;
		gridbagconstraints.ipadx = 7;
		
		gridbagconstraints.weightx=1.0;
		gridbagconstraints.weighty=1.0;
		
		setLayout(gridbaglayout);

		gridbagAdd(vInq, 0, 0, 1, 1);
		
		gridbagAdd(vTit, 0, 1, 1, 1);
		gridbagAdd(xTit, 2, 1, 2, 1);
		
		gridbagAdd(vCont, 0, 2, 1, 1);
		gridbagAdd(xCont, 2, 2, 5, 1);
		
		gridbagAdd(regis, 4, 3, 1, 1);
		gridbagAdd(cancel, 5, 3, 1, 1);
		
		
		gridbagconstraints.anchor = GridBagConstraints.CENTER;

//		pack();
//		setResizable(false);
//		setVisible(true);
		
		
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
	
    class InqRegisButtonListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource().equals(regis)) {	
				
			}
			else if(e.getSource().equals(cancel)) {
				
			}
		}
		
	}
		
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new InqRegis();

	}

}
