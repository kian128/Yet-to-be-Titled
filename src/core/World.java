package core;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector3f;

import entity.Entity;
import entity.EntityBlock;
import entity.EntityMob;

public class World {
	
	public static ArrayList<EntityMob> mobList = new ArrayList<EntityMob>();
	public static ArrayList<EntityBlock> terrainList = new ArrayList<EntityBlock>();
	public static ArrayList<Entity> collidableList = new ArrayList<Entity>();
	
	public static Vector3f spawnPoint = new Vector3f(-2, 2, 0);
	
	public static float gravity = 9.8f;
	
	public World() {
	}
	
	public void updateMobs(long delta) {
		for(int n = 0; n < mobList.size(); n++) {
			mobList.get(n).update(delta);
		}
	}
	
	public Entity getEntity(float x, float y, float z) {
		for(int n = 0; n < mobList.size(); n++) {
			if(mobList.get(n).getX() == x && mobList.get(n).getY() == y && mobList.get(n).getZ() == z) {
				return mobList.get(n);
			}
		}
		return null;
	}
}