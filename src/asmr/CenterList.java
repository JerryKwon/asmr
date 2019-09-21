package asmr;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class CenterList extends JFrame{
	
	private JButton centerRegist, cageRegist, searchManager, save;
	private JLabel vCenterList,vCageList,vCenterInfo,vCenterNum,vEstDate,vCenterName,vPhoneNum,vArea,vOperTime,vOperTimeDash,vCenterManager,vCageNum,vCageBig,vCageMid,vCageSmall,vCageBigCount,vCageMidCount,vCageSmallCount;
	private JTextField xCenterNum,xEstDate,xCenterName,xPhoneNum,xArea,xCenterManager,xCageBig,xCageMid,xCageSmall;
	private JComboBox<String> cbOperTimeOpen,cbOperTimeClose;
	
	//테이블과 스크롤 패널
	private JTable eCenterList, eCageList;
	private JScrollPane scrollpane1, scrollpane2;
	
	//해당 클래스의 메인 레이아웃인 GridBag 
	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;
	
	//테이블의 컬럼 배열
	private final String[] col1 = {"센터명","주소","운영시간"};
	private final String[] col2 = {"케이지 순번","크기"};
	
	//테이블 컬럼 정보 - 센터목록
	private DefaultTableModel model1 = new DefaultTableModel(col1,0);
	//테이블 컬럼 정보 - 케이지
	private DefaultTableModel model2 = new DefaultTableModel(col2,0);
	
	// private final String[] phoneNumStart = {"010","02","031","032","033","041","042","043","044","051","052","053","054","055","061","062","063","064"};
	private final String[] operTimeOpen = {"08:00","08:30","09:00","09:30","10:00","10:30","11:00"};
	private final String[] operTimeClose = {"16:00","16:30","17:00","17:30","18:00","18:30","19:00"};
	
//	CenterListButtonListener centerListButtonListener;
	
	//CenterList 생성자
	public CenterList(){
		gridBagLayout = new GridBagLayout();
		gridBagConstraints = new GridBagConstraints();
		
//		centerListButtonListener = new CenterListButtonListener();
		
		//센터목록, 케이지목록, 센터정보 텍스트
		vCenterList = new JLabel("센터목록");
		eCenterList = new JTable(model1);
		scrollpane1 = new JScrollPane(eCenterList);
		scrollpane1.setPreferredSize(new Dimension(400,100));
		
		//케이지목록
		vCageList = new JLabel("케이지목록");
		eCageList = new JTable(model2);
		scrollpane2 = new JScrollPane(eCageList);
		scrollpane2.setPreferredSize(new Dimension(300,100));
		
		vCenterInfo = new JLabel("센터정보");
		
		//센터정보 아래 입력창 JLabel과 JTextField
		vCenterNum = new JLabel("센터번호");
		xCenterNum = new JTextField(10);
		xCenterNum.setEnabled(false);
		
		//날짜 형태 2001.01.20[10글자]
		vEstDate = new JLabel("설립일자");
		xEstDate = new JTextField(10);
		xEstDate.setEnabled(false);
		
		//센터명
		vCenterName = new JLabel("센터명");
		xCenterName = new JTextField(10);
		
		//전화번호 하나의 문자열로 변경
		vPhoneNum = new JLabel("전화번호");
		xPhoneNum = new JTextField(10);
		
		//면적
		vArea = new JLabel("면적");
		xArea = new JTextField(10);
		
		//운영시간
		vOperTime = new JLabel("운영시간");
		vOperTimeDash = new JLabel("~");
		cbOperTimeOpen = new JComboBox<String>(operTimeOpen);
		cbOperTimeClose = new JComboBox<String>(operTimeClose);
		
		//센터장 이름(직원명 - VARCHAR(20)) - 한글 1글자당 3byte 6.667글자
		vCenterManager = new JLabel("센터장");
		xCenterManager = new JTextField(10);
		xCenterManager.setEnabled(false);
		
		//총 케이지수 라벨
		vCageNum = new JLabel("총 케이지 수");
		
		//케이지
		vCageBig = new JLabel("대형");
		xCageBig = new JTextField(2);
		xCageBig.setEnabled(false);
		
		vCageMid = new JLabel("중형");
		xCageMid = new JTextField(2);
		xCageMid.setEnabled(false);
		
		vCageSmall = new JLabel("소형");
		xCageSmall = new JTextField(2);
		xCageSmall.setEnabled(false);
		
		vCageBigCount = new JLabel("개");
		vCageMidCount = new JLabel("개");
		vCageSmallCount = new JLabel("개");
		
		//버튼
		centerRegist = new JButton("등록");
//		centerRegist.addActionListener(centerListButtonListener);
		
		cageRegist = new JButton("등록");
//		cageRegist.addActionListener(centerListButtonListener);
		
		searchManager = new JButton("검색");
		
		save = new JButton("저장");
//		save.addActionListener(centerListButtonListener);
		
		
		CenterListView();
	}

	//Component 배치
	private void CenterListView() {
		setLayout(gridBagLayout);
		
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.ipadx = 7;
		
		gridBagConstraints.weightx=1.0;
		gridBagConstraints.weighty=1.0;
		
		//센터목록과 등록버튼
		gridbagAdd(vCenterList, 0, 0, 1, 1);
		gridbagAdd(centerRegist, 9, 0, 1, 1);
		
		//케이지와 등록버튼
		gridbagAdd(vCageList, 11, 0, 1, 1);
		gridbagAdd(cageRegist, 20, 0, 1, 1);
		
		//센터목록테이블, 케이지목록테이블
		gridbagAdd(scrollpane1, 0, 1, 10, 5);
		gridbagAdd(scrollpane2, 11, 1, 10, 5);
		
		//센터정보
		gridbagAdd(vCenterInfo, 0, 6, 1, 1);
		
		//센터번호
		gridbagAdd(vCenterNum, 0, 7, 1, 1);
		gridbagAdd(xCenterNum, 1, 7, 1, 1);
	
		//설립일자
		gridbagAdd(vEstDate, 11, 7, 1, 1);
		gridbagAdd(xEstDate, 12, 7, 1, 1);
		
		//센터명
		gridbagAdd(vCenterName,0, 8,1,1);
		gridbagAdd(xCenterName, 1, 8, 1, 1);
		
		//전화번호
		gridbagAdd(vPhoneNum, 11, 8, 1, 1);
		gridbagAdd(xPhoneNum, 12, 8, 1, 1);
		
		//면적
		gridbagAdd(vArea, 0, 9, 1, 1);
		gridbagAdd(xArea, 1, 9, 1, 1);
		
		//운영시간
		Component[] cops = {cbOperTimeOpen, vOperTimeDash,cbOperTimeClose};
		CombinePanel operTimePanel = new CombinePanel(cops,0,0);
		
		gridbagAdd(vOperTime, 11, 9, 1, 1);
		gridbagAdd(operTimePanel, 12, 9, 1, 1);
		
//		gridbagAdd(cbOperTimeOpen, 12, 9, 1, 1);
//		gridbagAdd(vOperTimeDash, 13, 9, 1, 1);
//		gridbagAdd(cbOperTimeClose, 14, 9, 1, 1);
		
		//센터장
		gridbagAdd(vCenterManager, 0, 10, 1, 1);
		gridbagAdd(xCenterManager, 1, 10, 1, 1);
		gridbagAdd(searchManager, 2, 10, 1, 1);
		
		//총 케이지 수
		gridbagAdd(vCageNum, 0, 11, 1, 1);
		
		//형태별 케이지 나열을 위한 Bottom Panel 및 배치
		BottomPanel bottomPanel = new BottomPanel();
		gridbagAdd(bottomPanel, 0, 12, 2, 1);
		
		//저장버튼 배치
		JPanel savePanel = new JPanel();
		savePanel.add(save);
		savePanel.setBorder(BorderFactory.createEmptyBorder(10,325,0,0));
		gridbagAdd(savePanel, 0, 13, 21, 1);
		
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
	
//	class CenterListButtonListener implements ActionListener{
//
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			// TODO Auto-generated method stub
//			if(e.getSource().equals(centerRegist)) {
//				System.out.println("센터등록버튼 테스트");
//			}
//			
//			if(e.getSource().equals(cageRegist)) {
//				System.out.println("케이지등록버튼 테스트");
//			}
//			
//			if(e.getSource().equals(save)) {
//				System.out.println("저장등록버튼 테스트");
//			}
//		}
//		
//	}
//	
	public static void main(String[] args) {
		new CenterList();
	}
}


