import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Tester extends JPanel {

	JFrame window = new JFrame("Title");
	Timer tmr = null;
	Random rnd = new Random();
	FlappySquare bird = null;
	ArrayList<Pipe> pipes = new ArrayList<>();
	private BufferedImage image = null;
	private BufferedImage getReady1 = null;
	private BufferedImage getReady2 = null;
	private BufferedImage getReady3 = null;
	private BufferedImage go = null;
	
	long time = System.currentTimeMillis();
	int score = 0;

	public Tester() throws InterruptedException{
		window.setBounds(0, 0, 1920, 1080);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().add(this);
		window.setAlwaysOnTop(true);
		try { 
			image = ImageIO.read(new File("background.png")); 
			getReady1 = ImageIO.read(new File("getReady1.png"));
			getReady2 = ImageIO.read(new File("getReady2.png"));
			getReady3 = ImageIO.read(new File("getReady3.png"));
			go = ImageIO.read(new File("go.png"));
		}
		catch (Exception e) {}
		bird = new FlappySquare();
		pipes.add(new Pipe(1000));
		pipes.add(new Pipe(1690));
		pipes.add(new Pipe(2380));
		window.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		window.setLocation(0,0);
		window.setVisible(true);

		//======================================== Events

		repaint();
		Thread.sleep(500);

		repaint();
		Thread.sleep(500);

		repaint();
		Thread.sleep(500);

		repaint();
		Thread.sleep(500);


		tmr = new Timer(15, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (bird.update() == true) {
					repaint();
					tmr.stop();
					JOptionPane.showOptionDialog(window, "Final Score: " + score, "Game Over", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE, null, new Object[]{}, null);					
					System.exit(0);
				}
				for (Pipe p : pipes) {
					if (p.move() == true)
						score++;
					if (
							score==5 && p.xSpeed==-7 || 
							score==15 && p.xSpeed==-8 || 
							score==25 && p.xSpeed==-9 || 
							score==35 && p.xSpeed==-10 || 
							score==45 && p.xSpeed==-11 || 
							score==55 && p.xSpeed==-12 ||
							score==65 && p.xSpeed==-13 || 
							score==75 && p.xSpeed==-14 || 
							score==85 && p.xSpeed==-15 
							) 
					{
						p.accelerate();
					}
					if (bird.intersects(p) && ( (bird.y < p.y + 930) || (bird.y + 100 > p.y + 1280) )) {
						repaint();
						tmr.stop();
						JOptionPane.showOptionDialog(window, "Final Score: " + score, "Game Over", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE, null, new Object[]{}, null);					
						System.exit(0);         
					}
				}

				repaint();
			}
		});

		window.addMouseListener(new MouseListener() {
			@Override
			public void mousePressed(MouseEvent e) {
				bird.flap();
			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});

		window.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseMoved(MouseEvent e) {

			}

			@Override
			public void mouseDragged(MouseEvent e) {

			}
		});

		window.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyCode() == 0)
					bird.flap();
			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {

			}
		});

		//======================================== End Events
		tmr.start();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
		bird.draw(g, score);
		for (Pipe p : pipes)
			p.draw(g);
		if (System.currentTimeMillis() - time < 500)
			g.drawImage(getReady1, 600, 400, null);
		else if (System.currentTimeMillis() - time < 1000)
			g.drawImage(getReady2,  600,  400,  null);
		else if (System.currentTimeMillis() - time < 1500)
			g.drawImage(getReady3,  600,  400,  null);
		else if (System.currentTimeMillis() - time < 2000)
			g.drawImage(go,  600,  400,  null);
	}

	public static void main(String[] args) {
		try {new Tester();}
		catch (Exception e) {}
	}
}