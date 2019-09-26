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

public class AnncDetailPopup extends JFrame {
	
	private JLabel vProtCntrName, vAnncRegis, vAbanName, vAnmlKinds, vKind, vSex, vAge,vColor, vNeutWhet, vAnmlSize, vRscuDate, vRscuLoc, vExpln;   
	
	private JTextField xProtCntrName, xAbanName, xAnmlKinds, xKind, xSex, xAge, xColor, xNeutWhet, xAnmlSize, xRscuDate, xRscuLoc, xExpln;
	
	private JButton confirm, cancel;
	
	GridBagLayout gridbagLayout;
	GridBagConstraints gridbagConstraints;
	
	private BufferedImage buttonIcon;
	
	private JButton imageButton;
	
	public AnncDetailPopup() throws IOException {
		gridbagLayout = new GridBagLayout();
		gridbagConstraints = new GridBagConstraints();
		
		vAnncRegis = new JLabel("������");
		
		vProtCntrName = new JLabel("��ȣ���͸�");
		xProtCntrName = new JTextField(20);

		vAbanName = new JLabel("���⵿����");
		xAbanName = new JTextField(20);
		
		vAnmlKinds = new JLabel("��������");
		xAnmlKinds = new JTextField(20);
		
		vKind = new JLabel("ǰ��");
		xKind = new JTextField(20);
		
		vSex = new JLabel("����");
		xSex = new JTextField(20);
		
		vAge = new JLabel("����");
		xAge = new JTextField(20);
		
		vColor= new JLabel("����");
		xColor = new JTextField(20);
		
		vNeutWhet = new JLabel("�߼�ȭ����");
		xNeutWhet = new JTextField(20);
		
		vAnmlSize = new JLabel("����ũ��"); 
		xAnmlSize = new JTextField(20);
		
		vRscuDate = new JLabel("��������");
		xRscuDate = new JTextField(20);
		vRscuLoc = new JLabel("�������");
		xRscuLoc = new JTextField(20);
		
		vExpln= new JLabel("����");   
		xExpln = new JTextField(20);
		
		confirm = new JButton("�Ծ��û");
		cancel = new JButton("���");
		
		buttonIcon = ImageIO.read(new File("./images/cat1.png"));
		imageButton = new JButton(new ImageIcon(buttonIcon));
		imageButton.setBorderPainted(false);
		imageButton.setContentAreaFilled(false);
		imageButton.setFocusPainted(false);
		
		
		AnncDetailPopupView();
		
	}
	
	private void AnncDetailPopupView() {
		
		setTitle("������");
		
		gridbagConstraints.anchor = GridBagConstraints.WEST;
		gridbagConstraints.ipadx = 7;

		gridbagConstraints.weightx=1.0;
		gridbagConstraints.weighty=1.0;

		setLayout(gridbagLayout);
		
		gridbagAdd(vAnncRegis, 0, 0, 1, 1);
		
		gridbagAdd(vProtCntrName, 0, 1, 1, 1);
		gridbagAdd(xProtCntrName, 2, 1, 1, 1);
		gridbagAdd(vAbanName, 4, 1, 1, 1);
		gridbagAdd(xAbanName, 6, 1, 1, 1);
		
		gridbagAdd(vAnmlKinds, 0, 2, 1, 1);
		gridbagAdd(xAnmlKinds, 2, 2, 1, 1);
		gridbagAdd(vKind, 4, 2, 1, 1);
		gridbagAdd(xKind, 6, 2, 1, 1);
		
		gridbagAdd(vSex, 0, 3, 1, 1);
		gridbagAdd(xSex, 2, 3, 1, 1);
		gridbagAdd(vAge, 4, 3, 1, 1);
		gridbagAdd(xAge, 6, 3, 1, 1);
		
		gridbagAdd(vColor, 0, 4, 1, 1);
		gridbagAdd(xColor, 2, 4, 1, 1);
		gridbagAdd(vNeutWhet, 4, 4, 1, 1);
		gridbagAdd(xNeutWhet, 6, 4, 1, 1);
		
		gridbagAdd(vAnmlSize, 0, 5, 1, 1);
		gridbagAdd(xAnmlSize, 2, 5, 1, 1);
		
		gridbagAdd(vRscuDate, 0, 6, 1, 1);
		gridbagAdd(xRscuDate, 2, 6, 1, 1);
		gridbagAdd(vRscuLoc, 4, 6, 1, 1);
		gridbagAdd(xRscuLoc, 6, 6, 1, 1);
		
		gridbagAdd(vExpln, 0, 7, 1, 1);
		gridbagAdd(xExpln, 2, 7, 1, 1);
		
		gridbagAdd(imageButton, 3,8,1,3);
		
		gridbagAdd(confirm,3, 12, 1, 1);
		gridbagAdd(cancel, 4, 12, 1, 1);
		
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
		
		new AnncDetailPopup();

	}

}