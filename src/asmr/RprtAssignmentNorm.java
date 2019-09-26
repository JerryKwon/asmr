package asmr;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class RprtAssignmentNorm extends JPanel {
	
	private JLabel vRprtNo, vRprtDttm, vRprtName, vTelNo, vRprtTp, vWrtPrsnTp, vAnmlKinds,
	vAnmlSize, vExpln, vDscvDttm, vDscvLoc, vApprovalWaitList, vApprovalCompleteList, vRprtInfo;
	
	private JTextField xRprtNo, xRprtDttm, xRprtName, xTelNo, xExpln, xDscvDttm, xDscvLoc;
	
	private JComboBox<String> cbRprtTp, cbWrtPrsnTp, cbAnmlKinds, cbAnmlSize;
	
	private JTable eApprovalWaitList, eApprovalCompleteList;
	
	private JScrollPane scrollpane1, scrollpane2;
	
	private String[] rprtDiv = {"�߰�", "�ΰ�"};
	private String[] wrtPrsnDiv = {"����","�����"};
	private String[] anmlDiv = {"��", "�����"};
	private String[] anmlSizeDiv = {"��", "��", "��"};
	
	private BufferedImage buttonIcon;
	
	private JButton Imagebutton, regis;
	
	GridBagLayout gridbaglayout;
	GridBagConstraints gridbagconstraints;
	
	ApprovalWaitListMouseListener approvalWaitListMouseListener;
	
	private final String[] col1 = {"�Ű��Ͻ�","��������","����ũ��","����","�������"};
	private final String[] col2 = {"�Ű��Ͻ�","��������","����ũ��","����"};
	
	private DefaultTableModel model1 = new DefaultTableModel(col1,0);
	private DefaultTableModel model2 = new DefaultTableModel(col2,0);
	
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	
	RprtRegisterButtonListener rprtRegisterButtonListener;
	
	public RprtAssignmentNorm() throws IOException {
		gridbaglayout = new GridBagLayout();
		gridbagconstraints = new GridBagConstraints();
		
		rprtRegisterButtonListener = new RprtRegisterButtonListener();
		
		approvalWaitListMouseListener = new ApprovalWaitListMouseListener();
		
		// eRprtList, eCageList
		
		vApprovalWaitList = new JLabel("���δ����");
		eApprovalWaitList = new JTable(model1);
		eApprovalWaitList.addMouseListener(approvalWaitListMouseListener);
		scrollpane1 = new JScrollPane(eApprovalWaitList);
		scrollpane1.setPreferredSize(new Dimension(300,100));
		
		vApprovalCompleteList = new JLabel("���οϷ���");
		eApprovalCompleteList = new JTable(model2);
		scrollpane2 = new JScrollPane(eApprovalCompleteList);
		scrollpane2.setPreferredSize(new Dimension(300,100));
		
		vRprtInfo = new JLabel("�Ű�����");
		
		vRprtNo = new JLabel("�Ű��ȣ");
		xRprtNo = new JTextField(20);
		xRprtNo.setEditable(false);
		
		vRprtDttm = new JLabel("�Ű��Ͻ�");
		xRprtDttm = new JTextField(20);
		xRprtDttm.setEditable(false);
		
		vRprtName = new JLabel("�Ű��ڸ�");
		xRprtName = new JTextField(20);
		xRprtName.setEditable(false);
		
		vTelNo = new JLabel("����ó");
		xTelNo = new JTextField(20);
		xTelNo.setEditable(false);
		
		vRprtTp = new JLabel("�Ű���");
		cbRprtTp = new JComboBox<String>(rprtDiv);
		cbRprtTp.setEnabled(false);
		
		vWrtPrsnTp = new JLabel("�ۼ��ڱ���");
		cbWrtPrsnTp = new JComboBox<String>(wrtPrsnDiv);
		cbWrtPrsnTp.setEnabled(false);
		
		vAnmlKinds = new JLabel("��������");
		cbAnmlKinds = new JComboBox<String>(anmlDiv);
		cbAnmlKinds.setEnabled(false);
		
		vAnmlSize = new JLabel("����ũ��");
		cbAnmlSize = new JComboBox<String>(anmlSizeDiv);
		cbAnmlSize.setEnabled(false);
		
		vExpln = new JLabel("����");
		xExpln = new JTextField(20);
		xExpln.setEditable(false);
		
		vDscvDttm = new JLabel("�߰��Ͻ�");
		xDscvDttm = new JTextField(20);
		xDscvDttm.setEditable(false);
		
		vDscvLoc = new JLabel("�߰����");
		xDscvLoc = new JTextField(20);
		xDscvLoc.setEditable(false);
		
		buttonIcon = ImageIO.read(new File("./img/cat1.png"));
		Imagebutton = new JButton(new ImageIcon(buttonIcon));
		Imagebutton.setBorderPainted(false);
		Imagebutton.setContentAreaFilled(false);
		Imagebutton.setFocusPainted(false);
		
		regis = new JButton("���");
		regis.setBackground(blue);
		regis.setForeground(white);
		regis.addActionListener(rprtRegisterButtonListener);
		
		RprtAssignmentNormView();
	}
	
	private void RprtAssignmentNormView() {
		
		//setTitle("�Ű����_�Ϲݼ���");	
		
		gridbagconstraints.anchor = GridBagConstraints.WEST;
		gridbagconstraints.ipadx = 7;
		
		gridbagconstraints.weightx=1.0;
		gridbagconstraints.weighty=1.0;
		
		setLayout(gridbaglayout);

		gridbagAdd(vApprovalWaitList, 0, 0, 1, 1);
		gridbagAdd(vApprovalCompleteList, 4, 0, 1, 1);
		gridbagAdd(regis, 8,0,1,1);
		
		gridbagAdd(scrollpane1, 0, 1, 4, 1);
		gridbagAdd(scrollpane2, 4, 1, 4, 1);
		
		gridbagAdd(vRprtInfo, 0, 6, 1, 1);
		
		gridbagAdd(vRprtNo, 0, 7, 1, 1);
		gridbagAdd(xRprtNo, 2, 7, 1, 1);
		//�Ű���
		gridbagAdd(vRprtDttm, 4, 7, 1, 1);
		gridbagAdd(xRprtDttm, 6, 7, 1, 1);
		
		gridbagAdd(vRprtName, 0, 8, 1, 1);
		gridbagAdd(xRprtName, 2, 8, 1, 1);
		
		gridbagAdd(vTelNo, 4, 8, 1, 1);
		gridbagAdd(xTelNo, 6, 8, 1, 1);
		
		gridbagAdd(vRprtTp, 0, 9, 1, 1);
		gridbagAdd(cbRprtTp, 2, 9, 1, 1);
		gridbagAdd(vWrtPrsnTp, 4, 9, 1, 1);
		gridbagAdd(cbWrtPrsnTp, 6, 9, 1, 1);
		
		gridbagAdd(vAnmlKinds, 0, 10, 1, 1);
		gridbagAdd(cbAnmlKinds, 2, 10, 1, 1);
		gridbagAdd(vAnmlSize, 4, 10, 1, 1);
		gridbagAdd(cbAnmlSize, 6, 10, 1, 1);
		
		gridbagAdd(vExpln, 0,11,1,1);
		gridbagAdd(xExpln, 2,11,1,1);

		gridbagAdd(vDscvDttm, 0, 12, 1, 1);
		gridbagAdd(xDscvDttm, 2, 12, 1, 1);
		
		gridbagAdd(vDscvLoc, 0, 13, 1, 1);
		gridbagAdd(xDscvLoc, 2, 13, 1, 1);
		
		gridbagAdd(Imagebutton, 11,7,1,3);
		
		gridbagconstraints.anchor = GridBagConstraints.CENTER;

		//pack();
		//setResizable(false);
		//setVisible(true);
		
	}
	
	class RprtRegisterButtonListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource().equals(regis)) {
				try {
					new RscuRegisPopup();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}
		}
		
	}
	
	class ApprovalWaitListMouseListener extends MouseAdapter{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			super.mouseClicked(e);
			if(e.getButton()==1) {
				
			}
		}
		
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
	

	public static void main(String[] args) throws IOException {
		new RprtAssignmentNorm();
	}

}






