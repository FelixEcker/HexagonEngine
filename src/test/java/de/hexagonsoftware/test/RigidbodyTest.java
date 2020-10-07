package de.hexagonsoftware.test;

import java.awt.Color;
import java.awt.Graphics;

import de.hexagonsoftware.engine.game.GameObject;
import de.hexagonsoftware.engine.game.components.Rigidbody;

public class RigidbodyTest extends GameObject {
	@Override
	public void start() {
		super.start();
		super.setWidth(10);
		super.setHeight(10);
		super.addComponent("Rigidbody", new Rigidbody(this));
	}

	@Override
	public void update() {
		super.update();
		super.setX(500);
		super.setY(600);
	}

	@Override
	public void render(Graphics g) {
		super.render(g);
		g.setColor(Color.blue);
		g.fillRect(super.getX(), super.getY(), super.getWidth(), super.getHeight());
	}
}
