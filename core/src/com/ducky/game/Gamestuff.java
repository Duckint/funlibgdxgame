package com.ducky.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

import static com.badlogic.gdx.Gdx.graphics;

public class Gamestuff extends ApplicationAdapter {


      float speedX = 90;
	float multiplier = 0.5f;

	//long startTime;
	//long elapsedTime;

	Player objMPlayer = new Player();
      private Viewport viewport;
	OrthographicCamera playercam;


	ShapeRenderer rects;
	private ArrayList<Rectangle> platformArray;
	private ArrayList<Rectangle> enemy1Array;
	private ArrayList<Rectangle> wallArray;
	private ArrayList<Enemy> enemies;
	Platforms rectangle1;
	Platforms rectangle2;

	Enemy objen = new Enemy(500.0f, 120.0f, 32.0f, 48.0f, 30.0f, 450.0f, 550.0f);

	int viewportWidth;
	int viewportHeight;

    public void platformCollision() {
		for(Rectangle platCollide : platformArray) {
			if(objMPlayer.player.overlaps(platCollide)) {
				objMPlayer.playerVelocity.y = 0;
				objMPlayer.playerPos.y = platCollide.y + platCollide.height;
				objMPlayer.canJump = true;
				objMPlayer.canSlam = false;
				objMPlayer.isSlamming = false;
			}
		}
	}

	public void enemyCollision() {
        for (Rectangle enemyCollide1 : enemy1Array) {
			float enemyTop = enemyCollide1.y + enemyCollide1.height;

			float enemyX = enemyCollide1.x - playercam.position.x + viewportWidth / 2;
			float enemyY = enemyCollide1.y - playercam.position.y + viewportHeight / 2 ;

            enemyCollide1.setPosition(enemyX, enemyY);


			if(objMPlayer.playerPos.y + objMPlayer.player.height >= enemyTop && objMPlayer.player.overlaps(enemyCollide1)) {
               objen.enemyRender = false;
			} else if(objMPlayer.playerPos.y - objMPlayer.player.height <= enemyTop && objMPlayer.player.overlaps(enemyCollide1) && objen.enemyRender  ) {
				objMPlayer.playerPos.x = 140;
				objMPlayer.playerPos.y = 120;
			}

			enemyCollide1.setPosition(enemyCollide1.x + playercam.position.x - viewportWidth / 2, enemyCollide1.y + playercam.position.y - viewportHeight / 2);
		}
	}

	public void input() {



		if(Gdx.input.isKeyPressed(Keys.R)) {
             objMPlayer.playerPos.x = 140;
			objMPlayer.playerPos.y = 120;

			objen.enemyRender = true;
		}
		if(Gdx.input.isKeyPressed(Keys.A)) {
			objMPlayer.playerPos.x -= speedX * graphics.getDeltaTime();

		}
		if(Gdx.input.isKeyPressed(Keys.D)) {
			objMPlayer.playerPos.x += speedX * graphics.getDeltaTime();
		}
		if(Gdx.input.isKeyPressed(Keys.W) && objMPlayer.canJump) {
			objMPlayer.playerVelocity.y = objMPlayer.jumpPowa;
			objMPlayer.canJump = false;
			objMPlayer.isPlayerJumping = true;
			objMPlayer.canSlam = true;
		}
		if(Gdx.input.isKeyPressed(Keys.SPACE) && objMPlayer.canSlam && objMPlayer.isPlayerJumping) {
			objMPlayer.playerVelocity.y = -10.0f;
			objMPlayer.isSlamming = true;
			objMPlayer.canSlam = false;
		}
	}
    @Override
	public void create () {

		rectangle1 = Platforms.createRectangle(100, 80, 200, 20);
		rectangle2 = Platforms.createRectangle(400, 60, 200, 20);

		objMPlayer.playerVelocity = new Vector2(0, 0);
        viewportWidth = graphics.getWidth();
		viewportHeight = graphics.getHeight();

		platformArray = new ArrayList<Rectangle>();
		enemy1Array = new ArrayList<Rectangle>();
		wallArray = new ArrayList<Rectangle>();
		enemies = new ArrayList<Enemy>();


		objMPlayer.playerRender = new ShapeRenderer();
		rects = new ShapeRenderer();

		//startTime = TimeUtils.nanoTime();
		//WHY CAN'T THIS STUPID CAMERA WORK?!?!
        playercam = new OrthographicCamera();
		playercam.setToOrtho(false, graphics.getWidth() / 2, graphics.getHeight() / 2);
        viewport = new FitViewport(graphics.getWidth(), graphics.getHeight(), playercam);
		viewport.setCamera(playercam);

        viewport.apply();


		platformArray.add(rectangle1.getRectangle());
		platformArray.add(rectangle2.getRectangle());



		enemy1Array.add(objen.getRectangle());
		//enemy1Array.add(enemies[2].getRectangle());

	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 1, 1);

		playercam.position.x = objMPlayer.playerPos.x;
		playercam.position.y = objMPlayer.playerPos.y;
		playercam.update();

		//elapsedTime = System.nanoTime() - startTime;
		playercam.position.x = MathUtils.clamp(objMPlayer.playerPos.x + objMPlayer.player.width / 2, viewportWidth / 2, viewportWidth * 2 - viewportWidth / 2);
		playercam.position.y = MathUtils.clamp(objMPlayer.playerPos.y + objMPlayer.player.height / 2, viewportHeight / 2, viewportHeight * 2 - viewportHeight / 2);
		playercam.update();
		rects.setProjectionMatrix(playercam.combined);
		Gdx.gl.glViewport(0, 0, viewportWidth, viewportHeight);


		objMPlayer.playerdraw();
		input();

		objMPlayer.playerPos.add(objMPlayer.playerVelocity);
		objMPlayer.player.setPosition(objMPlayer.playerPos.x, objMPlayer.playerPos.y);

		platformCollision();


		objMPlayer.playerVelocity.y -= objMPlayer.currentFallSpeed * graphics.getDeltaTime();

		objen.enemyType1();
		enemyCollision();


		rects.begin(ShapeRenderer.ShapeType.Line);
		rects.rect(platformArray.get(0).x, platformArray.get(0).y, platformArray.get(0).width, platformArray.get(0).height);
		rects.rect(platformArray.get(1).x, platformArray.get(1).y, platformArray.get(1).width, platformArray.get(1).height);
         if(objen.enemyRender) {
			 rects.rect(enemy1Array.get(0).x, enemy1Array.get(0).y, enemy1Array.get(0).width, enemy1Array.get(0).height);
		 }
         /*if()
		 {
			 rects.rect(enemy1Array.get(1).x, enemy1Array.get(1).y, enemy1Array.get(1).width, enemy1Array.get(1).height);
		 }*/
         rects.end();

	}

	@Override
	public void dispose () {
	}
}