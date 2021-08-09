package com.herb_mc.extra_enchants.mixin;

import com.herb_mc.extra_enchants.lib.AttributeModCommons;
import com.herb_mc.extra_enchants.lib.EnchantmentMappings;
import com.herb_mc.extra_enchants.lib.LivingEntityMixinAccess;
import com.herb_mc.extra_enchants.registry.ModEnchants;
import com.herb_mc.extra_enchants.lib.UUIDCommons;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.fabricmc.fabric.impl.tool.attribute.ToolManagerImpl;
import net.minecraft.block.Block;
import net.minecraft.block.OreBlock;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.EntityDamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.*;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import java.util.*;

import static net.minecraft.enchantment.EnchantmentHelper.getEquipmentLevel;
import static net.minecraft.enchantment.EnchantmentHelper.getLevel;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin implements AttributeModCommons, UUIDCommons, LivingEntityMixinAccess {

    @Shadow public abstract int getArmor();

    @Shadow public abstract void heal(float amount);

    @Unique private final LivingEntity thisEntity = (LivingEntity) (Object) this;
    @Unique private int EXPOSED;
    @Unique private float STEP_HEIGHT = 0F;
    @Unique private int SPRINT_BOOST = 0;
    @Unique private final LivingEntity entityStatic = (LivingEntity) (Object) this;
    @Unique private final Random rand = entityStatic.getRandom();
    @Unique int level = 0;

    @Override
    public int exposedAccess() {
        return EXPOSED;
    }

    @Override
    public void exposedModify(int i) {
        EXPOSED = i;
    }

    @ModifyArg(
            method = "applyArmorToDamage",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/DamageUtil;getDamageLeft(FFF)F"
            ),
            index = 1
    )
    private float applyArmorEffects(float armor) {
        armor *= 1.0D - level * EnchantmentMappings.cleavingArmorPierce.getDouble();
        return armor > 0 ? (EXPOSED > 0) ? armor * EnchantmentMappings.exposedArmorMult.getFloat() : armor : 0;
    }

    @ModifyArg(
            method = "jump",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/LivingEntity;setVelocity(DDD)V"
            ),
            index = 1
    )
    private double addJumpVelocity(double d) {
        return (EnchantmentHelper.getEquipmentLevel(ModEnchants.LEAPING, thisEntity) > 0) ? d + EnchantmentMappings.leapingBaseVelocity.getFloat() * (float) EnchantmentHelper.getEquipmentLevel(ModEnchants.LEAPING, thisEntity) : d;
    }

    @ModifyArg(
            method = "baseTick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/LivingEntity;setAir(I)V"
            )
    )
    private int neptuneModAirUnderwater(int air) {
        return (EnchantmentHelper.getEquipmentLevel(ModEnchants.CORE_OF_NEPTUNE, thisEntity) > 0 && thisEntity.isSubmergedIn(FluidTags.WATER)) ? air - EnchantmentMappings.coreNeptuneBreathLoss.getInt() : air;
    }

    @ModifyArgs(
            method = "travel",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/LivingEntity;setVelocity(Lnet/minecraft/util/math/Vec3d;)V",
                    ordinal = 0
            )
    )
    protected void neptuneModSwimSpeed(Args args) {
        if (EnchantmentHelper.getEquipmentLevel(ModEnchants.CORE_OF_NEPTUNE, thisEntity) > 0) {
            Vec3d vec3d = thisEntity.getVelocity();
            if (thisEntity.horizontalCollision && thisEntity.isClimbing()) {
                vec3d = new Vec3d(vec3d.x, 0.2D, vec3d.z);
            }
            args.set(0, vec3d.multiply(EnchantmentMappings.coreNeptuneWaterDrag.getDouble(), 0.800000011920929D, EnchantmentMappings.coreNeptuneWaterDrag.getDouble()));
        }
    }

    @ModifyConstant(
            method = "travel",
            constant = @Constant(doubleValue = 0.08D)
    )
    private double featherweightFallSpeed(double d){
        return (EnchantmentHelper.getEquipmentLevel(ModEnchants.FEATHERWEIGHT, thisEntity) > 0 && thisEntity.getVelocity().y < 0 && !thisEntity.isSneaking()) ? d / (EnchantmentHelper.getEquipmentLevel(ModEnchants.FEATHERWEIGHT, thisEntity) + 1) * EnchantmentMappings.featherweightFallSpeedScale.getDouble() : d;
    }

    @ModifyConstant(
            method = "getNextAirOnLand",
            constant = @Constant(intValue = 4))
    private int neptuneModAir(int air) {
        return (EnchantmentHelper.getEquipmentLevel(ModEnchants.CORE_OF_NEPTUNE, thisEntity) > 0) ? rand.nextInt(EnchantmentMappings.coreNeptuneBreathGainRand.getInt()) % EnchantmentMappings.coreNeptuneBreathGainMod.getInt() : air;
    }

    @ModifyVariable(
            method = "applyArmorToDamage",
            at = @At(
                    value = "HEAD",
                    target = "Lnet/minecraft/entity/LivingEntity;applyArmorToDamage(Lnet/minecraft/entity/damage/DamageSource;F)F"
            ),
            ordinal = 0
    )
    private float applyToughnessDR(float amount) {
        return (getEquipmentLevel(ModEnchants.TOUGH, thisEntity) > 0) ? amount * (1.0F - EnchantmentMappings.toughDamageReduction.getFloat() * getEquipmentLevel(ModEnchants.TOUGH, thisEntity)) : amount;
    }

    @ModifyConstant(
            method = "isBlocking",
            constant = @Constant(intValue = 5)
    )
    private int applyReflex(int i) {
        if (thisEntity.getMainHandStack() != null & EnchantmentHelper.getLevel(ModEnchants.REFLEX, thisEntity.getMainHandStack()) > 0) {
            return EnchantmentMappings.reflexReadyTicks.getInt();
        }
        return i;
    }

    @ModifyVariable(
            method = "applyArmorToDamage",
            at = @At(
                    value = "HEAD",
                    target = "Lnet/minecraft/entity/LivingEntity;applyArmorToDamage(Lnet/minecraft/entity/damage/DamageSource;F)F"
            ),
            ordinal = 0
    )
    private DamageSource getCleavingLevel(DamageSource source) {
        level = 0;
        if (source.getAttacker() != null)
            if (source.getAttacker() instanceof LivingEntity && !source.isProjectile() && ((LivingEntity) source.getAttacker()).getActiveHand() != null)
                level = getLevel(ModEnchants.CLEAVING, ((LivingEntity) source.getAttacker()).getStackInHand(((LivingEntity) source.getAttacker()).getActiveHand()));
            else if (source.getAttacker() instanceof LivingEntity && !source.isProjectile() && ((LivingEntity) source.getAttacker()).getMainHandStack() != null)
                level = getLevel(ModEnchants.CLEAVING, ((LivingEntity) source.getAttacker()).getMainHandStack());
        return source;
    }

    @ModifyVariable(
            method = "computeFallDamage",
            at = @At(
                    value = "HEAD",
                    target = "Lnet/minecraft/entity/LivingEntity;computeFallDamage(FF)I"
            ),
            ordinal = 0
    )
    private float enchantmentFallDistanceHandler(float fallDistance) {
        int i = EnchantmentHelper.getEquipmentLevel(ModEnchants.FEATHERWEIGHT, thisEntity);
        if (!thisEntity.isSneaking() && i > 0)
            fallDistance /= 2 * i / EnchantmentMappings.featherweightFallSpeedScale.getDouble();
        i = EnchantmentHelper.getEquipmentLevel(ModEnchants.LEAPING, thisEntity);
        if (i > 0)
            fallDistance -= (float) i - EnchantmentMappings.leapingFallHeight.getFloat();
        return fallDistance;
    }

    @ModifyVariable(
            method = "damage",
            at = @At(
                    value = "HEAD",
                    target = "Lnet/minecraft/entity/LivingEntity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z"
            ),
            ordinal = 0
    )
    private float damageCalc(float amount, DamageSource source) {
        int level = EnchantmentHelper.getEquipmentLevel(ModEnchants.SHOCK_RESISTANT, thisEntity);
        if (level > 0 && source == DamageSource.FLY_INTO_WALL)
            amount *= EnchantmentMappings.shockResistBaseMult.getFloat() - 1.0F + 1.0F / (EnchantmentMappings.shockResistScale.getFloat() * Math.log10(EnchantmentHelper.getEquipmentLevel(ModEnchants.SHOCK_RESISTANT, thisEntity)) + 1.0F);
        if (EnchantmentHelper.getEquipmentLevel(ModEnchants.ACE, thisEntity) > 0 && thisEntity.isFallFlying())
            amount *= 1D - Math.log10(EnchantmentHelper.getEquipmentLevel(ModEnchants.ACE, thisEntity) + 1) * EnchantmentMappings.aceDamageReducerMult.getDouble();
        if (EXPOSED > 0)
            amount *= EnchantmentMappings.exposedDamageMult.getFloat();
        if (source instanceof EntityDamageSource && EnchantmentHelper.getEquipmentLevel(ModEnchants.CORE_OF_THE_BLOOD_GOD, thisEntity) > 0 && (rand.nextDouble() < EnchantmentMappings.coreBloodCritChance.getDouble()))
            amount *= EnchantmentMappings.coreBloodCritMult.getDouble();
        if (EnchantmentHelper.getEquipmentLevel(ModEnchants.BLAZE_AFFINITY, thisEntity) > 0 && thisEntity.isOnFire())
            amount *= EnchantmentMappings.blazeAffinityIncomingMult.getDouble();
        if (EnchantmentHelper.getEquipmentLevel(ModEnchants.CORE_OF_THE_WARP, thisEntity) > 0) {
            amount *= EnchantmentMappings.coreWarpIncomingDamage.getDouble();
            if (rand.nextDouble() < EnchantmentMappings.coreWarpTeleportChance.getDouble() && (thisEntity.world.getBiome(thisEntity.getBlockPos()).getCategory() == Biome.Category.THEEND || thisEntity.world.getBiomeKey(thisEntity.getBlockPos()).get().getValue().toString().equals("minecraft:warped_forest")))
                randomTeleport(EnchantmentMappings.coreWarpTeleportRange.getInt(), EnchantmentMappings.coreWarpTeleportTries.getInt());
        }
        if (source.getSource() != null && source.getSource() instanceof LivingEntity) {
            if (EnchantmentHelper.getEquipmentLevel(ModEnchants.CORE_OF_PURITY, (LivingEntity) source.getSource()) > 0)
                amount = EnchantmentMappings.corePurityBaseDamage.getFloat() + 1;
        }
        if (EnchantmentHelper.getEquipmentLevel(ModEnchants.CORE_OF_PURITY, thisEntity) > 0 && thisEntity.getHealth() / thisEntity.getMaxHealth() > EnchantmentMappings.corePurityThreshold.getFloat())
            amount *= EnchantmentMappings.corePurityDamageMult.getFloat();
        return amount;
    }

    @Inject(
            method = "applyDamage",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/LivingEntity;setHealth(F)V"
            ),
            locals = LocalCapture.CAPTURE_FAILSOFT
    )
    private void applyLifesteal(DamageSource source, float amount, CallbackInfo info, float health) {
        float baseHealAmount = Math.min(amount, health);
        if ((source.name.equals("mob") || source.name.equals("player")) && source.getAttacker() != null) {
            int level = EnchantmentHelper.getLevel(ModEnchants.LIFESTEAL, ((LivingEntity) source.getAttacker()).getMainHandStack());
            if (level > 0)
                ((LivingEntity) source.getAttacker()).heal(Math.min(EnchantmentMappings.lifestealBasePercent.getFloat() * level * baseHealAmount, EnchantmentMappings.lifestealMax.getFloat()));
        }
    }

    @ModifyVariable(
            method = "travel",
            at = @At(
                    value = "INVOKE_ASSIGN",
                    target = "Lnet/minecraft/block/Block;getSlipperiness()F"
            )
    )
    private float slimeySlipperiness(float f) {
        return (EnchantmentHelper.getEquipmentLevel(ModEnchants.SLIMEY, thisEntity) > 0) ? EnchantmentMappings.slimeySlipperiness.getFloat() : f;
    }

    @ModifyVariable(
            method = "getNextAirUnderwater",
            at = @At(
                    value = "INVOKE_ASSIGN",
                    target = "Lnet/minecraft/enchantment/EnchantmentHelper;getRespiration(Lnet/minecraft/entity/LivingEntity;)I"
            ),
            ordinal = 1)
    protected int respLevel(int i) {
        return EnchantmentHelper.getEquipmentLevel(ModEnchants.CORE_OF_NEPTUNE, thisEntity) > 0 ? 0 : i;
    }

    @Inject(
            method = "damage",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/LivingEntity;damageShield(F)V"
            )
    )
    protected void instabilityHandler(DamageSource source, float amount, CallbackInfoReturnable<Boolean> info) {
        if (thisEntity.getActiveItem() != null && EnchantmentHelper.getLevel(ModEnchants.CURSE_OF_INSTABILITY, thisEntity.getActiveItem()) > 0)
            randomTeleport(EnchantmentMappings.instabilityTeleportRange.getInt(), EnchantmentMappings.instabilityTeleportTries.getInt());
    }

    @Inject(
            method = "writeCustomDataToNbt",
            at = @At("RETURN")
    )
    protected void writeCustomDataToNbt(NbtCompound nbt, CallbackInfo info) {
        nbt.putInt("exposedTicks", EXPOSED);
        nbt.putFloat("stepHeight", STEP_HEIGHT);
        nbt.putInt("sprintBoost", SPRINT_BOOST);
    }

    @Inject(
            method = "readCustomDataFromNbt",
            at = @At("RETURN")
    )
    protected void readCustomDataFromNbt(NbtCompound nbt, CallbackInfo info) {
        EXPOSED = nbt.getInt("exposedTicks");
        STEP_HEIGHT = nbt.getFloat("stepHeight");
        SPRINT_BOOST = nbt.getInt("sprintBoost");
    }

    @Inject(
            method = "travel",
            at = @At(
                    value = "INVOKE_ASSIGN",
                    target = "Lnet/minecraft/entity/LivingEntity;getVelocity()Lnet/minecraft/util/math/Vec3d;",
                    ordinal = 10
            )
    )
    private void setMinimumVelocity(Vec3d movementInput, CallbackInfo info) {
        if (EnchantmentHelper.getEquipmentLevel(ModEnchants.PROPELLING, thisEntity) > 0)
            if (thisEntity.getVelocity().length() < EnchantmentMappings.propellingMinAccelVelocity.getDouble() + EnchantmentMappings.propellingAdditionalAccelVelocity.getDouble() * (EnchantmentHelper.getEquipmentLevel(ModEnchants.PROPELLING, thisEntity) - 1)) {
                double mult = EnchantmentMappings.propellingAdditionalAccel.getDouble() * EnchantmentHelper.getEquipmentLevel(ModEnchants.PROPELLING, thisEntity);
                if (thisEntity.getBlockY() >= EnchantmentMappings.propellingPenaltyStartHeight.getDouble())
                    mult *= (EnchantmentMappings.propellingPenaltyCriticalHeight.getDouble() - thisEntity.getBlockY()) / EnchantmentMappings.propellingPenaltyStartHeight.getDouble();
                if (mult < 0)
                    mult = 0;
                mult += EnchantmentMappings.propellingMinAccel.getDouble();
                thisEntity.setVelocity(thisEntity.getVelocity().add(thisEntity.getRotationVector().multiply(mult)));
            }
    }

    @Inject(
            method = "jump",
            at = @At("TAIL")
    )
    private void changeVelocity(CallbackInfo info) {
        int i = EnchantmentHelper.getEquipmentLevel(ModEnchants.LUNGING, thisEntity);
        float mult = 1F + EnchantmentMappings.lungingJumpVelocityBoost.getFloat() * i;
        if (i > 0)
            thisEntity.setVelocity(thisEntity.getVelocity().x * mult, thisEntity.getVelocity().y, thisEntity.getVelocity().z * mult);
    }

    @Inject(
            at = @At("HEAD"),
            method = "tick"
    )
    public void tickAllEnchants(CallbackInfo info) {
        if (!thisEntity.isSpectator()) {
            if (EXPOSED > 0)
                EXPOSED--;
            if (STEP_HEIGHT == 0F)
                STEP_HEIGHT = thisEntity.stepHeight;
            int i = getEquipmentLevel(ModEnchants.WINDSTEP, thisEntity);
            thisEntity.stepHeight = STEP_HEIGHT;
            if (i > 0)
                thisEntity.stepHeight += i * EnchantmentMappings.windstepHeight.getFloat();
            removeAttribute(thisEntity, EntityAttributes.GENERIC_ATTACK_DAMAGE, ACE_ATTRIBUTE_ID);
            i = getEquipmentLevel(ModEnchants.ACE, thisEntity);
            if (i > 0 && thisEntity.isFallFlying())
                modAttributeBase(thisEntity, EntityAttributes.GENERIC_ATTACK_DAMAGE, i, ACE_ATTRIBUTE_ID, "ace_attack_damage", EnchantmentMappings.aceExtraMeleeDamage.getFloat(), EntityAttributeModifier.Operation.ADDITION);
            removeAttribute(thisEntity, EntityAttributes.GENERIC_ATTACK_DAMAGE, CORE_OF_NEPTUNE_ATTRIBUTE_ID);
            removeAttribute(thisEntity, EntityAttributes.GENERIC_ATTACK_SPEED, CORE_OF_NEPTUNE_ATTRIBUTE_ID);
            removeAttribute(thisEntity, EntityAttributes.GENERIC_MOVEMENT_SPEED, CORE_OF_NEPTUNE_ATTRIBUTE_ID);
            if (EnchantmentHelper.getEquipmentLevel(ModEnchants.CORE_OF_NEPTUNE, thisEntity) > 0) {
                int air = thisEntity.getAir();
                double penaltyThreshold = EnchantmentMappings.coreNeptuneAirPenaltyThreshold.getDouble();
                double buffThreshold = EnchantmentMappings.coreNeptuneAirBuffThreshold.getDouble();
                if (air > penaltyThreshold) {
                    double airMult = (penaltyThreshold - air) / (300 - penaltyThreshold);
                    modAttributeBase(thisEntity, EntityAttributes.GENERIC_ATTACK_DAMAGE, 1, CORE_OF_NEPTUNE_ATTRIBUTE_ID, "nep_attack_damage", airMult * EnchantmentMappings.coreNeptuneMaxDamagePenalty.getDouble(), EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
                    modAttributeBase(thisEntity, EntityAttributes.GENERIC_ATTACK_SPEED, 1, CORE_OF_NEPTUNE_ATTRIBUTE_ID, "nep_attack_speed", airMult * EnchantmentMappings.coreNeptuneMaxAttackSpeedPenalty.getDouble(), EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
                    modAttributeBase(thisEntity, EntityAttributes.GENERIC_MOVEMENT_SPEED, 1, CORE_OF_NEPTUNE_ATTRIBUTE_ID, "nep_movement_speed", airMult * EnchantmentMappings.coreNeptuneMaxMoveSpeedPenalty.getDouble(), EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
                } else if (air < buffThreshold) {
                    double airMult = (buffThreshold - air) / buffThreshold;
                    modAttributeBase(thisEntity, EntityAttributes.GENERIC_ATTACK_DAMAGE, 1, CORE_OF_NEPTUNE_ATTRIBUTE_ID, "nep_attack_damage", airMult * EnchantmentMappings.coreNeptuneMaxDamageBuff.getDouble(), EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
                    modAttributeBase(thisEntity, EntityAttributes.GENERIC_ATTACK_SPEED, 1, CORE_OF_NEPTUNE_ATTRIBUTE_ID, "nep_attack_speed", airMult * EnchantmentMappings.coreNeptuneMaxAttackSpeedBuff.getDouble(), EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
                    modAttributeBase(thisEntity, EntityAttributes.GENERIC_MOVEMENT_SPEED, 1, CORE_OF_NEPTUNE_ATTRIBUTE_ID, "nep_movement_speed", airMult * EnchantmentMappings.coreNeptuneMaxMoveSpeedBuff.getDouble(), EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
                }
            }
            removeAttribute(thisEntity, EntityAttributes.GENERIC_ATTACK_DAMAGE, BLAZE_ATTRIBUTE_ID);
            i = EnchantmentHelper.getEquipmentLevel(ModEnchants.BLAZE_AFFINITY, thisEntity);
            if (i > 0 && thisEntity.isOnFire())
                modAttributeBase(thisEntity, EntityAttributes.GENERIC_ATTACK_DAMAGE, 1, BLAZE_ATTRIBUTE_ID, "blz_attack_daamge", EnchantmentMappings.blazeAffinityExtraDamage.getDouble(), EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
            removeAttribute(thisEntity, EntityAttributes.GENERIC_MOVEMENT_SPEED, BOOSTING_ATTRIBUTE_ID);
            i = getEquipmentLevel(ModEnchants.BOOSTING, thisEntity);
            if (thisEntity.isSprinting() && i > 0 && SPRINT_BOOST >= 0) {
                SPRINT_BOOST++;
                if (SPRINT_BOOST < EnchantmentMappings.boostingDuration.getInt() * i)
                    modAttributeBase(thisEntity, EntityAttributes.GENERIC_MOVEMENT_SPEED, i, BOOSTING_ATTRIBUTE_ID, "boost_speed", EnchantmentMappings.boostingSpeedBoost.getDouble(), EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
                else
                    SPRINT_BOOST = -EnchantmentMappings.boostingCooldown.getInt();
            }
            if (SPRINT_BOOST > 0 && !thisEntity.isSprinting())
                SPRINT_BOOST = -EnchantmentMappings.boostingCooldown.getInt();
            if (SPRINT_BOOST < 0 && !thisEntity.isSprinting())
                SPRINT_BOOST++;
            i = getEquipmentLevel(ModEnchants.DWARVEN, thisEntity);
            if (i > 0 && (thisEntity.isSneaking() || EnchantmentMappings.dwarvenAlwaysActive.getBool())) {
                Vec3d vec = getNearestOre(EnchantmentMappings.dwarvenActiveRange.getDouble());
                double sneak = 0;
                if (thisEntity.isInSwimmingPose())
                    sneak = 0.8;
                if (thisEntity.isSneaking())
                    sneak = 0.2;
                if (vec != null) {
                    double dx = vec.getX() - thisEntity.getX();
                    double dy = vec.getY() - thisEntity.getY() - 1.0 - sneak;
                    double dz = vec.getZ() - thisEntity.getZ();
                    thisEntity.world.addParticle(ParticleTypes.ELECTRIC_SPARK, true, thisEntity.getX() + dx / 10, thisEntity.getY() + 1.0 - sneak + dy / 10, thisEntity.getZ() + dz / 10, dx / 1.25, dy / 1.25, dz / 1.25);
                }
            }
            i = getEquipmentLevel(ModEnchants.CORE_OF_THE_BLOOD_GOD, thisEntity);
            removeAttribute(thisEntity, EntityAttributes.GENERIC_ARMOR, CORE_OF_THE_BLOOD_GOD_ATTRIBUTE_ID);
            removeAttribute(thisEntity, EntityAttributes.GENERIC_ATTACK_DAMAGE, CORE_OF_THE_BLOOD_GOD_ATTRIBUTE_ID);
            removeAttribute(thisEntity, EntityAttributes.GENERIC_MAX_HEALTH, CORE_OF_THE_BLOOD_GOD_ATTRIBUTE_ID);
            if (i > 0) {
                modAttributeBase(thisEntity, EntityAttributes.GENERIC_ARMOR, 1, CORE_OF_THE_BLOOD_GOD_ATTRIBUTE_ID, "bld_armor", EnchantmentMappings.coreBloodArmorPenalty.getDouble(), EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
                modAttributeBase(thisEntity, EntityAttributes.GENERIC_ATTACK_DAMAGE, 1, CORE_OF_THE_BLOOD_GOD_ATTRIBUTE_ID, "bld_attack_damage", EnchantmentMappings.coreBloodAttackBuff.getDouble(), EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
                modAttributeBase(thisEntity, EntityAttributes.GENERIC_MAX_HEALTH, 1, CORE_OF_THE_BLOOD_GOD_ATTRIBUTE_ID, "bld_health", EnchantmentMappings.coreBloodHealthBoost.getDouble(), EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
            }
            i = getEquipmentLevel(ModEnchants.CORE_OF_THE_WARP, thisEntity);
            removeAttribute(thisEntity, EntityAttributes.GENERIC_MAX_HEALTH, CORE_OF_THE_WARP_ATTRIBUTE_ID);
            removeAttribute(thisEntity, EntityAttributes.GENERIC_MOVEMENT_SPEED, CORE_OF_THE_WARP_ATTRIBUTE_ID);
            if (i > 0) {
                modAttributeBase(thisEntity, EntityAttributes.GENERIC_MAX_HEALTH, 1, CORE_OF_THE_WARP_ATTRIBUTE_ID, "warp_health", EnchantmentMappings.coreWarpHealthPenalty.getDouble(), EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
                modAttributeBase(thisEntity, EntityAttributes.GENERIC_MOVEMENT_SPEED, 1, CORE_OF_THE_WARP_ATTRIBUTE_ID, "warp_speed", EnchantmentMappings.coreWarpSpeedBuff.getDouble(), EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
            }
            i = getEquipmentLevel(ModEnchants.CORE_OF_PURITY, thisEntity);
            removeAttribute(thisEntity, EntityAttributes.GENERIC_MAX_HEALTH, CORE_OF_PURITY_ATTRIBUTE_ID);
            removeAttribute(thisEntity, EntityAttributes.GENERIC_MOVEMENT_SPEED, CORE_OF_PURITY_ATTRIBUTE_ID);
            if (i > 0) {
                modAttributeBase(thisEntity, EntityAttributes.GENERIC_MAX_HEALTH, 1, CORE_OF_PURITY_ATTRIBUTE_ID, "ev_health", EnchantmentMappings.corePurityHealthMult.getFloat(), EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
                modAttributeBase(thisEntity, EntityAttributes.GENERIC_MOVEMENT_SPEED, 1, CORE_OF_PURITY_ATTRIBUTE_ID, "ev_speed", EnchantmentMappings.corePuritySpeedPenalty.getFloat(), EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
            }
            i = getEquipmentLevel(ModEnchants.NIGHT_VISION, thisEntity);
            if (i > 0 && (thisEntity.isSneaking() || EnchantmentMappings.nightVisionAlwaysActive.getBool()))
                thisEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 2000, 100, true, false, false));
            if ((i == 0 || i > 0 && !thisEntity.isSneaking()) && thisEntity.getStatusEffect(StatusEffects.NIGHT_VISION) != null)
                if (Objects.requireNonNull(thisEntity.getStatusEffect(StatusEffects.NIGHT_VISION)).getAmplifier() == 100)
                    thisEntity.removeStatusEffect(StatusEffects.NIGHT_VISION);
            i = getEquipmentLevel(ModEnchants.PSYCHIC, thisEntity);
            if (i > 0 && (thisEntity.isSneaking() || EnchantmentMappings.psychicAlwaysActive.getBool())) {
                EntityHitResult result = raycast(EnchantmentMappings.psychicActiveRange.getDouble());
                if (result != null)
                    if (result.getEntity() instanceof LivingEntity)
                        ((LivingEntity) result.getEntity()).addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 2, 20, true, false, false));
            }
            removeAttribute(thisEntity, EntityAttributes.GENERIC_ATTACK_DAMAGE, BARBARIC_ATTRIBUTE_ID);
            i = getLevel(ModEnchants.BARBARIC, thisEntity.getMainHandStack());
            if (i > 0) {
                if (thisEntity.getActiveHand() != null)
                    modAttributeBase(thisEntity, EntityAttributes.GENERIC_ATTACK_DAMAGE, 20 - this.getArmor(), BARBARIC_ATTRIBUTE_ID, "bar_attack_damage", EnchantmentMappings.barbaricBasePercent.getDouble(), EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
                else if (thisEntity.getMainHandStack() != null)
                    modAttributeBase(thisEntity, EntityAttributes.GENERIC_ATTACK_DAMAGE, 20 - this.getArmor(), BARBARIC_ATTRIBUTE_ID, "bar_attack_damage", EnchantmentMappings.barbaricBasePercent.getDouble(), EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
            }
            i = getEquipmentLevel(ModEnchants.BERSERK, thisEntity);
            removeAttribute(thisEntity, EntityAttributes.GENERIC_ATTACK_DAMAGE, BERSERK_ATTRIBUTE_ID);
            if (i > 0)
                modAttributeLogarithmic(thisEntity, EntityAttributes.GENERIC_ATTACK_DAMAGE, (thisEntity.getMaxHealth() - thisEntity.getHealth()) * i, BERSERK_ATTRIBUTE_ID, "ber_attack_damage", EnchantmentMappings.berserkMult.getDouble(), EnchantmentMappings.berserkLogBase.getInt(), EnchantmentMappings.berserkBase.getDouble(), EntityAttributeModifier.Operation.ADDITION);
            if (thisEntity instanceof HorseEntity) {
                i = getEquipmentLevel(ModEnchants.SWIFTNESS, thisEntity);
                removeAttribute(thisEntity, EntityAttributes.GENERIC_MOVEMENT_SPEED, SWIFTNESS_ATTRIBUTE_ID);
                if (i > 0)
                    modAttributeBase(thisEntity, EntityAttributes.GENERIC_MOVEMENT_SPEED, i, SWIFTNESS_ATTRIBUTE_ID, "swift_speed_boost", EnchantmentMappings.swiftnessSpeedMult.getDouble(), EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
                i = getEquipmentLevel(ModEnchants.BOUNDING, thisEntity);
                removeAttribute(thisEntity, EntityAttributes.HORSE_JUMP_STRENGTH, BOUNDING_JUMP_BOOST_ID);
                if (i > 0)
                    modAttributeBase(thisEntity, EntityAttributes.HORSE_JUMP_STRENGTH, i, BOUNDING_JUMP_BOOST_ID, "bounding_jump_height_boost", EnchantmentMappings.boundingJumpBoost.getDouble(), EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
            }
            if (EnchantmentHelper.getEquipmentLevel(ModEnchants.CORE_OF_NEPTUNE, thisEntity) > 0 && thisEntity.getAir() < 0)
                thisEntity.setAir(0);
            if (thisEntity.getHealth() > thisEntity.getMaxHealth())
                thisEntity.setHealth(thisEntity.getMaxHealth());
            if (EnchantmentHelper.getLevel(ModEnchants.MAGNETIC, thisEntity.getMainHandStack()) > 0) {
                List<ItemEntity> list = getNearestItems(EnchantmentHelper.getLevel(ModEnchants.MAGNETIC, thisEntity.getMainHandStack()));
                if (!list.isEmpty()) {
                    for (ItemEntity entity : list) {
                        if (entity.getVelocity().length() <= EnchantmentMappings.magneticMaxVelocity.getDouble())
                            entity.setVelocity(entity.getVelocity().subtract(entity.getPos().subtract(new Vec3d(thisEntity.getPos().x, thisEntity.getPos().y + 1, thisEntity.getPos().z)).multiply(EnchantmentHelper.getLevel(ModEnchants.MAGNETIC, thisEntity.getMainHandStack()) * EnchantmentMappings.magneticScalar.getDouble())));
                    }
                }
            }
        }
    }

    private List<ItemEntity> getNearestItems(int level) {
        double effectiveDistance = EnchantmentMappings.magneticBaseRadius.getDouble() + EnchantmentMappings.magneticAdditionalRadius.getDouble() * (level - 1);
        List<ItemEntity> list = thisEntity.world.getEntitiesByClass(ItemEntity.class, thisEntity.getBoundingBox().expand(effectiveDistance), EntityPredicates.VALID_ENTITY);
        double squareDist = Math.pow(effectiveDistance, 2);
        List<ItemEntity> finalList = new ArrayList<>();
        if (!list.isEmpty()) {
            for (ItemEntity entity : list) {
                if (getSquareDist(entity.getPos(), thisEntity.getPos()) < squareDist)
                    finalList.add(entity);
            }
        }
        return finalList;
    }

    private Vec3d getNearestOre(double range) {
        Vec3d vec3d = null;
        double lowestSquareDistance = Math.pow(range, 2);
        Vec3d vec1 = new Vec3d(thisEntity.getX(), thisEntity.getY() + 1.0, thisEntity.getZ());
        for (BlockPos pos : BlockPos.iterate(thisEntity.getBlockPos().add(-(range + 1), -(range + 1), -(range + 1)), thisEntity.getBlockPos().add(range + 1, range + 1, range + 1))) {
            Vec3d vec2 = new Vec3d(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
            Block currentBlock = thisEntity.world.getBlockState(pos).getBlock();
            if (currentBlock instanceof OreBlock && getSquareDist(vec1, vec2) <= lowestSquareDistance) {
                vec3d = vec2;
                lowestSquareDistance = getSquareDist(vec1, vec2);
            }
        }
        return vec3d;
    }

    private static double getSquareDist(Vec3d in1, Vec3d in2){
        in2 = in2.subtract(in1);
        return in2.x * in2.x + in2.y * in2.y + in2.z * in2.z;
    }

    public EntityHitResult raycast(double range) {
        Vec3d veceye = thisEntity.getEyePos();
        Vec3d vecdir = thisEntity.getRotationVec(1.0F);
        Vec3d vecext = veceye.add(vecdir.x * range, vecdir.y * range, vecdir.z * range);
        Box box = thisEntity.getBoundingBox().stretch(vecdir.multiply(range)).expand(0.0D, 0.0D, 0.0D);
        return ProjectileUtil.raycast(thisEntity, veceye, vecext, box, (entityx) -> !entityx.isSpectator() && entityx.collides(), Math.pow(range + 1, 2));
    }

    public void randomTeleport(int range, int tries){
        double d = thisEntity.getX();
        double e = thisEntity.getY();
        double f = thisEntity.getZ();

        for(int i = 0; i < tries; ++i) {
            double g = thisEntity.getX() + (thisEntity.getRandom().nextDouble() - 0.5D) * range;
            double h = MathHelper.clamp(thisEntity.getY() + (double)(thisEntity.getRandom().nextInt(range) - 8), thisEntity.world.getBottomY(), (thisEntity.world.getBottomY() + (thisEntity.world).getLogicalHeight() - 1));
            double j = thisEntity.getZ() + (thisEntity.getRandom().nextDouble() - 0.5D) * range;
            if (thisEntity.hasVehicle()) {
                thisEntity.stopRiding();
            }

            if (thisEntity.teleport(g, h, j, true)) {
                thisEntity.fallDistance = 0;
                SoundEvent soundEvent = thisEntity instanceof FoxEntity ? SoundEvents.ENTITY_FOX_TELEPORT : SoundEvents.ENTITY_ENDERMAN_TELEPORT;
                thisEntity.world.playSound(null, d, e, f, soundEvent, SoundCategory.PLAYERS, 1.0F, 1.0F);
                thisEntity.playSound(soundEvent, 1.0F, 1.0F);
                break;
            }
        }
    }

}