package com.herb_mc.extra_enchants.mixin;

import com.herb_mc.extra_enchants.registry.ModEnchants;
import net.minecraft.item.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({Item.class})
public class ItemMixin {

    @Unique

    @Inject(at = @At("HEAD"), method = "getEnchantability", cancellable = true)
    private void getEnchantability(CallbackInfoReturnable<Integer> info) {
        Item item = (Item) (Object) this;
        if ((item instanceof HorseArmorItem && ModEnchants.CAN_ENCHANT_HORSE_ARMOR.getBool()) || (item instanceof ElytraItem && ModEnchants.CAN_ENCHANT_ELYTRA.getBool()) ||  (item instanceof ShieldItem && ModEnchants.CAN_ENCHANT_SHIELD.getBool()) || (item instanceof SnowballItem&& ModEnchants.CAN_ENCHANT_SNOWBALL.getBool()))
            info.setReturnValue(1);
    }

    @Inject(at = @At("HEAD"), method = "isEnchantable", cancellable = true)
    private void isEnchantable(ItemStack stack, CallbackInfoReturnable<Boolean> info) {
        Item item = (Item) (Object) this;
        if ((item instanceof HorseArmorItem && ModEnchants.CAN_ENCHANT_HORSE_ARMOR.getBool()) || (item instanceof ElytraItem && ModEnchants.CAN_ENCHANT_ELYTRA.getBool()) ||  (item instanceof ShieldItem && ModEnchants.CAN_ENCHANT_SHIELD.getBool()) || (item instanceof SnowballItem && ModEnchants.CAN_ENCHANT_SNOWBALL.getBool()))
            info.setReturnValue(true);
    }

}
