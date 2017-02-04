package com.airone.game.States;

import com.airone.game.Main_Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class StateMenu extends State
{
    private Texture bg;
    private Texture plyButton;
    private Sprite backward;
    private Sprite coin;
    private Sprite ply;
    private Texture coins;
    private BitmapFont Score;
    private int cn=0;
    private int sc=0;
    private BitmapFont Coins;
    private Preferences preferences;

    public StateMenu(GameStateManager gsm) {
        super(gsm);

        preferences = Gdx.app.getPreferences("Values");
        //set score
        sc = preferences.getInteger("Score");
        cn = preferences.getInteger("Coins");
        preferences.flush();

        camera.setToOrtho(false, Main_Game.width / 2, Main_Game.width / 2);
        bg = new Texture("bg.png");
        backward = new Sprite(bg);
        backward.setBounds(0, 0, camera.viewportWidth, camera.viewportHeight);

        coins = new Texture("coin.png");
        coin = new Sprite(coins);
        coin.setBounds(5, 5, 20, 12);

        plyButton = new Texture("playbtn.png");
        ply = new Sprite(plyButton);
        ply.setBounds((camera.viewportWidth/2)-10, (camera.viewportHeight/2)-7, 20, 14);

        Score = new BitmapFont();
        Score.setColor(Color.WHITE);

        Coins = new BitmapFont();
        Coins.setColor(Color.WHITE);
    }
    //gameStateManager.set(new PlayS(gameStateManager, sc, cn));
    @Override
    protected void Input() {
        if(Gdx.input.isTouched())
            gameStateManager.set(new PlayS(gameStateManager, sc, cn));
    }

    @Override
    public void Update(float dt) {
        Input();
    }

    @Override
    public void render(SpriteBatch sb) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        backward.draw(sb);
        Score.draw(sb, ""+sc, camera.viewportWidth/2, (camera.viewportHeight/10)*9);
        coin.draw(sb);
        Coins.draw(sb, ""+cn, (camera.viewportWidth/8), (camera.viewportHeight/14));
        ply.draw(sb);
        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        Score.dispose();
        Coins.dispose();
        coins.dispose();
    }
}
