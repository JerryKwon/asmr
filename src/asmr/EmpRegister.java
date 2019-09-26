package asmr;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EmpRegister extends JPanel implements ActionListener{
	private JLabel vEmpRegist,vEmpName,vBelongCenter,vEmpType,vWorkType,vBirthDate,vGender,vAddress,vPhoneNum;
	private JTextField xEmpName,xBelongCenter,xBirthDate,xAddress,xPhoneNum;
	private JButton centerSearch,addressSearch, imageButton, register, cancel;
	private JComboBox<String> cbEmpType,cbWorkType,cbGender;
	private BufferedImage buttonIcon;
	
	private final String[] empTypeDiv = {"정규직","계약직"};
	private final String[] workTypeDiv = {"센터장","관리직원","수의사","보호관리직원","사무직종사자","유기동물구조원"};
	private final String[] genterDiv = {"남","여"};
	
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	
	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;
	
	public EmpRegister() throws IOException {
	
		gridBagLayout = new GridBagLayout();
		gridBagConstraints = new GridBagConstraints();
		
		vEmpRegist = new JLabel("직원등록");
		
		vEmpName = new JLabel("직원명");
		xEmpName = new JTextField(10);
		
		//센터검색팝업(CenterSearch)와 Listener로 화면연결 하셔야합니다.
		vBelongCenter = new JLabel("소속센터");
		xBelongCenter = new JTextField(10);
		xBelongCenter.setEnabled(false);
		centerSearch = new JButton("검색");
		centerSearch.setBackground(blue);
		centerSearch.setForeground(white);
		centerSearch.addActionListener(this);
		
		vEmpType = new JLabel("직원구분");
		cbEmpType = new JComboBox<String>(empTypeDiv);
		
		vWorkType = new JLabel("업무분야");
		cbWorkType = new JComboBox<String>(workTypeDiv);
		
		vBirthDate = new JLabel("생년월일");
		xBirthDate = new JTextField(10);
		xBirthDate.setEnabled(false);
		buttonIcon = ImageIO.read(new File("images/cal1.png"));
		imageButton = new JButton(new ImageIcon(buttonIcon));
		imageButton.setBorderPainted(false);
		imageButton.setContentAreaFilled(false);
		imageButton.setFocusPainted(false);
		
		vGender = new JLabel("성별");
		cbGender = new JComboBox<String>(genterDiv);
		
		vAddress = new JLabel("주소");
		xAddress = new JTextField(15);
		xAddress.setEnabled(false);
		addressSearch = new JButton("검색");
		addressSearch.setBackground(blue);
		addressSearch.setForeground(white);
		
		vPhoneNum = new JLabel("전화번호");
		xPhoneNum = new JTextField(10);
		
		register = new JButton("등록");
		register.setBackground(blue);
		register.setForeground(white);
		
		cancel = new JButton("취소");
		
		EmpRegisterView();
		
	}
	
	private void EmpRegisterView() {
		
		setLayout(gridBagLayout);
		
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.ipadx = 7;
		
		gridBagConstraints.weightx=1.0;
		gridBagConstraints.weighty=1.0;
		
		gridbagAdd(vEmpRegist, 0, 0, 1, 1);
		
		gridbagAdd(vEmpName, 0, 1, 1, 1);
		gridbagAdd(xEmpName, 1, 1, 1, 1);
		
		gridbagAdd(vBelongCenter, 2, 1, 1, 1);
		gridbagAdd(xBelongCenter, 3, 1, 1, 1);
		gridbagAdd(centerSearch, 4, 1, 1, 1);
		
		gridbagAdd(vEmpType, 0, 2, 1, 1);
		gridbagAdd(cbEmpType, 1, 2, 1, 1);
		
		gridbagAdd(vWorkType, 2, 2, 1, 1);
		gridbagAdd(cbWorkType, 3, 2, 1, 1);
		
		gridbagAdd(vBirthDate, 0, 3, 1, 1);
		CombinePanel birthDatePanel = new CombinePanel(xBirthDate,imageButton,false);
		gridbagAdd(birthDatePanel, 1, 3, 1, 1);
		
		gridbagAdd(vGender, 2, 3, 1, 1);
		gridbagAdd(cbGender, 3, 3, 1, 1);
		
		gridbagAdd(vAddress, 0, 4, 1, 1);
		CombinePanel addressSearchPanel = new CombinePanel(xAddress, addressSearch,false);
		gridbagAdd(addressSearchPanel, 1, 4, 1, 1);

		gridbagAdd(vPhoneNum, 0, 5, 1, 1);
		gridbagAdd(xPhoneNum, 1, 5, 1, 1);

		CombinePanel registerAndCancel = new CombinePanel(register, cancel,true);
		registerAndCancel.setBorder(BorderFactory.createEmptyBorder(0, 220, 0, 0));
		gridbagAdd(registerAndCancel, 0, 6, 5, 1);

	}
	
	private void gridbagAdd(Component c, int x, int y, int w , int h) {
		
		gridBagConstraints.gridx = x;
		gridBagConstraints.gridy = y;
		gridBagConstraints.gridwidth = w;
		gridBagConstraints.gridheight = h;
		
		gridBagLayout.setConstraints(c, gridBagConstraints);
		
		add(c);
	}
	
	//두개의 컴포넌트를 하나의 패널로 묶는 JPanel
	class CombinePanel extends JPanel {
		//컴포넌트 1, 컴포넌트 2, 패널 구성시 좌,우 margin 공간을 없애기 위한 Flag
		public CombinePanel(Component c1, Component c2, boolean isBorder) {
			//Margin이 필요하지 않을 때
			if(!isBorder) {
				setLayout(new FlowLayout(FlowLayout.LEFT,0,0));	
			}
			
			add(c1);
			add(c2);
		}
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(centerSearch)) {
			new CenterSearch();
		}
	}
	
	public static void main(String[] args) throws IOException {
		new EmpRegister();
	}
}
