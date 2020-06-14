package com.severgames.lib;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TextView extends Objects{

    private BitmapFont font;

    private String text;

    public TextView(){
        super();
        initialize();

    }

    private void initialize(){
        sprite = new Sprite();
        font = new BitmapFont();
        font.setColor(Color.BLACK);
    }

    public void setColor(Color color){
        font.setColor(color);
    }

    public Color getColor(){
        return font.getColor();
    }

    public TextView(String text){
        super();
        this.text=text;
        initialize();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }



    public void draw(SpriteBatch batch){
        font.draw(batch,text,x,y);
    }

}
