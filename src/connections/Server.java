package connections;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.lwjgl.opengl.Display;

import core.Main;
import core.World;

public class Server extends Thread {
	
	private int port = 1234;
	private String ip = "25.22.95.89";
	private int maxQueueLength = 4; //max queue length for incoming connections
	
	private DataInputStream in;
	private DataOutputStream out;
	
	public Server() throws IOException {
		ServerSocket server = new ServerSocket(port, maxQueueLength, InetAddress.getByName(ip));
		System.out.println("Server established on port " + port + " with ip " + ip);
		
		System.out.println("Waiting for incoming connection...");
		Socket socket = server.accept();
		System.out.println("Connection found with ip " + socket.getInetAddress().getHostAddress());
			
		in = new DataInputStream(socket.getInputStream());
		out = new DataOutputStream(socket.getOutputStream());
	}
	
	public void run() {
		while(true) {
			try {
				Main.npc.setX(in.readFloat());
				Main.npc.setY(in.readFloat());
				Main.npc.setZ(in.readFloat());
				
				out.writeFloat(Main.player.getX());
				out.writeFloat(Main.player.getY());
				out.writeFloat(Main.player.getZ());
			}catch(Exception e) {
				e.printStackTrace();
			}
		} 
	}
	
	public static void main(String args[]) throws IOException {
		Server server = new Server();
		
		Main main = new Main();
		World.spawnPoint.setX(2);
		main.init();
		
		server.start();
		
		while(!Display.isCloseRequested()) {
			main.render();
			main.update();
		}
		
		main.exit();
	}
}