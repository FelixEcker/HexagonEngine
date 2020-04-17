/* *********************************************************************
 * Hexagon Engine Version 0.1.0 Early Alpha Stage.
 * Created by Felix Eckert.
 * 
 * Hexagon Engine is a free-to-use 2D Game Engine build in and for Java,
 * in use for Software relying on 2D Graphics.
 * 
 * *********************************************************************
 * 
 * Copyright (c) 2020 Felix Eckert
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 * 
 * 
 * ********************************************************************/

package com.hexagonstudios.hexagonengine;

import java.util.Properties;

import com.hexagonstudios.hexagonengine.graphics.HexagonGraphicsEngine;
import com.hexagonstudios.hexagonengine.input.KeyInput;
import com.hexagonstudios.hexagonengine.physics.HexagonPhysicsEngine;
import com.hexagonstudios.hexagonengine.sound.HexagonSoundEngine;

public class HexagonEngine implements Runnable {
	// Client Variables
	private String clientName;
	private String clientVersion;
	private String clientGameWindowTitle;
	private int[] resolution;
	private int FPS;
	
	// Engine Variables
	private String hexagonVersion = "0.1.0 EARLY ALPHA";
	private Properties engineOptions;
	private Properties engineErrorCodes;
	private HexagonGraphicsEngine graphicsEngine;
	private HexagonSoundEngine soundEngine;
	private HexagonPhysicsEngine physicsEngine;
	private ObjectHandler objectHandler;
	private KeyInput keyInput;
	private IGameHandler gameHandler;
	private Thread thread;
	boolean running;
	
	public HexagonEngine(String clientName, String clientVersion, String clientGameWindowTitle, int[] resolution) {
		this.clientName = clientName;
		this.clientVersion = clientVersion;
		this.clientGameWindowTitle = clientGameWindowTitle;
		this.resolution = resolution;
	}

	/**
	 * Initialises up HexagonEngine and launches the initialization process.
	 * After init-process is completed, it starts the game-loop.
	 * 
	 * @param cltInitMethods Implementation of the IInitMethod interface, which contains the client's init functions
	 * @param autoRun Sets if the game-loop should be entered automatically.
	 * @author Felix Eckert
	 * */
	public void initEngine(IInitMethods cltInitMethods, boolean autoRun) {
		preInit();
		cltInitMethods.preInit(this);
		
		init();
		cltInitMethods.init(this);
		
		postInit();
		cltInitMethods.postInit(this);
		
		if (autoRun)
			startEngine();
	}
	
	/**
	 * Starts the Engine thread
	 * 
	 * @author Felix Eckert
	 * */
	public void startEngine() {
		thread = new Thread(this);
		thread.start();
	}
	
	/**
	 * Pre-Initialization function.
	 * Loads Engine Options and Error Code file
	 * 
	 * @author Felix Eckert
	 * */
	public void preInit() {
		System.out.println("HexagonEngine is starting...");
		System.out.println("-----INFORMATION-----");
		System.out.println("	[HEX-VERSION] "+hexagonVersion);
		System.out.println("	[CLIENT-NAME] "+clientName);
		System.out.println("	[CLIENT-VERSION] "+clientVersion);
		System.out.println("	[RESOLUTION] "+resolution[0]+"x"+resolution[1]);
		System.out.println("	[MAX-FPS] "+FPS);
		System.out.println("---------------------");
		
		// Load Engine Options
		engineOptions = new Properties();
		try {
			engineOptions.load(ClassLoader.getSystemClassLoader().getResourceAsStream("EngineOptions.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Load Engine Error Codes
		engineErrorCodes = new Properties();
		try {
			engineErrorCodes.load(ClassLoader.getSystemClassLoader().getResourceAsStream("ErrorCodes.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Initialization function.
	 * Initializes all Engine Components. (Graphics, UI, Sound)
	 * 
	 * @author Felix Eckert
	 * */
	public void init() {
		// Create Graphics Engine
		graphicsEngine = new HexagonGraphicsEngine(resolution, clientGameWindowTitle, this);
		// Create UI Engine
		// Create Sound Engine
		soundEngine = new HexagonSoundEngine();
		// Create ObjectHandler
		objectHandler = new ObjectHandler();
		// Create Physics Engine
		physicsEngine = new HexagonPhysicsEngine(this);
		// Add Key Listener
		keyInput = new KeyInput();
		graphicsEngine.getGameWindow().getFrame().addKeyListener(keyInput);
	}
	
	/**
	 * Post-Initialization function.
	 * Initializes the Client's Game Handler and Parses his Animations.
	 * */
	public void postInit() {
		// Load Client Game Class into game loop
		getGameHandler().init(this);
		// Parse Animation Files
	}
	
	/**
	 * Contains Gameloop.
	 * 
	 * @author Felix Eckert
	 * */
	@Override
	public void run() {
		long oldTimestamp = System.nanoTime();
		double ticks = 60.0;
		double ns = 1000000000 / ticks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		long now;
		
		running = true;
		
		while(running) {	
			now = System.nanoTime();
			delta += (now - oldTimestamp) / ns;
			oldTimestamp = now;
			
			while(delta > 1 ) {	
				this.gameHandler.update();
				delta--;	
			}
			
			if (running)
				graphicsEngine.getRenderer().render();
			
			frames++;
			
			if (System.currentTimeMillis() - timer > 1000) {	
				timer += 1000;	
				frames = 0;	
			}
			
			try {
				thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		stop();
	}
	
	public void stop() {
		if (!running)
			return;
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		running = false;
	}
	
	/**
	 * Used by Client to add implementation of IGameHandler
	 * 
	 * @param gameHandler Client's Game Handler
	 * @author Felix Eckert
	 * */
	public void addGameHandler(IGameHandler gameHandler) {
		this.gameHandler = gameHandler;
	}
	
	/**
	 * Returns Client's Game Handler
	 * 
	 * @author Felix Eckert
	 * */
	public IGameHandler getGameHandler() { return this.gameHandler; }
	
	/**
	 * Returns the Object Handler of the engine.
	 * 
	 * @author Felix Eckert
	 * */
	public ObjectHandler getObjectHandler() { return this.objectHandler; }
	
	/**
	 * Returns the Engine's Sound Engine
	 * 
	 * @author Felix Eckert
	 * */
	public HexagonSoundEngine getSoundEngine() { return this.soundEngine; }
	
	/**
	 * Returns the Engine's key Listener
	 * 
	 * @author Felix Eckert
	 * */
	public KeyInput getKeyInput() { return this.keyInput; }
	
	/**
	 * Returns the Engine's Phyiscs Engine
	 * 
	 * @author Felix Eckert
	 * */
	public HexagonPhysicsEngine getPhysicsEngine() { return this.physicsEngine; }
}
