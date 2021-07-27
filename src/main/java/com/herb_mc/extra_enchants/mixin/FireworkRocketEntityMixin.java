package com.herb_mc.extra_enchants.mixin;

import com.herb_mc.extra_enchants.registry.ModEnchants;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FireworkRocketEntity.class)
public abstract class FireworkRocketEntityMixin implements FireworkRocketEntityInterface{

    @Shadow protected abstract boolean wasShotByEntity();

    @ModifyVariable(
            method = "tick",
            at = @At(
                    value = "INVOKE_ASSIGN",
                    target = "Lnet/minecraft/entity/LivingEntity;getRotationVector()Lnet/minecraft/util/math/Vec3d;"
            )
    )
    protected Vec3d turboBoost(Vec3d vec) {
        return (EnchantmentHelper.getEquipmentLevel(ModEnchants.TURBO, this.shooter()) > 0) ? vec.multiply(1.5) : vec;
    }

    @Inject(
            method = "tick",
            at = @At("TAIL")
    )
    protected void decreaseLifetime(CallbackInfo info) {
        if (this.shooter() != null && EnchantmentHelper.getEquipmentLevel(ModEnchants.TURBO, this.shooter()) > 0 && this.shooter().isFallFlying() && this.wasShotByEntity())
            this.life(this.getLife() + 2);
    }

}
