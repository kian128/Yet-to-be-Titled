package gui;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import render.Fonts;
import render.Render2D;

public class GuiButton extends GuiComponent {
	
	private String s;
	private int texture;
	
	private Runnable r;
	
	private float activeBorderWidth = 4;
	private float activeBorderRed = 0.5f, activeBorderGreen = 0, activeBorderBlue = 0;

	public GuiButton(float x, float y, float width, float height, float red, float green, float blue, String s) {
		super(x, y, width, height, red, green, blue);
		
		this.s = s;
	}
	
	public GuiButton(float x, float y, float width, float height, int texture, String s) {
		super(x, y, width, height, 0, 0, 0);
		
		this.texture = texture;
		this.s = s;
	}
	
	@Override
	public void activateButton() {
		r.run();
	}
	
	public void setActivation(Runnable r) {
		this.r = r;
	}
	
	@Override
	public void renderShapes() {
		if(isActive) {
			Render2D.renderRectangle(x - activeBorderWidth, y - activeBorderWidth, width + activeBorderWidth * 2, height + activeBorderWidth * 2, activeBorderRed, activeBorderGreen, activeBorderBlue);
		}
		if(texture == 0) {
			Render2D.renderRectangle(x, y, width, height, red / 1.5f, green / 1.5f, blue / 1.5f);
			Render2D.renderRectangle(x + 2, y + 2, width - 4, height - 4, red, green, blue);
		} else {
			Render2D.renderTexturedRectangle(x, y, width, height, texture);
		}
	}
	
	@Override
	public void renderText() {
		Fonts.drawCenteredString(x + width / 2, y + height / 2 - 1, "black", s);
	}
}
