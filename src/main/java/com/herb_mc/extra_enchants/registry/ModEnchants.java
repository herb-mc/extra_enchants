package com.herb_mc.extra_enchants.registry;

import com.chocohead.mm.api.ClassTinkerers;
import com.herb_mc.extra_enchants.enchantments.*;
import com.herb_mc.extra_enchants.ExtraEnchantsMod;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModEnchants {

    static EnchantmentTarget AXE = ClassTinkerers.getEnum(EnchantmentTarget.class, "AXE");
    static EnchantmentTarget HORSE_ARMOR = ClassTinkerers.getEnum(EnchantmentTarget.class, "HORSE_ARMOR");
    static EnchantmentTarget WEAPONS = ClassTinkerers.getEnum(EnchantmentTarget.class, "WEAPONS");
    public static Enchantment ARCHITECT = new Architect(Enchantment.Rarity.UNCOMMON, EnchantmentTarget.ARMOR_CHEST, new EquipmentSlot[]{EquipmentSlot.CHEST});
    public static Enchantment ARROW_SPEED = new ArrowSpeed(Enchantment.Rarity.COMMON, EnchantmentTarget.BOW, new EquipmentSlot[]{EquipmentSlot.MAINHAND,EquipmentSlot.OFFHAND});
    public static Enchantment BARBARIC = new Barbaric(Enchantment.Rarity.RARE, WEAPONS, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    public static Enchantment BERSERK = new Berserk(Enchantment.Rarity.UNCOMMON, EnchantmentTarget.ARMOR_CHEST, new EquipmentSlot[]{EquipmentSlot.CHEST});
    public static Enchantment BLOODCORE = new Bloodcore(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.ARMOR_CHEST, new EquipmentSlot[]{EquipmentSlot.CHEST});
    public static Enchantment BOUNDING = new Bounding(Enchantment.Rarity.COMMON, HORSE_ARMOR, new EquipmentSlot[]{EquipmentSlot.CHEST});
    public static Enchantment CLEAVING = new Cleaving(Enchantment.Rarity.UNCOMMON, AXE, EquipmentSlot.MAINHAND);
    public static Enchantment DEXTROUS = new Dextrous(Enchantment.Rarity.RARE, EnchantmentTarget.ARMOR_CHEST, new EquipmentSlot[]{EquipmentSlot.CHEST});
    public static Enchantment DWARVEN = new Dwarven(Enchantment.Rarity.RARE, EnchantmentTarget.ARMOR_HEAD, new EquipmentSlot[]{EquipmentSlot.HEAD});
    public static Enchantment ENDER = new Ender(Enchantment.Rarity.RARE, EnchantmentTarget.BOW, new EquipmentSlot[]{EquipmentSlot.MAINHAND,EquipmentSlot.OFFHAND});
    public static Enchantment EXPLOSIVE = new Explosive(Enchantment.Rarity.RARE, EnchantmentTarget.BOW, new EquipmentSlot[]{EquipmentSlot.MAINHAND,EquipmentSlot.OFFHAND});
    public static Enchantment EXPOSING = new Exposing(Enchantment.Rarity.COMMON, EnchantmentTarget.CROSSBOW, new EquipmentSlot[]{EquipmentSlot.MAINHAND,EquipmentSlot.OFFHAND});
    public static Enchantment EVIOCORE = new Eviocore(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.ARMOR_CHEST, new EquipmentSlot[]{EquipmentSlot.CHEST});
    public static Enchantment FEATHERWEIGHT = new Featherweight(Enchantment.Rarity.RARE, EnchantmentTarget.ARMOR_FEET, new EquipmentSlot[]{EquipmentSlot.FEET});
    public static Enchantment LEAPING = new Leaping(Enchantment.Rarity.RARE, EnchantmentTarget.ARMOR_FEET, new EquipmentSlot[]{EquipmentSlot.FEET});
    public static Enchantment LIFESTEAL = new Lifesteal(Enchantment.Rarity.RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    public static Enchantment LUNGING = new Lunging(Enchantment.Rarity.UNCOMMON, EnchantmentTarget.ARMOR_FEET, new EquipmentSlot[]{EquipmentSlot.FEET});
    public static Enchantment NIGHT_VISION = new NightVision(Enchantment.Rarity.RARE, EnchantmentTarget.ARMOR_HEAD, new EquipmentSlot[]{EquipmentSlot.HEAD});
    public static Enchantment SHARPSHOOTER = new Sharpshooter(Enchantment.Rarity.RARE, EnchantmentTarget.ARMOR_HEAD, new EquipmentSlot[]{EquipmentSlot.HEAD});
    public static Enchantment SURFACE_SKIMMER = new SurfaceSkimmer(Enchantment.Rarity.COMMON, HORSE_ARMOR, new EquipmentSlot[]{EquipmentSlot.CHEST});
    public static Enchantment SWIFTNESS = new Swiftness(Enchantment.Rarity.COMMON, HORSE_ARMOR, new EquipmentSlot[]{EquipmentSlot.CHEST});
    public static Enchantment TERRAFORMING = new Terraforming(Enchantment.Rarity.RARE, EnchantmentTarget.DIGGER, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    public static Enchantment TOUGH = new Tough(Enchantment.Rarity.UNCOMMON, EnchantmentTarget.ARMOR_LEGS, new EquipmentSlot[]{EquipmentSlot.LEGS});
    public static Enchantment VOIDCORE = new Voidcore(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.ARMOR_CHEST, new EquipmentSlot[]{EquipmentSlot.CHEST});
    public static Enchantment WARDING = new Warding(Enchantment.Rarity.UNCOMMON, HORSE_ARMOR, new EquipmentSlot[]{EquipmentSlot.CHEST});
    public static Enchantment WEIGHTED = new Weighted(Enchantment.Rarity.RARE, EnchantmentTarget.ARMOR_CHEST, new EquipmentSlot[]{EquipmentSlot.CHEST});
    public static Enchantment WINDSTEP = new Windstep(Enchantment.Rarity.RARE, EnchantmentTarget.ARMOR_FEET, new EquipmentSlot[]{EquipmentSlot.FEET});

    public static Enchantment[] ENCHANTMENTS_CORE_COMPAT = {ModEnchants.BLOODCORE, ModEnchants.EVIOCORE, ModEnchants.VOIDCORE};
    public static Enchantment[] ENCHANTMENTS_ARMOR_MOBILITY_COMPAT = {ModEnchants.LEAPING, ModEnchants.LUNGING, ModEnchants.WINDSTEP};
    public static Enchantment[] ENCHANTMENTS_ARMOR_OFFENSE_COMPAT = {ModEnchants.WEIGHTED, ModEnchants.DEXTROUS, ModEnchants.BERSERK};
    public static Enchantment[] ENCHANTMENTS_ARMOR_VISION_COMPAT = {ModEnchants.DWARVEN, ModEnchants.SHARPSHOOTER, ModEnchants.NIGHT_VISION};
    public static Enchantment[] ENCHANTMENTS_HORSE_ARMOR_MOBILITY_COMPAT = {ModEnchants.BOUNDING, ModEnchants.SURFACE_SKIMMER, ModEnchants.SWIFTNESS};
    public static Enchantment[] ENCHANTMENTS_WEAPONS_COMPAT = {ModEnchants.BARBARIC, ModEnchants.LIFESTEAL};

    public static void registerEnchants() {
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "architect"), ARCHITECT);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "arrow_speed"), ARROW_SPEED);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "barbaric"), BARBARIC);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "berserk"), BERSERK);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "bloodcore"), BLOODCORE);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "bounding"), BOUNDING);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "cleaving"), CLEAVING);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "dextrous"), DEXTROUS);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "dwarven"), DWARVEN);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "ender"), ENDER);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "explosive"), EXPLOSIVE);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "exposing"), EXPOSING);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "eviocore"), EVIOCORE);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "featherweight"), FEATHERWEIGHT);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "leaping"), LEAPING);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "lifesteal"), LIFESTEAL);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "lunging"), LUNGING);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "night_vision"), NIGHT_VISION);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "sharpshooter"), SHARPSHOOTER);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "surface_skimmer"), SURFACE_SKIMMER);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "swiftness"), SWIFTNESS);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "terraforming"), TERRAFORMING);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "tough"), TOUGH);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "voidcore"), VOIDCORE);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "warding"), WARDING);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "weighted"), WEIGHTED);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "windstep"), WINDSTEP);
    }

    public static boolean isEnchantType(Enchantment other, Enchantment[] type) {
        for(Enchantment e : type) {
            if (other == e) return false;
        }
        return true;
    }

}
