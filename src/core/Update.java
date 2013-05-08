package core;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class Update extends Thread{
	
	public static String status;
	public static String url = "https://dl.dropboxusercontent.com/u/77565661/Yet%20to%20be%20titled/games.zip";
	
	public void run() {
		try {
			status = "Downloading...";
			
			URL url = new URL(this.url);
			url.openConnection();
			InputStream reader = url.openStream();
				
			FileOutputStream writer = new FileOutputStream("game.zip");
			byte[] buffer = new byte[153600];
			int totalBytesRead = 0;
			int bytesRead = 0;
				
			while ((bytesRead = reader.read(buffer)) > 0) {
				writer.write(buffer, 0, bytesRead);
				buffer = new byte[153600];
				totalBytesRead += bytesRead;
			}
			
			writer.close();
			reader.close();
			
			status = "Extracting...";
			extract("game.zip");
			
			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
			status = "ERROR ERROR ERROR YOU DUN GOOFED";
		}
	}
	
	public void extract(String dir) {
		try {
			byte[] data = new byte[1000];
			int byteRead;
			
			ZipInputStream zin = new ZipInputStream(new BufferedInputStream(new FileInputStream(dir))); //read from the zip
			BufferedOutputStream bout = null; //writes the files zin reads
			ZipEntry entry;
			
			while((entry = zin.getNextEntry()) != null) { //loops for each entry in the zip
				byteRead = 0;
				data = new byte[1000];
				
				File file = new File(dir, entry.getName());
				if(entry.isDirectory()) {
					file.mkdirs();
				} else {
					bout = new BufferedOutputStream(new FileOutputStream(entry.getName()), 1000);
				}
				
				while((byteRead = zin.read(data, 0, 1000)) != -1) {
					bout.write(data, 0, byteRead);
				}
				
				bout.flush();
				bout.close();
			}
			
			zin.close();
			
			File file = new File("game.zip");
			file.deleteOnExit();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}