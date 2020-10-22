package de.hexagonsoftware.engine.hud;

import java.awt.Graphics;
import java.util.HashMap;

public class HUDManager {
	private HashMap<String, HUD> huds;
	private String activeHUD;
	private boolean hudActive = false;
	
	public HUDManager() {
		huds = new HashMap<>();
		activeHUD = "";
	}
	
	public void addHUD(String name, HUD hud) {
		huds.put(name, hud);
	}
	
	public void setActiveHUD(String name) {
		activeHUD = name;
	}
	
	public void activateHUD () { hudActive = true; }
	public void deactivateHUD () { hudActive = false; }
	
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
