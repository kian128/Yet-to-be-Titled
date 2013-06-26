package core;

import static org.lwjgl.opengl.GL11.GL_ALL_ATTRIB_BITS;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_LIGHT0;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glPopAttrib;
import static org.lwjgl.opengl.GL11.glPushAttrib;
import entity.EntityBlock;
import entity.EntityMob;
import gui.Gui;
import gui.GuiMenuCharCreation;
import gui.GuiMenuMain;
import gui.GuiMenuOptions;
import gui.GuiMenuPause;
import gui.GuiMenuUpdate;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import render.Fonts;
import render.Model;
import render.ModelCube;
import render.Renderer;
import render.Textures;
import world.World;

public class Main {
	
	public static int screenWidth = 860, screenHeight = 640;
	public static String title = "Yet to be titled...";
	
	public static Renderer renderer;
	
	Screen screen = new Screen();
	Input input = new Input();
	Camera camera = new Camera();
	Options options = new Options();
	Lighting lighting = new Lighting();
	Textures textures = new Textures();
	Fonts fonts = new Fonts();
	World world = new World();
	Gui gui = new Gui();
	
	public static EntityMob player;
	public static EntityMob npc;
	
	public static boolean isPaused = false;
	public static boolean showDev = true;
	
	public static float playerWidth = 0.8f, playerHeight = 0.8f;
	public static String playerName;
	
	public void init() {
		//Mouse.setGrabbed(true);
		
		options.loadConfig();
		
		screen.initDisplay(screenWidth, screenHeight, options.getVsync(), options.resizable, title);
		screen.projectionGluLookAt(options.getFov(), screenWidth, screenHeight, options.zNear, options.zFar, 0.0f, 0.0f, options.cameraDistance, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f);
		fonts.initFonts();
		screen.initOpengl();
		lighting.initLighting();
		textures.initTextures();
		
		renderer = new Renderer();
		
		Gui.setCurrentGui(new GuiMenuMain());
	}
	
	public void render(long delta) {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		glPushAttrib(GL_ALL_ATTRIB_BITS);
			//renderer.updateMobs();
			//renderer.updateTerrain();
			//renderer.generateTextureCoordinates();
			
        	screen.projectionGluLookAt(options.getFov(), screenWidth, screenHeight, options.zNear, options.zFar, 0.0f, 0.0f, options.cameraDistance, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f);
	        renderer.render3D();
	        
			screen.projectionOrtho(screenWidth, screenHeight);
			glLoadIdentity();
			lighting.disableLighting();
			renderer.render2D(delta);
			lighting.enableLighting();
			
			//renderer.renderShadowMap();
		glPopAttrib();
		
		if(isInGame()) lighting.setLightPosition(GL_LIGHT0, Main.player.getX() + 50, 100, Main.player.getZ() + 50);
	}
	
	public void update(long delta) {
		input.update(delta);
		if(!isPaused) world.update(delta);
		if(isInGame()) camera.translate();
		
		if(options.getLockedfps()) {
			Display.sync(options.getMaxfps());
		}
		Display.update();
	}
	
	public static boolean isInGame() {
		if(Gui.guiCurrent == null) {
			return true;
		} else if(Gui.guiCurrent.getClass() == GuiMenuPause.class) {
			return true;
		}
		return false;
	}
	
	public static boolean isInMenu() {
		if(Gui.guiCurrent != null) { 
			if(Gui.guiCurrent.getClass() != GuiMenuPause.class) {
				return true;
			}
		}
		return false;
	}
	
	public void exit() {
		Display.destroy();
		System.exit(0);
	}
	
	public static void main(String args[]) {
		Main main = new Main();
		main.init();
		
		Timing timing = new Timing();
		
		while(!Display.isCloseRequested()) {
			long delta = timing.getDelta();
			main.render(delta);
			main.update(delta);
			timing.updateFps();
		}
		
		main.exit();
	}
}
