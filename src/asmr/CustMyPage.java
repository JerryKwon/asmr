package asmr;

import java.awt.Font;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class CustMyPage extends JPanel{
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	private void ChangeFont(JComponent[] comps, Font font) {
    	for(JComponent comp: comps) {
    		comp.setFont(font);
    	}
    }

}
