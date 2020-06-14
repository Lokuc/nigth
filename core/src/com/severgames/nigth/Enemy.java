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
    private int num=0;
    private boolean temp=false;
    private int tempi;


    Enemy(int num){
        sprite = new Sprite(new Texture("enemy.png"));
        sprite.setSize(MyGdxGame.H/5,MyGdxGame.H/5);
        bullet = new Bullet();
        this.num=num;
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

    void setX(int xa) {
        if(Math.abs(X-xa)<=sprite.getWidth()/2+30){
            shot();
        }
        if(Math.abs(X-xa)<=speed){
            X=xa;
        }else{
            X+=X-xa>=0?-speed:speed;
        }
        temp=false;
        for(int i=0;i<Menu.frame.enemy.length;i++){
            if(i==num){
                continue;
            }
            if(Menu.frame.enemy[i].getRectandle().contains(sprite.getBoundingRectangle())){
                temp=true;
                tempi=i;
                break;
            }
        }
        if(!temp) {
            tempi= (int) sprite.getX();
            sprite.setX(X);
        }else{
            sprite.setX(Menu.frame.enemy[tempi].getX()+sprite.getWidth());
        }

        temp=false;
        for(int i=0;i<Menu.frame.enemy.length;i++){
            if(i==num){
                continue;
            }
            if(Menu.frame.enemy[i].getRectandle().contains(sprite.getBoundingRectangle())){
                temp=true;
            }
        }
        if(temp) {
            sprite.setX(tempi);
        }
    }


}
