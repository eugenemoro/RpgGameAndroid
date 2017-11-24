package com.geek.rpg.game.effects;


import com.geek.rpg.game.FlyingText;
import com.geek.rpg.game.InfoSystem;
import com.geek.rpg.game.Unit;

public class DefenceStanceEffect extends Effect {
    @Override
    public void start(Unit unit, int rounds) {
        super.start(unit, rounds);
        unit.getStats().setDefence(unit.getStats().getDefence() + 3);
        unit.getBattleScreen().getInfoSystem().addMessage("Shields UP!!! +3", unit, FlyingText.Colors.GREEN);
    }

    @Override
    public void end() {
        unit.getStats().setDefence(unit.getStats().getDefence() - 3);
        unit.getBattleScreen().getInfoSystem().addMessage("Shields DOWN!!! -3", unit, FlyingText.Colors.WHITE);
    }
}
