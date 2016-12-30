package com.mygdx.service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.concurrent.TimeUnit;

public class ScoreService {

    public final String GAME_PREFS = "com.mygdx.game.prefs";
    public final String GAME_SCORE = "com.mygdx.game.score";
    public final String GAME_PASSIVE_INCOME = "com.mygdx.game.passiveincome";
    public final String GAME_SAVED_TIMESTAMP = "com.mygdx.game.savedtumestamp";
    private Preferences prefs; // saving our scores
    private int points;
    private int passiveIncome;

    public ScoreService() {
        init();
    }


    private void init() {
        prefs = Gdx.app.getPreferences(GAME_PREFS);
        loadScores();
        loadPassiveIncome();
        calculateGainedPassiveIncome();
    }

    private void calculateGainedPassiveIncome() {
        long savedTimestamp = getSavedTimestamp();
        if (savedTimestamp > 0) {
            long millisPassed = TimeUtils.timeSinceMillis(savedTimestamp);
            long seconds = TimeUnit.MILLISECONDS.toSeconds(millisPassed);
            System.out.println("Passed seconds:" + seconds);
        } else {
            // do nothing
        }
    }

    public void addPoints(int pointsToAdd) {
        points += pointsToAdd;
        updateSavedScoreAndPassiveIncomeInPrefs();
    }

    public void loadScores() {
        points = prefs.getInteger(GAME_SCORE);
    }

    private void loadPassiveIncome() { // loading passive points
        passiveIncome = prefs.getInteger(GAME_PASSIVE_INCOME);

    }

    public void addPoint() {
        points++;
        updateSavedScoreAndPassiveIncomeInPrefs();
    }

    private void updateSavedScoreAndPassiveIncomeInPrefs() {
        prefs.putInteger(GAME_SCORE, points); // saving
        prefs.putInteger(GAME_PASSIVE_INCOME, passiveIncome);
        prefs.flush();
    }

    public int getPoints() {
        return points;
    }

    public int getPassiveIncome() {
        return passiveIncome;
    }

    public void addPassiveIncome() {
        passiveIncome++;
        updateSavedScoreAndPassiveIncomeInPrefs();
    }

    public void resetGameScore() {
        points = 0;
        passiveIncome = 0;
        updateSavedScoreAndPassiveIncomeInPrefs();
    }

    public long getSavedTimestamp() {
        return prefs.getLong(GAME_SAVED_TIMESTAMP);

    }

    public void saveCurrentTimestamp() {
        prefs.putLong(GAME_SAVED_TIMESTAMP, TimeUtils.millis());
        prefs.flush(); // flush is saving
    }
}
