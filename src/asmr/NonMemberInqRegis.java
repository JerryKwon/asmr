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
		
		vInq = new JLabel("문의등록");
		
		vTit = new JLabel("제목");
		xTit = new JTextField(20);
		
		vName = new JLabel("성명");
		xName = new JTextField(20);
		
		vTelNo = new JLabel("전화번호");
		xTelNo = new JTextField(20);
		
		vCont = new JLabel("내용");
		xCont = new JTextArea(5,50);
		
		regis = new JButton("등록");
		cancel = new JButton("취소");
		
		NonMemberInqView();
		
	}
	
	private void NonMemberInqView() {
		
		setTitle("비회원_문의등록");	
		
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
	      //가장 왼쪽 위 gridx, gridy값은 0 			
				
		gridbagconstraints.gridwidth  = w;	//넓이	
		gridbagconstraints.gridheight = h;	//높이	
	     			
	      			
	    gridbaglayout.setConstraints(c, gridbagconstraints); //컴포넌트를 컴포넌트 위치+크기 정보에 따라 GridBagLayout에 배치			
				
	   add(c);			
				
	   }
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new NonMemberInqRegis();

	}

}
