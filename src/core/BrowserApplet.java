package core;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Canvas;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;

public class BrowserApplet extends Applet {
	
	Canvas displayParent;
	Thread gameThread;
	Main main;
	
	private boolean running = false;
	
	public void init() {
		setLayout(new BorderLayout());
		try{
			displayParent = new Canvas() {
				public final void addNotify() {
					super.addNotify();
					startLWJGL();
				}
				public final void removeNotify() {
					stopLWJGL();
					super.removeNotify();
				}
			};
			displayParent.setSize(getWidth(), getHeight());
			add(displayParent);
			displayParent.setFocusable(true);
			displayParent.requestFocus();
			displayParent.setIgnoreRepaint(true);
			setVisible(true);
		} catch(Exception e) {
			System.err.println(e);
			System.exit(1);
		}
	}
	
	public void startLWJGL() {
		gameThread = new Thread() {
			public void run() {
				running = true;
				try {
					Display.setParent(displayParent);
					
					main = new Main();
					main.init();
				} catch(LWJGLException e) {
					e.printStackTrace();
					return;
				}
				while(running) {
					main.render();
					main.update();
				}
				main.exit();
			}
		};
		gameThread.start();
	}
	
	public void stopLWJGL() {
		running = false;
		try{
			gameThread.join();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		Display.destroy();
	}
	
	public void start() {
		
	}
	
	public void stop() {
		
	}
	
	public void destroy() {
		remove(displayParent);
		super.destroy();
	}
}
