package com.herb_mc.extra_enchants.chocoasm;

import net.minecraft.item.AxeItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.item.Item;

public class Axe extends AxeEnchantmentTargetMixin {
    @Override
    public boolean isAcceptableItem(Item item) {
        return item instanceof AxeItem;
    }
}

@Mixin(EnchantmentTarget.class)
abstract class AxeEnchantmentTargetMixin {
    @Shadow
    abstract boolean isAcceptableItem(Item item);
}