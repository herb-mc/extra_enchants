package com.herb_mc.extra_enchants.mixin;

import com.herb_mc.extra_enchants.commons.UUIDCommons;
import com.herb_mc.extra_enchants.registry.ModEnchants;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.attribute.EntityAttributes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.Objects;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin implements UUIDCommons, GameRenderInterfaceMixin {

    GameRenderer renderer =  (GameRenderer) (Object) this;

    @ModifyVariable(method = "updateMovementFovMultiplier", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/client/network/AbstractClientPlayerEntity;getSpeed()F"))
    public float f(float f) {
        AbstractClientPlayerEntity player = (AbstractClientPlayerEntity)renderer.getClient().getCameraEntity();
        int i = EnchantmentHelper.getEquipmentLevel(ModEnchants.STEADFAST, player);
        if (player != null && Objects.requireNonNull(player.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED)).getModifier(STEADFAST_ATTRIBUTE_ID) != null)
            f = (player.isSprinting()) ? (float) (((f - 1) * 2 + 1) / (1.15 * i + 1.15)) : (float) (((f - 1) * 2 + 1) / (i + 1));
        return f;
    }

}
