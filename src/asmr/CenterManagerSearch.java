package asmr;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
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
		
		vCenterManagerSerach = new JLabel("센터장목록");
		vCenterManagerSerach.setFont(new Font("나눔고딕", Font.BOLD, 16));
		
//		eCenterManagerList = new JTable(model1);
		eCenterManagerList = new JTable(model1) {
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
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
		setTitle("센터장 검색");
		
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.ipadx = 7;
		
		gridBagConstraints.weightx=1.0;
		gridBagConstraints.weighty=1.0;
		
		gridbagAdd(vCenterManagerSerach, 0, 0, 1, 1);
		
		JPanel scrollPanel = new JPanel();
		scrollPanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
		scrollPanel.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
		scrollPanel.add(scrollpane);
		gridbagAdd(scrollPanel, 0, 1, 2, 1);
		
		JComponent[] bComps = {confirm,cancel};
		ChangeFont(bComps, new Font("나눔고딕", Font.BOLD, 12));
		CombinePanel buttonPanel = new CombinePanel(bComps,10,0);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(0,35,0,0));
		gridbagAdd(buttonPanel, 0, 2, 1, 1);
		
		pack();
		setLocationRelativeTo(null);
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
		public CombinePanel(Component[] cops, int borderWidth, int borderHeight) {
			//Margin이 필요하지 않을 때
			
			setLayout(new FlowLayout(FlowLayout.LEFT,borderWidth,borderHeight));
			
			for (Component c: cops) {
				add(c);
			}
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
	
	
    private void ChangeFont(JComponent[] comps, Font font) {
    	for(JComponent comp: comps) {
    		comp.setFont(font);
    	}
    }
    
//  버튼 기능 구현으로 인하여 메인 메서드를 삭제합니다.	
	public static void main(String[] args) {
		new CenterManagerSearch();
	}
}
