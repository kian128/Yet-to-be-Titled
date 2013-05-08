package gui;

import core.Main;
import core.Options;
import render.Fonts;
import render.Render2D;

public class GuiSlider extends GuiComponent {
	
	private Runnable slideUp, slideDown;
	
	private String id;
	
	private int graduations;
	private int startCounter;
	private int counter;
	private float intermediate;
	private float displayValue;
	private float initDisplayValue;
	
	private float tWidth = 10, tHeight = 10, tBorder = 4;
	
	Options options = new Options();
	
	public GuiSlider(String id, float x, float y, float width, float height, float red, float green, float blue, int graduations, int startCounter, float intermediate, float displayValue) {
		super(x, y, width, height, red, green, blue);
		
		this.id = id;
		
		this.graduations = graduations;
		this.startCounter = startCounter;
		this.counter = startCounter;
		this.intermediate = intermediate;
		this.displayValue = displayValue;
		this.initDisplayValue = displayValue;
		
		counter = options.getSliderOffset(id);
	}
	
	public void setSlides(Runnable slideUp, Runnable slideDown) {
		this.slideUp = slideUp;
		this.slideDown = slideDown;
	}
	
	@Override
	public void activateSliderUp() {
		if(counter >= 0 && counter < graduations) {
			displayValue += intermediate;
			counter++;
			options.setSliderOffset(id, counter);
			slideUp.run();
		}
	}
	
	@Override
	public void activateSliderDown() {
		if(counter > 0 && counter <= graduations) {
			displayValue -= intermediate;
			counter--;
			options.setSliderOffset(id, counter);
			slideDown.run();
		}
	}
	
	public void resetSlider(float displayValue) {
		this.counter = startCounter;
		this.displayValue = displayValue;
		options.setSliderOffset(id, startCounter);
	}
	
	@Override
	public void renderShapes() {
		Render2D.renderRectangle(x, y, width, height, red / 1.5f, green / 1.5f, blue / 1.5f);
		Render2D.renderRectangle(x + 2, y + 2, width - 4, height - 4, red, green, blue);
		
		for(int n = 0; n < graduations; n++) {
			Render2D.renderLine(x + n * (width / graduations), y, x + n * (width / graduations), y + height, 0.5f, 0.5f, 0.5f);
		}
		
		if(isActive) {
			Render2D.renderTriangleUniformDown(x + counter * (width / graduations) - tWidth / 2 - tBorder, y - tHeight - tBorder / 2, tWidth + tBorder * 2, tHeight + tBorder * 2, 0.5f, 0, 0);
			Render2D.renderTriangleUniformUp(x + counter * (width / graduations) - tWidth / 2 - tBorder, y + height - tBorder * (5f / 4f), tWidth + tBorder * 2, tHeight + tBorder * 2, 0.5f, 0, 0);
		}
		
		Render2D.renderTriangleUniformDown(x + counter * (width / graduations) - tWidth / 2, y - tHeight, tWidth, tHeight, 1, 1, 1);
		Render2D.renderTriangleUniformUp(x + counter * (width / graduations) - tWidth / 2, y + height, tWidth, tHeight, 1, 1, 1);
	}
	
	@Override
	public void renderText() {
		Fonts.drawCenteredString(x + width / 2, y + height / 2 - 1, "black", Float.toString(displayValue));
	}
}
