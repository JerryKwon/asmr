package asmr;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import asmr.CenterManagerSearch.CenterManagerSearchListener;

public class CenterManagerSearch extends JFrame{
	private JLabel vCenterManagerSerach;
	private JTable eCenterManagerList;
	private JScrollPane scrollpane;
	private JButton confirm,cancel;
	
	private final String[] col1 = {"�̸�","�������"};
	
	private DefaultTableModel model1 = new DefaultTableModel(col1,0);
	
	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;
	
	CenterManagerSearchListener centerManagerSearchListener;
	
	public CenterManagerSearch() {
		gridBagLayout = new GridBagLayout();
		gridBagConstraints = new GridBagConstraints();
		
		centerManagerSearchListener = new CenterManagerSearchListener();
		
		vCenterManagerSerach = new JLabel("������˻�");
		
		eCenterManagerList = new JTable(model1);
		scrollpane = new JScrollPane(eCenterManagerList);
		scrollpane.setPreferredSize(new Dimension(200,100));
		
		confirm = new JButton("Ȯ��");
		confirm.addActionListener(centerManagerSearchListener);
		cancel = new JButton("���");
		cancel.addActionListener(centerManagerSearchListener);
		
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
	
	class CenterManagerSearchListener implements ActionListener{

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
	
//  ��ư ��� �������� ���Ͽ� ���� �޼��带 �����մϴ�.	
//	public static void main(String[] args) {
//		new CenterManagerSearch();
//	}
}
