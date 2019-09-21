package asmr;
import javax.swing.*;
import java.awt.*;

public class MainPage extends JPanel{
	private JLabel iMainBanner, iMainDog, iMainCat, iMainEtc;
	private JLabel vMainProtect, vMainDog, vMainCat, vMainEtc;
	
	
	GridBagLayout gridbaglayout;				// ȭ���� �����ϴ� ���̾ƿ�
	GridBagConstraints gridbagconstraints;	
	
	public MainPage() {
		
		gridbaglayout = new GridBagLayout();		
		gridbagconstraints = new GridBagConstraints();
		
		iMainBanner = new JLabel(new ImageIcon("images/mainbanner.jpg"));
		iMainDog = new JLabel(new ImageIcon("images/"));
		vMainProtect = new JLabel("���� ��ȣ���� ���⵿��");
		
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
	      //���� ���� �� gridx, gridy���� 0 			
				
		gridbagconstraints.gridwidth  = w;	//����	
		gridbagconstraints.gridheight = h;	//����	
	     			
	      			
	    gridbaglayout.setConstraints(c, gridbagconstraints); //������Ʈ�� ������Ʈ ��ġ+ũ�� ������ ���� GridBagLayout�� ��ġ			
				
	   add(c);			
				
	   }

	public static void main(String[] args) {

	}

}
