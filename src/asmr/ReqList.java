package asmr;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

public class ReqList extends JPanel {
	
	ReqListButtonListener reqListButtonListener;
	
	private JLabel vReqList, vAdopEva, vAnmlInfo,
	vAbanName, vAnmlKinds, vKind, vSex, vAge,
	vReqPrsnInfo, vReqPrsnName, vTelNo,
	vAddr, vEvaRes, vRes, vReas, vAdopDate, vWarn;
	
	private JTextField xAbanName, xAnmlKinds,
	xKind, xSex, xAge, xReqPrsnName, xTelNo,
	xAddr, xReas, xAdopDate;
	
	private String[] resDiv = {"����", "�ź�"};
	
	private JComboBox<String> cbRes;
	
	private JButton regis, cancel;
	
	private JTable eReqList;
	
	private JScrollPane scrollPane1;
	
	private JDateChooser chooser;
	
	GridBagLayout gridbagLayout;
	GridBagConstraints gridbagConstraints;
	
	private final String[] col1 = {"��û�Ͻ�","���⵿����","��������","ǰ��","��û�θ�","��ȭ��ȣ", "�ּ�"};
	private DefaultTableModel model1 = new DefaultTableModel(col1,0);

	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	private Color black = new Color(0,0,0);
	
	public ReqList() throws IOException {
		
		reqListButtonListener = new ReqListButtonListener();
		
		gridbagLayout = new GridBagLayout();
		gridbagConstraints = new GridBagConstraints();
		
		vReqList = new JLabel("��û���");
		vReqList.setFont(new Font("�������", Font.BOLD, 24));
		
		eReqList = new JTable(model1){
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
		scrollPane1 = new JScrollPane(eReqList);
		scrollPane1.setPreferredSize(new Dimension(1000,100));
		
		vAdopEva = new JLabel("�Ծ�ɻ�");
		vAdopEva.setFont(new Font("�������", Font.BOLD, 24));
		
		vAnmlInfo = new JLabel("��������");
		vAnmlInfo.setFont(new Font("�������", Font.BOLD, 20));
		
		vAbanName = new JLabel("���⵿����");
		xAbanName = new JTextField(20);
		xAbanName.setEnabled(false);
		
		vAnmlKinds = new JLabel("��������");
		xAnmlKinds = new JTextField(20);
		xAnmlKinds.setEnabled(false);
		
		vKind = new JLabel("ǰ��");
		xKind = new JTextField(20);
		xKind.setEnabled(false);
		
		vSex = new JLabel("����");
		xSex = new JTextField(20);
		xSex.setEnabled(false);
		
		vAge = new JLabel("����");
		xAge = new JTextField(20);
		xAge.setEnabled(false);
		
		vReqPrsnInfo = new JLabel("��û������");
		vReqPrsnInfo.setFont(new Font("�������", Font.BOLD, 20));
		
		vWarn = new JLabel("��û�� ����(����ó, �ּ�)�� �´��� �ٽ� �� �� Ȯ�����ּ���.");
		vWarn.setFont(new Font("�������", Font.BOLD, 16));
		
		
		vReqPrsnName = new JLabel("��û�ڸ�");
		xReqPrsnName = new JTextField(20);
		xReqPrsnName.setEnabled(false);
		
		vTelNo = new JLabel("��ȭ��ȣ");
		xTelNo = new JTextField(20);
		xTelNo.setEnabled(false);
		
		vAddr = new JLabel("�ּ�");
		xAddr = new JTextField(20);
		xAddr.setEnabled(false);
		
		vEvaRes = new JLabel("�ɻ���");
		vEvaRes.setFont(new Font("�������", Font.BOLD, 20));
		vEvaRes.setEnabled(false);
		
		vRes = new JLabel("���");
		cbRes = new JComboBox<String>(resDiv);
		cbRes.setEnabled(false);
		
		vReas = new JLabel("����");
		xReas = new JTextField(20);
		xReas.setEnabled(false);
		
		vAdopDate = new JLabel("�Ծ�����");
		xAdopDate = new JTextField(20);
		xAdopDate.setEnabled(false);
		
		LocalDate now = LocalDate.now();
		Date date = Date.valueOf(now);
		chooser = new JDateChooser(date,"yyyy-MM-dd");
		
		regis = new JButton("���");
		regis.setBackground(blue);
		regis.setForeground(white);
		regis.addActionListener(reqListButtonListener);
		regis.setFont(new Font("�������", Font.BOLD, 16));
		
		cancel = new JButton("���");
		cancel.setBackground(white);
		cancel.setForeground(black);
		cancel.addActionListener(reqListButtonListener);
		cancel.setFont(new Font("�������", Font.BOLD, 16));
		
		JComponent[] vComps = {vAbanName, vAnmlKinds, vKind, vSex, vAge, vReqPrsnName, vTelNo, vAddr,
				vRes, vReas, vAdopDate};
		ChangeFont(vComps, new Font("�������", Font.PLAIN, 16));

		ReqListView();
	
	}
	
	private void ReqListView() {
		//setTitle("��û���");
		setBackground(MainFrame.bgc);
		
		
		gridbagConstraints.insets = new Insets(5,5,5,5);
		
		gridbagConstraints.anchor = GridBagConstraints.WEST;
		gridbagConstraints.ipadx = 7;

		gridbagConstraints.weightx=1.0;
		gridbagConstraints.weighty=1.0;

		setLayout(gridbagLayout);
		
		gridbagAdd(vReqList, 0, 0, 1, 1);
		
		gridbagAdd(scrollPane1, 0, 1, 10, 1);
		
		gridbagAdd(vAdopEva, 0, 2, 1, 1);

		gridbagAdd(vAnmlInfo, 0, 3, 1, 1);
		
		gridbagAdd(vAbanName, 0, 4, 1, 1);
		gridbagAdd(xAbanName, 2, 4, 1, 1);
		
		gridbagAdd(vAnmlKinds, 0, 5, 1, 1);
		gridbagAdd(xAnmlKinds, 2, 5, 1, 1);
		gridbagAdd(vKind, 4, 5, 1, 1);
		gridbagAdd(xKind, 6, 5, 1, 1);
		
		gridbagAdd(vSex, 0, 6, 1, 1);
		gridbagAdd(xSex, 2, 6, 1, 1);
		gridbagAdd(vAge, 4, 6, 1, 1);
		gridbagAdd(xAge, 6, 6, 1, 1);
		
		gridbagAdd(vReqPrsnInfo, 0, 7, 1, 1);
		gridbagAdd(vWarn, 2, 7, 1, 1);

		gridbagAdd(vReqPrsnName, 0, 8, 1, 1);
		gridbagAdd(xReqPrsnName, 2, 8, 1, 1);
		
		gridbagAdd(vTelNo, 0, 9, 1, 1);
		gridbagAdd(xTelNo, 2, 9, 1, 1);
		
		gridbagAdd(vEvaRes, 0, 10, 1, 1);
		
		gridbagAdd(vRes, 0, 11, 1, 1);
		gridbagAdd(cbRes, 2, 11, 1, 1);
		
		gridbagAdd(vReas, 0, 12, 1, 1);
		gridbagAdd(xReas, 2, 12, 1, 1);
		
		gridbagAdd(vAdopDate, 0, 13, 1, 1);
		gridbagAdd(chooser, 2, 13, 1, 1);
		
		JPanel plainPanel = new JPanel(new FlowLayout(FlowLayout.LEADING,20,0));
		plainPanel.add(regis);
		plainPanel.add(cancel);
		plainPanel.setBackground(MainFrame.bgc);
		plainPanel.setBorder(BorderFactory.createEmptyBorder(0, 425, 0, 0));
		
		gridbagAdd(plainPanel, 0, 14, 7, 0);
		
//		gridbagAdd(regis, 3, 14, 1, 1);
//		gridbagAdd(cancel, 4, 14, 1, 1);
		
		//pack();
		//setResizable(false);
		//setVisible(true);
			
	}
	
	private void gridbagAdd(Component c, int x, int y, int w, int h) {			
		
		gridbagConstraints.gridx = x;		
		gridbagConstraints.gridy = y; 		
	      //���� ���� �� gridx, gridy���� 0 			
				
		gridbagConstraints.gridwidth  = w;	//����	
		gridbagConstraints.gridheight = h;	//����	
	     			
	      			
	    gridbagLayout.setConstraints(c, gridbagConstraints); //������Ʈ�� ������Ʈ ��ġ+ũ�� ������ ���� GridBagLayout�� ��ġ			
				
	   add(c);			
				
	   }	
	
    private void ChangeFont(JComponent[] comps, Font font) {
    	for(JComponent comp: comps) {
    		comp.setFont(font);
    	}
    }
    
    class ReqListButtonListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource().equals(regis)) {	
				
			}
			else if(e.getSource().equals(cancel)) {
				
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//new ReqList();
	}

}
