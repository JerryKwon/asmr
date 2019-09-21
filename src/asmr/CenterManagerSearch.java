package asmr;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import asmr.CenterManagerSearch.CenterManagerSearchButtonListener;

public class CenterManagerSearch extends JFrame{
	private JLabel vCenterManagerSerach;
	private JTable eCenterManagerList;
	private JScrollPane scrollpane;
	private JButton confirm,cancel;
	
	private final String[] col1 = {"이름","생년월일"};
	
	private DefaultTableModel model1 = new DefaultTableModel(col1,0);
	
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	
	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;
	
	CenterManagerSearchButtonListener centerManagerSearchButtonListener;
	CenterManagerSearchMouseListener centerManagerSearchMouseListener;
	
	public CenterManagerSearch() {
		gridBagLayout = new GridBagLayout();
		gridBagConstraints = new GridBagConstraints();
		
		centerManagerSearchButtonListener = new CenterManagerSearchButtonListener();
		centerManagerSearchMouseListener = new CenterManagerSearchMouseListener();
		
		vCenterManagerSerach = new JLabel("센터장검색");
		
		eCenterManagerList = new JTable(model1);
		eCenterManagerList.addMouseListener(centerManagerSearchMouseListener);
		scrollpane = new JScrollPane(eCenterManagerList);
		scrollpane.setPreferredSize(new Dimension(200,100));
		
		confirm = new JButton("확인");
		confirm.setBackground(blue);
		confirm.setForeground(white);
		confirm.addActionListener(centerManagerSearchButtonListener);
		
		cancel = new JButton("취소");
		cancel.addActionListener(centerManagerSearchButtonListener);
		
		CenterManagerSearchView();
	}
	
	private void CenterManagerSearchView() {
		setLayout(gridBagLayout);
		
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.ipadx = 7;
		
		gridBagConstraints.weightx=1.0;
		gridBagConstraints.weighty=1.0;
		
		gridbagAdd(vCenterManagerSerach, 0, 0, 1, 1);
		
		gridbagAdd(scrollpane, 0, 1, 2, 1);
		
		ButtonPanel buttonPanel = new ButtonPanel();
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(0,35,0,0));
		gridbagAdd(buttonPanel, 0, 2, 1, 1);
		
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

	class ButtonPanel extends JPanel{
		public ButtonPanel() {
			setLayout(new FlowLayout(FlowLayout.RIGHT));
			add(confirm);
			add(cancel);
			
		}
	}
	
	class CenterManagerSearchButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource().equals(confirm)) {
				
			}
			else if(e.getSource().equals(cancel)) {
				dispose();
			}
		}
	}
	
	class CenterManagerSearchMouseListener extends MouseAdapter{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			super.mouseClicked(e);
			if(e.getButton()==1) {
				
			}
		}
		
	}
	
//  버튼 기능 구현으로 인하여 메인 메서드를 삭제합니다.	
//	public static void main(String[] args) {
//		new CenterManagerSearch();
//	}
}
