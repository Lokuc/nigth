package com.severgames.nigth;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

abstract class Subject {

     protected Sprite sprite;


    public Rectangle getRectandle(){
        return sprite.getBoundingRectangle();
    }

    public void destroy(){
        sprite.setPosition(-sprite.getWidth(),-sprite.getHeight());
    }

}
