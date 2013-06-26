package connections;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

public class Server {
	
	public static void main(String args[]) {
		new ServerThread().start();
	}
}

class ServerThread extends Thread {
	
	private DatagramSocket socket = null;
	private DatagramPacket packet = null;
	
	private boolean running = true;
	
	private ArrayList<Player> playerList = new ArrayList<Player>();
	
	public ServerThread() {
		try {
			socket = new DatagramSocket(1234, InetAddress.getByName("25.22.95.89"));
			System.out.println("Started server on port " + socket.getLocalPort());
		} catch(Exception e) {
			e.printStackTrace();
			running = false;
		}
	}
	
	public void run() {
		while(running) {
			try {
				receivePacket();
				sendPacket();
			} catch(Exception e) {
				e.printStackTrace();
				running = false;
			}
		}
		
		socket.close();
	}
	
	private void receivePacket() throws IOException {
		byte[] buf = new byte[256];
		packet = new DatagramPacket(buf, buf.length);
		socket.receive(packet);
		
		String received = new String(packet.getData(), 0, packet.getLength());
		
		if(received.split(" ")[0].equals("join")) {
			playerList.add(new Player(received.split(" ")[1], 0, 0));
			System.out.println(received.split(" ")[1] + " joined");
		}
		
		if(received.split(" ")[0].equals("leave")) {
			for(int n = 0; n < playerList.size(); n++) {
				if(playerList.get(n).name.equals(received.split(" ")[1])) {
					playerList.remove(n);
					System.out.println(received.split(" ")[1] + " left");
				}
			}
		}
		
		if(received.split(" ")[0].equals("position")) {
			for(int n = 0; n < playerList.size(); n++) {
				if(playerList.get(n).name.equals(received.split(" ")[1])) {
					playerList.get(n).x = Integer.parseInt(received.split(" ")[2]);
					playerList.get(n).y = Integer.parseInt(received.split(" ")[3]);
				}
			}
		}
		
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void sendPacket() throws IOException {
		String message = "";
		for(int n = 0; n < playerList.size(); n++) {
			message += (playerList.get(n).name + " " + playerList.get(n).x + " " + playerList.get(n).y + "/");
		}
		byte[] buf = message.getBytes();
		
		InetAddress address = packet.getAddress();
		int port = packet.getPort();
		packet = new DatagramPacket(buf, buf.length, address, port);
		socket.send(packet);
	}
}
