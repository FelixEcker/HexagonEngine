package de.hexagonsoftware.engine.game.components;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.rmi.server.UID;

import de.hexagonsoftware.engine.HexagonEngine;
import de.hexagonsoftware.engine.game.GameObject;
import de.hexagonsoftware.engine.game.IGameObjectComponent;

//TODO: Fix Collisions.

public class Rigidbody implements IGameObjectComponent {
	/**
	 * The GameObject it was added to
	 * */
	private GameObject parentObj;
	/**
	 * The ID of the instance of the Rigidbody
	 * */
	private UID id;
	
	public Rigidbody(GameObject parentObj) {
		this.parentObj = parentObj;
		this.id 	   = new UID();
	}
	
	@Override
	public void start() {
	}

	@Override
	public void update() {
		for (GameObject obj : HexagonEngine.HE_GOBJ_MANAGER.getGOBJ_OBJS().values()) {
			if (obj.getComponents().containsKey("Rigidbody")) {
				Rigidbody rb = (Rigidbody) obj.getComponent("Rigidbody");
				
				//System.out.println(obj);
				//System.out.println("	"+rb+": "+rb.id);
				//System.out.println("	"+this+": "+this.id);
				
				if (rb.equals(this))
					continue;
				
				if (rb.getBoundingBox().intersects(new Rectangle(parentObj.getX(), parentObj.getY(), parentObj.getWidth(), parentObj.getHeight()))) {
					parentObj.setX(parentObj.getX()-1);
					parentObj.setY(parentObj.getY()-1);
					//System.out.println("Other: "+rb.getBoundingBox());
					//System.out.println("Self: "+this.getBoundingBox());
				}
			} else {
				continue;
			}
		}
	}

	@Override
	public void render(Graphics g) {}
	
	public Rectangle getBoundingBox() {
		return new Rectangle(parentObj.getX(), parentObj.getY(), parentObj.getWidth(), parentObj.getHeight());
	}
	
	@Override
	public boolean equals(Object otherObject) {
	    // check for reference equality.
		if (this == otherObject) {
		  return true;
		}
		if (otherObject instanceof Rigidbody) {
		  Rigidbody that = (Rigidbody) otherObject;
		  // Check for name equality.
		      return (id == null && that.id == null)
		          || id.equals(that.id);
		}
		    return false;
	}
}
