package asmr;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

public class RscuRegisPopup extends JFrame {
	
	RscuRegisPopupButtonListener rscuRegisPopupButtonListener;
	
	private JLabel vRscuRegis, vRscuDttm, vRscuLoc, vRepRscuCrewName;
	
	private JTextField xRscuDttm, xRscuLoc, xRepRscuCrewName;
	
	private JButton confirm, cancel, imageButton, btnSearch;
	
//	private BufferedImage buttonIcon;
	
	private JDateChooser chooser;
	
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	private Color black = new Color(0,0,0);
	
	GridBagLayout gridbagLayout;
	GridBagConstraints gridbagConstraints;

	public RscuRegisPopup() throws IOException  {
		
		rscuRegisPopupButtonListener = new RscuRegisPopupButtonListener();
		
		gridbagLayout = new GridBagLayout();
		gridbagConstraints = new GridBagConstraints();
		
		vRscuRegis = new JLabel("구조등록");
		vRscuRegis.setFont(new Font("나눔고딕", Font.BOLD, 24));
		
		vRscuDttm = new JLabel("구조일시");
		vRscuDttm.setFont(new Font("나눔고딕", Font.PLAIN, 16));
		xRscuDttm = new JTextField(20);
		xRscuDttm.setEditable(false);
		
		vRscuLoc = new JLabel("구조장소");
		vRscuLoc.setFont(new Font("나눔고딕", Font.PLAIN, 16));
		xRscuLoc = new JTextField(20);
		
		vRepRscuCrewName = new JLabel("대표구조대원명");
		vRepRscuCrewName.setFont(new Font("나눔고딕", Font.PLAIN, 16));
		xRepRscuCrewName = new JTextField(20);
		xRepRscuCrewName.setEditable(false);
		
		btnSearch = new JButton("검색");
		btnSearch.setBackground(blue);
		btnSearch.setForeground(white);
		
		confirm = new JButton("확인");
		confirm.setFont(new Font("나눔고딕", Font.BOLD, 16));
		confirm.setBackground(blue);
		confirm.setForeground(white);
		confirm.addActionListener(rscuRegisPopupButtonListener);
		
		cancel = new JButton("취소");
		cancel.setFont(new Font("나눔고딕", Font.BOLD, 16));
		cancel.setBackground(white);
		cancel.setForeground(black);
		cancel.addActionListener(rscuRegisPopupButtonListener);
		
//		buttonIcon = ImageIO.read(new File("./images/cal1.png"));
//		imageButton = new JButton(new ImageIcon(buttonIcon));
//		imageButton.setBorderPainted(false);
//		imageButton.setContentAreaFilled(false);
//		imageButton.setFocusPainted(false);
		
		LocalDate now = LocalDate.now();
		Date date = Date.valueOf(now);
		chooser = new JDateChooser(date,"YYYY-MM-dd");
		chooser.setEnabled(false);
		
		RscuRegisPopupView();
	}
	
	private void RscuRegisPopupView() {
		
		setTitle("구조등록");
		
		gridbagConstraints.anchor = GridBagConstraints.WEST;
		gridbagConstraints.ipadx = 7;

		gridbagConstraints.weightx=1.0;
		gridbagConstraints.weighty=1.0;

		setLayout(gridbagLayout);
		
		gridbagAdd(vRscuRegis, 0, 0, 1, 1);
		
		gridbagAdd(vRscuDttm, 0, 1, 1, 1);
		gridbagAdd(chooser, 2, 1, 2, 1);
//		gridbagAdd(imageButton, 5, 1, 1, 1);
		
		gridbagAdd(vRscuLoc, 0, 2, 1, 1);
		gridbagAdd(xRscuLoc, 2, 2, 2, 1);
		
		gridbagAdd(vRepRscuCrewName, 0, 4, 1, 1);
		gridbagAdd(xRepRscuCrewName, 2, 4, 1, 1);
		
		gridbagAdd(btnSearch, 4, 4, 1, 1);
		
		gridbagAdd(confirm,2, 5, 1, 1);
		gridbagAdd(cancel, 3, 5, 1, 1);
		
		pack();
		setLocationRelativeTo(null);
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
	
    class RscuRegisPopupButtonListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource().equals(confirm)) {	
				
			}
			else if(e.getSource().equals(cancel)) {
				dispose();
				
			}
			else if(e.getSource().equals(btnSearch)) {
			
				
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException  {
		// TODO Auto-generated method stub
		
		new RscuRegisPopup();

	}

}
