package de.hexagonsoftware.engine.hud.widgets;

import java.awt.Graphics;
import java.awt.Point;

import de.hexagonsoftware.engine.HexagonEngine;

public class ButtonBox extends HUDWidget {	
	public static int 
	TOP_LEFT      = 0, 
	TOP_RIGHT     = 1, 
	BOTTOM_LEFT   = 2,
	BOTTOM_RIGHT  = 3, 
	TOP_CENTER 	  = 4, 
	BOTTOM_CENTER = 5, 
	CENTER 		  = 6
	;
	
	private int alignment;
	private ImageButton[] buttons;
	
	private boolean guiInitialised = false;
	private int ticks = 0;
	private int ticksToUpdate = 60;
	
	public ButtonBox(int x, int y) {
		super(x, y);
	}
	
	public ButtonBox(int x, int y, int alignment) {
		super(x, y);
		this.alignment = alignment;
	}
	
	public ButtonBox(int x, int y, int alignment, ImageButton[] buttons) {
		super(x, y);
		this.alignment = alignment;
		this.buttons = buttons;
		calculatePositions();
		this.guiInitialised = true;
	}

	public Point getButtonPosition(ImageButton button) {
		Point location = null;
		int buttonWidth = button.getWidth();
		int buttonHeight = button.getHeight();
		
		switch (alignment) {
		case 0:
			location = new Point(0, buttonHeight);
			break;
		case 1:
			location = new Point(HexagonEngine.getWidth()-buttonWidth, buttonHeight);
			break;
		case 2:
			location = new Point(0, HexagonEngine.getHeight()-buttonHeight);
			break;
		case 3:
			location = new Point(HexagonEngine.getWidth()-buttonWidth, HexagonEngine.getHeight()-buttonHeight);
			break;
		case 4:
			location = new Point((int) ((HexagonEngine.getWidth()/2)-buttonWidth/2), buttonHeight);
			break;
		case 5:
			location = new Point((int) ((HexagonEngine.getWidth()/2)-buttonWidth/2), HexagonEngine.getHeight()-buttonHeight);
			break;
		case 6:
			location = new Point((int) ((HexagonEngine.getWidth()/2)-buttonWidth/2), (HexagonEngine.getHeight()/2)-(buttonHeight/2));
			break;
		}
		
		return location;
	}
	
	public void calculatePositions() {
		if (!guiInitialised)
			guiInitialised = true;
		
		if (buttons.length < 0)
			return;

		this.height = 0;
		for (ImageButton button : buttons) {
			this.height = height + button.height;
		}
		
		int buttonHeightAverage = this.height/buttons.length;
		int prevButtonHeight = 0;
		for (int i = 0; i < buttons.length; i++) {
			prevButtonHeight = i != 0 ? buttons[i-1].getHeight() + prevButtonHeight : 0;
			Point pos = getButtonPosition(buttons[i]);
			pos.setLocation(pos.getX(), pos.getY()-prevButtonHeight);
			buttons[i].setX((int) pos.getX());
			buttons[i].setY((int) pos.getY()+buttonHeightAverage);
		}
	}

	@Override
	public void update() {
		ticks++;
		
		for (ImageButton button : buttons) {
			if (guiInitialised)
				button.update();
		}
		
		if (ticks % ticksToUpdate == 0) {
			calculatePositions();
		}
	}
	
	@Override
	public void render(Graphics g) {
		for (ImageButton button : buttons) {
			button.render(g);
		}
	}
	
	public int getAlignment() {
		return alignment;
	}

	public ImageButton[] getButtons() {
		return buttons;
	}

	public void setAlignment(int alignment) {
		this.alignment = alignment;
	}

	public void setButtons(ImageButton[] buttons) {
		this.buttons = buttons;
		calculatePositions();
	}

	public int getTicksToUpdate() {
		return ticksToUpdate;
	}

	public void setTicksToUpdate(int ticksToUpdate) {
		this.ticksToUpdate = ticksToUpdate;
	}
}
