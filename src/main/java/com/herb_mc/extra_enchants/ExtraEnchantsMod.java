package com.herb_mc.extra_enchants;

import com.herb_mc.extra_enchants.registry.ModEnchants;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.resource.ResourceType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExtraEnchantsMod implements ModInitializer {

    public static final MinecraftClient client = MinecraftClient.getInstance();

    public static final String MOD_ID = "extra_enchants";

    public static final Logger EXTRA_ENCHANTS_LOGGER = LogManager.getLogger();

    @Override
    public void onInitialize() {
        ResourceManagerHelper.get(ResourceType.SERVER_DATA)
                .registerReloadListener(new ReloadListener());
        ModEnchants.registerEnchants();
    }

}
