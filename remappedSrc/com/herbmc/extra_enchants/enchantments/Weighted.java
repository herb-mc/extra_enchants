package com.herbmc.extra_enchants.enchantments;

import com.herbmc.extra_enchants.registry.ModEnchants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;

public class Weighted extends Enchantment {

    public Weighted(Rarity weight, EnchantmentTarget type, EquipmentSlot[] equipmentSlots) {
        super(weight, type, equipmentSlots);
    }

    @Override
    public int getMinPower(int level) {
        return 16 + (level - 1) * 10;
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    public int getMaxPower(int level) {
        return this.getMinPower(level) + 14;
    }

    @Override
    protected boolean canAccept(Enchantment other)
    {
        return super.canAccept(other) && other != ModEnchants.DEXTROUS && other != Enchantments.THORNS;
    }

}
