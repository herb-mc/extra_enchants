package com.herb_mc.extra_enchants.mixin;

import com.herb_mc.extra_enchants.commons.AttributeModCommons;
import com.herb_mc.extra_enchants.registry.ModEnchants;
import com.herb_mc.extra_enchants.commons.UUIDCommons;
import net.minecraft.block.Material;
import net.minecraft.block.OreBlock;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.EntityDamageSource;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

import static net.minecraft.enchantment.EnchantmentHelper.getEquipmentLevel;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin implements EntityInterfaceMixin, HorseBaseEntityInterfaceMixin, AttributeModCommons, UUIDCommons {

    @Shadow public abstract int getArmor();

    private final LivingEntity thisEntity = (LivingEntity) (Object) this;

    private boolean init = false;
    private float STEP_HEIGHT;

    private static final Random rand = new Random();
    int level = 0;

    @Inject(at = @At("TAIL"), method = "baseTick")
    protected void baseTick(CallbackInfo info){
        if (!init) {
            STEP_HEIGHT = thisEntity.stepHeight;
            init = true;
        }
    }

    @Inject(at = @At("HEAD"), method = "onDeath")
    protected void onDeath(DamageSource source, CallbackInfo info){
        if (source.getAttacker() instanceof LivingEntity) {
            int i = getEquipmentLevel(ModEnchants.LIFESTEAL, (LivingEntity) source.getAttacker());
            ((LivingEntity) source.getAttacker()).heal((float) i);
        }
    }

    @ModifyVariable(
            method = "applyArmorToDamage",
            at = @At(value = "HEAD", target = "Lnet/minecraft/entity/LivingEntity;applyArmorToDamage(Lnet/minecraft/entity/damage/DamageSource;F)F"),
            ordinal = 0)
    private float amount(float amount) {
        int i = getEquipmentLevel(ModEnchants.TOUGH, thisEntity);
        if (i > 0) return amount * (1.0F - 0.03F * i);
        return amount;
    }

    @ModifyVariable(
            method = "applyArmorToDamage",
            at = @At(value = "HEAD", target = "Lnet/minecraft/entity/LivingEntity;applyArmorToDamage(Lnet/minecraft/entity/damage/DamageSource;F)F"),
            ordinal = 0)
    private DamageSource source(DamageSource source) {
        level = 0;
        if (source.getAttacker() != null) if (source.getAttacker() instanceof LivingEntity) level = getEquipmentLevel(ModEnchants.CLEAVING, (LivingEntity) source.getAttacker());
        return source;
    }

    @ModifyVariable(
            method = "computeFallDamage",
            at = @At(value = "HEAD", target = "Lnet/minecraft/entity/LivingEntity;computeFallDamage(FF)I"),
            ordinal = 0)
    private float fallDistance(float fallDistance) {
        int i = EnchantmentHelper.getEquipmentLevel(ModEnchants.FEATHERWEIGHT, thisEntity);
        if (!thisEntity.isSneaking() && i > 0) fallDistance /= 2 * i;
        i = EnchantmentHelper.getEquipmentLevel(ModEnchants.LEAPING, thisEntity);
        if (i > 0) fallDistance -= (float) i - 1.0F;
        return fallDistance;
    }

    @ModifyVariable(
            method = "damage",
            at = @At(value = "HEAD", target = "Lnet/minecraft/entity/LivingEntity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z"),
            ordinal = 0)
    private float amount(float amount, DamageSource source) {
        int i = EnchantmentHelper.getEquipmentLevel(ModEnchants.BLOODCORE, thisEntity);
        if (source instanceof EntityDamageSource && i > 0 && rand.nextDouble() < 0.25) amount *= 1.8;
        i = EnchantmentHelper.getEquipmentLevel(ModEnchants.VOIDCORE, thisEntity);
        if (i > 0) amount *= 0.6;
        if (source.getSource() != null && source.getSource() instanceof LivingEntity) {
            i = EnchantmentHelper.getEquipmentLevel(ModEnchants.EVIOCORE, (LivingEntity) source.getSource());
            if (i > 0) amount = 1;
        }
        i = EnchantmentHelper.getEquipmentLevel(ModEnchants.EVIOCORE, thisEntity);
        if (i > 0 && thisEntity.getHealth() / thisEntity.getMaxHealth() > 0.6) amount *= 0.3;
        return amount;
    }

    @ModifyArg(
            method = "applyArmorToDamage",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/DamageUtil;getDamageLeft(FFF)F"),
            index = 1)
    private float armor(float armor) {
        double mult = 1.0D - (float) (1.8D * (level / (2.0D * level + 4.0D)));
        return (float) (armor * mult);
    }

    @ModifyArg(
            method = "jump",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;setVelocity(DDD)V"),
            index = 1)
    private double jumpVelocity(double d) {
        int i = EnchantmentHelper.getEquipmentLevel(ModEnchants.LEAPING, thisEntity);
        if (i > 0) d += 0.075F * (float) i;
        return d;
    }

    @ModifyConstant(method = "travel", constant = @Constant(doubleValue = 0.08D))
    private double d(double d){
        int i = EnchantmentHelper.getEquipmentLevel(ModEnchants.FEATHERWEIGHT, thisEntity);
        if (i > 0 && thisEntity.getVelocity().y < 0 && !thisEntity.isSneaking()) {
            d /= i + 1;
        }
        return d;
    }

    @Inject(
            method = "jump",
            at = @At("TAIL"))
    private void changeVelocity(CallbackInfo info) {
        int i = EnchantmentHelper.getEquipmentLevel(ModEnchants.LUNGING, thisEntity);
        float mult = (float) (1 + 0.1 * i);
        if (i > 0) thisEntity.setVelocity(thisEntity.getVelocity().x * mult, thisEntity.getVelocity().y, thisEntity.getVelocity().z * mult);
    }

    @Inject(at = @At("HEAD"), method = "tick")
    public void tick(CallbackInfo info) {
        int i = getEquipmentLevel(ModEnchants.WINDSTEP, thisEntity);
        if (STEP_HEIGHT > 0) {
            thisEntity.stepHeight = STEP_HEIGHT;
            if (i > 0) thisEntity.stepHeight += i * 0.4F;
        }
        i = getEquipmentLevel(ModEnchants.DWARVEN, thisEntity);
        if (i > 0) {
            Vec3d vec = getNearestOre();
            double sneak = 0;
            if (thisEntity.isSneaking()) sneak = 0.2;
            if (vec != null) {
                double dx = vec.getX() - thisEntity.getX();
                double dy = vec.getY() - thisEntity.getY() - 1.0 - sneak;
                double dz = vec.getZ() - thisEntity.getZ();
                thisEntity.world.addParticle(ParticleTypes.ELECTRIC_SPARK, true, thisEntity.getX() + dx / 10, thisEntity.getY() + 1.0 - sneak + dy / 10, thisEntity.getZ() + dz / 10, dx / 1.25, dy / 1.25, dz / 1.25);
            }
        }
        i = getEquipmentLevel(ModEnchants.BLOODCORE, thisEntity);
        removeAttribute(thisEntity, EntityAttributes.GENERIC_ARMOR, BLOODCORE_ATTRIBUTE_ID);
        removeAttribute(thisEntity, EntityAttributes.GENERIC_ATTACK_DAMAGE, BLOODCORE_ATTRIBUTE_ID);
        removeAttribute(thisEntity, EntityAttributes.GENERIC_MAX_HEALTH, BLOODCORE_ATTRIBUTE_ID);
        if (i > 0){
            modAttributeBase(thisEntity, EntityAttributes.GENERIC_ARMOR, 1, BLOODCORE_ATTRIBUTE_ID, "bld_armor", -0.2, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
            modAttributeBase(thisEntity, EntityAttributes.GENERIC_ATTACK_DAMAGE, 1, BLOODCORE_ATTRIBUTE_ID, "bld_attack_damage", 0.15, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
            modAttributeBase(thisEntity, EntityAttributes.GENERIC_MAX_HEALTH, 1, BLOODCORE_ATTRIBUTE_ID, "bld_attack_damage", 1.0, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
        }
        i = getEquipmentLevel(ModEnchants.VOIDCORE, thisEntity);
        removeAttribute(thisEntity, EntityAttributes.GENERIC_MAX_HEALTH, VOIDCORE_ATTRIBUTE_ID);
        removeAttribute(thisEntity, EntityAttributes.GENERIC_MOVEMENT_SPEED, VOIDCORE_ATTRIBUTE_ID);
        if (i > 0){
            modAttributeBase(thisEntity, EntityAttributes.GENERIC_MAX_HEALTH, 1, VOIDCORE_ATTRIBUTE_ID, "vd_health", -0.5, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
            modAttributeBase(thisEntity, EntityAttributes.GENERIC_MOVEMENT_SPEED, 1, VOIDCORE_ATTRIBUTE_ID, "vd_speed", 0.15, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
            if (thisEntity.getHealth() > thisEntity.getMaxHealth()) thisEntity.setHealth(thisEntity.getMaxHealth());
        }
        i = getEquipmentLevel(ModEnchants.EVIOCORE, thisEntity);
        removeAttribute(thisEntity, EntityAttributes.GENERIC_MAX_HEALTH, EVIOCORE_ATTRIBUTE_ID);
        removeAttribute(thisEntity, EntityAttributes.GENERIC_MOVEMENT_SPEED, EVIOCORE_ATTRIBUTE_ID);
        if (i > 0){
            modAttributeBase(thisEntity, EntityAttributes.GENERIC_MAX_HEALTH, 1, EVIOCORE_ATTRIBUTE_ID, "ev_health", 1.0, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
            modAttributeBase(thisEntity, EntityAttributes.GENERIC_MOVEMENT_SPEED, 1, EVIOCORE_ATTRIBUTE_ID, "ev_movement_speed", -0.1, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
        }
        i = getEquipmentLevel(ModEnchants.BARBARIC, thisEntity);
        removeAttribute(thisEntity, EntityAttributes.GENERIC_ATTACK_DAMAGE, BARBARIC_ATTRIBUTE_ID);
        if (i > 0) modAttributeBase(thisEntity, EntityAttributes.GENERIC_ATTACK_DAMAGE, 20 - this.getArmor(), BARBARIC_ATTRIBUTE_ID, "bar_attack_damage", 0.04, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
        i = getEquipmentLevel(ModEnchants.BERSERK, thisEntity);
        removeAttribute(thisEntity, EntityAttributes.GENERIC_ATTACK_DAMAGE, BERSERK_ATTRIBUTE_ID);
        if (i > 0) modAttributeExtended(thisEntity, EntityAttributes.GENERIC_ATTACK_DAMAGE, i, BERSERK_ATTRIBUTE_ID, "ber_attack_damage", (thisEntity.getMaxHealth() - thisEntity.getHealth()), 2.0, 2.0, 1.0, 2.0, 0.0, 4.0, 0.0, EntityAttributeModifier.Operation.ADDITION);
        if (thisEntity instanceof HorseEntity) {
            if (getEquipmentLevel(ModEnchants.SURFACE_SKIMMER, thisEntity) > 0) this.updateFloating();
            i = getEquipmentLevel(ModEnchants.SWIFTNESS, thisEntity);
            removeAttribute(thisEntity, EntityAttributes.GENERIC_MOVEMENT_SPEED, SWIFTNESS_ATTRIBUTE_ID);
            if (i > 0) modAttributeBase(thisEntity, EntityAttributes.GENERIC_MOVEMENT_SPEED, i, SWIFTNESS_ATTRIBUTE_ID, "swift_speed_boost", 0.1, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
            i = getEquipmentLevel(ModEnchants.BOUNDING, thisEntity);
            removeAttribute(thisEntity, EntityAttributes.HORSE_JUMP_STRENGTH, BOUNDING_JUMP_BOOST_ID);
            if (i > 0) modAttributeBase(thisEntity, EntityAttributes.HORSE_JUMP_STRENGTH, i, BOUNDING_JUMP_BOOST_ID, "bounding_jump_height_boost", 0.1, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
        }
    }

    private void updateFloating() {
        if (this.isInWater()) {
            thisEntity.setOnGround(true);
            double vecY = 0.019 + rand.nextGaussian() / 500;
            thisEntity.setVelocity(thisEntity.getVelocity().add(0.0D, vecY, 0.0D));
        }
        BlockPos entityPos = thisEntity.getBlockPos();
        Material mat = thisEntity.world.getBlockState((new BlockPos(entityPos.getX(), thisEntity.getBoundingBox().getMin(Direction.Axis.Y) + 0.1D, entityPos.getZ()))).getMaterial();
        if (mat == Material.WATER || mat == Material.UNDERWATER_PLANT){
            if (thisEntity.getVelocity().y < 0.0) thisEntity.setVelocity(new Vec3d(thisEntity.getVelocity().x, 0.0, thisEntity.getVelocity().z));
            thisEntity.setOnGround(true);
            this.setAir(false);
        }
    }

    public boolean isInWater() {
        return !this.getFirstUpdate() && this.getFluidHeight().getDouble(FluidTags.WATER) > 0.0D;
    }

    private Vec3d getNearestOre() {
        Vec3d vec3d = null;
        double lowestSquareDistance = 30.25;
        Vec3d vec1 = new Vec3d(thisEntity.getX(), thisEntity.getY() + 1.0, thisEntity.getZ());
        for (BlockPos pos : BlockPos.iterate(thisEntity.getBlockPos().add(-6, -6, -6), thisEntity.getBlockPos().add(6, 6, 6))) {
            Vec3d vec2 = new Vec3d(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
            if (thisEntity.world.getBlockState(pos).getBlock() instanceof OreBlock && getSquareDist(vec1, vec2) <= lowestSquareDistance) {
                vec3d = vec2;
                lowestSquareDistance = getSquareDist(vec1, vec2);
            }
        }
        return vec3d;
    }

    public double getSquareDist(Vec3d in1, Vec3d in2){
        in2 = in2.subtract(in1);
        return in2.x * in2.x + in2.y * in2.y + in2.z * in2.z;
    }

}