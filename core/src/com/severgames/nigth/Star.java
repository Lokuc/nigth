package com.severgames.nigth;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.Random;

public class Star {

    private Sprite sprite;
    private static int HEIGTH;
    private int step;
    private int maxStep;
    private static Random random;
    private int temp;
    private final int SPEED=500;
    private float sleepTime=0f;
    private float time=0f;

    Star(Random random,int H){
        HEIGTH=H;
        Star.random = random;
        sprite = new Sprite(new Texture("star.png"));
        sprite.setSize(HEIGTH/8,HEIGTH/8);
        sprite.setPosition(-sprite.getWidth(),HEIGTH+sprite.getHeight());
        step= MyGdxGame.W /8;
        maxStep=MyGdxGame.W/step;
        spawn();
    }

    private void spawn(){
        sleepTime=random.nextFloat()+random.nextInt(2);
        temp=random.nextInt(maxStep);
        sprite.setPosition(step*temp,HEIGTH+sprite.getHeight());

    }

    void draw(SpriteBatch batch,float delta){
        time+=delta;
        if(sleepTime<time) {
            sprite.setY(sprite.getY() - SPEED * delta);
            if (sprite.getY() <= -sprite.getHeight()) {
                spawn();
            }
            sprite.draw(batch);
        }
    }

}
