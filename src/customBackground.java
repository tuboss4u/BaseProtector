import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class customBackground extends JPanel implements ActionListener, MouseListener {

	BufferedImage background;
	BufferedImage arrowImage;
	CopyOnWriteArrayList<arrow> arrowList;
	CopyOnWriteArrayList<enemy> enemyList;
	BufferedImage enemyImage;
	JLabel kills;
	counters scoreCounters = new counters();
	long enemyTimer = -1;

	int ammo = 30;

	customBackground(String x) {

		arrowList = new CopyOnWriteArrayList<arrow>();
		enemyList = new CopyOnWriteArrayList<enemy>();
		try {
			background = ImageIO.read(this.getClass().getResourceAsStream(x));
			arrowImage = ImageIO.read(this.getClass().getResourceAsStream("arrowImage.png"));
			enemyImage = ImageIO.read(this.getClass().getResourceAsStream("axeEnemy.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void paintComponent(Graphics g) {
		g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), null);

		scoreCounters.draw(g);

		for (arrow a : arrowList) {
			a.draw(g);
		}
		for (enemy e : enemyList) {
			e.paint(g);

		}

	}

	void addArrow(int x, int y) {
		arrowList.add(new arrow(198, 60, 100, 100, 10, 10, x, y, arrowImage));
		scoreCounters.ammo--;
		repaint();
	}

	void updateArrows() {
		for (arrow a : arrowList) {
			a.update();
			checkCollision();

		}

	}

	void update() {
		for (enemy e : enemyList) {
			e.update();
			checkCollision();
		}
		repaint();
	}

	void addEnemy() {
		if (enemyTimer == -1) {
			enemyTimer = System.currentTimeMillis();
		}
		if (System.currentTimeMillis() - enemyTimer >= 1500) {
			enemyList.add(new enemy(1329, 300, 200, 200, 1, enemyImage));
			enemyTimer = -1;
		}
	}

	void checkCollision() {
		for (enemy e : enemyList) {
			for (arrow a : arrowList) {
				if (e.getBox().intersects(a.getBox())) {
					System.out.println("collision");
					enemyList.remove(e);
					arrowList.remove(a);

					scoreCounters.levelTracker++;
					if (scoreCounters.ammo == 0) {
						JOptionPane.showMessageDialog(null, "GAME OVER");
					} else {

					}
					if (scoreCounters.levelTracker == 20 && scoreCounters.ammo >= 1) {
						JOptionPane.showMessageDialog(null, "Level 2!");
						scoreCounters.ammo = 60;
					} else {

					}
					if (scoreCounters.levelTracker == 60 && scoreCounters.ammo >= 1) {
						JOptionPane.showMessageDialog(null, "Level 3!");
					} else {

					}

				}
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("hi");

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
