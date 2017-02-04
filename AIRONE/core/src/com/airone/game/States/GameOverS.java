package com.airone.game.States;

import com.airone.game.Main_Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameOverS extends State
{
    private Texture background;
    private Sprite back;
    private Texture tPost;
    private Sprite post;
    private BitmapFont Score;
    private BitmapFont HScore;
    private BitmapFont Coins;
    Preferences preferences;
    private int sc;
    private int hs;
    private int cn;

    protected GameOverS(GameStateManager gameStateManager, int coins, int score, int fsc) {
        super(gameStateManager);
        camera.setToOrtho(false, Main_Game.width / 2, Main_Game.width / 2);
        preferences = Gdx.app.getPreferences("Values");



        background = new Texture("bg.png");
        tPost = new Texture("post.png");
        back = new Sprite(background);
        post = new Sprite(tPost);
        sc = score;
        hs=fsc;
        cn=coins;
        back.setBounds(0, 0, camera.viewportWidth, camera.viewportHeight);
        post.setBounds(camera.viewportWidth/14, camera.viewportWidth/15, 200, 175);
        if(fsc<score)
        {
            //set new score
            writeNewScore(score, coins);
            hs=score;
        }else{
            preferences.flush();
        }
        Score = new BitmapFont();
        Score.setColor(Color.WHITE);
        Coins = new BitmapFont();
        Coins.setColor(Color.WHITE);
        HScore = new BitmapFont();
        HScore.setColor(Color.WHITE);
    }

    private void writeNewScore(int score, int coins) {
        //Write score
        preferences.putInteger("Score",score);
        preferences.flush();
    }


    @Override
    protected void Input() {
        if(Gdx.input.justTouched()){
            gameStateManager.set(new StateMenu(gameStateManager));
        }
    }

    @Override
    public void Update(float delta) {
        Input();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        back.draw(spriteBatch);
        post.draw(spriteBatch);
        Score.draw(spriteBatch, ""+sc,camera.viewportWidth/2, camera.viewportHeight/3+78);
        HScore.draw(spriteBatch, ""+hs,camera.viewportWidth/2, camera.viewportHeight/3+52);
        Coins.draw(spriteBatch, ""+cn,camera.viewportWidth/2, camera.viewportHeight/3+23);

        spriteBatch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        tPost.dispose();
    }
}
