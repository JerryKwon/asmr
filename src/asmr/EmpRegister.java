package asmr;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

public class EmpRegister extends JFrame {					
	private JLabel vEmpName, vEmpPhone, vEmpRegist, vEmpHireDate, vEmpClass, vDeptName, vEmpSal, vSpvName, vEmpAnin, vEmpComm,				
	vDeptSelect, vSpvSelect;				
					
	private JTextField xEmpName, xEmpPhone, xEmpRegist, xEmpHireDate, xDeptName, xEmpSal, xSpvName, xEmpAnin, xEmpComm;				
					
	private String[] col1 = {"부서명", "부서위치"};				// 소속부서 선택 테이블의 컬럼명
	private String[] col2 = {"부서명", "성명"};				// 직속상사 선택 테이블의 컬럼명
	private String[] div = {"정규직", "임시직", "계약직"};		// 사원구분 콤보박스의 목록		
					
	private DefaultTableModel model1 = new DefaultTableModel(col1, 0);				
	private DefaultTableModel model2 = new DefaultTableModel(col2, 0);				
					
	private JTable eDept, eSpv;				
	private JScrollPane scrollpane1, scrollpane2;				
					
	private JButton regist, cancel;				
	private JComboBox<String> cbEmpClass;				
					
	GridBagLayout gridbaglayout;				// 화면을 구성하는 레이아웃
	GridBagConstraints gridbagconstraints;		// gridbag레이아웃에 컴포넌트의 위치를 잡아주는 역할		
					
	public EmpRegister() {				
					
					
			gridbaglayout = new GridBagLayout();		
			gridbagconstraints = new GridBagConstraints();		
					
					
			vEmpName = new JLabel("사원명");		
			xEmpName = new JTextField(20);		
					
			vEmpPhone = new JLabel("전화번호");		
			xEmpPhone = new JTextField(20);		
					
			vEmpRegist = new JLabel("주민등록번호");		
			xEmpRegist = new JTextField(20);		
					
			vEmpHireDate = new JLabel("입사일자");		
			xEmpHireDate = new JTextField(20);		
					
			vEmpClass = new JLabel("사원구분");		
			cbEmpClass = new JComboBox<String>(div);		
					
					
			vDeptName = new JLabel("소속부서명");		
			xDeptName= new JTextField(20);		
			xDeptName.setEditable(false);		
					
			vEmpSal = new JLabel("급여");		
			xEmpSal= new JTextField(20);		
					
					
			vSpvName= new JLabel("직속상사명");		
			xSpvName= new JTextField(20);		
			xSpvName.setEditable(false);		
					
			vEmpAnin = new JLabel("연봉");		
			xEmpAnin= new JTextField(20);		
					
					
			vEmpComm= new JLabel("시간당수당");		
			xEmpComm= new JTextField(20);		
					
					
			vDeptSelect = new JLabel("소속부서 선택");		
			vSpvSelect= new JLabel("직속상사 선택");		
					
					
			eDept = new JTable(model1);		
//			eDept.addMouseListener(new JTableMouseListener());		
			scrollpane1 = new JScrollPane(eDept);		
					
			eSpv = new JTable(model2);		
//			eSpv.addMouseListener(new JTableMouseListener());		
			scrollpane2 = new JScrollPane(eSpv);		
					
			scrollpane1.setPreferredSize(new Dimension(150, 100));		
			scrollpane2.setPreferredSize(new Dimension(150, 100));		
					
					
			regist = new JButton("등록");		
//			regist.addActionListener(this);		
			cancel = new JButton("취소");		
//			cancel.addActionListener(this);		
					
//			getDeptData(EmpData.selectDept());		
//			getSvpData(EmpData.selectSpv());		
			EmpRegisterView();		
		}			
					
	private void EmpRegisterView() {				
					
			setTitle("사원 등록");		
					
			gridbagconstraints.anchor = GridBagConstraints.WEST;		
			gridbagconstraints.ipadx = 7;		
					
			gridbagconstraints.weightx=1.0;		
			gridbagconstraints.weighty=1.0;		
					
			setLayout(gridbaglayout);		
					
			gridbagAdd(vEmpName, 0, 0, 1, 1);		
			gridbagAdd(xEmpName, 2, 0, 1, 1);		
			gridbagAdd(vEmpPhone, 0, 1, 1, 1);		
			gridbagAdd(xEmpPhone, 2, 1, 1, 1);		
			gridbagAdd(vEmpRegist, 0, 2, 1, 1);		
			gridbagAdd(xEmpRegist, 2, 2, 1, 1);		
			gridbagAdd(vEmpHireDate, 0, 3, 1, 1);		
			gridbagAdd(xEmpHireDate, 2, 3, 1, 1);		
			gridbagAdd(vEmpClass, 0, 4, 1, 1);		
			gridbagAdd(cbEmpClass, 2, 4, 1, 1);		
			gridbagAdd(vDeptName, 0, 5, 1, 1);		
			gridbagAdd(xDeptName, 2, 5, 1, 1);		
			gridbagAdd(vEmpSal, 0, 6, 1, 1);		
			gridbagAdd(xEmpSal, 2, 6, 1, 1);		
			gridbagAdd(vSpvName, 0, 7, 1, 1);		
			gridbagAdd(xSpvName, 2, 7, 1, 1);		
			gridbagAdd(vEmpAnin, 0, 8, 1, 1);		
			gridbagAdd(xEmpAnin, 2, 8, 1, 1);		
			gridbagAdd(vEmpComm, 0, 9, 1, 1);		
			gridbagAdd(xEmpComm, 2, 9, 1, 1);		
			gridbagAdd(vDeptSelect, 0, 10, 1, 1);		
			gridbagAdd(vSpvSelect, 2, 10, 1, 1);		
					
			gridbagconstraints.anchor = GridBagConstraints.CENTER;		
					
			gridbagAdd(scrollpane1, 0, 11, 2, 1);		
			gridbagAdd(scrollpane2, 2, 11, 2, 1);		
			gridbagAdd(regist, 0, 12, 1, 1);		
			gridbagAdd(cancel, 2, 12, 1, 1);		
					
			pack();		
			setResizable(false);		
			setVisible(true);		
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
			new EmpRegister();		
		}			
					
	}				
