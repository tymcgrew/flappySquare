import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class FlappySquare extends Rectangle {

	private int ySpeed;
	public Color color;
	
	public FlappySquare() {
		x = 50;
		y = 500;
		width = 100;
		height = 100;
		ySpeed = 0;
		color = Color.RED;
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
	
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, width, height);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, width, height);
	}
	
}
