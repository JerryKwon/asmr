package asmr;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class AddressSearchPop extends JFrame{
	private JLabel vAddrSearch;
	
	public AddressSearchPop() {
		
		
		AddressSearchPopView();
	}
	private void AddressSearchPopView() {
		setTitle("주소검색");
		setSize(600, 600);
		
		pack();
		setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
