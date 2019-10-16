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
		
		vTitle = new JLabel("마이페이지");
		vEmpName = new JLabel("직원명");
		xEmpName = new JTextField(20);
		xEmpName.setEditable(false);
		
		vEmpType = new JLabel("직원구분");
		xEmpType = new JTextField(20);
		xEmpType.setEditable(false);
		
		vPassword = new JLabel("비밀번호");
		xPassword = new JPasswordField(20);
		
		vPassConfirm = new JLabel("비밀번호확인");
		xPassConfirm = new JPasswordField(20);
		
		vAddress = new JLabel("주소");
		xAddress = new JTextField(30);
		xAddress.setEditable(false);
		
		vPhone = new JLabel("전화번호");
		xPhone = new JTextField(20);
		
		vCenterName = new JLabel("소속센터");
		xCenterName = new JTextField(20);
		xCenterName.setEditable(false);
		
		vBizField = new JLabel("업무분야");
		xBizField = new JTextField(20);
		xBizField.setEditable(false);
		
		//성별 ?
		vSex = new JLabel("성별");
		xSex = new JTextField(20);
		xSex.setEditable(false);
		
		vBirthDay = new JLabel("생년월일");
		xBirthDay = new JTextField(20);
		xBirthDay.setEditable(false);
		
		bSearch = new JButton("검색");
		bAdjust = new JButton("수정");
		bCancel = new JButton("취소");
		
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
	      //가장 왼쪽 위 gridx, gridy값은 0 			
				
		gridbagconstraints.gridwidth  = w;	//넓이	
		gridbagconstraints.gridheight = h;	//높이	
	     			
	      			
	    gridbaglayout.setConstraints(c, gridbagconstraints); //컴포넌트를 컴포넌트 위치+크기 정보에 따라 GridBagLayout에 배치			
				
	   add(c);			
				
	   }

	public static void main(String[] args) {

	}

}
