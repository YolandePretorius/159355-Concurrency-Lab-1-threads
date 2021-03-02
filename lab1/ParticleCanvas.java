/*
 * Created on Mar 6, 2006
 * Updated 2014
 *
 */
package lab1;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

class ParticleCanvas extends Canvas implements Runnable, KeyListener {

	private static final Object ParticleCanvas = null;
	private Particle[] particles = new Particle[0]; 

	public ParticleCanvas(int size) {
		setSize(new Dimension(size, size));
		addKeyListener(this);
	}

	public void run() {
		try {
			while (true) {
				repaint();
				Thread.sleep(100); // arbitrary wait
			}
		} catch (InterruptedException ie) { 
			return; 
		}
	}

	// Called by the applet
	synchronized void setParticles(Particle[] ps) {
		if (ps == null) 
		throw new IllegalArgumentException("Cannot set null");

		particles = ps; 
	}

	protected synchronized Particle[] getParticles() { 
		return particles; 
	}

	@Override
	public void paint(Graphics g) {
		Particle[] ps = getParticles();

		for (int i = 0; i < ps.length; i++) ps[i].draw(g);
	}

	public void keyTyped(KeyEvent ke) {
		// This reads a character
		char ch = ke.getKeyChar();
		System.out.println(ch);

		// Your job is to make these keys do something!

		switch (ch) {
			case 'r': 
				Particle[] ParticleList = getParticles();
				
				for (Particle particle: ParticleList) { if (particle.isAlive()){
					 particle.interrupt();
				}
				}
					int n = ParticleList.length; 

						Particle[] particlesNew = new Particle[n];
						for (int i = 0; i < n; i++) {
							particlesNew[i] = new Particle(200, 200);
							particlesNew[i].setName("Particle Thread " + i);
							particlesNew[i].start();
						}		
					
					setParticles(particlesNew);
				
					break;
			case 'n': 
				
				Particle[]  oldParticleList = getParticles();  // get old array of threads
				int  o  = oldParticleList.length + 1; // get length of old array
				
				Particle[] newParticleListArray = new Particle[o];// create new array with extra length
				
			
				
				for (int i = 0; i < oldParticleList.length; i++) {
					
					newParticleListArray[i] = oldParticleList[i];
				}		
				
				int positionEnd = newParticleListArray.length-1;
				newParticleListArray[positionEnd] = new Particle(200, 200);
				newParticleListArray[positionEnd].setName("Particle Thread " + positionEnd);
				newParticleListArray[positionEnd].start();
				
				setParticles(newParticleListArray);
				
				break;
			case 'd': Particle[] ParticleListDelete = getParticles();
					 
					  
					  if(ParticleListDelete.length != 0) {
						  Particle[] newDeleteList = new Particle[ ParticleListDelete.length -1];
						  for (int i = 0; i < newDeleteList.length; i++) {
							  newDeleteList[i] = ParticleListDelete[i];			
							}
						  
						  setParticles(newDeleteList);
					  }
				break;
			case 'q':  Particle[] quitParticles = getParticles();
					   Particle[] exitlist = new Particle[0];
						for (Particle particle2 : quitParticles) {
							if (particle2.isAlive()){
								 particle2.interrupt();
							}		
						}
						setParticles(exitlist);
						System.exit(0);
				break;
			default:
				break;
		}
	}

	public void keyPressed(KeyEvent ke) {

	}

	public void keyReleased(KeyEvent ke) {
		// Empty method
	}
}

