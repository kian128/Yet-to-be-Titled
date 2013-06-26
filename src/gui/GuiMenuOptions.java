package gui;

import java.awt.Color;

import render.Fonts;
import core.Main;
import core.Options;

public class GuiMenuOptions extends Gui {
	
	private GuiButton buttonBack;
	private GuiButton buttonDefault;
	private GuiSlider sliderFov;
	private GuiSlider sliderfps;
	private GuiSlider sliderLockedfps;
	private GuiSlider sliderVsync;
	private GuiButton buttonControls;
	
	private static String warningVsync = "";
	
	Options options = new Options();
	
	public GuiMenuOptions() {
		sliderFov = new GuiSlider(Main.screenWidth / 2, Main.screenHeight / 2 - 100, 200, 20, 1, 1, 1, 20, GuiSlider.getFloatsAsArray(options.dfov - 10, options.dfov + 10, 1), options.getFov());
		sliderFov.setDisplayValue(new Runnable() {
			public void run() {
				options.setFov((int) Float.parseFloat(sliderFov.displayValue));
			}
		});
		
		sliderfps = new GuiSlider(Main.screenWidth / 2, Main.screenHeight / 2 - 50, 200, 20, 1, 1, 1, 40, GuiSlider.getFloatsAsArray(options.dmaxfps - 20, options.dmaxfps + 20, 1), options.getMaxfps());
		sliderfps.setDisplayValue(new Runnable() {
			public void run() {
				options.setMaxfps((int) Float.parseFloat(sliderfps.displayValue));
			}
		});
		
		sliderLockedfps = new GuiSlider(Main.screenWidth / 2, Main.screenHeight / 2, 200, 20, 1, 1, 1, 1, GuiSlider.getObjectsAsArray(new String("false"), new String("true")), options.getLockedfps());
		sliderLockedfps.setDisplayValue(new Runnable() {
			public void run() {
				options.setLockedfps(Boolean.parseBoolean(sliderLockedfps.displayValue));
			}
		});
		
		sliderVsync = new GuiSlider(Main.screenWidth / 2, Main.screenHeight / 2 + 50, 200, 20, 1, 1, 1, 1, GuiSlider.getObjectsAsArray(new String("false"), new String("true")), options.getVsync());
		sliderVsync.setDisplayValue(new Runnable() {
			public void run() {
				if(options.getVsync() != Boolean.parseBoolean(sliderVsync.displayValue)) {
					warningVsync = "*requires restart";
				}
				
				options.setVsync(Boolean.parseBoolean(sliderVsync.displayValue));
			}
		});
		
		buttonControls = new GuiButton(Main.screenWidth / 2 - 50, Main.screenHeight / 2 + 150, 100, 20, 1, 1, 1, "CONTROLS");
		buttonControls.setActivation(new Runnable() {
			public void run() {
				setCurrentGui(new GuiMenuControls());
			}
		});
		
		buttonBack = new GuiButton(Main.screenWidth / 2 - 50, Main.screenHeight / 2 + 200, 100, 20, 1, 1, 1, "RETURN");
		buttonBack.setActivation(new Runnable() {
			public void run() {
				setCurrentGui(new GuiMenuMain());
			}
		});
		
		buttonDefault = new GuiButton(Main.screenWidth / 2 - 50, Main.screenHeight / 2 + 240, 100, 20, 1, 1, 1, "DEFAULT");
		buttonDefault.setActivation(new Runnable() {
			public void run() {
				options.setDefault();
				sliderFov.setSliderManual(new Float(options.getFov()));
				sliderfps.setSliderManual(new Float(options.getMaxfps()));
				sliderLockedfps.setSliderManual(new Boolean(options.getLockedfps()));
				sliderVsync.setSliderManual(new Boolean(options.getVsync()));
			}
		});
		
		addComponents(sliderFov, sliderfps, sliderLockedfps, sliderVsync, buttonControls, buttonBack, buttonDefault);
		setComponentActive(buttonBack);
	}
	
	@Override
	public void renderText() {
		Fonts.drawString(Fonts.font_16_White, Main.screenWidth / 2 - 200, Main.screenHeight / 2 - 97, "Field of View:");
		Fonts.drawString(Fonts.font_16_White, Main.screenWidth / 2 - 200, Main.screenHeight / 2 - 47, "Max FPS:");
		Fonts.drawString(Fonts.font_16_White, Main.screenWidth / 2 - 200, Main.screenHeight / 2 + 3, "Locked FPS:");
		Fonts.drawString(Fonts.font_16_White, Main.screenWidth / 2 - 200, Main.screenHeight / 2 + 53, "Vsync:");
			Fonts.drawString(Fonts.font_16_White, Main.screenWidth / 2 + 220, Main.screenHeight / 2 + 53, warningVsync);
	}
}
