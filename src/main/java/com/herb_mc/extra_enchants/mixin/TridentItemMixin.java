package com.herb_mc.extra_enchants.mixin;

import com.herb_mc.extra_enchants.registry.ModEnchants;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TridentItem;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TridentItem.class)
public class TridentItemMixin {

    @Unique TridentItem thisItem = (TridentItem) (Object) this;

    @Inject(method = "onStoppedUsing", at = @At("HEAD"))
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks, CallbackInfo info) {
        int i = thisItem.getMaxUseTime(stack) - remainingUseTicks;
        if (EnchantmentHelper.getLevel(ModEnchants.LAUNCHING, stack) > 0 && i >= 10) {
            user.setVelocity(user.getVelocity().add(user.getRotationVector().multiply(EnchantmentHelper.getLevel(ModEnchants.LAUNCHING, stack))));
        }
    }

}
