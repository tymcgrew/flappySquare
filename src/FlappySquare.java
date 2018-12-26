import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class FlappySquare extends Rectangle {

	private int ySpeed;
	public Color color;
	private BufferedImage smokeImage = null;
	private BufferedImage fireImage = null;
	private BufferedImage blueFireImage = null;

	public FlappySquare() {
		x = 150;
		y = 500;
		width = 100;
		height = 100;
		ySpeed = 0;
		color = Color.RED;
		try {
			smokeImage = ImageIO.read(new File("smoke.png"));
			fireImage = ImageIO.read(new File("fire.png"));
			blueFireImage = ImageIO.read(new File("blueFire.png"));
			}
		catch (Exception e) {}
	}

	public boolean update() {
		if (y > 900) {
			ySpeed = 0;
			y = 900;
			return true;
		}
		else if (y < 0) {
			ySpeed = 0;
			y = 0;
			return true;
		}
		else {
			ySpeed += 1;
			y += ySpeed;
			return false;
		}
	}

	public void flap() {
		ySpeed = -15;
	}

	public void draw(Graphics g, int score) {
		if (score > 9 && score < 20)
			g.drawImage(smokeImage, x - 150, y-50, null);
		g.setColor(color);
		g.fillRect(x, y, width, height);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, width, height);
		if (score > 19 && score < 50)
			g.drawImage(fireImage, x - 200, y, null);
		else if (score > 49)
			g.drawImage(blueFireImage, x - 200, y, null);
	}

}
