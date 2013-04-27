package connections;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JOptionPane;

import org.lwjgl.opengl.Display;

import core.Main;

public class Client extends Thread {
	
	private DataInputStream in;
	private DataOutputStream out;
	
	public Client() throws IOException {
		String ip = JOptionPane.showInputDialog("Enter IP");
		int port = 1234;
		
		Socket socket = new Socket(ip, port);
		
		in = new DataInputStream(socket.getInputStream());
		out = new DataOutputStream(socket.getOutputStream());
	}
	
	public void run() {
		while(true) {
			try {
				out.writeFloat(Main.player.getX());
				out.writeFloat(Main.player.getY());
				out.writeFloat(Main.player.getZ());
			
				Main.npc.setX(in.readFloat());
				Main.npc.setY(in.readFloat());
				Main.npc.setZ(in.readFloat());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String args[]) throws IOException {
		Client client = new Client();
		
		Main main = new Main();
		main.init();
		
		client.start();
		
		while(!Display.isCloseRequested()) {
			main.render();
			main.update();
		}
		
		main.exit();
	}
}
