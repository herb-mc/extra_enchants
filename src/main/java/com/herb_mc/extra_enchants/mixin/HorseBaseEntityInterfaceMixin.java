package com.herb_mc.extra_enchants.mixin;

import net.minecraft.entity.passive.HorseBaseEntity;
import net.minecraft.util.math.Box;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(HorseBaseEntity.class)
public interface HorseBaseEntityInterfaceMixin {

    @Invoker("isInAir")
    boolean checkInAir();

    @Invoker("setInAir")
    void setAir(boolean isInAir);

}
