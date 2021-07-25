package com.herb_mc.extra_enchants.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.VertexConsumers;
import net.minecraft.client.render.entity.feature.HorseArmorFeatureRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.item.HorseArmorItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Environment(EnvType.CLIENT)
@Mixin(HorseArmorFeatureRenderer.class)
public class HorseArmorRendererMixin {

    @ModifyVariable(
            method = "render",
            at = @At(
                    value = "INVOKE_ASSIGN",
                    target = "Lnet/minecraft/client/render/VertexConsumerProvider;getBuffer(Lnet/minecraft/client/render/RenderLayer;)Lnet/minecraft/client/render/VertexConsumer;"
            )
    )
    private VertexConsumer renderHorseArmorGlint(VertexConsumer vertexConsumer, MatrixStack matrixStackIn, VertexConsumerProvider vertexConsumerProvider, int i, HorseEntity horseEntity, float f, float g, float h, float j, float k, float l) {
        return renderGlint(vertexConsumer, vertexConsumerProvider, horseEntity);
    }

    private VertexConsumer renderGlint(VertexConsumer vertexConsumer, VertexConsumerProvider vertexConsumerProvider, HorseEntity horseEntity) {
        if (horseEntity.getArmorType().hasEnchantments() && horseEntity.getArmorType().getItem() instanceof HorseArmorItem)
            return VertexConsumers.union(vertexConsumerProvider.getBuffer(RenderLayer.getEntityGlint()), vertexConsumerProvider.getBuffer(RenderLayer.getEntityCutoutNoCull(((HorseArmorItem) horseEntity.getArmorType().getItem()).getEntityTexture())));
        return vertexConsumer;
    }

}
