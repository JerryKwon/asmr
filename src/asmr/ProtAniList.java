package asmr;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.TextField;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

public class ProtAniList extends JFrame {
	private JLabel vProtAniRegister, vProtAniInfo, vAbanAniNo, vAbanAniType, vRescueNo, vAbanAniName, vAge, vParAniName, vAniType, vKind, vSex, vNeutWhet, vColor, vAniSize, vRegisDate, vDescription, vDscvDate, vCage, vDscvPlace;
	private TextField xAbanAniNo, xAbanAniType, xRescueNo, xAbanAniName, xAge, xParAniName, xAniType ,xKind, xColor, xRegisDate, xDscvDate, xDscvPlace;
	private JComboBox<String> cbSex, cbNeutWhet, cbAniSize, cbCage;
	private JButton register, modify, cancel, returning;
	private TextArea xDescription;

	private JTable eProtAniList;
	private JScrollPane aniListScroll;
	
	private JScrollPane descriptionScroll;
	
	private final String[] col1 = {"유기동물명","동물종류","품종","나이(개월)","크기","설명"};
	
	private DefaultTableModel model1 = new DefaultTableModel(col1,0);
	
	private final String[] sexDiv = {"수컷","암컷","미상"};
	private final String[] cbNeutWhetDiv = {"Y","N"};
	private final String[] aniSizeDiv = {"대","중","소"};
	private final String[] cageDiv = {"케이지1(대)","케이지4(중)","케이지9(소)"};
	
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
		
		vProtAniRegister = new JLabel("보호동물등록");
		
		register = new JButton("등록");
		register.setBackground(blue);
		register.setForeground(white);
		
		eProtAniList = new JTable(model1);
		eProtAniList.addMouseListener(protAniListMouseListener);
		aniListScroll = new JScrollPane(eProtAniList);
		aniListScroll.setPreferredSize(new Dimension(700,100));
		
		vProtAniInfo = new JLabel("보호동물정보");
		
		vAbanAniNo = new JLabel("유기동물번호");
		xAbanAniNo = new TextField(10);
		xAbanAniNo.setEnabled(false);
		
		vAbanAniType = new JLabel("유기동물구분");
		xAbanAniType = new TextField(10);
		xAbanAniType.setEnabled(false);
		
		vRescueNo = new JLabel("구조번호");
		xRescueNo = new TextField(10);
		xRescueNo.setEnabled(false);
		
		vAbanAniName = new JLabel("유기동물명");
		xAbanAniName = new TextField(10);
		xAbanAniName.setEnabled(false);
		
		vAge = new JLabel("나이(개월)");
		xAge = new TextField(10);
		xAge.setEnabled(false);
		
		vParAniName = new JLabel("어미유기동물명");
		xParAniName = new TextField(10);
		xParAniName.setEnabled(false);
		
		vAniType = new JLabel("동물종류");
		xAniType = new TextField(10);
		xAniType.setEnabled(false);
		
		vKind = new JLabel("품종");
		xKind = new TextField(10);
		xKind.setEnabled(false);
		
		vSex = new JLabel("성별");
		cbSex = new JComboBox<String>(sexDiv);
		
		vNeutWhet = new JLabel("중성화여부");
		cbNeutWhet = new JComboBox<String>(cbNeutWhetDiv);
		
		vColor = new JLabel("색상");
		xColor = new TextField(10);
		xColor.setEnabled(false);
		
		vAniSize = new JLabel("동물크기");
		cbAniSize = new JComboBox<String>(aniSizeDiv);
		
		vRegisDate = new JLabel("등록일자");
		xRegisDate = new TextField(10);
		xRegisDate.setEnabled(false);
		
		vDescription = new JLabel("설명");
		xDescription = new TextArea();
		descriptionScroll = new JScrollPane(xDescription);
		descriptionScroll.setPreferredSize(new Dimension(400,150));
		descriptionScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		vDscvDate = new JLabel("발견일시");
		xDscvDate = new TextField(10);
		xDscvDate.setEnabled(false);
		
		vCage = new JLabel("케이지");
		cbCage = new JComboBox<String>(cageDiv);
		
		vDscvPlace = new JLabel("발견장소");
		xDscvPlace = new TextField(10);
		xDscvPlace.setEnabled(false);
		
		modify = new JButton("수정");
		modify.setBackground(blue);
		modify.setForeground(white);
		
		cancel = new JButton("취소");
		
		returning = new JButton("반환");
		returning.setBackground(red);
		returning.setForeground(white);
		
		pictureManage = new JButton("사진관리");
		pictureManage.setBackground(blue);
		pictureManage.setForeground(white);
		
		previous = new JButton("<<");
		next = new JButton(">>");
		
		ProtAniListView();
	}
	
	private void ProtAniListView() throws IOException {
		setLayout(gridBagLayout);
		
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.ipadx = 7;
		
		gridBagConstraints.weightx=1.0;
		gridBagConstraints.weighty=1.0;
		
		gridbagAdd(vProtAniRegister, 0, 0, 1, 1);
		
		JPanel registerPanel = new JPanel();
		registerPanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,3));
		registerPanel.add(register);
		registerPanel.setBorder(BorderFactory.createEmptyBorder(0,135,0,0));
		gridbagAdd(registerPanel, 5, 0, 1, 1);
		
		gridbagAdd(aniListScroll, 0, 1, 10, 1);
		
		Component[] cops = {modify, cancel, returning};
		CombinePanel buttonPanel = new CombinePanel(cops,true);
		
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
		
		File input = new File("images/dog_400_400.jpg");
		BufferedImage image = ImageIO.read(input);
		BufferedImage resized = resize(image,200,200);
		imageLabel = new JLabel();
		ImageIcon icon = new ImageIcon(resized);
		imageLabel.setIcon(icon);
		
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
		
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(0,250,0,0));
		gridbagAdd(buttonPanel, 0, 12, 7, 1);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.add(previous);
		bottomPanel.add(next);
		bottomPanel.setBorder(BorderFactory.createEmptyBorder(0,40,0,0));
		gridbagAdd(bottomPanel, 5, 11 ,3, 1);
		
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
	
	//두개의 컴포넌트를 하나의 패널로 묶는 JPanel
	class CombinePanel extends JPanel {
		//컴포넌트 1, 컴포넌트 2, 패널 구성시 좌,우 margin 공간을 없애기 위한 Flag
		public CombinePanel(Component[] cops, boolean isBorder) {
			//Margin이 필요하지 않을 때
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
	
    private static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
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
