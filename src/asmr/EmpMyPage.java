package src.asmr;
import javax.swing.*;
import java.awt.*;


public class EmpMyPage extends JPanel{
	private JLabel vTitle, vEmpName, vEmpType, vPassword, vPassConfirm, vAddress, vPhone,
			vCenterName, vBizField, vSex, vBirthDay;
	
	private JTextField xEmpName, xEmpType, xAddress, xPhone, xCenterName, xBizField, xSex, xBirthDay;
	private JPasswordField xPassword, xPassConfirm;
	
	private JButton bSearch, bAdjust, bCancel;
	
	GridBagLayout gridbaglayout;
	GridBagConstraints gridbagconstraints;
	
	public EmpMyPage() {
		gridbaglayout = new GridBagLayout();		
		gridbagconstraints = new GridBagConstraints();
		
		vTitle = new JLabel("����������");
		vEmpName = new JLabel("������");
		xEmpName = new JTextField(20);
		xEmpName.setEditable(false);
		
		vEmpType = new JLabel("��������");
		xEmpType = new JTextField(20);
		xEmpType.setEditable(false);
		
		vPassword = new JLabel("��й�ȣ");
		xPassword = new JPasswordField(20);
		
		vPassConfirm = new JLabel("��й�ȣȮ��");
		xPassConfirm = new JPasswordField(20);
		
		vAddress = new JLabel("�ּ�");
		xAddress = new JTextField(30);
		xAddress.setEditable(false);
		
		vPhone = new JLabel("��ȭ��ȣ");
		xPhone = new JTextField(20);
		
		vCenterName = new JLabel("�ҼӼ���");
		xCenterName = new JTextField(20);
		xCenterName.setEditable(false);
		
		vBizField = new JLabel("�����о�");
		xBizField = new JTextField(20);
		xBizField.setEditable(false);
		
		//���� ?
		vSex = new JLabel("����");
		xSex = new JTextField(20);
		xSex.setEditable(false);
		
		vBirthDay = new JLabel("�������");
		xBirthDay = new JTextField(20);
		xBirthDay.setEditable(false);
		
		bSearch = new JButton("�˻�");
		bAdjust = new JButton("����");
		bCancel = new JButton("���");
		
		EmpMyPageView();
		
	}
	private void EmpMyPageView() {
		
		gridbagconstraints.anchor = GridBagConstraints.WEST;		
		gridbagconstraints.ipadx = 7;		
				
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
		
		gridbagAdd(vPassConfirm, 0,4,1,1);
		gridbagAdd(xPassConfirm, 1,4,1,1);
		gridbagAdd(vBirthDay, 2,4,1,1);
		gridbagAdd(xBirthDay, 3,4,1,1);
		
		gridbagAdd(vAddress, 0,5,1,1);
		gridbagAdd(xAddress, 1,5,1,1);
		gridbagAdd(bSearch, 2,5,1,1);
		
		gridbagAdd(vPhone, 0,6,1,1);
		gridbagAdd(xPhone, 1,6,1,1);
		
		gridbagconstraints.anchor = GridBagConstraints.CENTER;
		
		gridbagAdd(bAdjust, 1,7,1,1);
		gridbagAdd(bCancel, 2,7,1,1);
		
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

	}

}
