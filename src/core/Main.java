package core;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import render.Fonts;
import render.ModelCube;
import render.Renderer;
import render.Textures;
import entity.EntityBlock;
import entity.EntityMob;

public class Main {
	
	public static int screenWidth = 860, screenHeight = 640;
	private String title = "Yet to be titled...";
	
	public static EntityMob player;
	public static EntityMob npc;
	
	public static Renderer renderer;
	
	Screen screen = new Screen();
	Input input = new Input();
	Camera camera = new Camera();
	Options options = new Options();
	Timing timing = new Timing();
	Lighting lighting = new Lighting();
	Textures textures = new Textures();
	Fonts fonts = new Fonts();
	//dun i dun goofed?
	
	public void init() {
		options.loadConfig();
		
		screen.initDisplay(screenWidth, screenHeight, options.vsync, options.resizable, title);
		screen.projectionGluLookAt(options.fov, screenWidth, screenHeight, options.zNear, options.zFar, 0.0f, 0.0f, options.cameraDistance, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f);
		fonts.initFonts();
		screen.initOpengl();
		lighting.initLighting();
		
		player = new EntityMob(World.spawnPoint.x, World.spawnPoint.y, World.spawnPoint.z, 1, 1, 1, true, new ModelCube(), new Vector3f(0.6f, 0, 0), 0.005f);
		npc = new EntityMob(2, 0, 0, 1, 1, 1, true, new ModelCube(), new Vector3f(0, 0.6f, 0), 0.005f);
		
		for(int x = -25; x < 25; x++) {
			for(int z = -25; z < 25; z++) {
				new EntityBlock(x, -1, z, 1, 1, 1, true, new ModelCube(), textures.loadTexture("res/textures/grass.png"));
			}
		}
		
		renderer = new Renderer();
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
			
        	screen.projectionGluLookAt(options.fov, screenWidth, screenHeight, options.zNear, options.zFar, 0.0f, 0.0f, options.cameraDistance, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f);
	        renderer.render3D();
	        
			screen.projectionOrtho(screenWidth, screenHeight);
			glLoadIdentity();
			lighting.disableLighting();
			renderer.render2D();
	        
			//renderer.renderShadowMap();
		glPopAttrib();
		
		lighting.setLightPosition(GL_LIGHT0, player.getX() + 50, 100, player.getZ() + 50);
	}
	
	public void update() {
		long delta = timing.getDelta();
		
		input.update(delta);
		camera.translate();
		timing.updateFps();
		
		//Display.sync(options.maxfps);
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
