package com.herb_mc.extra_enchants.mixin;

import com.herb_mc.extra_enchants.lib.PersistentProjectileEntityMixinAccess;
import com.herb_mc.extra_enchants.registry.ModEnchants;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TridentItem;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(TridentItem.class)
public class TridentItemMixin {

    @Unique TridentItem thisItem = (TridentItem) (Object) this;
    Vec3d velocity;

    @Inject(
            method = "onStoppedUsing",
            at = @At("HEAD")
    )
    public void addLaunchingVelocity(ItemStack stack, World world, LivingEntity user, int remainingUseTicks, CallbackInfo info) {
        int i = thisItem.getMaxUseTime(stack) - remainingUseTicks;
        velocity = user.getRotationVector().multiply(EnchantmentHelper.getLevel(ModEnchants.LAUNCHING, stack));
        if (EnchantmentHelper.getLevel(ModEnchants.LAUNCHING, stack) > 0 && i >= 10 && EnchantmentHelper.getLevel(Enchantments.RIPTIDE, stack) <= 0) {
            user.setVelocity(user.getVelocity().add(velocity));
            user.fallDistance = 0;
        }
    }

    @Inject(
            method = "onStoppedUsing",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/World;spawnEntity(Lnet/minecraft/entity/Entity;)Z"
            ),
            locals = LocalCapture.CAPTURE_FAILSOFT
    )
    public void addAttributes(ItemStack stack, World world, LivingEntity user, int remainingUseTicks, CallbackInfo info, PlayerEntity playerEntity, int i, TridentEntity tridentEntity){
        tridentEntity.setVelocity(tridentEntity.getVelocity().subtract(velocity.multiply(0.7)));
        if (EnchantmentHelper.getEquipmentLevel(ModEnchants.ACE, user) > 0)
            ((PersistentProjectileEntityMixinAccess) tridentEntity).setAce(EnchantmentHelper.getEquipmentLevel(ModEnchants.ACE, user));
        if (EnchantmentHelper.getEquipmentLevel(ModEnchants.CORE_OF_NEPTUNE, user) > 0)
            ((PersistentProjectileEntityMixinAccess) tridentEntity).setNeptune(true);
        if (EnchantmentHelper.getEquipmentLevel(ModEnchants.CORE_OF_PURITY, user) > 0)
            ((PersistentProjectileEntityMixinAccess) tridentEntity).setPurity(true);
    }

}
