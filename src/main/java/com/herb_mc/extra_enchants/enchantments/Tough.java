package com.herb_mc.extra_enchants.enchantments;

import com.herb_mc.extra_enchants.registry.ModEnchants;
import net.minecraft.enchantment.*;
import net.minecraft.entity.EquipmentSlot;

public class Tough extends Enchantment {

    public Tough(Rarity weight, EnchantmentTarget type, EquipmentSlot[] equipmentSlots) {
        super(weight, type, equipmentSlots);
    }

    @Override
    public int getMinPower(int level) {
        return 2 + (level - 1) * 7;
    }

    @Override
    public int getMaxLevel() {
        return 4;
    }

    @Override
    public int getMaxPower(int level) {
        return this.getMinPower(level) + 15;
    }

    @Override
    protected boolean canAccept(Enchantment other)
    {
        return super.canAccept(other) && !(other instanceof ProtectionEnchantment);
    }

}
