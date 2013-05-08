package render;

import static org.lwjgl.opengl.GL11.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.PNGDecoder;
import org.newdawn.slick.opengl.PNGDecoder.Format;

public class Textures {
	
	public static int TEX_GRASS;
	public static int TEX_CRATE;
	
	public void initTextures() {
		TEX_GRASS = loadTexture("res/textures/grass.png");
		TEX_CRATE = loadTexture("res/textures/crate.png");
	}
	
	public int loadTexture(String fileDir) {
		int texture = glGenTextures();
		InputStream in = null;
		try {
			in = new FileInputStream(fileDir);
			PNGDecoder decoder = new PNGDecoder(in);
			ByteBuffer buffer = BufferUtils.createByteBuffer(4 * decoder.getWidth() * decoder.getHeight());
			decoder.decode(buffer, decoder.getWidth() * 4, Format.RGBA);
			buffer.flip();
			in.close();

			glBindTexture(GL_TEXTURE_2D, texture);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
			glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, decoder.getWidth(), decoder.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
			glBindTexture(GL_TEXTURE_2D, 0);
		}catch (FileNotFoundException ex) {
			System.err.println("Failed to find the texture files.");
               Display.destroy();
               System.exit(1);
		}catch (IOException ex) {
               System.err.println("Failed to load the texture files.");
               Display.destroy();
               System.exit(1);
           }
		return texture;
	}
}