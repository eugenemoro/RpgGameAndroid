package com.geek.rpg.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.awt.Font;

/**
 * Created by FlameXander on 13.11.2017.
 */

public class Assets {
    private static final Assets ourInstance = new Assets();

    public static Assets getInstance() {
        return ourInstance;
    }

    private AssetManager assetManager;
    private TextureAtlas textureAtlas;

    public TextureAtlas getAtlas() {
        return textureAtlas;
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    private Assets() {
        assetManager = new AssetManager();
    }

    public void loadAssets(ScreenManager.ScreenType type) {
        switch (type) {
            case MENU:
                assetManager.load("rpg.pack", TextureAtlas.class);
                assetManager.load("background.png", Texture.class);
                assetManager.load("menuBtn.png", Texture.class);
                assetManager.finishLoading();
                textureAtlas = assetManager.get("rpg.pack", TextureAtlas.class);
                break;
            case BATTLE:
                assetManager.load("rpg.pack", TextureAtlas.class);
                assetManager.load("background.png", Texture.class);
                assetManager.load("rpg.pack", TextureAtlas.class);
                assetManager.load("btnMeleeAttack.png", Texture.class);
                assetManager.load("btnHeal.png", Texture.class);
                assetManager.load("btnDefence.png", Texture.class);
                assetManager.load("actionPanel.png", Texture.class);
                assetManager.finishLoading();
                textureAtlas = assetManager.get("rpg.pack", TextureAtlas.class);
                break;
        }
    }


    public void clear() {
        assetManager.clear();
    }
}
