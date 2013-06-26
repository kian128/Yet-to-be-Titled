package core;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import render.ModelCube;
import render.Textures;
import world.World;
import entity.EntityBlock;
import entity.EntityMob;
import entity.EntityPresets;
import gui.Gui;
import gui.GuiMenuMain;
import gui.GuiMenuPause;

public class Input {
	
	Timing timing = new Timing();
	Screen screen = new Screen();
	Options options = new Options();
	Camera camera = new Camera();
	
	EntityMob player;
	
	private int key_Forward = Keyboard.KEY_W;
	private int key_Backward = Keyboard.KEY_S;
	private int key_Left = Keyboard.KEY_A;
	private int key_Right = Keyboard.KEY_D;
	
	private int key_Jump = Keyboard.KEY_SPACE;
	private int key_Down = Keyboard.KEY_LSHIFT;
	
	private int key_LookUp = Keyboard.KEY_UP;
	private int key_LookDown = Keyboard.KEY_DOWN;
	private int key_LookLeft = Keyboard.KEY_LEFT;
	private int key_LookRight = Keyboard.KEY_RIGHT;
	
	private int key_ToggleDev = Keyboard.KEY_F3;
	private int key_ToggleFlying = Keyboard.KEY_F;
	
	public void update(long delta) {
		player = Main.player;
		
		if(Main.isInMenu())  {
			Main.isPaused = true;
		}
		
		if(Main.isInGame() && !Main.isPaused) updateInGame(delta);
		if(Main.isPaused) updateInMenu(delta);
		updateAll(delta);
		
		while(Mouse.next()) {
			if(Main.isInGame() && !Main.isPaused) updateInGameMouseNext(delta);
			if(Main.isPaused) updateInMenuMouseNext(delta);
			updateAllMouseNext(delta);
		}
		
		while(Keyboard.next()) {
			if(Main.isInGame() && !Main.isPaused) updateInGameKeyboardNext(delta);
			if(Main.isPaused) updateInMenuKeyboardNext(delta);
			updateAllKeyboardNext(delta);
		}
	}
	
	public void updateInGame(long delta) {
		if(Keyboard.isKeyDown(key_Forward)) {
			if(!Keyboard.isKeyDown(key_Down)) player.moveZ(-player.getSpeed() * delta);
			World.playerDirection = "north";
		}
	
		if(Keyboard.isKeyDown(key_Backward)) {
			if(!Keyboard.isKeyDown(key_Down)) player.moveZ(player.getSpeed() * delta);
			World.playerDirection = "south";
		}
	
		if(Keyboard.isKeyDown(key_Left)) {
			if(!Keyboard.isKeyDown(key_Down)) player.moveX(-player.getSpeed() * delta);
			World.playerDirection = "west";
		}
	
		if(Keyboard.isKeyDown(key_Right)) {
			if(!Keyboard.isKeyDown(key_Down)) player.moveX(player.getSpeed() * delta);
			World.playerDirection = "east";
		}
	
		if(Keyboard.isKeyDown(key_Jump)) {
			if(player.isFlying) {
				player.moveY(player.getSpeed() * delta);
			} else {
				player.jump(0.21f);
			}
		}
		if(Keyboard.isKeyDown(key_Down)) {
			if(player.isFlying) {
				player.moveY(-player.getSpeed() * delta);
			}
		}
	
		if(Keyboard.isKeyDown(key_LookUp)) {
			Main.npc.moveZ(player.getSpeed() * delta);
			camera.rotY += camera.rotSpeed * delta * 0.5f;
		}
		if(Keyboard.isKeyDown(key_LookDown)) {
			Main.npc.moveZ(-player.getSpeed() * delta);
			camera.rotY -= camera.rotSpeed * delta * 0.5f;
		}
		if(Keyboard.isKeyDown(key_LookLeft)) {
			for(int n = 0; n < World.mobList.size(); n++) {
				World.mobList.get(n).rotate(camera.rotSpeed * delta);
			}
			
			for(int n = 0; n < World.terrainList.size(); n++) {
				World.terrainList.get(n).rotate(camera.rotSpeed * delta);
			}
			
			//camera.rotY += camera.rotSpeed * delta * 0.5f;
		}
		if(Keyboard.isKeyDown(key_LookRight)) {
			for(int n = 0; n < World.mobList.size(); n++) {
				World.mobList.get(n).rotate(-camera.rotSpeed * delta);
			}
			
			for(int n = 0; n < World.terrainList.size(); n++) {
				World.terrainList.get(n).rotate(-camera.rotSpeed * delta);
			}
			
			//camera.rotY -= camera.rotSpeed * delta * 0.5f;
		}
	}
	
	public void updateInGameKeyboardNext(long delta) {
		if(Keyboard.isKeyDown(Keyboard.KEY_RETURN)) {
			if(World.playerDirection == "north") {
				EntityPresets.newBlockCrate(player.getX() + 0.1f, player.getY(), player.getZ() - 0.8f, 0.8f, 0.8f, 0.8f);
			}
			if(World.playerDirection == "south") {
				EntityPresets.newBlockCrate(player.getX() + 0.1f, player.getY(), player.getZ() + player.getZWidth(), 0.8f, 0.8f, 0.8f);
			}
			if(World.playerDirection == "east") {
				EntityPresets.newBlockCrate(player.getX() + player.getZWidth(), player.getY(), player.getZ() + 0.1f, 0.8f, 0.8f, 0.8f);
			}
			if(World.playerDirection == "west") {
				EntityPresets.newBlockCrate(player.getX() - 0.8f, player.getY(), player.getZ() + 0.1f, 0.8f, 0.8f, 0.8f);
			}
			
			System.out.println("Placed new block at (" + player.getX() + ", " + player.getY() + ", " + player.getZ() + ")");
		}
		
		if(Keyboard.isKeyDown(key_ToggleFlying)) {
			player.isFlying = !player.isFlying;
		}
	
		if(Keyboard.isKeyDown(Keyboard.KEY_BACK)) {
			camera.reset();
		}
	}
	
	public void updateInGameMouseNext(long delta) {
		int dWheel = Mouse.getDWheel();
		if(dWheel > 0) {
			camera.zoomIn();
		} else if(dWheel < 0) {
			camera.zoomOut();
		}
	}
	
	public void updateInMenu(long delta) {
	}
	
	public void updateInMenuKeyboardNext(long delta) {
		if(Keyboard.isKeyDown(Keyboard.KEY_RETURN)) {
			Gui.guiCurrent.activateButton();
		}
	
		if(Keyboard.isKeyDown(Keyboard.KEY_UP)) {
			Gui.guiCurrent.selectComponentUp();
		}
	
		if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
			Gui.guiCurrent.selectComponentDown();
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
			Gui.guiCurrent.activateSliderDown();
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
			Gui.guiCurrent.activateSliderUp();
		}
		
		if(Character.isLetter(Keyboard.getEventCharacter())) {
			Gui.guiCurrent.appendString(Character.toString(Keyboard.getEventCharacter()));
		} else if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			Gui.guiCurrent.appendString(" ");
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_BACK)) {
			Gui.guiCurrent.shortenString(1);
		}
	}
	
	public void updateInMenuMouseNext(long delta) {
		
	}
	
	public void updateAll(long delta) {
		
	}
	
	public void updateAllKeyboardNext(long delta) {
		if(Keyboard.isKeyDown(key_ToggleDev)) {
			Main.showDev = !Main.showDev;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) && Main.isInGame()) {
			if(Main.isPaused) {
				Gui.setCurrentGui(null);
				Main.isPaused = false;
			} else {
				Gui.setCurrentGui(new GuiMenuPause());
				Main.isPaused = true;
			}
		}
	}
	
	public void updateAllMouseNext(long delta) {
		
	}
}