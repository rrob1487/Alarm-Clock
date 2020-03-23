import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class AlarmSystem implements Runnable {

	private static ArrayList<AlarmContainer> alarms = new ArrayList<AlarmContainer>(0);
	private static final String filename = "alarmlist.txt";
	private static AlarmPage paige;
	public static boolean running = true;

	public AlarmSystem(){
		this.readAlarmList();
	}


	private void readAlarmList(){
		try {
			File f = new File(filename);
			f.createNewFile();
			Scanner scan = new Scanner(f);
			while(scan.hasNextLine()){
				int hour, day, month, minute;
				String rep;
				scan.nextInt();
				hour = scan.nextInt();
				minute = scan.nextInt();
				day = scan.nextInt();
				month = scan.nextInt();
				rep = scan.next();
				alarms.add(new AlarmContainer(AlarmSystem.generateId(), hour, minute, day, month, rep));
			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static int getElement(int id){
		for(int i = 0; i < alarms.size(); i++){
			if(alarms.get(i).getId() == id)
				return i;
		}
		return -1;
	}
	private static void writeAlarmList() throws IOException{
		File file = new File(filename);
		file.delete();
		file.createNewFile();
		BufferedWriter f = new BufferedWriter(new FileWriter(filename));
		for(int i = 0; i < AlarmSystem.alarms.size(); i++){
			f.append("\n" + AlarmSystem.alarms.get(i).getSerial());
		}
		f.close();
	}

	public static int generateId(){
		return alarms.size();
	}
	public static void showAlarmPage() throws InterruptedException{
		paige = new AlarmPage();
		Thread t = new Thread(paige);
		t.start();
		return;
	}	
	public static ArrayList<AlarmContainer> getAlarms(){
		return alarms;
	}
	public static String getNextAlarm(){
		int day = 50;
		int hour = 25;
		int element = -1;
		for(int i = 0; i < alarms.size(); i++){
			if(day > alarms.get(i).getDay()){
				element = i;
				day = alarms.get(i).getDay();
				hour = alarms.get(i).getHour();
				
			}
			else if(day == alarms.get(i).getDay() && hour > alarms.get(i).getHour()){
				element = i;
			}
		}
		if(element == -1){
			return "Not Set";
		}
		return alarms.get(element).toString();
	}
	public static boolean addAlarm(AlarmContainer a) throws IOException{
		if(AlarmSystem.alarms.contains(a)){
			return false;
		}
		BufferedWriter f = new BufferedWriter(new FileWriter(filename));
		f.append("\n" + a.getSerial());
		f.close();
		AlarmSystem.alarms.add(a);
		return true;
	}
	public static boolean removeAlarm(int id) throws IOException{
		System.out.println("Removed");
		AlarmSystem.alarms.remove(AlarmSystem.getElement(id));
		AlarmSystem.writeAlarmList();
		return true;
	}
	public void run() {
		while(running){
			Thread[] t = new Thread[alarms.size()];
			if(alarms.size() == 0) System.out.println("empty");
			for(int i = 0; i < alarms.size(); i++){
				System.out.println(alarms.get(i));
				t[i] = new Thread(alarms.get(i));
				t[i].start();
				try {
					t[i].join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {	
				e.printStackTrace();
			}
		}

	}


	public static void main(String[] args) {
		AlarmSystem a = new AlarmSystem();
		DateFormat date = new SimpleDateFormat("HH:mm:ss");
		System.out.println(date.format(new Date()));
		String s = date.format(new Date());
		Thread t = new Thread(a);
		try {
			t.start();
			AlarmSystem.addAlarm(new AlarmContainer(
					AlarmSystem.generateId(), 
					Integer.parseInt(s.substring(0, 2)), 
					Integer.parseInt(s.substring(3, 5))+1, 
					7, 12, "0000000"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		

	}

}
