package com.herb_mc.extra_enchants;

import com.google.gson.*;
import com.google.gson.JsonObject;
import com.herb_mc.extra_enchants.commons.EnchantBuilder;
import com.herb_mc.extra_enchants.commons.ScalableEnchantBuilder;
import com.herb_mc.extra_enchants.registry.ModEnchants;
import net.fabricmc.fabric.api.resource.*;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.resource.JsonDataLoader;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;

import static com.herb_mc.extra_enchants.ExtraEnchantsMod.EXTRA_ENCHANTS_LOGGER;
import static com.herb_mc.extra_enchants.ExtraEnchantsMod.MOD_ID;

public class ReloadListener extends JsonDataLoader implements SimpleSynchronousResourceReloadListener {

    static int loaded;
    static int disabled;

    public static final Identifier IDENTIFIER = new Identifier(MOD_ID, "enchantment_configuration");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting()
            .disableHtmlEscaping().create();

    @Override
    public Identifier getFabricId() {
        return IDENTIFIER;
    }

    public ReloadListener() {
        super(GSON, "enchantment_configuration");
    }

    @Override
    public void apply(Map<Identifier, JsonElement> loader, ResourceManager manager, Profiler profiler) {
        loaded = 0;
        disabled = 0;
        for (Identifier id : manager.findResources("enchantment_configuration", path -> path.endsWith(".json"))) {
            try (InputStream stream = manager.getResource(id).getInputStream()) {
                String name = id.toString().replace("extra_enchants:enchantment_configuration/", "").replace(".json", "");
                BufferedReader br = new BufferedReader(new InputStreamReader(stream));
                String line = "";
                StringBuilder strBuilder = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    strBuilder.append(line);
                }
                stream.close();
                JsonObject file = new JsonParser().parse(strBuilder.toString()).getAsJsonObject();
                boolean scalable = file.get("type").getAsString().equals("scalable");
                boolean enabled = file.get("available").getAsBoolean();
                if (!enabled)
                    disabled++;
                Enchantment.Rarity rarity = Enchantment.Rarity.valueOf(file.get("rarity").getAsString().toUpperCase());
                int minPower = file.get("min_power").getAsInt();
                int minPowerDelta = 0;
                if (scalable)
                    minPowerDelta = file.get("min_power_delta").getAsInt();
                int maxPower = file.get("max_power").getAsInt();
                int maxPowerDelta = 0;
                if (scalable)
                    maxPowerDelta = file.get("max_power_delta").getAsInt();
                int maxLevel = file.get("max_level").getAsInt();
                boolean isCurse = file.get("is_curse").getAsBoolean();
                boolean isTreasure = file.get("is_treasure").getAsBoolean();
                JsonArray incompatArray = file.get("incompatible_enchants").getAsJsonArray();
                Iterator<JsonElement> iter = incompatArray.iterator();
                Enchantment[] incompatibleEnchantments = new Enchantment[incompatArray.size()];
                if (incompatibleEnchantments.length > 0) {
                    int index = 0;
                    while (iter.hasNext()) {
                        incompatibleEnchantments[index] = ModEnchants.valueOf(iter.next().getAsString().toLowerCase());
                        index++;
                    }
                }
                if (scalable)
                    ((ScalableEnchantBuilder) ModEnchants.enchantments.get(name)).setAttributes(enabled, rarity, minPower, minPowerDelta, maxPower, maxPowerDelta, maxLevel, isCurse, isTreasure, incompatibleEnchantments);
                else
                    ((EnchantBuilder) ModEnchants.enchantments.get(name)).setAttributes(enabled, rarity, minPower, maxPower, maxLevel, isCurse, isTreasure, incompatibleEnchantments);
                loaded++;
            } catch (Exception e) {
                EXTRA_ENCHANTS_LOGGER.error("Error occurred while loading resource json " + id.toString(), e);
            }
        }
        EXTRA_ENCHANTS_LOGGER.info("Loaded {} enchantments, {} disabled", loaded, disabled);
    }

    @Override
    public void reload(ResourceManager manager) {

    }

}
