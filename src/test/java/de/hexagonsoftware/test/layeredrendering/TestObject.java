package de.hexagonsoftware.test.layeredrendering;

import java.awt.Color;
import java.awt.Graphics;

import de.hexagonsoftware.engine.game.GameObject;
import de.hexagonsoftware.engine.game.components.LayeredRenderer;
import de.hexagonsoftware.engine.game.components.Renderer;

/**
 * This objects Demos the LayeredRenderer
 * 
 * @author Felix Eckert
 * */
public class TestObject extends GameObject {
	@Override
	public void start() {
		LayeredRenderer lr = new LayeredRenderer();
		lr.start();
		
		// The first layer to be added
		Renderer layer1 = new Renderer() {
			@Override
			public void render(Graphics g) {
				Color oldColor = g.getColor();
				g.setColor(Color.BLUE);
				g.fillOval(100, 100, 50, 50);
			}
		};
		
		// The second layer to be added
		Renderer layer2 = new Renderer() {
			@Override
			public void render(Graphics g) {
				Color oldColor = g.getColor();
				g.setColor(Color.RED);
				g.fillOval(111, 111, 25, 25);
			}
		};
		
		// Add both layers to the LayeredRenderer
		lr.addLayer(layer1, true);
		lr.addLayer(layer2, true);
		
		// Finally add the LayeredRenderer to this objects Components
		addComponent("layeredRenderer", lr);
	}
	
	@Override
	public void render(Graphics g) {
		super.render(g);
	}
}
