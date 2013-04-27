package core;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.*;
import static org.lwjgl.opengl.GL14.*;
import static org.lwjgl.opengl.ARBShadowAmbient.*;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public class Lighting {
	
	public static FloatBuffer lightPosition;
	public static FloatBuffer ambientLight;
	public static FloatBuffer diffuseLight;
	
	private float shadowIntensity = 0.5f;
	private float diffuseIntensity = 0.6f;
	
	public void initLighting() {
		glEnable(GL_LIGHTING);
		glEnable(GL_LIGHT0);
		
		glEnable(GL_NORMALIZE);
		glEnable(GL_COLOR_MATERIAL);
		
		setLightPosition(GL_LIGHT0, 0, 0, 0);
		setAmbientLight(GL_LIGHT0, 0, 0, 0, 1);
		setDiffuseLight(GL_LIGHT0, diffuseIntensity, diffuseIntensity, diffuseIntensity, 1);
		glLightModel(GL_LIGHT_MODEL_AMBIENT, ambientLight);
		
		glPolygonOffset(2.5f, 0.0f);

		//texture clamping TODO: read up on this
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
		
		//bilinear texture filtering TODO: read up on this aswell
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
		
		glTexParameteri(GL_TEXTURE_2D, GL_DEPTH_TEXTURE_MODE, GL_INTENSITY);
		
		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_COMPARE_FAIL_VALUE_ARB, 1 - shadowIntensity);
		
		glTexGeni(GL_S, GL_TEXTURE_GEN_MODE, GL_EYE_LINEAR);
		glTexGeni(GL_T, GL_TEXTURE_GEN_MODE, GL_EYE_LINEAR);
		glTexGeni(GL_R, GL_TEXTURE_GEN_MODE, GL_EYE_LINEAR);
		glTexGeni(GL_Q, GL_TEXTURE_GEN_MODE, GL_EYE_LINEAR);
	}
	
	public void setLightPosition(int lightNumber, float x, float y, float z) {
		lightPosition = BufferUtils.createFloatBuffer(4);
		lightPosition.put(x).put(y).put(z).put(1.0f);
		lightPosition.flip();
		glLight(lightNumber, GL_POSITION, lightPosition);
	}
	
	public void setAmbientLight(int lightNumber, float red, float green, float blue, float alpha) {
		ambientLight = BufferUtils.createFloatBuffer(4);
		ambientLight.put(green).put(red).put(blue).put(alpha);
		ambientLight.flip();
		glLight(lightNumber, GL_AMBIENT, ambientLight);
	}
	
	public void setDiffuseLight(int lightNumber, float red, float green, float blue, float alpha) {
		ambientLight = BufferUtils.createFloatBuffer(4);
		ambientLight.put(green).put(red).put(blue).put(alpha);
		ambientLight.flip();
		glLight(lightNumber, GL_DIFFUSE, ambientLight);
	}
	
	public void enableLighting() {
		glEnable(GL_LIGHTING);
		glEnable(GL_LIGHT0);
		glEnable(GL_NORMALIZE);
	}
	
	public void disableLighting() {
		glDisable(GL_LIGHTING);
		glDisable(GL_LIGHT0);
		glDisable(GL_NORMALIZE);
	}
}