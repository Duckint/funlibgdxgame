package com.ducky.game;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Player {
    ShapeRenderer playerRender;
    public Rectangle player = new Rectangle(100, 140, 50, 50);
    public float playerFallSpeed = 20;
    public boolean isPlayerJumping;

    //this is just stating that I want it to be true by default.
    public boolean canJump = true;

    public void playerdraw()
    {
        playerRender.begin(ShapeRenderer.ShapeType.Line);
        playerRender.rect(player.x, player.y, player.width, player.height);
        playerRender.end();
    }



}
