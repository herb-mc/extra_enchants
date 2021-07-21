package com.herb_mc.extra_enchants.mixin;

import net.minecraft.entity.passive.HorseBaseEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(HorseBaseEntity.class)
public interface HorseBaseEntityInterfaceMixin {

    @Invoker("setInAir")
    void setAir(boolean isInAir);

}
