package com.severgames.nigth;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Enemy implements Subject{

    protected Sprite sprite;

    int x,y;

    private int X=0;
    private Bullet bullet;
    private float reloadTime;
    private boolean active;
    private final int speed = 5;
    private int num=0;
    private boolean temp=false;
    private int tempi;


    Enemy(int num){
        active = false;
        sprite = new Sprite(new Texture("enemy.png"));
        sprite.setSize(MyGdxGame.H/5,MyGdxGame.H/5);
        bullet = new Bullet();
        this.num=num;
        sprite.setX(X);
        sprite.setY(MyGdxGame.H-sprite.getHeight());
    }

    void spawn(){
        active= true;
        sprite.setX(-sprite.getWidth()*2);
    }

    Bullet getBullet(){
        return bullet;
    }



    @Override
    public void destroy(){
        active=false;
        sprite.setPosition(-sprite.getWidth(),-sprite.getHeight());
    }

    @Override
    public boolean getActive() {
        return active;
    }

    @Override
    public Rectangle getRectandle(){
        return sprite.getBoundingRectangle();
    }

    int getNum(){
        return num;
    }



    void draw(SpriteBatch batch, float delta){
        if(active) {
            move();
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

    private void move(){
        for(Enemy value : Menu.frame.enemy) {

        }
        sprite.setX(X);
    }


    public int getX() {
        return X;
    }

    void setXP(int x){
        sprite.setX(x);
    }

    void setX(int xa) {
        if(active) {
            if (Math.abs(X - xa) <= sprite.getWidth() / 2 + 30) {
                shot();
            }
            if (Math.abs(X - xa) <= speed) {
                X = xa;
            } else {
                X += X - xa > 0 ? -speed : speed;
            }
        }
    }


}
