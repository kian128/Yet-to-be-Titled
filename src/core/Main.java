package core;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import render.Fonts;
import render.ModelCube;
import render.Render2D;
import render.Renderer;
import render.Textures;
import entity.EntityBlock;
import entity.EntityMob;
import gui.Gui;
import gui.GuiMenuMain;
import gui.GuiMenuOptions;
import gui.GuiMenuUpdate;

public class Main {
	
	public static int screenWidth = 860, screenHeight = 640;
	private String title = "Yet to be titled...";
	
	public static EntityMob player;
	public static EntityMob npc;
	
	public static Renderer renderer;
	
	public enum GameState {
		MENU_MAIN, MENU_OPTIONS, MENU_UPDATE, GAME_MAIN;
	}
	
	public enum HudState {
		CLEAR, HUD_MAIN, HUD_DEV;
	}
	
	public static GameState gameState = GameState.MENU_MAIN;
	public static HudState hudState = HudState.HUD_DEV;
	
	public static GuiMenuMain guiMenuMain;
	public static GuiMenuOptions guiMenuOptions;
	public static GuiMenuUpdate guiMenuUpdate;
	public static Gui guiCurrent;
	
	Screen screen = new Screen();
	Input input = new Input();
	Camera camera = new Camera();
	Options options = new Options();
	Timing timing = new Timing();
	Lighting lighting = new Lighting();
	Textures textures = new Textures();
	Fonts fonts = new Fonts();
	World world = new World();
	
	public void init() {
		//Mouse.setGrabbed(true);
		
		options.loadConfig();
		
		screen.initDisplay(screenWidth, screenHeight, options.vsync, options.resizable, title);
		screen.projectionGluLookAt(options.getFov(), screenWidth, screenHeight, options.zNear, options.zFar, 0.0f, 0.0f, options.cameraDistance, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f);
		fonts.initFonts();
		screen.initOpengl();
		lighting.initLighting();
		textures.initTextures();
		
		player = new EntityMob(World.spawnPoint.x, World.spawnPoint.y, World.spawnPoint.z, 0.8f, 0.8f, 0.8f, true, new ModelCube(), new Vector3f(0.6f, 0, 0), 0.005f);
		npc = new EntityMob(2, 3, 0, 1, 1, 1, true, new ModelCube(), new Vector3f(0, 0.6f, 0), 0.005f);
		
		new EntityBlock(-1, 0, 1, 1, 1, 1, true, new ModelCube(), textures.TEX_CRATE);
		new EntityBlock(-2, 1, 1, 1, 1, 1, true, new ModelCube(), textures.TEX_CRATE);
		new EntityBlock(-3, 2, 1, 1, 1, 1, true, new ModelCube(), textures.TEX_CRATE);
		new EntityBlock(-5, 2, 1, 1, 1, 1, true, new ModelCube(), textures.TEX_CRATE);
		new EntityBlock(-5, 2, -1, 1, 1, 1, true, new ModelCube(), textures.TEX_CRATE);
		
		for(int x = -25; x < 25; x++) {
			for(int z = -25; z < 25; z++) {
				new EntityBlock(x, -1, z, 1, 1, 1, true, new ModelCube(), textures.TEX_GRASS);
			}
		}
		
		renderer = new Renderer();
		
		guiMenuMain = new GuiMenuMain();
		guiMenuOptions = new GuiMenuOptions();
		guiMenuUpdate = new GuiMenuUpdate();
	}
	
	public void render() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		/*glPushMatrix();
			screen.projectionOrtho(screenWidth, screenHeight);
			glLoadIdentity();
			renderer.render2D();
		glPopMatrix();*/
		
		lighting.enableLighting();
		
		glPushAttrib(GL_ALL_ATTRIB_BITS);
			//renderer.updateMobs();
			//renderer.updateTerrain();
			//renderer.generateTextureCoordinates();
			
        	screen.projectionGluLookAt(options.getFov(), screenWidth, screenHeight, options.zNear, options.zFar, 0.0f, 0.0f, options.cameraDistance, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f);
	        renderer.render3D();
	        
			screen.projectionOrtho(screenWidth, screenHeight);
			glLoadIdentity();
			lighting.disableLighting();
			renderer.render2D();
			lighting.enableLighting();
			
			//renderer.renderShadowMap();
		glPopAttrib();
		
		lighting.setLightPosition(GL_LIGHT0, player.getX() + 50, 100, player.getZ() + 50);
	}
	
	public void update() {
		long delta = timing.getDelta();
		
		input.update(delta);
		if(gameState == GameState.GAME_MAIN) world.updateMobs(delta);
		npc.jump(World.gravity * 0.0013f * delta);
		camera.translate();
		timing.updateFps();
		
		Display.sync(options.getMaxfps());
		Display.update();
	}
	
	public void exit() {
		Display.destroy();
		System.exit(0);
	}
	
	public static void main(String args[]) {
		Main main = new Main();
		main.init();
		
		while(!Display.isCloseRequested()) {
			main.render();
			main.update();
		}
		
		main.exit();
	}
}
