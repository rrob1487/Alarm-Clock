import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AlarmContainer implements Runnable{

	//hour is 24 hour
	private int id, hour, minute, day, month;
	private boolean repeat;
	private String rep = "0000000";
	private DateFormat date = new SimpleDateFormat("HH:mm:MM:dd");

	public AlarmContainer(int id, int hour, int min, int day, int month, String repeat){
		this.id = id;
		this.hour = hour;
		this.minute = min;
		this.day = day;
		this.month = month;
		if(!rep.equals(repeat)){
			this.rep = repeat;
			this.repeat = true;
		}
		System.out.println(this.toString());
	}


	private boolean isActivate(){
		String s = date.format(new Date());
		if(Integer.parseInt(s.substring(0, 2)) == hour && Integer.parseInt(s.substring(3, 5)) == minute && Integer.parseInt(s.substring(6, 8)) == month && Integer.parseInt(s.substring(9, 11)) == day){
			return true;
		}
		return false;
	}
	public void run() {	
		if(this.isActivate()){
			ActiveAlarm alarmObject = new ActiveAlarm();
			
			Thread t = new Thread(alarmObject);
			t.start();
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			//Check If It Will Occur Again or Delete it
			if(this.repeat){
				try {
					this.setNewRepeatAlarm();
				} catch (IOException e) {
					e.printStackTrace();
				}				
			}
			else{
				try {
					AlarmSystem.removeAlarm(this.id);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("not active");
		return;
	}
	
	private void setNewRepeatAlarm() throws IOException {
		AlarmSystem.removeAlarm(this.id);
		Calendar cal = Calendar.getInstance();
		for(int i = Calendar.DAY_OF_WEEK; i < rep.length(); i++){
			if(rep.charAt(i) == '1'){
				AlarmSystem.addAlarm(new AlarmContainer(
						AlarmSystem.generateId(), hour, minute,
						day + 0,//uhhh 
						month, rep));
				//need to check that range is within month
			}
		}
		//for(int i = 0; i < ) This loop is for if the next repeat is next week
		
	}
	public String toString(){
		String s = "";
		s += hour + ":" + minute + " " + month + "/" + day;
		return s;
	}


	public int getId() {
		return id;
	}
	public int getHour() {
		return hour;
	}
	public int getMinute() {
		return minute;
	}
	public int getDay() {
		return day;
	}
	public int getMonth() {
		return month;
	}
	public boolean isRepeat() {
		return repeat;
	}
	public String getRep() {
		return rep;
	}
	public DateFormat getDate() {
		return date;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public void setMinute(int minute) {
		this.minute = minute;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public void setRepeat(boolean repeat) {
		this.repeat = repeat;
	}
	public void setRep(String rep) {
		this.rep = rep;
	}
	public void setDate(DateFormat date) {
		this.date = date;
	}	
	public String getSerial(){
		String s = "";
		s+= this.id + " " + this.hour + " ";
		if(this.minute<10)
			s += "0" + this.minute + " " +this.day + " " + this.month + " " + this.rep;
		else
			s += this.minute + " " +this.day + " " + this.month + " " + this.rep;
		return s;
	}
	
	public static void main(String[] args) throws InterruptedException{
		DateFormat date = new SimpleDateFormat("HH:mm:ss");
		String s = date.format(new Date());
		System.out.println(s.substring(0,2) + " " +s.substring(3, 5) + " " + s.substring(6,8));
		
		AlarmContainer a = new AlarmContainer(0, Integer.parseInt(s.substring(0, 2)), Integer.parseInt(s.substring(3, 5)), 7, 12, "0000000");
		Thread t = new Thread(a);
		t.start();
		t.join();
		System.out.println("reached");
	}


}
