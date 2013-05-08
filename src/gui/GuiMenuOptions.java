package gui;

import render.Fonts;
import core.Main;
import core.Options;

public class GuiMenuOptions extends Gui {
	
	private GuiButton buttonBack;
	private GuiButton buttonDefault;
	private GuiSlider sliderFov;
	private GuiSlider sliderfps;
	
	Options options = new Options();
	
	public GuiMenuOptions() {
		sliderFov = new GuiSlider("options_fov", Main.screenWidth / 2, Main.screenHeight / 2 - 100, 200, 20, 1, 1, 1, 20, 10, 1, options.getFov());
		sliderFov.setSlides(new Runnable() {
			public void run() {
				options.setFov(options.getFov() + 1);
			}
		}, new Runnable() {
			public void run() {
				options.setFov(options.getFov() - 1);
			}
		});
		
		sliderfps = new GuiSlider("options_fps", Main.screenWidth / 2, Main.screenHeight / 2 - 50, 200, 20, 1, 1, 1, 40, 20, 2, options.getMaxfps());
		sliderfps.setSlides(new Runnable() {
			public void run() {
				options.setMaxfps(options.getMaxfps() + 2);
			}
		}, new Runnable() {
			public void run() {
				options.setMaxfps(options.getMaxfps() - 2);
			}
		});
		
		buttonBack = new GuiButton(Main.screenWidth / 2 - 50, Main.screenHeight / 2 + 200, 100, 20, 1, 1, 1, "RETURN");
		buttonBack.setActivation(new Runnable() {
			public void run() {
				Main.gameState = Main.GameState.MENU_MAIN;
			}
		});
		
		buttonDefault = new GuiButton(Main.screenWidth / 2 - 50, Main.screenHeight / 2 + 240, 100, 20, 1, 1, 1, "DEFAULT");
		buttonDefault.setActivation(new Runnable() {
			public void run() {
				options.setDefault();
				sliderFov.resetSlider(options.getFov());
				sliderfps.resetSlider(options.getMaxfps());
			}
		});
		
		addComponents(sliderFov, sliderfps, buttonBack, buttonDefault);
		setComponentActive(buttonBack);
	}
	
	@Override
	public void renderText() {
		Fonts.drawString(Main.screenWidth / 2 - 150, Main.screenHeight / 2 - 93, "white", "Field of View:");
		Fonts.drawString(Main.screenWidth / 2 - 150, Main.screenHeight / 2 - 43, "white", "Max FPS:");
	}
}
