package asmr;
import javax.swing.*;
import java.awt.*;


public class MotherFrame extends JFrame {
	private JPanel pSignInHeader, pEmpMyPage;
	
	GridBagLayout gridbaglayout;
	GridBagConstraints gridbagconstraints;
	
	public MotherFrame() {
		gridbaglayout = new GridBagLayout();		
		gridbagconstraints = new GridBagConstraints();
		
		pSignInHeader = new SignedInHeader();
		pEmpMyPage = new EmpMyPage();
		
		MotherFrameView();
	}
	private void MotherFrameView() {
		setTitle("ASMR");
		setExtendedState(MAXIMIZED_BOTH);
		
		gridbagconstraints.anchor = GridBagConstraints.CENTER;		
		gridbagconstraints.ipadx = 7;		
				
		gridbagconstraints.weightx=1.0;		
		gridbagconstraints.weighty=1.0;	
		
		setLayout(gridbaglayout);
		gridbagAdd(pSignInHeader, 0,0,1,1);
		gridbagAdd(pEmpMyPage, 0,1,1,1);
		
		pack();
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		new MotherFrame();
	}

}
