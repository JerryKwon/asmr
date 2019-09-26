package asmr;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class VisitRegisPopup extends JFrame {
	
	private JLabel vVisitRegis, vVisitDate, vVisitCont;
	
	private JTextField xVisitDate, xVisitCont;
	
	private JButton regis, cancel;
	
	private JButton imageButton;
	
	private BufferedImage buttonIcon;
	
	GridBagLayout gridbagLayout;
	GridBagConstraints gridbagConstraints;
	
	public VisitRegisPopup() throws IOException {
		
		gridbagLayout = new GridBagLayout();
		gridbagConstraints = new GridBagConstraints();
		
		vVisitRegis = new JLabel("방문등록");
		
		vVisitDate = new JLabel("방문일자");
		xVisitDate = new JTextField(20);
		buttonIcon = ImageIO.read(new File("./images/cal1.png"));
		imageButton = new JButton(new ImageIcon(buttonIcon));
		imageButton.setBorderPainted(false);
		imageButton.setContentAreaFilled(false);
		imageButton.setFocusPainted(false);

		regis = new JButton("등록");
		cancel = new JButton("취소");
		
		vVisitCont = new JLabel("방문내용");
		xVisitCont = new JTextField(20);
		
		VisitRegisPopupView();
		
	}
	
	private void VisitRegisPopupView() {
		setTitle("방문등록");
		
		gridbagConstraints.anchor = GridBagConstraints.WEST;
		gridbagConstraints.ipadx = 7;

		gridbagConstraints.weightx=1.0;
		gridbagConstraints.weighty=1.0;

		setLayout(gridbagLayout);
		
		gridbagAdd(vVisitRegis, 0, 0, 1, 1);
		
		gridbagAdd(vVisitDate, 0, 1, 1, 1);
		gridbagAdd(xVisitDate, 2, 1, 1, 1);
		gridbagAdd(imageButton, 4, 1, 1, 1);
		
		gridbagAdd(vVisitCont, 0, 2, 1, 1);
		gridbagAdd(xVisitCont, 2, 2, 1, 1);
		
		gridbagAdd(regis, 2, 3, 1, 1);
		gridbagAdd(cancel, 3, 3, 1, 1);
		
		pack();
		setResizable(false);
		setVisible(true);
		
	}
	private void gridbagAdd(Component c, int x, int y, int w, int h) {			
		
		gridbagConstraints.gridx = x;		
		gridbagConstraints.gridy = y; 		
	      //가장 왼쪽 위 gridx, gridy값은 0 			
				
		gridbagConstraints.gridwidth  = w;	//넓이	
		gridbagConstraints.gridheight = h;	//높이	
	     			
	      			
	    gridbagLayout.setConstraints(c, gridbagConstraints); //컴포넌트를 컴포넌트 위치+크기 정보에 따라 GridBagLayout에 배치			
				
	   add(c);			
				
	   }	

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		new VisitRegisPopup();
	}

}
