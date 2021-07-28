package com.herb_mc.extra_enchants.mixin;

import com.herb_mc.extra_enchants.lib.PersistentProjectileEntityMixinAccess;
import com.herb_mc.extra_enchants.registry.ModEnchants;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(CrossbowItem.class)
public class CrossbowItemMixin {

    @Inject(
            method = "createArrow",
            at = @At(
                    value = "INVOKE_ASSIGN",
                    target = "Lnet/minecraft/item/ArrowItem;createArrow(Lnet/minecraft/world/World;Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/LivingEntity;)Lnet/minecraft/entity/projectile/PersistentProjectileEntity;"
            ),
            locals = LocalCapture.CAPTURE_FAILSOFT
    )
    private static void applyCrossBowEnchants(World world, LivingEntity entity, ItemStack crossbow, ItemStack arrow, CallbackInfoReturnable<PersistentProjectileEntity> ci, ArrowItem arrowItem, PersistentProjectileEntity persistentProjectileEntity) {
        ((PersistentProjectileEntityMixinAccess) persistentProjectileEntity).setPlayerOwner(true);
        ((PersistentProjectileEntityMixinAccess) persistentProjectileEntity).setCrit(true);
        if (EnchantmentHelper.getEquipmentLevel(ModEnchants.CORE_OF_PURITY, entity) > 0)
            persistentProjectileEntity.setDamage(0);
        else {
            if (EnchantmentHelper.getLevel(ModEnchants.EXPOSING, crossbow) > 0)
                ((PersistentProjectileEntityMixinAccess) persistentProjectileEntity).setExposing(EnchantmentHelper.getLevel(ModEnchants.EXPOSING, crossbow) * 30);
            if (EnchantmentHelper.getLevel(ModEnchants.THUNDERBOLT, crossbow) > 0)
                ((PersistentProjectileEntityMixinAccess) persistentProjectileEntity).setThunderbolt(true);
        }
    }

}
