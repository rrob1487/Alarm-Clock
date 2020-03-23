import javax.swing.JFrame;

public class SunButton extends Button{
	
	private static final long serialVersionUID = 1L;

	public SunButton(){
		super("Sun.png", 90);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Alarm");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 400);
		frame.setLocation(400, 200);
		frame.setLayout(null);
		frame.add(new PlusButton());
		frame.add(new SunButton());
		frame.setVisible(true);

	}

}
