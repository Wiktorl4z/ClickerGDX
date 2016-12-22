package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.screens.SplashScreen;

public class MyGdxGame extends Game {

	public final static String GAME_NAME = "Clicker";
	public final static int WIDTH = 480;
	public final static int HEIGHT = 700;
	private boolean paused;

	private int points;



	public void addPoint(){
		points++;
	}

	@Override
	public void create () {
	this.setScreen(new SplashScreen(this));
	}

	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}

	public int getPoints() {
		return points;
	}
}
