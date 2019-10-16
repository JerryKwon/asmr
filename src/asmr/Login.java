package src.asmr;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;


public class Login extends JPanel implements ActionListener{
	private JLabel vLogin, vID, vPassword;
	private JButton bLogin;
	private JTextField xID;
	private JPasswordField xPassword;
	private MainFrame main;
	
	GridBagLayout gridbaglayout;				// ȭ���� �����ϴ� ���̾ƿ�
	GridBagConstraints gridbagconstraints;
	
	public Login() {
		gridbaglayout = new GridBagLayout();		
		gridbagconstraints = new GridBagConstraints();
		
		vLogin = new JLabel("�α���");
		vID = new JLabel("���̵�");
		xID = new JTextField(20);
		vPassword = new JLabel("��й�ȣ");
		xPassword = new JPasswordField(20);
		
		bLogin = new JButton("�α���");
		bLogin.addActionListener(this);
		LoginView();
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(bLogin)){
			main.removeAll();
		}
	}

	private void LoginView() {
		
		gridbagconstraints.anchor = GridBagConstraints.WEST;		
		gridbagconstraints.ipadx = 7;		
				
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
