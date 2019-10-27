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
		
		vAnncRegis = new JLabel("공고등록");
		vAnncRegis.setFont(new Font("나눔고딕", Font.BOLD, 24));

		vAbanName = new JLabel("유기동물명");
		xAbanName = new JTextField(20);
		
		vAnmlKinds = new JLabel("동물종류");
		xAnmlKinds = new JTextField(20);
		
		vKind = new JLabel("품종");
		xKind = new JTextField(20);
		
		vSex = new JLabel("성별");
		xSex = new JTextField(20);
		
		vAge = new JLabel("나이");
		xAge = new JTextField(20);
		
		vColor= new JLabel("색상");
		xColor = new JTextField(20);
		
		vNeutWhet = new JLabel("중성화여부");
		xNeutWhet = new JTextField(20);
		
		vAnmlSize = new JLabel("동물크기"); 
		xAnmlSize = new JTextField(20);
		
		vFeat = new JLabel("특징");
		xFeat = new JTextArea(4,75);
		anncContentScroll1 = new JScrollPane(xFeat);
		anncContentScroll1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		vExpln= new JLabel("설명");   
		xExpln = new JTextArea(4,75);
		anncContentScroll2 = new JScrollPane(xExpln);
		anncContentScroll2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		search = new JButton("검색");
		search.setBackground(blue);
		search.setForeground(white);
		search.setFont(new Font("나눔고딕", Font.BOLD, 12));
		search.addActionListener(anncRegisButtonListener);
		
		confirm = new JButton("등록");
		confirm.setBackground(blue);
		confirm.setForeground(white);
		confirm.setFont(new Font("나눔고딕", Font.BOLD, 12));
		confirm.addActionListener(anncRegisButtonListener);
		
		cancel = new JButton("취소");
		cancel.setBackground(white);
		cancel.setForeground(black);
		cancel.setFont(new Font("나눔고딕", Font.BOLD, 12));
		cancel.addActionListener(anncRegisButtonListener);
		
		JComponent[] vComps = {vAbanName, vAnmlKinds, vKind, vSex, vAge, vColor, vNeutWhet,
				vAnmlSize, vFeat, vExpln};
		ChangeFont(vComps, new Font("나눔고딕", Font.PLAIN, 16));
		
		AnncRegisView();
		
	}
	
	private void AnncRegisView() {
		
		setTitle("공고등록");
		
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
	      //가장 왼쪽 위 gridx, gridy값은 0 			
				
		gridbagConstraints.gridwidth  = w;	//넓이	
		gridbagConstraints.gridheight = h;	//높이	
	     			
	      			
	    gridbagLayout.setConstraints(c, gridbagConstraints); //컴포넌트를 컴포넌트 위치+크기 정보에 따라 GridBagLayout에 배치			
				
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
