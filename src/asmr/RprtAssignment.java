package asmr;

import java.awt.Button;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import asmr.ProtAniList.ProtAniListButtonListener;

public class RprtAssignment extends JPanel {
	
	RprtAssignmentButtonListener rprtAssignmentButtonListener;
	
	private JLabel vRprtNo, vRprtDttm, vRprtName, vTelNo, vRprtTp, vWrtPrsnTp, vAnmlKinds,
	vAnmlSize, vExpln, vDscvDttm, vDscvLoc, vRprtList, vCageList, vRprtInfo;
	
	private JTextField xRprtNo, xRprtDttm, xRprtName, xTelNo, xDscvDttm, xDscvLoc;
	
	private JTextArea xExpln;
	
	private JComboBox<String> cbRprtTp, cbWrtPrsnTp, cbAnmlKinds, cbAnmlSize;
	
	private JTable eRprtList, eCageList;
	
	private JScrollPane scrollpane1, scrollpane2, rprtContentScroll;
	
	private String[] rprtDiv = {"�߰�", "�ΰ�"};
	private String[] wrtPrsnDiv = {"����","�����"};
	private String[] anmlDiv = {"��", "�����"};
	private String[] anmlSizeDiv = {"��", "��", "��"};
	
	private BufferedImage buttonIcon;
	
	private JButton previous, next, Imagebutton;
	
	GridBagLayout gridbaglayout;
	GridBagConstraints gridbagconstraints;
	
	RprtAssignmentMouseListener rprtAssignmentMouseListener;
	
	private final String[] col1 = {"�Ű��Ͻ�","��������","����ũ��","����","�������͸�"};
	private final String[] col2 = {"���͸�","�ּ�","����������(��)","����������(��)","����������(��)"};
	
	private DefaultTableModel model1 = new DefaultTableModel(col1,0);
	private DefaultTableModel model2 = new DefaultTableModel(col2,0);
	
	public RprtAssignment() throws IOException {
		
		rprtAssignmentButtonListener = new RprtAssignmentButtonListener();
		
		gridbaglayout = new GridBagLayout();
		gridbagconstraints = new GridBagConstraints();
		
		// eRprtList, eCageList
		
		rprtAssignmentMouseListener = new RprtAssignmentMouseListener();
		
		vRprtList = new JLabel("�Ű���");
		vRprtList.setFont(new Font("�������", Font.BOLD, 24));
		
		eRprtList = new JTable(model1){
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
		eRprtList.addMouseListener(rprtAssignmentMouseListener);
		scrollpane1 = new JScrollPane(eRprtList);
		scrollpane1.setPreferredSize(new Dimension(600,200));
		
		vCageList = new JLabel("���ͺ���������Ȳ");
		vCageList.setFont(new Font("�������", Font.BOLD, 24));
		
		eCageList = new JTable(model2){
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
		eCageList.addMouseListener(rprtAssignmentMouseListener);
		scrollpane2 = new JScrollPane(eCageList);
		scrollpane2.setPreferredSize(new Dimension(600,200));
		
		vRprtInfo = new JLabel("�Ű�����");
		vRprtInfo.setFont(new Font("�������", Font.BOLD, 20));
		
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
		xExpln = new JTextArea(4,75);
		xExpln.setEditable(false);
		rprtContentScroll = new JScrollPane(xExpln);
		rprtContentScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		vDscvDttm = new JLabel("�߰��Ͻ�");
		xDscvDttm = new JTextField(20);
		xDscvDttm.setEditable(false);
		
		vDscvLoc = new JLabel("�߰����");
		xDscvLoc = new JTextField(20);
		xDscvLoc.setEditable(false);
		
		buttonIcon = ImageIO.read(new File("./images/cat1.png"));
		Imagebutton = new JButton(new ImageIcon(buttonIcon));
		Imagebutton.setBorderPainted(false);
		Imagebutton.setContentAreaFilled(false);
		Imagebutton.setFocusPainted(false);
		
		previous = new JButton("<<");
		previous.addActionListener(rprtAssignmentButtonListener);
		previous.setFont(new Font("�������", Font.BOLD, 16));
		next = new JButton(">>");
		next.addActionListener(rprtAssignmentButtonListener);
		next.setFont(new Font("�������", Font.BOLD, 16));
		
		JComponent[] vComps = {vRprtNo, vRprtDttm, vRprtName, vTelNo, vRprtTp, vWrtPrsnTp, vAnmlKinds,
				vAnmlSize, vExpln, vDscvDttm, vDscvLoc};
		ChangeFont(vComps, new Font("�������", Font.PLAIN, 16));
		
		new Button();
		
		RprtAssignmentView();
	}
	
	private void RprtAssignmentView() {
		
		//setTitle("�Ű����_���μ���");	
		
		gridbagconstraints.anchor = GridBagConstraints.WEST;
		gridbagconstraints.ipadx = 7;
		
		gridbagconstraints.weightx=1.0;
		gridbagconstraints.weighty=1.0;
		
		setLayout(gridbaglayout);

		gridbagAdd(vRprtList, 0, 0, 1, 1);
		gridbagAdd(vCageList, 4, 0, 1, 1);
		
		gridbagAdd(scrollpane1, 0, 1, 4, 4);
		gridbagAdd(scrollpane2, 4, 1, 4, 4);
		
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
		gridbagAdd(xExpln, 2,11,6,1);

		gridbagAdd(vDscvDttm, 0, 12, 1, 1);
		gridbagAdd(xDscvDttm, 2, 12, 1, 1);
		
		gridbagAdd(vDscvLoc, 0, 13, 1, 1);
		gridbagAdd(xDscvLoc, 2, 13, 1, 1);
		
		gridbagAdd(Imagebutton, 11,7,5,3);
		
		gridbagAdd(previous, 12,11,1,3);
		gridbagAdd(next, 13,11,1,3);
		
		
		
		gridbagconstraints.anchor = GridBagConstraints.CENTER;

		//pack();
		//setResizable(false);
		//setVisible(true);	
		
	}
	
	class RprtAssignmentButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource().equals(previous)) {
				
			}
			else if(e.getSource().equals(next)) {
				
			}
			
		}
	
	}
	
	class RprtAssignmentMouseListener extends MouseAdapter{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			super.mouseClicked(e);
			if(e.getButton()==1) {
				
			}
		}
		
	}
	
    private void ChangeFont(JComponent[] comps, Font font) {
    	for(JComponent comp: comps) {
    		comp.setFont(font);
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
		new RprtAssignment();
	}

}






