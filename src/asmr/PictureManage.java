package asmr;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class PictureManage extends JFrame{
	private JLabel vPictureManage, vPicturePath, vSelectedPicture, vPreview, imageLabel;
	private JTextField xPicturePath;
	private JButton pictureSearch, register, cancel, add, remove;
	
	private final String[] col1 = {"파일명"};
	
	private DefaultTableModel model1 = new DefaultTableModel(col1,0);
	private DefaultTableModel model2 = new DefaultTableModel(col1,0);
	
	private JTable ePictureList;
	private JScrollPane pictureListScroll;
	
	private JTable eSelectedPictureList;
	private JScrollPane selectedPictureListScroll;
	
	private JFileChooser fc;
	
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	
	private String path,fileName;
	private BufferedImage noImage;
	
	
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
		vPicturePath.setFont(new Font("나눔고딕", Font.PLAIN, 16));
		xPicturePath = new JTextField(10);
		xPicturePath.setEnabled(false);
		pictureSearch = new JButton("찾아보기");
		pictureSearch.setBackground(blue);
		pictureSearch.setForeground(white);
		pictureSearch.addActionListener(pictureManageButtonListener);
		
		fc = new JFileChooser();
		
		fc.setMultiSelectionEnabled(true);
		
		
//		ePictureList = new JTable(model1);
		//테이블 비 활성화
		//https://stackoverflow.com/questions/9919230/disable-user-edit-in-jtable
		ePictureList = new JTable(model1) {
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
		ePictureList.addMouseListener(pictureManageFileMouseListener);

		pictureListScroll = new JScrollPane(ePictureList);
		pictureListScroll.setPreferredSize(new Dimension(200,150));
		
		add = new JButton(">>");
		add.addActionListener(pictureManageButtonListener);
		
		remove = new JButton("<<");
		remove.addActionListener(pictureManageButtonListener);
		
		vSelectedPicture = new JLabel("선택된 사진");
		
//		eSelectedPictureList = new JTable(model2);
		eSelectedPictureList = new JTable(model2) {
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };

		eSelectedPictureList.addMouseListener(pictureManageSeletedFileMouseListener);
		selectedPictureListScroll = new JScrollPane(eSelectedPictureList);
		selectedPictureListScroll.setPreferredSize(new Dimension(200,150));
		
		vPreview = new JLabel("미리보기");
		
		File input = new File("images/NoImage.png");
		BufferedImage image = ImageIO.read(input);
		noImage = resize(image,100,100);
		imageLabel = new JLabel();
		ImageIcon icon = new ImageIcon(noImage);
		imageLabel.setIcon(icon);
		
		
		register = new JButton("등록");
		register.setBackground(blue);
		register.setForeground(white);
		register.addActionListener(pictureManageButtonListener);
		
		cancel = new JButton("취소");
		cancel.addActionListener(pictureManageButtonListener);
		
		JComponent[] vComps = {vSelectedPicture,vPreview};
		ChangeFont(vComps, new Font("나눔고딕", Font.PLAIN, 16));
		
		JComponent[] bComps = {pictureSearch, register, cancel, add, remove};
		ChangeFont(bComps, new Font("나눔고딕", Font.BOLD, 12));
		
		PictureManageView();
	}
	
	private void PictureManageView() throws IOException {
		setLayout(gridBagLayout);
		setTitle("유기동물사진관리");
		
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.ipadx = 7;
		
		gridBagConstraints.weightx=1.0;
		gridBagConstraints.weighty=1.0;
		
//		gridbagAdd(vPictureManage, 0, 0, 1, 1);
		
		gridbagAdd(vPicturePath, 0, 1, 1, 1);
		
		Component[] cops1 = {xPicturePath,pictureSearch};
		CombinePanel picturePathPanel = new CombinePanel(cops1, 5, 0);
		gridbagAdd(picturePathPanel, 1, 1, 2, 1);
		
		gridbagAdd(pictureListScroll, 1, 3, 1, 3);
		
		JPanel plainPanel = new JPanel();
		plainPanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
		plainPanel.add(vSelectedPicture);
		plainPanel.setBorder(BorderFactory.createEmptyBorder(0, 65, 0, 0));
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
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 230, 0, 0));
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
				FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif", "png");
				
				fc.setFileFilter(filter);
				
				int result = fc.showOpenDialog(null);
				
				if (result != JFileChooser.APPROVE_OPTION) {
		            JOptionPane.showMessageDialog(null, 
		                    "파일을 선택하지 않았습니다.", "경고", 
		                    JOptionPane.WARNING_MESSAGE);
		            return;
		        }
		        
		        // 파일 선택했을 경우
		        path = fc.getSelectedFile().getPath();
		        //pack();
		        xPicturePath.setText(path);
		        
		        DefaultTableModel model = (DefaultTableModel)ePictureList.getModel();
//		        fileName = path.substring( path.lastIndexOf('\\')+1, path.length());
		        fileName = path;
		        model.addRow(new Object[] {fileName});
					
			}
			else if (e.getSource().equals(add)){
				DefaultTableModel model = (DefaultTableModel)eSelectedPictureList.getModel();
				int column = 0;
				int sRow = ePictureList.getSelectedRow();
				String value = ePictureList.getModel().getValueAt(sRow, column).toString();
				model.addRow(new Object[] {value});
			}
			else if (e.getSource().equals(remove)){
				DefaultTableModel model = (DefaultTableModel)eSelectedPictureList.getModel();
				int column = 0;
				int sRow = eSelectedPictureList.getSelectedRow();
				model.removeRow(sRow);
				ImageIcon icon = new ImageIcon(noImage);
				imageLabel.setIcon(icon);
			}
			else if (e.getSource().equals(register)){
				if(eSelectedPictureList.getRowCount() == 0) {
					JOptionPane.showMessageDialog(null, 
		                    "목록에 사진이 없습니다.", "경고", 
		                    JOptionPane.WARNING_MESSAGE);
				}
				else {
					int result = JOptionPane.showConfirmDialog(null, "사진을 등록하시겠습니까?","Confirm",JOptionPane.YES_NO_OPTION);
					if(result == JOptionPane.YES_OPTION) {
						JOptionPane.showMessageDialog(null, "등록되었습니다.","등록 확인",JOptionPane.PLAIN_MESSAGE);
						//SQL 동작 필요
						dispose();
					}
				}
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
			
				DefaultTableModel model = (DefaultTableModel)eSelectedPictureList.getModel();
				int column = 0;
				int sRow = eSelectedPictureList.getSelectedRow();
				String value = model.getValueAt(sRow, column).toString();	
				File input = new File(value);
				BufferedImage image = null;
				try {
					image = ImageIO.read(input);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				BufferedImage resized = resize(image,100,100);
				ImageIcon icon = new ImageIcon(resized);
				imageLabel.setIcon(icon);
			}
		}
    	
    }
    
    private void ChangeFont(JComponent[] comps, Font font) {
    	for(JComponent comp: comps) {
    		comp.setFont(font);
    	}
    }
    
	public static void main(String[] args) throws IOException {
		new PictureManage();
	}
}
