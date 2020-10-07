package de.hexagonsoftware.engine.graphics;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;

import de.hexagonsoftware.engine.HexagonEngine;

/**
 * @author N3ROO
 * */
public class EventListener implements GLEventListener {
	public static GL2 gl = null;
	
    public void init(GLAutoDrawable drawable) {
        HexagonEngine.logger.info("Setting up OpenGL settings...");

        GL2 gl = drawable.getGL().getGL2();
        gl.glClearColor(0, 0, 0, 1);
        gl.glEnable(GL2.GL_TEXTURE_2D);
        gl.glEnable(GL2.GL_BLEND);
        gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
    }

	public void display(GLAutoDrawable drawable) {
		// Update gl variable
        gl = drawable.getGL().getGL2();

        if (HexagonEngine.isHE_RUNNING())
        	HexagonEngine.getGame().render();
        
        // It clears buffers about color writing.
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
	}
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {}
	
	public void dispose(GLAutoDrawable drawable) {

	}
}
