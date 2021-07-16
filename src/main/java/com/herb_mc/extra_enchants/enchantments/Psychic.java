package com.herb_mc.extra_enchants.enchantments;

import com.herb_mc.extra_enchants.registry.ModEnchants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

import static com.herb_mc.extra_enchants.registry.ModEnchants.isEnchantType;

public class Psychic extends Enchantment {

    public Psychic(Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes) {
        super(weight, type, slotTypes);
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
        return super.canAccept(other) && isEnchantType(other, ModEnchants.ENCHANTMENTS_ARMOR_VISION_COMPAT);
    }

}
