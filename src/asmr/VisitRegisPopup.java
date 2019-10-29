package asmr;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

public class VisitRegisPopup extends JFrame {
	
	VisitRegisPopupButtonListener visitRegisPopupButtonListener;
	
	private JLabel vVisitRegis, vVisitDate, vVisitCont;
	
	private JButton regis, cancel;
	
	private JTextArea xVisitCont;
	
//	private JButton imageButton;
	
//	private BufferedImage buttonIcon;
	
	GridBagLayout gridbagLayout;
	GridBagConstraints gridbagConstraints;
	
	private JDateChooser chooser;
	
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	private Color black = new Color(0,0,0);
	
	public VisitRegisPopup() throws IOException {
		
		visitRegisPopupButtonListener = new VisitRegisPopupButtonListener();
		
		gridbagLayout = new GridBagLayout();
		gridbagConstraints = new GridBagConstraints();
		
		vVisitRegis = new JLabel("방문등록");
		vVisitRegis.setFont(new Font("나눔고딕", Font.BOLD, 24));
		
		vVisitDate = new JLabel("방문일자");
		vVisitDate.setFont(new Font("나눔고딕", Font.PLAIN, 16));
		
		LocalDate now = LocalDate.now();
		Date date = Date.valueOf(now);
		chooser = new JDateChooser(date,"YYYY-MM-dd");
		
//		xVisitDate = new JTextField(20);
//		buttonIcon = ImageIO.read(new File("./images/cal1.png"));
//		imageButton = new JButton(new ImageIcon(buttonIcon));
//		imageButton.setBorderPainted(false);
//		imageButton.setContentAreaFilled(false);
//		imageButton.setFocusPainted(false);

		regis = new JButton("등록");
		regis.setFont(new Font("나눔고딕", Font.BOLD, 16));
		regis.setBackground(blue);
		regis.setForeground(white);
		regis.addActionListener(visitRegisPopupButtonListener);
		
		cancel = new JButton("취소");
		cancel.setFont(new Font("나눔고딕", Font.BOLD, 16));
		cancel.setBackground(white);
		cancel.setForeground(black);
		cancel.addActionListener(visitRegisPopupButtonListener);
		
		vVisitCont = new JLabel("방문내용");
		vVisitCont.setFont(new Font("나눔고딕", Font.PLAIN, 16));
		xVisitCont = new JTextArea(4,75);
		
		VisitRegisPopupView();
		
	}
	
	private void VisitRegisPopupView() {
		setTitle("방문등록");
		
		gridbagConstraints.anchor = GridBagConstraints.WEST;
		gridbagConstraints.ipadx = 7;

		gridbagConstraints.weightx=1.0;
		gridbagConstraints.weighty=1.0;

		setLayout(gridbagLayout);
		
		gridbagAdd(vVisitRegis, 0, 0, 1, 1);
		
		gridbagAdd(vVisitDate, 0, 1, 1, 1);
		gridbagAdd(chooser, 2, 1, 1, 1);
//		gridbagAdd(imageButton, 4, 1, 1, 1);
		
		gridbagAdd(vVisitCont, 0, 2, 1, 1);
		gridbagAdd(xVisitCont, 2, 2, 6, 1);
		
		gridbagAdd(regis, 3, 3, 1, 1);
		gridbagAdd(cancel, 4, 3, 1, 1);
		
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
	
	
    class VisitRegisPopupButtonListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource().equals(regis)) {	
				
			}
			else if(e.getSource().equals(cancel)) {
				dispose();
				
			}
		}
		
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		new VisitRegisPopup();
	}

}
