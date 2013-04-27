package entity;

import org.lwjgl.util.vector.Vector3f;

import render.Model;
import core.World;

public class EntityBlock extends Entity {
	
	protected Model model;
	protected float colorRed, colorGreen, colorBlue;
	protected int texture;
	
	public EntityBlock(float x, float y, float z, float xWidth, float height, float zWidth, boolean isCollidable, Model model, Vector3f color) {
		super(x, y, z, xWidth, height, zWidth, isCollidable);
		
		this.model = model;
		this.colorRed = color.x;
		this.colorGreen = color.y;
		this.colorBlue = color.z;
		
		World.terrainList.add(this);
	}
	
	public EntityBlock(float x, float y, float z, float xWidth, float height, float zWidth, boolean isCollidable, Model model, int texture) {
		super(x, y, z, xWidth, height, zWidth, isCollidable);
		
		this.model = model;
		this.texture = texture;
		
		World.terrainList.add(this);
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
	
	public void remove() {
		World.terrainList.remove(this);
		if(isCollidable) {
			World.collidableList.remove(this);
		}
	}
}