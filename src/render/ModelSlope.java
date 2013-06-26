package render;

import static org.lwjgl.opengl.GL11.*;

public class ModelSlope extends Model {
	
	@Override
	public void render(float x, float y, float z, float xWidth, float height, float zWidth, float rot, int texture) {
		float x1 = rotateCoordinates2f(x, z, x + xWidth / 2, z + zWidth / 2, rot).x, z1 = rotateCoordinates2f(x, z, x + xWidth / 2, z + zWidth / 2, rot).y;
		float x2 = rotateCoordinates2f(x, z + zWidth, x + xWidth / 2, z + zWidth / 2, rot).x, z2 = rotateCoordinates2f(x, z + zWidth, x + xWidth / 2, z + zWidth / 2, rot).y;
		float x3 = rotateCoordinates2f(x + xWidth, z + zWidth, x + xWidth / 2, z + zWidth / 2, rot).x, z3 = rotateCoordinates2f(x + xWidth, z + zWidth, x + xWidth / 2, z + zWidth / 2, rot).y;
		float x4 = rotateCoordinates2f(x + xWidth, z, x + xWidth / 2, z + zWidth / 2, rot).x, z4 = rotateCoordinates2f(x + xWidth, z, x + xWidth / 2, z + zWidth / 2, rot).y;
		
		glBindTexture(GL_TEXTURE_2D, texture);
			glBegin(GL_QUADS);
				glNormal3f(0, 1, 0); //top
					glTexCoord2f(1, 0);
					glVertex3f(x4, y + height, z4);
					glTexCoord2f(0, 0);
					glVertex3f(x1, y + height, z1);
					glTexCoord2f(0, 1);
					glVertex3f(x2, y, z2);
					glTexCoord2f(1, 1);
					glVertex3f(x3, y, z3);
	            
				glNormal3f(0, -1, 0); //bottom
					glTexCoord2f(1, 1);
					glVertex3f(x3, y, z3);
					glTexCoord2f(0, 1);
					glVertex3f(x2, y, z2);
					glTexCoord2f(0, 0);
					glVertex3f(x1, y, z1);
					glTexCoord2f(1, 0);
					glVertex3f(x4, y, z4);
	           
				glNormal3f(0, 0, 1); //front
					glTexCoord2f(1, 1);
					glVertex3f(x3, y, z3);
					glTexCoord2f(0, 1);
					glVertex3f(x2, y, z2);
					glTexCoord2f(0, 1);
					glVertex3f(x2, y, z2);
					glTexCoord2f(1, 1);
					glVertex3f(x3, y, z3);
	           
				glNormal3f(0, 0, -1); //back
					glTexCoord2f(1, 0);
					glVertex3f(x4, y, z4);
					glTexCoord2f(0, 0);
					glVertex3f(x1, y, z1);
					glTexCoord2f(0, 0);
					glVertex3f(x1, y + height, z1);
					glTexCoord2f(1, 0);
					glVertex3f(x4, y + height, z4);
	           
				glNormal3f(-1, 0, 0); //left
					glTexCoord2f(0, 1);
					glVertex3f(x2, y, z2);
					glTexCoord2f(0, 0);
					glVertex3f(x1, y + height, z1);
					glTexCoord2f(0, 0);
					glVertex3f(x1, y, z1);
					glTexCoord2f(0, 1);
					glVertex3f(x2, y, z2);
	           
				glNormal3f(1, 0, 0); //right
					glTexCoord2f(1, 0);
					glVertex3f(x4, y + height, z4);
					glTexCoord2f(1, 1);
					glVertex3f(x3, y, z3);
					glTexCoord2f(1, 1);
					glVertex3f(x3, y, z3);
					glTexCoord2f(1, 0);
					glVertex3f(x4, y, z4);
				glColor3f(1, 1, 1);
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
				glNormal3f(0, 1, 0); //top
					glVertex3f(x4, y + height, z4);
					glVertex3f(x1, y + height, z1);
					glVertex3f(x2, y, z2);
					glVertex3f(x3, y, z3);
        
				glNormal3f(0, -1, 0); //bottom
					glVertex3f(x3, y, z3);
					glVertex3f(x2, y, z2);
					glVertex3f(x1, y, z1);
					glVertex3f(x4, y, z4);
       
				glNormal3f(0, 0, 1); //front
					glVertex3f(x3, y, z3);
					glVertex3f(x2, y, z2);
					glVertex3f(x2, y, z2);
					glVertex3f(x3, y, z3);
       
				glNormal3f(0, 0, -1); //back
					glVertex3f(x4, y, z4);
					glVertex3f(x1, y, z1);
					glVertex3f(x1, y + height, z1);
					glVertex3f(x4, y + height, z4);
       
				glNormal3f(-1, 0, 0); //left
					glVertex3f(x2, y, z2);
					glVertex3f(x1, y + height, z1);
					glVertex3f(x1, y, z1);
					glVertex3f(x2, y, z2);
       
				glNormal3f(1, 0, 0); //right
					glVertex3f(x4, y + height, z4);
					glVertex3f(x3, y, z3);
					glVertex3f(x3, y, z3);
					glVertex3f(x4, y, z4);
			glColor3f(1, 1, 1);
		glEnd();
	}
}
