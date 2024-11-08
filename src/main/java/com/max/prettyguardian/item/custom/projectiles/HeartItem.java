package com.max.prettyguardian.item.custom.projectiles;


import com.max.prettyguardian.worldgen.entity.ModEntityType;
import com.max.prettyguardian.worldgen.entity.projectile.HeartEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
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
}
