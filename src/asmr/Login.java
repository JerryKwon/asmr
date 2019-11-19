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
	GridBagLayout gridbaglayout;				// 화면을 구성하는 레이아웃
	GridBagConstraints gridbagconstraints;
	
	public Login() {
		
		gridbaglayout = new GridBagLayout();		
		gridbagconstraints = new GridBagConstraints();
		
		vLogin = new JLabel("로그인");
		vLogin.setFont(new Font("나눔고딕", Font.BOLD, 24));
		vLogin.setBorder(new EmptyBorder(0, 10, 20, 0));
		vID = new JLabel("아이디");
		vID.setFont(new Font("나눔고딕", Font.PLAIN, 16));
		xID = new JTextField(15);
		xID.setFont(new Font("나눔고딕", Font.PLAIN, 16));
		vPassword = new JLabel("비밀번호");
		vPassword.setFont(new Font("나눔고딕", Font.PLAIN, 16));
		xPassword = new JPasswordField(15);
		xPassword.setFont(new Font("나눔고딕", Font.PLAIN, 16));
		
		bLogin = new JButton("로그인");
		bLogin.setFont(new Font("나눔고딕", Font.PLAIN, 16));
		bLogin.addActionListener(this);
		
		empID = "-1";
		LoginView();
	}
	private void EmpLoginCheck(String id, String pw){
		if(pw.equals(EmpData.getEmpPwd(id))){
			main.setLogin();
		}
		else{
			//비밀번호 틀림
			JOptionPane.showMessageDialog(null, "ID 혹은 비밀번호를 확인해주세요.");
			clearField();
		}
	}
	private void CustLoginCheck(String id, String pw){
		int custNo;
		custNo = CustData.getCustNo(id);
		if(custNo == 0){
			JOptionPane.showMessageDialog(null, "존재하지 않는 아이디 입니다.");
		}
		else{
			if(pw.equals(CustData.getCustPwd(custNo))){
				main.setCustLogin();
			}
			else{
				JOptionPane.showMessageDialog(null, "비밀번호를 확인해주세요.");
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
					JOptionPane.showMessageDialog(null, "ID 나 비밀번호를 정확하게 입력해주세요", "메시지", JOptionPane.ERROR_MESSAGE);
				}
			}
		}catch(NumberFormatException e1){
			JOptionPane.showMessageDialog(null, "ID 나 비밀번호를 정확하게 입력해주세요", "메시지", JOptionPane.ERROR_MESSAGE);
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
	      //가장 왼쪽 위 gridx, gridy값은 0 			
				
		gridbagconstraints.gridwidth  = w;	//넓이	
		gridbagconstraints.gridheight = h;	//높이
			
	    gridbaglayout.setConstraints(c, gridbagconstraints); //컴포넌트를 컴포넌트 위치+크기 정보에 따라 GridBagLayout에 배치			
				
	   add(c);			
				
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
