package com.herb_mc.extra_enchants.mixin;

import com.herb_mc.extra_enchants.lib.LivingEntityMixinAccess;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(Entity.class)
public class EntityMixin {

    @Unique Entity thisEntity = (Entity) (Object) this;

    @ModifyArg(
            method = "onStruckByLightning",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/Entity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z"
            )
    )
    protected float damage(float f) {
        return (thisEntity instanceof LivingEntity && ((LivingEntityMixinAccess)thisEntity).exposedAccess() > 0) ? f * 1.5F : f;
    }

}
