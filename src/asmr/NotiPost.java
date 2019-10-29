package asmr;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class NotiPost extends JPanel {
	
	private JLabel vNoti, vWrt, vWrtDttm, vTit, vCont;
	
	private JTextField xWrt, xWrtDttm, xTit;
	
	private JTextArea xCont;
	
	private JButton update, getBack;
	
	GridBagLayout gridbaglayout;
	GridBagConstraints gridbagconstraints;
	
	NotiPostButtonListener notiPostButtonListener;
	
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	private Color black = new Color(0,0,0);
	
	private NotiPost() {
		
		notiPostButtonListener = new NotiPostButtonListener();
		
		gridbaglayout = new GridBagLayout();
		gridbagconstraints = new GridBagConstraints();
		
		vNoti = new JLabel("공지사항");
		vNoti.setFont(new Font("나눔고딕", Font.BOLD, 24));
		
		vWrt = new JLabel("작성자");
		xWrt = new JTextField(20);
		xWrt.setEditable(false);
		
		vWrtDttm = new JLabel("작성일시");
		xWrtDttm = new JTextField(20);
		xWrtDttm.setEditable(false);
		
		vTit = new JLabel("제목");
		xTit = new JTextField(20);
		xTit.setEditable(false);
		
		vCont = new JLabel("내용");
		xCont = new JTextArea(5,50);
		xCont.setEditable(false);
		
		
		update = new JButton("수정");
		update.setFont(new Font("나눔고딕", Font.BOLD, 16));
		update.setBackground(blue);
		update.setForeground(white);
		update.addActionListener(notiPostButtonListener);
		
		getBack = new JButton("목록으로");
		getBack.setFont(new Font("나눔고딕", Font.BOLD, 16));
		getBack.setBackground(white);
		getBack.setForeground(black);
		getBack.addActionListener(notiPostButtonListener);
		
		
		JComponent[] vComps = {vWrt, vWrtDttm, vTit, vCont};
		ChangeFont(vComps, new Font("나눔고딕", Font.PLAIN, 16));
		
		NotiPostView();
		
	}
	
	private void NotiPostView() {
		
//		setTitle("신고배정_일반센터");	
		
		gridbagconstraints.anchor = GridBagConstraints.WEST;
		gridbagconstraints.ipadx = 7;
		
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
	
	private void gridbagAdd(Component c, int x, int y, int w, int h) {			
		
		gridbagconstraints.gridx = x;		
		gridbagconstraints.gridy = y; 		
	      //가장 왼쪽 위 gridx, gridy값은 0 			
				
		gridbagconstraints.gridwidth  = w;	//넓이	
		gridbagconstraints.gridheight = h;	//높이	
	     			
	      			
	    gridbaglayout.setConstraints(c, gridbagconstraints); //컴포넌트를 컴포넌트 위치+크기 정보에 따라 GridBagLayout에 배치			
				
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
			// TODO Auto-generated method stub
			if(e.getSource().equals(update)) {	
				
			}
			else if(e.getSource().equals(getBack)) {
				
			}
		}
		
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new NotiPost();

	}

}
