package com.max.prettyguardian.enchantment.custom;


import com.mojang.serialization.MapCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public record SlowEnchantmentEffect() implements EnchantmentEntityEffect {

    public static final MapCodec<SlowEnchantmentEffect> slow_enchantment_effect_codec = MapCodec.unit(SlowEnchantmentEffect::new);

    @Override
    public void apply(
            @NotNull ServerLevel serverLevel,
            int enchantmentLevel,
            @NotNull EnchantedItemInUse enchantedItemInUse,
            @NotNull Entity entity,
            @NotNull Vec3 vec3
    ) {
        if (enchantmentLevel == 1 && entity instanceof LivingEntity livingEntity) {
            livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 150, 4));
        }
    }

    @Override
    public @NotNull MapCodec<? extends EnchantmentEntityEffect> codec() {
        return slow_enchantment_effect_codec;
    }
}
