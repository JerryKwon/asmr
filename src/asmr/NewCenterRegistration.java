package asmr;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import asmr.CenterList.BottomPanel;

public class NewCenterRegistration extends JFrame{
	private JLabel vNewCenterRegist,vCenterName,vCenterType,vArea,vPhoneNum,vEstDate,vOperTime,vOperTimeDash,vCenterManager,vAddress,vCageNum,vCageBig,vCageMid,vCageSmall,vCageBigCount,vCageMidCount,vCageSmallCount;
	private JTextField xCenterName,xArea,xPhoneNum,xEstDate,xCenterManager,xAddress,xCageBig,xCageMid,xCageSmall;
	private JButton centerManagerSearch,addressSearch, save, imageButton;
	private JComboBox<String> cbCenterType,cbOperTimeOpen,cbOperTimeClose;
	private BufferedImage buttonIcon;
	
	
	private final String[] centerTypeDiv = {"본부","일반"};
	private final String[] operTimeOpenDiv = {"08:00","08:30","09:00","09:30","10:00","10:30","11:00"};
	private final String[] operTimeCloseDiv = {"16:00","16:30","17:00","17:30","18:00","18:30","19:00"};
	
	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;
	
	public NewCenterRegistration() throws IOException {
		//레이아웃 생성
		gridBagLayout = new GridBagLayout();
		gridBagConstraints = new GridBagConstraints();
		
		//화면명
		vNewCenterRegist = new JLabel("신규센터등록");
		
		//센터명
		vCenterName = new JLabel("센터명");
		xCenterName = new JTextField(10);
		
		//센터구분
		vCenterType = new JLabel("센터구분");
		cbCenterType = new JComboBox<String>(centerTypeDiv);
		
		//면적
		vArea = new JLabel("면적");
		xArea = new JTextField(10);
		
		//전화번호
		vPhoneNum = new JLabel("전화번호");
		xPhoneNum = new JTextField(10);
		
		//설립일자
		vEstDate = new JLabel("설립일자");
		xEstDate = new JTextField(10); 
		xEstDate.setEnabled(false);
		buttonIcon = ImageIO.read(new File("images/cal1.png"));
		imageButton = new JButton(new ImageIcon(buttonIcon));
		imageButton.setBorderPainted(false);
		imageButton.setContentAreaFilled(false);
		imageButton.setFocusPainted(false);
		
		//운영시간
		vOperTime = new JLabel("운영시간");
		cbOperTimeOpen = new JComboBox<String>(operTimeOpenDiv);
		vOperTimeDash = new JLabel("~");
		cbOperTimeClose = new JComboBox<String>(operTimeCloseDiv);
		
		vCenterManager = new JLabel("센터장");
		xCenterManager = new JTextField(10);
		xCenterManager.setEnabled(false);
		centerManagerSearch = new JButton("검색");
		
		//주소
		vAddress = new JLabel("주소");
		xAddress = new JTextField(20);
		xAddress.setEnabled(false);
		addressSearch = new JButton("검색");
		
		//총 케이지 수
		vCageNum = new JLabel("총 케이지 수");
		
		//대형
		vCageBig = new JLabel("대형");
		xCageBig = new JTextField(2);
		vCageBigCount = new JLabel("개");
		
		//중형
		vCageMid = new JLabel("중형");
		xCageMid = new JTextField(2);
		vCageMidCount = new JLabel("개");
		
		//소형
		vCageSmall = new JLabel("소형");
		xCageSmall = new JTextField(2);
		vCageSmallCount = new JLabel("개");
		
		//저장버튼
		save = new JButton("저장");
		
		//배치함수
		NewCenterRegistrationView();
	}

	private void NewCenterRegistrationView() {
		setLayout(gridBagLayout);
		
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.ipadx = 7;
		
		gridBagConstraints.weightx=1.0;
		gridBagConstraints.weighty=1.0;
		
		//화면제목
		gridbagAdd(vNewCenterRegist, 0, 0, 1, 1);
		
		//센터명
		gridbagAdd(vCenterName, 0, 1, 1, 1);
		gridbagAdd(xCenterName, 1, 1, 1, 1);
		
		//센터구분
		gridbagAdd(vCenterType, 11, 1, 1, 1);
		gridbagAdd(cbCenterType, 12, 1, 1, 1);
		
		//면적
		gridbagAdd(vArea, 0, 2, 1, 1);
		gridbagAdd(xArea, 1, 2, 1, 1);
		
		//전화번호
		gridbagAdd(vPhoneNum, 11, 2, 1, 1);
		gridbagAdd(xPhoneNum, 12, 2, 1, 1);
		
		//설립일자
		gridbagAdd(vEstDate, 0, 3, 1, 1);
		JPanel plainPanel1 = new JPanel();
		plainPanel1.setLayout(new FlowLayout(FlowLayout.LEFT,5,0));
		plainPanel1.add(imageButton);
		Component[] cops1 = {xEstDate, plainPanel1};
		CombinePanel estDatePanel = new CombinePanel(cops1, 0, 0);
		gridbagAdd(estDatePanel, 1, 3, 1, 1);
		
		//운영시간
		gridbagAdd(vOperTime, 11, 3, 1, 1);
		Component[] cops2 = {cbOperTimeOpen, vOperTimeDash, cbOperTimeClose};
		CombinePanel operTimePanel = new CombinePanel(cops2,0,0);
		gridbagAdd(operTimePanel, 12 ,3, 1, 1);
		
		//센터장	
		gridbagAdd(vCenterManager, 0, 4, 1, 1);
		JPanel plainPanel2 = new JPanel();
		plainPanel2.setLayout(new FlowLayout(FlowLayout.LEFT,5,0));
		plainPanel2.add(centerManagerSearch);
		Component[] cops3 = {xCenterManager, plainPanel2};
		CombinePanel centerManagerPanel = new CombinePanel(cops3,0,0);
		gridbagAdd(centerManagerPanel, 1, 4, 1, 1);
		
		
		//주소
		gridbagAdd(vAddress, 0, 5, 1, 1);
		gridbagAdd(xAddress, 1, 5, 1, 1);
		gridbagAdd(addressSearch, 2, 5, 1, 1);
		
		//총 케이지 수
		gridbagAdd(vCageNum, 0, 6, 1, 1);
		
		//형태별 케이지 나열을 위한 Bottom Panel 및 배치
		BottomPanel bottomPanel = new BottomPanel();
		gridbagAdd(bottomPanel, 0, 7, 3, 1);
		
		
		//저장버튼
		gridbagAdd(save, 10, 10, 1, 1);
		
		
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
	
	//케이지 형태별 구분을 나열을 위한 Bottom Panel 선언
	class BottomPanel extends JPanel{
		public BottomPanel() {
			setLayout(new FlowLayout(FlowLayout.LEFT));

			add(vCageBig);
			add(xCageBig);
			add(vCageBigCount);
			add(vCageMid);
			add(xCageMid);
			add(vCageMidCount);
			add(vCageSmall);
			add(xCageSmall);
			add(vCageSmallCount);
			
		}
	}
	
	class CombinePanel extends JPanel {
		//컴포넌트 1, 컴포넌트 2, 패널 구성시 좌,우 margin 공간을 없애기 위한 Flag
		public CombinePanel(Component[] cops, int borderWidth, int borderHeight) {
			//Margin이 필요하지 않을 때
			
			setLayout(new FlowLayout(FlowLayout.LEFT,borderWidth,borderHeight));
			
			for (Component c: cops) {
				add(c);
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		new NewCenterRegistration();
	}
}
