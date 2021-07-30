package com.herb_mc.extra_enchants.asm;

import net.minecraft.item.AxeItem;
import net.minecraft.item.SnowballItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.item.Item;

public class Snowball extends SnowballEnchantmentTargetMixin {

    @Override
    public boolean isAcceptableItem(Item item) {
        return item instanceof SnowballItem;
    }

}

@Mixin(EnchantmentTarget.class)
abstract class SnowballEnchantmentTargetMixin {

    @Shadow
    abstract boolean isAcceptableItem(Item item);

}