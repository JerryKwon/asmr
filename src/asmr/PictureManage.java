package asmr;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class PictureManage extends JFrame{
	private JLabel vPictureManage, vPicturePath, vSelectedPicture, vPreview, imageLabel;
	private JTextField xPicturePath;
	private JButton pictureSearch, register, cancel, add, remove;
	
	private final String[] col1 = {"파일명"};
	
	private DefaultTableModel model1 = new DefaultTableModel(col1,0);
	
	private JTable ePictureList;
	private JScrollPane pictureListScroll;
	
	private JTable eSelectedPictureList;
	private JScrollPane selectedPictureListScroll;
	
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	
	PictureManageButtonListener pictureManageButtonListener;
	PictureManageFileMouseListener pictureManageFileMouseListener;
	PictureManageSeletedFileMouseListener pictureManageSeletedFileMouseListener;
	
	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;
	
	public PictureManage() throws IOException {
		
		gridBagLayout = new GridBagLayout();		
		gridBagConstraints = new GridBagConstraints();
		
		pictureManageButtonListener = new PictureManageButtonListener();
		pictureManageFileMouseListener = new PictureManageFileMouseListener();
		pictureManageSeletedFileMouseListener = new PictureManageSeletedFileMouseListener();
		
		vPictureManage = new JLabel("사진관리");
		
		vPicturePath = new JLabel("사진경로");
		xPicturePath = new JTextField(10);
		xPicturePath.setEnabled(false);
		pictureSearch = new JButton("찾아보기");
		pictureSearch.setBackground(blue);
		pictureSearch.setForeground(white);
		pictureSearch.addActionListener(pictureManageButtonListener);
		
		ePictureList = new JTable(model1);
		ePictureList.addMouseListener(pictureManageFileMouseListener);
		pictureListScroll = new JScrollPane(ePictureList);
		pictureListScroll.setPreferredSize(new Dimension(100,150));
		
		add = new JButton(">>");
		add.addActionListener(pictureManageButtonListener);
		
		remove = new JButton("<<");
		remove.addActionListener(pictureManageButtonListener);
		
		vSelectedPicture = new JLabel("선택된 사진");
		
		eSelectedPictureList = new JTable(model1);
		eSelectedPictureList.addMouseListener(pictureManageSeletedFileMouseListener);
		selectedPictureListScroll = new JScrollPane(eSelectedPictureList);
		selectedPictureListScroll.setPreferredSize(new Dimension(100,150));
		
		vPreview = new JLabel("미리보기");
		
		File input = new File("images/dog_400_400.jpg");
		BufferedImage image = ImageIO.read(input);
		BufferedImage resized = resize(image,100,100);
		imageLabel = new JLabel();
		ImageIcon icon = new ImageIcon(resized);
		imageLabel.setIcon(icon);
		
		
		register = new JButton("등록");
		register.setBackground(blue);
		register.setForeground(white);
		register.addActionListener(pictureManageButtonListener);
		
		cancel = new JButton("취소");
		cancel.addActionListener(pictureManageButtonListener);
		
		PictureManageView();
	}
	
	private void PictureManageView() throws IOException {
		setLayout(gridBagLayout);
		
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.ipadx = 7;
		
		gridBagConstraints.weightx=1.0;
		gridBagConstraints.weighty=1.0;
		
		gridbagAdd(vPictureManage, 0, 0, 1, 1);
		
		gridbagAdd(vPicturePath, 0, 1, 1, 1);
		
		Component[] cops1 = {xPicturePath,pictureSearch};
		CombinePanel picturePathPanel = new CombinePanel(cops1, 5, 0);
		gridbagAdd(picturePathPanel, 1, 1, 2, 1);
		
		gridbagAdd(pictureListScroll, 1, 3, 1, 3);
		
		JPanel plainPanel = new JPanel();
		plainPanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
		plainPanel.add(vSelectedPicture);
		plainPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		gridbagAdd(plainPanel, 3, 2, 1, 1);
		
		gridbagAdd(add, 2, 4, 1, 1);
		gridbagAdd(remove, 2, 5, 1, 1);
		
		gridbagAdd(selectedPictureListScroll, 3, 3, 1, 3);
		
		JPanel plainPanel2 = new JPanel();
		plainPanel2.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
		plainPanel2.add(vPreview);
		plainPanel2.setBorder(BorderFactory.createEmptyBorder(0, 45, 0, 0));
		gridbagAdd(plainPanel2, 4, 2, 1, 1);
		
		JPanel plainPanel3 = new JPanel();
		plainPanel3.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
		plainPanel3.add(imageLabel);
		plainPanel3.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		gridbagAdd(plainPanel3, 4, 3, 3, 3);
		
		Component[] cops = {register,cancel};
		CombinePanel buttonPanel = new CombinePanel(cops,5,0);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 175, 0, 0));
		gridbagAdd(buttonPanel, 0, 6, 7, 1);
		
		
		pack();
		setResizable(false);
		setVisible(true);		
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
		//컴포넌트 1, 컴포넌트 2, 패널 구성시 좌,우 margin 공간을 없애기 위한 Flag
		public CombinePanel(Component[] cops, int borderWidth, int borderHeight) {
			//Margin이 필요하지 않을 때
			
			setLayout(new FlowLayout(FlowLayout.LEFT,borderWidth,borderHeight));
			
			for (Component c: cops) {
				add(c);
			}
		}
	}
	
	//사진크기 변환 함수입니다.
	//https://memorynotfound.com/java-resize-image-fixed-width-height-example/
    private static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }
	
    class PictureManageButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource().equals(pictureSearch)) {
				
			}
			else if (e.getSource().equals(add)){
				
			}
			else if (e.getSource().equals(remove)){
				
			}
			else if (e.getSource().equals(register)){
				
			}
			else if (e.getSource().equals(cancel)){
				dispose();
			}
		}
    	
    }
    
    class PictureManageFileMouseListener extends MouseAdapter{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			super.mouseClicked(e);
			if(e.getButton()==1) {
				
			}
		}
    	
    }
    
    class PictureManageSeletedFileMouseListener extends MouseAdapter{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			super.mouseClicked(e);
			if(e.getButton()==1) {
				
			}
		}
    	
    }
    
	public static void main(String[] args) throws IOException {
		new PictureManage();
	}
}
