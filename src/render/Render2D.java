package render;

import static org.lwjgl.opengl.GL11.*;

public class Render2D {
	
	public static void renderRectangle(float x, float y, float width, float height, float red, float green, float blue) {
		glBegin(GL_QUADS);
			glColor3f(red, green, blue);
				glVertex2f(x, y);	
				glVertex2f(x, y + height);
				glVertex2f(x + width, y + height);
				glVertex2f(x + width, y);
			glColor3f(1, 1, 1);
		glEnd();
	}
	
	public static void renderTexturedRectangle(float x, float y, float width, float height, int texture) {
		glBindTexture(GL_TEXTURE_2D, texture);
			glBegin(GL_QUADS);
				glVertex2f(x, y);	
				glVertex2f(x, y + height);
				glVertex2f(x + width, y + height);
				glVertex2f(x + width, y);
			glEnd();
		glBindTexture(GL_TEXTURE_2D, 0);
	}
	
	public static void renderTriangleUniformUp(float x, float y, float width, float height, float red, float green, float blue) {
		glBegin(GL_TRIANGLES);
			glColor3f(red, green, blue);
				glVertex2f(x, y + height);
				glVertex2f(x + width, y + height);
				glVertex2f(x + width / 2, y);
			glColor3f(1, 1, 1);
		glEnd();
	}
	
	public static void renderTriangleUniformDown(float x, float y, float width, float height, float red, float green, float blue) {
		glBegin(GL_TRIANGLES);
			glColor3f(red, green, blue);
				glVertex2f(x, y);
				glVertex2f(x + width / 2, y + height);
				glVertex2f(x + width, y);
			glColor3f(1, 1, 1);
		glEnd();
	}
	
	public static void renderLine(float startX, float startY, float endX, float endY, float red, float green, float blue) {
		glBegin(GL_LINES);
			glColor3f(red, green, blue);
				glVertex2f(startX, startY);
				glVertex2f(endX, endY);
			glColor3f(1, 1, 1);
		glEnd();
	}
}