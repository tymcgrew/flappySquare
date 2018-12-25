import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

import javax.imageio.ImageIO;

public class Pipe extends Rectangle {

	public int xSpeed;
	public BufferedImage topImage = null;
	public BufferedImage bottomImage = null;
	private Random rnd = new Random();

	public Pipe(int x) {
		this.x = x;
		y = rnd.nextInt(550) - 900;
		width = 150;
		height = 2210;
		xSpeed = -7;
		try { 
			topImage = ImageIO.read(new File("topPipe.png")); 
			bottomImage = ImageIO.read(new File("bottomPipe.png"));
		}
		catch (Exception e) {}
	}

	public boolean move(){
		x += xSpeed;
		if (x < -150) {
			x = 1920;
			y = rnd.nextInt(550) - 900;
			return true;
		}
		return false;
	}
	
	public void accelerate() {
		xSpeed -= 1;
	}

	public void draw(Graphics g) {
		g.drawImage(topImage, x, y, null);
		g.drawImage(bottomImage, x, y + 1280, null);
	}

}
