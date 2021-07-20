package com.herb_mc.extra_enchants.commons;

import com.herb_mc.extra_enchants.registry.ModEnchants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

public class ScalableEnchantBuilder extends Enchantment {

    Rarity rarity;
    int minPower;
    int minPowerDelta;
    int maxPower;
    int maxPowerDelta;
    int maxLevel;
    boolean isCursed;
    boolean isTreasure;
    Enchantment[] incompatibleEnchantments;
    boolean enabled;

    public ScalableEnchantBuilder(Rarity weight, EnchantmentTarget type, EquipmentSlot[] equipmentSlots, int minPower, int minPowerDelta, int maxPower, int maxPowerDelta, int maxLevel, boolean isCursed, boolean isTreasure, Enchantment[] incompatibleEnchantments) {
        super(weight, type, equipmentSlots);
        this.rarity = weight;
        this.minPower = minPower;
        this.minPowerDelta = minPowerDelta;
        this.maxPower = maxPower;
        this.maxPowerDelta = maxPowerDelta;
        this.maxLevel = maxLevel;
        this.isCursed = isCursed;
        this.isTreasure = isTreasure;
        this.incompatibleEnchantments = incompatibleEnchantments;
        this.enabled = true;
    }

    @Override
    public int getMinPower(int level) {
        return minPower + (level - 1) * minPowerDelta;
    }

    @Override
    public int getMaxPower(int level) {
        return maxPower + (level - 1) * maxPowerDelta;
    }

    @Override
    public int getMaxLevel() {
        return maxLevel;
    }

    @Override
    protected boolean canAccept(Enchantment other)
    {
        if (ModEnchants.isEnchantType(other, incompatibleEnchantments))
            return false;
        return super.canAccept(other);
    }

    @Override
    public boolean isCursed() {
        return isCursed;
    }

    @Override
    public boolean isTreasure() {
        return isTreasure;
    }

    @Override
    public Rarity getRarity() {
        return this.rarity;
    }

    @Override
    public boolean isAvailableForEnchantedBookOffer() {
        return enabled;
    }

    @Override
    public boolean isAvailableForRandomSelection() {
        return enabled;
    }

    public void setAttributes(boolean enabled, Rarity weight, int minPower, int minPowerDelta, int maxPower, int maxPowerDelta, int maxLevel, boolean isCursed, boolean isTreasure, Enchantment[] incompatibleEnchantments) {
        this.setEnabled(enabled);
        this.setRarity(weight);
        this.setMinPower(minPower);
        this.setMinPowerDelta(minPowerDelta);
        this.setMaxPower(maxPower);
        this.setMaxPowerDelta(maxPowerDelta);
        this.setMaxLevel(maxLevel);
        this.setCursed(isCursed);
        this.setTreasure(isTreasure);
        this.setIncompatibleEnchantments(incompatibleEnchantments);
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setRarity(Rarity weight) {
        this.rarity = weight;
    }

    public void setMinPower(int minPower) {
        this.minPower = minPower;
    }

    public void setMinPowerDelta(int minPowerDelta) {
        this.minPowerDelta = minPowerDelta;
    }

    public void setMaxPower(int maxPower) {
        this.maxPower = maxPower;
    }

    public void setMaxPowerDelta(int maxPowerDelta) {
        this.maxPowerDelta = maxPowerDelta;
    }

    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    public void setCursed(boolean cursed) {
        isCursed = cursed;
    }

    public void setTreasure(boolean treasure) {
        isTreasure = treasure;
    }

    public void setIncompatibleEnchantments(Enchantment[] incompatibleEnchantments) {
        this.incompatibleEnchantments = incompatibleEnchantments;
    }

}
