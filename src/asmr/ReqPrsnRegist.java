package asmr;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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
	
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	private Color red = new Color(217,0,27);
	
	CustItemListener custItemListener;
	NonCustItemListener nonCustItemListener;
	ReqPrsnRegistButtonListener reqPrsnRegistButtonListener;
	ReqPrsnRegistMouseListener reqRegistMouseListener;
	
	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;
	
	public ReqPrsnRegist() {
		
		gridBagLayout = new GridBagLayout();		
		gridBagConstraints = new GridBagConstraints();
		
		reqRegistMouseListener = new ReqPrsnRegistMouseListener();
		reqPrsnRegistButtonListener = new ReqPrsnRegistButtonListener();
		custItemListener = new CustItemListener();
		nonCustItemListener = new NonCustItemListener();
		
		vReqPrsnRegist = new JLabel("인수자등록");
		
		cust = new JRadioButton("회원");
		cust.addItemListener(custItemListener);
		nonCust = new JRadioButton("비회원");
		nonCust.addItemListener(nonCustItemListener);
		
		vCustSearch = new JLabel("회원검색");
		
		cbCustSearch = new JComboBox<String>(custSearchDiv);
		xCustSearch = new TextField(10);
		custSearch = new JButton("검색");
		custSearch.setBackground(blue);
		custSearch.setForeground(white);
		custSearch.addActionListener(reqPrsnRegistButtonListener);
		
		eCustList = new JTable(model1);
		eCustList.addMouseListener(reqRegistMouseListener);
		custListScroll = new JScrollPane(eCustList);
		custListScroll.setPreferredSize(new Dimension(350,100));
		
		vNonCustRegist = new JLabel("비회원등록");
		
		vName = new JLabel("성명");
		xName = new TextField(10);
		
		vAddress = new JLabel("주소");
		xAddress = new TextField(15);
		xAddress.setEnabled(false);
		addressSearch = new JButton("검색");
		addressSearch.setBackground(blue);
		addressSearch.setForeground(white);
		addressSearch.addActionListener(reqPrsnRegistButtonListener);
		
		vPhoneNum = new JLabel("전화번호");
		xPhoneNum = new TextField(10);
		
		returning = new JButton("반환");
		returning.setBackground(red);
		returning.setForeground(white);
		returning.addActionListener(reqPrsnRegistButtonListener);
		
		cancel = new JButton("취소");
		cancel.addActionListener(reqPrsnRegistButtonListener);
		
		//기본적으로 회원 활성화
		activateCust();
		
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
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 100, 0, 0));
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
	
	class CustItemListener implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			if(e.getStateChange()==ItemEvent.SELECTED) {
				activateCust();
			}
//			else if(e.getStateChange()==ItemEvent.DESELECTED) {
//				
//			}
		}
		
	}
	
	class NonCustItemListener implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			if(e.getStateChange()==ItemEvent.SELECTED) {
				activateNonCust();
			}
//			else if(e.getStateChange()==ItemEvent.DESELECTED) {
//				
//			}
		}
		
	}
	
	private void activateCust() {
		nonCust.setSelected(false);
		cust.setSelected(true);
		
		xName.setEnabled(false);
		addressSearch.setEnabled(false);
		xPhoneNum.setEnabled(false);
		
		xCustSearch.setEnabled(true);
		custSearch.setEnabled(true);
		cbCustSearch.setEnabled(true);
	}
	
	private void activateNonCust() {
		cust.setSelected(false);
		nonCust.setSelected(true);
		
		xName.setEnabled(true);
		addressSearch.setEnabled(true);
		xPhoneNum.setEnabled(true);
		
		xCustSearch.setEnabled(false);
		custSearch.setEnabled(false);
		cbCustSearch.setEnabled(false);
		
	}
	
	class ReqPrsnRegistButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource().equals(custSearch)) {
				
			}
			else if(e.getSource().equals(addressSearch)) {
				
			}
			else if(e.getSource().equals(returning)) {
				
			}
			else if(e.getSource().equals(cancel)) {
				dispose();
			}
		}
		
	}
	
	class ReqPrsnRegistMouseListener extends MouseAdapter{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			super.mouseClicked(e);
			if(e.getButton()==1) {
				
			}
		}
		
	}
	
	public static void main(String[] args) {
		new ReqPrsnRegist();
	}
}
