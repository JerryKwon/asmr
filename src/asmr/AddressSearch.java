package asmr;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddressSearch extends JFrame {
	private JLabel vCityProv, vRoadNm, vBldgNum, vRoadNmAddr1, vRoadNmAddr2, vDtilAddr;
	private JTextField xRoadNm, xBldgNum, xDtilAddr;
	private JButton search, confirm, cancel;
	private JComboBox<String> cbCityProv;
	
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "addrmon";
	private String password = "addrmon";
	
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private ResultSetMetaData rsmd = null;
	
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	
	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;
	
	GridBagLayout btGridBagLayout;
	GridBagConstraints btGridBagConstraints;
	
	public AddressSearch() {
		// TODO Auto-generated constructor stub
		gridBagLayout = new GridBagLayout();
		gridBagConstraints = new GridBagConstraints();
		
		vCityProv = new JLabel("시도*");
		cbCityProv = new JComboBox<String>();
		
		vRoadNm = new JLabel("도로명*");
		xRoadNm = new JTextField(10);
		
		vBldgNum = new JLabel("건물번호*");
		xBldgNum = new JTextField(10);
		
		search = new JButton("검색");
		search.setBackground(blue);
		search.setForeground(white);
		
		vRoadNmAddr1 = new JLabel("도로명주소");
		vRoadNmAddr1.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
		vRoadNmAddr2 = new JLabel("");
		vRoadNmAddr2.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
		
		vDtilAddr = new JLabel("상세주소");
		xDtilAddr = new JTextField(10);
		
		confirm = new JButton("확인");
		confirm.setBackground(blue);
		confirm.setForeground(white);
		
		cancel = new JButton("취소");
		
		JComponent[] vComps = {vCityProv,vRoadNm,vBldgNum,vRoadNmAddr1,vRoadNmAddr2,vDtilAddr};
		ChangeFont(vComps, new Font("나눔고딕", Font.PLAIN, 16));
		
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
//		
		gridbagAdd(vRoadNm, 0, 1, 1, 1);
		gridbagAdd(xRoadNm, 1, 1, 1, 1);
		
		gridbagAdd(vBldgNum, 0, 2, 1, 1);
		gridbagAdd(xBldgNum, 1, 2, 1, 1);
		gridbagAdd(search, 2, 2, 1, 1);
		
//		BottomPanel bottomPanel = new BottomPanel();
//		gridbagAdd(bottomPanel, 0, 3, 1, 1);
//		
		gridbagAdd(vRoadNmAddr1, 0, 3, 1, 1);
		gridbagAdd(vRoadNmAddr2, 1, 3, 1, 1);
		
		gridbagAdd(vDtilAddr, 0, 4, 1, 1);
		gridbagAdd(xDtilAddr, 1, 4, 1, 1);
		
		JComponent[] buttons = {confirm,cancel};
		CombinePanel buttonPanel = new CombinePanel(buttons, 10, 0);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 70, 0, 0));
		gridbagAdd(buttonPanel, 0, 5, 3, 1);
		
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


    public void disconnection() {

        try {

                 if(pstmt != null) pstmt.close();

                 if(rs != null) rs.close();

                 if(con != null) con.close();

        } catch (SQLException e) {
        	e.printStackTrace();
        }

    }


	
	class BottomPanel extends JPanel{
		public BottomPanel() {
			// TODO Auto-generated constructor stub
			btGridBagLayout = new GridBagLayout();
			btGridBagConstraints = new GridBagConstraints();
			
			setLayout(btGridBagLayout);
			
			btGridBagConstraints.anchor = GridBagConstraints.WEST;
			btGridBagConstraints.ipadx = 7;
			
			btGridBagConstraints.weightx=1.0;
			btGridBagConstraints.weighty=1.0;
			
			btGridbagAdd(vRoadNmAddr1, 0, 0, 1, 1);
			btGridbagAdd(vRoadNmAddr2, 1, 0, 1, 1);
			
			btGridbagAdd(vDtilAddr, 0, 1, 1, 1);
			btGridbagAdd(xDtilAddr, 1, 1, 1, 1);
			
			JComponent[] buttons = {confirm,cancel};
			CombinePanel buttonPanel = new CombinePanel(buttons, 10, 0);
			btGridbagAdd(buttonPanel, 0, 2, 3, 1);
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
	
	public static void main(String[] args) {
		new AddressSearch();
	}
}
