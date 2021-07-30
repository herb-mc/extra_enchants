package com.herb_mc.extra_enchants.asm;

import com.chocohead.mm.api.ClassTinkerers;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.MappingResolver;

public class EarlyRiser implements Runnable {

    @Override
    public void run() {
        MappingResolver remapper = FabricLoader.getInstance().getMappingResolver();
        String enchantmentTarget = remapper.mapClassName("intermediary", "net.minecraft.class_1886");
        ClassTinkerers.enumBuilder(enchantmentTarget).addEnumSubclass("AXE", "com.herb_mc.extra_enchants.asm.Axe").build();
        ClassTinkerers.enumBuilder(enchantmentTarget).addEnumSubclass("ELYTRA", "com.herb_mc.extra_enchants.asm.Elytra").build();
        ClassTinkerers.enumBuilder(enchantmentTarget).addEnumSubclass("HORSE_ARMOR", "com.herb_mc.extra_enchants.asm.HorseArmor").build();
        ClassTinkerers.enumBuilder(enchantmentTarget).addEnumSubclass("SHIELD", "com.herb_mc.extra_enchants.asm.Shield").build();
        ClassTinkerers.enumBuilder(enchantmentTarget).addEnumSubclass("SNOWBALL", "com.herb_mc.extra_enchants.asm.Snowball").build();
        ClassTinkerers.enumBuilder(enchantmentTarget).addEnumSubclass("WEAPONS", "com.herb_mc.extra_enchants.asm.Weapons").build();
        String enchantmentRarity = remapper.mapClassName("intermediary", "net.minecraft.class_1887$class_1888");
        ClassTinkerers.enumBuilder(enchantmentRarity, int.class).addEnum("NULL", 0).build();
    }

}