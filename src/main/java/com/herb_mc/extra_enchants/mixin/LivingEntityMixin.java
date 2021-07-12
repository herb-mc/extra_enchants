package com.herb_mc.extra_enchants.mixin;

import com.herb_mc.extra_enchants.interfaces.AttributeModifierInterface;
import com.herb_mc.extra_enchants.registry.ModEnchants;
import com.herb_mc.extra_enchants.interfaces.GlobalUUIDInterface;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.HorseEntity;
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
public abstract class LivingEntityMixin implements LivingEntityInterfaceMixin, EntityInterfaceMixin, HorseBaseEntityInterfaceMixin, AttributeModifierInterface, GlobalUUIDInterface {

    @Shadow protected abstract void fall(double heightDifference, boolean onGround, BlockState landedState, BlockPos landedPosition);

    private LivingEntity thisEntity = (LivingEntity) (Object) this;

    private boolean init = false;
    private float STEP_HEIGHT;

    private static final Random rand = new Random();
    int level = 0;

    @Inject(at = @At("TAIL"), method = "baseTick")
    protected void baseTick(CallbackInfo info){
        if(!init) {
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
        if(i > 0) return amount * (1.0F - 0.03F * i);
        return amount;
    }

    @ModifyVariable(
            method = "applyArmorToDamage",
            at = @At(value = "HEAD", target = "Lnet/minecraft/entity/LivingEntity;applyArmorToDamage(Lnet/minecraft/entity/damage/DamageSource;F)F"),
            ordinal = 0)
    private DamageSource source(DamageSource source) {
        level = 0;
        if(source.getAttacker() != null) if(source.getAttacker() instanceof LivingEntity) level = getEquipmentLevel(ModEnchants.CLEAVING, (LivingEntity) source.getAttacker());
        return source;
    }

    @ModifyVariable(
            method = "jump",
            at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/entity/LivingEntity;getJumpVelocity()F"),
            index = 1)
    private float f(float f) {
        int i = EnchantmentHelper.getEquipmentLevel(ModEnchants.LEAPING,thisEntity);
        if(i > 0) f += 0.07F * (float) i;
        return f;
    }

    @ModifyVariable(
            method = "computeFallDamage",
            at = @At(value = "HEAD", target = "Lnet/minecraft/entity/LivingEntity;computeFallDamage(FF)I"),
            ordinal = 0)
    private float fallDistance(float fallDistance) {
        int i = EnchantmentHelper.getEquipmentLevel(ModEnchants.LEAPING,thisEntity);
        if(i > 0) fallDistance -= (float) i - 1.0F;
        return fallDistance;
    }

    @ModifyArg(
            method = "applyArmorToDamage",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/DamageUtil;getDamageLeft(FFF)F"),
            index = 1
    )
    private float armor(float armor) {
        double mult = 1.0D - (float) (1.8D * (level / (2.0D * level + 4.0D)));
        return (float) (armor * mult);
    }

    @Inject(at = @At("HEAD"), method = "tick")
    public void tick(CallbackInfo info) {
        int i = getEquipmentLevel(ModEnchants.WINDSTEP, thisEntity);
        if(STEP_HEIGHT > 0) {
            thisEntity.stepHeight = STEP_HEIGHT;
            if (i > 0) thisEntity.stepHeight += i * 0.4F;
        }
        i = getEquipmentLevel(ModEnchants.BERSERK, thisEntity);
        removeAttribute(thisEntity, EntityAttributes.GENERIC_ATTACK_DAMAGE, BERSERK_ATTACK_DAMAGE_BOOST_ID);
        if (i > 0) modAttributeExtended(thisEntity, EntityAttributes.GENERIC_ATTACK_DAMAGE, i, BERSERK_ATTACK_DAMAGE_BOOST_ID, "ber_attack_damage", (thisEntity.getMaxHealth() - thisEntity.getHealth()), 2.0, 2.0, 1.0, 2.0, 0.0, 4.0, 0.0, EntityAttributeModifier.Operation.ADDITION);
        if(thisEntity instanceof HorseEntity) {
            if (getEquipmentLevel(ModEnchants.SURFACE_SKIMMER, thisEntity) > 0) this.updateFloating();
            i = getEquipmentLevel(ModEnchants.SWIFTNESS, thisEntity);
            removeAttribute(thisEntity, EntityAttributes.GENERIC_MOVEMENT_SPEED, SWIFTNESS_SPEED_BOOST_ID);
            if (i > 0) modAttributeBase(thisEntity, EntityAttributes.GENERIC_MOVEMENT_SPEED, i, SWIFTNESS_SPEED_BOOST_ID, "swift_speed_boost", 0.1, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
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
        if(mat == Material.WATER || mat == Material.UNDERWATER_PLANT){
            if(thisEntity.getVelocity().y < 0.0) thisEntity.setVelocity(new Vec3d(thisEntity.getVelocity().x, 0.0, thisEntity.getVelocity().z));
            thisEntity.setOnGround(true);
            this.setAir(false);
        }
    }

    public boolean isInWater() {
        return !this.getFirstUpdate() && this.getFluidHeight().getDouble(FluidTags.WATER) > 0.0D;
    }

}