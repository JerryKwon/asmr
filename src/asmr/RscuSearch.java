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
	
	private final String[] col1 = {"�����Ͻ�","��������","ũ��","�������"};
	
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
		
		vRscuList = new JLabel("�������");
		vRscuList.setFont(new Font("�������", Font.PLAIN, 16));
		
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
		
		confirm = new JButton("Ȯ��");
		confirm.setBackground(blue);
		confirm.setForeground(white);
		confirm.addActionListener(rscuSearchButtonListener);
		
		cancel = new JButton("���");
		cancel.addActionListener(rscuSearchButtonListener);
		
		RscuSearchView();
	}
	
	private void RscuSearchView() {
		
		setLayout(gridBagLayout);
		setTitle("�����˻�");
		
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
		//������Ʈ 1, ������Ʈ 2, �г� ������ ��,�� margin ������ ���ֱ� ���� Flag
		public CombinePanel(Component[] cops, int borderWidth, int borderHeight) {
			//Margin�� �ʿ����� ���� ��
			
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
					JOptionPane.showMessageDialog(null, "�ƹ��͵� ���õ��� �ʾҽ��ϴ�.", "����", JOptionPane.WARNING_MESSAGE);
				}
				else {
					int result = JOptionPane.showConfirmDialog(null, "�ش� ������ �����Ͻðڽ��ϱ�?", "��������",JOptionPane.QUESTION_MESSAGE);
					if(result == JOptionPane.OK_OPTION) {
						JOptionPane.showMessageDialog(null, "���õǾ����ϴ�.","����Ȯ��",JOptionPane.PLAIN_MESSAGE);
						//������ȣ�� ��ȣ��������˾��� ����� �� �ֵ���!
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
