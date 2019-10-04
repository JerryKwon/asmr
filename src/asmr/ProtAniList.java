package asmr;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

public class ProtAniList extends JPanel {
	private JLabel vProtAniRegister, vProtAniInfo, vAbanAniNo, vAbanAniType, vRescueNo, vAbanAniName, vAge, vParAniName, vAniType, vKind, vSex, vNeutWhet, vColor, vAniSize, vRegisDate, vDescription, vDscvDate, vCage, vDscvPlace;
	private JTextField xAbanAniNo, xAbanAniType, xRescueNo, xAbanAniName, xAge, xParAniName, xAniType ,xKind, xColor, xRegisDate, xDscvDate, xDscvPlace;
	private JComboBox<String> cbSex, cbNeutWhet, cbAniSize, cbCage;
	private JButton modify, cancel, returning;
	private JTextArea xDescription;

	private JTable eProtAniList;
	private JScrollPane aniListScroll;
	
	private JScrollPane descriptionScroll;
	
	private final String[] col1 = {"���⵿����","��������","ǰ��","����(����)","ũ��","����"};
	
	private DefaultTableModel model1 = new DefaultTableModel(col1,0);
	
	private final String[] sexDiv = {"����","����","�̻�"};
	private final String[] cbNeutWhetDiv = {"Y","N"};
	private final String[] aniSizeDiv = {"��","��","��"};
	private final String[] cageDiv = {"������1(��)","������4(��)","������9(��)"};
	
	private JLabel imageLabel;
	private JButton previous, next, pictureManage;
	
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	private Color red = new Color(217,0,27);
	
	
	ProtAniListMouseListener protAniListMouseListener;
	
	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;
	
	public ProtAniList() throws IOException {
		
		gridBagLayout = new GridBagLayout();		
		gridBagConstraints = new GridBagConstraints();
		
		protAniListMouseListener = new ProtAniListMouseListener();
		
		vProtAniRegister = new JLabel("��ȣ�������");
		vProtAniRegister.setFont(new Font("�������", Font.BOLD, 24));
		
//		register = new JButton("���");
//		register.setBackground(blue);
//		register.setForeground(white);
		
		eProtAniList = new JTable(model1);
		eProtAniList.addMouseListener(protAniListMouseListener);
		aniListScroll = new JScrollPane(eProtAniList);
		aniListScroll.setPreferredSize(new Dimension(1400,200));
		
		vProtAniInfo = new JLabel("��ȣ��������");
		vProtAniInfo.setFont(new Font("�������", Font.BOLD, 20));
		
		vAbanAniNo = new JLabel("���⵿����ȣ");
		xAbanAniNo = new JTextField(12);
		xAbanAniNo.setEnabled(false);
		
		vAbanAniType = new JLabel("���⵿������");
		xAbanAniType = new JTextField(12);
		xAbanAniType.setEnabled(false);
		
		vRescueNo = new JLabel("������ȣ");
		xRescueNo = new JTextField(12);
		xRescueNo.setEnabled(false);
		
		vAbanAniName = new JLabel("���⵿����");
		xAbanAniName = new JTextField(12);
		xAbanAniName.setEnabled(false);
		
		vAge = new JLabel("����(����)");
		xAge = new JTextField(12);
		xAge.setEnabled(false);
		
		vParAniName = new JLabel("������⵿����");
		xParAniName = new JTextField(12);
		xParAniName.setEnabled(false);
		
		vAniType = new JLabel("��������");
		xAniType = new JTextField(12);
		xAniType.setEnabled(false);
		
		vKind = new JLabel("ǰ��");
		xKind = new JTextField(12);
		xKind.setEnabled(false);
		
		vSex = new JLabel("����");
		cbSex = new JComboBox<String>(sexDiv);
		
		vNeutWhet = new JLabel("�߼�ȭ����");
		cbNeutWhet = new JComboBox<String>(cbNeutWhetDiv);
		
		vColor = new JLabel("����");
		xColor = new JTextField(12);
		xColor.setEnabled(false);
		
		vAniSize = new JLabel("����ũ��");
		cbAniSize = new JComboBox<String>(aniSizeDiv);
		
		vRegisDate = new JLabel("�������");
		xRegisDate = new JTextField(12);
		xRegisDate.setEnabled(false);
		
		vDescription = new JLabel("����");
		xDescription = new JTextArea();
		descriptionScroll = new JScrollPane(xDescription);
		descriptionScroll.setPreferredSize(new Dimension(400,150));
		descriptionScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		vDscvDate = new JLabel("�߰��Ͻ�");
		xDscvDate = new JTextField(12);
		xDscvDate.setEnabled(false);
		
		vCage = new JLabel("������");
		cbCage = new JComboBox<String>(cageDiv);
		
		vDscvPlace = new JLabel("�߰����");
		xDscvPlace = new JTextField(12);
		xDscvPlace.setEnabled(false);
		
		modify = new JButton("����");
		modify.setBackground(blue);
		modify.setForeground(white);
		
		cancel = new JButton("���");
		
		returning = new JButton("��ȯ");
		returning.setBackground(red);
		returning.setForeground(white);
		
		pictureManage = new JButton("��������");
		pictureManage.setBackground(blue);
		pictureManage.setForeground(white);
		
		File input = new File("images/dog_400_400.jpg");
		BufferedImage image = ImageIO.read(input);
		BufferedImage resized = resize(image,200,200);
		imageLabel = new JLabel();
		ImageIcon icon = new ImageIcon(resized);
		imageLabel.setIcon(icon);
		
		previous = new JButton("<<");
		next = new JButton(">>");
		
		JComponent[] fontComps1 = {vAbanAniNo, vAbanAniType, vRescueNo, vAbanAniName, vAge, vParAniName, vAniType, vKind, vSex, vNeutWhet, vColor, vAniSize, vRegisDate, vDescription, vDscvDate, vCage, vDscvPlace};
		ChangeFont(fontComps1, new Font("�������", Font.PLAIN, 16));

		JComponent[] fontComps2 = {modify,cancel,returning,pictureManage};
		ChangeFont(fontComps2, new Font("�������", Font.BOLD, 16));

		
		ProtAniListView();
	}
	
	private void ProtAniListView() throws IOException {
		setLayout(gridBagLayout);
		
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.ipadx = 7;
		
		gridBagConstraints.weightx=1.0;
		gridBagConstraints.weighty=1.0;
		
		gridbagAdd(vProtAniRegister, 0, 0, 1, 1);
		
//		JPanel registerPanel = new JPanel();
//		registerPanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,3));
//		registerPanel.add(register);
//		registerPanel.setBorder(BorderFactory.createEmptyBorder(0,135,0,0));
//		gridbagAdd(registerPanel, 5, 0, 1, 1);
//		
		JPanel aniListPanel = new JPanel();
		aniListPanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
		aniListPanel.add(aniListScroll);
		aniListPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,0));
		gridbagAdd(aniListPanel, 0, 1, 20, 1);
		
		vProtAniInfo.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
		gridbagAdd(vProtAniInfo, 0, 2, 1, 1);
		
		gridbagAdd(vAbanAniNo, 0, 3, 1, 1);
		gridbagAdd(xAbanAniNo, 1, 3, 1, 1);
		
		gridbagAdd(vAbanAniType, 2, 3, 1, 1);
		gridbagAdd(xAbanAniType, 3, 3, 1, 1);
		
		gridbagAdd(vRescueNo, 4, 3, 1, 1);
		gridbagAdd(xRescueNo, 5, 3, 1, 1);
		
		gridbagAdd(vAbanAniName, 0, 4, 1, 1);
		gridbagAdd(xAbanAniName, 1, 4, 1, 1);
		
		gridbagAdd(vAge, 2, 4, 1, 1);
		gridbagAdd(xAge, 3, 4, 1, 1);
		
		gridbagAdd(vParAniName, 4, 4, 1, 1);
		gridbagAdd(xParAniName, 5, 4, 1, 1);
		
		gridbagAdd(vAniType, 0, 5, 1, 1);
		gridbagAdd(xAniType, 1, 5, 1, 1);
		
		gridbagAdd(vKind, 2, 5, 1, 1);
		gridbagAdd(xKind, 3, 5, 1, 1);
		
		gridbagAdd(vSex, 0, 6, 1, 1);
		gridbagAdd(cbSex, 1, 6, 1, 1);
		
		gridbagAdd(vNeutWhet, 2, 6, 1, 1);
		gridbagAdd(cbNeutWhet, 3, 6, 1, 1);
		
		gridbagAdd(vColor, 0, 7, 1, 1);
		gridbagAdd(xColor, 1, 7, 1, 1);
		
		gridbagAdd(vAniSize, 2, 7, 1, 1);
		gridbagAdd(cbAniSize, 3, 7, 1, 1);

		JPanel manButtonPanel = new JPanel();
		manButtonPanel.add(pictureManage);
		manButtonPanel.setBorder(BorderFactory.createEmptyBorder(0,100,0,0));
		gridbagAdd(manButtonPanel, 5, 7, 1, 1);
		
		gridbagAdd(imageLabel, 5, 5, 1, 8);
		
		gridbagAdd(vRegisDate, 0, 8, 1, 1);
		gridbagAdd(xRegisDate, 1, 8, 1, 1);
		
		gridbagAdd(vDescription, 0, 9, 1, 1);
		gridbagAdd(descriptionScroll, 1, 9, 4, 1);
		
		gridbagAdd(vDscvDate, 0, 10, 1, 1);
		gridbagAdd(xDscvDate, 1, 10, 1, 1);
		
		gridbagAdd(vCage, 2, 10, 1, 1);
		gridbagAdd(cbCage, 3, 10, 1, 1);
		
		gridbagAdd(vDscvPlace, 0, 11, 1, 1);
		gridbagAdd(xDscvPlace, 1, 11, 1, 1);
		
		Component[] cops1 = {modify, cancel, returning};
		CombinePanel buttonPanel = new CombinePanel(cops1,15,0);
		
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(20,600,0,0));
		gridbagAdd(buttonPanel, 0, 12, 7, 1);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout(FlowLayout.LEFT,20,10));
		bottomPanel.add(previous);
		bottomPanel.add(next);
		bottomPanel.setBorder(BorderFactory.createEmptyBorder(0,25,0,0));
		gridbagAdd(bottomPanel, 5, 11 ,3, 1);
	
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
		public CombinePanel(Component[] cops, int borderWidth, int borderHeight) {
			//Margin�� �ʿ����� ���� ��
			
			setLayout(new FlowLayout(FlowLayout.LEFT,borderWidth,borderHeight));
			
			for (Component c: cops) {
				add(c);
			}
		}
	}
	
	//����ũ�� ��ȯ �Լ��Դϴ�.
	//https://memorynotfound.com/java-resize-image-fixed-width-height-example/
    private static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }
	
    private void ChangeFont(JComponent[] comps, Font font) {
    	for(JComponent comp: comps) {
    		comp.setFont(font);
    	}
    }
    
	class ProtAniListMouseListener extends MouseAdapter{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			super.mouseClicked(e);
			if(e.getButton()==1) {
				
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		new ProtAniList();
	}
}
