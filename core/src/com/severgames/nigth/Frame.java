package com.severgames.nigth;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.severgames.lib.Button;

import java.util.Random;

import static com.severgames.lib.ClickListener.*;

public class Frame extends ScreenAdapter{


    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Vector3 vector3;
    private MyPlane plane;
    private float vusota;
    private boolean press = false;
    private int x=0,y=0;
    private Enemy enemy;
    private Button shotB;
    private Star[] stars;



    @Override
    public void show() {
        batch = new SpriteBatch();
        vector3 = new Vector3();
        plane = new MyPlane();
        shotB=new Button("but.png");

        shotB.setSizeH(5);
        stars = new Star[10];
        Random r = new Random();
        for(int i=0;i<stars.length;i++){
            stars[i]=new Star(r,MyGdxGame.H);
        }
        shotB.setPosition(POSITION_HORIZONTAL.LeftBottom, POSITION_VERTICAL.DownBottom);
        enemy = new Enemy();
        camera = new OrthographicCamera();
        camera.setToOrtho(false,MyGdxGame.W,MyGdxGame.H);
        enemy.spawn();
    }


    @Override
    public void render(float delta) {
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        Gdx.gl.glClearColor(0,0,1f-vusota/500f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        vusota+=delta;
        plane.checkCol();
        switch (Gdx.app.getType()){
            case Desktop:
                if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
                    plane.shot();
                }
            break;
        }



        if(!press){
            if(Gdx.input.isTouched(0)) {
                vector3.set(Gdx.input.getX(0),Gdx.input.getY(0),0);
                camera.unproject(vector3);
                //x= (int) vector3.x;
                //y= (int) vector3.y;
                plane.x= (int) (plane.getX()-vector3.x);
                plane.y= (int) (plane.getY()-vector3.y);
                press = true;
            }else {
                press = false;
            }
        }else if(Gdx.input.isTouched(0)){
            vector3.set(Gdx.input.getX(0),Gdx.input.getY(0),0);
            camera.unproject(vector3);
            plane.setX((int) ((vector3.x-x))+plane.x);
            plane.setY((int) ((vector3.y-y))+plane.y);
            //x= (int) vector3.x;
        }else{
            press = false;
        }
        if(Gdx.input.isTouched(1)){
            vector3.set(Gdx.input.getX(1),Gdx.input.getY(1),0f);
            camera.unproject(vector3);
            if(shotB.getRect().contains(new Vector2(vector3.x,vector3.y))){
                plane.shot();
            }
        }
        enemy.setX(plane.getX());

        batch.begin();

        for(Star s:stars){
            s.draw(batch,delta);
        }
        plane.draw(batch,delta);
        enemy.draw(batch,delta);
        shotB.draw(batch);

        batch.end();
    }

}
