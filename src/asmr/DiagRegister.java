package asmr;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class DiagRegister extends JFrame{
	private JLabel vDiagRegister, vDiagDate, vDiagType, vIndiResult, vIndiVtrnName, vOudiResult, vHospName, vDisease, vInfecWhet, vCureType, vHsptzDate, vDschDate,vDeathType, vDeathReason, vDiagContent; 
	private JTextField xDiagDate, xIndiVtrnName, xHospName, xDisease, xInfecWhet, xCureType, xHsptzDate, xDschDate, xDeathType, xDeathReason;
	private JComboBox<String> cbDiagType, cbIndiResult, cbOudiResult, cbCureType, cbDeathType;
	private JButton imageButton1, imageButton2, register, cancel;
	private JTextArea xDiagContent;
	private BufferedImage buttonIcon;
	
	private JScrollPane diagContentScroll;
	
	private String[] diagTypeDiv = {"����","����"};
	private String[] indiResultDiv = {"����","����","����"};
	private String[] oudiResultDiv = {"ġ��","���"};
	private String[] cureTypeDiv = {"���","�Կ�"};
	private String[] deathTypeDiv = {"�ڿ���","�ȶ���"};
	
	
	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;
	
	public DiagRegister() throws IOException {
		gridBagLayout = new GridBagLayout();		
		gridBagConstraints = new GridBagConstraints();
	
		vDiagRegister = new JLabel("������");
		
		vDiagDate = new JLabel("��������");
		xDiagDate = new JTextField(10);
		xDiagDate.setEnabled(false);
		buttonIcon = ImageIO.read(new File("images/cal1.png"));
		imageButton1 = new JButton(new ImageIcon(buttonIcon));
		imageButton1.setBorderPainted(false);
		imageButton1.setContentAreaFilled(false);
		imageButton1.setFocusPainted(false);
		
		
		vDiagType = new JLabel("���ᱸ��");
		cbDiagType = new JComboBox<String>(diagTypeDiv);
		
		vIndiResult = new JLabel("������");
		cbIndiResult = new JComboBox<String>(indiResultDiv);
		
		vIndiVtrnName = new JLabel("�������ǻ��");
		xIndiVtrnName =  new JTextField(10);
		
		vOudiResult = new JLabel("�������");
		cbOudiResult = new JComboBox<String>(oudiResultDiv);
		
		vHospName = new JLabel("������");
		xHospName = new JTextField(10);
		
		vDisease = new JLabel("����");
		xDisease = new JTextField(10);
		
		vInfecWhet = new JLabel("��������");
		xInfecWhet = new JTextField(10);
		
		vCureType = new JLabel("ġ�ᱸ��");
		cbCureType = new JComboBox<String>(cureTypeDiv);
//		xCureType = new JTextField(10);
		
		vHsptzDate = new JLabel("�Կ�����");
		xHsptzDate = new JTextField(10);
		
		vDschDate = new JLabel("�������");
		xDschDate = new JTextField(10);
		imageButton2 = new JButton(new ImageIcon(buttonIcon));
		imageButton2.setBorderPainted(false);
		imageButton2.setContentAreaFilled(false);
		imageButton2.setFocusPainted(false);
		
		vDeathType = new JLabel("�������");
		cbDeathType = new JComboBox<String>(deathTypeDiv);
//		xDeathType = new JTextField(10);
		
		vDeathReason = new JLabel("�������");
		xDeathReason = new JTextField(10);
		
		vDiagContent = new JLabel("���᳻��");
		xDiagContent = new JTextArea();
		diagContentScroll = new JScrollPane(xDiagContent);
		diagContentScroll.setPreferredSize(new Dimension(400,150));
		diagContentScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		register = new JButton("���");
		cancel = new JButton("���");
		
		DiagRegisterView();
	}
	
	private void DiagRegisterView() {
		setLayout(gridBagLayout);
		
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.ipadx = 7;
		
		gridBagConstraints.weightx=1.0;
		gridBagConstraints.weighty=1.0;
		
		gridbagAdd(vDiagRegister, 0, 0, 1, 1);
		
		gridbagAdd(vDiagDate, 0, 1, 1, 1);
		Component[] cops1 = {xDiagDate, imageButton1};
		CombinePanel diagDatePanel = new CombinePanel(cops1, false);
		gridbagAdd(diagDatePanel, 1, 1, 1, 1);
		
		gridbagAdd(vDiagType, 2, 1, 1, 1);
		gridbagAdd(cbDiagType, 3, 1, 1, 1);
		
		gridbagAdd(vIndiResult, 0, 2, 1, 1);
		gridbagAdd(cbIndiResult, 1, 2, 1, 1);
		
		gridbagAdd(vIndiVtrnName, 2, 2, 1, 1);
		gridbagAdd(xIndiVtrnName, 3, 2, 1, 1);
		
		gridbagAdd(vOudiResult, 0, 3, 1, 1);
		gridbagAdd(cbOudiResult, 1, 3, 1, 1);
		
		gridbagAdd(vHospName, 2, 3, 1, 1);
		gridbagAdd(xHospName, 3, 3, 1, 1);
		
		gridbagAdd(vDisease, 0, 4, 1, 1);
		gridbagAdd(xDisease, 1, 4, 1, 1);
		
		gridbagAdd(vInfecWhet, 2, 4, 1, 1);
		gridbagAdd(xInfecWhet, 3, 4, 1, 1);
		
		gridbagAdd(vCureType, 0, 5, 1, 1);
		gridbagAdd(cbCureType, 1, 5, 1, 1);
		
		gridbagAdd(vHsptzDate, 2, 5, 1, 1);
		gridbagAdd(xHsptzDate, 3, 5, 1, 1);
		
		gridbagAdd(vDschDate, 2, 6, 1, 1);
		Component[] cops2 = {xDschDate, imageButton2};
		CombinePanel dschDatePanel = new CombinePanel(cops2, false);
		gridbagAdd(dschDatePanel, 3, 6, 1, 1);
		
		gridbagAdd(vDeathType, 0, 7, 1, 1);
		gridbagAdd(cbDeathType, 1, 7, 1, 1);
		
		gridbagAdd(vDeathReason, 2, 7, 1, 1);
		gridbagAdd(xDeathReason, 3, 7, 1, 1);
		
		gridbagAdd(vDiagContent, 0, 8, 1, 1);
		gridbagAdd(diagContentScroll, 1, 8, 3, 1);
		
		Component[] cops3 = {register, cancel};
		CombinePanel buttonPanel = new CombinePanel(cops3,true);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(10,175,0,0));
		gridbagAdd(buttonPanel, 0, 9, 4, 1);
		
		pack();
		setResizable(false);
		setVisible(true);
	}
	
	private void gridbagAdd(Component c, int x, int y, int w , int h) {
		
		gridBagConstraints.gridx = x;
		gridBagConstraints.gridy = y;
		gridBagConstraints.gridwidth = w;
		gridBagConstraints.gridheight = h;
		
		gridBagLayout.setConstraints(c, gridBagConstraints);
		
		add(c);
	}
	
	class CombinePanel extends JPanel {
		//������Ʈ 1, ������Ʈ 2, �г� ������ ��,�� margin ������ ���ֱ� ���� Flag
		public CombinePanel(Component[] cops, boolean isBorder) {
			//Margin�� �ʿ����� ���� ��
			if(!isBorder) {
				setLayout(new FlowLayout(FlowLayout.LEFT,0,0));	
			}
			else {
				setLayout(new FlowLayout(FlowLayout.LEFT,15,0));	
			}
			for (Component c: cops) {
				add(c);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		new DiagRegister();
	}
}
