package com.mygdx.ui;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class ResetScoreButton extends Button {

    public ResetScoreButton(final IClickCallBack callback) {
        super(prepareResetButtonStyle());
        init(callback);

    }

    private void init(IClickCallBack callback) {
        this.setWidth(100);
        this.setHeight(100);
        this.setX(330);
        this.setY(560);

        this.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                callback.onClick();
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }

    private static Button.ButtonStyle prepareResetButtonStyle() { // ladowanie atlasu
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("ui-red.atlas"));// laduje plik z assetow
        Skin skin = new Skin(atlas); // z asetow tworzy skina
        Button.ButtonStyle buttonStyle = new Button.ButtonStyle(); // z tego skina bierzemy
        buttonStyle.up = skin.getDrawable("button_01"); // przyciski
        buttonStyle.down = skin.getDrawable("button_02");
        return buttonStyle;
    }
}
