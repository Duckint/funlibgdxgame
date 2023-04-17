package com.ducky.game;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Player {
    ShapeRenderer playerRender;
    public Vector2 playerVelocity;
    public Vector2 playerPos = new Vector2(140, 120);
    public Rectangle player = new Rectangle(playerPos.x, playerPos.y, 50, 50);
    public float sprintPowa = 1.02f;
    public float currentFallSpeed = 50.0f;
    public float slamPowa = 6.0f;
    public boolean canPlayerRun;
    public boolean isPlayerJumping = true;
    public boolean canJump = false;
    public float jumpPowa = 100.0f;
    public boolean canSlam = false;
    public boolean isSlamming = false;
    public float accelerationX = 50.0f;
    public float decelerationX = 100.0f;
    //public float maxSpeedX = 200.0f;
    public void playerdraw()
    {
        playerRender.begin(ShapeRenderer.ShapeType.Line);
        playerRender.rect(playerPos.x, playerPos.y, player.width, player.height);
        playerRender.end();
    }
}
