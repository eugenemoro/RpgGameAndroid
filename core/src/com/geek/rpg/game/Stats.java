package com.geek.rpg.game;

import java.io.Serializable;

/**
 * Created by FlameXander on 13.11.2017.
 */

public class Stats implements Cloneable, Serializable {
    private int baseStrength;
    private int baseDexterity;
    private int baseEndurance;
    private int baseDefence;
    private int baseSpellPower;

    private int strength;
    private int dexterity;
    private int endurance;
    private int defence;
    private int spellpower;

    private float strengthPerLevel;
    private float dexterityPerLevel;
    private float endurancePerLevel;
    private float defencePerLevel;
    private float spellPowerPerLevel;

    @Override
    protected Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getBaseStrength() {
        return baseStrength;
    }

    public void setBaseStrength(int baseStrength) {
        this.baseStrength = baseStrength;
    }

    public int getBaseDexterity() {
        return baseDexterity;
    }

    public void setBaseDexterity(int baseDexterity) {
        this.baseDexterity = baseDexterity;
    }

    public int getBaseEndurance() {
        return baseEndurance;
    }

    public void setBaseEndurance(int baseEndurance) {
        this.baseEndurance = baseEndurance;
    }

    public int getBaseDefence() {
        return baseDefence;
    }

    public void setBaseDefence(int baseDefence) {
        this.baseDefence = baseDefence;
    }

    public int getBaseSpellPower() {
        return baseSpellPower;
    }

    public void setBaseSpellPower(int baseSpellPower) {
        this.baseSpellPower = baseSpellPower;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getEndurance() {
        return endurance;
    }

    public void setEndurance(int endurance) {
        this.endurance = endurance;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getSpellpower() {
        return spellpower;
    }

    public void setSpellpower(int spellpower) {
        this.spellpower = spellpower;
    }

    public float getStrengthPerLevel() {
        return strengthPerLevel;
    }

    public void setStrengthPerLevel(float strengthPerLevel) {
        this.strengthPerLevel = strengthPerLevel;
    }

    public float getDexterityPerLevel() {
        return dexterityPerLevel;
    }

    public void setDexterityPerLevel(float dexterityPerLevel) {
        this.dexterityPerLevel = dexterityPerLevel;
    }

    public float getEndurancePerLevel() {
        return endurancePerLevel;
    }

    public void setEndurancePerLevel(float endurancePerLevel) {
        this.endurancePerLevel = endurancePerLevel;
    }

    public float getDefencePerLevel() {
        return defencePerLevel;
    }

    public void setDefencePerLevel(float defencePerLevel) {
        this.defencePerLevel = defencePerLevel;
    }

    public float getSpellPowerPerLevel() {
        return spellPowerPerLevel;
    }

    public void setSpellPowerPerLevel(float spellPowerPerLevel) {
        this.spellPowerPerLevel = spellPowerPerLevel;
    }

    public Stats(int level, int baseStrength, int baseDexterity, int baseEndurance, int baseDefence, int baseSpellPower, float strengthPerLevel, float dexterityPerLevel, float endurancePerLevel, float defencePerLevel, float spellPowerPerLevel) {
        this.baseStrength = baseStrength;
        this.baseDexterity = baseDexterity;
        this.baseEndurance = baseEndurance;
        this.baseDefence = baseDefence;
        this.baseSpellPower = baseSpellPower;
        this.strengthPerLevel = strengthPerLevel;
        this.dexterityPerLevel = dexterityPerLevel;
        this.endurancePerLevel = endurancePerLevel;
        this.defencePerLevel = defencePerLevel;
        this.spellPowerPerLevel = spellPowerPerLevel;
        this.recalculate(level);
    }

    public void recalculate(int level) {
        strength = baseStrength + (int) ((level - 1) * strengthPerLevel);
        dexterity = baseDexterity + (int) ((level - 1) * dexterityPerLevel);
        endurance = baseEndurance + (int) ((level - 1) * endurancePerLevel);
        defence = baseDefence + (int) ((level - 1) * defencePerLevel);
        spellpower = baseSpellPower + (int) ((level - 1) * spellPowerPerLevel);
    }
}