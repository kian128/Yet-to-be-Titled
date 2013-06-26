package core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

import world.World;

import entity.EntityBlock;
import entity.EntityMob;
import entity.EntityPresets;

public class SaveHandler {
	
	public void save(String dir) {
		FileWriter fwriter = null;
		BufferedWriter writer = null;
		
		try {
			fwriter = new FileWriter(dir);
	        writer = new BufferedWriter(fwriter);
	        
	        for(int n = 0; n < World.mobList.size(); n++) {
	        	EntityMob mob = World.mobList.get(n);
	        	writer.write(mob.id + " ");
	        	writer.write(mob.getX() + " ");
	        	writer.write(mob.getY() + " ");
	        	writer.write(mob.getZ() + " ");
	        	writer.write(mob.getXWidth() + " ");
	        	writer.write(mob.getHeight() + " ");
	        	writer.write(mob.getZWidth() + " ");
	        	writer.write(mob.getRotation() + " ");
	        	/*writer.write(mob.isCollidable + " ");
	        	writer.write(mob.model.toString().split(" ")[1] + " ");
	        	writer.write(mob.texture + " ");
	        	writer.write(mob.colorRed + " ");
	        	writer.write(mob.colorGreen + " ");
	        	writer.write(mob.colorBlue + " ");
	        	writer.write(mob.speed + " ");*/
	        	writer.newLine();
	        }
	        
	        for(int n = 0; n < World.terrainList.size(); n++) {
	        	EntityBlock block = World.terrainList.get(n);
	        	writer.write(block.id + " ");
	        	writer.write(block.getX() + " ");
	        	writer.write(block.getY() + " ");
	        	writer.write(block.getZ() + " ");
	        	writer.write(block.getXWidth() + " ");
	        	writer.write(block.getHeight() + " ");
	        	writer.write(block.getZWidth() + " ");
	        	writer.write(block.getRotation() + " ");
	        	/*writer.write(block.isCollidable + " ");
	        	writer.write(block.model.toString().split(" ")[1] + " ");
	        	writer.write(block.texture + " ");
	        	writer.write(block.colorRed + " ");
	        	writer.write(block.colorGreen + " ");
	        	writer.write(block.colorBlue + " ");*/
	        	writer.newLine();
	        } 
	        
	        writer.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void load(String dir) {
		FileReader freader = null;
		BufferedReader reader = null;
		String s;
		
		try {
			freader = new FileReader(dir);
			reader = new BufferedReader(freader);
			
			while((s = reader.readLine()) != null) {
				String[] sections = s.split(" ");
				
				String id = sections[0];
				float x = Float.parseFloat(sections[1]);
				float y = Float.parseFloat(sections[2]);
				float z = Float.parseFloat(sections[3]);
				float xWidth = Float.parseFloat(sections[4]);
				float height = Float.parseFloat(sections[5]);
				float zWidth = Float.parseFloat(sections[6]);
				float rot = Float.parseFloat(sections[7]);
				/*boolean isCollidable = Boolean.parseBoolean(sections[8]);
				
				Model model;
				
				if(sections[9].contains("render")) {
					Class modelClass = Class.forName(sections[8].split("@")[0]);
					model = (Model) modelClass.newInstance();
				} else {
					model = new Model(sections[9]);
				}
				
				int texture = 0;
				for(int n = 0; n < Textures.textureList.size(); n++) {
					if(sections[10].equals(Integer.toString(Textures.textureList.get(n)))) {
						texture = Textures.textureList.get(n);
					}
				}
				
				float colorRed = Float.parseFloat(sections[11]);
				float colorGreen = Float.parseFloat(sections[12]);
				float colorBlue = Float.parseFloat(sections[13]);
				
				float speed = 0;
				if(sections.length > 14) {
					speed = Float.parseFloat(sections[14]);
				}*/
				
				if(id.startsWith("mob")) {
					/*EntityMob mob;
					if(texture == 0) {
						mob = new EntityMob(id, x, y, z, xWidth, height, zWidth, isCollidable, model, new Vector3f(colorRed, colorGreen, colorBlue), speed);
					} else {
						mob = new EntityMob(id, x, y, z, xWidth, height, zWidth, isCollidable, model, texture, speed);
					}*/
					
					if(id.contains("player")) {
						//Main.player = mob;
						Main.player = EntityPresets.newMobPlayer(x, y, z);
						Main.player.setRotation(rot);
					}
					
					if(id.contains("npc")) {
						//Main.npc = mob;
						Main.npc = EntityPresets.newMobNpc(x, y, z);
						Main.npc.setRotation(rot);
					}
				}
				
				if(id.startsWith("block")) {
					/*EntityBlock block;
					if(texture == 0) {
						block = new EntityBlock(id, x, y, z, xWidth, height, zWidth, isCollidable, model, new Vector3f(colorRed, colorGreen, colorBlue));
					} else {
						block = new EntityBlock(id, x, y, z, xWidth, height, zWidth, isCollidable, model, texture);
					}*/
					
					if(id.contains("grass")) {
						EntityPresets.newBlockGrass(x, y, z).setRotation(rot);
					}
					
					if(id.contains("crate")) {
						EntityPresets.newBlockCrate(x, y, z, xWidth, height, zWidth).setRotation(rot);
					}
					
					if(id.contains("wood")) {
						EntityPresets.newBlockWood(x, y, z).setRotation(rot);
					}
				}
				
				if(id.startsWith("slope")) {
					if(id.contains("wood")) {
						EntityPresets.newSlopeWood(x, y, z);
					}
				}
			}
		} catch(FileNotFoundException e) {
			System.out.println(dir + " not found, creating file...");
			FileWriter fwriter = null;
			BufferedWriter writer= null;
			
			try {
				fwriter = new FileWriter(dir);
		        writer = new BufferedWriter(fwriter);
			} catch(Exception e2) {
				e.printStackTrace();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
