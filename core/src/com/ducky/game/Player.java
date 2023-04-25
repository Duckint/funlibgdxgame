package com.ducky.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import static com.badlogic.gdx.Gdx.graphics;



public class Player {
    ShapeRenderer playerRender;

    public Vector2 playerVelocity;
    //public Vector2 startingPlayerPos = new Vector2();
    public Vector2 playerPos = new Vector2(140, 105);
    public Rectangle player = new Rectangle(playerPos.x, playerPos.y, 50, 50);
    public boolean isCrouching = false;
    public float sprintPowa = 1.02f;
    public float currentFallSpeed = 50.0f;
    public float slamPowa = 20.0f;
    public boolean canPlayerRun;
    public boolean isPlayerJumping = true;
    public boolean canJump = false;
    public float jumpPowa = 100.0f;
    public boolean canSlam = false;
    public boolean isSlamming = false;
    public float accelerationX = 75.0f;
    public float decelerationX = 100.0f;
    //public float maxSpeedX = 200.0f;

    public void keyinput() {
        if(Gdx.input.isKeyPressed(Input.Keys.S) && !isCrouching && !isPlayerJumping) {
            player.height = player.height / 2.5f;
            isCrouching = true;
            if(playerVelocity.x > 0) {
                accelerationX--;
            }
        } else if(!Gdx.input.isKeyPressed(Input.Keys.S) && isCrouching ) {
            player.height = player.height * 2.5f;
            isCrouching = false;
            accelerationX = 75f;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            if(playerVelocity.x >= 0) {
                playerVelocity.x = -accelerationX * graphics.getDeltaTime();
            }
            playerVelocity.x -= accelerationX * graphics.getDeltaTime();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            if(playerVelocity.x <= 0) {
                playerVelocity.x = accelerationX * graphics.getDeltaTime();
            }
            playerVelocity.x += accelerationX * graphics.getDeltaTime();
        }

        if (!Gdx.input.isKeyPressed(Input.Keys.A) && !Gdx.input.isKeyPressed(Input.Keys.D)) {
            if (playerVelocity.x > 0) {
                playerVelocity.x -= decelerationX * graphics.getDeltaTime();
                if (playerVelocity.x < 0) {
                    playerVelocity.x = 0;
                }
            } else if (playerVelocity.x < 0) {
                playerVelocity.x += decelerationX * graphics.getDeltaTime();
                if(playerVelocity.x > 0) {
                    playerVelocity.x = 0;
                }
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W) && canJump) {
            playerVelocity.y = jumpPowa;
            canJump = false;
            isPlayerJumping = true;
            canSlam = true;
            canPlayerRun = false;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE) && canSlam && isPlayerJumping) {
            playerVelocity.y = -currentFallSpeed * slamPowa;
            //currentFallSpeed * slamPowa = how hard the player slams

            isSlamming = true;
            canSlam = false;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) && canPlayerRun) {
            playerVelocity.x = playerVelocity.x * sprintPowa;
        }
    }
    public void playerdraw()
    {
        playerRender.begin(ShapeRenderer.ShapeType.Line);
        playerRender.rect(playerPos.x, playerPos.y, player.width, player.height);
        playerRender.end();
    }
}
