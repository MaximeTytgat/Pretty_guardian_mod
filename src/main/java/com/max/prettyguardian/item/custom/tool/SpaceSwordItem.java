package com.max.prettyguardian.item.custom.tool;

import com.max.prettyguardian.enchantment.ModEnchantmentEffects;
import com.max.prettyguardian.enchantment.ModEnchantments;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import org.jetbrains.annotations.NotNull;

public class SpaceSwordItem extends SwordItem {
    public SpaceSwordItem(Tier tier, Properties properties) {
        super(tier, properties.rarity(Rarity.EPIC));
    }

    @Override
    public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
        stack.enchant(ModEnchantments.SLOW_ENCHANTMENT, 1);
        return super.onEntityItemUpdate(stack, entity);
    }

    @Override
    public boolean isFoil(@NotNull ItemStack itemStack) {
        return true;
    }

    @Override
    public boolean isEnchantable(@NotNull ItemStack itemStack) {
        return false;
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return false;
    }
}
