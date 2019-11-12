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
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class CustMyPage extends JPanel implements ActionListener{
	
	private JLabel vTitle, vCustName, vPassword, vPassConfirm, vAddress, vPhone, vID;
	private JTextField xCustName, xAddress, xPhone, xID;
	private JPasswordField xPassword, xPassConfirm;
	
	private JButton bSearch, bAdjust, bCancel, bCancel2, bSave;
	
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	
	private Component[] comp1, comp2;
	private CombinePanel btn1, btn2;
	
	GridBagLayout gridbaglayout;
	GridBagConstraints gridbagconstraints;
	
	public CustMyPage(){
		gridbaglayout = new GridBagLayout();		
		gridbagconstraints = new GridBagConstraints();
		
		vTitle = new JLabel("����������");
		vTitle.setFont(new Font("�������", Font.BOLD, 24));
		vTitle.setBorder(new EmptyBorder(0, 10, 20, 0));
		
		vID = new JLabel("���̵�");
		xID = new JTextField(20);
		xID.setEditable(false);
		
		vCustName = new JLabel("�̸�");
		xCustName = new JTextField(20);
		xCustName.setEditable(false);
		
		vPassword = new JLabel("��й�ȣ");
		xPassword = new JPasswordField(20);
		xPassword.setEditable(false);
		
		vPassConfirm = new JLabel("��й�ȣȮ��");
		xPassConfirm = new JPasswordField(20);
		xPassConfirm.setEditable(false);
		
		vAddress = new JLabel("�ּ�");
		xAddress = new JTextField(30);
		xAddress.setEditable(false);
		
		vPhone = new JLabel("��ȭ��ȣ");
		xPhone = new JTextField(20);
		xPhone.setEditable(false);
		
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
		//bSave.setVisible(false);
		
		bCancel = new JButton("���");
		bCancel.addActionListener(this);
		
		bCancel2 = new JButton("���");
		bCancel2.addActionListener(this);
		
		JComponent[] slabel = {vCustName, vPassword, vPassConfirm, vAddress, vPhone, vID};
		ChangeFont(slabel, new Font("�������", Font.PLAIN, 16));
		JComponent[] sbutton = {bSearch, bAdjust, bCancel, bCancel2, bSave};
		ChangeFont(sbutton, new Font("�������", Font.BOLD, 16));
		
		comp1 = new Component[] {bAdjust, bCancel};
		btn1 = new CombinePanel(comp1, true);
		
		comp2 = new Component[] {bSave, bCancel2};
		btn2 = new CombinePanel(comp2, true);
		
		btn1.setVisible(true);
		btn2.setVisible(false);
		
		CustData.setCustData();
		xID.setText((String) CustData.custMydata.get("���̵�"));
		xCustName.setText((String) CustData.custMydata.get("�̸�"));
		xPassword.setText((String) CustData.custMydata.get("��й�ȣ"));
		xAddress.setText((String) CustData.custMydata.get("�ּ�"));
		xPhone.setText((String) CustData.custMydata.get("��ȭ��ȣ"));
		
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
			xPassword.setEditable(true);
			xPassConfirm.setEditable(true);
			xPhone.setEditable(true);
		}
		else if(e.getSource().equals(bSave)){
			
		}
		else if(e.getSource().equals(bCancel2)){
			btn2.setVisible(false);
			btn1.setVisible(true);			
			bSearch.setEnabled(false);
			xPassword.setEditable(false);
			xPassConfirm.setEditable(false);
			xPhone.setEditable(false);
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
		
		gridbagAdd(vPassConfirm, 0,4,1,1);
		gridbagAdd(xPassConfirm, 1,4,1,1);
		
		gridbagAdd(vAddress, 0,5,1,1);
		gridbagAdd(xAddress, 1,5,2,1);
		gridbagAdd(bSearch, 3,5,2,1);
		
		gridbagAdd(vPhone, 0,6,1,1);
		gridbagAdd(xPhone, 1,6,1,1);
		
		//gridbagconstraints.anchor = GridBagConstraints.CENTER;
		
		//gridbagAdd(bAdjust, 1,7,1,1);
		//gridbagAdd(bSave, 1,7,1,1);
		//gridbagAdd(bCancel, 2,7,1,1);
		
		gridbagAdd(btn1, 1, 7, 1, 1);
		gridbagAdd(btn2, 1, 7, 1, 1);
	}
	class CombinePanel extends JPanel {
		//������Ʈ 1, ������Ʈ 2, �г� ������ ��,�� margin ������ ���ֱ� ���� Flag
		public CombinePanel(Component[] cops, boolean isBorder) {
			//Margin�� �ʿ����� ���� ��
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
	      //���� ���� �� gridx, gridy���� 0 			
				
		gridbagconstraints.gridwidth  = w;	//����	
		gridbagconstraints.gridheight = h;	//����	
	     			
	      			
	    gridbaglayout.setConstraints(c, gridbagconstraints); //������Ʈ�� ������Ʈ ��ġ+ũ�� ������ ���� GridBagLayout�� ��ġ			
				
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
