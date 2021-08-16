package com.herb_mc.extra_enchants.mixin;

import com.herb_mc.extra_enchants.lib.EnchantmentMappings;
import com.herb_mc.extra_enchants.registry.ModEnchants;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Environment(EnvType.CLIENT)
@Mixin(AbstractClientPlayerEntity.class)
public class AbstractClientPlayerEntityMixin {

    @ModifyVariable(
            method = "getSpeed",
            at = @At(
                    value = "STORE",
                    ordinal = 0
            ),
            index = 4
    )
    private float FOV(float f) {
        if (((Object) this) instanceof AbstractClientPlayerEntity thisEntity) {
            f *= 20.0F;
            float div = 20.0F;
            int strongDrawLevel = EnchantmentHelper.getLevel(ModEnchants.SNIPER, thisEntity.getActiveItem());
            int nimbleLevel = EnchantmentHelper.getLevel(ModEnchants.NIMBLE, thisEntity.getActiveItem());
            if (strongDrawLevel > 0)
                div = div + div * EnchantmentMappings.sniperDrawMult.getFloat() * strongDrawLevel;
            else if (nimbleLevel > 0)
                div = div + div * nimbleLevel * EnchantmentMappings.nimbleDrawMult.getFloat();
            if (div <= 0)
                div = 1F;
            f /= div;
        }
        return f;
    }

}
