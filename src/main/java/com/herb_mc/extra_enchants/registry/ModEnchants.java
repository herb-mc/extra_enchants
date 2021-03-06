package com.herb_mc.extra_enchants.registry;

import com.chocohead.mm.api.ClassTinkerers;
import com.herb_mc.extra_enchants.lib.EnchantBuilder;
import com.herb_mc.extra_enchants.lib.ScalableEnchantBuilder;
import com.herb_mc.extra_enchants.ExtraEnchantsMod;
import com.herb_mc.extra_enchants.lib.ValueContainer;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModEnchants {

    static EnchantmentTarget AXE = ClassTinkerers.getEnum(EnchantmentTarget.class, "AXE");
    static EnchantmentTarget ELYTRA = ClassTinkerers.getEnum(EnchantmentTarget.class, "ELYTRA");
    static EnchantmentTarget HORSE_ARMOR = ClassTinkerers.getEnum(EnchantmentTarget.class, "HORSE_ARMOR");
    static EnchantmentTarget SHIELD = ClassTinkerers.getEnum(EnchantmentTarget.class, "SHIELD");
    static EnchantmentTarget SNOWBALL = ClassTinkerers.getEnum(EnchantmentTarget.class, "SNOWBALL");
    static EnchantmentTarget WEAPONS = ClassTinkerers.getEnum(EnchantmentTarget.class, "WEAPONS");
    static Enchantment.Rarity NULL = ClassTinkerers.getEnum(Enchantment.Rarity.class, "NULL");

    public static ValueContainer CAN_ENCHANT_ELYTRA = new ValueContainer(true);
    public static ValueContainer CAN_ENCHANT_HORSE_ARMOR = new ValueContainer(true);
    public static ValueContainer CAN_ENCHANT_SHIELD = new ValueContainer(true);
    public static ValueContainer CAN_ENCHANT_SNOWBALL = new ValueContainer(false);
    public static ValueContainer EXTENDED_TRIDENT_ENCHANTS = new ValueContainer(true);

    // Ace
    public static Enchantment ACE = new ScalableEnchantBuilder(
            Enchantment.Rarity.RARE,
            ELYTRA,
            new EquipmentSlot[]{EquipmentSlot.CHEST},
            15,
            10,
            30,
            10,
            3,
            false,
            false,
            new Enchantment[]{ModEnchants.PROPELLING, ModEnchants.ACE, ModEnchants.TURBO, ModEnchants.SHOCK_RESISTANT}
    );
    // Antigravity
    public static Enchantment ANTIGRAVITY = new EnchantBuilder(
            Enchantment.Rarity.RARE,
            EnchantmentTarget.DIGGER,
            new EquipmentSlot[]{EquipmentSlot.MAINHAND},
            20,
            50,
            1,
            false,
            true,
            new Enchantment[]{ModEnchants.TERRAFORMING}
    );
    // Architect
    public static Enchantment ARCHITECT = new ScalableEnchantBuilder(
            Enchantment.Rarity.UNCOMMON,
            EnchantmentTarget.ARMOR_CHEST,
            new EquipmentSlot[]{EquipmentSlot.CHEST},
            15,
            10,
            30,
            20,
            2,
            false,
            false,
            new Enchantment[]{}
    );
    // Arrow Speed
    public static Enchantment ARROW_SPEED = new ScalableEnchantBuilder(
            Enchantment.Rarity.COMMON,
            EnchantmentTarget.BOW,
            new EquipmentSlot[]{EquipmentSlot.MAINHAND,EquipmentSlot.OFFHAND},
            5,
            10,
            20,
            15,
            3,
            false,
            false,
            new Enchantment[]{Enchantments.POWER}
    );
    // Barbaric
    public static Enchantment BARBARIC = new EnchantBuilder(
            Enchantment.Rarity.RARE,
            WEAPONS,
            new EquipmentSlot[]{EquipmentSlot.MAINHAND},
            20,
            50,
            1,
            false,
            false,
            new Enchantment[]{ModEnchants.BARBARIC, ModEnchants.LIFESTEAL}
    );
    // Berserk
    public static Enchantment BERSERK = new ScalableEnchantBuilder(
            Enchantment.Rarity.UNCOMMON,
            EnchantmentTarget.ARMOR_CHEST,
            new EquipmentSlot[]{EquipmentSlot.CHEST},
            15,
            10,
            25,
            13,
            3,
            false,
            false,
            new Enchantment[]{Enchantments.PROTECTION, Enchantments.BLAST_PROTECTION, Enchantments.FIRE_PROTECTION, Enchantments.PROJECTILE_PROTECTION, ModEnchants.WEIGHTED, ModEnchants.DEXTROUS, ModEnchants.BERSERK, ModEnchants.MAGIC_GUARD}
    );
    // Blaze Affinity
    public static Enchantment BLAZE_AFFINITY = new EnchantBuilder(
            Enchantment.Rarity.COMMON,
            EnchantmentTarget.ARMOR_LEGS,
            new EquipmentSlot[]{EquipmentSlot.LEGS},
            20,
            50,
            1,
            false,
            true,
            new Enchantment[]{}
    );
    // Boosting
    public static Enchantment BOOSTING = new ScalableEnchantBuilder(
            Enchantment.Rarity.UNCOMMON,
            EnchantmentTarget.ARMOR_LEGS,
            new EquipmentSlot[]{EquipmentSlot.LEGS},
            10,
            10,
            20,
            15,
            3,
            false,
            false,
            new Enchantment[]{ModEnchants.STEADFAST, ModEnchants.BOOSTING}
    );
    // Bounding
    public static Enchantment BOUNDING = new ScalableEnchantBuilder(
            Enchantment.Rarity.COMMON,
            HORSE_ARMOR,
            new EquipmentSlot[]{EquipmentSlot.CHEST},
            1,
            10,
            20,
            15,
            3,
            false,
            false,
            new Enchantment[]{ModEnchants.BOUNDING, ModEnchants.SWIFTNESS}
    );
    // Cleaving
    public static Enchantment CLEAVING = new ScalableEnchantBuilder(
            Enchantment.Rarity.UNCOMMON,
            AXE,
            new EquipmentSlot[]{EquipmentSlot.MAINHAND},
            1,
            12,
            17,
            17,
            4,
            false,
            false,
            new Enchantment[]{Enchantments.SHARPNESS, Enchantments.SMITE, Enchantments.BANE_OF_ARTHROPODS}
    );
    // Core of Neptune
    public static Enchantment CORE_OF_NEPTUNE = new EnchantBuilder(
            Enchantment.Rarity.VERY_RARE,
            EnchantmentTarget.ARMOR_CHEST,
            new EquipmentSlot[]{EquipmentSlot.CHEST},
            20,
            50,
            1,
            true,
            true,
            new Enchantment[]{ModEnchants.CORE_OF_THE_BLOOD_GOD, ModEnchants.CORE_OF_PURITY, ModEnchants.CORE_OF_THE_WARP, ModEnchants.CORE_OF_NEPTUNE}
    );
    // Core of Purity
    public static Enchantment CORE_OF_PURITY = new EnchantBuilder(
            Enchantment.Rarity.VERY_RARE,
            EnchantmentTarget.ARMOR_CHEST,
            new EquipmentSlot[]{EquipmentSlot.CHEST},
            20,
            50,
            1,
            true,
            true,
            new Enchantment[]{ModEnchants.CORE_OF_THE_BLOOD_GOD, ModEnchants.CORE_OF_PURITY, ModEnchants.CORE_OF_THE_WARP, ModEnchants.CORE_OF_NEPTUNE}
    );
    // Core of the Blood God
    public static Enchantment CORE_OF_THE_BLOOD_GOD = new EnchantBuilder(
            Enchantment.Rarity.VERY_RARE,
            EnchantmentTarget.ARMOR_CHEST,
            new EquipmentSlot[]{EquipmentSlot.CHEST},
            30,
            50,
            1,
            true,
            true,
            new Enchantment[]{ModEnchants.CORE_OF_THE_BLOOD_GOD, ModEnchants.CORE_OF_PURITY, ModEnchants.CORE_OF_THE_WARP, ModEnchants.CORE_OF_NEPTUNE}
    );
    // Core of the Warp
    public static Enchantment CORE_OF_THE_WARP = new EnchantBuilder(
            Enchantment.Rarity.VERY_RARE,
            EnchantmentTarget.ARMOR_CHEST,
            new EquipmentSlot[]{EquipmentSlot.CHEST},
            20,
            50,
            1,
            true,
            true,
            new Enchantment[]{ModEnchants.CORE_OF_THE_BLOOD_GOD, ModEnchants.CORE_OF_PURITY, ModEnchants.CORE_OF_THE_WARP, ModEnchants.CORE_OF_NEPTUNE}
    );
    // Curse of Instability
    public static Enchantment CURSE_OF_INSTABILITY = new EnchantBuilder(
            Enchantment.Rarity.RARE,
            SHIELD,
            new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND},
            20,
            50,
            1,
            true,
            true,
            new Enchantment[]{}
    );
    // Dextrous
    public static Enchantment DEXTROUS = new ScalableEnchantBuilder(
            Enchantment.Rarity.RARE,
            EnchantmentTarget.ARMOR_CHEST,
            new EquipmentSlot[]{EquipmentSlot.CHEST},
            10,
            15,
            25,
            25,
            2,
            false,
            false,
            new Enchantment[]{Enchantments.THORNS, ModEnchants.WEIGHTED, ModEnchants.DEXTROUS, ModEnchants.BERSERK}
    );
    // Dwarven
    public static Enchantment DWARVEN = new EnchantBuilder(
            Enchantment.Rarity.RARE,
            EnchantmentTarget.ARMOR_HEAD,
            new EquipmentSlot[]{EquipmentSlot.HEAD},
            20,
            50,
            1,
            false,
            false,
            new Enchantment[]{ModEnchants.DWARVEN, ModEnchants.SHARPSHOOTER, ModEnchants.NIGHT_VISION, ModEnchants.PSYCHIC}
    );
    // Ender
    public static Enchantment ENDER = new EnchantBuilder(
            Enchantment.Rarity.RARE,
            EnchantmentTarget.BOW,
            new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND},
            20,
            50,
            1,
            false,
            true,
            new Enchantment[]{Enchantments.POWER, Enchantments.INFINITY, ModEnchants.EXPLOSIVE, ModEnchants.ENDER, ModEnchants.NIMBLE}
    );
    // Explosive
    public static Enchantment EXPLOSIVE = new ScalableEnchantBuilder(
            Enchantment.Rarity.RARE,
            EnchantmentTarget.BOW,
            new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND},
            15,
            15,
            30,
            20,
            2,
            false,
            false,
            new Enchantment[]{Enchantments.POWER, Enchantments.INFINITY, ModEnchants.EXPLOSIVE, ModEnchants.ENDER, ModEnchants.SNIPER, ModEnchants.NIMBLE}
    );
    // Exposing
    public static Enchantment EXPOSING = new ScalableEnchantBuilder(
            Enchantment.Rarity.RARE,
            EnchantmentTarget.CROSSBOW,
            new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND},
            15,
            10,
            30,
            10,
            3,
            false,
            false,
            new Enchantment[]{ModEnchants.EXPOSING, ModEnchants.THUNDERBOLT}
    );
    // Featherweight
    public static Enchantment FEATHERWEIGHT = new ScalableEnchantBuilder(
            Enchantment.Rarity.RARE,
            EnchantmentTarget.ARMOR_FEET,
            new EquipmentSlot[]{EquipmentSlot.FEET},
            15,
            10,
            25,
            13,
            3,
            false,
            false,
            new Enchantment[]{Enchantments.FEATHER_FALLING}
    );
    // Fireproof
    public static Enchantment FIREPROOF = new EnchantBuilder(
            Enchantment.Rarity.RARE,
            EnchantmentTarget.BREAKABLE,
            new EquipmentSlot[]{},
            25,
            50,
            1,
            false,
            true,
            new Enchantment[]{}
    );
    // Launching
    public static Enchantment LAUNCHING = new ScalableEnchantBuilder(
            Enchantment.Rarity.RARE,
            EnchantmentTarget.TRIDENT,
            new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND},
            15,
            15,
            30,
            10,
            3,
            false,
            false,
            new Enchantment[]{}
    );
    // Leaping
    public static Enchantment LEAPING = new ScalableEnchantBuilder(
            Enchantment.Rarity.UNCOMMON,
            EnchantmentTarget.ARMOR_FEET,
            new EquipmentSlot[]{EquipmentSlot.FEET},
            15,
            10,
            25,
            25,
            2,
            false,
            false,
            new Enchantment[]{ModEnchants.LEAPING, ModEnchants.LUNGING, ModEnchants.WINDSTEP, ModEnchants.SLIMEY}
    );
    // Lifesteal
    public static Enchantment LIFESTEAL = new ScalableEnchantBuilder(
            Enchantment.Rarity.RARE,
            WEAPONS,
            new EquipmentSlot[]{EquipmentSlot.MAINHAND},
            15,
            10,
            25,
            10,
            3,
            false,
            false,
            new Enchantment[]{ModEnchants.BARBARIC, ModEnchants.LIFESTEAL}
    );
    // Lunging
    public static Enchantment LUNGING = new ScalableEnchantBuilder(
            Enchantment.Rarity.UNCOMMON,
            EnchantmentTarget.ARMOR_FEET,
            new EquipmentSlot[]{EquipmentSlot.FEET},
            15,
            10,
            25,
            25,
            2,
            false,
            false,
            new Enchantment[]{ModEnchants.LEAPING, ModEnchants.LUNGING, ModEnchants.WINDSTEP, ModEnchants.SLIMEY}
    );
    // Magic Guard
    public static Enchantment MAGIC_GUARD = new EnchantBuilder(
            Enchantment.Rarity.RARE,
            EnchantmentTarget.ARMOR_CHEST,
            new EquipmentSlot[]{EquipmentSlot.CHEST},
            30,
            50,
            1,
            false,
            false,
            new Enchantment[]{Enchantments.THORNS, ModEnchants.WEIGHTED, ModEnchants.DEXTROUS, ModEnchants.BERSERK, ModEnchants.MAGIC_GUARD}
    );
    // Magnetic
    public static Enchantment MAGNETIC = new ScalableEnchantBuilder(
            Enchantment.Rarity.RARE,
            EnchantmentTarget.DIGGER,
            new EquipmentSlot[]{},
            15,
            10,
            30,
            10,
            3,
            false,
            true,
            new Enchantment[]{ModEnchants.TERRAFORMING}
    );
    // Night Vision
    public static Enchantment NIGHT_VISION = new EnchantBuilder(
            Enchantment.Rarity.RARE,
            EnchantmentTarget.ARMOR_HEAD,
            new EquipmentSlot[]{EquipmentSlot.HEAD},
            20,
            50,
            1,
            false,
            false,
            new Enchantment[]{ModEnchants.DWARVEN, ModEnchants.SHARPSHOOTER, ModEnchants.NIGHT_VISION, ModEnchants.PSYCHIC}
    );
    // Nimble
    public static Enchantment NIMBLE = new ScalableEnchantBuilder(
            Enchantment.Rarity.UNCOMMON,
            EnchantmentTarget.BOW,
            new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND},
            1,
            9,
            14,
            9,
            5,
            false,
            false,
            new Enchantment[]{ModEnchants.SNIPER, ModEnchants.ENDER, ModEnchants.EXPLOSIVE, ModEnchants.NIMBLE}
    );
    // Propelling
    public static Enchantment PROPELLING = new ScalableEnchantBuilder(
            Enchantment.Rarity.UNCOMMON,
            ELYTRA,
            new EquipmentSlot[]{EquipmentSlot.CHEST},
            15,
            10,
            30,
            10,
            3,
            false,
            false,
            new Enchantment[]{ModEnchants.PROPELLING, ModEnchants.ACE, ModEnchants.TURBO, ModEnchants.SHOCK_RESISTANT}
    );
    // Psychic
    public static Enchantment PSYCHIC = new EnchantBuilder(
            Enchantment.Rarity.RARE,
            EnchantmentTarget.ARMOR_HEAD,
            new EquipmentSlot[]{EquipmentSlot.HEAD},
            20,
            50,
            1,
            false,
            false,
            new Enchantment[]{ModEnchants.DWARVEN, ModEnchants.SHARPSHOOTER, ModEnchants.NIGHT_VISION, ModEnchants.PSYCHIC}
    );
    // Reflecting
    public static Enchantment REFLECTING = new ScalableEnchantBuilder(
            Enchantment.Rarity.UNCOMMON,
            SHIELD,
            new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND},
            15,
            10,
            30,
            10,
            3,
            false,
            false,
            new Enchantment[]{ModEnchants.REFLEX, ModEnchants.REFLECTING, ModEnchants.STALWART}
    );
    // Reflex
    public static Enchantment REFLEX = new EnchantBuilder(
            Enchantment.Rarity.UNCOMMON,
            SHIELD,
            new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND},
            15,
            50,
            1,
            false,
            false,
            new Enchantment[]{ModEnchants.REFLEX, ModEnchants.REFLECTING, ModEnchants.STALWART}
    );
    // Sharpshooter
    public static Enchantment SHARPSHOOTER = new EnchantBuilder(
            Enchantment.Rarity.RARE,
            EnchantmentTarget.ARMOR_HEAD,
            new EquipmentSlot[]{EquipmentSlot.HEAD},
            20,
            50,
            1,
            false,
            false,
            new Enchantment[]{ModEnchants.DWARVEN, ModEnchants.SHARPSHOOTER, ModEnchants.NIGHT_VISION, ModEnchants.PSYCHIC}
    );
    // Shock Resistance
    public static Enchantment SHOCK_RESISTANT = new ScalableEnchantBuilder(
            Enchantment.Rarity.UNCOMMON,
            ELYTRA,
            new EquipmentSlot[]{EquipmentSlot.CHEST},
            20,
            10,
            30,
            10,
            3,
            false,
            false,
            new Enchantment[]{ModEnchants.ACE, ModEnchants.PROPELLING}
    );
    // Slimey
    public static Enchantment SLIMEY = new EnchantBuilder(
            Enchantment.Rarity.RARE,
            EnchantmentTarget.ARMOR_FEET,
            new EquipmentSlot[]{EquipmentSlot.FEET},
            20,
            50,
            1,
            false,
            false,
            new Enchantment[]{ModEnchants.LEAPING, ModEnchants.LUNGING, ModEnchants.WINDSTEP, ModEnchants.SLIMEY}
    );
    // Soulbound
    public static Enchantment SOULBOUND = new EnchantBuilder(
            Enchantment.Rarity.VERY_RARE,
            EnchantmentTarget.BREAKABLE,
            new EquipmentSlot[]{},
            20,
            50,
            1,
            false,
            true,
            new Enchantment[]{Enchantments.VANISHING_CURSE}
    );
    // Stalwart
    public static Enchantment STALWART = new EnchantBuilder(
            Enchantment.Rarity.UNCOMMON,
            SHIELD,
            new EquipmentSlot[]{},
            20,
            50,
            1,
            false,
            true,
            new Enchantment[]{ModEnchants.REFLEX, ModEnchants.REFLECTING, ModEnchants.STALWART}
    );
    // Steadfast
    public static Enchantment STEADFAST = new ScalableEnchantBuilder(
            Enchantment.Rarity.UNCOMMON,
            EnchantmentTarget.ARMOR_LEGS,
            new EquipmentSlot[]{EquipmentSlot.LEGS},
            20,
            10,
            30,
            20,
            2,
            false,
            false,
            new Enchantment[]{ModEnchants.STEADFAST, ModEnchants.BOOSTING}
    );
    // Sniper
    public static Enchantment SNIPER = new ScalableEnchantBuilder(
            Enchantment.Rarity.UNCOMMON,
            EnchantmentTarget.BOW,
            new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND},
            20,
            10,
            30,
            20,
            2,
            false,
            false,
            new Enchantment[]{ModEnchants.EXPLOSIVE, ModEnchants.NIMBLE}
    );
    // Swiftness
    public static Enchantment SWIFTNESS = new ScalableEnchantBuilder(
            Enchantment.Rarity.COMMON,
            HORSE_ARMOR,
            new EquipmentSlot[]{EquipmentSlot.CHEST},
            1,
            10,
            20,
            15,
            3,
            false,
            false,
            new Enchantment[]{ModEnchants.BOUNDING, ModEnchants.SWIFTNESS}
    );
    // Terraforming
    public static Enchantment TERRAFORMING = new EnchantBuilder(
            Enchantment.Rarity.RARE,
            EnchantmentTarget.DIGGER,
            new EquipmentSlot[]{EquipmentSlot.MAINHAND},
            20,
            50,
            1,
            false,
            true,
            new Enchantment[]{Enchantments.FORTUNE, Enchantments.SILK_TOUCH, ModEnchants.ANTIGRAVITY, ModEnchants.MAGNETIC}
    );
    // Thunderbolt
    public static Enchantment THUNDERBOLT = new EnchantBuilder(
            Enchantment.Rarity.RARE,
            EnchantmentTarget.CROSSBOW,
            new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND},
            20,
            50,
            1,
            false,
            true,
            new Enchantment[]{ModEnchants.THUNDERBOLT, ModEnchants.EXPOSING, Enchantments.PIERCING, Enchantments.QUICK_CHARGE}
    );
    // Tough
    public static Enchantment TOUGH = new ScalableEnchantBuilder(
            Enchantment.Rarity.UNCOMMON,
            EnchantmentTarget.ARMOR_LEGS,
            new EquipmentSlot[]{EquipmentSlot.LEGS},
            1,
            13,
            16,
            13,
            4,
            false,
            false,
            new Enchantment[]{Enchantments.PROTECTION, Enchantments.BLAST_PROTECTION, Enchantments.FIRE_PROTECTION, Enchantments.PROJECTILE_PROTECTION}
    );
    // Turbo
    public static Enchantment TURBO = new EnchantBuilder(
            Enchantment.Rarity.RARE,
            ELYTRA,
            new EquipmentSlot[]{EquipmentSlot.CHEST},
            20,
            50,
            1,
            false,
            false,
            new Enchantment[]{ModEnchants.PROPELLING, ModEnchants.ACE, ModEnchants.TURBO}
    );
    // Warding
    public static Enchantment WARDING = new EnchantBuilder(
            Enchantment.Rarity.UNCOMMON,
            HORSE_ARMOR,
            new EquipmentSlot[]{EquipmentSlot.CHEST},
            15,
            50,
            1,
            false,
            true,
            new Enchantment[]{}
    );
    // Weighted
    public static Enchantment WEIGHTED = new ScalableEnchantBuilder(
            Enchantment.Rarity.RARE,
            EnchantmentTarget.ARMOR_CHEST,
            new EquipmentSlot[]{EquipmentSlot.CHEST},
            16,
            10,
            30,
            20,
            2,
            false,
            false,
            new Enchantment[]{Enchantments.THORNS, ModEnchants.WEIGHTED, ModEnchants.DEXTROUS, ModEnchants.BERSERK, ModEnchants.MAGIC_GUARD}
    );
    // Windstep
    public static Enchantment WINDSTEP = new EnchantBuilder(
            Enchantment.Rarity.UNCOMMON,
            EnchantmentTarget.ARMOR_FEET,
            new EquipmentSlot[]{EquipmentSlot.FEET},
            20,
            50,
            1,
            false,
            false,
            new Enchantment[]{ModEnchants.LEAPING, ModEnchants.LUNGING, ModEnchants.WINDSTEP, ModEnchants.SLIMEY}
    );

    public static boolean isEnchantType(Enchantment other, Enchantment[] type) {
        if (type == null) return false;
        for(Enchantment e : type) {
            if (other == e) return true;
        }
        return false;
    }

    public static void registerEnchants() {
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "ace"), ACE);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "antigravity"), ANTIGRAVITY);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "architect"), ARCHITECT);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "arrow_speed"), ARROW_SPEED);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "barbaric"), BARBARIC);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "berserk"), BERSERK);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "blaze_affinity"), BLAZE_AFFINITY);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "boosting"), BOOSTING);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "bounding"), BOUNDING);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "cleaving"), CLEAVING);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "core_of_neptune"), CORE_OF_NEPTUNE);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "core_of_purity"), CORE_OF_PURITY);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "core_of_the_blood_god"), CORE_OF_THE_BLOOD_GOD);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "core_of_the_warp"), CORE_OF_THE_WARP);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "curse_of_instability"), CURSE_OF_INSTABILITY);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "dextrous"), DEXTROUS);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "dwarven"), DWARVEN);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "ender"), ENDER);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "explosive"), EXPLOSIVE);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "exposing"), EXPOSING);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "featherweight"), FEATHERWEIGHT);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "fireproof"), FIREPROOF);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "launching"), LAUNCHING);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "leaping"), LEAPING);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "lifesteal"), LIFESTEAL);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "lunging"), LUNGING);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "magic_guard"), MAGIC_GUARD);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "magnetic"), MAGNETIC);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "night_vision"), NIGHT_VISION);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "nimble"), NIMBLE);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "propelling"), PROPELLING);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "psychic"), PSYCHIC);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "reflecting"), REFLECTING);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "reflex"), REFLEX);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "sharpshooter"), SHARPSHOOTER);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "shock_resistant"), SHOCK_RESISTANT);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "slimey"), SLIMEY);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "sniper"), SNIPER);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "soulbound"), SOULBOUND);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "stalwart"), STALWART);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "steadfast"), STEADFAST);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "swiftness"), SWIFTNESS);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "terraforming"), TERRAFORMING);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "thunderbolt"), THUNDERBOLT);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "tough"), TOUGH);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "turbo"), TURBO);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "warding"), WARDING);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "weighted"), WEIGHTED);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ExtraEnchantsMod.MOD_ID, "windstep"), WINDSTEP);
    }

}
