package com.max.prettyguardian.item.custom.projectiles;


import com.max.prettyguardian.worldgen.entity.ModEntityType;
import com.max.prettyguardian.worldgen.entity.projectile.HeartEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

public class HeartItem extends Item {

    public final float damage;

    public HeartItem(Properties properties, float damage) {
        super(properties);
        this.damage = damage;
    }

    public HeartEntity createArrow(Level world, LivingEntity shooter, float damage) {
        return new HeartEntity(ModEntityType.HEART.get(), shooter, world, damage);
    }

    public boolean isInfinite(ItemStack bow) {
        int enchant = net.minecraft.world.item.enchantment.EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY, bow);
        return enchant > 0 && this.getClass() == HeartItem.class;
    }
}
