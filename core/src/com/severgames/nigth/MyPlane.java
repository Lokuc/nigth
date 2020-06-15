package com.severgames.nigth;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class MyPlane implements Subject{

    protected Sprite sprite;

    int x,y;

    private int X=0,Y=0;
    private Bullet bullet;
    private float reloadTime;
    private final int speed=20;
    private boolean active;


    MyPlane(){
        sprite = new Sprite(new Texture("plane.png"));
        sprite.setSize(MyGdxGame.H/5,MyGdxGame.H/5);
        bullet = new Bullet();
        active=true;
        sprite.setX(X);
        sprite.setY(Y);
    }



    @Override
    public void destroy(){
        active=false;
    }

    @Override
    public boolean getActive() {
        return active;
    }

    @Override
    public Rectangle getRectandle(){
        return sprite.getBoundingRectangle();
    }





    void draw(SpriteBatch batch,float delta){
        if(active) {
            reloadTime += delta;
            sprite.draw(batch);
            bullet.draw(batch, delta);
        }
    }

    Bullet getBullet(){
        return bullet;
    }


    void shot(){
        if(reloadTime>=2) {
            bullet.spawn(true, (int) (sprite.getX() + sprite.getWidth() / 2), (int) (sprite.getY() + sprite.getWidth()));
            reloadTime=0;
        }
    }


    public int getX() {
        return X;
    }

    public void setX(int xa) {
        if(Math.abs(X-xa)<=speed){
            X=xa;
        }else{
            X+=X-xa>=0?-speed:speed;
        }
        if(X<=0){
            X=0;
        }else if(X>MyGdxGame.W-sprite.getHeight()) {
            X = (int) (MyGdxGame.W - sprite.getHeight());
        }
        sprite.setX(X);
    }

    public int getY() {
        return Y;
    }

    public void setY(int ya) {
        if(Math.abs(Y-ya)<=speed){
            Y=ya;
        }else{
            Y+=Y-ya>=0?-speed:speed;
        }
        if(Y<=0){
            Y=0;
        }else if(Y>MyGdxGame.H-sprite.getHeight()) {
            Y = (int) (MyGdxGame.H-sprite.getHeight());
        }
        sprite.setY(Y);
    }


}
