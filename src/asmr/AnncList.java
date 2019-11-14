package asmr;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import asmr.RprtRegis.RprtRegisterButtonListener;

// 공고목록
public class AnncList extends JPanel{
	
	AnncListMouseListener anncListMouseListener;
	
	private JPanel[] panelArray;
	
	private JLabel vAnncList;
	
	private JButton regis;
	
	GridBagLayout gridbagLayout;
	GridBagConstraints gridbagConstraints;
	
	AnncListButtonListener anncListButtonListener;
	
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	
	public AnncList() throws IOException {
		
		anncListMouseListener = new AnncListMouseListener();
		
		gridbagLayout = new GridBagLayout();
		gridbagConstraints = new GridBagConstraints();
		
		anncListButtonListener = new AnncListButtonListener();
		
		vAnncList = new JLabel("공고목록");
		vAnncList.setFont(new Font("나눔고딕", Font.BOLD, 24));
		
		regis = new JButton("등록");
		regis.setBackground(blue);
		regis.setForeground(white);
		regis.addActionListener(anncListButtonListener);
		regis.setFont(new Font("나눔고딕", Font.BOLD, 16));
		
		panelArray = new JPanel[8];
		
		for (int i =0 ; i<6; i++) {
			panelArray[i] = new Annc();
			panelArray[i].addMouseListener(anncListMouseListener);
		}
		
		AnncListView();
			
	}
	
	
	private void AnncListView() {
		
		
		gridbagConstraints.anchor = GridBagConstraints.WEST;
		gridbagConstraints.ipadx = 7;

		gridbagConstraints.weightx=1.0;
		gridbagConstraints.weighty=1.0;

		setLayout(gridbagLayout);
		
		gridbagAdd(vAnncList, 0, 0, 1, 1);
		//gridbagAdd(regis, 4, 0, 1, 1);
		
		gridbagAdd(panelArray[0], 0, 1, 2 , 2);
		gridbagAdd(panelArray[1], 2, 1, 2 , 2);
		
		gridbagAdd(panelArray[2], 0, 3, 2 , 2);
		gridbagAdd(panelArray[3], 2, 3, 2 , 2);
		
		gridbagAdd(panelArray[4], 0, 5, 2 , 2);
		gridbagAdd(panelArray[5], 2, 5, 2 , 2);
		
//		gridbagAdd(panelArray[6], 0, 7, 2 , 2);
//		gridbagAdd(panelArray[7], 2, 7, 2 , 2);
		
		
//		pack();
//		setResizable(false);
//		setVisible(true);
		
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
	
	
	class AnncListButtonListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource().equals(regis)) {
				new AnncRegis();	
			}
		}
		
	}
	
	
	
	class AnncListMouseListener extends MouseAdapter{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			super.mouseClicked(e);
			if(e.getButton()==1) {
				try {
					new AnncDetailPopup();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
			}
		}
		
	}
	
	
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		new AnncList();

	}

}
