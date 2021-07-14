package com.herb_mc.extra_enchants.mixin;

import net.minecraft.enchantment.DamageEnchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TridentItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DamageEnchantment.class)
public class DamageEnchantmentMixin {

    @Inject(method = "isAcceptableItem", at = @At("HEAD"), cancellable = true)
    public void isAcceptable(ItemStack stack, CallbackInfoReturnable<Boolean> info) {
        if (stack.getItem() instanceof TridentItem) info.setReturnValue(true);
    }

}
