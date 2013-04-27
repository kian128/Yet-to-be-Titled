package core;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Options {
	
	public boolean vsync;
	public boolean resizable;
	public int maxfps;
	public int fov;
	public float zNear, zFar;
	public float cameraDistance;
	
	public void loadConfig() {
		Properties config = new Properties();
		try {
			config.load(new FileInputStream("res/config.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		vsync = Boolean.parseBoolean(config.getProperty("vsync"));
		resizable = Boolean.parseBoolean(config.getProperty("resizable"));
		maxfps = Integer.parseInt(config.getProperty("maxfps"));
		fov = Integer.parseInt(config.getProperty("fov"));
		zNear = Float.parseFloat(config.getProperty("renderNear"));
		zFar = Float.parseFloat(config.getProperty("renderFar"));
		cameraDistance = Float.parseFloat(config.getProperty("cameraDistance"));
	}
	
	public void setDefault() {
		vsync = true;
		resizable = false;
		maxfps = 60;
		fov = 75;
		zNear = 0.001f;
		zFar = 100f;
		cameraDistance = 20;
	}
}