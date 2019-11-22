package asmr;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import javax.swing.BorderFactory;
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

public class ReqList extends JPanel {
	
	ReqListButtonListener reqListButtonListener;
	
	private JLabel vReqList, vAdopEva, vAnmlInfo,
	vAbanName, vAnmlKinds, vKind, vSex, vAge,
	vReqPrsnInfo, vReqPrsnName, vTelNo,
	vAddr, vEvaRes, vRes, vReas, vAdopDate, vWarn;
	
	private JTextField xAbanName, xAnmlKinds,
	xKind, xSex, xAge, xReqPrsnName, xTelNo,
	xAddr, xReas, xAdopDate;
	
	private String[] resDiv = {"승인", "거부"};
	
	private JComboBox<String> cbRes;
	
	private JButton regis, cancel;
	
	private JTable eReqList;
	
	private JScrollPane scrollPane1;
	
	private JDateChooser chooser;
	
	GridBagLayout gridbagLayout;
	GridBagConstraints gridbagConstraints;
	
	private final String[] col1 = {"신청일시","유기동물명","동물종류","품종","신청인명","전화번호", "주소"};
	private DefaultTableModel model1 = new DefaultTableModel(col1,0);

	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	private Color black = new Color(0,0,0);
	
	public ReqList() throws IOException {
		
		reqListButtonListener = new ReqListButtonListener();
		
		gridbagLayout = new GridBagLayout();
		gridbagConstraints = new GridBagConstraints();
		
		vReqList = new JLabel("신청목록");
		vReqList.setFont(new Font("나눔고딕", Font.BOLD, 24));
		
		eReqList = new JTable(model1){
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
		scrollPane1 = new JScrollPane(eReqList);
		scrollPane1.setPreferredSize(new Dimension(1000,100));
		
		vAdopEva = new JLabel("입양심사");
		vAdopEva.setFont(new Font("나눔고딕", Font.BOLD, 24));
		
		vAnmlInfo = new JLabel("동물정보");
		vAnmlInfo.setFont(new Font("나눔고딕", Font.BOLD, 20));
		
		vAbanName = new JLabel("유기동물명");
		xAbanName = new JTextField(20);
		xAbanName.setEnabled(false);
		
		vAnmlKinds = new JLabel("동물종류");
		xAnmlKinds = new JTextField(20);
		xAnmlKinds.setEnabled(false);
		
		vKind = new JLabel("품종");
		xKind = new JTextField(20);
		xKind.setEnabled(false);
		
		vSex = new JLabel("성별");
		xSex = new JTextField(20);
		xSex.setEnabled(false);
		
		vAge = new JLabel("나이");
		xAge = new JTextField(20);
		xAge.setEnabled(false);
		
		vReqPrsnInfo = new JLabel("신청자정보");
		vReqPrsnInfo.setFont(new Font("나눔고딕", Font.BOLD, 20));
		
		vWarn = new JLabel("신청자 정보(연락처, 주소)가 맞는지 다시 한 번 확인해주세요.");
		vWarn.setFont(new Font("나눔고딕", Font.BOLD, 16));
		
		
		vReqPrsnName = new JLabel("신청자명");
		xReqPrsnName = new JTextField(20);
		xReqPrsnName.setEnabled(false);
		
		vTelNo = new JLabel("전화번호");
		xTelNo = new JTextField(20);
		xTelNo.setEnabled(false);
		
		vAddr = new JLabel("주소");
		xAddr = new JTextField(20);
		xAddr.setEnabled(false);
		
		vEvaRes = new JLabel("심사결과");
		vEvaRes.setFont(new Font("나눔고딕", Font.BOLD, 20));
		vEvaRes.setEnabled(false);
		
		vRes = new JLabel("결과");
		cbRes = new JComboBox<String>(resDiv);
		cbRes.setEnabled(false);
		
		vReas = new JLabel("사유");
		xReas = new JTextField(20);
		xReas.setEnabled(false);
		
		vAdopDate = new JLabel("입양일자");
		xAdopDate = new JTextField(20);
		xAdopDate.setEnabled(false);
		
		LocalDate now = LocalDate.now();
		Date date = Date.valueOf(now);
		chooser = new JDateChooser(date,"yyyy-MM-dd");
		
		regis = new JButton("등록");
		regis.setBackground(blue);
		regis.setForeground(white);
		regis.addActionListener(reqListButtonListener);
		regis.setFont(new Font("나눔고딕", Font.BOLD, 16));
		
		cancel = new JButton("취소");
		cancel.setBackground(white);
		cancel.setForeground(black);
		cancel.addActionListener(reqListButtonListener);
		cancel.setFont(new Font("나눔고딕", Font.BOLD, 16));
		
		JComponent[] vComps = {vAbanName, vAnmlKinds, vKind, vSex, vAge, vReqPrsnName, vTelNo, vAddr,
				vRes, vReas, vAdopDate};
		ChangeFont(vComps, new Font("나눔고딕", Font.PLAIN, 16));

		ReqListView();
	
	}
	
	private void ReqListView() {
		//setTitle("신청목록");
		setBackground(MainFrame.bgc);
		
		
		gridbagConstraints.insets = new Insets(5,5,5,5);
		
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
		gridbagAdd(chooser, 2, 13, 1, 1);
		
		JPanel plainPanel = new JPanel(new FlowLayout(FlowLayout.LEADING,20,0));
		plainPanel.add(regis);
		plainPanel.add(cancel);
		plainPanel.setBackground(MainFrame.bgc);
		plainPanel.setBorder(BorderFactory.createEmptyBorder(0, 425, 0, 0));
		
		gridbagAdd(plainPanel, 0, 14, 7, 0);
		
//		gridbagAdd(regis, 3, 14, 1, 1);
//		gridbagAdd(cancel, 4, 14, 1, 1);
		
		//pack();
		//setResizable(false);
		//setVisible(true);
			
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
    
    class ReqListButtonListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource().equals(regis)) {	
				
			}
			else if(e.getSource().equals(cancel)) {
				
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//new ReqList();
	}

}
