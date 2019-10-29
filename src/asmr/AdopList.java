package asmr;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

public class AdopList extends JPanel {
	
	AdopListButtonListener adopListButtonListener;
	
	private JLabel vAdopList, vAdopDate, vWave,
	vVisitList;
	
	private JTextField xSearch;
	
	private String[] searchDiv = {"유기동물명", "입양자명"};
	
	private JComboBox<String> cbSearch;
	
	private JButton search, regis1, regis2, cancel;
	
	private JTable eAdopList, eVisitList;
	
	private JScrollPane scrollPane1, scrollPane2;
	
	GridBagLayout gridbagLayout;
	GridBagConstraints gridbagConstraints;
	
//	private BufferedImage buttonIcon1, buttonIcon2;
	
	private JDateChooser chooser1, chooser2;
	
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
//	private Color black = new Color(0,0,0);
	
	private final String[] col1 = {"입양일자","유기동물명","동물종류","품종","입양자명","전화번호", "주소"};
	private DefaultTableModel model1 = new DefaultTableModel(col1,0);
	
	private final String[] col2 = {"방문회차","방문일자","방문직원명","내용"};
	private DefaultTableModel model2 = new DefaultTableModel(col2,0);

	
	public AdopList() throws IOException {
		
		adopListButtonListener = new AdopListButtonListener();
		
		gridbagLayout = new GridBagLayout();
		gridbagConstraints = new GridBagConstraints();
		
		vAdopList = new JLabel("입양목록");
		vAdopList.setFont(new Font("나눔고딕", Font.BOLD, 24));
		
		cbSearch = new JComboBox<String>(searchDiv);
		xSearch = new JTextField(20);
		search = new JButton("검색");
		search.setFont(new Font("나눔고딕", Font.BOLD, 16));
		search.setBackground(blue);
		search.setForeground(white);
		search.addActionListener(adopListButtonListener);
		
		vAdopDate = new JLabel("입양일자");
		vAdopDate.setFont(new Font("나눔고딕", Font.PLAIN, 16));
		
//		xStartDate = new JTextField(20);
		
		vWave = new JLabel("~");
		vWave.setFont(new Font("나눔고딕", Font.PLAIN, 16));
//		buttonIcon1 = ImageIO.read(new File("./images/cal1.png"));
		
//		imageButton1 = new JButton(new ImageIcon(buttonIcon1));
//		imageButton1.setBorderPainted(false);
//		imageButton1.setContentAreaFilled(false);
//		imageButton1.setFocusPainted(false);
		
		LocalDate now1 = LocalDate.now();
		Date date1 = Date.valueOf(now1);
		chooser1 = new JDateChooser(date1,"YYYY-MM-dd");
		
		LocalDate now2 = LocalDate.now();
		Date date2 = Date.valueOf(now2);
		chooser2 = new JDateChooser(date2,"YYYY-MM-dd");
	
//		xEndDate = new JTextField(20);
//		buttonIcon2 = ImageIO.read(new File("./images/cal1.png"));
//		imageButton2 = new JButton(new ImageIcon(buttonIcon2));
//		imageButton2.setBorderPainted(false);
//		imageButton2.setContentAreaFilled(false);
//		imageButton2.setFocusPainted(false);
		
		eAdopList = new JTable(model1){
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
		scrollPane1 = new JScrollPane(eAdopList);
		scrollPane1.setPreferredSize(new Dimension(1200,100));
		
		vVisitList = new JLabel("방문목록");
		vVisitList.setFont(new Font("나눔고딕", Font.BOLD, 24));
		
		regis1 = new JButton("등록");
		regis1.setFont(new Font("나눔고딕", Font.BOLD, 16));
		regis1.setBackground(blue);
		regis1.setForeground(white);
		regis1.addActionListener(adopListButtonListener);
		
		eVisitList = new JTable(model2){
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
		scrollPane2 = new JScrollPane(eVisitList);
		scrollPane2.setPreferredSize(new Dimension(1200,100));
		
//		regis2 = new JButton("등록");
//		regis2.setFont(new Font("나눔고딕", Font.BOLD, 16));
//		regis2.setBackground(blue);
//		regis2.setForeground(white);
//		regis2.addActionListener(adopListButtonListener);
//		
//		cancel = new JButton("취소");
//		cancel.setFont(new Font("나눔고딕", Font.BOLD, 16));
//		cancel.setBackground(white);
//		cancel.setForeground(black);
//		cancel.addActionListener(adopListButtonListener);
		
		AdopListView();
		
	}
	
	private void AdopListView() {
		
		//setTitle("입양목록");
		
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
		gridbagAdd(chooser1, 8, 1, 1, 1);
//		gridbagAdd(imageButton1, 9, 1, 1, 1);
		gridbagAdd(vWave, 10, 1, 1, 1);
		gridbagAdd(chooser2, 11, 1, 1, 1);
//		gridbagAdd(imageButton2, 12, 1, 1, 1);
		
		gridbagAdd(scrollPane1, 0, 2, 15, 1);
		
		gridbagAdd(vVisitList, 0, 3, 1, 1);
		gridbagAdd(regis1, 12, 3, 1, 1);
		
		gridbagAdd(scrollPane2, 0, 4, 15, 1);
		
//		gridbagAdd(regis2, 5, 5, 1, 1);
//		gridbagAdd(cancel, 6, 5, 1, 1);
		
		//pack();
		//setResizable(false);
		///setVisible(true);
		
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
	
    private void ChangeFont(JComponent[] comps, Font font) {
    	for(JComponent comp: comps) {
    		comp.setFont(font);
    	}
    }
    
    class AdopListButtonListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource().equals(search)) {	
				
			}
			else if(e.getSource().equals(cancel)) {
				
			}
			else if(e.getSource().equals(regis1)) {
				try {
					new VisitRegisPopup();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
							
			}
			else if(e.getSource().equals(regis2)) {
				
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//new AdopList();

	}

}
