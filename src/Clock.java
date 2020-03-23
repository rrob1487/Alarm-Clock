import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JTextArea;


public class Clock extends JTextArea{


	private static final long serialVersionUID = 1L;
	private String time;
	private DateFormat format = new SimpleDateFormat("hh:mm:ss");
	
	public Clock(){
		super("Not Set");
		//this.setLayout(null);
		this.setEditable(false);
		this.setEnabled(false);
		this.setDisabledTextColor(Color.WHITE);
		this.setSize(500, 145);
		this.setLocation(0, 70);
		this.setFont(new Font("times", Font.BOLD, 120));
		this.setForeground(Color.WHITE);
		this.setBackground(Color.BLACK);
	}
	
	public void updateTime(){
		Date cal = new Date();
		time = format.format(cal);
		this.setText(time);
	}

//	public void run() {
//		while(true){
//			updateTime();
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//	}

	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Alarm");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		frame.setLayout(null);
		frame.setLocation(400, 200);
		frame.add(new PlusButton());
		frame.add(new SunButton());
		frame.add(new MicButton());
		Clock c = new Clock();
		frame.add(c);
		frame.setVisible(true);
	}

}
