package com.geek.rpg.game.actions;

import com.badlogic.gdx.graphics.Texture;
import com.geek.rpg.game.Assets;
import com.geek.rpg.game.Unit;
import com.geek.rpg.game.effects.DefenceStanceEffect;

public class DefenceStanceAction extends BaseAction {
    public DefenceStanceAction() {
        super("DEFENCE", "btnDefence");
    }

    @Override
    public boolean action(Unit me) {
        DefenceStanceEffect dse = new DefenceStanceEffect();
        dse.start(me, 1);
        me.addEffect(dse);
        return true;
    }
}
