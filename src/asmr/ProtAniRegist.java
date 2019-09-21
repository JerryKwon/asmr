package asmr;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextField;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class ProtAniRegist extends JFrame{
	private JLabel vProtAniRegist, vAbanAniType, vRescNo, vParAbanAniName, vAbanAniName, vAge, vAniType, vKind, vSex, vNeutWhet, vColor, vAniSize, vFeature, vPicture, vCage;
	private TextField xRescNo, xParAbanAniName, xAbanAniName, xAge, xKind, xColor;
	private JComboBox<String> cbAbanAniType, cbAniType, cbSex, cbNeutWhet, cbAniSize, cbCage;
	private JTextArea xFeature;
	private JButton pictureManage, register, cancel;
	
	private JScrollPane featureScroll;
	
	private final String[] abanAniTypeDiv = {"구조","탄생"};
	private final String[] aniTypeDiv = {"개","고양이","기타"};
	private final String[] sexDiv = {"수컷","암컷","미상"};
	private final String[] neutWhetDiv = {"Y","N"};
	private final String[] aniSizeDiv = {"대","중","소"};
	private final String[] cageDiv = {"케이지1(대)","케이지4(중)","케이지9(소)"};
	
	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;
	
	public ProtAniRegist() {
		
		gridBagLayout = new GridBagLayout();		
		gridBagConstraints = new GridBagConstraints();
		
		vProtAniRegist = new JLabel("보호동물등록");
		
		vAbanAniType = new JLabel("유기동물구분");
		cbAbanAniType = new JComboBox<String>(abanAniTypeDiv);
		
		vRescNo = new JLabel("구조번호");
		xRescNo = new TextField(10);
		xRescNo.setEnabled(false);
		
		vParAbanAniName = new JLabel("어미유기동물명");
		xParAbanAniName = new TextField(10);
		xParAbanAniName.setEnabled(false);
		
		vAbanAniName = new JLabel("유기동물명");
		xAbanAniName = new TextField(10);
		
		vAge = new JLabel("나이(개월)");
		xAge = new TextField(10);
		
		vAniType = new JLabel("동물종류");
		cbAniType = new JComboBox<String>(aniTypeDiv);
		
		vKind = new JLabel("품종");
		xKind = new TextField(10);
		
		vSex = new JLabel("성별");
		cbSex = new JComboBox<String>(sexDiv);
		
		vNeutWhet = new JLabel("중성화여부");
		cbNeutWhet = new JComboBox<String>(neutWhetDiv);
		
		vColor = new JLabel("색상");
		xColor = new TextField(10);
		
		vAniSize = new JLabel("동물크기");
		cbAniSize = new JComboBox<String>(aniSizeDiv);
		
		vFeature = new JLabel("특징");
		xFeature = new JTextArea();
		featureScroll = new JScrollPane(xFeature);
		featureScroll.setPreferredSize(new Dimension(300,100));
		featureScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		vPicture = new JLabel("사진");
		pictureManage = new JButton("사진관리");
		
		vCage = new JLabel("케이지");
		cbCage = new JComboBox<String>(cageDiv);
		
		register = new JButton("등록");
		cancel = new JButton("취소");
		
		ProtAniRegistView();
	}
	
	private void ProtAniRegistView() {
		setLayout(gridBagLayout);
		
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.ipadx = 7;
		
		gridBagConstraints.weightx=1.0;
		gridBagConstraints.weighty=1.0;
	
		gridbagAdd(vProtAniRegist, 0, 0, 1, 1);
		
		gridbagAdd(vAbanAniType, 0, 1, 1, 1);
		gridbagAdd(cbAbanAniType, 1, 1, 1, 1);
		
		gridbagAdd(vRescNo, 2, 1, 1, 1);
		gridbagAdd(xRescNo, 3, 1, 1, 1);
		
		gridbagAdd(vParAbanAniName, 4, 1, 1, 1);
		gridbagAdd(xParAbanAniName, 5, 1, 1, 1);
		
		gridbagAdd(vAbanAniName, 0, 2, 1, 1);
		gridbagAdd(xAbanAniName, 1, 2, 1, 1);
		
		gridbagAdd(vAge, 2, 2, 1, 1);
		gridbagAdd(xAge, 3, 2, 1, 1);
		
		gridbagAdd(vAniType, 0, 3, 1, 1);
		gridbagAdd(cbAniType, 1, 3, 1, 1);
		
		gridbagAdd(vKind, 2, 3, 1, 1);
		gridbagAdd(xKind, 3, 3, 1, 1);
		
		gridbagAdd(vSex, 0, 4, 1, 1);
		gridbagAdd(cbSex, 1, 4, 1, 1);
		
		gridbagAdd(vNeutWhet, 2, 4, 1, 1);
		gridbagAdd(cbNeutWhet, 3, 4, 1, 1);
		
		gridbagAdd(vColor, 0, 5, 1, 1);
		gridbagAdd(xColor, 1, 5, 1, 1);
		
		gridbagAdd(vAniSize, 2, 5, 1, 1);
		gridbagAdd(cbAniSize, 3, 5, 1, 1);
		
		gridbagAdd(vFeature, 0, 6, 1, 1);
		gridbagAdd(featureScroll, 1, 6, 3, 1);
		
		gridbagAdd(vPicture, 0, 7, 1, 1);
		gridbagAdd(pictureManage, 1, 7, 1, 1);
		
		gridbagAdd(vCage, 0, 8, 1, 1);
		gridbagAdd(cbCage, 1, 8, 1, 1);
	
		Component[] cops = {register, cancel};
		CombinePanel buttonPanel = new CombinePanel(cops, true);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 225, 0, 0));
		gridbagAdd(buttonPanel, 0, 9, 6, 1);
		
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
	
	public static void main(String[] args) {
		new ProtAniRegist();
	}
}
