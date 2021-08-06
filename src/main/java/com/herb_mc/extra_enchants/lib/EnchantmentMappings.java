package com.herb_mc.extra_enchants.lib;

import com.herb_mc.extra_enchants.registry.ModEnchants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;

import java.util.HashMap;
import java.util.Map;

public class EnchantmentMappings {

    public static Map<String, Enchantment> enchantments;
    public static Map<String, ValueContainer> enchantmentConfig;

    public static ValueContainer aceDamageReducerMult = new ValueContainer(0.25D);
    public static ValueContainer aceExtraArrowDamage = new ValueContainer(0.5F);
    public static ValueContainer aceExtraMeleeDamage = new ValueContainer(1.0F);
    public static ValueContainer aceExtraTridentDamage = new ValueContainer(2.0F);
    public static ValueContainer corePurityBaseDamage = new ValueContainer(0.0F);
    public static ValueContainer corePurityHealthMult = new ValueContainer(2.0F);
    public static ValueContainer corePuritySpeedPenalty = new ValueContainer(-0.1F);
    public static ValueContainer corePurityDamageMult = new ValueContainer(0.3F);
    public static ValueContainer corePurityThreshold = new ValueContainer(0.6F);
    public static ValueContainer nimbleDrawMult = new ValueContainer(-0.1F);
    public static ValueContainer sharpshooterArrowDamage = new ValueContainer(1.0F);
    public static ValueContainer sharpshooterTridentDamage = new ValueContainer(3.0F);
    public static ValueContainer sharpshooterFOVScale = new ValueContainer(0.33F);
    public static ValueContainer sniperDrawMult = new ValueContainer(1.0F);
    public static ValueContainer sniperDamageBase = new ValueContainer(2.0F);
    public static ValueContainer sniperVelocityMult = new ValueContainer(0.1F);

    static
    {
        // enchantment mappings
        enchantments = new HashMap<>();
        enchantments.put("ace", ModEnchants.ACE);
        enchantments.put("antigravity", ModEnchants.ANTIGRAVITY);
        enchantments.put("architect", ModEnchants.ARCHITECT);
        enchantments.put("arrow_speed", ModEnchants.ARROW_SPEED);
        enchantments.put("barbaric", ModEnchants.BARBARIC);
        enchantments.put("berserk", ModEnchants.BERSERK);
        enchantments.put("blaze_affinity", ModEnchants.BLAZE_AFFINITY);
        enchantments.put("blazing", ModEnchants.BLAZING);
        enchantments.put("boosting", ModEnchants.BOOSTING);
        enchantments.put("bounding", ModEnchants.BOUNDING);
        enchantments.put("cleaving", ModEnchants.CLEAVING);
        enchantments.put("core_of_neptune", ModEnchants.CORE_OF_NEPTUNE);
        enchantments.put("core_of_purity", ModEnchants.CORE_OF_PURITY);
        enchantments.put("core_of_the_blood_god", ModEnchants.CORE_OF_THE_BLOOD_GOD);
        enchantments.put("core_of_the_warp", ModEnchants.CORE_OF_THE_WARP);
        enchantments.put("curse_of_instability", ModEnchants.CURSE_OF_INSTABILITY);
        enchantments.put("dextrous", ModEnchants.DEXTROUS);
        enchantments.put("dwarven", ModEnchants.DWARVEN);
        enchantments.put("ender", ModEnchants.ENDER);
        enchantments.put("explosive", ModEnchants.EXPLOSIVE);
        enchantments.put("exposing", ModEnchants.EXPOSING);
        enchantments.put("featherweight", ModEnchants.FEATHERWEIGHT);
        enchantments.put("fireproof", ModEnchants.FIREPROOF);
        enchantments.put("launching", ModEnchants.LAUNCHING);
        enchantments.put("leaping", ModEnchants.LEAPING);
        enchantments.put("lifesteal", ModEnchants.LIFESTEAL);
        enchantments.put("lunging", ModEnchants.LUNGING);
        enchantments.put("magnetic", ModEnchants.MAGNETIC);
        enchantments.put("night_vision", ModEnchants.NIGHT_VISION);
        enchantments.put("nimble", ModEnchants.NIMBLE);
        enchantments.put("propelling", ModEnchants.PROPELLING);
        enchantments.put("psychic", ModEnchants.PSYCHIC);
        enchantments.put("reflecting", ModEnchants.REFLECTING);
        enchantments.put("reflex", ModEnchants.REFLEX);
        enchantments.put("sharpshooter", ModEnchants.SHARPSHOOTER);
        enchantments.put("shock_resistant", ModEnchants.SHOCK_RESISTANT);
        enchantments.put("slimey", ModEnchants.SLIMEY);
        enchantments.put("sniper", ModEnchants.SNIPER);
        enchantments.put("soulbound", ModEnchants.SOULBOUND);
        enchantments.put("stalwart", ModEnchants.STALWART);
        enchantments.put("steadfast", ModEnchants.STEADFAST);
        enchantments.put("surface_skimmer", ModEnchants.SURFACE_SKIMMER);
        enchantments.put("swiftness", ModEnchants.SWIFTNESS);
        enchantments.put("terraforming", ModEnchants.TERRAFORMING);
        enchantments.put("testing", ModEnchants.TESTING);
        enchantments.put("thunderbolt", ModEnchants.THUNDERBOLT);
        enchantments.put("tough", ModEnchants.TOUGH);
        enchantments.put("turbo", ModEnchants.TURBO);
        enchantments.put("warding", ModEnchants.WARDING);
        enchantments.put("weighted", ModEnchants.WEIGHTED);
        enchantments.put("windstep", ModEnchants.WINDSTEP);
        enchantments.put("withering", ModEnchants.WITHERING);
        // vanilla
        enchantments.put("aqua_affinity", Enchantments.AQUA_AFFINITY);
        enchantments.put("bane_of_arthropods", Enchantments.BANE_OF_ARTHROPODS);
        enchantments.put("blast_protection", Enchantments.BLAST_PROTECTION);
        enchantments.put("channeling", Enchantments.CHANNELING);
        enchantments.put("curse_of_binding", Enchantments.BINDING_CURSE);
        enchantments.put("curse_of_vanishing", Enchantments.VANISHING_CURSE);
        enchantments.put("depth_strider", Enchantments.DEPTH_STRIDER);
        enchantments.put("efficiency", Enchantments.EFFICIENCY);
        enchantments.put("feather_falling", Enchantments.FEATHER_FALLING);
        enchantments.put("fire_aspect", Enchantments.FIRE_ASPECT);
        enchantments.put("fire_protection", Enchantments.FIRE_PROTECTION);
        enchantments.put("flame", Enchantments.FLAME);
        enchantments.put("fortune", Enchantments.FORTUNE);
        enchantments.put("frost_walker", Enchantments.FROST_WALKER);
        enchantments.put("impaling", Enchantments.IMPALING);
        enchantments.put("infinity", Enchantments.INFINITY);
        enchantments.put("knockback", Enchantments.KNOCKBACK);
        enchantments.put("looting", Enchantments.LOOTING);
        enchantments.put("loyalty", Enchantments.LOYALTY);
        enchantments.put("luck_of_the_sea", Enchantments.LUCK_OF_THE_SEA);
        enchantments.put("lure", Enchantments.LURE);
        enchantments.put("mending", Enchantments.MENDING);
        enchantments.put("multishot", Enchantments.MULTISHOT);
        enchantments.put("piercing", Enchantments.PIERCING);
        enchantments.put("power", Enchantments.POWER);
        enchantments.put("projectile_protection", Enchantments.PROJECTILE_PROTECTION);
        enchantments.put("protection", Enchantments.PROTECTION);
        enchantments.put("punch", Enchantments.PUNCH);
        enchantments.put("quick_charge", Enchantments.QUICK_CHARGE);
        enchantments.put("respiration", Enchantments.RESPIRATION);
        enchantments.put("riptide", Enchantments.RIPTIDE);
        enchantments.put("sharpness", Enchantments.SHARPNESS);
        enchantments.put("silk_touch", Enchantments.SILK_TOUCH);
        enchantments.put("smite", Enchantments.SMITE);
        enchantments.put("soul_speed", Enchantments.SOUL_SPEED);
        enchantments.put("sweeping_edge", Enchantments.SWEEPING);
        enchantments.put("thorns", Enchantments.THORNS);
        enchantments.put("unbreaking", Enchantments.UNBREAKING);

        enchantmentConfig = new HashMap<>();
        enchantmentConfig.put("general_configuration:directly_enchant_elytra", ModEnchants.CAN_ENCHANT_ELYTRA);
        enchantmentConfig.put("general_configuration:directly_enchant_horse_armor", ModEnchants.CAN_ENCHANT_HORSE_ARMOR);
        enchantmentConfig.put("general_configuration:directly_enchant_shields", ModEnchants.CAN_ENCHANT_SHIELD);
        enchantmentConfig.put("general_configuration:directly_enchant_snowballs", ModEnchants.CAN_ENCHANT_SNOWBALL);
        enchantmentConfig.put("general_configuration:extended_trident_enchants", ModEnchants.EXTENDED_TRIDENT_ENCHANTS);
        enchantmentConfig.put("ace:damage_reduction_multiplier", aceDamageReducerMult);
        enchantmentConfig.put("ace:extra_arrow_damage", aceExtraArrowDamage);
        enchantmentConfig.put("ace:extra_melee_damage", aceExtraMeleeDamage);
        enchantmentConfig.put("ace:extra_trident_damage", aceExtraTridentDamage);
        enchantmentConfig.put("core_of_purity:base_damage", corePurityBaseDamage);
        enchantmentConfig.put("core_of_purity:damage_mult", corePurityDamageMult);
        enchantmentConfig.put("core_of_purity:damage_mult_threshold", corePurityThreshold);
        enchantmentConfig.put("core_of_purity:health_mult", corePurityHealthMult);
        enchantmentConfig.put("core_of_purity:speed_penalty", corePuritySpeedPenalty);
        enchantmentConfig.put("nimble:draw_mult", nimbleDrawMult);
        enchantmentConfig.put("sharpshooter:extra_arrow_damage", sharpshooterArrowDamage);
        enchantmentConfig.put("sharpshooter:extra_trident_damage", sharpshooterTridentDamage);
        enchantmentConfig.put("sharpshooter:fov_mod", sharpshooterFOVScale);
        enchantmentConfig.put("sniper:draw_mult", sniperDrawMult);
        enchantmentConfig.put("sniper:base_damage", sniperDamageBase);
        enchantmentConfig.put("sniper:velocity_mult", sniperVelocityMult);

    }

    public static Enchantment valueOf(String key) {
        return enchantments.get(key);
    }

}
