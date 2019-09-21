package asmr;

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

public class CenterSearch extends JFrame{
	private JLabel vCenterSearch;
	private JButton confirm, cancel;
	
	private JTable eCenterList;
	private JScrollPane scrollpane;
	
	private final String[] col1 = {"센터명","주소"};
	
	private DefaultTableModel model1 = new DefaultTableModel(col1,0);
	
	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;
	
	public CenterSearch() {
		gridBagLayout = new GridBagLayout();		
		gridBagConstraints = new GridBagConstraints();
		
		vCenterSearch = new JLabel("센터검색");
		
		eCenterList = new JTable(model1);
		scrollpane = new JScrollPane(eCenterList);
		scrollpane.setPreferredSize(new Dimension(250,100));
		
		confirm = new JButton("확인");
		cancel = new JButton("취소");
		
		CenterSearchView();
	}
	
	private void CenterSearchView() {
		setLayout(gridBagLayout);
		
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.ipadx = 7;
		
		gridBagConstraints.weightx=1.0;
		gridBagConstraints.weighty=1.0;
		
		gridbagAdd(vCenterSearch, 0, 0, 1, 1);
		
		JPanel panel = new JPanel();
		panel.add(scrollpane);
		panel.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));
		gridbagAdd(panel, 0, 1, 1, 1);
		
		Component[] cops = {confirm,cancel};
		CombinePanel buttonPanel = new CombinePanel(cops, true);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(0,70,0,0));
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
	
	//여러개의 컴포넌트를 하나의 패널로 묶는 JPanel
	class CombinePanel extends JPanel {
		//컴포넌트 배열, 패널 구성시 좌,우 margin 공간을 없애기 위한 Flag
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
		new CenterSearch();
	}
}
