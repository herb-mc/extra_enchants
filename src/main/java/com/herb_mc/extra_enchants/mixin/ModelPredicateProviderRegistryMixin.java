package com.herb_mc.extra_enchants.mixin;

import com.herb_mc.extra_enchants.lib.EnchantmentMappings;
import com.herb_mc.extra_enchants.registry.ModEnchants;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin(ModelPredicateProviderRegistry.class)
public class ModelPredicateProviderRegistryMixin {

    private static int strongDrawLevel;
    private static int nimbleLevel;

    @Inject(
            method = "method_27890(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/world/ClientWorld;Lnet/minecraft/entity/LivingEntity;I)F",
            at = @At("HEAD"),
            remap = false
    )
    private static void getStackStrongDraw(ItemStack stack, ClientWorld world, LivingEntity livingEntity, int seed, CallbackInfoReturnable<Float> info) {
        strongDrawLevel = EnchantmentHelper.getLevel(ModEnchants.SNIPER, stack);
        nimbleLevel = EnchantmentHelper.getLevel(ModEnchants.NIMBLE, stack);
    }

    @Inject(
            method = "method_27890(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/world/ClientWorld;Lnet/minecraft/entity/LivingEntity;I)F",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/LivingEntity;getActiveItem()Lnet/minecraft/item/ItemStack;"
            ),
            cancellable = true)
    private static void bowModelEffects(ItemStack stack, ClientWorld world, LivingEntity livingEntity, int seed, CallbackInfoReturnable<Float> info) {
        float f  = (float)(stack.getMaxUseTime() - livingEntity.getItemUseTimeLeft());
        float div = 20.0F;
        if (strongDrawLevel > 0) {
            div = div + div * EnchantmentMappings.sniperDrawMult.getFloat() * strongDrawLevel;
            info.setReturnValue(f / div);
        }
        else if (nimbleLevel > 0) {
            div = div + div * nimbleLevel * EnchantmentMappings.nimbleDrawMult.getFloat();
            if (div <= 1F)
                div = 1F;
            info.setReturnValue(f / div);
        }
    }

}
