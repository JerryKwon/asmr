package asmr;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AnncDetailPopup extends JFrame{
	
	private JLabel vProtCntrName, vAnncRegis, vAbanName, vAnmlKinds, vKind, vSex, vAge,vColor, vNeutWhet, vAnmlSize, vRscuDate, vRscuLoc, vExpln;   
	
	private JTextField xProtCntrName, xAbanName, xAnmlKinds, xKind, xSex, xAge, xColor, xNeutWhet, xAnmlSize, xRscuDate, xRscuLoc, xExpln;
	
	private JButton confirm, cancel;
	
	Map<String, Serializable> anncDtl;
	List<Map<String, Serializable>> picList;
	private ArrayList<String> picPath = new ArrayList<String>();
	
	GridBagLayout gridbagLayout;
	GridBagConstraints gridbagConstraints;
	
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	private Color black = new Color(0,0,0);
	
	private BufferedImage buttonIcon;
	private ImageIcon noImageIcon;
	
	AnncDetailPopupButtonListener anncDetailPopupButtonListener;
	
	private JButton previous, next, imageButton;
	private int pnt, picMax;
	
	public AnncDetailPopup(String abanNo) throws IOException {
		gridbagLayout = new GridBagLayout();
		gridbagConstraints = new GridBagConstraints();
		
		anncDetailPopupButtonListener = new AnncDetailPopupButtonListener();
		
		vAnncRegis = new JLabel("상세정보");
		vAnncRegis.setFont(new Font("나눔고딕", Font.BOLD, 24));
		
		vProtCntrName = new JLabel("보호센터명");
		xProtCntrName = new JTextField(20);
		xProtCntrName.setEditable(false);

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
		
		vColor= new JLabel("색상");
		xColor = new JTextField(20);
		xColor.setEditable(false);
		
		vNeutWhet = new JLabel("중성화여부");
		xNeutWhet = new JTextField(20);
		xNeutWhet.setEditable(false);
		
		vAnmlSize = new JLabel("동물크기"); 
		xAnmlSize = new JTextField(20);
		xAnmlSize.setEditable(false);
		
		vRscuDate = new JLabel("구조일자");
		xRscuDate = new JTextField(20);
		xRscuDate.setEditable(false);
		
		vRscuLoc = new JLabel("구조장소");
		xRscuLoc = new JTextField(20);
		xRscuLoc.setEditable(false);
		
		vExpln= new JLabel("설명");   
		xExpln = new JTextField(20);
		xExpln.setEditable(false);
		
		confirm = new JButton("입양신청");
		confirm.setBackground(blue);
		confirm.setForeground(white);
		confirm.setFont(new Font("나눔고딕", Font.BOLD, 12));
		confirm.addActionListener(anncDetailPopupButtonListener);
		
		cancel = new JButton("취소");
		cancel.setBackground(white);
		cancel.setForeground(black);
		cancel.setFont(new Font("나눔고딕", Font.BOLD, 12));
		cancel.addActionListener(anncDetailPopupButtonListener);
		
		previous = new JButton("<<");
		previous.addActionListener(anncDetailPopupButtonListener);
		previous.setFont(new Font("나눔고딕", Font.BOLD, 12));
		
		next = new JButton(">>");
		next.addActionListener(anncDetailPopupButtonListener);
		next.setFont(new Font("나눔고딕", Font.BOLD, 12));
		
		buttonIcon = ImageIO.read(new File("./images/cat1.png"));
		imageButton = new JButton(new ImageIcon(buttonIcon));
		imageButton.setBorderPainted(false);
		imageButton.setContentAreaFilled(false);
		imageButton.setFocusPainted(false);
		
		File input = new File("images/NoImage.png");
	    BufferedImage image = ImageIO.read(input);
	    BufferedImage resized = resize(image,200,200);
	    noImageIcon = new ImageIcon(resized);
		
		JComponent[] vComps = {vProtCntrName, vAbanName, vAnmlKinds, vKind, vSex, vAge, vColor, vNeutWhet,
				vAnmlSize, vRscuDate,vRscuLoc, vExpln};
		ChangeFont(vComps, new Font("나눔고딕", Font.PLAIN, 16));
		
		anncDtl = AnncData.getAnncDetail(abanNo);
		picList = AnncData.getAbanPicPath(abanNo);
		
		for(int i=0; i<picList.size(); i++){
			picPath.add(picList.get(i).get("경로").toString());
		}
		pnt = 0;
		picMax = picPath.size();
		setData();
		
		AnncDetailPopupView();
		
	}
	
	private void AnncDetailPopupView() {
		this.getContentPane().setBackground(MainFrame.bgc);
		setTitle("상세정보");
		
		gridbagConstraints.insets = new Insets(5,5,5,5);
		
		gridbagConstraints.anchor = GridBagConstraints.WEST;
		gridbagConstraints.ipadx = 7;

		gridbagConstraints.weightx=1.0;
		gridbagConstraints.weighty=1.0;

		setLayout(gridbagLayout);
		
		gridbagAdd(vAnncRegis, 0, 0, 1, 1);
		
		gridbagAdd(vProtCntrName, 0, 1, 1, 1);
		gridbagAdd(xProtCntrName, 2, 1, 1, 1);
		gridbagAdd(vAbanName, 4, 1, 1, 1);
		gridbagAdd(xAbanName, 6, 1, 1, 1);
		
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
		
		gridbagAdd(vAnmlSize, 0, 5, 1, 1);
		gridbagAdd(xAnmlSize, 2, 5, 1, 1);
		
		gridbagAdd(vRscuDate, 0, 6, 1, 1);
		gridbagAdd(xRscuDate, 2, 6, 1, 1);
		gridbagAdd(vRscuLoc, 4, 6, 1, 1);
		gridbagAdd(xRscuLoc, 6, 6, 1, 1);
		
		gridbagAdd(vExpln, 0, 7, 1, 1);
		gridbagAdd(xExpln, 2, 7, 1, 1);
		
		gridbagAdd(imageButton, 3,8,1,3);
		
		gridbagAdd(previous,3, 11, 1, 1);
		gridbagAdd(next, 4, 11, 1, 1);
		
		gridbagAdd(confirm,3, 12, 1, 1);
		gridbagAdd(cancel, 4, 12, 1, 1);
		
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	private void setData(){
		xProtCntrName.setText(anncDtl.get("센터명").toString());
		xAbanName.setText(anncDtl.get("동물명").toString());
		xAnmlKinds.setText(anncDtl.get("동물종류").toString());
		xKind.setText(anncDtl.get("품종").toString());
		xSex.setText(anncDtl.get("성별").toString());
		xAge.setText(anncDtl.get("나이").toString());
		xColor.setText(anncDtl.get("색상").toString());
		xNeutWhet.setText(anncDtl.get("중성화여부").toString());
		xAnmlSize.setText(anncDtl.get("동물크기").toString());		
		String rscuDt = null;
		try {
			
			rscuDt = anncDtl.get("구조일시").toString();
			
		}catch(NullPointerException e) {
			rscuDt = "";
		}
		xRscuDate.setText(rscuDt);
		
		String rscuLoc = null;
		try {
			
			rscuLoc = anncDtl.get("구조장소").toString();
			
		}catch(NullPointerException e) {
			rscuLoc = "";
		}
		xRscuLoc.setText(rscuLoc);
		
		xExpln.setText(anncDtl.get("특징").toString());
		try {
			File input = new File(picPath.get(pnt));
			BufferedImage image = ImageIO.read(input);
			BufferedImage resized = resize(image,200,200);
			ImageIcon icon = new ImageIcon(resized);
			imageButton.setIcon(icon);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			imageButton.setIcon(noImageIcon);
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
	
	class AnncDetailPopupButtonListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource().equals(previous)) {
				if(pnt > 0) {
					pnt -= 1;
					try {
						File input = new File(picPath.get(pnt));
						BufferedImage image = ImageIO.read(input);
						BufferedImage resized = resize(image,200,200);
						ImageIcon icon = new ImageIcon(resized);
						imageButton.setIcon(icon);
					} catch(IOException e1) {
						e1.printStackTrace();
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "첫번째 사진입니다.", "경고", JOptionPane.WARNING_MESSAGE);
				}
			}
			else if(e.getSource().equals(next)) {
				if(pnt < picMax-1) {
					pnt += 1;
					try {
						File input = new File(picPath.get(pnt));
						BufferedImage image = ImageIO.read(input);
						BufferedImage resized = resize(image,200,200);
						ImageIcon icon = new ImageIcon(resized);
						imageButton.setIcon(icon);
					} catch(IOException e1) {
						e1.printStackTrace();
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "마지막 사진입니다.", "경고", JOptionPane.WARNING_MESSAGE);
				}
			}
			else if(e.getSource().equals(cancel)) {
				dispose();
			}
		}
		
	}
	
    private void ChangeFont(JComponent[] comps, Font font) {
    	for(JComponent comp: comps) {
    		comp.setFont(font);
    	}
    }
    private static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		new AnncDetailPopup("2019112201");

	}

}
