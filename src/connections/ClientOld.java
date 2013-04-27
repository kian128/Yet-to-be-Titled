package connections;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import render.ModelCube;

import core.Main;
import entity.EntityMob;

public class ClientOld extends Thread {
	
	private int port = 1234;
	private String ip = "localhost";
	private int id;
	
	DataInputStream in;
	DataOutputStream out;
	
	int numberOfPlayers = 1;
	
	ArrayList<EntityMob> players = new ArrayList<EntityMob>();
	
	public ClientOld() throws IOException {
		Socket socket = new Socket(ip, port);
		
		in = new DataInputStream(socket.getInputStream());
		out = new DataOutputStream(socket.getOutputStream());
		
		String name = JOptionPane.showInputDialog("Enter Name");
		out.writeUTF(name);
		id = in.readInt();
	}
	
	public void sendCoords() throws IOException {
		out.writeInt(id);
		out.writeFloat(Main.player.getX());
		out.writeFloat(Main.player.getY());
		out.writeFloat(Main.player.getZ());
	}
	
	public void receiveCoords() throws IOException {
		for(int n = 0; n < numberOfPlayers; n++) {
			int id = in.readInt();
			float x = in.readFloat();
			float y = in.readFloat();
			float z = in.readFloat();
			
			if(numberOfPlayers < id) {
				numberOfPlayers = id;
				for(int m = 0; m < (id - numberOfPlayers); m++) {
					EntityMob player = new EntityMob(0, 0, 0, 1, 1, 1, false, new ModelCube(), new Vector3f(1, 0, 0), 0.005f);
					players.add(player);
				}
			}
			
			if(id == n) {
				players.get(n).setX(x);
				players.get(n).setY(y);
				players.get(n).setZ(z);
			}
		}
	}
	
	public void run() {
		while(true) {
			try {
				sendCoords();
				receiveCoords();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
}
	
	public static void main(String args[]) throws IOException {
		Main main = new Main();
		main.init();
		
		ClientOld client = new ClientOld();
		client.start();
		
		while(!Display.isCloseRequested()) {
			main.render();
			main.update();
		}
		
		main.exit();
	}
}
