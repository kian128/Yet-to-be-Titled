package connections;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerOld {
	
	private int port = 1234;
	private String ip = "localhost";
	private int maxQueueLength = 4; //max queue length for incoming connections
	
	public static int numberOfPlayers = 0;
	
	public ServerOld() throws IOException {
		ServerSocket server = new ServerSocket(port, maxQueueLength, InetAddress.getByName(ip));
		System.out.println("Server established on port " + port + " with ip " + ip);
		
		while(true) { 
			System.out.println("Waiting for incoming connection...");
			Socket socket = server.accept();
			System.out.println("Connection found with ip " + socket.getInetAddress().getHostAddress());
			
			DataInputStream in = new DataInputStream(socket.getInputStream());
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			
			numberOfPlayers++;
			String name = in.readUTF();
			out.writeInt(numberOfPlayers);
			
			new Connections(name, numberOfPlayers, in, out);
		}
	}
	
	public static void main(String args[]) throws IOException {
		new ServerOld();
	}
}

class Connections extends Thread {
	
	float x, y, z;
	String name;
	int id;
	
	DataInputStream in;
	DataOutputStream out;
	
	public Connections(String name, int id, DataInputStream in, DataOutputStream out) {
		x = 0;
		y = 0;
		z = 0;
		
		this.name = name;
		this.id = id;
		this.in = in;
		this.out = out;
		
		start();
	}
	
	public void run() {
		while(true) {
			try{
			for(int n = 0; n < ServerOld.numberOfPlayers; n++) {
				int id = in.readInt();
				float x = in.readFloat();
				float y = in.readFloat();
				float z = in.readFloat();
				
				if(id == this.id) {
					this.x = x;
					this.y = y;
					this.z = z;
				}
			}
			for(int n = 0; n < ServerOld.numberOfPlayers; n++) {
				out.writeInt(id);
				out.writeFloat(x);
				out.writeFloat(y);
				out.writeFloat(z);
			}
			} catch(Exception e) {
				
			}
		}
	}
}
