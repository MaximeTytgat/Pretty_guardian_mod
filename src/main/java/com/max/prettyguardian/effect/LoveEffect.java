package com.max.prettyguardian.effect;

import com.max.prettyguardian.particle.ModParticles;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class LoveEffect extends MobEffect {
    private final Random random = new Random();
    protected LoveEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }

    @Override
    public boolean applyEffectTick(@NotNull LivingEntity livingEntity, int pAmplifier) {
        if (random.nextInt() > 4) {

            float f = random.nextFloat() * 0.5F - 0.25F;

            Vec3 look = livingEntity.getLookAngle();
            double angleRadians = Math.atan2(look.z, look.x);
            double angleDegrees = Math.toDegrees(angleRadians);

            // random number between 0 and 360
            angleDegrees += random.nextInt(360);

            float distance = 0.5F;
            double offsetX = Math.cos(Math.toRadians(angleDegrees)) * distance;
            double offsetZ = Math.sin(Math.toRadians(angleDegrees)) * distance;

            livingEntity.level().addParticle(ModParticles.LOVE_PARTICLES.get(), livingEntity.getX() + offsetX, livingEntity.getY() + 2 + f, livingEntity.getZ() + offsetZ, 0.0D, 0.0D, 0.0D);

            return super.applyEffectTick(livingEntity, pAmplifier);
        }

        return false;
    }
}
