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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import asmr.AddressSearch.AddrSearchButtonListener;
import asmr.AddressSearch.AddrSearchCbListener;
import asmr.AddressSearch.CombinePanel;

public class NewAddressSearch extends JFrame{
	private JLabel vCityProv, vSgg, vRoadNm, vBldgNum, vRoadNmAddr1, vRoadNmAddr2, vDtilAddr;
	private JTextField xRoadNm, xBldgNum, xRoadNmAddr, xDtilAddr, xAddress;
	private JButton search, confirm, cancel;
	private JComboBox<String> cbCityProv,cbSgg;
	
	private JTable eAddressList;
	private JScrollPane scrollPane;
	private final String[] col1 = {"주소","우편번호"};
	
	private DefaultTableModel model1 = new DefaultTableModel(col1,0);
	
	private String normalAddr = null;
	
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "addrmon";
	private String password = "addrmon";
	
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private ResultSetMetaData rsmd = null;
	
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	
	private boolean isFirst = true;
	
	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;
	
	GridBagLayout btGridBagLayout;
	GridBagConstraints btGridBagConstraints;
	
	AddrSearchCbListener addrSearchCbListener;
	AddrSearchButtonListener addrSearchButtonListener;
	AddrSearchMouseListener addrSearchMouseListener;
	
	public NewAddressSearch(JTextField xAddress) {
		// TODO Auto-generated constructor stub
		this.xAddress = xAddress;
		
		gridBagLayout = new GridBagLayout();
		gridBagConstraints = new GridBagConstraints();
		
		addrSearchCbListener = new AddrSearchCbListener();
		addrSearchButtonListener = new AddrSearchButtonListener();
		addrSearchMouseListener = new AddrSearchMouseListener();
		
		vCityProv = new JLabel("시도*");
		vCityProv.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		cbCityProv = new JComboBox<String>();
//		cbCityProv.setSelectedIndex(anIndex);
		cbCityProv.addItemListener(addrSearchCbListener);
//		cbCityProv.addActionListener(addrSearchCbActionListener);
		
		vSgg= new JLabel("시군구*");
		vSgg.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		cbSgg = new JComboBox<String>();
		
		vRoadNm = new JLabel("도로명*/건물번호");
		vRoadNm.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		xRoadNm = new JTextField(10);
		
//		vBldgNum = new JLabel("건물번호*");
		xBldgNum = new JTextField(3);
		
		search = new JButton("검색");
		search.setBackground(blue);
		search.setForeground(white);
		search.addActionListener(addrSearchButtonListener);
		
		eAddressList = new JTable(model1){
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
		eAddressList.addMouseListener(addrSearchMouseListener);
		scrollPane = new JScrollPane(eAddressList);
		scrollPane.setPreferredSize(new Dimension(425,200));
		eAddressList.getColumnModel().getColumn(0).setPreferredWidth(350);
		eAddressList.getColumnModel().getColumn(1).setPreferredWidth(75);
	    
//		vRoadNmAddr1 = new JLabel("도로명주소");
//		vRoadNmAddr1.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
		
//		xRoadNmAddr = new JTextField(15);
//		xRoadNmAddr.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, right));
//		xRoadNmAddr.setEditable(false);
		
//		vRoadNmAddr2 = new JLabel("");
//		vRoadNmAddr2.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
		
		vDtilAddr = new JLabel("상세주소");
		vDtilAddr.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		xDtilAddr = new JTextField(15);
		
		confirm = new JButton("확인");
		confirm.setBackground(blue);
		confirm.setForeground(white);
		confirm.addActionListener(addrSearchButtonListener);
		
		cancel = new JButton("취소");
		cancel.addActionListener(addrSearchButtonListener);
		
		JComponent[] vComps = {vCityProv,vSgg,vRoadNm,vDtilAddr};
		ChangeFont(vComps, new Font("나눔고딕", Font.PLAIN, 16));
		
//		vRoadNmAddr2.setFont(new Font("나눔고딕", Font.PLAIN, 8));
		
		JComponent[] bComps = {search,confirm,cancel};
		ChangeFont(bComps, new Font("나눔고딕", Font.BOLD, 12));
		
		GetCityProv();
		
		AddressSearchView();
	}
	
	private void AddressSearchView() {
		setLayout(gridBagLayout);
		setTitle("주소검색");
		
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.ipadx = 7;
		
		gridBagConstraints.weightx=1.0;
		gridBagConstraints.weighty=1.0;
		
		gridbagAdd(vCityProv, 0, 0, 1, 1);
		gridbagAdd(cbCityProv, 1, 0, 2, 1);

		gridbagAdd(vSgg, 0, 1, 1, 1);
		gridbagAdd(cbSgg, 1, 1, 2, 1);
		
		gridbagAdd(vRoadNm, 0, 2, 1, 1);
		gridbagAdd(xRoadNm, 1, 2, 1, 1);
		
//		gridbagAdd(vBldgNum, 0, 3, 1, 1);
		gridbagAdd(xBldgNum, 2, 2, 1, 1);
		gridbagAdd(search, 3, 2, 1, 1);
		
//		BottomPanel bottomPanel = new BottomPanel();
//		gridbagAdd(bottomPanel, 0, 3, 1, 1);
//		
//		gridbagAdd(vRoadNmAddr1, 0, 4, 1, 1);
//		JPanel plainPanel = new JPanel();
//		plainPanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
//		plainPanel.add(xRoadNmAddr);
//		plainPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
//		gridbagAdd(plainPanel, 1, 4, 2, 1);
		
		JPanel plainPanel = new JPanel();
		plainPanel.add(scrollPane);
		gridbagAdd(plainPanel, 0, 4, 6, 1);
		
		gridbagAdd(vDtilAddr, 0, 5, 1, 1);
		gridbagAdd(xDtilAddr, 1, 5, 2, 1);
		
		JComponent[] buttons = {confirm,cancel};
		CombinePanel buttonPanel = new CombinePanel(buttons, 10, 0);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 160, 0, 0));
		gridbagAdd(buttonPanel, 0, 6, 3, 1);
		
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
	}
	
	private void GetCityProv() {
		connection();
		
		try {
			String query="select distinct city_prov_nm from road_nm_code order by 1";
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				cbCityProv.addItem(rs.getString("city_prov_nm"));
			}
		
		}
		catch(SQLException e){
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

	
	private void gridbagAdd(Component c, int x, int y, int w , int h) {
		
		gridBagConstraints.gridx = x;
		gridBagConstraints.gridy = y;
		gridBagConstraints.gridwidth = w;
		gridBagConstraints.gridheight = h;
		
		gridBagLayout.setConstraints(c, gridBagConstraints);
		
		add(c);
	}

	
	private void btGridbagAdd(Component c, int x, int y, int w , int h) {
		
		btGridBagConstraints.gridx = x;
		btGridBagConstraints.gridy = y;
		btGridBagConstraints.gridwidth = w;
		btGridBagConstraints.gridheight = h;
		
		btGridBagLayout.setConstraints(c, btGridBagConstraints);
		
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
	
    private void ChangeFont(JComponent[] comps, Font font) {
    	for(JComponent comp: comps) {
    		comp.setFont(font);
    	}
    }
    
    class AddrSearchCbListener implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			if(e.getStateChange()==ItemEvent.SELECTED) {				
				if(!isFirst) {
					String cityProvNm = cbCityProv.getSelectedItem().toString();
					connection();
					cbSgg.removeAllItems();
					
					try {
						String query="select distinct sgg_nm from road_nm_code where city_prov_nm='"+cityProvNm+"' order by 1";
						pstmt = con.prepareStatement(query);
						rs = pstmt.executeQuery();
						while(rs.next()) {
							cbSgg.addItem(rs.getString("sgg_nm"));
						}
					
					}
					catch(SQLException e1){
						e1.printStackTrace();
					}
					
					
					
					disconnection();
				}
				else isFirst=false;
			}
		}
    	
    }
    
    class AddrSearchMouseListener extends MouseAdapter{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			super.mouseClicked(e);
			
			if(e.getButton()==1) {
				int clickedRow = eAddressList.getSelectedRow();
				normalAddr = (String)eAddressList.getValueAt(clickedRow, 0);
			}
		}
    	
    }
    
    private void GetAddressWONum(String cityProvNm,String sggNm,String roadNm) {
    	connection();
		
		try {
			StringBuffer query = new StringBuffer("SELECT ROAD_ADDR||' '||BLDG_PR_NUM||CASE WHEN t2.SGG_BLDG_NM is null THEN ' ' ELSE ' ('||t2.SGG_BLDG_NM||') ' END ROAD_ADDR, POST_NUM "); 
					query.append("FROM( ");  
					query.append("   SELECT UMD_TYPE,to_number(regexp_replace(rd.ROAD_NM, '[^0-9]','')) subnum, rd.CITY_PROV_NM||' '||rd.SGG_NM||' '||rd.ROAD_NM ROAD_ADDR, to_number(nvl(ad.bldg_pr_num,'')) BLDG_PR_NUM, ai.SGG_BLDG_NM, ai.post_num POST_NUM "); 
					query.append("   	  FROM( ");
					query.append("   		SELECT * ");
					query.append("			FROM road_nm_code ");
					query.append("			WHERE CITY_PROV_NM='"+cityProvNm+"' ");
					query.append("			AND SGG_NM='"+sggNm+"' AND ROAD_NM LIKE '"+roadNm+"%') rd LEFT OUTER JOIN address ad ");
					query.append("				ON rd.road_nm_code = ad.road_nm_code and rd.umd_sri_num = ad.umd_sri_num ");
					query.append("				LEFT OUTER JOIN ADD_INFO ai on ad.MAN_NUM = ai.MAN_NUM ");
					query.append("ORDER BY UMD_TYPE DESC, subnum ASC, bldg_pr_num ASC ");
					query.append(")t2 ");
			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				model1.addRow(new Object[] {rs.getString("ROAD_ADDR"),rs.getString("POST_NUM")});
			}
		
		}
		catch(SQLException e1){
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "올바른 주소를 입력하세요","경고",JOptionPane.WARNING_MESSAGE);
		}
		
		disconnection();
    }
    
    private void GetAddressWNum(String cityProvNm,String sggNm,String roadNm,String bldgNum) {
    	connection();
		
		try {
			StringBuffer query = new StringBuffer("SELECT ROAD_ADDR||' '||BLDG_PR_NUM||CASE WHEN t2.SGG_BLDG_NM is null THEN ' ' ELSE ' ('||t2.SGG_BLDG_NM||') ' END ROAD_ADDR, POST_NUM "); 
			query.append("FROM( ");  
			query.append("   SELECT UMD_TYPE,to_number(regexp_replace(rd.ROAD_NM, '[^0-9]','')) subnum, rd.CITY_PROV_NM||' '||rd.SGG_NM||' '||rd.ROAD_NM ROAD_ADDR, to_number(nvl(ad.bldg_pr_num,'')) BLDG_PR_NUM, ai.SGG_BLDG_NM, ai.post_num POST_NUM "); 
			query.append("   	  FROM( ");
			query.append("   		SELECT * ");
			query.append("			FROM road_nm_code ");
			query.append("			WHERE CITY_PROV_NM='"+cityProvNm+"' ");
			query.append("			AND SGG_NM='"+sggNm+"' AND ROAD_NM LIKE '"+roadNm+"%') rd LEFT OUTER JOIN address ad ");
			query.append("				ON rd.road_nm_code = ad.road_nm_code and rd.umd_sri_num = ad.umd_sri_num ");
			query.append("				LEFT OUTER JOIN ADD_INFO ai on ad.MAN_NUM = ai.MAN_NUM ");
			query.append("			WHERE ad.bldg_pr_num='"+bldgNum+"' ");
			query.append("ORDER BY UMD_TYPE DESC, subnum ASC, bldg_pr_num ASC ");
			query.append(")t2 ");
			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				model1.addRow(new Object[] {rs.getString("ROAD_ADDR"),rs.getString("POST_NUM")});
			}
		
		}
		catch(SQLException e1){
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "올바른 주소를 입력하세요","경고",JOptionPane.WARNING_MESSAGE);
		}
		
		disconnection();
    }
    
    
    class AddrSearchButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource().equals(search)) {
				model1.setNumRows(0);
				
				String cityProvNm = null;
				String sggNm = null;
				String roadNm = null;
				String bldgNum = null;
				
				if(cbCityProv.getSelectedItem().toString().isEmpty()
						|| cbSgg.getSelectedItem() == null
						|| xRoadNm.getText().toString().isEmpty()) {
					JOptionPane.showMessageDialog(null, "필수사항을 모두 입력하세요","경고",JOptionPane.WARNING_MESSAGE);
				}
				
				else {
					
					cityProvNm = cbCityProv.getSelectedItem().toString();
					sggNm = cbSgg.getSelectedItem().toString();
					roadNm = xRoadNm.getText().toString();
					bldgNum = xBldgNum.getText().toString();
					
					bldgNum= bldgNum.trim();
					
					if(bldgNum.isEmpty()) {
						GetAddressWONum(cityProvNm,sggNm,roadNm);
					}
					
					else {
						GetAddressWNum(cityProvNm,sggNm,roadNm,bldgNum);
					}
		
				}
			}
			else if(e.getSource().equals(confirm)) {
				try {
					StringBuffer totalAddress = new StringBuffer(normalAddr.trim());
					totalAddress.append(" ");
					totalAddress.append(xDtilAddr.getText().toString());
					xAddress.setText(totalAddress.toString().trim());
					
					dispose();	
				}catch(NullPointerException e2){
					if(eAddressList.getRowCount()==0)
						JOptionPane.showMessageDialog(null, "주소를 먼저 검색하세요", "안내", JOptionPane.QUESTION_MESSAGE);
					else {
						if(eAddressList.getSelectedRow()==-1)
							JOptionPane.showMessageDialog(null, "기본주소를 선택하세요	", "안내", JOptionPane.QUESTION_MESSAGE);
					}
				}					
			}
			else if(e.getSource().equals(cancel)) {
				dispose();
			}
		}
    	
    }
    public static void main(String[] args) {
		new NewAddressSearch(null);
	}
}
