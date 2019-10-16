package src.asmr;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

public class MainPage extends JPanel{

	private Image iDog, iCat, iEtc;
	private JLabel vMainBanner, vMainProtect, iMainDog, iMainCat, iMainEtc, vMainDog, vMainCat, vMainEtc,
				   vNowCenter, vNotice, vAnnc;
	private JTextArea xNowCenter;
	private JScrollPane sNowCenter, sNotice;
	
	private JTable eNotice;
	private final String[] col = {"����", "�ۼ��Ͻ�"};
	private DefaultTableModel model = new DefaultTableModel(col, 0);
	
	GridBagLayout gridbaglayout;				// ȭ���� �����ϴ� ���̾ƿ�
	GridBagConstraints gridbagconstraints;	
	
	public MainPage() {
		
		gridbaglayout = new GridBagLayout();		
		gridbagconstraints = new GridBagConstraints();
		
		vMainBanner = new JLabel(new ImageIcon("images/mainbanner.jpg"));
		
		vMainProtect = new JLabel("���� ��ȣ���� ���⵿��");
		vMainProtect.setFont(new Font("�������", Font.PLAIN, 20));
		iDog = resize(new ImageIcon("images/dog1.png"), 70, 70);
		iMainDog = new JLabel(new ImageIcon(iDog));
		vMainDog = new JLabel("521");
		vMainDog.setFont(new Font("�������", Font.BOLD, 24));
		
		iCat = resize(new ImageIcon("images/cat1.png"), 70, 70);
		iMainCat = new JLabel(new ImageIcon(iCat));
		vMainCat = new JLabel("322");
		vMainCat.setFont(new Font("�������", Font.BOLD, 24));
		
		iEtc = resize(new ImageIcon("images/bird1.png"), 70, 70);
		iMainEtc = new JLabel(new ImageIcon(iEtc));
		vMainEtc = new JLabel("62");
		vMainEtc.setFont(new Font("�������", Font.BOLD, 24));
		
		vNowCenter = new JLabel("���� ���Ϳ�����");
		vNowCenter.setFont(new Font("�������", Font.PLAIN, 20));
		xNowCenter = new JTextArea();
		xNowCenter.setText("[10:30] ���Ｍ�ʼ��� ��Ű (�����)�� ��ϵǾ����ϴ�.\n"
				+ "[11:31] �λ��ؿ�뼾�� ��� (��)�� �Ծ�Ǿ����ϴ�.\n"
				+ "[14:09] �뱸�޼����� ���� (��Ÿ)�� ��ȯ�Ǿ����ϴ�.");
		sNowCenter = new JScrollPane(xNowCenter);
		sNowCenter.setPreferredSize(new Dimension(400,150));
		sNowCenter.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		sNowCenter.setEnabled(false);
		
		vNotice = new JLabel("��������");
		vNotice.setFont(new Font("�������", Font.PLAIN, 20));
		eNotice = new JTable(model);
		sNotice = new JScrollPane(eNotice);
		sNotice.setPreferredSize(new Dimension(350,120));
		sNotice.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		vAnnc = new JLabel("����");
		vAnnc.setFont(new Font("�������", Font.PLAIN, 20));
		
		MainPageView();
	}
	private Image resize(ImageIcon i, int w, int h) {
		Image tmp = i.getImage();
		Image resized = tmp.getScaledInstance(w, h, Image.SCALE_SMOOTH);
		return resized;
	}
	private void MainPageView() {
		
		gridbagconstraints.anchor = GridBagConstraints.WEST;		
		gridbagconstraints.ipadx = 7;		
				
		gridbagconstraints.weightx=1.0;		
		gridbagconstraints.weighty=1.0;		
				
		setLayout(gridbaglayout);
		
		gridbagAdd(vMainBanner, 0, 0, 10, 1);
		gridbagAdd(vMainProtect, 0, 1, 6, 1);
		gridbagAdd(iMainDog, 0, 2, 1, 1);
		gridbagAdd(iMainCat, 1, 2, 1, 1);
		gridbagAdd(iMainEtc, 2, 2, 1, 1);
		gridbagAdd(vMainDog, 0, 3, 1, 1);
		gridbagAdd(vMainCat, 1, 3, 1, 1);
		gridbagAdd(vMainEtc, 2, 3, 1, 1);
		gridbagAdd(vNowCenter, 4, 1, 3, 1);
		gridbagAdd(sNowCenter, 4, 2, 3, 2);
		gridbagAdd(vNotice, 0, 5, 3, 1);
		gridbagAdd(sNotice, 0, 6, 5, 2);
		gridbagAdd(vAnnc, 4, 5, 3, 1);
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

	}

}
