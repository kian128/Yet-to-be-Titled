package core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Options {
	
	public boolean vsync;
	public boolean resizable;
	private static int maxfps;
	private static int fov;
	public float zNear, zFar;
	public float cameraDistance;
	
	private static Properties config;
	private static Properties sliders;
	
	public void loadConfig() {
		config = new Properties();
		sliders = new Properties();
		try {
			config.load(new FileInputStream("res/config.txt"));
			sliders.load(new FileInputStream("res/data/sliders.txt"));
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
	
	public void setSliderOffset(String slider, int value) {
		sliders.setProperty(slider, Integer.toString(value));
		try {
			sliders.store(new FileOutputStream("res/data/sliders.txt"), null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getSliderOffset(String slider) {
		return Integer.parseInt(sliders.getProperty(slider));
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
	
	public void setProperty(String property, String value) {
		config.setProperty(property, value);
		try {
			config.store(new FileOutputStream("res/config.txt"), null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setDefault() {
		vsync = false;
		resizable = false;
		maxfps = 120;
		fov = 55;
		zNear = 0.01f;
		zFar = 100f;
		cameraDistance = 20;
		
		setProperty("vsync", Boolean.toString(vsync));
		setProperty("resizable", Boolean.toString(resizable));
		setProperty("maxfps", Integer.toString(maxfps));
		setProperty("fov", Integer.toString(fov));
		setProperty("zNear", Float.toString(zNear));
		setProperty("zFar", Float.toString(zFar));
		setProperty("cameraDistance", Float.toString(cameraDistance));
	}
}