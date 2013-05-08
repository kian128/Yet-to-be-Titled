package gui;

import render.Fonts;
import core.Main;
import core.Update;

public class GuiMenuUpdate extends Gui {
	
	GuiButton buttonYes;
	GuiButton buttonNo;
	
	private boolean isUpdating;
	
	public GuiMenuUpdate() {
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
				Main.gameState = Main.GameState.MENU_MAIN;
			}
		});
		
		addComponents(buttonYes, buttonNo);
		setComponentActive(buttonNo);
	}
	
	@Override
	public void renderText() {
		Fonts.drawCenteredString(Main.screenWidth / 2, Main.screenHeight / 2 - 40, "white", "Are you sure you want to update?");
	}
	
	@Override
	public void update() {
		if(!isUpdating) {
			super.update();
		} else {
			Fonts.drawCenteredString(Main.screenWidth / 2, Main.screenHeight / 2 - 40, "white", Update.status);
		}
	}
}