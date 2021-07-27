package com.herb_mc.extra_enchants.mixin;

import net.minecraft.item.ElytraItem;
import net.minecraft.item.HorseArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({Item.class})
public class ItemMixin {

    @Unique
    Item item = (Item) (Object) this;

    @Inject(at = @At("HEAD"), method = "getEnchantability", cancellable = true)
    private void getEnchantability(CallbackInfoReturnable<Integer> info) {
        if (item instanceof HorseArmorItem || item instanceof ElytraItem)
            info.setReturnValue(1);
    }

    @Inject(at = @At("HEAD"), method = "isEnchantable", cancellable = true)
    private void isEnchantable(ItemStack stack, CallbackInfoReturnable<Boolean> info) {
        if (item instanceof HorseArmorItem || item instanceof ElytraItem)
            info.setReturnValue(Boolean.TRUE);
    }

}
