package com.hexagonstudios.enginetest;

import com.hexagonstudios.hexagonengine.*;

public class Main {

	public static void main(String[] args) {
		HexagonEngine engine = new HexagonEngine("Test", "1.0", "Test", new int[] {1080, 540});
		engine.initEngine(new InitMethods(), false);
		engine.startEngine();
	}

}
