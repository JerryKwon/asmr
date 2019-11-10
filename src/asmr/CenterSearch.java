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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class CenterSearch extends JFrame{
	private JLabel vCenterSearch;
	private JButton confirm, cancel;
	private JTextField xBelongCenter;
	
	private JTable eCenterList;
	private JScrollPane scrollpane;
	
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "asmr";
	private String password = "asmr";
	
	private ArrayList<String> cntrNos;
	private String cntrNo = null;
	
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private ResultSetMetaData rsmd = null;
	
	private final String[] col1 = {"���͸�","�ּ�"};
	
	private DefaultTableModel model1 = new DefaultTableModel(col1,0);
	
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	
	CenterSearchButtonListener centerSearchButtonListener;
	CenterSearchMouseListener centerSearchMouseListener;
	
	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;
	
	public CenterSearch(JTextField xBelongCenter) {
		gridBagLayout = new GridBagLayout();		
		gridBagConstraints = new GridBagConstraints();
		
		centerSearchButtonListener = new CenterSearchButtonListener();
		centerSearchMouseListener = new CenterSearchMouseListener();
		
		this.xBelongCenter = xBelongCenter;
		
		cntrNos = new ArrayList<String>();
		
		vCenterSearch = new JLabel("���͸��");
		
//		eCenterList = new JTable(model1);
		eCenterList = new JTable(model1) {
		        private static final long serialVersionUID = 1L;

		        public boolean isCellEditable(int row, int column) {                
		                return false;               
		        };
		    };
		
		eCenterList.addMouseListener(centerSearchMouseListener);
		scrollpane = new JScrollPane(eCenterList);
		scrollpane.setPreferredSize(new Dimension(450,100));
		eCenterList.getColumnModel().getColumn(0).setPreferredWidth(125);
		eCenterList.getColumnModel().getColumn(1).setPreferredWidth(325);
		
		confirm = new JButton("Ȯ��");
		confirm.setBackground(blue);
		confirm.setForeground(white);
		confirm.addActionListener(centerSearchButtonListener);
		
		cancel = new JButton("���");
		cancel.addActionListener(centerSearchButtonListener);
		
		CenterSearchView();
		
		GetCenterList();
	}
	
	private void CenterSearchView() {
		setLayout(gridBagLayout);
		setTitle("���Ͱ˻�");
		
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.ipadx = 7;
		
		gridBagConstraints.weightx=1.0;
		gridBagConstraints.weighty=1.0;
		
		vCenterSearch.setFont(new Font("�������", Font.BOLD, 16));
		gridbagAdd(vCenterSearch, 0, 0, 1, 1);
		
		JPanel panel = new JPanel();
		panel.add(scrollpane);
		panel.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));
		gridbagAdd(panel, 0, 1, 1, 1);
		
		JComponent[] cops = {confirm,cancel};
		ChangeFont(cops, new Font("�������", Font.BOLD, 12));
		CombinePanel buttonPanel = new CombinePanel(cops, true);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(0,175,0,0));
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
	
	//�������� ������Ʈ�� �ϳ��� �гη� ���� JPanel
	class CombinePanel extends JPanel {
		//������Ʈ �迭, �г� ������ ��,�� margin ������ ���ֱ� ���� Flag
		public CombinePanel(Component[] cops, boolean isBorder) {
			//Margin�� �ʿ����� ���� ��
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
	
	class CenterSearchButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource().equals(confirm)) {
				int clickedRow = eCenterList.getSelectedRow();
				String cntrName = (String)eCenterList.getValueAt(clickedRow, 0);
				xBelongCenter.setText(cntrName);
				dispose();
			}
			else if(e.getSource().equals(cancel)) {
				cntrNo = "";
				dispose();
			}
		}
		
	}
	
	class CenterSearchMouseListener extends MouseAdapter{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			super.mouseClicked(e);
			
			if(e.getButton()==1) {
				int clickedRow = eCenterList.getSelectedRow();
				cntrNo = cntrNos.get(clickedRow);
				System.out.println(cntrNo);
			}
		}
		
	}

    // �����ͺ��̽� ����

    public void connection() {

             try {

                      Class.forName("oracle.jdbc.driver.OracleDriver");

                      con = DriverManager.getConnection(url,user,password);


             } catch (ClassNotFoundException e) {
            	 e.printStackTrace();
             } catch (SQLException e) {
            	 e.printStackTrace();
             }

    }

    // �����ͺ��̽� ���� ����
    public void disconnection() {

        try {

                 if(pstmt != null) pstmt.close();

                 if(rs != null) rs.close();

                 if(con != null) con.close();

        } catch (SQLException e) {
        	e.printStackTrace();
        }

    }
	
    private void GetCenterList() {
    	connection();
    	
    	try {
    		
			StringBuffer query= new StringBuffer("SELECT CNTR_NO, CNTR_NAME, ADDR ");
			query.append("FROM CNTR ");
//			query.append("WHERE CNTR_TP <> 'h' ");
			query.append("ORDER BY 1 ");
			
    		pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {	
				
				cntrNos.add(rs.getString("CNTR_NO"));
				
				model1.addRow(new Object[] {rs.getString("CNTR_NAME"),rs.getString("ADDR")});
			}
    		
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	
    	disconnection();
    }
    
	private void ChangeFont(JComponent[] comps, Font font) {
    	for(JComponent comp: comps) {
    		comp.setFont(font);
    	}
    }
	
	
	
	public String getCntrNo() {
		return cntrNo;
	}

	//  ������ �۾� ����� ���� �޼��� �ּ�ó���Ͽ����ϴ�.
	public static void main(String[] args) {
		new CenterSearch(new JTextField());
	}
}
