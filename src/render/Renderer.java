package render;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL14.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.ARBFramebufferObject.*;
import static org.lwjgl.util.glu.GLU.*;

import java.awt.Color;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import world.World;

import core.Lighting;
import core.Main;
import core.Options;
import core.Screen;
import core.Timing;
import entity.Entity;
import entity.EntityMob;
import gui.Gui;
import gui.GuiMenuMain;

public class Renderer {
	
	/*private int mobVertexHandler, mobColorHandler, mobNormalHandler;
	private FloatBuffer mobVertexData, mobColorData, mobNormalData;
	private int mobVertexBufferSize, mobColorBufferSize, mobNormalBufferSize;
	
	private int terrainVertexHandler, terrainColorHandler, terrainNormalHandler;
	private FloatBuffer terrainVertexData, terrainColorData, terrainNormalData;
	private int terrainVertexBufferSize, terrainColorBufferSize, terrainNormalBufferSize;*/
	
	private int shadowMapWidth, shadowMapHeight;
	private int frameBuffer, renderBuffer;
	private int shadowMapResolution = 1024;
	
	private FloatBuffer textureBuffer;
	private Matrix4f depthModelViewProjection;
	
	private Options options = new Options();
	
	public void render2D(long delta) {
		if(Main.isInMenu() || Main.isPaused) {
			Gui.updateGui(delta);
		}
		
		if(Main.isInGame()) {
			//Render2D.renderRectangle(10, 10, Main.player.hp * 10, 20, 1, 0, 0, 1);
			Fonts.drawString(Fonts.font_16_White, 15, 60, "Gold: " + Main.player.gold);
			//Fonts.drawString(Fonts.font_16_Black, 15, 13, "HP: " + (int) Main.player.hp);
		}
		
		if(Main.showDev) {
			Fonts.drawString(Fonts.font_16_White, Main.screenWidth - Fonts.font_16_White.getWidth("FPS: " + Timing.displayfps) - 10, 10, "FPS: " + Timing.displayfps);
			Fonts.drawString(Fonts.font_16_White, Main.screenWidth - Fonts.font_16_White.getWidth("FOV: " + options.getFov()) - 10, 30, "FOV: " + options.getFov());
			Fonts.drawString(Fonts.font_16_White, Main.screenWidth - Fonts.font_16_White.getWidth("Paused: " + Main.isPaused) - 10, 50, "Paused: " + Main.isPaused);
			if(Main.isInGame()) {
				Fonts.drawString(Fonts.font_16_White, Main.screenWidth - Fonts.font_16_White.getWidth("Flying: " + Main.player.isFlying) - 10, 70, "Flying: " + Main.player.isFlying);
				Fonts.drawString(Fonts.font_16_White, Main.screenWidth - Fonts.font_16_White.getWidth("x: " + (int) Main.player.getX()) - 10, 90, "x: " + (int) Main.player.getX());
				Fonts.drawString(Fonts.font_16_White, Main.screenWidth - Fonts.font_16_White.getWidth("y: " + (int) Main.player.getY()) - 10, 110, "y: " + (int) Main.player.getY());
				Fonts.drawString(Fonts.font_16_White, Main.screenWidth - Fonts.font_16_White.getWidth("z: " + (int) Main.player.getZ()) - 10, 130, "z: " + (int) Main.player.getZ());
			}
		}
	}
	
	public void render3D() {
		if(Main.isInGame()) {
			for(int n = 0; n < World.mobList.size(); n++) {
				if(isInRenderDistanceFromPlayer(World.mobList.get(n).getX(), World.mobList.get(n).getY(), World.mobList.get(n).getZ())) {
					World.mobList.get(n).render();
				}
			}
			for(int n = 0; n < World.terrainList.size(); n++) {
				if(isInRenderDistanceFromPlayer(World.terrainList.get(n).getX(), World.terrainList.get(n).getY(), World.terrainList.get(n).getZ())) {
					World.terrainList.get(n).render();
				}	
			}
		}
	}
		
	public static boolean isInRenderDistanceFromPlayer(float x, float y, float z) {
		//float angle = (float) Math.tanh((z - player.getZ()) / (x - player.getX()));
		float dist = (float) Math.sqrt(Math.pow(x - Main.player.getX(), 2) + Math.pow(y - Main.player.getY(), 2) + Math.pow(z - Main.player.getZ(), 2));
		
		if(z > Main.player.getZ() - 15 && z < Main.player.getZ() + 12) {
			if(x < Main.player.getX() + 16 && x > Main.player.getX() - 17) {
				return true;
			}
		}
		return false;
	}
}