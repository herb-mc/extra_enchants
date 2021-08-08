package com.herb_mc.extra_enchants;

import com.chocohead.mm.api.ClassTinkerers;
import com.google.gson.*;
import com.google.gson.JsonObject;
import com.herb_mc.extra_enchants.lib.EnchantBuilder;
import com.herb_mc.extra_enchants.lib.EnchantmentMappings;
import com.herb_mc.extra_enchants.lib.ScalableEnchantBuilder;
import com.herb_mc.extra_enchants.lib.ValueContainer;
import com.herb_mc.extra_enchants.registry.ModEnchants;
import net.fabricmc.fabric.api.resource.*;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.resource.JsonDataLoader;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;

import static com.herb_mc.extra_enchants.ExtraEnchantsMod.EXTRA_ENCHANTS_LOGGER;
import static com.herb_mc.extra_enchants.ExtraEnchantsMod.MOD_ID;

public class ReloadListener extends JsonDataLoader implements SimpleSynchronousResourceReloadListener {

    static int loaded;
    static int modified;
    static int disabled;

    public static final Identifier IDENTIFIER = new Identifier(MOD_ID, "enchantments");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting()
            .disableHtmlEscaping().create();

    @Override
    public Identifier getFabricId() {
        return IDENTIFIER;
    }

    public ReloadListener() {
        super(GSON, "enchantments");
    }

    @Override
    public void apply(Map<Identifier, JsonElement> loader, ResourceManager manager, Profiler profiler) {
        loaded = 0;
        modified = 0;
        disabled = 0;
        for (Identifier id : manager.findResources("enchantment_base", path -> path.endsWith(".json"))) {
            try (InputStream stream = manager.getResource(id).getInputStream()) {
                String name = id.toString().replace("extra_enchants:enchantment_base/", "").replace(".json", "");
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
                Enchantment.Rarity rarity;
                if (file.get("rarity").getAsString().equalsIgnoreCase("NULL"))
                    rarity = ClassTinkerers.getEnum(Enchantment.Rarity.class, "NULL");
                else
                    rarity = Enchantment.Rarity.valueOf(file.get("rarity").getAsString().toUpperCase());
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
                        incompatibleEnchantments[index] = EnchantmentMappings.valueOf(iter.next().getAsString().toLowerCase());
                        index++;
                    }
                }
                if (scalable)
                    ((ScalableEnchantBuilder) EnchantmentMappings.enchantments.get(name)).setAttributes(enabled, rarity, minPower, minPowerDelta, maxPower, maxPowerDelta, maxLevel, isCurse, isTreasure, incompatibleEnchantments);
                else
                    ((EnchantBuilder) EnchantmentMappings.enchantments.get(name)).setAttributes(enabled, rarity, minPower, maxPower, maxLevel, isCurse, isTreasure, incompatibleEnchantments);
                loaded++;
            } catch (Exception e) {
                EXTRA_ENCHANTS_LOGGER.error("Error occurred while loading resource json " + id.toString(), e);
            }
        }
        EXTRA_ENCHANTS_LOGGER.info("Loaded {} enchantments, {} disabled", loaded, disabled);
        for (Identifier id : manager.findResources("enchantment_configuration", path -> path.endsWith(".json"))) {
            try (InputStream stream = manager.getResource(id).getInputStream()) {
                String name = id.toString().replace("extra_enchants:enchantment_configuration/", "").replace(".json", ":");
                BufferedReader br = new BufferedReader(new InputStreamReader(stream));
                String line = "";
                StringBuilder strBuilder = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    strBuilder.append(line);
                }
                stream.close();
                JsonObject file = new JsonParser().parse(strBuilder.toString()).getAsJsonObject();
                for (Map.Entry<String, JsonElement> element: file.entrySet()) {
                    if (EnchantmentMappings.enchantmentConfig.get(name + element.getKey()) != null) {
                        ValueContainer temp = EnchantmentMappings.enchantmentConfig.get(name + element.getKey());
                        if (temp.getValue() instanceof Integer) {
                            EnchantmentMappings.enchantmentConfig.get(name + element.getKey()).setValue(element.getValue().getAsInt());
                        } else if (temp.getValue() instanceof Boolean) {
                            EnchantmentMappings.enchantmentConfig.get(name + element.getKey()).setValue(element.getValue().getAsBoolean());
                        } else if (temp.getValue() instanceof Double) {
                            EnchantmentMappings.enchantmentConfig.get(name + element.getKey()).setValue(element.getValue().getAsDouble());
                        } else if (temp.getValue() instanceof Float) {
                            EnchantmentMappings.enchantmentConfig.get(name + element.getKey()).setValue(element.getValue().getAsFloat());
                        } else {
                            EXTRA_ENCHANTS_LOGGER.error("Invalid data type for {}", name + element.getKey());
                        }
                    }
                }
            } catch (Exception e) {
                EXTRA_ENCHANTS_LOGGER.error("Failed to apply enchantment config change at " + id.toString(), e);
            }
            modified++;
        }
        EXTRA_ENCHANTS_LOGGER.info("Modified {} enchantments", modified);
    }

    @Override
    public void reload(ResourceManager manager) {

    }

}
