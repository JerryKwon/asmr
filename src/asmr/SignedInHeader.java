package asmr;

import javax.swing.*;
import java.awt.*;


public class SignedInHeader extends JPanel {
	
	private JLabel vCenterName, vWelcome;

	private JButton bMainButton, bLogout, bUserName;
	
	private JMenuBar mBar;
	private JMenu mCenter, mEmp, mAban, mReport, mAdop, mPost;
	
	private String[] mlCenter = {"센터목록"};
	private String[] mlEmp = {"직원등록", "직원조회"};
	private String[] mlAban = {"보호", "진료"};
	private String[] mlReport = {"신고등록", "신고배정(본부센터)", "신고배정(일반센터)"};
	private String[] mlAdop = {"공고목록", "신청목록", "입양목록"};
	private String[] mlPost = {"공지사항", "문의/답변"};
	
	private JPanel pMain;
	
	GridBagLayout gridbaglayout;				// 화면을 구성하는 레이아웃
	GridBagConstraints gridbagconstraints;	
	
	public SignedInHeader() {
				
		gridbaglayout = new GridBagLayout();		
		gridbagconstraints = new GridBagConstraints();
		
		bMainButton = new JButton(new ImageIcon("images/main.png"));
		bMainButton.setContentAreaFilled(false);
		bMainButton.setFocusPainted(false);
		bMainButton.setBorderPainted(false);
		
		bLogout = new JButton("로그아웃");
		
		bUserName = new JButton("권영인");
		bUserName.setContentAreaFilled(false);
		bUserName.setFocusPainted(false);
		bUserName.setBorderPainted(false);
		bUserName.setForeground(Color.cyan);
		
		vCenterName = new JLabel("부산남구보호센터");
		vWelcome = new JLabel("님 환영합니다");
		
		mBar = new JMenuBar();

		mCenter = new JMenu("센터");
		JMenuItem[] mlCenterItem = new JMenuItem[mlCenter.length];
		for (int i=0; i < mlCenter.length; i++){
			mlCenterItem[i] = new JMenuItem(mlCenter[i]);
			mCenter.add(mlCenterItem[i]);
		}
		mBar.add(mCenter);
		
		mEmp = new JMenu("직원");
		JMenuItem[] mlEmpItem = new JMenuItem[mlEmp.length];
		for (int i=0; i < mlEmp.length; i++){
			mlEmpItem[i] = new JMenuItem(mlEmp[i]);
			mEmp.add(mlEmpItem[i]);
		}
		mBar.add(mEmp);
		
		mAban = new JMenu("동물");
		JMenuItem[] mlAbanItem = new JMenuItem[mlAban.length];
		for (int i=0; i < mlAban.length; i++){
			mlAbanItem[i] = new JMenuItem(mlAban[i]);
			mAban.add(mlAbanItem[i]);
		}
		mBar.add(mAban);
		
		mReport = new JMenu("신고");
		JMenuItem[] mlReportItem = new JMenuItem[mlReport.length];
		for (int i=0; i < mlReport.length; i++){
			mlReportItem[i] = new JMenuItem(mlReport[i]);
			mReport.add(mlReportItem[i]);
		}
		mBar.add(mReport);
		
		mAdop = new JMenu("입양");
		JMenuItem[] mlAdopItem = new JMenuItem[mlAdop.length];
		for (int i=0; i < mlAdop.length; i++){
			mlAdopItem[i] = new JMenuItem(mlAdop[i]);
			mAdop.add(mlAdopItem[i]);
		}
		mBar.add(mAdop);
		
		mPost = new JMenu("게시판");
		JMenuItem[] mlPostItem = new JMenuItem[mlPost.length];
		for (int i=0; i < mlPost.length; i++){
			mlPostItem[i] = new JMenuItem(mlPost[i]);
			mPost.add(mlPostItem[i]);
		}
		mBar.add(mPost);
		
		SignedInHeaderView();
	}
	private void SignedInHeaderView() {
		
		gridbagconstraints.anchor = GridBagConstraints.WEST;		
		gridbagconstraints.ipadx = 7;		
				
		gridbagconstraints.weightx=1.0;		
		gridbagconstraints.weighty=1.0;		
				
		setLayout(gridbaglayout);
		
		gridbagAdd(bMainButton, 0, 0, 1, 3);
		gridbagAdd(mBar, 8, 1, 1, 2);
		gridbagAdd(vCenterName, 9, 1, 2, 1);
		gridbagAdd(bUserName, 9, 2, 1, 1);
		gridbagAdd(vWelcome, 10, 2, 1, 1);
		gridbagAdd(bLogout, 11, 0, 1, 1);


	}
	private void gridbagAdd(Component c, int x, int y, int w, int h) {			
		
		gridbagconstraints.gridx = x;		
		gridbagconstraints.gridy = y; 		
	      //가장 왼쪽 위 gridx, gridy값은 0 			
				
		gridbagconstraints.gridwidth  = w;	//넓이	
		gridbagconstraints.gridheight = h;	//높이	
	     			
	      			
	    gridbaglayout.setConstraints(c, gridbagconstraints); //컴포넌트를 컴포넌트 위치+크기 정보에 따라 GridBagLayout에 배치			
				
	   add(c);			
				
	   }
	public static void main(String[] args) {
		// TODO Auto-generated method stub		
		
	}

}
