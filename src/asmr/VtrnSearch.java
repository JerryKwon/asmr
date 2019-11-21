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

public class VtrnSearch extends JFrame {
	
	private JLabel vVtrnSearch;
	private JTable eVtrnList;
	private JScrollPane scrollpane;
	private JButton confirm,cancel;
	private JTextField xVtrn;
	
	private String vtrnBdate = null;
	private String userCntrNo;
	
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "asmr";
	private String password = "asmr";
	
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private ResultSetMetaData rsmd = null;
	
	private final String[] col1 = {"이름","생년월일"};
	
	private DefaultTableModel model1 = new DefaultTableModel(col1,0);
	
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	
	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;
	
	CenterManagerSearchButtonListener centerManagerSearchButtonListener;
	CenterManagerSearchMouseListener centerManagerSearchMouseListener;
	
	public VtrnSearch(JTextField xVtrn,String cntrNo) {
		this.xVtrn = xVtrn;
//		this.cntrManagerBdate = cntrManagerBdate;
		
		gridBagLayout = new GridBagLayout();
		gridBagConstraints = new GridBagConstraints();
		
		centerManagerSearchButtonListener = new CenterManagerSearchButtonListener();
		centerManagerSearchMouseListener = new CenterManagerSearchMouseListener();
		
		userCntrNo = cntrNo;
		
		vVtrnSearch = new JLabel("수의사목록");
		vVtrnSearch.setFont(new Font("나눔고딕", Font.PLAIN, 16));
		vVtrnSearch.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 0));
		
//		eCenterManagerList = new JTable(model1);
		eVtrnList = new JTable(model1) {
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
		eVtrnList.getTableHeader().setReorderingAllowed(false);
		eVtrnList.getTableHeader().setResizingAllowed(false);
	    eVtrnList.addMouseListener(centerManagerSearchMouseListener);
		scrollpane = new JScrollPane(eVtrnList);
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
		setTitle("수의사 검색");
		this.getContentPane().setBackground(MainFrame.bgc);
		
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.ipadx = 7;
		
		gridBagConstraints.weightx=1.0;
		gridBagConstraints.weighty=1.0;
		
		gridbagAdd(vVtrnSearch, 0, 0, 1, 1);
		
		JPanel scrollPanel = new JPanel();
		scrollPanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
		scrollPanel.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
		scrollPanel.setBackground(MainFrame.bgc);
		scrollPanel.add(scrollpane);
		
		JPanel plainPanel = new JPanel();
		plainPanel.add(scrollPanel);
		plainPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		plainPanel.setBackground(MainFrame.bgc);
		gridbagAdd(plainPanel, 0, 1, 2, 1);
		
		JComponent[] bComps = {confirm,cancel};
		ChangeFont(bComps, new Font("나눔고딕", Font.BOLD, 12));
		CombinePanel buttonPanel = new CombinePanel(bComps,10,0);
		buttonPanel.setBackground(MainFrame.bgc);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(10,60,0,0));
		gridbagAdd(buttonPanel, 0, 2, 1, 1);
		
		readCenterManager();
		
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

	
	private void readCenterManager() {
		connection();
		
		try {
			StringBuffer query= new StringBuffer("SELECT e.EMP_NAME name, e.BRTH_YEAR_MNTH_DAY bdate ");
			query.append("FROM EMP e INNER JOIN (");
			query.append("	SELECT DISTINCT EMP_NO FROM EMP_WORK_HIST");
			query.append("	WHERE WORK_END_DATE = to_date('9999-12-31','YYYY-MM-DD')");
			query.append("	AND BIZ_FILD = 'd' AND CNTR_NO='"+userCntrNo+"' ) cm ");
			query.append("	ON e.EMP_NO = cm.EMP_NO");
			
			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String[] bdate = rs.getString("bdate").split(" ");
				
				model1.addRow(new Object[] {rs.getString("name"),bdate[0]});
			}
		
			
		}
		catch(SQLException e1){
			e1.printStackTrace();
		}
		
		disconnection();
	}
	
    // 데이터베이스 연결

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

    // 데이터베이스 연결 해제
    public void disconnection() {

        try {

                 if(pstmt != null) pstmt.close();

                 if(rs != null) rs.close();

                 if(con != null) con.close();

        } catch (SQLException e) {
        	e.printStackTrace();
        }

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
				int clickedRow = eVtrnList.getSelectedRow();
				String vtrnName = (String) eVtrnList.getValueAt(clickedRow, 0);
//				cntrManagerBdate = (String) eCenterManagerList.getValueAt(clickedRow, 1);
				xVtrn.setText(vtrnName);
				dispose();
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
				int clickedRow = eVtrnList.getSelectedRow();
				vtrnBdate = (String)eVtrnList.getValueAt(clickedRow, 1);
			}
		}
		
	}

	public String getVtrnBdate() {
		return vtrnBdate;
	}

	private void ChangeFont(JComponent[] comps, Font font) {
    	for(JComponent comp: comps) {
    		comp.setFont(font);
    	}
    }
    
	
	public static void main(String[] args) {
		new VtrnSearch(null,null);
	}
}
