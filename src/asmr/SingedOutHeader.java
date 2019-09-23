package asmr;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class SingedOutHeader extends JFrame {

	private JButton bMainButton, bLogin, bRegister;
	
	private JMenuBar mBar;
	private JMenu mCenter, mEmp, mAban, mReport, mAdop, mPost;
	
	private String[] mlCenter = {"���͸��"};
	private String[] mlEmp = {"�������", "������ȸ"};
	private String[] mlAban = {"�������", "��ȣ", "����"};
	private String[] mlReport = {"�Ű���", "�Ű����(���μ���)", "�Ű����(�Ϲݼ���)"};
	private String[] mlAdop = {"������", "��û���", "�Ծ���"};
	private String[] mlPost = {"��������", "����/�亯"};
	
	private JPanel pContents;
	
	GridBagLayout gridbaglayout;				// ȭ���� �����ϴ� ���̾ƿ�
	GridBagConstraints gridbagconstraints;	
	
	public SingedOutHeader() {
				
		gridbaglayout = new GridBagLayout();		
		gridbagconstraints = new GridBagConstraints();
		
		MenuActionListener listener = new MenuActionListener();
		
		bMainButton = new JButton(" ", new ImageIcon("images/main.png"));
		bMainButton.setContentAreaFilled(false);
		bMainButton.setFocusPainted(false);
		bMainButton.setBorderPainted(false);
		bMainButton.addActionListener(listener);
		
		bLogin = new JButton("�α���");
		bLogin.addActionListener(listener);
		bRegister = new JButton("ȸ������");
		
		mBar = new JMenuBar();
		

		mCenter = new JMenu("����");
		JMenuItem[] mlCenterItem = new JMenuItem[mlCenter.length];
		for (int i=0; i < mlCenter.length; i++){
			mlCenterItem[i] = new JMenuItem(mlCenter[i]);
			mlCenterItem[i].addActionListener(listener);
			mCenter.add(mlCenterItem[i]);
		}
		mBar.add(mCenter);
		
		mEmp = new JMenu("����");
		JMenuItem[] mlEmpItem = new JMenuItem[mlEmp.length];
		for (int i=0; i < mlEmp.length; i++){
			mlEmpItem[i] = new JMenuItem(mlEmp[i]);
			mEmp.add(mlEmpItem[i]);
		}
		mBar.add(mEmp);
		
		mAban = new JMenu("����");
		JMenuItem[] mlAbanItem = new JMenuItem[mlAban.length];
		for (int i=0; i < mlAban.length; i++){
			mlAbanItem[i] = new JMenuItem(mlAban[i]);
			mAban.add(mlAbanItem[i]);
		}
		mBar.add(mAban);
		
		mReport = new JMenu("�Ű�");
		JMenuItem[] mlReportItem = new JMenuItem[mlReport.length];
		for (int i=0; i < mlReport.length; i++){
			mlReportItem[i] = new JMenuItem(mlReport[i]);
			mReport.add(mlReportItem[i]);
		}
		mBar.add(mReport);
		
		mAdop = new JMenu("�Ծ�");
		JMenuItem[] mlAdopItem = new JMenuItem[mlAdop.length];
		for (int i=0; i < mlAdop.length; i++){
			mlAdopItem[i] = new JMenuItem(mlAdop[i]);
			mAdop.add(mlAdopItem[i]);
		}
		mBar.add(mAdop);
		
		mPost = new JMenu("�Խ���");
		JMenuItem[] mlPostItem = new JMenuItem[mlPost.length];
		for (int i=0; i < mlPost.length; i++){
			mlPostItem[i] = new JMenuItem(mlPost[i]);
			mPost.add(mlPostItem[i]);
		}
		mBar.add(mPost);
		pContents = new Login();
		
		MainPageView();
	}
	private void MainPageView() {
		setTitle("ASMR");
		setExtendedState(MAXIMIZED_BOTH);
		
		gridbagconstraints.anchor = GridBagConstraints.WEST;		
		gridbagconstraints.ipadx = 7;		
				
		gridbagconstraints.weightx=1.0;		
		gridbagconstraints.weighty=1.0;		
				
		setLayout(gridbaglayout);
		gridbagAdd(bMainButton, 0, 0, 1, 3, 0.1, 0.1);
		gridbagAdd(mBar, 1, 1, 1, 1, 0.1, 0.1);
		gridbagAdd(bLogin, 7, 0, 1, 1, 0.1, 0.1);
		gridbagAdd(bRegister, 8, 0, 1, 1, 0.1, 0.1);
		gridbagAdd(pContents, 1, 2, 1, 1, 1.0, 1.0);
		
		
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
				gridbagAdd(pContents, 1, 2, 10, 3, 1.0, 1.0);
				revalidate();
				repaint();
				break;
			case "�α���":
				getContentPane().remove(pContents);
				pContents = new Login();
				gridbagAdd(pContents, 1, 2, 10, 3, 1.0, 1.0);
				revalidate();
				repaint();
			}
		}
	}

	private void gridbagAdd(Component c, int x, int y, int w, int h, double wx, double wy) {			
		
		gridbagconstraints.gridx = x;		
		gridbagconstraints.gridy = y; 		
	      //���� ���� �� gridx, gridy���� 0 			
				
		gridbagconstraints.gridwidth  = w;	//����	
		gridbagconstraints.gridheight = h;	//����
		
		gridbagconstraints.weightx = wx;
		gridbagconstraints.weighty = wy;
	     			
	      			
	    gridbaglayout.setConstraints(c, gridbagconstraints); //������Ʈ�� ������Ʈ ��ġ+ũ�� ������ ���� GridBagLayout�� ��ġ			
				
	   add(c);			
				
	   }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new SingedOutHeader();
	}

}
