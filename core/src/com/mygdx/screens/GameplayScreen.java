package com.mygdx.screens;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.entities.Player;
import com.mygdx.game.MyGdxGame;

public class GameplayScreen extends AbstractScreen {

    private Player player;
    private Button playerButton;
    private Label scoreLabel;  // uzywany w 2D

    public GameplayScreen(MyGdxGame game) {
        super(game);
    }

    protected void init() {
        initPlayer();
        initPlayerButton();
        initScoreLabel();
    }

    private void initScoreLabel() {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont();
        scoreLabel = new Label("Test123", labelStyle);
        scoreLabel.setX(20);
        scoreLabel.setY(650);
        stage.addActor(scoreLabel);
    }

    private void initPlayerButton() {
        playerButton = new Button(new Button.ButtonStyle()); // chodzi o to aby button był przezroczysty po to dodajemy
        // Button.ButtonStyle
        playerButton.setWidth(460);
        playerButton.setHeight(360);
        playerButton.setX(10);
        playerButton.setY(170);
        playerButton.setDebug(true);

        stage.addActor(playerButton); // dodawanie Buttona do stage
        playerButton.addListener(new ClickListener() {          // przechwytywanie akcji w Buttonie

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("click");
                player.reactOnClick();
                game.addPoint();

                return super.touchDown(event, x, y, pointer, button);
            }
        });
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
        stage.draw();
        spriteBatch.end();
    }

    private void update() {
        scoreLabel.setText("Score: " + game.getPoints());
        stage.act();

    }
}
