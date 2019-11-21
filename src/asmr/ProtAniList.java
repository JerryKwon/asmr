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
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

public class ProtAniList extends JPanel {
	private JLabel vProtAniRegister, vProtAniInfo, vAbanAniNo, vAbanAniType, vRescueNo, vAbanAniName, vAge, vParAniName, vAniType, vKind, vSex, vNeutWhet, vColor, vAniSize, vRegisDate, vDescription, vDscvDate, vCage, vDscvPlace;
	private JTextField xAbanAniNo, xAbanAniType, xRescueNo, xAbanAniName, xAge, xParAniName, xAniType ,xKind, xColor, xRegisDate, xDscvDate, xDscvPlace;
	private JComboBox<String> cbSex, cbNeutWhet, cbAniSize, cbCage;
	private JButton register,modify, cancel, returning;
	private JTextArea xDescription;

	private JTable eProtAniList;
	private JScrollPane aniListScroll;
	
	private JScrollPane descriptionScroll;
	
	private ArrayList<String> cntrNos;
	private ArrayList<String> abanNos;
	private ArrayList<String> abanTps;
	private ArrayList<String> abanPics;
	private ArrayList<String> newPicPaths;
	
	private String parAbanAniNo = null;
	
	private String userCntrNo;
	private String userEmpNo;
	private String userCntrType;
	private String userWorkType;
	
	private int picMax;
	private int pnt;
	private boolean isClicked = false;
	
	private ImageIcon noImageIcon;
	
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "asmr";
	private String password = "asmr";
	
	private String prevSex = null;
	private String prevNeut = null;
	private String prevSize = null;
	private String prevDesc = null;
	private String prevCage = null;
	private ArrayList<String> prevPics = null;
	
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private ResultSetMetaData rsmd = null;
	
	private final String[] col1 = {"유기동물명","동물종류","품종","나이(개월)","크기","설명"};
	
	private DefaultTableModel model1 = new DefaultTableModel(col1,0);
	
	private final String[] sexDiv = {"수컷","암컷","미상"};
	private final String[] cbNeutWhetDiv = {"Y","N"};
	private final String[] aniSizeDiv = {"대","중","소"};
	private final String[] cageDiv = {"케이지1(대)","케이지4(중)","케이지9(소)"};
	
	private JLabel imageLabel;
	private JButton previous, next, pictureManage;
	
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	private Color red = new Color(217,0,27);
	
	ProtAniListMouseListener protAniListMouseListener;
	ProtAniListButtonListener protAniListButtonListener;
	
	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;
	
	public ProtAniList(String cntrNo, String empNo) throws IOException {
		
		gridBagLayout = new GridBagLayout();		
		gridBagConstraints = new GridBagConstraints();
		
		protAniListMouseListener = new ProtAniListMouseListener();
		protAniListButtonListener = new ProtAniListButtonListener();
		
		cntrNos = new ArrayList<String>();
		abanNos = new ArrayList<String>();
		abanTps = new ArrayList<String>();
		abanPics = new ArrayList<String>();
		newPicPaths = new ArrayList<String>();
		
		userCntrNo = cntrNo;
		userEmpNo = empNo;
		
		userCntrType = GetCntrType(userCntrNo);
		userWorkType = GetWorkType(userCntrNo,userEmpNo); 
		
		vProtAniRegister = new JLabel("보호동물목록");
		vProtAniRegister.setFont(new Font("나눔고딕", Font.BOLD, 24));
		
		register = new JButton("등록");
		register.setBackground(blue);
		register.setForeground(white);
		register.addActionListener(protAniListButtonListener);
		
//		eProtAniList = new JTable(model1);
		eProtAniList = new JTable(model1) {
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
		eProtAniList.getTableHeader().setReorderingAllowed(false);
		eProtAniList.getTableHeader().setResizingAllowed(false);
		eProtAniList.addMouseListener(protAniListMouseListener);
		aniListScroll = new JScrollPane(eProtAniList);
		aniListScroll.setPreferredSize(new Dimension(1300,200));
		eProtAniList.getColumnModel().getColumn(0).setPreferredWidth(100);
		eProtAniList.getColumnModel().getColumn(1).setPreferredWidth(75);
		eProtAniList.getColumnModel().getColumn(2).setPreferredWidth(150);
		eProtAniList.getColumnModel().getColumn(3).setPreferredWidth(75);
		eProtAniList.getColumnModel().getColumn(4).setPreferredWidth(50);
		eProtAniList.getColumnModel().getColumn(5).setPreferredWidth(900);
		
		
		
		vProtAniInfo = new JLabel("보호동물정보");
		vProtAniInfo.setFont(new Font("나눔고딕", Font.BOLD, 20));
		
		vAbanAniNo = new JLabel("유기동물번호");
		xAbanAniNo = new JTextField(12);
		xAbanAniNo.setEditable(false);
		
		vAbanAniType = new JLabel("유기동물구분");
		xAbanAniType = new JTextField(12);
		xAbanAniType.setEditable(false);
		
//		vRescueNo = new JLabel("구조번호");
//		xRescueNo = new JTextField(12);
//		xRescueNo.setEditable(false);
		
		vAbanAniName = new JLabel("유기동물명");
		xAbanAniName = new JTextField(12);
		xAbanAniName.setEditable(false);
		
		vAge = new JLabel("나이(개월)");
		xAge = new JTextField(12);
		xAge.setEditable(false);
		
		vParAniName = new JLabel("어미유기동물명");
		xParAniName = new JTextField(12);
		xParAniName.setEditable(false);
		
		vAniType = new JLabel("동물종류");
		xAniType = new JTextField(12);
		xAniType.setEditable(false);
		
		vKind = new JLabel("품종");
		xKind = new JTextField(12);
		xKind.setEditable(false);
		
		vSex = new JLabel("성별");
		cbSex = new JComboBox<String>(sexDiv);
		cbSex.setEnabled(false);
		
		vNeutWhet = new JLabel("중성화여부");
		cbNeutWhet = new JComboBox<String>(cbNeutWhetDiv);
		cbNeutWhet.setEnabled(false);
		
		vColor = new JLabel("색상");
		xColor = new JTextField(12);
		xColor.setEditable(false);
		
		vAniSize = new JLabel("동물크기");
		cbAniSize = new JComboBox<String>(aniSizeDiv);
		cbAniSize.setEnabled(false);
		
		vRegisDate = new JLabel("등록일자");
		xRegisDate = new JTextField(12);
		xRegisDate.setEditable(false);
		
		vDescription = new JLabel("비고");
		xDescription = new JTextArea();
		xDescription.setLineWrap(true);
		xDescription.setEditable(false);
		descriptionScroll = new JScrollPane(xDescription);
		descriptionScroll.setPreferredSize(new Dimension(400,150));
		descriptionScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		vDscvDate = new JLabel("발견일시");
		xDscvDate = new JTextField(12);
		xDscvDate.setEditable(false);
		
		vCage = new JLabel("케이지");
		cbCage = new JComboBox<String>();
		cbCage.setEnabled(false);
		
		vDscvPlace = new JLabel("발견장소");
		xDscvPlace = new JTextField(12);
		xDscvPlace.setEditable(false);
		
		modify = new JButton("수정");
		modify.setBackground(blue);
		modify.setForeground(white);
		modify.addActionListener(protAniListButtonListener);
		
		cancel = new JButton("취소");
		cancel.addActionListener(protAniListButtonListener);
		
		returning = new JButton("반환");
		returning.setBackground(red);
		returning.setForeground(white);
		returning.addActionListener(protAniListButtonListener);
		
		pictureManage = new JButton("사진관리");
		pictureManage.setEnabled(false);
		pictureManage.setBackground(blue);
		pictureManage.setForeground(white);
		pictureManage.addActionListener(protAniListButtonListener);
		
		
		File input = new File("images/NoImage.png");
		BufferedImage image = ImageIO.read(input);
		BufferedImage resized = resize(image,200,200);
		imageLabel = new JLabel();
		noImageIcon = new ImageIcon(resized);
		imageLabel.setIcon(noImageIcon);
		
		previous = new JButton("<<");
		previous.addActionListener(protAniListButtonListener);
		next = new JButton(">>");
		next.addActionListener(protAniListButtonListener);
		
		JComponent[] fontComps1 = {vAbanAniNo, vAbanAniType, vAbanAniName, vAge, vParAniName, vAniType, vKind, vSex, vNeutWhet, vColor, vAniSize, vRegisDate, vDescription, vDscvDate, vCage, vDscvPlace};
		ChangeFont(fontComps1, new Font("나눔고딕", Font.PLAIN, 16));

		JComponent[] fontComps2 = {register,modify,cancel,returning,pictureManage};
		ChangeFont(fontComps2, new Font("나눔고딕", Font.BOLD, 16));

		GetProtAniList();
		
		ProtAniListView();
	}
	
	private void ProtAniListView() throws IOException {
		setLayout(gridBagLayout);
		setBackground(MainFrame.bgc);
		
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.ipadx = 7;
		
		gridBagConstraints.weightx=1.0;
		gridBagConstraints.weighty=1.0;
		
		gridBagConstraints.insets = new Insets(3, 3, 3, 3);
		
		gridbagAdd(vProtAniRegister, 0, 0, 1, 1);
		
		JPanel registerPanel = new JPanel();
		registerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT,0,0));
		registerPanel.add(register);
		registerPanel.setBackground(MainFrame.bgc);
		registerPanel.setBorder(BorderFactory.createEmptyBorder(0,300,0,0));
		gridbagAdd(registerPanel, 9, 0, 1, 1);
		
		JPanel aniListPanel = new JPanel();
		aniListPanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
		aniListPanel.add(aniListScroll);
		aniListPanel.setBackground(MainFrame.bgc);
		aniListPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,0));
		gridbagAdd(aniListPanel, 0, 1, 10, 1);
		
		vProtAniInfo.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
		gridbagAdd(vProtAniInfo, 0, 2, 1, 1);
		
		gridbagAdd(vAbanAniNo, 0, 3, 1, 1);
		gridbagAdd(xAbanAniNo, 1, 3, 1, 1);
		
		gridbagAdd(vAbanAniType, 2, 3, 1, 1);
		gridbagAdd(xAbanAniType, 3, 3, 1, 1);
		
//		gridbagAdd(vRescueNo, 4, 3, 1, 1);
//		gridbagAdd(xRescueNo, 5, 3, 1, 1);
		
		gridbagAdd(vAbanAniName, 0, 4, 1, 1);
		gridbagAdd(xAbanAniName, 1, 4, 1, 1);
		
		gridbagAdd(vAge, 2, 4, 1, 1);
		gridbagAdd(xAge, 3, 4, 1, 1);
		
		gridbagAdd(vParAniName, 4, 3, 1, 1);
		gridbagAdd(xParAniName, 5, 3, 1, 1);
		
		gridbagAdd(vAniType, 0, 5, 1, 1);
		gridbagAdd(xAniType, 1, 5, 1, 1);
		
		gridbagAdd(vKind, 2, 5, 1, 1);
		gridbagAdd(xKind, 3, 5, 1, 1);
		
		gridbagAdd(vSex, 0, 6, 1, 1);
		gridbagAdd(cbSex, 1, 6, 1, 1);
		
		gridbagAdd(vNeutWhet, 2, 6, 1, 1);
		gridbagAdd(cbNeutWhet, 3, 6, 1, 1);
		
		gridbagAdd(vColor, 0, 7, 1, 1);
		gridbagAdd(xColor, 1, 7, 1, 1);
		
		gridbagAdd(vAniSize, 2, 7, 1, 1);
		gridbagAdd(cbAniSize, 3, 7, 1, 1);

		JPanel manButtonPanel = new JPanel();
		manButtonPanel.add(pictureManage);
		manButtonPanel.setBackground(MainFrame.bgc);
		manButtonPanel.setBorder(BorderFactory.createEmptyBorder(0,100,0,0));
		gridbagAdd(manButtonPanel, 5, 7, 1, 1);
		
		gridbagAdd(imageLabel, 5, 5, 1, 8);
		
		gridbagAdd(vRegisDate, 0, 8, 1, 1);
		gridbagAdd(xRegisDate, 1, 8, 1, 1);
		
		gridbagAdd(vDescription, 0, 9, 1, 1);
		gridbagAdd(descriptionScroll, 1, 9, 4, 1);
		
		gridbagAdd(vDscvDate, 0, 10, 1, 1);
		gridbagAdd(xDscvDate, 1, 10, 1, 1);
		
		gridbagAdd(vCage, 2, 10, 1, 1);
		gridbagAdd(cbCage, 3, 10, 1, 1);
		
		gridbagAdd(vDscvPlace, 0, 11, 1, 1);
		gridbagAdd(xDscvPlace, 1, 11, 1, 1);
		
		Component[] cops1 = {modify, cancel, returning};
		CombinePanel buttonPanel = new CombinePanel(cops1,15,0);

		buttonPanel.setBackground(MainFrame.bgc);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(20,500,0,0));
		gridbagAdd(buttonPanel, 0, 12, 7, 1);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout(FlowLayout.LEFT,20,10));
		bottomPanel.add(previous);
		bottomPanel.add(next);
		bottomPanel.setBackground(MainFrame.bgc);
		bottomPanel.setBorder(BorderFactory.createEmptyBorder(0,25,0,0));
		gridbagAdd(bottomPanel, 5, 11 ,3, 1);
	
	}
	
	private void gridbagAdd(Component c, int x, int y, int w , int h) {
		
		gridBagConstraints.gridx = x;
		gridBagConstraints.gridy = y;
		gridBagConstraints.gridwidth = w;
		gridBagConstraints.gridheight = h;
		
		gridBagLayout.setConstraints(c, gridBagConstraints);
		
		add(c);
	}
	
	class ProtAniListButtonListener implements ActionListener{


//		File input = new File(abanPics.get(0));
//		BufferedImage image = ImageIO.read(input);
//		BufferedImage resized = resize(image,200,200);
//		ImageIcon icon = new ImageIcon(resized);
//		imageLabel.setIcon(icon);
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource().equals(register)){
				if(userWorkType.equals("p")) {
					ProtAniRegist protAniRegist = new ProtAniRegist(userCntrNo);
					protAniRegist.addWindowListener(new WindowAdapter() {
	
						@Override
						public void windowClosed(WindowEvent e) {
							// TODO Auto-generated method stub
							super.windowClosed(e);
							GetProtAniList();
							clearAll();
						}
					
					});
				}
				else {
					JOptionPane.showMessageDialog(null, "보호관리직원만 유기동물을 등록할 수 있습니다.", "안내", JOptionPane.WARNING_MESSAGE);
				}
			}
			else if(e.getSource().equals(pictureManage)) {
				try {
					// 0001 => ABAN_NO
					String abanNo = xAbanAniNo.getText();
					NewPictureManage newPictureManage = new NewPictureManage(abanNo);
					newPictureManage.addWindowListener(new WindowAdapter() {

						@Override
						public void windowClosed(WindowEvent e) {
							// TODO Auto-generated method stub
							super.windowClosed(e);
							abanPics = newPictureManage.getPaths();
							picMax = abanPics.size();
							try {
								File input = new File(abanPics.get(0));
								BufferedImage image = ImageIO.read(input);
								BufferedImage resized = resize(image,200,200);
								ImageIcon icon = new ImageIcon(resized);
								imageLabel.setIcon(icon);
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								imageLabel.setIcon(noImageIcon);
							}
						}
					
					});
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if(e.getSource().equals(previous)) {
				if(pnt > 0) {
					pnt -= 1;
					try {
						File input = new File(abanPics.get(pnt));
						BufferedImage image = ImageIO.read(input);
						BufferedImage resized = resize(image,200,200);
						ImageIcon icon = new ImageIcon(resized);
						imageLabel.setIcon(icon);
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
						File input = new File(abanPics.get(pnt));
						BufferedImage image = ImageIO.read(input);
						BufferedImage resized = resize(image,200,200);
						ImageIcon icon = new ImageIcon(resized);
						imageLabel.setIcon(icon);
					} catch(IOException e1) {
						e1.printStackTrace();
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "마지막 사진입니다.", "경고", JOptionPane.WARNING_MESSAGE);
				}
			}
			else if(e.getSource().equals(modify)) {
				
	//				int clickedRow = -1;
					
				if(eProtAniList.getSelectedRow()!=-1) {
			
					//권한 일반 보호센터의 센터장, 사무직종사자, 보호관리직원
					if(userCntrType.equals("n")&&(userWorkType.equals("c")||userWorkType.equals("o")||userWorkType.equals("p"))) {
					
						String newSex = null;
						String newNeut = null;
						String newSize = null;
						String newDesc = null;
						String newCage = null;
						ArrayList<String> newPics = new ArrayList<String>();
						
						if(!isClicked) {
							
							isClicked = true;
							
							
							prevSex = (String)cbSex.getSelectedItem();
							prevNeut = (String)cbNeutWhet.getSelectedItem();
							prevSize = (String)cbAniSize.getSelectedItem();
							prevDesc = xDescription.getText();
							prevCage = (String)cbCage.getSelectedItem();
							prevPics = abanPics;
							
							
							modify.setText("확인");
							JComponent[] changeStatusComps = {cbSex,cbNeutWhet,cbAniSize,cbCage,pictureManage};
							for(JComponent cop: changeStatusComps) {
								cop.setEnabled(true);
							}
							
							xDescription.setEditable(true);
						}
						else {
							isClicked = false;
							
							int result = JOptionPane.showConfirmDialog(null, "보호동물정보를 수정하시겠습니까?", "보호동물정보수정", JOptionPane.YES_NO_OPTION);
							if(result == JOptionPane.OK_OPTION) {
								newSex = (String)cbSex.getSelectedItem();
								newNeut = (String)cbNeutWhet.getSelectedItem();
								newSize = (String)cbAniSize.getSelectedItem();
								newDesc = xDescription.getText();
								newCage = (String)cbCage.getSelectedItem();
								newPics = abanPics; 
								
								if(prevSex.equals(newSex)&&prevNeut.equals(newNeut)&&prevSize.equals(newSize)&&prevDesc.equals(newDesc)&&prevCage.equals(newCage)&&prevPics.equals(newPics)) {
									JOptionPane.showMessageDialog(null, "변경된정보가 없습니다.", "알림", JOptionPane.WARNING_MESSAGE);
									clearAll();
								}
								//나머지 보호동물 정보는 모두 업데이트 된다고 가정을 하고
								else {
									int clickedRow = eProtAniList.getSelectedRow();
									
									if(!prevPics.equals(newPics)&&!prevCage.equals(newCage)) {
										String abanNo = abanNos.get(clickedRow);
										String cntrNo = cntrNos.get(clickedRow);
										UpdateAban(abanNo,cntrNo,newSex,newNeut,newSize,newDesc,newCage,newPics,prevPics.size(),newPics.size());
									}
									//케이지는 같은 경우라서 - 케이지 변경 필요 X
									else if(!prevPics.equals(newPics)&&prevCage.equals(newCage)) {
										String abanNo = abanNos.get(clickedRow);
										String cntrNo = cntrNos.get(clickedRow);
										UpdateAban(abanNo,cntrNo,newSex,newNeut,newSize,newDesc,null,newPics,prevPics.size(),newPics.size());
									}
									//사진은 같은 경우라서 - 사진 변경 필요 X
									else if(prevPics.equals(newPics)&&!prevCage.equals(newCage)) {
										String abanNo = abanNos.get(clickedRow);
										String cntrNo = cntrNos.get(clickedRow);
										UpdateAban(abanNo,cntrNo,newSex,newNeut,newSize,newDesc,newCage,null,prevPics.size(),newPics.size());
									}
									else if(prevPics.equals(newPics)&&prevCage.equals(newCage)) {
										String abanNo = abanNos.get(clickedRow);
										String cntrNo = cntrNos.get(clickedRow);
										UpdateAban(abanNo,cntrNo,newSex,newNeut,newSize,newDesc,null,null,prevPics.size(),newPics.size());
									}
									clearAll();
									GetProtAniList();
								}
							}
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "동물정보수정 권한이 없습니다.", "안내", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
			else if(e.getSource().equals(cancel)) {
				clearAll();
			}
			else if(e.getSource().equals(returning)) {
				
				if(eProtAniList.getSelectedRow()!=-1) {
					if(userCntrType.equals("n")&&(userWorkType.equals("o")||userWorkType.equals("p"))) {
					
						int clickedRow = eProtAniList.getSelectedRow();
						String abanNo = abanNos.get(clickedRow);
						ReqPrsnRegist reqPrsnRegist = new ReqPrsnRegist(abanNo);
						reqPrsnRegist.addWindowListener(new WindowAdapter() {
		
							@Override
							public void windowClosed(WindowEvent e) {
								// TODO Auto-generated method stub
								super.windowClosed(e);
								GetProtAniList();
								clearAll();
							}
						});			
					}
					else {
						JOptionPane.showMessageDialog(null, "동물정보반환 권한이 없습니다.", "안내", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		}
		
	}

	private void UpdateAban(String abanNo,String cntrNo,String newSex,String newNeut,String newSize,String newDesc,String newCage, ArrayList<String> newPics, int prevPicNum,int newPicNum) {
		
		String engSex = null;
		String engSize = null;
		
		switch(newSex) {
		case "수컷":
			engSex="m";
			break;
		case "암컷":
			engSex="f";
			break;
		case "미상":
			engSex="u";
			break;
		}
		
		switch(newSize) {
		case "대":
			engSize="b";
			break;
		case "중":
			engSize="m";
			break;
		case "소":
			engSize="s";
			break;
		}
		
		//Aban변경쿼리
		StringBuffer query1 = new StringBuffer();
		//케이지변경쿼리 => 현재 PROT END_DATE 변경하고, 새로운 PROT 추가해줘야함
		StringBuffer query2 = new StringBuffer();
		StringBuffer query3 = new StringBuffer();
		
		//사진변경쿼리
		StringBuffer query4 = new StringBuffer();
		StringBuffer query5 = new StringBuffer();
		
		//사진, 케이지, 정보 모두 변경
		if(newCage!=null && newPics!=null) {
//			query1.append("UPDATE ABAN SET SEX=?, NEUT_WHET=?, ANML_SIZE=?, FEAT=? WHERE ABAN_NO=? ");
		
			
			query2.append("UPDATE PROT SET PROT_END_DATE=trunc(sysdate) WHERE ABAN_NO='"+abanNo+"' ");
		
			String cageOrnu = newCage.replaceAll("[^0-9]", "");
			
			query3.append("INSERT INTO PROT(PROT_NO,PROT_START_DATE,ABAN_NO,CNTR_NO,CAGE_ORNU,CAMA_EMP_NO) ");
			query3.append("SELECT ");
			query3.append("	CASE WHEN SUBSTR(PROT_NO,1,8) = to_char(TRUNC(SYSDATE),'yyyymmdd') ");
			query3.append(" THEN to_char(TRUNC(SYSDATE),'yyyymmdd') || CASE WHEN SUBSTR(PROT_NO,10,1) = '9' THEN to_char(SUBSTR(PROT_NO,9,1)+1) ELSE SUBSTR(PROT_NO,9,1) END || CASE WHEN SUBSTR(PROT_NO,10,1)='9' THEN '0' ELSE to_char(SUBSTR(PROT_NO,10,1)+1) END ");
			query3.append(" ELSE to_char(TRUNC(SYSDATE),'yyyymmdd') || '01' END PROT_NO, ");
			query3.append("	TRUNC(SYSDATE) PROT_START_DATE, ");
			query3.append("	'"+abanNo+"' ABAN_NO, ");
			query3.append("	'"+cntrNo+"' CNTR_NO, ");
			query3.append("	'"+cageOrnu+"' CAGE_ORNU, ");
			query3.append("	(SELECT /*+INDEX_ASC(EMP_WORK_HIST EMP_WORK_HIST_PK) */ EMP_NO FROM EMP_WORK_HIST ");
			query3.append("	WHERE CNTR_NO = '"+cntrNo+"' ");
			query3.append("	AND BIZ_FILD='p' ");
			query3.append("	AND ROWNUM=1) CAMA_EMP_NO ");
			query3.append("FROM(SELECT NVL(PROT_NO,0) PROT_NO ");
			query3.append("	FROM(SELECT /*+INDEX_DESC(PROT PROT_PK) */ MAX(PROT_NO) PROT_NO ");
			query3.append("		FROM PROT) ");
			query3.append(") ");
			
			if(prevPicNum>0) {
				query4.append("DELETE FROM ABAN_PIC WHERE ABAN_NO='"+abanNo+"' ");
			}
			
			if(newPicNum > 0) {
				query5.append("INSERT INTO ABAN_PIC(ABAN_NO,ANML_PIC_ORNU,PATH) ");
				query5.append("SELECT '"+abanNo+"', ABAN_NO, ROWNUM ANML_PIC_ORNU, PATH PATH ");
				query5.append("FROM( ");
				for(int i=0;i<newPics.size()-1;i++) {
					query5.append("SELECT '"+newPics.get(i)+"' path FROM DUAL ");
					query5.append("UNION ALL ");
				}
				query5.append("SELECT '"+newPics.get(newPics.size()-1)+"' path FROM DUAL ");
				query5.append(") ");
			}
		}
		//케이지 변경 필요 X
		else if(newCage==null && newPics!=null) {
//			query1.append("UPDATE ABAN SET SEX=?, NEUT_WHET=?, ANML_SIZE=?, FEAT=? WHERE ABAN_NO=? ");
		
			
			if(prevPicNum>0) {
				query4.append("DELETE FROM ABAN_PIC WHERE ABAN_NO='"+abanNo+"' ");
			}
			if(newPicNum > 0) {
				query5.append("INSERT INTO ABAN_PIC(ABAN_NO,ANML_PIC_ORNU,PATH) ");
				query5.append("SELECT '"+abanNo+"' ABAN_NO, ROWNUM ANML_PIC_ORNU, PATH PATH ");
				query5.append("FROM( ");
				for(int i=0;i<newPics.size()-1;i++) {
					query5.append("SELECT '"+newPics.get(i)+"' path FROM DUAL ");
					query5.append("UNION ALL ");
				}
				query5.append("SELECT '"+newPics.get(newPics.size()-1)+"' path FROM DUAL ");
				query5.append(") ");
			}
		}//사진 변경 X
		else if(newCage!=null && newPics==null) {
//			query1.append("UPDATE ABAN SET SEX=?, NEUT_WHET=?, ANML_SIZE=?, FEAT=? WHERE ABAN_NO=? ");
		
			String cageOrnu = newCage.replaceAll("[^0-9]", "");
			query2.append("UPDATE PROT SET PROT_END_DATE=trunc(sysdate) WHERE ABAN_NO='"+abanNo+"' ");
		
			query3.append("INSERT INTO PROT(PROT_NO,PROT_START_DATE,ABAN_NO,CNTR_NO,CAGE_ORNU,CAMA_EMP_NO) ");
			query3.append("SELECT ");
			query3.append("	CASE WHEN SUBSTR(PROT_NO,1,8) = to_char(TRUNC(SYSDATE),'yyyymmdd') ");
			query3.append(" THEN to_char(TRUNC(SYSDATE),'yyyymmdd') || CASE WHEN SUBSTR(PROT_NO,10,1) = '9' THEN to_char(SUBSTR(PROT_NO,9,1)+1) ELSE SUBSTR(PROT_NO,9,1) END || CASE WHEN SUBSTR(PROT_NO,10,1)='9' THEN '0' ELSE to_char(SUBSTR(PROT_NO,10,1)+1) END ");
			query3.append(" ELSE to_char(TRUNC(SYSDATE),'yyyymmdd') || '01' END PROT_NO, ");
			query3.append("	TRUNC(SYSDATE) PROT_START_DATE, ");
			query3.append("	'"+abanNo+"' ABAN_NO, ");
			query3.append("	'"+cntrNo+"' CNTR_NO, ");
			query3.append("	'"+cageOrnu+"' CAGE_ORNU, ");
			query3.append("	(SELECT /*+INDEX_ASC(EMP_WORK_HIST EMP_WORK_HIST_PK) */ EMP_NO FROM EMP_WORK_HIST ");
			query3.append("	WHERE CNTR_NO = '"+cntrNo+"' ");
			query3.append("	AND BIZ_FILD='p' ");
			query3.append("	AND ROWNUM=1) CAMA_EMP_NO ");
			query3.append("FROM(SELECT NVL(PROT_NO,0) PROT_NO ");
			query3.append("	FROM(SELECT /*+INDEX_DESC(PROT PROT_PK) */ MAX(PROT_NO) PROT_NO ");
			query3.append("		FROM PROT) ");
			query3.append(") ");
			
			
		}
		//케이지,사진 변경 필요 X [동물정보만]
		else if(newCage==null && newPics==null) {
//			query1.append("UPDATE ABAN SET SEX=?, NEUT_WHET=?, ANML_SIZE=?, FEAT=? WHERE ABAN_NO=? ");
		}
	
		connection();
		
		try {
			query1.append("UPDATE ABAN SET SEX=?, NEUT_WHET=?, ANML_SIZE=?, FEAT=? WHERE ABAN_NO=? ");
			pstmt = con.prepareStatement(query1.toString());
			pstmt.setString(1, engSex);
			pstmt.setString(2, newNeut);
			pstmt.setString(3, engSize);
			pstmt.setString(4, newDesc);
			pstmt.setString(5, abanNo);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				con.commit();
			}
			
			if(query2.length() != 0) {
			
				pstmt = con.prepareStatement(query2.toString());
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					con.commit();
				}
			}
			
			if(query3.length() != 0) {
				pstmt = con.prepareStatement(query3.toString());
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					con.commit();
				}
			}
			
			if(query4.length() != 0) {
				pstmt = con.prepareStatement(query4.toString());
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					con.commit();
				}
			}
			
			if(query5.length() != 0) {
				pstmt = con.prepareStatement(query5.toString());
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					con.commit();
				}
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		disconnection();
		
	}
	
	private void clearAll() {
		
		isClicked = false;
		pnt = 0;
		picMax = 0;
		
		eProtAniList.getSelectionModel().clearSelection();
		imageLabel.setIcon(noImageIcon);
		
		JTextComponent[] jcomps = {xAbanAniNo,xAbanAniType,xAbanAniName,xAge,xParAniName,xAniType,xKind,xColor,xDescription,xRegisDate,xDscvDate,xDscvPlace};
		
		for(JTextComponent jcomp:jcomps) {
			jcomp.setText("");
		}
		
		cbSex.setSelectedItem("수컷");
		cbNeutWhet.setSelectedItem("Y");
		cbAniSize.setSelectedItem("대");
		
		cbCage.removeAllItems();
		
		modify.setText("수정");
		JComponent[] changeStatusComps = {cbSex,cbNeutWhet,cbAniSize,cbCage,pictureManage};
		for(JComponent cop: changeStatusComps) {
			cop.setEnabled(false);
		}
		xDescription.setEditable(false);
	}
	
	private String GetCntrType(String cntrNo) {
		String result = null;
		
		String query = "select cntr_tp from cntr where cntr_no='"+cntrNo+"' ";
		
		connection();
		
		try {
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				result = rs.getString("CNTR_TP");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		disconnection();
		
		return result;
	}
	
	private String GetWorkType(String cntrNo, String empNo) {
		String result = null;
		
		StringBuffer query = new StringBuffer("SELECT /*+INDEX_DESC(EMP_WORK_HIST EMP_WORK_HIST_PK)*/ BIZ_FILD ");
		query.append("FROM emp_work_hist ");
		query.append("WHERE emp_no='"+empNo+"' AND cntr_no='"+cntrNo+"' AND work_end_date=to_date('9999-12-31','YYYY-MM-DD') AND ROWNUM =1 ");
		
		connection();
		
		try {
			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				result = rs.getString("BIZ_FILD");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		disconnection();
		
		return result;
	}
	
	//두개의 컴포넌트를 하나의 패널로 묶는 JPanel
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
	
    private void ChangeFont(JComponent[] comps, Font font) {
    	for(JComponent comp: comps) {
    		comp.setFont(font);
    	}
    }
    
	class ProtAniListMouseListener extends MouseAdapter{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			super.mouseClicked(e);
			if(e.getButton()==1) {
				cbCage.removeAllItems();
				GetProtAni();
				if(!parAbanAniNo.isEmpty()) {
					//부모 이름 찾아서 텍스트필드에 넣어주기
					GetParAbanAniName();
				}
			}
		}
		
	}
	
	private void GetParAbanAniName() {
		
		StringBuffer query = new StringBuffer("SELECT ABAN_NAME FROM ABAN WHERE ABAN_NO='"+parAbanAniNo+"' ");
		
		connection();
		
		try {
			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				xParAniName.setText(rs.getString("ABAN_NAME"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		disconnection();
	}
	
	private void GetProtAni() {
		int clickedRow = eProtAniList.getSelectedRow();
		String cntrNo = cntrNos.get(clickedRow);
		String abanNo = abanNos.get(clickedRow);
		String abanTp = abanTps.get(clickedRow);
		
		StringBuffer query1 = new StringBuffer();
		
		if(abanTp.equals("r")) {
			query1.append("SELECT a.ABAN_NO,a.ABAN_TP, rs.RSCU_NO, a.ABAN_NAME, a.AGE, a.MOM_ABAN_NO, a.ANML_KINDS, a.KIND, a.SEX, a.NEUT_WHET, a.COLOR, a.ANML_SIZE, a.REGIS_DATE, a.FEAT, rp.DSCV_DTTM, rp.DSCV_LOC ");
			query1.append("FROM (SELECT RSCU_NO FROM RSCU) rs INNER JOIN (SELECT * FROM ABAN WHERE ABAN_NO='"+abanNo+"') a ");
			query1.append(" ON rs.rscu_no = a.rscu_no INNER JOIN (SELECT * FROM ASSG WHERE ASSG_RES = 'a') a2 ");
			query1.append(" ON a2.assg_no = rs.rscu_no INNER JOIN RPRT rp ON rp.rprt_no = a2.rprt_no ");
			
			parAbanAniNo = "";
		}
		else if(abanTp.equals("b")) {
			query1.append("SELECT a.ABAN_NO,a.ABAN_TP, a.ABAN_NAME, a.AGE, a.MOM_ABAN_NO, a.ANML_KINDS, a.KIND, a.SEX, a.NEUT_WHET, a.COLOR, a.ANML_SIZE, a.REGIS_DATE, a.FEAT FROM ABAN a WHERE ABAN_NO='"+abanNo+"' ");
		}
		
		StringBuffer query2 = new StringBuffer("SELECT '케이지'||c.CAGE_ORNU||'('||CASE c.CAGE_SIZE WHEN 's' THEN '소' WHEN 'm' THEN '중' WHEN 'b' THEN '대' END||')' cages ");
		query2.append("FROM (SELECT * ");
		query2.append("	FROM CAGE ");
		query2.append("	WHERE CNTR_NO = '"+cntrNo+"') c INNER JOIN (SELECT * ");
		query2.append("FROM( ");
		query2.append("SELECT CAGE_ORNU FROM CAGE ");
		query2.append("WHERE CNTR_NO ='"+cntrNo+"' ");
		query2.append("MINUS ");
		query2.append("SELECT CAGE_ORNU FROM PROT ");
		query2.append("WHERE CNTR_NO='"+cntrNo+"' ");
		query2.append("AND PROT_END_DATE=to_date('9999-12-31','YYYY-MM-DD') ");
		query2.append("UNION ALL ");
		query2.append("SELECT c.CAGE_ORNU ");
		query2.append("FROM CAGE c INNER JOIN (SELECT * FROM PROT ");
		query2.append("	WHERE ABAN_NO ='"+abanNo+"' ");
		query2.append("	AND PROT_END_DATE = to_date('9999-12-31','YYYY-MM-DD')) p ");
		query2.append("	ON c.cntr_no = p.cntr_no AND c.cage_ornu = p.cage_ornu) ");
		query2.append("ORDER BY 1 ");
		query2.append(") c2 ON c.CAGE_ORNU = c2.CAGE_ORNU ");
		
		StringBuffer query3 = new StringBuffer("SELECT '케이지'||c.CAGE_ORNU||'('||CASE c.CAGE_SIZE WHEN 's' THEN '소' WHEN 'm' THEN '중' WHEN 'b' THEN '대' END||')' cages ");
		query3.append("FROM CAGE c INNER JOIN (SELECT * FROM PROT ");
		query3.append("	WHERE ABAN_NO ='"+abanNo+"' ");
		query3.append("	AND PROT_END_DATE = to_date('9999-12-31','YYYY-MM-DD')) p ");
		query3.append("	ON c.cntr_no = p.cntr_no AND c.cage_ornu = p.cage_ornu ");
		
		StringBuffer query4 = new StringBuffer("SELECT PATH FROM ABAN_PIC ");
		query4.append("WHERE ABAN_NO='"+abanNo+"'");
		
		connection();
		
		try {
			//query1
			pstmt = con.prepareStatement(query1.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				
				String abanType = rs.getString("ABAN_TP");
				String anmlKinds = rs.getString("ANML_KINDS");
				String sex = rs.getString("SEX");
				String anmlSize = rs.getString("ANML_SIZE");
				
				String korAbanType = null;
				String korAnmlKinds = null;
				String korSex = null;
				String korAnmlSize = null;
				
				switch(abanType) {
				case "r":
					korAbanType="구조";
					break;
				case "b":
					korAbanType="탄생";
					break;
				}
				
				switch(anmlKinds) {
				case "d":
					korAnmlKinds="개";
					break;
				case "c":
					korAnmlKinds="고양이";
					break;
				case "e":
					korAnmlKinds="기타";
					break;
				}
				
				switch(sex) {
				case "m":
					korSex="수컷";
					break;
				case "f":
					korSex="암컷";
					break;
				case "u":
					korSex="미상";
					break;
				}
				
				switch(anmlSize) {
				case "b":
					korAnmlSize="대";
					break;
				case "m":
					korAnmlSize="중";
					break;
				case "s":
					korAnmlSize="소";
					break;
				}
				
				if(abanTp.equals("r")) {
					
					xDscvDate.setText(rs.getString("DSCV_DTTM"));
					xDscvPlace.setText(rs.getString("DSCV_LOC"));
					
				}
				
				if(abanTp.equals("b")) {
					parAbanAniNo = rs.getString("MOM_ABAN_NO");
				}
				
				xAbanAniNo.setText(rs.getString("ABAN_NO"));
				xAbanAniType.setText(korAbanType);
				xAbanAniName.setText(rs.getString("ABAN_NAME"));
//				xRescueNo.setText(rs.getString("RSCU_NO"));
				xAge.setText(rs.getString("AGE"));
				
//				xParAniName.setText(rs.getString("MOM_ABAN_NO"));
				
				xAniType.setText(korAnmlKinds);
				xKind.setText(rs.getString("KIND"));
				cbSex.setSelectedItem(korSex);
				cbNeutWhet.setSelectedItem(rs.getString("NEUT_WHET"));
				xColor.setText(rs.getString("COLOR"));
				cbAniSize.setSelectedItem(korAnmlSize);
				xRegisDate.setText(rs.getString("REGIS_DATE").split(" ")[0]);
				xDescription.setText(rs.getString("FEAT"));
				
			}
			
			//query2
			pstmt = con.prepareStatement(query2.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				cbCage.addItem(rs.getString("cages"));
			}
			
			//query3
			pstmt = con.prepareStatement(query3.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				cbCage.setSelectedItem(rs.getString("cages"));
			}
			
			abanPics.clear();
			pstmt = con.prepareStatement(query4.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				abanPics.add(rs.getString("PATH"));
			}
			
			pnt = 0;
			picMax = abanPics.size();
			
//			String firstPic = null;
//			File input = null;
			try {
				
			String firstPic = abanPics.get(0);
			File input = new File(firstPic);
			BufferedImage image = ImageIO.read(input);
			BufferedImage resized = resize(image,200,200);
			ImageIcon icon = new ImageIcon(resized);
			imageLabel.setIcon(icon);
			
			}catch(Exception e) {
				imageLabel.setIcon(noImageIcon); 
				
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		disconnection();
		
	}
	
    private void GetProtAniList() {
    	cntrNos.clear();
    	abanNos.clear();
    	abanTps.clear();
    	model1.setNumRows(0);
    	
    	StringBuffer query = new StringBuffer();
    	
    	if(userCntrType.equals("h")) {
    	
	    	query.append("SELECT p.CNTR_NO, a.ABAN_NO, a.ABAN_TP, a.ABAN_NAME, a.ANML_KINDS, a.KIND, a.AGE, a.ANML_SIZE, a.FEAT ");
	    	query.append("FROM ABAN a INNER JOIN (SELECT * FROM PROT ");
	    	query.append("	WHERE PROT_END_DATE = to_date('9999-12-31','YYYY-MM-DD')) p ");
	    	query.append("	ON a.ABAN_NO = p.ABAN_NO ");
	    	query.append("ORDER BY 1,2 ");
	    	
    	}
    	
    	else if(userCntrType.equals("n")) {
    		
    		query.append("SELECT p.CNTR_NO, a.ABAN_NO, a.ABAN_TP, a.ABAN_NAME, a.ANML_KINDS, a.KIND, a.AGE, a.ANML_SIZE, a.FEAT ");
	    	query.append("FROM ABAN a INNER JOIN (SELECT * FROM PROT ");
	    	query.append("	WHERE PROT_END_DATE = to_date('9999-12-31','YYYY-MM-DD')) p ");
	    	query.append("	ON a.ABAN_NO = p.ABAN_NO AND p.CNTR_NO='"+userCntrNo+"' ");
	    	query.append("ORDER BY 1,2 ");
	    	
    	}
    	
    	connection();
    	
    	try {
    		
			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				
				cntrNos.add(rs.getString("CNTR_NO"));
				abanNos.add(rs.getString("ABAN_NO"));
				abanTps.add(rs.getString("ABAN_TP"));
				
				String anmlKinds = rs.getString("ANML_KINDS");
				String anmlSize = rs.getString("ANML_SIZE");
				
				String korAnmlKinds = null;
				String korAnmlSize = null;
				
				switch(anmlKinds) {
				case "d":
					korAnmlKinds="개";
					break;
				case "c":
					korAnmlKinds="고양이";
					break;
				case "e":
					korAnmlKinds="기타";
					break;
				}
				
				switch(anmlSize) {
				case "b":
					korAnmlSize="대";
					break;
				case "m":
					korAnmlSize="중";
					break;
				case "s":
					korAnmlSize="소";
					break;
				}
								
				model1.addRow(new Object[] {rs.getString("ABAN_NAME"),korAnmlKinds,rs.getString("KIND"),rs.getString("AGE"),korAnmlSize,rs.getString("FEAT")});
			}
    		
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	
    	disconnection();
    }
	
	
    // 데이터베이스 연결

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

    // 데이터베이스 연결 해제
    public void disconnection() {

        try {

                 if(pstmt != null) pstmt.close();

                 if(rs != null) rs.close();

                 if(con != null) con.close();

        } catch (SQLException e) {
        	e.printStackTrace();
        }

    }
	
	public static void main(String[] args) throws IOException {
		new ProtAniList(null,null);
	}
}
