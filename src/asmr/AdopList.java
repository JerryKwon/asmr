package asmr;

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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class AdopList extends JFrame {
	
	private JLabel vAdopList, vAdopDate, vWave,
	vVisitList;
	
	private JTextField xSearch, xStartDate, xEndDate;
	
	private String[] searchDiv = {"���⵿����", "�Ծ��ڸ�"};
	
	private JComboBox<String> cbSearch;
	
	private JButton search, regis1, regis2, cancel, imageButton1, imageButton2;
	
	private JTable eAdopList, eVisitList;
	
	private JScrollPane scrollPane1, scrollPane2;
	
	GridBagLayout gridbagLayout;
	GridBagConstraints gridbagConstraints;
	
	private BufferedImage buttonIcon1, buttonIcon2;
	
	private final String[] col1 = {"�Ծ�����","���⵿����","��������","ǰ��","�Ծ��ڸ�","��ȭ��ȣ", "�ּ�"};
	private DefaultTableModel model1 = new DefaultTableModel(col1,0);
	
	private final String[] col2 = {"�湮ȸ��","�湮����","�湮������","����"};
	private DefaultTableModel model2 = new DefaultTableModel(col2,0);

	
	public AdopList() throws IOException {
		
		gridbagLayout = new GridBagLayout();
		gridbagConstraints = new GridBagConstraints();
		
		vAdopList = new JLabel("�Ծ���");
		
		cbSearch = new JComboBox<String>(searchDiv);
		xSearch = new JTextField(20);
		search = new JButton("�˻�");
		
		vAdopDate = new JLabel("�Ծ�����");
		xStartDate = new JTextField(20);
		vWave = new JLabel("~");
		buttonIcon1 = ImageIO.read(new File("./images/cal1.png"));
		imageButton1 = new JButton(new ImageIcon(buttonIcon1));
		imageButton1.setBorderPainted(false);
		imageButton1.setContentAreaFilled(false);
		imageButton1.setFocusPainted(false);
	
		xEndDate = new JTextField(20);
		buttonIcon2 = ImageIO.read(new File("./images/cal1.png"));
		imageButton2 = new JButton(new ImageIcon(buttonIcon2));
		imageButton2.setBorderPainted(false);
		imageButton2.setContentAreaFilled(false);
		imageButton2.setFocusPainted(false);
		
		eAdopList = new JTable(model1);
		scrollPane1 = new JScrollPane(eAdopList);
		scrollPane1.setPreferredSize(new Dimension(1200,100));
		
		vVisitList = new JLabel("�湮���");
		regis1 = new JButton("���");
		
		eVisitList = new JTable(model2);
		scrollPane2 = new JScrollPane(eVisitList);
		scrollPane2.setPreferredSize(new Dimension(1200,100));
		
		regis2 = new JButton("���");
		cancel = new JButton("���");
		
		AdopListView();
		
	}
	
	private void AdopListView() {
		
		setTitle("�Ծ���");
		
		gridbagConstraints.anchor = GridBagConstraints.WEST;
		gridbagConstraints.ipadx = 7;

		gridbagConstraints.weightx=1.0;
		gridbagConstraints.weighty=1.0;

		setLayout(gridbagLayout);
		
		gridbagAdd(vAdopList, 0, 0, 1, 1);
		
		gridbagAdd(cbSearch, 0, 1, 1, 1);
		gridbagAdd(xSearch, 2, 1, 1, 1);
		gridbagAdd(search, 3, 1, 1, 1);
		gridbagAdd(vAdopDate, 6, 1, 1, 1);
		gridbagAdd(xStartDate, 8, 1, 1, 1);
		gridbagAdd(imageButton1, 9, 1, 1, 1);
		gridbagAdd(vWave, 10, 1, 1, 1);
		gridbagAdd(xEndDate, 11, 1, 1, 1);
		gridbagAdd(imageButton2, 12, 1, 1, 1);
		
		gridbagAdd(scrollPane1, 0, 2, 15, 1);
		
		gridbagAdd(vVisitList, 0, 3, 1, 1);
		gridbagAdd(regis1, 12, 3, 1, 1);
		
		gridbagAdd(scrollPane2, 0, 4, 15, 1);
		
		gridbagAdd(regis2, 5, 5, 1, 1);
		gridbagAdd(cancel, 6, 5, 1, 1);
		
		pack();
		setResizable(false);
		setVisible(true);
		
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
		new AdopList();

	}

}
