package com.herb_mc.extra_enchants.mixin;

import com.herb_mc.extra_enchants.lib.LivingEntityMixinAccess;
import com.herb_mc.extra_enchants.lib.PersistentProjectileEntityMixinAccess;
import com.herb_mc.extra_enchants.registry.ModEnchants;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
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
import net.minecraft.nbt.NbtElement;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import java.util.Random;

@Mixin(PersistentProjectileEntity.class)
public abstract class PersistentProjectileEntityMixin implements PersistentProjectileEntityMixinAccess {

    @Shadow protected boolean inGround;

    @Shadow public abstract void setVelocity(double x, double y, double z, float speed, float divergence);

    @Unique private final PersistentProjectileEntity thisEntity = (PersistentProjectileEntity) (Object) this;
    @Unique public int explosive;
    @Unique public boolean explosiveParticles = false;
    @Unique public int exposing = 0;
    @Unique public boolean critical = false;
    @Unique public boolean ender;
    @Unique public boolean enderParticles = false;
    @Unique public boolean playerOwner = false;
    @Unique public boolean purity = false;
    @Unique public boolean sharpshooter = false;
    @Unique public boolean thunderbolt = false;
    @Unique public boolean thunderboltParticles = false;
    @Unique public boolean neptune = false;
    @Unique private final Random rand = new Random();
    private Entity hitResult;

    @Override
    public void setExplosive(int i) {
        this.explosive = i;
    }

    @Override
    public void setExposing(int i) {
        this.exposing = i;
    }

    @Override
    public void setCrit(boolean i) {
        this.critical = i;
    }

    @Override
    public void setEnder(boolean i) {
        this.ender = i;
    }

    @Override
    public void setNeptune(boolean i) {
        this.neptune = i;
    }

    @Override
    public void setPlayerOwner(boolean i) {
        this.playerOwner = i;
    }

    @Override
    public void setPurity(boolean i) {
        this.purity = i;
    }

    @Override
    public void setSharpshooter(boolean i) {
        this.sharpshooter = i;
    }

    @Override
    public void setThunderbolt(boolean i) {
        this.thunderbolt = i;
    }

    @ModifyArgs(
            method = "tick",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/World;addParticle(Lnet/minecraft/particle/ParticleEffect;DDDDDD)V"
            )
    )
    private void particle(Args args){
        if (explosiveParticles) {
            args.set(0, ParticleTypes.SMOKE);
            args.set(4, (rand.nextDouble() - 0.5) / 15);
            args.set(5, (rand.nextDouble() - 0.5) / 15);
            args.set(6, (rand.nextDouble() - 0.5) / 15);
        } else if (enderParticles) {
            args.set(0, ParticleTypes.REVERSE_PORTAL);
            args.set(4, (rand.nextDouble() - 0.5) / 15);
            args.set(5, (rand.nextDouble() - 0.5) / 15);
            args.set(6, (rand.nextDouble() - 0.5) / 15);
        } else if (thunderboltParticles && thisEntity.world.isSkyVisible(thisEntity.getBlockPos())) {
            args.set(0, ParticleTypes.ELECTRIC_SPARK);
            args.set(4, (rand.nextDouble() - 0.5) * 2);
            args.set(5, (rand.nextDouble() - 0.5) * 2);
            args.set(6, (rand.nextDouble() - 0.5) * 2);
        }
    }

    @Redirect(
            method = "onEntityHit",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/projectile/PersistentProjectileEntity;setVelocity(Lnet/minecraft/util/math/Vec3d;)V"
            )
    )
    private void setReflectedVelocity(PersistentProjectileEntity persistentProjectileEntity, Vec3d velocity) {
        if (hitResult instanceof LivingEntity && ((LivingEntity) hitResult).isBlocking() && ((LivingEntity) hitResult).getActiveItem() != null && EnchantmentHelper.getLevel(ModEnchants.REFLECTING, ((LivingEntity) hitResult).getActiveItem()) > 0) {
            double absSpeed = persistentProjectileEntity.getVelocity().length();
            persistentProjectileEntity.setVelocity(hitResult.getRotationVector().multiply(absSpeed * (0.4 + 0.1 * EnchantmentHelper.getLevel(ModEnchants.REFLECTING, ((LivingEntity) hitResult).getActiveItem()))));
        }
        else
            persistentProjectileEntity.setVelocity(velocity);
    }

    @Inject(
            method = "onEntityHit",
            at = @At("HEAD")
    )
    protected void getEntityHitResult(EntityHitResult entityHitResult, CallbackInfo info) {
        hitResult = entityHitResult.getEntity();
    }

    @Inject(
            method = "setOwner",
            at = @At("TAIL")
    )
    protected void setAttributesFromOwner(@Nullable Entity entity, CallbackInfo info) {
        if (entity instanceof LivingEntity && !(entity instanceof PlayerEntity)) {
            if (EnchantmentHelper.getEquipmentLevel(ModEnchants.ACE, (LivingEntity) entity) > 0 && ((LivingEntity) entity).isFallFlying()) {
                thisEntity.setDamage(thisEntity.getDamage() + EnchantmentHelper.getEquipmentLevel(ModEnchants.ACE, (LivingEntity) entity) / 2F);
                sharpshooter = true;
            }
            if (EnchantmentHelper.getEquipmentLevel(ModEnchants.SHARPSHOOTER, (LivingEntity) entity) > 0 && entity.isSneaking()) {
                thisEntity.setDamage(thisEntity.getDamage() + 1);
                sharpshooter = true;
            }
            if (EnchantmentHelper.getEquipmentLevel(ModEnchants.CORE_OF_PURITY, (LivingEntity) entity) > 0)
                purity = true;
            if (((LivingEntity) entity).getMainHandStack() != null) {
                ItemStack stack = ((LivingEntity) entity).getMainHandStack();
                if (EnchantmentHelper.getLevel(ModEnchants.EXPLOSIVE, stack) > 0)
                    explosive += EnchantmentHelper.getLevel(ModEnchants.EXPLOSIVE, stack);
                else if (EnchantmentHelper.getLevel(ModEnchants.ENDER, stack) > 0)
                    ender = true;
            }
        }
        // yes, custom crit particle booleans need to be defined locally, trying to do so in other mixins actually confused jvm somehow
        if (entity instanceof PlayerEntity) {
            if (((LivingEntity) entity).getActiveHand() != null && EnchantmentHelper.getEquipmentLevel(ModEnchants.CORE_OF_PURITY, (LivingEntity) entity) == 0) {
                ItemStack stack = ((LivingEntity) entity).getStackInHand(((LivingEntity) entity).getActiveHand());
                if (EnchantmentHelper.getLevel(ModEnchants.EXPLOSIVE, stack) > 0)
                    explosiveParticles = true;
                else if (EnchantmentHelper.getLevel(ModEnchants.ENDER, stack) > 0)
                    enderParticles = true;
                else if (EnchantmentHelper.getLevel(ModEnchants.THUNDERBOLT, stack) > 0)
                    thunderboltParticles = true;
            }
        }
    }

    @Inject(
            method = "writeCustomDataToNbt",
            at = @At("RETURN")
    )
    protected void writeCustomDataToNbt(NbtCompound nbt, CallbackInfo info) {
        nbt.putBoolean("ender", ender);
        nbt.putBoolean("purity", purity);
        nbt.putInt("explosive", explosive);
        nbt.putInt("exposing", exposing);
        nbt.putBoolean("neptune", neptune);
        nbt.putBoolean("sharpshooter", sharpshooter);
        nbt.putBoolean("shotByPlayer", playerOwner);
        nbt.putBoolean("thunderbolt", thunderbolt);
    }

    @Inject(
            method = "readCustomDataFromNbt",
            at = @At("RETURN")
    )
    protected void readCustomDataFromNbt(NbtCompound nbt, CallbackInfo info) {
        ender = nbt.getBoolean("ender");
        purity = nbt.getBoolean("purity");
        explosive = nbt.getInt("explosive");
        exposing = nbt.getInt("exposing");
        neptune = nbt.getBoolean("neptune");
        sharpshooter = nbt.getBoolean("sharpshooter");
        playerOwner = nbt.getBoolean("shotByPlayer");
        thunderbolt = nbt.getBoolean("thunderbolt");
    }

    @Inject(
            method = "tick",
            at = @At("TAIL")
    )
    protected void inGroundChecks(CallbackInfo info) {
        if(thisEntity instanceof ArrowEntity && this.inGround && (critical || !playerOwner)) {
            if (explosive >= 1) {
                thisEntity.world.createExplosion(thisEntity, thisEntity.getX(), thisEntity.getY(), thisEntity.getZ(), explosive / 2.0F + 0.5F, Explosion.DestructionType.NONE);
                thisEntity.discard();
            }
            else if (ender && thisEntity.getOwner() != null && thisEntity.getOwner().isAlive()) {
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
            else if (thunderbolt && thisEntity.world.isSkyVisible(thisEntity.getBlockPos()))
                lightningAtSelf(thisEntity);
        }
    }

    @Inject(
            at = @At("HEAD"),
            method = "onEntityHit",
            cancellable = true
    )
    protected void entityCollisionCheck(EntityHitResult hitResult, CallbackInfo info) {
        if(thisEntity instanceof ArrowEntity) {
            if (thisEntity.isCritical() || !playerOwner) {
                Entity target = hitResult.getEntity();
                int phase = -1;
                float decreasePower = 1.0F;
                if (target instanceof EnderDragonPart dragonTarget) {
                    phase = dragonTarget.owner.getPhaseManager().getCurrent().getType().getTypeId();
                }
                if (phase != 3 && !(target instanceof EndermanEntity) && !target.isSpectator() && !target.isInvulnerable()) {
                    if (exposing > 0 && target instanceof LivingEntity livingTarget) {
                        StatusEffectInstance glow = new StatusEffectInstance(StatusEffects.GLOWING, exposing, 1, true, true);
                        livingTarget.addStatusEffect(glow);
                        ((LivingEntityMixinAccess) livingTarget).exposedModify(exposing);
                    }
                    if (phase != 6 && explosive > 0) {
                        explosionArrow(target, decreasePower);
                        info.cancel();
                    }
                    else if (ender && thisEntity.getOwner() != null && thisEntity.getOwner().isAlive()) {
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
                    else if (thunderbolt && thisEntity.world.isSkyVisible(target.getBlockPos()))
                        lightningAtSelf(thisEntity);
                }
            }
        }
    }

    @Inject(
            at = @At("TAIL"),
            method = "onEntityHit",
            cancellable = true
    )
    protected void handleLivingEntityEnder(EntityHitResult hitResult, CallbackInfo info) {
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

    public void lightningAtSelf(PersistentProjectileEntity source) {
        LightningEntity lightningEntity = EntityType.LIGHTNING_BOLT.create(source.world);
        lightningEntity.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(source.getBlockPos()));
        lightningEntity.setChanneler(source.getOwner() instanceof ServerPlayerEntity ? (ServerPlayerEntity)source.getOwner() : null);
        source.world.spawnEntity(lightningEntity);
        source.discard();
    }

}
