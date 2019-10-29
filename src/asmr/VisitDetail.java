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
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import com.toedter.calendar.JDateChooser;

public class VisitDetail extends JFrame {
	
	VisitDetailButtonListener visitDetailButtonListener;
	
	private JLabel vVisitDetail, vAnmlInfo, vAbanName, vAnmlKinds, vKind, vSex, vAge,
	vReqPrsnInfo, vReqPrsnName, vTelNo, vAddr, vVisitDate, vVisitCont;
	
	private JTextField xAbanName, xAnmlKinds, xKind, xSex, xAge, xReqPrsnName, xTelNo,
	xAddr;
	
	private JTextArea xVisitCont;
	
	private JButton confirm, cancel;
	
//	private BufferedImage buttonIcon;
	
	GridBagLayout gridbagLayout;
	GridBagConstraints gridbagConstraints;
	
	private JDateChooser chooser;
	
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	private Color black = new Color(0,0,0);
	
	public VisitDetail() throws IOException {
		
		visitDetailButtonListener = new VisitDetailButtonListener();
		
		gridbagLayout = new GridBagLayout();
		gridbagConstraints = new GridBagConstraints();
		
		vVisitDetail = new JLabel("방문상세");
		vVisitDetail.setFont(new Font("나눔고딕", Font.BOLD, 24));
		
		vAnmlInfo = new JLabel("동물정보");
		vAnmlInfo.setFont(new Font("나눔고딕", Font.BOLD, 20));
		
		vAbanName = new JLabel("유기동물명");
		xAbanName = new JTextField(20);
		xAbanName.setEditable(false);
		
		vAnmlKinds = new JLabel("동물종류");
		xAnmlKinds = new JTextField(20);
		xAnmlKinds.setEditable(false);
		
		vKind = new JLabel("품종");
		xKind = new JTextField(20);
		xKind.setEditable(false);
		
		vSex = new JLabel("성별");
		xSex = new JTextField(20);
		xSex.setEditable(false);
		
		vAge = new JLabel("나이");
		xAge = new JTextField(20);
		xAge.setEditable(false);
		
		vReqPrsnInfo = new JLabel("신청자정보");
		vReqPrsnInfo.setFont(new Font("나눔고딕", Font.BOLD, 20));

		vReqPrsnName = new JLabel("신청자명");
		xReqPrsnName = new JTextField(20);
		xReqPrsnName.setEditable(false);
		
		vTelNo = new JLabel("전화번호");
		xTelNo = new JTextField(20);
		xTelNo.setEditable(false);
		
		vAddr = new JLabel("주소");
		xAddr = new JTextField(20);
		xAddr.setEditable(false);
		
		vVisitDate = new JLabel("방문일자");
		
		LocalDate now = LocalDate.now();
		Date date = Date.valueOf(now);
		chooser = new JDateChooser(date,"YYYY-MM-dd");
		chooser.setEnabled(false);
//		xVisitDate = new JTextField(20);
//		buttonIcon = ImageIO.read(new File("./images/cal1.png"));
//		imageButton = new JButton(new ImageIcon(buttonIcon));
//		imageButton.setBorderPainted(false);
//		imageButton.setContentAreaFilled(false);
//		imageButton.setFocusPainted(false);
		
		vVisitCont = new JLabel("방문내용");
		xVisitCont = new JTextArea(4,75);
		xVisitCont.setEditable(false);
		
	
		confirm = new JButton("확인");
		confirm.setFont(new Font("나눔고딕", Font.BOLD, 16));
		confirm.setBackground(blue);
		confirm.setForeground(white);
		confirm.addActionListener(visitDetailButtonListener);
		
		
		cancel = new JButton("취소");
		cancel.setFont(new Font("나눔고딕", Font.BOLD, 16));
		cancel.setBackground(white);
		cancel.setForeground(black);
		cancel.addActionListener(visitDetailButtonListener);
		
		JComponent[] vComps = {vAbanName, vAnmlKinds, vKind, vSex, vAge, vReqPrsnName, vTelNo,
				vAddr, vVisitDate, vVisitCont};
		ChangeFont(vComps, new Font("나눔고딕", Font.PLAIN, 16));
		
		VisitDetailView();
				
	}
	
	private void VisitDetailView() {
		setTitle("방문등록");
		
		gridbagConstraints.anchor = GridBagConstraints.WEST;
		gridbagConstraints.ipadx = 7;

		gridbagConstraints.weightx=1.0;
		gridbagConstraints.weighty=1.0;

		setLayout(gridbagLayout);

		gridbagAdd(vVisitDetail, 0, 0, 1, 1);
		
		gridbagAdd(vAnmlInfo, 0, 1, 1, 1);
		
		gridbagAdd(vAbanName, 0, 2, 1, 1);
		gridbagAdd(xAbanName, 2, 2, 1, 1);
		
		gridbagAdd(vAnmlKinds, 0, 3, 1, 1);
		gridbagAdd(xAnmlKinds, 2, 3, 1, 1);
		gridbagAdd(vKind, 4, 3, 1, 1);
		gridbagAdd(xKind, 6, 3, 1, 1);
		
		gridbagAdd(vSex, 0, 4, 1, 1);
		gridbagAdd(xSex, 2, 4, 1, 1);
		gridbagAdd(vAge, 4, 4, 1, 1);
		gridbagAdd(xAge, 6, 4, 1, 1);
		
		gridbagAdd(vReqPrsnInfo, 0, 5, 1, 1);
		
		gridbagAdd(vReqPrsnName, 0, 6, 1, 1);
		gridbagAdd(xReqPrsnName, 2, 6, 1, 1);
		
		gridbagAdd(vTelNo, 0, 7, 1, 1);
		gridbagAdd(xTelNo, 2, 7, 1, 1);
		
		gridbagAdd(vAddr, 0, 8, 1, 1);
		gridbagAdd(xAddr, 2, 8, 1, 1);
		
		gridbagAdd(vVisitDate, 0, 9, 1, 1);
		gridbagAdd(chooser, 2, 9, 1, 1);
//		gridbagAdd(imageButton, 4, 9, 1, 1);
		
		gridbagAdd(vVisitCont, 0, 10, 1, 1);
		gridbagAdd(xVisitCont, 2, 10, 10, 1);
		
		gridbagAdd(confirm, 3, 11, 1, 1);
		gridbagAdd(cancel, 4, 11, 1, 1);
		
	
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
	}
	
	private void ChangeFont(JComponent[] comps, Font font) {
    	for(JComponent comp: comps) {
    		comp.setFont(font);
    	}
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
	
    class VisitDetailButtonListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource().equals(confirm)) {	
				
			}
			else if(e.getSource().equals(cancel)) {
				dispose();
				
			}
		}
		
	}
	
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		new VisitDetail();

	}

}
