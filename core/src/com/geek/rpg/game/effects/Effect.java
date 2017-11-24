package com.geek.rpg.game.effects;

import com.geek.rpg.game.Unit;

import java.io.Serializable;

public abstract class Effect implements Serializable {
    Unit unit;
    int rounds;

    public void start(Unit unit, int rounds) {
        this.unit = unit;
        this.rounds = rounds;
    }

    public void tick() {
        this.rounds--;
    }

    public boolean isEnded() {
        return rounds == 0;
    }

    public abstract void end();
}
