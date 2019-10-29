package asmr;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class NotiBoard extends JPanel {
	
//	private Container contain;
	
	NotiBoardButtonListener notiBoardButtonListener;
	// ����¡ �̱���, ����¡ ��ȣ ����!
	private JLabel vNoti;
	
	private JTextField xSearch;
	
	private JComboBox<String> cbSearch;
	
	private JTable eNoticeList;
	
	private JScrollPane scrollPane;
	
	private String[] searchDiv = {"����", "�ۼ���"};
	
	private JButton regis0, search;
	
	private final String[] col = {"��ȣ","����","�ۼ���","�ۼ��Ͻ�"};
	
	private DefaultTableModel model = new DefaultTableModel(col,0);
	
	GridBagLayout gridbaglayout;
	GridBagConstraints gridbagconstraints;
	
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	public NotiBoard() {
		
		notiBoardButtonListener = new NotiBoardButtonListener();
		
		gridbaglayout = new GridBagLayout();
		gridbagconstraints = new GridBagConstraints();
		
		vNoti = new JLabel("��������");
		vNoti.setFont(new Font("�������", Font.BOLD, 24));
		eNoticeList = new JTable(model){
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
		scrollPane = new JScrollPane(eNoticeList);
		scrollPane.setPreferredSize(new Dimension(700,300));
		
		regis0 = new JButton("���");
		regis0.setFont(new Font("�������", Font.BOLD, 16));
		regis0.setBackground(blue);
		regis0.setForeground(white);
		regis0.addActionListener(notiBoardButtonListener);
		
		cbSearch = new JComboBox<String>(searchDiv);
		xSearch = new JTextField(20);
		search = new JButton("�˻�");
		search.setFont(new Font("�������", Font.BOLD, 16));
		search.setBackground(blue);
		search.setForeground(white);
		search.addActionListener(notiBoardButtonListener);
		
		
		NotiBoardView();
	
	}
	
	private void NotiBoardView() {
		
		//setTitle("��������");	
		
		gridbagconstraints.anchor = GridBagConstraints.WEST;
		gridbagconstraints.ipadx = 7;
		
		gridbagconstraints.weightx=1.0;
		gridbagconstraints.weighty=1.0;
		
		setLayout(gridbaglayout);

		gridbagAdd(vNoti, 0, 0, 1, 1);
		
		gridbagAdd(regis0, 5, 1, 1, 1);
		
		gridbagAdd(scrollPane, 0, 2, 6, 1);
		
		gridbagAdd(cbSearch, 1, 3, 1, 1);
		gridbagAdd(xSearch, 2, 3, 1, 1);
		gridbagAdd(search, 3, 3, 1, 1);
		
		gridbagconstraints.anchor = GridBagConstraints.CENTER;

		//pack();
		//setResizable(false);
		//setVisible(true);
		
	}
	
	private void gridbagAdd(Component c, int x, int y, int w, int h) {			
		
		gridbagconstraints.gridx = x;		
		gridbagconstraints.gridy = y; 		
	      //���� ���� �� gridx, gridy���� 0 			
				
		gridbagconstraints.gridwidth  = w;	//����	
		gridbagconstraints.gridheight = h;	//����	
	     			
	      			
	    gridbaglayout.setConstraints(c, gridbagconstraints); //������Ʈ�� ������Ʈ ��ġ+ũ�� ������ ���� GridBagLayout�� ��ġ			
				
	   add(c);			
				
	   }
	
	
    class NotiBoardButtonListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource().equals(regis0)) {
				MainFrame.notiCase();
			}
			else if(e.getSource().equals(search)) {
				
			}
		}

		private Container getContentPane() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//new NotiBoard();
	}

}
