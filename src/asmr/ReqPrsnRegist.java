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
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ReqPrsnRegist extends JFrame {
	private JLabel vReqPrsnRegist, vCustSearch, vNonCustRegist, vName, vAddress, vPhoneNum;
	private TextField xCustSearch, xName, xAddress, xPhoneNum;
	private JButton custSearch, addressSearch, returning, cancel;
	private JRadioButton cust,nonCust;
	private JComboBox<String> cbCustSearch;
	
	private JTable eCustList;
	private JScrollPane custListScroll;
	
	private final String[] col1 = {"이름","생년월일","주소"};
	
	private DefaultTableModel model1 = new DefaultTableModel(col1,0);
	
	private final String[] custSearchDiv = {"이름","생년월일"};
	
	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;
	
	public ReqPrsnRegist() {
		
		gridBagLayout = new GridBagLayout();		
		gridBagConstraints = new GridBagConstraints();
		
		vReqPrsnRegist = new JLabel("인수자등록");
		
		cust = new JRadioButton("회원");
		nonCust = new JRadioButton("비회원");
		
		vCustSearch = new JLabel("회원검색");
		
		cbCustSearch = new JComboBox<String>(custSearchDiv);
		xCustSearch = new TextField(10);
		custSearch = new JButton("검색");
		
		eCustList = new JTable(model1);
		custListScroll = new JScrollPane(eCustList);
		custListScroll.setPreferredSize(new Dimension(350,100));
		
		vNonCustRegist = new JLabel("비회원등록");
		
		vName = new JLabel("성명");
		xName = new TextField(10);
		
		vAddress = new JLabel("주소");
		xAddress = new TextField(15);
		addressSearch = new JButton("검색");
		
		vPhoneNum = new JLabel("전화번호");
		xPhoneNum = new TextField(10);
		
		returning = new JButton("반환");
		cancel = new JButton("취소");
		
		ReqPrsnRegistView();
		
	}
	
	private void ReqPrsnRegistView() {
		setLayout(gridBagLayout);
		
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.ipadx = 7;
		
		gridBagConstraints.weightx=1.0;
		gridBagConstraints.weighty=1.0;
		
		gridbagAdd(vReqPrsnRegist, 0, 0, 1, 1);
		
		gridbagAdd(cust, 0, 1, 1, 1);
		gridbagAdd(nonCust, 1, 1, 1, 1);
		
		gridbagAdd(vCustSearch, 0, 2, 1, 1);
		
		gridbagAdd(cbCustSearch, 0, 3, 1, 1);
		gridbagAdd(xCustSearch, 1, 3, 1, 1);
		gridbagAdd(custSearch, 2, 3, 1, 1);
		
		gridbagAdd(custListScroll, 0, 4, 4, 1);
		
		gridbagAdd(vNonCustRegist, 0, 5, 1, 1);
		
		gridbagAdd(vName, 0, 6, 1, 1);
		gridbagAdd(xName, 1, 6, 1, 1);
		
		gridbagAdd(vAddress, 0, 7, 1, 1);
		gridbagAdd(xAddress, 1, 7, 1, 1);
		gridbagAdd(addressSearch, 2, 7, 1, 1);
		
		gridbagAdd(vPhoneNum, 0, 8, 1, 1);
		gridbagAdd(xPhoneNum, 1, 8, 1, 1);
		
		Component[] cops = {returning, cancel};
		CombinePanel buttonPanel = new CombinePanel(cops, true);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 120, 0, 0));
		gridbagAdd(buttonPanel, 0, 9, 4, 1);
		
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
	
	class CombinePanel extends JPanel {
		//컴포넌트 1, 컴포넌트 2, 패널 구성시 좌,우 margin 공간을 없애기 위한 Flag
		public CombinePanel(Component[] cops, boolean isBorder) {
			//Margin이 필요하지 않을 때
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
		new ReqPrsnRegist();
	}
}
