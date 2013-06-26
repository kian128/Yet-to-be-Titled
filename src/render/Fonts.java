package render;

import java.awt.Color;
import java.util.ArrayList;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.font.effects.Effect;
import org.newdawn.slick.font.effects.ShadowEffect;

public class Fonts {
	
	public static UnicodeFont font_16_Black;
	public static UnicodeFont font_16_Gray;
	public static UnicodeFont font_16_White;
	
	public void initFonts() {
		//java.awt.Font awtFont = new java.awt.Font("Monospaced Plain", java.awt.Font.PLAIN, 16);
		try {
			font_16_Black = new UnicodeFont("res/fonts/font.ttf", 16, false, false);
			font_16_Gray = new UnicodeFont("res/fonts/font.ttf", 16, false, false);
			font_16_White = new UnicodeFont("res/fonts/font.ttf", 16, false, false);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		initFont(font_16_Black, new ColorEffect(Color.black));
		initFont(font_16_Gray, new ColorEffect(Color.gray));
		initFont(font_16_White, new ColorEffect(Color.white));
	}
	
	private static void initFont(UnicodeFont font, Effect ... effects) {
		try {
			for(int n = 0; n < effects.length; n++) {
				font.getEffects().add(effects[n]);
			}
			font.addAsciiGlyphs();
			font.loadGlyphs();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void drawString(UnicodeFont font, float x, float y, String s) {
		font.drawString(x, y, s);
	}
	
	public static void drawCenteredString(UnicodeFont font, float x, float y, String s) {
		float stringWidth = font.getWidth(s);
		font.drawString(x - stringWidth / 2, y, s);
	}
}