package com.herb_mc.extra_enchants.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(FireworkRocketEntity.class)
public interface FireworkRocketEntityInterface {

    @Accessor("shooter")
    LivingEntity shooter();

    @Accessor("life")
    int getLife();

    @Accessor("life")
    void life(int life);

}
