package asmr;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class CenterList extends JPanel{
	
	private JButton centerRegist, cageRegist, searchManager, modify, cancel;
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
	
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	
//  �����ʵ�
	CenterListButtonListener centerListButtonListener;
	CenterListMouseListener centerListMouseListener;

	
	//CenterList ������
	public CenterList(){
		gridBagLayout = new GridBagLayout();
		gridBagConstraints = new GridBagConstraints();
		
		centerListButtonListener = new CenterListButtonListener();
		centerListMouseListener = new CenterListMouseListener();

		
		//���͸��, ���������, �������� �ؽ�Ʈ
		vCenterList = new JLabel("���͸��");
		vCenterList.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
//		eCenterList = new JTable(model1);
		eCenterList = new JTable(model1) {
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
		
		eCenterList.addMouseListener(centerListMouseListener);
		scrollpane1 = new JScrollPane(eCenterList);
		scrollpane1.setPreferredSize(new Dimension(600,200));
		
		//���������
		vCageList = new JLabel("���������");
		vCageList.setBorder(BorderFactory.createEmptyBorder(0, 20, 10, 0));
//		eCageList = new JTable(model2);
		eCageList = new JTable(model2) {
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };

		scrollpane2 = new JScrollPane(eCageList);
		scrollpane2.setPreferredSize(new Dimension(300,200));
		
		vCenterInfo = new JLabel("��������");
		vCenterInfo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		
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
		xCenterName.setEnabled(false);
		
		//��ȭ��ȣ �ϳ��� ���ڿ��� ����
		vPhoneNum = new JLabel("��ȭ��ȣ");
		xPhoneNum = new JTextField(10);
		xPhoneNum.setEnabled(false);
		
		//����
		vArea = new JLabel("����");
		xArea = new JTextField(10);
		xArea.setEnabled(false);
		
		//��ð�
		vOperTime = new JLabel("��ð�");
		vOperTimeDash = new JLabel("~");
		cbOperTimeOpen = new JComboBox<String>(operTimeOpen);
		cbOperTimeOpen.setEnabled(false);
		cbOperTimeClose = new JComboBox<String>(operTimeClose);
		cbOperTimeClose.setEnabled(false);
		
		//������ �̸�(������ - VARCHAR(20)) - �ѱ� 1���ڴ� 3byte 6.667����
		vCenterManager = new JLabel("������");
		xCenterManager = new JTextField(10);
		xCenterManager.setEnabled(false);
		
		//�� �������� ��
		vCageNum = new JLabel("�� ������ ��");
		vCageNum.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
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
		centerRegist.setBackground(blue);
		centerRegist.setForeground(white);
		centerRegist.addActionListener(centerListButtonListener);
		
		cageRegist = new JButton("���");
		cageRegist.setBackground(blue);
		cageRegist.setForeground(white);
		cageRegist.addActionListener(centerListButtonListener);
		
		searchManager = new JButton("�˻�");
		searchManager.setBackground(blue);
		searchManager.setForeground(white);
		searchManager.setEnabled(false);
		searchManager.addActionListener(centerListButtonListener);
		
		modify = new JButton("����");
		modify.setBackground(blue);
		modify.setForeground(white);
		modify.addActionListener(centerListButtonListener);
		
		cancel = new JButton("���");
		cancel.addActionListener(centerListButtonListener);
		
		JComponent[] vTitleComps = {vCenterList,vCageList};
		ChangeFont(vTitleComps, new Font("�������", Font.BOLD, 24));
		
		vCenterInfo.setFont(new Font("�������", Font.BOLD, 20));
		
		JComponent[] vContextComps	= {vCenterNum,vEstDate,vCenterName,vPhoneNum,vArea,vOperTime,vOperTimeDash,vCenterManager,vCageNum,vCageBig,vCageMid,vCageSmall,vCageBigCount,vCageMidCount,vCageSmallCount};
		ChangeFont(vContextComps, new Font("�������", Font.PLAIN, 16));
		
		JComponent[] bComps = {centerRegist, cageRegist, searchManager, modify, cancel};
		ChangeFont(vContextComps, new Font("�������", Font.BOLD, 16));
		
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
		gridbagAdd(centerRegist, 4, 0, 1, 1);
		
		//�������� ��Ϲ�ư
		gridbagAdd(vCageList, 5, 0, 1, 1);
		JPanel plainPanel2 = new JPanel();
		plainPanel2.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
		plainPanel2.add(cageRegist);
		plainPanel2.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 0));
		gridbagAdd(plainPanel2, 9, 0, 1, 1);
		
		//���͸�����̺�, ������������̺�
		gridbagAdd(scrollpane1, 0, 1, 5, 5);
		JPanel plainPanel = new JPanel();
		plainPanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
		plainPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		plainPanel.add(scrollpane2);
		gridbagAdd(plainPanel, 5, 1, 5, 5);
		
		//��������
		gridbagAdd(vCenterInfo, 0, 6, 1, 1);
		
		//���͹�ȣ
		gridbagAdd(vCenterNum, 0, 7, 1, 1);
		gridbagAdd(xCenterNum, 1, 7, 1, 1);
	
		//��������
		gridbagAdd(vEstDate, 2, 7, 1, 1);
		gridbagAdd(xEstDate, 3, 7, 1, 1);
		
		//���͸�
		gridbagAdd(vCenterName,0, 8,1,1);
		gridbagAdd(xCenterName, 1, 8, 1, 1);
		
		//��ȭ��ȣ
		gridbagAdd(vPhoneNum, 2, 8, 1, 1);
		gridbagAdd(xPhoneNum, 3, 8, 1, 1);
		
		//����
		gridbagAdd(vArea, 0, 9, 1, 1);
		gridbagAdd(xArea, 1, 9, 1, 1);
		
		//��ð�
		Component[] cops = {cbOperTimeOpen, vOperTimeDash,cbOperTimeClose};
		CombinePanel operTimePanel = new CombinePanel(cops,0,0);
		
		gridbagAdd(vOperTime, 2, 9, 1, 1);
		gridbagAdd(operTimePanel, 3, 9, 1, 1);
		
//		gridbagAdd(cbOperTimeOpen, 12, 9, 1, 1);
//		gridbagAdd(vOperTimeDash, 13, 9, 1, 1);
//		gridbagAdd(cbOperTimeClose, 14, 9, 1, 1);
		
		//������
		gridbagAdd(vCenterManager, 0, 10, 1, 1);
		JComponent[] centerManagerComps = {xCenterManager, searchManager};
		CombinePanel centerManagerPanel = new CombinePanel(centerManagerComps, 0, 0);
		gridbagAdd(centerManagerPanel, 1, 10, 1, 1);
		
//		gridbagAdd(xCenterManager, 1, 10, 1, 1);
//		gridbagAdd(searchManager, 2, 10, 1, 1);
		
		//�� ������ ��
		gridbagAdd(vCageNum, 0, 11, 1, 1);
		
		//���º� ������ ������ ���� Bottom Panel �� ��ġ
		BottomPanel bottomPanel = new BottomPanel();
		gridbagAdd(bottomPanel, 0, 12, 2, 1);
		
		//�����ư ��ġ
		JComponent[] bComps = {modify,cancel};
		CombinePanel buttonPanel = new CombinePanel(bComps, 10, 0);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(10,325,0,0));
		gridbagAdd(buttonPanel, 0, 13, 21, 1);
		
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
	
	class CenterListButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource().equals(centerRegist)) {
				try {
					new NewCenterRegistration();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			if(e.getSource().equals(cageRegist)) {
				new CageRegister();
			}
			if(e.getSource().equals(searchManager)) {
				new CenterManagerSearch();
			}
			if(e.getSource().equals(modify)) {
				modify.setText("Ȯ��");
				JComponent[] changeStatusComps = {xCenterName,xPhoneNum,xPhoneNum,xArea,cbOperTimeOpen,cbOperTimeClose,searchManager};
				for(JComponent cop: changeStatusComps) {
					cop.setEnabled(true);
				}
			}

			if(e.getSource().equals(cancel)) {
				modify.setText("����");
				JComponent[] changeStatusComps = {xCenterName,xPhoneNum,xPhoneNum,xArea,cbOperTimeOpen,cbOperTimeClose,searchManager};
				for(JComponent cop: changeStatusComps) {
					cop.setEnabled(false);
				}
			}
			
		}
		
	}
	
//  ���͸�����̺� Ŭ���� �߻��ϴ� ������
	class CenterListMouseListener extends MouseAdapter{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			super.mouseClicked(e);
			
//			https://blaseed.tistory.com/18			
			//1:��Ŭ��, 3:��Ŭ��
			if(e.getButton() == 1) {
				
			}
		}
	}

	
    private void ChangeFont(JComponent[] comps, Font font) {
    	for(JComponent comp: comps) {
    		comp.setFont(font);
    	}
    }
    
	
	public static void main(String[] args) {
		new CenterList();
	}
}


