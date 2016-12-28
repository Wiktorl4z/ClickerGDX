package com.mygdx.service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundService {

    private Sound moneySound;
    private Music music;
    private Sound jump;
    private Sound pick;
    private Sound book;

    public SoundService() {
        init();
    }

    private void init() {
        moneySound = Gdx.audio.newSound(Gdx.files.internal("sounds/money1.mp3"));
        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/music.mp3"));
        jump = Gdx.audio.newSound(Gdx.files.internal("sounds/jump.wav"));
        pick = Gdx.audio.newSound(Gdx.files.internal("sounds/pick.mp3"));
        book = Gdx.audio.newSound(Gdx.files.internal("sounds/book.wav"));

    }

    public void playMoneySound() {
        moneySound.play();
    }
    public void playPickSound() {
        pick.play();
    }
    public void playJumpSound() {
        jump.play();
    }
    public void playBookSound() {
        book.play();
    }

    public void startPlayingMusic(boolean looped) {
        music.setVolume(0.1f);
        music.play();
        music.setLooping(looped);
    }
}
