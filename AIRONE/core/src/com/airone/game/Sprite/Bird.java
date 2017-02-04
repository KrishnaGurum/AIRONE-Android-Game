package com.airone.game.Sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Bird {
    private static final int GROUND_GRAV = -15;
    private static final int MOTION = 100;
    private Vector3 pos;
    private Vector3 vel;
    private Rectangle boundry;
    private GraphicMotion birdGraphicMotion;
    private Texture txtr;
    private Sound flap;

    public Bird(int x, int y){
        pos = new Vector3(x, y, 0);
        vel = new Vector3(0, 0, 0);
        txtr = new Texture("birdanimation.png");
        birdGraphicMotion = new GraphicMotion(new TextureRegion(txtr), 8, 0.5f);
        boundry = new Rectangle(x, y, txtr.getWidth() / 8, txtr.getHeight());
        flap = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));
    }

    public void update(float dt){
        birdGraphicMotion.update(dt);
        if(pos.y > 0)
            vel.add(0, GROUND_GRAV, 0);
        vel.scl(dt);
        pos.add(MOTION * dt, vel.y, 0);
        if(pos.y < 0)
            pos.y = 0;

        vel.scl(1/dt);
        boundry.setPosition(pos.x, pos.y);
    }

    public Vector3 getPosition() {
        return pos;
    }

    public TextureRegion getTexture() {
        return birdGraphicMotion.getFrame();
    }

    public void jump(){
        vel.y = 250;
        flap.play(0.5f);
    }

    public Rectangle getBounds(){
        return boundry;
    }

    public void dispose(){
        txtr.dispose();
        flap.dispose();
    }

}
