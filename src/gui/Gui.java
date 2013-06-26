package gui;

import java.util.ArrayList;
import java.util.Arrays;

import org.lwjgl.input.Keyboard;

import core.Main;

import render.Render2D;

public class Gui {
	
	private GuiComponent[] components;
	
	public GuiComponent componentActive;
	private int componentActiveId;
	
	public static Gui guiCurrent;
	
	public static void updateGui(long delta) {
		if(guiCurrent != null) {
			guiCurrent.update(delta);
		}
	}
	
	public static void setCurrentGui(Gui gui) {
		guiCurrent = gui;
		
		if(gui == null) {
			Main.isPaused = false;
		}
	}
	
	public void addComponents(GuiComponent ... components) {
		this.components = components;
		this.componentActive = components[0];
		this.componentActive.isActive = true;
	}
	
	public void update(long delta) {
		guiCurrent = this;
	
		this.renderShapes();
		for(int n = 0; n < components.length; n++) {
			components[n].renderShapes(delta);
		}
		
		this.renderText();
		for(int n = 0; n < components.length; n++) {
			components[n].renderText(delta);
		}
	}
	
	public void renderShapes() {
	}
	
	public void renderText() {
	}
	
	public void setComponentActive(GuiComponent componentActive) {
		for(int n = 0; n < components.length; n++) {
			if(components[n] == componentActive) {
				componentActiveId = n;
				break;
			}
		}
		
		this.componentActive.isActive = false;
		this.componentActive = componentActive;
		this.componentActive.isActive = true;
	}
	
	public void selectComponentUp() {
		if(componentActiveId > 0) {
			componentActiveId--;
			setComponentActive(components[componentActiveId]);
		}
	}
	
	public void selectComponentDown() {
		if(componentActiveId < components.length - 1) {
			componentActiveId++;
			setComponentActive(components[componentActiveId]);
		}
	}
	
	public void activateButton() {
		if(componentActive.getClass() == GuiButton.class) {
			componentActive.activateButton();
		}
	}
	
	public void activateSliderUp() {
		if(componentActive.getClass() == GuiSlider.class) {
			componentActive.activateSliderUp();
		}
	}
	
	public void activateSliderDown() {
		if(componentActive.getClass() == GuiSlider.class) {
			componentActive.activateSliderDown();
		}
	}
	
	public void appendString(String s) {
		if(componentActive.getClass() == GuiTextbox.class) {
			componentActive.appendString(s);
		}
	}
	
	public void shortenString(int n) {
		if(componentActive.getClass() == GuiTextbox.class) {
			componentActive.shortenString(n);
		}
	}
	
	public void clearString() {
		if(componentActive.getClass() == GuiTextbox.class) {
			componentActive.clearString();
		}
	}
}