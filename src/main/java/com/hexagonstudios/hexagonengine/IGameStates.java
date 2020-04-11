package com.hexagonstudios.hexagonengine;

import java.util.List;

public interface IGameStates {
	void addGameState(IGameState state);
	void removeGameState(int id);
	IGameState getGameState(int id);
}