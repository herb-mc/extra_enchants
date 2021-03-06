package com.herb_mc.extra_enchants.mixin;

import com.herb_mc.extra_enchants.lib.EnchantmentMappings;
import com.herb_mc.extra_enchants.lib.UUIDCommons;
import com.herb_mc.extra_enchants.registry.ModEnchants;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
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

@Environment(EnvType.CLIENT)
@Mixin(GameRenderer.class)
public abstract class GameRendererMixin implements UUIDCommons {

    @ModifyVariable(
            method = "updateMovementFovMultiplier",
            at = @At(
                    value = "INVOKE_ASSIGN",
                    target = "Lnet/minecraft/client/network/AbstractClientPlayerEntity;getSpeed()F"
            )
    )
    public float removeFOVModFromSteadfast(float f) {
        if (((GameRenderer) (Object) this).getClient().getCameraEntity() instanceof AbstractClientPlayerEntity player) {
            float i = (float) ((EnchantmentHelper.getEquipmentLevel(ModEnchants.STEADFAST, player) + 1) * EnchantmentMappings.steadfastSpeedMult.getDouble());
            if (Objects.requireNonNull(player.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED)).getModifier(STEADFAST_ATTRIBUTE_ID) != null)
                f = (player.isSprinting()) ? (float) (((f - 1) * 2 + 1) / 1.15 * i) : ((f - 1) * 2 + 1) / i;
        }
        return f;
    }

    @Inject(
            method = "getFov(Lnet/minecraft/client/render/Camera;FZ)D",
            at = @At("RETURN"),
            cancellable = true
    )
    public void sharpShooterFOVMod(CallbackInfoReturnable<Double> callbackInfo) {
        MinecraftClient.getInstance().options.smoothCameraEnabled = false;
        if (((GameRenderer) (Object) this).getClient().getCameraEntity() instanceof AbstractClientPlayerEntity player)
            if((EnchantmentHelper.getEquipmentLevel(ModEnchants.SHARPSHOOTER, player) > 0 && (player.isSneaking()))) {
                MinecraftClient.getInstance().options.smoothCameraEnabled = true;
                double fov = callbackInfo.getReturnValue();
                callbackInfo.setReturnValue(fov * EnchantmentMappings.sharpshooterFOVScale.getFloat());
            }
    }

}
