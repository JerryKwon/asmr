package src.asmr;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ReqList extends JPanel {
	
	private JLabel vReqList, vAdopEva, vAnmlInfo,
	vAbanName, vAnmlKinds, vKind, vSex, vAge,
	vReqPrsnInfo, vReqPrsnName, vTelNo,
	vAddr, vEvaRes, vRes, vReas, vAdopDate, vWarn;
	
	private JTextField xAbanName, xAnmlKinds,
	xKind, xSex, xAge, xReqPrsnName, xTelNo,
	xAddr, xReas, xAdopDate;
	
	private String[] resDiv = {"����", "�ź�"};
	
	private JComboBox<String> cbRes;
	
	private JButton regis, cancel, imageButton;
	
	private JTable eReqList;
	
	private JScrollPane scrollPane1;
	
	GridBagLayout gridbagLayout;
	GridBagConstraints gridbagConstraints;
	
	private final String[] col1 = {"��û�Ͻ�","���⵿����","��������","ǰ��","��û�θ�","��ȭ��ȣ", "�ּ�"};
	private DefaultTableModel model1 = new DefaultTableModel(col1,0);

	private BufferedImage buttonIcon;
	
	public ReqList() throws IOException {
		gridbagLayout = new GridBagLayout();
		gridbagConstraints = new GridBagConstraints();
		
		vReqList = new JLabel("��û���");
		
		eReqList = new JTable(model1);
		scrollPane1 = new JScrollPane(eReqList);
		scrollPane1.setPreferredSize(new Dimension(1000,100));
		
		vAdopEva = new JLabel("�Ծ�ɻ�");
		
		vAnmlInfo = new JLabel("��������");
		
		vAbanName = new JLabel("���⵿����");
		xAbanName = new JTextField(20);
		
		vAnmlKinds = new JLabel("��������");
		xAnmlKinds = new JTextField(20);
		
		vKind = new JLabel("ǰ��");
		xKind = new JTextField(20);
		
		vSex = new JLabel("����");
		xSex = new JTextField(20);
		
		vAge = new JLabel("����");
		xAge = new JTextField(20);
		
		vReqPrsnInfo = new JLabel("��û������");
		
		vWarn = new JLabel("��û�� ����(����ó, �ּ�)�� �´��� �ٽ� �� �� Ȯ�����ּ���.");
		
		vReqPrsnName = new JLabel("��û�ڸ�");
		xReqPrsnName = new JTextField(20);
		
		vTelNo = new JLabel("��ȭ��ȣ");
		xTelNo = new JTextField(20);
		
		vAddr = new JLabel("�ּ�");
		xAddr = new JTextField(20);
		
		vEvaRes = new JLabel("�ɻ���");
		
		vRes = new JLabel("���");
		cbRes = new JComboBox<String>(resDiv);
		
		vReas = new JLabel("����");
		xReas = new JTextField(20);
		
		vAdopDate = new JLabel("�Ծ�����");
		xAdopDate = new JTextField(20);
		
		regis = new JButton("���");
		cancel = new JButton("���");
		
		buttonIcon = ImageIO.read(new File("./images/cal1.png"));
		imageButton = new JButton(new ImageIcon(buttonIcon));
		imageButton.setBorderPainted(false);
		imageButton.setContentAreaFilled(false);
		imageButton.setFocusPainted(false);

		ReqListView();
	
	}
	
	private void ReqListView() {
		//setTitle("��û���");
		
		gridbagConstraints.anchor = GridBagConstraints.WEST;
		gridbagConstraints.ipadx = 7;

		gridbagConstraints.weightx=1.0;
		gridbagConstraints.weighty=1.0;

		setLayout(gridbagLayout);
		
		gridbagAdd(vReqList, 0, 0, 1, 1);
		
		gridbagAdd(scrollPane1, 0, 1, 10, 1);
		
		gridbagAdd(vAdopEva, 0, 2, 1, 1);

		gridbagAdd(vAnmlInfo, 0, 3, 1, 1);
		
		gridbagAdd(vAbanName, 0, 4, 1, 1);
		gridbagAdd(xAbanName, 2, 4, 1, 1);
		
		gridbagAdd(vAnmlKinds, 0, 5, 1, 1);
		gridbagAdd(xAnmlKinds, 2, 5, 1, 1);
		gridbagAdd(vKind, 4, 5, 1, 1);
		gridbagAdd(xKind, 6, 5, 1, 1);
		
		gridbagAdd(vSex, 0, 6, 1, 1);
		gridbagAdd(xSex, 2, 6, 1, 1);
		gridbagAdd(vAge, 4, 6, 1, 1);
		gridbagAdd(xAge, 6, 6, 1, 1);
		
		gridbagAdd(vReqPrsnInfo, 0, 7, 1, 1);
		gridbagAdd(vWarn, 2, 7, 1, 1);

		gridbagAdd(vReqPrsnName, 0, 8, 1, 1);
		gridbagAdd(xReqPrsnName, 2, 8, 1, 1);
		
		gridbagAdd(vTelNo, 0, 9, 1, 1);
		gridbagAdd(xTelNo, 2, 9, 1, 1);
		
		gridbagAdd(vEvaRes, 0, 10, 1, 1);
		
		gridbagAdd(vRes, 0, 11, 1, 1);
		gridbagAdd(cbRes, 2, 11, 1, 1);
		
		gridbagAdd(vReas, 0, 12, 1, 1);
		gridbagAdd(xReas, 2, 12, 1, 1);
		
		gridbagAdd(vAdopDate, 0, 13, 1, 1);
		gridbagAdd(xAdopDate, 2, 13, 1, 1);
		gridbagAdd(imageButton, 3, 13, 1, 1);
		
		gridbagAdd(regis, 3, 14, 1, 1);
		gridbagAdd(cancel, 4, 14, 1, 1);
		
		//pack();
		//setResizable(false);
		//setVisible(true);
			
	}
	
	private void gridbagAdd(Component c, int x, int y, int w, int h) {			
		
		gridbagConstraints.gridx = x;		
		gridbagConstraints.gridy = y; 		
	      //���� ���� �� gridx, gridy���� 0 			
				
		gridbagConstraints.gridwidth  = w;	//����	
		gridbagConstraints.gridheight = h;	//����	
	     			
	      			
	    gridbagLayout.setConstraints(c, gridbagConstraints); //������Ʈ�� ������Ʈ ��ġ+ũ�� ������ ���� GridBagLayout�� ��ġ			
				
	   add(c);			
				
	   }	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//new ReqList();
	}

}
