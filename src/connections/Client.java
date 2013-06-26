package connections;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glVertex2f;

import java.awt.List;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Client {
	
	private static int screenWidth = 400, screenHeight = 400;
	
	public static int playerX = 200, playerY = 200;
	
	private static DatagramSocket socket = null;
	private static ClientThread clientThread;
	
	public static ArrayList<Player> connectedPlayers = new ArrayList<Player>();
	
	public static int tick = 0;
	
	public static void main(String args[]) {
		initClient();
		
		try {
			String ip = JOptionPane.showInputDialog("Enter IP");
			String username = JOptionPane.showInputDialog("Enter username");
			int port = 1234;
			
			socket = new DatagramSocket();
			clientThread = new ClientThread(socket, username, ip, port);
			clientThread.start();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		while(!Display.isCloseRequested()) {
			update();
			tick++;
			if(tick > 5000) {
				System.err.println("timed out");
				System.exit(0);
			}
		}
		
		while(clientThread.isAlive()) {
			clientThread.running = false;
		}
		
		socket.close();
	}
	
	private static void initClient() {
		try {
			Display.setDisplayMode(new DisplayMode(screenWidth, screenHeight));
			Display.setTitle("Multiplayer Game Test");
			Display.create();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, screenWidth, screenHeight, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
	}
	
	private static void update() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		//render code
		glBegin(GL_QUADS);
			glVertex2f(playerX, playerY);
			glVertex2f(playerX, playerY + 20);
			glVertex2f(playerX + 20, playerY + 20);
			glVertex2f(playerX + 20, playerY);
		glEnd();
		
		for(int n = 0; n < connectedPlayers.size(); n++) {
			glBegin(GL_QUADS);
				glVertex2f(connectedPlayers.get(n).x, connectedPlayers.get(n).y);
				glVertex2f(connectedPlayers.get(n).x, connectedPlayers.get(n).y + 20);
				glVertex2f(connectedPlayers.get(n).x + 20, connectedPlayers.get(n).y + 20);
				glVertex2f(connectedPlayers.get(n).x + 20, connectedPlayers.get(n).y);
			glEnd();
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_W)) playerY -= 2;
		if(Keyboard.isKeyDown(Keyboard.KEY_S)) playerY += 2;
		if(Keyboard.isKeyDown(Keyboard.KEY_A)) playerX -= 2;
		if(Keyboard.isKeyDown(Keyboard.KEY_D)) playerX += 2;
		
		if(playerX < 0) playerX = 0;
		if(playerX > screenWidth - 20) playerX = screenWidth - 20;
		if(playerY < 0) playerY = 0;
		if(playerY > screenHeight - 20) playerY = screenHeight - 20;
		
		Display.sync(120);
		Display.update();
	}
}

class ClientThread extends Thread {
	
	private DatagramSocket socket = null;
	private String ip;
	private int port;
	private String username;
	
	public boolean running = true;
	
	public ClientThread(DatagramSocket socket, String username, String ip, int port) {
		this.socket = socket;
		this.ip = ip;
		this.port = port;
		this.username = username;
	}
	
	public void run() {
		try {
			sendAction("join");
		} catch(Exception e) {
			e.printStackTrace();
			running = false;
		}
		
		while(running) {
			try {
				sendPacket();
				receivePacket();
			} catch(Exception e) {
				e.printStackTrace();
				running = false;
			}
		}
		
		try {
			sendAction("leave");
		} catch (IOException e) {
			e.printStackTrace();
		}
		socket.close();
	}
	
	private void sendAction(String action) throws IOException {
		String message = action + " " + username;
		byte[] buf = message.getBytes();
		InetAddress address = InetAddress.getByName(ip);
		DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port);
		socket.send(packet);
	}
	
	private void sendPacket() throws IOException {
		String message = "position " + username + " " + Client.playerX + " " + Client.playerY;
		byte[] buf = message.getBytes();
		InetAddress address = InetAddress.getByName(ip);
		DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port);
		socket.send(packet);
		Client.tick = 0;
	}
	
	private void receivePacket() throws IOException {
		byte[] buf = new byte[256];
		DatagramPacket packet = new DatagramPacket(buf, buf.length);
		
		long start = System.currentTimeMillis();
		socket.receive(packet);
		long end = System.currentTimeMillis();
		
		long latency = end - start;
		System.out.println(latency);
		
		String received = new String(packet.getData(), 0, packet.getLength());
		
		Client.connectedPlayers.clear();
		String[] playerData = received.split("/");
		
		for(int n = 0; n < playerData.length; n++) {
			String username = playerData[n].split(" ")[0];
			int x = Integer.parseInt(playerData[n].split(" ")[1]);
			int y = Integer.parseInt(playerData[n].split(" ")[2]);
			
			if(!username.equals(this.username)) {
				Client.connectedPlayers.add(new Player(username, x, y));
			}
		}
	}
}
