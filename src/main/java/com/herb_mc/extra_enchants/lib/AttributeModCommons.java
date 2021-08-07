package com.herb_mc.extra_enchants.lib;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public interface AttributeModCommons {

    default void removeAttribute(LivingEntity entity, EntityAttribute attribute, UUID uuid){
        EntityAttributeInstance instance = entity.getAttributeInstance(attribute);
        if (instance != null) {
            if (instance.getModifier(uuid) != null) {
                instance.removeModifier(uuid);
            }
        }
    }

    default void modAttributeBase(LivingEntity entity, EntityAttribute entityAttribute, int inVar, UUID uuid, String str, double base, EntityAttributeModifier.Operation operation) {
        EntityAttributeInstance instance = entity.getAttributeInstance(entityAttribute);
        if (instance != null) {
            instance.addTemporaryModifier(new EntityAttributeModifier(uuid,
                    str,
                    base * inVar,
                    operation));
        }
    }

    default void modAttributeExtended(LivingEntity entity, EntityAttribute entityAttribute, int inVar, UUID uuid, String str, double mult, double numerPower, double denomPower, double numerMult, double denomMult, double numerConst, double denomConst, double fConst, EntityAttributeModifier.Operation operation) {
        EntityAttributeInstance instance = entity.getAttributeInstance(entityAttribute);
        if (instance != null) {
            instance.addTemporaryModifier(new EntityAttributeModifier(uuid,
                    str,
                    mult * (numerMult * Math.pow(inVar, numerPower) + numerConst) / (denomMult * Math.pow(inVar, denomPower) + denomConst) + fConst,
                    operation));
        }
    }

    default void modAttributeLogarithmic(LivingEntity entity, EntityAttribute entityAttribute, double inVar, UUID uuid, String str, double mult, int logBase, double base, EntityAttributeModifier.Operation operation) {
        EntityAttributeInstance instance = entity.getAttributeInstance(entityAttribute);
        if (instance != null) {
            instance.addTemporaryModifier(new EntityAttributeModifier(uuid,
                    str,
                    (float) (mult * Math.log10(inVar) / Math.log10(logBase) + base),
                    operation));
        }
    }

}
