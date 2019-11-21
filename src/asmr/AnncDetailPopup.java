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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AnncDetailPopup extends JFrame {
	
	private JLabel vProtCntrName, vAnncRegis, vAbanName, vAnmlKinds, vKind, vSex, vAge,vColor, vNeutWhet, vAnmlSize, vRscuDate, vRscuLoc, vExpln;   
	
	private JTextField xProtCntrName, xAbanName, xAnmlKinds, xKind, xSex, xAge, xColor, xNeutWhet, xAnmlSize, xRscuDate, xRscuLoc, xExpln;
	
	private JButton confirm, cancel;
	
	GridBagLayout gridbagLayout;
	GridBagConstraints gridbagConstraints;
	
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	private Color black = new Color(0,0,0);
	
	private BufferedImage buttonIcon;
	
	AnncDetailPopupButtonListener anncDetailPopupButtonListener;
	
	private JButton previous, next, imageButton;
	
	public AnncDetailPopup(String abanNo) throws IOException {
		gridbagLayout = new GridBagLayout();
		gridbagConstraints = new GridBagConstraints();
		
		anncDetailPopupButtonListener = new AnncDetailPopupButtonListener();
		
		vAnncRegis = new JLabel("������");
		vAnncRegis.setFont(new Font("�������", Font.BOLD, 24));
		
		vProtCntrName = new JLabel("��ȣ���͸�");
		xProtCntrName = new JTextField(20);
		xProtCntrName.setEditable(false);

		vAbanName = new JLabel("���⵿����");
		xAbanName = new JTextField(20);
		xAbanName.setEditable(false);
		
		vAnmlKinds = new JLabel("��������");
		xAnmlKinds = new JTextField(20);
		xAnmlKinds.setEditable(false);
		
		vKind = new JLabel("ǰ��");
		xKind = new JTextField(20);
		xKind.setEditable(false);
		
		vSex = new JLabel("����");
		xSex = new JTextField(20);
		xSex.setEditable(false);
		
		vAge = new JLabel("����");
		xAge = new JTextField(20);
		xAge.setEditable(false);
		
		vColor= new JLabel("����");
		xColor = new JTextField(20);
		xColor.setEditable(false);
		
		vNeutWhet = new JLabel("�߼�ȭ����");
		xNeutWhet = new JTextField(20);
		xNeutWhet.setEditable(false);
		
		vAnmlSize = new JLabel("����ũ��"); 
		xAnmlSize = new JTextField(20);
		xAnmlSize.setEditable(false);
		
		vRscuDate = new JLabel("��������");
		xRscuDate = new JTextField(20);
		xRscuDate.setEditable(false);
		
		vRscuLoc = new JLabel("�������");
		xRscuLoc = new JTextField(20);
		xRscuLoc.setEditable(false);
		
		vExpln= new JLabel("����");   
		xExpln = new JTextField(20);
		xExpln.setEditable(false);
		
		confirm = new JButton("�Ծ��û");
		confirm.setBackground(blue);
		confirm.setForeground(white);
		confirm.setFont(new Font("�������", Font.BOLD, 12));
		confirm.addActionListener(anncDetailPopupButtonListener);
		
		cancel = new JButton("���");
		cancel.setBackground(white);
		cancel.setForeground(black);
		cancel.setFont(new Font("�������", Font.BOLD, 12));
		cancel.addActionListener(anncDetailPopupButtonListener);
		
		previous = new JButton("<<");
		previous.addActionListener(anncDetailPopupButtonListener);
		previous.setFont(new Font("�������", Font.BOLD, 12));
		
		next = new JButton(">>");
		next.addActionListener(anncDetailPopupButtonListener);
		next.setFont(new Font("�������", Font.BOLD, 12));
		
		buttonIcon = ImageIO.read(new File("./images/cat1.png"));
		imageButton = new JButton(new ImageIcon(buttonIcon));
		imageButton.setBorderPainted(false);
		imageButton.setContentAreaFilled(false);
		imageButton.setFocusPainted(false);
		
		JComponent[] vComps = {vProtCntrName, vAbanName, vAnmlKinds, vKind, vSex, vAge, vColor, vNeutWhet,
				vAnmlSize, vRscuDate,vRscuLoc, vExpln};
		ChangeFont(vComps, new Font("�������", Font.PLAIN, 16));
		
		
		AnncDetailPopupView();
		
	}
	
	private void AnncDetailPopupView() {
		this.getContentPane().setBackground(MainFrame.bgc);
		setTitle("������");
		
		gridbagConstraints.insets = new Insets(5,5,5,5);
		
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
		
		gridbagAdd(previous,3, 11, 1, 1);
		gridbagAdd(next, 4, 11, 1, 1);
		
		gridbagAdd(confirm,3, 12, 1, 1);
		gridbagAdd(cancel, 4, 12, 1, 1);
		
		pack();
		setLocationRelativeTo(null);
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
	
	class AnncDetailPopupButtonListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource().equals(previous)) {
				
			}
			else if(e.getSource().equals(next)) {
				
			}
			else if(e.getSource().equals(cancel)) {
				dispose();
			}
		}
		
	}
	
    private void ChangeFont(JComponent[] comps, Font font) {
    	for(JComponent comp: comps) {
    		comp.setFont(font);
    	}
    }

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		new AnncDetailPopup("2019112201");

	}

}
