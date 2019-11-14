package asmr;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class RprtAssignmentNorm extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "asmr";
	private String password = "asmr";
	
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private ResultSetMetaData rsmd = null;
	
	RprtAssignmentNormButtonListener rprtAssignmentNormButtonListener;
	
	private JLabel vRprtNo, vRprtDttm, vRprtName, vTelNo, vRprtTp, vWrtPrsnTp, vAnmlKinds,
	vAnmlSize, vExpln, vDscvDttm, vDscvLoc, vApprovalWaitList, vApprovalCompleteList, vRprtInfo;
	
	private JTextField xRprtNo, xRprtDttm, xRprtName, xTelNo,  xDscvDttm, xDscvLoc;
	
	private JTextArea xExpln;
	
	private JComboBox<String> cbRprtTp, cbWrtPrsnTp, cbAnmlKinds, cbAnmlSize;
	
	private JTable eApprovalWaitList, eApprovalCompleteList;
	
	private JScrollPane scrollpane1, scrollpane2, rprtContentScroll;
	
	private String[] rprtDiv = {"발견", "인계"};
	private String[] wrtPrsnDiv = {"직원","사용자"};
	private String[] anmlDiv = {"개", "고양이"};
	private String[] anmlSizeDiv = {"대", "중", "소"};
	
	private BufferedImage buttonIcon;
	
	private JButton previous, next, Imagebutton, regis;
	
	GridBagLayout gridbaglayout;
	GridBagConstraints gridbagconstraints;
	
	ApprovalWaitListMouseListener approvalWaitListMouseListener;
	
	
	private final String[] col1 = {"신고일시","동물종류","동물크기","설명","배정결과"};
	private final String[] col2 = {"신고일시","동물종류","동물크기","설명"};
	
	private DefaultTableModel model1 = new DefaultTableModel(col1,0);
	private DefaultTableModel model2 = new DefaultTableModel(col2,0);
	
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	
	static List<Map<String, Serializable>> assgRprtListData ;
	static List<Map<String, Serializable>> apprAssgListData ;
	private JComboBox<String> combobox;
	static int regisRow;
	
	//RprtRegisterButtonListener rprtRegisterButtonListener;
	
	public RprtAssignmentNorm() throws IOException {
		
		combobox = new JComboBox<String>();
		combobox.addMouseListener(approvalWaitListMouseListener);
		  
		gridbaglayout = new GridBagLayout();
		gridbagconstraints = new GridBagConstraints();
		
		//rprtRegisterButtonListener = new RprtRegisterButtonListener();
		rprtAssignmentNormButtonListener = new RprtAssignmentNormButtonListener();
		approvalWaitListMouseListener = new ApprovalWaitListMouseListener();
		
		// eRprtList, eCageList
		
		vApprovalWaitList = new JLabel("승인대기목록");
		vApprovalWaitList.setFont(new Font("나눔고딕", Font.BOLD, 24));
		
		eApprovalWaitList = new JTable(model1){
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return true;               
	        };
	    };
	    model1.fireTableDataChanged();
		eApprovalWaitList.addMouseListener(approvalWaitListMouseListener);
		scrollpane1 = new JScrollPane(eApprovalWaitList);
		scrollpane1.setPreferredSize(new Dimension(600,200));
		
		vApprovalCompleteList = new JLabel("승인완료목록");
		vApprovalCompleteList.setFont(new Font("나눔고딕", Font.BOLD, 24));
		
		eApprovalCompleteList = new JTable(model2){
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
	    eApprovalCompleteList.addMouseListener(approvalWaitListMouseListener);
		scrollpane2 = new JScrollPane(eApprovalCompleteList);
		scrollpane2.setPreferredSize(new Dimension(600,200));
		
		vRprtInfo = new JLabel("신고정보");
		vRprtInfo.setFont(new Font("나눔고딕", Font.BOLD, 20));
		
		vRprtNo = new JLabel("신고번호");
		xRprtNo = new JTextField(20);
		xRprtNo.setEditable(false);
		
		vRprtDttm = new JLabel("신고일시");
		xRprtDttm = new JTextField(20);
		xRprtDttm.setEditable(false);
		
		vRprtName = new JLabel("신고자명");
		xRprtName = new JTextField(20);
		xRprtName.setEditable(false);
		
		vTelNo = new JLabel("연락처");
		xTelNo = new JTextField(20);
		xTelNo.setEditable(false);
		
		vRprtTp = new JLabel("신고구분");
		cbRprtTp = new JComboBox<String>(rprtDiv);
		cbRprtTp.setEnabled(false);
		
		vWrtPrsnTp = new JLabel("작성자구분");
		cbWrtPrsnTp = new JComboBox<String>(wrtPrsnDiv);
		cbWrtPrsnTp.setEnabled(false);
		
		vAnmlKinds = new JLabel("동물종류");
		cbAnmlKinds = new JComboBox<String>(anmlDiv);
		cbAnmlKinds.setEnabled(false);
		
		vAnmlSize = new JLabel("동물크기");
		cbAnmlSize = new JComboBox<String>(anmlSizeDiv);
		cbAnmlSize.setEnabled(false);
		
		vExpln = new JLabel("설명");
		xExpln = new JTextArea(4,75);
		xExpln.setEditable(false);
		rprtContentScroll = new JScrollPane(xExpln);
	    rprtContentScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		vDscvDttm = new JLabel("발견일시");
		xDscvDttm = new JTextField(20);
		xDscvDttm.setEditable(false);
		
		vDscvLoc = new JLabel("발견장소");
		xDscvLoc = new JTextField(20);
		xDscvLoc.setEditable(false);
		
		buttonIcon = ImageIO.read(new File("./images/cat1.png"));
		Imagebutton = new JButton(new ImageIcon(buttonIcon));
		Imagebutton.setBorderPainted(false);
		Imagebutton.setContentAreaFilled(false);
		Imagebutton.setFocusPainted(false);
		
		regis = new JButton("등록");
		regis.setBackground(blue);
		regis.setForeground(white);
		regis.addActionListener(rprtAssignmentNormButtonListener);
		regis.setFont(new Font("나눔고딕", Font.BOLD, 16));
		
		previous = new JButton("<<");
		previous.addActionListener(rprtAssignmentNormButtonListener);
		previous.setFont(new Font("나눔고딕", Font.BOLD, 16));
		next = new JButton(">>");
		next.addActionListener(rprtAssignmentNormButtonListener);
		next.setFont(new Font("나눔고딕", Font.BOLD, 16));
		
		
		
		JComponent[] vComps = {vRprtNo, vRprtDttm, vRprtName, vTelNo, vRprtTp, vWrtPrsnTp, vAnmlKinds,
				vAnmlSize, vExpln, vDscvDttm, vDscvLoc};
		ChangeFont(vComps, new Font("나눔고딕", Font.PLAIN, 16));
		
		
		
		changeCellEditor(eApprovalWaitList, eApprovalWaitList.getColumnModel().getColumn(4));
		
		assgRprtListData = RprtData.getAssgRprtList();
		apprAssgListData = RprtData.getApprAssgList();
		
		
		getData();
		getApprData();
		RprtAssignmentNormView();
	}
	
	private void RprtAssignmentNormView() {
		
		//setTitle("신고배정_일반센터");	
		
		gridbagconstraints.anchor = GridBagConstraints.WEST;
		gridbagconstraints.ipadx = 7;
		
		gridbagconstraints.weightx=1.0;
		gridbagconstraints.weighty=1.0;
		
		setLayout(gridbaglayout);

		gridbagAdd(vApprovalWaitList, 0, 0, 1, 1);
		gridbagAdd(vApprovalCompleteList, 4, 0, 1, 1);
		gridbagAdd(regis, 7,0,1,1);
		
		gridbagAdd(scrollpane1, 0, 1, 4, 1);
		gridbagAdd(scrollpane2, 4, 1, 4, 1);
		
		gridbagAdd(vRprtInfo, 0, 6, 1, 1);
		
		gridbagAdd(vRprtNo, 0, 7, 1, 1);
		gridbagAdd(xRprtNo, 2, 7, 1, 1);
		//신고일
		gridbagAdd(vRprtDttm, 4, 7, 1, 1);
		gridbagAdd(xRprtDttm, 6, 7, 1, 1);
		
		gridbagAdd(vRprtName, 0, 8, 1, 1);
		gridbagAdd(xRprtName, 2, 8, 1, 1);
		
		gridbagAdd(vTelNo, 4, 8, 1, 1);
		gridbagAdd(xTelNo, 6, 8, 1, 1);
		
		gridbagAdd(vRprtTp, 0, 9, 1, 1);
		gridbagAdd(cbRprtTp, 2, 9, 1, 1);
		gridbagAdd(vWrtPrsnTp, 4, 9, 1, 1);
		gridbagAdd(cbWrtPrsnTp, 6, 9, 1, 1);
		
		gridbagAdd(vAnmlKinds, 0, 10, 1, 1);
		gridbagAdd(cbAnmlKinds, 2, 10, 1, 1);
		gridbagAdd(vAnmlSize, 4, 10, 1, 1);
		gridbagAdd(cbAnmlSize, 6, 10, 1, 1);
		
		gridbagAdd(vExpln, 0,11,1,1);
		gridbagAdd(xExpln, 2,11,6,1);

		gridbagAdd(vDscvDttm, 0, 12, 1, 1);
		gridbagAdd(xDscvDttm, 2, 12, 1, 1);
		
		gridbagAdd(vDscvLoc, 0, 13, 1, 1);
		gridbagAdd(xDscvLoc, 2, 13, 1, 1);
		
		gridbagAdd(Imagebutton, 11,7,5,3);
		
		gridbagAdd(previous, 12,13,1,3);
		gridbagAdd(next, 13,13,1,3);
		
		
		gridbagconstraints.anchor = GridBagConstraints.CENTER;

		//pack();
		//setResizable(false);
		//setVisible(true);
		
	}
	
	class RprtAssignmentNormButtonListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource().equals(regis)) {
				try {
					new RscuRegisPopup();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}
			else if (e.getSource().equals(previous)) {
				
			}
			else if(e.getSource().equals(next)) {
				
			}
		}
		
	}
	
    private void ChangeFont(JComponent[] comps, Font font) {
    	for(JComponent comp: comps) {
    		comp.setFont(font);
    	}
    }
    
    void changeCellEditor(JTable table, TableColumn column) {

//        for(int i=0;i<RprtData.getCntrList().size();i++) {
//        	combobox.addItem(RprtData.getCntrList().get(i));
//        }

        combobox.addItem("승인");
        combobox.addItem("반려");
        

        column.setCellEditor(new DefaultCellEditor(combobox));

        DefaultTableCellRenderer renderer =
                new DefaultTableCellRenderer();
        renderer.setToolTipText("클릭하면 콤보박스로 변경됩니다.");
        column.setCellRenderer(renderer);
}
	
	class ApprovalWaitListMouseListener extends MouseAdapter{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			super.mouseClicked(e);
			if(e.getButton()==1) {
				int clickedRow = eApprovalWaitList.getSelectedRow();
				String rprtDttm = (String)eApprovalWaitList.getValueAt(clickedRow, 0);	
				GetRprt(rprtDttm);

				
				int column = 4;
				// 센터에서 배정결과정보 담기.
				String assgNo = (String) assgRprtListData.get(clickedRow).get("배정번호");
				String value = (String) eApprovalWaitList.getModel().getValueAt(clickedRow, column);
				
				SetAssgRes(assgNo,value);
				
				
				
//				int row = eApprovalCompleteList.getSelectedRow();
				String dttm = (String)eApprovalCompleteList.getValueAt(regisRow, 0);
				GetRprt(dttm);

			}
			
//			else if(e.getButton()==2) {
//				int clickedRow = eApprovalWaitList.getSelectedRow();
//				String rprtDttm = (String)eApprovalWaitList.getValueAt(clickedRow, 0);				
//				GetRprt(rprtDttm);
//
//				int column = 4;
//				// 센터에서 배정결과정보 담기.
//				String assgNo = (String) assgRprtListData.get(clickedRow).get("배정번호");
//				String value = (String) eApprovalWaitList.getModel().getValueAt(clickedRow, column);
//				
//				SetAssgRes(assgNo,value);
//				
//				int row = eApprovalCompleteList.getSelectedRow();
//				String dttm = (String)eApprovalCompleteList.getValueAt(row, 0);
//				GetRprt(dttm);
//
//			}
		}
		
		private void SetAssgRes(String assgno, String value) {
			connection();
			
			value = (String)combobox.getSelectedItem();
			
			String engRes = null;
			
			switch(value) {
			case "승인":
				engRes = "a";
				break;
			case "반려":
				engRes = "d";
				break;
			}
					
			try {
				
				StringBuffer query= new StringBuffer("update assg ");
				query.append("set assg_res ='"+engRes+"', ");
				query.append("	  appr_dttm = sysdate ");
				query.append("where assg_no = '"+assgno+"' ");
				
				pstmt = con.prepareStatement(query.toString());
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
  					con.commit();
  				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			disconnection();
			
		}
		
	}
	
	
	
	
	private void GetRprt(String rprtDttm) {
		   
		   connection();
		   try {
			   
			   		   
//				StringBuffer query= new StringBuffer("SELECT R.RPRT_NO, R.RPRT_DTTM, C.CUST_NAME, C.TEL_NO, R.RPRT_TP, C.CUST_TP, R.ANML_KINDS, R.ANML_SIZE, R.EXPLN, R.DSCV_DTTM, R.DSCV_LOC ");
//				query.append("FROM (SELECT R.RPRT_NO, R.RPRT_DTTM, R.RPRT_TP, R.ANML_KINDS, R.ANML_SIZE, R.EXPLN, R.DSCV_DTTM, R.DSCV_LOC,  PATH, R.RPRT_PRSN_NO ");
//				query.append("FROM RPRT_PIC P ");
//				query.append("JOIN RPRT R ");
//				query.append("ON R.RPRT_NO = P.RPRT_NO) R ");
//				query.append("JOIN CUST C ");
//				query.append("ON R.RPRT_PRSN_NO = C.CUST_NO ");
//				query.append("WHERE RPRT_DTTM = to_date('"+rprtDttm+"','YYYY-MM-DD hh24:mi:ss') ");

			   StringBuffer query= new StringBuffer("SELECT R.RPRT_NO, R.RPRT_DTTM, C.CUST_NAME, C.TEL_NO, R.RPRT_TP, C.CUST_TP, R.ANML_KINDS, R.ANML_SIZE, R.EXPLN, R.DSCV_DTTM, R.DSCV_LOC ");
			   query.append("FROM RPRT R ");
			   query.append("JOIN CUST C ");
			   query.append("ON R.RPRT_PRSN_NO = C.CUST_NO ");
			   query.append("WHERE RPRT_DTTM = to_date('"+rprtDttm+"','YYYY-MM-DD hh24:mi:ss') ");

					
				pstmt = con.prepareStatement(query.toString());
				rs = pstmt.executeQuery();
				
//				StringBuffer query2 = new StringBuffer("select rprt.rprt_no, path ");
//				query2.append("from rprt_pic ");
//				query2.append("join rprt ");
//				query2.append("on rprt.rprt_no = rprt_pic.rprt_no ");
//				
//				pstmt = con.prepareStatement(query2.toString());
//				rs = pstmt.executeQuery();
//				if(rs.next()) {
//					con.commit();
//				}

				
				while(rs.next()) {

					String rprtType = rs.getString("RPRT_TP");
					String korRprtType = null;
					
					String custType = rs.getString("CUST_TP");
					String korcustType = null;
					
					String anmlKindsType = rs.getString("ANML_KINDS");
					String korAnmlKindsType = null;
					
					String anmlSizeType = rs.getString("ANML_SIZE");
					String korAnmlSizeType = null;
					
//					String xPath = rs.getString("PATH");
					
					switch(rprtType) {
					case "d":
						korRprtType = "발견";
						break;
					case "h":
						korRprtType = "인계";
						break;
					}
					
					switch(custType) {
					case "m":
						korcustType = "회원";
						break;
					case "n":
						korcustType = "비회원";
						break;
					}
					
					switch(anmlKindsType) {
					case "d":
						korAnmlKindsType = "개";
						break;
					case "c":
						korAnmlKindsType = "고양이";
						break;
					case "e":
						korAnmlKindsType = "기타";
						break;
					}
					
					switch(anmlSizeType) {
					case "b":
						korAnmlSizeType = "대";
						break;
					case "m":
						korAnmlSizeType = "중";
						break;
					case "s":
						korAnmlSizeType = "소";
						break;
					}
//		 R.EXPLN, R.DSCV_DTTM, R.DSCV_LOC
					xRprtNo.setText(rs.getString("RPRT_NO"));
					xRprtDttm.setText(rs.getString("RPRT_DTTM"));
					xRprtName.setText(rs.getString("CUST_NAME"));
					xTelNo.setText(rs.getString("TEL_NO"));
					cbRprtTp.setSelectedItem(korRprtType);
					cbWrtPrsnTp.setSelectedItem(korcustType);
					cbAnmlKinds.setSelectedItem(korAnmlKindsType);
					cbAnmlSize.setSelectedItem(korAnmlSizeType);
					xExpln.setText(rs.getString("EXPLN"));
					xDscvDttm.setText(rs.getString("DSCV_DTTM"));
					xDscvLoc.setText(rs.getString("DSCV_LOC"));
//					xPath.setText(rs.getString("path"));
					
//				    buttonIcon = ImageIO.read(new File(xPath));
					
//					xPath = rs.getString("PATH");
					
					
					
				}
					
			}catch(Exception e2) {
				e2.printStackTrace();
			}
			
			disconnection();
		   
		   
		   
	   }
	
	
	
	
    void getData() {
    	
        for(int i=0; i < assgRprtListData.size(); i++) {
        	
           model1.addRow(new Object[] {
        		 assgRprtListData.get(i).get("신고일시"),
                 assgRprtListData.get(i).get("동물종류"),
                 assgRprtListData.get(i).get("동물크기"),
                 assgRprtListData.get(i).get("설명")
                 
                 
//                 rprtListData.get(i).get("배정센터명")
           });
        }
  }
    
    void getApprData() {
    	
        for(int i=0; i < apprAssgListData.size(); i++) {
        	
           model2.addRow(new Object[] {
        		 apprAssgListData.get(i).get("신고일시"),
        		 apprAssgListData.get(i).get("동물종류"),
        		 apprAssgListData.get(i).get("동물크기"),
        		 apprAssgListData.get(i).get("설명")
                 
                 
//                 rprtListData.get(i).get("배정센터명")
           });
        }
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

	




	private void gridbagAdd(Component c, int x, int y, int w, int h) {			
		
		gridbagconstraints.gridx = x;		
		gridbagconstraints.gridy = y; 		
	      //가장 왼쪽 위 gridx, gridy값은 0 			
				
		gridbagconstraints.gridwidth  = w;	//넓이	
		gridbagconstraints.gridheight = h;	//높이	
	     			
	      			
	    gridbaglayout.setConstraints(c, gridbagconstraints); //컴포넌트를 컴포넌트 위치+크기 정보에 따라 GridBagLayout에 배치			
				
	   add(c);			
				
	   }
	

	public static void main(String[] args) throws IOException {
		new RprtAssignmentNorm();
//		System.out.println(assgRprtListData);
	}

}






