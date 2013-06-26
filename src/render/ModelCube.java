package render;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.GL11;

public class ModelCube extends Model {
	
	@Override
	public void render(float x, float y, float z, float xWidth, float height, float zWidth, float rot, int texture) {
		float x1 = rotateCoordinates2f(x, z, x + xWidth / 2, z + zWidth / 2, rot).x, z1 = rotateCoordinates2f(x, z, x + xWidth / 2, z + zWidth / 2, rot).y;
		float x2 = rotateCoordinates2f(x, z + zWidth, x + xWidth / 2, z + zWidth / 2, rot).x, z2 = rotateCoordinates2f(x, z + zWidth, x + xWidth / 2, z + zWidth / 2, rot).y;
		float x3 = rotateCoordinates2f(x + xWidth, z + zWidth, x + xWidth / 2, z + zWidth / 2, rot).x, z3 = rotateCoordinates2f(x + xWidth, z + zWidth, x + xWidth / 2, z + zWidth / 2, rot).y;
		float x4 = rotateCoordinates2f(x + xWidth, z, x + xWidth / 2, z + zWidth / 2, rot).x, z4 = rotateCoordinates2f(x + xWidth, z, x + xWidth / 2, z + zWidth / 2, rot).y;
		
		glBindTexture(GL_TEXTURE_2D, texture);
			glBegin(GL_QUADS);
				glNormal3f(0, 1, 0); //top
					glTexCoord2f(0, 0);
					glVertex3f(x4, y + height, z4);
					glTexCoord2f(1, 0);
					glVertex3f(x1, y + height, z1);
					glTexCoord2f(1, 1);
					glVertex3f(x2, y + height, z2);
					glTexCoord2f(0, 1);
					glVertex3f(x3, y + height, z3);
		            
				glNormal3f(0, -1, 0); //bottom
					glTexCoord2f(0, 0);
					glVertex3f(x3, y, z3);
					glTexCoord2f(1, 0);
					glVertex3f(x2, y, z2);
					glTexCoord2f(1, 1);
					glVertex3f(x1, y, z1);
					glTexCoord2f(0, 1);
					glVertex3f(x4, y, z4);
		           
				glNormal3f(rotateCoordinates2f(0, 1, 0, 0, rot).x, 0, rotateCoordinates2f(0, 1, 0, 0, rot).y); //front
					glTexCoord2f(1, 0);
					glVertex3f(x3, y + height, z3);
					glTexCoord2f(0, 0);
					glVertex3f(x2, y + height, z2);
					glTexCoord2f(0, 1);
					glVertex3f(x2, y, z2);
					glTexCoord2f(1, 1);
					glVertex3f(x3, y, z3);
		           
				glNormal3f(rotateCoordinates2f(0, -1, 0, 0, rot).x, 0, rotateCoordinates2f(0, -1, 0, 0, rot).y); //back
					glTexCoord2f(0, 1);
					glVertex3f(x4, y, z4);
					glTexCoord2f(1, 1);
					glVertex3f(x1, y, z1);
					glTexCoord2f(1, 0);
					glVertex3f(x1, y + height, z1);
					glTexCoord2f(0, 0);
					glVertex3f(x4, y + height, z4);
		           
				glNormal3f(rotateCoordinates2f(-1, 0, 0, 0, rot).x, 0, rotateCoordinates2f(-1, 0, 0, 0, rot).y); //left
					glTexCoord2f(1, 0);
					glVertex3f(x2, y + height, z2);
					glTexCoord2f(0, 0);
					glVertex3f(x1, y + height, z1);
					glTexCoord2f(0, 1);
					glVertex3f(x1, y, z1);
					glTexCoord2f(1, 1);
					glVertex3f(x2, y, z2);
		           
				glNormal3f(rotateCoordinates2f(1, 0, 0, 0, rot).x, 0, rotateCoordinates2f(1, 0, 0, 0, rot).y); //right
					glTexCoord2f(1, 0);
					glVertex3f(x4, y + height, z4);
					glTexCoord2f(0, 0);
					glVertex3f(x3, y + height, z3);
					glTexCoord2f(0, 1);
					glVertex3f(x3, y, z3);
					glTexCoord2f(1, 1);
					glVertex3f(x4, y, z4);
			glEnd();
		glBindTexture(GL_TEXTURE_2D, 0);
	}
	
	@Override
	public void render(float x, float y, float z, float xWidth, float height, float zWidth, float rot, float red, float green, float blue) {
		float x1 = rotateCoordinates2f(x, z, x + xWidth / 2, z + zWidth / 2, rot).x, z1 = rotateCoordinates2f(x, z, x + xWidth / 2, z + zWidth / 2, rot).y;
		float x2 = rotateCoordinates2f(x, z + zWidth, x + xWidth / 2, z + zWidth / 2, rot).x, z2 = rotateCoordinates2f(x, z + zWidth, x + xWidth / 2, z + zWidth / 2, rot).y;
		float x3 = rotateCoordinates2f(x + xWidth, z + zWidth, x + xWidth / 2, z + zWidth / 2, rot).x, z3 = rotateCoordinates2f(x + xWidth, z + zWidth, x + xWidth / 2, z + zWidth / 2, rot).y;
		float x4 = rotateCoordinates2f(x + xWidth, z, x + xWidth / 2, z + zWidth / 2, rot).x, z4 = rotateCoordinates2f(x + xWidth, z, x + xWidth / 2, z + zWidth / 2, rot).y;
		
		glBegin(GL_QUADS);
			glColor3f(red, green, blue);
				glNormal3f(0, 1, 0); //top face
					glVertex3f(x4, y + height, z4);
					glVertex3f(x1, y + height, z1);
					glVertex3f(x2, y + height, z2);
					glVertex3f(x3, y + height, z3);
            
				glNormal3f(0, -1, 0); //bottom face
					glVertex3f(x3, y, z3);
					glVertex3f(x2, y, z2);
					glVertex3f(x1, y, z1);
					glVertex3f(x4, y, z4);
           
				glNormal3f(0, 0, 1); //front face
					glVertex3f(x3, y + height, z3);
					glVertex3f(x2, y + height, z2);
					glVertex3f(x2, y, z2);
					glVertex3f(x3, y, z3);
           
				glNormal3f(0, 0, -1); //back face
					glVertex3f(x4, y, z4);
					glVertex3f(x1, y, z1);
					glVertex3f(x1, y + height, z1);
					glVertex3f(x4, y + height, z4);
				
				glNormal3f(-1, 0, 0); //left face
					glVertex3f(x2, y + height, z2);
					glVertex3f(x1, y + height, z1);
					glVertex3f(x1, y, z1);
					glVertex3f(x2, y, z2);
           
				glNormal3f(1, 0, 0); //right face
					glVertex3f(x4, y + height, z4);
					glVertex3f(x3, y + height, z3);
					glVertex3f(x3, y, z3);
					glVertex3f(x4, y, z4);
			glColor3f(1, 1, 1);
		glEnd();
		
		/*glBegin(GL_TRIANGLES);
			glColor3f(red, green, blue);
				glNormal3f(-1, 0, 0);
					glVertex3f(x, y, z);
					glVertex3f(x, y, z + zWidth);
					glVertex3f(x, y + height, z + zWidth);
				
				glNormal3f(0, 0, -1);
					glVertex3f(x + xWidth, y + height, z);
					glVertex3f(x, y, z);
					glVertex3f(x, y + height, z);
					
				glNormal3f(0, -1, 0);
					glVertex3f(x + xWidth, y, z + zWidth);
					glVertex3f(x, y, z);
					glVertex3f(x + xWidth, y, z);
			    
					glNormal3f(0, 0, -1);
					glVertex3f(x + xWidth, y + height, z);
					glVertex3f(x + xWidth, y, z);
					glVertex3f(x, y, z);
			
				glNormal3f(-1, 0, 0);
					glVertex3f(x, y, z);
					glVertex3f(x, y + height, z + zWidth);
					glVertex3f(x, y + height, z);
				
				glNormal3f(0, -1, 0);
					glVertex3f(x + xWidth, y, z + zWidth);
					glVertex3f(x, y, z + zWidth);
					glVertex3f(x, y, z);
			   
				glNormal3f(0, 0, 1);
					glVertex3f(x, y + height, z + zWidth);
					glVertex3f(x, y, z + zWidth);
					glVertex3f(x + xWidth, y, z + zWidth);
			   
				glNormal3f(1, 0, 0);
					glVertex3f(x + xWidth, y + height, z + zWidth);
					glVertex3f(x + xWidth, y, z);
					glVertex3f(x + xWidth, y + height, z);
			    
				glNormal3f(1, 0, 0);
					glVertex3f(x + xWidth, y, z);
					glVertex3f(x + xWidth, y + height, z + zWidth);
					glVertex3f(x + xWidth, y, z + zWidth);
			
				glNormal3f(0, 1, 0);
					glVertex3f(x + xWidth, y + height, z + zWidth);
					glVertex3f(x + xWidth, y + height, z);
					glVertex3f(x, y + height, z);
			
				glNormal3f(0, 1, 0);
					glVertex3f(x + xWidth, y + height, z + zWidth);
					glVertex3f(x, y + height, z);
					glVertex3f(x, y + height, z + zWidth);
			
				glNormal3f(0, 0, 1);
					glVertex3f(x + xWidth, y + height, z + zWidth);
					glVertex3f(x, y + height, z + zWidth);
					glVertex3f(x + xWidth, y, z + zWidth);
		glEnd();*/
	}
	
	@Override
	public float[] getVertices(float x, float y, float z, float xWidth, float height, float zWidth) {
		return new float[] {
			x, y, z,
			x, y, z + zWidth,
			x, y + height, z + zWidth,
			    
			x + xWidth, y + height, z,
			x, y, z,
			x, y + height, z,
			    
			x + xWidth, y, z + zWidth,
			x, y, z,
			x + xWidth, y, z,
			    
			x + xWidth, y + height, z,
			x + xWidth, y, z,
			x, y, z,
			    
			x, y, z,
			x, y + height, z + zWidth,
			x, y + height, z,
			    
			x + xWidth, y, z + zWidth,
			x, y, z + zWidth,
			x, y, z,
			   
			x, y + height, z + zWidth,
			x, y, z + zWidth,
			x + xWidth, y, z + zWidth,
			   
			x + xWidth, y + height, z + zWidth,
			x + xWidth, y, z,
			x + xWidth, y + height, z,
			    
			x + xWidth, y, z,
			x + xWidth, y + height, z + zWidth,
			x + xWidth, y, z + zWidth,
			    
			x + xWidth, y + height, z + zWidth,
			x + xWidth, y + height, z,
			x, y + height, z,
			    
			x + xWidth, y + height, z + zWidth,
			x, y + height, z,
			x, y + height, z + zWidth,
			    
			x + xWidth, y + height, z + zWidth,
			x, y + height, z + zWidth,
			x + xWidth, y, z + zWidth
		};
	}
	
	@Override
	public float[] getColor(float red, float green, float blue) {
		return new float[] {
			red, green, blue,
			red, green, blue,
			red, green, blue,

			red, green, blue,
			red, green, blue,
			red, green, blue,
			
			red, green, blue,
			red, green, blue,
			red, green, blue,
			
			red, green, blue,
			red, green, blue,
			red, green, blue,
			
			red, green, blue,
			red, green, blue,
			red, green, blue,
			
			red, green, blue,
			red, green, blue,
			red, green, blue,
			
			red, green, blue,
			red, green, blue,
			red, green, blue,
			
			red, green, blue,
			red, green, blue,
			red, green, blue,
			
			red, green, blue,
			red, green, blue,
			red, green, blue,
			
			red, green, blue,
			red, green, blue,
			red, green, blue,
			
			red, green, blue,
			red, green, blue,
			red, green, blue,
			
			red, green, blue,
			red, green, blue,
			red, green, blue
		};
	}
	
	@Override
	public float[] getNormals() {
		return new float[] {
			-1, 0, 0, 
			-1, 0, 0, 
			-1, 0, 0,
			
			0, 0, -1, 
			0, 0, -1, 
			0, 0, -1,
			
			0, -1, 0, 
			0, -1, 0, 
			0, -1, 0,
			
			0, 0, -1, 
			0, 0, -1, 
			0, 0, -1,
			
			-1, 0, 0, 
			-1, 0, 0, 
			-1, 0, 0,
			
			0, -1, 0, 
			0, -1, 0, 
			0, -1, 0,
			
			0, 0, 1, 
			0, 0, 1, 
			0, 0, 1,
			
			1, 0, 0, 
			1, 0, 0, 
			1, 0, 0,
			
			1, 0, 0, 
			1, 0, 0, 
			1, 0, 0,
			
			0, 1, 0, 
			0, 1, 0, 
			0, 1, 0,
			
			0, 1, 0, 
			0, 1, 0, 
			0, 1, 0, 
			
			0, 0, 1, 
			0, 0, 1, 
			0, 0, 1
		};
	}
}