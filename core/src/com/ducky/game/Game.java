package com.ducky.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Gdx;

public class Game extends ApplicationAdapter {

	 public static final float speedx = 100;
	 float x = 50;
	 float y = 50;
	ShapeRenderer shape;
	
	@Override
	public void create () {
		shape = new ShapeRenderer();
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 1, 1, 1);


	}
	
	@Override
	public void dispose () {
	}
}
