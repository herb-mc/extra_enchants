package com.herb_mc.extra_enchants.mixin;

import com.herb_mc.extra_enchants.registry.ModEnchants;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.particle.ParticleTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import java.util.Random;

@Mixin(PersistentProjectileEntity.class)
public abstract class PersistentProjectileEntityMixin {

    @Shadow public abstract void setDamage(double damage);

    private final PersistentProjectileEntity thisEntity = (PersistentProjectileEntity) (Object) this;
    private boolean init = false;
    private boolean isExplosive = false;
    private boolean isEnder = false;
    private boolean isEvio = false;
    private final Random rand = new Random();

    @Inject(method = "tick", at = @At("HEAD"))
    public void tick(CallbackInfo info) {
        if (thisEntity.getOwner() instanceof LivingEntity && !init){
            if (EnchantmentHelper.getEquipmentLevel(ModEnchants.EVIOCORE, (LivingEntity) thisEntity.getOwner()) > 0)
                isEvio = true;
            else {
                if (EnchantmentHelper.getEquipmentLevel(ModEnchants.EXPLOSIVE, (LivingEntity) thisEntity.getOwner()) > 0)
                    isExplosive = true;
                else if (EnchantmentHelper.getEquipmentLevel(ModEnchants.ENDER, (LivingEntity) thisEntity.getOwner()) > 0)
                    isEnder = true;
            }
            init = true;
        }
    }

    @ModifyArgs(
            method = "tick",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/World;addParticle(Lnet/minecraft/particle/ParticleEffect;DDDDDD)V"))
    private void particle(Args args){
        if(isEvio){
            this.setDamage(0);
        }
        if (isExplosive) {
            args.set(0, ParticleTypes.SMOKE);
            args.set(4, (rand.nextDouble() - 0.5) / 15);
            args.set(5, (rand.nextDouble() - 0.5) / 15);
            args.set(6, (rand.nextDouble() - 0.5) / 15);
        }
        else if (isEnder) {
            args.set(0, ParticleTypes.REVERSE_PORTAL);
            args.set(4, (rand.nextDouble() - 0.5) / 15);
            args.set(5, (rand.nextDouble() - 0.5) / 15);
            args.set(6, (rand.nextDouble() - 0.5) / 15);
        }
    }

}
