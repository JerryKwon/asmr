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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Annc extends JPanel {
	
	private JLabel vRegisDate, vAnmlKinds, vKind, vSex, vDscvLoc, vFeat;
	
	private JTextField xRegisDate, xAnmlKinds, xKind, xSex, xDscvLoc, xFeat;
	
	private BufferedImage buttonIcon;
	
	private JButton imageButton;
	
	GridBagLayout gridbagLayout;
	GridBagConstraints gridbagConstraints;
	
	public Annc() throws IOException {
		gridbagLayout = new GridBagLayout();
		gridbagConstraints = new GridBagConstraints();
		
		vRegisDate = new JLabel("등록일자");
		xRegisDate = new JTextField(20);
		
		vAnmlKinds = new JLabel("동물종류");
		xAnmlKinds = new JTextField(20);
		
		vKind = new JLabel("품종");
		xKind = new JTextField(20);
		
		vSex = new JLabel("성별");
		xSex = new JTextField(20);
		
		vDscvLoc = new JLabel("발견장소");
		xDscvLoc = new JTextField(20);
		
		vFeat = new JLabel("특징");
		xFeat = new JTextField(20);
		
		buttonIcon = ImageIO.read(new File("./images/cat1.png"));
		imageButton = new JButton(new ImageIcon(buttonIcon));
		imageButton.setBorderPainted(false);
		imageButton.setContentAreaFilled(false);
		imageButton.setFocusPainted(false);
		
		AnncView();
	}
	
	private void AnncView() {
		
		gridbagConstraints.anchor = GridBagConstraints.WEST;
		gridbagConstraints.ipadx = 7;

		gridbagConstraints.weightx=1.0;
		gridbagConstraints.weighty=1.0;

		setLayout(gridbagLayout);
		
		gridbagAdd(vRegisDate, 5, 0, 1, 1);
		gridbagAdd(xRegisDate, 7, 0, 1, 1);
		
		gridbagAdd(vAnmlKinds, 5, 1, 1, 1);
		gridbagAdd(xAnmlKinds, 7, 1, 1, 1);
		
		gridbagAdd(vKind, 5, 2, 1, 1);
		gridbagAdd(xKind, 7, 2, 1, 1);
		
		gridbagAdd(vSex, 5, 3, 1, 1);
		gridbagAdd(xSex, 7, 3, 1, 1);
		
		gridbagAdd(vDscvLoc, 5, 4, 1, 1);
		gridbagAdd(xDscvLoc, 7, 4, 1, 1);
		
		gridbagAdd(vFeat, 5, 5, 1, 1);
		gridbagAdd(xFeat, 7, 5, 1, 1);
		
		gridbagAdd(imageButton, 0, 0, 4, 4);
		
		
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

	}

}
