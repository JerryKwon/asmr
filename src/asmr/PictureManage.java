package asmr;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
	
	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;
	
	public PictureManage() throws IOException {
		
		gridBagLayout = new GridBagLayout();		
		gridBagConstraints = new GridBagConstraints();
		
		vPictureManage = new JLabel("사진관리");
		
		vPicturePath = new JLabel("사진경로");
		xPicturePath = new JTextField(10);
		xPicturePath.setEnabled(false);
		pictureSearch = new JButton("찾아보기");
		
		ePictureList = new JTable(model1);
		pictureListScroll = new JScrollPane(ePictureList);
		pictureListScroll.setPreferredSize(new Dimension(100,150));
		
		add = new JButton(">>");
		remove = new JButton("<<");
		
		vSelectedPicture = new JLabel("선택된 사진");
		
		eSelectedPictureList = new JTable(model1);
		selectedPictureListScroll = new JScrollPane(eSelectedPictureList);
		selectedPictureListScroll.setPreferredSize(new Dimension(100,150));
		
		vPreview = new JLabel("미리보기");
		imageLabel = new JLabel();
		
		register = new JButton("등록");
		cancel = new JButton("취소");
		
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
		gridbagAdd(xPicturePath, 1, 1, 1, 1);
		gridbagAdd(pictureSearch, 2, 1, 1, 1);
		
		gridbagAdd(pictureListScroll, 1, 3, 1, 3);
		
		gridbagAdd(vSelectedPicture, 3, 2, 1, 1);
		
		gridbagAdd(add, 2, 4, 1, 1);
		gridbagAdd(remove, 2, 5, 1, 1);
		
		gridbagAdd(selectedPictureListScroll, 3, 3, 1, 3);
		
		gridbagAdd(vPreview, 4, 2, 1, 1);
		imageLabel.setSize(new Dimension(200,200));
		ImageIcon icon = new ImageIcon(ImageIO.read(new File("images/dog_100_100.jpg")));
		imageLabel.setIcon(icon);
		imageLabel.setSize(new Dimension(100,100));
		
		gridbagAdd(imageLabel, 4, 3, 3, 3);
		
		Component[] cops = {register,cancel};
		CombinePanel buttonPanel = new CombinePanel(cops, true);
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
		public CombinePanel(Component[] cops, boolean isBorder) {
			//Margin이 필요하지 않을 때
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
	
	public static void main(String[] args) throws IOException {
		new PictureManage();
	}
}
