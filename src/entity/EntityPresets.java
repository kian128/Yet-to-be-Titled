package entity;

import org.lwjgl.util.vector.Vector3f;

import core.Main;

import render.ModelCube;
import render.ModelSlope;
import render.Textures;
import world.World;

public class EntityPresets {
	
	public static EntityMob newMobPlayer(float x, float y, float z) {
		return new EntityMob("mob", "player", x, y, z, Main.playerWidth, Main.playerHeight, Main.playerWidth, true, new ModelCube(), new Vector3f(0.6f, 0, 0), 0.005f);
	}
	
	public static EntityMob newMobNpc(float x, float y, float z) {
		return new EntityMob("mob", "npc", x, y, z, 1, 1, 1, true, new ModelCube(), new Vector3f(0, 0.6f, 0), 0.005f);
	}
	
	public static EntityBlock newBlockCrate(float x, float y, float z, float xWidth, float height, float zWidth) {
		return new EntityBlock("block", "crate", x, y, z, xWidth, height, zWidth, true, new ModelCube(), Textures.TEX_CRATE);
	}
	
	public static EntityBlock newBlockWood(float x, float y, float z) {
		return new EntityBlock("block", "wood", x, y, z, 1, 1, 1, true, new ModelCube(), Textures.TEX_WOOD);
	}
	
	public static EntityBlock newBlockGrass(float x, float y, float z) {
		return new EntityBlock("block", "grass", x, y, z, 1, 1, 1, true, new ModelCube(), Textures.TEX_GRASS);
	}
	
	public static EntityBlock newSlopeWood(float x, float y, float z) {
		return new EntityBlock("slope", "wood", x, y, z, 1, 1, 1, true, new ModelSlope(), Textures.TEX_CRATE);
	}
}