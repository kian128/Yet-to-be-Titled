package core;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Mouse;

public class Camera {
	
	public static float rotX = 65;
	public static float rotY = 0;
	
	public float zoomSpeed = 0.05f;
	public float rotSpeed = 0.1f;
	
	public static float scaleX = 1f;
	public static float scaleY = 1f;
	public static float scaleZ = 1f;
	
	public void translate() {
		glLoadIdentity();
		glPushAttrib(GL_TRANSFORM_BIT);
			glRotatef(rotX, 1, 0, 0);
			glRotatef(rotY, 0, 1, 0);
			glRotatef(0, 0, 0, 1);
			glTranslatef(-(Main.player.getX() + Main.player.getXWidth() / 2) * scaleX, -(Main.player.getY() + Main.player.getXWidth() / 2) * scaleY, -(Main.player.getZ() + Main.player.getXWidth() / 2) * scaleZ);
			glScalef(scaleX, scaleY, scaleZ);
        glPopAttrib();
	}
}