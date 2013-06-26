package gui;

import render.Fonts;
import world.World;
import core.Main;

public class GuiMenuCharCreation extends Gui {
	
	private GuiTextbox textboxName;
	private GuiSlider sliderCharWidth;
	private GuiSlider sliderCharHeight;
	private GuiButton buttonConfirm;
	private GuiButton buttonReturn;
	
	private float dPlayerWidth = 0.8f, dPlayerHeight = 0.8f;
	private float dPlayerRed = 1.0f, dPlayerGreen = 0.0f, dPlayerBlue = 0.0f;
	
	World world = new World();
	
	public GuiMenuCharCreation() {
		Main.playerWidth = dPlayerWidth;
		Main.playerHeight = dPlayerHeight;
		
		textboxName = new GuiTextbox(Main.screenWidth / 2 - 100, Main.screenHeight / 2 - 150, 200, 20, 1, 1, 1, "Enter your name", 15);
		
		sliderCharWidth = new GuiSlider(Main.screenWidth / 2, Main.screenHeight / 2 - 50, 200, 20, 1, 1, 1, 10, GuiSlider.getFloatsAsArray(dPlayerWidth - 5 * 0.1f, dPlayerWidth + 5 * 0.1f, 0.1f), Main.playerWidth);
		sliderCharWidth.setDisplayValue(new Runnable() {
			public void run() {
				Main.playerWidth = Float.parseFloat(sliderCharWidth.displayValue);
			}
		});
		
		sliderCharHeight = new GuiSlider(Main.screenWidth / 2, Main.screenHeight / 2, 200, 20, 1, 1, 1, 10, GuiSlider.getFloatsAsArray(dPlayerHeight - 5 * 0.1f, dPlayerHeight + 5 * 0.1f, 0.1f), Main.playerHeight);
		sliderCharHeight.setDisplayValue(new Runnable() {
			public void run() {
				Main.playerHeight = Float.parseFloat(sliderCharHeight.displayValue);
			}
		});
		
		buttonConfirm = new GuiButton(Main.screenWidth / 2 - 50, Main.screenHeight / 2 + 200, 100, 20, 1, 1, 1, "CONTINUE");
		buttonConfirm.setActivation(new Runnable() {
			public void run() {
				if(!textboxName.getText().isEmpty()) {
					Main.playerName = textboxName.getText();
					world.loadNew();
					setCurrentGui(null); 
				}
			}
		});
		
		buttonReturn = new GuiButton(Main.screenWidth / 2 - 50, Main.screenHeight / 2 + 240, 100, 20, 1, 1, 1, "RETURN");
		buttonReturn.setActivation(new Runnable() {
			public void run() {
				setCurrentGui(new GuiMenuMain());
			}
		});
		
		addComponents(textboxName, sliderCharWidth, sliderCharHeight, buttonConfirm, buttonReturn);
		setComponentActive(textboxName);
	}
	
	@Override
	public void renderText() {
		Fonts.drawString(Fonts.font_16_White, Main.screenWidth / 2 - 150, Main.screenHeight / 2 - 47, "Player Width:");
		Fonts.drawString(Fonts.font_16_White, Main.screenWidth / 2 - 150, Main.screenHeight / 2 + 3, "Player Height:");
	}
}