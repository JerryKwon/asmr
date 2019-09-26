package asmr;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UserRegister extends JPanel implements ActionListener {
	private JLabel vUserRegister, vUserName, vID, vPassword, vPasswordConfirm, vBirthDay, vAddress, vPhone;
	private JTextField xUserName, xID, xBirthDay, xAddress, xPhone;
	private JPasswordField xPassword, xPasswordConfirm;
	private JButton bCal, bDupConfirm, bAddrSearch, bUserRegister, bCancel;
	
	//버튼색
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	
	GridBagLayout gridbaglayout;				// 화면을 구성하는 레이아웃
	GridBagConstraints gridbagconstraints;

	public UserRegister() {
		
		gridbaglayout = new GridBagLayout();		
		gridbagconstraints = new GridBagConstraints();
		
		vUserRegister = new JLabel("회원가입");
		
		vUserName = new JLabel("이름");
		xUserName = new JTextField(20);
		
		vID = new JLabel("아이디");
		xID = new JTextField(20);
		bDupConfirm = new JButton("중복확인");
		bDupConfirm.setBackground(blue);
		bDupConfirm.setForeground(white);
		
		vPassword = new JLabel("비밀번호");
		xPassword = new JPasswordField(20);
		
		vPasswordConfirm = new JLabel("비밀번호확인");
		xPasswordConfirm = new JPasswordField(20);
		
		vBirthDay = new JLabel("생년월일");
		xBirthDay = new JTextField(20);
		bCal = new JButton(new ImageIcon("images/cal1.png"));
		bCal.setContentAreaFilled(false);
		bCal.setFocusPainted(false);
		bCal.setBorderPainted(false);
		
		vAddress = new JLabel("주소");
		xAddress = new JTextField(20);
		bAddrSearch = new JButton("검색");
		bAddrSearch.setBackground(blue);
		bAddrSearch.setForeground(white);
		bAddrSearch.addActionListener(this);
		
		vPhone = new JLabel("전화번호");
		xPhone = new JTextField(20);
		
		bUserRegister = new JButton("등록");
		bUserRegister.setBackground(blue);
		bUserRegister.setForeground(white);
		bCancel = new JButton("취소");
		
		UserRegisterView();
	}
	private void UserRegisterView() {
		
		gridbagconstraints.anchor = GridBagConstraints.WEST;		
		gridbagconstraints.ipadx = 7;
		
		gridbagconstraints.weightx=1.0;		
		gridbagconstraints.weighty=1.0;		
				
		setLayout(gridbaglayout);
		gridbagAdd(vUserRegister, 0, 0, 1, 1);
		gridbagAdd(vUserName, 0, 1, 1, 1);
		gridbagAdd(xUserName, 1, 1, 1, 1);
		gridbagAdd(vID, 0, 2, 1, 1);
		gridbagAdd(xID, 1, 2, 1, 1);
		gridbagAdd(bDupConfirm, 2, 2, 1, 1);
		gridbagAdd(vPassword, 0, 3, 1, 1);
		gridbagAdd(xPassword, 1, 3, 1, 1);
		gridbagAdd(vPasswordConfirm, 0, 4, 1, 1);
		gridbagAdd(xPasswordConfirm, 1, 4, 1, 1);
		gridbagAdd(vBirthDay, 0, 5, 1, 1);
		gridbagAdd(xBirthDay, 1, 5, 1, 1);
		gridbagAdd(bCal, 2, 5, 1, 1);
		gridbagAdd(vAddress, 0, 6, 1, 1);
		gridbagAdd(xAddress, 1, 6, 1, 1);
		gridbagAdd(bAddrSearch, 2, 6, 1, 1);
		gridbagAdd(vPhone, 0, 7, 1, 1);
		gridbagAdd(xPhone, 1, 7, 1, 1);
		
		Component[] comps = {bUserRegister, bCancel};
		CombinePanel btn = new CombinePanel(comps, true);
		
		gridbagAdd(btn, 1, 8, 1, 1);
		
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
	//두개의 컴포넌트를 하나의 패널로 묶는 JPanel
	class CombinePanel extends JPanel {
		//컴포넌트 1, 컴포넌트 2, 패널 구성시 좌,우 margin 공간을 없애기 위한 Flag
		public CombinePanel(Component[] cops, boolean isBorder) {
			//Margin이 필요하지 않을 때
			if(!isBorder) {
				setLayout(new FlowLayout(FlowLayout.LEFT,0,0));	
			}
			else {
				setLayout(new FlowLayout(FlowLayout.LEFT,15,0));	
			}
			for (Component c: cops) {
				add(c);
			}
		}
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(bAddrSearch)) {
		new AddressSearchPop();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}


}
