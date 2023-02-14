package com.ducky.game;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Player {
    ShapeRenderer playerRender;
    public Rectangle player = new Rectangle(50, 100, 50, 50);
    public float playerFallSpeed = 20;

    public void playerdraw()
    {
        playerRender.begin(ShapeRenderer.ShapeType.Line);
        playerRender.rect(player.x, player.y, player.width, player.height);
        playerRender.end();
    }



}
