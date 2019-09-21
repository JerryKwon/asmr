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
	GridBagConstraints gridbagconstraints;		// gridbag레이아웃에 컴포넌트의 위치를 잡아주는 역할
			
	public Register() {		
			
			
			gridbaglayout = new GridBagLayout();
			gridbagconstraints = new GridBagConstraints();
			
			vTitle = new JLabel("회원가입");
			
			vEmpPhone = new JLabel("이름");
			xEmpPhone = new JTextField(20);
			
			vEmpRegist = new JLabel("아이디");
			xEmpRegist = new JTextField(20);
			checkId = new JButton("중복확인");
			
			vEmpHireDate = new JLabel("비밀번호");
			xEmpHireDate = new JTextField(20);
			
			
			vDeptName = new JLabel("비밀번호확인");
			xDeptName= new JTextField(20);
//			xDeptName.setEditable(false);
			
			vEmpSal = new JLabel("생년월일");
			xEmpSal= new JTextField(20);
			
			
			vSpvName= new JLabel("주소");
			xSpvName= new JTextField(20);
			searchAddress = new JButton("검색");
			
			vEmpAnin = new JLabel("전화번호");
			xEmpAnin= new JTextField(4);
			vHyphen = new JLabel("-");
			xSecondNumber = new JTextField(4);
			xThirdNumber = new JTextField(4);
			
			
			vEmpComm= new JLabel("시간당수당");
			xEmpComm= new JTextField(20);
			
			
			vDeptSelect = new JLabel("소속부서 선택");
			vSpvSelect= new JLabel("직속상사 선택");
			
			

			
			
			regist = new JButton("등록");
//			regist.addActionListener(this);
			cancel = new JButton("취소");
//			cancel.addActionListener(this);
			
//			getDeptData(EmpData.selectDept());
//			getSvpData(EmpData.selectSpv());
			EmpRegisterView();
		}	
			
	private void EmpRegisterView() {		
			
			setTitle("회원가입");
			
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
		      //가장 왼쪽 위 gridx, gridy값은 0 	
			
			gridbagconstraints.gridwidth  = w;
			gridbagconstraints.gridheight = h;
		     	
		      	
		    gridbaglayout.setConstraints(c, gridbagconstraints); //컴포넌트를 컴포넌트 위치+크기 정보에 따라 GridBagLayout에 배치	
			
		   add(c);
		   
		   }	
			
		public static void main(String[] args) {	
			new Register();
		}	
			
	}		
