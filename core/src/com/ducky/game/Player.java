package com.ducky.game;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Player {
    ShapeRenderer playerRender;
    public Vector2 playerVelocity;
    public Vector2 playerPos = new Vector2(140, 120);
    public Rectangle player = new Rectangle(playerPos.x, playerPos.y, 50, 50);

    public float playerFallSpeed = 1.0f;
    //public boolean isPlayerJumping; this variable has no use atm
    //this is a reminder to myself that this variable should start true
    public boolean canJump = false;
    public float jumpPowa = 1.3f;



    public void playerdraw()
    {
        playerRender.begin(ShapeRenderer.ShapeType.Line);
        playerRender.rect(playerPos.x, playerPos.y, player.width, player.height);
        playerRender.end();
    }



}
