package com.herb_mc.extra_enchants.mixin;

import com.herb_mc.extra_enchants.lib.EnchantmentMappings;
import com.herb_mc.extra_enchants.registry.ModEnchants;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Environment(EnvType.CLIENT)
@Mixin(AbstractClientPlayerEntity.class)
public class AbstractClientPlayerEntityMixin {

    @ModifyConstant(
            method = "getSpeed",
            constant = @Constant(floatValue = 20.0F)
    )
    private float FOVMod(float f) {
        if (((Object) this) instanceof AbstractClientPlayerEntity thisEntity) {
            int strongDrawLevel = EnchantmentHelper.getLevel(ModEnchants.SNIPER, thisEntity.getActiveItem());
            int nimbleLevel = EnchantmentHelper.getLevel(ModEnchants.NIMBLE, thisEntity.getActiveItem());
            if (strongDrawLevel > 0)
                f = f + f * EnchantmentMappings.sniperDrawMult.getFloat() * strongDrawLevel;
            else if (nimbleLevel > 0)
                f = f + f * nimbleLevel * EnchantmentMappings.nimbleDrawMult.getFloat();
        }
        return Math.max(f, 1F);
    }

}
