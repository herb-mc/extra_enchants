package com.herb_mc.extra_enchants.mixin;

import com.herb_mc.extra_enchants.interfaces.AttributeModifierInterface;
import com.herb_mc.extra_enchants.registry.ModEnchants;
import com.herb_mc.extra_enchants.interfaces.GlobalUUIDInterface;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin implements AttributeModifierInterface, GlobalUUIDInterface {

    private final LivingEntity thisEntity = (PlayerEntity) (Object) this;

    @ModifyVariable(
            method = "getBlockBreakingSpeed",
            at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/entity/player/PlayerInventory;getBlockBreakingSpeed(Lnet/minecraft/block/BlockState;)F"))
    private float f(float f) {
        int i = EnchantmentHelper.getEquipmentLevel(ModEnchants.TERRAFORMING,thisEntity);
        if(i > 0 && f > 1.0) f += 58.0F;
        return f;
    }

    @Inject(at = @At("HEAD"), method = "tick")
    public void tick(CallbackInfo info) {
        int i = EnchantmentHelper.getEquipmentLevel(ModEnchants.DEXTROUS, thisEntity);
        removeAttribute(thisEntity, EntityAttributes.GENERIC_ATTACK_SPEED, DEXTERITY_ATTACK_SPEED_BOOST_ID);
        if (i > 0)
            modAttributeBase(thisEntity, EntityAttributes.GENERIC_ATTACK_SPEED, i, DEXTERITY_ATTACK_SPEED_BOOST_ID, "dex_attack_speed", 0.1D, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
        i = EnchantmentHelper.getEquipmentLevel(ModEnchants.WEIGHTED, thisEntity);
        removeAttribute(thisEntity, EntityAttributes.GENERIC_ATTACK_SPEED, WEIGHTED_ATTACK_SPEED_BOOST_ID);
        removeAttribute(thisEntity, EntityAttributes.GENERIC_ATTACK_DAMAGE, WEIGHTED_ATTACK_DAMAGE_BOOST_ID);
        if (i > 0) {
            modAttributeBase(thisEntity, EntityAttributes.GENERIC_ATTACK_SPEED, i, WEIGHTED_ATTACK_SPEED_BOOST_ID, "wei_attack_speed", -0.15D, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
            modAttributeBase(thisEntity, EntityAttributes.GENERIC_ATTACK_DAMAGE, i, WEIGHTED_ATTACK_DAMAGE_BOOST_ID, "wei_attack_damage", 0.2D, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
        }
        i = EnchantmentHelper.getEquipmentLevel(ModEnchants.ARCHITECT, thisEntity);
        removeAttribute(thisEntity, ReachEntityAttributes.REACH, ARCHITECT_REACH_BOOST_ID);
        removeAttribute(thisEntity, ReachEntityAttributes.ATTACK_RANGE, ARCHITECT_RANGE_BOOST_ID);
        if (i > 0) {
            modAttributeBase(thisEntity, ReachEntityAttributes.REACH, i, ARCHITECT_REACH_BOOST_ID, "arch_reach_boost", 1.0, EntityAttributeModifier.Operation.ADDITION);
            modAttributeBase(thisEntity, ReachEntityAttributes.ATTACK_RANGE, i, ARCHITECT_RANGE_BOOST_ID, "arch_range_boost", -1.0, EntityAttributeModifier.Operation.ADDITION);
        }
    }

}
