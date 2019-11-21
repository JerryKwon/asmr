package asmr;

import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
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
import java.text.SimpleDateFormat;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerDateModel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class RprtRegis extends JPanel implements ActionListener{
	private JLabel vRprtName,  vTelNo, vRprtTp,vAnmlKinds,
	vAnmlSize, vExpln,vDscvDttm, vDscvLoc, vPic, vTitle;
	
	private JTextField xRprtName, xTelNo, xDscvDttm,
	xDscvLoc, xPic;
	
	private JTextArea xExpln;
	
	private String[] rprtDiv = {"�߰�", "�ΰ�"};
	
	private String[] anmlDiv = {"��", "�����", "��Ÿ"};
	
	private String[] anmlSizeDiv = {"��", "��", "��"};

	private JButton regist, cancel, Imagebutton;
	
	private BufferedImage buttonIcon;
	
	private Button btnSearch;
	
	private JComboBox<String> cbRprtTp, cbAnmlKinds, cbAnmlSize;
	
	private JFileChooser fc;
	
	private JSpinner timeSpinner;
	
	private JSpinner.DateEditor timeEditor;
	
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	
	private JScrollPane rprtContentScroll;
	
	RprtRegisterButtonListener RprtRegisterButtonListener;
	
	GridBagLayout gridbaglayout;
	GridBagConstraints gridbagconstraints;
	
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "asmr";
	private String password = "asmr";
	
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private ResultSetMetaData rsmd = null;

	public RprtRegis() throws IOException{
		
		RprtRegisterButtonListener = new RprtRegisterButtonListener();
		
		gridbaglayout = new GridBagLayout();
		gridbagconstraints = new GridBagConstraints();
		
		vTitle = new JLabel("���⵿���Ű�");
		vTitle.setFont(new Font("�������", Font.BOLD, 24));
		vTitle.setBorder(new EmptyBorder(0,10,20,0));

		vRprtName = new JLabel("�Ű��ڸ�");
		xRprtName = new JTextField(20);

		vTelNo = new JLabel("��ȭ��ȣ");
		xTelNo = new JTextField(20);

		vRprtTp = new JLabel("�Ű���");
		cbRprtTp = new JComboBox<String>(rprtDiv);
	
		vAnmlKinds = new JLabel("��������");
		cbAnmlKinds = new JComboBox<String>(anmlDiv);
				
		vAnmlSize = new JLabel("����ũ��");
		cbAnmlSize = new JComboBox<String>(anmlSizeDiv);

		vExpln = new JLabel("����");
		xExpln = new JTextArea(4,75);
		rprtContentScroll = new JScrollPane(xExpln);
		rprtContentScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		vDscvDttm = new JLabel("�߰��Ͻ�");
		//xDscvDttm = new JTextField(20);
		//xDscvDttm.setEditable(false);

		vDscvLoc = new JLabel("�߰����");
		xDscvLoc = new JTextField(20);
		
		
		vPic = new JLabel("����");
		xPic = new JTextField(20);
		xPic.setEditable(false);
		
		btnSearch = new Button("ã�ƺ���");
		btnSearch.addActionListener(this);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		regist = new JButton("���");
		regist.setBackground(blue);
		regist.setForeground(white);
		regist.addActionListener(RprtRegisterButtonListener);
		cancel = new JButton("���");
		cancel.addActionListener(RprtRegisterButtonListener);
		
		buttonIcon = ImageIO.read(new File("./images/cal1.png"));
		Imagebutton = new JButton(new ImageIcon(buttonIcon));
		Imagebutton.setBorderPainted(false);
		Imagebutton.setContentAreaFilled(false);
		Imagebutton.setFocusPainted(false);
		
		fc = new JFileChooser();
		
		fc.setMultiSelectionEnabled(true);
		
		timeSpinner = new JSpinner(new SpinnerDateModel());
		timeEditor = new JSpinner.DateEditor(timeSpinner, "yyyy.MM.dd HH:mm");
		timeSpinner.setEditor(timeEditor);
		//timeSpinner.setValue(new Date());
		
		JComponent[] vComps2 = {vRprtName, vTelNo, vRprtTp, vAnmlKinds, vAnmlSize, vExpln ,
				vDscvDttm, vDscvLoc, vPic};
		ChangeFont(vComps2, new Font("�������", Font.PLAIN, 16));
		
		JComponent[] bComps = {regist, cancel, fc};
		ChangeFont(bComps, new Font("�������", Font.BOLD, 16));
		
		RprtRegisView();
	}
	
	class RprtRegisterButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource().equals(regist)) {
				RegistRprt();
				MainFrame.mainCase();	
			}
			if(e.getSource().equals(cancel)) {
				MainFrame.mainCase();
				//dispose();
			}
		}
		
	}

	
	public void actionPerformed(ActionEvent e) {
		
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif", "png");
		
		fc.setFileFilter(filter);
		
		int result = fc.showOpenDialog(null);
		
		if (result != JFileChooser.APPROVE_OPTION) {
            JOptionPane.showMessageDialog(null, 
                    "������ �������� �ʾҽ��ϴ�.", "���", 
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // ���� �������� ���
        String path = fc.getSelectedFile().getPath();
        //pack();
        
        xPic.setText(path);
		
	}
	
	private void ChangeFont(JComponent[] comps, Font font) {
		for(JComponent comp: comps) {
	   		comp.setFont(font);
	  	}
	 }
	
	private void RprtRegisView() {

		//setTitle("�Ű� ���");
		gridbagconstraints.insets = new Insets(5,5,5,5);

		gridbagconstraints.anchor = GridBagConstraints.WEST;
		gridbagconstraints.ipadx = 7;

		gridbagconstraints.weightx=1.0;
		gridbagconstraints.weighty=1.0;

		setLayout(gridbaglayout);
		
		gridbagAdd(vTitle, 0, 0, 2, 1);
		gridbagAdd(vRprtName, 0, 1, 1, 1);
		gridbagAdd(xRprtName, 2, 1, 1, 1);
		gridbagAdd(vTelNo, 10, 1, 1, 1);
		gridbagAdd(xTelNo, 12, 1, 1, 1);
		gridbagAdd(vRprtTp, 0, 2, 1, 1);
		gridbagAdd(cbRprtTp, 2, 2, 1, 1);
		gridbagAdd(vAnmlKinds, 0, 3, 1, 1);
		gridbagAdd(cbAnmlKinds, 2, 3, 1, 1);
		gridbagAdd(vAnmlSize, 10, 3, 1, 1);
		gridbagAdd(cbAnmlSize, 12, 3, 1, 1);
		gridbagAdd(vExpln, 0, 4, 1, 1);
		gridbagAdd(xExpln, 2, 4, 11, 1);
		gridbagAdd(vDscvDttm, 0, 7, 1, 1);
		gridbagAdd(timeSpinner, 2, 7, 1, 1);
		//gridbagAdd(Imagebutton, 4,6,1,1);
		//gridbagAdd(timeSpinner, 8,6,1,1);
		
		gridbagAdd(vDscvLoc, 0, 8, 1, 1);
		gridbagAdd(xDscvLoc, 2, 8, 1, 1);
		gridbagAdd(vPic, 0, 9, 1, 1);
		gridbagAdd(xPic, 2, 9, 1, 1);
		gridbagAdd(btnSearch, 4,9,1,1);
		
		gridbagconstraints.anchor = GridBagConstraints.CENTER;

		gridbagAdd(regist, 4, 10, 1, 1);
		gridbagAdd(cancel, 6, 10, 1, 1);

		//pack();
		//setResizable(false);
		//setVisible(true);

	}
	
	private void gridbagAdd(Component c, int x, int y, int w, int h) {			
		
		gridbagconstraints.gridx = x;		
		gridbagconstraints.gridy = y; 		
	      //���� ���� �� gridx, gridy���� 0 			
				
		gridbagconstraints.gridwidth  = w;	//����	
		gridbagconstraints.gridheight = h;	//����	
	     			
	      			
	    gridbaglayout.setConstraints(c, gridbagconstraints); //������Ʈ�� ������Ʈ ��ġ+ũ�� ������ ���� GridBagLayout�� ��ġ			
				
	   add(c);			
				
	   }			
	
	private void RegistRprt() {
		
		connection();
		
		String rprtName =  xRprtName.getText();
		
		String telNo = xTelNo.getText();
		
		String rprtTp = (String)cbRprtTp.getSelectedItem();
		String engRprtTp = null;
		
		String anmlKinds = (String)cbAnmlKinds.getSelectedItem();
		String engAnmlKinds = null;
		
		String anmlSize = (String)cbAnmlSize.getSelectedItem();
		String engAnmlSize = null;
		
		String expln = xExpln.getText();
		
		SimpleDateFormat formater = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		String dscvDttm = formater.format(timeSpinner.getValue());
		
		String dscvLoc = xDscvLoc.getText();
		
		String pic = xPic.getText();
		
		
		switch(rprtTp) {
		case "�߰�":
			engRprtTp = "d";
			break;
		case "�ΰ�":
			engRprtTp = "h";
			break;
		}
		
		switch(anmlKinds) {
		case "��":
			engAnmlKinds = "d";
			break;
		case "�����":
			engAnmlKinds = "c";
			break;
		case "��Ÿ":
			engAnmlKinds = "e";
			break;
		}
		
		switch(anmlSize) {
		case "��":
    		engAnmlSize="b";
    		break;
    	case "��":
    		engAnmlSize="m";
    		break;
    	case "��":
    		engAnmlSize="s";
    		break;
		}
		
		try {
			StringBuffer query1 = new StringBuffer("INSERT INTO RPRT(RPRT_NO, RPRT_DTTM, RPRT_TP, RPRT_MTHD, ANML_KINDS, ANML_SIZE, EXPLN, RPRT_PRSN_NO, DSCV_DTTM, DSCV_LOC) ");
			query1.append("VALUES( ");
			query1.append("NO_SEQ.nextval, ");
			query1.append("sysdate, ");
			query1.append("'"+engRprtTp+"', ");
			query1.append("'i', ");
			query1.append("'"+engAnmlKinds+"', ");
			query1.append("'"+engAnmlSize+"', ");
			query1.append("'"+expln+"', ");
			query1.append("'0000001', ");
			query1.append("to_date('"+dscvDttm+"','yyyy.MM.dd HH24:mi:ss'), ");
			query1.append("'"+dscvLoc+"') ");

			
			pstmt = con.prepareStatement(query1.toString());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				con.commit();
			}
			
			StringBuffer query2 = new StringBuffer("INSERT INTO RPRT_PIC(RPRT_NO, RPRT_PIC_ORNU, PATH) ");
			query2.append("VALUES( ");
			query2.append("NO_SEQ.currval, ");
			query2.append("RPRT_PIC_SEQ.nextval, ");
			query2.append("'"+pic+"') ");
			
			pstmt = con.prepareStatement(query2.toString());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				con.commit();
			}
//			System.out.println(query2.toString());
			
		}catch(SQLException e) {
			e.printStackTrace();
		}

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
		//new RprtRegis();		
		
		
	}			
	
	

}


