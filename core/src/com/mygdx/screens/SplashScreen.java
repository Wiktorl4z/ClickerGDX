package com.mygdx.screens;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.MyGdxGame;

public class SplashScreen extends AbstractScreen {

    private Texture splashImg;

    public SplashScreen(MyGdxGame game) {
        super(game);
        init();
    }

    private void init() {
        // TODO implement better assests loading when game grows
        splashImg = new Texture("badlogic.jpg");
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        spriteBatch.begin();
        spriteBatch.draw(splashImg, 0, 0);
        spriteBatch.end();
    }
}
