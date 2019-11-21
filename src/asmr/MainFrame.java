package asmr;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;


public class MainFrame extends JFrame {
	
	public static Color bgc = new Color(253, 247, 207);

	private JButton bMainButton, bLogo, bLogin, bRegister, bLogout, bUserName, bCustName;
	private JLabel vCenterName, vWelcome;
	
	private JMenuBar mBar;
	private JMenu mAban, mReport, mAdop, mPost, mAdopManage;
	private JMenuItem mReportAni, mAdopAnnc, mCenter, mEmp, mReportManage;
	
	private String[] mlAban = {"기본정보관리", "진료"};
	private String[] mlAdop = {"입양신청관리", "입양목록"};
	private String[] mlPost = {"공지사항", "문의/답변"};
	
	private static JPanel pContents;
	private static ContentPanel ContentPanel;	
	
	Login login;
	public static String empName, empCntr, custID, custName, CntrNo;
	public static int custNo;
	
	public MainFrame() {
		login = new Login();
		login.setMain(this);
		
		setLayout(null);
		
		MenuActionListener listener = new MenuActionListener();
		
		ImageIcon iMainIcon = new ImageIcon("images/main.png");
		Image iMainLogo = iMainIcon.getImage();
		Image iMainButton = iMainLogo.getScaledInstance(180, 180, Image.SCALE_SMOOTH);
		
		bMainButton = new JButton(new ImageIcon(iMainButton));
		bMainButton.setContentAreaFilled(false);
		bMainButton.setFocusPainted(false);
		bMainButton.setBorderPainted(false);
		bMainButton.addActionListener(listener);
		
		ImageIcon iLogoIcon = new ImageIcon("images/asmr_logo.png");
		Image iLogo = iLogoIcon.getImage();
		Image iLogoBtn = iLogo.getScaledInstance(600, 150, Image.SCALE_SMOOTH);
		
		bLogo = new JButton(new ImageIcon(iLogoBtn));
		bLogo.setContentAreaFilled(false);
		bLogo.setFocusPainted(false);
		bLogo.setBorderPainted(false);
		bLogo.addActionListener(listener);
		
		bLogin = new JButton("로그인");
		bLogin.setContentAreaFilled(false);
		bLogin.setFocusPainted(false);
		bLogin.setBorderPainted(false);
		bLogin.setFont(new Font("나눔고딕", Font.BOLD, 14));
		bLogin.addActionListener(listener);
		
		bRegister = new JButton("회원가입");
		bRegister.setContentAreaFilled(false);
		bRegister.setFocusPainted(false);
		bRegister.setBorderPainted(false);
		bRegister.setFont(new Font("나눔고딕", Font.BOLD, 14));
		bRegister.addActionListener(listener);
		
		bLogout = new JButton("로그아웃");
		bLogout.setContentAreaFilled(false);
		bLogout.setFocusPainted(false);
		//bLogout.setBorderPainted(false);
		bLogout.setFont(new Font("나눔고딕", Font.BOLD, 14));
		bLogout.addActionListener(listener);
		
		vWelcome = new JLabel("님 환영합니다.");
		vWelcome.setFont(new Font("나눔고딕", Font.BOLD, 14));
		vCenterName = new JLabel(" ");
		vCenterName.setFont(new Font("나눔고딕", Font.BOLD, 14));
		bUserName = new JButton(" ");
		bUserName.setFont(new Font("나눔고딕", Font.BOLD, 14));
		bUserName.addActionListener(listener);
		bUserName.setContentAreaFilled(false);
		bUserName.setFocusPainted(false);
		bUserName.setBorderPainted(false);
		bUserName.setForeground(new Color(0, 180, 255));
		
		bCustName = new JButton(" ");
		bCustName.setFont(new Font("나눔고딕", Font.BOLD, 14));
		bCustName.addActionListener(listener);
		bCustName.setContentAreaFilled(false);
		bCustName.setFocusPainted(false);
		bCustName.setBorderPainted(false);
		bCustName.setForeground(new Color(0, 180, 255));
		
		mBar = new JMenuBar();
		UIManager.put("Menu.font", new Font("나눔고딕", Font.BOLD, 20));

		mCenter = new JMenuItem("센터");
		mCenter.setPreferredSize(new Dimension(220, 50));
		mCenter.setBorder(new EmptyBorder(0,90,0,0));
		mCenter.setFont(new Font("나눔고딕", Font.BOLD, 20));
		//mCenter.setBackground(Color.WHITE);
		mCenter.setOpaque(false);
		mCenter.addActionListener(listener);
		mBar.add(mCenter);
		
		//mBar.add(new JSeparator(JSeparator.VERTICAL));
		mBar.add(Box.createHorizontalStrut(10));
		
		mEmp = new JMenuItem("직원");
		mEmp.setPreferredSize(new Dimension(220, 50));
		mEmp.setBorder(new EmptyBorder(0,90,0,0));
		mEmp.setFont(new Font("나눔고딕", Font.BOLD, 20));
		//mEmp.setBackground(Color.WHITE);
		mEmp.setOpaque(false);
		mEmp.addActionListener(listener);
		mBar.add(mEmp);
		mBar.add(Box.createHorizontalStrut(10));
		
		mAban = new JMenu("유기동물");
		JMenuItem[] mlAbanItem = new JMenuItem[mlAban.length];
		for (int i=0; i < mlAban.length; i++){
			mlAbanItem[i] = new JMenuItem(mlAban[i]);
			mlAbanItem[i].addActionListener(listener);
			mlAbanItem[i].setPreferredSize(new Dimension(218, 40));
			mlAbanItem[i].setFont(new Font("나눔고딕", Font.BOLD, 17));
			mAban.add(mlAbanItem[i]);
		}
		mAban.setPreferredSize(new Dimension(220, 50));
		mAban.setBorder(new EmptyBorder(0,65,0,0));
		mBar.add(mAban);
		mBar.add(Box.createHorizontalStrut(10));
		
		mReport = new JMenu("신고");
		mReportAni = new JMenuItem("유기동물신고");
		mReportAni.addActionListener(listener);
		mReportAni.setPreferredSize(new Dimension(218, 40));
		mReportAni.setFont(new Font("나눔고딕", Font.BOLD, 17));
		mReport.add(mReportAni);
		
		mReportManage = new JMenuItem("신고정보관리");
		mReportManage.setPreferredSize(new Dimension(218, 40));
		mReportManage.setFont(new Font("나눔고딕", Font.BOLD, 17));
		mReportManage.addActionListener(listener);
		mReport.setPreferredSize(new Dimension(220, 50));
		mReport.setBorder(new EmptyBorder(0,90,0,0));
		mReport.add(mReportManage);
		
		mBar.add(mReport);		
		mBar.add(Box.createHorizontalStrut(10));		
		
		mAdop = new JMenu("입양");
		mAdopAnnc = new JMenuItem("유기동물공고");
		mAdopAnnc.addActionListener(listener);
		mAdopAnnc.setPreferredSize(new Dimension(218, 40));
		mAdopAnnc.setFont(new Font("나눔고딕", Font.BOLD, 17));
		mAdop.add(mAdopAnnc);
		mAdopManage = new JMenu("입양관리");
		mAdopManage.setPreferredSize(new Dimension(218, 40));
		mAdopManage.setFont(new Font("나눔고딕", Font.BOLD, 17));
		
		JMenuItem[] mlAdopItem = new JMenuItem[mlAdop.length];
		for (int i=0; i < mlAdop.length; i++){
			mlAdopItem[i] = new JMenuItem(mlAdop[i]);
			mlAdopItem[i].addActionListener(listener);
			mlAdopItem[i].setPreferredSize(new Dimension(218, 40));
			mlAdopItem[i].setFont(new Font("나눔고딕", Font.BOLD, 17));
			mAdopManage.add(mlAdopItem[i]);
		}
		mAdop.add(mAdopManage);
		mAdop.setPreferredSize(new Dimension(220, 50));
		mAdop.setBorder(new EmptyBorder(0,90,0,0));
		mBar.add(mAdop);
		mBar.add(Box.createHorizontalStrut(10));
		
		mPost = new JMenu("게시판");
		JMenuItem[] mlPostItem = new JMenuItem[mlPost.length];
		for (int i=0; i < mlPost.length; i++){
			mlPostItem[i] = new JMenuItem(mlPost[i]);
			mlPostItem[i].addActionListener(listener);
			mlPostItem[i].setPreferredSize(new Dimension(218, 40));
			mlPostItem[i].setFont(new Font("나눔고딕", Font.BOLD, 17));
			mPost.add(mlPostItem[i]);
		}
		mPost.setPreferredSize(new Dimension(220, 50));
		mPost.setBorder(new EmptyBorder(0,80,0,0));
		mBar.add(mPost);
		mBar.setBackground(new Color(253, 237, 131));
		mBar.setBorder(new LineBorder(new Color(125, 117, 65)));
		
		ContentPanel = new ContentPanel();
		ContentPanel.setBackground(bgc);
		pContents = new MainPage();
		ContentPanel.add(pContents);
		
		JComponent[] coms = {bMainButton, bLogin, bRegister, bLogout, bUserName, bCustName};
		changeCursor(coms);
		
		MainPageView();
	}
	private void MainPageView() {
		setTitle("ASMR");
		setExtendedState(MAXIMIZED_BOTH);
		this.getContentPane().setBackground(new Color(253, 247, 207));
		
		bMainButton.setBounds(80,50,175,175);
		bLogo.setBounds(650, 5, 600, 150);
		mBar.setBounds(290,170,1370,50);
		
		bLogin.setBounds(1700, 130, 85, 30);
		bRegister.setBounds(1800, 130, 90, 30);
		
		vCenterName.setBounds(1695,170,170,25);
		bUserName.setBounds(1685,195,90,25);
		bCustName.setBounds(1685,195,90,25);
		vWelcome.setBounds(1755,195,170,25);
		bLogout.setBounds(1820, 130, 90, 30);
		
		ContentPanel.setBounds(50, 250, 1800, 1100);
		//ContentPanel.setBackground(Color.WHITE);
		
		this.add(bMainButton);
		this.add(bLogo);
		this.add(mBar);
		this.add(ContentPanel);
		
		// 비로그인 파트
		this.add(bLogin);
		this.add(bRegister);
		
		// 로그인 파트
		this.add(bLogout);
		this.add(vCenterName);
		this.add(bUserName);
		this.add(bCustName);
		this.add(vWelcome);
		
		// 버튼 보이기 여부
		bLogout.setVisible(false);
		vCenterName.setVisible(false);
		bUserName.setVisible(false);
		bCustName.setVisible(false);
		vWelcome.setVisible(false);
		
		// 메뉴 접근 가능여부
		mCenter.setEnabled(false);
		mEmp.setEnabled(false);
		mAban.setEnabled(false);
		mReportManage.setEnabled(false);
		mAdopManage.setEnabled(false);
		
		pack();
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void setLogout() {
		// 버튼 보이기 여부
		bLogin.setVisible(true);
		bRegister.setVisible(true);
		bLogout.setVisible(false);
		vCenterName.setVisible(false);
		bUserName.setVisible(false);
		bCustName.setVisible(false);
		vWelcome.setVisible(false);
		
		// 메뉴 접근 가능여부
		mCenter.setEnabled(false);
		mEmp.setEnabled(false);
		mAban.setEnabled(false);
		mReportManage.setEnabled(false);
		mAdopManage.setEnabled(false);
		
		//메인 페이지로
		ContentPanel.removeAll();
		pContents = new MainPage();
		ContentPanel.add(pContents);
		ContentPanel.revalidate();
		ContentPanel.repaint();
		//아이디, 비밀번호 제거
		login.clearField();
	}
	public void setLogin() {
		
		// 버튼 보이기 여부
		bLogin.setVisible(false);
		bRegister.setVisible(false);
		
		bLogout.setVisible(true);
		vWelcome.setVisible(true);
		empName = EmpData.getEmpName(login.empID);
		bUserName.setText(empName);
		bUserName.setVisible(true);
		
		empCntr = EmpData.getEmpCntr(login.empID);
		vCenterName.setText(empCntr);
		vCenterName.setVisible(true);
		
		// 메뉴 접근 가능여부
		mCenter.setEnabled(true);
		mEmp.setEnabled(true);
		mAban.setEnabled(true);
		mReportManage.setEnabled(true);
		mAdopManage.setEnabled(true);
		
		//메인 페이지 로드
		ContentPanel.removeAll();
		pContents = new MainPage();
		ContentPanel.add(pContents);
		ContentPanel.revalidate();
		ContentPanel.repaint();
	}
	public void setCustLogin(){
		// 버튼 보이기 여부
		bLogin.setVisible(false);
		bRegister.setVisible(false);
				
		bLogout.setVisible(true);
		vWelcome.setVisible(true);
		custNo = CustData.getCustNo(login.custID);
		custName = CustData.getCustName(custNo);
		bCustName.setText(custName);
		bCustName.setVisible(true);
		
		//메인 페이지 로드
		ContentPanel.removeAll();
		pContents = new MainPage();
		ContentPanel.add(pContents);
		ContentPanel.revalidate();
		ContentPanel.repaint();
	}
	
	class MenuActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String cont = e.getActionCommand();
			if(e.getSource().equals(bMainButton)){
				ContentPanel.removeAll();
				pContents = new MainPage();
				ContentPanel.add(pContents);
				revalidate();
				repaint();
			}else if(cont == "로그인"){
				ContentPanel.removeAll();
				pContents = login;
				ContentPanel.add(pContents);
				revalidate();
				repaint();
			}else if(cont == empName){
				ContentPanel.removeAll();
				pContents = new EmpMyPage();
				ContentPanel.add(pContents);
				revalidate();
				repaint();
			}else if(cont == custName){
				ContentPanel.removeAll();
				pContents = new CustMyPage();
				ContentPanel.add(pContents);
				revalidate();
				repaint();
			}else if(cont == "회원가입"){
				ContentPanel.removeAll();
				pContents = new CustRegister();
				ContentPanel.add(pContents);
				revalidate();
				repaint();
			}else if(e.getSource().equals(mCenter)){
				ContentPanel.removeAll();
				String empNo = login.getEmpNo();
				String cntrNo = EmpData.getCntrNo(empNo);
				pContents = new CenterList(cntrNo,empNo);
				ContentPanel.add(pContents);
				revalidate();
				repaint();
			}else if(cont == "로그아웃"){
				setLogout();
			}else if(e.getSource().equals(mEmp)){
				ContentPanel.removeAll();
				String empNo = login.getEmpNo();
				String cntrNo = EmpData.getCntrNo(empNo);
				pContents = new EmpList(cntrNo,empNo);
				ContentPanel.add(pContents);
				revalidate();
				repaint();
			}else if(cont == "기본정보관리"){
				ContentPanel.removeAll();
				try {
					String empNo = login.getEmpNo();
					String cntrNo = EmpData.getCntrNo(empNo);
					pContents = new ProtAniList(cntrNo,empNo);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ContentPanel.add(pContents);
				revalidate();
				repaint();
			}else if(cont == "진료"){
				ContentPanel.removeAll();
				try {
					String empNo = login.getEmpNo();
					String cntrNo = EmpData.getCntrNo(empNo);
					pContents = new DiagAniList(cntrNo,empNo);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ContentPanel.add(pContents);
				revalidate();
				repaint();
			}else if(cont == "유기동물신고"){
				ContentPanel.removeAll();
				try {
					pContents = new RprtRegis();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ContentPanel.add(pContents);
				revalidate();
				repaint();
			}else if(e.getSource().equals(mReportManage)){
				ContentPanel.removeAll();
				try {
					if(EmpData.getEmpCntrTp().equals(new String("h"))){
						pContents = new RprtAssignment();
					}
					else if(EmpData.getEmpCntrTp().equals(new String("n"))){
						pContents = new RprtAssignmentNorm();
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ContentPanel.add(pContents);
				revalidate();
				repaint();
			}else if(cont == "유기동물공고"){
				ContentPanel.removeAll();
				try {
					pContents = new AnncList();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				ContentPanel.add(pContents);
				revalidate();
				repaint();
			}else if(cont == "입양신청관리"){
				ContentPanel.removeAll();
				try {
					pContents = new ReqList();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ContentPanel.add(pContents);
				revalidate();
				repaint();
			}else if(cont == "입양목록"){
				ContentPanel.removeAll();
				try {
					pContents = new AdopList();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ContentPanel.add(pContents);
				revalidate();
				repaint();
			}else if(cont == "공지사항"){
				ContentPanel.removeAll();
				pContents = new NotiBoard();
				ContentPanel.add(pContents);
				revalidate();
				repaint();
			}else if(cont == "문의/답변"){
				ContentPanel.removeAll();
				pContents = new InqAnsBoard();
				ContentPanel.add(pContents);
				revalidate();
				repaint();
			}
			
		}
	}
	public static void notiCase() {
		ContentPanel.removeAll();
		pContents = new NotiWrtUpt();
		ContentPanel.add(pContents);
		ContentPanel.revalidate();
		ContentPanel.repaint();
	}
	
	public static void notiPostCase() {
		ContentPanel.removeAll();
		pContents = new NotiPost();
		ContentPanel.add(pContents);
		ContentPanel.revalidate();
		ContentPanel.repaint();
	}
	
	public static void InqPostCase() {
		ContentPanel.removeAll();
		pContents = new InqPost();
		ContentPanel.add(pContents);
		ContentPanel.revalidate();
		ContentPanel.repaint();
	}
	
	public static void notiBoardCase() {
		ContentPanel.removeAll();
		pContents = new NotiBoard();
		ContentPanel.add(pContents);
		ContentPanel.revalidate();
		ContentPanel.repaint();
	}
	
	public static void qnaCase() {
		ContentPanel.removeAll();
		pContents = new InqRegis();
		ContentPanel.add(pContents);
		ContentPanel.revalidate();
		ContentPanel.repaint();
	}
	
	public static void qnaBoardCase() {
		ContentPanel.removeAll();
		pContents = new InqAnsBoard();
		ContentPanel.add(pContents);
		ContentPanel.revalidate();
		ContentPanel.repaint();
	}
	
	public static void mainCase() {
		ContentPanel.removeAll();
		pContents = new MainPage();
		ContentPanel.add(pContents);
		ContentPanel.revalidate();
		ContentPanel.repaint();
	}
	public static void mainNotiCase(String postNo) {
		ContentPanel.removeAll();
		pContents = new MainNotiPost(postNo);
		ContentPanel.add(pContents);
		ContentPanel.revalidate();
		ContentPanel.repaint();
	}
	private void changeCursor(JComponent[] comps){
		for(JComponent c:comps){
			c.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
	}
	
	class ContentPanel extends JPanel {
		public void ContentPanel() {
			this.setPreferredSize(new Dimension(1800, 1100));
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainFrame main = new MainFrame();		
	}

}
