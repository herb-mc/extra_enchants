package com.herb_mc.extra_enchants.lib;

import com.herb_mc.extra_enchants.registry.ModEnchants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;

import java.util.HashMap;
import java.util.Map;

public class EnchantmentMappings {

    public static Map<String, Enchantment> enchantments;

    static
    {
        // extra enchants
        enchantments = new HashMap<>();
        enchantments.put("ace", ModEnchants.ACE);
        enchantments.put("antigravity", ModEnchants.ANTIGRAVITY);
        enchantments.put("architect", ModEnchants.ARCHITECT);
        enchantments.put("arrow_speed", ModEnchants.ARROW_SPEED);
        enchantments.put("barbaric", ModEnchants.BARBARIC);
        enchantments.put("berserk", ModEnchants.BERSERK);
        enchantments.put("blaze_affinity", ModEnchants.BLAZE_AFFINITY);
        enchantments.put("boosting", ModEnchants.BOOSTING);
        enchantments.put("bounding", ModEnchants.BOUNDING);
        enchantments.put("cleaving", ModEnchants.CLEAVING);
        enchantments.put("core_of_neptune", ModEnchants.CORE_OF_NEPTUNE);
        enchantments.put("core_of_purity", ModEnchants.CORE_OF_PURITY);
        enchantments.put("core_of_the_blood_god", ModEnchants.CORE_OF_THE_BLOOD_GOD);
        enchantments.put("core_of_the_warp", ModEnchants.CORE_OF_THE_WARP);
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
    }

    public static Enchantment valueOf(String key) {
        return enchantments.get(key);
    }

}
