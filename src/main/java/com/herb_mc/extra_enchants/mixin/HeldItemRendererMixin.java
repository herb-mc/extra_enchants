package com.herb_mc.extra_enchants.mixin;

import com.herb_mc.extra_enchants.registry.ModEnchants;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HeldItemRenderer.class)
public class HeldItemRendererMixin {

    private AbstractClientPlayerEntity player;

    @Inject(
            method = "renderFirstPersonItem",
            at = @At("HEAD")
    )
    protected void getLocalPlayer(AbstractClientPlayerEntity player, float tickDelta, float pitch, Hand hand, float swingProgress, ItemStack item, float equipProgress, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo info) {
        this.player = player;
    }

    @ModifyConstant(
            method = "renderFirstPersonItem",
            constant = @Constant(floatValue = 20.0F)
    )
    private float bowDrawProgress(float f) {
        int strongDrawLevel = EnchantmentHelper.getLevel(ModEnchants.SNIPER, player.getActiveItem());
        int nimbleLevel = EnchantmentHelper.getLevel(ModEnchants.NIMBLE, player.getActiveItem());
        return strongDrawLevel > 0 ? f + 20F * strongDrawLevel : nimbleLevel > 0 ? nimbleLevel <= 9 ? f - nimbleLevel * 2F : 1F : f;
    }

}
