package com.ducky.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
//import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Input.Keys;
import static com.badlogic.gdx.Gdx.gl20;
import static com.badlogic.gdx.Gdx.graphics;
//import com.badlogic.gdx.utils.TimeUtils;
import java.util.ArrayList;

public class Gamestuff extends ApplicationAdapter {

	Player objMPlayer = new Player();

	private OrthographicCamera playercam;

	ShapeRenderer rects;
	private ArrayList<Rectangle> platformArray;
	private ArrayList<Rectangle> enemy1Array;
	private ArrayList<Rectangle> wallArray;
	Platforms rectangle1 = Platforms.createRectangle(100, 80, 200, 20);
	Platforms rectangle2 = Platforms.createRectangle(400, 60, 200, 20);

	Enemies objen = new Enemies(500.0f, 120.0f, 32.0f, 32.0f, 30.0f, 450.0f, 550.0f);


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

			if(objMPlayer.playerPos.y + objMPlayer.player.height >= enemyTop && objMPlayer.player.overlaps(enemyCollide1)) {
               objen.enemyRender = false;
			} else if(objMPlayer.playerPos.y - objMPlayer.player.height <= enemyTop && objMPlayer.player.overlaps(enemyCollide1) && objen.enemyRender  ) {
				objMPlayer.playerPos.x = 140;
				objMPlayer.playerPos.y = 120;
			}
		}
	}

	public void input() {
		if(Gdx.input.isKeyPressed(Keys.R)) {
			objMPlayer.playerPos.x = 140;
			objMPlayer.playerPos.y = 120;

			objen.enemyRender = true;
		}
		if(Gdx.input.isKeyPressed(Keys.A)) {
			objMPlayer.playerVelocity.x = -objMPlayer.speedX * graphics.getDeltaTime();
		}
		else{objMPlayer.playerVelocity.x = 0;}
		if(Gdx.input.isKeyPressed(Keys.D)) {
			objMPlayer.playerVelocity.x = objMPlayer.speedX * graphics.getDeltaTime();
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

		playercam.position.x = MathUtils.clamp(playercam.position.x, playercam.viewportWidth / 2f, 100 - playercam.viewportWidth / 2f);
		playercam.position.y = MathUtils.clamp(playercam.position.y, playercam.viewportHeight / 2f, 100 - playercam.viewportHeight / 2f);
	}
    @Override
	public void create () {

		graphics.setWindowedMode(800, 600);

		objMPlayer.speedX = 90.0f;
		
		objMPlayer.playerVelocity = new Vector2(0, 0);


		platformArray = new ArrayList<>();
		enemy1Array = new ArrayList<>();
		wallArray = new ArrayList<>();


		playercam = new OrthographicCamera(800, 600);

		playercam.position.set(playercam.viewportWidth / 2f, playercam.viewportHeight / 2f, 0);
		playercam.update();

		objMPlayer.playerRender = new ShapeRenderer();
		rects = new ShapeRenderer();

		platformArray.add(rectangle1.getRectangle());
		platformArray.add(rectangle2.getRectangle());

		enemy1Array.add(objen.getRectangle());
	}

	@Override
	public void render () {
		input();
		playercam.update();
		rects.setProjectionMatrix(playercam.combined);

		Gdx.gl.glClear(gl20.GL_COLOR_BUFFER_BIT);
		objMPlayer.playerdraw();


		objMPlayer.playerPos.add(objMPlayer.playerVelocity);
		objMPlayer.player.setPosition(objMPlayer.playerPos.x, objMPlayer.playerPos.y);

		platformCollision();

		objMPlayer.playerVelocity.y -= objMPlayer.currentFallSpeed * graphics.getDeltaTime();

		objen.enemyType1();
		enemyCollision();


		rects.begin(ShapeRenderer.ShapeType.Line);
		for(Rectangle platform : platformArray) {
			rects.rect(platform.x, platform.y, platform.width, platform.height);
		}
		for(Rectangle enemies : enemy1Array) {
			if(objen.enemyRender) {
				rects.rect(enemies.x, enemies.y, enemies.width, enemies.height);
			}
		}
         rects.end();
	}

	@Override
	public void dispose () {
		objMPlayer.playerRender.dispose();
		rects.dispose();
	}
}