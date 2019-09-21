package asmr;
import javax.swing.*;
import java.awt.*;

public class MainPage extends JPanel{
	private JLabel iMainBanner, iMainDog, iMainCat, iMainEtc;
	private JLabel vMainProtect, vMainDog, vMainCat, vMainEtc;
	
	
	GridBagLayout gridbaglayout;				// 화면을 구성하는 레이아웃
	GridBagConstraints gridbagconstraints;	
	
	public MainPage() {
		
		gridbaglayout = new GridBagLayout();		
		gridbagconstraints = new GridBagConstraints();
		
		iMainBanner = new JLabel(new ImageIcon("images/mainbanner.jpg"));
		iMainDog = new JLabel(new ImageIcon("images/"));
		vMainProtect = new JLabel("현재 보호중인 유기동물");
		
		MainPageView();
	}
	private void MainPageView() {
		
		setSize(1900, 1000);
		gridbagconstraints.anchor = GridBagConstraints.WEST;		
		gridbagconstraints.ipadx = 7;		
				
		gridbagconstraints.weightx=1.0;		
		gridbagconstraints.weighty=1.0;		
				
		setLayout(gridbaglayout);
		gridbagAdd(iMainBanner, 0,0,10,1);
		gridbagAdd(vMainProtect, 0,1,3,1);
			
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
