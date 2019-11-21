package asmr;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AdopReq extends JPanel {
	
	AdopReqButtonListener adopReqButtonListener;

	private JLabel vAdopReq, vAnmlInfo, vAbanName, vAnmlKinds, vKind, vSex, vAge,
	vReqPrsnInfo, vReqPrsnName, vTelNo, vAddr, vWarn;
	
	private JTextField xAbanName, xAnmlKinds, xKind, xSex, xAge,
	xReqPrsnName, xTelNo, xAddr;
	
	private JButton req, cancel, imageButton;
	
	private BufferedImage buttonIcon;
	
	GridBagLayout gridbagLayout;
	GridBagConstraints gridbagConstraints;
	
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	private Color black = new Color(0,0,0);
	
	public AdopReq() throws IOException {
		
		adopReqButtonListener = new AdopReqButtonListener();
		
		gridbagLayout = new GridBagLayout();
		gridbagConstraints = new GridBagConstraints();
		
		vAdopReq = new JLabel("�Ծ��û");
		vAdopReq.setFont(new Font("�������", Font.BOLD, 24));
		
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
		vWarn = new JLabel("��û������(����ó, �ּ�)�� �´��� �ٽ� �� �� Ȯ�����ּ���.");
		
		vReqPrsnName = new JLabel("��û�ڸ�");
		xReqPrsnName = new JTextField(20);
		
		vTelNo = new JLabel("��ȭ��ȣ");
		xTelNo = new JTextField(20);
		
		vAddr = new JLabel("�ּ�");
		xAddr = new JTextField(20);
		
		req = new JButton("��û");
		req.setBackground(blue);
		req.setForeground(white);
		req.addActionListener(adopReqButtonListener);
		req.setFont(new Font("�������", Font.BOLD, 12));
		
		cancel = new JButton("���");
		cancel.setBackground(white);
		cancel.setForeground(black);
		cancel.addActionListener(adopReqButtonListener);
		cancel.setFont(new Font("�������", Font.BOLD, 12));
		
		buttonIcon = ImageIO.read(new File("./images/process.png"));
		imageButton = new JButton(new ImageIcon(buttonIcon));
		imageButton.setBorderPainted(false);
		imageButton.setContentAreaFilled(false);
		imageButton.setFocusPainted(false);
		
		JComponent[] vComps1 = {vAnmlInfo, vReqPrsnInfo, vWarn};
		ChangeFont(vComps1, new Font("�������", Font.BOLD, 16));
		
		JComponent[] vComps2 = {vAbanName, vAnmlKinds, vKind, vSex, vAge, vReqPrsnName, vTelNo, vAddr};
		ChangeFont(vComps2, new Font("�������", Font.PLAIN, 16));
		
		
		AdopReqView();
	}
	
	private void AdopReqView() {
		
		//setTitle("�Ծ��û");
		
		gridbagConstraints.insets = new Insets(5,5,5,5);
		
		gridbagConstraints.anchor = GridBagConstraints.WEST;
		gridbagConstraints.ipadx = 7;

		gridbagConstraints.weightx=1.0;
		gridbagConstraints.weighty=1.0;

		setLayout(gridbagLayout);
		
		gridbagAdd(vAdopReq, 0, 0, 1, 1);
		
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
		gridbagAdd(vWarn, 2, 5, 1, 1);
		
		gridbagAdd(vReqPrsnName, 0, 6, 1, 1);
		gridbagAdd(xReqPrsnName, 2, 6, 1, 1);
		
		gridbagAdd(vTelNo, 0, 7, 1, 1);
		gridbagAdd(xTelNo, 2, 7, 1, 1);
		
		gridbagAdd(vAddr, 0, 8, 1, 1);
		gridbagAdd(xAddr, 2, 8, 1, 1);
		
		gridbagAdd(imageButton, 1,9,7,3);
		
		gridbagAdd(req,3, 15, 1, 1);
		gridbagAdd(cancel, 4, 15, 1, 1);
		
//		pack();
//		setResizable(false);
//		setVisible(true);
		
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
	
    private void ChangeFont(JComponent[] comps, Font font) {
    	for(JComponent comp: comps) {
    		comp.setFont(font);
    	}
    }
    
    class AdopReqButtonListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource().equals(req)) {	
				
			}
			else if(e.getSource().equals(cancel)) {
				
			}
		}
		
	}
	

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		new AdopReq();

	}

}
