package gui;

import java.awt.Color;

import render.Fonts;
import core.Main;
import core.Update;

public class GuiMenuUpdate extends Gui {
	
	GuiButton buttonYes;
	GuiButton buttonNo;
	
	private boolean isUpdating;
	
	public GuiMenuUpdate() {
		super();
		
		buttonYes = new GuiButton(Main.screenWidth / 2 - 50, Main.screenHeight / 2 + 80, 100, 20, 1, 1, 1, "YES");
		buttonYes.setActivation(new Runnable() {
			public void run() {
				isUpdating = true;
				Update update = new Update();
				update.start();
			}
		});
		
		buttonNo = new GuiButton(Main.screenWidth / 2 - 50, Main.screenHeight / 2 + 120, 100, 20, 1, 1, 1, "NO");
		buttonNo.setActivation(new Runnable() {
			public void run() {
				setCurrentGui(new GuiMenuMain());
			}
		});
		
		addComponents(buttonYes, buttonNo);
		setComponentActive(buttonNo);
	}
	
	@Override
	public void renderText() {
		Fonts.drawCenteredString(Fonts.font_16_White, Main.screenWidth / 2, Main.screenHeight / 2 - 40, "Are you sure you want to update?");
	}
	
	@Override
	public void update(long delta) {
		if(!isUpdating) {
			super.update(delta);
		} else {
			Fonts.drawCenteredString(Fonts.font_16_White, Main.screenWidth / 2, Main.screenHeight / 2 - 40, Update.status);
		}
	}
}