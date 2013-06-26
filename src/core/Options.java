package core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Options {
	
	private static boolean vsync;
	public boolean resizable;
	private static int maxfps;
	private static boolean lockedfps;
	private static int fov;
	public float zNear, zFar;
	public float cameraDistance;
	
	public boolean dvsync = false;
	public boolean dresizable = false;
	public static int dmaxfps = 60;
	public static boolean dLockedFramerate = false;
	public static int dfov = 45;
	public float dzNear = 0.01f, dzFar = 100f;
	public float dcameraDistance = 19;
	
	private static Properties config;
	
	public void loadConfig() {
		config = new Properties();
		try {
			config.load(new FileInputStream("res/config.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		vsync = Boolean.parseBoolean(config.getProperty("vsync"));
		resizable = Boolean.parseBoolean(config.getProperty("resizable"));
		maxfps = Integer.parseInt(config.getProperty("maxfps"));
		lockedfps = Boolean.parseBoolean(config.getProperty("lockedfps"));
		fov = Integer.parseInt(config.getProperty("fov"));
		zNear = Float.parseFloat(config.getProperty("renderNear"));
		zFar = Float.parseFloat(config.getProperty("renderFar"));
		cameraDistance = Float.parseFloat(config.getProperty("cameraDistance"));
	}
	
	public int getFov() {
		return fov;
	}
	public void setFov(int fov) {
		this.fov = fov;
		setProperty("fov", Integer.toString(fov));
	}
	
	public int getMaxfps() {
		return maxfps;
	}
	public void setMaxfps(int maxfps) {
		this.maxfps = maxfps;
		setProperty("maxfps", Integer.toString(maxfps));
	}
	
	public boolean getLockedfps() {
		return lockedfps;
	}
	public void setLockedfps(boolean lockedfps) {
		this.lockedfps = lockedfps;
		setProperty("lockedfps", Boolean.toString(lockedfps));
	}
	
	public boolean getVsync() {
		return vsync;
	}
	public void setVsync(boolean vsync) {
		this.vsync = vsync;
		setProperty("vsync", Boolean.toString(vsync));
	}
	
	public void setProperty(String property, String value) {
		config.setProperty(property, value);
		try {
			config.store(new FileOutputStream("res/config.txt"), null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setDefault() {
		vsync = dvsync;
		resizable = dresizable;
		maxfps = dmaxfps;
		lockedfps = dLockedFramerate;
		fov = dfov;
		zNear = dzNear;
		zFar = dzFar;
		cameraDistance = dcameraDistance;
		
		setProperty("vsync", Boolean.toString(vsync));
		setProperty("resizable", Boolean.toString(resizable));
		setProperty("maxfps", Integer.toString(maxfps));
		setProperty("lockedFramerate", Boolean.toString(lockedfps));
		setProperty("fov", Integer.toString(fov));
		setProperty("zNear", Float.toString(zNear));
		setProperty("zFar", Float.toString(zFar));
		setProperty("cameraDistance", Float.toString(cameraDistance));
	}
}