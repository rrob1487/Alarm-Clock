import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NewAlarmPanel extends JPanel {


	private static final long serialVersionUID = 1L;
	private JPanel buttons, toggles, top;
	private static JTextField time;
	public static int midday = 0, min = 0, hour = 1;;
	private static NewAlarmButton hr1, hr5, ampm, min1, min15, clear, okay;

	public NewAlarmPanel(){
		super();
		this.setBackground(Color.black);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setSize(new Dimension(400, 400));

		toggles = new JPanel();
		toggles.setBackground(Color.black);
		toggles.setLayout(new BoxLayout(toggles, BoxLayout.Y_AXIS));

		top = new JPanel();
		top.setBackground(Color.black);
		top.setLayout(new BoxLayout(top, BoxLayout.X_AXIS));

		time = new JTextField("1:00");
		time.setEditable(false);
		time.setEnabled(false);
		time.setFont(new Font("times", Font.ITALIC, 100));
		time.setForeground(Color.WHITE);
		time.setBackground(Color.BLACK);
		top.add(time);

		buttons = new JPanel();
		buttons.setLayout(new GridLayout(3, 2));
		buttons.setBackground(Color.black);

		hr1 = new NewAlarmButton("+1 Hr");
		hr1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				hour++;
				updateTime();
			}});
		buttons.add(hr1);
		
		min1 = new NewAlarmButton("+1 Min");
		min1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				min++;
				updateTime();
			}});
		buttons.add(min1);


		hr5 = new NewAlarmButton("+5 Hr");
		hr5.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				hour+=5;
				updateTime();
			}});
		buttons.add(hr5);

		
		min15 = new NewAlarmButton("+15 Min");
		min15.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				min+=15;
				updateTime();
			}});
		buttons.add(min15);

		ampm = new NewAlarmButton("AM");
		ampm.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(ampm.getText() == "AM"){
					ampm.setText("PM");
					midday = 12;
				}
				else{
					ampm.setText("AM");
					midday = 0;
				}
			}});
		toggles.add(ampm);
		
		clear = new NewAlarmButton("Clear");
		clear.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				min = 0;
				hour = 1;
				updateTime();
			}});
		toggles.add(clear);
		
		okay = new NewAlarmButton("Okay");
		okay.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Calendar cal = Calendar.getInstance();
//				if(hour+midday < cal.get(Calendar.HOUR_OF_DAY) || (hour+midday == cal.get(Calendar.HOUR_OF_DAY) && min < cal.get(Calendar.MINUTE))){
//					try {
//						AlarmSystem.addAlarm(new AlarmContainer(
//								AlarmSystem.generateId(), 
//								hour-midday, min, 
//								cal.get(Calendar.DAY_OF_MONTH+1), 
//								cal.get(Calendar.MONTH), 
//								"0000000"));
//					} catch (IOException e1) {
//						e1.printStackTrace();
//					}
//				}
//				else{
					try {
						AlarmSystem.addAlarm(new AlarmContainer(
								AlarmSystem.generateId(), 
								hour+midday, 
								min, 
								cal.get(Calendar.DAY_OF_MONTH), 
								cal.get(Calendar.MONTH)+1, 
								"0000000"));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				//}
				AlarmPage.switchCenter();
			}});
		toggles.add(okay);
		
		top.add(toggles);


		this.add(top);
		this.add(buttons);

	}

	public static void updateTime(){
		if(min<10){
			time.setText(hour + ":0" + min);
		}
		else{
			time.setText(hour + ":" + min);
		}
	}
}
