package com.severgames.lib;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

abstract class Objects {

    String id;
    static int iterator = 0;
    ClickListener mainClick;
    boolean addClickListen;
    Sprite sprite;
    int x=0,y=0,h=0,w=0;
    boolean ClickNeed;
    Vector3 vector3;
    OrthographicCamera camera;


    public void addClickListener(ClickListener click, OrthographicCamera camera){
        mainClick = click;
        this.camera = camera;
        vector3 = new Vector3();
        addClickListen=true;
    }

    public void removeClickListener(){
        addClickListen=false;
        mainClick=null;
        camera=null;
    }

    Objects(){
        addClickListen=false;
        ClickNeed=false;
        id=iterator+"";
        iterator++;
    }




    void setClickNeed(){
        ClickNeed=true;
    }


    void load(Sprite sprite){
        this.sprite = sprite;
    }

    void load(String path){
        this.sprite = new Sprite(new Texture(path));
    }

    public boolean id(String id){
        return id.equals(this.id);
    }

    public void setPosition(int X,int Y){
        x=X;
        y=Y;
        setPos(X,Y);
    }

    void setPos(int X,int Y){
        sprite.setPosition(X,Y);
    }

    void setSizen(int W,int H){
        sprite.setSize(W,H);
    }

    public void setSize(int W,int H){
        h=H;
        w = W;
        setSizen(W,H);
    }

    public void setPosition(ClickListener.POSITION_HORIZONTAL position_horizontal, ClickListener.POSITION_VERTICAL position_vertical) {
        switch (position_horizontal) {
            case Center:
                sprite.setX((float) Gdx.graphics.getWidth() / 2 - sprite.getWidth() / 2);
                break;
            case LeftBottom:
                sprite.setX(0);
                break;
            case RightBottom:
                sprite.setX(Gdx.graphics.getWidth() - sprite.getWidth());
                break;
            case LeftCenter:
                int temp = (int) (Gdx.graphics.getWidth() / 4f - sprite.getWidth() / 2f);
                sprite.setX(temp < 0 ? 0 : temp);
                break;
            case RightCenter:
                int temp2 = (int) ((Gdx.graphics.getWidth() / 4f) * 3f - sprite.getWidth() / 2f);
                sprite.setX(temp2 > Gdx.graphics.getWidth() - sprite.getWidth() ? Gdx.graphics.getWidth() - sprite.getWidth() : temp2);
                break;
        }
        switch (position_vertical) {
            case Center:
                sprite.setY((float) Gdx.graphics.getHeight() / 2 - sprite.getHeight() / 2);
                break;
            case UpBottom:
                sprite.setY(Gdx.graphics.getHeight() - sprite.getHeight());
                break;
            case DownBottom:
                sprite.setY(0);
                break;
            case UpCenter:
                int temp = (int) ((Gdx.graphics.getHeight() / 4) * 3 - sprite.getHeight() / 2);
                sprite.setY(temp > Gdx.graphics.getHeight() - sprite.getHeight() ? Gdx.graphics.getHeight() - sprite.getHeight() : temp);
                break;
            case DownCenter:
                int temp2 = (int) (Gdx.graphics.getHeight() / 4 - sprite.getHeight() / 2);
                sprite.setY(temp2 > 0 ? temp2 : 0);
                break;

        }
        setPos((int)sprite.getX(),(int)sprite.getY());

    }

    public String getId() {
        return id;
    }

    protected void onClick(Vector3 vector3){
        if(addClickListen||ClickNeed) {
            if (sprite.getBoundingRectangle().contains(new Vector2(vector3.x, vector3.y))) {
                click();
            }
        }
    }

    protected void click(){
        mainClick.click(id);
    }

    public void setSizeH(int del){
        float h = Gdx.graphics.getHeight()/del;
        float w = (h/sprite.getHeight()*sprite.getWidth());
        sprite.setSize(w,h);
    }

    public void setSizeW(int del){
        float w = Gdx.graphics.getWidth()/del;
        float h = (w/sprite.getWidth()*sprite.getHeight());
        sprite.setSize(w,h);
    }

    protected void checkClick(){
        if(addClickListen&&Gdx.input.justTouched()){
            vector3.set(Gdx.input.getX(),Gdx.input.getY(),0);
            camera.unproject(vector3);
            onClick(vector3);
        }
    }

    public void draw(SpriteBatch batch){
        checkClick();
        sprite.draw(batch);
    }



    public void setId(String id) {
        this.id = id;
    }


}
