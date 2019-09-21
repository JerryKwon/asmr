package asmr;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CageRegister extends JFrame {
	private JLabel vCageRegister,vCenterName,vCageSize;
	private JTextField xCenterName;
	private JComboBox<String> cbCageSize;
	private JButton confirm,cancel;
	
	private final String[] cageSizeDiv = {"대","중","소"};
	
	private Color blue = new Color(22,155,213);
	private Color white = new Color(255,255,255);
	
	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;
	
	CageRegisterButtonListener cageRegisterButtonListener;
	
	public CageRegister() {
		gridBagLayout = new GridBagLayout();
		gridBagConstraints = new GridBagConstraints();
		
		cageRegisterButtonListener = new CageRegisterButtonListener();
		
		vCageRegister = new JLabel("케이지등록");
		
		vCenterName = new JLabel("센터명");
		xCenterName = new JTextField(10);
		xCenterName.setEnabled(false);
		
		vCageSize = new JLabel("케이지크기");
		cbCageSize = new JComboBox<String>(cageSizeDiv);
		
		confirm = new JButton("확인");
		confirm.setBackground(blue);
		confirm.setForeground(white);
		confirm.addActionListener(cageRegisterButtonListener);
		cancel = new JButton("취소");
		cancel.addActionListener(cageRegisterButtonListener);
		
		CageRegisterView();
	}

	private void CageRegisterView() {
		setLayout(gridBagLayout);

		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.ipadx = 7;
		
		gridBagConstraints.weightx=1.0;
		gridBagConstraints.weighty=1.0;
		
		gridbagAdd(vCageRegister, 0, 0, 1, 1);
		
		vCenterName.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
		gridbagAdd(vCenterName, 0, 1, 1, 1);
		
		gridbagAdd(xCenterName, 1, 1, 2, 1);
		
		vCageSize.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
		gridbagAdd(vCageSize, 0, 2, 1, 1);
		
		gridbagAdd(cbCageSize, 1, 2, 2, 1);
		
		ButtonPanel buttonPanel = new ButtonPanel();
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(0,30,0,0));
		gridbagAdd(buttonPanel, 0, 6, 6, 1);
		
		
		pack();
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
	
	
	class ButtonPanel extends JPanel{
		public ButtonPanel() {
			setLayout(new FlowLayout(FlowLayout.RIGHT));
			add(confirm);
			add(cancel);
			
		}
	}
	
	class CageRegisterButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource().equals(confirm)) {
				
			}
			else if (e.getSource().equals(cancel)) {
				dispose();
			}
		}
		
	}
	
//	리스너 작업으로 인하여 main 메서드 삭제
//	public static void main(String[] args) {
//		new CageRegister();
//	}
}
