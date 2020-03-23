import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

public class MicButton extends Button {

	
	private static final long serialVersionUID = 1L;
	private static boolean changeIcon, micActive = true;
	
	public MicButton(){
		super("mic_on.png", 180);
		this.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				MicButton.setChange();
		}});
	}
	
	public static void setChange(){
		MicButton.changeIcon = true;
	}
	
	public boolean getChange(){
		return MicButton.changeIcon;
	}
	
	public void change(){
		if(micActive){
			this.setIcon(new ImageIcon(getScaledImage(new ImageIcon("mic_off.png").getImage(), super.width, super.height)));
			MicButton.micActive = false;
		}
		else{
			this.setIcon(new ImageIcon(getScaledImage(new ImageIcon("mic_on.png").getImage(), super.width, super.height)));
			MicButton.micActive = true;
		}
		MicButton.changeIcon = false;
	}
	
	
//	public static void main(String[] args) {
//		JFrame frame = new JFrame("Alarm");
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
//		frame.setUndecorated(true);
//		frame.setLayout(null);
//		frame.setLocation(400, 200);
//		frame.add(new PlusButton());
//		frame.add(new SunButton());
//		frame.add(new MicButton());
//		frame.setVisible(true);
//	}

}
