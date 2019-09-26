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
	
	private String[] searchDiv = {"유기동물명", "입양자명"};
	
	private JComboBox<String> cbSearch;
	
	private JButton search, regis1, regis2, cancel, imageButton1, imageButton2;
	
	private JTable eAdopList, eVisitList;
	
	private JScrollPane scrollPane1, scrollPane2;
	
	GridBagLayout gridbagLayout;
	GridBagConstraints gridbagConstraints;
	
	private BufferedImage buttonIcon1, buttonIcon2;
	
	private final String[] col1 = {"입양일자","유기동물명","동물종류","품종","입양자명","전화번호", "주소"};
	private DefaultTableModel model1 = new DefaultTableModel(col1,0);
	
	private final String[] col2 = {"방문회차","방문일자","방문직원명","내용"};
	private DefaultTableModel model2 = new DefaultTableModel(col2,0);

	
	public AdopList() throws IOException {
		
		gridbagLayout = new GridBagLayout();
		gridbagConstraints = new GridBagConstraints();
		
		vAdopList = new JLabel("입양목록");
		
		cbSearch = new JComboBox<String>(searchDiv);
		xSearch = new JTextField(20);
		search = new JButton("검색");
		
		vAdopDate = new JLabel("입양일자");
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
		
		vVisitList = new JLabel("방문목록");
		regis1 = new JButton("등록");
		
		eVisitList = new JTable(model2);
		scrollPane2 = new JScrollPane(eVisitList);
		scrollPane2.setPreferredSize(new Dimension(1200,100));
		
		regis2 = new JButton("등록");
		cancel = new JButton("취소");
		
		AdopListView();
		
	}
	
	private void AdopListView() {
		
		setTitle("입양목록");
		
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
	      //가장 왼쪽 위 gridx, gridy값은 0 			
				
		gridbagConstraints.gridwidth  = w;	//넓이	
		gridbagConstraints.gridheight = h;	//높이	
	     			
	      			
	    gridbagLayout.setConstraints(c, gridbagConstraints); //컴포넌트를 컴포넌트 위치+크기 정보에 따라 GridBagLayout에 배치			
				
	   add(c);			
				
	   }		
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		new AdopList();

	}

}
