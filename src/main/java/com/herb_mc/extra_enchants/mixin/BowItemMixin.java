package com.herb_mc.extra_enchants.mixin;

import com.herb_mc.extra_enchants.lib.PersistentProjectileEntityMixinAccess;
import com.herb_mc.extra_enchants.registry.ModEnchants;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(BowItem.class)
public class BowItemMixin {

    @Unique private static int strongDrawLevel;
    @Unique private static int nimbleLevel;

    @Inject(
            method = "onStoppedUsing",
            at = @At("HEAD")
    )
    private void getEnchantLevels(ItemStack stack, World world, LivingEntity user, int remainingUseTicks, CallbackInfo ci) {
        strongDrawLevel = EnchantmentHelper.getLevel(ModEnchants.SNIPER, stack);
        nimbleLevel = EnchantmentHelper.getLevel(ModEnchants.NIMBLE, stack);
    }

    @Inject(
            method = "onStoppedUsing",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/projectile/PersistentProjectileEntity;setProperties(Lnet/minecraft/entity/Entity;FFFFF)V",
                    shift = At.Shift.AFTER
            ),
            locals = LocalCapture.CAPTURE_FAILSOFT
    )
    private void applyBowEnchants(ItemStack stack, World world, LivingEntity user, int remainingUseTicks, CallbackInfo ci, PlayerEntity playerEntity, boolean bl, ItemStack itemStack, int i, float f, boolean bl2, ArrowItem arrowItem, PersistentProjectileEntity persistentProjectileEntity) {
        ((PersistentProjectileEntityMixinAccess) persistentProjectileEntity).setPlayerOwner(true);
        if (EnchantmentHelper.getEquipmentLevel(ModEnchants.CORE_OF_PURITY, user) > 0)
            persistentProjectileEntity.setDamage(0);
        else {
            if (f == 1.0F)
                ((PersistentProjectileEntityMixinAccess) persistentProjectileEntity).setCrit(true);
            if (EnchantmentHelper.getLevel(ModEnchants.ARROW_SPEED, stack) > 0)
                persistentProjectileEntity.setVelocity(persistentProjectileEntity.getVelocity().multiply((20.0F + 3.0F * EnchantmentHelper.getLevel(ModEnchants.ARROW_SPEED, stack)) / 20.0F));
            if (EnchantmentHelper.getLevel(ModEnchants.NIMBLE, stack) > 0) {
                persistentProjectileEntity.setDamage(persistentProjectileEntity.getDamage() - 0.2F * EnchantmentHelper.getLevel(ModEnchants.NIMBLE, stack));
                if (persistentProjectileEntity.getDamage() < 0.5)
                    persistentProjectileEntity.setDamage(0.5);
            }
            if (EnchantmentHelper.getLevel(ModEnchants.SNIPER, stack) > 0) {
                persistentProjectileEntity.setVelocity(persistentProjectileEntity.getVelocity().multiply((10.0F + EnchantmentHelper.getLevel(ModEnchants.SNIPER, stack)) / 10.0F));
                persistentProjectileEntity.setDamage(persistentProjectileEntity.getDamage() + 2F * EnchantmentHelper.getLevel(ModEnchants.SNIPER, stack));
            }
            if (EnchantmentHelper.getEquipmentLevel(ModEnchants.ACE, user) > 0 && user.isFallFlying())
                persistentProjectileEntity.setDamage(persistentProjectileEntity.getDamage() + EnchantmentHelper.getEquipmentLevel(ModEnchants.ACE, user) / 2F);
            if (EnchantmentHelper.getEquipmentLevel(ModEnchants.SHARPSHOOTER, user) > 0 && user.isSneaking()) {
                persistentProjectileEntity.setDamage(persistentProjectileEntity.getDamage() + 1);
                ((PersistentProjectileEntityMixinAccess) persistentProjectileEntity).setSharpshooter(true);
            }
            if (EnchantmentHelper.getLevel(ModEnchants.EXPLOSIVE, stack) > 0)
                ((PersistentProjectileEntityMixinAccess) persistentProjectileEntity).setExplosive(EnchantmentHelper.getLevel(ModEnchants.EXPLOSIVE, stack));
            else if (EnchantmentHelper.getLevel(ModEnchants.ENDER, stack) > 0)
                ((PersistentProjectileEntityMixinAccess) persistentProjectileEntity).setEnder(true);
        }
    }

    @ModifyConstant(
            method = "getPullProgress",
            constant = @Constant(floatValue = 20.0F)
    )
    private static float longbowModPullProgress(float f) {
        return (strongDrawLevel > 0) ? f + 20F * strongDrawLevel : (nimbleLevel > 0) ? (nimbleLevel <= 9) ? f - nimbleLevel * 2F: 1F: f;
    }

}
