package com.herb_mc.extra_enchants.mixin;

import com.herb_mc.extra_enchants.registry.ModEnchants;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.entity.projectile.WitherSkullEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SnowballItem;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SnowballItem.class)
public class SnowballItemMixin {

    @Unique private ItemStack stack;
    @Unique private PlayerEntity thisUser;
    @Unique private World thisWorld;

    @Inject(
            method = "use",
            at = @At("HEAD")
    )
    protected void use(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> info) {
        stack = user.getStackInHand(hand);
        thisUser = user;
        thisWorld = world;
    }

    @ModifyArg(
            method = "use",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/World;spawnEntity(Lnet/minecraft/entity/Entity;)Z"
            )
    )
    private Entity modifiableEntity(Entity entity) {
        if (EnchantmentHelper.getLevel(ModEnchants.WITHERING, stack) > 0) {
            entity = new WitherSkullEntity(thisWorld, thisUser, thisUser.getRotationVector().x, thisUser.getRotationVector().y, thisUser.getRotationVector().z);
            ((WitherSkullEntity)entity).setProperties(thisUser, thisUser.getPitch(), thisUser.getYaw(), 0.0F, 1.5F, 1.0F);
            entity.setPosition(thisUser.getEyePos().add(thisUser.getRotationVector().multiply(0.6)));
            if (thisUser.getRandom().nextDouble() <= 0.1)
                ((WitherSkullEntity)entity).setCharged(true);
        }
        else if (EnchantmentHelper.getLevel(ModEnchants.BLAZING, stack) > 0) {
            entity = new SmallFireballEntity(thisUser.world, thisUser, thisUser.getRotationVector().x * 1.2 + thisUser.getRandom().nextGaussian() / 20, thisUser.getRotationVector().y * 1.2 + thisUser.getRandom().nextGaussian() / 20, thisUser.getRotationVector().z * 1.2 + thisUser.getRandom().nextGaussian() / 20);
            entity.setPosition(thisUser.getEyePos().add(thisUser.getRotationVector().multiply(0.6)));
        }
        return entity;
    }


}
