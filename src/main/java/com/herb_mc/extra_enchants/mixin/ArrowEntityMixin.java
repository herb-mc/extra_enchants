package com.herb_mc.extra_enchants.mixin;

import com.herb_mc.extra_enchants.registry.ModEnchants;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ArrowEntity.class)
public abstract class ArrowEntityMixin extends PersistentProjectileEntity implements LivingEntityInterfaceMixin, ArrowEntityInterfaceMixin {

    private int ender;
    private int exploding;
    private int ticks;
    private boolean crit;

    protected ArrowEntityMixin(EntityType<? extends PersistentProjectileEntity> type, LivingEntity owner, World world) {
        super(type, owner, world);
        this.ender = 0;
        this.exploding = 0;
        this.ticks = 0;
        this.crit = false;
    }

    @Inject(at = @At("HEAD"), method = "initFromStack")
    protected void initFromStack(CallbackInfo info) {
        Entity entity = this.getOwner();
        int i = EnchantmentHelper.getEquipmentLevel(ModEnchants.ENDER, (LivingEntity) entity);
        if(i >= 1)
            this.ender = 1;
        i = EnchantmentHelper.getEquipmentLevel(ModEnchants.EXPLOSIVE, (LivingEntity) entity);
        if(i >= 1)
            this.exploding = i;
    }

    @Inject(at = @At("HEAD"), method = "tick")
    public void tick(CallbackInfo info){
        if(this.ticks < 1) {
            this.crit = this.isCritical();
            this.ticks++;
        }
        if(this.crit && this.inGround) {
            if (this.exploding >= 1) {
                this.world.createExplosion(this, this.getX(), this.getY(), this.getZ(), (float) this.exploding / 2.0F + 0.5F, Explosion.DestructionType.NONE);
                this.discard();
            } else if (this.ender >= 1) {
                Entity entity = this.getOwner();
                if (entity instanceof ServerPlayerEntity serverPlayerEntity) {
                    if (entity.hasVehicle()) {
                        serverPlayerEntity.requestTeleportAndDismount(this.getX(), this.getY(), this.getZ());
                    } else {
                        entity.requestTeleport(this.getX(), this.getY(), this.getZ());
                    }
                }
                entity.fallDistance = 0.0F;
                entity.damage(DamageSource.FALL, 2.0F);
                this.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 1.0F, 1.0F);
                this.discard();
            }
        }
    }

}