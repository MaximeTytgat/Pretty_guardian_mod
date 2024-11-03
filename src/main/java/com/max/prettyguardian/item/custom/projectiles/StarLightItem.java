package com.max.prettyguardian.item.custom.projectiles;


import com.max.prettyguardian.worldgen.entity.ModEntityType;
import com.max.prettyguardian.worldgen.entity.projectile.StarLightEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class StarLightItem extends Item {
    public final float damage;

    public StarLightItem(Properties properties, float damage) {
        super(properties);
        this.damage = damage;
    }

    public StarLightEntity createArrow(Level world, LivingEntity shooter, float damage) {
        return new StarLightEntity(ModEntityType.STAR_LIGHT.get(), shooter, world, damage);
    }
}
