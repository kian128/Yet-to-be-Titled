package render;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL14.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.ARBFramebufferObject.*;
import static org.lwjgl.util.glu.GLU.*;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import core.Lighting;
import core.Main;
import core.Screen;
import core.Timing;
import core.World;
import entity.Entity;
import entity.EntityMob;

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
	
	private float renderDistanceFromPlayer = 17;
	
	EntityMob player;
	
	public Renderer() {
		/*
		mobVertexHandler = glGenBuffers();
		mobVertexData = BufferUtils.createFloatBuffer(0);
		mobVertexBufferSize = 0;
		
		mobColorHandler = glGenBuffers();
		mobColorData = BufferUtils.createFloatBuffer(0);
		mobColorBufferSize = 0;
		
		mobNormalHandler = glGenBuffers();
		mobNormalData = BufferUtils.createFloatBuffer(0);
		mobNormalBufferSize = 0;
		
		terrainVertexHandler = glGenBuffers();
		terrainVertexData = BufferUtils.createFloatBuffer(0);
		terrainVertexBufferSize = 0;
		
		terrainColorHandler = glGenBuffers();
		terrainColorData = BufferUtils.createFloatBuffer(0);
		terrainColorBufferSize = 0;
		
		terrainNormalHandler = glGenBuffers();
		terrainNormalData = BufferUtils.createFloatBuffer(0);
		terrainNormalBufferSize = 0;
		*/
		
		final int MAX_RENDERBUFFER_SIZE = glGetInteger(GL_MAX_RENDERBUFFER_SIZE);
		final int MAX_TEXTURE_SIZE = glGetInteger(GL_MAX_TEXTURE_SIZE);
		
		if(MAX_TEXTURE_SIZE > shadowMapResolution) {
			if(MAX_RENDERBUFFER_SIZE < MAX_TEXTURE_SIZE) {
				shadowMapWidth = shadowMapHeight = MAX_RENDERBUFFER_SIZE;
			}else{
				shadowMapWidth = shadowMapHeight = shadowMapResolution;
			}
		}else{
			shadowMapWidth = shadowMapHeight = MAX_TEXTURE_SIZE;
		}
		
		frameBuffer = glGenFramebuffers();
		glBindFramebuffer(GL_FRAMEBUFFER, frameBuffer);
		
		renderBuffer = glGenRenderbuffers();
		glBindRenderbuffer(GL_RENDERBUFFER, renderBuffer);
		
		glRenderbufferStorage(GL_RENDERBUFFER, GL_DEPTH_COMPONENT32, shadowMapWidth, shadowMapHeight);
		glFramebufferRenderbuffer(GL_FRAMEBUFFER, GL_DEPTH_ATTACHMENT, GL_RENDERBUFFER, renderBuffer);
		
		glDrawBuffer(GL_NONE); //disable color writing - not needed
		glReadBuffer(GL_NONE); //disable color reading - not needed
		
		//error checking
		int FBOStatus = glCheckFramebufferStatus(GL_FRAMEBUFFER);
		if(FBOStatus != GL_FRAMEBUFFER_COMPLETE) {
			System.err.println(gluErrorString(glGetError()));
		}
		
		glBindFramebuffer(GL_FRAMEBUFFER, 0);
		
		textureBuffer = BufferUtils.createFloatBuffer(16);
		depthModelViewProjection = new Matrix4f();
		
		player = Main.player;
	}
	
	public void render2D() {
		Fonts.drawString(10, 10, "FPS: " + Timing.displayfps);
		Fonts.drawString(10, 30, "TEST STRING");
	}
	
	public void render3D() {
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
		
		/*glEnableClientState(GL_VERTEX_ARRAY);
		glEnableClientState(GL_COLOR_ARRAY);
		glEnableClientState(GL_NORMAL_ARRAY);
		
		glBindBuffer(GL_ARRAY_BUFFER, mobVertexHandler);
		glBufferData(GL_ARRAY_BUFFER, mobVertexData, GL_STATIC_DRAW);
			glVertexPointer(3, GL_FLOAT, 0, 0L);
		
		glBindBuffer(GL_ARRAY_BUFFER, mobColorHandler);
		glBufferData(GL_ARRAY_BUFFER, mobColorData, GL_STATIC_DRAW);
			glColorPointer(3, GL_FLOAT, 0, 0L);
		
		glBindBuffer(GL_ARRAY_BUFFER, mobNormalHandler);
		glBufferData(GL_ARRAY_BUFFER, mobNormalData, GL_STATIC_DRAW);
			glNormalPointer(GL_FLOAT, 0, 0L);
		
		glDrawArrays(GL_TRIANGLES, 0, mobVertexBufferSize);
		
		glBindBuffer(GL_ARRAY_BUFFER, terrainColorHandler);
		glBufferData(GL_ARRAY_BUFFER, terrainColorData, GL_STATIC_DRAW);
			glColorPointer(3, GL_FLOAT, 0, 0L);
		
		glBindBuffer(GL_ARRAY_BUFFER, terrainNormalHandler);
		glBufferData(GL_ARRAY_BUFFER, terrainNormalData, GL_STATIC_DRAW);
			glNormalPointer(GL_FLOAT, 0, 0L);
		
		glBindBuffer(GL_ARRAY_BUFFER, terrainVertexHandler);
		glBufferData(GL_ARRAY_BUFFER, terrainVertexData, GL_STATIC_DRAW);
			glVertexPointer(3, GL_FLOAT, 0, 0L);
		
		glDrawArrays(GL_TRIANGLES, 0, terrainVertexBufferSize);
		
		glDisableClientState(GL_VERTEX_ARRAY);
		glDisableClientState(GL_COLOR_ARRAY);
		glDisableClientState(GL_NORMAL_ARRAY);
		
		glBindBuffer(GL_ARRAY_BUFFER, 0);*/
	}
	
	/*public void updateMobs() {
		mobVertexBufferSize = 0;
		mobColorBufferSize = 0;
		mobNormalBufferSize = 0;
		
		for(int n = 0; n < World.mobList.size(); n++) {
			if(isEntityWithinRenderBounds(World.mobList.get(n))) {
				mobVertexBufferSize += World.mobList.get(n).getVertices().length;
				mobColorBufferSize += World.mobList.get(n).getColor().length;
				mobNormalBufferSize += World.mobList.get(n).getNormals().length;
			}
		}
		
		mobVertexData = BufferUtils.createFloatBuffer(mobVertexBufferSize);
		mobColorData = BufferUtils.createFloatBuffer(mobColorBufferSize);
		mobNormalData = BufferUtils.createFloatBuffer(mobNormalBufferSize);
		
		for(int n = 0; n < World.mobList.size(); n++) {
			if(isEntityWithinRenderBounds(World.mobList.get(n))) {
				mobVertexData.put(World.mobList.get(n).getVertices());
				mobColorData.put(World.mobList.get(n).getColor());
				mobNormalData.put(World.mobList.get(n).getNormals());
			}
		}
		
		mobVertexData.flip();
		mobColorData.flip();
		mobNormalData.flip();
	}
	
	public void updateTerrain() {
		terrainVertexBufferSize = 0;
		terrainColorBufferSize = 0;
		terrainNormalBufferSize = 0;
		
		for(int n = 0; n < World.terrainList.size(); n++) {
			if(isEntityWithinRenderBounds(World.terrainList.get(n))) {
				terrainVertexBufferSize += World.terrainList.get(n).getVertices().length;
				terrainColorBufferSize += World.terrainList.get(n).getColor().length;
				terrainNormalBufferSize += World.terrainList.get(n).getNormals().length;
			}
		}
		
		terrainVertexData = BufferUtils.createFloatBuffer(terrainVertexBufferSize);
		terrainColorData = BufferUtils.createFloatBuffer(terrainColorBufferSize);
		terrainNormalData = BufferUtils.createFloatBuffer(terrainNormalBufferSize);
		
		for(int n = 0; n < World.terrainList.size(); n++) {
			if(isEntityWithinRenderBounds(World.terrainList.get(n))) {
				terrainVertexData.put(World.terrainList.get(n).getVertices());
				terrainColorData.put(World.terrainList.get(n).getColor());
				terrainNormalData.put(World.terrainList.get(n).getNormals());
			}
		}
		
		terrainVertexData.flip();
		terrainColorData.flip();
		terrainNormalData.flip();
	}*/
	
	private boolean isInRenderDistanceFromPlayer(float x, float y, float z) {
		//float angle = (float) Math.tanh((z - player.getZ()) / (x - player.getX()));
		float dist = (float) Math.sqrt(Math.pow(x - player.getX(), 2) + Math.pow(y - player.getY(), 2) + Math.pow(z - player.getZ(), 2));
		
		if(z > Main.player.getZ() - renderDistanceFromPlayer && z < Main.player.getZ() + 10) {
			if(x < Main.player.getX() + 20 && x > Main.player.getX() - 20) {
				return true;
			}
		}
		return false;
	}
	
	public void generateTextureCoordinates() {
		glTexEnvi(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_MODULATE); //TODO: look up what this does
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_COMPARE_MODE, GL_COMPARE_R_TO_TEXTURE);
		
		glEnable(GL_TEXTURE_GEN_S);
		glEnable(GL_TEXTURE_GEN_T);
		glEnable(GL_TEXTURE_GEN_R);
		glEnable(GL_TEXTURE_GEN_Q);
		
		textureBuffer.clear();
		
		textureBuffer.put(0, depthModelViewProjection.m00);
		textureBuffer.put(1, depthModelViewProjection.m01);
		textureBuffer.put(2, depthModelViewProjection.m02);
		textureBuffer.put(3, depthModelViewProjection.m03);
		glTexGen(GL_S, GL_EYE_PLANE, textureBuffer);
		
		textureBuffer.put(0, depthModelViewProjection.m10);
		textureBuffer.put(1, depthModelViewProjection.m11);
		textureBuffer.put(2, depthModelViewProjection.m12);
		textureBuffer.put(3, depthModelViewProjection.m13);
		glTexGen(GL_T, GL_EYE_PLANE, textureBuffer);
		
		textureBuffer.put(0, depthModelViewProjection.m20);
		textureBuffer.put(1, depthModelViewProjection.m21);
		textureBuffer.put(2, depthModelViewProjection.m22);
		textureBuffer.put(3, depthModelViewProjection.m23);
		glTexGen(GL_R, GL_EYE_PLANE, textureBuffer);
		
		textureBuffer.put(0, depthModelViewProjection.m30);
		textureBuffer.put(1, depthModelViewProjection.m31);
		textureBuffer.put(2, depthModelViewProjection.m32);
		textureBuffer.put(3, depthModelViewProjection.m33);
		glTexGen(GL_Q, GL_EYE_PLANE, textureBuffer);
	}
	
	public void renderShadowMap() {
		FloatBuffer lightModelView = BufferUtils.createFloatBuffer(16);
		FloatBuffer lightProjection = BufferUtils.createFloatBuffer(16);
		
		Matrix4f lightModelViewTemp = new Matrix4f();
		Matrix4f lightProjectionTemp = new Matrix4f();
		
		float sceneBoundingRadius = 30;
		
		float dx = Lighting.lightPosition.get(0) - player.getX();
		float dy = Lighting.lightPosition.get(1) - player.getY();
		float dz = Lighting.lightPosition.get(2) - player.getZ();
		
		float lightToSceneDistance = (float) Math.sqrt(dx * dx + dy * dy + dz * dz);
		
		float nearPlane = lightToSceneDistance - sceneBoundingRadius;
		
		if(nearPlane < 0) {
			System.err.println("Camera is too close to screen");
		}
		
		//creating viewing frustrum of the light source
		float fov = (float) Math.toDegrees(2 * Math.atan(sceneBoundingRadius / lightToSceneDistance));
		
		glMatrixMode(GL_PROJECTION);
		
		glPushMatrix();
			glLoadIdentity();
		
			gluPerspective(fov, 1, nearPlane, nearPlane + sceneBoundingRadius * 2);
			glGetFloat(GL_PROJECTION_MATRIX, lightProjection);
			glMatrixMode(GL_MODELVIEW);
			
		glPushMatrix();
			glLoadIdentity();
				
			gluLookAt(Lighting.lightPosition.get(0), Lighting.lightPosition.get(1), Lighting.lightPosition.get(2), 0, 0, 0, 0, 1, 0);
			glGetFloat(GL_MODELVIEW_MATRIX, lightModelView);
			glViewport(0, 0, shadowMapWidth, shadowMapHeight);
				
			glBindFramebuffer(GL_FRAMEBUFFER, frameBuffer);
			
			glClear(GL_DEPTH_BUFFER_BIT);
		
		glPushAttrib(GL_ALL_ATTRIB_BITS);
		{
			glShadeModel(GL_FLAT);
			
			glDisable(GL_LIGHTING);
			glDisable(GL_COLOR_MATERIAL);
			glDisable(GL_NORMALIZE);
			
			glColorMask(false, false, false, false);
			
			glEnable(GL_POLYGON_OFFSET_FILL);
			
			render3D();
			
			glCopyTexImage2D(GL_TEXTURE_2D, 0, GL_DEPTH_COMPONENT, 0, 0, shadowMapWidth, shadowMapHeight, 0);
			
			glPopMatrix();
			glMatrixMode(GL_PROJECTION);
			
			glPopMatrix();
			glMatrixMode(GL_MODELVIEW);
			
			glBindFramebuffer(GL_FRAMEBUFFER, 0);
		}
		glPopAttrib();
		
		glViewport(0, 0, Display.getWidth(), Display.getHeight());
		lightProjectionTemp.load(lightProjection);
		lightModelViewTemp.load(lightModelView);
		lightProjection.flip();
		lightModelView.flip();
		
		depthModelViewProjection.setIdentity();
		depthModelViewProjection.translate(new Vector3f(0.5f, 0.5f, 0.5f));
		depthModelViewProjection.scale(new Vector3f(0.5f, 0.5f, 0.5f));
		
		Matrix4f.mul(depthModelViewProjection, lightProjectionTemp, depthModelViewProjection);
		Matrix4f.mul(depthModelViewProjection, lightModelViewTemp, depthModelViewProjection);
		Matrix4f.transpose(depthModelViewProjection, depthModelViewProjection);
	}
}