package asmr;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class Login extends JPanel implements ActionListener{
	private JLabel vLogin, vID, vPassword;
	private JButton bLogin;
	private JTextField xID;
	private JPasswordField xPassword;
	
	private MainFrame main;
	static String empID, custID;
	GridBagLayout gridbaglayout;				// ȭ���� �����ϴ� ���̾ƿ�
	GridBagConstraints gridbagconstraints;
	
	public Login() {
		
		gridbaglayout = new GridBagLayout();		
		gridbagconstraints = new GridBagConstraints();
		
		vLogin = new JLabel("�α���");
		vLogin.setFont(new Font("�������", Font.BOLD, 24));
		vLogin.setBorder(new EmptyBorder(0, 10, 20, 0));
		vID = new JLabel("���̵�");
		vID.setFont(new Font("�������", Font.PLAIN, 16));
		xID = new JTextField(15);
		xID.setFont(new Font("�������", Font.PLAIN, 16));
		vPassword = new JLabel("��й�ȣ");
		vPassword.setFont(new Font("�������", Font.PLAIN, 16));
		xPassword = new JPasswordField(15);
		xPassword.setFont(new Font("�������", Font.PLAIN, 16));
		
		bLogin = new JButton("�α���");
		bLogin.setFont(new Font("�������", Font.PLAIN, 16));
		bLogin.addActionListener(this);
		
		empID = "-1";
		LoginView();
	}
	private void EmpLoginCheck(String id, String pw){
		if(pw.equals(EmpData.getEmpPwd(id))){
			main.setLogin();
		}
		else{
			//��й�ȣ Ʋ��
			JOptionPane.showMessageDialog(null, "ID Ȥ�� ��й�ȣ�� Ȯ�����ּ���.");
			clearField();
		}
	}
	private void CustLoginCheck(String id, String pw){
		int custNo;
		custNo = CustData.getCustNo(id);
		if(custNo == 0){
			JOptionPane.showMessageDialog(null, "�������� �ʴ� ���̵� �Դϴ�.");
		}
		else{
			if(pw.equals(CustData.getCustPwd(custNo))){
				main.setCustLogin();
			}
			else{
				JOptionPane.showMessageDialog(null, "��й�ȣ�� Ȯ�����ּ���.");
			}
		}
				
	}
	public void actionPerformed(ActionEvent e) {
		try{			
			if(e.getSource().equals(bLogin)){
				if(xID.getText() != ""){
					if(checkID(xID.getText())){
						empID = xID.getText();
						EmpLoginCheck(empID, new String(xPassword.getPassword()));
					}
					else{
						custID = xID.getText();
						CustLoginCheck(custID, new String(xPassword.getPassword()));
					}
				}
				else if(xID.getText() == ""){
					JOptionPane.showMessageDialog(null, "ID �� ��й�ȣ�� ��Ȯ�ϰ� �Է����ּ���", "�޽���", JOptionPane.ERROR_MESSAGE);
				}
			}
		}catch(NumberFormatException e1){
			JOptionPane.showMessageDialog(null, "ID �� ��й�ȣ�� ��Ȯ�ϰ� �Է����ּ���", "�޽���", JOptionPane.ERROR_MESSAGE);
		}
	}
	private boolean checkID(String ID){
		Pattern pint = Pattern.compile("(^[0-9]*$)");
		Matcher mint = pint.matcher(ID);
		
		return mint.find();
	}
	public void setMain(MainFrame main){
		this.main = main;
	}
	public void clearField(){
		xID.setText(null);
		xPassword.setText(null);
		empID = null;
		custID = null;
	}
	static String getEmpNo(){
		return empID;
	}
	private void LoginView() {
		
		gridbagconstraints.anchor = GridBagConstraints.WEST;		
		gridbagconstraints.ipadx = 7;	
		gridbagconstraints.insets = new Insets(5,5,5,5);
				
		gridbagconstraints.weightx=1.0;		
		gridbagconstraints.weighty=1.0;		
				
		setLayout(gridbaglayout);
		gridbagAdd(vLogin, 0, 0, 2, 1);
		gridbagAdd(vID, 0, 1, 1, 1);
		gridbagAdd(xID, 1, 1, 1, 1);
		gridbagAdd(vPassword, 0, 2, 1, 1);
		gridbagAdd(xPassword, 1, 2, 1, 1);
		gridbagconstraints.fill = GridBagConstraints.VERTICAL;
		gridbagAdd(bLogin, 2, 1, 1, 2);
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
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
