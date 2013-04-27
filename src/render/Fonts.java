package render;

import java.awt.Font;
import java.io.InputStream;

import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.util.ResourceLoader;

public class Fonts {
	
	private static UnicodeFont font;
	
	public void initFonts() {
		//java.awt.Font awtFont = new java.awt.Font("Monospaced Plain", java.awt.Font.PLAIN, 16);
		try {
			font = new UnicodeFont("res/fonts/font.ttf", 16, false, false);
			font.getEffects().add(new ColorEffect(java.awt.Color.white));
			font.addAsciiGlyphs();
			font.loadGlyphs();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void drawString(float x, float y, String s) {
		font.drawString(x, y - 5, s);
	}
	
	public static void drawCenteredString(float x, float y, String s) {
		float stringWidth = font.getWidth(s);
		float stringHeight = font.getHeight(s);
		font.drawString(x - stringWidth / 2, y - stringHeight/ 2 - 5, s);
	}
}