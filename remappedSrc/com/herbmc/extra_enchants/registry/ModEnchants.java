package com.herbmc.extra_enchants.registry;

import com.chocohead.mm.api.ClassTinkerers;
import com.herbmc.extra_enchants.ExtraEnchantsMod;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class ModEnchants {

    static EnchantmentTarget AXE = ClassTinkerers.getEnum(EnchantmentTarget.class, "AXE");
    static EnchantmentTarget HORSE_ARMOR = ClassTinkerers.getEnum(EnchantmentTarget.class, "HORSE_ARMOR");
    public static Enchantment ARCHITECT = new Architect(Enchantment.Rarity.UNCOMMON, EnchantmentTarget.ARMOR_CHEST, new EquipmentSlot[]{EquipmentSlot.CHEST});
    public static Enchantment BERSERK = new Berserk(Enchantment.Rarity.UNCOMMON, EnchantmentTarget.ARMOR_CHEST, new EquipmentSlot[]{EquipmentSlot.CHEST});
    public static Enchantment BOUNDING = new Bounding(Enchantment.Rarity.UNCOMMON, HORSE_ARMOR, new EquipmentSlot[]{EquipmentSlot.CHEST});
    public static Enchantment CLEAVING = new Cleaving(Enchantment.Rarity.UNCOMMON, AXE, EquipmentSlot.MAINHAND);
    public static Enchantment DEXTROUS = new Dextrous(Enchantment.Rarity.RARE, EnchantmentTarget.ARMOR_CHEST, new EquipmentSlot[]{EquipmentSlot.CHEST});
    public static Enchantment ENDER = new Ender(Enchantment.Rarity.RARE, EnchantmentTarget.BOW, new EquipmentSlot[]{EquipmentSlot.MAINHAND,EquipmentSlot.OFFHAND});
    public static Enchantment EXPLOSIVE = new Explosive(Enchantment.Rarity.RARE, EnchantmentTarget.BOW, new EquipmentSlot[]{EquipmentSlot.MAINHAND,EquipmentSlot.OFFHAND});
    public static Enchantment LIFESTEAL = new Lifesteal(Enchantment.Rarity.UNCOMMON, EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    public static Enchantment SURFACE_SKIMMER = new SurfaceSkimmer(Enchantment.Rarity.UNCOMMON, HORSE_ARMOR, new EquipmentSlot[]{EquipmentSlot.CHEST});
    public static Enchantment SWIFTNESS = new Swiftness(Enchantment.Rarity.UNCOMMON, HORSE_ARMOR, new EquipmentSlot[]{EquipmentSlot.CHEST});
    public static Enchantment WEIGHTED = new Weighted(Enchantment.Rarity.RARE, EnchantmentTarget.ARMOR_CHEST, new EquipmentSlot[]{EquipmentSlot.CHEST});

    public static void registerEnchants() {

        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "architect"), ARCHITECT);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "berserk"), BERSERK);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "bounding"), BOUNDING);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "cleaving"), CLEAVING);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "dextrous"), DEXTROUS);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "ender"), ENDER);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "explosive"), EXPLOSIVE);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "lifesteal"), LIFESTEAL);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "surface_skimmer"), SURFACE_SKIMMER);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "swiftness"), SWIFTNESS);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "weighted"), WEIGHTED);
    }

}
