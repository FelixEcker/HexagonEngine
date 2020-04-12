package com.hexagonstudios.hexagonengine.sound;

import com.hexagonstudios.hexagonengine.HexagonEngine;
import com.hexagonstudios.hexagonengine.IGameObject;

public interface ISoundObject extends IGameObject {
	void init(HexagonSoundEngine soundEngine);
	void activate();
	void update(HexagonEngine engine);
}
