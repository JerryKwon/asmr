package asmr;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;


public class MainFrame extends JFrame {

	private JButton bMainButton, bLogin, bRegister, bLogout, bUserName;
	private JLabel vCenterName, vWelcome;
	
	private JMenuBar mBar;
	private JMenu mCenter, mEmp, mAban, mReport, mAdop, mPost, mReportManage, mAdopManage;
	private JMenuItem mReportAni, mAdopAnnc;
	
	private String[] mlCenter = {"���͸��"};
	private String[] mlEmp = {"������ȸ"};
	private String[] mlAban = {"�⺻��������", "����"};
	private String[] mlReport = {"�Ű����(���μ���)", "�Ű����(�Ϲݼ���)"};
	private String[] mlAdop = {"�Ծ��û����", "�Ծ�Ϸ���"};
	private String[] mlPost = {"��������", "����/�亯"};
	
	private static JPanel pContents;
	private static ContentPanel ContentPanel;	
	
	Login login;
	public static String empName, empID, empCntr;
	
	public MainFrame() {
		login = new Login();
		login.setMain(this);
		
		empID = login.empID;
		empName = EmpData.getEmpName(Integer.parseInt(login.empID));
		empCntr = EmpData.getEmpCntr(Integer.parseInt(login.empID));
		
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
		vCenterName = new JLabel(" ");
		bUserName = new JButton(" ");
		bUserName.addActionListener(listener);
		bUserName.setContentAreaFilled(false);
		bUserName.setFocusPainted(false);
		bUserName.setBorderPainted(false);
		bUserName.setForeground(new Color(0, 180, 255));
		
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
		
		mAban = new JMenu("���⵿��");
		JMenuItem[] mlAbanItem = new JMenuItem[mlAban.length];
		for (int i=0; i < mlAban.length; i++){
			mlAbanItem[i] = new JMenuItem(mlAban[i]);
			mlAbanItem[i].addActionListener(listener);
			mlAbanItem[i].setPreferredSize(new Dimension(218, 40));
			mlAbanItem[i].setFont(new Font("�������", Font.BOLD, 17));
			mAban.add(mlAbanItem[i]);
		}
		mAban.setPreferredSize(new Dimension(220, 50));
		mAban.setBorder(new EmptyBorder(0,65,0,0));
		mBar.add(mAban);
		mBar.add(Box.createHorizontalStrut(10));
		
		mReport = new JMenu("�Ű�");
		mReportAni = new JMenuItem("���⵿���Ű�");
		mReportAni.addActionListener(listener);
		mReportAni.setPreferredSize(new Dimension(218, 40));
		mReportAni.setFont(new Font("�������", Font.BOLD, 17));
		mReport.add(mReportAni);
		
		mReportManage = new JMenu("�Ű���������");
		mReportManage.setPreferredSize(new Dimension(218, 40));
		mReportManage.setFont(new Font("�������", Font.BOLD, 17));
		JMenuItem[] mlReportItem = new JMenuItem[mlReport.length];
		for (int i=0; i < mlReport.length; i++){
			mlReportItem[i] = new JMenuItem(mlReport[i]);
			mlReportItem[i].addActionListener(listener);
			mlReportItem[i].setPreferredSize(new Dimension(218, 40));
			mlReportItem[i].setFont(new Font("�������", Font.BOLD, 17));
			mReportManage.add(mlReportItem[i]);
		}
		mReport.setPreferredSize(new Dimension(220, 50));
		mReport.setBorder(new EmptyBorder(0,90,0,0));
		
		
		mBar.add(mReport);
		mReport.add(mReportManage);
		mBar.add(Box.createHorizontalStrut(10));
		
		
		mAdop = new JMenu("�Ծ�");
		mAdopAnnc = new JMenuItem("���⵿������");
		mAdopAnnc.addActionListener(listener);
		mAdopAnnc.setPreferredSize(new Dimension(218, 40));
		mAdopAnnc.setFont(new Font("�������", Font.BOLD, 17));
		mAdop.add(mAdopAnnc);
		mAdopManage = new JMenu("�Ծ����");
		mAdopManage.setPreferredSize(new Dimension(218, 40));
		mAdopManage.setFont(new Font("�������", Font.BOLD, 17));
		
		JMenuItem[] mlAdopItem = new JMenuItem[mlAdop.length];
		for (int i=0; i < mlAdop.length; i++){
			mlAdopItem[i] = new JMenuItem(mlAdop[i]);
			mlAdopItem[i].addActionListener(listener);
			mlAdopItem[i].setPreferredSize(new Dimension(218, 40));
			mlAdopItem[i].setFont(new Font("�������", Font.BOLD, 17));
			mAdopManage.add(mlAdopItem[i]);
		}
		mAdop.add(mAdopManage);
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
		
		ContentPanel.setBounds(50, 250, 1800, 1100);
		
		this.add(bMainButton);
		this.add(mBar);
		this.add(ContentPanel);
		
		// ��α��� ��Ʈ
		this.add(bLogin);
		this.add(bRegister);
		
		// �α��� ��Ʈ
		this.add(bLogout);
		this.add(vCenterName);
		this.add(bUserName);
		this.add(vWelcome);
		
		// ��ư ���̱� ����
		bLogout.setVisible(false);
		vCenterName.setVisible(false);
		bUserName.setVisible(false);
		vWelcome.setVisible(false);
		
		// �޴� ���� ���ɿ���
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
		// ��ư ���̱� ����
		bLogin.setVisible(true);
		bRegister.setVisible(true);
		bLogout.setVisible(false);
		vCenterName.setVisible(false);
		bUserName.setVisible(false);
		vWelcome.setVisible(false);
		
		// �޴� ���� ���ɿ���
		mCenter.setEnabled(false);
		mEmp.setEnabled(false);
		mAban.setEnabled(false);
		mReportManage.setEnabled(false);
		mAdopManage.setEnabled(false);
	}
	public void setLogin() {
		
		// ��ư ���̱� ����
		bLogin.setVisible(false);
		bRegister.setVisible(false);
		
		bLogout.setVisible(true);
		vWelcome.setVisible(true);
		empName = EmpData.getEmpName(Integer.parseInt(login.empID));
		bUserName.setText(empName);
		bUserName.setVisible(true);
		
		empCntr = EmpData.getEmpCntr(Integer.parseInt(login.empID));
		vCenterName.setText(empCntr);
		vCenterName.setVisible(true);
		
		// �޴� ���� ���ɿ���
		mCenter.setEnabled(true);
		mEmp.setEnabled(true);
		mAban.setEnabled(true);
		mReportManage.setEnabled(true);
		mAdopManage.setEnabled(true);
		
		//���� ������ �ε�
		ContentPanel.removeAll();
		pContents = new MainPage();
		ContentPanel.add(pContents);
		ContentPanel.revalidate();
		ContentPanel.repaint();
	}
	
	class MenuActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String cont = e.getActionCommand();
			if(cont == " "){
				ContentPanel.removeAll();
				pContents = new MainPage();
				ContentPanel.add(pContents);
				revalidate();
				repaint();
			}else if(cont == "�α���"){
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
			}else if(cont == "ȸ������"){
				ContentPanel.removeAll();
				pContents = new UserRegister();
				ContentPanel.add(pContents);
				revalidate();
				repaint();
			}else if(cont == "���͸��"){
				ContentPanel.removeAll();
				pContents = new CenterList();
				ContentPanel.add(pContents);
				revalidate();
				repaint();
			}else if(cont == "�α׾ƿ�"){
				setLogout();
			}else if(cont == "������ȸ"){
				ContentPanel.removeAll();
				pContents = new EmpList();
				ContentPanel.add(pContents);
				revalidate();
				repaint();
			}else if(cont == "�⺻��������"){
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
			}else if(cont == "����"){
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
			}else if(cont == "���⵿���Ű�"){
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
			}else if(cont == "�Ű����(���μ���)"){
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
			}else if(cont == "�Ű����(�Ϲݼ���)"){
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
			}else if(cont == "���⵿������"){
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
			}else if(cont == "�Ծ��û����"){
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
			}else if(cont == "�Ծ�Ϸ���"){
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
			}else if(cont == "��������"){
				ContentPanel.removeAll();
				pContents = new NotiBoard();
				ContentPanel.add(pContents);
				revalidate();
				repaint();
			}else if(cont == "����/�亯"){
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
