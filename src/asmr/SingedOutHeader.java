package asmr;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class SingedOutHeader extends JFrame {

	private JButton bMainButton, bLogin, bRegister;
	
	private JMenuBar mBar;
	private JMenu mCenter, mEmp, mAban, mReport, mAdop, mPost;
	
	private String[] mlCenter = {"센터목록"};
	private String[] mlEmp = {"직원등록", "직원조회"};
	private String[] mlAban = {"동물등록", "보호", "진료"};
	private String[] mlReport = {"신고등록", "신고배정(본부센터)", "신고배정(일반센터)"};
	private String[] mlAdop = {"공고목록", "신청목록", "입양목록"};
	private String[] mlPost = {"공지사항", "문의/답변"};
	
	private JPanel pContents;
	private ContentPanel ContentPanel;	
	
	public SingedOutHeader() {
		
		setLayout(null);
		
		MenuActionListener listener = new MenuActionListener();
		
		bMainButton = new JButton(" ", new ImageIcon("images/main.png"));
		bMainButton.setContentAreaFilled(false);
		bMainButton.setFocusPainted(false);
		bMainButton.setBorderPainted(false);
		bMainButton.addActionListener(listener);
		
		bLogin = new JButton("로그인");
		bLogin.addActionListener(listener);
		bRegister = new JButton("회원가입");
		
		mBar = new JMenuBar();
		

		mCenter = new JMenu("센터");
		JMenuItem[] mlCenterItem = new JMenuItem[mlCenter.length];
		for (int i=0; i < mlCenter.length; i++){
			mlCenterItem[i] = new JMenuItem(mlCenter[i]);
			mlCenterItem[i].addActionListener(listener);
			mCenter.add(mlCenterItem[i]);
		}
		mBar.add(mCenter);
		
		mEmp = new JMenu("직원");
		JMenuItem[] mlEmpItem = new JMenuItem[mlEmp.length];
		for (int i=0; i < mlEmp.length; i++){
			mlEmpItem[i] = new JMenuItem(mlEmp[i]);
			mlEmpItem[i].addActionListener(listener);
			mEmp.add(mlEmpItem[i]);
		}
		mBar.add(mEmp);
		
		mAban = new JMenu("동물");
		JMenuItem[] mlAbanItem = new JMenuItem[mlAban.length];
		for (int i=0; i < mlAban.length; i++){
			mlAbanItem[i] = new JMenuItem(mlAban[i]);
			mlAbanItem[i].addActionListener(listener);
			mAban.add(mlAbanItem[i]);
		}
		mBar.add(mAban);
		
		mReport = new JMenu("신고");
		JMenuItem[] mlReportItem = new JMenuItem[mlReport.length];
		for (int i=0; i < mlReport.length; i++){
			mlReportItem[i] = new JMenuItem(mlReport[i]);
			mlReportItem[i].addActionListener(listener);
			mReport.add(mlReportItem[i]);
		}
		mBar.add(mReport);
		
		mAdop = new JMenu("입양");
		JMenuItem[] mlAdopItem = new JMenuItem[mlAdop.length];
		for (int i=0; i < mlAdop.length; i++){
			mlAdopItem[i] = new JMenuItem(mlAdop[i]);
			mlAdopItem[i].addActionListener(listener);
			mAdop.add(mlAdopItem[i]);
		}
		mBar.add(mAdop);
		
		mPost = new JMenu("게시판");
		JMenuItem[] mlPostItem = new JMenuItem[mlPost.length];
		for (int i=0; i < mlPost.length; i++){
			mlPostItem[i] = new JMenuItem(mlPost[i]);
			mlPostItem[i].addActionListener(listener);
			mPost.add(mlPostItem[i]);
		}
		mBar.add(mPost);
		
		ContentPanel = new ContentPanel();
		pContents = new MainPage();
		ContentPanel.add(pContents);
		MainPageView();
	}
	private void MainPageView() {
		setTitle("ASMR");
		setExtendedState(MAXIMIZED_BOTH);
		
		bMainButton.setBounds(10,10,240,240);
		mBar.setBounds(310,110,300,50);
		bLogin.setBounds(700, 10, 100, 40);
		bRegister.setBounds(810, 10, 100, 40);
		ContentPanel.setBounds(100,200, 500, 500);
		
		this.add(bMainButton);
		this.add(mBar);
		this.add(ContentPanel);
		this.add(bLogin);
		this.add(bRegister);
		
		pack();
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	class MenuActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String cont = e.getActionCommand();
			switch(cont) {
			case " ":
				getContentPane().remove(pContents);
				pContents = new MainPage();
				revalidate();
				repaint();
				break;
			case "로그인":
				getContentPane().remove(pContents);
				pContents = new Login();
				revalidate();
				repaint();
			}
		}
	}
	class ContentPanel extends JPanel {
		public void ContentPanel() {
			
			setPreferredSize(new Dimension(1800, 900));
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new SingedOutHeader();
	}

}
