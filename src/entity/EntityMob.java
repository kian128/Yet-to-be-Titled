package entity;

import org.lwjgl.util.vector.Vector3f;

import render.Model;
import world.World;
import core.Main;

public class EntityMob extends Entity {

	public String id;
	public float speed;
	
	public Model model;
	public float colorRed, colorGreen, colorBlue;
	
	public int texture;
	
	protected boolean isOnGround;
	protected float verticalVelocity;
	public boolean isFlying = false;
	
	public int gold;
	public float hp = 20;
	
	public EntityMob(String typeGen, String typeSpec, float x, float y, float z, float xWidth, float height, float zWidth, boolean isCollidable, Model model, Vector3f color, float speed) {
		super(x, y, z, xWidth, height, zWidth, isCollidable);
		
		this.model = model;
		this.colorRed = color.x;
		this.colorGreen = color.y;
		this.colorBlue = color.z;
		
		this.id = typeGen + "_" + typeSpec + "_" + (World.getNumberOfEntities(typeSpec) + 1);
		this.speed = speed;
		this.rot = 0;
		
		World.mobList.add(this);
	}
	
	public EntityMob(String typeGen, String typeSpec, float x, float y, float z, float xWidth, float height, float zWidth, boolean isCollidable, Model model, int texture, float speed) {
		super(x, y, z, xWidth, height, zWidth, isCollidable);
		
		this.model = model;
		this.texture = texture;
		
		this.id = typeGen + "_" + typeSpec + "_" + (World.getNumberOfEntities(typeSpec) + 1);
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
			model.render(x, y, z, xWidth, height, zWidth, rot, colorRed, colorGreen, colorBlue);
		} else {
			model.render(x, y, z, xWidth, height, zWidth, rot, texture);
		}
	}
	
	public void update(long delta) {
		if(!isFlying) {
			if(!isOnGround) {
				verticalVelocity -= World.gravity * 0.00008f * delta;
			} else {
				verticalVelocity = 0;
			}
			moveY(verticalVelocity);
		}
		if((Main.player.getY() - World.baseHeight) * 2.7f >= 23) {
			Main.player.setY(23f / 2.7f + World.baseHeight);
		}

		if(hp <= 0) {
			remove();
		}
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
