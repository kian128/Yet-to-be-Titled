package entity;

import org.lwjgl.util.vector.Vector3f;

import render.Model;
import world.World;

public class EntityBlock extends Entity {
	
	public String id;
	
	public Model model;
	public float colorRed, colorGreen, colorBlue;
	public int texture;
	
	public EntityBlock(String typeGen, String typeSpec, float x, float y, float z, float xWidth, float height, float zWidth, boolean isCollidable, Model model, Vector3f color) {
		super(x, y, z, xWidth, height, zWidth, isCollidable);
		
		this.model = model;
		this.colorRed = color.x;
		this.colorGreen = color.y;
		this.colorBlue = color.z;
		
		this.id = "block_" + typeSpec + "_" + (World.getNumberOfEntities(typeSpec) + 1);
		
		World.terrainList.add(this);
	}
	
	public EntityBlock(String typeGen, String typeSpec, float x, float y, float z, float xWidth, float height, float zWidth, boolean isCollidable, Model model, int texture) {
		super(x, y, z, xWidth, height, zWidth, isCollidable);
		
		this.model = model;
		this.texture = texture;
		
		this.id = typeGen + "_" + typeSpec + "_" + (World.getNumberOfEntities(typeSpec) + 1);
		
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
			model.render(x, y, z, xWidth, height, zWidth, rot, colorRed, colorGreen, colorBlue);
		} else {
			model.render(x, y, z, xWidth, height, zWidth, rot, texture);
		}
	}
	
	public void remove() {
		World.terrainList.remove(this);
		if(isCollidable) {
			World.collidableList.remove(this);
		}
	}
}