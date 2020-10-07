package de.hexagonsoftware.engine.game;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.HashMap;

/**
 * This class is used for every GameObject.
 * It contains a start, update and render function.
 * 
 * @author Felix Eckert
 * */
public class GameObject {
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	private HashMap<String, IGameObjectComponent> components;
	
	public GameObject() {
		this.components = new HashMap<>();
	}
	
	/**
	 * @param x The X Coordinate of the Object
	 * @param y The Y Coordinate of the Object
	 * */
	public GameObject(int x, int y) {
		this.components = new HashMap<>();
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Returns a IGameObjectComponent which is a member of this Game Object
	 * 
	 * @param name The name of the IGameObjectComponent
	 * @return an IGameObjectComponent associated with the given name.
	 * */
	public IGameObjectComponent getComponent(String name) {
		if (!this.components.containsKey(name))
			return null;
		
		return this.components.get(name);
	}
	
	public void start() {
		for (IGameObjectComponent comp : components.values()) {
			comp.start();
		}
	}
	
	public void start(boolean startGameObjectComponents) {
		if (startGameObjectComponents) {
			for (IGameObjectComponent comp : components.values()) {
				comp.start();
			}
		}
	}
	
	public void update() {
		for (IGameObjectComponent comp : components.values()) {
			comp.update();
		}
	}
	
	public void render(Graphics g) {
		for (IGameObjectComponent comp : components.values()) {
			comp.render(g);
		}
	}
	
	public void clicked(MouseEvent e) {}
	
	public void addComponent(String name, IGameObjectComponent comp) {
		this.components.put(name, comp);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public HashMap<String, IGameObjectComponent> getComponents() {
		return components;
	}
}
