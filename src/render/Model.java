package render;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glNormal3f;
import static org.lwjgl.opengl.GL11.glVertex3f;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

public class Model {
	
	protected float[] vertices;
	protected float[] color;
	protected float[] normals;
	protected Vector2f[] faces; //holds vertex and normal index for each face
	
	protected String path;
	
	protected Model() {
	}
	
	public Model(String dir) {
		loadModel(dir);
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
	
	public void render(float x, float y, float z, float xWidth, float height, float zWidth, float rot, float red, float green, float blue) {
		//overwrite this code if external class handles rendering
		
		glBegin(GL_TRIANGLES);
			glColor3f(red, green, blue);
				for(int n = 0; n < faces.length / 3; n++) {
					//glNormal3f(normals[n], normals[n + 1], normals[n + 2]);
					//glVertex3f(vertices[n * 3], vertices[n * 3 + 1], vertices[n * 3 + 2]);
					
					glNormal3f(normals[(int) (faces[n * 3].y - 1) * 3], normals[(int) (faces[n * 3].y - 1) * 3 + 1], normals[(int) (faces[n * 3].y - 1) * 3 + 2]);
					glVertex3f(x + vertices[(int) (faces[n * 3].x - 1) * 3], y + vertices[(int) (faces[n * 3].x - 1) * 3 + 1], z + vertices[(int) (faces[n * 3].x - 1) * 3 + 2]);
					glNormal3f(normals[(int) (faces[n * 3 + 1].y - 1) * 3], normals[(int) (faces[n * 3 + 1].y - 1) * 3 + 1], normals[(int) (faces[n * 3 + 1].y - 1) * 3 + 2]);
					glVertex3f(x + vertices[(int) (faces[n * 3 + 1].x - 1) * 3], y + vertices[(int) (faces[n * 3 + 1].x - 1) * 3 + 1], z + vertices[(int) (faces[n * 3 + 1].x - 1) * 3 + 2]);
					glNormal3f(normals[(int) (faces[n * 3 + 2].y - 1) * 3], normals[(int) (faces[n * 3 + 2].y - 1) * 3 + 1], normals[(int) (faces[n * 3 + 2].y - 1) * 3 + 2]);
					glVertex3f(x + vertices[(int) (faces[n * 3 + 2].x - 1) * 3], y + vertices[(int) (faces[n * 3 + 2].x - 1) * 3 + 1], z + vertices[(int) (faces[n * 3 + 2].x - 1) * 3+ 2]);
				}
			glColor3f(1, 1, 1);
		glEnd();
	}

	public void render(float x, float y, float z, float xWidth, float height, float zWidth, float rot, int texture) {
	}
	
	public Vector2f rotateCoordinates2f(float x, float y, float xo, float yo, float rot) {
		float x1 = (float) ((x - xo) * Math.cos(Math.toRadians(rot)) + (y - yo) * Math.sin(Math.toRadians(rot))) + xo;
		float y1 = (float) (-(x - xo) * Math.sin(Math.toRadians(rot)) + (y - yo) * Math.cos(Math.toRadians(rot))) + yo;
		
		return new Vector2f(x1, y1);
	}
	
	@Override
	public String toString() {
		if(path != null) {
			return path;
		}
		return this.getClass().toString();
	}
	
	public void loadModel(String dir) {
		path = dir;
		BufferedReader reader;
		String line;
		int lineNumber = 0;
		
		try {
			InputStream in = new FileInputStream(dir);
			reader = new BufferedReader(new InputStreamReader(in));
			
			ArrayList<Float> vertexList = new ArrayList<Float>();
			ArrayList<Float> normalList = new ArrayList<Float>();
			ArrayList<Vector2f> faceList = new ArrayList<Vector2f>();
			
			while((line = reader.readLine()) != null) {
				String[] values = line.split(" ");
				
				if(values[0].equals("v")) {
					for(int n = 1; n < values.length; n++) {
						vertexList.add(Float.parseFloat(values[n]));
					}
				}
				if(values[0].equals("vn")) {
					for(int n = 1; n < values.length; n++) {
						normalList.add(Float.parseFloat(values[n]));
					}
				}
				if(values[0].equals("f")) {
					for(int n = 1; n < values.length; n++) {
						faceList.add(new Vector2f(Float.parseFloat(values[n].split("//")[0]), Float.parseFloat(values[n].split("//")[1])));
					}
				}
			}
			
			vertices = new float[vertexList.size()];
			normals = new float [normalList.size()];
			faces = new Vector2f[faceList.size()];
			
			for(int n = 0; n < vertexList.size(); n++) {
				vertices[n] = vertexList.get(n);
			}
			
			for(int n = 0; n < normalList.size(); n++) {
				normals[n] = normalList.get(n);
			}
			
			for(int n = 0; n < faceList.size(); n++) {
				faces[n] = faceList.get(n);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
