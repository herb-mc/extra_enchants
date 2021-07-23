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
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.explosion.Explosion;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import java.util.Random;

@Mixin(PersistentProjectileEntity.class)
public abstract class PersistentProjectileEntityMixin {

    @Shadow public abstract void setDamage(double damage);

    @Shadow protected boolean inGround;

    @Shadow public abstract boolean isCritical();

    @Unique private final PersistentProjectileEntity thisEntity = (PersistentProjectileEntity) (Object) this;
    @Unique public int explosive;
    @Unique public boolean ender;
    @Unique public boolean purity = false;
    @Unique public int exposing = 0;
    @Unique public boolean critical = false;
    @Unique public boolean sharpshooter = false;
    @Unique public boolean neptune = false;
    @Unique public boolean playerOwner = false;
    @Unique public int launching = 0;
    @Unique private final Random rand = new Random();

    @Inject(at = @At("TAIL"), method = "setOwner")
    protected void setOwner(@Nullable Entity entity, CallbackInfo info) {
        if (entity instanceof LivingEntity) {
            ItemStack stack = ((LivingEntity) entity).getStackInHand(((LivingEntity) entity).getActiveHand());
            if (EnchantmentHelper.getLevel(ModEnchants.ARROW_SPEED, stack) > 0) {
                int i = EnchantmentHelper.getLevel(ModEnchants.ARROW_SPEED, stack);
                thisEntity.setVelocity(thisEntity.getVelocity().multiply((20.0F + 3.0F * i) / 20.0F));
                thisEntity.setDamage(thisEntity.getDamage() + 0.25 * i);
            }
            if (EnchantmentHelper.getEquipmentLevel(ModEnchants.SHARPSHOOTER, (LivingEntity) entity) > 0 && entity.isSneaking()) {
                thisEntity.setDamage(thisEntity.getDamage() + EnchantmentHelper.getEquipmentLevel(ModEnchants.SHARPSHOOTER, (LivingEntity) entity));
                sharpshooter = true;
            }
            if (EnchantmentHelper.getEquipmentLevel(ModEnchants.CORE_OF_NEPTUNE, (LivingEntity) entity) > 0)
                neptune = true;
            if (EnchantmentHelper.getLevel(ModEnchants.EXPOSING, stack) > 0)
                exposing = EnchantmentHelper.getEquipmentLevel(ModEnchants.EXPOSING, (LivingEntity) entity);
            if (EnchantmentHelper.getEquipmentLevel(ModEnchants.CORE_OF_PURITY, (LivingEntity) entity) > 0)
                purity = true;
            if (EnchantmentHelper.getLevel(ModEnchants.EXPLOSIVE, stack) > 0)
                explosive += EnchantmentHelper.getLevel(ModEnchants.EXPLOSIVE, stack);
            else if (EnchantmentHelper.getLevel(ModEnchants.ENDER, stack) > 0)
                ender = true;
            if (entity instanceof PlayerEntity)
                playerOwner = true;
        }
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("RETURN"))
    protected void writeCustomDataToNbt(NbtCompound nbt, CallbackInfo info) {
        nbt.putBoolean("ender", ender);
        nbt.putBoolean("purity", purity);
        nbt.putInt("explosive", explosive);
        nbt.putInt("exposing", exposing);
        nbt.putBoolean("neptune", neptune);
        nbt.putBoolean("sharpshooter", sharpshooter);
        nbt.putBoolean("shot_by_player", playerOwner);
        if (thisEntity instanceof TridentEntity) {
            nbt.putInt("launching", launching);
        }
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("RETURN"))
    protected void readCustomDataFromNbt(NbtCompound nbt, CallbackInfo info) {
        ender = nbt.getBoolean("ender");
        purity = nbt.getBoolean("purity");
        explosive = nbt.getInt("explosive");
        exposing = nbt.getInt("exposing");
        neptune = nbt.getBoolean("neptune");
        sharpshooter = nbt.getBoolean("sharpshooter");
        playerOwner = nbt.getBoolean("shot_by_player");
        if (thisEntity instanceof TridentEntity) {
            launching = nbt.getInt("launching");
        }
    }

    @ModifyArgs(
            method = "tick",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/World;addParticle(Lnet/minecraft/particle/ParticleEffect;DDDDDD)V"))
    private void particle(Args args){
        if(purity){
            this.setDamage(0);
        }
        if (explosive > 0) {
            args.set(0, ParticleTypes.SMOKE);
            args.set(4, (rand.nextDouble() - 0.5) / 15);
            args.set(5, (rand.nextDouble() - 0.5) / 15);
            args.set(6, (rand.nextDouble() - 0.5) / 15);
        }
        else if (ender) {
            args.set(0, ParticleTypes.REVERSE_PORTAL);
            args.set(4, (rand.nextDouble() - 0.5) / 15);
            args.set(5, (rand.nextDouble() - 0.5) / 15);
            args.set(6, (rand.nextDouble() - 0.5) / 15);
        }
    }

    @Inject(method = "tick", at = @At("HEAD"))
    protected void setCritical(CallbackInfo info) {
        if (this.isCritical())
            critical = true;
    }

    @Inject(method = "tick", at = @At("TAIL"))
    protected void tick(CallbackInfo info) {
        if(thisEntity instanceof ArrowEntity && this.inGround && (critical || !playerOwner)) {
            if (explosive >= 1) {
                thisEntity.world.createExplosion(thisEntity, thisEntity.getX(), thisEntity.getY(), thisEntity.getZ(), explosive / 2.0F + 0.5F, Explosion.DestructionType.NONE);
                thisEntity.discard();
            } else if (ender && thisEntity.getOwner() != null && thisEntity.getOwner().isAlive()) {
                Entity entity = thisEntity.getOwner();
                if (entity instanceof ServerPlayerEntity serverPlayerEntity) {
                    if (entity.hasVehicle()) {
                        serverPlayerEntity.requestTeleportAndDismount(thisEntity.getX(), thisEntity.getY(), thisEntity.getZ());
                    } else {
                        entity.requestTeleport(thisEntity.getX(), thisEntity.getY(), thisEntity.getZ());
                    }
                }
                else if (entity instanceof LivingEntity) {
                    if (entity.hasVehicle()) {
                        entity.getRootVehicle().teleport(thisEntity.getX(), thisEntity.getY(), thisEntity.getZ());
                    } else {
                        entity.teleport(thisEntity.getX(), thisEntity.getY(), thisEntity.getZ());
                    }
                }
                entity.fallDistance = 0.0F;
                entity.damage(DamageSource.FALL, 2.0F);
                thisEntity.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 1.0F, 1.0F);
                thisEntity.discard();
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "onEntityHit", cancellable = true)
    protected void onEntityCollision(EntityHitResult hitResult, CallbackInfo info) {
        if(thisEntity instanceof ArrowEntity) {
            if (this.isCritical() || !playerOwner) {
                Entity target = hitResult.getEntity();
                int phase = -1;
                float decreasePower = 1.0F;
                if (target instanceof EnderDragonPart dragonTarget) {
                    phase = dragonTarget.owner.getPhaseManager().getCurrent().getType().getTypeId();
                }
                if (phase != 6 && phase != 3 && !(target instanceof EndermanEntity) && !target.isSpectator() && !target.isInvulnerable()) {
                    if (exposing > 0 && target instanceof LivingEntity livingTarget) {
                        StatusEffectInstance glow = new StatusEffectInstance(StatusEffects.GLOWING, exposing, 20, true, true);
                        livingTarget.addStatusEffect(glow);
                    }
                    if (explosive > 0) {
                        explosionArrow(target, decreasePower);
                        info.cancel();
                    } else if (ender && thisEntity.getOwner() != null && thisEntity.getOwner().isAlive()) {
                        ArrowEntity entity = (ArrowEntity) (Object) this;
                        entity.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 1.0F, 1.0F);
                        Entity owner = thisEntity.getOwner();
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

    @Inject(at = @At("TAIL"), method = "onEntityHit", cancellable = true)
    protected void handleEntityEnder(EntityHitResult hitResult, CallbackInfo info) {
        if(thisEntity instanceof ArrowEntity && !playerOwner && ender && thisEntity.getOwner() != null && thisEntity.getOwner().isAlive()) {
            Entity target = hitResult.getEntity();
            ArrowEntity entity = (ArrowEntity) (Object) this;
            Entity owner = thisEntity.getOwner();
            if (owner instanceof LivingEntity) {
                entity.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 1.0F, 1.0F);
                if (owner.hasVehicle()) {
                    owner.getRootVehicle().teleport(target.getX(), target.getY(), target.getZ());
                } else {
                    owner.teleport(target.getX(), target.getY(), target.getZ());
                }
                entity.fallDistance = 0.0F;
                entity.damage(DamageSource.FALL, 1.0F);
            }
        }
    }

    public void explosionArrow(Entity target, float powerMod){
        thisEntity.world.createExplosion(thisEntity, target.getX(), (target.getY() + 2.0F * thisEntity.getY()) / 3.0F, target.getZ(), explosive / powerMod, Explosion.DestructionType.NONE);
        thisEntity.discard();
    }

}
