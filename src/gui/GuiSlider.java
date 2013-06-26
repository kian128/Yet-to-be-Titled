package gui;

import java.awt.Color;
import java.lang.reflect.Array;
import java.util.Arrays;

import org.lwjgl.opengl.Display;

import core.Main;
import core.Options;
import render.Fonts;
import render.Render2D;

public class GuiSlider extends GuiComponent {
	
	private int graduations;
	private int counter;
	
	private float[] valuesF;
	private Object[] valuesObj;
	
	public Runnable setDisplayValue;
	public String displayValue;
	
	private float tWidth = 10, tHeight = 10, tBorder = 4;
	
	public GuiSlider(float x, float y, float width, float height, float red, float green, float blue, int graduations, float[] array, float startValue) {
		super(x, y, width, height, red, green, blue);
		this.graduations = graduations;
		this.valuesF = array;
		displayValue = Float.toString(startValue);
		checkCounterPosition(new Float(displayValue));
	}
	
	public GuiSlider(float x, float y, float width, float height, float red, float green, float blue, int graduations, Object[] array, Object startObject) {
		super(x, y, width, height, red, green, blue);
		this.graduations = graduations;
		this.valuesObj = array;
		displayValue = startObject.toString();
		checkCounterPosition(startObject);
	}
	
	public static float[] getFloatsAsArray(float min, float max, float graduationSize) {
		int arraySize = (int) ((max - min) / graduationSize + 1);
		float[] array = new float[arraySize];
		for(int n = 0; n < arraySize; n++) {
			array[n] = min + n * graduationSize;
		}
		
		return array;
	}
	
	public static Object[] getObjectsAsArray(Object ... objects) {
		return objects;
	}
	
	public void setDisplayValue(Runnable r) {
		setDisplayValue = r;
	}
	
	public void checkCounterPosition(Object value) {
		if(value.getClass() == Float.class) {
			for(int n = 0; n < valuesF.length; n++) {
				if(Float.toString(valuesF[n]).equals(value.toString())) {
					displayValue = value.toString();
					counter = n;
				}
			}
		} else {
			for(int n = 0; n < valuesObj.length; n++) {
				if(valuesObj[n].toString().equals(value.toString())) {
					displayValue = value.toString();
					counter = n;
				}
			}
		}
	}
	
	@Override
	public void activateSliderUp() {
		if(valuesF != null) {
			if(counter < valuesF.length - 1) {
				counter++;
				displayValue = Float.toString(valuesF[counter]);
			}
		} else {
			if(counter < valuesObj.length - 1) {
				counter++;
				displayValue = valuesObj[counter].toString();
			}
		}
		setDisplayValue.run();
	}
	
	@Override
	public void activateSliderDown() {
		if(counter > 0) {
			if(valuesF != null) {
				counter--;
				displayValue = Float.toString(valuesF[counter]);
			} else {
				counter--;
				displayValue = valuesObj[counter].toString();
			}
		}
		setDisplayValue.run();
	}
	
	public void setSliderManual(Object o) {
		displayValue = o.toString();
		setDisplayValue.run();
		checkCounterPosition(o);
	}
	
	@Override
	public void renderShapes(long delta) {
		Render2D.renderRectangle(x, y, width, height, red / 1.5f, green / 1.5f, blue / 1.5f, 1);
		Render2D.renderRectangle(x + 2, y + 2, width - 4, height - 4, red, green, blue, 1);
		
		for(int n = 0; n < graduations; n++) {
			Render2D.renderLine(x + n * (width / graduations), y, x + n * (width / graduations), y + height, 0.5f, 0.5f, 0.5f, 1);
		}
		
		if(isActive) {
			Render2D.renderTriangleUniformDown(x + counter * (width / graduations) - tWidth / 2 - tBorder, y - tHeight - tBorder / 2, tWidth + tBorder * 2, tHeight + tBorder * 2, 0.5f, 0, 0, 1);
			Render2D.renderTriangleUniformUp(x + counter * (width / graduations) - tWidth / 2 - tBorder, y + height - tBorder * (5f / 4f), tWidth + tBorder * 2, tHeight + tBorder * 2, 0.5f, 0, 0, 1);
		}
		
		Render2D.renderTriangleUniformDown(x + counter * (width / graduations) - tWidth / 2, y - tHeight, tWidth, tHeight, 1, 1, 1, 1);
		Render2D.renderTriangleUniformUp(x + counter * (width / graduations) - tWidth / 2, y + height, tWidth, tHeight, 1, 1, 1, 1);
	}
	
	@Override
	public void renderText(long delta) {
		Fonts.drawCenteredString(Fonts.font_16_Black, x + width / 2, y + 3, displayValue);
	}
}
