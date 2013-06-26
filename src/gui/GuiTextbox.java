package gui;

import java.awt.Color;

import core.Main;
import render.Fonts;
import render.Render2D;

public class GuiTextbox extends GuiComponent {
	
	private StringBuilder startText;
	private StringBuilder text;
	private int charLimit;
	
	private int tick;
	
	private float activeBorderWidth = 2;
	private float activeBorderRed = 0.5f, activeBorderGreen = 0, activeBorderBlue = 0;

	public GuiTextbox(float x, float y, float width, float height, float red, float green, float blue, String startText, int charLimit) {
		super(x, y, width, height, red, green, blue);
		
		this.startText = new StringBuilder(startText);
		this.charLimit = charLimit;
		text = new StringBuilder("");
	}
	
	@Override
	public void appendString(String s) {
		if(text.length() <= charLimit) {
			text.append(s);
		}
	}
	
	@Override
	public void shortenString(int n) {
		if(text.length() != 0) {
			text.setLength(text.length() - n);
		}
	}
	
	@Override
	public void clearString() {
		text.setLength(0);
	}
	
	@Override
	public String getText() {
		return text.toString();
	}

	@Override
	protected void renderShapes(long delta) {
		if(isActive) {
			Render2D.renderRectangle(x - activeBorderWidth, y - activeBorderWidth, width + activeBorderWidth * 2, height + activeBorderWidth * 2, activeBorderRed, activeBorderGreen, activeBorderBlue, 1);
		}
		
		Render2D.renderRectangle(x, y, width, height, red / 2, green / 2, blue / 2, 1);
		Render2D.renderRectangle(x + 2, y + 2, width - 4, height - 4, red, green, blue, 1);
		
		float stringWidth = Fonts.font_16_Black.getWidth(text);
		float stringHeight = Fonts.font_16_Black.getHeight(text);
		
		tick += delta / 5;
		if(tick <= 50 && isActive && text.length() != 0) Render2D.renderRectangle(x + width / 2 + stringWidth / 2, y + height - 5, 10, 2, 0, 0, 0, 0.8f);
		if(tick > 100) tick = 0;
	}
	
	@Override
	protected void renderText(long delta) {
		if(text.length() > 0) {
			Fonts.drawCenteredString(Fonts.font_16_Black, x + width / 2, y + 3, text.toString());
		} else {
			Fonts.drawCenteredString(Fonts.font_16_Gray, x + width / 2, y + 3, startText.toString());
		}
	}
}