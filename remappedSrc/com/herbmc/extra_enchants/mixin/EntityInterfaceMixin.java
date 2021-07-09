package com.herbmc.extra_enchants.mixin;

import it.unimi.dsi.fastutil.objects.Object2DoubleMap;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.Fluid;
import net.minecraft.tag.Tag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Entity.class)
public interface EntityInterfaceMixin {

    @Accessor("world")
    World getWorld();

    @Accessor("firstUpdate")
    boolean getFirstUpdate();

    @Accessor("fluidHeight")
    Object2DoubleMap<Tag<Fluid>> getFluidHeight();

    @Accessor("onGround")
    void setOnGround(boolean onGround);

    @Invoker("getY")
    double invokeGetY();

    @Invoker("getBoundingBox")
    Box invokeGetBoundingBox();

    @Invoker("setVelocity")
    void invokeSetVelocity(Vec3d velocity);

    @Invoker("getVelocity")
    Vec3d invokeGetVelocity();


    @Invoker("isInLava")
    boolean invokeIsInLava();

    @Invoker("getBlockPos")
    BlockPos invokeGetBlockPos();

}
