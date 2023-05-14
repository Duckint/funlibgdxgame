package com.ducky.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
	Menus objMenu = new Menus(false, false, false);

	private OrthographicCamera playercam;

	ShapeRenderer rects;
	SpriteBatch batch;
	BitmapFont font;
	boolean isPaused = false;

    public void wallCollision() {
		for(Rectangle wallCollide : objLvl.wallArray) {
			if(objMPlayer.player.overlaps(wallCollide)) {
				objMPlayer.playerVelocity.x = 0;
				objMPlayer.playerPos.x = wallCollide.x + wallCollide.width;
			}
		}
	}
	public boolean collides(Rectangle rect)
	{
		Rectangle query = new Rectangle(rect.x, rect.y, rect.x + rect.width, rect.y + rect.height);
		return false;
	}
    public void platformCollision() {
		for(Rectangle platCollide : objLvl.platformArray) {
			if(objMPlayer.player.overlaps(platCollide)) {
				objMPlayer.playerVelocity.y = 0;
				objMPlayer.playerPos.y = platCollide.y + platCollide.height;
				objMPlayer.canPlayerRun = true;
				objMPlayer.canJump = true;
				objMPlayer.canSlam = false;
				objMPlayer.isSlamming = false;
				objMPlayer.isPlayerJumping = false;
			}
		}
	}

	public void enemyCollision() {
        for (Enemies enemyCollide1 : objLvl.enemy1Array) {
			float enemyTop = enemyCollide1.getRectangle().y + enemyCollide1.getRectangle().height;

			if(objMPlayer.playerPos.y + objMPlayer.player.height >= enemyTop && objMPlayer.player.overlaps(enemyCollide1.getRectangle()) && Gdx.input.isKeyPressed(Keys.P))
			{
               enemyCollide1.enemyRender = false;
				for(Enemies enemy : objLvl.enemy1Array)
				{
					if(enemy.getRectangle().equals(enemyCollide1))
					{
						enemy.setEnemyRender(false);
						break;
					}
				}
			}
			else if(objMPlayer.playerPos.y - objMPlayer.player.height <= enemyTop && objMPlayer.player.overlaps(enemyCollide1.getRectangle()) && enemyCollide1.enemyRender) {
				objMPlayer.playerPos.x = 140;
				objMPlayer.playerPos.y = 105;
			}
		}
	}
	public void reset(){
		if(Gdx.input.isKeyPressed(Input.Keys.R)) {
			objMPlayer.playerPos.x = objLvl.levelPlayerpos.x;
			objMPlayer.playerPos.y = objLvl.levelPlayerpos.y;
			objMPlayer.playerVelocity.y = 0;
			objMPlayer.playerVelocity.x = 0;
			objMPlayer.player.height = 50;
			objMPlayer.accelerationX = 75.0f;
			for(Enemies enemy : objLvl.enemy1Array) {
				enemy.setEnemyRender(true);
			}
		}
	}
    @Override
	public void create () {
		graphics.setWindowedMode(1200, 800);
		graphics.setTitle("Fun Game :D");

		batch = new SpriteBatch();
		font = new BitmapFont(Gdx.files.internal("reallycool.fnt"), false);

		objMPlayer.playerVelocity = new Vector2(0, 0);

		objLvl.platformArray = new ArrayList<>();
		objLvl.enemy1Array = new ArrayList<>();
		objLvl.wallArray = new ArrayList<>();
		objMenu.menubuttons = new ArrayList<>();

		playercam = new OrthographicCamera(1200, 800);

		playercam.position.set(playercam.viewportWidth / 2f, playercam.viewportHeight / 2f, 0);
		playercam.update();

		objMenu.mmC();

		objMenu.menurender = new ShapeRenderer();
		objMPlayer.playerRender = new ShapeRenderer();
		rects = new ShapeRenderer();

		objLvl.leveltest();
	}
	@Override
	public void render () {
		objMenu.mainmenu();
		if(objMenu.getStart()){

			if(Gdx.input.isKeyPressed(Keys.ESCAPE) && isPaused) {
				isPaused = false;
			}
			else if(Gdx.input.isKeyPressed(Keys.ESCAPE) && !isPaused) {
				isPaused = true;
			}
            if(!isPaused)
			{
				objMPlayer.playerMovementCalculations();
			}
			reset();
			objMPlayer.keyinput();
			playercam.update();
			rects.setProjectionMatrix(playercam.combined);
			batch.setProjectionMatrix(batch.getProjectionMatrix().setToOrtho2D(0, 0, 1200, 800));

			Gdx.gl.glClear(gl20.GL_COLOR_BUFFER_BIT);
			batch.begin();



			if(isPaused) {
				String text = "Paused";
				font.draw(batch, text, 400, 750);
			}
			batch.end();
			objMPlayer.playerdraw();



			rects.begin(ShapeRenderer.ShapeType.Line);
			for (Rectangle walls : objLvl.wallArray) {
				rects.rect(walls.x, walls.y, walls.width, walls.height);
			}
			wallCollision();
			for (Rectangle platform : objLvl.platformArray) {
				rects.rect(platform.x, platform.y, platform.width, platform.height);
			}
			platformCollision();
			for (Enemies enemies : objLvl.enemy1Array) {
				if (enemies.getEnemyRender()) {
					rects.rect(enemies.getRectangle().x, enemies.getRectangle().y, enemies.getRectangle().width, enemies.getRectangle().height);
				}
				if(!isPaused){enemies.enemyType1();}
			}

			enemyCollision();

			rects.end();
		}
	}
	@Override
	public void dispose () {
		objMPlayer.playerRender.dispose();
		objMenu.menurender.dispose();
		rects.dispose();
		batch.dispose();

	}
}