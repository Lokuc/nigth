package com.severgames.nigth;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Enemy extends Subject{

    protected Sprite sprite;

    int x,y;

    private int X=0;
    private Bullet bullet;
    private float reloadTime;
    private boolean active = false;
    private final int speed = 5;


    Enemy(){
        sprite = new Sprite(new Texture("enemy.png"));
        sprite.setSize(MyGdxGame.H/5,MyGdxGame.H/5);
        bullet = new Bullet();
        sprite.setX(X);
        sprite.setY(MyGdxGame.H-sprite.getHeight());
    }

    public Rectangle getRectandle(){
        return sprite.getBoundingRectangle();
    }

    void spawn(){
        active= true;
        sprite.setX(-sprite.getWidth()*2);
    }

    Bullet getBullet(){
        return bullet;
    }

    public void destroy(){
        active=false;
        sprite.setPosition(-sprite.getWidth(),-sprite.getHeight());
    }




    void draw(SpriteBatch batch, float delta){
        if(active) {
            reloadTime += delta;
            sprite.draw(batch);
            bullet.draw(batch, delta);
        }
    }

    private void shot(){
        if(reloadTime>=2) {
            bullet.spawn(false, (int) (sprite.getX() + sprite.getWidth() / 2), (int) (sprite.getY()));
            reloadTime=0;
        }
    }


    public int getX() {
        return X;
    }

    public void setX(int xa) {
        if(Math.abs(X-xa)<=sprite.getWidth()/2+30){
            shot();
        }
        if(Math.abs(X-xa)<=speed){
            X=xa;
        }else{
            X+=X-xa>=0?-speed:speed;
        }
        sprite.setX(X);
    }


}
