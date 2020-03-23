import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

public class ActiveAlarm extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;
	private Clock c = new Clock();
	private TurnOffButton button;
	private static boolean isActive = false;
	
	
	public ActiveAlarm(){
		ActiveAlarm.isActive = true;
		this.setAlwaysOnTop(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		this.setLayout(null);
		this.getContentPane().setBackground(Color.BLACK);
		
		this.add(c);
		
		button = new TurnOffButton();
		this.add(button);
		button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ActiveAlarm.setActive(false);
				
			}
			
			
		});
		
		this.setVisible(true);
	}
	
	public static void setActive(boolean b){
		ActiveAlarm.isActive = b;
	}
	public static boolean getActive(){
		return ActiveAlarm.isActive;
	}
	
	public void run() {
		File soundFile = new File("Alarm.wav");
		try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		
			while(ActiveAlarm.isActive){
				this.c.updateTime();
				System.out.println("beep");
				Thread.sleep(1000);
			}	
			clip.stop();
			clip.close();
			
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		this.dispose();
		return;
	}

	public static void main(String[] args) throws InterruptedException {
		ActiveAlarm aa = new ActiveAlarm();
		Thread t = new Thread(aa);
		t.start();
		t.join();
		System.out.println("Reached");
		t = null;
	}

}
