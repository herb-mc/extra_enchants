package com.herb_mc.extra_enchants.mixin;

import net.minecraft.entity.projectile.ProjectileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ProjectileEntity.class)
public interface ProjectileEntityInterfaceMixin {

    @Accessor("leftOwner")
    boolean leftOwner();


}