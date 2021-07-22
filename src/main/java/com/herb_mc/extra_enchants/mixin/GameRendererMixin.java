package com.herb_mc.extra_enchants.mixin;

import com.herb_mc.extra_enchants.commons.UUIDCommons;
import com.herb_mc.extra_enchants.registry.ModEnchants;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.attribute.EntityAttributes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import com.herb_mc.extra_enchants.ExtraEnchantsMod;

import java.util.Objects;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin implements UUIDCommons, GameRenderInterfaceMixin {

    @Unique GameRenderer renderer =  (GameRenderer) (Object) this;

    @ModifyVariable(method = "updateMovementFovMultiplier", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/client/network/AbstractClientPlayerEntity;getSpeed()F"))
    public float f(float f) {
        AbstractClientPlayerEntity player = (AbstractClientPlayerEntity)renderer.getClient().getCameraEntity();
        int i = EnchantmentHelper.getEquipmentLevel(ModEnchants.STEADFAST, player);
        if (player != null && Objects.requireNonNull(player.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED)).getModifier(STEADFAST_ATTRIBUTE_ID) != null)
            f = (player.isSprinting()) ? (float) (((f - 1) * 2 + 1) / (1.15 * i + 1.15)) : (float) (((f - 1) * 2 + 1) / (i + 1));
        return f;
    }

    @Inject(method = "getFov(Lnet/minecraft/client/render/Camera;FZ)D", at = @At("RETURN"), cancellable = true)
    public void sharpshooterFOVMod(CallbackInfoReturnable<Double> callbackInfo) {
        ExtraEnchantsMod.client.options.smoothCameraEnabled = false;
        if((EnchantmentHelper.getEquipmentLevel(ModEnchants.SHARPSHOOTER, (AbstractClientPlayerEntity)renderer.getClient().getCameraEntity()) > 0 && (Objects.requireNonNull(renderer.getClient().getCameraEntity())).isSneaking())) {
            ExtraEnchantsMod.client.options.smoothCameraEnabled = true;
            double fov = callbackInfo.getReturnValue();
            callbackInfo.setReturnValue(fov / 3);
        }
    }

}
