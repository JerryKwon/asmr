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
	
	//��ư��
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	
	GridBagLayout gridbaglayout;				// ȭ���� �����ϴ� ���̾ƿ�
	GridBagConstraints gridbagconstraints;

	public CustRegister() {
		
		gridbaglayout = new GridBagLayout();		
		gridbagconstraints = new GridBagConstraints();
		
		vCustRegister = new JLabel("ȸ������");
		vCustRegister.setFont(new Font("�������", Font.BOLD, 24));
		vCustRegister.setBorder(new EmptyBorder(0, 10, 20, 0));
		
		vCustName = new JLabel("�̸�");
		xCustName = new JTextField(15);
		
		vID = new JLabel("���̵�");
		xID = new JTextField(15);
		bDupConfirm = new JButton("�ߺ�Ȯ��");
		bDupConfirm.setBackground(blue);
		bDupConfirm.setForeground(white);
		
		vPassword = new JLabel("��й�ȣ");
		xPassword = new JPasswordField(15);
		
		vPasswordConfirm = new JLabel("��й�ȣȮ��");
		xPasswordConfirm = new JPasswordField(15);
		
		vBirthDay = new JLabel("�������");
		LocalDate now = LocalDate.now();
		Date date = Date.valueOf(now);
		chooser = new JDateChooser(date,"yyyy-MM-dd");
		
		vAddress = new JLabel("�ּ�");
		xAddress = new JTextField(30);
		xAddress.setEditable(false);
		
		bAddrSearch = new JButton("�˻�");
		bAddrSearch.setBackground(blue);
		bAddrSearch.setForeground(white);
		bAddrSearch.addActionListener(this);
		
		vPhone = new JLabel("��ȭ��ȣ");
		xPhone = new JTextField(15);
		
		bRegister = new JButton("���");
		bRegister.setBackground(blue);
		bRegister.setForeground(white);
		bCancel = new JButton("���");
		
		JComponent[] slabel = {vCustName, vID, vPassword, vPasswordConfirm, vBirthDay, vAddress, vPhone,
				xCustName, xID, xPhone, xPassword, xPasswordConfirm};
		ChangeFont(slabel, new Font("�������", Font.PLAIN, 16));
		JComponent[] sbutton = {bDupConfirm, bAddrSearch, bRegister, bCancel};
		ChangeFont(sbutton, new Font("�������", Font.BOLD, 16));
		
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
		gridbagAdd(vBirthDay, 0, 5, 1, 1);
		gridbagAdd(chooser, 1, 5, 1, 1);
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
			new NewAddressSearch(xAddress);
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
