package com.herb_mc.extra_enchants.mixin;

import com.herb_mc.extra_enchants.lib.AttributeModCommons;
import com.herb_mc.extra_enchants.lib.EnchantmentMappings;
import com.herb_mc.extra_enchants.registry.ModEnchants;
import com.herb_mc.extra_enchants.lib.UUIDCommons;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin implements AttributeModCommons, UUIDCommons {

    @Unique private final LivingEntity thisEntity = (PlayerEntity) (Object) this;

    @ModifyArg(
            method = "disableShield",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/player/ItemCooldownManager;set(Lnet/minecraft/item/Item;I)V"
            )
    )
    private int decreaseShieldCooldown(int i) {
        return thisEntity.getActiveItem() != null && EnchantmentHelper.getLevel(ModEnchants.STALWART, thisEntity.getActiveItem()) > 0 ? EnchantmentMappings.stalwartCooldown.getInt() : i;
    }

    @ModifyVariable(
            method = "getBlockBreakingSpeed",
            at = @At(
                    value = "INVOKE_ASSIGN",
                    target = "Lnet/minecraft/entity/player/PlayerInventory;getBlockBreakingSpeed(Lnet/minecraft/block/BlockState;)F"
            )
    )
    private float addTerraformingSpeed(float f) {
        return (thisEntity.getActiveHand() != null && EnchantmentHelper.getLevel(ModEnchants.TERRAFORMING, thisEntity.getStackInHand(thisEntity.getActiveHand())) > 0 && f > 1.0) ? f + EnchantmentMappings.terraformingToolSpeed.getInt() : f;
    }

    @Inject(
            at = @At("HEAD"),
            method = "tick"
    )
    public void tickAllEnchants(CallbackInfo info) {
        int i = EnchantmentHelper.getEquipmentLevel(ModEnchants.DEXTROUS, thisEntity);
        removeAttribute(thisEntity, EntityAttributes.GENERIC_ATTACK_SPEED, DEXTERITY_ATTRIBUTE_ID);
        if (i > 0)
            modAttributeBase(thisEntity, EntityAttributes.GENERIC_ATTACK_SPEED, i, DEXTERITY_ATTRIBUTE_ID, "dex_attack_speed", EnchantmentMappings.dextrousAttackSpeedBoost.getDouble(), EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
        i = EnchantmentHelper.getEquipmentLevel(ModEnchants.WEIGHTED, thisEntity);
        removeAttribute(thisEntity, EntityAttributes.GENERIC_ATTACK_SPEED, WEIGHTED_ATTRIBUTE_ID);
        removeAttribute(thisEntity, EntityAttributes.GENERIC_ATTACK_DAMAGE, WEIGHTED_ATTRIBUTE_ID);
        if (i > 0) {
            modAttributeBase(thisEntity, EntityAttributes.GENERIC_ATTACK_SPEED, i, WEIGHTED_ATTRIBUTE_ID, "wei_attack_speed", EnchantmentMappings.weightedAttackSpeedPenalty.getDouble(), EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
            modAttributeBase(thisEntity, EntityAttributes.GENERIC_ATTACK_DAMAGE, i, WEIGHTED_ATTRIBUTE_ID, "wei_attack_damage", EnchantmentMappings.weightedAttackDamageIncrease.getDouble(), EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
        }
        i = EnchantmentHelper.getEquipmentLevel(ModEnchants.ARCHITECT, thisEntity);
        removeAttribute(thisEntity, ReachEntityAttributes.REACH, ARCHITECT_ATTRIBUTE_ID);
        if (i > 0)
            modAttributeBase(thisEntity, ReachEntityAttributes.REACH, i, ARCHITECT_ATTRIBUTE_ID, "arch_reach_boost", EnchantmentMappings.architectRangeBoost.getDouble(), EntityAttributeModifier.Operation.ADDITION);
        i = EnchantmentHelper.getEquipmentLevel(ModEnchants.STEADFAST, thisEntity);
        if (i > 0) {
            ItemStack itemStack = thisEntity.getActiveItem();
            removeAttribute(thisEntity, EntityAttributes.GENERIC_MOVEMENT_SPEED, STEADFAST_ATTRIBUTE_ID);
            if (thisEntity.isUsingItem() && (itemStack.isOf(Items.BOW) || itemStack.isOf(Items.CROSSBOW) || itemStack.isOf(Items.TRIDENT))) {
                modAttributeBase(thisEntity, EntityAttributes.GENERIC_MOVEMENT_SPEED, i, STEADFAST_ATTRIBUTE_ID, "steadfast_movement_speed", EnchantmentMappings.steadfastSpeedMult.getDouble(), EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
            }
        }
    }

}
