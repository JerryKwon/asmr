package asmr;

import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Annc extends JPanel {
	
	private JLabel vRegisDate, vAnmlKinds, vKind, vSex, vDscvLoc, vFeat;
	
	private JTextField xRegisDate, xAnmlKinds, xKind, xSex, xDscvLoc, xFeat;
	
	private BufferedImage buttonIcon;
	
	private JButton imageButton;
	private ImageIcon noImageIcon;
	
	Map<String, Serializable> annc;
	
	GridBagLayout gridbagLayout;
	GridBagConstraints gridbagConstraints;
	
	public Annc(String abanNo) throws IOException {
		setBackground(MainFrame.bgc);
		gridbagLayout = new GridBagLayout();
		gridbagConstraints = new GridBagConstraints();
		
		vRegisDate = new JLabel("등록일자");
		xRegisDate = new JTextField(20);
		xRegisDate.setEditable(false);
		
		vAnmlKinds = new JLabel("동물종류");
		xAnmlKinds = new JTextField(20);
		xAnmlKinds.setEditable(false);
		
		vKind = new JLabel("품종");
		xKind = new JTextField(20);
		xKind.setEditable(false);
		
		vSex = new JLabel("성별");
		xSex = new JTextField(20);
		xSex.setEditable(false);
		
		vDscvLoc = new JLabel("발견장소");
		xDscvLoc = new JTextField(20);
		xDscvLoc.setEditable(false);
		
		vFeat = new JLabel("특징");
		xFeat = new JTextField(20);
		xFeat.setEditable(false);
		
		buttonIcon = ImageIO.read(new File("./images/cat1.png"));
		imageButton = new JButton(new ImageIcon(buttonIcon));
		imageButton.setBorderPainted(false);
		imageButton.setContentAreaFilled(false);
		imageButton.setFocusPainted(false);
		
		File input = new File("images/NoImage.png");
	    BufferedImage image = ImageIO.read(input);
	    BufferedImage resized = resize(image,200,200);
	    noImageIcon = new ImageIcon(resized);
		
		JComponent[] vComps2 = {vRegisDate, vAnmlKinds, vKind, vSex, vDscvLoc, vFeat};
		ChangeFont(vComps2, new Font("나눔고딕", Font.PLAIN, 16));
		
		annc = AnncData.getAnnc(abanNo);
		setData();
		AnncView();
	}
	
	private void AnncView() {
		
		gridbagConstraints.insets = new Insets(5,5,5,5);
		
		gridbagConstraints.anchor = GridBagConstraints.WEST;
		gridbagConstraints.ipadx = 7;

		gridbagConstraints.weightx=1.0;
		gridbagConstraints.weighty=1.0;

		setLayout(gridbagLayout);
		
		gridbagAdd(vRegisDate, 6, 0, 1, 1);
		gridbagAdd(xRegisDate, 7, 0, 1, 1);
		
		gridbagAdd(vAnmlKinds, 6, 1, 1, 1);
		gridbagAdd(xAnmlKinds, 7, 1, 1, 1);
		
		gridbagAdd(vKind, 6, 2, 1, 1);
		gridbagAdd(xKind, 7, 2, 1, 1);
		
		gridbagAdd(vSex, 6, 3, 1, 1);
		gridbagAdd(xSex, 7, 3, 1, 1);
		
		gridbagAdd(vDscvLoc, 6, 4, 1, 1);
		gridbagAdd(xDscvLoc, 7, 4, 1, 1);
		
		gridbagAdd(vFeat, 6, 5, 1, 1);
		gridbagAdd(xFeat, 7, 5, 1, 1);
		
		gridbagAdd(imageButton, 0, 0, 6, 6);
		
		
	}
	
	private void ChangeFont(JComponent[] comps, Font font) {
	    	for(JComponent comp: comps) {
	    		comp.setFont(font);
	    	}
	    }	
	private void setData() {
		xRegisDate.setText(annc.get("등록일자").toString());
		xAnmlKinds.setText(annc.get("동물종류").toString());
		xKind.setText(annc.get("품종").toString());
		xSex.setText(annc.get("성별").toString());
		xDscvLoc.setText(annc.get("발견장소").toString());
		xFeat.setText(annc.get("특징").toString());
		try {
			File input = new File(annc.get("경로").toString());
			BufferedImage image = ImageIO.read(input);
			BufferedImage resized = resize(image,200,200);
			ImageIcon icon = new ImageIcon(resized);
			imageButton.setIcon(icon);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			imageButton.setIcon(noImageIcon);
		}
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

	private static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		

	}

}
