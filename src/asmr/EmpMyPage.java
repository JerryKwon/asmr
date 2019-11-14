package asmr;
import java.awt.Color;
import java.awt.Component;
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


public class EmpMyPage extends JPanel implements ActionListener{
	private JLabel vTitle, vEmpName, vEmpType, vPassword, vNewPass, vPassConfirm, vAddress, vPhone,
			vCenterName, vBizField, vSex, vBirthDay;
	
	private JTextField xEmpName, xEmpType, xAddress, xPhone, xCenterName, xBizField, xSex, xBirthDay;
	private JPasswordField xPassword, xNewPass, xPassConfirm;
	
	private JButton bSearch, bAdjust, bCancel, bSave;
	
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	
	private String nowPass, nowAddr, nowTelNo, newPass, newPassConfirm, newAddr, newTelNo;
	
	GridBagLayout gridbaglayout;
	GridBagConstraints gridbagconstraints;
	
	public EmpMyPage() {
		gridbaglayout = new GridBagLayout();		
		gridbagconstraints = new GridBagConstraints();
		
		vTitle = new JLabel("����������");
		vTitle.setFont(new Font("�������", Font.BOLD, 24));
		vTitle.setBorder(new EmptyBorder(0, 10, 20, 0));
		
		vEmpName = new JLabel("������");
		xEmpName = new JTextField(20);
		xEmpName.setEditable(false);
		
		vEmpType = new JLabel("��������");
		xEmpType = new JTextField(20);
		xEmpType.setEditable(false);
		
		vPassword = new JLabel("���� ��й�ȣ");
		xPassword = new JPasswordField(20);
		xPassword.setEditable(false);
		
		vNewPass = new JLabel("�� ��й�ȣ");
		xNewPass = new JPasswordField(20);
		xNewPass.setEditable(false);
		xNewPass.setText(null);
		
		vPassConfirm = new JLabel("��й�ȣȮ��");
		xPassConfirm = new JPasswordField(20);
		xPassConfirm.setEditable(false);
		xPassConfirm.setText(null);
		
		vAddress = new JLabel("�ּ�");
		xAddress = new JTextField(30);
		xAddress.setEditable(false);
		
		vPhone = new JLabel("��ȭ��ȣ");
		xPhone = new JTextField(20);
		xPhone.setEditable(false);
		
		vCenterName = new JLabel("�ҼӼ���");
		xCenterName = new JTextField(20);
		xCenterName.setEditable(false);
		
		vBizField = new JLabel("�����о�");
		xBizField = new JTextField(20);
		xBizField.setEditable(false);
		
		vSex = new JLabel("����");
		xSex = new JTextField(20);
		xSex.setEditable(false);
		
		vBirthDay = new JLabel("�������");
		xBirthDay = new JTextField(20);
		xBirthDay.setEditable(false);
		
		bSearch = new JButton("�˻�");
		bSearch.addActionListener(this);
		bSearch.setBackground(blue);
		bSearch.setForeground(white);
		bSearch.setEnabled(false);
		
		bAdjust = new JButton("����");
		bAdjust.addActionListener(this);
		bAdjust.setBackground(blue);
		bAdjust.setForeground(white);
		
		bSave = new JButton("Ȯ��");
		bSave.addActionListener(this);
		bSave.setBackground(blue);
		bSave.setForeground(white);
		bSave.setVisible(false);
		
		bCancel = new JButton("���");
		bCancel.addActionListener(this);
		
		JComponent[] slabel = {vEmpName, vEmpType, vPassword, vNewPass, vPassConfirm, vAddress, vPhone,
				vCenterName, vBizField, vSex, vBirthDay};
		ChangeFont(slabel, new Font("�������", Font.PLAIN, 16));
		JComponent[] sbutton = {bSearch, bAdjust, bCancel, bSave};
		ChangeFont(sbutton, new Font("�������", Font.BOLD, 16));
		
		EmpData.setEmpData(Login.getEmpNo());
		xEmpName.setText((String) EmpData.empdata.get("������"));
		xEmpType.setText((String) EmpData.empdata.get("��������"));
		xPassword.setText((String) EmpData.empdata.get("��й�ȣ"));
		xAddress.setText((String) EmpData.empdata.get("�ּ�"));
		xPhone.setText((String) EmpData.empdata.get("��ȭ��ȣ"));
		xCenterName.setText((String) EmpData.empdata.get("�ҼӼ���"));
		xBizField.setText((String) EmpData.empdata.get("�����о�"));
		xSex.setText((String) EmpData.empdata.get("����"));
		xBirthDay.setText((String) EmpData.empdata.get("�������"));
		
		EmpMyPageView();
		
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource().equals(bSearch)){
			new NewAddressSearch(xAddress);
		}
		else if(e.getSource().equals(bAdjust)){
			bAdjust.setVisible(false);
			bSave.setVisible(true);
			bSearch.setEnabled(true);
			xNewPass.setEditable(true);
			xPassConfirm.setEditable(true);
			xPhone.setEditable(true);
			nowPass = new String(xPassword.getPassword());
			nowAddr = xAddress.getText();
			nowTelNo = xPhone.getText();
		}
		else if(e.getSource().equals(bCancel)){
			bAdjust.setVisible(true);
			bSave.setVisible(false);
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
		else if(e.getSource().equals(bSave)){
			newPass = new String(xNewPass.getPassword());
			newPassConfirm = new String(xPassConfirm.getPassword());
			newAddr = xAddress.getText();
			newTelNo = xPhone.getText();
			int ans;
			ans = JOptionPane.showConfirmDialog(null, "�ش� �������� �����Ͻðڽ��ϱ�?", "�޽���", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(ans == JOptionPane.OK_OPTION){
				//0. ����� �ٲٴ°� ? 1. ���� ������ ������ �˻�  2. ��й�ȣ ���� üũ  3. ��й�ȣ ��ġ ���� �˻�
				if(newPass.equals("") && newPass.equals(newPassConfirm)){
					//��й�ȣ �ȹٲ� -> ������ ������ üũ �Ŀ� �ٸ������� ������Ų��.
					if(checkEqual()){
						JOptionPane.showMessageDialog(null, "���� ���� �����մϴ�.", "�޽���", JOptionPane.WARNING_MESSAGE);
					}
					else{
						EmpData.updateEmp(newAddr, newTelNo);
						nowAddr = newAddr;
						nowTelNo = newTelNo;
						xAddress.setEditable(false);
						xPhone.setEditable(false);
						xNewPass.setEditable(false);
						xPassConfirm.setEditable(false);
						bSearch.setEnabled(false);
						bSave.setVisible(false);
						bAdjust.setVisible(true);
						
						JOptionPane.showMessageDialog(null, "����Ǿ����ϴ�.", "�޽���", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else{
					// ��й�ȣ �ٲ� (�н����� üũ)
					if(nowPass.equals(newPass)){
						JOptionPane.showMessageDialog(null, "���� ��й�ȣ�� �����ϴ�.", "�޽���", JOptionPane.WARNING_MESSAGE);
					}
					else if(!newPass.equals(newPassConfirm)){
						JOptionPane.showMessageDialog(null, "��й�ȣ Ȯ�ΰ� ��ġ���� �ʽ��ϴ�.", "�޽���", JOptionPane.WARNING_MESSAGE);
					}
					else if(newPass.equals(newPassConfirm)){
						if(newPass.length() < 4 || newPass.length() > 11){
							JOptionPane.showMessageDialog(null, "��й�ȣ�� 4 ~ 10 �ڸ��Դϴ�.", "�޽���", JOptionPane.WARNING_MESSAGE);
						}
						else{
							if(checkEqual()){
								//��й�ȣ�� ����
								EmpData.updateEmpPass(newPass);
								nowPass = newPass;
								
								xAddress.setEditable(false);
								xPhone.setEditable(false);
								xNewPass.setEditable(false);
								xNewPass.setText(null);
								xPassConfirm.setEditable(false);
								xPassConfirm.setText(null);
								bSearch.setEnabled(false);
								bSave.setVisible(false);
								bAdjust.setVisible(true);
								
								JOptionPane.showMessageDialog(null, "��й�ȣ�� ����Ǿ����ϴ�.", "�޽���", JOptionPane.INFORMATION_MESSAGE);
							}
							else{
								//��й�ȣ, �������� ����
								EmpData.updateEmp(newAddr, newTelNo);
								EmpData.updateEmpPass(newPass);
								
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
								bSave.setVisible(false);
								bAdjust.setVisible(true);
								
								JOptionPane.showMessageDialog(null, "������ ����Ǿ����ϴ�.", "�޽���", JOptionPane.INFORMATION_MESSAGE);
							}
						}
					}
				}
			}
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
	private void EmpMyPageView() {
		
		gridbagconstraints.anchor = GridBagConstraints.WEST;		
		gridbagconstraints.ipadx = 7;
		gridbagconstraints.insets = new Insets(5,5,5,5);
				
		gridbagconstraints.weightx=1.0;		
		gridbagconstraints.weighty=1.0;		
				
		setLayout(gridbaglayout);
		
		gridbagAdd(vTitle, 0,0,1,1);
		gridbagAdd(vEmpName, 0,1,1,1);
		gridbagAdd(xEmpName, 1,1,1,1);
		gridbagAdd(vCenterName, 2,1,1,1);
		gridbagAdd(xCenterName, 3,1,1,1);
		
		gridbagAdd(vEmpType, 0,2,1,1);
		gridbagAdd(xEmpType, 1,2,1,1);
		gridbagAdd(vBizField, 2,2,1,1);
		gridbagAdd(xBizField, 3,2,1,1);
		
		gridbagAdd(vPassword, 0,3,1,1);
		gridbagAdd(xPassword, 1,3,1,1);
		gridbagAdd(vSex, 2,3,1,1);
		gridbagAdd(xSex, 3,3,1,1);
		
		gridbagAdd(vNewPass, 0,4,1,1);
		gridbagAdd(xNewPass, 1,4,1,1);
		gridbagAdd(vBirthDay, 2,4,1,1);
		gridbagAdd(xBirthDay, 3,4,1,1);
		
		gridbagAdd(vPassConfirm, 0,5,1,1);
		gridbagAdd(xPassConfirm, 1,5,1,1);
		
		gridbagAdd(vAddress, 0,6,1,1);
		gridbagAdd(xAddress, 1,6,2,1);
		gridbagAdd(bSearch, 3,6,1,1);
		
		gridbagAdd(vPhone, 0,7,1,1);
		gridbagAdd(xPhone, 1,7,1,1);
		
		gridbagconstraints.anchor = GridBagConstraints.CENTER;
		
		gridbagAdd(bAdjust, 1,8,1,1);
		gridbagAdd(bSave, 1,8,1,1);
		gridbagAdd(bCancel, 2,8,1,1);
		
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
	private void ChangeFont(JComponent[] comps, Font font) {
		for(JComponent comp: comps) {
			comp.setFont(font);
		}
	}

	public static void main(String[] args) {

	}

}
