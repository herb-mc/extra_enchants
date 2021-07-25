package com.herb_mc.extra_enchants.asm;

import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

public class Elytra extends ElytraEnchantmentTargetMixin{

    @Override
    public boolean isAcceptableItem(Item item) {
        return item instanceof ElytraItem;
    }

}


@Mixin(EnchantmentTarget.class)
abstract class ElytraEnchantmentTargetMixin {

    @Shadow
    abstract boolean isAcceptableItem(Item item);

}