package com.herb_mc.extra_enchants.mixin;

import net.minecraft.entity.projectile.TridentEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(TridentEntity.class)
public abstract class TridentEntityMixin extends PersistentProjectileEntityMixin {

    @ModifyArg(
            method = "onEntityHit",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/Entity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z"
            )
    )
    private float thrownTridentDamageMod(float f) {
        if (neptune)
            f += 8;
        if (sharpshooter)
            f += 4;
        if (purity)
            f = 1;
        return f;
    }

}
