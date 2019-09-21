package asmr;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import asmr.CenterList.BottomPanel;

public class NewCenterRegistration extends JFrame{
	private JLabel vNewCenterRegist,vCenterName,vCenterType,vArea,vPhoneNum,vEstDate,vOperTime,vOperTimeDash,vCenterManager,vAddress,vCageNum,vCageBig,vCageMid,vCageSmall,vCageBigCount,vCageMidCount,vCageSmallCount;
	private JTextField xCenterName,xArea,xPhoneNum,xEstDate,xCenterManager,xAddress,xCageBig,xCageMid,xCageSmall;
	private JButton centerManagerSearch,addressSearch, save, imageButton;
	private JComboBox<String> cbCenterType,cbOperTimeOpen,cbOperTimeClose;
	private BufferedImage buttonIcon;
	
	
	private final String[] centerTypeDiv = {"����","�Ϲ�"};
	private final String[] operTimeOpenDiv = {"08:00","08:30","09:00","09:30","10:00","10:30","11:00"};
	private final String[] operTimeCloseDiv = {"16:00","16:30","17:00","17:30","18:00","18:30","19:00"};
	
	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;
	
	public NewCenterRegistration() throws IOException {
		//���̾ƿ� ����
		gridBagLayout = new GridBagLayout();
		gridBagConstraints = new GridBagConstraints();
		
		//ȭ���
		vNewCenterRegist = new JLabel("�űԼ��͵��");
		
		//���͸�
		vCenterName = new JLabel("���͸�");
		xCenterName = new JTextField(10);
		
		//���ͱ���
		vCenterType = new JLabel("���ͱ���");
		cbCenterType = new JComboBox<String>(centerTypeDiv);
		
		//����
		vArea = new JLabel("����");
		xArea = new JTextField(10);
		
		//��ȭ��ȣ
		vPhoneNum = new JLabel("��ȭ��ȣ");
		xPhoneNum = new JTextField(10);
		
		//��������
		vEstDate = new JLabel("��������");
		xEstDate = new JTextField(10); 
		xEstDate.setEnabled(false);
		buttonIcon = ImageIO.read(new File("images/cal1.png"));
		imageButton = new JButton(new ImageIcon(buttonIcon));
		imageButton.setBorderPainted(false);
		imageButton.setContentAreaFilled(false);
		imageButton.setFocusPainted(false);
		
		//��ð�
		vOperTime = new JLabel("��ð�");
		cbOperTimeOpen = new JComboBox<String>(operTimeOpenDiv);
		vOperTimeDash = new JLabel("~");
		cbOperTimeClose = new JComboBox<String>(operTimeCloseDiv);
		
		vCenterManager = new JLabel("������");
		xCenterManager = new JTextField(10);
		xCenterManager.setEnabled(false);
		centerManagerSearch = new JButton("�˻�");
		
		//�ּ�
		vAddress = new JLabel("�ּ�");
		xAddress = new JTextField(20);
		xAddress.setEnabled(false);
		addressSearch = new JButton("�˻�");
		
		//�� ������ ��
		vCageNum = new JLabel("�� ������ ��");
		
		//����
		vCageBig = new JLabel("����");
		xCageBig = new JTextField(2);
		vCageBigCount = new JLabel("��");
		
		//����
		vCageMid = new JLabel("����");
		xCageMid = new JTextField(2);
		vCageMidCount = new JLabel("��");
		
		//����
		vCageSmall = new JLabel("����");
		xCageSmall = new JTextField(2);
		vCageSmallCount = new JLabel("��");
		
		//�����ư
		save = new JButton("����");
		
		//��ġ�Լ�
		NewCenterRegistrationView();
	}

	private void NewCenterRegistrationView() {
		setLayout(gridBagLayout);
		
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.ipadx = 7;
		
		gridBagConstraints.weightx=1.0;
		gridBagConstraints.weighty=1.0;
		
		//ȭ������
		gridbagAdd(vNewCenterRegist, 0, 0, 1, 1);
		
		//���͸�
		gridbagAdd(vCenterName, 0, 1, 1, 1);
		gridbagAdd(xCenterName, 1, 1, 1, 1);
		
		//���ͱ���
		gridbagAdd(vCenterType, 11, 1, 1, 1);
		gridbagAdd(cbCenterType, 12, 1, 1, 1);
		
		//����
		gridbagAdd(vArea, 0, 2, 1, 1);
		gridbagAdd(xArea, 1, 2, 1, 1);
		
		//��ȭ��ȣ
		gridbagAdd(vPhoneNum, 11, 2, 1, 1);
		gridbagAdd(xPhoneNum, 12, 2, 1, 1);
		
		//��������
		gridbagAdd(vEstDate, 0, 3, 1, 1);
		JPanel plainPanel1 = new JPanel();
		plainPanel1.setLayout(new FlowLayout(FlowLayout.LEFT,5,0));
		plainPanel1.add(imageButton);
		Component[] cops1 = {xEstDate, plainPanel1};
		CombinePanel estDatePanel = new CombinePanel(cops1, 0, 0);
		gridbagAdd(estDatePanel, 1, 3, 1, 1);
		
		//��ð�
		gridbagAdd(vOperTime, 11, 3, 1, 1);
		Component[] cops2 = {cbOperTimeOpen, vOperTimeDash, cbOperTimeClose};
		CombinePanel operTimePanel = new CombinePanel(cops2,0,0);
		gridbagAdd(operTimePanel, 12 ,3, 1, 1);
		
		//������	
		gridbagAdd(vCenterManager, 0, 4, 1, 1);
		JPanel plainPanel2 = new JPanel();
		plainPanel2.setLayout(new FlowLayout(FlowLayout.LEFT,5,0));
		plainPanel2.add(centerManagerSearch);
		Component[] cops3 = {xCenterManager, plainPanel2};
		CombinePanel centerManagerPanel = new CombinePanel(cops3,0,0);
		gridbagAdd(centerManagerPanel, 1, 4, 1, 1);
		
		
		//�ּ�
		gridbagAdd(vAddress, 0, 5, 1, 1);
		gridbagAdd(xAddress, 1, 5, 1, 1);
		gridbagAdd(addressSearch, 2, 5, 1, 1);
		
		//�� ������ ��
		gridbagAdd(vCageNum, 0, 6, 1, 1);
		
		//���º� ������ ������ ���� Bottom Panel �� ��ġ
		BottomPanel bottomPanel = new BottomPanel();
		gridbagAdd(bottomPanel, 0, 7, 3, 1);
		
		
		//�����ư
		gridbagAdd(save, 10, 10, 1, 1);
		
		
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
	
	public static void main(String[] args) throws IOException{
		new NewCenterRegistration();
	}
}
