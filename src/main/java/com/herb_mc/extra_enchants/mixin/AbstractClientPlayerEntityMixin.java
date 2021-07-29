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
        int strongDrawLevel = EnchantmentHelper.getLevel(ModEnchants.STRONG_DRAW, thisEntity.getActiveItem());
        int nimbleLevel = EnchantmentHelper.getLevel(ModEnchants.NIMBLE, thisEntity.getActiveItem());
        return strongDrawLevel > 0 ? f + 10F * strongDrawLevel : nimbleLevel > 0 ? nimbleLevel <= 9 ? f - nimbleLevel * 2F : 1F : f;
    }

}
