package asmr;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class MainPage extends JPanel{

	private Image iDog, iCat, iEtc;
	private JLabel vMainBanner, vMainProtect, iMainDog, iMainCat, iMainEtc, vMainDog, vMainCat, vMainEtc,
				   vNowCenter, vNotice, vAnnc, vinfectBanner, vGood, vReport, vAdop;
	private JTextArea xNowCenter;
	private JScrollPane sNowCenter, sNotice;
	
	private JTable eNotice;
	private final String[] col = {"제목", "작성일시"};
	private DefaultTableModel model = new DefaultTableModel(col, 0);
	
	List<Map<String, Serializable>> notiListData;
	
	GridBagLayout gridbaglayout;				// 화면을 구성하는 레이아웃
	GridBagConstraints gridbagconstraints;	
	
	public MainPage() {
		
		gridbaglayout = new GridBagLayout();		
		gridbagconstraints = new GridBagConstraints();
		
		vMainBanner = new JLabel(new ImageIcon("images/mainbanner.jpg"));
		
		Image infect = resize(new ImageIcon("images/inf_banner.jpg"), 600, 785);
		vinfectBanner = new JLabel(new ImageIcon(infect));
		
		Image good = resize(new ImageIcon("images/good.jpg"), 450, 250);
		vGood = new JLabel(new ImageIcon(good));
		
		Image report = resize(new ImageIcon("images/report.png"), 650, 300);
		vReport = new JLabel(new ImageIcon(report));
		
//		vMainProtect = new JLabel("현재 보호중인 유기동물");
//		vMainProtect.setFont(new Font("나눔고딕", Font.PLAIN, 20));
//		vMainProtect.setBorder(new EmptyBorder(0,0,0,250));
//		iDog = resize(new ImageIcon("images/dog1.png"), 70, 70);
//		iMainDog = new JLabel(new ImageIcon(iDog));
//		vMainDog = new JLabel("521");
//		vMainDog.setFont(new Font("나눔고딕", Font.BOLD, 24));
//		
//		iCat = resize(new ImageIcon("images/cat1.png"), 70, 70);
//		iMainCat = new JLabel(new ImageIcon(iCat));
//		vMainCat = new JLabel("322");
//		vMainCat.setFont(new Font("나눔고딕", Font.BOLD, 24));
//		
//		iEtc = resize(new ImageIcon("images/bird1.png"), 70, 70);
//		iMainEtc = new JLabel(new ImageIcon(iEtc));
//		vMainEtc = new JLabel("62");
//		vMainEtc.setFont(new Font("나눔고딕", Font.BOLD, 24));
//		
//		vNowCenter = new JLabel("지금 센터에서는");
//		vNowCenter.setFont(new Font("나눔고딕", Font.PLAIN, 20));
//		vNowCenter.setBorder(new EmptyBorder(0,0,10,0));
//		xNowCenter = new JTextArea();
//		xNowCenter.setText("[10:30] 서울서초센터 밍키 (고양이)가 등록되었습니다.\n"
//				+ "[11:31] 부산해운대센터 방울 (개)가 입양되었습니다.\n"
//				+ "[14:09] 대구달성센터 해피 (기타)가 반환되었습니다.");
//		sNowCenter = new JScrollPane(xNowCenter);
//		sNowCenter.setPreferredSize(new Dimension(400,150));
//		sNowCenter.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
//		sNowCenter.setEnabled(false);
		
		vNotice = new JLabel("공지사항");
		vNotice.setFont(new Font("나눔고딕", Font.BOLD, 20));
		eNotice = new JTable(model){
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
	    DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
	    dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		eNotice.getColumnModel().getColumn(0).setPreferredWidth(400);
		eNotice.getColumnModel().getColumn(0).setCellRenderer(dtcr);
		eNotice.getColumnModel().getColumn(1).setPreferredWidth(150);
		eNotice.getColumnModel().getColumn(1).setCellRenderer(dtcr);
		sNotice = new JScrollPane(eNotice);
		sNotice.setPreferredSize(new Dimension(550,140));
		sNotice.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
//		vAnnc = new JLabel("공고");
//		vAnnc.setFont(new Font("나눔고딕", Font.PLAIN, 20));
//		vAnnc.setBorder(new EmptyBorder(10,0,0,0));
		
//		try {
//			pAnnc = new Annc();
//		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		notiListData = MainPageData.getNotiList(5);
		getData();
		MainPageView();
	}
	private Image resize(ImageIcon i, int w, int h) {
		Image tmp = i.getImage();
		Image resized = tmp.getScaledInstance(w, h, Image.SCALE_SMOOTH);
		return resized;
	}
	private void MainPageView() {
		
		gridbagconstraints.anchor = GridBagConstraints.WEST;		
		gridbagconstraints.ipadx = 7;		
				
		gridbagconstraints.weightx=1.0;		
		gridbagconstraints.weighty=1.0;		
				
		setLayout(gridbaglayout);
		
		gridbagAdd(vNotice, 0, 0, 1, 1);
		gridbagAdd(sNotice, 0, 1, 1, 1);
//		gridbagAdd(vGood, 0, 1, 5, 1);
		gridbagAdd(vReport, 0, 2, 5, 1);
		gridbagAdd(vMainBanner, 0, 3, 5, 1);
		gridbagAdd(vinfectBanner, 11, 0, 1, 10);
		
//		gridbagAdd(vMainProtect, 0, 1, 6, 1);
//		gridbagAdd(iMainDog, 0, 2, 1, 1);
//		gridbagAdd(iMainCat, 1, 2, 1, 1);
//		gridbagAdd(iMainEtc, 2, 2, 1, 1);
//		gridbagAdd(vMainDog, 0, 3, 1, 1);
//		gridbagAdd(vMainCat, 1, 3, 1, 1);
//		gridbagAdd(vMainEtc, 2, 3, 1, 1);
//		gridbagAdd(vNowCenter, 4, 1, 3, 1);
//		gridbagAdd(sNowCenter, 4, 2, 3, 2);
//		gridbagAdd(vAnnc, 4, 5, 3, 1);
//		gridbagAdd(pAnnc, 4, 6, 3, 1);
	}
	private void getData(){
		for(int i=0; i < notiListData.size(); i++){
			model.addRow(new Object[] {
					notiListData.get(i).get("제목"),
					notiListData.get(i).get("작성일시"),
			});
		}
	}
	private void gridbagAdd(Component c, int x, int y, int w, int h) {			
		
		gridbagconstraints.gridx = x;		
		gridbagconstraints.gridy = y; 		
	      //가장 왼쪽 위 gridx, gridy값은 0 			
				
		gridbagconstraints.gridwidth  = w;	//넓이	
		gridbagconstraints.gridheight = h;	//높이	
	     			
	      			
	    gridbaglayout.setConstraints(c, gridbagconstraints); //컴포넌트를 컴포넌트 위치+크기 정보에 따라 GridBagLayout에 배치			
				
	   add(c);			
				
	   }

	public static void main(String[] args) {

	}

}
