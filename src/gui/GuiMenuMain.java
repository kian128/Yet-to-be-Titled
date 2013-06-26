package gui;

import world.World;
import core.Main;
import core.SaveHandler;

public class GuiMenuMain extends Gui {
	
	SaveHandler saves = new SaveHandler();
	World world = new World();
	
	private GuiButton buttonStartNew;
	private GuiButton buttonStartLoad;
	private GuiButton buttonOptions;
	private GuiButton buttonUpdate;
	private GuiButton buttonExit;
	
	public GuiMenuMain() {
		buttonStartNew = new GuiButton(Main.screenWidth / 2 - 50, Main.screenHeight / 2 - 40, 100, 20, 1, 1, 1, "NEW");
		buttonStartNew.setActivation(new Runnable() {
			public void run() {
				//setCurrentGui(new GuiMenuCharCreation());
				world.loadNew();
				setCurrentGui(null); 
			}
		});
		
		buttonStartLoad = new GuiButton(Main.screenWidth / 2 - 50, Main.screenHeight / 2 - 0, 100, 20, 1, 1, 1, "LOAD");
		buttonStartLoad.setActivation(new Runnable() {
			public void run() {
				saves.load("res/save/save.txt");
				setCurrentGui(null);
				Main.isPaused = false;
			}
		});
		
		buttonOptions = new GuiButton(Main.screenWidth / 2 - 50, Main.screenHeight / 2 + 40, 100, 20, 1, 1, 1, "OPTIONS");
		buttonOptions.setActivation(new Runnable() {
			public void run() {
				setCurrentGui(new GuiMenuOptions());
			}
		});
		
		buttonUpdate = new GuiButton(Main.screenWidth / 2 - 50, Main.screenHeight / 2 + 80, 100, 20, 1, 1, 1, "UPDATE");
		buttonUpdate.setActivation(new Runnable() {
			public void run() {
				setCurrentGui(new GuiMenuUpdate());
			}
		});
		
		buttonExit = new GuiButton(Main.screenWidth / 2 - 50, Main.screenHeight / 2 + 120, 100, 20, 1, 1, 1, "EXIT");
		buttonExit.setActivation(new Runnable() {
			public void run() {
				System.exit(0);
			}
		});
		
		addComponents(buttonStartNew, buttonStartLoad, buttonOptions, buttonUpdate, buttonExit);
		setComponentActive(buttonStartNew);
	}
}