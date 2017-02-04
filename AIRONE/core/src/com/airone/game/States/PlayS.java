package com.airone.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.airone.game.Main_Game;
import com.airone.game.Sprite.Bird;
import com.airone.game.Sprite.Brick;

public class PlayS extends State{
    private static int brkSpace = 200;
    private static final int brkCount = 25;
    private static final int baseOffset = -50;

    private Bird bird;
    private Texture bg;
    private Texture base;
    private Vector2 basePos1, basePos2;
    private int sc=0, cn=0;

    private BitmapFont score;

    private static int currentScore;

    private Array<Brick> bricks;

    public PlayS(GameStateManager gameStateManager, int sc, int cn) {
        super(gameStateManager);
        bird = new Bird(50, 300);
        this.sc =sc;
        this.cn=cn;
        camera.setToOrtho(false, Main_Game.width / 2, Main_Game.height / 2);
        bg = new Texture("bg.png");
        base = new Texture("ground.png");
        basePos1 = new Vector2(camera.position.x - camera.viewportWidth / 2, baseOffset);
        basePos2 = new Vector2((camera.position.x - camera.viewportWidth / 2) + base.getWidth(), baseOffset);

        score = new BitmapFont();
        score.setColor(Color.WHITE);

        bricks = new Array<Brick>();

        for(int i = 1; i <= brkCount; i++){
            bricks.add(new Brick(i * (brkSpace + Brick.brkWidth)));
        }
        currentScore = 0;
    }
    @Override
    protected void Input() {
        if(Gdx.input.justTouched())
            bird.jump();
    }

    @Override
    public void Update(float dt) {
        Input();
        updateGround();
        bird.update(dt);
        camera.position.x = bird.getPosition().x + 80;

        for(int i = 0; i < bricks.size; i++){
            Brick brick = bricks.get(i);

            if(camera.position.x - (camera.viewportWidth / 2) > brick.getPosTopBrick().x + brick.getTopBrick().getWidth()){
                brick.reposition(brick.getPosTopBrick().x  + ((Brick.brkWidth + brkSpace) * brkCount));
                if(brkSpace>120) {
                    brkSpace -= 10;
                }
            }
                if (brick.passed(bird.getBounds())) {
                    passedThrough();
                    brick.coinDispose();
                }

            if(brick.collides(bird.getBounds()))
                gameStateManager.set(new GameOverS(gameStateManager, cn, currentScore, sc));
        }

        if(bird.getPosition().y <= base.getHeight() + baseOffset)
            gameStateManager.set(new StateMenu(gameStateManager));
        camera.update();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(bg, camera.position.x - (camera.viewportWidth / 2), 0, camera.viewportWidth, camera.viewportHeight);
        sb.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);
        for(Brick brick : bricks) {
            sb.draw(brick.getTopBrick(), brick.getPosTopBrick().x, brick.getPosTopBrick().y);
            if(brick.getCoin()!=null && brick.getPosCoin()!=null) {
                sb.draw(brick.getCoin(), brick.getPosCoin().x, brick.getPosCoin().y);
            }
            sb.draw(brick.getBottomBrick(), brick.getPosBotBrick().x, brick.getPosBotBrick().y);
        }

        sb.draw(base, basePos1.x, basePos2.y);
        sb.draw(base, basePos2.x, basePos2.y);
        score.draw(sb, ""+currentScore, camera.position.x, (camera.viewportHeight/10)*9);
        sb.end();
    }

    public void passedThrough()
    {
        cn+=1;
        currentScore++;
    }

    @Override
    public void dispose() {
        bg.dispose();
        bird.dispose();
        base.dispose();
        for(Brick brick : bricks)
            brick.dispose();
    }

    private void updateGround(){
        if(camera.position.x - (camera.viewportWidth / 2) > basePos1.x + base.getWidth())
            basePos1.add(base.getWidth() * 2, 0);
        if(camera.position.x - (camera.viewportWidth / 2) > basePos2.x + base.getWidth())
            basePos2.add(base.getWidth() * 2, 0);
    }
}
