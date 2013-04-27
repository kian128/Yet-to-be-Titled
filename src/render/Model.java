package render;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector3f;

public class Model {
	
	protected float[] vertices;
	protected float[] color;
	protected float[] normals;
	
	protected Model() {
	}
	
	public float[] getVertices(float x, float y, float z, float xWidth, float height, float zWidth) {
		return vertices;
	}
	
	public float[] getColor(float red, float green, float blue) {
		return color;
	}
	
	public float[] getNormals() {
		return normals;
	}
	
	public void render(float x, float y, float z, float xWidth, float height, float zWidth, float red, float green, float blue) {
	}

	public void render(float x, float y, float z, float xWidth, float height, float zWidth, int texture) {
	}
}
