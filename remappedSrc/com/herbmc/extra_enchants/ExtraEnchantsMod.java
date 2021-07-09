package com.herbmc.extra_enchants;

import com.chocohead.mm.api.ClassTinkerers;
import com.herbmc.extra_enchants.registry.ModEnchants;
import net.fabricmc.api.ModInitializer;
import net.minecraft.enchantment.EnchantmentTarget;

import net.minecraft.item.Items;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExtraEnchantsMod implements ModInitializer {

    public static final String MOD_ID = "extra_enchants";
    public static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void onInitialize() {
        ModEnchants.registerEnchants();
    }

}
