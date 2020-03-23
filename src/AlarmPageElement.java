import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AlarmPageElement extends JPanel {

	private JButton edit;
	private JTextField info;

	public AlarmPageElement(AlarmContainer a){
		super();
		this.setBackground(Color.BLACK);
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.setPreferredSize(new Dimension(100, 100));


		edit = new JButton("Delete");
		edit.setEnabled(true);
		edit.setFont(new Font("hk", 1, 50));
		edit.setLayout(null);
		edit.setSize(200, this.getHeight());
		edit.setBackground(Color.BLACK);
		edit.setForeground(Color.WHITE);
		edit.setBorder(null);
		edit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try {
					AlarmSystem.removeAlarm(a.getId());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}});


		info = new JTextField(a.toString());
		if(a.getMinute() < 10){
			info.setText(a.getMonth() + "/" + a.getDay() + "\t" +a.getHour() + ":" + "0" + a.getMinute());
		}
		info.setEditable(false);
		info.setEnabled(false);
		info.setFont(new Font("times", Font.ITALIC, 60));
		info.setForeground(Color.WHITE);
		info.setBackground(Color.BLACK);

		this.add(info, BorderLayout.WEST);
		this.add(edit, BorderLayout.EAST);
	}

}
