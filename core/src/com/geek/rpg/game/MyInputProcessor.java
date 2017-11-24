package com.geek.rpg.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by FlameXander on 13.11.2017.
 */

public class MyInputProcessor implements InputProcessor {
    private Vector2 cursor;
    private boolean isClicked;

    public MyInputProcessor() {
        this.cursor = new Vector2(0, 0);
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        cursor.set(screenX, screenY);
        ScreenManager.getInstance().getViewport().unproject(cursor);
        isClicked = true;
        return false;
    }

    public boolean isTouchedInArea(Rectangle rect) {
        if (!isTouched()) return false;
        return rect.contains(cursor);
    }

    public boolean isTouched() {
        return isClicked;
    }

    public float getX() {
        return cursor.x;
    }

    public float getY() {
        return cursor.y;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        isClicked = false;
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
