package com.herb_mc.extra_enchants.chocoasm;

import net.minecraft.item.AxeItem;
import net.minecraft.item.SwordItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.item.Item;

public class Weapons extends WeaponsEnchantmentTargetMixin {

    @Override
    public boolean isAcceptableItem(Item item) {
        return item instanceof AxeItem || item instanceof SwordItem;
    }

}

@Mixin(EnchantmentTarget.class)
abstract class WeaponsEnchantmentTargetMixin {

    @Shadow
    abstract boolean isAcceptableItem(Item item);

}