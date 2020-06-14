package com.severgames.nigth;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class MyGdxGame extends Game {

	static int H;
	static int W;
	static final int SPEEDBULLET =  1500

	@Override
	public void create () {
		H= Gdx.graphics.getHeight();
		W= Gdx.graphics.getWidth();
		System.out.println(H+" "+W);
		Menu menu = new Menu(this);
		setScreen(menu);
	}
	
	@Override
	public void dispose () {

	}
}
