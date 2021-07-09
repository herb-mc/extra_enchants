package com.herb_mc.extra_enchants;

import com.herb_mc.extra_enchants.registry.ModEnchants;
import net.fabricmc.api.ModInitializer;

public class ExtraEnchantsMod implements ModInitializer {

    public static final String MOD_ID = "extra_enchants";

    @Override
    public void onInitialize() {
        ModEnchants.registerEnchants();
    }

}
