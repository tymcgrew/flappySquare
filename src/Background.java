import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Background extends Rectangle {
	private BufferedImage image = null;
	private int speed;

	public Background(int x, int y, int width, int height, int speed, String imageFile) {
		super(x, y, width, height);
		this.speed = speed;
		try { image = ImageIO.read(new File(imageFile)); }
		catch (Exception e) {}
	}
	
	public void move(int val) {	x += val*speed;	}
	public void draw(Graphics g) {
		g.drawImage(image, x, y, null);
	}
	
}
