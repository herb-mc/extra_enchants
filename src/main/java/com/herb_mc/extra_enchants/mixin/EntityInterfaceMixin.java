package com.herb_mc.extra_enchants.mixin;

import it.unimi.dsi.fastutil.objects.Object2DoubleMap;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.Fluid;
import net.minecraft.tag.Tag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Entity.class)
public interface EntityInterfaceMixin {

    @Accessor("firstUpdate")
    boolean firstUpdate();

    @Accessor("fluidHeight")
    Object2DoubleMap<Tag<Fluid>> fluidHeight();

}
