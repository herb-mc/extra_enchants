package com.herb_mc.extra_enchants.mixin;

import com.herb_mc.extra_enchants.registry.ModEnchants;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.dragon.EnderDragonPart;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.explosion.Explosion;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(ProjectileEntity.class)
public abstract class ProjectileEntityMixin implements EntityInterfaceMixin {

    @Shadow @Nullable public abstract Entity getOwner();

    private int exploding;
    private int ender;
    private int glowing = 0;
    private boolean speed = false;
    private final Random rand = new Random();

    @Inject(at = @At("TAIL"), method = "setOwner")
    protected void setOwner(Entity entity, CallbackInfo info) {
        if (entity != null) {
            exploding = EnchantmentHelper.getEquipmentLevel(ModEnchants.EXPLOSIVE, (LivingEntity) entity);
            ender = EnchantmentHelper.getEquipmentLevel(ModEnchants.ENDER, (LivingEntity) entity);
            int i = EnchantmentHelper.getEquipmentLevel(ModEnchants.EXPOSING, (LivingEntity) entity);
            if(i >= 1)
                glowing = 40 * i;
        }
    }

    @Inject(at = @At("TAIL"), method = "tick")
    protected void tick(CallbackInfo info){
        if(!speed && this.getOwner() != null && (Object) this instanceof ArrowEntity) {
            int i = EnchantmentHelper.getEquipmentLevel(ModEnchants.ARROW_SPEED, (LivingEntity) this.getOwner());
            if(i > 0) {
                ArrowEntity arrow = (ArrowEntity) (Object) this;
                this.invokeSetVelocity(this.invokeGetVelocity().multiply((20.0F + 3.0F * i) / 20.0F));
                arrow.setDamage(arrow.getDamage() + 0.25 * i);
            }
            speed = true;
        }
    }

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/projectile/ProjectileEntity;onEntityHit(Lnet/minecraft/util/hit/EntityHitResult;)V"), method = "onCollision", cancellable = true)
    protected void onEntityCollision(HitResult hitResult, CallbackInfo info) {
        if((Object) this instanceof ArrowEntity) {
            ArrowEntity thisObject = (ArrowEntity) (Object) this;
            if (thisObject.isCritical()) {
                EntityHitResult result = (EntityHitResult) hitResult;
                Entity target = result.getEntity();
                int phase = -1;
                float decreasePower = 1.0F;
                if (target instanceof EnderDragonPart) {
                    EnderDragonPart dragonTarget = (EnderDragonPart) target;
                    phase = dragonTarget.owner.getPhaseManager().getCurrent().getType().getTypeId();
                }
                if ((Object) this instanceof ArrowEntity && phase != 6 && phase != 3 && !(target instanceof EndermanEntity) && !target.isSpectator() && !target.isInvulnerable()) {
                    if (glowing > 0 && target instanceof LivingEntity livingTarget) {
                        StatusEffectInstance glow = new StatusEffectInstance(StatusEffects.GLOWING, glowing, 20, true, true);
                        livingTarget.addStatusEffect(glow);
                    }
                    if (this.exploding >= 1) {
                        ArrowEntity entity = (ArrowEntity) (Object) this;
                        if (entity.isCritical()) {
                            explosionArrow(target, decreasePower);
                            info.cancel();
                        }
                    } else if (this.ender >= 1) {
                        ArrowEntity entity = (ArrowEntity) (Object) this;
                        entity.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 1.0F, 1.0F);
                        Entity owner = this.getOwner();
                        if (owner instanceof ServerPlayerEntity serverPlayerEntity) {
                            if (owner.hasVehicle()) {
                                serverPlayerEntity.requestTeleportAndDismount(target.getX(), target.getY(), target.getZ());
                            } else {
                                owner.requestTeleport(target.getX(), target.getY(), target.getZ());
                            }
                        }

                        entity.fallDistance = 0.0F;
                        entity.damage(DamageSource.FALL, 1.0F);
                    }
                }
            }
        }
    }

    public void explosionArrow(Entity target, float powerMod){
        this.getWorld().createExplosion((Entity) (Object) this, target.getX(), (target.getY() + 2.0F * this.invokeGetY()) / 3.0F, target.getZ(), (float) this.exploding / powerMod, Explosion.DestructionType.NONE);
        this.invokeDiscard();
    }

}
