package com.geek.rpg.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class SpecialFX {
    private Vector2 positionFrom;
    private Vector2 positionTo;
    private float scaleFrom;
    private float scaleTo;
    private float time;
    private float frameSpeed;
    private float maxTime;
    private int maxFrames;
    private TextureRegion texture;
    private TextureRegion[] regions;

    public boolean isActive() {
        return time > -100.0f;
    }

    public SpecialFX() {
        positionFrom = new Vector2(0, 0);
        positionTo = new Vector2(0, 0);
        maxFrames = 64;
        frameSpeed = 0.01f;
        time = -100.0f;
        maxTime = 0;
        scaleFrom = 1.0f;
        scaleTo = 1.0f;
        texture = Assets.getInstance().getAtlas().findRegion("explosion64");
        TextureRegion[][] tr = new TextureRegion(texture).split(64, 64);
        regions = new TextureRegion[maxFrames];
        int counter = 0;
        for (int i = 0; i < tr.length; i++) {
            for (int j = 0; j < tr[0].length; j++) {
                regions[counter] = tr[i][j];
                counter++;
            }
        }
    }

    public void setup(float xFrom, float yFrom, float xTo, float yTo, float maxTime, float scaleFrom, float scaleTo, float delay, boolean oneCycle) {
        this.positionFrom.set(xFrom, yFrom);
        this.positionTo.set(xTo, yTo);
        this.maxTime = maxTime;
        if (oneCycle) {
            frameSpeed = maxTime / maxFrames;
        } else {
            frameSpeed = 0.01f;
        }
        this.scaleFrom = scaleFrom;
        this.scaleTo = scaleTo;
        this.time = -delay;
    }

    public void render(SpriteBatch batch) {
        if (isActive() && time > 0.0f) {
            int currentFrame = (int) (time / frameSpeed) % maxFrames;
            float x = positionFrom.x + (time / maxTime) * (positionTo.x - positionFrom.x);
            float y = positionFrom.y + (time / maxTime) * (positionTo.y - positionFrom.y);
            float currentScale = scaleFrom + (time / maxTime) * (scaleTo - scaleFrom);
            batch.draw(regions[currentFrame], x - 32, y - 32, 32, 32, 64, 64, currentScale, currentScale, 0);
        }
    }

    public void update(float dt) {
        if (isActive()) {
            time += dt;
            if (time > maxTime) {
                time = -100.0f;
            }
        }
    }
}
