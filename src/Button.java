import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public abstract class Button extends JButton{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected final int width = 75;
	protected final int height = 75;
	
	public Button(String icon, int xLocation){
		super();
		this.setEnabled(true);
		this.setLayout(null);
		this.setSize(width, height);
		this.setLocation(xLocation+5, 270);
		this.setBackground(Color.BLACK);
		this.setForeground(new Color(Color.TRANSLUCENT));
		this.setBorder(null);
		this.setIcon(new ImageIcon(getScaledImage(new ImageIcon(icon).getImage(), width, height)));
	}
	
	protected Image getScaledImage(Image srcImg, int w, int h){
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImg.createGraphics();

	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();

	    return resizedImg;
	}
}
