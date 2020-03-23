import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class NextAlarm extends JTextArea {

	private static final long serialVersionUID = 1L;

	public NextAlarm(){
		super("Next Alarm: Not Set");
		this.setEditable(false);
		this.setEnabled(false);
		this.setSize(225, 25);
		this.setAlignmentY(RIGHT_ALIGNMENT);
		this.setLocation(275, 10);
		this.setFont(new Font("times", Font.ITALIC, 20));
		this.setForeground(Color.WHITE);
		this.setBackground(Color.BLACK);
		
	}
	
	
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Alarm");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		frame.setLayout(null);
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setLocation(400, 200);
		frame.add(new PlusButton());
		frame.add(new SunButton());
		frame.add(new MicButton());
		frame.add(new NextAlarm());
		Clock c = new Clock();
		frame.setVisible(true);

	}

}
