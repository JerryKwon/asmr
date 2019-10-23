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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class CenterList extends JPanel{
	
	private JButton centerRegist, cageRegist, searchManager, modify, cancel;
	private JLabel vCenterList,vCageList,vCenterInfo,vCenterNum,vEstDate,vCenterName,vPhoneNum,vArea,vOperTime,vOperTimeDash,vCenterManager,vCageNum,vCageBig,vCageMid,vCageSmall,vCageBigCount,vCageMidCount,vCageSmallCount;
	private JTextField xCenterNum,xEstDate,xCenterName,xPhoneNum,xArea,xCenterManager,xCageBig,xCageMid,xCageSmall;
	private JComboBox<String> cbOperTimeOpen,cbOperTimeClose;
	
	private String cntrManagerBdate = null;
	
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "asmr";
	private String password = "asmr";
	
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private ResultSetMetaData rsmd = null;
	
	//���̺�� ��ũ�� �г�
	private JTable eCenterList, eCageList;
	private JScrollPane scrollpane1, scrollpane2;
	
	//�ش� Ŭ������ ���� ���̾ƿ��� GridBag 
	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;
	
	//���̺��� �÷� �迭
	private final String[] col1 = {"���͸�","�ּ�","��ð�"};
	private final String[] col2 = {"������ ����","ũ��"};
	
	//���̺� �÷� ���� - ���͸��
	private DefaultTableModel model1 = new DefaultTableModel(col1,0);
	//���̺� �÷� ���� - ������
	private DefaultTableModel model2 = new DefaultTableModel(col2,0);
	
	// private final String[] phoneNumStart = {"010","02","031","032","033","041","042","043","044","051","052","053","054","055","061","062","063","064"};
	private final String[] operTimeOpen = {"08:00","08:30","09:00","09:30","10:00","10:30","11:00"};
	private final String[] operTimeClose = {"16:00","16:30","17:00","17:30","18:00","18:30","19:00"};
	
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	
//  �����ʵ�
	CenterListButtonListener centerListButtonListener;
	CenterListMouseListener centerListMouseListener;

	
	//CenterList ������
	public CenterList(){
		gridBagLayout = new GridBagLayout();
		gridBagConstraints = new GridBagConstraints();
		
		centerListButtonListener = new CenterListButtonListener();
		centerListMouseListener = new CenterListMouseListener();

		
		//���͸��, ���������, �������� �ؽ�Ʈ
		vCenterList = new JLabel("���͸��");
		vCenterList.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
//		eCenterList = new JTable(model1);
		eCenterList = new JTable(model1) {
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
		
		eCenterList.addMouseListener(centerListMouseListener);
		scrollpane1 = new JScrollPane(eCenterList);
		scrollpane1.setPreferredSize(new Dimension(600,200));
		eCenterList.getColumnModel().getColumn(0).setPreferredWidth(150);
		eCenterList.getColumnModel().getColumn(1).setPreferredWidth(350);
		eCenterList.getColumnModel().getColumn(2).setPreferredWidth(100);
		
		//���������
		vCageList = new JLabel("���������");
		vCageList.setBorder(BorderFactory.createEmptyBorder(0, 20, 10, 0));
//		eCageList = new JTable(model2);
		eCageList = new JTable(model2) {
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };

		scrollpane2 = new JScrollPane(eCageList);
		scrollpane2.setPreferredSize(new Dimension(300,200));
		
		vCenterInfo = new JLabel("��������");
		vCenterInfo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		
		//�������� �Ʒ� �Է�â JLabel�� JTextField
		vCenterNum = new JLabel("���͹�ȣ");
		xCenterNum = new JTextField(10);
		xCenterNum.setEnabled(false);
		
		//��¥ ���� 2001.01.20[10����]
		vEstDate = new JLabel("��������");
		xEstDate = new JTextField(10);
		xEstDate.setEnabled(false);
		
		//���͸�
		vCenterName = new JLabel("���͸�");
		xCenterName = new JTextField(10);
		xCenterName.setEnabled(false);
		
		//��ȭ��ȣ �ϳ��� ���ڿ��� ����
		vPhoneNum = new JLabel("��ȭ��ȣ");
		xPhoneNum = new JTextField(10);
		xPhoneNum.setEnabled(false);
		
		//����
		vArea = new JLabel("����");
		xArea = new JTextField(10);
		xArea.setEnabled(false);
		
		//��ð�
		vOperTime = new JLabel("��ð�");
		vOperTimeDash = new JLabel("~");
		cbOperTimeOpen = new JComboBox<String>(operTimeOpen);
		cbOperTimeOpen.setEnabled(false);
		cbOperTimeClose = new JComboBox<String>(operTimeClose);
		cbOperTimeClose.setEnabled(false);
		
		//������ �̸�(������ - VARCHAR(20)) - �ѱ� 1���ڴ� 3byte 6.667����
		vCenterManager = new JLabel("������");
		xCenterManager = new JTextField(10);
		xCenterManager.setEnabled(false);
		
		//�� �������� ��
		vCageNum = new JLabel("�� ������ ��");
		vCageNum.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		//������
		vCageBig = new JLabel("����");
		xCageBig = new JTextField(2);
		xCageBig.setEnabled(false);
		
		vCageMid = new JLabel("����");
		xCageMid = new JTextField(2);
		xCageMid.setEnabled(false);
		
		vCageSmall = new JLabel("����");
		xCageSmall = new JTextField(2);
		xCageSmall.setEnabled(false);
		
		vCageBigCount = new JLabel("��");
		vCageMidCount = new JLabel("��");
		vCageSmallCount = new JLabel("��");
		
		//��ư
		centerRegist = new JButton("���");
		centerRegist.setBackground(blue);
		centerRegist.setForeground(white);
		centerRegist.addActionListener(centerListButtonListener);
		
		cageRegist = new JButton("���");
		cageRegist.setBackground(blue);
		cageRegist.setForeground(white);
		cageRegist.addActionListener(centerListButtonListener);
		
		searchManager = new JButton("�˻�");
		searchManager.setBackground(blue);
		searchManager.setForeground(white);
		searchManager.setEnabled(false);
		searchManager.addActionListener(centerListButtonListener);
		
		modify = new JButton("����");
		modify.setBackground(blue);
		modify.setForeground(white);
		modify.addActionListener(centerListButtonListener);
		
		cancel = new JButton("���");
		cancel.addActionListener(centerListButtonListener);
		
		JComponent[] vTitleComps = {vCenterList,vCageList};
		ChangeFont(vTitleComps, new Font("�������", Font.BOLD, 24));
		
		vCenterInfo.setFont(new Font("�������", Font.BOLD, 20));
		
		JComponent[] vContextComps	= {vCenterNum,vEstDate,vCenterName,vPhoneNum,vArea,vOperTime,vOperTimeDash,vCenterManager,vCageNum,vCageBig,vCageMid,vCageSmall,vCageBigCount,vCageMidCount,vCageSmallCount};
		ChangeFont(vContextComps, new Font("�������", Font.PLAIN, 16));
		
		JComponent[] bComps = {centerRegist, cageRegist, searchManager, modify, cancel};
		ChangeFont(vContextComps, new Font("�������", Font.BOLD, 16));
		
		CenterListView();
		
		GetCenterList();
	}

	//Component ��ġ
	private void CenterListView() {
		setLayout(gridBagLayout);
		
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.ipadx = 7;
		
		gridBagConstraints.weightx=1.0;
		gridBagConstraints.weighty=1.0;
		
		//���͸�ϰ� ��Ϲ�ư
		gridbagAdd(vCenterList, 0, 0, 1, 1);
		gridbagAdd(centerRegist, 4, 0, 1, 1);
		
		//�������� ��Ϲ�ư
		gridbagAdd(vCageList, 5, 0, 1, 1);
		JPanel plainPanel2 = new JPanel();
		plainPanel2.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
		plainPanel2.add(cageRegist);
		plainPanel2.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 0));
		gridbagAdd(plainPanel2, 9, 0, 1, 1);
		
		//���͸�����̺�, ������������̺�
		gridbagAdd(scrollpane1, 0, 1, 5, 5);
		JPanel plainPanel = new JPanel();
		plainPanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
		plainPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		plainPanel.add(scrollpane2);
		gridbagAdd(plainPanel, 5, 1, 5, 5);
		
		//��������
		gridbagAdd(vCenterInfo, 0, 6, 1, 1);
		
		//���͹�ȣ
		gridbagAdd(vCenterNum, 0, 7, 1, 1);
		gridbagAdd(xCenterNum, 1, 7, 1, 1);
	
		//��������
		gridbagAdd(vEstDate, 2, 7, 1, 1);
		gridbagAdd(xEstDate, 3, 7, 1, 1);
		
		//���͸�
		gridbagAdd(vCenterName,0, 8,1,1);
		gridbagAdd(xCenterName, 1, 8, 1, 1);
		
		//��ȭ��ȣ
		gridbagAdd(vPhoneNum, 2, 8, 1, 1);
		gridbagAdd(xPhoneNum, 3, 8, 1, 1);
		
		//����
		gridbagAdd(vArea, 0, 9, 1, 1);
		gridbagAdd(xArea, 1, 9, 1, 1);
		
		//��ð�
		Component[] cops = {cbOperTimeOpen, vOperTimeDash,cbOperTimeClose};
		CombinePanel operTimePanel = new CombinePanel(cops,0,0);
		
		gridbagAdd(vOperTime, 2, 9, 1, 1);
		gridbagAdd(operTimePanel, 3, 9, 1, 1);
		
//		gridbagAdd(cbOperTimeOpen, 12, 9, 1, 1);
//		gridbagAdd(vOperTimeDash, 13, 9, 1, 1);
//		gridbagAdd(cbOperTimeClose, 14, 9, 1, 1);
		
		//������
		gridbagAdd(vCenterManager, 0, 10, 1, 1);
		JComponent[] centerManagerComps = {xCenterManager, searchManager};
		CombinePanel centerManagerPanel = new CombinePanel(centerManagerComps, 0, 0);
		gridbagAdd(centerManagerPanel, 1, 10, 1, 1);
		
//		gridbagAdd(xCenterManager, 1, 10, 1, 1);
//		gridbagAdd(searchManager, 2, 10, 1, 1);
		
		//�� ������ ��
		gridbagAdd(vCageNum, 0, 11, 1, 1);
		
		//���º� ������ ������ ���� Bottom Panel �� ��ġ
		BottomPanel bottomPanel = new BottomPanel();
		gridbagAdd(bottomPanel, 0, 12, 2, 1);
		
		//�����ư ��ġ
		JComponent[] bComps = {modify,cancel};
		CombinePanel buttonPanel = new CombinePanel(bComps, 10, 0);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(10,325,0,0));
		gridbagAdd(buttonPanel, 0, 13, 21, 1);
		
	}
	
	
	private void gridbagAdd(Component c, int x, int y, int w , int h) {
		
		gridBagConstraints.gridx = x;
		gridBagConstraints.gridy = y;
		gridBagConstraints.gridwidth = w;
		gridBagConstraints.gridheight = h;
		
		gridBagLayout.setConstraints(c, gridBagConstraints);
		
		add(c);
	}
	
	// ���� ��� ��������
	private void GetCenterList() {
		model1.setRowCount(0);
		
		connection();
		
		try {
			StringBuffer query= new StringBuffer("SELECT c.CNTR_NAME name, c.ADDR addr, SUBSTR(c.OPEN_TIME,1,2)||':'||SUBSTR(c.OPEN_TIME,3,2)||'-'||SUBSTR(c.CLSE_TIME,1,2)||':'||SUBSTR(c.CLSE_TIME,3,2) opr_time ");
			query.append("FROM CNTR c");
			
			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {		
				model1.addRow(new Object[] {rs.getString("name"),rs.getString("addr"),rs.getString("opr_time")});
			}
		
		}catch(Exception e1) {
			e1.printStackTrace();
		}
		
		disconnection();
	}
	
	private void GetCenter() {
		int clickedRow = eCenterList.getSelectedRow();
		String cntrName = (String)eCenterList.getValueAt(clickedRow, 0);
		
		GetCenter1(cntrName);
		GetCenter2(cntrName);
	}
	
	private void GetCenter1(String cntrName) {
		connection();
		
		try {
			StringBuffer query= new StringBuffer("SELECT t.CNTR_NO, t.CNTR_NAME, t.ADDR, t.TEL_NO, t.\"AREA\" ,SUBSTR(OPEN_TIME,1,2)||':'||SUBSTR(OPEN_TIME,3,2) OPEN_TIME, SUBSTR(CLSE_TIME,1,2)||':'||SUBSTR(CLSE_TIME,3,2) CLSE_TIME, ESTB_DATE,CNTR_TP,e.EMP_NO,e.EMP_NAME ");
			query.append("FROM (");
			query.append("	SELECT c1.*,wh.EMP_NO");
			query.append("	FROM(");
			query.append("		SELECT * ");
			query.append("		FROM CNTR");
			query.append("		WHERE CNTR_NAME='"+cntrName+"') c1 LEFT OUTER JOIN EMP_WORK_HIST wh ");
			query.append("			ON c1.CNTR_NO = wh.CNTR_NO ");
			query.append("			AND wh.WORK_END_DATE = to_date('9999-12-31','YYYY-MM-DD') ");
			query.append("			AND wh.BIZ_FILD = 'c') t RIGHT OUTER JOIN EMP e ");
			query.append("				ON t.EMP_NO = e.EMP_NO ");
				
			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String[] estbDate = rs.getString("ESTB_DATE").split(" ");
				
				xCenterNum.setText(rs.getString("CNTR_NO"));
				xCenterName.setText(rs.getString("CNTR_NAME"));
				xArea.setText(rs.getString("AREA"));
				xCenterManager.setText(rs.getString("EMP_NAME"));
				xEstDate.setText(estbDate[0]);
				xPhoneNum.setText(rs.getString("TEL_NO"));
				cbOperTimeOpen.setSelectedItem(rs.getString("OPEN_TIME"));
				cbOperTimeClose.setSelectedItem(rs.getString("CLSE_TIME"));
			}
				
		}catch(Exception e2) {
			e2.printStackTrace();
		}
		
		disconnection();
	}
	
	private void GetCenter2(String cntrName) {
		connection();
		
		try {
			StringBuffer query= new StringBuffer("SELECT ct.CAGE_TYPE, NVL(c.CNT,0) CNT ");
			query.append("FROM (");
			query.append("	SELECT 'b' CAGE_TYPE FROM DUAL ");
			query.append("	UNION ALL ");
			query.append("	SELECT 'm' FROM DUAL ");
			query.append("	UNION ALL ");
			query.append("	SELECT 's' FROM DUAL ");
			query.append(") ct LEFT OUTER JOIN (SELECT t.CAGE_SIZE, COUNT(*) CNT ");
			query.append("				FROM (SELECT c1.*,c2.CAGE_ORNU,c2.CAGE_SIZE ");
			query.append("					FROM ( ");
			query.append("							SELECT CNTR_NO ");
			query.append("							FROM CNTR ");
			query.append("							WHERE CNTR_NAME='���Ｍ�ʺ�ȣ����') c1 LEFT OUTER JOIN CAGE c2 ");
			query.append("																ON c1.CNTR_NO=c2.CNTR_NO ) t ");
			query.append("				GROUP BY t.CAGE_SIZE) c ");
			query.append("		ON ct.CAGE_TYPE=c.CAGE_SIZE ");
			
			
			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				switch(rs.getString("CAGE_TYPE")) {
					case "b":
						xCageBig.setText(rs.getString("CNT"));
						break;
					case "m":
						xCageMid.setText(rs.getString("CNT"));
						break;
					case "s":
						xCageSmall.setText(rs.getString("CNT"));
						break;
				}
			}
			
		}catch(Exception e3) {
			e3.printStackTrace();
		}
		
		disconnection();
	}
	
	private void GetCageList() {
		model2.setRowCount(0);
		
		connection();
		
		try {
			StringBuffer query = new StringBuffer("SELECT c1.CNTR_NO,c2.CAGE_ORNU,c2.CAGE_SIZE ");
			query.append("FROM ( ");
			query.append("	SELECT * FROM CNTR ");
			query.append("	WHERE CNTR_NAME='���Ｍ�ʺ�ȣ����') c1 INNER JOIN CAGE c2 ");
			query.append("										ON c1.CNTR_NO = c2.CNTR_NO ");
			
			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String korCageSize = null;
				
				switch(rs.getString("CAGE_SIZE")) {
				case "b":
					korCageSize = "����";
					break;
				case "m":
					korCageSize = "����";
					break;
				case "s":
					korCageSize = "����";
					break;
				
				}
				model2.addRow(new Object[] {rs.getString("CAGE_ORNU"),korCageSize});
			}
			
		}catch(Exception e4) {
			e4.printStackTrace();
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

	
	//������ ���º� ������ ������ ���� Bottom Panel ����
	class BottomPanel extends JPanel{
		public BottomPanel() {
			setLayout(new FlowLayout(FlowLayout.LEFT));

			add(vCageBig);
			add(xCageBig);
			add(vCageBigCount);
			add(vCageMid);
			add(xCageMid);
			add(vCageMidCount);
			add(vCageSmall);
			add(xCageSmall);
			add(vCageSmallCount);
			
		}
	}

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
	
	class CenterListButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource().equals(centerRegist)) {
				try {
					NewCenterRegistration newCenterRegistration= new NewCenterRegistration();
					newCenterRegistration.addWindowListener(new WindowAdapter() {

						@Override
						public void windowClosed(WindowEvent e) {
							// TODO Auto-generated method stub
							super.windowClosed(e);
							GetCenterList();
						}
					
					
					});
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			if(e.getSource().equals(cageRegist)) {
				int clickedRow = eCenterList.getSelectedRow();
				String cntrName = (String)eCenterList.getValueAt(clickedRow, 0);
				CageRegister cageRegister = new CageRegister(cntrName);
				cageRegister.addWindowListener(new WindowAdapter() {

					@Override
					public void windowClosed(WindowEvent e) {
						// TODO Auto-generated method stub
						super.windowClosed(e);
						GetCageList();
						GetCenter2(cntrName);
					}
					
				});
			}
			if(e.getSource().equals(searchManager)) {
				new CenterManagerSearch(xCenterManager,cntrManagerBdate);
			}
			if(e.getSource().equals(modify)) {
				modify.setText("Ȯ��");
				JComponent[] changeStatusComps = {xCenterName,xPhoneNum,xPhoneNum,xArea,cbOperTimeOpen,cbOperTimeClose,searchManager};
				for(JComponent cop: changeStatusComps) {
					cop.setEnabled(true);
				}
			}

			if(e.getSource().equals(cancel)) {
				modify.setText("����");
				JComponent[] changeStatusComps = {xCenterName,xPhoneNum,xPhoneNum,xArea,cbOperTimeOpen,cbOperTimeClose,searchManager};
				for(JComponent cop: changeStatusComps) {
					cop.setEnabled(false);
				}
			}
			
		}
		
	}
	
//  ���͸�����̺� Ŭ���� �߻��ϴ� ������
	class CenterListMouseListener extends MouseAdapter{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			super.mouseClicked(e);
			
//			https://blaseed.tistory.com/18			
			//1:��Ŭ��, 3:��Ŭ��
			if(e.getButton() == 1) {
				GetCageList();
				GetCenter();
			}
		}
	}

	
    private void ChangeFont(JComponent[] comps, Font font) {
    	for(JComponent comp: comps) {
    		comp.setFont(font);
    	}
    }
    
	
	public static void main(String[] args) {
		new CenterList();
	}
}


