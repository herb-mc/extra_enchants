package com.herb_mc.extra_enchants.chocoasm;

import net.minecraft.item.HorseArmorItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.item.Item;

public class HorseArmor extends HorseArmorEnchantmentTargetMixin {
    @Override
    public boolean isAcceptableItem(Item item) {
        return item instanceof HorseArmorItem;
    }
}

@Mixin(EnchantmentTarget.class)
abstract class HorseArmorEnchantmentTargetMixin {
    @Shadow
    abstract boolean isAcceptableItem(Item item);
}