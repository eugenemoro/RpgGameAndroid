package com.geek.rpg.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.geek.rpg.game.actions.BaseAction;
import com.geek.rpg.game.effects.Effect;
import com.geek.rpg.game.screens.BattleScreen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Unit implements Serializable {
    public enum AnimationType {
        IDLE(0), ATTACK(1);

        int number;

        AnimationType(int number) {
            this.number = number;
        }
    }

    transient private BattleScreen battleScreen;
    private Hero hero;
    transient private Unit target;
    transient private TextureRegion texture;
    transient private TextureRegion textureHpBar;
    private int hp;
    private int maxHp;
    private int level;
    private Rectangle rect;
    private Autopilot autopilot;
    private Vector2 position;
    private boolean flip;
    private float attackAction;
    private float takeDamageAction;
    private Stats stats;
    transient private Group actionPanel;
    private List<Effect> effects;
    transient private List<BaseAction> actions;
    private UnitFactory.UnitType type;

    transient private TextureRegion[][] frames;
    private AnimationType currentAnimation;
    private float animationTime;
    private float animationSpeed;
    private int maxFrame;
    private int maxAnimationType;
    private int animationFrame;

    public static final int WIDTH = 90;
    public static final int HEIGHT = 150;

    public void setCurrentAnimation(AnimationType currentAnimation) {
        this.currentAnimation = currentAnimation;
    }

    public Autopilot getAutopilot() {
        return autopilot;
    }

    public void setActionPanel(Group actionPanel) {
        this.actionPanel = actionPanel;
    }

    public void setAutopilot(Autopilot autopilot) {
        this.autopilot = autopilot;
    }

    public boolean isAI() {
        return autopilot != null;
    }

    public boolean isMyTeammate(Unit another) {
        return this.hero == another.hero;
    }

    public Stats getStats() {
        return stats;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public Group getActionPanel() {
        return actionPanel;
    }

    public void setAttackAction(float attackAction) {
        this.attackAction = attackAction;
    }

    public Unit getTarget() {
        return target;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public BattleScreen getBattleScreen() {
        return battleScreen;
    }

    public List<BaseAction> getActions() {
        return actions;
    }

    public int getLevel() {
        return level;
    }

    public void setActions(List<BaseAction> actions) {
        this.actions = actions;
    }

    public void setFlip(boolean flip) {
        this.flip = flip;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Rectangle getRect() {
        return rect;
    }

    public void setTarget(Unit target) {
        this.target = target;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public TextureRegion getTexture() {
        return texture;
    }

    public UnitFactory.UnitType getType() {
        return type;
    }

    public Unit(UnitFactory.UnitType type, TextureRegion texture, Stats stats) {
        this.texture = texture;
        this.type = type;
        this.effects = new ArrayList<Effect>();
        this.position = new Vector2(0, 0);
        this.actions = new ArrayList<BaseAction>();
        this.stats = stats;
        this.textureHpBar = Assets.getInstance().getAtlas().findRegion("hpBar");
        this.animationSpeed = 0.2f;
        this.frames = this.texture.split(WIDTH, HEIGHT);
        this.maxFrame = this.frames[0].length;
        this.maxAnimationType = this.frames.length - 1;
        this.currentAnimation = AnimationType.IDLE;
    }

    public void reload(TextureRegion texture, List<BaseAction> actions) {
        this.textureHpBar = Assets.getInstance().getAtlas().findRegion("hpBar");
        this.texture = texture;
        this.frames = this.texture.split(WIDTH, HEIGHT);
        this.actions = actions;
        this.effects.clear();
        this.animationSpeed = 0.2f;
        this.frames = this.texture.split(WIDTH, HEIGHT);
        this.maxFrame = this.frames[0].length;
        this.maxAnimationType = this.frames.length - 1;
        this.currentAnimation = AnimationType.IDLE;
    }

    public void recalculateSecondaryStats() {
        this.maxHp = 5 * stats.getEndurance();
        this.hp = this.maxHp;
    }

    public void setToMap(BattleScreen battleScreen, int cellX, int cellY) {
        this.battleScreen = battleScreen;
        this.position.set(battleScreen.getStayPoints()[cellX][cellY]);
        this.rect = new Rectangle(position.x, position.y, texture.getRegionWidth(), texture.getRegionHeight());
    }

    public void evade() {
        battleScreen.getInfoSystem().addMessage("MISS", this, FlyingText.Colors.WHITE);
    }

    public void render(SpriteBatch batch) {
        if (takeDamageAction > 0) {
            batch.setColor(1f, 1f - takeDamageAction, 1f - takeDamageAction, 1f);
        }
        float dx = (50f * (float) Math.sin((1f - attackAction) * 3.14f));
        if (flip) dx *= -1;
        float ang = 0;
        if (!isAlive()) ang = 90;
        int n = currentAnimation.number;
        if (n > maxAnimationType) {
            n = 0;
        }
        if (flip) {
            frames[n][animationFrame].flip(true, false);
        }
        batch.draw(frames[n][animationFrame], position.x + dx, position.y, 0, 0, WIDTH, HEIGHT, 1, 1, ang);
        if (flip) {
            frames[n][animationFrame].flip(true, false);
        }
        batch.setColor(1f, 1f, 1f, 1f);
    }

    public void renderInfo(SpriteBatch batch, BitmapFont font) {
        batch.setColor(0.5f, 0, 0, 1);
        batch.draw(textureHpBar, position.x, position.y + 130);
        batch.setColor(0, 1, 0, 1);
        batch.draw(textureHpBar, position.x, position.y + 130, (int) ((float) hp / (float) maxHp * textureHpBar.getRegionWidth()), 20);
        batch.setColor(1, 1, 1, 1);
        font.draw(batch, String.valueOf(hp), position.x, position.y + 149, 90, 1, false);
    }

    public void update(float dt) {
        animationTime += dt;
        animationFrame = (int) (animationTime / animationSpeed);
        animationFrame = animationFrame % maxFrame;
        if (takeDamageAction > 0) {
            takeDamageAction -= dt;
            if(takeDamageAction <= 0) {
                currentAnimation = AnimationType.IDLE;
            }
        }
        if (attackAction > 0) {
            attackAction -= dt;
            if(attackAction <= 0) {
                currentAnimation = AnimationType.IDLE;
            }
        }
    }

    public void getTurn() {
        target = null;
        for (int i = effects.size() - 1; i >= 0; i--) {
            effects.get(i).tick();
            if (effects.get(i).isEnded()) {
                effects.get(i).end();
                effects.remove(i);
            }
        }
    }

    public void changeHp(int value) {
        int prevHp = hp;
        hp += value;
        if (hp > maxHp) {
            hp = maxHp;
        }
        if (hp < 0) {
            hp = 0;
            if (isAI()) {
                int amount = level;
                GameSession.getInstance().getPlayer().addGold(amount);
                battleScreen.getInfoSystem().addMessage("Gold +" + amount, this, FlyingText.Colors.GOLD);
            }
        }
        if (value > 0) {
            battleScreen.getInfoSystem().addMessage("HP +" + (hp - prevHp), this, FlyingText.Colors.GREEN);
        } else if (value < 0) {
            takeDamageAction = 1.0f;
            battleScreen.getInfoSystem().addMessage("HP " + -(prevHp - hp), this, FlyingText.Colors.RED);
        } else {
            battleScreen.getInfoSystem().addMessage("0", this, FlyingText.Colors.WHITE);
        }
    }

    public void addEffect(Effect effect) {
        effects.add(effect);
    }

    public void setLevelTo(int newLevel) {
        this.level = newLevel;
        this.stats.recalculate(newLevel);
        this.recalculateSecondaryStats();
    }
}
