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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

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
import javax.swing.table.DefaultTableModel;

public class NewPictureManage extends JFrame{
	private JLabel vPictureManage, vPicturePath, vSelectedPicture, vPreview, imageLabel;
	private JTextField xPicturePath;
	private JButton pictureSearch, register, cancel, add, remove;
	
	private String abanNo;
	private ArrayList<String> paths;
	private ArrayList<String> prevPaths;
	
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "asmr";
	private String password = "asmr";
	
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private ResultSetMetaData rsmd = null;
	
	private final String[] col1 = {"���ϸ�"};
	
	private DefaultTableModel model1 = new DefaultTableModel(col1,0);
	private DefaultTableModel model2 = new DefaultTableModel(col1,0);
	
	private JTable ePictureList;
	private JScrollPane pictureListScroll;
	
	private JTable eSelectedPictureList;
	private JScrollPane selectedPictureListScroll;
	
	private JFileChooser fc;
	
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	private Color red = new Color(217,0,27);
	
	private String path,fileName;
	private BufferedImage noImage;
	
	
	PictureManageButtonListener pictureManageButtonListener;
	PictureManageFileMouseListener pictureManageFileMouseListener;
	PictureManageSeletedFileMouseListener pictureManageSeletedFileMouseListener;
	
	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;
	
	public NewPictureManage(String abanNo) throws IOException {
		
		gridBagLayout = new GridBagLayout();		
		gridBagConstraints = new GridBagConstraints();
		
		pictureManageButtonListener = new PictureManageButtonListener();
		pictureManageFileMouseListener = new PictureManageFileMouseListener();
		pictureManageSeletedFileMouseListener = new PictureManageSeletedFileMouseListener();
		
		this.abanNo = abanNo;
		paths = new ArrayList<String>();
		prevPaths = new ArrayList<String>();
		
		vPictureManage = new JLabel("��������");
		
		vPicturePath = new JLabel("�������");
		vPicturePath.setFont(new Font("��������", Font.PLAIN, 16));
		xPicturePath = new JTextField(10);
		xPicturePath.setEnabled(false);
		pictureSearch = new JButton("ã�ƺ���");
		pictureSearch.setBackground(blue);
		pictureSearch.setForeground(white);
		pictureSearch.addActionListener(pictureManageButtonListener);
		
		fc = new JFileChooser();
		
		fc.setMultiSelectionEnabled(false);
		
		
//		ePictureList = new JTable(model1);
		//���̺� �� Ȱ��ȭ
		//https://stackoverflow.com/questions/9919230/disable-user-edit-in-jtable
//		ePictureList = new JTable(model1) {
//	        private static final long serialVersionUID = 1L;
//
//	        public boolean isCellEditable(int row, int column) {                
//	                return false;               
//	        };
//	    };
//		ePictureList.addMouseListener(pictureManageFileMouseListener);
//
//		pictureListScroll = new JScrollPane(ePictureList);
//		pictureListScroll.setPreferredSize(new Dimension(200,150));
		
//		add = new JButton(">>");
//		add.addActionListener(pictureManageButtonListener);
		
		remove = new JButton("����");
		remove.addActionListener(pictureManageButtonListener);
		remove.setBackground(red);
		remove.setForeground(white);
		
		vSelectedPicture = new JLabel("���õ� ����");
		
//		eSelectedPictureList = new JTable(model2);
		eSelectedPictureList = new JTable(model2) {
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };

		eSelectedPictureList.addMouseListener(pictureManageSeletedFileMouseListener);
		selectedPictureListScroll = new JScrollPane(eSelectedPictureList);
		selectedPictureListScroll.setPreferredSize(new Dimension(400,150));
		
		vPreview = new JLabel("�̸�����");
		
		File input = new File("images/NoImage.png");
		BufferedImage image = ImageIO.read(input);
		noImage = resize(image,100,100);
		imageLabel = new JLabel();
		ImageIcon icon = new ImageIcon(noImage);
		imageLabel.setIcon(icon);
		
		
		register = new JButton("���");
		register.setBackground(blue);
		register.setForeground(white);
		register.addActionListener(pictureManageButtonListener);
		
		cancel = new JButton("���");
		cancel.addActionListener(pictureManageButtonListener);
		
		JComponent[] vComps = {vSelectedPicture,vPreview};
		ChangeFont(vComps, new Font("��������", Font.PLAIN, 16));
		
		JComponent[] bComps = {pictureSearch, register, cancel, remove};
		ChangeFont(bComps, new Font("��������", Font.BOLD, 12));
		
		GetAbanPics(abanNo);
		
		prevPaths = paths;
		
		PictureManageView();
	}
	
	private void PictureManageView() throws IOException {
		setLayout(gridBagLayout);
		setTitle("���⵿����������");
		
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.ipadx = 7;
		
		gridBagConstraints.weightx=1.0;
		gridBagConstraints.weighty=1.0;
		
//		gridbagAdd(vPictureManage, 0, 0, 1, 1);
		
		gridbagAdd(vPicturePath, 0, 1, 1, 1);
		
		Component[] cops1 = {xPicturePath,pictureSearch};
		CombinePanel picturePathPanel = new CombinePanel(cops1, 5, 0);
		gridbagAdd(picturePathPanel, 1, 1, 2, 1);
		
//		gridbagAdd(pictureListScroll, 1, 3, 1, 3);
		
		JPanel plainPanel = new JPanel();
		plainPanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
		plainPanel.add(vSelectedPicture);
		plainPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		gridbagAdd(plainPanel, 1, 2, 1, 1);
		
//		gridbagAdd(add, 2, 4, 1, 1);
//		gridbagAdd(remove, 2, 5, 1, 1);
		
		gridbagAdd(selectedPictureListScroll, 1, 3, 1, 3);
		
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
		
		Component[] cops = {register,remove,cancel};
		CombinePanel buttonPanel = new CombinePanel(cops,5,0);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 230, 0, 0));
		gridbagAdd(buttonPanel, 0, 6, 7, 1);
		
		pack();
		setLocationRelativeTo(null);
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
		//������Ʈ 1, ������Ʈ 2, �г� ������ ��,�� margin ������ ���ֱ� ���� Flag
		public CombinePanel(Component[] cops, int borderWidth, int borderHeight) {
			//Margin�� �ʿ����� ���� ��
			
			setLayout(new FlowLayout(FlowLayout.LEFT,borderWidth,borderHeight));
			
			for (Component c: cops) {
				add(c);
			}
		}
	}
	
	//����ũ�� ��ȯ �Լ��Դϴ�.
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
		                    "������ �������� �ʾҽ��ϴ�.", "���", 
		                    JOptionPane.WARNING_MESSAGE);
		            return;
		        }
		        
		        // ���� �������� ���
		        path = fc.getSelectedFile().getPath();
		        //pack();
		        xPicturePath.setText(path);
		        
		        DefaultTableModel model = (DefaultTableModel)eSelectedPictureList.getModel();
//		        fileName = path.substring( path.lastIndexOf('\\')+1, path.length());
		        fileName = path;
		        paths.add(fileName);
		        model.addRow(new Object[] {fileName});
					
			}
//			else if (e.getSource().equals(add)){
//				DefaultTableModel model = (DefaultTableModel)eSelectedPictureList.getModel();
//				int column = 0;
//				int sRow = ePictureList.getSelectedRow();
//				String value = ePictureList.getModel().getValueAt(sRow, column).toString();
//				paths.add(value);
//				model.addRow(new Object[] {value});
//			}
			else if (e.getSource().equals(remove)){
				DefaultTableModel model = (DefaultTableModel)eSelectedPictureList.getModel();
				int column = 0;
				int sRow = eSelectedPictureList.getSelectedRow();
				paths.remove(sRow);
				model.removeRow(sRow);
				ImageIcon icon = new ImageIcon(noImage);
				imageLabel.setIcon(icon);
			}
			else if (e.getSource().equals(register)){
				if(eSelectedPictureList.getRowCount() == 0) {
					JOptionPane.showMessageDialog(null, 
		                    "������ ������� �ʽ��ϴ�", "�ȳ�", 
		                    JOptionPane.QUESTION_MESSAGE);
					dispose();
					}
				else {
					int result = JOptionPane.showConfirmDialog(null, "������ ����Ͻðڽ��ϱ�?","Confirm",JOptionPane.YES_NO_OPTION);
					if(result == JOptionPane.YES_OPTION) {
						JOptionPane.showMessageDialog(null, "��ϵǾ����ϴ�.","��� Ȯ��",JOptionPane.PLAIN_MESSAGE);
						//SQL ���� �ʿ�
						dispose();
					}
				}
			}
			else if (e.getSource().equals(cancel)){
				paths = prevPaths;
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
    
    private void GetAbanPics(String abanNo) {
    	connection();
    	
    	try {
    		
			StringBuffer query= new StringBuffer("SELECT ANML_PIC_ORNU,PATH FROM ABAN_PIC ");
			query.append("WHERE ABAN_NO = '"+abanNo+"' ");
			query.append("ORDER BY 1 ");
			
			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				
				paths.add(rs.getString("PATH"));
				model2.addRow(new Object[] {rs.getString("PATH")});
			}	
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	
    	disconnection();
    }
	
	// �����ͺ��̽� ����

    public void connection() {

             try {

                      Class.forName("oracle.jdbc.driver.OracleDriver");

                      con = DriverManager.getConnection(url,user,password);


             } catch (ClassNotFoundException e) {
            	 e.printStackTrace();
             } catch (SQLException e) {
            	 e.printStackTrace();
             }

    }

    // �����ͺ��̽� ���� ����
    public void disconnection() {

        try {

                 if(pstmt != null) pstmt.close();

                 if(rs != null) rs.close();

                 if(con != null) con.close();

        } catch (SQLException e) {
        	e.printStackTrace();
        }

    }
    
    public ArrayList<String> getPaths() {
		return paths;
	}

	private void ChangeFont(JComponent[] comps, Font font) {
    	for(JComponent comp: comps) {
    		comp.setFont(font);
    	}
    }
    
	public static void main(String[] args) throws IOException {
		new NewPictureManage(null);
	}
}