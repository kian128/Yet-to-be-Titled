package render;

import java.awt.Font;
import java.io.InputStream;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.util.ResourceLoader;

import static org.lwjgl.opengl.GL11.*;

public class Fonts {
	
	private static UnicodeFont font16White;
	private static UnicodeFont font16Black;
	
	public void initFonts() {
		//java.awt.Font awtFont = new java.awt.Font("Monospaced Plain", java.awt.Font.PLAIN, 16);
		try {
			font16White = new UnicodeFont("res/fonts/font.ttf", 16, false, false);
			font16Black = new UnicodeFont("res/fonts/font.ttf", 16, false, false);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		initFont(font16White, java.awt.Color.white);
		initFont(font16Black, java.awt.Color.black);
	}
	
	private void initFont(UnicodeFont font, java.awt.Color color) {
		try {
			font.getEffects().add(new ColorEffect(color));
			font.addAsciiGlyphs();
			font.loadGlyphs();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void drawString(float x, float y, String color, String s) {
		if(color == "white") {
			font16White.drawString(x, y - 5, s);
		}
		if(color == "black") {
			font16Black.drawString(x, y - 5, s);
		}
	}
	
	public static void drawCenteredString(float x, float y, String color, String s) {
		if(color == "white") {
			float stringWidth = font16White.getWidth(s);
			float stringHeight = font16White.getHeight(s);
			font16White.drawString(x - stringWidth / 2, y - stringHeight / 2, s);
		}
		if(color == "black") {
			float stringWidth = font16Black.getWidth(s);
			float stringHeight = font16Black.getHeight(s);
			font16Black.drawString(x - stringWidth / 2, y - stringHeight / 2, s);
		}
	}
}