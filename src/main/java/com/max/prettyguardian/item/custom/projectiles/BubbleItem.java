package com.max.prettyguardian.item.custom.projectiles;


import com.max.prettyguardian.worldgen.entity.ModEntityType;
import com.max.prettyguardian.worldgen.entity.projectile.BubbleEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class BubbleItem extends Item {

    public final float damage;

    public BubbleItem(Properties properties, float damage) {
        super(properties);
        this.damage = damage;
    }

    public BubbleEntity createArrow(Level world, LivingEntity shooter, float damage) {
        return new BubbleEntity(ModEntityType.BUBBLE.get(), shooter, world, damage);
    }
}
