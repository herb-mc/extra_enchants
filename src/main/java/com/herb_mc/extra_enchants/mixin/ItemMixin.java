package com.herb_mc.extra_enchants.mixin;

import net.minecraft.item.*;
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
        System.out.println(item.asItem().getName());
        if (item instanceof HorseArmorItem || item instanceof ElytraItem ||  item instanceof ShieldItem)
            info.setReturnValue(1);
    }

    @Inject(at = @At("HEAD"), method = "isEnchantable", cancellable = true)
    private void isEnchantable(ItemStack stack, CallbackInfoReturnable<Boolean> info) {
        if (item instanceof HorseArmorItem || item instanceof ElytraItem || item instanceof ShieldItem)
            info.setReturnValue(Boolean.TRUE);
    }

}
