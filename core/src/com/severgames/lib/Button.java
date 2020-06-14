package com.severgames.lib;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Button extends Objects{


    public Button(Sprite sprite) {
        super();
        load(sprite);
    }

    public Rectangle getRect(){
        return sprite.getBoundingRectangle();
    }

    public Button(String path) {
        super();
        load(path);
    }




}
