package com.herb_mc.extra_enchants.lib;

import com.herb_mc.extra_enchants.registry.ModEnchants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;

import java.util.HashMap;
import java.util.Map;

public class EnchantmentMappings {

    public static Map<String, Enchantment> enchantments;
    public static Map<String, ValueContainer> enchantmentConfig;

    public static ValueContainer exposedArmorMult = new ValueContainer(0.9F);
    public static ValueContainer exposedDamageMult = new ValueContainer(1.1F);
    public static ValueContainer exposedLightningDamageMult = new ValueContainer(1.5F);

    public static ValueContainer aceDamageReducerMult = new ValueContainer(0.25D);
    public static ValueContainer aceExtraArrowDamage = new ValueContainer(0.5F);
    public static ValueContainer aceExtraMeleeDamage = new ValueContainer(1.0F);
    public static ValueContainer aceExtraTridentDamage = new ValueContainer(2.0F);
    public static ValueContainer architectRangeBoost = new ValueContainer(1.0D);
    public static ValueContainer arrowSpeedVelocityMult = new ValueContainer(0.15F);
    public static ValueContainer barbaricBasePercent = new ValueContainer(0.04D);
    public static ValueContainer berserkBase = new ValueContainer(0.2D);
    public static ValueContainer berserkMult = new ValueContainer(0.629D);
    public static ValueContainer berserkLogBase = new ValueContainer(10);
    public static ValueContainer blazeAffinityIncomingMult = new ValueContainer(0.95D);
    public static ValueContainer blazeAffinityExtraDamage = new ValueContainer(0.1D);
    public static ValueContainer boostingCooldown = new ValueContainer(60);
    public static ValueContainer boostingDuration = new ValueContainer(20);
    public static ValueContainer boostingSpeedBoost = new ValueContainer(0.4D);
    public static ValueContainer boundingJumpBoost = new ValueContainer(0.1D);
    public static ValueContainer cleavingArmorPierce = new ValueContainer(0.18D);
    public static ValueContainer coreNeptuneBreathGainMod = new ValueContainer(2);
    public static ValueContainer coreNeptuneBreathGainRand = new ValueContainer(16);
    public static ValueContainer coreNeptuneBreathLoss = new ValueContainer(3);
    public static ValueContainer coreNeptuneTridentDamage = new ValueContainer(8.0F);
    public static ValueContainer coreNeptuneWaterDrag = new ValueContainer(0.97D);
    public static ValueContainer coreNeptuneAirPenaltyThreshold = new ValueContainer(120.0D);
    public static ValueContainer coreNeptuneMaxDamagePenalty = new ValueContainer(0.3D);
    public static ValueContainer coreNeptuneMaxAttackSpeedPenalty = new ValueContainer(0.3D);
    public static ValueContainer coreNeptuneMaxMoveSpeedPenalty = new ValueContainer(0.2D);
    public static ValueContainer coreNeptuneAirBuffThreshold = new ValueContainer(90.0D);
    public static ValueContainer coreNeptuneMaxDamageBuff = new ValueContainer(0.3D);
    public static ValueContainer coreNeptuneMaxAttackSpeedBuff = new ValueContainer(0.3D);
    public static ValueContainer coreNeptuneMaxMoveSpeedBuff = new ValueContainer(0.2D);
    public static ValueContainer corePurityBaseDamage = new ValueContainer(0.0F);
    public static ValueContainer corePurityHealthMult = new ValueContainer(2.0F);
    public static ValueContainer corePuritySpeedPenalty = new ValueContainer(-0.1F);
    public static ValueContainer corePurityDamageMult = new ValueContainer(0.3F);
    public static ValueContainer corePurityThreshold = new ValueContainer(0.6F);
    public static ValueContainer coreBloodArmorPenalty = new ValueContainer(-0.2D);
    public static ValueContainer coreBloodAttackBuff = new ValueContainer(0.15D);
    public static ValueContainer coreBloodHealthBoost = new ValueContainer(1.0D);
    public static ValueContainer coreBloodCritChance = new ValueContainer(0.25D);
    public static ValueContainer coreBloodCritMult = new ValueContainer(1.8D);
    public static ValueContainer coreWarpHealthPenalty = new ValueContainer(-0.5D);
    public static ValueContainer coreWarpSpeedBuff = new ValueContainer(0.15D);
    public static ValueContainer coreWarpIncomingDamage = new ValueContainer(0.6D);
    public static ValueContainer coreWarpTeleportChance = new ValueContainer(0.05D);
    public static ValueContainer coreWarpTeleportTries = new ValueContainer(16);
    public static ValueContainer coreWarpTeleportRange = new ValueContainer(16);
    public static ValueContainer instabilityTeleportTries = new ValueContainer(16);
    public static ValueContainer instabilityTeleportRange = new ValueContainer(16);
    public static ValueContainer dextrousAttackSpeedBoost = new ValueContainer(0.1D);
    public static ValueContainer dwarvenActiveRange = new ValueContainer(5.5D);
    public static ValueContainer dwarvenAlwaysActive = new ValueContainer(true);
    public static ValueContainer explosiveBasePower = new ValueContainer(1.0F);
    public static ValueContainer explosiveInGroundScale = new ValueContainer(0.5F);
    public static ValueContainer exposingBaseDuration = new ValueContainer(30);
    public static ValueContainer featherweightFallSpeedScale = new ValueContainer(1.0D);
    public static ValueContainer launchingVelocityScale = new ValueContainer(1.0D);
    public static ValueContainer leapingBaseVelocity = new ValueContainer(0.075F);
    public static ValueContainer leapingFallHeight = new ValueContainer(1.0F);
    public static ValueContainer lifestealBasePercent = new ValueContainer(0.03F);
    public static ValueContainer lifestealMax = new ValueContainer(3.0F);
    public static ValueContainer lungingJumpVelocityBoost = new ValueContainer(0.15F);
    public static ValueContainer magneticBaseRadius = new ValueContainer(5.0D);
    public static ValueContainer magneticAdditionalRadius = new ValueContainer(1.0D);
    public static ValueContainer magneticScalar = new ValueContainer(0.25D);
    public static ValueContainer magneticMaxVelocity = new ValueContainer(5.0D);
    public static ValueContainer nightVisionAlwaysActive = new ValueContainer(false);
    public static ValueContainer nimbleDrawMult = new ValueContainer(-0.1F);
    public static ValueContainer propellingMinAccelVelocity = new ValueContainer(0.6D);
    public static ValueContainer propellingAdditionalAccelVelocity = new ValueContainer(0.06D);
    public static ValueContainer propellingMinAccel = new ValueContainer(0.015D);
    public static ValueContainer propellingAdditionalAccel = new ValueContainer(0.01D);
    public static ValueContainer propellingPenaltyStartHeight = new ValueContainer(128.0D);
    public static ValueContainer propellingPenaltyCriticalHeight = new ValueContainer(256.0D);
    public static ValueContainer psychicActiveRange = new ValueContainer(6.0D);
    public static ValueContainer psychicAlwaysActive = new ValueContainer(false);
    public static ValueContainer reflectingBaseReflectedVelocity = new ValueContainer(0.5D);
    public static ValueContainer reflectingAdditionalReflectedVelocity = new ValueContainer(0.1D);
    public static ValueContainer reflexReadyTicks = new ValueContainer(0);
    public static ValueContainer sharpshooterArrowDamage = new ValueContainer(1.0F);
    public static ValueContainer sharpshooterTridentDamage = new ValueContainer(3.0F);
    public static ValueContainer sharpshooterFOVScale = new ValueContainer(0.33F);
    public static ValueContainer sniperDrawMult = new ValueContainer(1.0F);
    public static ValueContainer sniperDamageBase = new ValueContainer(2.0F);
    public static ValueContainer sniperVelocityMult = new ValueContainer(0.1F);
    public static ValueContainer shockResistBaseMult = new ValueContainer(0.8F);
    public static ValueContainer shockResistScale = new ValueContainer(1.5F);
    public static ValueContainer slimeySlipperiness = new ValueContainer(1.0F);
    public static ValueContainer stalwartCooldown = new ValueContainer(60);
    public static ValueContainer steadfastSpeedMult = new ValueContainer(1.0D);
    public static ValueContainer swiftnessSpeedMult = new ValueContainer(0.1D);
    public static ValueContainer terraformingDropItems = new ValueContainer(false);
    public static ValueContainer terraformingToolSpeed = new ValueContainer(58);
    public static ValueContainer toughDamageReduction = new ValueContainer(0.03F);
    public static ValueContainer turboLifetimeDecrement = new ValueContainer(2);
    public static ValueContainer turboSpeedMult = new ValueContainer(1.5D);
    public static ValueContainer wardingRange = new ValueContainer(8.0D);
    public static ValueContainer weightedAttackDamageIncrease = new ValueContainer(0.2D);
    public static ValueContainer weightedAttackSpeedPenalty = new ValueContainer(-0.15D);
    public static ValueContainer windstepHeight = new ValueContainer(0.4F);

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
        enchantments.put("magic_guard", ModEnchants.MAGIC_GUARD);
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
        enchantments.put("swiftness", ModEnchants.SWIFTNESS);
        enchantments.put("terraforming", ModEnchants.TERRAFORMING);
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

        //enchantment config mappings
        enchantmentConfig = new HashMap<>();
        enchantmentConfig.put("general_configuration:directly_enchant_elytra", ModEnchants.CAN_ENCHANT_ELYTRA);
        enchantmentConfig.put("general_configuration:directly_enchant_horse_armor", ModEnchants.CAN_ENCHANT_HORSE_ARMOR);
        enchantmentConfig.put("general_configuration:directly_enchant_shields", ModEnchants.CAN_ENCHANT_SHIELD);
        enchantmentConfig.put("general_configuration:directly_enchant_snowballs", ModEnchants.CAN_ENCHANT_SNOWBALL);
        enchantmentConfig.put("general_configuration:extended_trident_enchants", ModEnchants.EXTENDED_TRIDENT_ENCHANTS);
        enchantmentConfig.put("general_configuration:exposed_armor_mult", exposedArmorMult);
        enchantmentConfig.put("general_configuration:exposed_damage_mult", exposedDamageMult);
        enchantmentConfig.put("general_configuration:exposed_lightning_damage_mult", exposedLightningDamageMult);
        enchantmentConfig.put("ace:damage_reduction_mult", aceDamageReducerMult);
        enchantmentConfig.put("ace:arrow_damage_boost", aceExtraArrowDamage);
        enchantmentConfig.put("ace:melee_damage_boost", aceExtraMeleeDamage);
        enchantmentConfig.put("ace:trident_damage_boost", aceExtraTridentDamage);
        enchantmentConfig.put("architect:range_boost", architectRangeBoost);
        enchantmentConfig.put("arrow_speed:velocity_mult", arrowSpeedVelocityMult);
        enchantmentConfig.put("barbaric:damage_mult", barbaricBasePercent);
        enchantmentConfig.put("berserk:base_mult", berserkBase);
        enchantmentConfig.put("berserk:log_mult", berserkMult);
        enchantmentConfig.put("berserk:log_base", berserkLogBase);
        enchantmentConfig.put("blaze_affinity:incoming_damage_mult", blazeAffinityIncomingMult);
        enchantmentConfig.put("blaze_affinity:damage_mult", blazeAffinityExtraDamage);
        enchantmentConfig.put("boosting:base_duration", boostingDuration);
        enchantmentConfig.put("boosting:cooldown", boostingCooldown);
        enchantmentConfig.put("boosting:speed_boost", boostingSpeedBoost);
        enchantmentConfig.put("bounding:jump_boost", boundingJumpBoost);
        enchantmentConfig.put("cleaving:armor_ignored", cleavingArmorPierce);
        enchantmentConfig.put("core_of_neptune:breath_gain_mod", coreNeptuneBreathGainMod);
        enchantmentConfig.put("core_of_neptune:breath_gain_rand_range", coreNeptuneBreathGainRand);
        enchantmentConfig.put("core_of_neptune:breath_loss_rate", coreNeptuneBreathLoss);
        enchantmentConfig.put("core_of_neptune:trident_damage_boost", coreNeptuneTridentDamage);
        enchantmentConfig.put("core_of_neptune:water_drag", coreNeptuneWaterDrag);
        enchantmentConfig.put("core_of_neptune:air_penalty_threshold", coreNeptuneAirPenaltyThreshold);
        enchantmentConfig.put("core_of_neptune:attack_damage_max_penalty", coreNeptuneMaxDamagePenalty);
        enchantmentConfig.put("core_of_neptune:attack_speed_max_penalty", coreNeptuneMaxAttackSpeedPenalty);
        enchantmentConfig.put("core_of_neptune:movement_speed_max_penalty", coreNeptuneMaxMoveSpeedPenalty);
        enchantmentConfig.put("core_of_neptune:air_boost_threshold", coreNeptuneAirBuffThreshold);
        enchantmentConfig.put("core_of_neptune:attack_damage_max_boost", coreNeptuneMaxDamageBuff);
        enchantmentConfig.put("core_of_neptune:attack_speed_max_boost", coreNeptuneMaxAttackSpeedBuff);
        enchantmentConfig.put("core_of_neptune:movement_speed_max_boost", coreNeptuneMaxMoveSpeedBuff);
        enchantmentConfig.put("core_of_purity:base_damage", corePurityBaseDamage);
        enchantmentConfig.put("core_of_purity:damage_mult", corePurityDamageMult);
        enchantmentConfig.put("core_of_purity:damage_mult_threshold", corePurityThreshold);
        enchantmentConfig.put("core_of_purity:health_mult", corePurityHealthMult);
        enchantmentConfig.put("core_of_purity:speed_penalty", corePuritySpeedPenalty);
        enchantmentConfig.put("core_of_the_blood_god:armor_penalty", coreBloodArmorPenalty);
        enchantmentConfig.put("core_of_the_blood_god:attack_boost", coreBloodAttackBuff);
        enchantmentConfig.put("core_of_the_blood_god:crit_chance", coreBloodCritChance);
        enchantmentConfig.put("core_of_the_blood_god:crit_mult", coreBloodCritMult);
        enchantmentConfig.put("core_of_the_blood_god:health_boost", coreBloodHealthBoost);
        enchantmentConfig.put("core_of_the_warp:health_penalty", coreWarpHealthPenalty);
        enchantmentConfig.put("core_of_the_warp:incoming_damage_mult", coreWarpIncomingDamage);
        enchantmentConfig.put("core_of_the_warp:speed_boost", coreWarpSpeedBuff);
        enchantmentConfig.put("core_of_the_warp:teleport_chance", coreWarpTeleportChance);
        enchantmentConfig.put("core_of_the_warp:teleport_range", coreWarpTeleportRange);
        enchantmentConfig.put("core_of_the_warp:teleport_tries", coreWarpTeleportTries);
        enchantmentConfig.put("curse_of_instability:teleport_range", instabilityTeleportRange);
        enchantmentConfig.put("curse_of_instability:teleport_tries", instabilityTeleportTries);
        enchantmentConfig.put("dextrous:attack_speed_boost", dextrousAttackSpeedBoost);
        enchantmentConfig.put("dwarven:active_range", dwarvenActiveRange);
        enchantmentConfig.put("dwarven:always_active", dwarvenAlwaysActive);
        enchantmentConfig.put("explosive:base_explosion_power", explosiveBasePower);
        enchantmentConfig.put("explosive:in_ground_scale", explosiveInGroundScale);
        enchantmentConfig.put("exposing:base_duration", exposingBaseDuration);
        enchantmentConfig.put("featherweight:fall_speed_scale", featherweightFallSpeedScale);
        enchantmentConfig.put("launching:velocity_scale", launchingVelocityScale);
        enchantmentConfig.put("leaping:base_velocity", leapingBaseVelocity);
        enchantmentConfig.put("leaping:extra_fall_height", leapingFallHeight);
        enchantmentConfig.put("lifesteal:base_heal_percent", lifestealBasePercent);
        enchantmentConfig.put("lifesteal:max_heal_amount", lifestealMax);
        enchantmentConfig.put("lunging:jump_velocity_boost", lungingJumpVelocityBoost);
        enchantmentConfig.put("magnetic:base_radius", magneticBaseRadius);
        enchantmentConfig.put("magnetic:additional_radius", magneticAdditionalRadius);
        enchantmentConfig.put("magnetic:velocity_scalar", magneticScalar);
        enchantmentConfig.put("magnetic:max_velocity", magneticMaxVelocity);
        enchantmentConfig.put("night_vision:always_active", nightVisionAlwaysActive);
        enchantmentConfig.put("nimble:draw_mult", nimbleDrawMult);
        enchantmentConfig.put("propelling:accel_velocity", propellingMinAccelVelocity);
        enchantmentConfig.put("propelling:additional_accel_velocity", propellingAdditionalAccelVelocity);
        enchantmentConfig.put("propelling:min_accel", propellingMinAccel);
        enchantmentConfig.put("propelling:additional_accel", propellingAdditionalAccel);
        enchantmentConfig.put("propelling:penalty_start_height", propellingPenaltyStartHeight);
        enchantmentConfig.put("propelling:critical_height", propellingPenaltyCriticalHeight);
        enchantmentConfig.put("psychic:active_range", psychicActiveRange);
        enchantmentConfig.put("psychic:always_active", psychicAlwaysActive);
        enchantmentConfig.put("reflecting:base_reflected_velocity", reflectingBaseReflectedVelocity);
        enchantmentConfig.put("reflecting:additional_reflected_velocity", reflectingAdditionalReflectedVelocity);
        enchantmentConfig.put("reflex:ready_ticks", reflexReadyTicks);
        enchantmentConfig.put("sharpshooter:arrow_damage_boost", sharpshooterArrowDamage);
        enchantmentConfig.put("sharpshooter:trident_damage_boost", sharpshooterTridentDamage);
        enchantmentConfig.put("sharpshooter:fov_mod", sharpshooterFOVScale);
        enchantmentConfig.put("shock_resistant:base_damage_multiplier", shockResistBaseMult);
        enchantmentConfig.put("shock_resistant:damage_mult_scale", shockResistScale);
        enchantmentConfig.put("slimey:slipperiness", slimeySlipperiness);
        enchantmentConfig.put("stalwart:cooldown", stalwartCooldown);
        enchantmentConfig.put("sniper:draw_mult", sniperDrawMult);
        enchantmentConfig.put("sniper:base_damage", sniperDamageBase);
        enchantmentConfig.put("sniper:velocity_mult", sniperVelocityMult);
        enchantmentConfig.put("steadfast:speed_mult", steadfastSpeedMult);
        enchantmentConfig.put("swiftness:speed_mult", swiftnessSpeedMult);
        enchantmentConfig.put("terraforming:additional_tool_speed", terraformingToolSpeed);
        enchantmentConfig.put("terraforming:drop_items", terraformingDropItems);
        enchantmentConfig.put("tough:damage_reduction", toughDamageReduction);
        enchantmentConfig.put("turbo:lifetime_decrement", turboLifetimeDecrement);
        enchantmentConfig.put("turbo:speed_mult", turboSpeedMult);
        enchantmentConfig.put("warding:range", wardingRange);
        enchantmentConfig.put("weighted:attack_damage_boost", weightedAttackDamageIncrease);
        enchantmentConfig.put("weighted:attack_speed_penalty", weightedAttackSpeedPenalty);
        enchantmentConfig.put("windstep:step_height_boost", windstepHeight);
    }

    public static Enchantment valueOf(String key) {
        return enchantments.get(key);
    }

}
