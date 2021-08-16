package com.herb_mc.extra_enchants.mixin;

import com.herb_mc.extra_enchants.lib.EnchantmentMappings;
import com.herb_mc.extra_enchants.lib.LivingEntityMixinAccess;
import com.herb_mc.extra_enchants.registry.ModEnchants;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityMixin {

    @Inject(
            method = "isInvulnerableTo",
            at = @At("HEAD"),
            cancellable = true
    )
    protected void magicGuard(DamageSource damageSource, CallbackInfoReturnable<Boolean> info) {
        if ((Object) this instanceof LivingEntity && EnchantmentHelper.getLevel(ModEnchants.MAGIC_GUARD, ((LivingEntity) (Object) this).getEquippedStack(EquipmentSlot.CHEST)) > 0 && damageSource.getAttacker() == null && damageSource != DamageSource.CRAMMING && damageSource != DamageSource.OUT_OF_WORLD && damageSource != DamageSource.DROWN && damageSource != DamageSource.STARVE && damageSource != DamageSource.FLY_INTO_WALL && damageSource != DamageSource.FALL)
            info.setReturnValue(true);
    }

    @ModifyArg(
            method = "onStruckByLightning",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/Entity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z"
            )
    )
    protected float damage(float f) {
        Entity thisEntity = (Entity) (Object) this;
        return (thisEntity instanceof LivingEntity && ((LivingEntityMixinAccess)thisEntity).exposedAccess() > 0) ? f * EnchantmentMappings.exposedLightningDamageMult.getFloat() : f;
    }

}
