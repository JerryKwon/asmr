package asmr;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

public class DiagAniList extends JFrame{
	private JLabel vProtAniList, vDiagList, vDiagInfo, vDiagDate, vDiagType, vIndiResult, vIndiVtrnName, vOudiResult, vHospName, vDisease, vInfecWhet, vCureType, vHsptzDate, vDschDate, vDeathType, vDeathReason, vDiagContent;
	private JTextField xDiagDate, xDiagType, xIndiResult, xIndiVtrnName, xOudiResult, xHospName, xDisease, xInfecWhet, xCureType, xHsptzDate, xDschDate, xDeathType, xDeathReason;
	private JButton diagRegister, imageButton, modify, cancel;
	private JTextArea xDiagContent; 
	private BufferedImage buttonIcon;
	
	private final String[] col1 = {"유기동물명","동물종류","품종","나이(개월)","크기"};
	private final String[] col2 = {"진료일자","진료구분","내용"};
	
	private DefaultTableModel model1 = new DefaultTableModel(col1,0);
	private DefaultTableModel model2 = new DefaultTableModel(col2,0);
	
	private JTable eProtAniList;
	private JScrollPane protAniListScroll;
	
	private JTable eDiagList;
	private JScrollPane diagListScroll;
	
	private JScrollPane diagContentScroll;
	
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	
	ProtAniListMouseListener protAniListMouseListener;
	DiagListMouseListener diagListMouseListener;
	
	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;
	
	public DiagAniList() throws IOException {
		gridBagLayout = new GridBagLayout();		
		gridBagConstraints = new GridBagConstraints();
	
		protAniListMouseListener = new ProtAniListMouseListener();
		diagListMouseListener = new DiagListMouseListener();
		
		vProtAniList = new JLabel("보호동물목록");
		
		diagRegister = new JButton("진료등록");
		diagRegister.setBackground(blue);
		diagRegister.setForeground(white);
		
		vDiagList = new JLabel("진료목록");
		
		eProtAniList = new JTable(model1);
		eProtAniList.addMouseListener(protAniListMouseListener);
		protAniListScroll = new JScrollPane(eProtAniList);
		protAniListScroll.setPreferredSize(new Dimension(350,100));
		
		eDiagList = new JTable(model2);
		eDiagList.addMouseListener(diagListMouseListener);
		diagListScroll = new JScrollPane(eDiagList);
		diagListScroll.setPreferredSize(new Dimension(350,100));
		
		vDiagInfo = new JLabel("진료정보");
		
		vDiagDate = new JLabel("진료일자");
		xDiagDate = new JTextField(10);
		xDiagDate.setEnabled(false);
		
		vDiagType = new JLabel("진료구분");
		xDiagType = new JTextField(10);
		xDiagType.setEnabled(false);
		
		vIndiResult = new JLabel("진료결과");
		xIndiResult = new JTextField(10);
		xIndiResult.setEnabled(false);
		
		vIndiVtrnName = new JLabel("내진수의사명");
		xIndiVtrnName =  new JTextField(10);
		xIndiVtrnName.setEnabled(false);
		
		vOudiResult = new JLabel("외진결과");
		xOudiResult = new JTextField(10);
		xOudiResult.setEnabled(false);
		
		vHospName = new JLabel("병원명");
		xHospName = new JTextField(10);
		xHospName.setEnabled(false);
		
		vDisease = new JLabel("병명");
		xDisease = new JTextField(10);
		xDisease.setEnabled(false);
		
		vInfecWhet = new JLabel("전염여부");
		xInfecWhet = new JTextField(10);
		xInfecWhet.setEnabled(false);
		
		vCureType = new JLabel("치료구분");
		xCureType = new JTextField(10);
		xCureType.setEnabled(false);
		
		vHsptzDate = new JLabel("입원일자");
		xHsptzDate = new JTextField(10);
		xHsptzDate.setEnabled(false);
		
		vDschDate = new JLabel("퇴원일자");
		xDschDate = new JTextField(10);
		xDschDate.setEnabled(false);
		buttonIcon = ImageIO.read(new File("images/cal1.png"));
		imageButton = new JButton(new ImageIcon(buttonIcon));
		imageButton.setBorderPainted(false);
		imageButton.setContentAreaFilled(false);
		imageButton.setFocusPainted(false);
		
		
		vDeathType = new JLabel("사망구분");
		xDeathType = new JTextField(10);
		xDeathType.setEnabled(false);
		
		vDeathReason = new JLabel("사망사유");
		xDeathReason = new JTextField(10);
		xDeathReason.setEnabled(false);
		
		vDiagContent = new JLabel("진료내용");
		xDiagContent = new JTextArea();
		xDiagContent.setEnabled(false);
		diagContentScroll = new JScrollPane(xDiagContent);
		diagContentScroll.setPreferredSize(new Dimension(400,150));
		diagContentScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		modify = new JButton("수정");
		modify.setBackground(blue);
		modify.setForeground(white);
		
		cancel = new JButton("취소");
		
		DiagAniListView();
		
	}

	private void DiagAniListView() {
		setLayout(gridBagLayout);
		
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.ipadx = 7;
		
		gridBagConstraints.weightx=1.0;
		gridBagConstraints.weighty=1.0;
		
		gridbagAdd(vProtAniList, 0, 0, 1, 1);
		gridbagAdd(diagRegister, 2, 0, 1, 1);
		gridbagAdd(protAniListScroll, 0, 1, 3, 1);
		
		gridbagAdd(vDiagList, 3, 0, 1, 1);
		gridbagAdd(diagListScroll, 3, 1, 3, 1);
		
		gridbagAdd(vDiagInfo, 0, 2, 1, 1);
		
		gridbagAdd(vDiagDate, 0, 3, 1, 1);
		gridbagAdd(xDiagDate, 1, 3, 1, 1);
		
		gridbagAdd(vDiagType, 2, 3, 1, 1);
		gridbagAdd(xDiagType, 3, 3, 1, 1);
		
		gridbagAdd(vIndiResult, 0, 4, 1, 1);
		gridbagAdd(xIndiResult, 1, 4, 1, 1);
		
		gridbagAdd(vIndiVtrnName, 2, 4, 1, 1);
		gridbagAdd(xIndiVtrnName, 3, 4, 1, 1);
		
		gridbagAdd(vOudiResult, 0, 5, 1, 1);
		gridbagAdd(xOudiResult, 1, 5, 1, 1);
		
		gridbagAdd(vHospName, 2, 5, 1, 1);
		gridbagAdd(xHospName, 3, 5, 1, 1);
		
		gridbagAdd(vDisease, 0, 6, 1, 1);
		gridbagAdd(xDisease, 1, 6, 1, 1);
		
		gridbagAdd(vInfecWhet, 2, 6, 1, 1);
		gridbagAdd(xInfecWhet, 3, 6, 1, 1);
		
		gridbagAdd(vCureType, 0, 7, 1, 1);
		gridbagAdd(xCureType, 1, 7, 1, 1);
		
		gridbagAdd(vHsptzDate, 2, 7, 1, 1);
		gridbagAdd(xHsptzDate, 3, 7, 1, 1);
		
		gridbagAdd(vDschDate, 2, 8, 1, 1);
		Component[] cops1 = {xDschDate,imageButton};
		CombinePanel dschDatePanel = new CombinePanel(cops1, false);
		gridbagAdd(dschDatePanel, 3, 8, 1, 1);
//		gridbagAdd(xDschDate, 3, 8, 1, 1);
//		gridbagAdd(imageButton, 4, 8, 1, 1);
		
		gridbagAdd(vDeathType, 0, 9, 1, 1);
		gridbagAdd(xDeathType, 1, 9, 1, 1);

		gridbagAdd(vDeathReason, 2, 9, 1, 1);
		gridbagAdd(xDeathReason, 3, 9, 1, 1);
		
		gridbagAdd(vDiagContent, 0, 10, 1, 1);
		gridbagAdd(diagContentScroll, 1, 10, 3, 1);
	
		Component[] cops2 = {modify, cancel};
		CombinePanel buttonPanel = new CombinePanel(cops2, true);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 280, 0, 0));
		gridbagAdd(buttonPanel, 0, 11, 6, 1);
		
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
	
	class ProtAniListMouseListener extends MouseAdapter{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			super.mouseClicked(e);
			if(e.getButton()==1) {
				
			}
		}
		
	}
	
	class DiagListMouseListener extends MouseAdapter{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			super.mouseClicked(e);
			if(e.getButton()==1) {
				checkDiagType();
			}
		}
		
	}
	
	//진료목록이 element를 읽은 후에 그 element의 진료구분에 따라 달력 imageButton을 활성화/비활성화합니다.
	private void checkDiagType() {
		String target = xDiagType.getText();
		if(target=="내진")
			imageButton.setEnabled(false);
		else if(target=="외진")
			imageButton.setEnabled(true);
	}
	
	public static void main(String[] args) throws IOException {
		new DiagAniList();
	}
}
