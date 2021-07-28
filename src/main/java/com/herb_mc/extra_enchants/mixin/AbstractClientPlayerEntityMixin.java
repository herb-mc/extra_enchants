package com.herb_mc.extra_enchants.mixin;

import com.herb_mc.extra_enchants.registry.ModEnchants;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Environment(EnvType.CLIENT)
@Mixin(AbstractClientPlayerEntity.class)
public class AbstractClientPlayerEntityMixin {

    @Unique AbstractClientPlayerEntity thisEntity = (AbstractClientPlayerEntity) (Object) this;

    @ModifyConstant(
            method = "getSpeed",
            constant = @Constant(floatValue = 20.0F)
    )
    private float FOVMod(float f) {
        return EnchantmentHelper.getLevel(ModEnchants.STRONG_DRAW, thisEntity.getActiveItem()) > 0 ? f + 10F * EnchantmentHelper.getLevel(ModEnchants.STRONG_DRAW, thisEntity.getActiveItem()) : f;
    }

}
