package com.severgames.nigth;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Bullet {

    private Sprite sprite;
    private boolean toUp = false;
    private boolean active = false;

    Bullet(){
        sprite = new Sprite(new Texture("bullet.png"));
        sprite.setSize(MyGdxGame.H/50,MyGdxGame.H/25);
    }

    void spawn(boolean toUp,int X,int Y){
        active=true;
        this.toUp=toUp;
        sprite.setX(X-sprite.getWidth()/2);
        sprite.setY(Y);
    }


    void checkCollision(){
        if(sprite.getY()-sprite.getHeight()<=0){
            deactivate();
        }else if(sprite.getY()>MyGdxGame.H){
            deactivate();
        }

    }

    private void deactivate(){
        active=false;
        sprite.setX(-sprite.getWidth());
        sprite.setY(-sprite.getHeight());
    }

    void draw(SpriteBatch batch,float delta) {
        if (active){
            if (toUp) {
                sprite.setY(sprite.getY()+MyGdxGame.SPEEDBULLET * delta);
            } else {
                sprite.setY(sprite.getY()-MyGdxGame.SPEEDBULLET * delta);
            }
            sprite.draw(batch);
        }

    }

}
