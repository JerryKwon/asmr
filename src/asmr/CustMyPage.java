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

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class CustMyPage extends JPanel implements ActionListener{
	
	private JLabel vTitle, vCustName, vPassword, vNewPass, vPassConfirm, vAddress, vPhone, vID;
	private JTextField xCustName, xAddress, xPhone, xID;
	private JPasswordField xPassword, xNewPass, xPassConfirm;
	
	private JButton bSearch, bAdjust, bCancel, bCancel2, bSave;
	
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	
	private String nowAddr, nowTelNo, nowPass, newAddr, newTelNo, newPass, newPassConfirm;
	
	private Component[] comp1, comp2;
	private CombinePanel btn1, btn2;
	
	GridBagLayout gridbaglayout;
	GridBagConstraints gridbagconstraints;
	
	public CustMyPage(){
		setBackground(MainFrame.bgc);
		gridbaglayout = new GridBagLayout();		
		gridbagconstraints = new GridBagConstraints();
		
		vTitle = new JLabel("마이페이지");
		vTitle.setFont(new Font("나눔고딕", Font.BOLD, 24));
		vTitle.setBorder(new EmptyBorder(0, 10, 20, 0));
		
		vID = new JLabel("아이디");
		xID = new JTextField(20);
		xID.setEditable(false);
		
		vCustName = new JLabel("이름");
		xCustName = new JTextField(20);
		xCustName.setEditable(false);
		
		vPassword = new JLabel("현재 비밀번호");
		xPassword = new JPasswordField(20);
		xPassword.setEditable(false);
		
		vNewPass = new JLabel("새 비밀번호");
		xNewPass = new JPasswordField(20);
		xNewPass.setEditable(false);
		
		vPassConfirm = new JLabel("비밀번호확인");
		xPassConfirm = new JPasswordField(20);
		xPassConfirm.setEditable(false);
		
		vAddress = new JLabel("주소");
		xAddress = new JTextField(30);
		xAddress.setEditable(false);
		
		vPhone = new JLabel("전화번호");
		xPhone = new JTextField(20);
		xPhone.setEditable(false);
		
		bSearch = new JButton("검색");
		bSearch.addActionListener(this);
		bSearch.setBackground(blue);
		bSearch.setForeground(white);
		bSearch.setEnabled(false);
		
		bAdjust = new JButton("수정");
		bAdjust.addActionListener(this);
		bAdjust.setBackground(blue);
		bAdjust.setForeground(white);
		
		bSave = new JButton("확인");
		bSave.addActionListener(this);
		bSave.setBackground(blue);
		bSave.setForeground(white);
		//bSave.setVisible(false);
		
		bCancel = new JButton("취소");
		bCancel.addActionListener(this);
		
		bCancel2 = new JButton("취소");
		bCancel2.addActionListener(this);
		
		JComponent[] slabel = {vCustName, vPassword, vNewPass, vPassConfirm, vAddress, vPhone, vID};
		ChangeFont(slabel, new Font("나눔고딕", Font.PLAIN, 16));
		JComponent[] sbutton = {bSearch, bAdjust, bCancel, bCancel2, bSave};
		ChangeFont(sbutton, new Font("나눔고딕", Font.BOLD, 16));
		
		comp1 = new Component[] {bAdjust, bCancel};
		btn1 = new CombinePanel(comp1, true);
		btn1.setBackground(MainFrame.bgc);
		
		comp2 = new Component[] {bSave, bCancel2};
		btn2 = new CombinePanel(comp2, true);
		btn2.setBackground(MainFrame.bgc);
		
		btn1.setVisible(true);
		btn2.setVisible(false);
		
		CustData.setCustData();
		xID.setText((String) CustData.custMydata.get("아이디"));
		xCustName.setText((String) CustData.custMydata.get("이름"));
		xPassword.setText((String) CustData.custMydata.get("비밀번호"));
		xAddress.setText((String) CustData.custMydata.get("주소"));
		xPhone.setText((String) CustData.custMydata.get("전화번호"));
		
		CustMyPageView();
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource().equals(bSearch)){
			new NewAddressSearch(xAddress);
		}
		else if(e.getSource().equals(bAdjust)){
			btn1.setVisible(false);
			btn2.setVisible(true);
			bSearch.setEnabled(true);
			xNewPass.setEditable(true);
			xPassConfirm.setEditable(true);
			xPhone.setEditable(true);
			nowPass = new String(xPassword.getPassword());
			nowAddr = xAddress.getText();
			nowTelNo = xPhone.getText();
		}
		else if(e.getSource().equals(bSave)){
			newPass = new String(xNewPass.getPassword());
			newPassConfirm = new String(xPassConfirm.getPassword());
			newAddr = xAddress.getText();
			newTelNo = xPhone.getText();
			int ans;
			ans = JOptionPane.showConfirmDialog(null, "해당 내용으로 수정하시겠습니까?", "메시지", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(ans == JOptionPane.OK_OPTION){
				//0. 비번을 바꾸는가 ? 1. 원래 정보와 같은지 검사  2. 비밀번호 조건 체크  3. 비밀번호 일치 여부 검사
				if(newPass.equals("") && newPass.equals(newPassConfirm)){
					//비밀번호 안바꿈 -> 기존과 같은지 체크 후에 다른정보만 수정시킨다.
					if(checkEqual()){
						JOptionPane.showMessageDialog(null, "변경된 내용이 없습니다.", "메시지", JOptionPane.WARNING_MESSAGE);
					}
					else{
						CustData.updateCust(newAddr, newTelNo);
						nowAddr = newAddr;
						nowTelNo = newTelNo;
						xAddress.setEditable(false);
						xPhone.setEditable(false);
						xNewPass.setEditable(false);
						xPassConfirm.setEditable(false);
						bSearch.setEnabled(false);
						btn2.setVisible(false);
						btn1.setVisible(true);
						
						JOptionPane.showMessageDialog(null, "변경되었습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else{
					// 비밀번호 바꿈 (패스워드 체크)
					if(nowPass.equals(newPass)){
						JOptionPane.showMessageDialog(null, "현재 비밀번호와 같습니다.", "메시지", JOptionPane.WARNING_MESSAGE);
					}
					else if(!newPass.equals(newPassConfirm)){
						JOptionPane.showMessageDialog(null, "비밀번호 확인과 일치하지 않습니다.", "메시지", JOptionPane.WARNING_MESSAGE);
					}
					else if(newPass.equals(newPassConfirm)){
						if(newPass.length() < 4 || newPass.length() > 11){
							JOptionPane.showMessageDialog(null, "비밀번호는 4 ~ 10 자리입니다.", "메시지", JOptionPane.WARNING_MESSAGE);
						}
						else{
							if(checkEqual()){
								//비밀번호만 변경
								CustData.updateCustPass(newPass);
								nowPass = newPass;
								
								xAddress.setEditable(false);
								xPhone.setEditable(false);
								xNewPass.setEditable(false);
								xNewPass.setText(null);
								xPassConfirm.setEditable(false);
								xPassConfirm.setText(null);
								bSearch.setEnabled(false);
								btn2.setVisible(false);
								btn1.setVisible(true);
								
								JOptionPane.showMessageDialog(null, "비밀번호가 변경되었습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
							}
							else{
								//비밀번호, 개인정보 변경
								CustData.updateCust(newAddr, newTelNo);
								CustData.updateCustPass(newPass);
								
								nowAddr = newAddr;
								nowTelNo = newTelNo;
								nowPass = newPass;
								xAddress.setEditable(false);
								xPhone.setEditable(false);
								xNewPass.setText(null);
								xNewPass.setEditable(false);
								xPassConfirm.setText(null);
								xPassConfirm.setEditable(false);
								bSearch.setEnabled(false);
								btn2.setVisible(false);
								btn1.setVisible(true);
								
								JOptionPane.showMessageDialog(null, "정보가 변경되었습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
							}
						}
					}
				}
			}
		}
		else if(e.getSource().equals(bCancel2)){
			btn2.setVisible(false);
			btn1.setVisible(true);			
			bSearch.setEnabled(false);
			xNewPass.setEditable(false);
			xPassConfirm.setEditable(false);
			xPhone.setEditable(false);
			xPassword.setText(nowPass);
			xNewPass.setText(null);
			xPassConfirm.setText(null);
			xAddress.setText(nowAddr);
			xPhone.setText(nowTelNo);
		}
	}
	private boolean checkEqual(){
		if(nowAddr.equals(newAddr) && nowTelNo.equals(newTelNo)){
			return true;
		}
		else{
			return false;
		}
	}
	private void CustMyPageView(){
		gridbagconstraints.anchor = GridBagConstraints.WEST;		
		gridbagconstraints.ipadx = 7;
		gridbagconstraints.insets = new Insets(5,5,5,5);
				
		gridbagconstraints.weightx=1.0;		
		gridbagconstraints.weighty=1.0;		
				
		setLayout(gridbaglayout);
		
		gridbagAdd(vTitle, 0,0,1,1);
		
		gridbagAdd(vID, 0,1,1,1);
		gridbagAdd(xID, 1,1,1,1);
		
		gridbagAdd(vCustName, 0,2,1,1);
		gridbagAdd(xCustName, 1,2,1,1);
		
		gridbagAdd(vPassword, 0,3,1,1);
		gridbagAdd(xPassword, 1,3,1,1);
		
		gridbagAdd(vNewPass, 0,4,1,1);
		gridbagAdd(xNewPass, 1,4,1,1);
		
		gridbagAdd(vPassConfirm, 0,5,1,1);
		gridbagAdd(xPassConfirm, 1,5,1,1);
		
		gridbagAdd(vAddress, 0,6,1,1);
		gridbagAdd(xAddress, 1,6,2,1);
		gridbagAdd(bSearch, 3,6,2,1);
		
		gridbagAdd(vPhone, 0,7,1,1);
		gridbagAdd(xPhone, 1,7,1,1);
		
		//gridbagconstraints.anchor = GridBagConstraints.CENTER;
		
		//gridbagAdd(bAdjust, 1,7,1,1);
		//gridbagAdd(bSave, 1,7,1,1);
		//gridbagAdd(bCancel, 2,7,1,1);
		
		gridbagAdd(btn1, 1, 8, 1, 1);
		gridbagAdd(btn2, 1, 8, 1, 1);
	}
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
	private void ChangeFont(JComponent[] comps, Font font) {
    	for(JComponent comp: comps) {
    		comp.setFont(font);
    	}
    }

}
