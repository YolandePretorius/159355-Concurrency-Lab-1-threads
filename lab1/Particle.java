/*
 * Created on Mar 6, 2006
 *
 */
package lab1;

import java.awt.Graphics;
import java.util.Random;

class Particle extends Thread {
	protected int x;
	protected int y;
	protected final Random rng = new Random(this.hashCode());

	public Particle(int initialX, int initialY) { 
		x = initialX;
		y = initialY;
	}

	public void run() {
		while (true) {
			try {
				while (true) {
					move();
					sleep(100); // arbitrary wait time
				}
			} catch (InterruptedException ie) { 
				return; 
			}
		}
	}

	public synchronized void move() {
		x += (rng.nextInt() % 10);
		y += (rng.nextInt() % 10);
	}

	public void draw(Graphics g) {
		int lx, ly;
		synchronized (this) { lx = x; ly = y;}
		g.drawRect(lx, ly, 10, 10);
	}
}

