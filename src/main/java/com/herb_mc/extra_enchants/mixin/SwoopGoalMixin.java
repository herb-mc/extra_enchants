package com.herb_mc.extra_enchants.mixin;

import com.herb_mc.extra_enchants.registry.ModEnchants;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.mob.PhantomEntity;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Iterator;
import java.util.List;

@Mixin(PhantomEntity.SwoopMovementGoal.class)
public abstract class SwoopGoalMixin {

    @Shadow
    private PhantomEntity field_7333;

    @Unique private static boolean found = false;

    @Inject(at = @At("TAIL"), method = "shouldContinue", cancellable = true)
    private void shouldContinue(CallbackInfoReturnable<Boolean> info){
        List<HorseEntity> list = field_7333.world.getEntitiesByClass(HorseEntity.class, field_7333.getBoundingBox().expand(8.0D), EntityPredicates.VALID_ENTITY);
        if (!list.isEmpty()) {
            Iterator HorseIter = list.iterator();

            while(HorseIter.hasNext()) {
                HorseEntity horse = (HorseEntity) HorseIter.next();
                if(EnchantmentHelper.getEquipmentLevel(ModEnchants.WARDING,horse) > 0) found = true;
            }

            if(found){
                found = false;
                info.setReturnValue(false);
            }
        }
    }

}
