package com.severgames.nigth;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
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
    Enemy[] enemy;
    private Button shotB;
    private Star[] stars;
    private Subject[] subject;
    private boolean []dest;
    private Sprite gameOver;
    private float timeSpawnEnemy=0;



    @Override
    public void show() {
        batch = new SpriteBatch();
        vector3 = new Vector3();
        plane = new MyPlane();
        shotB=new Button("but.png");
        shotB.setSizeH(5);
        gameOver=new Sprite(new Texture("gameover.png"));
        stars = new Star[10];
        Random r = new Random();
        for(int i=0;i<stars.length;i++){
            stars[i]=new Star(r,MyGdxGame.H);
        }
        shotB.setPosition(POSITION_HORIZONTAL.LeftBottom, POSITION_VERTICAL.DownBottom);
        enemy= new Enemy[3];
        camera = new OrthographicCamera();
        camera.setToOrtho(false,MyGdxGame.W,MyGdxGame.H);
        for(int i=0;i<enemy.length;i++){
            enemy[i]=new Enemy(i);
        }
        enemy[0].spawn();
        subject=  new Subject[enemy.length*2+2];
        subject[0]=plane.getBullet();
        subject[1]=plane;
        subject[2]=enemy[0];
        subject[3]=enemy[1];
        subject[4]=enemy[2];
        subject[5]=enemy[0].getBullet();
        subject[6]=enemy[1].getBullet();
        subject[7]=enemy[2].getBullet();
        dest=new boolean[enemy.length*2+2];
    }


    @Override
    public void render(float delta) {
        timeSpawnEnemy+=delta;
        if(timeSpawnEnemy>=5.0f){
            for (Enemy value : enemy) {
                if (!value.getActive()) {
                    value.spawn();
                    timeSpawnEnemy = 0;
                }
            }
        }
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        Gdx.gl.glClearColor(0, 0, 1f - vusota / 500f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(plane.getLife()) {
            vusota += delta;
            checkCol();
            if (Gdx.app.getType() == Application.ApplicationType.Desktop) {
                if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                    plane.shot();
                }
            }


            if (!press) {
                if (Gdx.input.isTouched(0)) {
                    vector3.set(Gdx.input.getX(0), Gdx.input.getY(0), 0);
                    camera.unproject(vector3);
                    //x= (int) vector3.x;
                    //y= (int) vector3.y;
                    plane.x = (int) (plane.getX() - vector3.x);
                    plane.y = (int) (plane.getY() - vector3.y);
                    press = true;
                } else {
                    press = false;
                }
            } else if (Gdx.input.isTouched(0)) {
                vector3.set(Gdx.input.getX(0), Gdx.input.getY(0), 0);
                camera.unproject(vector3);
                plane.setX((int) ((vector3.x - x)) + plane.x);
                plane.setY((int) ((vector3.y - y)) + plane.y);
                //x= (int) vector3.x;
            } else {
                press = false;
            }
            if (Gdx.input.isTouched(1)) {
                vector3.set(Gdx.input.getX(1), Gdx.input.getY(1), 0f);
                camera.unproject(vector3);
                if (shotB.getRect().contains(new Vector2(vector3.x, vector3.y))) {
                    plane.shot();
                }
            }
            for(int i=0;i<enemy.length;i++){
                enemy[i].setX(plane.getX());
            }

            batch.begin();

            for (Star s : stars) {
                s.draw(batch, delta);
            }
            plane.draw(batch, delta);
            for(int i=0;i<enemy.length;i++){
                enemy[i].draw(batch,delta);
            }
            shotB.draw(batch);

            batch.end();
        }else{
            batch.begin();
            gameOver.draw(batch);
            batch.end();
        }

    }


    private void checkCol(){
        for(int i=0;i<dest.length;i++){
            dest[i]=false;
        }
        for(int i=0;i<subject.length;i++){
            for(int j=0;j<subject.length;j++){
                if (i != j) {
                    if(subject[i].getRectandle().contains(subject[j].getRectandle())){
                        dest[i]=true;
                        dest[j]=true;
                    }
                }
            }
        }
        for(int i=0;i<subject.length;i++){
            if(dest[i]){
                subject[i].destroy();
            }
        }

    }

}
