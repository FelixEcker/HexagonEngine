package com.hexagonstudios.enginetest;

import java.util.ArrayList;
import java.util.List;

import com.hexagonstudios.hexagonengine.IGameState;
import com.hexagonstudios.hexagonengine.IGameStates;

public class GameStates implements IGameStates {
	public List<IGameState> states = new ArrayList<IGameState>();
	
	@Override
	public void addGameState(IGameState state) {
		states.add(state);
	}
	
	@Override
	public void removeGameState(int id) {
		states.remove(id);
	}

	@Override
	public IGameState getGameState(int id) {
		System.out.println(id + "<--[STATE ID] [STATE NAME]--> "+states.get(id));
		return states.get(id);
	}
}
