package asmr;

import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerDateModel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class RprtRegis extends JFrame implements ActionListener{
	private JLabel vRprtName,  vTelNo, vRprtTp,vAnmlKinds,
	vAnmlSize, vExpln,vDscvDttm, vDscvLoc, vPic;
	
	private JTextField xRprtName, xTelNo, xDscvDttm,
	xDscvLoc, xPic;
	
	private JTextArea xExpln;
	
	private String[] rprtDiv = {"�߰�", "�ΰ�"};
	
	private String[] anmlDiv = {"��", "�����"};
	
	private String[] anmlSizeDiv = {"��", "��", "��"};

	private JButton regist, cancel, Imagebutton;
	
	private BufferedImage buttonIcon;
	
	private Button btnSearch;
	
	private JComboBox<String> cbRprtTp, cbAnmlKinds, cbAnmlSize;
	
	private JFileChooser fc;
	
	private JSpinner timeSpinner;
	
	private JSpinner.DateEditor timeEditor;
	
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	
	private JScrollPane rprtContentScroll;
	
	RprtRegisterButtonListener RprtRegisterButtonListener;
	
	GridBagLayout gridbaglayout;
	GridBagConstraints gridbagconstraints;

	public RprtRegis() throws IOException{
		
		RprtRegisterButtonListener = new RprtRegisterButtonListener();
		
		gridbaglayout = new GridBagLayout();
		gridbagconstraints = new GridBagConstraints();

		vRprtName = new JLabel("�Ű��ڸ�");
		xRprtName = new JTextField(20);

		vTelNo = new JLabel("��ȭ��ȣ");
		xTelNo = new JTextField(20);

		vRprtTp = new JLabel("�Ű���");
		cbRprtTp = new JComboBox<String>(rprtDiv);
	
		vAnmlKinds = new JLabel("��������");
		cbAnmlKinds = new JComboBox<String>(anmlDiv);
				
		vAnmlSize = new JLabel("����ũ��");
		cbAnmlSize = new JComboBox<String>(anmlSizeDiv);

		vExpln = new JLabel("����");
		xExpln = new JTextArea(4,75);
		rprtContentScroll = new JScrollPane(xExpln);
		rprtContentScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		vDscvDttm = new JLabel("�߰��Ͻ�");
		xDscvDttm = new JTextField(20);
		xDscvDttm.setEditable(false);

		vDscvLoc = new JLabel("�߰����");
		xDscvLoc = new JTextField(20);
		
		
		vPic = new JLabel("����");
		xPic = new JTextField(20);
		xPic.setEditable(false);
		
		btnSearch = new Button("ã�ƺ���");
		btnSearch.addActionListener(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		regist = new JButton("���");
		regist.setBackground(blue);
		regist.setForeground(white);
		regist.addActionListener(RprtRegisterButtonListener);
		cancel = new JButton("���");
		cancel.addActionListener(RprtRegisterButtonListener);
		
		buttonIcon = ImageIO.read(new File("./images/cal1.png"));
		Imagebutton = new JButton(new ImageIcon(buttonIcon));
		Imagebutton.setBorderPainted(false);
		Imagebutton.setContentAreaFilled(false);
		Imagebutton.setFocusPainted(false);
		
		fc = new JFileChooser();
		
		fc.setMultiSelectionEnabled(true);
		
		timeSpinner = new JSpinner(new SpinnerDateModel());
		timeEditor = new JSpinner.DateEditor(timeSpinner, "yyyy.MM.dd HH:mm:ss");
		timeSpinner.setEditor(timeEditor);
		//timeSpinner.setValue(new Date());
		
		RprtRegisView();
	}
	
	class RprtRegisterButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource().equals(regist)) {
				
			}
			if(e.getSource().equals(cancel)) {
				dispose();
			}
		}
		
	}

	
	public void actionPerformed(ActionEvent e) {
		
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif", "png");
		
		fc.setFileFilter(filter);
		
		int result = fc.showOpenDialog(null);
		
		if (result != JFileChooser.APPROVE_OPTION) {
            JOptionPane.showMessageDialog(null, 
                    "������ �������� �ʾҽ��ϴ�.", "���", 
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // ���� �������� ���
        String path = fc.getSelectedFile().getPath();
        pack();
        
        xPic.setText(path);
		
	}
	
	
	
	
	
	private void RprtRegisView() {

		setTitle("�Ű� ���");

		gridbagconstraints.anchor = GridBagConstraints.WEST;
		gridbagconstraints.ipadx = 7;

		gridbagconstraints.weightx=1.0;
		gridbagconstraints.weighty=1.0;

		setLayout(gridbaglayout);

		gridbagAdd(vRprtName, 0, 0, 1, 1);
		gridbagAdd(xRprtName, 2, 0, 1, 1);
		gridbagAdd(vTelNo, 10, 0, 1, 1);
		gridbagAdd(xTelNo, 12, 0, 1, 1);
		gridbagAdd(vRprtTp, 0, 1, 1, 1);
		gridbagAdd(cbRprtTp, 2, 1, 1, 1);
		gridbagAdd(vAnmlKinds, 0, 2, 1, 1);
		gridbagAdd(cbAnmlKinds, 2, 2, 1, 1);
		gridbagAdd(vAnmlSize, 10, 2, 1, 1);
		gridbagAdd(cbAnmlSize, 12, 2, 1, 1);
		gridbagAdd(vExpln, 0, 3, 1, 1);
		gridbagAdd(xExpln, 2, 3, 11, 1);
		gridbagAdd(vDscvDttm, 0, 6, 1, 1);
		gridbagAdd(xDscvDttm, 2, 6, 1, 1);
		gridbagAdd(Imagebutton, 4,6,1,1);
		gridbagAdd(timeSpinner, 8,6,1,1);
		
		gridbagAdd(vDscvLoc, 0, 7, 1, 1);
		gridbagAdd(xDscvLoc, 2, 7, 1, 1);
		gridbagAdd(vPic, 0, 8, 1, 1);
		gridbagAdd(xPic, 2, 8, 1, 1);
		gridbagAdd(btnSearch, 4,8,1,1);
		
		gridbagconstraints.anchor = GridBagConstraints.CENTER;

		gridbagAdd(regist, 4, 9, 1, 1);
		gridbagAdd(cancel, 6, 9, 1, 1);

		pack();
		setResizable(false);
		setVisible(true);

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
		new RprtRegis();		
	}			
	
	

}


