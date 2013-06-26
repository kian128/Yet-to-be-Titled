package core;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Mouse;

import world.World;

public class Camera {
	
	public static float rotX = 55;
	public static float rotY = 0;
	
	public float zoomSpeed = 0.05f;
	public float rotSpeed = 0.1f;
	
	public static float scaleX = 1f;
	public static float scaleY = 1f;
	public static float scaleZ = 1f;
	
	public boolean isFollowingHorizontal = true;
	public boolean isFollowingVertical = true;
	public boolean canRotate = true;
	public boolean canZoom = true;
	
	public void translate() {
		if(Main.player.getY() > World.baseHeight && rotX <= 90) {
			rotX = 55 + (Main.player.getY() - World.baseHeight) * 4.0f;
		}
		if(rotX > 90) rotX = 90;
		
		glLoadIdentity();
		glPushAttrib(GL_TRANSFORM_BIT);
			glRotatef(rotX, 1, 0, 0);
			glRotatef(rotY, 0, 1, 0);
			glRotatef(0, 0, 0, 1);
				if(isFollowingHorizontal && isFollowingVertical) {
					glTranslatef(-(Main.player.getX() + Main.player.getXWidth() / 2) * scaleX, -(Main.player.getY() + Main.player.getXWidth() / 2) * scaleY, -(Main.player.getZ() + Main.player.getXWidth() / 2) * scaleZ);
				} else if(isFollowingHorizontal) {
					glTranslatef(-(Main.player.getX() + Main.player.getXWidth() / 2) * scaleX, 0, -(Main.player.getZ() + Main.player.getXWidth() / 2) * scaleZ);
				} else if(isFollowingVertical) {
					glTranslatef(0, -(Main.player.getY() + Main.player.getXWidth() / 2) * scaleY, 0);
				} else {
					glTranslatef(0, 0, 0);
				}
			glScalef(scaleX, scaleY, scaleZ);
        glPopAttrib();
	}
	
	public void zoomIn() {
		if(canZoom) {
			scaleX += zoomSpeed;
			scaleY += zoomSpeed;
			scaleZ += zoomSpeed;
		}
	}
	
	public void zoomOut() {
		if(canZoom) {
			scaleX -= zoomSpeed;
			scaleY -= zoomSpeed;
			scaleZ -= zoomSpeed;
		}
	}
	
	public void reset() {
		if(canZoom) {
			scaleX = 1;
			scaleY = 1;
			scaleZ = 1;
		}
		if(canRotate) {
			rotX = 65;
			rotY = 0;
		}
	}
}