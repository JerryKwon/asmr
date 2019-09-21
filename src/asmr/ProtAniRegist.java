package asmr;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextField;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class ProtAniRegist extends JFrame{
	private JLabel vProtAniRegist, vAbanAniType, vRescNo, vParAbanAniName, vAbanAniName, vAge, vAniType, vKind, vSex, vNeutWhet, vColor, vAniSize, vFeature, vPicture, vCage;
	private TextField xRescNo, xParAbanAniName, xAbanAniName, xAge, xKind, xColor;
	private JComboBox<String> cbAbanAniType, cbAniType, cbSex, cbNeutWhet, cbAniSize, cbCage;
	private JTextArea xFeature;
	private JButton pictureManage, register, cancel;
	
	private JScrollPane featureScroll;
	
	private final String[] abanAniTypeDiv = {"����","ź��"};
	private final String[] aniTypeDiv = {"��","�����","��Ÿ"};
	private final String[] sexDiv = {"����","����","�̻�"};
	private final String[] neutWhetDiv = {"Y","N"};
	private final String[] aniSizeDiv = {"��","��","��"};
	private final String[] cageDiv = {"������1(��)","������4(��)","������9(��)"};
	
	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;
	
	public ProtAniRegist() {
		
		gridBagLayout = new GridBagLayout();		
		gridBagConstraints = new GridBagConstraints();
		
		vProtAniRegist = new JLabel("��ȣ�������");
		
		vAbanAniType = new JLabel("���⵿������");
		cbAbanAniType = new JComboBox<String>(abanAniTypeDiv);
		
		vRescNo = new JLabel("������ȣ");
		xRescNo = new TextField(10);
		xRescNo.setEnabled(false);
		
		vParAbanAniName = new JLabel("������⵿����");
		xParAbanAniName = new TextField(10);
		xParAbanAniName.setEnabled(false);
		
		vAbanAniName = new JLabel("���⵿����");
		xAbanAniName = new TextField(10);
		
		vAge = new JLabel("����(����)");
		xAge = new TextField(10);
		
		vAniType = new JLabel("��������");
		cbAniType = new JComboBox<String>(aniTypeDiv);
		
		vKind = new JLabel("ǰ��");
		xKind = new TextField(10);
		
		vSex = new JLabel("����");
		cbSex = new JComboBox<String>(sexDiv);
		
		vNeutWhet = new JLabel("�߼�ȭ����");
		cbNeutWhet = new JComboBox<String>(neutWhetDiv);
		
		vColor = new JLabel("����");
		xColor = new TextField(10);
		
		vAniSize = new JLabel("����ũ��");
		cbAniSize = new JComboBox<String>(aniSizeDiv);
		
		vFeature = new JLabel("Ư¡");
		xFeature = new JTextArea();
		featureScroll = new JScrollPane(xFeature);
		featureScroll.setPreferredSize(new Dimension(300,100));
		featureScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		vPicture = new JLabel("����");
		pictureManage = new JButton("��������");
		
		vCage = new JLabel("������");
		cbCage = new JComboBox<String>(cageDiv);
		
		register = new JButton("���");
		cancel = new JButton("���");
		
		ProtAniRegistView();
	}
	
	private void ProtAniRegistView() {
		setLayout(gridBagLayout);
		
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.ipadx = 7;
		
		gridBagConstraints.weightx=1.0;
		gridBagConstraints.weighty=1.0;
	
		gridbagAdd(vProtAniRegist, 0, 0, 1, 1);
		
		gridbagAdd(vAbanAniType, 0, 1, 1, 1);
		gridbagAdd(cbAbanAniType, 1, 1, 1, 1);
		
		gridbagAdd(vRescNo, 2, 1, 1, 1);
		gridbagAdd(xRescNo, 3, 1, 1, 1);
		
		gridbagAdd(vParAbanAniName, 4, 1, 1, 1);
		gridbagAdd(xParAbanAniName, 5, 1, 1, 1);
		
		gridbagAdd(vAbanAniName, 0, 2, 1, 1);
		gridbagAdd(xAbanAniName, 1, 2, 1, 1);
		
		gridbagAdd(vAge, 2, 2, 1, 1);
		gridbagAdd(xAge, 3, 2, 1, 1);
		
		gridbagAdd(vAniType, 0, 3, 1, 1);
		gridbagAdd(cbAniType, 1, 3, 1, 1);
		
		gridbagAdd(vKind, 2, 3, 1, 1);
		gridbagAdd(xKind, 3, 3, 1, 1);
		
		gridbagAdd(vSex, 0, 4, 1, 1);
		gridbagAdd(cbSex, 1, 4, 1, 1);
		
		gridbagAdd(vNeutWhet, 2, 4, 1, 1);
		gridbagAdd(cbNeutWhet, 3, 4, 1, 1);
		
		gridbagAdd(vColor, 0, 5, 1, 1);
		gridbagAdd(xColor, 1, 5, 1, 1);
		
		gridbagAdd(vAniSize, 2, 5, 1, 1);
		gridbagAdd(cbAniSize, 3, 5, 1, 1);
		
		gridbagAdd(vFeature, 0, 6, 1, 1);
		gridbagAdd(featureScroll, 1, 6, 3, 1);
		
		gridbagAdd(vPicture, 0, 7, 1, 1);
		gridbagAdd(pictureManage, 1, 7, 1, 1);
		
		gridbagAdd(vCage, 0, 8, 1, 1);
		gridbagAdd(cbCage, 1, 8, 1, 1);
	
		Component[] cops = {register, cancel};
		CombinePanel buttonPanel = new CombinePanel(cops, true);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 225, 0, 0));
		gridbagAdd(buttonPanel, 0, 9, 6, 1);
		
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
	
	//�ΰ��� ������Ʈ�� �ϳ��� �гη� ���� JPanel
	class CombinePanel extends JPanel {
		//������Ʈ 1, ������Ʈ 2, �г� ������ ��,�� margin ������ ���ֱ� ���� Flag
		public CombinePanel(Component[] cops, boolean isBorder) {
			//Margin�� �ʿ����� ���� ��
			if(!isBorder) {
				setLayout(new FlowLayout(FlowLayout.LEFT,0,0));	
			}
			else {
				setLayout(new FlowLayout(FlowLayout.LEFT,15,0));	
			}
			for (Component c: cops) {
				add(c);
			}
		}
	}
	
	public static void main(String[] args) {
		new ProtAniRegist();
	}
}
