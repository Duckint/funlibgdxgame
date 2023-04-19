package com.ducky.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
//import com.badlogic.gdx.graphics.g2d.BitmapFont;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Input.Keys;
import static com.badlogic.gdx.Gdx.gl20;
import static com.badlogic.gdx.Gdx.graphics;
import java.util.ArrayList;

public class Gamestuff extends ApplicationAdapter {

	Player objMPlayer = new Player();
	Levels objLvl = new Levels();

	private OrthographicCamera playercam;

	ShapeRenderer rects;
	//SpriteBatch batch = new SpriteBatch();
	//BitmapFont font = new BitmapFont();
	//String text = "This is text";
	private ArrayList<Rectangle> platformArray;
	private ArrayList<Rectangle> enemy1Array;
	private ArrayList<Rectangle> wallArray;


	Enemies objen = new Enemies(500.0f, 120.0f, 32.0f, 32.0f, 30.0f, 450.0f, 550.0f);

    public void wallCollision() {
		for(Rectangle wallCollide : wallArray) {
			if(objMPlayer.player.overlaps(wallCollide)) {
				objMPlayer.playerVelocity.x = 0;
				objMPlayer.playerPos.x = wallCollide.x + wallCollide.width;
			}
		}
	}
    public void platformCollision() {
		for(Rectangle platCollide : platformArray) {
			if(objMPlayer.player.overlaps(platCollide)) {
				objMPlayer.playerVelocity.y = 0;
				objMPlayer.playerPos.y = platCollide.y + platCollide.height;
				objMPlayer.canPlayerRun = true;
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
			} else if(objMPlayer.playerPos.y - objMPlayer.player.height <= enemyTop && objMPlayer.player.overlaps(enemyCollide1) && objen.enemyRender) {
				objMPlayer.playerPos.x = 140;
				objMPlayer.playerPos.y = 105;
			}
		}
	}

	public void input() {
		if(Gdx.input.isKeyPressed(Keys.R)) {
			objMPlayer.playerPos.x = 140;
			objMPlayer.playerPos.y = 105;
			objMPlayer.playerVelocity.y = 0;
			objMPlayer.playerVelocity.x = 0;
			objMPlayer.player.height = 50;
			objMPlayer.accelerationX = 75.0f;

			objen.enemyRender = true;
		}
		if(Gdx.input.isKeyPressed(Keys.S) && objMPlayer.isCrouching == false && objMPlayer.canJump == true) {
			objMPlayer.player.height = objMPlayer.player.height / 2.5f;
			objMPlayer.isCrouching = true;
			objMPlayer.accelerationX = -0.5f;
		} else if(!Gdx.input.isKeyPressed(Keys.S) && objMPlayer.isCrouching == true ) {
			objMPlayer.player.height = objMPlayer.player.height * 2.5f;
			objMPlayer.isCrouching = false;
			objMPlayer.accelerationX = 75f;
		}
		if(Gdx.input.isKeyPressed(Keys.A)) {
			if(objMPlayer.playerVelocity.x >= 0) {
				objMPlayer.playerVelocity.x = -objMPlayer.accelerationX * graphics.getDeltaTime();
			}
			objMPlayer.playerVelocity.x -= objMPlayer.accelerationX * graphics.getDeltaTime();
		}
		if(Gdx.input.isKeyPressed(Keys.D)) {
			if(objMPlayer.playerVelocity.x <= 0) {
				objMPlayer.playerVelocity.x = objMPlayer.accelerationX * graphics.getDeltaTime();
			}
			objMPlayer.playerVelocity.x += objMPlayer.accelerationX * graphics.getDeltaTime();
		}

		if (!Gdx.input.isKeyPressed(Keys.A) && !Gdx.input.isKeyPressed(Keys.D)) {
			if (objMPlayer.playerVelocity.x > 0) {
				objMPlayer.playerVelocity.x -= objMPlayer.decelerationX * graphics.getDeltaTime();
				if (objMPlayer.playerVelocity.x < 0) {
					objMPlayer.playerVelocity.x = 0;
				}
			} else if (objMPlayer.playerVelocity.x < 0) {
				objMPlayer.playerVelocity.x += objMPlayer.decelerationX * graphics.getDeltaTime();
				if(objMPlayer.playerVelocity.x > 0) {
					objMPlayer.playerVelocity.x = 0;
				}
			}
		}
		if(Gdx.input.isKeyPressed(Keys.W) && objMPlayer.canJump) {
			objMPlayer.playerVelocity.y = objMPlayer.jumpPowa;
			objMPlayer.canJump = false;
			objMPlayer.isPlayerJumping = true;
			objMPlayer.canSlam = true;
			objMPlayer.canPlayerRun = false;
		}
		if(Gdx.input.isKeyPressed(Keys.SPACE) && objMPlayer.canSlam && objMPlayer.isPlayerJumping) {
			objMPlayer.playerVelocity.y = -objMPlayer.currentFallSpeed * objMPlayer.slamPowa;
			//currentFallSpeed * slamPowa = how hard the player slams

			objMPlayer.isSlamming = true;
			objMPlayer.canSlam = false;
		}
        if(Gdx.input.isKeyPressed(Keys.SHIFT_LEFT) && objMPlayer.canPlayerRun) {
           objMPlayer.playerVelocity.x = objMPlayer.playerVelocity.x * objMPlayer.sprintPowa;
		}
	}
    @Override
	public void create () {


		graphics.setWindowedMode(1200, 800);
		Platforms rectangle1 = Platforms.createRectangle(100, 80, 200, 20);
		Platforms rectangle2 = Platforms.createRectangle(400, 60, 200, 20);
		Platforms wall1 = Platforms.createRectangle(80, 80, 20, 600);


		objMPlayer.playerVelocity = new Vector2(0, 0);


		platformArray = new ArrayList<>();
		enemy1Array = new ArrayList<>();
		wallArray = new ArrayList<>();


		playercam = new OrthographicCamera(1200, 800);

		playercam.position.set(playercam.viewportWidth / 2f, playercam.viewportHeight / 2f, 0);
		playercam.update();

		objMPlayer.playerRender = new ShapeRenderer();
		rects = new ShapeRenderer();

		platformArray.add(rectangle1.getRectangle());
		platformArray.add(rectangle2.getRectangle());
		wallArray.add(wall1.getRectangle());

		enemy1Array.add(objen.getRectangle());
	}

	@Override
	public void render () {

		input();
		playercam.update();
		rects.setProjectionMatrix(playercam.combined);

		Gdx.gl.glClear(gl20.GL_COLOR_BUFFER_BIT);
        /*batch.begin();
		font.draw(batch, text, 100, 100);*/
		objMPlayer.playerdraw();

        objMPlayer.playerPos.add(objMPlayer.playerVelocity.x * graphics.getDeltaTime(), objMPlayer.playerVelocity.y * graphics.getDeltaTime());
		objMPlayer.player.setPosition(objMPlayer.playerPos.x, objMPlayer.playerPos.y);

		wallCollision();
		platformCollision();


		objMPlayer.playerVelocity.y -= objMPlayer.currentFallSpeed * graphics.getDeltaTime();

		objen.enemyType1();
		enemyCollision();


		rects.begin(ShapeRenderer.ShapeType.Line);
		for(Rectangle walls: wallArray) {
			rects.rect(walls.x, walls.y, walls.width, walls.height);
		}
		for(Rectangle platform : platformArray) {
			rects.rect(platform.x, platform.y, platform.width, platform.height);
		}
		for(Rectangle enemies : enemy1Array) {
			if(objen.enemyRender) {
				rects.rect(enemies.x, enemies.y, enemies.width, enemies.height);
			}
		}
		//batch.end();
         rects.end();
	}

	@Override
	public void dispose () {
		objMPlayer.playerRender.dispose();
		rects.dispose();
		//batch.dispose();
	}
}