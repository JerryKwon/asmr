package asmr;


import java.awt.Button;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MyFrame extends JFrame {
	private JLabel vRprtName,  vTelNo, vRprtTp,vAnmlKinds,
	vAnmlSize, vExpln,vDscvDttm, vDscvLoc, vPic;
	
	private JTextField xRprtName, xTelNo, xExpln,xDscvDttm,
	xDscvLoc, xPic;
	
	private String[] rprtDiv = {"발견", "인계"};
	
	private String[] anmlDiv = {"개", "고양이"};
	
	private String[] anmlSizeDiv = {"대", "중", "소"};

	private JButton regist, cancel, Imagebutton;
	
	private BufferedImage buttonIcon;
	
	private Button btnSearch;
	
	private JComboBox<String> cbRprtTp, cbAnmlKinds, cbAnmlSize;
	
	GridBagLayout gridbaglayout;
	GridBagConstraints gridbagconstraints;

	public MyFrame() throws IOException{
		
		gridbaglayout = new GridBagLayout();
		gridbagconstraints = new GridBagConstraints();


		vRprtName = new JLabel("신고자명");
		xRprtName = new JTextField(20);

		vTelNo = new JLabel("전화번호");
		xTelNo = new JTextField(20);

		vRprtTp = new JLabel("신고구분");
		cbRprtTp = new JComboBox<String>(rprtDiv);

		
		vAnmlKinds = new JLabel("동물종류");
		cbAnmlKinds = new JComboBox<String>(anmlDiv);

				
		vAnmlSize = new JLabel("동물크기");
		cbAnmlSize = new JComboBox<String>(anmlSizeDiv);


		vExpln = new JLabel("설명");
		xExpln = new JTextField(20);

		vDscvDttm = new JLabel("발견일시");
		xDscvDttm = new JTextField(20);

		vDscvLoc = new JLabel("발견장소");
		xDscvLoc = new JTextField(20);
		
		vPic = new JLabel("사진");
		xPic = new JTextField(20);
		
		btnSearch = new Button("찾아보기");
		
		regist = new JButton("등록");
		cancel = new JButton("취소");
		
		buttonIcon = ImageIO.read(new File("./images/cal1.png"));
		Imagebutton = new JButton(new ImageIcon(buttonIcon));
		Imagebutton.setBorderPainted(false);
		Imagebutton.setContentAreaFilled(false);
		Imagebutton.setFocusPainted(false);

		RprtRegisterView();
	}
	
	private void RprtRegisterView() {

		setTitle("신고 등록");

		gridbagconstraints.anchor = GridBagConstraints.WEST;
		gridbagconstraints.ipadx = 7;

		gridbagconstraints.weightx=1.0;
		gridbagconstraints.weighty=1.0;

		setLayout(gridbaglayout);

		gridbagAdd(vRprtName, 0, 0, 1, 1);
		gridbagAdd(xRprtName, 2, 0, 1, 1);
		gridbagAdd(vTelNo, 10, 0, 1, 1);
		gridbagAdd(xTelNo, 12, 0, 1, 1);
		gridbagAdd(vRprtTp, 0, 1, 1, 1);
		gridbagAdd(cbRprtTp, 2, 1, 1, 1);
		gridbagAdd(vAnmlKinds, 0, 2, 1, 1);
		gridbagAdd(cbAnmlKinds, 2, 2, 1, 1);
		gridbagAdd(vAnmlSize, 10, 2, 1, 1);
		gridbagAdd(cbAnmlSize, 12, 2, 1, 1);
		gridbagAdd(vExpln, 0, 3, 1, 1);
		gridbagAdd(xExpln, 2, 3, 1, 1);
		gridbagAdd(vDscvDttm, 0, 6, 1, 1);
		gridbagAdd(xDscvDttm, 2, 6, 1, 1);
		gridbagAdd(Imagebutton, 4,6,1,1);
		
		gridbagAdd(vDscvLoc, 0, 7, 1, 1);
		gridbagAdd(xDscvLoc, 2, 7, 1, 1);
		gridbagAdd(vPic, 0, 8, 1, 1);
		gridbagAdd(xPic, 2, 8, 1, 1);
		gridbagAdd(btnSearch, 4,8,1,1);
		
		gridbagconstraints.anchor = GridBagConstraints.CENTER;

		gridbagAdd(regist, 4, 9, 1, 1);
		gridbagAdd(cancel, 6, 9, 1, 1);

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
				
	public static void main(String[] args) throws IOException {			
		new MyFrame();	
	}			
	
	

}


