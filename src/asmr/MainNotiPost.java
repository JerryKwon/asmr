package asmr;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MainNotiPost extends JPanel {
	
	private JLabel vNoti, vWrt, vWrtDttm, vTit, vCont;
	private JTextField xTit, xWrt, xWrtDttm;
	private JTextArea xCont;
	private JButton update, getBack;
	
	GridBagLayout gridbaglayout;
	GridBagConstraints gridbagconstraints;
	
	NotiPostButtonListener notiPostButtonListener;
	
	Map<String, Serializable> notiData;
	
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	private Color black = new Color(0,0,0);
	
	public MainNotiPost(String postNo) {
		setBackground(MainFrame.bgc);
		notiPostButtonListener = new NotiPostButtonListener();
		
		gridbaglayout = new GridBagLayout();
		gridbagconstraints = new GridBagConstraints();
		
		vNoti = new JLabel("°øÁö»çÇ×");
		vNoti.setFont(new Font("³ª´®°íµñ", Font.BOLD, 24));
		
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
		update.addActionListener(notiPostButtonListener);
		update.setEnabled(false);
		
		getBack = new JButton("¸ÞÀÎÀ¸·Î");
		getBack.setFont(new Font("³ª´®°íµñ", Font.BOLD, 16));
		getBack.setBackground(white);
		getBack.setForeground(black);
		getBack.addActionListener(notiPostButtonListener);
		
		
		JComponent[] vComps = {vWrt, vWrtDttm, vTit, vCont};
		ChangeFont(vComps, new Font("³ª´®°íµñ", Font.PLAIN, 16));
		
		notiData = MainPageData.getNotiData(postNo);
		setData();
		MainNotiPostView();
		
	}
	
	private void MainNotiPostView() {
		
		gridbagconstraints.anchor = GridBagConstraints.WEST;
		gridbagconstraints.ipadx = 7;
		gridbagconstraints.insets = new Insets(5,5,5,5);
		
		gridbagconstraints.weightx=1.0;
		gridbagconstraints.weighty=1.0;
		
		setLayout(gridbaglayout);

		gridbagAdd(vNoti, 0, 0, 1, 1);
		
		gridbagAdd(vWrt, 0, 1, 1, 1);
		gridbagAdd(xWrt, 2, 1, 1, 1);
		gridbagAdd(vWrtDttm, 3, 1, 1, 1);
		gridbagAdd(xWrtDttm, 4, 1, 2, 1);
		
		gridbagAdd(vTit, 0, 2, 1, 1);
		gridbagAdd(xTit, 2, 2, 1, 1);
		
		gridbagAdd(vCont, 0, 3, 1, 1);
		gridbagAdd(xCont, 2, 3, 6, 2);
		
		gridbagAdd(update, 4, 7, 1, 1);
		gridbagAdd(getBack, 5, 7, 1, 1);
		
		gridbagconstraints.anchor = GridBagConstraints.CENTER;

//		pack();
//		setResizable(false);
//		setVisible(true);
		
	}
	private void setData(){
		xTit.setText(notiData.get("Á¦¸ñ").toString());
		xCont.setText(notiData.get("³»¿ë").toString());
		xWrt.setText(notiData.get("ÀÛ¼ºÀÚ").toString());
		xWrtDttm.setText(notiData.get("ÀÛ¼ºÀÏ½Ã").toString());
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
	
	private void ChangeFont(JComponent[] comps, Font font) {
    	for(JComponent comp: comps) {
    		comp.setFont(font);
    	}
    }
    class NotiPostButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(getBack)) {
				MainFrame.mainCase();
			}
		}
		
	}

}
