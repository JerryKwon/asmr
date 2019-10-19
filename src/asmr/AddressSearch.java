package asmr;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import javax.swing.JTextField;

public class AddressSearch extends JFrame {
	private JLabel vCityProv, vSgg, vRoadNm, vBldgNum, vRoadNmAddr1, vRoadNmAddr2, vDtilAddr;
	private JTextField xRoadNm, xBldgNum, xRoadNmAddr, xDtilAddr, xAddress;
	private JButton search, confirm, cancel;
	private JComboBox<String> cbCityProv,cbSgg;
	
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
	
	public AddressSearch(JTextField xAddress) {
		// TODO Auto-generated constructor stub
		this.xAddress = xAddress;
		
		gridBagLayout = new GridBagLayout();
		gridBagConstraints = new GridBagConstraints();
		
		addrSearchCbListener = new AddrSearchCbListener();
		addrSearchButtonListener = new AddrSearchButtonListener();
		
		vCityProv = new JLabel("시도*");
		cbCityProv = new JComboBox<String>();
//		cbCityProv.setSelectedIndex(anIndex);
		cbCityProv.addItemListener(addrSearchCbListener);
//		cbCityProv.addActionListener(addrSearchCbActionListener);
		
		vSgg= new JLabel("시군구*");
		cbSgg = new JComboBox<String>();
		
		vRoadNm = new JLabel("도로명*");
		xRoadNm = new JTextField(10);
		
		vBldgNum = new JLabel("건물번호*");
		xBldgNum = new JTextField(10);
		
		search = new JButton("검색");
		search.setBackground(blue);
		search.setForeground(white);
		search.addActionListener(addrSearchButtonListener);
		
		vRoadNmAddr1 = new JLabel("도로명주소");
		vRoadNmAddr1.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
		
		xRoadNmAddr = new JTextField(15);
//		xRoadNmAddr.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, right));
		xRoadNmAddr.setEditable(false);
		
		vRoadNmAddr2 = new JLabel("");
		vRoadNmAddr2.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
		
		vDtilAddr = new JLabel("상세주소");
		xDtilAddr = new JTextField(10);
		
		confirm = new JButton("확인");
		confirm.setBackground(blue);
		confirm.setForeground(white);
		confirm.addActionListener(addrSearchButtonListener);
		
		cancel = new JButton("취소");
		cancel.addActionListener(addrSearchButtonListener);
		
		JComponent[] vComps = {vCityProv,vSgg,vRoadNm,vBldgNum,vRoadNmAddr1,vDtilAddr};
		ChangeFont(vComps, new Font("나눔고딕", Font.PLAIN, 16));
		
		vRoadNmAddr2.setFont(new Font("나눔고딕", Font.PLAIN, 8));
		
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
		gridbagAdd(cbCityProv, 1, 0, 1, 1);

		gridbagAdd(vSgg, 0, 1, 1, 1);
		gridbagAdd(cbSgg, 1, 1, 2, 1);
		
		gridbagAdd(vRoadNm, 0, 2, 1, 1);
		gridbagAdd(xRoadNm, 1, 2, 1, 1);
		
		gridbagAdd(vBldgNum, 0, 3, 1, 1);
		gridbagAdd(xBldgNum, 1, 3, 1, 1);
		gridbagAdd(search, 2, 3, 1, 1);
		
//		BottomPanel bottomPanel = new BottomPanel();
//		gridbagAdd(bottomPanel, 0, 3, 1, 1);
//		
		gridbagAdd(vRoadNmAddr1, 0, 4, 1, 1);
		JPanel plainPanel = new JPanel();
		plainPanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
		plainPanel.add(xRoadNmAddr);
		plainPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
		gridbagAdd(plainPanel, 1, 4, 2, 1);
		
		gridbagAdd(vDtilAddr, 0, 5, 1, 1);
		gridbagAdd(xDtilAddr, 1, 5, 1, 1);
		
		JComponent[] buttons = {confirm,cancel};
		CombinePanel buttonPanel = new CombinePanel(buttons, 10, 0);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 70, 0, 0));
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


	
//	class BottomPanel extends JPanel{
//		public BottomPanel() {
//			// TODO Auto-generated constructor stub
//			btGridBagLayout = new GridBagLayout();
//			btGridBagConstraints = new GridBagConstraints();
//			
//			setLayout(btGridBagLayout);
//			
//			btGridBagConstraints.anchor = GridBagConstraints.WEST;
//			btGridBagConstraints.ipadx = 7;
//			
//			btGridBagConstraints.weightx=1.0;
//			btGridBagConstraints.weighty=1.0;
//			
//			btGridbagAdd(vRoadNmAddr1, 0, 0, 1, 1);
//			btGridbagAdd(vRoadNmAddr2, 1, 0, 1, 1);
//			
//			btGridbagAdd(vDtilAddr, 0, 1, 1, 1);
//			btGridbagAdd(xDtilAddr, 1, 1, 1, 1);
//			
//			JComponent[] buttons = {confirm,cancel};
//			CombinePanel buttonPanel = new CombinePanel(buttons, 10, 0);
//			btGridbagAdd(buttonPanel, 0, 2, 3, 1);
//		}
//	}
//	
	
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
    
    class AddrSearchButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource().equals(search)) {
				
				String cityProvNm = null;
				String sggNm = null;
				String roadNm = null;
				String bldgNum = null;
				
				if(cbCityProv.getSelectedItem().toString().isEmpty()
						|| cbSgg.getSelectedItem() == null
						|| xRoadNm.getText().toString().isEmpty()
						|| xBldgNum.getText().toString().isEmpty()) {
					JOptionPane.showMessageDialog(null, "필수사항을 모두 입력하세요","경고",JOptionPane.WARNING_MESSAGE);
				}
				
				else {
					cityProvNm = cbCityProv.getSelectedItem().toString();
					sggNm = cbSgg.getSelectedItem().toString();
					roadNm = xRoadNm.getText().toString();
					bldgNum = xBldgNum.getText().toString();
					
					connection();
					cbSgg.removeAllItems();
					
					try {
						StringBuffer query = new StringBuffer("SELECT rd.city_prov_nm||' '||rd.sgg_nm||' '||rd.road_nm||' '||ad.bldg_pr_num||' ('||ai.admn_d_nm||CASE when ai.sgg_bldg_nm is null then ')' ELSE ', '||ai.sgg_bldg_nm||')' END result "); 
								query.append("FROM(");  
								query.append("   SELECT *"); 
								query.append("   FROM road_nm_code"); 
								query.append("   WHERE CITY_PROV_NM='"+cityProvNm+"'"); 
								query.append("   AND SGG_NM='"+sggNm+"' AND ROAD_NM='"+roadNm+"'"); 
								query.append("   ) rd LEFT OUTER JOIN ");
								query.append("   address ad ");
								query.append("	ON rd.road_nm_code = ad.road_nm_code and rd.umd_sri_num = ad.umd_sri_num ");
								query.append("		LEFT OUTER JOIN ");
								query.append("	add_info ai ");
								query.append("	ON ad.man_num = ai.man_num ");
								query.append("WHERE ad.bldg_pr_num ='");
								query.append(bldgNum);
								query.append("'");
						pstmt = con.prepareStatement(query.toString());
						rs = pstmt.executeQuery();
						while(rs.next()) {
							xRoadNmAddr.setText(rs.getString("result"));
						}
					
					}
					catch(SQLException e1){
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "올바른 주소를 입력하세요","경고",JOptionPane.WARNING_MESSAGE);
					}
					
					disconnection();
					}
			}
			else if(e.getSource().equals(confirm)) {
				StringBuffer totalAddress = new StringBuffer(xRoadNmAddr.getText().toString());
				totalAddress.append(" ");
				totalAddress.append(xDtilAddr.getText().toString());
				xAddress.setText(totalAddress.toString());
				
				dispose();
			}
			else if(e.getSource().equals(cancel)) {
				dispose();
			}
		}
    	
    }
    
	public static void main(String[] args) {
//		new AddressSearch();
	}
}
