package asmr;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
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
import javax.swing.table.DefaultTableModel;

public class ProtAniList extends JPanel {
	private JLabel vProtAniRegister, vProtAniInfo, vAbanAniNo, vAbanAniType, vRescueNo, vAbanAniName, vAge, vParAniName, vAniType, vKind, vSex, vNeutWhet, vColor, vAniSize, vRegisDate, vDescription, vDscvDate, vCage, vDscvPlace;
	private JTextField xAbanAniNo, xAbanAniType, xRescueNo, xAbanAniName, xAge, xParAniName, xAniType ,xKind, xColor, xRegisDate, xDscvDate, xDscvPlace;
	private JComboBox<String> cbSex, cbNeutWhet, cbAniSize, cbCage;
	private JButton register,modify, cancel, returning;
	private JTextArea xDescription;

	private JTable eProtAniList;
	private JScrollPane aniListScroll;
	
	private JScrollPane descriptionScroll;
	
	private ArrayList<String> cntrNos;
	private ArrayList<String> abanNos;
	
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "asmr";
	private String password = "asmr";
	
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private ResultSetMetaData rsmd = null;
	
	private final String[] col1 = {"���⵿����","��������","ǰ��","����(����)","ũ��","����"};
	
	private DefaultTableModel model1 = new DefaultTableModel(col1,0);
	
	private final String[] sexDiv = {"����","����","�̻�"};
	private final String[] cbNeutWhetDiv = {"Y","N"};
	private final String[] aniSizeDiv = {"��","��","��"};
	private final String[] cageDiv = {"������1(��)","������4(��)","������9(��)"};
	
	private JLabel imageLabel;
	private JButton previous, next, pictureManage;
	
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	private Color red = new Color(217,0,27);
	
	ProtAniListMouseListener protAniListMouseListener;
	ProtAniListButtonListener protAniListButtonListener;
	
	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;
	
	public ProtAniList() throws IOException {
		
		gridBagLayout = new GridBagLayout();		
		gridBagConstraints = new GridBagConstraints();
		
		protAniListMouseListener = new ProtAniListMouseListener();
		protAniListButtonListener = new ProtAniListButtonListener();
		
		cntrNos = new ArrayList<String>();
		abanNos = new ArrayList<String>();
		
		vProtAniRegister = new JLabel("��ȣ�������");
		vProtAniRegister.setFont(new Font("�������", Font.BOLD, 24));
		
		register = new JButton("���");
		register.setBackground(blue);
		register.setForeground(white);
		register.addActionListener(protAniListButtonListener);
		
//		eProtAniList = new JTable(model1);
		eProtAniList = new JTable(model1) {
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
		eProtAniList.addMouseListener(protAniListMouseListener);
		aniListScroll = new JScrollPane(eProtAniList);
		aniListScroll.setPreferredSize(new Dimension(1400,200));
		eProtAniList.getColumnModel().getColumn(0).setPreferredWidth(150);
		eProtAniList.getColumnModel().getColumn(1).setPreferredWidth(75);
		eProtAniList.getColumnModel().getColumn(2).setPreferredWidth(150);
		eProtAniList.getColumnModel().getColumn(3).setPreferredWidth(75);
		eProtAniList.getColumnModel().getColumn(4).setPreferredWidth(50);
		eProtAniList.getColumnModel().getColumn(5).setPreferredWidth(900);
		
		
		
		vProtAniInfo = new JLabel("��ȣ��������");
		vProtAniInfo.setFont(new Font("�������", Font.BOLD, 20));
		
		vAbanAniNo = new JLabel("���⵿����ȣ");
		xAbanAniNo = new JTextField(12);
		xAbanAniNo.setEditable(false);
		
		vAbanAniType = new JLabel("���⵿������");
		xAbanAniType = new JTextField(12);
		xAbanAniType.setEditable(false);
		
		vRescueNo = new JLabel("������ȣ");
		xRescueNo = new JTextField(12);
		xRescueNo.setEditable(false);
		
		vAbanAniName = new JLabel("���⵿����");
		xAbanAniName = new JTextField(12);
		xAbanAniName.setEditable(false);
		
		vAge = new JLabel("����(����)");
		xAge = new JTextField(12);
		xAge.setEditable(false);
		
		vParAniName = new JLabel("������⵿����");
		xParAniName = new JTextField(12);
		xParAniName.setEditable(false);
		
		vAniType = new JLabel("��������");
		xAniType = new JTextField(12);
		xAniType.setEditable(false);
		
		vKind = new JLabel("ǰ��");
		xKind = new JTextField(12);
		xKind.setEditable(false);
		
		vSex = new JLabel("����");
		cbSex = new JComboBox<String>(sexDiv);
		cbSex.setEnabled(false);
		
		vNeutWhet = new JLabel("�߼�ȭ����");
		cbNeutWhet = new JComboBox<String>(cbNeutWhetDiv);
		cbNeutWhet.setEnabled(false);
		
		vColor = new JLabel("����");
		xColor = new JTextField(12);
		xColor.setEditable(false);
		
		vAniSize = new JLabel("����ũ��");
		cbAniSize = new JComboBox<String>(aniSizeDiv);
		cbAniSize.setEnabled(false);
		
		vRegisDate = new JLabel("�������");
		xRegisDate = new JTextField(12);
		xRegisDate.setEditable(false);
		
		vDescription = new JLabel("����");
		xDescription = new JTextArea();
		xDescription.setEditable(false);
		descriptionScroll = new JScrollPane(xDescription);
		descriptionScroll.setPreferredSize(new Dimension(400,150));
		descriptionScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		vDscvDate = new JLabel("�߰��Ͻ�");
		xDscvDate = new JTextField(12);
		xDscvDate.setEditable(false);
		
		vCage = new JLabel("������");
		cbCage = new JComboBox<String>();
		cbCage.setEnabled(false);
		
		vDscvPlace = new JLabel("�߰����");
		xDscvPlace = new JTextField(12);
		xDscvPlace.setEditable(false);
		
		modify = new JButton("����");
		modify.setBackground(blue);
		modify.setForeground(white);
		modify.addActionListener(protAniListButtonListener);
		
		cancel = new JButton("���");
		cancel.addActionListener(protAniListButtonListener);
		
		returning = new JButton("��ȯ");
		returning.setBackground(red);
		returning.setForeground(white);
		returning.addActionListener(protAniListButtonListener);
		
		pictureManage = new JButton("��������");
		pictureManage.setEnabled(false);
		pictureManage.setBackground(blue);
		pictureManage.setForeground(white);
		pictureManage.addActionListener(protAniListButtonListener);
		
		
		File input = new File("images/NoImage.png");
		BufferedImage image = ImageIO.read(input);
		BufferedImage resized = resize(image,200,200);
		imageLabel = new JLabel();
		ImageIcon icon = new ImageIcon(resized);
		imageLabel.setIcon(icon);
		
		previous = new JButton("<<");
		previous.addActionListener(protAniListButtonListener);
		next = new JButton(">>");
		next.addActionListener(protAniListButtonListener);
		
		JComponent[] fontComps1 = {vAbanAniNo, vAbanAniType, vRescueNo, vAbanAniName, vAge, vParAniName, vAniType, vKind, vSex, vNeutWhet, vColor, vAniSize, vRegisDate, vDescription, vDscvDate, vCage, vDscvPlace};
		ChangeFont(fontComps1, new Font("�������", Font.PLAIN, 16));

		JComponent[] fontComps2 = {register,modify,cancel,returning,pictureManage};
		ChangeFont(fontComps2, new Font("�������", Font.BOLD, 16));

		GetProtAniList();
		
		ProtAniListView();
	}
	
	private void ProtAniListView() throws IOException {
		setLayout(gridBagLayout);
		
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.ipadx = 7;
		
		gridBagConstraints.weightx=1.0;
		gridBagConstraints.weighty=1.0;
		
		gridbagAdd(vProtAniRegister, 0, 0, 1, 1);
		
		JPanel registerPanel = new JPanel();
		registerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT,0,0));
		registerPanel.add(register);
		registerPanel.setBorder(BorderFactory.createEmptyBorder(0,300,0,0));
		gridbagAdd(registerPanel, 9, 0, 1, 1);
		
		JPanel aniListPanel = new JPanel();
		aniListPanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
		aniListPanel.add(aniListScroll);
		aniListPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,0));
		gridbagAdd(aniListPanel, 0, 1, 10, 1);
		
		vProtAniInfo.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
		gridbagAdd(vProtAniInfo, 0, 2, 1, 1);
		
		gridbagAdd(vAbanAniNo, 0, 3, 1, 1);
		gridbagAdd(xAbanAniNo, 1, 3, 1, 1);
		
		gridbagAdd(vAbanAniType, 2, 3, 1, 1);
		gridbagAdd(xAbanAniType, 3, 3, 1, 1);
		
		gridbagAdd(vRescueNo, 4, 3, 1, 1);
		gridbagAdd(xRescueNo, 5, 3, 1, 1);
		
		gridbagAdd(vAbanAniName, 0, 4, 1, 1);
		gridbagAdd(xAbanAniName, 1, 4, 1, 1);
		
		gridbagAdd(vAge, 2, 4, 1, 1);
		gridbagAdd(xAge, 3, 4, 1, 1);
		
		gridbagAdd(vParAniName, 4, 4, 1, 1);
		gridbagAdd(xParAniName, 5, 4, 1, 1);
		
		gridbagAdd(vAniType, 0, 5, 1, 1);
		gridbagAdd(xAniType, 1, 5, 1, 1);
		
		gridbagAdd(vKind, 2, 5, 1, 1);
		gridbagAdd(xKind, 3, 5, 1, 1);
		
		gridbagAdd(vSex, 0, 6, 1, 1);
		gridbagAdd(cbSex, 1, 6, 1, 1);
		
		gridbagAdd(vNeutWhet, 2, 6, 1, 1);
		gridbagAdd(cbNeutWhet, 3, 6, 1, 1);
		
		gridbagAdd(vColor, 0, 7, 1, 1);
		gridbagAdd(xColor, 1, 7, 1, 1);
		
		gridbagAdd(vAniSize, 2, 7, 1, 1);
		gridbagAdd(cbAniSize, 3, 7, 1, 1);

		JPanel manButtonPanel = new JPanel();
		manButtonPanel.add(pictureManage);
		manButtonPanel.setBorder(BorderFactory.createEmptyBorder(0,100,0,0));
		gridbagAdd(manButtonPanel, 5, 7, 1, 1);
		
		gridbagAdd(imageLabel, 5, 5, 1, 8);
		
		gridbagAdd(vRegisDate, 0, 8, 1, 1);
		gridbagAdd(xRegisDate, 1, 8, 1, 1);
		
		gridbagAdd(vDescription, 0, 9, 1, 1);
		gridbagAdd(descriptionScroll, 1, 9, 4, 1);
		
		gridbagAdd(vDscvDate, 0, 10, 1, 1);
		gridbagAdd(xDscvDate, 1, 10, 1, 1);
		
		gridbagAdd(vCage, 2, 10, 1, 1);
		gridbagAdd(cbCage, 3, 10, 1, 1);
		
		gridbagAdd(vDscvPlace, 0, 11, 1, 1);
		gridbagAdd(xDscvPlace, 1, 11, 1, 1);
		
		Component[] cops1 = {modify, cancel, returning};
		CombinePanel buttonPanel = new CombinePanel(cops1,15,0);
		
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(20,600,0,0));
		gridbagAdd(buttonPanel, 0, 12, 7, 1);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout(FlowLayout.LEFT,20,10));
		bottomPanel.add(previous);
		bottomPanel.add(next);
		bottomPanel.setBorder(BorderFactory.createEmptyBorder(0,25,0,0));
		gridbagAdd(bottomPanel, 5, 11 ,3, 1);
	
	}
	
	private void gridbagAdd(Component c, int x, int y, int w , int h) {
		
		gridBagConstraints.gridx = x;
		gridBagConstraints.gridy = y;
		gridBagConstraints.gridwidth = w;
		gridBagConstraints.gridheight = h;
		
		gridBagLayout.setConstraints(c, gridBagConstraints);
		
		add(c);
	}
	
	class ProtAniListButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource().equals(register)){
				new ProtAniRegist();
			}
			else if(e.getSource().equals(pictureManage)) {
				try {
					// 0001 => ABAN_NO
					new PictureManage("0001");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if(e.getSource().equals(previous)) {
				
			}
			else if(e.getSource().equals(next)) {
				
			}
			else if(e.getSource().equals(modify)) {
				modify.setText("Ȯ��");
				JComponent[] changeStatusComps = {cbSex,cbNeutWhet,cbAniSize,cbCage,pictureManage};
				for(JComponent cop: changeStatusComps) {
					cop.setEnabled(true);
				}
				xDescription.setEditable(true);
			}
			else if(e.getSource().equals(cancel)) {
				modify.setText("����");
				JComponent[] changeStatusComps = {cbSex,cbNeutWhet,cbAniSize,cbCage,pictureManage};
				for(JComponent cop: changeStatusComps) {
					cop.setEnabled(false);
				}
				xDescription.setEditable(false);
			}
			else if(e.getSource().equals(returning)) {
				new ReqPrsnRegist();
			}
		}
		
	}

	//�ΰ��� ������Ʈ�� �ϳ��� �гη� ���� JPanel
	class CombinePanel extends JPanel {
		//������Ʈ 1, ������Ʈ 2, �г� ������ ��,�� margin ������ ���ֱ� ���� Flag
		public CombinePanel(Component[] cops, int borderWidth, int borderHeight) {
			//Margin�� �ʿ����� ���� ��
			
			setLayout(new FlowLayout(FlowLayout.LEFT,borderWidth,borderHeight));
			
			for (Component c: cops) {
				add(c);
			}
		}
	}
	
	//����ũ�� ��ȯ �Լ��Դϴ�.
	//https://memorynotfound.com/java-resize-image-fixed-width-height-example/
    private static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }
	
    private void ChangeFont(JComponent[] comps, Font font) {
    	for(JComponent comp: comps) {
    		comp.setFont(font);
    	}
    }
    
	class ProtAniListMouseListener extends MouseAdapter{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			super.mouseClicked(e);
			if(e.getButton()==1) {
				GetProtAni();
			}
		}
		
	}
	
	private void GetProtAni() {
		int clickedRow = eProtAniList.getSelectedRow();
		String cntrNo = cntrNos.get(clickedRow);
		String abanNo = abanNos.get(clickedRow);
		
		StringBuffer query1 = new StringBuffer("SELECT a.ABAN_NO,a.ABAN_TP, rs.RSCU_NO, a.ABAN_NAME, a.AGE, a.MOM_ABAN_NO, a.ANML_KINDS, a.KIND, a.SEX, a.NEUT_WHET, a.COLOR, a.ANML_SIZE, a.REGIS_DATE, a.FEAT, rp.DSCV_DTTM, rp.DSCV_LOC ");
		query1.append("FROM (SELECT RSCU_NO FROM RSCU) rs INNER JOIN (SELECT * FROM ABAN WHERE ABAN_NO='"+abanNo+"') a ");
		query1.append(" ON rs.rscu_no = a.rscu_no INNER JOIN (SELECT * FROM ASSG WHERE ASSG_RES = 'a') a2 ");
		query1.append(" ON a2.assg_no = rs.rscu_no INNER JOIN RPRT rp ON rp.rprt_no = a2.rprt_no ");
	
		StringBuffer query2 = new StringBuffer("SELECT '������'||c.CAGE_ORNU||'('||CASE c.CAGE_SIZE WHEN 's' THEN '��' WHEN 'm' THEN '��' WHEN 'b' THEN '��' END||')' cages ");
		query2.append("FROM (SELECT * ");
		query2.append("	FROM CAGE ");
		query2.append("	WHERE CNTR_NO = '"+cntrNo+"') c INNER JOIN (SELECT * ");
		query2.append("FROM( ");
		query2.append("SELECT CAGE_ORNU FROM CAGE ");
		query2.append("WHERE CNTR_NO ='"+cntrNo+"' ");
		query2.append("MINUS ");
		query2.append("SELECT CAGE_ORNU FROM PROT ");
		query2.append("WHERE CNTR_NO='"+cntrNo+"' ");
		query2.append("AND PROT_END_DATE=to_date('9999-12-31','YYYY-MM-DD') ");
		query2.append("UNION ALL ");
		query2.append("SELECT c.CAGE_ORNU ");
		query2.append("FROM CAGE c INNER JOIN (SELECT * FROM PROT ");
		query2.append("	WHERE ABAN_NO ='"+abanNo+"' ");
		query2.append("	AND PROT_END_DATE = to_date('9999-12-31','YYYY-MM-DD')) p ");
		query2.append("	ON c.cntr_no = p.cntr_no AND c.cage_ornu = p.cage_ornu) ");
		query2.append("ORDER BY 1 ");
		query2.append(") c2 ON c.CAGE_ORNU = c2.CAGE_ORNU ");
		
		StringBuffer query3 = new StringBuffer("SELECT '������'||c.CAGE_ORNU||'('||CASE c.CAGE_SIZE WHEN 's' THEN '��' WHEN 'm' THEN '��' WHEN 'b' THEN '��' END||')' cages ");
		query3.append("FROM CAGE c INNER JOIN (SELECT * FROM PROT ");
		query3.append("	WHERE ABAN_NO ='"+abanNo+"' ");
		query3.append("	AND PROT_END_DATE = to_date('9999-12-31','YYYY-MM-DD')) p ");
		query3.append("	ON c.cntr_no = p.cntr_no AND c.cage_ornu = p.cage_ornu ");
		
		connection();
		
		try {
			//query1
			pstmt = con.prepareStatement(query1.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				
				String abanType = rs.getString("ABAN_TP");
				String anmlKinds = rs.getString("ANML_KINDS");
				String sex = rs.getString("SEX");
				String anmlSize = rs.getString("ANML_SIZE");
				
				String korAbanType = null;
				String korAnmlKinds = null;
				String korSex = null;
				String korAnmlSize = null;
				
				switch(abanType) {
				case "r":
					korAbanType="����";
					break;
				case "b":
					korAbanType="ź��";
					break;
				}
				
				switch(anmlKinds) {
				case "d":
					korAnmlKinds="��";
					break;
				case "c":
					korAnmlKinds="�����";
					break;
				case "e":
					korAnmlKinds="��Ÿ";
					break;
				}
				
				switch(sex) {
				case "m":
					korSex="����";
					break;
				case "f":
					korSex="����";
					break;
				case "u":
					korSex="�̻�";
					break;
				}
				
				switch(anmlSize) {
				case "b":
					korAnmlSize="��";
					break;
				case "m":
					korAnmlSize="��";
					break;
				case "s":
					korAnmlSize="��";
					break;
				}
				
				xAbanAniNo.setText(rs.getString("ABAN_NO"));
				xAbanAniType.setText(korAbanType);
				xRescueNo.setText(rs.getString("RSCU_NO"));
				xAbanAniName.setText(rs.getString("ABAN_NAME"));
				xAge.setText(rs.getString("AGE"));
				xParAniName.setText(rs.getString("MOM_ABAN_NO"));
				xAniType.setText(korAnmlKinds);
				xKind.setText(rs.getString("KIND"));
				cbSex.setSelectedItem(korSex);
				cbNeutWhet.setSelectedItem(rs.getString("NEUT_WHET"));
				xColor.setText(rs.getString("COLOR"));
				cbAniSize.setSelectedItem(korAnmlSize);
				xRegisDate.setText(rs.getString("REGIS_DATE").split(" ")[0]);
				xDescription.setText(rs.getString("FEAT"));
				xDscvDate.setText(rs.getString("DSCV_DTTM"));
				xDscvPlace.setText(rs.getString("DSCV_LOC"));
			}
			
			//query2
			pstmt = con.prepareStatement(query2.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				cbCage.addItem(rs.getString("cages"));
			}
			
			//query2
			pstmt = con.prepareStatement(query3.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				cbCage.setSelectedItem(rs.getString("cages"));
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		disconnection();
		
	}
	
    private void GetProtAniList() {
    	connection();
    	
    	StringBuffer query = new StringBuffer("SELECT p.CNTR_NO, a.ABAN_NO, a.ABAN_NAME, a.ANML_KINDS, a.KIND, a.AGE, a.ANML_SIZE, a.FEAT ");
    	query.append("FROM ABAN a INNER JOIN (SELECT * FROM PROT ");
    	query.append("	WHERE PROT_END_DATE = to_date('9999-12-31','YYYY-MM-DD')) p ");
    	query.append("	ON a.ABAN_NO = p.ABAN_NO ");
    	query.append("ORDER BY 1,2 ");
    	
    	try {
    		
			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				
				cntrNos.add(rs.getString("CNTR_NO"));
				abanNos.add(rs.getString("ABAN_NO"));
				
				String anmlKinds = rs.getString("ANML_KINDS");
				String anmlSize = rs.getString("ANML_SIZE");
				
				String korAnmlKinds = null;
				String korAnmlSize = null;
				
				switch(anmlKinds) {
				case "d":
					korAnmlKinds="��";
					break;
				case "c":
					korAnmlKinds="�����";
					break;
				case "e":
					korAnmlKinds="��Ÿ";
					break;
				}
				
				switch(anmlSize) {
				case "b":
					korAnmlSize="��";
					break;
				case "m":
					korAnmlSize="��";
					break;
				case "s":
					korAnmlSize="��";
					break;
				}
								
				model1.addRow(new Object[] {rs.getString("ABAN_NAME"),korAnmlKinds,rs.getString("KIND"),rs.getString("AGE"),korAnmlSize,rs.getString("FEAT")});
			}
    		
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	
    	disconnection();
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
		new ProtAniList();
	}
}
