package world;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import org.lwjgl.util.vector.Vector3f;

import core.Main;

import render.Model;
import render.ModelCube;
import render.Renderer;
import render.Textures;
import entity.Entity;
import entity.EntityBlock;
import entity.EntityMob;
import entity.EntityPresets;

public class World {
	
	public static ArrayList<EntityMob> mobList = new ArrayList<EntityMob>();
	public static ArrayList<EntityBlock> terrainList = new ArrayList<EntityBlock>();
	public static ArrayList<Entity> collidableList = new ArrayList<Entity>();
	
	public static Vector3f spawnPoint = new Vector3f(-2, 2, 0);
	public static float baseHeight = 0;
	public static float worldHeight = 0;
	
	public static float gravity = 9.8f;
	
	public static String playerDirection = "south";
	
	public World() {
	}
	
	public void update(long delta) {
		for(int n = 0; n < mobList.size(); n++) {
			if(Renderer.isInRenderDistanceFromPlayer(mobList.get(n).getX(), mobList.get(n).getY(), mobList.get(n).getZ())) {
				mobList.get(n).update(delta);
			}
		}
		
		Main.npc.jump(World.gravity * 0.0013f * delta);
	}
	
	public Entity getEntity(float x, float y, float z) {
		for(int n = 0; n < mobList.size(); n++) {
			if(mobList.get(n).getX() == x && mobList.get(n).getY() == y && mobList.get(n).getZ() == z) {
				return mobList.get(n);
			}
		}
		return null;
	}
	
	public static int getNumberOfEntities(String id) {
		int n = 0;
		for(int i = 0; i < World.terrainList.size(); i++) {
			if(World.terrainList.get(i).id.contains("crate")) {
				n++;
			}
		}
		return n;
	}
	
	public void loadNew() {
		Main.player = EntityPresets.newMobPlayer(spawnPoint.x, spawnPoint.y, spawnPoint.z);
		Main.npc = EntityPresets.newMobNpc(2, 3, 0);
		
		EntityPresets.newBlockCrate(-6, 0, -3, 1, 1, 1);
		EntityPresets.newBlockCrate(-7, 1, -3, 1, 1, 1);
		EntityPresets.newBlockCrate(-8, 2, -3, 1, 1, 1);
		EntityPresets.newBlockCrate(-10, 2, -3, 1, 1, 1);
		EntityPresets.newBlockCrate(-10, 2, -5, 1, 1, 1);
		
		Structures.buildHouseStandardExterior(0, 0, -15, 11, 3, 10);
		
		for(int x = -25; x < 25; x++) {
			for(int z = -25; z < 25; z++) {
				EntityPresets.newBlockGrass(x, -1, z);
			}
		}
	}
}