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

}
