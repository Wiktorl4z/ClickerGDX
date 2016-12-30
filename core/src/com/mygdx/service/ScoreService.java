package com.mygdx.service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class ScoreService {

    public final String GAME_PREFS = "com.mygdx.game.prefs";
    public final String GAME_SCORE = "com.mygdx.game.score";
    private Preferences prefs; // saving our scores
    private int points;

    public ScoreService() {
        init();
    }

    private void init() {
        prefs = Gdx.app.getPreferences(GAME_PREFS);
        loadScores();
    }

    public void addPoints(int pointsToAdd) {
        points += pointsToAdd;
        updateSavedScoreInPrefs();
    }

    public void loadScores() {
        points = prefs.getInteger(GAME_SCORE);
    }

    public void addPoint() {
        points++;
        updateSavedScoreInPrefs();
    }

    private void updateSavedScoreInPrefs() {
        prefs.putInteger(GAME_SCORE, points); // co zapisujemy
        prefs.flush();
    }

    public int getPoints() {
        return points;
    }

    public void addPassiveIncome() {
        System.out.println("Passive income click");
    }

    public void resetGameScore() {
        points = 0;
        updateSavedScoreInPrefs();
    }
}
