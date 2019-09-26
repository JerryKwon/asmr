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
		
		vVisitRegis = new JLabel("�湮���");
		
		vVisitDate = new JLabel("�湮����");
		xVisitDate = new JTextField(20);
		buttonIcon = ImageIO.read(new File("./images/cal1.png"));
		imageButton = new JButton(new ImageIcon(buttonIcon));
		imageButton.setBorderPainted(false);
		imageButton.setContentAreaFilled(false);
		imageButton.setFocusPainted(false);

		regis = new JButton("���");
		cancel = new JButton("���");
		
		vVisitCont = new JLabel("�湮����");
		xVisitCont = new JTextField(20);
		
		VisitRegisPopupView();
		
	}
	
	private void VisitRegisPopupView() {
		setTitle("�湮���");
		
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
	      //���� ���� �� gridx, gridy���� 0 			
				
		gridbagConstraints.gridwidth  = w;	//����	
		gridbagConstraints.gridheight = h;	//����	
	     			
	      			
	    gridbagLayout.setConstraints(c, gridbagConstraints); //������Ʈ�� ������Ʈ ��ġ+ũ�� ������ ���� GridBagLayout�� ��ġ			
				
	   add(c);			
				
	   }	

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		new VisitRegisPopup();
	}

}
