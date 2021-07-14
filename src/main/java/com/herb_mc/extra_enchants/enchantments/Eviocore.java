package com.herb_mc.extra_enchants.enchantments;

//shoutouts to fabsol

import com.herb_mc.extra_enchants.registry.ModEnchants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

import static com.herb_mc.extra_enchants.registry.ModEnchants.isEnchantType;

public class Eviocore extends Enchantment {

    public Eviocore(Rarity weight, EnchantmentTarget type, EquipmentSlot[] equipmentSlots) {
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
        return super.canAccept(other) && isEnchantType(other, ModEnchants.ENCHANTMENTS_CORE_COMPAT) && isEnchantType(other, ModEnchants.ENCHANTMENTS_ARMOR_OFFENSE_COMPAT);
    }

    @Override
    public boolean isCursed() {
        return true;
    }

    @Override
    public boolean isTreasure() {
        return true;
    }

}
