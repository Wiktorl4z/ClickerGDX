package com.mygdx.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class FlyingObject extends Image {

    public final static String MONEY = "money.png";
    private final static int WIDHT = 150;
    private final static int HEIGHT = 150;

    private final static int STARTING_X = 0;
    private final static int STARTING_Y = -100; // outside the window

    public FlyingObject() {

        super(new Texture(MONEY));

        this.setOrigin(WIDHT / 2, HEIGHT / 2);
        this.setSize(WIDHT, HEIGHT);

        // starting position
        this.setPosition(STARTING_X, STARTING_Y);

        this.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("touched");
                FlyingObject.this.remove();
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }

    public void fly(){
        Action a = Actions.parallel(
                Actions.moveBy(300,200,5),
                Actions.rotateBy(360,5)
        );
        Action b = Actions.parallel(
                Actions.moveBy(-500,900,3),
                Actions.rotateBy(360,5)
        );
        Action c = Actions.run(new Runnable() {
            @Override
            public void run() {
                FlyingObject.this.remove(); // removing object, when action done
            }
        });

        this.addAction(Actions.sequence(a, b, c));
    }
}