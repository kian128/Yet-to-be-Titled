package gui;

import java.lang.reflect.Method;

import core.Main;
import core.Update;

public class GuiMenuMain extends Gui {
	
	private GuiButton buttonStart;
	private GuiButton buttonOptions;
	private GuiButton buttonUpdate;
	private GuiButton buttonExit;
	
	public GuiMenuMain() {
		buttonStart = new GuiButton(Main.screenWidth / 2 - 50, Main.screenHeight / 2 - 40, 100, 20, 1, 1, 1, "START");
		buttonStart.setActivation(new Runnable() {
			public void run() {
				Main.gameState = Main.GameState.GAME_MAIN;
			}
		});
		
		buttonOptions = new GuiButton(Main.screenWidth / 2 - 50, Main.screenHeight / 2 + 10, 100, 20, 1, 1, 1, "OPTIONS");
		buttonOptions.setActivation(new Runnable() {
			public void run() {
				Main.gameState = Main.GameState.MENU_OPTIONS;
			}
		});
		
		buttonUpdate = new GuiButton(Main.screenWidth / 2 - 50, Main.screenHeight / 2 + 60, 100, 20, 1, 1, 1, "UPDATE");
		buttonUpdate.setActivation(new Runnable() {
			public void run() {
				Main.gameState = Main.GameState.MENU_UPDATE;
			}
		});
		
		buttonExit = new GuiButton(Main.screenWidth / 2 - 50, Main.screenHeight / 2 + 110, 100, 20, 1, 1, 1, "EXIT");
		buttonExit.setActivation(new Runnable() {
			public void run() {
				System.exit(0);
			}
		});
		
		addComponents(buttonStart, buttonOptions, buttonUpdate, buttonExit);
		setComponentActive(buttonStart);
	}
}