package asmr;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

public class EmpRegister extends JFrame{
	private JLabel vEmpRegist,vEmpName,vBelongCenter,vEmpType,vWorkType,vBirthDate,vGender,vAddress,vPhoneNum;
	private JTextField xEmpName,xBelongCenter,xBirthDate,xAddress,xPhoneNum;
	private JButton centerSearch,addressSearch, imageButton, register, cancel;
	private JComboBox<String> cbEmpType,cbWorkType,cbGender;
	private BufferedImage buttonIcon;
	private JDateChooser chooser;
	
	private static String hint = "- �� ������ 13�ڸ� ����";
	
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "asmr";
	private String password = "asmr";
	
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private ResultSetMetaData rsmd = null;
	
	private final String[] empTypeDiv = {"������","�����"};
	private final String[] workTypeDiv1 = {"������","��������"};
	private final String[] workTypeDiv2 = {"������","���ǻ�","��ȣ��������","�繫��������","���⵿��������"};
	private final String[] genterDiv = {"��","��"};
	
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	
	EmpRegisterButtonListener empRegisterButtonListener;
	
	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;
	
	public EmpRegister() throws IOException {
	
		gridBagLayout = new GridBagLayout();
		gridBagConstraints = new GridBagConstraints();
		
		empRegisterButtonListener = new EmpRegisterButtonListener();
		
		vEmpRegist = new JLabel("�������");
		
		vEmpName = new JLabel("������");
		xEmpName = new JTextField(10);
		
		//���Ͱ˻��˾�(CenterSearch)�� Listener�� ȭ�鿬�� �ϼž��մϴ�.
		vBelongCenter = new JLabel("�ҼӼ���");
		xBelongCenter = new JTextField(10);
		xBelongCenter.setEnabled(false);
		centerSearch = new JButton("�˻�");
		centerSearch.setBackground(blue);
		centerSearch.setForeground(white);
		centerSearch.addActionListener(empRegisterButtonListener);
		
		vEmpType = new JLabel("��������");
		cbEmpType = new JComboBox<String>(empTypeDiv);
		
		vWorkType = new JLabel("�����о�");
		cbWorkType = new JComboBox<String>();
		
		vBirthDate = new JLabel("�������");
		xBirthDate = new JTextField(10);
		xBirthDate.setEnabled(false);
		
		LocalDate now = LocalDate.now();
		Date date = Date.valueOf(now);
		chooser = new JDateChooser(date,"yyyy-MM-dd");
		
		buttonIcon = ImageIO.read(new File("images/cal1.png"));
		imageButton = new JButton(new ImageIcon(buttonIcon));
		imageButton.setBorderPainted(false);
		imageButton.setContentAreaFilled(false);
		imageButton.setFocusPainted(false);
		
		vGender = new JLabel("����");
		cbGender = new JComboBox<String>(genterDiv);
		
		vAddress = new JLabel("�ּ�");
		xAddress = new JTextField(15);
		xAddress.setEditable(false);
		addressSearch = new JButton("�˻�");
		addressSearch.setBackground(blue);
		addressSearch.setForeground(white);
		addressSearch.addActionListener(empRegisterButtonListener);
		
		vPhoneNum = new JLabel("��ȭ��ȣ");
		xPhoneNum = new JTextField(12);
		xPhoneNum.setText(hint);
		xPhoneNum.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if(xPhoneNum.getText().trim().length()==0) {
					xPhoneNum.setText(hint);
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				xPhoneNum.setText("");
			}
		});
		
		register = new JButton("���");
		register.setBackground(blue);
		register.setForeground(white);
		register.addActionListener(empRegisterButtonListener);
		
		cancel = new JButton("���");
		cancel.addActionListener(empRegisterButtonListener);
		
		EmpRegisterView();
		
		JComponent[] vComps = {vEmpName,vBelongCenter,vEmpType,vWorkType,vBirthDate,vGender,vAddress,vPhoneNum};
		ChangeFont(vComps, new Font("�������", Font.PLAIN, 16));
		
		JComponent[] bComps = {centerSearch,addressSearch, imageButton, register, cancel};
		ChangeFont(bComps, new Font("�������", Font.BOLD, 12));
	}
	
	private void EmpRegisterView() {
		
		setLayout(gridBagLayout);
		setTitle("�������");
		
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.ipadx = 7;
		
		gridBagConstraints.weightx=1.0;
		gridBagConstraints.weighty=1.0;
		
//		gridbagAdd(vEmpRegist, 0, 0, 1, 1);
		
		gridbagAdd(vEmpName, 0, 1, 1, 1);
		gridbagAdd(xEmpName, 1, 1, 1, 1);
		
		gridbagAdd(vBelongCenter, 2, 1, 1, 1);
		gridbagAdd(xBelongCenter, 3, 1, 1, 1);
		gridbagAdd(centerSearch, 4, 1, 1, 1);
		
		gridbagAdd(vEmpType, 0, 2, 1, 1);
		gridbagAdd(cbEmpType, 1, 2, 1, 1);
		
		gridbagAdd(vWorkType, 2, 2, 1, 1);
		gridbagAdd(cbWorkType, 3, 2, 2, 1);
		
		gridbagAdd(vBirthDate, 0, 3, 1, 1);
//		JComponent[] bdSet = {xBirthDate,chooser};
//		JComponent[] bdSet = {chooser};
//		CombinePanel birthDatePanel = new CombinePanel(bdSet, 0 , 0);
		gridbagAdd(chooser, 1, 3, 2, 1);
		
		gridbagAdd(vGender, 2, 3, 1, 1);
		gridbagAdd(cbGender, 3, 3, 1, 1);
		
		gridbagAdd(vAddress, 0, 4, 1, 1);
		JComponent[] addrSet = {xAddress, addressSearch};
		CombinePanel addressSearchPanel = new CombinePanel(addrSet, 0, 0);
		gridbagAdd(addressSearchPanel, 1, 4, 1, 1);

		gridbagAdd(vPhoneNum, 0, 5, 1, 1);
		gridbagAdd(xPhoneNum, 1, 5, 1, 1);

		JComponent[] buttons = {register, cancel};
		CombinePanel registerAndCancel = new CombinePanel(buttons, 15, 0);
		registerAndCancel.setBorder(BorderFactory.createEmptyBorder(0, 220, 0, 0));
		gridbagAdd(registerAndCancel, 0, 6, 5, 1);
		
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);

	}
	
	private void gridbagAdd(Component c, int x, int y, int w , int h) {
		
		gridBagConstraints.gridx = x;
		gridBagConstraints.gridy = y;
		gridBagConstraints.gridwidth = w;
		gridBagConstraints.gridheight = h;
		
		gridBagLayout.setConstraints(c, gridBagConstraints);
		
		add(c);
	}
	
	private String GetCenterType(String cntrNo) {
		String result = "";
		
		StringBuffer query = new StringBuffer("SELECT CNTR_TP FROM CNTR WHERE CNTR_NO='"+cntrNo+"' ");
		
		connection();
		
		try {
			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				result = rs.getString("CNTR_TP");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		disconnection();
		
		return result;
	}
	
	class EmpRegisterButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource().equals(centerSearch)) {
				CenterSearch centerSearch = new CenterSearch(xBelongCenter);
				centerSearch.addWindowListener(new WindowAdapter() {

					@Override
					public void windowClosed(WindowEvent e) {
						// TODO Auto-generated method stub
						super.windowClosed(e);
						cbWorkType.removeAllItems();
						String cntrNo = centerSearch.getCntrNo();
						String cntrType = GetCenterType(cntrNo);
						if(cntrType.equals("h")) {
							for(String workType:workTypeDiv1) {
								cbWorkType.addItem(workType);
							}
						}
						else if(cntrType.equals("n")) {
							for(String workType:workTypeDiv2) {
								cbWorkType.addItem(workType);
							}
						}
					}
				});
			}
			else if(e.getSource().equals(addressSearch)) {
				new NewAddressSearch(xAddress);
			}
			else if(e.getSource().equals(register)) {
				String empName = xEmpName.getText().trim();
				String belongCntr = xBelongCenter.getText().trim();
				String addr = xAddress.getText().trim();
				String phoneNum = xPhoneNum.getText().trim();
			
				int result = JOptionPane.showConfirmDialog(null, "�ű������� ����Ͻðڽ��ϱ�?", "�޽���", JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.OK_OPTION) {
				
					if(empName.isEmpty()||belongCntr.isEmpty()||addr.isEmpty()||phoneNum.isEmpty()) {
						JOptionPane.showMessageDialog(null, "���� �⺻������ �Է��ϼ���", "�޽���", JOptionPane.ERROR_MESSAGE);
					}
					
					else {
					
						if(NumberFormatCheck(phoneNum)) {
							RegistEmp();
							dispose();
						}
						else {
							JOptionPane.showMessageDialog(null, "��ȭ��ȣ ������ Ȯ���ϼ���.(������ \"-\" ���� ���� 13�ڸ�)", "�޽���", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
			
			else if(e.getSource().equals(cancel)) {
				dispose();
			}
		}
		
	}
	
	private boolean NumberFormatCheck(String phoneNum) {
		
		if(phoneNum.length()>13)
			return false;
		else {
			String repStr = phoneNum.replaceAll("[0-9]", "");
			String[] splitStr = repStr.split("");
			System.out.println(splitStr.toString());
			System.out.println(splitStr.length);
			if(splitStr.length==2)
				return true;
			else return false;
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
	
	private void RegistEmp() {
		connection();
		
		String empName = xEmpName.getText();
		String cntrName = xBelongCenter.getText();
		String korEmpType = (String)cbEmpType.getSelectedItem();
		String korBizFild = (String)cbWorkType.getSelectedItem();
		String bdate = ((JTextField)chooser.getDateEditor().getUiComponent()).getText();
		String korGender = (String)cbGender.getSelectedItem();
		String addr = xAddress.getText();
		String telNo = xPhoneNum.getText();
		
		String engEmpType = null;
		String engBizFild = null;
		String engGender = null;
		
		switch(korEmpType) {
		case "������":
			engEmpType="f";
			break;
		case "�����":
			engEmpType="c";
			break;
		}
		
		switch(korBizFild) {
		case "������":
			engBizFild = "c";
			break;
		case "��������":
			engBizFild = "m";
			break;
		case "���ǻ�":
			engBizFild = "d";
			break;
		case "��ȣ��������":
			engBizFild = "p";
			break;
		case "�繫��������":
			engBizFild = "o";
			break;
		case "���⵿��������":
			engBizFild = "r";
			break;
		}
		
		switch(korGender) {
		case "��":
			engGender = "m";
			break;
		case "��":
			engGender = "f";
			break;
		}
		
//		String[] pwds = bdate.split("-");
//		StringBuffer sb = new StringBuffer("");
//		for(String element:pwds) {
//			sb.append(element);
//		}
//		String pwd = sb.toString();
		
		try {
			StringBuffer query1 = new StringBuffer("INSERT INTO EMP(EMP_NO, EMP_NAME, BRTH_YEAR_MNTH_DAY, ADDR, SEX, TEL_NO) ");
			query1.append("SELECT ");
			query1.append("	CASE WHEN SUBSTR(EMP_NO,2,1)=9 AND SUBSTR(EMP_NO,3,1)=9 AND SUBSTR(EMP_NO,4,1)=9 ");
			query1.append("		THEN to_char(SUBSTR(EMP_NO,1,1)+1) ");
			query1.append("		ELSE SUBSTR(EMP_NO,1,1) END || ");
			query1.append("	CASE WHEN SUBSTR(EMP_NO,3,1)=9 AND SUBSTR(EMP_NO,4,1)=9 ");
			query1.append("		THEN to_char(SUBSTR(EMP_NO,2,1)+1) ");
			query1.append("		ELSE SUBSTR(EMP_NO,2,1) END || ");
			query1.append("	CASE WHEN SUBSTR(EMP_NO,4,1)=9 ");
			query1.append("		THEN to_char(SUBSTR(EMP_NO,3,1)+1) ");
			query1.append("		ELSE SUBSTR(EMP_NO,3,1) END || ");
			query1.append("	CASE WHEN SUBSTR(EMP_NO,4,1) = 9 ");
			query1.append("		THEN '0' ");
			query1.append("		ELSE to_char((SUBSTR(EMP_NO,4,1)+1)) END EMP_NO, ");
			query1.append("	'"+empName+"' EMP_NAME, ");
			query1.append("	'"+bdate+"' BDATE, ");
			query1.append("	'"+addr+"' ADDR, ");
			query1.append("	'"+engGender+"' SEX, ");
			query1.append("	'"+telNo+"' TEL_NO ");
			query1.append("FROM( ");
			query1.append("	SELECT /*+ INDEX_DESC(EMP EMP_PK) */ NVL(EMP_NO,0) EMP_NO ");
			query1.append("	FROM EMP ");
			query1.append("	WHERE ROWNUM=1) e ");
			
			pstmt = con.prepareStatement(query1.toString());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				con.commit();
			}
			
			StringBuffer query2 = new StringBuffer("INSERT INTO EMP_WORK_HIST(EMP_NO,WORK_START_DATE,CNTR_NO,EMP_TP,BIZ_FILD) ");
			query2.append("SELECT EMP_NO, ");
			query2.append("		  TRUNC(SYSDATE) WORK_START_DATE, ");
			query2.append("		  c.CNTR_NO, ");
			query2.append("		  '"+engEmpType+"' EMP_TP, ");
			query2.append("		  '"+engBizFild+"' BIZ_FILD ");
			query2.append("FROM( ");
			query2.append("	SELECT /*+ INDEX_DESC(EMP EMP_PK) */ NVL(EMP_NO,0) EMP_NO ");
			query2.append("	FROM EMP ");
			query2.append("	WHERE ROWNUM=1) e, (SELECT * FROM CNTR WHERE CNTR_NAME='"+cntrName+"') c ");
			
			pstmt = con.prepareStatement(query2.toString());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				con.commit();
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
	
    private void ChangeFont(JComponent[] comps, Font font) {
    	for(JComponent comp: comps) {
    		comp.setFont(font);
    	}
    }
	
	public static void main(String[] args) throws IOException {
		new EmpRegister();
	}
}
