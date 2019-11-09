package asmr;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

public class CustRegister extends JPanel implements ActionListener {
	private JLabel vCustRegister, vCustName, vID, vPassword, vPasswordConfirm, vBirthDay, vAddress, vPhone;
	private JTextField xCustName, xID, xAddress, xPhone;
	private JPasswordField xPassword, xPasswordConfirm;
	private JButton bDupConfirm, bAddrSearch, bRegister, bCancel;
	private JDateChooser chooser;
	
	//버튼색
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	
	GridBagLayout gridbaglayout;				// 화면을 구성하는 레이아웃
	GridBagConstraints gridbagconstraints;

	public CustRegister() {
		
		gridbaglayout = new GridBagLayout();		
		gridbagconstraints = new GridBagConstraints();
		
		vCustRegister = new JLabel("회원가입");
		vCustRegister.setFont(new Font("나눔고딕", Font.BOLD, 24));
		vCustRegister.setBorder(new EmptyBorder(0, 10, 20, 0));
		
		vCustName = new JLabel("이름");
		xCustName = new JTextField(15);
		
		vID = new JLabel("아이디");
		xID = new JTextField(15);
		bDupConfirm = new JButton("중복확인");
		bDupConfirm.setBackground(blue);
		bDupConfirm.setForeground(white);
		
		vPassword = new JLabel("비밀번호");
		xPassword = new JPasswordField(15);
		
		vPasswordConfirm = new JLabel("비밀번호확인");
		xPasswordConfirm = new JPasswordField(15);
		
		vBirthDay = new JLabel("생년월일");
		LocalDate now = LocalDate.now();
		Date date = Date.valueOf(now);
		chooser = new JDateChooser(date,"yyyy-MM-dd");
		
		vAddress = new JLabel("주소");
		xAddress = new JTextField(30);
		xAddress.setEditable(false);
		
		bAddrSearch = new JButton("검색");
		bAddrSearch.setBackground(blue);
		bAddrSearch.setForeground(white);
		bAddrSearch.addActionListener(this);
		
		vPhone = new JLabel("전화번호");
		xPhone = new JTextField(15);
		
		bRegister = new JButton("등록");
		bRegister.addActionListener(this);
		bRegister.setBackground(blue);
		bRegister.setForeground(white);
		bCancel = new JButton("취소");
		bCancel.addActionListener(this);
		
		JComponent[] slabel = {vCustName, vID, vPassword, vPasswordConfirm, vBirthDay, vAddress, vPhone,
				xCustName, xID, xPhone, xPassword, xPasswordConfirm};
		ChangeFont(slabel, new Font("나눔고딕", Font.PLAIN, 16));
		JComponent[] sbutton = {bDupConfirm, bAddrSearch, bRegister, bCancel};
		ChangeFont(sbutton, new Font("나눔고딕", Font.BOLD, 16));
		
		UserRegisterView();
	}
	private void UserRegisterView() {
		
		gridbagconstraints.anchor = GridBagConstraints.WEST;		
		gridbagconstraints.ipadx = 7;
		gridbagconstraints.insets = new Insets(5,5,5,5);
		
		gridbagconstraints.weightx=1.0;		
		gridbagconstraints.weighty=1.0;		
				
		setLayout(gridbaglayout);
		gridbagAdd(vCustRegister, 0, 0, 1, 1);
		gridbagAdd(vCustName, 0, 1, 1, 1);
		gridbagAdd(xCustName, 1, 1, 1, 1);
		gridbagAdd(vID, 0, 2, 1, 1);
		gridbagAdd(xID, 1, 2, 1, 1);
		gridbagAdd(bDupConfirm, 2, 2, 1, 1);
		gridbagAdd(vPassword, 0, 3, 1, 1);
		gridbagAdd(xPassword, 1, 3, 1, 1);
		gridbagAdd(vPasswordConfirm, 0, 4, 1, 1);
		gridbagAdd(xPasswordConfirm, 1, 4, 1, 1);
		// 생년월일 미관리
//		gridbagAdd(vBirthDay, 0, 5, 1, 1);
//		gridbagAdd(chooser, 1, 5, 1, 1);
		gridbagAdd(vAddress, 0, 6, 1, 1);
		gridbagAdd(xAddress, 1, 6, 2, 1);
		gridbagAdd(bAddrSearch, 3, 6, 1, 1);
		gridbagAdd(vPhone, 0, 7, 1, 1);
		gridbagAdd(xPhone, 1, 7, 1, 1);
		
		Component[] comps = {bRegister, bCancel};
		CombinePanel btn = new CombinePanel(comps, true);
		btn.setBorder(new EmptyBorder(20, 20, 0, 0));
		
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
		String[] checks = {"확인", "취소"};
		int a;
		if(e.getSource().equals(bAddrSearch)) {
			new NewAddressSearch(xAddress);
		}
		else if(e.getSource().equals(bDupConfirm)){
			
		}
		else if(e.getSource().equals(bCancel)){
			a = JOptionPane.showOptionDialog(this, "회원가입을 취소하시겠습니까?", "메시지", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, checks, checks[0]);
			if(a == 0){
				
			}
			else if(a == 1){
				
			}
		}
		else if(e.getSource().equals(bRegister)){
			a = JOptionPane.showOptionDialog(this, "회원으로 등록하시겠습니까?", "메시지", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, checks, checks[0]);
			if(a == 0){
				
				CustData.initCustData(xCustName.getText(), xAddress.getText(), xPhone.getText(),
						xID.getText(), new String(xPassword.getPassword()));
				CustData.createIsUserCust();
				JOptionPane.showMessageDialog(null, "등록되었습니다.");
			}
			else if(a == 1){
				
			}
		}
	}
	private void ChangeFont(JComponent[] comps, Font font) {
		for(JComponent comp: comps) {
			comp.setFont(font);
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}


}
