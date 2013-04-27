package core;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.*;

public class Screen {
	
	DisplayMode displayMode;
	
	public void initDisplay(int width, int height, boolean vsync, boolean resizable, String title) {
		try{
			Display.setDisplayMode(new DisplayMode(width, height));	
			Display.setVSyncEnabled(vsync);
			Display.setResizable(resizable);
			Display.setTitle(title);
			Display.setFullscreen(true);
			Display.create();
		} catch(LWJGLException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public void projectionOrtho(int width, int height) {
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, width, height, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
	}
	
	public void projectionGluLookAt(float fov, int width, int height, float renderNear, float renderFar, float cameraX, float cameraY, float cameraZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
		glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        gluPerspective(fov, (float) Display.getWidth() / (float) Display.getHeight(), renderNear, renderFar);
    	gluLookAt(cameraX, cameraY, cameraZ, centerX, centerY, centerZ, upX, upY, upZ);
    	glMatrixMode(GL_MODELVIEW);
	}
	
	public void initOpengl() {
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_CULL_FACE);
		glCullFace(GL_BACK);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glDepthFunc(GL_LEQUAL);
	}
}
