package asmr;

import java.awt.Button;
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
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class RprtAssignment extends JPanel {
	
	private JLabel vRprtNo, vRprtDttm, vRprtName, vTelNo, vRprtTp, vWrtPrsnTp, vAnmlKinds,
	vAnmlSize, vExpln, vDscvDttm, vDscvLoc, vRprtList, vCageList, vRprtInfo;
	
	private JTextField xRprtNo, xRprtDttm, xRprtName, xTelNo, xExpln, xDscvDttm, xDscvLoc;
	
	private JComboBox<String> cbRprtTp, cbWrtPrsnTp, cbAnmlKinds, cbAnmlSize;
	
	private JTable eRprtList, eCageList;
	
	private JScrollPane scrollpane1, scrollpane2;
	
	private String[] rprtDiv = {"발견", "인계"};
	private String[] wrtPrsnDiv = {"직원","사용자"};
	private String[] anmlDiv = {"개", "고양이"};
	private String[] anmlSizeDiv = {"대", "중", "소"};
	
	private BufferedImage buttonIcon;
	
	private JButton Imagebutton;
	
	GridBagLayout gridbaglayout;
	GridBagConstraints gridbagconstraints;
	
	private Button btn;
	
	private final String[] col1 = {"신고일시","동물종류","동물크기","설명","배정센터명"};
	private final String[] col2 = {"센터명","주소","여유케이지(대)","여유케이지(중)","여유케이지(소)"};
	
	private DefaultTableModel model1 = new DefaultTableModel(col1,0);
	private DefaultTableModel model2 = new DefaultTableModel(col2,0);
	
	public RprtAssignment() throws IOException {
		gridbaglayout = new GridBagLayout();
		gridbagconstraints = new GridBagConstraints();
		
		// eRprtList, eCageList
		
		vRprtList = new JLabel("신고목록");
		eRprtList = new JTable(model1);
		scrollpane1 = new JScrollPane(eRprtList);
		scrollpane1.setPreferredSize(new Dimension(300,100));
		
		vCageList = new JLabel("센터별케이지현황");
		eCageList = new JTable(model2);
		scrollpane2 = new JScrollPane(eCageList);
		scrollpane2.setPreferredSize(new Dimension(300,100));
		
		vRprtInfo = new JLabel("신고정보");
		
		vRprtNo = new JLabel("신고번호");
		xRprtNo = new JTextField(20);
		
		vRprtDttm = new JLabel("신고일시");
		xRprtDttm = new JTextField(20);
		
		vRprtName = new JLabel("신고자명");
		xRprtName = new JTextField(20);
		
		vTelNo = new JLabel("연락처");
		xTelNo = new JTextField(20);
		
		vRprtTp = new JLabel("신고구분");
		cbRprtTp = new JComboBox<String>(rprtDiv);
		
		vWrtPrsnTp = new JLabel("작성자구분");
		cbWrtPrsnTp = new JComboBox<String>(wrtPrsnDiv);
		
		vAnmlKinds = new JLabel("동물종류");
		cbAnmlKinds = new JComboBox<String>(anmlDiv);
		
		vAnmlSize = new JLabel("동물크기");
		cbAnmlSize = new JComboBox<String>(anmlSizeDiv);
		
		vExpln = new JLabel("설명");
		xExpln = new JTextField(20);
		
		vDscvDttm = new JLabel("발견일시");
		xDscvDttm = new JTextField(20);
		
		vDscvLoc = new JLabel("발견장소");
		xDscvLoc = new JTextField(20);
		
		buttonIcon = ImageIO.read(new File("./images/cat1.png"));
		Imagebutton = new JButton(new ImageIcon(buttonIcon));
		Imagebutton.setBorderPainted(false);
		Imagebutton.setContentAreaFilled(false);
		Imagebutton.setFocusPainted(false);
		
		btn = new Button();
		
		RprtAssignmentView();
	}
	
	//private void setBorder(Border createEmptyBorder) {
		// TODO Auto-generated method stub
		
	//}

	private void RprtAssignmentView() {
		
		gridbagconstraints.anchor = GridBagConstraints.WEST;
		gridbagconstraints.ipadx = 7;
		
		gridbagconstraints.weightx=1.0;
		gridbagconstraints.weighty=1.0;
		
		setLayout(gridbaglayout);

		gridbagAdd(vRprtList, 0, 0, 1, 1);
		gridbagAdd(vCageList, 4, 0, 1, 1);
		
		gridbagAdd(scrollpane1, 0, 1, 4, 1);
		gridbagAdd(scrollpane2, 4, 1, 4, 1);
		
		gridbagAdd(vRprtInfo, 0, 6, 1, 1);
		
		gridbagAdd(vRprtNo, 0, 7, 1, 1);
		gridbagAdd(xRprtNo, 2, 7, 1, 1);
		//신고일
		gridbagAdd(vRprtDttm, 4, 7, 1, 1);
		gridbagAdd(xRprtDttm, 6, 7, 1, 1);
		
		gridbagAdd(vRprtName, 0, 8, 1, 1);
		gridbagAdd(xRprtName, 2, 8, 1, 1);
		
		gridbagAdd(vTelNo, 4, 8, 1, 1);
		gridbagAdd(xTelNo, 6, 8, 1, 1);
		
		gridbagAdd(vRprtTp, 0, 9, 1, 1);
		gridbagAdd(cbRprtTp, 2, 9, 1, 1);
		gridbagAdd(vWrtPrsnTp, 4, 9, 1, 1);
		gridbagAdd(cbWrtPrsnTp, 6, 9, 1, 1);
		
		gridbagAdd(vAnmlKinds, 0, 10, 1, 1);
		gridbagAdd(cbAnmlKinds, 2, 10, 1, 1);
		gridbagAdd(vAnmlSize, 4, 10, 1, 1);
		gridbagAdd(cbAnmlSize, 6, 10, 1, 1);
		
		gridbagAdd(vExpln, 0,11,1,1);
		gridbagAdd(xExpln, 2,11,1,1);

		gridbagAdd(vDscvDttm, 0, 12, 1, 1);
		gridbagAdd(xDscvDttm, 2, 12, 1, 1);
		
		gridbagAdd(vDscvLoc, 0, 13, 1, 1);
		gridbagAdd(xDscvLoc, 2, 13, 1, 1);
		
		gridbagAdd(Imagebutton, 11,7,1,3);
		
		gridbagconstraints.anchor = GridBagConstraints.CENTER;

		
	}

	private void gridbagAdd(Component c, int x, int y, int w, int h) {			
		
		gridbagconstraints.gridx = x;		
		gridbagconstraints.gridy = y; 		
	      //가장 왼쪽 위 gridx, gridy값은 0 			
				
		gridbagconstraints.gridwidth  = w;	//넓이	
		gridbagconstraints.gridheight = h;	//높이	
	     			
	      			
	    gridbaglayout.setConstraints(c, gridbagconstraints); //컴포넌트를 컴포넌트 위치+크기 정보에 따라 GridBagLayout에 배치			
				
	   add(c);			
				
	   }

	public static void main(String[] args) throws IOException {
		new RprtAssignment();
	}

}






