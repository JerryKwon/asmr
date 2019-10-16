package src.asmr;

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

public class VisitDetail extends JFrame {
	
	private JLabel vVisitDetail, vAnmlInfo, vAbanName, vAnmlKinds, vKind, vSex, vAge,
	vReqPrsnInfo, vReqPrsnName, vTelNo, vAddr, vVisitDate, vVisitCont;
	
	private JTextField xAbanName, xAnmlKinds, xKind, xSex, xAge, xReqPrsnName, xTelNo,
	xAddr, xVisitDate, xVisitCont;
	
	private JButton confirm, cancel, imageButton;
	
	private BufferedImage buttonIcon;
	
	GridBagLayout gridbagLayout;
	GridBagConstraints gridbagConstraints;
	
	public VisitDetail() throws IOException {
		gridbagLayout = new GridBagLayout();
		gridbagConstraints = new GridBagConstraints();
		
		vVisitDetail = new JLabel("�湮��");
		
		vAnmlInfo = new JLabel("��������");
		
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
		
		vReqPrsnInfo = new JLabel("��û������");

		vReqPrsnName = new JLabel("��û�ڸ�");
		xReqPrsnName = new JTextField(20);
		
		vTelNo = new JLabel("��ȭ��ȣ");
		xTelNo = new JTextField(20);
		
		vAddr = new JLabel("�ּ�");
		xAddr = new JTextField(20);
		
		vVisitDate = new JLabel("�湮����");
		xVisitDate = new JTextField(20);
		buttonIcon = ImageIO.read(new File("./images/cal1.png"));
		imageButton = new JButton(new ImageIcon(buttonIcon));
		imageButton.setBorderPainted(false);
		imageButton.setContentAreaFilled(false);
		imageButton.setFocusPainted(false);
		
		vVisitCont = new JLabel("�湮����");
		xVisitCont = new JTextField(20);
		
	
		confirm = new JButton("Ȯ��");
		cancel = new JButton("���");
		
		VisitDetailView();
				
	}
	
	private void VisitDetailView() {
		setTitle("�湮���");
		
		gridbagConstraints.anchor = GridBagConstraints.WEST;
		gridbagConstraints.ipadx = 7;

		gridbagConstraints.weightx=1.0;
		gridbagConstraints.weighty=1.0;

		setLayout(gridbagLayout);

		gridbagAdd(vVisitDetail, 0, 0, 1, 1);
		
		gridbagAdd(vAnmlInfo, 0, 1, 1, 1);
		
		gridbagAdd(vAbanName, 0, 2, 1, 1);
		gridbagAdd(xAbanName, 2, 2, 1, 1);
		
		gridbagAdd(vAnmlKinds, 0, 3, 1, 1);
		gridbagAdd(xAnmlKinds, 2, 3, 1, 1);
		gridbagAdd(vKind, 4, 3, 1, 1);
		gridbagAdd(xKind, 6, 3, 1, 1);
		
		gridbagAdd(vSex, 0, 4, 1, 1);
		gridbagAdd(xSex, 2, 4, 1, 1);
		gridbagAdd(vAge, 4, 4, 1, 1);
		gridbagAdd(xAge, 6, 4, 1, 1);
		
		gridbagAdd(vReqPrsnInfo, 0, 5, 1, 1);
		
		gridbagAdd(vReqPrsnName, 0, 6, 1, 1);
		gridbagAdd(xReqPrsnName, 2, 6, 1, 1);
		
		gridbagAdd(vTelNo, 0, 7, 1, 1);
		gridbagAdd(xTelNo, 2, 7, 1, 1);
		
		gridbagAdd(vAddr, 0, 8, 1, 1);
		gridbagAdd(xAddr, 2, 8, 1, 1);
		
		gridbagAdd(vVisitDate, 0, 9, 1, 1);
		gridbagAdd(xVisitDate, 2, 9, 1, 1);
		gridbagAdd(imageButton, 4, 9, 1, 1);
		
		gridbagAdd(vVisitCont, 0, 10, 1, 1);
		gridbagAdd(xVisitCont, 2, 10, 1, 1);
		
		gridbagAdd(confirm, 2, 11, 1, 1);
		gridbagAdd(cancel, 3, 11, 1, 1);
		
	
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
		new VisitDetail();

	}

}
