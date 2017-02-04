package com.airone.game.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class GraphicMotion {
    private Array<TextureRegion> frames;
    private float maximumFrmTime;
    private float crntFrmTime;
    private int frmCount;
    private int frm;

    public GraphicMotion(TextureRegion rgn, int frmCount, float cycleTime) {
        frames = new Array<TextureRegion>();
        int frmWidth = (rgn.getRegionWidth() / frmCount);
        for (int i = 0; i < frmCount; i++) {
            frames.add(new TextureRegion(rgn, i * frmWidth, 0, frmWidth, rgn.getRegionHeight()));
        }
        this.frmCount = frmCount;
        maximumFrmTime = cycleTime / frmCount;
        frm = 0;
    }

    public void update(float dt) {
        crntFrmTime += dt;
        if (crntFrmTime > maximumFrmTime) {
            frm++;
            crntFrmTime = 0;
        }
        if (frm >= frmCount)
            frm = 0;
    }

    public TextureRegion getFrame() {
        return frames.get(frm);
    }
}
