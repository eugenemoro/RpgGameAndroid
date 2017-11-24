package com.geek.rpg.game;

import com.badlogic.gdx.graphics.Texture;
import com.geek.rpg.game.actions.BaseAction;
import com.geek.rpg.game.actions.DefenceStanceAction;
import com.geek.rpg.game.actions.FireballAction;
import com.geek.rpg.game.actions.MeleeAttackAction;
import com.geek.rpg.game.actions.RestAction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by FlameXander on 13.11.2017.
 */

public class UnitFactory {
    public enum UnitType {
        KNIGHT, SKELETON, MAGE;
    }

    private Map<UnitType, Unit> data;
    private List<Autopilot> aiBank;
    private List<BaseAction> actions;

    public List<BaseAction> getActions() {
        return actions;
    }

    public UnitFactory() {
        this.createActions();
        this.aiBank = new ArrayList<Autopilot>();
        this.aiBank.add(new Autopilot() {
            @Override
            public boolean turn(Unit me) {
                if (!me.getBattleScreen().canIMakeTurn()) {
                    return false;
                }
                Unit target = null;
                int counter = 0;
                do {
                    target = me.getBattleScreen().getUnits().get((int) (Math.random() * me.getBattleScreen().getUnits().size()));
                    counter++;
                    if (counter > 1000) break;
                } while (me.isMyTeammate(target) || !target.isAlive());
                me.setTarget(target);
                me.getActions().get(0).action(me);
                return true;
            }
        });
        this.createUnitPatterns();
    }

    public void createActions() {
        this.actions = new ArrayList<BaseAction>();
        this.actions.add(new MeleeAttackAction());
        this.actions.add(new DefenceStanceAction());
        this.actions.add(new RestAction());
        this.actions.add(new FireballAction());
    }

    public void createUnitPatterns() {
        data = new HashMap<UnitType, Unit>();
        Stats knightStats = new Stats(1, 20, 10, 30, 2, 5, 5f, 1f, 5f, 1f, 5f);
        Unit knight = new Unit(UnitType.KNIGHT, Assets.getInstance().getAtlas().findRegion("knightAnim"), knightStats);
        knight.getActions().add(actions.get(0));
        knight.getActions().add(actions.get(1));
        data.put(UnitType.KNIGHT, knight);

        Stats skeletonStats = new Stats(1, 10, 20, 15, 1, 0, 2.5f, 5.0f, 5.0f, 1f, 0.5f);
        Unit skeleton = new Unit(UnitType.SKELETON, Assets.getInstance().getAtlas().findRegion("skeleton"), skeletonStats);
        skeleton.getActions().add(actions.get(0));
        skeleton.getActions().add(actions.get(2));
        data.put(UnitType.SKELETON, skeleton);

        Stats mageStats = new Stats(1, 5, 10, 10, 0, 15, 0.5f, 0.5f, 2f, 0.2f, 5.0f);
        Unit mage = new Unit(UnitType.MAGE, Assets.getInstance().getAtlas().findRegion("mage"), mageStats);
        mage.getActions().add(actions.get(3));
        mage.getActions().add(actions.get(2));
        data.put(UnitType.MAGE, mage);

        //todo add archer
    }


    public void reloadUnit(Unit unit) {
        Unit unitPattern = data.get(unit.getType());
        unit.reload(unitPattern.getTexture(), unitPattern.getActions());
        if (unit.isAI()) {
            unit.setAutopilot(aiBank.get(0));
        }
    }

    public Unit createUnit(UnitType unitType, boolean flip, boolean ai, int level) {
        Unit unitPattern = data.get(unitType);
        Unit unit = new Unit(unitType, unitPattern.getTexture(), (Stats) unitPattern.getStats().clone());
        unit.setLevelTo(level);
        unit.setActions(unitPattern.getActions());
        if (ai) {
            unit.setAutopilot(aiBank.get(0));
        }
        unit.setFlip(flip);
        return unit;
    }
}
