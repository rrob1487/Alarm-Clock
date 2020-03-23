import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class AlarmPage extends JFrame implements Runnable {


	private static final long serialVersionUID = 1L;
	private static JScrollPane scroll;
	private JButton exit, add;
	private static JPanel panel;
	private static JPanel buttonPanel;
	private static JPanel elementContainer;
	private static NewAlarmPanel newAlarmPanel;
	private static Component center;
	public static boolean running = true;

	public AlarmPage() {
		super();
		this.toFront();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		this.getContentPane().setBackground(Color.BLACK);
		this.setLocation(400, 200);

		panel = new JPanel();
		panel.setBackground(Color.black);
		panel.setSize(this.getSize());
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.black);
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

		elementContainer = new JPanel();
		elementContainer.setBackground(Color.BLACK);
		elementContainer.setLayout(new BoxLayout(elementContainer, BoxLayout.Y_AXIS));
		fillElements();
		
		scroll = new JScrollPane(elementContainer);
		scroll.setPreferredSize(new Dimension(this.getWidth(), 400));
		center = scroll;
		
		newAlarmPanel = new NewAlarmPanel();
				
		panel.add(scroll, BorderLayout.PAGE_START);

		exit = new JButton("<");
		exit.setFont(new Font("hk", 1, 200));
		exit.setBackground(Color.BLACK);
		exit.setForeground(Color.WHITE);
		exit.setBorder(null);
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				AlarmPage.running = false;
			}});
		buttonPanel.add(exit);
		
		add = new JButton("+");
		add.setFont(new Font("", 1, 200));
		add.setBackground(Color.black);
		add.setForeground(Color.white);
		add.setBorder(null);
		add.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				AlarmPage.switchCenter();
				NewAlarmPanel.midday = 0;
				NewAlarmPanel.min = 0;
				NewAlarmPanel.hour = 1;
			}});
		buttonPanel.add(add);
		panel.add(buttonPanel, BorderLayout.PAGE_END);

		this.add(panel);
		this.setVisible(true);
	}

	public void run() {
		while(running){
			this.repaint();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.dispose();
		running = true;
	}
	
	public static void switchCenter(){
		System.out.println("switched");
		if(center == scroll){
			panel.remove(scroll);
			panel.add(newAlarmPanel, BorderLayout.PAGE_START);
			panel.remove(buttonPanel);
			panel.add(buttonPanel);
			panel.validate();
			panel.repaint();
			center = newAlarmPanel;
		}
		else{
			panel.remove(newAlarmPanel);
			panel.add(scroll, BorderLayout.PAGE_START);
			panel.remove(buttonPanel);
			panel.add(buttonPanel);
			fillElements();
			panel.validate();
			panel.repaint();
			center = scroll;
		}
	}
	
	private static void fillElements(){
		for(int i = 0; i < AlarmSystem.getAlarms().size(); i++){
			elementContainer.add(new AlarmPageElement(AlarmSystem.getAlarms().get(i)));
		}
	}

	public static void main(String[] args) {
		AlarmPage paige = new AlarmPage();
		Thread t = new Thread(paige);
		t.start();
	}

}
