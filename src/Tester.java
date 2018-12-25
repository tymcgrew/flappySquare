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
	int score = 0;
	int count = 0;

	public Tester() throws InterruptedException{
		window.setBounds(0, 0, 1920, 1080);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().add(this);
		window.setAlwaysOnTop(true);
		try { image = ImageIO.read(new File("background.png")); }
		catch (Exception e) {}
		bird = new FlappySquare();
		pipes.add(new Pipe(1750));
		pipes.add(new Pipe(2500));
		pipes.add(new Pipe(3250));
		window.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		window.setLocation(0,0);
		window.setVisible(true);

		//======================================== Events
		Thread.sleep(1000);
		
		tmr = new Timer(15, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (bird.update() == true) {
					repaint();
					tmr.stop();
					JOptionPane.showMessageDialog(window, "Final Score: " + score);
					System.exit(0);
				}
				for (Pipe p : pipes) {
					if (p.move() == true)
						score++;
					if (count % 500 == 1) {
						p.accelerate();
					}
					if (bird.intersects(p) && ( (bird.y < p.y + 930) || (bird.y + 100 > p.y + 1280) )) {
						repaint();
						tmr.stop();
						JOptionPane.showMessageDialog(window, "Final Score: " + score);
						System.exit(0);         
					}
				}
				
				count++;
					
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
		bird.draw(g);
		for (Pipe p : pipes)
			p.draw(g);
	}

	public static void main(String[] args) {
		try {new Tester();}
		catch (Exception e) {}
	}
}