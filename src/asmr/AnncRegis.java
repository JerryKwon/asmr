package src.asmr;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AnncRegis extends JFrame {
	
	private JLabel vAnncRegis, vAbanName, vAnmlKinds, vKind, vSex, vAge,vColor, vNeutWhet, vAnmlSize, vFeat, vExpln;   
	
	private JTextField xAbanName, xAnmlKinds, xKind, xSex, xAge, xColor, xNeutWhet, xAnmlSize, xFeat, xExpln;
	
	private JButton confirm, cancel, search;
	
	GridBagLayout gridbagLayout;
	GridBagConstraints gridbagConstraints;
	
	public AnncRegis() {
		gridbagLayout = new GridBagLayout();
		gridbagConstraints = new GridBagConstraints();
		
		vAnncRegis = new JLabel("공고등록");

		vAbanName = new JLabel("유기동물명");
		xAbanName = new JTextField(20);
		
		vAnmlKinds = new JLabel("동물종류");
		xAnmlKinds = new JTextField(20);
		
		vKind = new JLabel("품종");
		xKind = new JTextField(20);
		
		vSex = new JLabel("성별");
		xSex = new JTextField(20);
		
		vAge = new JLabel("나이");
		xAge = new JTextField(20);
		
		vColor= new JLabel("색상");
		xColor = new JTextField(20);
		
		vNeutWhet = new JLabel("중성화여부");
		xNeutWhet = new JTextField(20);
		
		vAnmlSize = new JLabel("동물크기"); 
		xAnmlSize = new JTextField(20);
		
		vFeat = new JLabel("특징");
		xFeat = new JTextField(20);
		
		vExpln= new JLabel("설명");   
		xExpln = new JTextField(20);
		
		search = new JButton("검색");
		confirm = new JButton("등록");
		cancel = new JButton("취소");
		
		AnncRegisView();
		
	}
	
	private void AnncRegisView() {
		
		setTitle("공고등록");
		
		gridbagConstraints.anchor = GridBagConstraints.WEST;
		gridbagConstraints.ipadx = 7;

		gridbagConstraints.weightx=1.0;
		gridbagConstraints.weighty=1.0;

		setLayout(gridbagLayout);
		
		gridbagAdd(vAnncRegis, 0, 0, 1, 1);
		
		gridbagAdd(vAbanName, 0, 1, 1, 1);
		gridbagAdd(xAbanName, 2, 1, 1, 1);
		gridbagAdd(search, 3, 1, 1, 1);
		
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
		
		gridbagAdd(vAnmlSize, 4, 5, 1, 1);
		gridbagAdd(xAnmlSize, 6, 5, 1, 1);
		
		gridbagAdd(vFeat, 0, 6, 1, 1);
		gridbagAdd(xFeat, 2, 6, 1, 1);
		
		gridbagAdd(vExpln, 0, 7, 1, 1);
		gridbagAdd(xExpln, 2, 7, 1, 1);
		
		
		
		gridbagAdd(confirm,2, 8, 1, 1);
		gridbagAdd(cancel, 3, 8, 1, 1);
		
		pack();
		setResizable(false);
		setVisible(true);
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new AnncRegis();

	}

}
