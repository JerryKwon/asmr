package asmr;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

public class RscuRegisPopup extends JFrame {
	
	static String rscuAssgDttm = RprtAssignmentNorm.rscuAssgDttm;
	
	private static String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private static String user = "asmr";
	private static String password = "asmr";
	
	private static Connection con = null;
	private static PreparedStatement pstmt = null;
	private static ResultSet rs = null;
	private static ResultSetMetaData rsmd = null;
	
	RscuRegisPopupButtonListener rscuRegisPopupButtonListener;
	
	private JLabel vRscuRegis, vRscuDttm, vRscuLoc, vRepRscuCrewName;
	
	private static JTextField xRscuDttm;
	private static JTextField xRscuLoc;
	private static JTextField xRepRscuCrewName;
	
	private JButton confirm, cancel, imageButton, btnSearch;
	
//	private BufferedImage buttonIcon;
	
	private static JDateChooser chooser;
	
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	private Color black = new Color(0,0,0);
	
	GridBagLayout gridbagLayout;
	GridBagConstraints gridbagConstraints;

	public RscuRegisPopup() throws IOException  {
		
		rscuRegisPopupButtonListener = new RscuRegisPopupButtonListener();
		
		gridbagLayout = new GridBagLayout();
		gridbagConstraints = new GridBagConstraints();
		
//		vRscuRegis = new JLabel("구조등록");
//		vRscuRegis.setFont(new Font("나눔고딕", Font.BOLD, 24));
		
		vRscuDttm = new JLabel("구조일시");
		vRscuDttm.setFont(new Font("나눔고딕", Font.PLAIN, 16));
		xRscuDttm = new JTextField(20);
		xRscuDttm.setEditable(false);
		
		vRscuLoc = new JLabel("구조장소");
		vRscuLoc.setFont(new Font("나눔고딕", Font.PLAIN, 16));
		xRscuLoc = new JTextField(20);
		
		vRepRscuCrewName = new JLabel("대표구조대원명");
		vRepRscuCrewName.setFont(new Font("나눔고딕", Font.PLAIN, 16));
		xRepRscuCrewName = new JTextField(20);
		xRepRscuCrewName.setText(EmpData.getEmpName(Login.getEmpNo()));
		xRepRscuCrewName.setEditable(false);
		
		btnSearch = new JButton("검색");
		btnSearch.setBackground(blue);
		btnSearch.setForeground(white);
		
		confirm = new JButton("확인");
		confirm.setFont(new Font("나눔고딕", Font.BOLD, 16));
		confirm.setBackground(blue);
		confirm.setForeground(white);
		confirm.addActionListener(rscuRegisPopupButtonListener);
		
		cancel = new JButton("취소");
		cancel.setFont(new Font("나눔고딕", Font.BOLD, 16));
		cancel.setBackground(white);
		cancel.setForeground(black);
		cancel.addActionListener(rscuRegisPopupButtonListener);
		
//		buttonIcon = ImageIO.read(new File("./images/cal1.png"));
//		imageButton = new JButton(new ImageIcon(buttonIcon));
//		imageButton.setBorderPainted(false);
//		imageButton.setContentAreaFilled(false);
//		imageButton.setFocusPainted(false);
		
		LocalDate now = LocalDate.now();
		Date date = Date.valueOf(now);
		chooser = new JDateChooser(date,"yyyy-MM-dd");
		chooser.setEnabled(true);
		
		RscuRegisPopupView();
	}
	
	private void RscuRegisPopupView() {
		this.getContentPane().setBackground(MainFrame.bgc);
		
		setTitle("구조등록");
		
		gridbagConstraints.anchor = GridBagConstraints.WEST;
		gridbagConstraints.ipadx = 7;

		gridbagConstraints.weightx=1.0;
		gridbagConstraints.weighty=1.0;

		gridbagConstraints.insets = new Insets(5, 5, 5, 5);
		
		setLayout(gridbagLayout);
		
//		gridbagAdd(vRscuRegis, 0, 0, 1, 1);
		
		gridbagAdd(vRscuDttm, 0, 1, 1, 1);
		gridbagAdd(chooser, 2, 1, 2, 1);
//		gridbagAdd(imageButton, 5, 1, 1, 1);
		
		gridbagAdd(vRscuLoc, 0, 2, 1, 1);
		gridbagAdd(xRscuLoc, 2, 2, 2, 1);
		
		gridbagAdd(vRepRscuCrewName, 0, 4, 1, 1);
		gridbagAdd(xRepRscuCrewName, 2, 4, 1, 1);
		
//		gridbagAdd(btnSearch, 4, 4, 1, 1);
		
		JPanel plainPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,20,0));
		plainPanel.add(confirm);
		plainPanel.add(cancel);
		plainPanel.setBackground(MainFrame.bgc);
		plainPanel.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 0));

		gridbagAdd(plainPanel, 0, 5, 5, 1);
		
//		gridbagAdd(confirm,2, 5, 1, 1);
//		gridbagAdd(cancel, 3, 5, 1, 1);
		
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
	}
	private void gridbagAdd(Component c, int x, int y, int w, int h) {			
		
		gridbagConstraints.gridx = x;		
		gridbagConstraints.gridy = y; 		
	      //가장 왼쪽 위 gridx, gridy값은 0 			
				
		gridbagConstraints.gridwidth  = w;	//넓이	
		gridbagConstraints.gridheight = h;	//높이	
	     			
	      			
	    gridbagLayout.setConstraints(c, gridbagConstraints); //컴포넌트를 컴포넌트 위치+크기 정보에 따라 GridBagLayout에 배치			
				
	   add(c);			
				
	   }		
	
    class RscuRegisPopupButtonListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource().equals(confirm)) {	
				AddRscu();
				JOptionPane.showMessageDialog(null, "구조가 등록되었습니다.", "구조등록확인", JOptionPane.PLAIN_MESSAGE);
				dispose();
				
			}
			else if(e.getSource().equals(cancel)) {
				dispose();
				
			}
			else if(e.getSource().equals(btnSearch)) {
			
				
			}
		}
		
	}
    
	static String getAssgNo(String assgDttm){

		String query = "SELECT assg_no FROM assg WHERE assg_dttm= to_date('"+assgDttm+"','YYYY-MM-DD hh24:mi:ss') ";
		String assgNo = "";
		try{
			pstmt = con.prepareStatement(query, rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				assgNo = rs.getString(1);
			}
		}catch(SQLException e){
			System.out.println("SELECT문 예외 발생");
			e.printStackTrace();
		}

		return assgNo;
	}
    
    
    
    
    static void AddRscu() {
    	connection();

		String rscuDttm = ((JTextField)chooser.getDateEditor().getUiComponent()).getText();
		String rscuLoc = xRscuLoc.getText();
		
//		String rscuCrew = xRepRscuCrewName.getText();
		
		String rscuNo =  Login.getEmpNo();
		
		String assgNo = getAssgNo(rscuAssgDttm);
		System.out.println(rscuAssgDttm);
		

		
    	
		try {
			StringBuffer query1 = new StringBuffer("INSERT INTO RSCU (RSCU_NO, RSCU_DTTM, RSCU_LOC, REP_RSCU_CREW_NO) ");
			query1.append("VALUES( ");
			query1.append("'"+assgNo+"', ");
			query1.append("to_date('"+rscuDttm+"','YYYY-MM-dd'), ");
			query1.append("'"+rscuLoc+"', ");
			query1.append("'"+rscuNo+"') ");


			
			pstmt = con.prepareStatement(query1.toString());
			System.out.println(query1);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				con.commit();
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		disconnection();
		
    	
    	
    }
    
 // 데이터베이스 연결

    public static void connection() {

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
    public static void disconnection() {

        try {

                 if(pstmt != null) pstmt.close();

                 if(rs != null) rs.close();

                 if(con != null) con.close();

        } catch (SQLException e) {
        	e.printStackTrace();
        }

    }
	
	public static void main(String[] args) throws IOException  {
		// TODO Auto-generated method stub
		
		new RscuRegisPopup();

	}

}
