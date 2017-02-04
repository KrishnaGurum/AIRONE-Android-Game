package com.airone.game.States;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class State
{
    protected OrthographicCamera camera;
    protected Vector3 mouse;
    protected GameStateManager gameStateManager;
    private int score=0;
    private int coins=0;

    protected State(GameStateManager gameStateManager)
    {
        this.gameStateManager = gameStateManager;
        camera = new OrthographicCamera();
        mouse = new Vector3();
    }

    protected abstract void Input();
    public abstract void Update(float delta);
    public abstract void render(SpriteBatch spriteBatch);
    public abstract void dispose();
}
