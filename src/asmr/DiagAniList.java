package src.asmr;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import java.sql.Date;
import java.time.LocalDate;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

public class DiagAniList extends JPanel{
	private JLabel vProtAniList, vDiagList, vDiagInfo, vDiagDate, vDiagType, vIndiResult, vIndiVtrnName, vOudiResult, vHospName, vDisease, vInfecWhet, vCureType, vHsptzDate, vDschDate, vDeathType, vDeathReason, vDiagContent;
	private JTextField xDiagDate, xDiagType, xIndiResult, xIndiVtrnName, xOudiResult, xHospName, xDisease, xInfecWhet, xCureType, xHsptzDate, xDschDate, xDeathType, xDeathReason;
	private JButton diagRegister, imageButton, modify, cancel;
	private JTextArea xDiagContent; 
	private BufferedImage buttonIcon;
	private JDateChooser chooser;
	
	private final String[] col1 = {"���⵿����","��������","ǰ��","����(����)","ũ��"};
	private final String[] col2 = {"��������","���ᱸ��","����"};
	
	private DefaultTableModel model1 = new DefaultTableModel(col1,0);
	private DefaultTableModel model2 = new DefaultTableModel(col2,0);
	
	private JTable eProtAniList;
	private JScrollPane protAniListScroll;
	
	private JTable eDiagList;
	private JScrollPane diagListScroll;
	
	private JScrollPane diagContentScroll;
	
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	
	DiagAniListButtonListener diagAniListButtonListener;
	ProtAniListMouseListener protAniListMouseListener;
	DiagListMouseListener diagListMouseListener;
	
	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;
	
	public DiagAniList() throws IOException {
		gridBagLayout = new GridBagLayout();		
		gridBagConstraints = new GridBagConstraints();
	
		diagAniListButtonListener = new DiagAniListButtonListener();
		protAniListMouseListener = new ProtAniListMouseListener();
		diagListMouseListener = new DiagListMouseListener();
		
		vProtAniList = new JLabel("��ȣ�������");
		vProtAniList.setFont(new Font("�������", Font.BOLD, 24));
		
		diagRegister = new JButton("������");
		diagRegister.setBackground(blue);
		diagRegister.setForeground(white);
		diagRegister.addActionListener(diagAniListButtonListener);
		
		vDiagList = new JLabel("������");
		vDiagList.setFont(new Font("�������", Font.BOLD, 24));
		vDiagList.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		
//		eProtAniList = new JTable(model1);
		eProtAniList = new JTable(model1) {
		        private static final long serialVersionUID = 1L;

		        public boolean isCellEditable(int row, int column) {                
		                return false;               
		        };
		    };
		eProtAniList.addMouseListener(protAniListMouseListener);
		protAniListScroll = new JScrollPane(eProtAniList);
		protAniListScroll.setPreferredSize(new Dimension(750,200));
		
//		eDiagList = new JTable(model2);
		eDiagList = new JTable(model2) {
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
		eDiagList.addMouseListener(diagListMouseListener);
		diagListScroll = new JScrollPane(eDiagList);
		diagListScroll.setPreferredSize(new Dimension(450,200));
		
		vDiagInfo = new JLabel("��������");
		vDiagInfo.setFont(new Font("�������", Font.BOLD, 20));
		vDiagInfo.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
		
		vDiagDate = new JLabel("��������");
		xDiagDate = new JTextField(12);
		xDiagDate.setEnabled(false);
		
		vDiagType = new JLabel("���ᱸ��");
		xDiagType = new JTextField(12);
		xDiagType.setEnabled(false);
		
		vIndiResult = new JLabel("������");
		xIndiResult = new JTextField(12);
		xIndiResult.setEnabled(false);
		
		vIndiVtrnName = new JLabel("�������ǻ��");
		xIndiVtrnName =  new JTextField(12);
		xIndiVtrnName.setEnabled(false);
		
		vOudiResult = new JLabel("�������");
		xOudiResult = new JTextField(12);
		xOudiResult.setEnabled(false);
		
		vHospName = new JLabel("������");
		xHospName = new JTextField(12);
		xHospName.setEnabled(false);
		
		vDisease = new JLabel("����");
		xDisease = new JTextField(12);
		xDisease.setEnabled(false);
		
		vInfecWhet = new JLabel("��������");
		xInfecWhet = new JTextField(12);
		xInfecWhet.setEnabled(false);
		
		vCureType = new JLabel("ġ�ᱸ��");
		xCureType = new JTextField(12);
		xCureType.setEnabled(false);
		
		vHsptzDate = new JLabel("�Կ�����");
		xHsptzDate = new JTextField(12);
		xHsptzDate.setEnabled(false);
		
		vDschDate = new JLabel("�������");
		
		LocalDate now = LocalDate.now();
		Date date = Date.valueOf(now);
		chooser = new JDateChooser(date,"YYYY.MM.dd");
		chooser.setEnabled(false);
		
//		xDschDate = new JTextField(12);
//		xDschDate.setEnabled(false);
//		buttonIcon = ImageIO.read(new File("images/cal1.png"));
//		imageButton = new JButton(new ImageIcon(buttonIcon));
//		imageButton.setBorderPainted(false);
//		imageButton.setContentAreaFilled(false);
//		imageButton.setFocusPainted(false);
		
		
		vDeathType = new JLabel("�������");
		xDeathType = new JTextField(12);
		xDeathType.setEnabled(false);
		
		vDeathReason = new JLabel("�������");
		xDeathReason = new JTextField(12);
		xDeathReason.setEnabled(false);
		
		vDiagContent = new JLabel("���᳻��");
		xDiagContent = new JTextArea();
		xDiagContent.setEnabled(false);
		diagContentScroll = new JScrollPane(xDiagContent);
		diagContentScroll.setPreferredSize(new Dimension(400,150));
		diagContentScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		modify = new JButton("����");
		modify.setBackground(blue);
		modify.setForeground(white);
		modify.addActionListener(diagAniListButtonListener);
		
		cancel = new JButton("���");
		cancel.addActionListener(diagAniListButtonListener);
		
		DiagAniListView();
		
		JComponent[] vComps = {vDiagDate, vDiagType, vIndiResult, vIndiVtrnName, vOudiResult, vHospName, vDisease, vInfecWhet, vCureType, vHsptzDate, vDschDate, vDeathType, vDeathReason, vDiagContent};
		ChangeFont(vComps, new Font("�������", Font.PLAIN, 16));
		JComponent[] bComps = {diagRegister, modify, cancel};
		ChangeFont(bComps, new Font("�������", Font.BOLD, 16));
		
	}

	private void DiagAniListView() {
		setLayout(gridBagLayout);
		
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.ipadx = 7;
		
		gridBagConstraints.weightx=1.0;
		gridBagConstraints.weighty=1.0;
		
		gridbagAdd(vProtAniList, 0, 0, 1, 1);
		gridbagAdd(diagRegister, 4, 0, 1, 1);
		JPanel plainPanel = new JPanel();
		plainPanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
		plainPanel.add(protAniListScroll);
		plainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 20));
		gridbagAdd(plainPanel, 0, 1, 5, 1);
		
		gridbagAdd(vDiagList, 5, 0, 1, 1);
		gridbagAdd(diagListScroll, 5, 1, 5, 1);
		
		gridbagAdd(vDiagInfo, 0, 2, 1, 1);
		
		gridbagAdd(vDiagDate, 0, 3, 1, 1);
		gridbagAdd(xDiagDate, 1, 3, 1, 1);
		
		gridbagAdd(vDiagType, 2, 3, 1, 1);
		gridbagAdd(xDiagType, 3, 3, 1, 1);
		
		gridbagAdd(vIndiResult, 0, 4, 1, 1);
		gridbagAdd(xIndiResult, 1, 4, 1, 1);
		
		gridbagAdd(vIndiVtrnName, 2, 4, 1, 1);
		gridbagAdd(xIndiVtrnName, 3, 4, 1, 1);
		
		gridbagAdd(vOudiResult, 0, 5, 1, 1);
		gridbagAdd(xOudiResult, 1, 5, 1, 1);
		
		gridbagAdd(vHospName, 2, 5, 1, 1);
		gridbagAdd(xHospName, 3, 5, 1, 1);
		
		gridbagAdd(vDisease, 0, 6, 1, 1);
		gridbagAdd(xDisease, 1, 6, 1, 1);
		
		gridbagAdd(vInfecWhet, 2, 6, 1, 1);
		gridbagAdd(xInfecWhet, 3, 6, 1, 1);
		
		gridbagAdd(vCureType, 0, 7, 1, 1);
		gridbagAdd(xCureType, 1, 7, 1, 1);
		
		gridbagAdd(vHsptzDate, 2, 7, 1, 1);
		gridbagAdd(xHsptzDate, 3, 7, 1, 1);
		
		gridbagAdd(vDschDate, 2, 8, 1, 1);
				
//		Component[] cops1 = {xDschDate,imageButton};
//		CombinePanel dschDatePanel = new CombinePanel(cops1, false);
//		gridbagAdd(dschDatePanel, 3, 8, 1, 1);

		gridbagAdd(chooser, 3, 8, 1, 1);

		
		
		gridbagAdd(vDeathType, 0, 9, 1, 1);
		gridbagAdd(xDeathType, 1, 9, 1, 1);

		gridbagAdd(vDeathReason, 2, 9, 1, 1);
		gridbagAdd(xDeathReason, 3, 9, 1, 1);
		
		gridbagAdd(vDiagContent, 0, 10, 1, 1);
		gridbagAdd(diagContentScroll, 1, 10, 3, 1);
	
		Component[] cops2 = {modify, cancel};
		CombinePanel buttonPanel = new CombinePanel(cops2, true);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 280, 0, 0));
		gridbagAdd(buttonPanel, 0, 11, 6, 1);

	}
	
	private void gridbagAdd(Component c, int x, int y, int w , int h) {
		
		gridBagConstraints.gridx = x;
		gridBagConstraints.gridy = y;
		gridBagConstraints.gridwidth = w;
		gridBagConstraints.gridheight = h;
		
		gridBagLayout.setConstraints(c, gridBagConstraints);
		
		add(c);
	}
	
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
	
	class DiagAniListButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource().equals(diagRegister)) {
				try {
					new DiagRegister();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if(e.getSource().equals(modify)) {
				modify.setText("Ȯ��");
				JComponent[] changeStatusComps = {xDiagContent,chooser};
				for(JComponent cop: changeStatusComps) {
					cop.setEnabled(true);	
				}
			}
			else if(e.getSource().equals(cancel)) {
				modify.setText("����");
				JComponent[] changeStatusComps = {xDiagContent,chooser};
				for(JComponent cop: changeStatusComps) {
					cop.setEnabled(false);		
				}
			}
		} 
	}	
	class ProtAniListMouseListener extends MouseAdapter{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			super.mouseClicked(e);
			if(e.getButton()==1) {
				
			}
		}
		
	}
	
	class DiagListMouseListener extends MouseAdapter{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			super.mouseClicked(e);
			if(e.getButton()==1) {
				checkDiagType();
			}
		}
		
	}
	
	//�������� element�� ���� �Ŀ� �� element�� ���ᱸ�п� ���� �޷� imageButton�� Ȱ��ȭ/��Ȱ��ȭ�մϴ�.
	private void checkDiagType() {
		String target = xDiagType.getText();
		if(target=="����")
			imageButton.setEnabled(false);
		else if(target=="����")
			imageButton.setEnabled(true);
	}
	
    private void ChangeFont(JComponent[] comps, Font font) {
    	for(JComponent comp: comps) {
    		comp.setFont(font);
    	}
    }
	
	public static void main(String[] args) {
	
	}
}
