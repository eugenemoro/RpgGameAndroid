package com.geek.rpg.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class FlyingText {
    public enum Colors {
        RED(1.0f, 0.0f, 0.0f), GREEN(0.0f, 1.0f, 0.0f), WHITE(1.0f, 1.0f, 1.0f), GOLD (0.5426f, 0.4574f, 0.0f);

        float r;
        float g;
        float b;

        Colors(float r, float g, float b) {
            this.r = r;
            this.g = g;
            this.b = b;
        }
    }

    private Vector2 position;
    private String text;
    private float time;
    private boolean active;
    private Colors color;

    public boolean isActive() {
        return active;
    }

    public FlyingText() {
        this.position = new Vector2(0, 0);
        this.text = "";
        this.time = 0.0f;
        this.color = Colors.WHITE;
    }

    public void setup(String text, float x, float y, Colors color) {
        this.active = true;
        this.position.set(x, y);
        this.text = text;
        this.color = color;
    }

    public void render(SpriteBatch batch, BitmapFont font) {
        font.setColor(color.r, color.g, color.b, 1.0f - time / 2.0f);
        font.draw(batch, text, position.x, position.y);
        font.setColor(1.0f, 1.0f, 1.0f, 1.0f);
    }

    public void update(float dt) {
        position.add(20 * dt, 60 * dt);
        time += dt;
        if (time > 2.0f) {
            time = 0.0f;
            active = false;
        }
    }
}
