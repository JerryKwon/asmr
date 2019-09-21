package asmr;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CenterManagerSearch extends JFrame{
	private JLabel vCenterManagerSerach;
	private JTable eCenterManagerList;
	private JScrollPane scrollpane;
	private JButton confirm,cancel;
	
	private final String[] col1 = {"이름","생년월일"};
	
	private DefaultTableModel model1 = new DefaultTableModel(col1,0);
	
	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;
	
	public CenterManagerSearch() {
		gridBagLayout = new GridBagLayout();
		gridBagConstraints = new GridBagConstraints();
		
		vCenterManagerSerach = new JLabel("센터장검색");
		
		eCenterManagerList = new JTable(model1);
		scrollpane = new JScrollPane(eCenterManagerList);
		scrollpane.setPreferredSize(new Dimension(200,100));
		
		confirm = new JButton("확인");
		cancel = new JButton("취소");
		
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
	
	
	public static void main(String[] args) {
		new CenterManagerSearch();
	}
}
