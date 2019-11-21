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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import asmr.NewCenterRegistration.NewCenterRegistButtonListener;

public class RscuSearch extends JFrame{
	JLabel vRscuList;
	JTable eRscuList;
	JButton confirm,cancel;
	JTextField xRscuNo;
	
	private String userCntrNo;
	
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "asmr";
	private String password = "asmr";
	
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private ResultSetMetaData rsmd = null;
	
	//구조번호를 담기 위한 배열
	private ArrayList<Integer> rscuNos = new ArrayList<Integer>();
	
	JScrollPane scrollPane = new JScrollPane();
	
	private final String[] col1 = {"구조일시","동물종류","크기","구조장소"};
	
	private DefaultTableModel model1 = new DefaultTableModel(col1,0);
	
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	
	RscuSearchButtonListener rscuSearchButtonListener;
	RscuSearchMouseListener rscuSearchMouseListener;
	
	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;
	
	public RscuSearch(JTextField xRscuNo, String cntrNo) {
		gridBagLayout = new GridBagLayout();		
		gridBagConstraints = new GridBagConstraints();
		
		rscuSearchButtonListener = new RscuSearchButtonListener();
		rscuSearchMouseListener = new RscuSearchMouseListener();
		
		userCntrNo = cntrNo;
		this.xRscuNo = xRscuNo;
		
		vRscuList = new JLabel("구조목록");
		vRscuList.setBorder(BorderFactory.createEmptyBorder(10,10,0,0));
		vRscuList.setFont(new Font("나눔고딕", Font.PLAIN, 16));
		
//		eRscuList = new JTable(model1);
		eRscuList = new JTable(model1) {
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
		eRscuList.getTableHeader().setReorderingAllowed(false);
		eRscuList.getTableHeader().setResizingAllowed(false);
		eRscuList.addMouseListener(rscuSearchMouseListener);
		scrollPane = new JScrollPane(eRscuList);
		scrollPane.setPreferredSize(new Dimension(525,100));
		
		eRscuList.getColumnModel().getColumn(0).setPreferredWidth(150);
		eRscuList.getColumnModel().getColumn(1).setPreferredWidth(75);
		eRscuList.getColumnModel().getColumn(2).setPreferredWidth(50);
		eRscuList.getColumnModel().getColumn(3).setPreferredWidth(250);
		
		
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
		this.getContentPane().setBackground(MainFrame.bgc);
		
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.ipadx = 7;
		
		gridBagConstraints.weightx=1.0;
		gridBagConstraints.weighty=1.0;
		
		gridbagAdd(vRscuList, 0, 0, 1, 1);
		
		JPanel plainPanel = new JPanel();
		plainPanel.add(scrollPane);
		plainPanel.setBackground(MainFrame.bgc);
		plainPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		gridbagAdd(plainPanel, 0, 1, 1, 1);
		
		Component[] cops = {confirm, cancel};
		CombinePanel buttonPanel = new CombinePanel(cops, 5, 5);
		buttonPanel.setBackground(MainFrame.bgc);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(0,200,0,0));
		gridbagAdd(buttonPanel, 0, 2, 1, 1);
		
		GetRscuList();
		
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
	
	class RscuSearchButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource().equals(confirm)) {
				if(eRscuList.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "아무것도 선택되지 않았습니다.", "에러", JOptionPane.WARNING_MESSAGE);
				}
				else {
					int clickedRow = eRscuList.getSelectedRow();
					int rscuNo = rscuNos.get(clickedRow);
					xRscuNo.setText(String.valueOf(rscuNo));
					dispose();
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
	
	private void GetRscuList() {
		connection();
		
		try {
			StringBuffer query= new StringBuffer("SELECT rs.RSCU_NO,rs.RSCU_DTTM,rp.ANML_KINDS,rp.ANML_SIZE,rs.RSCU_LOC ");
			query.append("FROM( ");
			query.append("	SELECT RSCU_NO,RSCU_DTTM,RSCU_LOC ");
			query.append("	FROM RSCU) rs INNER JOIN (SELECT ASSG_NO,RPRT_NO ");
			query.append("		FROM ASSG ");
			query.append("		WHERE ASSG_RES='a' AND CNTR_NO='"+userCntrNo+"' ) a ");
			query.append("		ON rs.RSCU_NO=a.ASSG_NO INNER JOIN (SELECT RPRT_NO,ANML_KINDS,ANML_SIZE ");
			query.append("			FROM RPRT) rp ");
			query.append("			ON a.RPRT_NO = rp.RPRT_NO LEFT OUTER JOIN ABAN a2 ON rs.RSCU_NO = a2.RSCU_NO ");
			query.append("WHERE a2.ABAN_NO is null ");
			
			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				
				rscuNos.add(rs.getInt("RSCU_NO"));
				
				model1.addRow(new Object[] {rs.getString("RSCU_DTTM"),rs.getString("ANML_KINDS"),rs.getString("ANML_SIZE"),rs.getString("RSCU_LOC")});
			}
		
			
		}catch(SQLException e) {
			e.printStackTrace();
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
	
	public static void main(String[] args) {
		new RscuSearch(new JTextField(),null);
	}
}
