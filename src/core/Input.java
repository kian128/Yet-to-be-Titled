package core;

import static org.lwjgl.opengl.GL11.glScalef;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import render.ModelCube;
import entity.EntityBlock;
import entity.EntityMob;

public class Input {
	
	Timing timing = new Timing();
	
	EntityMob player;
	Camera camera = new Camera();
	
	private int key_Forward = Keyboard.KEY_W;
	private int key_Backward = Keyboard.KEY_S;
	private int key_Left = Keyboard.KEY_A;
	private int key_Right = Keyboard.KEY_D;
	
	private int key_Up = Keyboard.KEY_SPACE;
	private int key_Down = Keyboard.KEY_LSHIFT;
	
	private int key_LookUp = Keyboard.KEY_UP;
	private int key_LookDown = Keyboard.KEY_DOWN;
	private int key_LookLeft = Keyboard.KEY_LEFT;
	private int key_LookRight = Keyboard.KEY_RIGHT;
	
	public void update(long delta) {
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
		
		if(Keyboard.isKeyDown(key_Up)) {
			player.moveY(player.getSpeed() * delta);
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
		
		while(Keyboard.next()) {
			if(Keyboard.isKeyDown(Keyboard.KEY_RETURN)) {
				new EntityBlock(player.getX(), player.getY(), player.getZ(), 1, 1, 1, false, new ModelCube(), new Vector3f(0, 0, 1));
				//Main.renderer.updateTerrain();
				System.out.println("Placed new block at (" + player.getX() + ", " + player.getY() + ", " + player.getZ() + ")");
			}
			
			if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
				Main.npc.remove();
			}
			
			if(Keyboard.isKeyDown(Keyboard.KEY_BACK)) {
				camera.scaleX = 1;
				camera.scaleY = 1;
				camera.scaleZ = 1;
				camera.rotX = 65;
				camera.rotY = 0;
			}
		}
		
		while(Mouse.next()) {
			int dWheel = Mouse.getDWheel();
			if(dWheel > 0) {
				camera.scaleX += camera.zoomSpeed;
				camera.scaleY += camera.zoomSpeed;
				camera.scaleZ += camera.zoomSpeed;
			} else if(dWheel < 0) {
				camera.scaleX -= camera.zoomSpeed;
				camera.scaleY -= camera.zoomSpeed;
				camera.scaleZ -= camera.zoomSpeed;
			}
		}
	}

}
