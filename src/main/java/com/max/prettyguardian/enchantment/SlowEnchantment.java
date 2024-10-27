package com.max.prettyguardian.enchantment;

import com.max.prettyguardian.util.ModTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.enchantment.Enchantment;
import org.jetbrains.annotations.NotNull;

public class SlowEnchantment extends Enchantment {
    protected SlowEnchantment() {
        super(
                Enchantment.definition(
                    ModTags.Items.SPACE_SWORD_ENCHANTABLE,
                    10,
                    2,
                    Enchantment.dynamicCost(1, 11),
                    Enchantment.dynamicCost(21, 11),
                    1,
                    FeatureFlagSet.of(FeatureFlags.BUNDLE),
                    EquipmentSlot.MAINHAND
                )
        );
    }

    @Override
    public void doPostAttack(LivingEntity attacker, @NotNull Entity target, int pLevel) {
        if (!attacker.level().isClientSide && target instanceof LivingEntity livingEntity) {
                livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 150, 4));
            }
    }
}
