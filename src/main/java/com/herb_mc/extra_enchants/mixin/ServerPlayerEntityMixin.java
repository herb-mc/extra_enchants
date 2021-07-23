package com.herb_mc.extra_enchants.mixin;

import com.herb_mc.extra_enchants.registry.ModEnchants;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin {

    @Unique ServerPlayerEntity thisEntity = (ServerPlayerEntity) (Object) this;

    @Inject(method = "copyFrom", at = @At("HEAD"))
    protected void copySoulbound(ServerPlayerEntity oldPlayer, boolean alive, CallbackInfo info) {
        if (!alive) {
            PlayerInventory inventory = oldPlayer.getInventory();
            for (int slot = 0; slot < inventory.size(); slot++)
                if (EnchantmentHelper.getLevel(ModEnchants.SOULBOUND, inventory.getStack(slot)) > 0) {
                    if (slot >= 36 && slot <= 40)
                        thisEntity.getInventory().insertStack(slot, inventory.getStack(slot));
                    else
                        thisEntity.getInventory().insertStack(inventory.getStack(slot));
                }
        }
    }

}
