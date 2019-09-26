package asmr;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UserRegister extends JPanel implements ActionListener {
	private JLabel vUserRegister, vUserName, vID, vPassword, vPasswordConfirm, vBirthDay, vAddress, vPhone;
	private JTextField xUserName, xID, xBirthDay, xAddress, xPhone;
	private JPasswordField xPassword, xPasswordConfirm;
	private JButton bCal, bDupConfirm, bAddrSearch, bUserRegister, bCancel;
	
	//��ư��
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	
	GridBagLayout gridbaglayout;				// ȭ���� �����ϴ� ���̾ƿ�
	GridBagConstraints gridbagconstraints;

	public UserRegister() {
		
		gridbaglayout = new GridBagLayout();		
		gridbagconstraints = new GridBagConstraints();
		
		vUserRegister = new JLabel("ȸ������");
		
		vUserName = new JLabel("�̸�");
		xUserName = new JTextField(20);
		
		vID = new JLabel("���̵�");
		xID = new JTextField(20);
		bDupConfirm = new JButton("�ߺ�Ȯ��");
		bDupConfirm.setBackground(blue);
		bDupConfirm.setForeground(white);
		
		vPassword = new JLabel("��й�ȣ");
		xPassword = new JPasswordField(20);
		
		vPasswordConfirm = new JLabel("��й�ȣȮ��");
		xPasswordConfirm = new JPasswordField(20);
		
		vBirthDay = new JLabel("�������");
		xBirthDay = new JTextField(20);
		bCal = new JButton(new ImageIcon("images/cal1.png"));
		bCal.setContentAreaFilled(false);
		bCal.setFocusPainted(false);
		bCal.setBorderPainted(false);
		
		vAddress = new JLabel("�ּ�");
		xAddress = new JTextField(20);
		bAddrSearch = new JButton("�˻�");
		bAddrSearch.setBackground(blue);
		bAddrSearch.setForeground(white);
		bAddrSearch.addActionListener(this);
		
		vPhone = new JLabel("��ȭ��ȣ");
		xPhone = new JTextField(20);
		
		bUserRegister = new JButton("���");
		bUserRegister.setBackground(blue);
		bUserRegister.setForeground(white);
		bCancel = new JButton("���");
		
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
	      //���� ���� �� gridx, gridy���� 0 			
				
		gridbagconstraints.gridwidth  = w;	//����	
		gridbagconstraints.gridheight = h;	//����	
	     			
	      			
	    gridbaglayout.setConstraints(c, gridbagconstraints); //������Ʈ�� ������Ʈ ��ġ+ũ�� ������ ���� GridBagLayout�� ��ġ			
				
	   add(c);			
				
	}
	//�ΰ��� ������Ʈ�� �ϳ��� �гη� ���� JPanel
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
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(bAddrSearch)) {
		new AddressSearchPop();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}


}
