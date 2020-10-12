package de.hexagonsoftware.engine;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.hexagonsoftware.engine.game.Camera;
import de.hexagonsoftware.engine.game.GameObjectManager;
import de.hexagonsoftware.engine.game.IGame;
import de.hexagonsoftware.engine.graphics.HEWindow;
import de.hexagonsoftware.engine.graphics.Renderer;
import de.hexagonsoftware.engine.input.Keyboard;
import de.hexagonsoftware.engine.input.Mouse;
import de.hexagonsoftware.engine.resources.ResourceManager;

/**
 * Hexagon Engine by Hexagon Software
 * Copyright (c) 2020 Hexagon Software
 * 
 * Please read the README and the LICENSE file
 * before contributing/using.
 * 
 * @author Felix Eckert
 * */
public class HexagonEngine implements Runnable {
	/* Gameloop Vars */	
	/**
	 * Controls if the Gameloop is running
	 * */
	private static boolean HE_RUNNING;
	/**
	 * The Engines thread
	 * */
	private Thread HE_THREAD;
	/**
	 * The amount of ticks (updates) executed
	 * */
	private int HE_CURRENT_TICKS;
	/**
	 * The maximum amount of updates before render is called
	 * */
	private int HE_MAX_UPDATES;
	/**
	 * The current fps of the engine
	 * */
	private int HE_FPS;
	/**
	 * The fps the game is capped to/targets.
	 * */
	private int HE_FPS_CAP;
	/**
     * Amount of nanoseconds for one loop to reach the targeted fps.
     */
    private int HE_TARGET_TIME = 1000000000;
	/*************************************************/
	/* Window Variables */
	/**
	 * The Window.
	 * */
	private HEWindow HE_WINDOW;
	/**
	 * The name the window is titled
	 * */
	private String HE_WINDOW_TITLE;
	/**
	 * The width of the OpenGL window
	 * */
	private static int HE_WINDOW_WIDTH;
	/**
	 * The height of the OpenGL window
	 * */
	private static int HE_WINDOW_HEIGHT;
	/**
	 * Sets if the Window should be resizable
	 * */
	private boolean HE_WINDOW_RESIZABLE;
	/**
	 * KeyAdapter for Window
	 * */
	public static final Keyboard HE_KEY_INPUT = new Keyboard();
	/**
	 * MouseAdpater for Window
	 * */
	public static final Mouse HE_MOUSE_INPUT = new Mouse();
	/*************************************************/
	/* Game Variables */
	private static IGame HE_IGAME_IMP;
	/**
	 * Manages the Games Objects (Updates, init, render) ; should be created by the engine
	 *  */
	public static final GameObjectManager HE_GOBJ_MANAGER = GameObjectManager.getInstance();
	/**
	 * Manages the Games Resources ; should be created by the engine
	 * */
	public static final ResourceManager HE_RES_MANAGER = ResourceManager.getInstance();
	/**
	 * The camera.
	 * */
	public static Camera HE_CAMERA;
	/*************************************************/
	/* Other */
	/**
	 * The engines Logger. Should only be used by the engine.
	 * */
	public static Logger logger = LogManager.getLogger("HexagonEngine");
	/**
	 * Tells the engine if the initialise method was executed succesfully
	 * */
	public boolean initialised;
	
	/**
	 * @param fpsCap The maximum fps
	 * */
	public HexagonEngine(int fpsCap) {
		HexagonEngine.HE_RUNNING = false;
		this.HE_CURRENT_TICKS = 0;
		this.HE_FPS_CAP = fpsCap;
		this.HE_TARGET_TIME = HE_TARGET_TIME / HE_FPS_CAP;
		this.HE_FPS = 0;
	}
	
	/**
	 * Initialises the Engine. Must be executed before Game Loop start.
	 * 
	 * @param title  The title of the window
	 * @param width  The width of the window
	 * @param height The height of the window
	 * */
	public void initialise(String title, int width, int height, boolean resizable) {
		logger.info("Initialising engine...");
		this.HE_WINDOW_TITLE = title;
		HexagonEngine.HE_WINDOW_WIDTH = width;
		HexagonEngine.HE_WINDOW_HEIGHT = height;
		this.HE_WINDOW_RESIZABLE = resizable;
		
		logger.info("Creating the Window...");
		
		this.HE_WINDOW = new HEWindow(HE_WINDOW_TITLE, width, height);
		
		HE_WINDOW.setDefaultCloseOperation(HE_WINDOW.EXIT_ON_CLOSE);
		HE_WINDOW.setResizable(resizable);
		HE_WINDOW.setVisible(true);

		HE_WINDOW.getCVS().addMouseListener(HE_MOUSE_INPUT);
		HE_WINDOW.getCVS().addKeyListener(HE_KEY_INPUT);
		
		HE_WINDOW.getCVS().requestFocus();
		
		initialised = true;
		logger.info("Engine initialised succesfully!");
	}
	
	/**
	 * Starts the gameloop
	 * 
	 * @param game The implementation of the IGame interface (required)
	 * */
	public void start(IGame game) {
		HexagonEngine.HE_IGAME_IMP = game;
		
		logger.info("Starting Game Loop...");
		this.HE_THREAD = new Thread(this, "HexagonEngine");
		HE_THREAD.start();
	}

	/**
	 * This function contains the Main-Gameloop.
	 * */
	public void run() {
		HE_RUNNING = true;
		
		long oldTimestamp = System.nanoTime();
		double ticks = 60.0;
		double ns = 1000000000 / ticks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		
		int frames = 0;
		
		long now;

		HE_CAMERA = new Camera();
		HE_IGAME_IMP.start();
		HE_GOBJ_MANAGER.start();
		while(HE_RUNNING) {
			if (!HE_WINDOW.isVisible())
				HE_RUNNING = false;
			
			now = System.nanoTime();
			
			delta += (now - oldTimestamp) / ns;
			oldTimestamp = now;
			
			while(delta > 1 ) {	
				tick();
				this.HE_CURRENT_TICKS++;
				delta--;
			}
			
			render();
			
			frames++;
			
			if (System.currentTimeMillis() - timer > 1000) {
				this.HE_FPS = frames;
				timer += 1000;
				frames = 0;
			}
		}
	}
	
	/**
	 * This function is responsible for executing rendering tasks (either graphics.Renderer or other)
	 * */
	public void render() {
		if (HE_WINDOW == null)
			return;

		HE_CAMERA.update();
		Renderer.render(HE_WINDOW, HE_IGAME_IMP);
	}
	
	/**
	 * This function is responsible for updating everything in the game.
	 * */
	public void tick() {
		HE_GOBJ_MANAGER.update();
		HE_IGAME_IMP.update();
	}

	public static boolean isHE_RUNNING() {
		return HE_RUNNING;
	}

	public Thread getHE_THREAD() {
		return HE_THREAD;
	}

	public int getHE_CURRENT_TICKS() {
		return HE_CURRENT_TICKS;
	}

	public int getHE_MAX_UPDATES() {
		return HE_MAX_UPDATES;
	}

	public int getHE_FPS() {
		return HE_FPS;
	}

	public int getHE_FPS_CAP() {
		return HE_FPS_CAP;
	}

	public HEWindow getHE_WINDOW() {
		return HE_WINDOW;
	}

	public String getHE_WINDOW_TITLE() {
		return HE_WINDOW_TITLE;
	}

	public int getHE_WINDOW_WIDTH() {
		return HE_WINDOW_WIDTH;
	}

	public int getHE_WINDOW_HEIGHT() {
		return HE_WINDOW_HEIGHT;
	}

	public static int getWidth() {
		return HE_WINDOW_WIDTH;
	}

	public static int getHeight() {
		return HE_WINDOW_HEIGHT;
	}
	
	public boolean isHE_WINDOW_RESIZABLE() {
		return HE_WINDOW_RESIZABLE;
	}

	public IGame getHE_IGAME_IMP() {
		return HE_IGAME_IMP;
	}

	public static Logger getLogger() {
		return logger;
	}

	public boolean isInitialised() {
		return initialised;
	}

	public static IGame getGame() {
		return HE_IGAME_IMP;
	}
	
	public static Camera getCamera() {
		return HE_CAMERA;
	}
}