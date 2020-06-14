package com.severgames.nigth;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.severgames.lib.Button;
import com.severgames.lib.ClickListener;


public class Menu extends ScreenAdapter implements ClickListener {

    private SpriteBatch batch;
    private Button start;
    private OrthographicCamera camera;
    private MyGdxGame game;
    static Frame frame;


    Menu(MyGdxGame game){
        this.game = game;
    }


    @Override
    public void show() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false,MyGdxGame.W,MyGdxGame.H);
        start = new Button("start.png");
        start.setSizeW(3);
        start.setPosition(ClickListener.POSITION_HORIZONTAL.Center, ClickListener.POSITION_VERTICAL.Center);
        start.addClickListener(this,camera);
    }


    @Override
    public void render(float delta) {
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        Gdx.gl.glClearColor(0.1f,0.7f,0.5f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        start.draw(batch);

        batch.end();

    }

    @Override
    public void click(String id) {
        if(start.id(id)){
            frame = new Frame();
            game.setScreen(frame);
            dispose();
        }
    }
}
