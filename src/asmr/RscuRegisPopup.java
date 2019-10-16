package src.asmr;

import java.awt.Color;
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

public class RscuRegisPopup extends JFrame {
	
	private JLabel vRscuRegis, vRscuDttm, vRscuLoc, vRepRscuCrewName;
	
	private JTextField xRscuDttm, xRscuLoc, xRepRscuCrewName;
	
	private JButton confirm, cancel, imageButton, btnSearch;
	
	private BufferedImage buttonIcon;
	
	
	GridBagLayout gridbagLayout;
	GridBagConstraints gridbagConstraints;
	
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	
	public RscuRegisPopup() throws IOException  {
		gridbagLayout = new GridBagLayout();
		gridbagConstraints = new GridBagConstraints();
		
		vRscuRegis = new JLabel("�������");
		
		vRscuDttm = new JLabel("�����Ͻ�");
		xRscuDttm = new JTextField(20);
		xRscuDttm.setEditable(false);
		
		vRscuLoc = new JLabel("�������");
		xRscuLoc = new JTextField(20);
		
		vRepRscuCrewName = new JLabel("��ǥ���������");
		xRepRscuCrewName = new JTextField(20);
		xRepRscuCrewName.setEditable(false);
		
		btnSearch = new JButton("�˻�");
		btnSearch.setBackground(blue);
		btnSearch.setForeground(white);
		
		confirm = new JButton("Ȯ��");
		confirm.setBackground(blue);
		confirm.setForeground(white);
		cancel = new JButton("���");
		
		buttonIcon = ImageIO.read(new File("./images/cal1.png"));
		imageButton = new JButton(new ImageIcon(buttonIcon));
		imageButton.setBorderPainted(false);
		imageButton.setContentAreaFilled(false);
		imageButton.setFocusPainted(false);
		
		RscuRegisPopupView();
	}
	
	private void RscuRegisPopupView() {
		
		setTitle("�������");
		
		gridbagConstraints.anchor = GridBagConstraints.WEST;
		gridbagConstraints.ipadx = 7;

		gridbagConstraints.weightx=1.0;
		gridbagConstraints.weighty=1.0;

		setLayout(gridbagLayout);
		
		gridbagAdd(vRscuRegis, 0, 0, 1, 1);
		
		gridbagAdd(vRscuDttm, 0, 1, 1, 1);
		gridbagAdd(xRscuDttm, 2, 1, 2, 1);
		gridbagAdd(imageButton, 5, 1, 1, 1);
		
		gridbagAdd(vRscuLoc, 0, 2, 1, 1);
		gridbagAdd(xRscuLoc, 2, 2, 2, 1);
		
		gridbagAdd(vRepRscuCrewName, 0, 4, 1, 1);
		gridbagAdd(xRepRscuCrewName, 2, 4, 1, 1);
		
		gridbagAdd(btnSearch, 4, 4, 1, 1);
		
		gridbagAdd(confirm,2, 5, 1, 1);
		gridbagAdd(cancel, 3, 5, 1, 1);
		
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
	
	public static void main(String[] args) throws IOException  {
		// TODO Auto-generated method stub
		
		new RscuRegisPopup();

	}

}
