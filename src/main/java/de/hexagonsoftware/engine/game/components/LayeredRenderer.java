package de.hexagonsoftware.engine.game.components;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

import de.hexagonsoftware.engine.game.IGameObjectComponent;

/**
 * Used for rendering layered awt Graphics.
 * 
 * @author Felix Eckert
 * */
public class LayeredRenderer implements IGameObjectComponent {
	private List<Renderer> renderers;
	private List<Boolean> renderersEnabled;
	
	@Override
	public void start() {
		renderers = new LinkedList<>();
		renderersEnabled = new LinkedList<>();
	}

	@Override
	public void update() {}

	@Override
	public void render(Graphics g) {
		for (Renderer render : renderers) {
			if (renderersEnabled.get(renderers.indexOf(render))) {
				render.render(g);
				continue;
			}
		}
	}

	/**
	 * Adds a layer to the renderers List
	 * 
	 * @param render The SpriteRenderer to be added
	 * @param enabled Sets if the layer should be enabled automatically
	 * */
	public void addLayer(Renderer render, boolean enabled) {
		renderers.add(render);
		renderersEnabled.add(enabled);
	}
	
	/**
	 * Removes a layer from the layer list, automatically moves the layers up
	 * @param layer The layer to be removed
	 * */
	public void removeLayer(int layer) {
		if (renderers.get(layer) == null)
			return;
		
		renderers.remove(layer);
	}
	
	/**
	 * Sets a layer in the renderers List
	 * 
	 * @param layer The index of the layer it should be, if it doesnt exist already, its simply adds it to the list
	 * @param render The SpriteRenderer to be added
	 * @param enabled Sets if the layer should be enabled automatically
	 * */
	public void setLayer(int layer, Renderer render, boolean enabled) {
		if (renderers.size() < layer+1) {
			renderers.add(render);
			renderersEnabled.add(enabled);
		}
		
		renderersEnabled.set(layer, enabled);
		renderers.set(layer, render);
	}
	
	/**
	 * Set the state of the given layer
	 * @param layer   The index of the layer of which the state should be changed
	 * @param enabled Sets if the layer should be enabled
	 * */
	public void setLayerState(int layer, boolean enabled) {
		if (renderers.size()-1 < layer) {
			throw new IndexOutOfBoundsException();
		}
		
		renderersEnabled.set(layer, enabled);
	}
}
