package com.herb_mc.extra_enchants.mixin;

import com.herb_mc.extra_enchants.registry.ModEnchants;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.TridentEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.entity.LightningEntity;

@Mixin(TridentEntity.class)
public abstract class TridentEntityMixin {

    TridentEntity thisEntity = (TridentEntity) (Object) this;
    boolean isEvio = false;
    boolean init = false;

    @ModifyArg(
            method = "onEntityHit",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z"))
    private float f(float f) {
        return isEvio ? 1 : f;
    }

    @Inject(
            method = "tick",
            at = @At("HEAD")
    )
    public void tick(CallbackInfo info){
        if (thisEntity.getOwner() != null) if(!init && EnchantmentHelper.getEquipmentLevel(ModEnchants.EVIOCORE, (LivingEntity) thisEntity.getOwner()) > 0) isEvio = true;
    }

}
