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
   
   private String[] rprtDiv = {"�߰�", "�ΰ�"};
   private String[] wrtPrsnDiv = {"����","�����"};
   private String[] anmlDiv = {"��", "�����"};
   private String[] anmlSizeDiv = {"��", "��", "��"};
   
   static BufferedImage buttonIcon;
   
   static String xPath;
   
   private JButton previous, next, Imagebutton;
   
   GridBagLayout gridbaglayout;
   GridBagConstraints gridbagconstraints;
   
   RprtAssignmentMouseListener rprtAssignmentMouseListener;

   private final String[] col1 = {"�Ű��Ͻ�","��������","����ũ��","����","�������͸�"};
   private final String[] col2 = {"���͸�","�ּ�","����������(��)","����������(��)","����������(��)"};
   
   private DefaultTableModel model1 = new DefaultTableModel(col1,0);
   private DefaultTableModel model2 = new DefaultTableModel(col2,0);
   
   
   List<Map<String, Serializable>> rprtListData;
   List<Map<String, Serializable>> cageListData;
   
   ArrayList<String> cntrListData;
   
   ArrayList<String> cntrMoListData;
   

   
//   private ArrayList<String> cntrNm;
   
   private JComboBox<String> combobox;
   
   ArrayList<String> array = new ArrayList<String>();


   
   public RprtAssignment() throws IOException {
	   
//	  rprtNos = new ArrayList<String>();
	   
	  combobox = new JComboBox<String>();
	  combobox.addMouseListener(rprtAssignmentMouseListener);
      
      rprtAssignmentButtonListener = new RprtAssignmentButtonListener();
      
      gridbaglayout = new GridBagLayout();
      gridbagconstraints = new GridBagConstraints();
      
      // eRprtList, eCageList
      
      rprtAssignmentMouseListener = new RprtAssignmentMouseListener();
      
      vRprtList = new JLabel("�Ű���");
      vRprtList.setFont(new Font("�������", Font.BOLD, 24));
      
      eRprtList = new JTable(model1){
           private static final long serialVersionUID = 1L;

           public boolean isCellEditable(int row, int column) {                
                   return true;               
           };
       };
      eRprtList.addMouseListener(rprtAssignmentMouseListener);
      scrollpane1 = new JScrollPane(eRprtList);
      scrollpane1.setPreferredSize(new Dimension(600,200));
      
      vCageList = new JLabel("���ͺ���������Ȳ");
      vCageList.setFont(new Font("�������", Font.BOLD, 24));
      
      eCageList = new JTable(model2){
           private static final long serialVersionUID = 1L;

           public boolean isCellEditable(int row, int column) {                
                   return false;               
           };
       };
//      eCageList.addMouseListener(rprtAssignmentMouseListener);
      scrollpane2 = new JScrollPane(eCageList);
      scrollpane2.setPreferredSize(new Dimension(600,200));
      
      vRprtInfo = new JLabel("�Ű�����");
      vRprtInfo.setFont(new Font("�������", Font.BOLD, 20));
      
      vRprtNo = new JLabel("�Ű��ȣ");
      xRprtNo = new JTextField(20);
      xRprtNo.setEditable(false);
      
      vRprtDttm = new JLabel("�Ű��Ͻ�");
      xRprtDttm = new JTextField(20);
      xRprtDttm.setEditable(false);
      
      vRprtName = new JLabel("�Ű��ڸ�");
      xRprtName = new JTextField(20);
      xRprtName.setEditable(false);
      
      vTelNo = new JLabel("����ó");
      xTelNo = new JTextField(20);
      xTelNo.setEditable(false);
      
      vRprtTp = new JLabel("�Ű���");
      cbRprtTp = new JComboBox<String>(rprtDiv);
      cbRprtTp.setEnabled(false);
      
      vWrtPrsnTp = new JLabel("�ۼ��ڱ���");
      cbWrtPrsnTp = new JComboBox<String>(wrtPrsnDiv);
      cbWrtPrsnTp.setEnabled(false);
      
      vAnmlKinds = new JLabel("��������");
      cbAnmlKinds = new JComboBox<String>(anmlDiv);
      cbAnmlKinds.setEnabled(false);
      
      vAnmlSize = new JLabel("����ũ��");
      cbAnmlSize = new JComboBox<String>(anmlSizeDiv);
      cbAnmlSize.setEnabled(false);
      
      vExpln = new JLabel("����");
      xExpln = new JTextArea(4,75);
      xExpln.setEditable(false);
      rprtContentScroll = new JScrollPane(xExpln);
      rprtContentScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
      
      vDscvDttm = new JLabel("�߰��Ͻ�");
      xDscvDttm = new JTextField(20);
      xDscvDttm.setEditable(false);
      
      vDscvLoc = new JLabel("�߰����");
      xDscvLoc = new JTextField(20);
      xDscvLoc.setEditable(false);
      
      buttonIcon = ImageIO.read(new File("./images/cat1.png"));
      Imagebutton = new JButton(new ImageIcon(buttonIcon));
      Imagebutton.setBorderPainted(false);
      Imagebutton.setContentAreaFilled(false);
      Imagebutton.setFocusPainted(false);

      previous = new JButton("<<");
      previous.addActionListener(rprtAssignmentButtonListener);
      previous.setFont(new Font("�������", Font.BOLD, 16));
      next = new JButton(">>");
      next.addActionListener(rprtAssignmentButtonListener);
      next.setFont(new Font("�������", Font.BOLD, 16));
      
      JComponent[] vComps = {vRprtNo, vRprtDttm, vRprtName, vTelNo, vRprtTp, vWrtPrsnTp, vAnmlKinds,
            vAnmlSize, vExpln, vDscvDttm, vDscvLoc};
      ChangeFont(vComps, new Font("�������", Font.PLAIN, 16));
      
      new Button();
      

      
      changeCellEditor(eRprtList, eRprtList.getColumnModel().getColumn(4));
      
      rprtListData = RprtData2.getRprtList();
      
      cntrListData = RprtData2.getCntrList();
      
//      RprtData.getCntrNoList(nm);
      
      cageListData = RprtData2.getCageList();

//      cntrNm = new ArrayList<String>();
//      System.out.println(xPath);
      getData();
      getCage();
      RprtAssignmentView();

   }
   
   private void RprtAssignmentView() {
      
      //setTitle("�Ű����_���μ���");   
      
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
      //�Ű���
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
//         else if(e.getSource().equals(combobox)) {
//        	 int clickedRow = eRprtList.getSelectedRow();
//        	 selectCntr = (String)combobox.getSelectedItem();
//        	 nm = selectCntr;
//        	 cntrno = RprtData.getCntrNoList(nm);
//
//        	 String rprtDttm = (String)eRprtList.getValueAt(clickedRow, 0);
//        	 rprtno = RprtData.getRprtNoList(rprtDttm);
//        	 
//        	 AssignCntr(cntrno, rprtno);
//
//        	 
//         }
         
      }

//	private void AssignCntr(String cntrno, String rprtno) {
//		// TODO Auto-generated method stub
//
//		 connection();
//		   try {
// 		   
//				StringBuffer query= new StringBuffer("INSERT INTO ASSG ");
//				query.append("VALUES(ASSG_SEQ.NEXTVAL, ");
//				query.append("'"+rprtno+"', ");
//				query.append("'"+cntrno+"', ");
//				query.append("SYSDATE, NULL, NULL,NULL ) ");
//					
//				pstmt = con.prepareStatement(query.toString());
//				rs = pstmt.executeQuery();
//				
//				if(rs.next()) {
//					con.commit();
//				}
//				
//				
//					
//			}catch(Exception e) {
//				e.printStackTrace();
//			}
//			
//			disconnection();
//		
//	}
   
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
        	 int clickedRow = eRprtList.getSelectedRow();
				String rprtDttm = (String)eRprtList.getValueAt(clickedRow, 0);
//				String cntrName = (String)eEmpList.getValueAt(clickedRow, 2);
//				String cntrNo = cntrNos.get(clickedRow);
				GetRprt(rprtDttm);
				
//				String selectCntr = (String)combobox.getSelectedItem();
//	        	 
				
				
//				String selectCntr = (String)eRprtList.getValueAt(clickedRow, 4);
				
				
//				System.out.println(selectCntr);
			
//	        	String cntrno = RprtData.getCntrNoList(combobox.getSelectedItem().toString());
//	        	System.out.println(cntrno);
//	        	String rprtno = RprtData.getRprtNoList(rprtDttm);
//	        	System.out.println(rprtno);
//	        	AssignCntr(cntrno, rprtno);
				
				int column = 4;
				int row = eRprtList.getSelectedRow();
				String value = (String) eRprtList.getModel().getValueAt(row, column);
				String cntrno = RprtData2.getCntrNoList(value);
	        	System.out.println(cntrno);
	        	String rprtno = RprtData2.getRprtNoList(rprtDttm);
	        	System.out.println(rprtno);
	        	AssignCntr(cntrno, rprtno);
            
         }
         
      }
      
      private void AssignCntr(String cntrno, String rprtno) {
  		// TODO Auto-generated method stub

  		 connection();
  		   try {
   		   
  				StringBuffer query= new StringBuffer("INSERT INTO ASSG ");
  				query.append("VALUES(ASSG_SEQ.NEXTVAL, ");
  				query.append("'"+rprtno+"', ");
  				query.append("'"+cntrno+"', ");
  				query.append("SYSDATE, NULL, NULL,NULL ) ");
  					
  				pstmt = con.prepareStatement(query.toString());
  				rs = pstmt.executeQuery();
  				
  				if(rs.next()) {
  					con.commit();
  				}
  				
  				
  					
  			}catch(Exception e) {
  				e.printStackTrace();
  			}
  			
  			disconnection();
  		
  	}
      
   }
   
   private void GetRprt(String rprtDttm) {
	   
	   connection();
	   try {
		   
		   		   
//			StringBuffer query= new StringBuffer("SELECT R.RPRT_NO, R.RPRT_DTTM, C.CUST_NAME, C.TEL_NO, R.RPRT_TP, C.CUST_TP, R.ANML_KINDS, R.ANML_SIZE, R.EXPLN, R.DSCV_DTTM, R.DSCV_LOC ");
//			query.append("FROM (SELECT R.RPRT_NO, R.RPRT_DTTM, R.RPRT_TP, R.ANML_KINDS, R.ANML_SIZE, R.EXPLN, R.DSCV_DTTM, R.DSCV_LOC,  PATH, R.RPRT_PRSN_NO ");
//			query.append("FROM RPRT_PIC P ");
//			query.append("JOIN RPRT R ");
//			query.append("ON R.RPRT_NO = P.RPRT_NO) R ");
//			query.append("JOIN CUST C ");
//			query.append("ON R.RPRT_PRSN_NO = C.CUST_NO ");
//			query.append("WHERE RPRT_DTTM = to_date('"+rprtDttm+"','YYYY-MM-DD hh24:mi:ss') ");

		   StringBuffer query= new StringBuffer("SELECT R.RPRT_NO, R.RPRT_DTTM, C.CUST_NAME, C.TEL_NO, R.RPRT_TP, C.CUST_TP, R.ANML_KINDS, R.ANML_SIZE, R.EXPLN, R.DSCV_DTTM, R.DSCV_LOC ");
		   query.append("FROM RPRT R ");
		   query.append("JOIN CUST C ");
		   query.append("ON R.RPRT_PRSN_NO = C.CUST_NO ");
		   query.append("WHERE RPRT_DTTM = to_date('"+rprtDttm+"','YYYY-MM-DD hh24:mi:ss') ");

				
			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			
//			StringBuffer query2 = new StringBuffer("select rprt.rprt_no, path ");
//			query2.append("from rprt_pic ");
//			query2.append("join rprt ");
//			query2.append("on rprt.rprt_no = rprt_pic.rprt_no ");
//			
//			pstmt = con.prepareStatement(query2.toString());
//			rs = pstmt.executeQuery();
//			if(rs.next()) {
//				con.commit();
//			}

			
			while(rs.next()) {

				String rprtType = rs.getString("RPRT_TP");
				String korRprtType = null;
				
				String custType = rs.getString("CUST_TP");
				String korcustType = null;
				
				String anmlKindsType = rs.getString("ANML_KINDS");
				String korAnmlKindsType = null;
				
				String anmlSizeType = rs.getString("ANML_SIZE");
				String korAnmlSizeType = null;
				
//				String xPath = rs.getString("PATH");
				
				switch(rprtType) {
				case "d":
					korRprtType = "�߰�";
					break;
				case "h":
					korRprtType = "�ΰ�";
					break;
				}
				
				switch(custType) {
				case "m":
					korcustType = "ȸ��";
					break;
				case "n":
					korcustType = "��ȸ��";
					break;
				}
				
				switch(anmlKindsType) {
				case "d":
					korAnmlKindsType = "��";
					break;
				case "c":
					korAnmlKindsType = "�����";
					break;
				case "e":
					korAnmlKindsType = "��Ÿ";
					break;
				}
				
				switch(anmlSizeType) {
				case "b":
					korAnmlSizeType = "��";
					break;
				case "m":
					korAnmlSizeType = "��";
					break;
				case "s":
					korAnmlSizeType = "��";
					break;
				}
//	 R.EXPLN, R.DSCV_DTTM, R.DSCV_LOC
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
//				xPath.setText(rs.getString("path"));
				
//			    buttonIcon = ImageIO.read(new File(xPath));
				
//				xPath = rs.getString("PATH");
				
				
				
			}
				
		}catch(Exception e2) {
			e2.printStackTrace();
		}
		
		disconnection();
	   
	   
	   
   }
   
   
    private void ChangeFont(JComponent[] comps, Font font) {
       for(JComponent comp: comps) {
          comp.setFont(font);
       }
    }

   private void gridbagAdd(Component c, int x, int y, int w, int h) {         
      
      gridbagconstraints.gridx = x;      
      gridbagconstraints.gridy = y;       
         //���� ���� �� gridx, gridy���� 0          
            
      gridbagconstraints.gridwidth  = w;   //����   
      gridbagconstraints.gridheight = h;   //����   
                 
                  
       gridbaglayout.setConstraints(c, gridbagconstraints); //������Ʈ�� ������Ʈ ��ġ+ũ�� ������ ���� GridBagLayout�� ��ġ         
            
      add(c);         
            
      }
   
    void getData() {
    	
            for(int i=0; i < rprtListData.size(); i++) {
            	
               model1.addRow(new Object[] {
                     rprtListData.get(i).get("�Ű��Ͻ�"),
                     rprtListData.get(i).get("��������"),
                     rprtListData.get(i).get("����ũ��"),
                     rprtListData.get(i).get("����")
                     
                     
//                     rprtListData.get(i).get("�������͸�")
               });
            }
      }
    
    void getCage() {
    	
        for(int i=0; i < cageListData.size(); i++) {
        	
           model2.addRow(new Object[] {
                 cageListData.get(i).get("���͸�"),
                 cageListData.get(i).get("�ּ�"),
                 cageListData.get(i).get("����������(��)"),
                 cageListData.get(i).get("����������(��)"),
                 cageListData.get(i).get("����������(��)")
                 
                 
//                 rprtListData.get(i).get("�������͸�")
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
        renderer.setToolTipText("Ŭ���ϸ� �޺��ڽ��� ����˴ϴ�.");
        column.setCellRenderer(renderer);
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

    

   public static void main(String[] args) throws IOException {
      new RprtAssignment();
//      System.out.println(RprtData.getCntrList().get(0).values());
      
   }

}





