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
import org.spongepowered.asm.mixin.injection.*;
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

    @ModifyVariable(
            method = "renderFirstPersonItem",
            at = @At(
                    value = "STORE",
                    ordinal = 2
            ),
            index = 16
    )
    private float bowGUIModelEffects(float f){
        int strongDrawLevel = EnchantmentHelper.getLevel(ModEnchants.SNIPER, player.getActiveItem());
        int nimbleLevel = EnchantmentHelper.getLevel(ModEnchants.NIMBLE, player.getActiveItem());
        f *= 20F;
        float div = 20.0F;
        if (strongDrawLevel > 0) {
            div = div + div * EnchantmentMappings.sniperDrawMult.getFloat() * strongDrawLevel;
        }
        else if (nimbleLevel > 0) {
            div = div + div * nimbleLevel * EnchantmentMappings.nimbleDrawMult.getFloat();
            if (div <= 1F)
                div = 1F;
        }
        return f / div;
    }

}
