package com.mygdx.controllers;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.MyGdxGame;
import com.mygdx.ui.BasicDialog;

public class RandomEventController {

    private static final int RANDOM_TICK_INTERVAL = 5;
    private MyGdxGame game;
    private Stage stage;

    public RandomEventController(MyGdxGame game, Stage stage) { // przekazujemy game
        this.game = game;
        this.stage = stage;
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
                gainMoneyEvent();
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

    private void triggedDialog(String text) {
        BasicDialog basicDialog = new BasicDialog();
        basicDialog.showDialog(stage, text);
    }

    private void gamePassiveIncome() {
        game.getScoreService().addPassiveIncome();
        triggedDialog("Yaaay! You gained passive income!");
    }

    private void loseMoneyEvent() {
        game.getScoreService().addPoints(-123);
        triggedDialog("Pay taxes! You owl!");
    }

    private void gainMoneyEvent() {
        game.getScoreService().addPoints(123);
        triggedDialog("Free money! Yeah!");
    }
}
