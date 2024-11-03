package com.max.prettyguardian.item.custom.projectiles;


import com.max.prettyguardian.worldgen.entity.ModEntityType;
import com.max.prettyguardian.worldgen.entity.projectile.CuteArrowEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

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
        int enchant = net.minecraft.world.item.enchantment.EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY, bow);
        return enchant > 0 && this.getClass() == CuteArrowItem.class;
    }
}
