package entity;

import org.lwjgl.util.vector.Vector3f;

import render.Model;
import core.Main;
import core.World;

public class EntityMob extends Entity {
	
	protected float speed;
	
	protected Model model;
	protected float colorRed, colorGreen, colorBlue;
	
	protected int texture;
	
	public boolean isOnGround;
	protected float verticalVelocity;
	
	public EntityMob(float x, float y, float z, float xWidth, float height, float zWidth, boolean isCollidable, Model model, Vector3f color, float speed) {
		super(x, y, z, xWidth, height, zWidth, isCollidable);
		
		this.model = model;
		this.colorRed = color.x;
		this.colorGreen = color.y;
		this.colorBlue = color.z;
		
		this.speed = speed;
		
		World.mobList.add(this);
	}
	
	public EntityMob(float x, float y, float z, float xWidth, float height, float zWidth, boolean isCollidable, Model model, int texture, float speed) {
		super(x, y, z, xWidth, height, zWidth, isCollidable);
		
		this.model = model;
		this.texture = texture;
		
		this.speed = speed;
		
		World.mobList.add(this);
	}
	
	public void jump(float speed) {
		if(isOnGround) {
			isOnGround = false;
			verticalVelocity = speed;
		}
	}
	
	public float[] getVertices() {
		return model.getVertices(x, y, z, xWidth, height, zWidth);
	}
	
	public float[] getColor() {
		return model.getColor(colorRed, colorGreen, colorBlue);
	}
	
	public float[] getNormals() {
		return model.getNormals();
	}
	
	public void render() {
		if(texture == 0) {
			model.render(x, y, z, xWidth, height, zWidth, colorRed, colorGreen, colorBlue);
		} else {
			model.render(x, y, z, xWidth, height, zWidth, texture);
		}
	}
	
	public void update(long delta) {
		if(!isOnGround) {
			verticalVelocity -= World.gravity * 0.00005f * delta;
		} else {
			verticalVelocity = 0;
		}
		
		moveY(verticalVelocity);
	}
	
	public float getSpeed() {
		return speed;
	}
	
	public void remove() {
		World.mobList.remove(this);
		if(isCollidable) {
			World.collidableList.remove(this);
		}
	}
}
