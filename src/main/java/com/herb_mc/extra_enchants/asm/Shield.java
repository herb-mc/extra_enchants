package com.herb_mc.extra_enchants.asm;

import net.minecraft.item.ShieldItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.item.Item;

public class Shield extends ShieldEnchantmentTargetMixin {

    @Override
    public boolean isAcceptableItem(Item item) {
        return item instanceof ShieldItem;
    }

}

@Mixin(EnchantmentTarget.class)
abstract class ShieldEnchantmentTargetMixin {

    @Shadow
    abstract boolean isAcceptableItem(Item item);

}