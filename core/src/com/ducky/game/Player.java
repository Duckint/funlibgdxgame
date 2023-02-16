package com.ducky.game;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Player {
    ShapeRenderer playerRender;


    public Vector2 playerVelocity;
    public Rectangle player = new Rectangle(50, 50, 50, 50);
    public float playerFallSpeed = 60;
    public boolean isPlayerJumping;
    //this is a reminder to myself that this variable should start true
    public boolean canJump = true;




    public void playerdraw()
    {
        playerRender.begin(ShapeRenderer.ShapeType.Line);
        playerRender.rect(player.x, player.y, player.width, player.height);
        playerRender.end();
    }



}
