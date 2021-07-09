package com.herbmc.extra_enchants.mixin;

import com.herbmc.extra_enchants.registry.ModEnchants;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;

import java.util.UUID;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity{

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Nullable
    public EntityAttributeInstance getAttributeInstance(EntityAttribute attribute) {
        return this.getAttributes().getCustomInstance(attribute);
    }

    @Inject(at = @At("HEAD"), method = "tick")
    public void tick(CallbackInfo info) {
        if((Object) this instanceof PlayerEntity) {
            UUID DEX_ATTACK_SPEED_BOOST_ID = UUID.fromString("662A6B8D-DA3E-4C1C-8813-96EA6097278A");
            UUID WEIGHTED_ATTACK_SPEED_BOOST_ID = UUID.fromString("662A6B8D-DA3E-4C1C-8813-96EA6097278B");
            UUID WEIGHTED_ATTACK_DAMAGE_BOOST_ID = UUID.fromString("662A6B8D-DA3E-4C1C-8813-96EA6097278C");
            UUID ARCH_REACH_BOOST_ID = UUID.fromString("662A6B8D-DA3E-4C1C-8813-96EA6097271C");
            int i = EnchantmentHelper.getEquipmentLevel(ModEnchants.DEXTROUS, (LivingEntity) (Object) this);
            removeModAttributes();
            modAttackSpeed(i, DEX_ATTACK_SPEED_BOOST_ID, "dex_attack_speed", 0.1D);
            i = EnchantmentHelper.getEquipmentLevel(ModEnchants.WEIGHTED, (LivingEntity) (Object) this);
            modAttackSpeed(i, WEIGHTED_ATTACK_SPEED_BOOST_ID, "wei_attack_speed", -0.15D);
            i = EnchantmentHelper.getEquipmentLevel(ModEnchants.ARCHITECT, (LivingEntity) (Object) this);
            modAttackRange(i, ARCH_REACH_BOOST_ID, "arch_reach_boost");
        }
    }

    private void removeModAttributes() {
        UUID DEX_ATTACK_SPEED_BOOST_ID = UUID.fromString("662A6B8D-DA3E-4C1C-8813-96EA6097278A");
        UUID WEIGHTED_ATTACK_SPEED_BOOST_ID = UUID.fromString("662A6B8D-DA3E-4C1C-8813-96EA6097278B");
        UUID WEIGHTED_ATTACK_DAMAGE_BOOST_ID = UUID.fromString("662A6B8D-DA3E-4C1C-8813-96EA6097278C");
        UUID ARCH_REACH_BOOST_ID = UUID.fromString("662A6B8D-DA3E-4C1C-8813-96EA6097271C");
        EntityAttributeInstance entityAttributeInstance = this.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_SPEED);
        if (entityAttributeInstance != null) {
            if (entityAttributeInstance.getModifier(DEX_ATTACK_SPEED_BOOST_ID) != null) {
                entityAttributeInstance.removeModifier(DEX_ATTACK_SPEED_BOOST_ID);
            }
            if (entityAttributeInstance.getModifier(WEIGHTED_ATTACK_SPEED_BOOST_ID) != null) {
                entityAttributeInstance.removeModifier(WEIGHTED_ATTACK_SPEED_BOOST_ID);
            }
        }
        entityAttributeInstance = this.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE);
        if (entityAttributeInstance != null) {
            if (entityAttributeInstance.getModifier(WEIGHTED_ATTACK_DAMAGE_BOOST_ID) != null) {
                entityAttributeInstance.removeModifier(WEIGHTED_ATTACK_DAMAGE_BOOST_ID);
            }
        }
        entityAttributeInstance = this.getAttributeInstance(ReachEntityAttributes.REACH);
        if (entityAttributeInstance != null) {
            if (entityAttributeInstance.getModifier(ARCH_REACH_BOOST_ID) != null) {
                entityAttributeInstance.removeModifier(ARCH_REACH_BOOST_ID);
            }
        }
    }

    private void modAttackSpeed(int lvl, UUID uuid, String str, double base) {
        EntityAttributeInstance entityAttributeInstance = this.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_SPEED);
        if (lvl > 0) {
            entityAttributeInstance.addTemporaryModifier(new EntityAttributeModifier(uuid, str, (base * lvl), EntityAttributeModifier.Operation.MULTIPLY_TOTAL));
        }
    }

    private void modAttackDamage(int lvl, UUID uuid, String str, double base) {
        EntityAttributeInstance entityAttributeInstance = this.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE);
        if (lvl > 0) {
            entityAttributeInstance.addTemporaryModifier(new EntityAttributeModifier(uuid, str, (base * lvl), EntityAttributeModifier.Operation.MULTIPLY_TOTAL));
        }
    }

    private void modAttackRange(int lvl, UUID uuid, String str) {
        EntityAttributeInstance entityAttributeInstance = this.getAttributeInstance(ReachEntityAttributes.REACH);
        if (lvl > 0) {
            entityAttributeInstance.addTemporaryModifier(new EntityAttributeModifier(uuid, str, lvl, EntityAttributeModifier.Operation.ADDITION));
        }
    }

}
