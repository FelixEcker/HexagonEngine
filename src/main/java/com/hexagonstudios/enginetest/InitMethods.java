package com.hexagonstudios.enginetest;

import com.hexagonstudios.hexagonengine.HexagonEngine;
import com.hexagonstudios.hexagonengine.IGameObject;
import com.hexagonstudios.hexagonengine.IInitMethods;

public class InitMethods implements IInitMethods {

	@Override
	public void preInit(HexagonEngine engine) {
		System.out.println("preInit");
	}

	@Override
	public void init(HexagonEngine engine) {
		System.out.println("init");
		engine.addGameHandler(new GameHandler());
	}

	@Override
	public void postInit(HexagonEngine engine) {
		System.out.println("postInit");
	}

}
