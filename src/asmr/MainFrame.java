package asmr;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;


public class MainFrame extends JFrame {

	private JButton bMainButton, bLogin, bRegister, bLogout, bUserName;
	private JLabel vCenterName, vWelcome;
	
	private JMenuBar mBar;
	private JMenu mCenter, mEmp, mAban, mReport, mAdop, mPost;
	
	private String[] mlCenter = {"���͸��"};
	private String[] mlEmp = {"�������", "������ȸ"};
	private String[] mlAban = {"�������", "��ȣ", "����"};
	private String[] mlReport = {"�Ű���", "�Ű����(���μ���)", "�Ű����(�Ϲݼ���)"};
	private String[] mlAdop = {"������", "��û���", "�Ծ���"};
	private String[] mlPost = {"��������", "����/�亯"};
	
	private JPanel pContents;
	private ContentPanel ContentPanel;	
	
	private Login Login;
	public static int LoginState;
	
	public MainFrame() {
		
		LoginState = 1;
		setLayout(null);
		
		MenuActionListener listener = new MenuActionListener();
		
		ImageIcon iMainIcon = new ImageIcon("images/main.png");
		Image iMainLogo = iMainIcon.getImage();
		Image iMainButton = iMainLogo.getScaledInstance(180, 180, Image.SCALE_SMOOTH);
		
		bMainButton = new JButton(" ", new ImageIcon(iMainButton));
		bMainButton.setContentAreaFilled(false);
		bMainButton.setFocusPainted(false);
		bMainButton.setBorderPainted(false);
		bMainButton.addActionListener(listener);
		
		bLogin = new JButton("�α���");
		bLogin.addActionListener(listener);
		bRegister = new JButton("ȸ������");
		bRegister.addActionListener(listener);
		
		bLogout = new JButton("�α׾ƿ�");
		bLogout.addActionListener(listener);
		
		vWelcome = new JLabel("�� ȯ���մϴ�.");
		vCenterName = new JLabel("�λ곲����ȣ����");
		bUserName = new JButton("�ǿ���");
		bUserName.addActionListener(listener);
		bUserName.setContentAreaFilled(false);
		bUserName.setFocusPainted(false);
		bUserName.setBorderPainted(false);
		bUserName.setForeground(Color.cyan);
		
		mBar = new JMenuBar();
		UIManager.put("Menu.font", new Font("�������", Font.BOLD, 20));

		mCenter = new JMenu("����");
		JMenuItem[] mlCenterItem = new JMenuItem[mlCenter.length];
		for (int i=0; i < mlCenter.length; i++){
			mlCenterItem[i] = new JMenuItem(mlCenter[i]);
			mlCenterItem[i].addActionListener(listener);
			mlCenterItem[i].setPreferredSize(new Dimension(218, 40));
			mlCenterItem[i].setFont(new Font("�������", Font.BOLD, 17));
			mCenter.add(mlCenterItem[i]);
		}
		mCenter.setPreferredSize(new Dimension(220, 50));
		mCenter.setBorder(new EmptyBorder(0,90,0,0));
		mBar.add(mCenter);
		
		//mBar.add(new JSeparator(JSeparator.VERTICAL));
		mBar.add(Box.createHorizontalStrut(10));
		
		mEmp = new JMenu("����");
		JMenuItem[] mlEmpItem = new JMenuItem[mlEmp.length];
		for (int i=0; i < mlEmp.length; i++){
			mlEmpItem[i] = new JMenuItem(mlEmp[i]);
			mlEmpItem[i].addActionListener(listener);
			mlEmpItem[i].setPreferredSize(new Dimension(218, 40));
			mlEmpItem[i].setFont(new Font("�������", Font.BOLD, 17));
			mEmp.add(mlEmpItem[i]);
		}
		mEmp.setPreferredSize(new Dimension(220, 50));
		mEmp.setBorder(new EmptyBorder(0,90,0,0));
		mBar.add(mEmp);
		mBar.add(Box.createHorizontalStrut(10));
		
		mAban = new JMenu("����");
		JMenuItem[] mlAbanItem = new JMenuItem[mlAban.length];
		for (int i=0; i < mlAban.length; i++){
			mlAbanItem[i] = new JMenuItem(mlAban[i]);
			mlAbanItem[i].addActionListener(listener);
			mlAbanItem[i].setPreferredSize(new Dimension(218, 40));
			mlAbanItem[i].setFont(new Font("�������", Font.BOLD, 17));
			mAban.add(mlAbanItem[i]);
		}
		mAban.setPreferredSize(new Dimension(220, 50));
		mAban.setBorder(new EmptyBorder(0,90,0,0));
		mBar.add(mAban);
		mBar.add(Box.createHorizontalStrut(10));
		
		mReport = new JMenu("�Ű�");
		JMenuItem[] mlReportItem = new JMenuItem[mlReport.length];
		for (int i=0; i < mlReport.length; i++){
			mlReportItem[i] = new JMenuItem(mlReport[i]);
			mlReportItem[i].addActionListener(listener);
			mlReportItem[i].setPreferredSize(new Dimension(218, 40));
			mlReportItem[i].setFont(new Font("�������", Font.BOLD, 17));
			mReport.add(mlReportItem[i]);
		}
		mReport.setPreferredSize(new Dimension(220, 50));
		mReport.setBorder(new EmptyBorder(0,90,0,0));
		mBar.add(mReport);
		mBar.add(Box.createHorizontalStrut(10));
		
		mAdop = new JMenu("�Ծ�");
		JMenuItem[] mlAdopItem = new JMenuItem[mlAdop.length];
		for (int i=0; i < mlAdop.length; i++){
			mlAdopItem[i] = new JMenuItem(mlAdop[i]);
			mlAdopItem[i].addActionListener(listener);
			mlAdopItem[i].setPreferredSize(new Dimension(218, 40));
			mlAdopItem[i].setFont(new Font("�������", Font.BOLD, 17));
			mAdop.add(mlAdopItem[i]);
		}
		mAdop.setPreferredSize(new Dimension(220, 50));
		mAdop.setBorder(new EmptyBorder(0,90,0,0));
		mBar.add(mAdop);
		mBar.add(Box.createHorizontalStrut(10));
		
		mPost = new JMenu("�Խ���");
		JMenuItem[] mlPostItem = new JMenuItem[mlPost.length];
		for (int i=0; i < mlPost.length; i++){
			mlPostItem[i] = new JMenuItem(mlPost[i]);
			mlPostItem[i].addActionListener(listener);
			mlPostItem[i].setPreferredSize(new Dimension(218, 40));
			mlPostItem[i].setFont(new Font("�������", Font.BOLD, 17));
			mPost.add(mlPostItem[i]);
		}
		mPost.setPreferredSize(new Dimension(220, 50));
		mPost.setBorder(new EmptyBorder(0,80,0,0));
		mBar.add(mPost);
		
		ContentPanel = new ContentPanel();
		pContents = new MainPage();
		//pContents.setBackground(Color.WHITE);
		ContentPanel.add(pContents);
		MainPageView();
	}
	private void MainPageView() {
		setTitle("ASMR");
		setExtendedState(MAXIMIZED_BOTH);
		//this.getContentPane().setBackground(Color.WHITE);
		
		bMainButton.setBounds(100,50,175,175);
		mBar.setBounds(310,110,1370,50);
		
		bLogin.setBounds(1700, 10, 100, 30);
		bRegister.setBounds(1810, 10, 100, 30);
		
		vCenterName.setBounds(1700,110,150,20);
		bUserName.setBounds(1700,125,75,30);
		vWelcome.setBounds(1770,130,150,20);
		bLogout.setBounds(1810, 10, 100, 30);
		
		ContentPanel.setBounds(50, 300, 1800, 900);
		
		this.add(bMainButton);
		this.add(mBar);
		this.add(ContentPanel);
		if(LoginState == 0) {
			this.add(bLogin);
			this.add(bRegister);
		}
		else if(LoginState == 1) {
			this.add(bLogout);
			this.add(vCenterName);
			this.add(bUserName);
			this.add(vWelcome);
		}
		
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
				ContentPanel.removeAll();
				pContents = new MainPage();
				ContentPanel.add(pContents);
				revalidate();
				repaint();
				break;
			case "�α���":
				ContentPanel.removeAll();
				pContents = new Login();
				ContentPanel.add(pContents);
				revalidate();
				repaint();
				break;
			case "ȸ������":
				ContentPanel.removeAll();
				pContents = new UserRegister();
				ContentPanel.add(pContents);
				revalidate();
				repaint();
				break;
			case "���͸��":
				ContentPanel.removeAll();
				pContents = new CenterList();
				ContentPanel.add(pContents);
				revalidate();
				repaint();
				break;
			case "�ǿ���":
				ContentPanel.removeAll();
				pContents = new EmpMyPage();
				ContentPanel.add(pContents);
				revalidate();
				repaint();
				break;
			case "�α׾ƿ�":
				new MainFrame();
				dispose();
				ContentPanel.removeAll();
				pContents = new MainPage();
				ContentPanel.add(pContents);
				revalidate();
				repaint();
				break;
			case "�������":
				ContentPanel.removeAll();
				try {
					pContents = new EmpRegister();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ContentPanel.add(pContents);
				revalidate();
				repaint();
				break;
				
			case "������ȸ":
				ContentPanel.removeAll();
				pContents = new EmpList();
				ContentPanel.add(pContents);
				revalidate();
				repaint();
				break;
			case "�������":
				ContentPanel.removeAll();
				pContents = new ProtAniRegist();
				ContentPanel.add(pContents);
				revalidate();
				repaint();
				break;
			case "��ȣ":
				ContentPanel.removeAll();
				try {
					pContents = new ProtAniList();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ContentPanel.add(pContents);
				revalidate();
				repaint();
				break;
			case "����":
				ContentPanel.removeAll();
				try {
					pContents = new DiagAniList();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ContentPanel.add(pContents);
				revalidate();
				repaint();
				break;
			case "�Ű���":
				ContentPanel.removeAll();
				try {
					pContents = new RprtRegist();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ContentPanel.add(pContents);
				revalidate();
				repaint();
				break;
			case "�Ű����(���μ���)":
				ContentPanel.removeAll();
				try {
					pContents = new RprtAssignment();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ContentPanel.add(pContents);
				revalidate();
				repaint();
				break;
			case "�Ű����(�Ϲݼ���)":
				ContentPanel.removeAll();
				try {
					pContents = new RprtAssignmentNorm();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ContentPanel.add(pContents);
				revalidate();
				repaint();
				break;
			case "������":
				ContentPanel.removeAll();
				pContents = new AnncList();
				ContentPanel.add(pContents);
				revalidate();
				repaint();
				break;
			case "��û���":
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
				break;
			case "�Ծ���":
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
				break;
			case "��������":
				ContentPanel.removeAll();
				pContents = new NotiBoard();
				ContentPanel.add(pContents);
				revalidate();
				repaint();
				break;
			case "����/�亯":
				ContentPanel.removeAll();
				pContents = new InqAnsBoard();
				ContentPanel.add(pContents);
				revalidate();
				repaint();
				break;
			}
		}
	}
	class ContentPanel extends JPanel {
		public void ContentPanel() {
			setPreferredSize(new Dimension(1800, 900));
			//setBackground(Color.WHITE);
		}
	}
	public void setLogin() {
		this.remove(bLogin);
		this.remove(bRegister);
		ContentPanel.removeAll();
		this.add(vCenterName);
		this.add(bUserName);
		this.add(vWelcome);
		revalidate();
		repaint();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainFrame main = new MainFrame();
	}

}
