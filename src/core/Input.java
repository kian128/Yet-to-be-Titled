package core;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import render.ModelCube;
import render.Textures;
import entity.EntityBlock;
import entity.EntityMob;
import gui.GuiButton;
import gui.GuiSlider;

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
	
	public void update(long delta) {
		if(Main.gameState == Main.GameState.GAME_MAIN) {
			player = Main.player;
		
			if(Keyboard.isKeyDown(key_Forward)) {
				player.moveZ(-player.getSpeed() * delta);
			}
		
			if(Keyboard.isKeyDown(key_Backward)) {
				player.moveZ(player.getSpeed() * delta);
			}
		
			if(Keyboard.isKeyDown(key_Left)) {
				player.moveX(-player.getSpeed() * delta);
			}
		
			if(Keyboard.isKeyDown(key_Right)) {
				player.moveX(player.getSpeed() * delta);
			}
		
			if(Keyboard.isKeyDown(key_Jump)) {
				player.jump(World.gravity * 0.0013f * delta);
			}
			if(Keyboard.isKeyDown(key_Down)) {
				player.moveY(-player.getSpeed() * delta);
			}
		
			if(Keyboard.isKeyDown(key_LookUp)) {
				camera.rotX += camera.rotSpeed * delta;
			}
			if(Keyboard.isKeyDown(key_LookDown)) {
				camera.rotX -= camera.rotSpeed * delta;
			}
			if(Keyboard.isKeyDown(key_LookLeft)) {
				camera.rotY += camera.rotSpeed * delta;
			}
			if(Keyboard.isKeyDown(key_LookRight)) {
				camera.rotY -= camera.rotSpeed * delta;
			}
		}
		
		while(Keyboard.next()) {
			if(Main.gameState == Main.GameState.GAME_MAIN) {
				if(Keyboard.isKeyDown(Keyboard.KEY_RETURN)) {
					new EntityBlock(player.getX(), player.getY(), player.getZ(), 0.8f, 0.8f, 0.8f, false, new ModelCube(), Textures.TEX_CRATE);
					//Main.renderer.updateTerrain();
					System.out.println("Placed new block at (" + player.getX() + ", " + player.getY() + ", " + player.getZ() + ")");
				}
			
				if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
					Main.gameState = Main.GameState.MENU_MAIN;
				}
			
				if(Keyboard.isKeyDown(Keyboard.KEY_BACK)) {
					camera.reset();
				}
			}
			
			if(Main.gameState == Main.GameState.MENU_MAIN || Main.gameState == Main.GameState.MENU_OPTIONS || Main.gameState == Main.GameState.MENU_UPDATE) {
				if(Keyboard.isKeyDown(Keyboard.KEY_RETURN)) {
					if(Main.guiCurrent.componentActive.getClass() == GuiButton.class) {
						Main.guiCurrent.activateButton();
					}
				}
			
				if(Keyboard.isKeyDown(Keyboard.KEY_UP)) {
					Main.guiCurrent.selectComponentUp();
				}
			
				if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
					Main.guiCurrent.selectComponentDown();
				}
				
				if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
					Main.guiCurrent.activateSliderDown();
				}
				
				if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
					Main.guiCurrent.activateSliderUp();
				}
			}
			
			if(Keyboard.isKeyDown(key_ToggleDev)) {
				if(Main.hudState == Main.HudState.CLEAR) {
					Main.hudState = Main.HudState.HUD_DEV;
				} else {
					Main.hudState = Main.HudState.CLEAR;
				}
			}
		}
		
		while(Mouse.next()) {
			if(Main.gameState == Main.GameState.GAME_MAIN) {
				int dWheel = Mouse.getDWheel();
				if(dWheel > 0) {
					camera.zoomIn();
				} else if(dWheel < 0) {
					camera.zoomOut();
				}
			}
		}
	}
}
