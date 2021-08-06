package com.herb_mc.extra_enchants.mixin;

import com.herb_mc.extra_enchants.lib.EnchantmentMappings;
import com.herb_mc.extra_enchants.registry.ModEnchants;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
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

@Environment(EnvType.CLIENT)
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
        if (strongDrawLevel > 0)
            f = f + f * EnchantmentMappings.sniperDrawMult.getFloat() * strongDrawLevel;
        else if (nimbleLevel > 0)
            f = f + f * nimbleLevel * EnchantmentMappings.nimbleDrawMult.getFloat();
        return Math.max(f, 1F);
    }

}
