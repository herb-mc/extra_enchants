package com.herb_mc.extra_enchants.mixin;

import com.herb_mc.extra_enchants.registry.ModEnchants;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.ItemEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemEntity.class)
public class ItemEntityMixin {

    ItemEntity thisEntity = (ItemEntity) (Object) this;

    @Inject(
            method = "isFireImmune",
            at = @At("RETURN"),
            cancellable = true
    )
    public void setFireproof(CallbackInfoReturnable<Boolean> info) {
        if (EnchantmentHelper.getLevel(ModEnchants.FIREPROOF, thisEntity.getStack()) > 0)
            info.setReturnValue(true);
    }

}