package asmr;

import javax.swing.*;
import java.awt.*;


public class SignedInHeader extends JPanel {
	
	private JLabel vCenterName, vWelcome;

	private JButton bMainButton, bLogout, bUserName;
	
	private JMenuBar mBar;
	private JMenu mCenter, mEmp, mAban, mReport, mAdop, mPost;
	
	private String[] mlCenter = {"���͸��"};
	private String[] mlEmp = {"�������", "������ȸ"};
	private String[] mlAban = {"��ȣ", "����"};
	private String[] mlReport = {"�Ű���", "�Ű����(���μ���)", "�Ű����(�Ϲݼ���)"};
	private String[] mlAdop = {"������", "��û���", "�Ծ���"};
	private String[] mlPost = {"��������", "����/�亯"};
	
	private JPanel pMain;
	
	GridBagLayout gridbaglayout;				// ȭ���� �����ϴ� ���̾ƿ�
	GridBagConstraints gridbagconstraints;	
	
	public SignedInHeader() {
				
		gridbaglayout = new GridBagLayout();		
		gridbagconstraints = new GridBagConstraints();
		
		bMainButton = new JButton(new ImageIcon("images/main.png"));
		bMainButton.setContentAreaFilled(false);
		bMainButton.setFocusPainted(false);
		bMainButton.setBorderPainted(false);
		
		bLogout = new JButton("�α׾ƿ�");
		
		bUserName = new JButton("�ǿ���");
		bUserName.setContentAreaFilled(false);
		bUserName.setFocusPainted(false);
		bUserName.setBorderPainted(false);
		bUserName.setForeground(Color.cyan);
		
		vCenterName = new JLabel("�λ곲����ȣ����");
		vWelcome = new JLabel("�� ȯ���մϴ�");
		
		mBar = new JMenuBar();

		mCenter = new JMenu("����");
		JMenuItem[] mlCenterItem = new JMenuItem[mlCenter.length];
		for (int i=0; i < mlCenter.length; i++){
			mlCenterItem[i] = new JMenuItem(mlCenter[i]);
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
	      //���� ���� �� gridx, gridy���� 0 			
				
		gridbagconstraints.gridwidth  = w;	//����	
		gridbagconstraints.gridheight = h;	//����	
	     			
	      			
	    gridbaglayout.setConstraints(c, gridbagconstraints); //������Ʈ�� ������Ʈ ��ġ+ũ�� ������ ���� GridBagLayout�� ��ġ			
				
	   add(c);			
				
	   }
	public static void main(String[] args) {
		// TODO Auto-generated method stub		
		
	}

}
