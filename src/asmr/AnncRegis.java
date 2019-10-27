package asmr;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class AnncRegis extends JFrame {
	
	AnncRegisButtonListener anncRegisButtonListener;
	
	private JLabel vAnncRegis, vAbanName, vAnmlKinds, vKind, vSex, vAge,vColor, vNeutWhet, vAnmlSize, vFeat, vExpln;   
	
	private JTextField xAbanName, xAnmlKinds, xKind, xSex, xAge, xColor, xNeutWhet, xAnmlSize;
	
	private JTextArea xFeat, xExpln;
	
	private JButton confirm, cancel, search;
	
	GridBagLayout gridbagLayout;
	GridBagConstraints gridbagConstraints;
	
	private JScrollPane anncContentScroll1,anncContentScroll2;
	
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	private Color black = new Color(0,0,0);
	
	public AnncRegis() {
		
		anncRegisButtonListener = new AnncRegisButtonListener();
		
		gridbagLayout = new GridBagLayout();
		gridbagConstraints = new GridBagConstraints();
		
		vAnncRegis = new JLabel("������");
		vAnncRegis.setFont(new Font("�������", Font.BOLD, 24));

		vAbanName = new JLabel("���⵿����");
		xAbanName = new JTextField(20);
		
		vAnmlKinds = new JLabel("��������");
		xAnmlKinds = new JTextField(20);
		
		vKind = new JLabel("ǰ��");
		xKind = new JTextField(20);
		
		vSex = new JLabel("����");
		xSex = new JTextField(20);
		
		vAge = new JLabel("����");
		xAge = new JTextField(20);
		
		vColor= new JLabel("����");
		xColor = new JTextField(20);
		
		vNeutWhet = new JLabel("�߼�ȭ����");
		xNeutWhet = new JTextField(20);
		
		vAnmlSize = new JLabel("����ũ��"); 
		xAnmlSize = new JTextField(20);
		
		vFeat = new JLabel("Ư¡");
		xFeat = new JTextArea(4,75);
		anncContentScroll1 = new JScrollPane(xFeat);
		anncContentScroll1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		vExpln= new JLabel("����");   
		xExpln = new JTextArea(4,75);
		anncContentScroll2 = new JScrollPane(xExpln);
		anncContentScroll2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		search = new JButton("�˻�");
		search.setBackground(blue);
		search.setForeground(white);
		search.setFont(new Font("�������", Font.BOLD, 12));
		search.addActionListener(anncRegisButtonListener);
		
		confirm = new JButton("���");
		confirm.setBackground(blue);
		confirm.setForeground(white);
		confirm.setFont(new Font("�������", Font.BOLD, 12));
		confirm.addActionListener(anncRegisButtonListener);
		
		cancel = new JButton("���");
		cancel.setBackground(white);
		cancel.setForeground(black);
		cancel.setFont(new Font("�������", Font.BOLD, 12));
		cancel.addActionListener(anncRegisButtonListener);
		
		JComponent[] vComps = {vAbanName, vAnmlKinds, vKind, vSex, vAge, vColor, vNeutWhet,
				vAnmlSize, vFeat, vExpln};
		ChangeFont(vComps, new Font("�������", Font.PLAIN, 16));
		
		AnncRegisView();
		
	}
	
	private void AnncRegisView() {
		
		setTitle("������");
		
		gridbagConstraints.anchor = GridBagConstraints.WEST;
		gridbagConstraints.ipadx = 7;

		gridbagConstraints.weightx=1.0;
		gridbagConstraints.weighty=1.0;

		setLayout(gridbagLayout);
		
		gridbagAdd(vAnncRegis, 0, 0, 1, 1);
		
		gridbagAdd(vAbanName, 0, 1, 1, 1);
		gridbagAdd(xAbanName, 2, 1, 1, 1);
		gridbagAdd(search, 3, 1, 1, 1);
		
		gridbagAdd(vAnmlKinds, 0, 2, 1, 1);
		gridbagAdd(xAnmlKinds, 2, 2, 1, 1);
		gridbagAdd(vKind, 4, 2, 1, 1);
		gridbagAdd(xKind, 6, 2, 1, 1);
		
		gridbagAdd(vSex, 0, 3, 1, 1);
		gridbagAdd(xSex, 2, 3, 1, 1);
		gridbagAdd(vAge, 4, 3, 1, 1);
		gridbagAdd(xAge, 6, 3, 1, 1);
		
		gridbagAdd(vColor, 0, 4, 1, 1);
		gridbagAdd(xColor, 2, 4, 1, 1);
		gridbagAdd(vNeutWhet, 4, 4, 1, 1);
		gridbagAdd(xNeutWhet, 6, 4, 1, 1);
		
		gridbagAdd(vAnmlSize, 4, 5, 1, 1);
		gridbagAdd(xAnmlSize, 6, 5, 1, 1);
		
		gridbagAdd(vFeat, 0, 6, 1, 1);
		gridbagAdd(xFeat, 2, 6, 6, 1);
		
		gridbagAdd(vExpln, 0, 7, 1, 1);
		gridbagAdd(xExpln, 2, 7, 6, 1);
		
		
		
		gridbagAdd(confirm,3, 8, 1, 1);
		gridbagAdd(cancel, 4, 8, 1, 1);
		
		pack();
		setResizable(false);
		setVisible(true);
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
    
	class AnncRegisButtonListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource().equals(search)) {
				new ProtAnmlSearchPopup();	
			}
			else if (e.getSource().equals(confirm)) {
				
			}
			else if(e.getSource().equals(cancel)) {
				dispose();
			}
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new AnncRegis();

	}

}
