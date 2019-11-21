package asmr;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
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
import javax.swing.JOptionPane;
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
	private static String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private static String user = "asmr";
	private static String password = "asmr";
	
	private static Connection con = null;
	private static PreparedStatement pstmt = null;
	private static ResultSet rs = null;
	private static ResultSetMetaData rsmd = null;
	
	RprtAssignmentNormButtonListener rprtAssignmentNormButtonListener;
	
	private JLabel vRprtNo, vRprtDttm, vRprtName, vTelNo, vRprtTp, vWrtPrsnTp, vAnmlKinds,
	vAnmlSize, vExpln, vDscvDttm, vDscvLoc, vApprovalWaitList, vApprovalCompleteList, vRprtInfo;
	
	private static JTextField xRprtNo;
	private static JTextField xRprtDttm;
	private static JTextField xRprtName;
	private static JTextField xTelNo;
	private static JTextField xDscvDttm;
	private static JTextField xDscvLoc;
	
	private static JTextArea xExpln;
	
	private static JComboBox<String> cbRprtTp, cbWrtPrsnTp, cbAnmlKinds, cbAnmlSize;
	
	private static JTable eApprovalWaitList;
	private static JTable eApprovalCompleteList;
	
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
	private final String[] col2 = {"배정일시","동물종류","동물크기","설명"};
	
	private DefaultTableModel model1 = new DefaultTableModel(col1,0);
	private DefaultTableModel model2 = new DefaultTableModel(col2,0);
	
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	
	static List<Map<String, Serializable>> assgRprtListData ;
	static List<Map<String, Serializable>> apprAssgListData ;
	private static JComboBox<String> combobox;
	static int regisRow;
	public static String assgClickedRow;
	public static String dttm;
	static String rscuAssgDttm = null;
	
	private static String picPath = "";
	
	private static ImageIcon noImageIcon;
	private static JLabel imageLabel;	
	
	
	//RprtRegisterButtonListener rprtRegisterButtonListener;
	
//	private String userWorkType;
//	
//	private String userCntrNo;
//	private String userEmpNo;
//	
//	private ArrayList<String> empNos;
//	private ArrayList<String> workStartDates;
//	private ArrayList<String> cntrNos;
	
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
		
		File input = new File("images/NoImage.png");
	    BufferedImage image = ImageIO.read(input);
	    BufferedImage resized = resize(image,200,200);
	    imageLabel = new JLabel();
	    noImageIcon = new ImageIcon(resized);
	    imageLabel.setIcon(noImageIcon);
		
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
		
//		userCntrNo = cntrNo;
//		userEmpNo = empNo;
		
//		userWorkType = GetWorkType(userCntrNo,userEmpNo); 
		
		
		getData();
		getApprData();
		RprtAssignmentNormView();
	}
	
	private void RprtAssignmentNormView() {
		setBackground(MainFrame.bgc);
		
		//setTitle("신고배정_일반센터");	
		gridbagconstraints.insets = new Insets(5,5,5,5);
		
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
		
		gridbagAdd(imageLabel, 11,7,5,3);
		
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
			String userWorkType = EmpData.getBiz(Login.getEmpNo());
			// TODO Auto-generated method stub
			if(e.getSource().equals(regis)) {
				if(userWorkType.equals("r")) {
					try {
						new RscuRegisPopup();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				else {
					JOptionPane.showMessageDialog(null, "구조등록권한이 없습니다.", "경고", JOptionPane.WARNING_MESSAGE);
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
	
	static class ApprovalWaitListMouseListener extends MouseAdapter{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			super.mouseClicked(e);
			if(eApprovalWaitList.getSelectedRow()!=-1) {
			if(e.getButton()==1) {
				int clickedRow = eApprovalWaitList.getSelectedRow();
				
//				int assgClickedRow = eApprovalCompleteList.getSelectedRow();
				
				String rprtDttm = (String)eApprovalWaitList.getValueAt(clickedRow, 0);	
				GetRprt(rprtDttm);
				
				picPath = RprtData.getPic(rprtDttm);
				
				try {
					File input = new File(picPath);
					BufferedImage image = ImageIO.read(input);
					BufferedImage resized = resize(image,200,200);
					ImageIcon icon = new ImageIcon(resized);
					imageLabel.setIcon(icon);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					imageLabel.setIcon(noImageIcon);
				}

				
				int column = 4;
				// 센터에서 배정결과정보 담기.
				String assgNo = (String) assgRprtListData.get(clickedRow).get("배정번호");
				String value = (String) eApprovalWaitList.getModel().getValueAt(clickedRow, column);
				
				SetAssgRes(assgNo,value);
				
				
				
//				int row = eApprovalCompleteList.getSelectedRow();
//				String dttm = (String)eApprovalCompleteList.getValueAt(assgClickedRow, 1);
//				System.out.println(dttm);
//				GetRprt(expln1);

				}
			}
			else {
				if(e.getButton()==1) {
//					int clickedRow = eApprovalWaitList.getSelectedRow();
					
					int clickedRow = eApprovalCompleteList.getSelectedRow();
					
					String assgDttm = (String)eApprovalCompleteList.getValueAt(clickedRow, 0);	
					GetAssg(assgDttm);
					rscuAssgDttm = assgDttm;
//					String assgNo1 = getAssgNo(assgDttm);
					
					picPath = RprtData.getAssgPic(assgDttm);
					
					try {
						File input = new File(picPath);
						BufferedImage image = ImageIO.read(input);
						BufferedImage resized = resize(image,200,200);
						ImageIcon icon = new ImageIcon(resized);
						imageLabel.setIcon(icon);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						imageLabel.setIcon(noImageIcon);
					}

					
//					int column = 4;
//					// 센터에서 배정결과정보 담기.
//					String assgNo = (String) assgRprtListData.get(clickedRow).get("배정번호");
//					String value = (String) eApprovalWaitList.getModel().getValueAt(clickedRow, column);
//					
//					SetAssgRes(assgNo,value);
					
					
					
//					int row = eApprovalCompleteList.getSelectedRow();
//					String dttm = (String)eApprovalCompleteList.getValueAt(assgClickedRow, 1);
//					System.out.println(dttm);
//					GetRprt(expln1);

					}
				
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
		
		static String getAssgNo(String assgDttm){
			connection(); 
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
			disconnection();
			return assgNo;
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
	
	private static void GetAssg(String assgDttm) {
//		SELECT R.RPRT_NO, R.RPRT_DTTM, C.CUST_NAME, C.TEL_NO, R.RPRT_TP, C.CUST_TP, R.ANML_KINDS, R.ANML_SIZE, R.EXPLN, R.DSCV_DTTM, R.DSCV_LOC
//		FROM RPRT R, CUST C, ASSG A
//		WHERE R.RPRT_PRSN_NO = C.CUST_NO 
//		AND A.RPRT_NO = R.RPRT_NO
//		AND a.assg_dttm = '19/11/13 05:12:24';
		   connection();
		   try {
			   StringBuffer query= new StringBuffer("SELECT R.RPRT_NO, R.RPRT_DTTM, C.CUST_NAME, C.TEL_NO, R.RPRT_TP, C.CUST_TP, R.ANML_KINDS, R.ANML_SIZE, R.EXPLN, R.DSCV_DTTM, R.DSCV_LOC ");
			   query.append("FROM RPRT R, CUST C, ASSG A ");
			   query.append("WHERE R.RPRT_PRSN_NO = C.CUST_NO  ");
			   query.append("AND A.RPRT_NO = R.RPRT_NO ");
			   query.append("AND a.assg_dttm = to_date('"+assgDttm+"','YYYY-MM-DD hh24:mi:ss') ");

					
				pstmt = con.prepareStatement(query.toString());
				rs = pstmt.executeQuery();
		   
		   
		   while(rs.next()) {

				String rprtType = rs.getString("RPRT_TP");
				String korRprtType = null;
				
				String custType = rs.getString("CUST_TP");
				String korcustType = null;
				
				String anmlKindsType = rs.getString("ANML_KINDS");
				String korAnmlKindsType = null;
				
				String anmlSizeType = rs.getString("ANML_SIZE");
				String korAnmlSizeType = null;
				
				
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
				
		   }
		   }catch(Exception e2) {
			e2.printStackTrace();
		}
		
		disconnection();
	   	
	}
	
	
	
	private static void GetRprt(String rprtDttm) {
		   
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
        		 apprAssgListData.get(i).get("신고승인일시"),
        		 apprAssgListData.get(i).get("동물종류"),
        		 apprAssgListData.get(i).get("동물크기"),
        		 apprAssgListData.get(i).get("설명")
                 
                 
//                 rprtListData.get(i).get("배정센터명")
           });
        }
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

	




	private void gridbagAdd(Component c, int x, int y, int w, int h) {			
		
		gridbagconstraints.gridx = x;		
		gridbagconstraints.gridy = y; 		
	      //가장 왼쪽 위 gridx, gridy값은 0 			
				
		gridbagconstraints.gridwidth  = w;	//넓이	
		gridbagconstraints.gridheight = h;	//높이	
	     			
	      			
	    gridbaglayout.setConstraints(c, gridbagconstraints); //컴포넌트를 컴포넌트 위치+크기 정보에 따라 GridBagLayout에 배치			
				
	   add(c);			
				
	   }
	
//	private void GetEmpList() {
//		empNos.clear();
//		workStartDates.clear();
//		cntrNos.clear();
////		model1.setRowCount(0);
//		
//		connection();
//		
//		try {
//			StringBuffer query= new StringBuffer("SELECT c.CNTR_NO, e.EMP_NO emp_no, wh.WORK_START_DATE, e.EMP_NAME emp_name, c.CNTR_NAME cntr_name ");
//			query.append("FROM ( SELECT EMP_NO, EMP_NAME FROM EMP) e INNER JOIN( ");
//			query.append("	SELECT  EMP_NO, WORK_START_DATE, CNTR_NO ");
//			query.append("	FROM EMP_WORK_HIST ");
//			query.append("	WHERE WORK_END_DATE=to_date('9999-12-31','YYYY-MM-DD')) wh ");
//			query.append("	ON e.EMP_NO = wh.EMP_NO INNER JOIN( ");
//			query.append("		SELECT CNTR_NO, CNTR_NAME FROM CNTR) c ");
//			query.append("		ON wh.CNTR_NO = c.CNTR_NO ");
//			query.append("ORDER BY 1,2,3 ASC ");
//			
//			pstmt = con.prepareStatement(query.toString());
//			rs = pstmt.executeQuery();
//			while(rs.next()) {		
//				empNos.add(rs.getString("emp_no"));
//				workStartDates.add(rs.getString("work_start_date"));
//				cntrNos.add(rs.getString("cntr_no"));
//				
////				model1.addRow(new Object[] {rs.getString("emp_no"),rs.getString("emp_name"),rs.getString("cntr_name")});
//			}
//		
//		}catch(Exception e1) {
//			e1.printStackTrace();
//		}
//		
//		disconnection();
//	}
	
//	private String GetWorkType(String cntrNo, String empNo) {
//		String result = null;
//		
//		StringBuffer query = new StringBuffer("SELECT /*+INDEX_DESC(EMP_WORK_HIST EMP_WORK_HIST_PK)*/ BIZ_FILD ");
//		query.append("FROM emp_work_hist ");
//		query.append("WHERE emp_no='"+empNo+"' AND cntr_no='"+cntrNo+"' AND work_end_date=to_date('9999-12-31','YYYY-MM-DD') AND ROWNUM =1 ");
//		
//		connection();
//		
//		try {
//			pstmt = con.prepareStatement(query.toString());
//			rs = pstmt.executeQuery();
//			while(rs.next()) {
//				result = rs.getString("BIZ_FILD");
//			}
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}
//		
//		disconnection();
//		
//		return result;
//	}
	
	
    private static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }


	public static void main(String[] args) throws IOException {
		new RprtAssignmentNorm();
//		System.out.println(assgRprtListData);
	}

}






