import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MainPage extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;
	private PlusButton pb = new PlusButton();
	private SunButton sb = new SunButton();
	private MicButton mb = new MicButton();
	private NextAlarm na = new NextAlarm();
	private JButton increase;
	private static boolean changeBrightness = false;
	private int counter = 0;
	private Clock c = new Clock();
	private static AlarmSystem system = new AlarmSystem();
	private Thread systemThread;
	private boolean lit = true;
	public static boolean running = true;

	public MainPage(){
		super();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		this.setLayout(null);
		this.getContentPane().setBackground(Color.BLACK);
		this.setLocation(400, 200);
		sb.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				MainPage.setChangeBrightness(true);
			}});
		pb.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try {
					AlarmSystem.showAlarmPage();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}});


		systemThread= new Thread(system);
		systemThread.start();

		this.add(pb);
		this.add(sb);
		this.add(mb);
		this.add(na);
		this.add(c);
		this.setVisible(true);
	}

	public void increaseBrightness(){
		this.remove(increase);
		this.add(pb);
		this.add(sb);
		this.add(mb);
		this.add(na);

		//activate script that increases brightness

		this.lit = true;

	}
	public void decreaseBrightness(){
		this.remove(pb);
		this.remove(sb);
		this.remove(mb);
		this.remove(na);

		increase = new JButton();
		increase.setLayout(null);
		increase.setSize(this.getSize());
		increase.setContentAreaFilled(false);
		increase.setBorder(null);
		increase.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				MainPage.setChangeBrightness(true);
			}});

		this.add(increase);

		//activate script that decreases brightness

		this.lit = false;
	}
	public static void setChangeBrightness(boolean set){
		if(set){
			MainPage.changeBrightness = true;
		}
	}
	public void change(){
		if(this.lit){
			this.decreaseBrightness();
		}
		else{
			this.increaseBrightness();
		}
	}

	public void run() {	
		while(MainPage.running){
			this.c.updateTime();
			if(MainPage.changeBrightness){
				this.change();
				MainPage.changeBrightness = false;
			}
			this.na.setText("Next Alarm: " + AlarmSystem.getNextAlarm());
			if(mb.getChange()){
				mb.change();
			}

			if(counter == 800){
				this.repaint();
				this.counter = 0;
			}
			this.counter++;
		}
		AlarmSystem.running = false;

	}

	public static void main(String[] args) throws InterruptedException, NumberFormatException, IOException {
		MainPage p = new MainPage();

		Thread t = new Thread(p);
		t.start();
		//DateFormat date = new SimpleDateFormat("HH:mm:ss");
		//String s = date.format(new Date());
		//AlarmSystem.addAlarm(new AlarmContainer(
		//		AlarmSystem.generateId(), 
		//		Integer.parseInt(s.substring(0, 2)), 
		//		Integer.parseInt(s.substring(3, 5))+1, 
		//		7, 12, "0000000"));
		//Thread.sleep(3000);
		//p.decreaseBrightness();
		t.join();
	}

}
