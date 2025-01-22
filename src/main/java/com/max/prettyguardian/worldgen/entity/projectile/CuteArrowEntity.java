package com.max.prettyguardian.worldgen.entity.projectile;

import com.max.prettyguardian.effect.ModEffects;
import com.max.prettyguardian.item.PrettyGuardianItem;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class CuteArrowEntity extends AbstractArrow {
    private boolean hit = false;
    public CuteArrowEntity(EntityType<CuteArrowEntity> entityType, Level world) {
        super(entityType, world);
    }

    public CuteArrowEntity(EntityType<CuteArrowEntity> entityType, LivingEntity shooter, Level world) {
        super(entityType, shooter, world, new ItemStack(PrettyGuardianItem.HEART_ARROW.get()), new ItemStack(PrettyGuardianItem.CUPIDON_BOW.get()));
    }
    @Override
    protected @NotNull ItemStack getPickupItem() {
        return new ItemStack(PrettyGuardianItem.HEART_ARROW.get());
    }

    @Override
    protected @NotNull ItemStack getDefaultPickupItem() {
        return new ItemStack(PrettyGuardianItem.HEART_ARROW.get());
    }

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        if (entityHitResult.getEntity() == this.getOwner()) {
            return;
        }
        this.hit = true;
        super.onHitEntity(entityHitResult);

        if (entityHitResult.getEntity() instanceof LivingEntity livingEntity) {
            livingEntity.addEffect(new MobEffectInstance(ModEffects.LOVE.getHolder().get(), 100, 1));
        }
    }

    @Override
    protected void onHitBlock(@NotNull BlockHitResult entityHitResult) {
        this.hit = true;
        super.onHitBlock(entityHitResult);
    }

    @Override
    public void tick() {
        super.tick();

        if(this.tickCount > 600 && this.pickup == AbstractArrow.Pickup.CREATIVE_ONLY) {
            this.discard();
        }

        if (!this.hit) {
            Vec3 vec3 = this.getDeltaMovement();
            double d5 = vec3.x;
            double d6 = vec3.y;
            double d1 = vec3.z;

            double randx = -0.5 + (random.nextDouble() * 1);
            double randy = -0.5 + (random.nextDouble() * 1);
            double randz = -0.5 + (random.nextDouble() * 1);

            this.level().addParticle(ParticleTypes.HEART,
                    randx + this.getX() + d5,
                    randy + this.getY() + d6,
                    randz + this.getZ() + d1,
                    -d5, -d6, -d1);
        }
    }
}
