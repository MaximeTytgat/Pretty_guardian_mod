package com.max.prettyguardian.item.custom.projectiles;


import com.google.common.collect.ImmutableMap;
import com.max.prettyguardian.enchantment.ModEnchantments;
import com.max.prettyguardian.util.EnchantmentUtils;
import com.max.prettyguardian.worldgen.entity.ModEntityType;
import com.max.prettyguardian.worldgen.entity.projectile.CuteArrowEntity;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.Level;

import java.util.Map;
import java.util.Optional;

public class CuteArrowItem extends Item {

    public final float damage;

    public CuteArrowItem(Properties properties, float damage) {
        super(properties);
        this.damage = damage;
    }

    public CuteArrowEntity createArrow(Level world, LivingEntity shooter) {
        CuteArrowEntity arrow = new CuteArrowEntity(ModEntityType.HEART_ARROW.get(), shooter, world);
        arrow.setBaseDamage(this.damage);
        return arrow;
    }


    public boolean isInfinite(ItemStack bow) {
        Map<ResourceKey<Enchantment>, Integer> itemEnchantments = EnchantmentUtils.getEnchantments(bow);
        int enchant = itemEnchantments.getOrDefault(Enchantments.INFINITY, 0);
        return enchant > 0 && this.getClass() == CuteArrowItem.class;
    }




}
