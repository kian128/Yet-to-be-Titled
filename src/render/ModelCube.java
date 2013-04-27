package render;

import static org.lwjgl.opengl.GL11.*;

public class ModelCube extends Model {
	
	public ModelCube() {
	}
	
	@Override
	public void render(float x, float y, float z, float xWidth, float height, float zWidth, int texture) {
		glBindTexture(GL_TEXTURE_2D, texture);
			glBegin(GL_QUADS);
				glNormal3f(0, 1, 0); //top face
					glTexCoord2f(0, 0);
					glVertex3f(x + xWidth, y + height, z);
					glTexCoord2f(1, 0);
					glVertex3f(x, y + height, z);
					glTexCoord2f(1, 1);
					glVertex3f(x, y + height, z + zWidth);
					glTexCoord2f(0, 1);
					glVertex3f(x + xWidth, y + height, z + zWidth);
		            
				glNormal3f(0, -1, 0); //bottom face
					glTexCoord2f(0, 0);
					glVertex3f(x + xWidth, y, z + zWidth);
					glTexCoord2f(1, 0);
					glVertex3f(x, y, z + zWidth);
					glTexCoord2f(1, 1);
					glVertex3f(x, y, z);
					glTexCoord2f(0, 1);
					glVertex3f(x + xWidth, y, z);
		           
				glNormal3f(0, 0, 1); //front face
					glTexCoord2f(1, 0);
					glVertex3f(x + xWidth, y + height, z + zWidth);
					glTexCoord2f(0, 0);
					glVertex3f(x, y + height, z + zWidth);
					glTexCoord2f(0, 1);
					glVertex3f(x, y, z + zWidth);
					glTexCoord2f(1, 1);
					glVertex3f(x + xWidth, y, z + zWidth);
		           
				glNormal3f(0, 0, -1); //back face
					glTexCoord2f(0, 1);
					glVertex3f(x + xWidth, y, z);
					glTexCoord2f(1, 1);
					glVertex3f(x, y, z);
					glTexCoord2f(1, 0);
					glVertex3f(x, y + height, z);
					glTexCoord2f(0, 0);
					glVertex3f(x + xWidth, y + height, z);
		           
				glNormal3f(-1, 0, 0); //left face
					glTexCoord2f(1, 0);
					glVertex3f(x, y + height, z + zWidth);
					glTexCoord2f(0, 0);
					glVertex3f(x, y + height, z);
					glTexCoord2f(0, 1);
					glVertex3f(x, y, z);
					glTexCoord2f(1, 1);
					glVertex3f(x, y, z + zWidth);
		           
				glNormal3f(1, 0, 0); //right face
					glTexCoord2f(1, 0);
					glVertex3f(x + xWidth, y + height, z);
					glTexCoord2f(0, 0);
					glVertex3f(x + xWidth, y + height, z + zWidth);
					glTexCoord2f(0, 1);
					glVertex3f(x + xWidth, y, z + zWidth);
					glTexCoord2f(1, 1);
					glVertex3f(x + xWidth, y, z);
			glEnd();
		glBindTexture(GL_TEXTURE_2D, 0);
	}
	
	@Override
	public void render(float x, float y, float z, float xWidth, float height, float zWidth, float red, float green, float blue) {
		glBegin(GL_QUADS);
			glColor3f(red, green, blue);
				glNormal3f(0, 1, 0);
					glVertex3f(x + xWidth, y + height, z);      
					glVertex3f(x, y + height, z);        
					glVertex3f(x, y + height, z + zWidth);
					glVertex3f(x + xWidth, y + height, z + zWidth);
			            
				glNormal3f(0, -1, 0);
					glVertex3f(x + xWidth, y, z + zWidth);
					glVertex3f(x, y, z + zWidth);
					glVertex3f(x, y, z);
					glVertex3f(x + xWidth, y, z);
			           
				glNormal3f(0, 0, 1);
					glVertex3f(x + xWidth, y + height, z + zWidth);
					glVertex3f(x, y + height, z + zWidth);
					glVertex3f(x, y, z + zWidth);
					glVertex3f(x + xWidth, y, z + zWidth);
			           
				glNormal3f(0, 0, -1);
					glVertex3f(x + xWidth, y, z);
					glVertex3f(x, y, z);
					glVertex3f(x, y + height, z);
			       	glVertex3f(x + xWidth, y + height, z);
			           
			    glNormal3f(-1, 0, 0);
			       	glVertex3f(x, y + height, z + zWidth);
			       	glVertex3f(x, y + height, z);
			       	glVertex3f(x, y, z);
			       	glVertex3f(x, y, z + zWidth);
			           
			    glNormal3f(1, 0, 0);
			       	glVertex3f(x + xWidth, y + height, z);
			       	glVertex3f(x + xWidth, y + height, z + zWidth);
			       	glVertex3f(x + xWidth, y, z + zWidth);
			       	glVertex3f(x + xWidth, y, z);
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