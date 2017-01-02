package com.mygdx.controllers;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.MyGdxGame;

public class RandomEventController {

    private static final int RANDOM_TICK_INTERVAL = 5;
    private MyGdxGame game;

    public RandomEventController(MyGdxGame game) { // przekazujemy game
        this.game = game;
        init();
    }

    private void init() {


        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                if (MathUtils.randomBoolean()) {
                    triggerRandomEvent();
                }
            }
        }, RANDOM_TICK_INTERVAL, RANDOM_TICK_INTERVAL);
    }

    private void triggerRandomEvent() {
        int randomNumber = MathUtils.random(1, 3);
        switch (randomNumber) {
            case 1:
                moneyEvent();
                break;
            case 2:
                loseMoneyEvent();
                break;
            case 3:
                gamePassiveIncome();
                break;
            default:
                break;
        }
    }

    private void gamePassiveIncome() {
        game.getScoreService().addPassiveIncome();
    }

    private void loseMoneyEvent() {
        game.getScoreService().addPoints(-123);
    }

    private void moneyEvent() {
        game.getScoreService().addPoints(123);
    }
}
