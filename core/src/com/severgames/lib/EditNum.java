package com.severgames.lib;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
public class EditNum extends Objects implements InputProcessor {

    private String text = "";
    private BitmapFont font;
    private EditNumListener mainEditTextLis;

    public EditNum() {
        super();
        super.setClickNeed();
        load("text.png");
        font = new BitmapFont();
        font.setColor(Color.BLACK);
    }

    public void setText(int number){
        this.text=number+"";
    }

    public int getText(){
        return Integer.parseInt(text);
    }

    protected void click(){
        Gdx.input.setOnscreenKeyboardVisible(true);
        Gdx.input.setInputProcessor(this);
    }

    public void addEditTextListener(EditNumListener editTextListener){
        mainEditTextLis = editTextListener;
    }

    public void draw(SpriteBatch batch){
        sprite.setSize(text.length()<=15?150:text.length()*9,font.getLineHeight()+20);
        super.draw(batch);
        font.draw(batch,text,10+x,23+y);
        //System.out.println(font.getLineHeight()+" ");
    }



    @Override
    public boolean keyDown(int keycode) {
        if(keycode==67){
            if(text.length()>0) {
                text = text.substring(0, text.length() - 1);
                if(mainEditTextLis!=null){
                    if(!text.equals("")) {
                        mainEditTextLis.EditNum(Integer.parseInt(text), id);
                    }else{
                        mainEditTextLis.EditNum(0,id);
                    }
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
            try {
                text += Integer.parseInt(character+"");
                if (mainEditTextLis != null) {
                    if(!text.equals("")) {
                        mainEditTextLis.EditNum(Integer.parseInt(text), id);
                    }else{
                        mainEditTextLis.EditNum(0,id);
                    }
                }
            }catch (Exception ignored){

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
