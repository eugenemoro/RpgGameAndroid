package com.geek.rpg.game;

import com.badlogic.gdx.Gdx;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by FlameXander on 20.11.2017.
 */

public class GameSession {
    private static final GameSession ourInstance = new GameSession();

    public static GameSession getInstance() {
        return ourInstance;
    }

    private Hero player;

    private GameSession() {
    }

    public Hero getPlayer() {
        return player;
    }

    public void startNewSession() {
        player = new Hero();
        makeStandartArmy();
    }

    public void saveSession() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(Gdx.files.local("mydata.sav").file()));
            oos.writeObject(player);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadSession() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Gdx.files.local("mydata.sav").file()));
            this.player = (Hero)ois.readObject();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void makeStandartArmy() {
        UnitFactory factory = new UnitFactory();
        player.setArmy(
                factory.createUnit(UnitFactory.UnitType.SKELETON, false, false, 1), factory.createUnit(UnitFactory.UnitType.KNIGHT, false, false, 1),
                factory.createUnit(UnitFactory.UnitType.MAGE, false, false, 1), factory.createUnit(UnitFactory.UnitType.SKELETON, false, false, 1),
                factory.createUnit(UnitFactory.UnitType.SKELETON, false, false, 1),  factory.createUnit(UnitFactory.UnitType.KNIGHT, false, false, 1)
        );
    }
}
