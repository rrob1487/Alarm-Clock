import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

public class NewAlarmButton extends JButton {

	
	private static final long serialVersionUID = 1L;

	public NewAlarmButton(String s){
		super(s);
		this.setFont(new Font("", 1, 50));
		this.setBackground(Color.black);
		this.setForeground(Color.white);
		this.setBorder(null);
		
	}
}
