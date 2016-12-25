package com.mygdx.screens;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.entities.Player;
import com.mygdx.game.MyGdxGame;
import com.mygdx.ui.IClickCallBack;
import com.mygdx.ui.PlayerButton;
import com.mygdx.ui.ResetScoreButton;
import com.mygdx.ui.ScoreLabel;

public class GameplayScreen extends AbstractScreen {

    private Texture bgTexture;
    private Player player;
    private PlayerButton playerButton;
    private ResetScoreButton resetScoreButton;
    private ScoreLabel scoreLabel;  // uzywany w 2D

    public GameplayScreen(MyGdxGame game) {
        super(game);
    }

    protected void init() {
        bgTexture = new Texture("bg.png");
        initPlayer();
        initPlayerButton();
        initScoreLabel();
        initResetScoreButton();
    }

    private void initResetScoreButton() {
        resetScoreButton = new ResetScoreButton(new IClickCallBack() {
            @Override
            public void onClick() {
                game.resetGameScore();
            }
        });
        stage.addActor(resetScoreButton); // dodajemy button do sceny
    }

    private void initScoreLabel() {
        scoreLabel = new ScoreLabel();
        stage.addActor(scoreLabel);
    }

    private void initPlayerButton() {
        playerButton = new PlayerButton(new IClickCallBack() {
            @Override
            public void onClick() {
                player.reactOnClick();
                game.addPoint();
            }
        });
        stage.addActor(playerButton); // dodawanie Buttona do stage

    }

    private void initPlayer() {
        player = new Player();
        stage.addActor(player);

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update();

        System.out.printf("Points: " + game.getPoints());

        spriteBatch.begin();
        spriteBatch.draw(bgTexture, 0, 0);
        spriteBatch.end();

        spriteBatch.begin();
        stage.draw();
        spriteBatch.end();


    }

    private void update() {
        scoreLabel.setText("Score: " + game.getPoints());
        stage.act();

    }
}