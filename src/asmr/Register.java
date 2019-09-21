package asmr;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

public class Register extends JFrame {			
	private JLabel vTitle, vEmpName, vEmpPhone, vEmpRegist, vEmpHireDate, vEmpClass, vDeptName, vEmpSal, vSpvName, vEmpAnin, vEmpComm,		
	vDeptSelect, vSpvSelect, vHyphen;		
			
	private JTextField xEmpName, xEmpPhone, xEmpRegist, xEmpHireDate, xDeptName, xEmpSal, xSpvName, xEmpAnin, xEmpComm, xSecondNumber, xThirdNumber;			
			
	private JButton checkId, searchAddress, regist, cancel;		
	
	private String[] startNumber = {"02", "051", "010"};
	
	private JComboBox<String> cbEmpClass;		
			
	GridBagLayout gridbaglayout;		
	GridBagConstraints gridbagconstraints;		// gridbag���̾ƿ��� ������Ʈ�� ��ġ�� ����ִ� ����
			
	public Register() {		
			
			
			gridbaglayout = new GridBagLayout();
			gridbagconstraints = new GridBagConstraints();
			
			vTitle = new JLabel("ȸ������");
			
			vEmpPhone = new JLabel("�̸�");
			xEmpPhone = new JTextField(20);
			
			vEmpRegist = new JLabel("���̵�");
			xEmpRegist = new JTextField(20);
			checkId = new JButton("�ߺ�Ȯ��");
			
			vEmpHireDate = new JLabel("��й�ȣ");
			xEmpHireDate = new JTextField(20);
			
			
			vDeptName = new JLabel("��й�ȣȮ��");
			xDeptName= new JTextField(20);
//			xDeptName.setEditable(false);
			
			vEmpSal = new JLabel("�������");
			xEmpSal= new JTextField(20);
			
			
			vSpvName= new JLabel("�ּ�");
			xSpvName= new JTextField(20);
			searchAddress = new JButton("�˻�");
			
			vEmpAnin = new JLabel("��ȭ��ȣ");
			xEmpAnin= new JTextField(4);
			vHyphen = new JLabel("-");
			xSecondNumber = new JTextField(4);
			xThirdNumber = new JTextField(4);
			
			
			vEmpComm= new JLabel("�ð������");
			xEmpComm= new JTextField(20);
			
			
			vDeptSelect = new JLabel("�ҼӺμ� ����");
			vSpvSelect= new JLabel("���ӻ�� ����");
			
			

			
			
			regist = new JButton("���");
//			regist.addActionListener(this);
			cancel = new JButton("���");
//			cancel.addActionListener(this);
			
//			getDeptData(EmpData.selectDept());
//			getSvpData(EmpData.selectSpv());
			EmpRegisterView();
		}	
			
	private void EmpRegisterView() {		
			
			setTitle("ȸ������");
			
			gridbagconstraints.anchor = GridBagConstraints.WEST;
			gridbagconstraints.ipadx = 7;
			
			gridbagconstraints.weightx=1.0;
			gridbagconstraints.weighty=1.0;
			
			setLayout(gridbaglayout);
			
			gridbagAdd(vTitle, 0, 0, 1, 1);
			gridbagAdd(vEmpPhone, 0, 1, 1, 1);
			gridbagAdd(xEmpPhone, 1, 1, 3, 1);
			gridbagAdd(vEmpRegist, 0, 2, 1, 1);
			gridbagAdd(xEmpRegist, 1, 2, 3, 1);
			gridbagAdd(checkId, 4, 2, 1, 1);
			gridbagAdd(vEmpHireDate, 0, 3, 1, 1);
			gridbagAdd(xEmpHireDate, 1, 3, 3, 1);
//			gridbagAdd(vEmpClass, 0, 4, 1, 1);
//			gridbagAdd(cbEmpClass, 2, 4, 1, 1);
			gridbagAdd(vDeptName, 0, 5, 1, 1);
			gridbagAdd(xDeptName, 1, 5, 3, 1);
			gridbagAdd(vEmpSal, 0, 6, 1, 1);
			gridbagAdd(xEmpSal, 1, 6, 3, 1);
			gridbagAdd(vSpvName, 0, 7, 1, 1);
			gridbagAdd(xSpvName, 1, 7, 3, 1);
			gridbagAdd(searchAddress, 4, 7, 1, 1);
			gridbagAdd(vEmpAnin, 0, 8, 1, 1);
//			gridbagAdd(vEmpComm, 0, 9, 1, 1);
//			gridbagAdd(xEmpComm, 2, 9, 1, 1);
//			gridbagAdd(vDeptSelect, 0, 10, 1, 1);
//			gridbagAdd(vSpvSelect, 2, 10, 1, 1);
			
			gridbagconstraints.anchor = GridBagConstraints.CENTER;

			
//			gridbagAdd(regist, 0, 12, 1, 1);
//			gridbagAdd(cancel, 2, 12, 1, 1);
			
			pack();
			setResizable(false);
			setVisible(true);
		}	
			
		private void gridbagAdd(Component c, int x, int y, int w, int h) {	
			
			gridbagconstraints.gridx = x;
			gridbagconstraints.gridy = y; 
		      //���� ���� �� gridx, gridy���� 0 	
			
			gridbagconstraints.gridwidth  = w;
			gridbagconstraints.gridheight = h;
		     	
		      	
		    gridbaglayout.setConstraints(c, gridbagconstraints); //������Ʈ�� ������Ʈ ��ġ+ũ�� ������ ���� GridBagLayout�� ��ġ	
			
		   add(c);
		   
		   }	
			
		public static void main(String[] args) {	
			new Register();
		}	
			
	}		
