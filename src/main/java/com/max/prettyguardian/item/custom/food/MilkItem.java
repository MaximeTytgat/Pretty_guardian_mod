package com.max.prettyguardian.item.custom.food;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class MilkItem extends Item {
    private static final int DRINK_DURATION = 32;
    private final boolean reminder;

    public MilkItem(Item.Properties properties) {
        super(properties);
        reminder = true;
    }

    public MilkItem(Item.Properties properties, boolean itemReminder) {
        super(properties);
        reminder = itemReminder;
    }

    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack itemStack, @NotNull Level level, @NotNull LivingEntity livingEntity) {
        super.finishUsingItem(itemStack, level, livingEntity);
        if (!level.isClientSide) {
            livingEntity.removeAllEffects();
        }

        if (livingEntity instanceof ServerPlayer serverplayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger(serverplayer, itemStack);
            serverplayer.awardStat(Stats.ITEM_USED.get(this));
        }

        if (livingEntity instanceof Player player && !player.getAbilities().instabuild) {
            itemStack.shrink(1);
        }

        ItemStack reminderStack = reminder ? new ItemStack(Items.BUCKET) : itemStack;

        return itemStack.isEmpty() ? reminderStack : itemStack;
    }

    @Override
    public int getUseDuration(@NotNull ItemStack itemStack) {
        return DRINK_DURATION;
    }

    @Override
    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack itemStack) {
        return UseAnim.DRINK;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand interactionHand) {
        return ItemUtils.startUsingInstantly(level, player, interactionHand);
    }
}
