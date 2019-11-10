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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ProtAnmlSearchPopup extends JFrame {
	
	private JLabel vProtAnmlSearch, vAbanName;
	
	private JTextField xAbanName,xParAbanAniName;
	
	private JButton confirm, cancel, search;
	
	private JTable eProtAnmlList;
	
	private JScrollPane scrollPane1;
	
	private ArrayList<String> protNos;
	private String protNo = null;
	private String protAniName = null;
	
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "asmr";
	private String password = "asmr";
	
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private ResultSetMetaData rsmd = null;
	
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	
	GridBagLayout gridbagLayout;
	GridBagConstraints gridbagConstraints;
	
	ProtAnmlSearchButtonListener protAnmlSearchButtonListener;
	ProtAnmlSearchMouseListener protAnmlSearchMouseListener;
	
	private final String[] col1 = {"���⵿����","��������","ǰ��","Ư¡","������"};
	private DefaultTableModel model1 = new DefaultTableModel(col1,0);
	
	public ProtAnmlSearchPopup(JTextField xParAbanAniName) {
		// TODO Auto-generated constructor stub
		gridbagLayout = new GridBagLayout();
		gridbagConstraints = new GridBagConstraints();
		
		protAnmlSearchButtonListener = new ProtAnmlSearchButtonListener();
		protAnmlSearchMouseListener = new ProtAnmlSearchMouseListener();
		
		protNos = new ArrayList<String>();
		
		this.xParAbanAniName = xParAbanAniName;
		
		eProtAnmlList = new JTable(model1);
		eProtAnmlList.addMouseListener(protAnmlSearchMouseListener);
		scrollPane1 = new JScrollPane(eProtAnmlList);
		scrollPane1.setPreferredSize(new Dimension(500,100));
		
//		vProtAnmlSearch = new JLabel("��ȣ�����˻�");
		
		vAbanName = new JLabel("���⵿����");
		vAbanName.setFont(new Font("�������", Font.PLAIN, 16));
		vAbanName.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 0));
		xAbanName = new JTextField(20);
		search = new JButton("�˻�");
		search.setBackground(blue);
		search.setForeground(white);
		search.addActionListener(protAnmlSearchButtonListener);
		
		confirm = new JButton("Ȯ��");
		confirm.setBackground(blue);
		confirm.setForeground(white);
		confirm.addActionListener(protAnmlSearchButtonListener);
		
		cancel = new JButton("���");
		cancel.addActionListener(protAnmlSearchButtonListener);
		
		JComponent[] jcomps = {search,confirm,cancel};
		for(JComponent jcomp : jcomps) {
			jcomp.setFont(new Font("�������", Font.BOLD, 12));
		}
		
		GetParAniList();
		
		ProtAnmlSearchPopupView();
	}
	
	private void ProtAnmlSearchPopupView() {
		
		setTitle("��ȣ�����˻�");
		
		gridbagConstraints.anchor = GridBagConstraints.WEST;
		gridbagConstraints.ipadx = 7;

		gridbagConstraints.weightx=1.0;
		gridbagConstraints.weighty=1.0;

		setLayout(gridbagLayout);
		
//		gridbagAdd(vProtAnmlSearch, 0, 0, 1, 1);
		
		gridbagAdd(vAbanName, 0, 1, 1, 1);
		gridbagAdd(xAbanName, 2, 1, 2, 1);
		gridbagAdd(search, 5, 1, 1, 1);
		
		JPanel plainPanel = new JPanel();
		plainPanel.add(scrollPane1);
//		plainPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 0));
		
		gridbagAdd(plainPanel, 0, 2, 6, 1);
		
		gridbagAdd(confirm, 2, 3, 1, 1);
		gridbagAdd(cancel, 3, 3, 1, 1);
		
		gridbagConstraints.anchor = GridBagConstraints.CENTER;

		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
	}
	
	private void gridbagAdd(Component c, int x, int y, int w, int h) {			
		
		gridbagConstraints.gridx = x;		
		gridbagConstraints.gridy = y; 		
	      //���� ���� �� gridx, gridy���� 0 			
				
		gridbagConstraints.gridwidth  = w;	//����	
		gridbagConstraints.gridheight = h;	//����	
	     			
	      			
	    gridbagLayout.setConstraints(c, gridbagConstraints); //������Ʈ�� ������Ʈ ��ġ+ũ�� ������ ���� GridBagLayout�� ��ġ			
				
	   add(c);			
				
	   }

	private class ProtAnmlSearchButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource().equals(search)) {
				String abanName = xAbanName.getText();
				if(abanName.length() > 0) {
					SearchParAniName(abanName);
				}
				else {
					GetParAniList();
				}
			}
			else if(e.getSource().equals(confirm)) {
				if(eProtAnmlList.getSelectedRow()!=-1) {
					xParAbanAniName.setText(protAniName);
					dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, "���õ� ������ �����ϴ�.", "�ȳ�", JOptionPane.QUESTION_MESSAGE);
				}
			}
			else if(e.getSource().equals(cancel)) {
				protNo = "";
				dispose();
			}
		}
		
	}
	
	private class ProtAnmlSearchMouseListener extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			super.mouseClicked(arg0);
			
			if(arg0.getButton()==1) {
				int clickedRow = eProtAnmlList.getSelectedRow();
				protNo = protNos.get(clickedRow);
				protAniName = (String)eProtAnmlList.getValueAt(clickedRow, 0);
			}
		}
		
	}
	
	public String getProtNo() {
		return protNo;
	}

	private void SearchParAniName(String abanName) {
		
		model1.setRowCount(0);
		
		StringBuffer query = new StringBuffer("SELECT a.ABAN_NO, a.ABAN_NAME, CASE a.ANML_KINDS WHEN 'd' THEN '��' WHEN 'c' THEN '�����' WHEN 'e' THEN '��Ÿ' END ANML_KINDS, a.KIND, a.FEAT, ");
		query.append("	'������' || c.CAGE_ORNU || '(' ||CASE c.CAGE_SIZE WHEN 'b' THEN '��' WHEN 'm' THEN '��' WHEN 's' THEN '��' END || ')' CAGE ");
		query.append("FROM ABAN a INNER JOIN (SELECT * FROM PROT ");
		query.append("	WHERE PROT_END_DATE=to_date('9999-12-31','YYYY-MM-DD')) p ");
		query.append("	ON a.ABAN_NO = p.ABAN_NO AND a.ABAN_NAME='"+abanName+"' INNER JOIN CAGE c ON p.CNTR_NO = c.CNTR_NO AND p.CAGE_ORNU = c.CAGE_ORNU ");
		query.append("ORDER BY 1 ");

		connection();
		
		try {
			
			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				protNos.add(rs.getString("ABAN_NO"));
				model1.addRow(new Object[] {rs.getString("ABAN_NAME"),rs.getString("ANML_KINDS"),rs.getString("KIND"),rs.getString("FEAT"),rs.getString("CAGE")});
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		disconnection();

		
	}
	
	private void GetParAniList() {
		
		model1.setRowCount(0);
		
		StringBuffer query = new StringBuffer("SELECT a.ABAN_NO, a.ABAN_NAME, CASE a.ANML_KINDS WHEN 'd' THEN '��' WHEN 'c' THEN '�����' WHEN 'e' THEN '��Ÿ' END ANML_KINDS, a.KIND, a.FEAT, ");
		query.append("	'������' || c.CAGE_ORNU || '(' ||CASE c.CAGE_SIZE WHEN 'b' THEN '��' WHEN 'm' THEN '��' WHEN 's' THEN '��' END || ')' CAGE ");
		query.append("FROM ABAN a INNER JOIN (SELECT * FROM PROT ");
		query.append("	WHERE PROT_END_DATE=to_date('9999-12-31','YYYY-MM-DD')) p ");
		query.append("	ON a.ABAN_NO = p.ABAN_NO INNER JOIN CAGE c ON p.CNTR_NO = c.CNTR_NO AND p.CAGE_ORNU = c.CAGE_ORNU ");
		query.append("ORDER BY 1 ");
		
		connection();
		
		try {
			
			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				protNos.add(rs.getString("ABAN_NO"));
				model1.addRow(new Object[] {rs.getString("ABAN_NAME"),rs.getString("ANML_KINDS"),rs.getString("KIND"),rs.getString("FEAT"),rs.getString("CAGE")});
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
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new ProtAnmlSearchPopup(null);

	}

}
