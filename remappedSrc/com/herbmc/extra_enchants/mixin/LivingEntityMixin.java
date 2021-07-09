package com.herbmc.extra_enchants.mixin;

import com.herbmc.extra_enchants.registry.ModEnchants;
import net.minecraft.block.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.DamageUtil;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.util.math.Box;
import net.minecraft.fluid.FluidState;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.system.CallbackI;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.UUID;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin implements LivingEntityInterfaceMixin, EntityInterfaceMixin {

    @Shadow public abstract float getMaxHealth();

    @Shadow public abstract float getHealth();

    @Shadow protected abstract void updatePostDeath();

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

        if((Object) this instanceof LivingEntity) {
            int i = EnchantmentHelper.getEquipmentLevel(ModEnchants.BERSERK, (LivingEntity) (Object) this);
            UUID BERSERK_ATTACK_DAMAGE_BOOST_ID = UUID.fromString("662A6B8D-DA3E-4C1C-8813-96EA6097278E");
            removeModAttributes();
            modAttackDamage(i, BERSERK_ATTACK_DAMAGE_BOOST_ID, "ber_attack_damage");
        }
        if((Object) this instanceof HorseEntity) {
            if (EnchantmentHelper.getEquipmentLevel(ModEnchants.SURFACE_SKIMMER, (LivingEntity) (Object) this) > 0) this.updateFloating();
            BlockPos entityPos = this.invokeGetBlockPos();
            Material mat = this.getWorld().getBlockState((new BlockPos(entityPos.getX(), this.invokeGetBoundingBox().getMin(Direction.Axis.Y) + 0.095D, entityPos.getZ()))).getMaterial();
            if(mat == Material.WATER || mat == Material.UNDERWATER_PLANT){
                if(this.invokeGetVelocity().y < 0.0) this.invokeSetVelocity(new Vec3d(this.invokeGetVelocity().x, 0.0, this.invokeGetVelocity().z));
                this.setOnGround(true);
            }
            int i = EnchantmentHelper.getEquipmentLevel(ModEnchants.SWIFTNESS, (LivingEntity) (Object) this);
            int j = EnchantmentHelper.getEquipmentLevel(ModEnchants.BOUNDING, (LivingEntity) (Object) this);
            UUID SWIFTNESS_SPEED_BOOST_ID = UUID.fromString("662A6B8D-DA3E-4C1C-8813-96EA6097277E");
            UUID BOUNDING_JUMP_BOOST_ID = UUID.fromString("662A6B8D-DA3E-4C1C-8813-96EA6097277F");
            removeHorseAttributes();
            modMovementSpeed(i, SWIFTNESS_SPEED_BOOST_ID, "swift_speed_boost", 0.1);
            modJumpHeight(j, BOUNDING_JUMP_BOOST_ID, "jump_height_boost", 0.1);
        }
    }

    private void removeModAttributes() {
        UUID BERSERK_ATTACK_DAMAGE_BOOST_ID = UUID.fromString("662A6B8D-DA3E-4C1C-8813-96EA6097278E");
        EntityAttributeInstance entityAttributeInstance = this.invokeGetAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE);
        if (entityAttributeInstance != null) {
            if (entityAttributeInstance.getModifier(BERSERK_ATTACK_DAMAGE_BOOST_ID) != null) {
                entityAttributeInstance.removeModifier(BERSERK_ATTACK_DAMAGE_BOOST_ID);
            }
        }
    }

    private void removeHorseAttributes() {
        UUID SWIFTNESS_SPEED_BOOST_ID = UUID.fromString("662A6B8D-DA3E-4C1C-8813-96EA6097277E");
        UUID BOUNDING_JUMP_BOOST_ID = UUID.fromString("662A6B8D-DA3E-4C1C-8813-96EA6097277F");
        EntityAttributeInstance entityAttributeInstance = this.invokeGetAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
        if (entityAttributeInstance != null) {
            if (entityAttributeInstance.getModifier(SWIFTNESS_SPEED_BOOST_ID) != null) {
                entityAttributeInstance.removeModifier(SWIFTNESS_SPEED_BOOST_ID);
            }
        }
        entityAttributeInstance = this.invokeGetAttributeInstance(EntityAttributes.HORSE_JUMP_STRENGTH);
        if (entityAttributeInstance != null) {
            if (entityAttributeInstance.getModifier(BOUNDING_JUMP_BOOST_ID) != null) {
                entityAttributeInstance.removeModifier(BOUNDING_JUMP_BOOST_ID);
            }
        }
    }

    private void modAttackDamage(int lvl, UUID uuid, String str) {
        EntityAttributeInstance entityAttributeInstance = this.invokeGetAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE);
        if (lvl > 0) {
            double berserkDamage = (double) (this.getMaxHealth() - this.getHealth()) * ((lvl * lvl) / (2.0D * lvl * lvl + 4.0D));
            entityAttributeInstance.addTemporaryModifier(new EntityAttributeModifier(uuid, str, berserkDamage, EntityAttributeModifier.Operation.ADDITION));
        }
    }

    private void modMovementSpeed(int lvl, UUID uuid, String str, double base) {
        EntityAttributeInstance entityAttributeInstance = this.invokeGetAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
        if (lvl > 0) {
            entityAttributeInstance.addTemporaryModifier(new EntityAttributeModifier(uuid, str, (base * lvl), EntityAttributeModifier.Operation.MULTIPLY_TOTAL));
        }
    }

    private void modJumpHeight(int lvl, UUID uuid, String str, double base) {
        EntityAttributeInstance entityAttributeInstance = this.invokeGetAttributeInstance(EntityAttributes.HORSE_JUMP_STRENGTH);
        if (lvl > 0) {
            entityAttributeInstance.addTemporaryModifier(new EntityAttributeModifier(uuid, str, (base * lvl), EntityAttributeModifier.Operation.MULTIPLY_TOTAL));
        }
    }

    private void updateFloating() {
        if (this.isInWater()) {
            this.setOnGround(true);
            this.invokeSetVelocity(this.invokeGetVelocity().add(0.0D, 0.02D, 0.0D));
        }
    }

    public boolean isInWater() {
        return !this.getFirstUpdate() && this.getFluidHeight().getDouble(FluidTags.WATER) > 0.0D;
    }

}
