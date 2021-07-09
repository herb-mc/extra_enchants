package com.herbmc.extra_enchants.mixin;

import com.herbmc.extra_enchants.registry.ModEnchants;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.HorseEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.minecraft.enchantment.EnchantmentHelper.getEquipmentLevel;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {

    @Inject(method = "getDepthStrider", at = @At(value = "HEAD"), cancellable = true)
    private static void getDepthStrider(LivingEntity entity, CallbackInfoReturnable info) {
        if(entity instanceof HorseEntity && getEquipmentLevel(ModEnchants.SURFACE_SKIMMER, entity) > 0)
            info.setReturnValue(getEquipmentLevel(ModEnchants.SURFACE_SKIMMER, entity));
    }

}
