package com.herb_mc.extra_enchants.mixin;

import com.herb_mc.extra_enchants.interfaces.AttributeModifierInterface;
import com.herb_mc.extra_enchants.registry.ModEnchants;
import com.herb_mc.extra_enchants.interfaces.GlobalUUIDInterface;
import net.minecraft.block.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.DamageUtil;
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
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin implements LivingEntityInterfaceMixin, EntityInterfaceMixin, HorseBaseEntityInterfaceMixin, AttributeModifierInterface, GlobalUUIDInterface {

    private static final Random rand = new Random();

    @Inject(at = @At("HEAD"), method = "onDeath")
    protected void onDeath(DamageSource source, CallbackInfo info){
        if (source.getAttacker() instanceof LivingEntity) {
            int i = EnchantmentHelper.getEquipmentLevel(ModEnchants.LIFESTEAL, (LivingEntity) source.getAttacker());
            ((LivingEntity) source.getAttacker()).heal((float) i);
        }
    }

    @Inject(method = "applyArmorToDamage", at = @At(value = "HEAD"), cancellable = true)
    protected void applyArmorToDamage(DamageSource source, float amount, CallbackInfoReturnable<Float> info){
        if(source.getAttacker() instanceof LivingEntity) {
            int i = EnchantmentHelper.getEquipmentLevel(ModEnchants.CLEAVING, (LivingEntity) source.getAttacker());
            if (!source.bypassesArmor() && i > 0) {
                float mult = 1.0F - (float) i / (2 * i + 6);
                this.invokeDamageArmor(source, amount);
                amount = DamageUtil.getDamageLeft(amount, (float) this.invokeGetArmor() * mult, (float) this.invokeGetAttributeValue(EntityAttributes.GENERIC_ARMOR_TOUGHNESS));
                info.setReturnValue(amount);
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "tick")
    public void tick(CallbackInfo info) {
        LivingEntity thisEntity = (LivingEntity) (Object) this;
        int i = EnchantmentHelper.getEquipmentLevel(ModEnchants.BERSERK, thisEntity);
        removeAttribute(thisEntity, EntityAttributes.GENERIC_ATTACK_DAMAGE, BERSERK_ATTACK_DAMAGE_BOOST_ID);
        if (i > 0) modAttributeExtended(thisEntity, EntityAttributes.GENERIC_ATTACK_DAMAGE, i, BERSERK_ATTACK_DAMAGE_BOOST_ID, "ber_attack_damage", (thisEntity.getMaxHealth() - thisEntity.getHealth()), 2.0, 2.0, 1.0, 2.0, 0.0, 4.0, 0.0, EntityAttributeModifier.Operation.ADDITION);
        if(thisEntity instanceof HorseEntity) {
            if (EnchantmentHelper.getEquipmentLevel(ModEnchants.SURFACE_SKIMMER, thisEntity) > 0) this.updateFloating();
            i = EnchantmentHelper.getEquipmentLevel(ModEnchants.SWIFTNESS, thisEntity);
            removeAttribute(thisEntity, EntityAttributes.GENERIC_MOVEMENT_SPEED, SWIFTNESS_SPEED_BOOST_ID);
            if (i > 0) modAttributeBase(thisEntity, EntityAttributes.GENERIC_MOVEMENT_SPEED, i, SWIFTNESS_SPEED_BOOST_ID, "swift_speed_boost", 0.1, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
            i = EnchantmentHelper.getEquipmentLevel(ModEnchants.BOUNDING, thisEntity);
            removeAttribute(thisEntity, EntityAttributes.HORSE_JUMP_STRENGTH, BOUNDING_JUMP_BOOST_ID);
            if (i > 0) modAttributeBase(thisEntity, EntityAttributes.HORSE_JUMP_STRENGTH, i, BOUNDING_JUMP_BOOST_ID, "bounding_jump_height_boost", 0.1, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
        }
    }

    private void updateFloating() {
        if (this.isInWater()) {
            this.setOnGround(true);
            double vecY = 0.019 + rand.nextGaussian() / 500;
            this.invokeSetVelocity(this.invokeGetVelocity().add(0.0D, vecY, 0.0D));
        }
        BlockPos entityPos = this.invokeGetBlockPos();
        Material mat = this.getWorld().getBlockState((new BlockPos(entityPos.getX(), this.invokeGetBoundingBox().getMin(Direction.Axis.Y) + 0.1D, entityPos.getZ()))).getMaterial();
        if(mat == Material.WATER || mat == Material.UNDERWATER_PLANT){
            if(this.invokeGetVelocity().y < 0.0) this.invokeSetVelocity(new Vec3d(this.invokeGetVelocity().x, 0.0, this.invokeGetVelocity().z));
            this.setOnGround(true);
            this.setAir(false);
        }
    }

    public boolean isInWater() {
        return !this.getFirstUpdate() && this.getFluidHeight().getDouble(FluidTags.WATER) > 0.0D;
    }

}