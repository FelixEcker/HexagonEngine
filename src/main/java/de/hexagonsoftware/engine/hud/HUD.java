package de.hexagonsoftware.engine.hud;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

import de.hexagonsoftware.engine.hud.widgets.HUDWidget;

public class HUD {
	protected List<HUDWidget> widgets;
	
	public HUD() {
		widgets = new LinkedList<>();
	}
	
	public void render(Graphics g) {
		widgets.forEach(widget -> widget.render(g));
	}
	
	public void update() {
		widgets.forEach(widget -> widget.update());
	}
	
	public void addWidget(HUDWidget widget) {
		widgets.add(widget);
	}
}
