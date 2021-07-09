package com.herb_mc.extra_enchants.interfaces;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;

import java.util.UUID;

public interface AttributeModifierInterface {

    default void removeAttribute(LivingEntity entity, EntityAttribute attribute, UUID uuid){
        EntityAttributeInstance instance = entity.getAttributeInstance(attribute);
        if (instance != null) {
            if (instance.getModifier(uuid) != null) {
                instance.removeModifier(uuid);
            }
        }
    }

    default void modAttributeBase(LivingEntity entity, EntityAttribute entityAttribute, int lvl, UUID uuid, String str, double base, EntityAttributeModifier.Operation operation) {
        EntityAttributeInstance instance = entity.getAttributeInstance(entityAttribute);
        if (instance != null) {
            instance.addTemporaryModifier(new EntityAttributeModifier(uuid,
                    str,
                    base * lvl,
                    operation));
        }
    }

    default void modAttributeExtended(LivingEntity entity, EntityAttribute entityAttribute, int lvl, UUID uuid, String str, double mult, double numerPower, double denomPower, double numerMult, double denomMult, double numerConst, double denomConst, double fConst, EntityAttributeModifier.Operation operation) {
        EntityAttributeInstance instance = entity.getAttributeInstance(entityAttribute);
        if (instance != null) {
            instance.addTemporaryModifier(new EntityAttributeModifier(uuid,
                    str,
                    mult * (numerMult * Math.pow(lvl, numerPower) + numerConst) / (denomMult * Math.pow(lvl, denomPower) + denomConst) + fConst,
                    operation));
        }
    }

}
