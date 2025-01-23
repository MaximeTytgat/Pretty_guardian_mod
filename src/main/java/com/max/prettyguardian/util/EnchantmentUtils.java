package com.max.prettyguardian.util;

import com.google.common.collect.ImmutableMap;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;

import java.util.Map;
import java.util.Optional;

public class EnchantmentUtils {
    public static Map<ResourceKey<Enchantment>, Integer> getEnchantments(ItemStack itemStack) {
        ItemEnchantments list = (itemStack != null && itemStack.isEnchanted()) ? itemStack.get(DataComponents.ENCHANTMENTS) : null;

        if (list == null || list.isEmpty()) {
            return ImmutableMap.of();
        }

        ImmutableMap.Builder<ResourceKey<Enchantment>, Integer> result = ImmutableMap.builder();

        list.entrySet().forEach((entry) -> {
            Holder<Enchantment> id = entry.getKey();
            int level = entry.getIntValue();

            Optional<ResourceKey<Enchantment>> key = id.unwrapKey();

            key.ifPresent(enchantmentResourceKey -> result.put(enchantmentResourceKey, level));
        });

        return result.build();
    }
}
