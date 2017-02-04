package com.airone.game.Sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Brick{
    public static final int brkWidth = 52;
    private static final int variation = 130;
    private static final int brkSpace = 100;
    private static final int leastOpening = 120;
    private Texture topbrick, bottombrick, coin;
    private Vector2 posbotbrick, postopbrick, posCoin;
    private Rectangle boundsTop, boundsBot, coinBounds;
    private Random rand;
    int ren;

    public Brick(float x){
        topbrick = new Texture("toptube.png");
        bottombrick = new Texture("bottomtube.png");
        coin = new Texture("coin.png");
        rand = new Random();
        ren = rand.nextInt(variation);
        postopbrick = new Vector2(x, ren + brkSpace + leastOpening);
        posbotbrick = new Vector2(x, postopbrick.y - brkSpace - bottombrick.getHeight());

        boundsTop = new Rectangle(postopbrick.x, postopbrick.y, topbrick.getWidth(), topbrick.getHeight());
        boundsBot = new Rectangle(posbotbrick.x, posbotbrick.y, bottombrick.getWidth(), bottombrick.getHeight());

        posCoin = new Vector2(x, ren+brkSpace+(leastOpening/2));
        coinBounds = new Rectangle(posCoin.x, posCoin.y, brkWidth, brkWidth);
    }


    public Texture getBottomBrick() {
        return bottombrick;
    }

    public Texture getTopBrick() {
        return topbrick;
    }

    public Texture getCoin() {
        return coin;
    }

    public Vector2 getPosCoin() {
        return posCoin;
    }

    public Vector2 getPosTopBrick() {
        return postopbrick;
    }

    public Vector2 getPosBotBrick() {return posbotbrick;}

    public void reposition(float x){
        postopbrick.set(x, rand.nextInt(variation) + brkSpace + leastOpening);
        posbotbrick.set(x, postopbrick.y - brkSpace - bottombrick.getHeight());
        if(getCoin()!=null && getPosCoin()!=null) {
            posCoin.set(x + 5, ren+brkSpace+(leastOpening/2));
        }
        boundsTop.setPosition(postopbrick.x, postopbrick.y);
        boundsBot.setPosition(posbotbrick.x, posbotbrick.y);
        if(getCoin()!=null && getPosCoin()!=null) {
            coinBounds.setPosition(posCoin.x, posCoin.y);
        }
    }

    public boolean collides(Rectangle player){
        return player.overlaps(boundsTop) || player.overlaps(boundsBot);
    }

    public boolean passed(Rectangle player){
        if(getCoin()!=null && getPosCoin()!=null) {
            return player.overlaps(coinBounds);
        }
        else
            return false;
    }

    public void coinDispose() {
        coin.dispose();
        posCoin = null;
        coinBounds = null;
    }
    public void dispose(){
        topbrick.dispose();
        bottombrick.dispose();
    }
}