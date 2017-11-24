package com.geek.rpg.game.actions;

import com.badlogic.gdx.graphics.Texture;
import com.geek.rpg.game.Assets;
import com.geek.rpg.game.Calculator;
import com.geek.rpg.game.Unit;

/**
 * Created by FlameXander on 17.11.2017.
 */

public class FireballAction extends BaseAction {
    public FireballAction() {
        super("FIREBALL", "btnFireball");
    }

    @Override
    public boolean action(Unit me) {
        if (me.getTarget() == null) return false;
        if (me.isMyTeammate(me.getTarget())) return false;
        me.setAttackAction(1.0f);
        me.setCurrentAnimation(Unit.AnimationType.ATTACK);
        int dmg = Calculator.getFireBallDamage(me, me.getTarget());
        me.getTarget().changeHp(-dmg);
        me.getBattleScreen().getSpecialFXEmitter().setup(me, me.getTarget(), 1.0f, 2f, 2f, 0.0f, true);
        me.getBattleScreen().getSpecialFXEmitter().setup(me.getTarget(), me.getTarget(), 1.0f, 2f, 10f, 1.0f, true);
        return true;
    }
}
