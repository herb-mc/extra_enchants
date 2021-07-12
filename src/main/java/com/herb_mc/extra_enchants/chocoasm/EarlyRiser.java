package com.herb_mc.extra_enchants.chocoasm;

import com.chocohead.mm.api.ClassTinkerers;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.MappingResolver;

public class EarlyRiser implements Runnable {

    @Override
    public void run() {
        MappingResolver remapper = FabricLoader.getInstance().getMappingResolver();
        String enchantmentTarget = remapper.mapClassName("intermediary", "net.minecraft.class_1886");
        ClassTinkerers.enumBuilder(enchantmentTarget).addEnumSubclass("AXE", "com.herb_mc.extra_enchants.chocoasm.Axe").build();
        ClassTinkerers.enumBuilder(enchantmentTarget).addEnumSubclass("HORSE_ARMOR", "com.herb_mc.extra_enchants.chocoasm.HorseArmor").build();
        ClassTinkerers.enumBuilder(enchantmentTarget).addEnumSubclass("WEAPONS", "com.herb_mc.extra_enchants.chocoasm.Weapons").build();
    }

}