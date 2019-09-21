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
	
	//���̺�� ��ũ�� �г�
	private JTable eCenterList, eCageList;
	private JScrollPane scrollpane1, scrollpane2;
	
	//�ش� Ŭ������ ���� ���̾ƿ��� GridBag 
	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;
	
	//���̺��� �÷� �迭
	private final String[] col1 = {"���͸�","�ּ�","��ð�"};
	private final String[] col2 = {"������ ����","ũ��"};
	
	//���̺� �÷� ���� - ���͸��
	private DefaultTableModel model1 = new DefaultTableModel(col1,0);
	//���̺� �÷� ���� - ������
	private DefaultTableModel model2 = new DefaultTableModel(col2,0);
	
	// private final String[] phoneNumStart = {"010","02","031","032","033","041","042","043","044","051","052","053","054","055","061","062","063","064"};
	private final String[] operTimeOpen = {"08:00","08:30","09:00","09:30","10:00","10:30","11:00"};
	private final String[] operTimeClose = {"16:00","16:30","17:00","17:30","18:00","18:30","19:00"};
	
//	CenterListButtonListener centerListButtonListener;
	
	//CenterList ������
	public CenterList(){
		gridBagLayout = new GridBagLayout();
		gridBagConstraints = new GridBagConstraints();
		
//		centerListButtonListener = new CenterListButtonListener();
		
		//���͸��, ���������, �������� �ؽ�Ʈ
		vCenterList = new JLabel("���͸��");
		eCenterList = new JTable(model1);
		scrollpane1 = new JScrollPane(eCenterList);
		scrollpane1.setPreferredSize(new Dimension(400,100));
		
		//���������
		vCageList = new JLabel("���������");
		eCageList = new JTable(model2);
		scrollpane2 = new JScrollPane(eCageList);
		scrollpane2.setPreferredSize(new Dimension(300,100));
		
		vCenterInfo = new JLabel("��������");
		
		//�������� �Ʒ� �Է�â JLabel�� JTextField
		vCenterNum = new JLabel("���͹�ȣ");
		xCenterNum = new JTextField(10);
		xCenterNum.setEnabled(false);
		
		//��¥ ���� 2001.01.20[10����]
		vEstDate = new JLabel("��������");
		xEstDate = new JTextField(10);
		xEstDate.setEnabled(false);
		
		//���͸�
		vCenterName = new JLabel("���͸�");
		xCenterName = new JTextField(10);
		
		//��ȭ��ȣ �ϳ��� ���ڿ��� ����
		vPhoneNum = new JLabel("��ȭ��ȣ");
		xPhoneNum = new JTextField(10);
		
		//����
		vArea = new JLabel("����");
		xArea = new JTextField(10);
		
		//��ð�
		vOperTime = new JLabel("��ð�");
		vOperTimeDash = new JLabel("~");
		cbOperTimeOpen = new JComboBox<String>(operTimeOpen);
		cbOperTimeClose = new JComboBox<String>(operTimeClose);
		
		//������ �̸�(������ - VARCHAR(20)) - �ѱ� 1���ڴ� 3byte 6.667����
		vCenterManager = new JLabel("������");
		xCenterManager = new JTextField(10);
		xCenterManager.setEnabled(false);
		
		//�� �������� ��
		vCageNum = new JLabel("�� ������ ��");
		
		//������
		vCageBig = new JLabel("����");
		xCageBig = new JTextField(2);
		xCageBig.setEnabled(false);
		
		vCageMid = new JLabel("����");
		xCageMid = new JTextField(2);
		xCageMid.setEnabled(false);
		
		vCageSmall = new JLabel("����");
		xCageSmall = new JTextField(2);
		xCageSmall.setEnabled(false);
		
		vCageBigCount = new JLabel("��");
		vCageMidCount = new JLabel("��");
		vCageSmallCount = new JLabel("��");
		
		//��ư
		centerRegist = new JButton("���");
//		centerRegist.addActionListener(centerListButtonListener);
		
		cageRegist = new JButton("���");
//		cageRegist.addActionListener(centerListButtonListener);
		
		searchManager = new JButton("�˻�");
		
		save = new JButton("����");
//		save.addActionListener(centerListButtonListener);
		
		
		CenterListView();
	}

	//Component ��ġ
	private void CenterListView() {
		setLayout(gridBagLayout);
		
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.ipadx = 7;
		
		gridBagConstraints.weightx=1.0;
		gridBagConstraints.weighty=1.0;
		
		//���͸�ϰ� ��Ϲ�ư
		gridbagAdd(vCenterList, 0, 0, 1, 1);
		gridbagAdd(centerRegist, 9, 0, 1, 1);
		
		//�������� ��Ϲ�ư
		gridbagAdd(vCageList, 11, 0, 1, 1);
		gridbagAdd(cageRegist, 20, 0, 1, 1);
		
		//���͸�����̺�, ������������̺�
		gridbagAdd(scrollpane1, 0, 1, 10, 5);
		gridbagAdd(scrollpane2, 11, 1, 10, 5);
		
		//��������
		gridbagAdd(vCenterInfo, 0, 6, 1, 1);
		
		//���͹�ȣ
		gridbagAdd(vCenterNum, 0, 7, 1, 1);
		gridbagAdd(xCenterNum, 1, 7, 1, 1);
	
		//��������
		gridbagAdd(vEstDate, 11, 7, 1, 1);
		gridbagAdd(xEstDate, 12, 7, 1, 1);
		
		//���͸�
		gridbagAdd(vCenterName,0, 8,1,1);
		gridbagAdd(xCenterName, 1, 8, 1, 1);
		
		//��ȭ��ȣ
		gridbagAdd(vPhoneNum, 11, 8, 1, 1);
		gridbagAdd(xPhoneNum, 12, 8, 1, 1);
		
		//����
		gridbagAdd(vArea, 0, 9, 1, 1);
		gridbagAdd(xArea, 1, 9, 1, 1);
		
		//��ð�
		Component[] cops = {cbOperTimeOpen, vOperTimeDash,cbOperTimeClose};
		CombinePanel operTimePanel = new CombinePanel(cops,0,0);
		
		gridbagAdd(vOperTime, 11, 9, 1, 1);
		gridbagAdd(operTimePanel, 12, 9, 1, 1);
		
//		gridbagAdd(cbOperTimeOpen, 12, 9, 1, 1);
//		gridbagAdd(vOperTimeDash, 13, 9, 1, 1);
//		gridbagAdd(cbOperTimeClose, 14, 9, 1, 1);
		
		//������
		gridbagAdd(vCenterManager, 0, 10, 1, 1);
		gridbagAdd(xCenterManager, 1, 10, 1, 1);
		gridbagAdd(searchManager, 2, 10, 1, 1);
		
		//�� ������ ��
		gridbagAdd(vCageNum, 0, 11, 1, 1);
		
		//���º� ������ ������ ���� Bottom Panel �� ��ġ
		BottomPanel bottomPanel = new BottomPanel();
		gridbagAdd(bottomPanel, 0, 12, 2, 1);
		
		//�����ư ��ġ
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
	
	//������ ���º� ������ ������ ���� Bottom Panel ����
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
		//������Ʈ 1, ������Ʈ 2, �г� ������ ��,�� margin ������ ���ֱ� ���� Flag
		public CombinePanel(Component[] cops, int borderWidth, int borderHeight) {
			//Margin�� �ʿ����� ���� ��
			
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
//				System.out.println("���͵�Ϲ�ư �׽�Ʈ");
//			}
//			
//			if(e.getSource().equals(cageRegist)) {
//				System.out.println("��������Ϲ�ư �׽�Ʈ");
//			}
//			
//			if(e.getSource().equals(save)) {
//				System.out.println("�����Ϲ�ư �׽�Ʈ");
//			}
//		}
//		
//	}
//	
	public static void main(String[] args) {
		new CenterList();
	}
}


