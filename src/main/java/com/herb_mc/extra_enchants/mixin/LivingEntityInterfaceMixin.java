package com.herb_mc.extra_enchants.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(LivingEntity.class)
public interface LivingEntityInterfaceMixin {

    @Accessor("jumpingCooldown")
    void setJumpingCooldown(int jumping);

    @Invoker("getArmor")
    int invokeGetArmor();

    @Invoker("getAttributeValue")
    double invokeGetAttributeValue(EntityAttribute attribute);

    @Invoker("getAttributeInstance")
    EntityAttributeInstance invokeGetAttributeInstance(EntityAttribute attribute);

    @Invoker("getAttributes")
    AttributeContainer invokeGetAttributes();

    @Invoker("damageArmor")
    void invokeDamageArmor(DamageSource source, float amount);

}