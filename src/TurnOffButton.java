import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

public class TurnOffButton extends JButton {

	private static final long serialVersionUID = 1L;

	public TurnOffButton(){
		super("Turn Off");
		this.setLayout(null);
		this.setSize(450, 100);
		this.setLocation(20, 215);
		this.setFont(new Font("hi", 1, 75));
		this.setBackground(Color.darkGray);
		this.setForeground(Color.RED);
		this.setBorder(null);
	}

}
