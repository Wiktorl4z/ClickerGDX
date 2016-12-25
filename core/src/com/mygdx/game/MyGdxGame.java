package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.mygdx.screens.SplashScreen;

public class MyGdxGame extends Game {

    public final String GAME_PREFS = "com.mygdx.game.prefs";
    public final String GAME_SCORE = "com.mygdx.game.score";
    public final static String GAME_NAME = "Clicker";
    public final static int WIDTH = 480;
    public final static int HEIGHT = 700;
    private boolean paused;
    private Preferences prefs;

    private int points;

    @Override
    public void create() {
        init();
        this.setScreen(new SplashScreen(this));
    }

    public void init() { // iniciowanie preferencji
        prefs = Gdx.app.getPreferences(GAME_PREFS);
        loadScores();
    }

    public void addPoint() {
        points++;
        updateSavedScoreInPrefs();
    }

    private void updateSavedScoreInPrefs() {
        prefs.putInteger(GAME_SCORE, points); // co zapisujemy
        prefs.flush();
    }

    public void loadScores() {
        points = prefs.getInteger(GAME_SCORE);
    }

    public void resetGameScore() {
        points = 0;
        updateSavedScoreInPrefs();
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