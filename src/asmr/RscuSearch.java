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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import asmr.NewCenterRegistration.NewCenterRegistButtonListener;

public class RscuSearch extends JFrame{
	JLabel vRscuList;
	JTable eRscuList;
	JButton confirm,cancel;
	
	JScrollPane scrollPane = new JScrollPane();
	
	private final String[] col1 = {"구조일시","동물종류","크기","구조장소"};
	
	private DefaultTableModel model1 = new DefaultTableModel(col1,0);
	
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	
	RscuSearchButtonListener rscuSearchButtonListener;
	RscuSearchMouseListener rscuSearchMouseListener;
	
	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;
	
	public RscuSearch() {
		gridBagLayout = new GridBagLayout();		
		gridBagConstraints = new GridBagConstraints();
		
		rscuSearchButtonListener = new RscuSearchButtonListener();
		rscuSearchMouseListener = new RscuSearchMouseListener();
		
		vRscuList = new JLabel("구조목록");
		vRscuList.setFont(new Font("나눔고딕", Font.PLAIN, 16));
		
//		eRscuList = new JTable(model1);
		eRscuList = new JTable(model1) {
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
		eRscuList.addMouseListener(rscuSearchMouseListener);
		scrollPane = new JScrollPane(eRscuList);
		scrollPane.setPreferredSize(new Dimension(400,100));
		
		confirm = new JButton("확인");
		confirm.setBackground(blue);
		confirm.setForeground(white);
		confirm.addActionListener(rscuSearchButtonListener);
		
		cancel = new JButton("취소");
		cancel.addActionListener(rscuSearchButtonListener);
		
		RscuSearchView();
	}
	
	private void RscuSearchView() {
		
		setLayout(gridBagLayout);
		setTitle("구조검색");
		
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.ipadx = 7;
		
		gridBagConstraints.weightx=1.0;
		gridBagConstraints.weighty=1.0;
		
		gridbagAdd(vRscuList, 0, 0, 1, 1);
		
		gridbagAdd(scrollPane, 0, 1, 1, 1);
		
		Component[] cops = {confirm, cancel};
		CombinePanel buttonPanel = new CombinePanel(cops, 5, 5);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(0,140,0,0));
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
	
	class RscuSearchButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource().equals(confirm)) {
				if(eRscuList.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "아무것도 선택되지 않았습니다.", "에러", JOptionPane.WARNING_MESSAGE);
				}
				else {
					int result = JOptionPane.showConfirmDialog(null, "해당 구조를 선택하시겠습니까?", "구조선택",JOptionPane.QUESTION_MESSAGE);
					if(result == JOptionPane.OK_OPTION) {
						JOptionPane.showMessageDialog(null, "선택되었습니다.","선택확인",JOptionPane.PLAIN_MESSAGE);
						//구조번호가 보호동물등록팝업에 연결될 수 있도록!
						dispose();
					}
				}
			}
			else if(e.getSource().equals(cancel)) {
				dispose();
			}
		}
		
	}
	
	class RscuSearchMouseListener extends MouseAdapter{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			super.mouseClicked(e);
			if(e.getButton()==1) {
				
			}
		}
		
	}
	
	public static void main(String[] args) {
		new RscuSearch();
	}
}
