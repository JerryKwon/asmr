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
	
	private final String[] empTypeDiv = {"������","�����"};
	private final String[] workTypeDiv = {"������","��������","���ǻ�","��ȣ��������","�繫��������","���⵿��������"};
	private final String[] genterDiv = {"��","��"};
	
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	
	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;
	
	public EmpRegister() throws IOException {
	
		gridBagLayout = new GridBagLayout();
		gridBagConstraints = new GridBagConstraints();
		
		vEmpRegist = new JLabel("�������");
		
		vEmpName = new JLabel("������");
		xEmpName = new JTextField(10);
		
		//���Ͱ˻��˾�(CenterSearch)�� Listener�� ȭ�鿬�� �ϼž��մϴ�.
		vBelongCenter = new JLabel("�ҼӼ���");
		xBelongCenter = new JTextField(10);
		xBelongCenter.setEnabled(false);
		centerSearch = new JButton("�˻�");
		centerSearch.setBackground(blue);
		centerSearch.setForeground(white);
		centerSearch.addActionListener(this);
		
		vEmpType = new JLabel("��������");
		cbEmpType = new JComboBox<String>(empTypeDiv);
		
		vWorkType = new JLabel("�����о�");
		cbWorkType = new JComboBox<String>(workTypeDiv);
		
		vBirthDate = new JLabel("�������");
		xBirthDate = new JTextField(10);
		xBirthDate.setEnabled(false);
		buttonIcon = ImageIO.read(new File("images/cal1.png"));
		imageButton = new JButton(new ImageIcon(buttonIcon));
		imageButton.setBorderPainted(false);
		imageButton.setContentAreaFilled(false);
		imageButton.setFocusPainted(false);
		
		vGender = new JLabel("����");
		cbGender = new JComboBox<String>(genterDiv);
		
		vAddress = new JLabel("�ּ�");
		xAddress = new JTextField(15);
		xAddress.setEnabled(false);
		addressSearch = new JButton("�˻�");
		addressSearch.setBackground(blue);
		addressSearch.setForeground(white);
		
		vPhoneNum = new JLabel("��ȭ��ȣ");
		xPhoneNum = new JTextField(10);
		
		register = new JButton("���");
		register.setBackground(blue);
		register.setForeground(white);
		
		cancel = new JButton("���");
		
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
	
	//�ΰ��� ������Ʈ�� �ϳ��� �гη� ���� JPanel
	class CombinePanel extends JPanel {
		//������Ʈ 1, ������Ʈ 2, �г� ������ ��,�� margin ������ ���ֱ� ���� Flag
		public CombinePanel(Component c1, Component c2, boolean isBorder) {
			//Margin�� �ʿ����� ���� ��
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
