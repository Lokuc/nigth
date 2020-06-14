package com.severgames.lib;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class EditText extends Objects implements InputProcessor {

    private String text="";
    private BitmapFont font;
    private EditTextListener mainEditTextLis;
    private String tips="";
    private boolean addTips = false;


    private Color color;

    public EditText() {
        super();
        super.setClickNeed();
        System.out.println(id);
        load("text.png");
        font = new BitmapFont();
        color = Color.BLACK;
        font.setColor(color);
    }

    public void setText(String text){
        this.text=text;
    }

    public void setTextTips(String tips){
        addTips=true;
        this.tips=tips;
    }


    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getText(){
        return text;
    }

    protected void click(){
        Gdx.input.setOnscreenKeyboardVisible(true);
        Gdx.input.setInputProcessor(this);
    }

    public void addEditTextListener(EditTextListener editTextListener){
        mainEditTextLis = editTextListener;
    }

    public void draw(SpriteBatch batch){
        sprite.setSize(text.length()<=15?150:text.length()*9,font.getLineHeight()+20);
        super.draw(batch);
        if(!text.equals("")) {
            font.setColor(color);
            font.draw(batch, text, 10 + x, 23 + y);
        }else if(addTips){
            font.setColor(Color.GRAY);
            font.draw(batch,tips,10+x,23+y);
        }
        //System.out.println(font.getLineHeight()+" ");
    }



    @Override
    public boolean keyDown(int keycode) {
        if(keycode==67){
            if(text.length()>0) {
                text = text.substring(0, text.length() - 1);
                if(mainEditTextLis!=null){
                    mainEditTextLis.EditText(text,id);
                }
            }
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }


    @Override
    public boolean keyTyped(char character) {
        if(character!='\b') {
            text+= character;
            if(mainEditTextLis!=null){
                mainEditTextLis.EditText(text,id);
            }
        }
        return false;
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

}
