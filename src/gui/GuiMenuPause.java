package gui;

import render.Render2D;
import world.World;
import core.Main;
import core.SaveHandler;

public class GuiMenuPause extends Gui {
	
	SaveHandler saves = new SaveHandler();
	
	private GuiButton buttonResume;
	private GuiButton buttonSave;
	private GuiButton buttonExit;
	
	public GuiMenuPause() {
		super();
		
		buttonResume = new GuiButton(Main.screenWidth / 2 - 50, Main.screenHeight / 2 - 40, 100, 20, 1, 1, 1, "RESUME");
		buttonResume.setActivation(new Runnable() {
			public void run() {
				setCurrentGui(null);
				Main.isPaused = false;
			}
		});
		
		buttonSave = new GuiButton(Main.screenWidth / 2 - 50, Main.screenHeight / 2 + 10, 100, 20, 1, 1, 1, "SAVE");
		buttonSave.setActivation(new Runnable() {
			public void run() {
				saves.save("res/save/save.txt");
				System.out.println("Saved to res/save/save.txt");
			}
		});
		
		buttonExit = new GuiButton(Main.screenWidth / 2 - 50, Main.screenHeight / 2 + 60, 100, 20, 1, 1, 1, "QUIT");
		buttonExit.setActivation(new Runnable() {
			public void run() {
				setCurrentGui(new GuiMenuMain());
				World.mobList.clear();
				World.terrainList.clear();
				World.collidableList.clear();
			}
		});
		
		addComponents(buttonResume, buttonSave, buttonExit);
		setComponentActive(buttonResume);
	}
	
	@Override
	public void renderShapes() {
		Render2D.renderRectangle(0, 0, Main.screenWidth, Main.screenHeight, 0, 0, 0, 0.2f);
	}
}