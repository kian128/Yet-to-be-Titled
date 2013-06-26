package gui;

import render.Fonts;
import core.Main;

public class GuiMenuControls extends Gui {
	
	private GuiButton buttonBack;
	
	public GuiMenuControls() {
		buttonBack = new GuiButton(Main.screenWidth / 2 - 50, Main.screenHeight / 2 + 200, 100, 20, 1, 1, 1, "RETURN");
		buttonBack.setActivation(new Runnable() {
			public void run() {
				setCurrentGui(new GuiMenuOptions());
			}
		});
		
		addComponents(buttonBack);
		setComponentActive(buttonBack);
	}
	
	@Override
	public void renderText() {
		Fonts.drawString(Fonts.font_16_White, Main.screenWidth / 2 - 200, Main.screenHeight / 2 - 100, "Movement:");
		Fonts.drawString(Fonts.font_16_White, Main.screenWidth / 2 - 200, Main.screenHeight / 2 - 70, "Jump/Up:");
		Fonts.drawString(Fonts.font_16_White, Main.screenWidth / 2 - 200, Main.screenHeight / 2 - 40, "Crouch/Down:");
		Fonts.drawString(Fonts.font_16_White, Main.screenWidth / 2 - 200, Main.screenHeight / 2 - 10, "Toggle Flying:");
		Fonts.drawString(Fonts.font_16_White, Main.screenWidth / 2 - 200, Main.screenHeight / 2 + 20, "Place Crate:");
		Fonts.drawString(Fonts.font_16_White, Main.screenWidth / 2 - 200, Main.screenHeight / 2 + 50, "Toggle Dev Hud:");
		
		Fonts.drawString(Fonts.font_16_White, Main.screenWidth / 2 + 100, Main.screenHeight / 2 - 100, "W A S D");
		Fonts.drawString(Fonts.font_16_White, Main.screenWidth / 2 + 100, Main.screenHeight / 2 - 70, "SPACE");
		Fonts.drawString(Fonts.font_16_White, Main.screenWidth / 2 + 100, Main.screenHeight / 2 - 40, "SHIFT");
		Fonts.drawString(Fonts.font_16_White, Main.screenWidth / 2 + 100, Main.screenHeight / 2 - 10, "F");
		Fonts.drawString(Fonts.font_16_White, Main.screenWidth / 2 + 100, Main.screenHeight / 2 + 20, "ENTER");
		Fonts.drawString(Fonts.font_16_White, Main.screenWidth / 2 + 100, Main.screenHeight / 2 + 50, "F3");
	}
}