package asmr;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import asmr.CenterList.BottomPanel;

public class NewCenterRegistration extends JFrame{
	private JLabel vNewCenterRegist,vCenterName,vCenterType,vArea,vPhoneNum,vEstDate,vOperTime,vOperTimeDash,vCenterManager,vAddress,vCageNum,vCageBig,vCageMid,vCageSmall,vCageBigCount,vCageMidCount,vCageSmallCount;
	private JTextField xCenterName,xArea,xPhoneNum,xEstDate,xCenterManager,xAddress,xCageBig,xCageMid,xCageSmall;
	private JButton centerManagerSearch,addressSearch, register, cancel, imageButton;
	private JComboBox<String> cbCenterType,cbOperTimeOpen,cbOperTimeClose;
	private BufferedImage buttonIcon;
	private JDateChooser chooser;
	
	private String cntrManagerBdate = null;
	
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "asmr";
	private String password = "asmr";
	
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private ResultSetMetaData rsmd = null;
	
	private final String[] centerTypeDiv = {"본부","일반"};
	private final String[] operTimeOpenDiv = {"08:00","08:30","09:00","09:30","10:00","10:30","11:00"};
	private final String[] operTimeCloseDiv = {"16:00","16:30","17:00","17:30","18:00","18:30","19:00"};
	
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	
	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;
	
	NewCenterRegistButtonListener newCenterRegistButtonListener = new NewCenterRegistButtonListener();
	
	public NewCenterRegistration() throws IOException {
		//레이아웃 생성
		gridBagLayout = new GridBagLayout();
		gridBagConstraints = new GridBagConstraints();
		
		//화면명
//		vNewCenterRegist = new JLabel("신규센터등록");
		
		//센터명
		vCenterName = new JLabel("센터명");
		xCenterName = new JTextField(10);
		
		//센터구분
		vCenterType = new JLabel("센터구분");
		cbCenterType = new JComboBox<String>(centerTypeDiv);
		
		//면적
		vArea = new JLabel("면적");
		xArea = new JTextField(10);
		
		//전화번호
		vPhoneNum = new JLabel("전화번호");
		xPhoneNum = new JTextField(10);
		
		//설립일자
		vEstDate = new JLabel("설립일자");

		LocalDate now = LocalDate.now();
		Date date = Date.valueOf(now);
		chooser = new JDateChooser(date,"YYYY-MM-dd");
		
//		xEstDate = new JTextField(10); 
//		xEstDate.setEnabled(false);		
//		buttonIcon = ImageIO.read(new File("images/cal1.png"));
//		imageButton = new JButton(new ImageIcon(buttonIcon));
//		imageButton.setBorderPainted(false);
//		imageButton.setContentAreaFilled(false);
//		imageButton.setFocusPainted(false);
		
		//운영시간
		vOperTime = new JLabel("운영시간");
		cbOperTimeOpen = new JComboBox<String>(operTimeOpenDiv);
		vOperTimeDash = new JLabel("~");
		cbOperTimeClose = new JComboBox<String>(operTimeCloseDiv);
		
		//센터장
		vCenterManager = new JLabel("센터장");
		xCenterManager = new JTextField(10);
		xCenterManager.setEnabled(false);
		centerManagerSearch = new JButton("검색");
		centerManagerSearch.setBackground(blue);
		centerManagerSearch.setForeground(white);
		centerManagerSearch.addActionListener(newCenterRegistButtonListener);
		
		//주소
		vAddress = new JLabel("주소");
		xAddress = new JTextField(20);
		xAddress.setEditable(false);
		addressSearch = new JButton("검색");
		addressSearch.setBackground(blue);
		addressSearch.setForeground(white);
		addressSearch.addActionListener(newCenterRegistButtonListener);
		
		//총 케이지 수
		vCageNum = new JLabel("총 케이지 수");
		
		//대형
		vCageBig = new JLabel("대형");
		xCageBig = new JTextField(2);
		vCageBigCount = new JLabel("개");
		
		//중형
		vCageMid = new JLabel("중형");
		xCageMid = new JTextField(2);
		vCageMidCount = new JLabel("개");
		
		//소형
		vCageSmall = new JLabel("소형");
		xCageSmall = new JTextField(2);
		vCageSmallCount = new JLabel("개");
		
		//저장버튼
		register = new JButton("등록");
		register.setBackground(blue);
		register.setForeground(white);
		register.addActionListener(newCenterRegistButtonListener);
		
		cancel = new JButton("취소");
		cancel.addActionListener(newCenterRegistButtonListener);
		
		//배치함수
		NewCenterRegistrationView();
	}

	private void NewCenterRegistrationView() {
		setLayout(gridBagLayout);
		setTitle("신규센터등록");
		
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.ipadx = 7;
		
		gridBagConstraints.weightx=1.0;
		gridBagConstraints.weighty=1.0;
		
		//화면제목
//		gridbagAdd(vNewCenterRegist, 0, 0, 1, 1);
		
		//센터명
		gridbagAdd(vCenterName, 0, 1, 1, 1);
		gridbagAdd(xCenterName, 1, 1, 1, 1);
		
		//센터구분
		gridbagAdd(vCenterType, 11, 1, 1, 1);
		gridbagAdd(cbCenterType, 12, 1, 1, 1);
		
		//면적
		gridbagAdd(vArea, 0, 2, 1, 1);
		gridbagAdd(xArea, 1, 2, 1, 1);
		
		//전화번호
		gridbagAdd(vPhoneNum, 11, 2, 1, 1);
		gridbagAdd(xPhoneNum, 12, 2, 1, 1);
		
		//설립일자
		gridbagAdd(vEstDate, 0, 3, 1, 1);
//		JPanel plainPanel1 = new JPanel();
//		plainPanel1.setLayout(new FlowLayout(FlowLayout.LEFT,5,0));
//		plainPanel1.add(imageButton);
//		Component[] cops1 = {xEstDate, plainPanel1};
//		CombinePanel estDatePanel = new CombinePanel(cops1, 0, 0);
		
		gridbagAdd(chooser, 1, 3, 1, 1);
		
		//운영시간
		gridbagAdd(vOperTime, 11, 3, 1, 1);
		Component[] cops2 = {cbOperTimeOpen, vOperTimeDash, cbOperTimeClose};
		CombinePanel operTimePanel = new CombinePanel(cops2,0,0);
		gridbagAdd(operTimePanel, 12 ,3, 1, 1);
		
		//센터장	
		gridbagAdd(vCenterManager, 0, 4, 1, 1);
		JPanel plainPanel2 = new JPanel();
		plainPanel2.setLayout(new FlowLayout(FlowLayout.LEFT,5,0));
		plainPanel2.add(centerManagerSearch);
		Component[] cops3 = {xCenterManager, plainPanel2};
		CombinePanel centerManagerPanel = new CombinePanel(cops3,0,0);
		gridbagAdd(centerManagerPanel, 1, 4, 1, 1);
		
		
		//주소
		gridbagAdd(vAddress, 0, 5, 1, 1);
		gridbagAdd(xAddress, 1, 5, 1, 1);
		gridbagAdd(addressSearch, 2, 5, 1, 1);
		
		//총 케이지 수
		gridbagAdd(vCageNum, 0, 6, 1, 1);
		
		//형태별 케이지 나열을 위한 Bottom Panel 및 배치
		BottomPanel bottomPanel = new BottomPanel();
		gridbagAdd(bottomPanel, 0, 7, 3, 1);
		
		
		//저장버튼
		JComponent[] buttons = {register, cancel};
		CombinePanel buttonPanel = new CombinePanel(buttons, 10, 0);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(5,250,0,0));
		gridbagAdd(buttonPanel, 0, 10, 4, 1);
		
		
		JComponent[] vContextComps = {vCenterName,vCenterType,vArea,vPhoneNum,vEstDate,vOperTime,vOperTimeDash,vCenterManager,vAddress,vCageNum,vCageBig,vCageMid,vCageSmall,vCageBigCount,vCageMidCount,vCageSmallCount};
		ChangeFont(vContextComps, new Font("나눔고딕", Font.PLAIN, 16));
		
		JComponent[] bComps= {centerManagerSearch,addressSearch, register, cancel};
		ChangeFont(bComps, new Font("나눔고딕", Font.BOLD, 12));
		
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
	
	//케이지 형태별 구분을 나열을 위한 Bottom Panel 선언
	class BottomPanel extends JPanel{
		public BottomPanel() {
			setLayout(new FlowLayout(FlowLayout.LEFT));

			add(vCageBig);
			add(xCageBig);
			add(vCageBigCount);
			add(vCageMid);
			add(xCageMid);
			add(vCageMidCount);
			add(vCageSmall);
			add(xCageSmall);
			add(vCageSmallCount);
			
		}
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

	private void RegistNewCenter() {
		connection();
		
		//센터 등록 관련 데이터
		String centerName = xCenterName.getText();
		String addr = xAddress.getText();
		String telNo = xPhoneNum.getText();
		String area = xArea.getText();
		String openTime = (String)cbOperTimeOpen.getSelectedItem();
		String clseTime = (String)cbOperTimeClose.getSelectedItem();
		String estbDate = ((JTextField)chooser.getDateEditor().getUiComponent()).getText();
		String centerType = (String)cbCenterType.getSelectedItem();
		
		String newOpenTime = null;
		String newClseTime = null;
		String newCenterType = null;
		
		String[] openTimes = openTime.split(":");
		String[] clseTimes = clseTime.split(":");
		
		StringBuffer sb1 = new StringBuffer(openTimes[0]);
		sb1.append(openTimes[1]);
		newOpenTime = sb1.toString();
		
		StringBuffer sb2 = new StringBuffer(clseTimes[0]);
		sb2.append(clseTimes[1]);
		newClseTime = sb2.toString();
		
		switch(centerType) {
		case "본부":
			newCenterType = "h";
			break;
		case "일반":
			newCenterType = "n";
			break;
		}
	
		// 직원근무이력 관련
		String cntrManagerName = xCenterManager.getText();
		
		
		// 신규케이지 등록 관련
		int bigCageNum = Integer.parseInt(xCageBig.getText());
		int midCageNum = Integer.parseInt(xCageMid.getText());
		int smallCageNum = Integer.parseInt(xCageSmall.getText());
		
		try {
			StringBuffer query1 = new StringBuffer("INSERT INTO CNTR ");
			query1.append("SELECT ");
			query1.append("	CASE WHEN SUBSTR(CNTR_NO,2,1)=9 ");
			query1.append("		THEN to_char(SUBSTR(CNTR_NO,1,1)+1) ");
			query1.append("		ELSE SUBSTR(CNTR_NO,1,1) END || ");
			query1.append("	CASE WHEN SUBSTR(CNTR_NO,2,1)=9 ");
			query1.append("		THEN '0' ");
			query1.append("		ELSE to_char((SUBSTR(CNTR_NO,2,1)+1)) END CNTR_NO, ");
			query1.append("	'"+centerName+"' CNTR_NAME, ");
			query1.append("	'"+addr+"' ADDR, ");
			query1.append("	'"+telNo+"' TEL_NO, ");
			query1.append("	'"+area+"' AREA, ");
			query1.append("	'"+newOpenTime+"' OPEN_TIME, ");
			query1.append("	'"+newClseTime+"' CLSE_TIME, ");
			query1.append("	 TO_DATE('"+estbDate+"','YYYY-MM-DD') ESTB_DATE, ");
			query1.append("	'"+newCenterType+"' CNTR_TP ");
			query1.append("FROM( ");
			query1.append("	SELECT /*+ INDEX_DESC(CNTR CNTR_PK)*/ CNTR_NO ");
			query1.append("	FROM CNTR ");
			query1.append("	WHERE ROWNUM=1 ) ");
			
			pstmt = con.prepareStatement(query1.toString());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				con.commit();
			}
			
			// 할당시에 오늘 일자로 무조건 WORK_START_DATE가 시작하기 때문에 한명의 직원은 변경처리를 하지 않고는 당일 내에 새로운 센터로 배정불가
			StringBuffer query2 = new StringBuffer("INSERT INTO EMP_WORK_HIST(EMP_NO,WORK_START_DATE,CNTR_NO,EMP_TP,BIZ_FILD) ");
			query2.append("SELECT DISTINCT EMP_NO, ");
			query2.append("		 TRUNC(SYSDATE) WORK_START_DATE, ");
			query2.append("		 (SELECT /*+ INDEX_DESC(c CNTR_PK) */ CNTR_NO ");
			query2.append("		  FROM CNTR c ");
			query2.append("		  WHERE ROWNUM = 1) CNTR_NO, ");
			query2.append("		  EMP_TP, ");
			query2.append("		  BIZ_FILD ");
			query2.append("FROM EMP_WORK_HIST ");
			query2.append("WHERE EMP_NO = ( ");
			query2.append("	SELECT EMP_NO FROM EMP ");
			query2.append("	WHERE EMP_NAME='"+cntrManagerName+"' ");
			query2.append("	AND BRTH_YEAR_MNTH_DAY=to_date('"+cntrManagerBdate+"','YYYY-MM-DD') ");
			query2.append(") ");
			
			pstmt = con.prepareStatement(query2.toString());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				con.commit();
			}
			
			String query3 = null;
			
			if(bigCageNum >= 1 || midCageNum >= 1 || smallCageNum >= 1) {
				query3 = RegistCage(bigCageNum,midCageNum,smallCageNum);
			
			
				pstmt = con.prepareStatement(query3.toString());
				rs = pstmt.executeQuery();
				if(rs.next()) {
					con.commit();
				}	
			}
		}catch(Exception e1) {
			e1.printStackTrace();
		}
		
		disconnection();
		
	}
	
	private String RegistCage(int bigCageNum, int midCageNum, int smallCageNum) {
		
		bigCageNum+=1;
		midCageNum+=1;
		smallCageNum+=1;
		
		StringBuffer query3 = new StringBuffer();
		
		if (bigCageNum != 1 && midCageNum != 1 && smallCageNum != 1) {
				
			query3.append("INSERT INTO CAGE ");
			query3.append("SELECT (SELECT /*+ INDEX_DESC(c CNTR_PK) */ CNTR_NO ");
			query3.append("		  FROM CNTR c ");
			query3.append("		  WHERE ROWNUM = 1) CNTR_NO, ROWNUM CAGE_ORNU, t3.CAGE_SIZE ");
			query3.append("FROM( ");
			query3.append("		SELECT * ");
			query3.append("		FROM( ");
			query3.append("			SELECT LEVEL FROM DUAL ");
			query3.append("			CONNECT BY LEVEL<"+bigCageNum+" ");
			query3.append("		) t1, (SELECT 'b' CAGE_SIZE FROM DUAL) t2 ");
			query3.append("		UNION ALL ");
			query3.append("		SELECT * ");
			query3.append("		FROM( ");
			query3.append("			SELECT LEVEL FROM DUAL ");
			query3.append("			CONNECT BY LEVEL<"+midCageNum+" ");
			query3.append("		) t1, (SELECT 'm' CAGE_SIZE FROM DUAL) t2 ");
			query3.append("		UNION ALL ");
			query3.append("		SELECT * ");
			query3.append("		FROM( ");
			query3.append("			SELECT LEVEL FROM DUAL ");
			query3.append("			CONNECT BY LEVEL<"+smallCageNum+" ");
			query3.append("		) t1, (SELECT 's' CAGE_SIZE FROM DUAL) t2 ");
			query3.append(")t3 ");

		}
		else if(bigCageNum==1) {
			query3.append("INSERT INTO CAGE ");
			query3.append("SELECT (SELECT /*+ INDEX_DESC(c CNTR_PK) */ CNTR_NO ");
			query3.append("		  FROM CNTR c ");
			query3.append("		  WHERE ROWNUM = 1) CNTR_NO, ROWNUM CAGE_ORNU, t3.CAGE_SIZE ");
			query3.append("FROM( ");
//			query3.append("		SELECT * ");
//			query3.append("		FROM( ");
//			query3.append("			SELECT LEVEL FROM DUAL ");
//			query3.append("			CONNECT BY LEVEL<"+bigCageNum+" ");
//			query3.append("		) t1, (SELECT 'b' CAGE_SIZE FROM DUAL) t2 ");
//			query3.append("		UNION ALL ");
			query3.append("		SELECT * ");
			query3.append("		FROM( ");
			query3.append("			SELECT LEVEL FROM DUAL ");
			query3.append("			CONNECT BY LEVEL<"+midCageNum+" ");
			query3.append("		) t1, (SELECT 'm' CAGE_SIZE FROM DUAL) t2 ");
			query3.append("		UNION ALL ");
			query3.append("		SELECT * ");
			query3.append("		FROM( ");
			query3.append("			SELECT LEVEL FROM DUAL ");
			query3.append("			CONNECT BY LEVEL<"+smallCageNum+" ");
			query3.append("		) t1, (SELECT 's' CAGE_SIZE FROM DUAL) t2 ");
			query3.append(")t3 ");
		}
		
		else if(midCageNum==1) {
			query3.append("INSERT INTO CAGE ");
			query3.append("SELECT (SELECT /*+ INDEX_DESC(c CNTR_PK) */ CNTR_NO ");
			query3.append("		  FROM CNTR c ");
			query3.append("		  WHERE ROWNUM = 1) CNTR_NO, ROWNUM CAGE_ORNU, t3.CAGE_SIZE ");
			query3.append("FROM( ");
			query3.append("		SELECT * ");
			query3.append("		FROM( ");
			query3.append("			SELECT LEVEL FROM DUAL ");
			query3.append("			CONNECT BY LEVEL<"+bigCageNum+" ");
			query3.append("		) t1, (SELECT 'b' CAGE_SIZE FROM DUAL) t2 ");
			query3.append("		UNION ALL ");
//			query3.append("		SELECT * ");
//			query3.append("		FROM( ");
//			query3.append("			SELECT LEVEL FROM DUAL ");
//			query3.append("			CONNECT BY LEVEL<"+midCageNum+" ");
//			query3.append("		) t1, (SELECT 'm' CAGE_SIZE FROM DUAL) t2 ");
//			query3.append("		UNION ALL ");
			query3.append("		SELECT * ");
			query3.append("		FROM( ");
			query3.append("			SELECT LEVEL FROM DUAL ");
			query3.append("			CONNECT BY LEVEL<"+smallCageNum+" ");
			query3.append("		) t1, (SELECT 's' CAGE_SIZE FROM DUAL) t2 ");
			query3.append(")t3 ");
		}
		
		else if(smallCageNum==1) {
			query3.append("INSERT INTO CAGE ");
			query3.append("SELECT (SELECT /*+ INDEX_DESC(c CNTR_PK) */ CNTR_NO ");
			query3.append("		  FROM CNTR c ");
			query3.append("		  WHERE ROWNUM = 1) CNTR_NO, ROWNUM CAGE_ORNU, t3.CAGE_SIZE ");
			query3.append("FROM( ");
			query3.append("		SELECT * ");
			query3.append("		FROM( ");
			query3.append("			SELECT LEVEL FROM DUAL ");
			query3.append("			CONNECT BY LEVEL<"+bigCageNum+" ");
			query3.append("		) t1, (SELECT 'b' CAGE_SIZE FROM DUAL) t2 ");
			query3.append("		UNION ALL ");
			query3.append("		SELECT * ");
			query3.append("		FROM( ");
			query3.append("			SELECT LEVEL FROM DUAL ");
			query3.append("			CONNECT BY LEVEL<"+midCageNum+" ");
			query3.append("		) t1, (SELECT 'm' CAGE_SIZE FROM DUAL) t2 ");
//			query3.append("		UNION ALL ");
//			query3.append("		SELECT * ");
//			query3.append("		FROM( ");
//			query3.append("			SELECT LEVEL FROM DUAL ");
//			query3.append("			CONNECT BY LEVEL<"+smallCageNum+" ");
//			query3.append("		) t1, (SELECT 's' CAGE_SIZE FROM DUAL) t2 ");
			query3.append(")t3 ");
		}
		
		else if(bigCageNum==1 && midCageNum==1) {
			query3.append("INSERT INTO CAGE ");
			query3.append("SELECT (SELECT /*+ INDEX_DESC(c CNTR_PK) */ CNTR_NO ");
			query3.append("		  FROM CNTR c ");
			query3.append("		  WHERE ROWNUM = 1) CNTR_NO, ROWNUM CAGE_ORNU, t3.CAGE_SIZE ");
			query3.append("FROM( ");
//			query3.append("		SELECT * ");
//			query3.append("		FROM( ");
//			query3.append("			SELECT LEVEL FROM DUAL ");
//			query3.append("			CONNECT BY LEVEL<"+bigCageNum+" ");
//			query3.append("		) t1, (SELECT 'b' CAGE_SIZE FROM DUAL) t2 ");
//			query3.append("		UNION ALL ");
//			query3.append("		SELECT * ");
//			query3.append("		FROM( ");
//			query3.append("			SELECT LEVEL FROM DUAL ");
//			query3.append("			CONNECT BY LEVEL<"+midCageNum+" ");
//			query3.append("		) t1, (SELECT 'm' CAGE_SIZE FROM DUAL) t2 ");
//			query3.append("		UNION ALL ");
			query3.append("		SELECT * ");
			query3.append("		FROM( ");
			query3.append("			SELECT LEVEL FROM DUAL ");
			query3.append("			CONNECT BY LEVEL<"+smallCageNum+" ");
			query3.append("		) t1, (SELECT 's' CAGE_SIZE FROM DUAL) t2 ");
			query3.append(")t3 ");
		}
		
		else if(bigCageNum==1 && smallCageNum==1) {
			query3.append("INSERT INTO CAGE ");
			query3.append("SELECT (SELECT /*+ INDEX_DESC(c CNTR_PK) */ CNTR_NO ");
			query3.append("		  FROM CNTR c ");
			query3.append("		  WHERE ROWNUM = 1) CNTR_NO, ROWNUM CAGE_ORNU, t3.CAGE_SIZE ");
			query3.append("FROM( ");
//			query3.append("		SELECT * ");
//			query3.append("		FROM( ");
//			query3.append("			SELECT LEVEL FROM DUAL ");
//			query3.append("			CONNECT BY LEVEL<"+bigCageNum+" ");
//			query3.append("		) t1, (SELECT 'b' CAGE_SIZE FROM DUAL) t2 ");
//			query3.append("		UNION ALL ");
			query3.append("		SELECT * ");
			query3.append("		FROM( ");
			query3.append("			SELECT LEVEL FROM DUAL ");
			query3.append("			CONNECT BY LEVEL<"+midCageNum+" ");
			query3.append("		) t1, (SELECT 'm' CAGE_SIZE FROM DUAL) t2 ");
//			query3.append("		UNION ALL ");
//			query3.append("		SELECT * ");
//			query3.append("		FROM( ");
//			query3.append("			SELECT LEVEL FROM DUAL ");
//			query3.append("			CONNECT BY LEVEL<"+smallCageNum+" ");
//			query3.append("		) t1, (SELECT 's' CAGE_SIZE FROM DUAL) t2 ");
			query3.append(")t3 ");
		}
		
		else if(midCageNum==1 && smallCageNum==1) {
			query3.append("INSERT INTO CAGE ");
			query3.append("SELECT (SELECT /*+ INDEX_DESC(c CNTR_PK) */ CNTR_NO ");
			query3.append("		  FROM CNTR c ");
			query3.append("		  WHERE ROWNUM = 1) CNTR_NO, ROWNUM CAGE_ORNU, t3.CAGE_SIZE ");
			query3.append("FROM( ");
			query3.append("		SELECT * ");
			query3.append("		FROM( ");
			query3.append("			SELECT LEVEL FROM DUAL ");
			query3.append("			CONNECT BY LEVEL<"+bigCageNum+" ");
			query3.append("		) t1, (SELECT 'b' CAGE_SIZE FROM DUAL) t2 ");
//			query3.append("		UNION ALL ");
//			query3.append("		SELECT * ");
//			query3.append("		FROM( ");
//			query3.append("			SELECT LEVEL FROM DUAL ");
//			query3.append("			CONNECT BY LEVEL<"+midCageNum+" ");
//			query3.append("		) t1, (SELECT 'm' CAGE_SIZE FROM DUAL) t2 ");
//			query3.append("		UNION ALL ");
//			query3.append("		SELECT * ");
//			query3.append("		FROM( ");
//			query3.append("			SELECT LEVEL FROM DUAL ");
//			query3.append("			CONNECT BY LEVEL<"+smallCageNum+" ");
//			query3.append("		) t1, (SELECT 's' CAGE_SIZE FROM DUAL) t2 ");
			query3.append(")t3 ");
		}
		
		String result = query3.toString();
		return result;
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
	
	class NewCenterRegistButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource().equals(centerManagerSearch)) {
				CenterManagerSearch centerManagerSearch = new CenterManagerSearch(xCenterManager);
				centerManagerSearch.addWindowListener(new WindowAdapter() {

					@Override
					public void windowClosed(WindowEvent e) {
						// TODO Auto-generated method stub
						super.windowClosed(e);
//						String nameAndBdate= xCenterManager.getText();
//						String[] stringArray = nameAndBdate.split(",");
//						cntrManager= xCenterManager.getText();
						cntrManagerBdate = centerManagerSearch.getCntrManagerBdate();
					}
					
				});
			}
			else if(e.getSource().equals(addressSearch)) {
				new AddressSearch(xAddress);
			}
			else if(e.getSource().equals(register)) {
				int result = JOptionPane.showConfirmDialog(null, "신규 센터를 등록하시겠습니까?", "센터 등록 확인", JOptionPane.YES_NO_OPTION);
				switch(result) {
				case JOptionPane.YES_OPTION:
					RegistNewCenter();
//					RegistCenter();
//					RegistEmpWorkHist();
//					InitRegistCage();
					dispose();
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
    
	
	public static void main(String[] args) throws IOException{
		new NewCenterRegistration();
	}
}
