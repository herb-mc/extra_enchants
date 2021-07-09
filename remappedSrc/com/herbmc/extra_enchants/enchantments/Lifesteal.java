package com.herbmc.extra_enchants.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;

import java.util.Objects;

public class Lifesteal extends Enchantment {

    public Lifesteal(Rarity weight, EnchantmentTarget type, EquipmentSlot[] equipmentSlots) {
        super(weight, type, equipmentSlots);
    }

    @Override
    public int getMinPower(int level) {
        return 15 + (level - 1) * 6;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public int getMaxPower(int level) {
        return this.getMinPower(level) + 5;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if(target instanceof LivingEntity && ((LivingEntity) target).canTakeDamage()) {
            float lastDamage = (float) (0.05 * level * Objects.requireNonNull(((LivingEntity) target).getDamageTracker().getMostRecentDamage()).getDamage());
            if(lastDamage > 3.0f)  lastDamage = 3.0f;
            user.heal(lastDamage);
        }
        super.onTargetDamaged(user, target, level);
    }

}
