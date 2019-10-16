package src.asmr;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class ProtAniRegist extends JFrame{
	private JLabel vProtAniRegist, vAbanAniType, vRscuNo, vParAbanAniName, vAbanAniName, vAge, vAniType, vKind, vSex, vNeutWhet, vColor, vAniSize, vFeature, vPicture, vCage;
	private JTextField xRscuNo, xParAbanAniName, xAbanAniName, xAge, xKind, xColor;
	private JComboBox<String> cbAbanAniType, cbAniType, cbSex, cbNeutWhet, cbAniSize, cbCage;
	private JTextArea xFeature;
	private JButton searchRscu, searchPar, pictureManage, register, cancel;
	
	private JScrollPane featureScroll;
	
	private final String[] abanAniTypeDiv = {"����","ź��"};
	private final String[] aniTypeDiv = {"��","�����","��Ÿ"};
	private final String[] sexDiv = {"����","����","�̻�"};
	private final String[] neutWhetDiv = {"Y","N"};
	private final String[] aniSizeDiv = {"��","��","��"};
	private final String[] cageDiv = {"������1(��)","������4(��)","������9(��)"};
	
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	
	ProtAniRegistItemListener protAniRegistItemListener;
	ProtAniRegistButtonListener protAniRegistButtonListener;
	
	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;
	
	public ProtAniRegist() {
		
		gridBagLayout = new GridBagLayout();		
		gridBagConstraints = new GridBagConstraints();
		
		protAniRegistItemListener = new ProtAniRegistItemListener();
		protAniRegistButtonListener = new ProtAniRegistButtonListener();
		
//		vProtAniRegist = new JLabel("��ȣ�������");
//		vProtAniRegist.setFont(new Font("�������", Font.BOLD, 20));
		
		
		vAbanAniType = new JLabel("���⵿������");
		cbAbanAniType = new JComboBox<String>(abanAniTypeDiv);
		cbAbanAniType.addItemListener(protAniRegistItemListener);
		
		vRscuNo = new JLabel("������ȣ");
		xRscuNo = new JTextField(10);
		xRscuNo.setEnabled(false);
		searchRscu = new JButton("�˻�");
		searchRscu.setBackground(blue);
		searchRscu.setForeground(white);
		searchRscu.addActionListener(protAniRegistButtonListener);
		
		vParAbanAniName = new JLabel("������⵿����");
		xParAbanAniName = new JTextField(10);
		xParAbanAniName.setEnabled(false);
		searchPar = new JButton("�˻�");
		searchPar.setBackground(blue);
		searchPar.setForeground(white);
		searchPar.addActionListener(protAniRegistButtonListener);
		
		vAbanAniName = new JLabel("���⵿����");
		xAbanAniName = new JTextField(10);
		
		vAge = new JLabel("����(����)");
		xAge = new JTextField(10);
		
		vAniType = new JLabel("��������");
		cbAniType = new JComboBox<String>(aniTypeDiv);
		
		vKind = new JLabel("ǰ��");
		xKind = new JTextField(10);
		
		vSex = new JLabel("����");
		cbSex = new JComboBox<String>(sexDiv);
		
		vNeutWhet = new JLabel("�߼�ȭ����");
		cbNeutWhet = new JComboBox<String>(neutWhetDiv);
		
		vColor = new JLabel("����");
		xColor = new JTextField(10);
		
		vAniSize = new JLabel("����ũ��");
		cbAniSize = new JComboBox<String>(aniSizeDiv);
		
		vFeature = new JLabel("Ư¡");
		xFeature = new JTextArea();
		featureScroll = new JScrollPane(xFeature);
		featureScroll.setPreferredSize(new Dimension(300,100));
		featureScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		vPicture = new JLabel("����");
		pictureManage = new JButton("��������");
		pictureManage.setBackground(blue);
		pictureManage.setForeground(white);
		pictureManage.addActionListener(protAniRegistButtonListener);
		
		vCage = new JLabel("������");
		cbCage = new JComboBox<String>(cageDiv);
		
		register = new JButton("���");
		register.setBackground(blue);
		register.setForeground(white);
		register.addActionListener(protAniRegistButtonListener);
		
		cancel = new JButton("���");
		cancel.addActionListener(protAniRegistButtonListener);
		
		JComponent[] vComps = {vAbanAniType, vRscuNo, vParAbanAniName, vAbanAniName, vAge, vAniType, vKind, vSex, vNeutWhet, vColor, vAniSize, vFeature, vPicture, vCage};
		ChangeFont(vComps, new Font("�������", Font.PLAIN, 16));
		
		JComponent[] bComps = {searchRscu, searchPar, pictureManage, register, cancel};
		ChangeFont(bComps, new Font("�������", Font.BOLD, 12));
		
		activateRscu();
		
		ProtAniRegistView();
	}
	
	private void ProtAniRegistView() {
		setLayout(gridBagLayout);
		setTitle("��ȣ�������");
		
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.ipadx = 7;
		
		gridBagConstraints.weightx=1.0;
		gridBagConstraints.weighty=1.0;
	
//		gridbagAdd(vProtAniRegist, 0, 0, 1, 1);
		
		gridbagAdd(vAbanAniType, 0, 1, 1, 1);
		gridbagAdd(cbAbanAniType, 1, 1, 1, 1);
		
		gridbagAdd(vRscuNo, 0, 2, 1, 1);
		gridbagAdd(xRscuNo, 1, 2, 1, 1);
		gridbagAdd(searchRscu, 2, 2, 1, 1);
		
		gridbagAdd(vParAbanAniName, 3, 2, 1, 1);
		gridbagAdd(xParAbanAniName, 4, 2, 1, 1);
		gridbagAdd(searchPar, 5, 2, 1, 1);
		
		gridbagAdd(vAbanAniName, 0, 3, 1, 1);
		gridbagAdd(xAbanAniName, 1, 3, 1, 1);
		
		gridbagAdd(vAge, 2, 3, 1, 1);
		gridbagAdd(xAge, 3, 3, 1, 1);
		
		gridbagAdd(vAniType, 0, 4, 1, 1);
		gridbagAdd(cbAniType, 1, 4, 1, 1);
		
		gridbagAdd(vKind, 2, 4, 1, 1);
		gridbagAdd(xKind, 3, 4, 1, 1);
		
		gridbagAdd(vSex, 0, 5, 1, 1);
		gridbagAdd(cbSex, 1, 5, 1, 1);
		
		gridbagAdd(vNeutWhet, 2, 5, 1, 1);
		gridbagAdd(cbNeutWhet, 3, 5, 1, 1);
		
		gridbagAdd(vColor, 0, 6, 1, 1);
		gridbagAdd(xColor, 1, 6, 1, 1);
		
		gridbagAdd(vAniSize, 2, 6, 1, 1);
		gridbagAdd(cbAniSize, 3, 6, 1, 1);
		
		gridbagAdd(vFeature, 0, 7, 1, 1);
		gridbagAdd(featureScroll, 1, 7, 3, 1);
		
		gridbagAdd(vPicture, 0, 8, 1, 1);
		gridbagAdd(pictureManage, 1, 8, 1, 1);
		
		gridbagAdd(vCage, 0, 9, 1, 1);
		gridbagAdd(cbCage, 1, 9, 1, 1);
	
		Component[] cops = {register, cancel};
		CombinePanel buttonPanel = new CombinePanel(cops, true);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 225, 0, 0));
		gridbagAdd(buttonPanel, 0, 10, 6, 1);
		
		pack();
		setLocationRelativeTo(null);
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
	
	//�ΰ��� ������Ʈ�� �ϳ��� �гη� ���� JPanel
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
	
	class ProtAniRegistItemListener implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			String target = (String)e.getItem();
			if(target=="����") {
				activateRscu();
			}
			else if(target=="ź��") {
				activateBirth();
			}
		}
		
	}
	
	private void activateRscu() {
		xRscuNo.setEnabled(true);
		searchRscu.setEnabled(true);
		
		xParAbanAniName.setEnabled(false);
		searchPar.setEnabled(false);
	}
	
	private void activateBirth() {
		xParAbanAniName.setEnabled(true);
		searchPar.setEnabled(true);
		
		xRscuNo.setEnabled(false);
		searchRscu.setEnabled(false);
		
	}
	
    private void ChangeFont(JComponent[] comps, Font font) {
    	for(JComponent comp: comps) {
    		comp.setFont(font);
    	}
    }
	
    class ProtAniRegistButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource().equals(searchRscu)) {
				new RscuSearch();
			}
			else if(e.getSource().equals(searchPar)) {
				new ProtAnmlSearchPopup();
			}
			else if(e.getSource().equals(pictureManage)) {
				try {
					new PictureManage();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if(e.getSource().equals(register)) {
				
			}
			else if(e.getSource().equals(cancel)) {
				dispose();
			}
		}
    	
    }
    
	public static void main(String[] args) {
		new ProtAniRegist();
	}
}
