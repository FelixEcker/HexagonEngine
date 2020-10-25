package de.hexagonsoftware.engine.hud;

import java.awt.Graphics;
import java.util.HashMap;

public class HUDManager {
	private HashMap<String, HUD> huds;
	private String activeHUD;
	private boolean hudActive = false;
	
	public HUDManager() {
		this.huds = new HashMap<>();
		this.activeHUD = "";
	}
	
	public void addHUD(String name, HUD hud) {
		huds.put(name, hud);
	}
	
	public void setActiveHUD(String name) {
		activeHUD = name;
	}
	
	public void activateHUD () { 
		hudActive = true;
		huds.get(activeHUD).activate();
	}
	public void deactivateHUD () { 
		hudActive = false;
		huds.get(activeHUD).deactivate();
	}
	
	public void render(Graphics g) {
		if (hudActive) {
			huds.get(activeHUD).render(g);
		}
	}
	
	public void update() {
		if (hudActive) {
			huds.get(activeHUD).update();
		}
	}
}
