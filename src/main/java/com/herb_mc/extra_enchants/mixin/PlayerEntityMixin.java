package com.herb_mc.extra_enchants.mixin;

import com.herb_mc.extra_enchants.interfaces.AttributeModifierInterface;
import com.herb_mc.extra_enchants.registry.ModEnchants;
import com.herb_mc.extra_enchants.interfaces.GlobalUUIDInterface;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity implements AttributeModifierInterface, GlobalUUIDInterface {

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Nullable
    public EntityAttributeInstance getAttributeInstance(EntityAttribute attribute) {
        return this.getAttributes().getCustomInstance(attribute);
    }

    @Inject(at = @At("HEAD"), method = "tick")
    public void tick(CallbackInfo info) {
        LivingEntity thisEntity = (LivingEntity) (Object) this;
        int i = EnchantmentHelper.getEquipmentLevel(ModEnchants.DEXTROUS, thisEntity);
        removeAttribute(this, EntityAttributes.GENERIC_ATTACK_SPEED, DEXTERITY_ATTACK_SPEED_BOOST_ID);
        if (i > 0)
            modAttributeBase(this, EntityAttributes.GENERIC_ATTACK_SPEED, i, DEXTERITY_ATTACK_SPEED_BOOST_ID, "dex_attack_speed", 0.1D, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
        i = EnchantmentHelper.getEquipmentLevel(ModEnchants.WEIGHTED, thisEntity);
        removeAttribute(this, EntityAttributes.GENERIC_ATTACK_SPEED, WEIGHTED_ATTACK_SPEED_BOOST_ID);
        removeAttribute(this, EntityAttributes.GENERIC_ATTACK_DAMAGE, WEIGHTED_ATTACK_DAMAGE_BOOST_ID);
        if (i > 0) {
            modAttributeBase(this, EntityAttributes.GENERIC_ATTACK_SPEED, i, WEIGHTED_ATTACK_SPEED_BOOST_ID, "wei_attack_speed", -0.15D, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
            modAttributeBase(this, EntityAttributes.GENERIC_ATTACK_DAMAGE, i, WEIGHTED_ATTACK_DAMAGE_BOOST_ID, "wei_attack_damage", 0.2D, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
        }
        i = EnchantmentHelper.getEquipmentLevel(ModEnchants.ARCHITECT, thisEntity);
        removeAttribute(this, ReachEntityAttributes.REACH, ARCHITECT_REACH_BOOST_ID);
        removeAttribute(this, ReachEntityAttributes.ATTACK_RANGE, ARCHITECT_RANGE_BOOST_ID);
        if (i > 0) {
            modAttributeBase(this, ReachEntityAttributes.REACH, i, ARCHITECT_REACH_BOOST_ID, "arch_reach_boost", 1.0, EntityAttributeModifier.Operation.ADDITION);
            modAttributeBase(this, ReachEntityAttributes.ATTACK_RANGE, i, ARCHITECT_RANGE_BOOST_ID, "arch_range_boost", -1.0, EntityAttributeModifier.Operation.ADDITION);
        }
    }

}
