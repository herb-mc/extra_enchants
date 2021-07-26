package com.herb_mc.extra_enchants.mixin;

import com.chocohead.mm.api.ClassTinkerers;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.item.ItemGroup;
import org.apache.commons.lang3.ArrayUtils;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(ItemGroup.class)
public class ItemGroupMixin {

    @Shadow
    @Final
    @Mutable
    public static ItemGroup MISC;

    @Shadow @Final public static ItemGroup TRANSPORTATION;

    @ModifyArg(method = "<clinit>", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/item/ItemGroup$12;setEnchantments([Lnet/minecraft/enchantment/EnchantmentTarget;)Lnet/minecraft/item/ItemGroup;"))
    private static EnchantmentTarget[] additionalCombatTarget(EnchantmentTarget... targets) {
        MISC.setEnchantments(ClassTinkerers.getEnum(EnchantmentTarget.class, "HORSE_ARMOR"));
        TRANSPORTATION.setEnchantments(ClassTinkerers.getEnum(EnchantmentTarget.class, "ELYTRA"));
        return ArrayUtils.addAll(targets, ClassTinkerers.getEnum(EnchantmentTarget.class, "AXE"), ClassTinkerers.getEnum(EnchantmentTarget.class, "WEAPONS"));
    }

}
