package asmr;

import java.awt.Button;
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
import java.util.ArrayList;
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

public class RprtAssignment extends JPanel {
   
   /**
    * 
    */
	
//	static ArrayList<String> rprtNos;
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "asmr";
	private String password = "asmr";
	
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private ResultSetMetaData rsmd = null;
	
   private static final long serialVersionUID = 1L;

   List<Map<String, Serializable>> erprtListData;
   
   RprtAssignmentButtonListener rprtAssignmentButtonListener;
   
   private JLabel vRprtNo, vRprtDttm, vRprtName, vTelNo, vRprtTp, vWrtPrsnTp, vAnmlKinds,
   vAnmlSize, vExpln, vDscvDttm, vDscvLoc, vRprtList, vCageList, vRprtInfo;
   
   private JTextField xRprtNo, xRprtDttm, xRprtName, xTelNo, xDscvDttm, xDscvLoc;
   
   private JTextArea xExpln;
   
   private JComboBox<String> cbRprtTp, cbWrtPrsnTp, cbAnmlKinds, cbAnmlSize;
   
   private JTable eRprtList, eCageList;
   
   private JScrollPane scrollpane1, scrollpane2, rprtContentScroll;
   
   private String[] rprtDiv = {"발견", "인계"};
   private String[] wrtPrsnDiv = {"직원","사용자"};
   private String[] anmlDiv = {"개", "고양이"};
   private String[] anmlSizeDiv = {"대", "중", "소"};
   
   private BufferedImage buttonIcon;
   
   private JButton previous, next, Imagebutton;
   
   GridBagLayout gridbaglayout;
   GridBagConstraints gridbagconstraints;
   
   RprtAssignmentMouseListener rprtAssignmentMouseListener;

   private final String[] col1 = {"신고일시","동물종류","동물크기","설명","배정센터명"};
   private final String[] col2 = {"센터명","주소","여유케이지(대)","여유케이지(중)","여유케이지(소)"};
   
   private DefaultTableModel model1 = new DefaultTableModel(col1,0);
   private DefaultTableModel model2 = new DefaultTableModel(col2,0);
   
   
   List<Map<String, Serializable>> rprtListData;
   
   ArrayList<String> cntrListData;
   
//   private ArrayList<String> cntrNm;
   
   private JComboBox<String> combobox;
   
   ArrayList<String> array = new ArrayList<String>();
   
   public RprtAssignment() throws IOException {
	   
//	  rprtNos = new ArrayList<String>();
	   
	  combobox = new JComboBox<String>();
      
      rprtAssignmentButtonListener = new RprtAssignmentButtonListener();
      
      gridbaglayout = new GridBagLayout();
      gridbagconstraints = new GridBagConstraints();
      
      // eRprtList, eCageList
      
      rprtAssignmentMouseListener = new RprtAssignmentMouseListener();
      
      vRprtList = new JLabel("신고목록");
      vRprtList.setFont(new Font("나눔고딕", Font.BOLD, 24));
      
      eRprtList = new JTable(model1){
           private static final long serialVersionUID = 1L;

           public boolean isCellEditable(int row, int column) {                
                   return true;               
           };
       };
      eRprtList.addMouseListener(rprtAssignmentMouseListener);
      scrollpane1 = new JScrollPane(eRprtList);
      scrollpane1.setPreferredSize(new Dimension(600,200));
      
      vCageList = new JLabel("센터별케이지현황");
      vCageList.setFont(new Font("나눔고딕", Font.BOLD, 24));
      
      eCageList = new JTable(model2){
           private static final long serialVersionUID = 1L;

           public boolean isCellEditable(int row, int column) {                
                   return false;               
           };
       };
      eCageList.addMouseListener(rprtAssignmentMouseListener);
      scrollpane2 = new JScrollPane(eCageList);
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
      
      previous = new JButton("<<");
      previous.addActionListener(rprtAssignmentButtonListener);
      previous.setFont(new Font("나눔고딕", Font.BOLD, 16));
      next = new JButton(">>");
      next.addActionListener(rprtAssignmentButtonListener);
      next.setFont(new Font("나눔고딕", Font.BOLD, 16));
      
      JComponent[] vComps = {vRprtNo, vRprtDttm, vRprtName, vTelNo, vRprtTp, vWrtPrsnTp, vAnmlKinds,
            vAnmlSize, vExpln, vDscvDttm, vDscvLoc};
      ChangeFont(vComps, new Font("나눔고딕", Font.PLAIN, 16));
      
      new Button();
      

      
      changeCellEditor(eRprtList, eRprtList.getColumnModel().getColumn(4));
      
      rprtListData = RprtData.getRprtList();
      
      cntrListData = RprtData.getCntrList();

//      cntrNm = new ArrayList<String>();
      
      getData();
      RprtAssignmentView();

   }
   
   private void RprtAssignmentView() {
      
      //setTitle("신고배정_본부센터");   
      
      gridbagconstraints.anchor = GridBagConstraints.WEST;
      gridbagconstraints.ipadx = 7;
      
      gridbagconstraints.weightx=1.0;
      gridbagconstraints.weighty=1.0;
      
      setLayout(gridbaglayout);

      gridbagAdd(vRprtList, 0, 0, 1, 1);
      gridbagAdd(vCageList, 4, 0, 1, 1);
      
      gridbagAdd(scrollpane1, 0, 1, 4, 4);
      gridbagAdd(scrollpane2, 4, 1, 4, 4);
      
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
      
      gridbagAdd(previous, 12,11,1,3);
      gridbagAdd(next, 13,11,1,3);
      

      gridbagconstraints.anchor = GridBagConstraints.CENTER;

      //pack();
      //setResizable(false);
      //setVisible(true);   
      
   }
   
   class RprtAssignmentButtonListener implements ActionListener{
      @Override
      public void actionPerformed(ActionEvent e) {
         // TODO Auto-generated method stub
         if(e.getSource().equals(previous)) {
            
         }
         else if(e.getSource().equals(next)) {
            
         }
         
      }
   
   }
   
//	private void GetRprt() {
//		int clickedRow = eRprtList.getSelectedRow();
////		String rprtNo = rprtNos.get(clickedRow);
//		
////		String cntrName = (String)eCenterList.getValueAt(clickedRow, 0);
//		
//		GetDetailRprt(rprtNo);
//	}
	
//	private void GetDetailRprt(String rprtNo) {
//		connection();
//		
//		try {
//			StringBuffer query= new StringBuffer("SELECT RPRT_NO, TEL_NO, RPRT_DTTM, RPRT_TP, ANML_KINDS, ANML_SIZE, EXPLN, CUST_NAME, DSCV_DTTM, DSCV_LOC ");
//			query.append("FROM RPRT");
//			query.append("JOIN CUST ");
//			query.append("ON RPRT.RPRT_PRSN_NO = CUST.CUST_NO ");
//		
//			pstmt = con.prepareStatement(query.toString());
//			rs = pstmt.executeQuery();
//			while(rs.next()) {
//	
//				xRprtNo.setText(rs.getString("RPRT_NO"));
//				xRprtDttm.setText(rs.getString("RPRT_DTTM"));
//				xRprtName.setText(rs.getString("CUST_NAME"));
//				xTelNo.setText(rs.getString("TEL_NO"));
//				cbRprtTp.setSelectedItem(rs.getString("RPRT_TP"));
//				cbAnmlKinds.setSelectedItem(rs.getString("ANML_KINDS"));
//				cbAnmlSize.setSelectedItem(rs.getString("ANML_SIZE"));
//				xExpln.setText(rs.getString("EXPLN"));
//				xDscvDttm.setText(rs.getString("DSCV_DTTM"));
//				xDscvLoc.setText(rs.getString("DSCV_LOC"));
//			}
//				
//		}catch(Exception e2) {
//			e2.printStackTrace();
//		}
//		
//		disconnection();
//	}
	
	
   
   class RprtAssignmentMouseListener extends MouseAdapter{

      @Override
      public void mouseClicked(MouseEvent e) {
         // TODO Auto-generated method stub
         super.mouseClicked(e);
         if(e.getButton()==1) {
            
         }
      }
      
   }
   
    private void ChangeFont(JComponent[] comps, Font font) {
       for(JComponent comp: comps) {
          comp.setFont(font);
       }
    }

   private void gridbagAdd(Component c, int x, int y, int w, int h) {         
      
      gridbagconstraints.gridx = x;      
      gridbagconstraints.gridy = y;       
         //가장 왼쪽 위 gridx, gridy값은 0          
            
      gridbagconstraints.gridwidth  = w;   //넓이   
      gridbagconstraints.gridheight = h;   //높이   
                 
                  
       gridbaglayout.setConstraints(c, gridbagconstraints); //컴포넌트를 컴포넌트 위치+크기 정보에 따라 GridBagLayout에 배치         
            
      add(c);         
            
      }
   
    void getData() {
    	
            for(int i=0; i < rprtListData.size(); i++) {
            	
               model1.addRow(new Object[] {
                     rprtListData.get(i).get("신고일시"),
                     rprtListData.get(i).get("동물종류"),
                     rprtListData.get(i).get("동물크기"),
                     rprtListData.get(i).get("설명")
                     
                     
//                     rprtListData.get(i).get("배정센터명")
               });
            }
      }
    


    
    void changeCellEditor(JTable table, TableColumn column) {

        for(int i=0;i<RprtData.getCntrList().size();i++) {
        	combobox.addItem(RprtData.getCntrList().get(i));
        }


        column.setCellEditor(new DefaultCellEditor(combobox));

        DefaultTableCellRenderer renderer =
                new DefaultTableCellRenderer();
        renderer.setToolTipText("클릭하면 콤보박스로 변경됩니다.");
        column.setCellRenderer(renderer);
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

    

   public static void main(String[] args) throws IOException {
      new RprtAssignment();
//      System.out.println(RprtData.getCntrList().get(0).values());
      
   }

}





