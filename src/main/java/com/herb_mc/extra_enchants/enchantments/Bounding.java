package com.herb_mc.extra_enchants.enchantments;

import com.herb_mc.extra_enchants.registry.ModEnchants;
import net.minecraft.enchantment.*;
import net.minecraft.entity.EquipmentSlot;

import static com.herb_mc.extra_enchants.registry.ModEnchants.isEnchantType;

public class Bounding extends Enchantment {

    public Bounding(Rarity weight, EnchantmentTarget type, EquipmentSlot[] equipmentSlots) {
        super(weight, type, equipmentSlots);
    }

    @Override
    public int getMinPower(int level) {
        return 1 + (level - 1) * 10;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public int getMaxPower(int level) {
        return this.getMinPower(level) + 15;
    }

    @Override
    protected boolean canAccept(Enchantment other)
    {
        return super.canAccept(other) && isEnchantType(other, ModEnchants.ENCHANTMENTS_HORSE_ARMOR_MOBILITY_COMPAT);
    }

}
