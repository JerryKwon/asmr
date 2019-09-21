package asmr;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class EmpList extends JFrame {
	private JLabel vEmpList,vEmpNameSearch,vEmpInfo,vEmpNo,vBelongCenter,vEmpType,vWorkType,vEmpName,vBirthDate,vPhoneNum;
	private JTextField xEmpNameSearch,xEmpNo,xBelongCenter,xEmpName,xBirthDate,xPhoneNum;
	private JButton empSearch,centerSearch, modify, cancel, resign;
	private JComboBox<String> cbSearchType,cbEmptype,cbWorkType;
	
	private JTable eEmpList;
	private JScrollPane scrollpane;
	
	private final String[] searchTypeDiv = {"�̸�","�Ҽ�"};
	private final String[] empTypeDiv = {"������","�����"};
	private final String[] workTypeDiv = {"������","��������","���ǻ�","��ȣ��������","�繫��������","���⵿��������"};
	
	private final String[] col1 = {"������ȣ","�̸�","�Ҽ�"};
	
	private DefaultTableModel model1 = new DefaultTableModel(col1,0);
	
	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;
	
	
	public EmpList() {
		
		gridBagLayout = new GridBagLayout();		
		gridBagConstraints = new GridBagConstraints();

		vEmpList = new JLabel("�������");
		
		cbSearchType = new JComboBox<String>(searchTypeDiv);
		xEmpNameSearch = new JTextField(10);
		empSearch = new JButton("�˻�");
		
		eEmpList = new JTable(model1);
		scrollpane = new JScrollPane(eEmpList);
		scrollpane.setPreferredSize(new Dimension(300,100));
		
		vEmpInfo = new JLabel("��������");
		
		vEmpNo = new JLabel("������ȣ");
		xEmpNo = new JTextField(10);
		xEmpNo.setEnabled(false);
		
		vBelongCenter = new JLabel("�Ҽ�");
		xBelongCenter = new JTextField(10);
		xBelongCenter.setEnabled(false);
		centerSearch = new JButton("�˻�");
		
		vEmpType = new JLabel("��������");
		cbEmptype = new JComboBox<String>(empTypeDiv);
		
		vWorkType = new JLabel("�����о�");
		cbWorkType = new JComboBox<String>(workTypeDiv);
		
		vEmpName = new JLabel("�̸�");
		xEmpName = new JTextField(10);
		xEmpName.setEnabled(false);
		
		vBirthDate = new JLabel("�������");
		xBirthDate = new JTextField(10);
		xBirthDate.setEnabled(false);
		
		vPhoneNum = new JLabel("��ȭ��ȣ");
		xPhoneNum = new JTextField(10);
		xPhoneNum.setEnabled(false);
		
		modify = new JButton("����");
		cancel = new JButton("���");
		resign = new JButton("���");
		 
		EmpListView();
		
	}
	
	private void EmpListView() {

		setLayout(gridBagLayout);
		
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.ipadx = 7;
		
		gridBagConstraints.weightx=1.0;
		gridBagConstraints.weighty=1.0;

		gridbagAdd(vEmpList, 0, 0, 1, 1);
		
		cbSearchType.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		gridbagAdd(cbSearchType, 0, 1, 1, 1);
		
		Component[] cpts = {xEmpNameSearch,empSearch};
		CombinePanel cp1 = new CombinePanel(cpts,true);
		gridbagAdd(cp1, 1, 1, 1, 1);
//		gridbagAdd(xEmpNameSearch, 1, 1, 1, 1);
//		gridbagAdd(empSearch, 2, 1, 1, 1);
		
		gridbagAdd(scrollpane, 0, 2, 2, 1);
		
		gridbagAdd(vEmpInfo, 0, 3, 1, 1);
		
		gridbagAdd(vEmpNo, 0, 4, 1, 1);
		gridbagAdd(xEmpNo, 1, 4, 1, 1);
		
		gridbagAdd(vBelongCenter, 2, 4, 1, 1);
		
//		Component[] cops1 = {xBelongCenter,centerSearch};
//		
//		CombinePanel combinePanel1 = new CombinePanel(cops1, false) ;
//		gridbagAdd(combinePanel1, 3, 4, 1, 1);

		gridbagAdd(xBelongCenter, 3, 4, 1, 1);
		gridbagAdd(centerSearch, 4, 4, 1, 1);
		
		gridbagAdd(vEmpType, 0, 5, 1, 1);
		gridbagAdd(cbEmptype, 1, 5, 1, 1);

		gridbagAdd(vWorkType, 2, 5, 1, 1);
		gridbagAdd(cbWorkType, 3, 5, 1, 1);
		
//		CombinePanel combinePanel2 =new CombinePanel(cops2, true);
//		gridbagAdd(combinePanel2, 2, 5, 3, 1);
//		
		gridbagAdd(vEmpName, 0, 6, 1, 1);
		gridbagAdd(xEmpName, 1, 6, 1, 1);
		
		gridbagAdd(vBirthDate, 0, 7, 1, 1);
		gridbagAdd(xBirthDate, 1, 7, 1, 1);
		
		gridbagAdd(vPhoneNum, 2, 7, 1, 1);
		gridbagAdd(xPhoneNum, 3, 7, 1, 1);
		
		Component[] buttons = {modify,cancel,resign};
		CombinePanel combineButtonPanel = new CombinePanel(buttons, true);
		combineButtonPanel.setBorder(BorderFactory.createEmptyBorder(10,150,0,0));
		gridbagAdd(combineButtonPanel, 0, 8, 5, 1);
		
		pack();
		setResizable(false);
		setVisible(true);
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
	public static void main(String[] args) {
		new EmpList();
	}
	
}
