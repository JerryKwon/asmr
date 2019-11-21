package asmr;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

	private JLabel vMainBanner, vNotice, vinfectBanner, vGood, vReport, vAdop;
	private JScrollPane sNotice;
	
	private JTable eNotice;
	private final String[] col = {"제목", "작성일시"};
	private DefaultTableModel model = new DefaultTableModel(col, 0);
	
	MainMouseListener mainMouseListener;
	List<Map<String, Serializable>> notiListData;
	
	GridBagLayout gridbaglayout;				// 화면을 구성하는 레이아웃
	GridBagConstraints gridbagconstraints;	
	
	public MainPage() {
		
		gridbaglayout = new GridBagLayout();		
		gridbagconstraints = new GridBagConstraints();
		
		mainMouseListener = new MainMouseListener();
		
		Image mainb = resize(new ImageIcon("images/mainbanner.jpg"), 650, 250);
		vMainBanner = new JLabel(new ImageIcon(mainb));
		
		Image infect = resize(new ImageIcon("images/inf_banner.jpg"), 600, 785);
		vinfectBanner = new JLabel(new ImageIcon(infect));
		
		Image good = resize(new ImageIcon("images/good.jpg"), 450, 250);
		vGood = new JLabel(new ImageIcon(good));
		
		Image report = resize(new ImageIcon("images/report.png"), 650, 300);
		vReport = new JLabel(new ImageIcon(report));
		
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
		eNotice.addMouseListener(mainMouseListener);
		sNotice = new JScrollPane(eNotice);
		sNotice.setPreferredSize(new Dimension(550,140));
		sNotice.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
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
		gridbagAdd(vMainBanner, 0, 2, 5, 1);
		gridbagAdd(vReport, 0, 3, 5, 1);
		gridbagAdd(vinfectBanner, 11, 0, 1, 10);
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
	class MainMouseListener extends MouseAdapter{
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			super.mouseClicked(e);
			
//			https://blaseed.tistory.com/18			
			//1:좌클릭, 3:우클릭
			if(e.getButton() == 1) {
				int clickedRow = eNotice.getSelectedRow();
				String postNo = notiListData.get(clickedRow).get("번호").toString();
				MainFrame.mainNotiCase(postNo);
			}
		}
	}

	public static void main(String[] args) {

	}

}
