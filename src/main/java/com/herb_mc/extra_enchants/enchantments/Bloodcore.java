package com.herb_mc.extra_enchants.enchantments;

//shoutouts to fabsol

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.ProtectionEnchantment;
import net.minecraft.entity.EquipmentSlot;

public class Bloodcore extends Enchantment {

    public Bloodcore(Rarity weight, EnchantmentTarget type, EquipmentSlot[] equipmentSlots) {
        super(weight, type, equipmentSlots);
    }

    @Override
    public int getMinPower(int level) {
        return 20;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public int getMaxPower(int level) {
        return 50;
    }

    @Override
    protected boolean canAccept(Enchantment other)
    {
        return super.canAccept(other) && !(other instanceof ProtectionEnchantment);
    }

    @Override
    public boolean isCursed() {
        return true;
    }

}
