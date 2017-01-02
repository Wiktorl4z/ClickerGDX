package com.mygdx.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.controllers.FlyingObjectController;
import com.mygdx.controllers.RandomEventController;
import com.mygdx.entities.Player;
import com.mygdx.game.MyGdxGame;
import com.mygdx.service.PassiveIncomeService;
import com.mygdx.ui.*;

public class GameplayScreen extends AbstractScreen {

    private Image bgImage;
    private Player player;
    private PlayerButton playerButton;
    private ResetScoreButton resetScoreButton;
    private GameLabel gameLabel;// uzywany w 2D
    private FlyingObjectController flyingObjectController;
    private PassiveIncomeService passiveIncomeService;
    private RandomEventController randomEventController;

    public GameplayScreen(MyGdxGame game) {
        super(game);
    }

    protected void init() {
        bgImage = new Image(new Texture("bg.png"));
        stage.addActor(bgImage);
        bgInit();
        initPlayer();
        initPlayerButton();
        initScoreLabel();
        initResetScoreButton();
        initFlyingStuffObjects();
        startTheMusic();
        initPassiveIncomeService();
        initPassiveIncomeInfoDialog();
        initRandomEventController();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update();

        System.out.printf("Points: " + game.getScoreService().getPoints());

        spriteBatch.begin();
        stage.draw();
        spriteBatch.end();
    }

    @Override
    public void pause() {
        super.pause();
        game.getScoreService().saveCurrentTimestamp();
        game.getScoreService().saveCurrentGamestate();
    }
    private void initRandomEventController() {
        randomEventController = new RandomEventController(game); // przekazanie konstruktora
    }

    private void initPassiveIncomeInfoDialog() {
        if (passiveIncomeService.getPointsToAdd() > 0) {
            BasicDialog basicDialog = new BasicDialog();
            stage.addActor(basicDialog);
            basicDialog.initContent("Passive income gained: " + passiveIncomeService.getPointsToAdd());
        }
    }

    private void update() {
        gameLabel.setText("Score: " + game.getScoreService().getPoints());
        stage.act();
    }

    private void initPassiveIncomeService() {
        passiveIncomeService = new PassiveIncomeService(game.getScoreService());
        passiveIncomeService.start();
    }

    private void startTheMusic() {
        game.getSoundService().startPlayingMusic(true);
    }

    private void initFlyingStuffObjects() {
        flyingObjectController = new FlyingObjectController(game, stage);
    }

    private void bgInit() {
        bgImage = new Image(new Texture("bg.png"));
        stage.addActor(bgImage);
    }

    private void initResetScoreButton() {
        resetScoreButton = new ResetScoreButton(new IClickCallBack() {
            @Override
            public void onClick() {
                game.getScoreService().resetGameScore();
            }
        });
        stage.addActor(resetScoreButton); // dodajemy button do sceny
    }

    private void initScoreLabel() {
        gameLabel = new GameLabel();
        stage.addActor(gameLabel);
    }

    private void initPlayerButton() {
        playerButton = new PlayerButton(new IClickCallBack() {
            @Override
            public void onClick() {
                player.reactOnClick();
                game.getScoreService().addPoint();
                game.getSoundService().playJumpSound();
            }
        });
        stage.addActor(playerButton); // dodawanie Buttona do stage

    }

    private void initPlayer() {
        player = new Player();
        stage.addActor(player);
    }

}