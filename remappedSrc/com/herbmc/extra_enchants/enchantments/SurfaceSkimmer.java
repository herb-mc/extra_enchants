package com.herbmc.extra_enchants.enchantments;

import com.herbmc.extra_enchants.registry.ModEnchants;
import net.minecraft.enchantment.*;
import net.minecraft.entity.EquipmentSlot;

public class SurfaceSkimmer extends Enchantment {

    public SurfaceSkimmer(Rarity weight, EnchantmentTarget type, EquipmentSlot[] equipmentSlots) {
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
        return super.canAccept(other) && other != ModEnchants.SWIFTNESS && other != ModEnchants.BOUNDING;
    }

}
