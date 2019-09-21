package asmr;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class MotherFrame extends JFrame implements ActionListener{
	private JPanel pHeader, pContents;
	
	GridBagLayout gridbaglayout;
	GridBagConstraints gridbagconstraints;
	
	public MotherFrame() {
		gridbaglayout = new GridBagLayout();		
		gridbagconstraints = new GridBagConstraints();
		
		pHeader = new SignedInHeader();
		
		pContents = new EmpMyPage();
		
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
		gridbagAdd(pHeader, 0,0,1,1);
		gridbagAdd(pContents, 0,1,1,1);
		
		pack();
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

	public void actionPerformed(ActionEvent e) {
		String cont = e.getActionCommand();
		switch(cont) {
		case "���͵��":
			pContents = new MainPage();
			break;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MotherFrame();
	}

}
